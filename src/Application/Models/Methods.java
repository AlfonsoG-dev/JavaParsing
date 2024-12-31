package Application.Models;

import Application.Operations.FileOperations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Methods {

    private FileOperations op;
    // FIXME: add more keywords
    public final static String[] DECLARATION_KEY_WORDS = {"public", "private", "protected"};

    public Methods(String rootPath) {
        op = new FileOperations(rootPath);
    }

    private boolean isLineMethod(String line) {
        boolean isMethod = false;
        String m = line.split("\\(")[0];
        String[] spaces = m.split(" ");
        if(spaces.length > 1) {
            isMethod = true;
        }
        return isMethod;
    }

    public List<String> getMethodsFromFile(String filePath)  {
        List<String> lines = null;
        List<String> declare = Arrays.asList(DECLARATION_KEY_WORDS);
        List<String> methods = new ArrayList<>();
        try {
            lines = op.getLinesOfFile(filePath);
            for(int i=0; i<lines.size(); ++i) {
                String line = lines.get(i).trim();
                String[] spaces = line.split(" ");
                if(declare.contains(spaces[0])) {
                    methods.add(line.replace("{", "").trim());
                } else if(line.contains("(") && line.contains(")") &&
                 line.endsWith("{") && isLineMethod(line)) {
                    methods.add(line.replace("{", "").trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return methods;
    }
    
}
