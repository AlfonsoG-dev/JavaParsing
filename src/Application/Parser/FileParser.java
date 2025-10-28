package Application.Parser;

import Application.Utils.FileUtils;
import Application.Models.Methods;

import java.io.File;

import java.util.Optional;


/**
 * Place holder class to assembly the java class methods.
 */
public class FileParser {
    private String filePath;

    public FileParser(String filePath) {
        this.filePath = Optional.ofNullable(filePath).orElse("." + File.separator);
    }

    public final void parse() {
        FileUtils fu = new FileUtils("");
        Methods m = new Methods(fu.fileLines(filePath));
        m.getMethodsDeclaration()
        .stream()
        .forEach(System.out::println);
    }

}
