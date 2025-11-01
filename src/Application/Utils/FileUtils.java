package Utils;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.FileVisitOption;

import java.util.List;
import java.util.ArrayList;

import java.util.Optional;

public class FileUtils {
    
    private String filePath;
    /**
     * Secure that file path has at least the root base of the URI
     * @param filePath the file path to get the items
     */
    public FileUtils(String filePath) {
        this.filePath = Optional.ofNullable(filePath).orElse("." + File.separator);
    }

    public List<Path> getFiles() {
        List<Path> files = new ArrayList<>();
        try {
            files.addAll(
                Files.walk(Paths.get(filePath), FileVisitOption.FOLLOW_LINKS)
                .filter(p -> p.toFile().isFile())
                .toList()
            );
        } catch(IOException e) {
            e.printStackTrace();
        }
        return files;
    }
    /**
     * List of files of a certain path that is a directory.
     * @param directory the path to list files from.
     * @return the list of files from that path.
     * @throws IOException when the path is invalid.
     */
    public List<Path> getFilesFromPath(String directory) {
        List<Path> files = new ArrayList<>();
        try {
            files.addAll(
                Files.walk(Paths.get(directory), FileVisitOption.FOLLOW_LINKS)
                .filter(p -> p.toFile().isFile())
                .toList()
            );
        } catch(IOException e) {
            e.printStackTrace();
        }
        return files;
    }
    /**
     * count directory files.
     * @param directory the path to count files of.
     * @return the number of files in the directory, or else 0.
     */
    private int countFiles(File directory) {
        int n = 0;
        if(directory.listFiles() != null && directory.listFiles().length > 0) {
            n = directory.listFiles().length;
        }
        return n;
    }
    public List<String> getDirectories() {
        List<String> directories = new ArrayList<>();
        try {
            directories.addAll(
                Files.walk(Paths.get(filePath), FileVisitOption.FOLLOW_LINKS)
                .filter(p -> p.toFile().isDirectory() && countFiles(p.toFile()) > 0)
                .map(p -> p.toString() + File.separator)
                .toList()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return directories;
    }

    public List<String> fileLines(String file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(file)))) {
            while(br.ready()) {
                lines.add(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
