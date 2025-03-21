plugins {
    kotlin("jvm")
    id("org.jetbrains.dokka")
//    id("me.him188.kotlin-jvm-blocking-bridge")
}

tasks.register<Jar>("dokkaJavadocJar") {
    group = "documentation"
    dependsOn(tasks.dokkaJavadoc)
    from(tasks.dokkaJavadoc.flatMap { it.outputDirectory })
    archiveClassifier.set("javadoc")
}

setupMavenCentralPublication {
    artifact(tasks.kotlinSourcesJar)
    artifact(tasks.getByName("dokkaJavadocJar"))
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    api("com.google.code.gson:gson:2.10.1")
    api("org.slf4j:slf4j-api:2.0.5")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.4")
    api("me.him188:kotlin-jvm-blocking-bridge-runtime:3.0.0-180.1")
    api("org.java-websocket:Java-WebSocket:1.5.7")
    implementation("org.jetbrains:annotations:24.0.1")

    annotationProcessor("org.java-websocket:Java-WebSocket:1.5.7")
    testCompileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.4")
    testImplementation("ch.qos.logback:logback-classic:1.4.14")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation(kotlin("test"))
}
