public class Administrator extends User {

    
    private String adminID;

    public Administrator(String userId, String email, String password, String firstName, String lastName,
    String dateOfBirth, String gender, String mobileNumber, String address, String adminID){
        super(userId, email, password, firstName, lastName, dateOfBirth, gender,mobileNumber,address);
        this.adminID = adminID;
    }

    // adminID Getter method
    public String getAdminID() {
        return this.adminID;
    }

    // adminID Setter method
    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }
}
