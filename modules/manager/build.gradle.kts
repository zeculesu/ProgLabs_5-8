plugins{
    kotlin("multiplatform")
}
kotlin{
    jvm{
        withJava()
    }
    js()
    sourceSets{
        val commonMain by getting
        val commonTest by getting
        val jvmMain by getting {
            dependencies {
                implementation(project(":modules:data"))
                implementation(project(":modules:user-interface"))
            }
        }
        val jvmTest by getting
    }
}