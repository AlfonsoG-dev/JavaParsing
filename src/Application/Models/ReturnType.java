package Application.Models;


import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ReturnType extends Methods {

    public ReturnType(List<String> lines) {
        super(lines);
    }
    public ReturnType() {
        super();
    }
    
    public List<String> getReturnTypeFromLine() {
        List<String> methods =  super.getMethodsFromFile(); 
        List<String> types = new ArrayList<>();

        for (int i=0; i<methods.size(); ++i) {
            String byMethod = methods.get(i).split("\\(")[0];
            String[] spaces = byMethod.split(" ");
            if(spaces.length == 2 && super.declarations.contains(spaces[0])) {
                spaces[0] = spaces[1];
            }
            types.add(spaces[spaces.length-2]);
        }
        return types;
    }

}
