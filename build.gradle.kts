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
        withJava()
    }
    js()
    sourceSets {
        val commonMain by getting
        val commonTest by getting
        val jvmMain by getting{
            dependencies {
                implementation("org.fusesource.jansi:jansi:2.3.2")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}