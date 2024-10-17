public class CreditUpdateNotification {
    private String memberID;
    private double updatedCreditAmount;

    // Constructor to initialize member ID and updated credit amount
    public CreditUpdateNotification(String memberID, double updatedCreditAmount) {
        this.memberID = memberID;
        this.updatedCreditAmount = updatedCreditAmount;
    }

    // Method to send the credit update notification to the member
    public void sendNotification() {
        // This could be an email or SMS, depending on system implementation
        System.out.println("Notification sent to Member ID: " + memberID +
                " with Updated Credit Amount: " + updatedCreditAmount);
    }

    // Getters and setters for memberID and updatedCreditAmount
    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public double getUpdatedCreditAmount() {
        return updatedCreditAmount;
    }

    public void setUpdatedCreditAmount(double updatedCreditAmount) {
        this.updatedCreditAmount = updatedCreditAmount;
    }
}
