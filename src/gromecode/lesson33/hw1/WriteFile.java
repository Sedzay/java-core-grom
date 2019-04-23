package gromecode.lesson33.hw1;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class WriteFile {

    public static void writeToFileFromConsole(String path) throws Exception {

        validate(path);

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {

            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            bufferedWriter = new BufferedWriter(new FileWriter(path, true));

            //считать ввод данных в консоли
            System.out.println("Enter file content to write in the file:");

            String text = "";
            StringBuffer stringBuffer = new StringBuffer();

            while (true) {
                text = bufferedReader.readLine();
                if (text.equals("wr")) {
                    bufferedWriter.append(stringBuffer);
                    return;
                }
                stringBuffer.append("\r\n");
                stringBuffer.append(text);
            }

        } catch (IOException e) {
            System.err.println("Can't write to file with path " + path);
        } finally {
            IOUtils.closeQuietly(bufferedReader);
            IOUtils.closeQuietly(bufferedWriter);
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
