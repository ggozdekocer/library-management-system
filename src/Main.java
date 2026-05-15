import model.book.*;
import model.library.Library;
import model.library.Librarian;
import model.member.*;
import service.LibraryService;

public class Main {
    public static void main(String[] args) {

        Library library = new Library();

        LibraryService service = new LibraryService(library);

        Librarian librarian = new Librarian("Admin", "1234", service);

        service.addBook(new Book(1, "Orhan Pamuk", "Kar", 85.0, "3. Baskı", "01/01/2020"));
        service.addBook(new Book(2, "Sabahattin Ali", "İçimizdeki Şeytan", 65.0, "5. Baskı", "01/03/2021"));
        service.addBook(new Book(3, "Yakup Kadri Karaosmanoglu", "Yaban", 55.0, "2. Baskı", "10/04/2019"));
        service.addBook(new Book(4, "Resat Nuri Guntekin", "Calikusu", 70.0, "7. Baskı", "05/06/2018"));
        service.addBook(new StudyBooks(5, "Herbert Schildt", "Java The Complete Reference", 250.0, "11. Baskı", "15/06/2022"));
        service.addBook(new StudyBooks(6, "Robert C. Martin", "Clean Code", 220.0, "1. Baskı", "20/08/2021"));
        service.addBook(new StudyBooks(7, "Thomas H. Cormen", "Introduction to Algorithms", 300.0, "3. Baskı", "11/11/2020"));
        service.addBook(new Journals(8, "IEEE", "Software Engineering Journal", 120.0, "Vol.12", "10/09/2023"));
        service.addBook(new Magazines(9, "National Geographic", "Space Edition", 45.0, "Temmuz 2024", "01/07/2024"));
        service.addBook(new Magazines(10, "Popular Science", "Yapay Zeka Özel Sayisi", 50.0, "Ocak 2024", "01/01/2024"));

        service.addMember(new Student(1, "01/09/2025", "Mertcan Koçer", "Ankara Çankaya", "0555-111-2233"));
        service.addMember(new Student(2, "15/09/2026", "Gözde Koçer", "Ankara Çankaya", "0544-222-3344"));
        service.addMember(new Student(3, "20/09/2024", "Bilge Kurtuluş", "Trabzon Ortahisar", "0533-333-4455"));
        service.addMember(new Faculty(4, "01/01/2023", "Prof. Dr. Ümit Celik", "Hacettepe Üniversitesi", "0224-111-0000"));
        service.addMember(new Faculty(5, "15/02/2021", "Doc. Dr. Sibel Sahin", "Bilkent Üniversitesi", "0224-222-0001"));


        System.out.println("Demo veriler yüklendi...\n");

        ConsoleMenu menu = new ConsoleMenu(service);
        menu.start();
    }
}