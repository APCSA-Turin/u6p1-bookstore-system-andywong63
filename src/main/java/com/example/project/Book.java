package com.example.project;

public class Book {
    private String title;
    private String author;
    private int yearPublished;
    private String isbn;
    private int quantity;

    public Book(String title, String author, int yearPublished, String isbn, int quantity) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.isbn = isbn;
        this.quantity = quantity;
    }

    // Getter/setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String newAuthor) {
        author = newAuthor;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int newYearPublished) {
        yearPublished = newYearPublished;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String newIsbn) {
        isbn = newIsbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int newQuantity) {
        quantity = newQuantity;
    }

    public String bookInfo() {
        return "Title: " + title + ", Author: " + author + ", Year: " + yearPublished + ", ISBN: " + isbn
                + ", Quantity: " + quantity;
    }
}