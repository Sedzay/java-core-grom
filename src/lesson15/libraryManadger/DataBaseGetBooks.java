package lesson15.libraryManadger;

import java.util.Date;

public class DataBaseGetBooks {
    private Visitor visitor;
    private Date date;
    private Book book;
    private int contact;

    public DataBaseGetBooks(long visitorId, String visitorName, Date date, Book book, int contact) {
        visitor = new Visitor(visitorId,visitorName);
        this.date = date;
        this.book = book;
        this.contact = contact;
    }

    public int getContact() {
        return contact;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "DataBaseGetBooks{" +
                "visitor=" + visitor.getName() +
                ", id=" + visitor.getId() +
                ", date=" + date +
                ", book=" + book +
                ", contact=" + contact +
                '}';
    }
}
