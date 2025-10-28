package Application.Models;

import java.util.ArrayList;
import java.util.List;

public class Methods {
    private List<String> declarations;
    public Methods(List<String> declarations) {
        this.declarations = declarations;
    }

    public List<String> getArguments() {
        List<String> types = new ArrayList<>();
        for(String d: declarations) {
            String[] arguments = d.split("\\(")[1].split("\\)");
            if(arguments.length == 0 || arguments[0].isEmpty()) {
                types.add("()");
            } else {
                types.add("(" + arguments[0] + ")");
            }
        }
        return types;
    }

    public List<String> getMethods() {
        List<String> methods = new ArrayList<>();
        for(String d: declarations) {
            if(d.contains("->")) {
                methods.add(d.split(" ")[0]);
            } else {
                String stripArguments = d.split("\\(")[0];
                String[] endPart = stripArguments.split(" ");
                String name = endPart[endPart.length-1];
                methods.add(name);
            }
        }
        return methods;
    }

}
