$srcClases = "src\application\*.java src\application\models\*.java src\application\parser\*.java src\application\utils\*.java "
$libFiles = ""
$compile = "javac --release 23 -Werror -Xlint:all -d .\bin\ $srcClases"
$createJar = "jar -cfm JavaParsing.jar Manifesto.txt -C .\bin\ ."
$javaCommand = "java -jar JavaParsing.jar"
$runCommand = "$compile" + " && " + "$createJar" + " && " +"$javaCommand"
Invoke-Expression $runCommand 
