package lesson15.libraryManadger;

import java.util.Arrays;
import java.util.Date;

public class BooksRepository {
    private User[] users;
    private Book[] books;

    UserType userTypeLogin;

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public void addAdmin() {
        if (users == null) {
            User user = new User(1, "admin", "admin123", UserType.ADMIN, null, null, null, 0);
            users = new User[]{user};
        } else
            System.out.println("admin already exists");
    }

    public void autorisations(UserType userType, String name, String password) {
        if (users == null || userType == UserType.VISITOR)
            return;
        boolean findUser = false;
        this.userTypeLogin = userType;
        for (User user : users) {
            if (user.isAuthorization() == true)
                user.setAuthorization(false);
            if (userType == user.getUserType()) {
                logIn(name, password);
                findUser = true;
            }
        }
        if (!findUser)
            System.out.println("Haven't user with users type " + userTypeLogin);
    }

    public void addVisitor(String name, int contact) {
        if (!checkAutorisationsAdmin())
            return;
        if (name == null || contact == 0) {
            System.out.println("You must filling all fields");
            return;
        }
        long id = users[users.length - 1].getId() + 1;
        User user = new User(id, name, null, UserType.VISITOR, null, null, null, contact);
        users = Arrays.copyOf(users, users.length + 1);
        users[users.length - 1] = user;
        System.out.println("Visitor added successfully!");
    }

    public void addLibrarian(String name, String password, String email, String address, String city, int contact) {
        if (!checkAutorisationsAdmin() || users == null)
            return;
        if (name == null || password == null || email == null || address == null || city == null || contact == 0) {
            System.out.println("You must filling all fields");
            return;
        }

        long id = users[users.length - 1].getId() + 1;
        User user = new User(id, name, password, UserType.LIBRARIAN, email, address, city, contact);
        users = Arrays.copyOf(users, users.length + 1);
        users[users.length - 1] = user;
        System.out.println("Librarian added successfully!");
    }

    public void showLibrarians() {
        if (!checkAutorisationsAdmin() || users == null)
            return;
        for (User user : users) {
            if (user.getUserType() == UserType.LIBRARIAN)
                System.out.println(user.toString());
        }
    }

    public void deleteLibrarian(long id) {
        if (!checkAutorisationsAdmin() || users == null)
            return;
        boolean findUser = false;
        int k = 0;
        for (User user : users) {
            if (user.getUserType() == UserType.LIBRARIAN && user.getId() == id) {
                users[k] = null;
                System.out.println("Record deleted successfully!");
                findUser = true;
                continue;
            }
            k++;
        }
        if (!findUser) {
            System.out.println("user with type " + UserType.LIBRARIAN + " or id not found");
            return;
        }
        User[] newUsers = new User[users.length - 1];
        for (int i = 0, j = 0; i < users.length; i++) {
            if (users[i] == null) {
                continue;
            }
            newUsers[j] = users[i];
            j++;
        }
        users = newUsers;
    }

    public void logOut() {
        //найти юзера с нужным типом, авторизацию и тип залогиненного юзера обнулить
        for (User user : users) {
            if (user.getUserType() == userTypeLogin) {
                user.setAuthorization(false);
                userTypeLogin = null;
            }
        }
    }

    public void addBooks(String collNo, String name, String author, String publisher, int quantity) {
        if (!checkAutorisationsLibrarian())
            return;
        if (collNo == null || name == null || author == null || publisher == null || quantity == 0) {
            System.out.println("all fields must be filling!");
            return;
        }
        Book book = new Book(0, collNo, name, author, publisher, quantity, 0, new Date());
        if (books == null) {
            book.setId(1);
            book.setAddededDate(new Date());
            books = new Book[]{book};
            printAddBooks();
            return;
        }
        for (Book book1 : books) {
            if (book1.equals(book) && book1.hashCode() == book.hashCode()) {
                book1.setQuantity(book1.getQuantity() + quantity);
                book1.setAddededDate(new Date());
                printAddBooks();
                return;
            }
        }
        books = Arrays.copyOf(books, books.length + 1);
        book.setId(books[books.length - 2].getId() + 1);
        books[books.length - 1] = book;
        printAddBooks();
    }

    public void viewBooks() {
        if (!checkAutorisationsLibrarian())
            return;
        if (books == null) {
            System.out.println("Array of books is empty");
            return;
        }
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }


    public void issueBook(String collNo, long id, String name, int contact) {
        if (!checkAutorisationsLibrarian())
            return;
        if (books == null)
            return;
        Book book = findBook(collNo);
        if (book == null || book.getQuantity()<=0) {
            System.out.println("Book not found");
            return;
        }
        User visitor = findVisitor(id);
        if (visitor == null) {
            System.out.println("Note: Please check Student id Carefully before issuing book");
            return;
        }
        if (!checkLastDateIssueBook(visitor)) {
            System.out.println("Student mast return book because last book was issued " + book.getIssuedDate());
            return;
        }
        book.setQuantity(book.getQuantity() - 1);
        book.setIssued(book.getIssued() + 1);
        addVisitorIssueBook(book, visitor, name, contact);
    }

    public void returnBook(String collNo, long id) {
        if (!checkAutorisationsLibrarian())
            return;
        if (collNo == null || id <= 0) {
            System.out.println("Incorrect data");
            return;
        }
        Book book = findBook(collNo);
        if (book == null) {
            System.out.println("Check the book properly");
            return;
        }
        User visitor = findVisitor(id);
        if (visitor == null) {
            System.out.println("Check the students ID properly");
            return;
        }
        book.setQuantity(book.getQuantity() + 1);
        book.setIssued(book.getIssued() - 1);

        if (book.getVisitors()!=null) {
            int i = 0;
            for (User user : book.getVisitors()) {
                if (user.equals(visitor) && user.hashCode() == visitor.hashCode()) {
                    book.getVisitors()[i] = null;
                    book.getIssuedDate()[i] = null;
                    break;
                }
                i++;
            }

            User[] newVisitors = new User[book.getVisitors().length-1];
            Date[] newIssuedBook = new Date[book.getIssuedDate().length-1];

            for (int j = 0, k = 0; j<book.getVisitors().length; j++) {
                if(book.getVisitors()[j] == null)
                    continue;
                newVisitors[k] = book.getVisitors()[j];
                newIssuedBook[k] = book.getIssuedDate()[j];
                k++;
            }
            book.setVisitors(newVisitors);
            book.setIssuedDate(newIssuedBook);
            System.out.println("Book returned successfully");
        }
    }

    public void viewIssuedBooks() {
        if (!checkAutorisationsLibrarian())
            return;
        if (books == null) {
            System.out.println("Array of books is empty");
            return;
        }
        for (Book book : books) {
            if (book.getVisitors() != null) {
                for (int i = 0; i < book.getVisitors().length; i++) {
                    System.out.println("Book{" +
                            "id=" + book.getId() +
                            ", bookcollno='" + book.getCollno() + '\'' +
                            ", studentId=" + book.getVisitors()[i].getId() +
                            ", studentName=" + book.getVisitors()[i].getName() +
                            ", studentContact=" + book.getVisitors()[i].getContact() +
                            ", issuedDate=" + book.getIssuedDate()[i] +
                            '}');
                }
            }
        }
    }


    //-----------------------------------------------------------------
    private void printAddBooks() {
        System.out.println("Book added successfully!");
    }

    private boolean checkAutorisationsAdmin() {
        if (userTypeLogin != UserType.ADMIN) {
            System.out.println("Log in how Admin");
            return false;
        }
        return true;
    }

    private boolean checkAutorisationsLibrarian() {
        if (userTypeLogin != UserType.LIBRARIAN) {
            System.out.println("Log in how Librarian");
            return false;
        }
        return true;
    }

    private void logIn(String name, String password) {
        if (name == null || password == null)
            return;
        for (User user : users) {
            if (user.getName() == name && user.getPassword() == password) {
                user.setAuthorization(true);
                return;
            }
        }
        System.out.println("Incorrect login or password in user with users type " + userTypeLogin);
        return;
    }

    private Book findBook(String collNo) {
        Book book = null;
        for (Book book1 : books) {
            if (book1.getCollno() == collNo) {
                book = book1;
            }
        }
        return book;
    }

    private User findVisitor(long id) {
        if (users == null)
            return null;

        for (User user : users) {
            if (user.getId() == id && user.getUserType() == UserType.VISITOR)
                return user;
        }
        return null;
    }

    private void addVisitorIssueBook(Book book, User user, String name, int contact) {
        User[] visitors = book.getVisitors();
        Date[] issueDates = book.getIssuedDate();
        User visitor = user;
        if (visitors == null) {
            visitors = new User[]{visitor};
            issueDates = new Date[]{new Date()};
        }
        else {
            visitors = Arrays.copyOf(visitors, visitors.length + 1);
            visitors[visitors.length - 1] = visitor;

            issueDates = Arrays.copyOf(issueDates, issueDates.length + 1);
            issueDates[issueDates.length - 1] = new Date();
        }
        book.setVisitors(visitors);
        book.setIssuedDate(issueDates);
        System.out.println("Book issued successfully!");
    }

    private boolean checkLastDateIssueBook(User visitor) {
        for (Book book : books) {
            int i = 0;
            if (book.getVisitors() != null) {
                for (User user : book.getVisitors()) {
                    if (user.equals(visitor) && user.hashCode() == visitor.hashCode()) {
                        if (new Date().getTime() - book.getIssuedDate()[i].getTime() < 30 * 86400000) {
                            System.out.println("Student must return old book");
                            return false;
                        }
                    }
                    i++;
                }
            }
        }
        return true;
    }
}
