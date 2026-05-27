package hre.typingstandup.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

data class OnboardingSlideUi(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String
)

private val DarkBackground = Color(0xFF0D1117)
private val DarkPanel = Color(0xFF111820)
private val Green = Color(0xFF74DE7A)
private val GreenDark = Color(0xFF1E8E3E)
private val TextWhite = Color(0xFFE8EAED)
private val TextMuted = Color(0xFFB8C0C8)
private val BorderGreen = Color(0xFF36D66B)

@Composable
fun OnboardingScreen(
    slides: List<OnboardingSlideUi>,
    onSkip: () -> Unit,
    onFinish: () -> Unit
) {
    val safeSlides = if (slides.size >= 4) slides else demoSlides()

    var currentPage by rememberSaveable { mutableIntStateOf(0) }
    var selectedMode by rememberSaveable { mutableStateOf("CLASICO") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        when (currentPage) {
            0 -> WelcomePage(
                slide = safeSlides[0],
                onStartConfig = { currentPage = 1 },
                onSkip = onSkip
            )

            1 -> MissionPage(
                slide = safeSlides[1],
                currentPage = currentPage,
                totalPages = safeSlides.size,
                onNext = { currentPage = 2 },
                onBack = { currentPage = 0 }
            )

            2 -> ProtocolPage(
                slide = safeSlides[2],
                currentPage = currentPage,
                totalPages = safeSlides.size,
                selectedMode = selectedMode,
                onModeSelected = { selectedMode = it },
                onNext = { currentPage = 3 },
                onBack = { currentPage = 1 }
            )

            3 -> RankingPage(
                slide = safeSlides[3],
                currentPage = currentPage,
                totalPages = safeSlides.size,
                onFinish = onFinish,
                onBack = { currentPage = 2 }
            )
        }
    }
}

@Composable
private fun WelcomePage(
    slide: OnboardingSlideUi,
    onStartConfig: () -> Unit,
    onSkip: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(118.dp))

        RemoteImage(
            imageUrl = slide.imageUrl,
            contentDescription = slide.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = slide.title,
            color = TextWhite,
            fontSize = 24.sp,
            lineHeight = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = slide.description,
            color = TextMuted,
            fontSize = 13.sp,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(38.dp))

        PrimaryTerminalButton(
            text = "INICIAR CONFIGURACION",
            onClick = onStartConfig
        )

        Spacer(modifier = Modifier.height(14.dp))

        SecondaryTerminalButton(
            text = "OMITIR CONFIGURACION",
            onClick = onSkip
        )
    }
}

@Composable
private fun MissionPage(
    slide: OnboardingSlideUi,
    currentPage: Int,
    totalPages: Int,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        ProgressHeader(
            label = "PROGRESS",
            currentPage = currentPage,
            totalPages = totalPages
        )

        Spacer(modifier = Modifier.height(28.dp))

        RemoteImage(
            imageUrl = slide.imageUrl,
            contentDescription = slide.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(255.dp)
        )

        Spacer(modifier = Modifier.height(34.dp))

        Text(
            text = slide.title.uppercase(),
            color = Green,
            fontSize = 15.sp,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 1.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color(0xFF1F2A33),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(18.dp)
        ) {
            Text(
                text = slide.description,
                color = TextMuted,
                fontSize = 14.sp,
                lineHeight = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        PrimaryTerminalButton(
            text = "Siguiente >",
            onClick = onNext
        )

        Spacer(modifier = Modifier.height(10.dp))

        SecondaryTerminalButton(
            text = "< Atrás",
            onClick = onBack
        )

        Spacer(modifier = Modifier.height(28.dp))
    }
}

@Composable
private fun ProtocolPage(
    slide: OnboardingSlideUi,
    currentPage: Int,
    totalPages: Int,
    selectedMode: String,
    onModeSelected: (String) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .border(2.dp, BorderGreen)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        TopBar(
            title = "TYPINGSTANDUP",
            currentPage = currentPage,
            totalPages = totalPages
        )

        Spacer(modifier = Modifier.height(42.dp))

        RemoteImage(
            imageUrl = slide.imageUrl,
            contentDescription = slide.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(267.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = slide.title,
            color = TextWhite,
            fontSize = 27.sp,
            lineHeight = 34.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = slide.description,
            color = TextMuted,
            fontSize = 14.sp,
            lineHeight = 21.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 6.dp)
        )

        Spacer(modifier = Modifier.height(28.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ModeCard(
                title = "CLÁSICO",
                icon = "<>",
                selected = selectedMode == "CLASICO",
                onClick = { onModeSelected("CLASICO") },
                modifier = Modifier.weight(1f)
            )

            ModeCard(
                title = "WEEKLY",
                icon = "▣",
                selected = selectedMode == "WEEKLY",
                onClick = { onModeSelected("WEEKLY") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SecondarySmallButton(
                text = "< ATRÁS",
                onClick = onBack,
                modifier = Modifier.weight(0.75f)
            )

            PrimarySmallButton(
                text = "SIGUIENTE ▻",
                onClick = onNext,
                modifier = Modifier.weight(1.45f)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun RankingPage(
    slide: OnboardingSlideUi,
    currentPage: Int,
    totalPages: Int,
    onFinish: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        TopBar(
            title = "TypingStandup",
            currentPage = currentPage,
            totalPages = totalPages
        )

        Spacer(modifier = Modifier.height(38.dp))

        RemoteImage(
            imageUrl = slide.imageUrl,
            contentDescription = slide.title,
            modifier = Modifier
                .width(200.dp)
                .height(327.dp)
        )

        Spacer(modifier = Modifier.height(45.dp))

        Text(
            text = "> ${slide.title} ▌",
            color = TextWhite,
            fontSize = 27.sp,
            lineHeight = 34.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = slide.description,
            color = TextMuted,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onFinish,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = GreenDark,
                contentColor = TextWhite
            )
        ) {
            Text(
                text = "Iniciar aplicacion ✓",
                fontSize = 15.sp,
                fontFamily = FontFamily.Monospace
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        SecondaryTerminalButton(
            text = "< Atrás",
            onClick = onBack
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun RemoteImage(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(RoundedCornerShape(7.dp))
            .border(
                width = 1.dp,
                color = Color(0xFF255D3C),
                shape = RoundedCornerShape(7.dp)
            )
            .background(DarkPanel)
    )
}

@Composable
private fun ProgressHeader(
    label: String,
    currentPage: Int,
    totalPages: Int
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                color = TextWhite,
                fontFamily = FontFamily.Monospace,
                fontSize = 14.sp,
                letterSpacing = 4.sp
            )

            Text(
                text = "${(currentPage + 1).toString().padStart(2, '0')} / ${totalPages.toString().padStart(2, '0')}",
                color = Green,
                fontFamily = FontFamily.Monospace,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(Color(0xFF30363D))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth((currentPage + 1).toFloat() / totalPages.toFloat())
                    .fillMaxHeight()
                    .background(Green)
            )
        }
    }
}

@Composable
private fun TopBar(
    title: String,
    currentPage: Int,
    totalPages: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "▻ $title",
            color = Green,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .width(24.dp)
                .height(4.dp)
                .background(Green, RoundedCornerShape(4.dp))
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "${currentPage + 1}/$totalPages",
            color = TextMuted,
            fontFamily = FontFamily.Monospace,
            fontSize = 12.sp,
            modifier = Modifier
                .background(Color(0xFF1C222A), RoundedCornerShape(4.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Composable
private fun PrimaryTerminalButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Green,
            contentColor = Color(0xFF07120B)
        )
    ) {
        Text(
            text = text,
            fontFamily = FontFamily.Monospace,
            fontSize = 13.sp
        )
    }
}

@Composable
private fun SecondaryTerminalButton(
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, Color(0xFF26323B)),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = TextMuted
        )
    ) {
        Text(
            text = text,
            fontFamily = FontFamily.Monospace,
            fontSize = 13.sp
        )
    }
}

@Composable
private fun PrimarySmallButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(41.dp),
        shape = RoundedCornerShape(7.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = GreenDark,
            contentColor = Color(0xFF07120B)
        )
    ) {
        Text(
            text = text,
            fontFamily = FontFamily.Monospace,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun SecondarySmallButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(41.dp),
        shape = RoundedCornerShape(7.dp),
        border = BorderStroke(1.dp, Color(0xFF26323B)),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = TextMuted
        )
    ) {
        Text(
            text = text,
            fontFamily = FontFamily.Monospace,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun ModeCard(
    title: String,
    icon: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = if (selected) Green else Color(0xFF1A2530)
    val background = if (selected) Color(0xFF102418) else Color(0xFF111820)

    Column(
        modifier = modifier
            .height(90.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(background)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(7.dp)
            )
            .clickable { onClick() }
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFF173B25), RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = icon,
                color = Green,
                fontFamily = FontFamily.Monospace,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            color = TextMuted,
            fontFamily = FontFamily.Monospace,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

private fun demoSlides(): List<OnboardingSlideUi> {
    return listOf(
        OnboardingSlideUi(
            id = 1,
            title = "Bienvenido a\nTyping Standup",
            description = "Inicializa tu identidad como desarrollador y domina el teclado con la precisión de un compilador de alto rendimiento.",
            imageUrl = "https://picsum.photos/seed/typing-code/900/600"
        ),
        OnboardingSlideUi(
            id = 2,
            title = "MISIÓN: VELOCIDAD Y PRECISIÓN",
            description = "Tu objetivo es escribir las palabras que aparecen en pantalla correctamente y lo más rápido posible. Cada tecla cuenta.",
            imageUrl = "https://picsum.photos/seed/typing-keyboard/900/600"
        ),
        OnboardingSlideUi(
            id = 3,
            title = "Protocolos de\nEjecución",
            description = "Elige entre el modo Clásico para mejorar tu frase o el Weekly Standup para enfrentarte a desafíos semanales únicos.",
            imageUrl = "https://picsum.photos/seed/typing-protocols/900/600"
        ),
        OnboardingSlideUi(
            id = 4,
            title = "Sube en el\nRanking Global",
            description = "Compite contra programadores de todo el mundo. Demuestra quién tiene el mejor tiempo de respuesta en el stream de datos.",
            imageUrl = "https://picsum.photos/seed/typing-ranking/900/600"
        )
    )
}