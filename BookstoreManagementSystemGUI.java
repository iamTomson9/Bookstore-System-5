// Import statements for Swing components
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BookstoreManagementSystemGUI extends JFrame {
    private Inventory inventory;

    // Constructor for the GUI class
    public BookstoreManagementSystemGUI() {
        super("Bookstore Management System");
        inventory = new Inventory();
        initialize();
    }

    // Method to initialize the GUI components
    private void initialize() {        
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(new GridLayout(5, 1));

        // Components for search functionality
        JLabel searchLabel = new JLabel("Search:");
        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Search");

        // Add action listener for the search button
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the search query from the text field
                String query = searchField.getText();
                // Perform the search based on the query
                List<Book> searchResults = searchBooks(query);
                // Display the search results
                displaySearchResults(searchResults);
            }
        });

        // Add components for search functionality to the main window
        JPanel searchPanel = new JPanel(new GridLayout(1, 3)); // Adjusted for search components
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel);

        // Buttons for different functionalities
        JButton inventoryButton = new JButton("Manage Inventory");
        JButton salesButton = new JButton("Process Sales");
        JButton revenueButton = new JButton("View Total Revenue");
        JButton topSellingBooksButton = new JButton("View Top Selling Books");
        JButton customersButton = new JButton("View Customers");
        
        // Add action listeners to buttons
         inventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showInventory();
            }
        });
        
        salesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processSales();
            }
        });

        revenueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTotalRevenue();
            }
        });

        topSellingBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTopSellingBooks();
            }
        });

        customersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCustomers();
            }
        });

        // Add buttons to the frame
        add(inventoryButton);
        add(salesButton);
        add(revenueButton);
        add(topSellingBooksButton);
        add(customersButton);
    }
    // Method to search books based on title, author, or genre
    private List<Book> searchBooks(String query) {
        List<Book> results = new ArrayList<>();
        for (Book book : inventory) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(query.toLowerCase()) ||
                    book.getGenre().toLowerCase().contains(query.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }

    // Method to display search results
    private void displaySearchResults(List<Book> searchResults) {
        if (searchResults.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No results found for the given search query.");
        } else {
            StringBuilder message = new StringBuilder("Search results:\n");
            for (Book book : searchResults) {
                message.append("Title: ").append(book.getTitle()).append(", ")
                        .append("Author: ").append(book.getAuthor()).append(", ")
                        .append("Genre: ").append(book.getGenre()).append("\n");
            }
            JOptionPane.showMessageDialog(null, message.toString());
        }
    }

    // Method to display inventory
    private void showInventory() {
        JFrame inventoryFrame = new JFrame("Inventory");
        JTextArea inventoryArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(inventoryArea);
        inventoryFrame.add(scrollPane);
        inventoryFrame.setSize(500, 300);

        // Display top selling books
        for (Book book : inventory.getTopSellingBooks(10)) {
            inventoryArea.append(book.toString() + "\n");
        }

        inventoryFrame.setVisible(true);
    }

    // Method to process sales
    private void processSales() {
        JFrame salesFrame = new JFrame("Process Sales");
        JPanel panel = new JPanel();
        JLabel isbnLabel = new JLabel("ISBN:");
        JTextField isbnField = new JTextField(10);
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField(5);
        JButton sellButton = new JButton("Sell");

        panel.add(isbnLabel);
        panel.add(isbnField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(sellButton);

        sellButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String isbn = isbnField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                Book book = findBookByISBN(isbn);
                if (book != null) {
                    inventory.processSale(book, quantity);
                    JOptionPane.showMessageDialog(null, "Sale processed successfully. Total revenue: $" + (quantity * book.getPrice()));
                } else {
                    JOptionPane.showMessageDialog(null, "Book not found in inventory.");
                }
            }
        });

        salesFrame.add(panel);
        salesFrame.setSize(300, 150);
        salesFrame.setVisible(true);
    }

    // Method to find a book by ISBN
    private Book findBookByISBN(String isbn) {
        for (Book book : inventory.getTopSellingBooks(10)) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    // Method to display total revenue
    private void showTotalRevenue() {
        JOptionPane.showMessageDialog(null, "Total Revenue: $" + inventory.calculateTotalRevenue());
    }

    // Method to display top selling books
    private void showTopSellingBooks() {
        JFrame topBooksFrame = new JFrame("Top Selling Books");
        JTextArea topBooksArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(topBooksArea);
        topBooksFrame.add(scrollPane);
        topBooksFrame.setSize(500, 300);

        // Display top selling books
        for (Book book : inventory.getTopSellingBooks(10)) {
            topBooksArea.append(book.toString() + "\n");
        }

        topBooksFrame.setVisible(true);
    }

    // Method to display customers
    private void showCustomers() {
        JFrame customersFrame = new JFrame("Customers");
        JTextArea customersArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(customersArea);
        customersFrame.add(scrollPane);
        customersFrame.setSize(500, 300);

        // Display customers and their purchase history
        for (Customer customer : inventory.getCustomers()) {
            customersArea.append(customer.toString() + "\n");
        }

        customersFrame.setVisible(true);
    }

    // Main method to run the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BookstoreManagementSystemGUI().setVisible(true);
            }
        });
    }
}
