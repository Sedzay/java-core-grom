package lesson15.libraryManadger;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {

        BooksRepository booksRepository = new BooksRepository();
        UserRepository userRepository = booksRepository.getUserRepository();
        System.out.println(Arrays.toString(userRepository.getUsers()));


        //add librarian
        userRepository.addLibrarian("lib1", "111", "email1", "address1", "city", 1);
        userRepository.autorisation(UserType.ADMIN, "ad", "pas");
        userRepository.autorisation(UserType.ADMIN, "admin", "admin123");

        userRepository.addLibrarian("lib1", "111", "email1", "address1", "city", 1);
        System.out.println(Arrays.toString(userRepository.getUsers()));

        userRepository.addLibrarian("lib1", "111", "email1", "address1", "city", 1);
        System.out.println(Arrays.toString(userRepository.getUsers()));

        userRepository.addLibrarian("lib2", "222", "email2", "address2", "city", 2);
        System.out.println(Arrays.toString(userRepository.getUsers()));

        //add visitor
        userRepository.addVisitor("Vasiya", 3);
        userRepository.addVisitor("Kolya", 8);
        System.out.println(Arrays.toString(userRepository.getUsers()));

        //show librarian
        userRepository.showLibrarians();

        //delete librarian
        userRepository.deleteLibrarian(333);
        System.out.println();
        userRepository.showLibrarians();

        userRepository.deleteLibrarian(2);
        System.out.println();
        userRepository.showLibrarians();
        System.out.println();
        System.out.println(Arrays.toString(userRepository.getUsers()));


        //add book
        booksRepository.addBook("nom1", "Kolobok", "People", "Zoriya", 5);
        //incorrect login & password
        userRepository.autorisation(UserType.LIBRARIAN, "lib", "lib");
        //correct login & password
        userRepository.autorisation(UserType.LIBRARIAN, "lib2", "222");
        booksRepository.addBook("nom1", "Kolobok", "People", "Zoriya", 5);
        booksRepository.addBook("nom1", "Kolobok", "People", "Zoriya", 1);
        booksRepository.viewBooks();

        booksRepository.addBook("nom2", "Skazka", "People2", "Zoriya", 4);
        booksRepository.viewBooks();


        //issue book
        booksRepository.issueBook("nom2", 4, "Vasiya", 3);
        booksRepository.issueBook("nom2", 5, "Kolya", 8);
        booksRepository.viewBooks();

        booksRepository.issueBook("nom1", 4, "Vasiya2", 4);
        booksRepository.viewBooks();

        for (User user : userRepository.getUsers()) {
            if (user != null && user.getUserType() == UserType.VISITOR) {
                System.out.println(Arrays.toString(user.getBooks()));
            }
        }

        //return book
        booksRepository.returnBook("nom1", 2);
        booksRepository.returnBook("nom4", 4);
        booksRepository.returnBook("nom2", 4);
        booksRepository.returnBook("nom2", 5);
        booksRepository.viewBooks();

        for (User user : userRepository.getUsers()) {
            if (user != null && user.getUserType() == UserType.VISITOR) {
                System.out.println(Arrays.toString(user.getBooks()));
            }
        }


    }
}
