package lesson33.hw1;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class WriteFile {

    public static void writeToFileFromConsole(String path) throws Exception {

        validate(path);

        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {

            inputStreamReader = new InputStreamReader(System.in);
            bufferedReader = new BufferedReader(inputStreamReader);

            fileWriter = new FileWriter(path, true);
            bufferedWriter = new BufferedWriter(fileWriter);

            //считать ввод данных в консоли
            System.out.println("Enter file content to write in the file:");

            String text = "";

            while (!text.equals("wr")) {
                text = bufferedReader.readLine();
                //записать данные в файл
                bufferedWriter.append(text);
                bufferedWriter.append("\r\n"); //\r - перевод строки для блокнота виндовс
            }

        } catch (IOException e) {
            System.err.println("Can't write to file with path " + path);
            return;
        } finally {
            IOUtils.closeQuietly(bufferedReader);
            IOUtils.closeQuietly(inputStreamReader);

            IOUtils.closeQuietly(bufferedWriter);
            IOUtils.closeQuietly(fileWriter);
        }
    }

    private static void validate(String path) throws Exception {
        File file = new File(path);

        if (!file.exists())
            throw new FileNotFoundException("File with path " + path + " not found");

        if (!file.canWrite())
            throw new Exception("Can't write to file with path " + path);
    }
}
