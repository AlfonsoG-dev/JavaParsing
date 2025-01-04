package Application.Models;


import java.util.List;
import java.util.ArrayList;

public class ReturnType {
    private Methods m;

    public ReturnType(Methods m) {
        this.m = m;
    }

    public List<String> getReturnTypeFromLine() {
        List<String> methods =  m.getMethodsFromFile(); 
        List<String> types = new ArrayList<>();

        for (int i=0; i<methods.size(); ++i) {
            String byMethod = methods.get(i).split("\\(")[0];
            String[] spaces = byMethod.split(" ");
            if(spaces.length == 2 && m.declarations.contains(spaces[0])) {
                spaces[0] = spaces[1];
            }
            types.add(spaces[spaces.length-2]);
        }
        return types;
    }

    public String getReturnTypeOfMethod(String methodName) {
        return getReturnTypeFromLine().get(m.getMethodIndex(methodName));
    }

}
