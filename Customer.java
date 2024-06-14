import java.util.List;  // Import List interface
import java.util.ArrayList;  // Import ArrayList class

// Class representing a customer
class Customer {
    private String name;
    private List<Book> purchaseHistory;

    // Constructor for Customer class
    public Customer(String name) {
        this.name = name;
        this.purchaseHistory = new ArrayList<>();
    }

    // Getter for customer name
    public String getName() {
        return name;
    }

    // Getter for customer's purchase history
    public List<Book> getPurchaseHistory() {
        return purchaseHistory;
    }

    // Method to add a purchase to the customer's history
    public void addPurchase(Book book) {
        purchaseHistory.add(book);
    }

    // Method to represent Customer as a String
    @Override
    public String toString() {
        StringBuilder history = new StringBuilder();
        for (Book book : purchaseHistory) {
            history.append(book.getTitle()).append(", ");
        }
        return "Customer: " + name + ", Purchase History: " + (purchaseHistory.isEmpty() ? "No purchases yet" : history.toString());
    }
}