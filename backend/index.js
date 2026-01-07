const { google } = require('googleapis');
const { OAuth2Client } = require('google-auth-library');

// The client ID of the WEB application from your Google Cloud console.
// Pass this as an environment variable during deployment.
const WEB_CLIENT_ID = process.env.WEB_CLIENT_ID;

// The ID of your Google Sheet.
// Pass this as an environment variable during deployment.
const SPREADSHEET_ID = process.env.SPREADSHEET_ID;

const oAuth2Client = new OAuth2Client(WEB_CLIENT_ID);

/**
 * An HTTP-triggered Cloud Function to handle new place submissions.
 *
 * @param {Object} req The HTTP request object.
 * @param {Object} res The HTTP response object.
 */
exports.submitPlace = async (req, res) => {
    // Set CORS headers for preflight requests
    res.set('Access-Control-Allow-Origin', '*');
    if (req.method === 'OPTIONS') {
        res.set('Access-Control-Allow-Methods', 'POST');
        res.set('Access-Control-Allow-Headers', 'Authorization, Content-Type');
        res.set('Access-Control-Max-Age', '3600');
        res.status(204).send('');
        return;
    }

    // 1. The Bouncer Checks the ID
    const authHeader = req.header('Authorization');
    if (!authHeader || !authHeader.startsWith('Bearer ')) {
        console.error('No bearer token provided.');
        res.status(401).send('Unauthorized: No token provided.');
        return;
    }
    const idToken = authHeader.split('Bearer ')[1];

    let payload;
    try {
        const ticket = await oAuth2Client.verifyIdToken({
            idToken: idToken,
            audience: WEB_CLIENT_ID,
        });
        payload = ticket.getPayload();
    } catch (error) {
        console.error('Token verification failed:', error);
        res.status(401).send('Unauthorized: Invalid token.');
        return;
    }

    // 2. The Bouncer Inspects the Request
    const { name, description, address, category } = req.body;
    if (!name || !description || !address || !category) {
        res.status(400).send('Bad Request: Missing required fields.');
        return;
    }

    // 3. The Bouncer Does the Work
    try {
        const auth = new google.auth.GoogleAuth({
            scopes: ['https://www.googleapis.com/auth/spreadsheets'],
        });
        const sheets = google.sheets({ version: 'v4', auth });

        const newRow = [
            `SUB_${new Date().getTime()}`, // A crude unique ID
            name,
            description,
            address,
            category,
            payload.sub, // The user's verified Google ID
            new Date().toISOString(),
            'pending'
        ];

        await sheets.spreadsheets.values.append({
            spreadsheetId: SPREADSHEET_ID,
            range: 'place_submissions!A1', // Appends to the first empty row
            valueInputOption: 'USER_ENTERED',
            resource: {
                values: [newRow],
            },
        });

        // 4. The Bouncer Nods
        res.status(200).send({ message: 'Submission received.' });

    } catch (error) {
        console.error('The API returned an error: ' + error);
        res.status(500).send('Internal Server Error');
    }
};