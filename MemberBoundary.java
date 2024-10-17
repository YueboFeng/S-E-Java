import java.util.ArrayList;

public class MemberBoundary {
    
    public void displayMemberProfile(Member member) {
        System.out.println("\n===== Member Profile =====:\n");
        System.out.println(String.format("| %-20s | %-20s | %-30s | %-20s |", "First Name", "Last Name", "Email", "Phone Number"));
        System.out.println("|----------------------|----------------------|--------------------------------|----------------------|");
        System.out.println(String.format("| %-20s | %-20s | %-30s | %-20s |",
            member.getFirstName(),
            member.getLastName(),
            member.getEmail(),
            member.getMobileNumber()));
    }

    public void displayCoursesTable() {
        CourseBoundary courseBoundary = new CourseBoundary();
        ArrayList<Course> ClassList = courseBoundary.getAllAvaliableCourses();
        
        System.out.println("\nHere is all available classes:\n");
        System.out.println(String.format("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |", "Course Name","Instructor", "Weekday", "Time","Duration", "Occurrence", "Credit Points"));
        System.out.println("|----------------------|----------------------|----------------------|----------------------|----------------------|----------------------|----------------------|");
        for (Course currentCourse: ClassList) {
            System.out.println(String.format("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |",
                currentCourse.getName(),
                currentCourse.getEmail(),
                currentCourse.getWeekday(),
                currentCourse.getTime(),
                currentCourse.getCourseDuration(),
                currentCourse.getOccurrence(),
                currentCourse.getCreditFee()));
        }    
    }
}
