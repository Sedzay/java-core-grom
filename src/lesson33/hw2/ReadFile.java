package lesson33.hw2;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class ReadFile {
    public void readFileByConsolePath() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        System.out.println("Please, enter file path to read:");
        String path = "";

        try {
            path = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("Can't read file by path " + path);
            return;
        } finally {
            IOUtils.closeQuietly(bufferedReader);
            IOUtils.closeQuietly(inputStreamReader);
        }

        readFile(path);
    }


    private void readFile(String path) {
        FileReader reader;

        try {
            reader = new FileReader(path);
        } catch (FileNotFoundException e) {
            System.err.println("File with path " + path + " not found");
            return;
        }

        BufferedReader br = new BufferedReader(reader);

        String line;
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Can't read file by path " + path);
        } finally {
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(br);
        }
    }
}
