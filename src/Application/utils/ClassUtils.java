package application.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.io.File;
import java.io.IOException;

public class ClassUtils {
    private String fileURI;

    public ClassUtils(String fileURI) {
        this.fileURI = fileURI;
    }

    private String getPackageName(String parent) {
        String local = fileURI;
        return local.replace(parent, "").replace(File.separator, ".").replace(".class", "");
    }

    public Class<?> getClassFromFile(String parent) throws MalformedURLException {
        File file = new File(fileURI);
        URL url = file.toURI().toURL();
        URL[] urls = new URL[]{url};
        try(URLClassLoader loader = new URLClassLoader(urls)) {
            return loader.loadClass(getPackageName(parent));
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
