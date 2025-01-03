
#!/bin/bash
srcClases="./src/*.java ./src/Application/Operations/*.java ./src/Application/Utils/*.java ./src/Application/Models/*.java "
javac -Werror -Xlint:all -d ./bin/ $srcClases
jar -cfm JavaParsing.jar Manifesto.txt -C ./bin/ .
java -jar JavaParsing.jar