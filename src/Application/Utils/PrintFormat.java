package Application.Utils;

import Application.Models.Methods;
import java.io.File;

public class PrintFormat {

    private File f;
    private Methods m;

    public PrintFormat(String filePath, Methods m) {
        this.m = m;
        f = new File(filePath);
    }

    public void printLineMethod(String method, String returnType, String prop) {
        int lineNumber = m.getMethodLineNumber(method);
        System.out.println(
            String.format(
                "%s:%s  %s -> %s%s",f.getPath(), lineNumber, returnType, method, prop
            )
        );
    }
    
}
