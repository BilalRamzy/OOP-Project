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

        Scanner in = new Scanner(System.in);
        Library lib = new Library();

        // Sample data
        lib.addBook(new Book("111", "Harry Potter", new Author("J.K. Rowling")));
        lib.addBook(new Book("222", "1984", new Author("George Orwell")));
        lib.addStudent(new Student("S1", "Bille"));
        lib.addStudent(new Student("S2", "Ali"));

        int ch;

        do {
            System.out.println("\n===== LIBRARY MENU =====");
            System.out.println("1. Show Books");
            System.out.println("2. Add Book");
            System.out.println("3. Add Student");
            System.out.println("4. Search Book");
            System.out.println("5. Sort Books");
            System.out.println("6. Borrow Book");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            ch = in.nextInt(); in.nextLine();

            switch (ch) {

                case 1:
                    for (Book b : lib.getBooks()) b.show();
                    break;

                case 2:
                    System.out.print("ISBN: ");
                    String isbn = in.nextLine();

                    System.out.print("Title: ");
                    String title = in.nextLine();

                    System.out.print("Author: ");
                    String a = in.nextLine();

                    lib.addBook(new Book(isbn, title, new Author(a)));
                    break;

                case 3:
                    System.out.print("Student ID: ");
                    String sid = in.nextLine();

                    System.out.print("Student Name: ");
                    String sn = in.nextLine();

                    lib.addStudent(new Student(sid, sn));
                    break;

                case 4:
                    System.out.print("Search title: ");
                    String t = in.nextLine();

                    Book found = lib.search(t);
                    if (found != null) found.show();
                    else System.out.println("Not found");
                    break;

                case 5:
                    lib.sortBooks();
                    System.out.println("Books sorted!");
                    break;

                case 6:
                    System.out.print("Student ID: ");
                    String stid = in.nextLine();

                    Student sObj = null;
                    for (Student s : lib.getStudents()) {
                        if (s.id.equals(stid)) sObj = s;
                    }

                    if (sObj == null) {
                        System.out.println("Student not found");
                        break;
                    }

                    System.out.print("Book title: ");
                    String bt = in.nextLine();

                    Book b = lib.search(bt);

                    if (b == null || !b.isAvailable()) {
                        System.out.println("Book not available");
                        break;
                    }

                    b.setAvailable(false);
                    sObj.borrow(b);
                    System.out.println("Borrowed!");
                    break;

                case 0:
                    System.out.println("Goodbye!");
            }

        } while (ch != 0);
    }
}

