package application;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

import application.models.Member;
import application.utils.ClassUtils;
import application.utils.FileUtils;

class JavaParsing {
    public static void main(String[] args) {
        System.console().printf("%s%n", "Hello there");
        try {
            String p = "./bin/application/utils/";
            List<Path> files = new FileUtils().getFiles(p);
            Class<?> cls = new ClassUtils(files.get(1).toString()).getClassFromFile("." + File.separator + "bin" + File.separator);
            Map<String, List<String>> mios = new Member(cls).getArguments();
            Set<String> keys = mios.keySet();
            for(String k: keys) {
                System.out.println(k + "==" + mios.get(k));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
