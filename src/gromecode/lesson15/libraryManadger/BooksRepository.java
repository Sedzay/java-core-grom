package gromecode.lesson15.libraryManadger;

import java.util.Date;

public class BooksRepository {

    private Book[] books = new Book[20];
    private UserRepository userRepository = new UserRepository();

    public Book[] getBooks() {
        return books;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public Book addBook(String collNo, String name, String author, String publisher, int quantity) {

        if (!userRepository.checkTypeOfAutorisedUser(UserType.LIBRARIAN)) {
            return null;
        }

        Book book = new Book(0, collNo, name, author, publisher, new Date());


        for (int i = 0; i < quantity; i++) {
            for (int j = 0; j < books.length; j++) {
                if (books[j] == null) {
                    books[j] = new Book(assignId(j), collNo, name, author, publisher, new Date());
                    System.out.println("Book with collno " + book.getCollno() + " and id " + books[j].getId() + " added successfully");
                    break;
                }
            }
        }
        return book;
    }


    public void viewBooks() {

        if (!userRepository.checkTypeOfAutorisedUser(UserType.LIBRARIAN)) {
            return;
        }
        Book[] newArrayBooks = new Book[books.length];

        for (Book book : books) {
            int i = 0;
            for (Book book1 : newArrayBooks) {
                if (book != null && book1 != null && book.equals(book1))
                    break;
                if (book != null && book1 == null) {
                    newArrayBooks[i] = book;
                    break;
                }
                i++;
            }
        }

        for (Book book : newArrayBooks) {
            if (book != null) {
                System.out.println(book.toString() + ", Count = " + countSameBooks(book) + ", Count issue = " + countIssuedBooks(book));
            }
        }
    }


    public void issueBook(String collNo, long id, String name, int contact) {
        if (!userRepository.checkTypeOfAutorisedUser(UserType.LIBRARIAN)) {
            return;
        }

        Book book = findBookForIssue(collNo);
        if (book == null) {
            System.out.println("Book for issue not found");
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
        //обновить у студента данные имени и контакта
        visitor.setName(name);
        visitor.setContact(contact);

        //записать студента в книге
        book.setVisitors(visitor);

        //записать дату выдачи
        book.setIssuedDate(new Date());

        //выдать книгу
        int index = 0;
        for (Book book1 : visitor.getBooks()) {
            if (book1 == null) {
                visitor.getBooks()[index] = book;
                System.out.println("Book with collno " + collNo + " issued successfully");
                return;
            }
            index++;
        }
    }


    public void returnBook(String collNo, long id) {
        if (!userRepository.checkTypeOfAutorisedUser(UserType.LIBRARIAN)) {
            return;
        }

        Book book = findBookForReturn(collNo, id);
        if (book == null) {
            System.out.println("Student with id " + id + " have not book with collno " + collNo);
            return;
        }

        //удалить книгу из списка юзера

        for (User user : userRepository.getUsers()) {
            if (user != null && user.getId() == id) {
                int index = 0;
                for (Book book1 : user.getBooks()) {
                    if (book1.equals(book)) {
                        user.getBooks()[index] = null;
                        System.out.println("Book returned successfully");
                        break;
                    }
                    index++;
                }
            }
        }

        //убрать студента из книги
        book.setVisitors(null);

        //обнулить дату выдачи
        book.setIssuedDate(null);
    }

    public void viewIssuedBooks() {
        if (!userRepository.checkTypeOfAutorisedUser(UserType.LIBRARIAN)) {
            return;
        }

        for (User user : userRepository.getUsers()) {
            if (user != null && user.getUserType() == UserType.VISITOR) {
                for (Book book : user.getBooks()) {
                    if (book != null) {
                        System.out.println("Book{" +
                                "id=" + book.getId() +
                                ", bookcollno='" + book.getCollno() + '\'' +
                                ", studentId=" + user.getId() +
                                ", studentName=" + user.getName() +
                                ", studentContact=" + user.getContact() +
                                ", issuedDate=" + book.getIssuedDate() +
                                '}');
                    }
                }
            }
        }
    }


    //-----------------------------------------------------------------
    private int countSameBooks(Book book) {
        int count = 0;
        for (Book book1 : books) {
            if (book1 != null && book1.equals(book)) {
                count++;
            }
        }
        return count;
    }

    private int countIssuedBooks(Book book) {
        int count = 0;
        for (User user : userRepository.getUsers()) {
            if (user != null && user.getUserType() == UserType.VISITOR) {
                for (Book book1 : user.getBooks()) {
                    if (book1 != null && book1.equals(book)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private Book findBookForIssue(String collNo) {

        //найти книги по нужному номеру
        //исключить уже выданные книги
        Book newBook = null;

        for (Book book : books) {
            if (book != null && book.getCollno().equals(collNo)) {
                newBook = findBookFromUser(book);
            }
            if (newBook != null) {
                return book;
            }
        }
        return null;
    }


    private Book findBookFromUser(Book book) {

        for (User user : userRepository.getUsers()) {
            if (user != null && user.getUserType() == UserType.VISITOR) {
                for (Book book1 : user.getBooks()) {
                    if (book1 != null && book1.getId() == book.getId())
                        return null;
                }
            }
        }
        return book;
    }


    private long assignId(int index) {

        return index == 0 ? 1 : books[index - 1].getId() + 1;
    }


    private User findVisitor(long id) {

        for (User user : userRepository.getUsers()) {
            if (user != null && user.getId() == id && user.getUserType() == UserType.VISITOR)
                return user;
        }
        return null;
    }


    private boolean checkLastDateIssueBook(User visitor) {

        for (Book book : visitor.getBooks()) {
            if (book != null && new Date().getTime() - book.getIssuedDate().getTime() < 30 * 86400000) {
                System.out.println("Student must return old book");
                return false;
            }
        }
        return true;
    }

    private Book findBookForReturn(String collno, long id) {
        for (User user : userRepository.getUsers()) {
            if (user != null && user.getId() == id) {
                for (Book book : user.getBooks()) {
                    if (book != null && book.getCollno().equals(collno))
                        return book;
                }
            }
        }
        return null;
    }


}
