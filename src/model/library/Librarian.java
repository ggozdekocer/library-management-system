package model.library;

import model.member.member_Record;
import service.LibraryService;

public class Librarian {

    private String name;
    private String password;
    private LibraryService libraryService;

    public Librarian(String name, String password, LibraryService libraryService) {
        this.name = name;
        this.password = password;
        this.libraryService = libraryService;
    }

    public boolean verifyMember(int memberID) {
        member_Record m = libraryService.findMemberByID(memberID);
        if (m != null) { System.out.println("Üye doğrulandı: " + m.getName()); return true; }
        System.out.println("Üye bulunamadı.");
        return false;
    }

    public void issueBook(int memberID, int bookID) {
        libraryService.borrowBook(memberID, bookID);
    }

    public void returnBook(int memberID, int bookID) {
        libraryService.returnBook(memberID, bookID);
    }

    public void createBill(int memberID, double amount) {
        member_Record m = libraryService.findMemberByID(memberID);
        if (m != null) {
            System.out.println("=== FATURA ===");
            System.out.println("Üye   : " + m.getName());
            System.out.println("Tutar : " + amount + " TL");
            System.out.println("==============");
        }
    }

    public String getName() { return name; }
}