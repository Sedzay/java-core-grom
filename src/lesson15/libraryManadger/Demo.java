package lesson15.libraryManadger;

import java.util.Arrays;
import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        //тест логина админа
        System.out.println("TEST LOGIN ADMIN****************");
        System.out.println("incorrect login: ");
        Admin admin = new Admin(1001);
        admin.login("Vasya", "admin123");
        System.out.println(admin.isAuthorization());
        System.out.println("incorrect password: ");
        admin = new Admin(1001);
        admin.login("admin", "123");
        System.out.println(admin.isAuthorization());
        System.out.println("correct password: ");
        admin = new Admin(1001);
        admin.login("admin", "admin123");
        System.out.println(admin.isAuthorization());
        System.out.println("--------------------------------");

        //тест добавления librarian
        System.out.println("TEST ADD Librarian****************");
        Book book1 = new Book(10002, "2", "Game of trones", "Georg Mikol", "Zorya", 1, 0, new Date());
        Book book2 = new Book(10001, "1", "Kolobok", "People", "Zorya", 1, 0, new Date());

        Book[] books = {book1, book2};
        DataBaseBooks dataBaseBooks = new DataBaseBooks(books);
        Visitor visitor1 = new Visitor(1,"Petrov");
        Visitor visitor2 = new Visitor(2, "Ivanov");
        Visitor[] visitors = {visitor1, visitor2};
        admin.addLibrarian(101, "Vasiya", "123", "someEmail", "Topol", "Kiev", 123456, dataBaseBooks, visitors,null);
        admin.addLibrarian(102, "Vasiya", "123", "someEmail", "Topol", "Kiev", 123456, dataBaseBooks, visitors, null);
        admin.addLibrarian(103, "Vasiya", "123", "someEmail", "Topol", "Kiev", 123456, dataBaseBooks, visitors, null);
        System.out.println(Arrays.toString(admin.getLibrarians()));
        System.out.println("--------------------------------");

        System.out.println("if add librarian with same id: ");
        admin.addLibrarian(103, "Vasiya", "123", "someEmail", "Topol", "Kiev", 123456, dataBaseBooks, visitors, null);
        System.out.println(Arrays.toString(admin.getLibrarians()));
        System.out.println("--------------------------------");

        //тест просмотр librarian
        System.out.println("TEST VIEW Librarian****************");
        admin.viewLibrarian();
        System.out.println("--------------------------------");

        //если нет записей
        System.out.println("if Librarians are not: ");
        Admin admin1 = new Admin(1002);
        admin1.viewLibrarian();
        admin1.login("admin", "admin123");
        admin1.viewLibrarian();
        System.out.println("--------------------------------");

        //тест удалить librarian
        System.out.println("TEST DELETE Librarian****************");
        admin.deleteLibrarian(102);
        System.out.println(Arrays.toString(admin.getLibrarians()));
        System.out.println("--------------------------------");

        //тест logout admin
        System.out.println("TEST LogOut ADMIN****************");
        admin.logOut();
        System.out.println(admin.isAuthorization());
        System.out.println("--------------------------------");

        //тест добавить книгу в базу данных
        System.out.println("TEST ADD BOOK in DATABASE BOOKS****************");
        System.out.println("add other book");

        Book book = new Book(10003, "3", "It", "Posev", "Zorya", 12, 0, new Date());
        for (Librarian librarian : admin.getLibrarians()) {
            librarian.login("Vasiya", "123");
        }
        admin.getLibrarians()[0].addBook(book);
        System.out.println("--------------------------------");

        System.out.println("add same book");
        admin.getLibrarians()[0].addBook(book);
        admin.getLibrarians()[0].showBooks();
        admin.getLibrarians()[1].showBooks();
        System.out.println("--------------------------------");

        //тест выдать книгу студенту
        System.out.println("TEST ISSUE BOOK TO Student****************");
        System.out.println("no problem: ");
        admin.getLibrarians()[0].issueBook("1", 1,"Petrov", 121212);
        admin.getLibrarians()[0].showBooks();
        admin.getLibrarians()[0].showDateBaseGetBooks();

        //количество книг не достаточно
        System.out.println("quantity is not: ");
        admin.getLibrarians()[0].issueBook("1", 1,"Petrov", 121212);
        admin.getLibrarians()[0].showBooks();
        admin.getLibrarians()[0].showDateBaseGetBooks();


        //тест вернуть книгу в иблиотеку
        System.out.println("TEST RETURN BOOK TO LIBRARY****************");
        System.out.println("no book: ");
        admin.getLibrarians()[0].returnBook("3",1);
        admin.getLibrarians()[0].showBooks();
        admin.getLibrarians()[0].showDateBaseGetBooks();

        System.out.println("no problem: ");
        admin.getLibrarians()[0].returnBook("1",1);
        admin.getLibrarians()[0].showBooks();
        admin.getLibrarians()[0].showDateBaseGetBooks();

    }
}
