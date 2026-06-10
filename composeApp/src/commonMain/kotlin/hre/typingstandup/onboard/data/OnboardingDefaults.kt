package hre.typingstandup.onboard.data

import hre.typingstandup.onboard.presentation.screen.OnboardingSlideUi

const val ONBOARDING_CONFIG_KEY = "onboarding_config"

val DEFAULT_ONBOARDING_JSON = """
[
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
      "es": "https://picsum.photos/seed/typing-code/900/600",
      "en": "https://picsum.photos/seed/typing-code/900/600",
      "fr": "https://picsum.photos/seed/typing-code/900/600"
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
      "es": "https://picsum.photos/seed/typing-keyboard/900/600",
      "en": "https://picsum.photos/seed/typing-keyboard/900/600",
      "fr": "https://picsum.photos/seed/typing-keyboard/900/600"
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
      "es": "https://picsum.photos/seed/typing-protocols/900/600",
      "en": "https://picsum.photos/seed/typing-protocols/900/600",
      "fr": "https://picsum.photos/seed/typing-protocols/900/600"
    }
  },
  {
    "id": 4,
    "title": {
      "es": "Sube en el Ranking Global",
      "en": "Climb the Global Ranking",
      "fr": "Montez dans le classement global"
    },
    "description": {
      "es": "Compite contra programadores de todo el mundo y demuestra quién tiene el mejor tiempo de respuesta en el stream de datos.",
      "en": "Compete against programmers worldwide and prove who has the fastest response time in the data stream.",
      "fr": "Affrontez des programmeurs du monde entier et démontrez qui possède le meilleur temps de réponse dans le flux de données."
    },
    "image_url": {
      "es": "https://picsum.photos/seed/typing-ranking/900/600",
      "en": "https://picsum.photos/seed/typing-ranking/900/600",
      "fr": "https://picsum.photos/seed/typing-ranking/900/600"
    }
  }
]
""".trimIndent()

fun defaultOnboardingSlides(): List<OnboardingSlideUi> {
    return parseOnboardingJson(DEFAULT_ONBOARDING_JSON)
}