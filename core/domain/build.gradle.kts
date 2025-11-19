plugins {
    alias(libs.plugins.kotlin.jvm)
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.rxjava3)
    implementation(libs.javax.inject)
    testImplementation(libs.junit)
    testImplementation(libs.truth)
}

