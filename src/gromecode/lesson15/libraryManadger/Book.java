package gromecode.lesson15.libraryManadger;

import java.util.Date;
import java.util.Objects;

public class Book {
    private long id;
    private String collno;
    private String name;
    private String author;
    private String publisher;
    private Date addededDate;

    private User visitor;
    private Date issuedDate;

    public Book(long id, String collno, String name, String author, String publisher, Date addededDate) {
        this.id = id;
        this.collno = collno;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.addededDate = addededDate;
    }

    public long getId() {
        return id;
    }

    public String getCollno() {
        return collno;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public Date getAddededDate() {
        return addededDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAddededDate(Date addededDate) {
        this.addededDate = addededDate;
    }

    public User getVisitors() {
        return visitor;
    }

    public void setVisitors(User visitors) {
        this.visitor = visitors;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return collno.equals(book.collno)
                && name.equals(book.name)
                && author.equals(book.author)
                && publisher.equals(book.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collno, name, author, publisher);
    }

    @Override
    public String toString() {
        return "Book{" +
                "collno='" + collno + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", addededDate=" + addededDate +
                '}';
    }
}
