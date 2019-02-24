package lesson15.libraryManadger;

import java.util.Arrays;

public class Visitor extends User {

    private Book[] books;

    public Visitor(long id, String name) {
        super(id);
        super.setName(name);
        books = new Book[0];
    }

    public void getBook(Book book) {
        //создать масси книг с размером на один больше существующего
        //скопирвать в новый массив книг все книги из существующего массива
        //добавить туда новую книгу по ИД
        //перезаписать массив книг
        if (book == null)
            return;

        for (Book book1 : books) {
            if(findSameBook(book1,book))
                return;
        }

        books = Arrays.copyOf(books, books.length + 1);
        int i = 0;
        for (Book book1 : books) {
            if (book1 == null) {
                books[i] = book;
            }
            i++;
        }
    }

    public void returnBook(long id) {
        int i = 0;
        Book[] newBooks = books;
        for (Book book : newBooks){
            if (book.getId() == id) {
                newBooks[i] = null;
                continue;
            }
            i++;
        }
        books = new Book[i];
        int j = 0;
        for (Book book : newBooks) {
            if(book == null)
                continue;
            books[j] = book;
            j++;
        }
    }

    private boolean findSameBook(Book book1, Book book) {
        if (book1.getId() == book.getId()
                && book1.getCollno() == book.getCollno()
                && book1.getName() == book.getName()
                && book1.getAuthor() == book.getAuthor()
                && book1.getPublisher() == book.getPublisher())
            return true;
        return false;
    }


}
