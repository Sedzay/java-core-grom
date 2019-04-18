package gromecode.lesson33;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class ReadWriteFile {

    public static void readFile(String path) {
        FileReader reader;

        try {
            reader = new FileReader(path);
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist");
            return;
        }

        BufferedReader br = new BufferedReader(reader);

        String line;
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Reading from file " + path + " failed");
        } finally {
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(br);
        }
    }


    public static void writeFile(String path) throws InterruptedException {

        FileWriter writer = null;
        BufferedWriter bufferedWriter = null;
        try {
            //TODO use other constructor
           // Thread.sleep(6000);

            writer = new FileWriter(path, true);
//          writer = new FileWriter(new File(path), true);
            bufferedWriter = new BufferedWriter(writer);

            // \r\n - так работает перевод каретки в блокните виндовс

            bufferedWriter.append("\n");

            bufferedWriter.append("TTTTT");

        } catch (IOException e) {
            System.err.println("Can't write to file");
        } finally {
            IOUtils.closeQuietly(bufferedWriter);
            IOUtils.closeQuietly(writer);
        }
    }
}
