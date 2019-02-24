package lesson15.libraryManadger;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Admin extends User {

    //при создании юзера админ ему вводятся логин и пароль
    //если логин admin и пароль admin123, то система дает залогинеться

    private Librarian[] librarians;


    public Admin(long id) {
        super(id);
        super.setName("admin");
        super.setPassword("admin123");
        librarians = new Librarian[0];

    }

    public Librarian[] getLibrarians() {
        return librarians;
    }


    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public boolean isAuthorization() {
        return super.isAuthorization();
    }

    @Override
    public void setAuthorization(boolean authorization) {
        super.setAuthorization(authorization);
    }

    //---------------------------------------------------------------

    @Override
    public void login(String name, String password) {
        super.login(name, password);
    }

    @Override
    public void logOut() {
        super.logOut();
    }

    //--------------------------------------------------------------

    public void addLibrarian(int id, String name, String password, String email, String address, String city, int contact, DataBaseBooks dataBaseBooks, Visitor[] visitors, DataBaseGetBooks[] dataBaseGetBooks) {
        if (!checkLogin())
            return;

        for (Librarian librarian : librarians) {
            if (librarian.getId() == id)
                return;
        }

        Librarian newLibrarian = new Librarian(id, name, password, email, address, city, contact,dataBaseBooks,visitors, dataBaseGetBooks);
        librarians = Arrays.copyOf(librarians,librarians.length + 1);
        int i = 0;
        for (Librarian librarian : librarians) {
            if (librarian == null) {
                add(newLibrarian, i);
            }
            i++;
        }
    }

    //------------------------------------------------------------

    public void viewLibrarian() {

        if (!checkLogin())
            return;

        if (librarians.length == 0)
            System.out.println("Librarians is null");
        for (Librarian librarian : librarians) {
            System.out.println(librarian);
        }
    }

    //--------------------------------------------------------------

    public void deleteLibrarian(long id) {
        if (!checkLogin())
            return;
        Librarian[] newLibrarians = null;
        int k = 0;
        for (Librarian librarian : librarians) {
            if(librarian.getId() == id) {
                librarians[k] = null;
                System.out.println("Record deleted successfully");
                newLibrarians = new Librarian[librarians.length - 1];
            }
            k++;
        }
        for (int i = 0, j = 0; i < librarians.length; i++) {
            if (librarians[i] == null) {
                continue;
            }
            newLibrarians[j] = librarians[i];
            j++;
        }
        librarians = newLibrarians;

    }

    //--------------------------------------------------------------

    private void add(Librarian librarian, int index) {
        librarians[index] = librarian;
        System.out.println("Librarian added successfully");
    }


}
