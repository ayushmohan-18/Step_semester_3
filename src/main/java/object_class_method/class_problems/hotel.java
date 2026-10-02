
package main.java.object_class_method.class_problems;

import java.time.LocalDate;
import java.util.*;

abstract class Room {
    private String roomNumber;

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public abstract double calculatePrice(long nights);
}

class StandardRoom extends Room {
    public StandardRoom(String number) {
        super(number);
    }

    public double calculatePrice(long nights) {
        return nights * 100;
    }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(String number) {
        super(number);
    }

    public double calculatePrice(long nights) {
        return nights * 180;
    }
}

class Suite extends Room {
    public Suite(String number) {
        super(number);
    }

    public double calculatePrice(long nights) {
        return nights * 300;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Reservation {
    private Room room;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate cancellationDeadline;
    private boolean active = true;

    public Reservation(Room room, Customer customer,
                       LocalDate startDate, LocalDate endDate,
                       LocalDate cancellationDeadline) {
        this.room = room;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cancellationDeadline = cancellationDeadline;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean isActive() {
        return active;
    }

    public boolean overlaps(LocalDate start, LocalDate end) {
        return active && start.isBefore(endDate)
                && end.isAfter(startDate);
    }

    public void cancel(LocalDate today) {
        if (!active) {
            System.out.println("Reservation is already cancelled.");
        } else if (today.isAfter(cancellationDeadline)) {
            System.out.println("Cancellation deadline has passed.");
        } else {
            active = false;
            System.out.println("Reservation for "
                    + customer.getName() + ", Room "
                    + room.getRoomNumber()
                    + " cancelled successfully.");
        }
    }
}

public class hotel {
    private static List<Reservation> reservations =
            new ArrayList<>();

    public static void main(String[] args) {
        Room standard = new StandardRoom("101");
        Room deluxe = new DeluxeRoom("201");

        Customer a = new Customer("Customer A");
        Customer b = new Customer("Customer B");
        Customer c = new Customer("Customer C");

        LocalDate start1 = LocalDate.of(2026, 1, 1);
        LocalDate end1 = LocalDate.of(2026, 1, 5);

        checkAvailability(standard, start1, end1);

        Reservation r1 = book(standard, a, start1, end1,
                LocalDate.of(2025, 12, 30));

        book(standard, b,
                LocalDate.of(2026, 1, 3),
                LocalDate.of(2026, 1, 7),
                LocalDate.of(2026, 1, 1));

        if (r1 != null) {
            r1.cancel(LocalDate.of(2025, 12, 29));
        }

        book(deluxe, c,
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 12),
                LocalDate.of(2026, 2, 8));
    }

    static boolean isAvailable(Room room,
                               LocalDate start, LocalDate end) {
        for (Reservation r : reservations) {
            if (r.getRoom() == room && r.overlaps(start, end)) {
                return false;
            }
        }
        return true;
    }

    static void checkAvailability(Room room,
                                  LocalDate start, LocalDate end) {
        if (isAvailable(room, start, end)) {
            System.out.println("Room " + room.getRoomNumber()
                    + " is available from " + start + " to " + end);
        } else {
            System.out.println("Room " + room.getRoomNumber()
                    + " is not available from " + start + " to " + end);
        }
    }

    static Reservation book(Room room, Customer customer,
                            LocalDate start, LocalDate end,
                            LocalDate deadline) {
        if (!end.isAfter(start)) {
            System.out.println("Invalid reservation dates.");
            return null;
        }

        if (!isAvailable(room, start, end)) {
            System.out.println("Room " + room.getRoomNumber()
                    + " is not available for selected dates.");
            return null;
        }

        Reservation reservation = new Reservation(
                room, customer, start, end, deadline);

        reservations.add(reservation);

        long nights = end.toEpochDay() - start.toEpochDay();

        System.out.println("Reservation confirmed for "
                + customer.getName() + ", Room "
                + room.getRoomNumber() + " (" + start
                + " to " + end + ")");

        System.out.println("Price: $"
                + room.calculatePrice(nights));

        return reservation;
    }
}