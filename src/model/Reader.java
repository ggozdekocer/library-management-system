package model;

import model.book.Book;
import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {
    private List<Book> books;

    public Reader(String name) {
        super(name);
        this.books = new ArrayList<>();
    }

    public void purchaseBook(Book book) {
        books.add(book);
        System.out.println(getName() + " kullanıcısı '" + book.getName() + "' kitabını satın aldı.");
    }

    public void borrowBook(Book book) {
        books.add(book);
        System.out.println(getName() + " kullanıcısı '" + book.getName() + "' kitabını ödünç aldı.");
    }

    public void returnBook(Book book) {
        books.remove(book);
        System.out.println(getName() + " kullanıcısı '" + book.getName() + "' kitabını iade etti.");
    }

    public void showBooks() {
        if (books.isEmpty()) {
            System.out.println(getName() + " adlı okuyucunun kitabı bulunmuyor.");
            return;
        }
        System.out.println("=== " + getName() + " Okuyucusunun Kitapları ===");
        for (Book book : books) book.display();
    }

    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }

    @Override
    public String whoyouare() { return "Okuyucu"; }
}