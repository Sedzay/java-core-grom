package lesson11.interfaceExample;

public class OracleDbProvider implements DbProvider {

    @Override
    public void connectToDb() {
        //logic for Oracle
    }

    @Override
    public void disconnectFromDb() {
        //logic for Oracle
    }

    @Override
    public void encryptData() {

    }
}
