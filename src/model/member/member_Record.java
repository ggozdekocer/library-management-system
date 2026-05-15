package model.member;

public abstract class member_Record {

    private int memberID;
    private String type;
    private String dateOfMembership;
    private int noBooksIssued;        // üyenin şu anki kitp saysı
    private final int maxBookLimit = 5;
    private String name;
    private String address;
    private String phoneNo;

    public member_Record(int memberID, String type, String dateOfMembership,
                         String name, String address, String phoneNo) {
        this.memberID = memberID;
        this.type = type;
        this.dateOfMembership = dateOfMembership;
        this.noBooksIssued = 0; // Başlangıçta kitap olmadığı için 0 yaptım.
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
    }


    public boolean incBookIssued() {
        if (noBooksIssued >= maxBookLimit) {
            System.out.println("HATA: " + name + " maksimum 5 kitap limitine ulaştı!");
            return false;
        }
        noBooksIssued++;
        return true;
    }

    public void decBookIssued() {
        if (noBooksIssued > 0) noBooksIssued--;
    }

    public void payBill(double amount) {
        System.out.println(name + " ödedi: " + amount + " TL");
    }

    public void displayInfo() {
        System.out.println("---------------------------");
        System.out.println("ID     : " + memberID);
        System.out.println("Ad     : " + name);
        System.out.println("Tür    : " + getMemberType());
        System.out.println("Kitap  : " + noBooksIssued + " / " + maxBookLimit);
        System.out.println("Telefon: " + phoneNo);
        System.out.println("---------------------------");
    }

    public abstract String getMemberType();


    public int getMemberID()       { return memberID; }
    public String getName()        { return name; }
    public int getNoBooksIssued()  { return noBooksIssued; }
    public int getMaxBookLimit()   { return maxBookLimit; }

    @Override
    public String toString() {
        return "[" + memberID + "] " + name + " (" + getMemberType() + ") "
                + noBooksIssued + "/" + maxBookLimit + " kitap";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof member_Record)) return false;
        member_Record other = (member_Record) obj;
        return this.memberID == other.memberID;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(memberID);
    }
}
