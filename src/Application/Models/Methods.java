package Application.Models;

import Application.Operations.FileOperations;

import java.util.ArrayList;
import java.util.List;

public class Methods {

    private FileOperations op;

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
        String l = lines.get(i).trim(), e = "", b = "", c = "";
        // FIXME: find a better way to build from 4 or more lines.
        while(n < lines.size()) {
            if(lines.get(n).trim().endsWith("{")) {
                e = " " + lines.get(n).trim();
                break;
            } else if(lines.get(n).trim().contains(",")) {
                b += l + " " + lines.get(n).trim();
                ++n;
            }
        }
        if(b.isEmpty()) {
            b = l;
        }
        c = b.concat(e);
        return c;
    }

    public List<String> getMethodsFromFile(String filePath)  {
        List<String> lines = null;
        List<String> methods = new ArrayList<>();
        try {
            lines = op.getLinesOfFile(filePath);
            for(int i=0; i<lines.size(); ++i) {
                String line = lines.get(i).trim();
                if(line.endsWith(",")) {
                    String c = buildMultiLineMethod(lines, i);
                    if(c.contains("(")) {
                        methods.add(c.replace("{", "").trim());
                    }
                } else if(line.contains("(") && line.contains(")") && line.endsWith("{") && isLineMethod(line)) {
                    methods.add(line.replace("{", "").trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return methods;
    }
    
}
