package hre.typingstandup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import hre.typingstandup.data.parseOnboardingJson
import hre.typingstandup.ui.OnboardingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val showHome = remember { mutableStateOf(false) }

                val slides = remember {
                    parseOnboardingJson(DEFAULT_ONBOARDING_JSON)
                }

                if (showHome.value) {
                    HomeTemporal()
                } else {
                    OnboardingScreen(
                        slides = slides,
                        onSkip = {
                            showHome.value = true
                        },
                        onFinish = {
                            showHome.value = true
                        }
                    )
                }
            }
        }
    }
}

@androidx.compose.runtime.Composable
fun HomeTemporal() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("HOME - Aplicación iniciada")
    }
}

private val DEFAULT_ONBOARDING_JSON = """
{
  "onboarding_config": [
    {
      "id": 1,
      "title": {
        "es": "Bienvenido a Typing Standup",
        "en": "Welcome to Typing Standup",
        "fr": "Bienvenue sur Typing Standup"
      },
      "description": {
        "es": "Inicializa tu identidad como desarrollador y domina el teclado con la precisión de un compilador de alto rendimiento.",
        "en": "Initialize your developer identity and master the keyboard with the precision of a high-performance compiler.",
        "fr": "Initialisez votre identité de développeur et maîtrisez le clavier avec la précision d'un compilateur haute performance."
      },
      "image_url": {
        "es": "https://images.unsplash.com/photo-1515879218367-8466d910aaa4",
        "en": "https://images.unsplash.com/photo-1515879218367-8466d910aaa4",
        "fr": "https://images.unsplash.com/photo-1515879218367-8466d910aaa4"
      }
    },
    {
      "id": 2,
      "title": {
        "es": "Misión: Velocidad y Precisión",
        "en": "Mission: Speed and Accuracy",
        "fr": "Mission : Vitesse et précision"
      },
      "description": {
        "es": "Tu objetivo es escribir las palabras que aparecen en pantalla correctamente y lo más rápido posible. Cada tecla cuenta.",
        "en": "Your goal is to type the words shown on screen correctly and as fast as possible. Every keystroke matters.",
        "fr": "Votre objectif est d'écrire correctement les mots affichés à l'écran le plus rapidement possible. Chaque touche compte."
      },
      "image_url": {
        "es": "https://images.unsplash.com/photo-1516321318423-f06f85e504b3",
        "en": "https://images.unsplash.com/photo-1516321318423-f06f85e504b3",
        "fr": "https://images.unsplash.com/photo-1516321318423-f06f85e504b3"
      }
    },
    {
      "id": 3,
      "title": {
        "es": "Modos de Ejecución",
        "en": "Execution Modes",
        "fr": "Modes d'exécution"
      },
      "description": {
        "es": "Elige entre el modo clásico para mejorar tu base o el Weekly Standup para enfrentarte a desafíos semanales únicos.",
        "en": "Choose between classic mode to improve your fundamentals or Weekly Standup to face unique weekly challenges.",
        "fr": "Choisissez entre le mode classique pour améliorer vos bases ou Weekly Standup pour relever des défis hebdomadaires uniques."
      },
      "image_url": {
        "es": "https://images.unsplash.com/photo-1517694712202-14dd9538aa97",
        "en": "https://images.unsplash.com/photo-1517694712202-14dd9538aa97",
        "fr": "https://images.unsplash.com/photo-1517694712202-14dd9538aa97"
      }
    },
    {
      "id": 4,
      "title": {
        "es": "Sube en el Ranking",
        "en": "Climb the Ranking",
        "fr": "Montez dans le classement"
      },
      "description": {
        "es": "Compite contra programadores de todo el mundo y demuestra quién tiene el mejor tiempo de respuesta en el stream de datos.",
        "en": "Compete against programmers worldwide and prove who has the fastest response time in the data stream.",
        "fr": "Affrontez des programmeurs du monde entier et démontrez qui possède le meilleur temps de réponse dans le flux de données."
      },
      "image_url": {
        "es": "https://images.unsplash.com/photo-1526379095098-d400fd0bf935",
        "en": "https://images.unsplash.com/photo-1526379095098-d400fd0bf935",
        "fr": "https://images.unsplash.com/photo-1526379095098-d400fd0bf935"
      }
    }
  ]
}
""".trimIndent()