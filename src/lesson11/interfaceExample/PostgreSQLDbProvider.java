package lesson11.interfaceExample;

public class PostgreSQLDbProvider implements DbProvider {

    @Override
    public void connectToDb() {
        //logic for Postgres
    }
    @Override
    public void disconnectFromDb() {
        //logic for Postgres
    }

    @Override
    public void encryptData() {

    }
}
