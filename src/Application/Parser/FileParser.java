package Application.Parser;

import Application.Utils.FileUtils;
import Application.Models.Methods;

import java.io.File;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;


/**
 * Place holder class to assembly the java class methods.
 */
public class FileParser {
    private String filePath;
    private FileUtils fileUtils;

    public FileParser(String filePath) {
        this.filePath = Optional.ofNullable(filePath).orElse("." + File.separator);
        fileUtils = new FileUtils("");
    }

    protected List<String> getLines() {
        List<String> n = List.copyOf(fileUtils.fileLines(filePath));
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

    public List<String> getDeclarations() {
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
    public final void parse() {
        Methods m = new Methods(getDeclarations());
        m.getArguments()
        .stream()
        .forEach(System.out::println);
    }

}
