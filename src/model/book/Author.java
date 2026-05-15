package model.book;

import model.Person;
import model.book.Book;
import java.util.ArrayList;
import java.util.List;

public class Author extends Person {
    private List<Book> books;

    public Author(String name) {
        super(name);
        this.books = new ArrayList<>();
    }

    public void newBook(Book book) {
        books.add(book);
    }

    public void showBooks() {
        if (books.isEmpty()) {
            System.out.println(getName() + " adlı yazara ait kitap bulunamadı.");
            return;
        }
        System.out.println("=== " + getName() + " Yazarının Kitapları ===");
        for (Book book : books) {
            book.display();
        }
    }

    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }

    @Override
    public String whoyouare() { return "Yazar"; }
}
