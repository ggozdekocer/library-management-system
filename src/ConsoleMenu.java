import model.book.*;
import model.member.*;
import service.LibraryService;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    private LibraryService service;
    private Scanner scanner;

    public ConsoleMenu(LibraryService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║    📚 GÖZDE KÜTÜPHANESİ          ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.println("Hoşgeldiniz...");

        boolean running = true;
        while (running) {
            System.out.println("\n=== ANA MENÜ ===");
            System.out.println("1. Kitap İşlemleri");
            System.out.println("2. Üye İşlemleri");
            System.out.println("3. Ödünç / İade");
            System.out.println("0. Sistemden Çıkış");

            int secim = readInt("Seçiminiz: ");
            switch (secim) {
                case 1 -> kitapMenu();
                case 2 -> uyeMenu();
                case 3 -> oduncMenu();
                case 0 -> { running = false; System.out.println("Çıkılıyor. Görüşmek Üzere..."); }
                default -> System.out.println("Geçersiz seçim. Lütfen Tekrar tuşlayın .");
            }
        }
        scanner.close();
    }

    private void kitapMenu() {
        boolean geri = false;
        while (!geri) {
            System.out.println("\n--- KİTAP İŞLEMLERİ ---");
            System.out.println("1. Kitap Ekle");
            System.out.println("2. Kitap Sil");
            System.out.println("3. Kitap Güncelle");
            System.out.println("4. Kitap Ara");
            System.out.println("5. Kategoriye Göre Listele");
            System.out.println("6. Yazara Göre Listele");
            System.out.println("7. Tüm Kitapları Listele");
            System.out.println("0. Geri");

            switch (readInt("Seçiminiz: ")) {
                case 1 -> kitapEkle();
                case 2 -> { int id = readInt("Silinecek ID: "); service.removeBook(id); }
                case 3 -> kitapGuncelle();
                case 4 -> kitapAra();
                case 5 -> kategoriyeGore();
                case 6 -> yazaraGore();
                case 7 -> service.listAllBooks();
                case 0 -> geri = true;
                default -> System.out.println("Geçersiz.");
            }
        }
    }

    private void kitapEkle() {
        System.out.println("Tür: 1=Genel  2=Dergi  3=Ders Kitabı  4=Magazin");
        int tur      = readInt("Tür: ");
        int id       = readInt("ID: ");
        String yazar = readString("Yazar: ");
        String isim  = readString("İsim: ");
        double fiyat = readDouble("Fiyat: ");
        String baski = readString("Baskı: ");
        String tarih = readString("Tarih (GG/AA/YYYY): ");

        Book kitap = switch (tur) {
            case 2 -> new Journals(id, yazar, isim, fiyat, baski, tarih);
            case 3 -> new StudyBooks(id, yazar, isim, fiyat, baski, tarih);
            case 4 -> new Magazines(id, yazar, isim, fiyat, baski, tarih);
            default -> new Book(id, yazar, isim, fiyat, baski, tarih);
        };
        service.addBook(kitap);
    }

    private void kitapGuncelle() {
        int id       = readInt("Güncellenecek ID: ");
        String isim  = readString("Yeni İsim (boş=değiştirme): ");
        String yazar = readString("Yeni Yazar (boş=değiştirme): ");
        double fiyat = readDouble("Yeni Fiyat (0=değiştirme): ");
        String baski = readString("Yeni Baskı (boş=değiştirme): ");
        service.updateBook(id, isim, yazar, fiyat, baski);
    }

    private void kitapAra() {
        System.out.println("Arama türü:");
        System.out.println("1. ID ile ara");
        System.out.println("2. İsim ile ara");
        System.out.println("3. Yazar ile ara");

        int secim = readInt("Seçiminiz: ");
        Book b = null;

        switch (secim) {
            case 1 -> {
                int id = readInt("Kitap ID: ");
                b = service.findBookByID(id);
            }
            case 2 -> {
                String isim = readString("Kitap İsmi: ");
                b = service.findBookByName(isim);
            }
            case 3 -> {
                String yazar = readString("Yazar Adı: ");
                b = service.findBookByAuthor(yazar);
            }
            default -> { System.out.println("Geçersiz. Lütfen tekrar deneyiniz."); return; }
        }

        if (b != null) b.display();
        else System.out.println("Kitap bulunamadı.");
    }

    private void kategoriyeGore() {
        System.out.println("1=Genel  2=Dergi  3=Ders Kitabı  4=Magazin");
        int c = readInt("Seçim: ");
        String kat = switch (c) {
            case 2 -> "Dergi / Journal";
            case 3 -> "Ders Kitabı / StudyBook";
            case 4 -> "Magazin / Magazine";
            default -> "Genel Kitap";
        };
        List<Book> liste = service.findBooksByCategory(kat);
        System.out.println("=== " + kat + " (" + liste.size() + " adet) ===");
        if (liste.isEmpty()) System.out.println("Kayıt yok.");
        else liste.forEach(System.out::println);
    }

    private void yazaraGore() {
        String yazar = readString("Yazar adı: ");
        List<Book> liste = service.findBooksByAuthorName(yazar);
        System.out.println("=== " + yazar + " (" + liste.size() + " kitap) ===");
        if (liste.isEmpty()) System.out.println("Kayıt yok.");
        else liste.forEach(System.out::println);
    }

    private void uyeMenu() {
        boolean geri = false;
        while (!geri) {
            System.out.println("\n--- ÜYE İŞLEMLERİ ---");
            System.out.println("1. Üye Ekle");
            System.out.println("2. Tüm Üyeleri Listele");
            System.out.println("3. Üye Bilgisi");
            System.out.println("0. Geri");

            switch (readInt("Seçim: ")) {
                case 1 -> uyeEkle();
                case 2 -> service.listAllMembers();
                case 3 -> {
                    int id = readInt("Üye ID: ");
                    member_Record m = service.findMemberByID(id);
                    if (m != null) m.displayInfo();
                    else System.out.println("Üye bulunamadı.");
                }
                case 0 -> geri = true;
                default -> System.out.println("Geçersiz.");
            }
        }
    }

    private void uyeEkle() {
        System.out.println("Tür: 1=Öğrenci  2=Öğretim Üyesi");
        int tur      = readInt("Tür: ");
        int id       = readInt("Üye ID: ");
        String isim  = readString("Ad Soyad: ");
        String tarih = readString("Üyelik Tarihi: ");
        String adres = readString("Adres: ");
        String tel   = readString("Telefon: ");

        member_Record uye = (tur == 2)
                ? new Faculty(id, tarih, isim, adres, tel)
                : new Student(id, tarih, isim, adres, tel);
        service.addMember(uye);
    }


    private void oduncMenu() {
        boolean geri = false;
        while (!geri) {
            System.out.println("\n--- ÖDÜNÇ / İADE ---");
            System.out.println("1. Kitap Ödünç Ver");
            System.out.println("2. Kitap İade Al");
            System.out.println("3. Üyenin Kitaplarını Gör");
            System.out.println("0. Geri");

            switch (readInt("Seçim: ")) {
                case 1 -> {
                    int mid = readInt("Üye ID: ");
                    int bid = readInt("Kitap ID: ");
                    service.borrowBook(mid, bid);
                }
                case 2 -> {
                    int mid = readInt("Üye ID: ");
                    int bid = readInt("Kitap ID: ");
                    service.returnBook(mid, bid);
                }
                case 3 -> {
                    int mid = readInt("Üye ID: ");
                    service.showBorrowedBooks(mid);
                }
                case 0 -> geri = true;
                default -> System.out.println("Geçersiz.");
            }
        }
    }


    private int readInt(String mesaj) {
        System.out.print(mesaj);
        while (!scanner.hasNextInt()) {
            System.out.print("Sayı gir: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }

    private double readDouble(String mesaj) {
        System.out.print(mesaj);
        while (!scanner.hasNextDouble()) {
            System.out.print("Sayısal değer gir: ");
            scanner.next();
        }
        double val = scanner.nextDouble();
        scanner.nextLine();
        return val;
    }

    private String readString(String mesaj) {
        System.out.print(mesaj);
        return scanner.nextLine().trim();
    }
}