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
        val jvmMain by getting
        val jvmTest by getting
    }
}