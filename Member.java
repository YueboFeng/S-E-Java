import java.util.*;

public class Member extends User {
    private ArrayList<String> bookedCourseList;
    private int creditAmount;
    private ArrayList<String> creditHistory;

    public Member(String userId, String email, String password, String firstName, String lastName,
    String dateOfBirth, String gender, String mobileNumber, String address, ArrayList<String> CourseName, int creditAmount, ArrayList<String> creditHistory){
        super(userId, email, password, firstName, lastName, dateOfBirth, gender,mobileNumber,address);
        this.bookedCourseList = CourseName;
        this.creditAmount = creditAmount;
        this.creditHistory = creditHistory;
    }

    public ArrayList<String> getBookedCourse(){
        return this.bookedCourseList;
    }

    public void setBookedCourse(ArrayList<String> CourseName){
        this.bookedCourseList = CourseName;
    }

    public int getCreditAmount(){
        return this.creditAmount;
    }

    public void setCreditAmount(int creditAmount){
        this.creditAmount = creditAmount;
    }

    public ArrayList<String> getCreditHistory(){
        return this.creditHistory;
    }

    public void setCreditHistory(ArrayList<String> creditHistory){
        this.creditHistory = creditHistory;
    }

    public void addCreditHistory(String history)
    {
        creditHistory.add(history);
    }

    public void addBookedCourse(String courseName){
        bookedCourseList.add(courseName);
    }

    // Method to cancel booked courses
    public boolean cancelBookedCourse(String courseName)
    {
        if (bookedCourseList.contains(courseName)) {
            bookedCourseList.remove(courseName);
            System.out.println("Course '" + courseName + "' has been successfully cancelled.");
            return true;
        } else {
            System.out.println("Course '" + courseName + "' not found in your booked courses.");
            return false;
        }
    }
    // Method to view all booked classes
    public void displayBookedCourse(){
        for(String name: bookedCourseList){
            System.out.println("booked name: "+ name);
        }
    }


    // Method to get details of one single course
    
    public void viewCourseDetails(String courseName){
        
        for(String bookedCourseName: bookedCourseList){
            if (bookedCourseName.equalsIgnoreCase(courseName)){
                CourseBoundary courseBoundary = new CourseBoundary();
                Course course = courseBoundary.getCourseByName(courseName);
                if (course != null) {
                    System.out.println(course.getCourseEntry()); 
                } else {
                    System.out.println("No courses found in the name: " + courseName);
                }
                return;
            }
        }
    }
    
    // add arrayList to string function
    public String toString(){
        StringBuilder bookedCourseListString=new StringBuilder();
        bookedCourseListString.append("[");
        for (String courseName: bookedCourseList){
            bookedCourseListString.append(courseName+",");
        }
        bookedCourseListString.append("]");
        StringBuilder creditHistoryString = new StringBuilder();
        creditHistoryString.append("[");
        for (String history: creditHistory){
            creditHistoryString.append(history+",");
        }
        creditHistoryString.append("]");
        return super.toString()+bookedCourseListString.toString()+";"+ creditAmount+";"+ creditHistoryString.toString();
    }
    
}
