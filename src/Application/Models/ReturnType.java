package Application.Models;


import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ReturnType {

    private Methods m;
    private final static String[] DECLARATION_KEYWORDS = {"public", "private", "protected"};

    public ReturnType(String filePath) {
        m = new Methods();
        m.initializeLinesOfFile(filePath);
    }
    
    public List<String> getReturnTypeFromLine() {
        List<String> methods =  m.getMethodsFromFile(); 
        List<String> types = new ArrayList<>();
        // FIXME: change scope to global or private
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
