$srcClases = "src\*.java src\Application\Models\*.java src\Application\Parser\*.java src\Application\Utils\*.java "
$libFiles = ""
$compile = "javac --release 23 -Werror -Xlint:all -d .\bin\ $srcClases"
$createJar = "jar -cfm JavaParsing.jar Manifesto.txt -C .\bin\ ."
$javaCommand = "java -jar JavaParsing.jar"
$runCommand = "$compile" + " && " + "$createJar" + " && " +"$javaCommand"
Invoke-Expression $runCommand 
