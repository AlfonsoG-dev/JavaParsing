package application;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.List;

import application.utils.ClassUtils;
import application.utils.FileUtils;

class JavaParsing {
    public static void main(String[] args) {
        System.console().printf("%s%n", "Hello there");
        try {
            String p = "./bin/application/utils/";
            List<Path> files = new FileUtils().getFiles(p);
            Class<?> cls = new ClassUtils(files.get(1).toString()).getClassFromFile("." + File.separator + "bin" + File.separator);
            Method[] methods = cls.getDeclaredMethods();
            for(Method m: methods) {
                System.out.println(m.getName());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
