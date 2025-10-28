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

    public List<String> getMethods() {
        List<String> methods = new ArrayList<>();
        for(String l: getLines()) {
            System.out.println(l + "nn");
        }
        return null;
    }
}
