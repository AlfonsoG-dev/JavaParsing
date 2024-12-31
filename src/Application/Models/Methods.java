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
                } else {
                    /**
                     * TODO: when the line method doesn't start with an accessor.
                     * - Use the () and the end of line {.
                     * - For some reason some statements will be included with the previous conditions.
                     * >- For instance an if statement will be included.
                     * - I thing the solution is by creating the condition evaluating the return type.
                     * >- The if statement or similar doesn't have a return type.
                     * >- In order to get the return type we must parse the line into 3 separated sections.
                     * >>- accessor - return type - method name.
                     * >- Some lines will have more sections when (static or final) keywords are use.
                     */
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return methods;
    }
    
}
