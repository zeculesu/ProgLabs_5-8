plugins {
    kotlin("multiplatform") version "1.9.0"
    java
}
tasks.withType<JavaCompile>{
    options.encoding="utf-8"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

kotlin {
    jvm {
        jvmToolchain(17)
        withJava()
        compilations.all{kotlinOptions.jvmTarget="17"}
        tasks.register<Jar>("fatJar"){
            duplicatesStrategy = DuplicatesStrategy.EXCLUDE
            group = "build"
            this.archiveBaseName = "proglab6client"
            manifest{
                attributes["Main-Class"] = "io.github.zeculesu.itmo.prog5.MainClient"
            }
            from(
                compilations["main"].runtimeDependencyFiles
                    .filter {f -> f.exists() }
                    .map{ d -> if (d.isDirectory) d else zipTree(d)}
            )
            with(tasks.jar.get() as CopySpec)
            destinationDirectory = rootProject.projectDir.resolve("out")
        }
    }
    js()
    sourceSets {
        val commonMain by getting
        val commonTest by getting
        val jvmMain by getting{
            dependencies{
                implementation("org.jline:jline:3.20.0")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))

            }
        }
    }
}