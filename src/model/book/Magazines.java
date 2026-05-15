package model.book;

public class Magazines extends Book {
    public Magazines(int bookID, String author, String name, double price,
                     String edition, String dateOfPurchase) {
        super(bookID, author, name, price, edition, dateOfPurchase);
    }

    @Override
    public String getCategory() { return "Magazin / Magazine"; }
}