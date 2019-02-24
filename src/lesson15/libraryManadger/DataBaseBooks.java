package lesson15.libraryManadger;

import java.util.Arrays;
import java.util.Date;

public class DataBaseBooks {
    private Book[] books;

    public DataBaseBooks(Book[]books) {
        this.books = books;
    }

    public Book[] getBooks() {
        return books;
    }

    public void addBook(Book book) {
        if (book == null)
            return;

        for (Book book1 : books) {
            if (findSameBook(book1, book)) {
                book1.setQuantity(book1.getQuantity() + book.getQuantity());
                book1.setAddededDate(new Date());
                return;
            }
        }

        books = Arrays.copyOf(books, books.length + 1);
        int i = 0;
        for (Book book1 : books) {
            if (book1 == null)
                books[i] = book;
            i++;
        }
    }

    public Book findBookById(long id) {
        for (Book book : books) {
            if (book.getId() == id)
                return book;
        }
        return null;
    }

    public void showBooks() {
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    private boolean findSameBook(Book book1, Book book) {
        if (book1.equals(book) && book1.hashCode() == book.hashCode())
            return true;
        return false;
    }
}
