
import { GoogleGenAI } from "@google/genai";

export async function getLocationProphecy(locationName: string, arcana: string): Promise<string> {
  const settingsStr = localStorage.getItem('neon_occult_settings');
  const apiKey = settingsStr ? JSON.parse(settingsStr).geminiApiKey : null;

  if (!apiKey) return "The spirits are silent, but the neon hums your name.";

  const ai = new GoogleGenAI(apiKey);

  try {
    const model = ai.getGenerativeModel({ model: "gemini-1.5-flash" });
    const result = await model.generateContent({
      contents: [{ role: "user", parts: [{ text: `You are an occult guide to New Orleans. Write a short, cryptic, poetic "spiritual reading" (2 sentences max) for a tourist visiting ${locationName}, which is associated with ${arcana} Tarot card. Use witchy, glitchy language, neon-noir metaphors, and techno-occult terminology. Ensure the tone is evocative and mysterious.` }] }],
      generationConfig: {
        temperature: 0.9,
        topP: 0.95,
      }
    });
    const response = await result.response;
    return response.text() || "The spirits are silent, but the neon hums your name.";
  } catch (error) {
    console.error("Gemini Error:", error);
    return "The veil is thin; seek the truth within the shadows.";
  }
}
