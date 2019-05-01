package lesson35;

import lesson35.exceptions.InternalServerException;

import java.io.*;
import java.util.ArrayList;

public abstract class AbstractRepository <T extends ReceiveId> {
    private String path = "";

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public StringBuffer readFile() throws InternalServerException {
        StringBuffer textFromFile = new StringBuffer();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                textFromFile.append(line);
                textFromFile.append("\r\n");
            }
            if (textFromFile.length() != 0) {
                textFromFile.replace(textFromFile.length() - 1, textFromFile.length(), "");
            }

        } catch (FileNotFoundException e) {
            throw new InternalServerException("File " + path + " not found");
        } catch (IOException e) {
            throw new InternalServerException("Reading from file " + path + " filed");
        }
        return textFromFile;
    }
    public ArrayList<T> mappingStringsToObjects(StringBuffer stringBuffer) throws Exception {
        ArrayList<T> ts = new ArrayList<>();

        if (stringBuffer.length() == 0)
            return ts;

        String[] lines = stringBuffer.toString().split("\r\n");
        int index = 0;
        try {
            for (String str : lines) {
                index++;
                ts.add(stringToObject(str.split(", ")));
            }
        }catch (IllegalArgumentException e) {
            throw new InternalServerException("String number " + index + " has incorrect data in file " + path);
        }
        return ts;
    }

    public T stringToObject(String[] attributes) throws Exception {
        return null;
    }

    public T addLine(T t) throws InternalServerException{
        File file = new File(path);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.append(t.toString());
            bw.append("\r\n");
        } catch (IOException e) {
            throw new InternalServerException("Writing to file " + path + " filed");
        }
        return t;
    }

    public void writeListObjectsToDb(ArrayList<T> ts) throws InternalServerException{
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
           for (T t : ts) {
               bw.append(t.toString());
               bw.append("\r\n");
           }
        }catch (IOException e) {
            throw new InternalServerException("Writing to file " + path + " filed");
        }
    }

    public long addId(ArrayList<T> ts) {
        return ts.size() == 0 ? 101 : ts.get(ts.size() - 1).getId() + 1;
    }

    public T findObjectById(long id, AbstractRepository abstractRepository) throws Exception{
        ArrayList<T> ts = abstractRepository.mappingStringsToObjects(abstractRepository.readFile());
        if (ts.isEmpty())
            return null;
        for (T t : ts) {
            if (t.getId() == id){
                return t;
            }
        }
        return null;
    }

}
