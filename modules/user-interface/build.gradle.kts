plugins {
    kotlin("multiplatform")
}
kotlin {
    jvm {
        withJava()
    }
    js()
    sourceSets {
        val commonMain by getting
        val commonTest by getting
        val jvmMain by getting {
            dependencies {
                implementation(project(":modules:manager"))
                implementation(project(":modules:data"))
            }
        }
        val jvmTest by getting{
            dependencies {
                implementation(kotlin("test"))
                implementation(project(":modules:manager"))
            }
        }
    }
}
