package gromecode.lesson34.hw2;

import gromecode.lesson34.hw2.Exceptions.InternalServerException;

import java.io.*;

public class Solution {
    public static void transferSentences(String fileFromPath, String fileToPath, String word) throws Exception {
        //проверить существвание файлов
        //проверить возможность считывания, записывания и перезаписывания файлов
        //считать данные с одного файла
        //разбить на массив предложений с разделителем "."
        //проверить каждое предложение согласно правил 10 символв и имеет ключевое слово
        //если соответствует правилам записать предложение в другой файл с новой строки
        //иначе перезаписать в старый файл, вернуть разделитель "."

        validate(fileFromPath, fileToPath);
        readFile(fileFromPath, fileToPath, word);

    }

    private static void readFile(String fileFromPath, String fileToPath, String word) {
        StringBuffer newContent = new StringBuffer();
        StringBuffer oldContent = new StringBuffer();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFromPath))) {
            String line;
            String[] sentences = null;
            while ((line = bufferedReader.readLine()) != null) {
                sentences = line.split("\\.");
                if (sentences == null)
                    return;

                for (String sentence : sentences) {

                    if (checkRuls(sentence, word)) {
                        newContent.append("\r\n");
                        newContent.append(sentence);
                    } else {
                        oldContent.append(sentence);
                        oldContent.append(".");
                    }
                }
            }
            writeToFile(newContent, fileToPath, oldContent, fileFromPath);
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist");
        } catch (
                IOException e) {
            System.err.println("Reading from file " + fileFromPath + " filed");
        }
    }

    private static boolean checkRuls(String sentence, String word) {
        if (sentence.length() < 10)
            return false;

        String[] words = sentence.split(" ");
        for (String str : words) {
            if (str.equals(word))
                return true;
        }
        return false;
    }

    private static void writeToFile(StringBuffer content, String fileToPath, StringBuffer otherContent, String fileFromPath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToPath, true))) {
            bufferedWriter.append(content);
            rewriteToOldFile(otherContent, fileFromPath);
        } catch (IOException e) {
            System.err.println("Reading from file " + fileToPath + " filed");
        }
    }

    private static void rewriteToOldFile(StringBuffer content, String pathOldFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathOldFile))) {
            bufferedWriter.append(content);
        } catch (IOException e) {
            System.err.println("Reading from file " + pathOldFile + " filed");
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
