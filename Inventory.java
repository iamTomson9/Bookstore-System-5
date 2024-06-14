import java.util.List;  // Import List interface
import java.util.ArrayList;  // Import ArrayList class
import java.util.Comparator; // Import Comparator class
import java.util.Iterator;

// Class representing the inventory
class Inventory implements Iterable<Book> {
    private List<Book> books;
    private List<Customer> customers;

    // Constructor for Inventory class
    public Inventory() {
        books = new ArrayList<>();
        customers = new ArrayList<>();
        initializeData(); // Initialize sample data
    }

    // Method to initialize sample data
    private void initializeData() {
        // Sample Books
        books.add(new Book("978-1408855652", "Harry Potter and the Philosopher's Stone", "J.K. Rowling", "Fantasy", 10.99, 100));
        books.add(new Book("978-0545582926", "The Hunger Games", "Suzanne Collins", "Young Adult", 8.99, 150));
        books.add(new Book("978-0747532699", "Harry Potter and the Chamber of Secrets", "J.K. Rowling", "Fantasy", 12.99, 80));
        

        // Sample Customers
        Customer customer1 = new Customer("John Doe");
        customer1.addPurchase(books.get(0)); // John Doe purchased "Harry Potter and the Philosopher's Stone"
        Customer customer2 = new Customer("Jane Smith");
        customer2.addPurchase(books.get(1)); // Jane Smith purchased "The Hunger Games"
        customer2.addPurchase(books.get(2)); // Jane Smith also purchased "Harry Potter and the Chamber of Secrets"
        customers.add(customer1);
        customers.add(customer2);
    }
    
     public List<Book> getAllBooks() {
        return books;
    }

    // Implement the Iterable interface
    @Override
    public Iterator<Book> iterator() {
        return books.iterator();
    }

    // Method to add a book to the inventory
    public void addBook(Book book) {
        books.add(book);
    }

    // Method to add a customer to the inventory
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Method to display the inventory
    public void displayInventory() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Method to search for books in the inventory based on a keyword
    public List<Book> searchBooks(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                book.getGenre().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    // Method to process a sale and update the inventory
    public void processSale(Book book, int quantitySold) {
        for (Book b : books) {
            if (b.getIsbn().equals(book.getIsbn())) {
                int remainingQuantity = b.getQuantity() - quantitySold;
                b.setQuantity(remainingQuantity);
                System.out.println("Sale processed successfully. Total revenue: $" + (quantitySold * b.getPrice()));
                return;
            }
        }
        System.out.println("Book not found in inventory.");
    }

    // Method to calculate total revenue
    public double calculateTotalRevenue() {
        double totalRevenue = 0;
        for (Book book : books) {
            totalRevenue += (book.getPrice() * (book.getQuantity()));
        }
        return totalRevenue;
    }

    // Method to get the top selling books
    public List<Book> getTopSellingBooks(int n) {
        List<Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort(Comparator.comparing(Book::getQuantity).reversed());
        return sortedBooks.subList(0, Math.min(n, sortedBooks.size()));
    }

    // Method to get the list of customers
    public List<Customer> getCustomers() {
        return customers;
    }
}
