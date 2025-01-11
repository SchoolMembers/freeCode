// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.chaquo.python:gradle:16.0.0")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    id("com.chaquo.python") version "16.0.0" apply false
}