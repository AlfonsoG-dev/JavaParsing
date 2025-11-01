package Application.Models;

import java.util.ArrayList;
import java.util.List;

public class Methods {
    private List<String> declarations;
    private final static String[] TOKENS =  {
        "public",
        "private",
        "record",
        "interface",
        "class",
        "static",
        "final",
    };
    public Methods(List<String> declarations) {
        this.declarations = declarations;
    }

    public List<String> getReturnType() {
        List<String> type = new ArrayList<>();
        for(String d: declarations) {
            if(d.contains("->")) {
                type.add(d.split(" ")[0]);
            } else {
                String name = d.split("\\(")[0];
                String[] spaces = name.split(" ");
                if(spaces.length > 2) {
                    type.add(spaces[spaces.length-2]);
                } else {
                    for(String t: TOKENS) {
                        if(spaces[0].equals(t)) {
                            spaces[0] = spaces[1];
                        }
                    }
                    type.add(spaces[0]);
                }
            }
        }
        return type;
    }

    public List<String> getArguments() {
        List<String> types = new ArrayList<>();
        for(String d: declarations) {
            String[] arguments = d.split("\\(")[1].split("\\)");
            if(arguments.length == 0 || arguments[0].isEmpty()) {
                types.add("()");
            } else {
                for(int i=0; i<arguments.length; ++i) {
                    if(arguments[i].endsWith(";")) {
                        arguments[i] = "";
                    }
                    if(arguments[i].contains(",")) {
                        String l = arguments[i] + ",";
                        String[] comas = l.split(",");
                        String or = "";
                        for(int j=0; j<comas.length; ++j) {
                            String a = comas[j].trim();
                            String[] spaces = a.split(" ");
                            or += spaces[0] + ", ";
                        }
                        if(or != "") {
                            types.add("(" + or.substring(0, or.length()-2) + ")");
                        }
                    } else if(arguments[i] != "") {
                        String[] s = arguments[i].split(" ");
                        if(s.length > 0) {
                            types.add("(" + s[0] + ")");
                        }
                    }
                }
            }
        }
        return types;
    }

    public List<String> getMethods() {
        List<String> methods = new ArrayList<>();
        for(String d: declarations) {
            if(d.contains("->")) {
                methods.add(d.split(" ")[0]);
            } else {
                String stripArguments = d.split("\\(")[0];
                String[] endPart = stripArguments.split(" ");
                String name = endPart[endPart.length-1];
                methods.add(name);
            }
        }
        return methods;
    }

}
