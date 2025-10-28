package Application.Models;

import java.util.ArrayList;
import java.util.List;

public class Methods {
    private List<String> lines;
    public Methods(List<String> lines) {
        this.lines = lines;
    }

    protected List<String> getLines() {
        List<String> n = List.copyOf(lines);
        return n;
    }
    private boolean isMethod(String l) {
        boolean m = false;
        if(!l.contains(".") && (l.contains("(") || l.contains(")")) && l.length() > 1) {
            m = true;
        }
        return m;
    }
    private boolean isMultiline(String l) {
        boolean m = false;
        if(l.endsWith(",")) {
            m = true;
        }
        return m;
    }

    public List<String> getMethodsDeclaration() {
        List<String> methodLines = new ArrayList<>();
        List<String> methods = new ArrayList<>();
        for(int i=0; i<getLines().size(); ++i) {
            String line = getLines().get(i).trim();
            if(isMultiline(line)) {
                StringBuffer mine = new StringBuffer();
                int n = i;
                while(true) {
                    String cl = getLines().get(n).trim();
                    if(cl.endsWith("{") || cl.endsWith(")")) {
                        mine.append(cl);
                        break;
                    } else {
                        mine.append(cl);
                    }
                    ++n;
                }
                methodLines.add(mine.toString().replace("{", ""));
            } else if(isMethod(line)) {
                methodLines.add(line.replace("{", ""));
            }
        }
        methods.addAll(
            methodLines
            .stream()
            .filter(l -> l.contains("("))
            .toList()
        );
        return methods;
    }
}
