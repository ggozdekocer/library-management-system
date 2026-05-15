package model.library;

import model.book.Book;
import model.member.member_Record;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Library {
    private List<Book> books;
    private Set<member_Record> readers;

    public Library() {
        this.books = new ArrayList<>();
        this.readers = new HashSet<>();
    }

    public void newBook(Book book) {
        books.add(book);
        System.out.println("Kitap eklendi: " + book.getName());
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void lendBook(Book book) {
        book.updateStatus("borrowed");
    }

    public void takeBackBook(Book book) {
        book.updateStatus("available");
    }

    public boolean addMember(member_Record member) {
        return readers.add(member);
    }

    public void getBooksList() {
        System.out.println("=== Tüm Kitaplar ===");
        for (Book b : books) System.out.println(b);
    }

    public void getReadersList() {
        System.out.println("=== Tüm Üyeler ===");
        for (member_Record r : readers) System.out.println(r);
    }

    public List<Book> getBooks()           { return books; }
    public Set<member_Record> getReaders() { return readers; }
}