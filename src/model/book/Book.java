package model.book;

public class Book {
    private int bookID;
    private String author;
    private String name;
    private double price;
    private String status;
    private String edition;
    private String dateOfPurchase;

    public Book(int bookID, String author, String name, double price,
                String edition, String dateOfPurchase) {
        this.bookID = bookID;
        this.author = author;
        this.name = name;
        this.price = price;
        this.status = "available";
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
    }


    public void changeOwner(String newOwner) {
        System.out.println("'" + name + "' kitabının sahibi " + newOwner + " olarak güncellendi.");
    }

    public String getOwner() {
        return status.equals("borrowed") ? "Ödünç alınmış" : "Kütüphanede";
    }

    public void display() {
        System.out.println("--------------------------------------------------");
        System.out.println("ID      : " + bookID);
        System.out.println("İsim    : " + name);
        System.out.println("Yazar   : " + author);
        System.out.println("Fiyat   : " + price + " TL");
        System.out.println("Durum   : " + status);
        System.out.println("Baskı   : " + edition);
        System.out.println("Tarih   : " + dateOfPurchase);
        System.out.println("Tür     : " + getCategory());
        System.out.println("--------------------------------------------------");
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return "Genel Kitap";
    }
    public String getTitle() { return name; }
    public String getAuthor() { return author; }

    public int getBookID() { return bookID; }
    public void setBookID(int bookID) { this.bookID = bookID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void setAuthor(String author) { this.author = author; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getStatus() { return status; }

    public String getEdition() { return edition; }
    public void setEdition(String edition) { this.edition = edition; }

    public String getDateOfPurchase() { return dateOfPurchase; }
    public void setDateOfPurchase(String d) { this.dateOfPurchase = d; }

    @Override
    public String toString() {
        return "[" + bookID + "] " + name + " - " + author + " (" + status + ")";
    }
}