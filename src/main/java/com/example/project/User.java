package com.example.project;

public class User {
    // requires 3 private attributes String name, String Id, Book book that is
    // initialized to empty
    private String name;
    private String id;
    private Book[] books = new Book[5];

    // requires 1 contructor with two parameters that will initialize the name and
    // id
    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getId() {
        return id;
    }

    public void setId(String newId) {
        id = newId;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] newBooks) {
        books = newBooks;
    }

    public String bookListInfo() {
        String result = "";
        for (Book book : books) {
            if (book == null) {
                result += "empty\n";
            } else {
                result += book.bookInfo() + "\n";
            }
        }
        return result;
    }

    public String userInfo() {
        return "Name: " + name + "\nId: " + id + "\nBooks: \n" + bookListInfo();
    }
}