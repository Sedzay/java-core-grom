package lesson15.libraryManadger;

import java.util.Arrays;
import java.util.Date;

public class Librarian extends User {
    private String email;
    private String address;
    private String city;
    private int contact;
    private DataBaseBooks dataBaseBooks;
    private Visitor[] visitors;
    private DataBaseGetBooks[] dataBaseGetBooks;

    public Librarian(long id, String name, String password, String email, String address, String city, int contact, DataBaseBooks dataBaseBooks, Visitor[] visitors, DataBaseGetBooks[] dataBaseGetBooks) {
        super(id);
        super.setName(name);
        super.setPassword(password);
        this.email = email;
        this.address = address;
        this.city = city;
        this.contact = contact;
        this.dataBaseBooks = dataBaseBooks;
        this.visitors = visitors;
        this.dataBaseGetBooks = dataBaseGetBooks;
    }

    public DataBaseBooks getDataBaseBooks() {
        return dataBaseBooks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public boolean isAuthorization() {
        return super.isAuthorization();
    }

    @Override
    public void setAuthorization(boolean authorization) {
        super.setAuthorization(authorization);
    }

    @Override
    public String toString() {
        return "Librarian{" +
                super.toString() +
                "email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", contact=" + contact +
                '}';
    }

    @Override
    public void login(String name, String password) {
        super.login(name, password);
    }

    @Override
    public void logOut() {
        super.logOut();
    }

    //------------------------------------------------------------
    public void addBook(Book book) {
        if (!checkLogin())
            return;
        if (dataBaseBooks == null)
            return;
        dataBaseBooks.addBook(book);
    }

    public void showBooks() {
        if (!checkLogin())
            return;
        if (dataBaseBooks == null)
            return;
        dataBaseBooks.showBooks();
    }

    public void issueBook(String bookCollNo, long visitorId, String visitorName, int visitorContact) {
        if (!checkLogin())
            return;
        if (!checkVisitor(visitorId))
            return;
        Book book = getBookByCollNoBook(bookCollNo);
        if (book == null || book.getQuantity() <= 0) {
            System.out.println("Quantity is null");
            return;
        }
        DataBaseGetBooks dbgs = new DataBaseGetBooks(visitorId, visitorName, new Date(), book, visitorContact);
        if (dataBaseGetBooks == null) {
            dataBaseGetBooks = new DataBaseGetBooks[1];
            dataBaseGetBooks[0] = dbgs;
        } else {
            //проверит есть ли у студента книга, которая лежит больше 30 дней
            for (DataBaseGetBooks dataBaseGetBooks1 : dataBaseGetBooks) {
                if (new Date().getTime() - dataBaseGetBooks1.getDate().getTime() > 30 * 86400000) {
                    System.out.println("Student must return old book");
                    return;
                }
            }
            dataBaseGetBooks = Arrays.copyOf(dataBaseGetBooks, dataBaseGetBooks.length + 1);
            dataBaseGetBooks[dataBaseGetBooks.length - 1] = dbgs;
        }
        for (Book book1 : dataBaseBooks.getBooks()) {
            if (book1.getCollno() == bookCollNo) {
                book1.setQuantity(book1.getQuantity() - 1);
                book1.setIssued(book1.getIssued() + 1);
            }
        }
        System.out.println("Book issued successfully!");
    }

    public void showDateBaseGetBooks() {
        if(dataBaseGetBooks == null) {
            System.out.println("DataBaseGetBooks is null");
            return;
        }
        for (DataBaseGetBooks dataBaseGetBooks1 : dataBaseGetBooks) {
            System.out.println(dataBaseGetBooks1.toString());
        }
    }

    public void returnBook(String bookCollNo, long visitorId) {
        Book book = getBookByCollNoBook(bookCollNo);
        if (book == null) {
            System.out.println("Check the book properly");
            return;
        }
        if (!checkVisitor(visitorId)) {
            System.out.println("Check the students ID properly");
            return;
        }
        if(!checkHaveVisitorBook(visitorId,bookCollNo)) {
            System.out.println("Check the students ID or the book properly");
            return;
        }
        book.setIssued(book.getIssued()-1);
        Book book1 = new Book(book.getId(),book.getCollno(),book.getName(),book.getAuthor(),book.getPublisher(),1,0,new Date());
        addBook(book1);
        DataBaseGetBooks[] newDataBaseGetBooks = null;
        int i = 0;
        for (DataBaseGetBooks dataBaseGetBooks1 : dataBaseGetBooks) {
            if (dataBaseGetBooks1.getBook().equals(book)) {
                dataBaseGetBooks[i] = null;
                newDataBaseGetBooks = new DataBaseGetBooks[dataBaseGetBooks.length - 1];
            }
            i++;
        }

        for (int j = 0, k = 0; i < dataBaseGetBooks.length; j++) {
            if (dataBaseGetBooks[i] == null) {
                continue;
            }
            newDataBaseGetBooks[k] = dataBaseGetBooks[j];
            k++;
        }
        dataBaseGetBooks = newDataBaseGetBooks;
        System.out.println("Book returned successfully!");
    }

    private boolean checkVisitor(long visitorId) {
        if (visitors == null || visitorId == 0)
            return false;
        for (Visitor visitor : visitors) {
            if (visitor != null) {
                if (visitor.getId() == visitorId) {
                    return true;
                }
            }
            System.out.println("Please check Student ID or name Curefully before issuing book");
        }
        return false;
    }

    private Book getBookByCollNoBook(String bookCollNo) {
        if (dataBaseBooks == null)
            return null;
        int i = 0;
        for (Book book : dataBaseBooks.getBooks()) {
            if (book != null) {
                if (book.getCollno() == bookCollNo)
                    return dataBaseBooks.getBooks()[i];
            }
            i++;
        }
        return null;
    }
    private boolean checkHaveVisitorBook(long visitorId, String bookCollNo) {
        for (DataBaseGetBooks dataBaseGetBooks1 : dataBaseGetBooks) {
            if(dataBaseGetBooks1.getBook().getCollno() == bookCollNo && dataBaseGetBooks1.getVisitor().getId() == visitorId)
                return true;
        }
        return false;
    }
}
