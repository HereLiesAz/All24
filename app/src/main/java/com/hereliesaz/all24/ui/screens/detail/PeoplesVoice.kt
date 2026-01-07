package com.hereliesaz.all24.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun PeoplesVoice(
    quote: String,
    attribution: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = quote,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = attribution,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
