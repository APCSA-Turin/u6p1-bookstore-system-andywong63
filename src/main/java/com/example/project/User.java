package com.example.project;

public class User {
    // Name
    private String name;
    // Unique user ID
    private String id;
    private Book[] books = new Book[5];

    // requires 1 contructor with two parameters that will initialize the name and
    // id
    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }

    // Getter/setter methods
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


    public void addBook(Book book) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = book;
                return;
            }
        }
    }

    public void removeBook(Book book) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == book) {
                books[i] = null;
            }
        }

        // Consolidate book list
        int nextEmpty = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null) {
                if (i != nextEmpty) {
                    books[nextEmpty] = books[i];
                    books[i] = null;
                }
                nextEmpty++;
            }
        }
    }

    // List book info for all books
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

    // List user info
    public String userInfo() {
        return "Name: " + name + "\nId: " + id + "\nBooks: \n" + bookListInfo();
    }
}