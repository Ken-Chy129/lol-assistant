package cn.ken.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/Ken-Chy129">Ken-Chy129</a>
 * @since 2024-04-22 18:01
 */
public class FileUtil {


    public static List<String> filesInDir(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Invalid directory: " + directoryPath);
        }

        List<String> filesList = new ArrayList<>();
        File[] filesAndDirs = directory.listFiles();

        if (filesAndDirs != null) {
            for (File fileOrDir : filesAndDirs) {
                if (fileOrDir.isFile()) {
                    filesList.add(fileOrDir.getName());
                }
            }
        }

        return filesList;
    }
    
    public static int getFileLastModifiedTime(String fileName) {
        Path path = Paths.get(fileName);
        try {
            return (int) (Files.getLastModifiedTime(path).toMillis() / 10000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
}
