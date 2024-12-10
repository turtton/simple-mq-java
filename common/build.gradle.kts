import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.jetbrains.annotations)
    implementation(libs.gson)
    implementation(libs.postgresql)
    implementation(libs.redis)

    testImplementation(libs.junit.jupiter)
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        showStandardStreams = true
        events("passed", "skipped", "passed", "failed")
        exceptionFormat = TestExceptionFormat.FULL
    }
}