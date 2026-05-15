package model.member;

public class Student extends member_Record {
    public Student(int memberID, String dateOfMembership,
                   String name, String address, String phoneNo) {
        super(memberID, "Student", dateOfMembership, name, address, phoneNo);
    }

    @Override
    public String getMemberType() { return "Öğrenci"; }
}
