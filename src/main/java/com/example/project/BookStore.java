package com.example.project;

public class BookStore {

    // requires at least 2 attributes Book[] books, User[] users (initialized to an
    // empty array of 10 max users)
    private Book[] books = new Book[0];
    private User[] users = new User[10];

    // requires 1 empty constructor
    public BookStore() {
    }

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
                users[i] = user;
                return;
            }
        }
    }

    public void removeUser(User user) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == user) {
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
        for (int i = 0; i < index; i++) {
            newBooks[i] = books[i];
        }
        newBooks[index] = book;
        for (int i = index; i < books.length; i++) {
            newBooks[i + 1] = books[i];
        }
        books = newBooks;
    }

    public void removeBook(Book book) {
        int arrayLength = books.length;
        for (Book currentBook : books) {
            if (currentBook == book) {
                int newQuantity = currentBook.getQuantity() - 1;
                currentBook.setQuantity(newQuantity);
                if (newQuantity <= 0)
                    arrayLength--;
            }
        }
        Book[] newBooks = new Book[arrayLength];
        int newBooksIndex = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i].getQuantity() > 0) {
                newBooks[newBooksIndex] = books[i];
                newBooksIndex++;
            }
            ;
        }
        books = newBooks;
    }

    public String bookStoreBookInfo() {
        String result = "";
        for (Book book : books) {
            result += book.bookInfo() + "\n";
        }
        return result;
    } // you are not tested on this method but use it for debugging purposes

    public String bookStoreUserInfo() {
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