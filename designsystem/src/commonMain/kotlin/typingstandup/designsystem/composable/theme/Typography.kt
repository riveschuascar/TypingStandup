package typingstandup.designsystem.composable.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun appTypography(): Typography {
    val jetBrainsMono = jetBrainsMono()
    val geistMono = geistMono()

    return Typography(
        displayLarge = TextStyle(
            fontFamily = jetBrainsMono,
            fontSize = 57.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 64.sp,
            letterSpacing = (-1.14).sp
        ),
        displayMedium = TextStyle(
            fontFamily = jetBrainsMono,
            fontSize = 45.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 52.sp,
            letterSpacing = (-0.90).sp
        ),
        displaySmall = TextStyle(
            fontFamily = jetBrainsMono,
            fontSize = 36.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 44.sp,
            letterSpacing = (-0.72).sp
        ),

        headlineLarge = TextStyle(
            fontFamily = jetBrainsMono,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 38.sp,
            letterSpacing = (-0.64).sp
        ),
        headlineMedium = TextStyle(
            fontFamily = jetBrainsMono,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 31.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = jetBrainsMono,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 28.sp
        ),

        titleLarge = TextStyle(
            fontFamily = jetBrainsMono,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 28.sp,
            letterSpacing = (-0.44).sp
        ),
        titleMedium = TextStyle(
            fontFamily = geistMono,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 24.sp,
            letterSpacing = (0.15).sp
        ),
        titleSmall = TextStyle(
            fontFamily = geistMono,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp,
            letterSpacing = (0.10).sp
        ),

        bodyLarge = TextStyle(
            fontFamily = geistMono,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 29.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = geistMono,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 24.sp
        ),
        bodySmall = TextStyle(
            fontFamily = geistMono,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 21.sp
        ),

        labelLarge = TextStyle(
            fontFamily = jetBrainsMono,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp,
            letterSpacing = (0.10).sp
        ),
        labelMedium = TextStyle(
            fontFamily = jetBrainsMono,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 14.sp
        ),
        labelSmall = TextStyle(
            fontFamily = jetBrainsMono,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 12.sp
        )
    )
}