package hre.typingstandup.onboard.presentation.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PrimaryTerminalButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.Companion
            .fillMaxWidth()
            .height(45.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color(0xFF07120B)
        )
    ) {
        Text(
            text = text,
            fontFamily = FontFamily.Companion.Monospace,
            fontSize = 13.sp
        )
    }
}

@Composable
fun SecondaryTerminalButton(
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.Companion
            .fillMaxWidth()
            .height(45.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, Color(0xFF26323B)),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Text(
            text = text,
            fontFamily = FontFamily.Companion.Monospace,
            fontSize = 13.sp
        )
    }
}

@Composable
fun PrimarySmallButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.Companion
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(41.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(7.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = Color(0xFF07120B)
        )
    ) {
        Text(
            text = text,
            fontFamily = FontFamily.Companion.Monospace,
            fontSize = 12.sp
        )
    }
}

@Composable
fun SecondarySmallButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.Companion
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(41.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(7.dp),
        border = BorderStroke(1.dp, Color(0xFF26323B)),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Text(
            text = text,
            fontFamily = FontFamily.Companion.Monospace,
            fontSize = 12.sp
        )
    }
}