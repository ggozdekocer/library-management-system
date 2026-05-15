
package model.member;

public class Faculty extends member_Record {
    public Faculty(int memberID, String dateOfMembership,
                   String name, String address, String phoneNo) {
        super(memberID, "Faculty", dateOfMembership, name, address, phoneNo);
    }

    @Override
    public String getMemberType() { return "Öğretim Üyesi"; }
}