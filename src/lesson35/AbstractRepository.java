package lesson35;

import lesson35.exceptions.InternalServerException;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractRepository<T extends ReceiveId> {
    private String path = "";

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public ArrayList<T> getList() throws Exception {
        ArrayList<T> ts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                ts.add(stringToObject(line.split(", ")));
            }
        } catch (FileNotFoundException e) {
            throw new InternalServerException("File " + path + " not found");
        } catch (IOException e) {
            throw new InternalServerException("Reading from file " + path + " filed");
        }
        return ts;
    }

    public T stringToObject(String[] attributes) throws Exception {
        return null;
    }

    public T save(T t) throws InternalServerException {
        File file = new File(path);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.append(t.toString());
            bw.append("\r\n");
        } catch (IOException e) {
            throw new InternalServerException("Writing to file " + path + " filed");
        }
        return t;
    }

    public void writeListObjectsToDb(ArrayList<T> ts) throws InternalServerException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (T t : ts) {
                bw.append(t.toString());
                bw.append("\r\n");
            }
        } catch (IOException e) {
            throw new InternalServerException("Writing to file " + path + " filed");
        }
    }

    public long addId(ArrayList<T> ts) {
        return ThreadLocalRandom.current().nextLong(0,10000000);
    }

    public T findObjectById(long id, AbstractRepository abstractRepository) throws Exception {
        ArrayList<T> ts = abstractRepository.getList();
        if (ts.isEmpty())
            return null;
        for (T t : ts) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

}
