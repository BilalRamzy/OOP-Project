import java.util.*;

// Parent class (Inheritance)
class Person {
    protected String id;
    protected String name;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void displayInfo() {   // Overridable method
        System.out.println(name + " (" + id + ")");
    }
}

// Student inherits Person
class Student extends Person {

    // Aggregation: student has borrowed books
    private List<Book> borrowed = new ArrayList<>();

    public Student(String id, String name) {
        super(id, name);
    }

    @Override
    public void displayInfo() {   // Overriding
        System.out.println("Student: " + name + " (" + id + ")");
    }

    public void borrow(Book b) { borrowed.add(b); }
}

// Abstract class (required)
abstract class Item {
    protected String title;

    public Item(String title) { this.title = title; }

    public abstract void show();
}

// Simple Author class (Composition)
class Author {
    private String name;
    public Author(String name) { this.name = name; }
    public String getName() { return name; }
}

// Book extends Item
class Book extends Item {
    private String isbn;
    private Author author;
    private boolean available = true;

    public Book(String isbn, String title, Author author) {
        super(title);
        this.isbn = isbn;
        this.author = author;
    }

    @Override
    public void show() {   // Overriding
        System.out.println(title + " | " + author.getName() + " | " + isbn);
    }

    // Overloading
    public boolean matches(String t) { return title.equalsIgnoreCase(t); }
    public boolean matches(int year) { return false; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean b) { available = b; }

    public String getTitle() { return title; }
}

// Library: Association + searching + sorting
class Library {
    private List<Book> books = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    public void addBook(Book b) { books.add(b); }
    public void addStudent(Student s) { students.add(s); }

    public Book search(String title) {
        for (Book b : books)
            if (b.matches(title)) return b;
        return null;
    }

    public void sortBooks() {
        books.sort(Comparator.comparing(Book::getTitle));
    }

    public List<Book> getBooks() { return books; }
    public List<Student> getStudents() { return students; }
}

// MAIN
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
            System.out.println("\n1. Show Books");
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
                    System.out.print("ID: ");
                    String sid = in.nextLine();
                    System.out.print("Name: ");
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
                    System.out.println("Sorted!");
                    break;

                case 6:
                    System.out.print("Student ID: ");
                    String stid = in.nextLine();

                    Student sObj = null;
                    for (Student s : lib.getStudents())
                        if (s.id.equals(stid)) sObj = s;

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
