package com.hereliesaz.all24.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// TODO: Add Roboto and Roboto Slab font families to the project.
// For now, we will use the default serif and sans-serif fonts.

// Emphasized Scale (for high-impact text)
val EmphasizedTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Serif, // Should be Roboto Slab
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Serif, // Should be Roboto Slab
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Serif, // Should be Roboto Slab
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
)

// Baseline Scale (for body copy and UI labels)
val BaselineTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif, // Should be Roboto
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif, // Should be Roboto
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.SansSerif, // Should be Roboto
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

// The main Typography object for the app, defaulting to the baseline scale.
val Typography = BaselineTypography
