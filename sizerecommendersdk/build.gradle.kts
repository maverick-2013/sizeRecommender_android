import java.util.Properties

plugins {
    id("com.android.library")
    id("maven-publish")
    alias(libs.plugins.kotlin.android)
}

val githubProperties = Properties()
githubProperties.load(project.rootProject.file("github.properties").reader())

val versionName = "1.0"
val artifactId = "sizeRecommenderSDK"
val githubUsername: String = githubProperties.getProperty("gpr.usr") ?: System.getenv("GPR_USER")
val githubPassword: String = githubProperties.getProperty("gpr.key") ?: System.getenv("GPR_KEY")


publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/${githubUsername}/sizeRecommender_android")
            credentials {
                username = githubUsername
                password = githubPassword
            }
        }
    }
    publications {
        create<MavenPublication>("gpr") {
            run {
                groupId = "com.otimonov"
                artifactId = artifactId
                version = versionName
                artifact("$buildDir/outputs/aar/${artifactId}-debug.aar")
            }
        }
    }
}

android {
    namespace = "com.otimonov.sizerecommendersdk"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}