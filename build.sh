srcClases="src/*.java src/Application/Parser/*.java src/Application/Models/*.java src/Application/Utils/*.java "
libFiles=""
javac --release 23 -Werror -Xlint:all -d ./bin/ $srcClases
jar -cfm JavaParsing.jar Manifesto.txt -C ./bin/ .
java -jar JavaParsing.jar
