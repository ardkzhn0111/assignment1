import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    private List<Book> books = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("Welcome to Library App!");
            System.out.println("1. Print all books");
            System.out.println("2. Add new book");
            System.out.println("3. Search books by title");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. Delete a book by id");
            System.out.println("7. Quit");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();
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

    private void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (Book b : books) {
                System.out.println(b);
            }
        }
    }

    private void addNewBook(){
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.print("Enter author: ");
        String author = sc.nextLine();
        System.out.print("Enter year: ");
        int year = sc.nextInt();
        sc.nextLine();
        Book b1 = new Book(title, author, year);
        books.add(b1);
        System.out.println("Book added into library");
    }

    private void searchBooksByTitle() {
        System.out.print("Enter part of the title to search: ");
        String keyword = sc.nextLine();
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().contains(keyword)) {
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
        int id = Integer.parseInt(sc.nextLine());
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
        int id = Integer.parseInt(sc.nextLine());
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
        int id = Integer.parseInt(sc.nextLine());
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