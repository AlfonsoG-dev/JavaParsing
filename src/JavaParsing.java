import Application.Models.*;
import Application.Utils.PrintFormat;

import java.util.List;

class JavaParsing {
    public static void main(String[] args) {
        Methods m = new Methods();
        String filePath = "./docs/JavaSample.java";
        m.initializeLists(filePath);

        Arguments a = new Arguments(m);
        ReturnType r = new ReturnType(m);

        List<String> methods = m.getMethodsName();

        int max = methods.size();

        PrintFormat p = new PrintFormat(filePath, m);

        for(int i=0; i<max; ++i) {
            String method = methods.get(i);
            p.printLineMethod(
                method,
                r.getReturnTypeOfMethod(method),
                a.getArgumentsOfMethod(method)
            );
        }

    }
}