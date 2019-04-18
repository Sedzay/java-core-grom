package gromecode.lesson34.hw3;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;

public class Solution {

    public static void copyFileContent(String fileFromPath, String fileToPath) throws Exception {
        validate(fileFromPath, fileToPath);

        File fileFrom = new File(fileFromPath);
        File fileTo = new File(fileToPath);
        Files.copy(fileFrom.toPath(), fileTo.toPath());
    }

    public static void copyFileContentApacheIO(String fileFromPath, String fileToPath) throws Exception {
        validate(fileFromPath, fileToPath);

        File fileFrom = new File(fileFromPath);
        File fileTo = new File(fileToPath);
        FileUtils.copyFile(fileFrom,fileTo);
    }

    private static void validate(String fileFromPath, String fileToPath) throws Exception {
        File fileFrom = new File(fileFromPath);
        File fileTo = new File(fileToPath);

        if (!fileFrom.exists())
            throw new FileNotFoundException("File " + fileFromPath + " does not exist");

        if (fileTo.exists())
            throw new FileAlreadyExistsException("File " + fileToPath + " already exist");

        if (!fileFrom.canRead())
            throw new Exception("File " + fileFromPath + " does not have permission to be read");

    }
}
