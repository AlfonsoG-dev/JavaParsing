package Application.Models;

import java.util.List;
import java.util.ArrayList;

public class Arguments {

    private Methods m;
    public Arguments(Methods m) {
        this.m = m;
    }

    private String buildMultilineArgument(String[] arguments) {
        String l = "", b = "( ";
        for(int j=0; j<arguments.length; ++j) {
            l = arguments[j].trim().split(" ")[0];
            b += l + ", ";
        }
        b = b.substring(0, b.length()-2) + " )";
        return b;
    }

    public List<String> getArgumentsFromLine() {
        List<String> methods = m.getMethodsFromFile();
        List<String> props = new ArrayList<>();

        for(int i=0; i<methods.size(); ++i) {
            String byParams = methods.get(i).split("\\(")[1];
            if(byParams.equals(")")) {
                props.add("( " + byParams);
            } else {
                String[] comas = byParams.split(",");
                if(comas.length == 1) {
                    props.add("( " + comas[0].split(" ")[0] + " )");
                } else {
                    props.add(buildMultilineArgument(comas));
                }
            }
        }
        return props;
    }
    public String getArgumentsOfMethod(String methodName) {
        return getArgumentsFromLine().get(m.getMethodIndex(methodName));
    }
    
}
