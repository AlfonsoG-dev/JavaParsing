package application.utils;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileUtils {

    /**
     * Traverse the entire file path provided to find only the files.
     * @param fileURI - the file path to see if it has files.
     */
    public List<Path> getFiles(String fileURI) {
        List<Path> files = new ArrayList<>();
        try(Stream<Path> s = Files.walk(Paths.get(fileURI), FileVisitOption.FOLLOW_LINKS)) {
            files.addAll(s
                    .filter(Files::isRegularFile)
                    .toList());
        } catch(IOException e) {
            e.printStackTrace();
        }
        return files;
    }
    public List<String> getFileLines(String fileURI) {
        List<String> lines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(fileURI)))) {
            String l;
            while((l = reader.readLine()) != null) {
                lines.add(l);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

}
