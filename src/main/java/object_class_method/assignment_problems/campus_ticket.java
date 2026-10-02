package main.java.object_class_method.assignment_problems;

import java.time.LocalDateTime;
import java.util.*;

class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

abstract class Seat {

    private String seatId;

    public Seat(String seatId) {
        this.seatId = seatId;
    }

    public String getSeatId() {
        return seatId;
    }

    public abstract double getPrice();
}

class RegularSeat extends Seat {

    public RegularSeat(String seatId) {
        super(seatId);
    }

    @Override
    public double getPrice() {
        return 150;
    }
}

class PremiumSeat extends Seat {

    public PremiumSeat(String seatId) {
        super(seatId);
    }

    @Override
    public double getPrice() {
        return 250;
    }
}

class ReclinerSeat extends Seat {

    public ReclinerSeat(String seatId) {
        super(seatId);
    }

    @Override
    public double getPrice() {
        return 400;
    }
}

class Show {

    private String showName;
    private LocalDateTime startTime;
    private Map<String, Seat> bookedSeats = new HashMap<>();

    public Show(String showName, LocalDateTime startTime) {
        this.showName = showName;
        this.startTime = startTime;
    }

    public boolean isSeatAvailable(Seat seat) {
        return !bookedSeats.containsKey(seat.getSeatId());
    }

    public boolean bookSeats(List<Seat> seats) {

        if (seats.size() > 6 || seats.isEmpty()) {
            System.out.println("Booking must contain 1 to 6 seats.");
            return false;
        }

        Set<String> requestedIds = new HashSet<>();

        for (Seat seat : seats) {

            if (!requestedIds.add(seat.getSeatId())) {
                System.out.println("Duplicate seat in booking: "
                        + seat.getSeatId());
                return false;
            }

            if (!isSeatAvailable(seat)) {
                System.out.println("Seat " + seat.getSeatId()
                        + " is already booked for this show.");
                return false;
            }
        }

        for (Seat seat : seats) {
            bookedSeats.put(seat.getSeatId(), seat);
        }

        return true;
    }

    public void releaseSeats(List<Seat> seats) {
        for (Seat seat : seats) {
            bookedSeats.remove(seat.getSeatId());
        }
    }

    public boolean hasStarted() {
        return !LocalDateTime.now().isBefore(startTime);
    }

    public String getShowName() {
        return showName;
    }
}

class Booking {

    private Customer customer;
    private Show show;
    private List<Seat> seats;
    private boolean cancelled;

    public Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>(seats);
        this.cancelled = false;
    }

    public void confirm() {

        if (show.bookSeats(seats)) {

            StringJoiner seatNames = new StringJoiner(", ");
            double total = 0;

            for (Seat seat : seats) {
                seatNames.add(seat.getSeatId());
                total += seat.getPrice();
            }

            System.out.printf(
                    "Booking confirmed for %s: %s. Total: ₹%.2f.%n",
                    customer.getName(),
                    seatNames,
                    total
            );
        }
    }

    public void cancel() {

        if (cancelled) {
            System.out.println("Booking already cancelled.");
            return;
        }

        if (show.hasStarted()) {
            System.out.println("Cannot cancel: show has already started.");
            return;
        }

        show.releaseSeats(seats);
        cancelled = true;

        StringJoiner seatNames = new StringJoiner(", ");

        for (Seat seat : seats) {
            seatNames.add(seat.getSeatId());
        }

        System.out.println(customer.getName()
                + "'s booking cancelled. Seats "
                + seatNames + " released.");
    }
}

public class campus_ticket {

    public static void main(String[] args) {

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Show show = new Show(
                "7 PM Show",
                LocalDateTime.now().plusHours(2)
        );

        Booking b1 = new Booking(
                asha,
                show,
                Arrays.asList(
                        new RegularSeat("A1"),
                        new RegularSeat("A2"),
                        new PremiumSeat("F5")
                )
        );

        b1.confirm();

        Booking b2 = new Booking(
                ravi,
                show,
                Arrays.asList(new RegularSeat("A2"))
        );

        b2.confirm();

        Booking b3 = new Booking(
                ravi,
                show,
                Arrays.asList(new ReclinerSeat("R1"))
        );

        b3.confirm();

        b1.cancel();

        Booking b4 = new Booking(
                neha,
                show,
                Arrays.asList(new RegularSeat("A2"))
        );

        b4.confirm();
    }
}