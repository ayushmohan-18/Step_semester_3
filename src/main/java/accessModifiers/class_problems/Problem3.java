package accessModifiers.class_problems;

public class Problem3 {

    public static void main(String[] args) {

        BookInventory b = new BookInventory(3);

        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut();

        System.out.println(
                "Available after checkout: " +
                b.getCopiesAvailable()
        );


        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn();

        System.out.println(
                "Available after checkin: " +
                b.getCopiesAvailable()
        );
    }
}


class BookInventory {

    private int copiesTotal;
    private int copiesAvailable;


    BookInventory(int copiesTotal) {

        if (copiesTotal <= 0) {
            throw new IllegalArgumentException(
                    "copiesTotal must be positive"
            );
        }

        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }


    void checkOut() {

        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }


    void checkIn() {

        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }


    int getCopiesAvailable() {

        return copiesAvailable;
    }
}