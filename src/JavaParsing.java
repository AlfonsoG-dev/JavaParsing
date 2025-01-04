import Application.Models.*;
import Application.Utils.PrintFormat;

import java.util.List;

class JavaParsing {
    public static void main(String[] args) {
        Methods m = new Methods();
        String filePath = "./src/Application/Models/Methods.java";
        m.initializeLists(filePath);

        Arguments a = new Arguments(m);
        ReturnType r = new ReturnType(m);

        List<String> props = m.getMethodsFromFile();

        int max = props.size();

        PrintFormat p = new PrintFormat(filePath, m);

        props
        .stream()
        .forEach(System.out::println);


    }
}