package model.book;

public class Journals extends Book {
    public Journals(int bookID, String author, String name, double price,
                    String edition, String dateOfPurchase) {
        super(bookID, author, name, price, edition, dateOfPurchase);
    }

    @Override
    public String getCategory() { return "Dergi / Journal"; }
}