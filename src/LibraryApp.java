import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    private List<Book> books = new ArrayList<Book>();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    printAllBooks();
                    break;
                case "2":
                    addNewBook();
                    break;
                case "3":
                    searchBooksByTitle();
                    break;
                case "4":
                    borrowBook();
                    break;
                case "5":
                    returnBook();
                    break;
                case "6":
                    deleteBookById();
                    break;
                case "7":
                    System.out.println("Exiting Library App. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\nWelcome to Library App!");
        System.out.println("1. Print all books");
        System.out.println("2. Add new book");
        System.out.println("3. Search books by title");
        System.out.println("4. Borrow a book");
        System.out.println("5. Return a book");
        System.out.println("6. Delete a book by id");
        System.out.println("7. Quit");
    }

    private void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private void addNewBook() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        int year;
        try {
            System.out.print("Enter year: ");
            year = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid year. Please enter a number.");
            return; // stop adding book
        }

        try {
            Book book = new Book(title, author, year);
            books.add(book);
            System.out.println("Book added: " + book);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private void searchBooksByTitle() {
        System.out.print("Enter part of the title to search: ");
        String keyword = scanner.nextLine().toLowerCase();
        boolean found = false;

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword)) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found with this title.");
        }
    }

    private void borrowBook() {
        System.out.print("Enter book id to borrow: ");
        int id = Integer.parseInt(scanner.nextLine());
        Book book = findBookById(id);

        if (book != null) {
            if (book.isAvailable()) {
                book.markAsBorrowed();
                System.out.println("Book borrowed: " + book);
            } else {
                System.out.println("Book is already borrowed.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    private void returnBook() {
        System.out.print("Enter book id to return: ");
        int id = Integer.parseInt(scanner.nextLine());
        Book book = findBookById(id);

        if (book != null) {
            if (!book.isAvailable()) {
                book.markAsReturned();
                System.out.println("Book returned: " + book);
            } else {
                System.out.println("Book was not borrowed.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    private void deleteBookById() {
        System.out.print("Enter book id to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        Book book = findBookById(id);

        if (book != null) {
            books.remove(book);
            System.out.println("Book deleted: " + book);
        } else {
            System.out.println("Book not found.");
        }
    }

    private Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}


