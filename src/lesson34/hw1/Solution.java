package lesson34.hw1;

import lesson34.hw1.Exeptions.InternalServerException;

import java.io.*;

public class Solution {

    public static void transferFileContent(String fileFromPath, String fileToPath) throws Exception {
        //1.проверить существование файлов +
        //2.проверить возможность чтения и записи файлов +
        //3.перенести все содержиме файла
        //4.удалить контент из файла
        //5.добавить контент к уже существующему с новой строки

        validate(fileFromPath, fileToPath);
        writeToFile(readFromFile(fileFromPath), fileToPath, fileFromPath);
    }

    private static StringBuffer readFromFile(String path) {
        StringBuffer res = new StringBuffer();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                res.append("\r\n");
                res.append(text);
            }
            //res.replace(res.length()-1, res.length(), ""); // и без этой строки запись начинается с новой строки
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist");
        } catch (IOException e) {
            System.err.println("Reading from file " + path + " filed");
        }
        return res;
    }

    private static void writeToFile(StringBuffer content, String fileToPath, String fileFromPath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToPath, true))) {
            bufferedWriter.append(content);
            clearFile(fileFromPath);
        } catch (IOException e) {
            System.err.println("Reading from file " + fileToPath + " filed");
        }
    }

    private static void clearFile(String path) {
        try (FileWriter fileWriter = new FileWriter(path)) {

        } catch (IOException e) {
            System.err.println("File does not exist");
        }
    }

    private static void validate(String fileFromPath, String fileToPath) throws Exception {
        File fileFrom = new File(fileFromPath);
        File fileTo = new File(fileToPath);

        if (!fileFrom.exists())
            throw new FileNotFoundException("File with path " + fileFromPath + " does not exists");

        if (!fileTo.exists())
            throw new FileNotFoundException("File with path " + fileTo + " does not exists");

        if (!fileFrom.canRead())
            throw new InternalServerException("File " + fileFromPath + " does not have permission to be read");

        if (!fileFrom.canWrite())
            throw new InternalServerException("File " + fileFromPath + " does not have permission to be cleared");

        if (!fileTo.canWrite())
            throw new InternalServerException("File " + fileToPath + " does not have permission to be written");
    }
}
