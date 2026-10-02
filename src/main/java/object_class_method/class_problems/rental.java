package main.java.object_class_method.class_problems;

abstract class RentalVehicle {
    private String vehicleId;
    private boolean available;

    public RentalVehicle(String vehicleId) {
        this.vehicleId = vehicleId;
        this.available = true;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract double calculateCharge(int days);
}

class RentalSedan extends RentalVehicle {
    public RentalSedan(String id) {
        super(id);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 100.0;
    }
}

class RentalSUV extends RentalVehicle {
    public RentalSUV(String id) {
        super(id);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 150.0;
    }
}

class RentalTruck extends RentalVehicle {
    public RentalTruck(String id) {
        super(id);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 200.0;
    }
}

class RentalCustomer {
    private String name;

    public RentalCustomer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class VehicleRental {
    private RentalVehicle vehicle;
    private RentalCustomer customer;
    private int days;
    private boolean active;

    public VehicleRental(
            RentalVehicle vehicle,
            RentalCustomer customer,
            int days) {

        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
        this.active = true;
    }

    public void returnVehicle() {
        if (active) {
            vehicle.setAvailable(true);
            active = false;

            System.out.println(
                    vehicle.getVehicleId()
                    + " returned by "
                    + customer.getName());
        } else {
            System.out.println("Vehicle is already returned.");
        }
    }

    public boolean isActive() {
        return active;
    }

    public double getTotalCharge() {
        return vehicle.calculateCharge(days);
    }
}

public class rental {

    public static void rentVehicle(
            RentalVehicle vehicle,
            RentalCustomer customer,
            int days) {

        if (days <= 0) {
            System.out.println("Invalid rental duration.");
            return;
        }

        if (!vehicle.isAvailable()) {
            System.out.println(
                    vehicle.getVehicleId()
                    + " is currently unavailable.");
            return;
        }

        vehicle.setAvailable(false);

        VehicleRental rentalRecord =
                new VehicleRental(vehicle, customer, days);

        System.out.println(
                vehicle.getVehicleId()
                + " rented successfully by "
                + customer.getName());

        System.out.println(
                "Rental charge: Rs "
                + rentalRecord.getTotalCharge());
    }

    public static void main(String[] args) {

        RentalSedan sedan = new RentalSedan("Sedan A");
        RentalSUV suv = new RentalSUV("SUV B");

        RentalCustomer c1 =
                new RentalCustomer("Customer 1");

        RentalCustomer c2 =
                new RentalCustomer("Customer 2");

        RentalCustomer c3 =
                new RentalCustomer("Customer 3");

        rentVehicle(sedan, c1, 3);

        rentVehicle(sedan, c2, 2);

        sedan.setAvailable(true);

        System.out.println("Sedan A returned by Customer 1.");

        rentVehicle(suv, c3, 5);
    }
}