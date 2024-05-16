// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        //navigation
        //kapt
        classpath ("com.android.tools.build:gradle:8.4.0")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
        // hilt
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.48.1")

    }
}