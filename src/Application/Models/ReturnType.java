package Application.Models;


import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ReturnType {

    private Methods m;
    private final static String[] DECLARATION_KEYWORDS = {"public", "private", "protected"};

    public ReturnType() {
        m = new Methods();
    }

    public List<String> getReturnTypeFromLine(String filePath) {
        List<String> methods = m.getMethodsFromFile(filePath); 
        List<String> types = new ArrayList<>();
        List<String> declarations = Arrays.asList(DECLARATION_KEYWORDS);
        for (int i=0; i<methods.size(); ++i) {
            String byMethod = methods.get(i).split("\\(")[0];
            String[] spaces = byMethod.split(" ");
            if(spaces.length == 2 && declarations.contains(spaces[0])) {
                spaces[0] = spaces[1];
            }
            types.add(spaces[spaces.length-2]);
        }
        return types;
    }

}
