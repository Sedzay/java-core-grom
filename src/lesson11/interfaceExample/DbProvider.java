package lesson11.interfaceExample;

public interface DbProvider {

    void connectToDb();

    void disconnectFromDb();

    void encryptData();
}
