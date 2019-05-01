package lesson35.order;

import lesson35.ReceiveId;
import lesson35.room.Room;
import lesson35.user.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Order implements ReceiveId {
    private long id;
    private User user;
    private Room room;
    private Date dateFrom;
    private Date dateTo;
    private double moneyPaid;

    public Order(long id, User user, Room room, Date dateFrom, Date dateTo, double moneyPaid) {
        this.id = id;
        this.user = user;
        this.room = room;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.moneyPaid = moneyPaid;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Room getRoom() {
        return room;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public double getMoneyPaid() {
        return moneyPaid;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Double.compare(order.moneyPaid, moneyPaid) == 0 &&
                user.equals(order.user) &&
                room.equals(order.room) &&
                dateFrom.equals(order.dateFrom) &&
                dateTo.equals(order.dateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, room, dateFrom, dateTo, moneyPaid);
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return id +
                ", " + user.getId() +
                ", " + room.getId() +
                ", " + dateFormat.format(dateFrom) +
                ", " + dateFormat.format(dateTo) +
                ", " + moneyPaid + ", ";
    }
}
