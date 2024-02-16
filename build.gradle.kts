plugins{
    kotlin("multiplatform") version "1.9.0"
}
allprojects{
    repositories{
        mavenCentral()
    }
}
kotlin{
    jvm{
        withJava()
    }
    js()
    sourceSets{
        val commonMain by getting
        val commonTest by getting
        val jvmMain by getting
        val jvmTest by getting{
            dependencies{
                implementation(kotlin("test"))
            }
        }
    }
}