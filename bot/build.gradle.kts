plugins {
    application
    kotlin("jvm")
}

application {
    mainClassName = "com.github.dod.doddy.Main"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":help"))

    implementation(project(":reputation"))
    implementation(project(":warnings"))
    implementation(project(":bans"))
    implementation(project(":setup"))
    implementation(project(":votes"))
    implementation(project(":rules"))
    implementation(project(":redirections"))
    implementation(project(":announcements"))
    implementation(project(":questions"))
    implementation(project(":ranks"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
}