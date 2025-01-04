package Application.Models;

import Application.Operations.FileOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Methods {
    public final static String[] DECLARATION_KEYWORDS = {"public", "private", "protected"};

    private FileOperations op;
    private List<String> lines;
    public List<String> declarations;
    
    public Methods(List<String> lines) {
        op = new FileOperations();
        this.lines = lines;
    }
    public Methods() {
        op = new FileOperations();
    }
    
    public void initializeLists(String filePath){
        try {
            declarations = Arrays.asList(DECLARATION_KEYWORDS);
            lines = op.getLinesOfFile(filePath)
                .stream()
                .filter(l -> !l.trim().startsWith("}") && !l.trim().endsWith(";") && !l.trim().startsWith(")") && !l.trim().startsWith("*"))
                .filter(l -> !l.isEmpty() && !l.contains(".") && !l.contains("="))
                .toList();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isLineMethod(String line) {
        boolean isMethod = false;
        if(line.contains("(") && line.contains(")")) {
            String m = line.split("\\(")[0];
            String[] spaces = m.split(" ");
            if(spaces.length > 1) {
                isMethod = true;
            }
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
            boolean
                a = lines.get(n).trim().contains(","),
                b = lines.get(n).trim().contains("{"),
                c = lines.get(n).trim().contains(")");
            if(n >= lines.size()) {
                break;
            }
            if(a && !b) {
                l += " " + lines.get(n).trim();
                ++n;
            }
            if(b || c) {
                l += " " + lines.get(n).trim();
                break;
            }
        }
        return l;
    }

    public List<String> getMethodsFromFile()  {
        List<String> methods = new ArrayList<>();
        for(int i=0; i<lines.size(); ++i) {
            String line = lines.get(i).trim();
            if(line.contains("(") && line.endsWith(",")) {
                String l = buildMultiLineMethod(lines, i);
                methods.add(l.replace("{", "").replace("}", "").trim());
            } else if(isLineMethod(line)) {
                methods.add(line.replace("{", "").replace("}", "").trim());
            }
        }
        return methods;
    }

    public List<String> getMethodsName() {
        List<String> names = new ArrayList<>(), methods = getMethodsFromFile();
        for(int i=0; i<methods.size(); ++i) {
            String line = methods.get(i).trim();
            String byMethod = line.split("\\(")[0];
            String[] spaces = byMethod.split(" ");
            names.add(spaces[spaces.length-1]);
        }
        return names;
    }
    public int getMethodIndex(String methodName) {
        int index = 0;
        List<String> names = getMethodsName();
        if(names.contains(methodName)) {
            index = names.indexOf(methodName);
        }
        return index;
    }

    public int getMethodLineNumber(String methodName) {
        int lineNumber = 0;
        List<String> names = new ArrayList<>();
        // FIXME: take the file original lines to get line number
        for(int i=0; i<lines.size(); ++i) {
            String line = lines.get(i).trim();
            if(isLineMethod(line)) {
                String byMethod = line.split("\\(")[0];
                String[] spaces = byMethod.split(" ");
                names.add(
                    String.format("%s:%s", (i+1), spaces[spaces.length-1])
                );
            }
        }
        for(String n: names) {
            String[] values = n.split(":");
            if(values[1].toLowerCase().equals(methodName.toLowerCase())) {
                lineNumber = Integer.parseInt(values[0]);
            }
        }
        return lineNumber;
    }
    
}
