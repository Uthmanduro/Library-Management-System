package models;

public class Member {
    private int member_id;
    private String name;
    private String email;
    private String phone;

    // Declare the getter and setter methods
    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Initialize the constructor
    public Member(int member_id, String name, String email, String phone) {
        setMember_id(member_id);
        setName(name);
        setEmail(email);
        setPhone(phone);
    }

    // override the default tostring() method to display a well formatted output
    @Override
    public String toString() {
        return "{ " + getMember_id() + " " + getName() + " " + getEmail() + " " + getPhone() + " }";
    }
}
