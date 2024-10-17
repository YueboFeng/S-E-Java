public class ClassModifyNotification {
    private String classID;
    private String modificationDetails;

    // Constructor to initialize class ID and modification details
    public ClassModifyNotification(String classID, String modificationDetails) {
        this.classID = classID;
        this.modificationDetails = modificationDetails;
    }

    // Method to send a notification about the class modification
    public void sendNotification() {
        // Implementation may vary; could be an internal system message or an email
        System.out.println("Notification sent for Class ID: " + classID +
                " with details: " + modificationDetails);
    }

    // Getters and setters for classID and modificationDetails
    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getModificationDetails() {
        return modificationDetails;
    }

    public void setModificationDetails(String modificationDetails) {
        this.modificationDetails = modificationDetails;
    }
}