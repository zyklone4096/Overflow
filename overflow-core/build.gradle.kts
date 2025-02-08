plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("org.jetbrains.dokka")
    id("me.him188.kotlin-jvm-blocking-bridge")
    id("org.ajoberstar.grgit")
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

val miraiVersion = extra("miraiVersion") ?: "2.16.0"

dependencies {
    compileOnly("net.mamoe:mirai-console:$miraiVersion")
    implementation("net.mamoe:mirai-core-api:$miraiVersion")
    implementation("net.mamoe:mirai-core-utils:$miraiVersion")
    implementation("org.java-websocket:Java-WebSocket:1.5.7")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("me.him188:kotlin-jvm-blocking-bridge-runtime:3.0.0-180.1")

    fun netty(s: String): Dependency? = implementation("io.netty:netty-$s:4.1.90.Final")
    netty("codec-http")
    netty("codec-socks")
    netty("transport")

    api(project(":onebot"))
    api(project(":overflow-core-api"))

    testImplementation("com.google.code.gson:gson:2.10.1")
    testImplementation("org.java-websocket:Java-WebSocket:1.5.7")
    testImplementation("net.mamoe:mirai-console")
    testImplementation("net.mamoe:mirai-console-terminal")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    register<JavaExec>("runConsole") {
        mainClass.set("RunConsoleKt")
        workingDir = File(project.projectDir, "run")
        classpath = sourceSets.test.get().runtimeClasspath
        standardInput = System.`in`
    }
}
