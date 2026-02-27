$Source="src\application\*.java src\application\models\*.java src\application\utils\*.java"
$Compile="javac -d bin -cp '$Libs' $Source"
$Jar="jar -cfm JavaParsing.jar Manifesto.txt -C bin\ . "
$Run="java -cp 'bin' application.JavaParsing"
Invoke-Expression ($Compile + " && " + $Jar + " && " + $Run)
