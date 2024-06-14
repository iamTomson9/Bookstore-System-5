import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// Interface for sellable items
interface Sellable {
    double getPrice();
    String getTitle();
}

// Class representing a book
class Book implements Sellable {
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private double price;
    private int quantity;

    // Constructor for Book class
    public Book(String isbn, String title, String author, String genre, double price, int quantity) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters for Book attributes
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Method to represent Book as a String
    @Override
    public String toString() {
        return "ISBN: " + isbn + ", Title: " + title + ", Author: " + author + ", Genre: " + genre +
                ", Price: $" + price + ", Quantity: " + quantity;
    }
}
