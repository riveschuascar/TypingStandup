import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.net.URI
import java.io.InputStream

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.googleGmsGoogleServices)
    // Room - Database
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.room)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)
            implementation("com.google.firebase:firebase-config:22.0.1")

            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)

            implementation(libs.ktor.client.okhttp)

            implementation(libs.androidx.lifecycle.process)

            implementation(libs.androidx.work.runtime.ktx)
            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.sqlite.bundled)

            implementation(libs.kotlinx.coroutines.play.services)
        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            // Koin - DI
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            //implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.navigation.compose)
            // ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            // Coil - Async Images
            implementation(libs.coil.compose)
            implementation(libs.coil.network)
            // Design System
            implementation(project(":designsystem"))
            // Room - Database
            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.sqlite.bundled)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
        }
        androidInstrumentedTest.dependencies {
            implementation(libs.kotlin.testJunit)
            implementation(libs.androidx.testExt.junit)
            implementation(libs.androidx.runner)
            implementation(libs.androidx.espresso.core)
            implementation(libs.ui.test.junit4)
        }
    }
}

android {
    namespace = "hre.typingstandup"
    compileSdkVersion(libs.versions.android.compileSdk.get().toInt())

    defaultConfig {
        applicationId = "hre.typingstandup"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(libs.compose.uiTooling)
    debugImplementation(libs.ui.test.manifest)
    // Firebase
    implementation(libs.firebase.database)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.remote.config)
    implementation(libs.firebase.analytics)
    // Koin - DI
    implementation(libs.koin.core)
    // Coil
    add("androidMainImplementation", "io.coil-kt.coil3:coil-compose:3.4.0")
    add("androidMainImplementation", "io.coil-kt.coil3:coil-network-okhttp:3.4.0")
    // Room - Database
    add("kspAndroid", libs.androidx.room.compiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}

tasks.register("downloadLocoStrings") {
    group = "localization"
    description = "Downloads strings from Loco (Localise.biz) for all supported languages."

    val apiKey = "uM6rm68FB2SB_699WImn0qomCpNMxGLC"
    val baseUrl = "https://localise.biz/api/export/locale"
    val locales = mapOf(
        "en" to "strings-en.xml",
        "es" to "strings-es.xml",
        "fr" to "strings-fr.xml"
    )
    val outputDir = file("src/commonMain/composeResources/values")

    doLast {
        if (!outputDir.exists()) {
            outputDir.mkdirs()
        }
        locales.forEach { (locale, fileName) ->
            val url = "$baseUrl/$locale.xml?key=$apiKey&format=android"
            println("[LOCO] Downloading $locale translations...")
            val outputFile = File(outputDir, fileName)
            try {
                URI(url).toURL().openStream().use { input: InputStream ->
                    outputFile.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }
                println("[LOCO] Successfully updated: $fileName")
            } catch (e: Exception) {
                logger.error("[LOCO] Failed to download $locale: ${e.message}")
            }
        }
    }
}

// Automatically download strings when generating resources or building for Android
tasks.matching { it.name.startsWith("generateComposeResClass") }.configureEach {
    dependsOn("downloadLocoStrings")
}

tasks.matching { it.name == "preBuild" }.configureEach {
    dependsOn("downloadLocoStrings")
}
