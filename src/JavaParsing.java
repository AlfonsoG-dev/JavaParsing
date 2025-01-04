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

        List<String> declarations = m.getMethodsFromFile();

        int max = declarations.size();

        PrintFormat p = new PrintFormat(filePath, m);

        System.out.println(
            m.getMethodsName().size()  + " | " + r.getReturnTypeFromLine().size() + " | " + a.getArgumentsFromLine().size() + "\n"
        );
        for(int i=0; i<max; ++i) {
            p.printLineMethod(
                declarations.get(i),
                m.getMethodsName().get(i),
                r.getReturnTypeFromLine().get(i),
                a.getArgumentsFromLine().get(i)
            );
        }

    }
}