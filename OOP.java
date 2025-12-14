import java.util.*;

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

        Library lib = new Library();

        // Sample data (Demo)
        lib.addBook(new Book("111", "Harry Potter", new Author("J.K. Rowling")));
        lib.addBook(new Book("222", "1984", new Author("George Orwell")));

        lib.addStudent(new Student("S1", "Bille"));
        lib.addStudent(new Student("S2", "Ali"));

        // Show all books
        System.out.println("Books in library:");
        for (Book b : lib.getBooks()) {
            b.show();
        }

        // Search example
        System.out.println("\nSearching for 1984:");
        Book found = lib.search("1984");
        if (found != null) {
            found.show();
        }

        // Sort example
        lib.sortBooks();
        System.out.println("\nAfter sorting:");
        for (Book b : lib.getBooks()) {
            b.show();
        }
    }
}


