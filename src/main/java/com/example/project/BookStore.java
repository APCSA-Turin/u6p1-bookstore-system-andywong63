package com.example.project;

public class BookStore {

    // requires at least 2 attributes Book[] books, User[] users (initialized to an
    // empty array of 10 max users)
    private Book[] books = new Book[0];
    private User[] users = new User[10];

    // requires 1 empty constructor
    public BookStore() {
    }

    // Getter/setter
    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] newUsers) {
        users = newUsers;
    }

    public Book[] getBooks() {
        return books;
    }

    public void addUser(User user) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                // Set first instance of null in array to the user
                users[i] = user;
                return;
            }
        }
    }

    public void removeUser(User user) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == user) {
                // Set all instances of user to null
                users[i] = null;
            }
        }
        consolidateUsers();
    }

    public void consolidateUsers() {
        int nextEmpty = 0;
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                if (i != nextEmpty) {
                    users[nextEmpty] = users[i];
                    users[i] = null;
                }
                nextEmpty++;
            }
        }
    }

    public void addBook(Book book) {
        Book[] newBooks = new Book[books.length + 1];
        for (int i = 0; i < books.length; i++) {
            newBooks[i] = books[i];
        }
        newBooks[books.length] = book;
        books = newBooks;
    }

    public void insertBook(Book book, int index) {
        Book[] newBooks = new Book[books.length + 1];
        for (int i = 0; i < index; i++) { // Insert all books before index
            newBooks[i] = books[i];
        }
        newBooks[index] = book; // Insert book from parameter
        for (int i = index; i < books.length; i++) { // Insert rest of books
            newBooks[i + 1] = books[i];
        }
        books = newBooks;
    }

    public void removeBook(Book book) {
        int arrayLength = books.length;
        for (Book currentBook : books) {
            if (currentBook == book) {
                int newQuantity = currentBook.getQuantity() - 1;
                currentBook.setQuantity(newQuantity); // Remove 1 from quantity of book
                if (newQuantity <= 0) // Remove book from array if none are left
                    arrayLength--;
            }
        }
        // Create new book arrau
        Book[] newBooks = new Book[arrayLength];
        int newBooksIndex = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i].getQuantity() > 0) {
                newBooks[newBooksIndex] = books[i];
                newBooksIndex++;
            }
        }
        books = newBooks;
    }

    public String bookStoreBookInfo() {
        // List all books in book store
        String result = "";
        for (Book book : books) {
            result += book.bookInfo() + "\n";
        }
        return result;
    } // you are not tested on this method but use it for debugging purposes

    public String bookStoreUserInfo() {
        // List all users in book store
        String result = "";
        for (User user : users) {
            if (user == null) {
                result += "null\n\n";
            } else {
                result += user.userInfo() + "\n";
            }
        }
        return result;
    } // you are not tested on this method but use it for debugging purposes
}