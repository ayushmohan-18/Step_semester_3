
package main.java.object_class_method.class_problems;

import java.util.ArrayList;
import java.util.List;

interface PaymentMethod {
    boolean processPayment(double amount, String orderId);
}

class CreditCardPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount, String orderId) {
        System.out.println("Payment initiated via Credit Card for Order " + orderId);
        return true;
    }
}

class PayPalPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount, String orderId) {
        System.out.println("Payment initiated via PayPal for Order " + orderId);
        return false;
    }
}

class BankTransferPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount, String orderId) {
        System.out.println("Payment initiated via Bank Transfer for Order " + orderId);
        return true;
    }
}

class Product {
    private String productId;
    private String name;
    private double price;

    public Product(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException("Invalid product or quantity");
        }

        this.product = product;
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
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

class Order {
    private String orderId;
    private Customer customer;
    private List<OrderItem> items;
    private String status;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = "Pending";

        System.out.println("Order created for " + customer.getName() + ".");
    }

    public void addProduct(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public double calculateTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    public void makePayment(PaymentMethod paymentMethod) {
        if (items.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        if (paymentMethod == null) {
            System.out.println("Payment method is invalid.");
            return;
        }

        boolean success = paymentMethod.processPayment(
            calculateTotal(), orderId
        );

        if (success) {
            status = "Paid";
            System.out.println("Payment for Order " + orderId + " successful.");
        } else {
            System.out.println("Payment for Order " + orderId + " failed.");
        }

        System.out.println("Order status: " + status);
    }

    public String getStatus() {
        return status;
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }
}

public class shopping {
    public static void main(String[] args) {

        Customer customerX = new Customer("Customer X");

        Order orderX = new Order("X", customerX);

        Product productA = new Product("P1", "Product A", 500);
        Product productB = new Product("P2", "Product B", 300);

        orderX.addProduct(productA, 2);
        orderX.addProduct(productB, 1);

        orderX.makePayment(new CreditCardPayment());

        System.out.println();

        Customer customerY = new Customer("Customer Y");

        Order orderY = new Order("Y", customerY);

        orderY.makePayment(new CreditCardPayment());

        System.out.println();

        Customer customerZ = new Customer("Customer Z");

        Order orderZ = new Order("Z", customerZ);

        Product productC = new Product("P3", "Product C", 700);

        orderZ.addProduct(productC, 1);

        orderZ.makePayment(new PayPalPayment());
    }
}