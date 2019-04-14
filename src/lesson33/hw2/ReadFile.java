package lesson33.hw2;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class ReadFile {
    public static void readFileByConsolePath() throws Exception{

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please, enter file path to read:");
        String path = "";

        try {
            path = bufferedReader.readLine();
        } catch (IOException e) {
            System.err.println("Can't read file by path " + path);
            return;
        } finally {
            IOUtils.closeQuietly(bufferedReader);
        }
        readFile(path);
    }


    private static void readFile(String path) throws Exception {
        validate(path);
        BufferedReader br = new BufferedReader(new FileReader(path));

        String line;
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Can't read file by path " + path);
        } finally {
            IOUtils.closeQuietly(br);
        }
    }

    private static void validate(String path) throws Exception{
        File file = new File(path);

        if (!file.exists())
            throw new FileNotFoundException("File with path " + path + " not found");

        if (!file.canRead())
            throw new Exception("Can't read file by path " + path);
    }
}
