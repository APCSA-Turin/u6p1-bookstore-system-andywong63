package com.example.project;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BookStore bookStore = new BookStore();

        boolean continueLoop = true;
        while (continueLoop) {
            ANSI.clearScreen();
            System.out.println(ANSI.bold("Book Store").underline());
            System.out.println(" [1] Add new book");
            System.out.println(" [2] Restock book");
            System.out.println(" [3] List all books");
            System.out.println(" [4] Register new student");
            System.out.println(" [5] List all registered students");
            System.out.println(" [6] Check out book");
            System.out.println(" [7] Check in book");
            System.out.println(" [8] User Info");
            System.out.println();
            System.out.println(" [0] Exit");
            System.out.println(ANSI.bold("Enter a number to continue"));
            
            System.out.print("[#] ");
            String choice = scanner.nextLine();
            if (choice.equals("0")) {
                continueLoop = false;
            } else if (choice.equals("1")) {
                System.out.println(ANSI.bold("Enter the book title"));
                System.out.print("[TITLE] ");
                String title = scanner.nextLine();
                System.out.println(ANSI.bold("Enter the book author"));
                System.out.print("[AUTHOR] ");
                String author = scanner.nextLine();
                System.out.println(ANSI.bold("Enter the year the book was published"));
                System.out.print("[YEAR] ");
                int year = scanner.nextInt();
                scanner.nextLine();
                System.out.println(ANSI.bold("Enter the book ISBN"));
                System.out.print("[ISBN] ");
                String isbn = scanner.nextLine();
                System.out.println(ANSI.bold("Enter the number of this book to add"));
                System.out.print("[QTY] ");
                int quantity = scanner.nextInt();
                scanner.nextLine();
                Book book = new Book(title, author, year, isbn, quantity);
                bookStore.addBook(book);
                System.out.println(ANSI.green(title + " has been added to the book store"));
            } else if (choice.equals("2")) {
                System.out.println("Enter the ISBN of the book to restock");
                System.out.print("[ISBN] ");
                String isbn = scanner.nextLine();
                boolean bookFound = false;
                for (Book book : bookStore.getBooks()) {
                    if (!book.getIsbn().equals(isbn)) continue;
                    bookFound = true;

                    System.out.println("Enter the amount to restock");
                    System.out.print("[QTY] ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                    book.setQuantity(book.getQuantity() + quantity);
                    System.out.println(ANSI.green("Successfully restocked " + book.getTitle()));
                    System.out.println(ANSI.green("New quantity: " + book.getQuantity()));
                }
                if (!bookFound) {
                    System.out.println(ANSI.red("Error: Could not find that book"));
                }
            } else if (choice.equals("3")) {
                boolean continueBookInfoLoop = true;
                while (continueBookInfoLoop) {
                    ANSI.clearScreen();
                    System.out.println(ANSI.bold("Available Books"));
                    Book[] books = bookStore.getBooks();
                    for (int i = 0; i < books.length; i++) {
                        if (books[i] == null) continue;
                        System.out.println(" [" + (i + 1) + "] " + books[i].getTitle());
                    }
                    System.out.println();
                    System.out.println(" [0] Back");
                    System.out.println(ANSI.bold("Enter a number to view more information"));
                    System.out.print("[#] ");
                    int bookIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (bookIndex == 0) {
                        continueBookInfoLoop = false;
                    } else {
                        ANSI.clearScreen();
                        Book book = books[bookIndex - 1];
                        System.out.println(ANSI.bold(book.getTitle()).underline());
                        System.out.println(ANSI.italic("By " + book.getAuthor()));
                        System.out.println("Year Published: " + book.getYearPublished());
                        System.out.println("ISBN: " + book.getIsbn());
                        System.out.println("Quantity: " + book.getQuantity());
                        System.out.print(ANSI.italic("Press Enter to go back"));
                        scanner.nextLine();
                    }
                }
            } else if (choice.equals("4")) {
                boolean hasSpace = false;
                for (User u : bookStore.getUsers()) {
                    if (u == null) hasSpace = true;
                }
                if (hasSpace) {
                    System.out.println(ANSI.bold("Enter the student name"));
                    System.out.print("[NAME] ");
                    String name = scanner.nextLine();
                    IdGenerate.generateID();
                    User student = new User(name, IdGenerate.getCurrentId());
                    bookStore.addUser(student);
                    System.out.println(ANSI.green("User registered with ID " + IdGenerate.getCurrentId()));
                } else {
                    System.out.println(ANSI.red("Error: Max users (" + bookStore.getUsers().length + ") has been reached"));
                }
            } else if (choice.equals("5")) {
                boolean continueUserInfoLoop = true;
                while (continueUserInfoLoop) {
                    ANSI.clearScreen();
                    System.out.println(ANSI.bold("Registered Students"));
                    User[] users = bookStore.getUsers();
                    for (int i = 0; i < users.length; i++) {
                        if (users[i] == null) continue;
                        System.out.println(" [" + (i + 1) + "] " + users[i].getName());
                    }
                    System.out.println();
                    System.out.println(" [0] Back");
                    System.out.println(ANSI.bold("Enter a number to view more information"));
                    System.out.print("[#] ");
                    int userIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (userIndex == 0) {
                        continueUserInfoLoop = false;
                    } else {
                        ANSI.clearScreen();
                        User user = users[userIndex - 1];
                        System.out.println(ANSI.bold(user.getName()).underline());
                        System.out.println("User ID: " + user.getId());
                        System.out.println("Books checked out:");
                        for (Book book : user.getBooks()) {
                            if (book == null) continue;
                            System.out.println(" - " + ANSI.underline(book.getTitle()) + ANSI.italic(" by " + book.getAuthor()));
                        }
                        System.out.print(ANSI.italic("Press Enter to go back"));
                        scanner.nextLine();
                    }
                }
            } else if (choice.equals("6")) {
                System.out.println("Enter your user ID");
                System.out.print("[UID] ");
                String userId = scanner.nextLine();
                boolean userFound = false;
                for (User user : bookStore.getUsers()) {
                    if (user == null) continue; // Skip current iteration
                    if (!user.getId().equals(userId)) continue;
                    userFound = true;

                    System.out.println(ANSI.yellow("Welcome back, " + user.getName()));

                    boolean hasSpace = false;
                    for (Book b : user.getBooks()) {
                        if (b == null) hasSpace = true;
                    }
                    if (!hasSpace) {
                        System.out.println(ANSI.red("Error: You can only check out up to " + user.getBooks().length + " books at a time"));
                        break; // Stop loop
                    }

                    System.out.println("Enter the ISBN of the book you want to check out");
                    System.out.print("[ISBN] ");
                    String isbn = scanner.nextLine();
                    boolean bookFound = false;
                    for (Book book : bookStore.getBooks()) {
                        if (!book.getIsbn().equals(isbn)) continue;
                        bookFound = true;

                        System.out.println(ANSI.bold("You are checking out:"));
                        System.out.println("Title: " + book.getTitle());
                        System.out.println("Author: " + book.getAuthor());
                        System.out.println("Year Published: " + book.getYearPublished());

                        bookStore.removeBook(book);
                        user.addBook(book);
                        System.out.println(ANSI.green("Successfully checked out book!"));
                    }
                    if (!bookFound) {
                        System.out.println(ANSI.red("Error: Could not find that book"));
                    }
                }
                
                if (!userFound) {
                    System.out.println(ANSI.red("Error: Could not find that user"));
                }
            } else if (choice.equals("7")) {
                System.out.println("Enter your user ID");
                System.out.print("[UID] ");
                String userId = scanner.nextLine();
                boolean userFound = false;
                for (User user : bookStore.getUsers()) {
                    if (user == null) continue; // Skip current iteration
                    if (!user.getId().equals(userId)) continue;
                    userFound = true;

                    ANSI.clearScreen();
                    System.out.println(ANSI.yellow("Welcome back, " + user.getName()));
                    System.out.println(ANSI.bold("Books checked out"));
                    Book[] userBooks = user.getBooks();
                    for (int i = 0; i < userBooks.length; i++) {
                        if (userBooks[i] == null) continue;
                        System.out.println(" [" + (i + 1) + "] " + userBooks[i].getTitle());
                    }
                    System.out.println(ANSI.bold("Enter a number to check in"));
                    System.out.print("[#] ");
                    int bookIndex = scanner.nextInt();
                    scanner.nextLine();

                    Book addBook = userBooks[bookIndex - 1];
                    boolean bookFound = false;
                    for (Book book : bookStore.getBooks()) {
                        if (book == addBook) {
                            book.setQuantity(book.getQuantity() + 1);
                            bookFound = true;
                            break; // Exit loop
                        }
                    }
                    if (bookFound == false) {
                        // Add book to the book list
                        bookStore.addBook(addBook);
                        addBook.setQuantity(1);
                    }
                    user.removeBook(addBook);

                    System.out.println(ANSI.green("Successfully checked in book!"));
                }
                
                if (!userFound) {
                    System.out.println(ANSI.red("Error: Could not find that user"));
                }
            } else if (choice.equals("8")) {
                System.out.println("Enter your user ID");
                System.out.print("[UID] ");
                String userId = scanner.nextLine();
                boolean userFound = false;
                for (User user : bookStore.getUsers()) {
                    if (user == null) continue; // Skip current iteration
                    if (!user.getId().equals(userId)) continue;
                    userFound = true;

                    boolean continueBookInfoLoop = true;
                    while (continueBookInfoLoop) {
                        ANSI.clearScreen();
                        System.out.println(ANSI.bold("Books checked out by " + user.getName()));
                        Book[] userBooks = user.getBooks();
                        for (int i = 0; i < userBooks.length; i++) {
                            if (userBooks[i] == null) continue;
                            System.out.println(" [" + (i + 1) + "] " + userBooks[i].getTitle());
                        }
                        System.out.println();
                        System.out.println(" [0] Back");
                        System.out.println(ANSI.bold("Enter a number to view more information"));
                        int bookIndex = scanner.nextInt();
                        scanner.nextLine();
                        if (bookIndex == 0) {
                            continueBookInfoLoop = false;
                        } else {
                            ANSI.clearScreen();
                            Book book = userBooks[bookIndex - 1];
                            System.out.println(ANSI.bold(book.getTitle()).underline());
                            System.out.println(ANSI.italic("By " + book.getAuthor()));
                            System.out.println("Year Published: " + book.getYearPublished());
                            System.out.println("ISBN: " + book.getIsbn());
                            System.out.print(ANSI.italic("Press Enter to go back"));
                            scanner.nextLine();
                        }
                    }
                    
                }
                
                if (!userFound) {
                    System.out.println(ANSI.red("Error: Could not find that user"));
                }
            }

            if (!choice.equals("0") && !choice.equals("3") && !choice.equals("5") && !choice.equals("8")) {
                System.out.print(ANSI.italic("Press Enter to continue"));
                scanner.nextLine();
            }
        }

        scanner.close();
    }
}
