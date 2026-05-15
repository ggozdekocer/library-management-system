package model.book;

public class StudyBooks extends Book {
    public StudyBooks(int bookID, String author, String name, double price,
                      String edition, String dateOfPurchase) {
        super(bookID, author, name, price, edition, dateOfPurchase);
    }

    @Override
    public String getCategory() { return "Ders Kitabı / StudyBook"; }
}