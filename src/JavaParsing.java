import Application.Models.*;

import java.util.List;

class JavaParsing {
    public static void main(String[] args) {
        Methods m = new Methods();
        m.initializeLists("./docs/JavaSample.java");

        Arguments a = new Arguments(m);
        ReturnType r = new ReturnType(m);

        List<String> 
            methods = m.getMethodsName(),
            types = r.getReturnTypeFromLine(),
            props = a.getArgumentsFromLine();

        int max = methods.size();

        for(int i=0; i<max; ++i) {
            System.out.println(types.get(i) + " -> " + methods.get(i) +  " :: " + props.get(i));
        }

    }
}