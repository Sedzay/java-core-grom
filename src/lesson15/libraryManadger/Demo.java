package lesson15.libraryManadger;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        //тест ввод админа
        System.out.println("TEST ADD Admin****************");
        BooksRepository booksRepository = new BooksRepository();
        booksRepository.addAdmin();
        //неверный пароль
        booksRepository.autorisations(UserType.ADMIN, "admin", "dmin123");
        //неверный логин
        booksRepository.autorisations(UserType.ADMIN, "dmin", "admin123");
        //Все верно
        booksRepository.autorisations(UserType.ADMIN, "admin", "admin123");
        System.out.println(Arrays.toString(booksRepository.getUsers()));
        //попытаться завести еще одного админа
        booksRepository.addAdmin();
        for (User user : booksRepository.getUsers()) {
            System.out.println(user.toString());
        }
        booksRepository.autorisations(UserType.LIBRARIAN, "admin", "admin123");
        System.out.println(Arrays.toString(booksRepository.getUsers()));

        //тест ввод библиотекаря
        System.out.println("TEST ADD Librarian****************");
        //вход как библиотекарь
        booksRepository.addLibrarian("Vasiya", "125", "someEmail", "someAddress", "Kiev", 321);
        //переавторизация админа
        booksRepository.autorisations(UserType.ADMIN, "admin", "admin123");
        System.out.println(Arrays.toString(booksRepository.getUsers()));
        booksRepository.addLibrarian("Vasiya", "125", "someEmail", "someAddress", "Kiev", 321);
        booksRepository.showLibrarians();
        booksRepository.addLibrarian("Bob", "125", "someEmail", "someAddress", "Kiev", 3214);
        booksRepository.showLibrarians();

        //тест удаления библиотекаря
        System.out.println("TEST Delete Librarian****************");
        booksRepository.deleteLibrarian(3);
        booksRepository.showLibrarians();
        booksRepository.addLibrarian("Bob", "125", "someEmail", "someAddress", "Kiev", 3214);
        booksRepository.showLibrarians();

        //тест отключения админа
        System.out.println("TEST LogOUT Admin****************");
        booksRepository.logOut();
        System.out.println(Arrays.toString(booksRepository.getUsers()));

        //тест подключения библиотекаря
        System.out.println("TEST LogIN Librarian****************");
        booksRepository.autorisations(UserType.LIBRARIAN, "Bob", "125");
        for (User user : booksRepository.getUsers()) {
            System.out.println(user.toString());
        }

        //подключение другого библиотекаря
        System.out.println();
        booksRepository.autorisations(UserType.LIBRARIAN, "Vasiya", "125");
        for (User user : booksRepository.getUsers()) {
            System.out.println(user.toString());
        }

        //тест добавление книги
        System.out.println("TEST ADD Book****************");
        booksRepository.addBooks("c199", "game of thrones", "Jordge Mikhol", "Zorya", 12);
        booksRepository.viewBooks();
        System.out.println();
        //добавление такой же книги
        System.out.println("add equales books");
        booksRepository.addBooks("c199", "game of thrones", "Jordge Mikhol", "Zorya", 3);
        booksRepository.viewBooks();
        System.out.println();
        //добавление другой книги
        System.out.println("add another books");
        booksRepository.addBooks("L200", "Kolobok", "People", "Zorya", 8);
        booksRepository.viewBooks();
        System.out.println();

        //тест выдать книгу
        System.out.println("TEST ISSUED Book****************");
        booksRepository.issueBook("c199", 3,"Kolya", 1245);
        System.out.println();
        //добавит студента визитора
        booksRepository.autorisations(UserType.ADMIN, "admin", "admin123");
        booksRepository.addVisitor("Kolya", 1245);
        booksRepository.addVisitor("John", 999);
        for (User user : booksRepository.getUsers()) {
            System.out.println(user.toString());
        }
        System.out.println();
        //выдать книгу
        booksRepository.autorisations(UserType.LIBRARIAN, "Vasiya", "125");
        booksRepository.issueBook("c199", 4,"Kolya", 1245);
        booksRepository.viewBooks();
        System.out.println();
        //нет книги
        booksRepository.issueBook("c200", 4,"Kolya", 1245);
        booksRepository.viewBooks();
        System.out.println();
        //еще одну книгу
        booksRepository.issueBook("L200", 4,"Kolya", 1245);
        booksRepository.issueBook("L200", 5,"John", 999);
        booksRepository.viewBooks();
        System.out.println();

        //тест показать список выданных книг
        booksRepository.viewIssuedBooks();

        //тест возврата книги
        System.out.println();
        //нет книги в списке
        booksRepository.returnBook("a25",6);

        booksRepository.returnBook("L200", 4);
        booksRepository.viewIssuedBooks();
        System.out.println();
        booksRepository.viewBooks();

    }
}
