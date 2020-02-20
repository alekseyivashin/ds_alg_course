java -Xmx512M -Xms32M -noverify -cp . -cp %1\lib\kotlin-preloader.jar ^
org.jetbrains.kotlin.preloading.Preloader -cp %1\lib\kotlin-compiler.jar ^
org.jetbrains.kotlin.cli.jvm.K2JVMCompiler src/week_1_1.kt -include-runtime -d week_1_1.jar