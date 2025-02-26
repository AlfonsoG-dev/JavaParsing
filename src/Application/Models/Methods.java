package Application.Models;

import Application.Operations.FileOperations;

import java.io.LineNumberReader;
import java.io.File;
import java.io.FileReader;

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
                .filter(l -> !l.trim().startsWith("}") && !l.trim().startsWith(")") && !l.trim().startsWith("*"))
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
    public String[] searchMethod(String methodName) throws Exception {
        List<String> methods = getMethodsFromFile();
        String[] searched = new String[2];
        for(int i=0; i<methods.size(); ++i) {
            String f = methods.get(i).split("\\(")[0];
            if(f.toLowerCase().contains(methodName.toLowerCase())) {
                searched[0] = methods.get(i);
                if((i+1) < methods.size()) {
                    searched[1] = methods.get(i+1);
                } else {
                    searched[1] = methods.get(i);
                }
            }
        }
        if(searched.equals("")) {
            throw new Exception("Method not found");
        }
        return searched;
    }
    public int getMethodIndex(String methodName) {
        int index = 0;
        List<String> names = getMethodsName();
        if(names.contains(methodName)) {
            index = names.indexOf(methodName);
        }
        return index;
    }
    public int getMethodLineNumber(String name, String filePath) {
        int number = 0;
        try (LineNumberReader lr = new LineNumberReader(new FileReader(new File(filePath)))) {
            while(lr.ready()) {
                if(lr.readLine().contains(name)) {
                    number = lr.getLineNumber();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return number;
    }
    public void getMethodContent(String searched, String filePath) {
        List<String> declarations = this.getMethodsFromFile();
        try {
            String[] searchedValues = this.searchMethod(searched);
            int first = this.getMethodLineNumber(searchedValues[0], filePath);
            int second = this.getMethodLineNumber(searchedValues[1], filePath);
            List<String> fileLines = op.getLinesOfFile(filePath);
            if(second == first) {
                second = fileLines.size();
            }
            if(second == 0) {
                second = first;
            }
            System.out.println(filePath + ":" + first + "\n");
            for(int i=first-1; i<second-1; ++i) {
                System.out.println(fileLines.get(i));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
