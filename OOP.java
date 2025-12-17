import java.util.*;
import java.util.Scanner;

// ------------------------------
// Parent class (Inheritance)
// ------------------------------
class Person {
    protected String id;
    protected String name;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // This will be overridden by child classes
    public void displayInfo() {
        System.out.println(name + " (" + id + ")");
    }
}

// ------------------------------
// Student extends Person
// Has a list of borrowed books (Aggregation)
// ------------------------------
class Student extends Person {

    private List<Book> borrowed = new ArrayList<>();

    public Student(String id, String name) {
        super(id, name);
    }

    @Override
    public void displayInfo() { // Overriding
        System.out.println("Student: " + name + " (" + id + ")");
    }

    public void borrow(Book b) {
        borrowed.add(b);
    }
}

// ------------------------------
// Abstract item class (OOP requirement)
// ------------------------------
abstract class Item {
    protected String title;

    public Item(String title) {
        this.title = title;
    }

    public abstract void show(); 
}

// ------------------------------
// Author class (Composition)
// ------------------------------
class Author {
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

// ------------------------------
// Book extends Item
// ------------------------------
class Book extends Item {
    private String bookNumber;
    private Author author;
    private boolean available = true;

    public Book(String bookNumber, String title, Author author) {
        super(title);
        this.bookNumber = bookNumber;
        this.author = author;
    }

    @Override
    public void show() { // Overriding
        System.out.println(title + " | " + author.getName() + " | " + bookNumber);
    }

    // Overloading example
    public boolean matches(String t) {
        return title.toLowerCase().equals(t.toLowerCase());
    }

    public boolean matches(int year) { // not used, but required
        return false;
    }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean b) { available = b; }

    public String getTitle() { return title; }
}

// ------------------------------
// Library class
// Contains searching, sorting, lists of books & students
// ------------------------------
class Library {

    private List<Book> books = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    public void addBook(Book b) { books.add(b); }
    public void addStudent(Student s) { students.add(s); }

    // ------------------------
    // MANUAL SEARCH (linear search)
    // ------------------------
    public Book search(String title) {
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            if (b.getTitle().toLowerCase().equals(title.toLowerCase())) {
                return b;
            }
        }
        return null;
    }

    // ------------------------
    // MANUAL SORT (bubble sort)
    // Sort books alphabetically by title
    // ------------------------
    public void sortBooks() {

        for (int i = 0; i < books.size() - 1; i++) {
            for (int j = 0; j < books.size() - i - 1; j++) {

                String t1 = books.get(j).getTitle();
                String t2 = books.get(j + 1).getTitle();

                // compare titles manually
                if (t1.compareToIgnoreCase(t2) > 0) {
                    // swap
                    Book temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }
    }

    public List<Book> getBooks() { return books; }
    public List<Student> getStudents() { return students; }
}

// ------------------------------
// MAIN CLASS
// ------------------------------

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Library lib = new Library();

        // -------- Add 10 default books --------
        lib.addBook(new Book("101", "harry potter", new Author("j.k. rowling")));
        lib.addBook(new Book("102", "1984", new Author("george orwell")));
        lib.addBook(new Book("103", "clean code", new Author("robert martin")));
        lib.addBook(new Book("104", "the hobbit", new Author("j.r.r tolkien")));
        lib.addBook(new Book("105", "java basics", new Author("james gosling")));
        lib.addBook(new Book("106", "algorithms", new Author("clrs")));
        lib.addBook(new Book("107", "data structures", new Author("mark allen")));
        lib.addBook(new Book("108", "design patterns", new Author("gof")));
        lib.addBook(new Book("109", "oop concepts", new Author("booch")));
        lib.addBook(new Book("110", "operating systems", new Author("silberschatz")));

        int role;

        do {
            System.out.println("\n===== LOGIN =====");
            System.out.println("1. Guest");
            System.out.println("2. Admin");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            role = in.nextInt();
            in.nextLine(); // clear buffer

            // ================= GUEST =================
            if (role == 1) {
                int gChoice;
                do {
                    System.out.println("\n--- Guest Menu ---");
                    System.out.println("1. Show all books");
                    System.out.println("2. Search book by title");
                    System.out.println("3. Sort books by title");
                    System.out.println("0. Back");
                    System.out.print("Choose: ");

                    gChoice = in.nextInt();
                    in.nextLine();

                    switch (gChoice) {

                        case 1:
                            for (Book b : lib.getBooks()) {
                                b.show();
                            }
                            break;

                        case 2:
                            System.out.print("Enter book title: ");
                            String title = in.nextLine().toLowerCase();

                            Book found = lib.search(title);
                            if (found != null)
                                found.show();
                            else
                                System.out.println("Book not found");
                            break;

                        case 3:
                            lib.sortBooks();
                            System.out.println("Books after sorting:");

                            for (Book b : lib.getBooks()) {
                                b.show();
                            }   
                            break;

                    }

                } while (gChoice != 0);
            }

            // ================= ADMIN =================
            else if (role == 2) {

                System.out.print("Username: ");
                String username = in.nextLine().toLowerCase();

                System.out.print("Password: ");
                String password = in.nextLine().toLowerCase();

                if (!username.equals("admin") || !password.equals("admin")) {
                    System.out.println("Wrong username or password!");
                    continue; // يرجع لاختيار Guest / Admin
                }

                int aChoice;
                do {
                    System.out.println("\n--- Admin Menu ---");
                    System.out.println("1. Add book");
                    System.out.println("2. Delete book by title");
                    System.out.println("0. Back");
                    System.out.print("Choose: ");

                    aChoice = in.nextInt();
                    in.nextLine();

                    switch (aChoice) {

                        case 1:
                            System.out.print("bookNumber: ");
                            String bookNumber = in.nextLine();

                            System.out.print("Title: ");
                            String title = in.nextLine().toLowerCase();

                            System.out.print("Author: ");
                            String author = in.nextLine().toLowerCase();

                            lib.addBook(new Book(bookNumber, title, new Author(author)));
                            System.out.println("Book added!");
                            break;

                        case 2:
                            System.out.print("Enter title to delete: ");
                            String t = in.nextLine().toLowerCase();

                            Book b = lib.search(t);
                            if (b != null) {
                                lib.getBooks().remove(b);
                                System.out.println("Book deleted!");
                            } else {
                                System.out.println("Book not found");
                            }
                            break;
                    }

                } while (aChoice != 0);
            }

        } while (role != 0);

        System.out.println("Goodbye!");
    }
}


