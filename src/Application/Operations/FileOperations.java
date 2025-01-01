package Application.Operations;

import Application.Utils.FileUtils;

import java.util.List;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileOperations {
    private FileUtils fileUtils;

    public FileOperations() {
        this.fileUtils = new FileUtils();
    }

    public List<File> getFilesOfDirectory(String dirPath) throws IOException {
        File f = new File(dirPath);
        if (!f.isDirectory()) throw new IOException("[Error] only directory type is allowed.");
        return fileUtils.getDirectorFiles(Files.newDirectoryStream(f.toPath()));
    }

    public List<String> getLinesOfFile(String filePath) throws IOException {
        if(!new File(filePath).isFile()) throw new IOException("[Error] only file type is allowed");
        return fileUtils.getFileLines(filePath);
    }

}
