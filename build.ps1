$srcClases = ".\src\*.java .\src\Application\Operations\*.java .\src\Application\Utils\*.java .\src\Application\Models\*.java "
$libFiles = ""
$compile = "javac -Werror -Xlint:all -d .\bin\ $srcClases"
$createJar = "jar -cfm JavaParsing.jar Manifesto.txt -C .\bin\ ."
$javaCommand = "java -jar JavaParsing.jar"
$runCommand = "$compile" + " && " + "$createJar" + " && " +"$javaCommand"
Invoke-Expression $runCommand 
