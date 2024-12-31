package Application.Utils;

import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.DirectoryStream;

public class FileUtils {
    private String rootPath;

    public FileUtils(String rootPath) {
        this.rootPath = rootPath;
    }
    public FileUtils() {}

    public List<File> getDirectorFiles(DirectoryStream<Path> dir) {
        List<File> files = new ArrayList<>();
        dir.forEach(p -> {
            File f = p.toFile();
            if(f.isFile()) {
                files.add(f);
            } else if(f.isDirectory() && f.listFiles() != null) {
                try {
                    files.addAll(
                        getDirectorFiles(
                            Files.newDirectoryStream(p)
                        )
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return files;
    }

    public List<String> getFileLines(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
            while(br.ready()) {
                lines.add(br.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }
}
