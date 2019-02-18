package lesson11.interfaceExample;

public class MySQLProvider implements DbProvider {

    @Override
    public void connectToDb() {
        //logic for MySQL
    }

    @Override
    public void disconnectFromDb() {

    }

    @Override
    public void encryptData() {

    }
}
