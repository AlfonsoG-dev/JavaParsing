import Application.Models.*;
import Application.Utils.PrintFormat;

class JavaParsing {
    public static void main(String[] args) {
        Methods m = new Methods();
        String filePath = "./src/Application/Models/Methods.java";
        // String filePath = "./docs/JavaSample.java";
        m.initializeLists(filePath);

        Arguments a = new Arguments(m);
        ReturnType r = new ReturnType(m);

        PrintFormat print = new PrintFormat(filePath, m);

        int total = m.getMethodsFromFile().size();
        for(int i=0; i<total; ++i) {
            String methodName = m.getMethodsName().get(i);
            print.printLineMethod(
                m.getMethodsFromFile().get(i),
                methodName,
                r.getReturnTypeOfMethod(methodName),
                a.getArgumentsOfMethod(methodName)
            );
        }
    }
}
