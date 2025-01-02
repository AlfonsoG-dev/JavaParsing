package Application.Models;

import Application.Operations.FileOperations;

import java.util.ArrayList;
import java.util.List;

public class Methods {

    private FileOperations op;

    public Methods() {
        op = new FileOperations();
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
    /**
     * only build multiline arguments of method when there is 3 levels of lines.
     *                           public int count (int o,
     *                           int c, 
     *                           inc b) {
     * @param lines the file lines.
     * @param i the iteration variable.
     * @return the line concatenated with the rest of lines.
     */
    private String buildMultiLineMethod(List<String> lines, int i) {
        int n = i+1;
        String l = lines.get(i).trim();
        while(true) {
            boolean a = lines.get(n).trim().contains(","), b = lines.get(n).trim().contains("{");
            if(n >= lines.size()) {
                break;
            }
            if(a && !b) {
                l += " " + lines.get(n).trim();
                ++n;
            }
            if(b) {
                l += " " + lines.get(n).trim();
                break;
            }
        }
        return l;
    }

    public List<String> getMethodsFromFile(String filePath)  {
        List<String> lines = null;
        List<String> methods = new ArrayList<>();
        try {
            lines = op.getLinesOfFile(filePath);
            for(int i=0; i<lines.size(); ++i) {
                String line = lines.get(i).trim();
                if(line.contains("(") && line.endsWith(",")) {
                    String l = buildMultiLineMethod(lines, i);
                    methods.add(l.replace("{", "").trim());
                } else if(line.contains("(") && line.contains(")") && isLineMethod(line)) {
                    methods.add(line.replace("{", "").trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return methods;
    }
    
}
