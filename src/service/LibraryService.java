package service;

import model.book.Book;
import model.library.Library;
import model.member.member_Record;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryService {

    private Library library;
    private Map<Integer, Book> bookMap;
    private Map<Integer, member_Record> memberMap;
    private Map<Integer, List<Book>> borrowedBooksMap;

    public LibraryService(Library library) {
        this.library = library;
        this.bookMap = new HashMap<>();
        this.memberMap = new HashMap<>();
        this.borrowedBooksMap = new HashMap<>();
    }


    public void addBook(Book book) {
        library.newBook(book);
        bookMap.put(book.getBookID(), book);
    }

    public boolean removeBook(int bookID) {
        Book book = bookMap.get(bookID);
        if (book == null) { System.out.println("Kitap bulunamadı."); return false; }
        if (book.getStatus().equals("borrowed")) {
            System.out.println("Bu kitap ödünç verilmiş, silinemez.");
            return false;
        }
        library.removeBook(book);
        bookMap.remove(bookID);
        System.out.println("Kitap silindi.");
        return true;
    }

    public boolean updateBook(int bookID, String newName, String newAuthor,
                              double newPrice, String newEdition) {
        Book book = bookMap.get(bookID);
        if (book == null) { System.out.println("Kitap bulunamadı."); return false; }
        if (!newName.isBlank())    book.setName(newName);
        if (!newAuthor.isBlank())  book.setAuthor(newAuthor);
        if (newPrice > 0)          book.setPrice(newPrice);
        if (!newEdition.isBlank()) book.setEdition(newEdition);
        System.out.println("Kitap güncellendi.");
        return true;
    }

    public Book findBookByID(int id) {
        return bookMap.get(id);
    }

    public Book findBookByName(String name) {
        for (Book b : library.getBooks())
            if (b.getName().equalsIgnoreCase(name)) return b;
        return null;
    }

    public Book findBookByAuthor(String author) {
        for (Book b : library.getBooks())
            if (b.getAuthor().equalsIgnoreCase(author)) return b;
        return null;
    }

    public Book findBookByAny(String query) {
        try {
            int id = Integer.parseInt(query);
            Book b = findBookByID(id);
            if (b != null) return b;
        } catch (NumberFormatException ignored) {}
        Book b = findBookByName(query);
        if (b != null) return b;
        return findBookByAuthor(query);
    }

    public List<Book> findBooksByCategory(String category) {
        List<Book> result = new ArrayList<>();
        for (Book b : library.getBooks())
            if (b.getCategory().equalsIgnoreCase(category)) result.add(b);
        return result;
    }

    public List<Book> findBooksByAuthorName(String author) {
        List<Book> result = new ArrayList<>();
        for (Book b : library.getBooks())
            if (b.getAuthor().equalsIgnoreCase(author)) result.add(b);
        return result;
    }

    public void listAllBooks() { library.getBooksList(); }



    public void addMember(member_Record member) {
        library.addMember(member);
        memberMap.put(member.getMemberID(), member);
        borrowedBooksMap.put(member.getMemberID(), new ArrayList<>());
    }

    public member_Record findMemberByID(int id) { return memberMap.get(id); }

    public void listAllMembers() { library.getReadersList(); }

    // ========== ÖDÜNÇ / İADE ==========

    public boolean borrowBook(int memberID, int bookID) {
        member_Record member = memberMap.get(memberID);
        Book book = bookMap.get(bookID);

        if (member == null) { System.out.println("Üye bulunamadı."); return false; }
        if (book == null)   { System.out.println("Kitap bulunamadı."); return false; }
        if (book.getStatus().equals("borrowed")) {
            System.out.println("Bu kitap şu an başkasında.");
            return false;
        }
        if (!member.incBookIssued()) return false;

        book.updateStatus("borrowed");
        borrowedBooksMap.get(memberID).add(book);
        library.lendBook(book);
        printBorrowBill(member, book);
        return true;
    }

    public boolean returnBook(int memberID, int bookID) {
        member_Record member = memberMap.get(memberID);
        Book book = bookMap.get(bookID);

        if (member == null) { System.out.println("Üye bulunamadı."); return false; }
        if (book == null)   { System.out.println("Kitap bulunamadı."); return false; }

        List<Book> memberBooks = borrowedBooksMap.get(memberID);
        if (!memberBooks.contains(book)) {
            System.out.println("Bu kitap bu üyede kayıtlı değil.");
            return false;
        }

        memberBooks.remove(book);
        book.updateStatus("available");
        member.decBookIssued();
        library.takeBackBook(book);
        printReturnBill(member, book);
        return true;
    }

    public void showBorrowedBooks(int memberID) {
        member_Record member = memberMap.get(memberID);
        if (member == null) { System.out.println("Üye bulunamadı."); return; }
        List<Book> list = borrowedBooksMap.get(memberID);
        System.out.println("=== " + member.getName() + " Ödünç Kitapları ===");
        if (list.isEmpty()) System.out.println("Ödünç kitap yok.");
        else for (Book b : list) System.out.println(b);
    }

    // ========== FATURA ==========

    private void printBorrowBill(member_Record member, Book book) {
        System.out.println();
        System.out.println("====== FATURA ======");
        System.out.println("İşlem : Ödünç Alma");
        System.out.println("Üye   : " + member.getName());
        System.out.println("Kitap : " + book.getName());
        System.out.println("Tutar : " + book.getPrice() + " TL");
        System.out.println("====================");
        System.out.println();
    }

    private void printReturnBill(member_Record member, Book book) {
        System.out.println();
        System.out.println("====== İADE FATURASI ======");
        System.out.println("İşlem : İade");
        System.out.println("Üye   : " + member.getName());
        System.out.println("Kitap : " + book.getName());
        System.out.println("İade  : " + book.getPrice() + " TL geri verildi.");
        System.out.println("===========================");
        System.out.println();
    }
}