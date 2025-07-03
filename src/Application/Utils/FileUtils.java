package Application.Utils;

import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.FileVisitOption;
import java.nio.file.DirectoryStream;

public class FileUtils {

    public List<File> getDirectorFiles(File dir) {
        List<File> files = new ArrayList<>();
        try {
            files = Files.walk(dir.toPath(), FileVisitOption.FOLLOW_LINKS)
                .map(Path::toFile)
                .filter(p -> p.isDirectory() && p.listFiles() != null)
                .toList();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return files;
    }

    public List<String> getFileLines(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
            String line;
            while((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }
}
