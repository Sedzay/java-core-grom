package lesson35.user;

import lesson35.exceptions.InternalServerException;

import java.io.*;
import java.util.ArrayList;

public class UserRepository {
    private String path = "D:\\DataBaseFiles\\UserDb.txt";

    User registerUser(User user) throws InternalServerException {

        user.setId(addUserId(mappingStringsToObjects(readFile())));

        File file = new File(path);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.append("\r\n");
            bw.append(user.toString());
        } catch (IOException e) {
            throw new InternalServerException("Writing to file " + path + " filed");
        }
        return user;
    }

    StringBuffer readFile() throws InternalServerException {
        StringBuffer textFromFile = new StringBuffer();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                textFromFile.append(line);
                textFromFile.append("\r\n");
            }
            textFromFile.replace(textFromFile.length() - 1, textFromFile.length(), "");
        } catch (FileNotFoundException e) {
            throw new InternalServerException("File " + path + " not found");
        } catch (IOException e) {
            throw new InternalServerException("Reading from file " + path + " filed");
        }
        return textFromFile;
    }

    ArrayList<User> mappingStringsToObjects(StringBuffer stringBuffer) throws InternalServerException {
        ArrayList<User> users = new ArrayList<>();
        String[] lines = stringBuffer.toString().split("\r\n");
        for (String str : lines) {
            users.add(stringToObject(str.split(", ")));
        }
        return users;
    }

    private User stringToObject(String[] attributes) throws InternalServerException {
        try {
            return new User(Long.parseLong(attributes[0]),
                    attributes[1],
                    attributes[2],
                    attributes[3],
                    UserType.valueOf(attributes[4]));
        } catch (IllegalArgumentException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    private long addUserId(ArrayList<User> users) {
        return users.get(users.size() - 1).getId() + 1;

    }
}
