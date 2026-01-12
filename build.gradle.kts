plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.seleniumhq.selenium:selenium-java:4.39.0")
    testImplementation("org.testng:testng:7.11.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.20.0")
    testImplementation("io.qameta.allure:allure-testng:2.21.0")
    testImplementation("io.rest-assured:rest-assured:6.0.0")
}

tasks.test {
    useTestNG()
}

tasks.register<Test>("signupAndLoginTests") {
    testClassesDirs = sourceSets.test.get().output.classesDirs
    classpath = sourceSets.test.get().runtimeClasspath

    useTestNG {
        includeGroups("signupAndLogin")
    }
}

tasks.register<Test>("smokeTests") {
    testClassesDirs = sourceSets.test.get().output.classesDirs
    classpath = sourceSets.test.get().runtimeClasspath

    useTestNG {
        includeGroups("smoke")
    }
}

tasks.register<Test>("ApiTests") {
    testClassesDirs = sourceSets.test.get().output.classesDirs
    classpath = sourceSets.test.get().runtimeClasspath

    useTestNG {
        includeGroups("ApiTests")
    }
}

