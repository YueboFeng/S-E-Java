import java.util.Scanner;

public class PreviousCourseController {

    private PreviousCourseBoundary previousCourseBoundary = new PreviousCourseBoundary();

    public void controlPreviousCourse(String email)
    {
        System.out.println("Here is the list of courses you have attended in the past:\n");
        String courseString = previousCourseBoundary.getPreviousCourseString(email);
        previousCourseBoundary.displayPreviousCourseNameList(courseString);
        
        System.out.println("\nPress 1 to input course name for further details, or press other keys to quit\n");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if (choice.equals("1"))
        {
            previousCourseBoundary.displayCourseDetail(email);
        }    
    }
}
