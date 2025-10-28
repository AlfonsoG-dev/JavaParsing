package Application.Models;

import java.util.ArrayList;
import java.util.List;

public class Methods {
    private List<String> declarations;
    public Methods(List<String> declarations) {
        this.declarations = declarations;
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
