import java.util.ArrayList;
import java.util.Scanner;

public class MemberController {
    CourseBookingController courseBookingController = new CourseBookingController();
    MemberBoundary memberBoundary = new MemberBoundary();
    UserBoundary userBoundary = new UserBoundary();
    MemberCourseController memberCourseController = new MemberCourseController();
    CourseBoundary courseBoundary = new CourseBoundary();
    PreviousCourseController previousCourseController = new PreviousCourseController();
    
    
    public void controlMemberBehaviour(String memberEmail) {
        Scanner scanner = new Scanner(System.in);
        boolean result = true;

        while (result) {
            System.out.println("\n-Press 1 to view and book available classes with credit points\n-Press 2 to view profile\n-Press 3 to view Upcoming classes and cancel classes\n-Press 4 to review previous classes\n-Press 5 to view credit history\n-Press 6 to review feedbacks\n-Press 7 to give feedbacks\n-Press other keys to logout");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("\nDisplaying available classes...\n");
                    courseBookingController.courseBooking(memberEmail);
                    break;
                case "2":
                    System.out.println("\nDisplaying profile...");
                    memberBoundary.displayMemberProfile(userBoundary.findMemberByEmail(memberEmail));
                    break;
                case "3":
                    System.out.println("\nDisplaying upcoming classes...\n");
                    memberCourseController.memberCourseFunction(memberEmail);
                    break;
                case "4":
                    System.out.println("\nDisplaying previous classes...\n");
                    previousCourseController.controlPreviousCourse(memberEmail);
                    break;
                case "5":
                    System.out.println("\nDisplaying credit history...\n");
                    Member member = userBoundary.findMemberByEmail(memberEmail);
                    ArrayList<String> creditHistoryList = member.getCreditHistory();
                    if (creditHistoryList != null)
                    {
                        for (String creditHistory: creditHistoryList)
                        {
                            System.out.println(creditHistory);  
                        }
                        break;
                    }
                    else
                    {
                        System.out.println("No credit history for " + member.getFirstName() + " " + member.getLastName() + " yet");
                    }
                    break;
                case "6":
                    System.out.println("\nDisplaying course feedback...\n");
                    courseBoundary.displayFeedBack();
                    break;
                case "7":
                    System.out.println("\nPreparing feedback interface...\n");    
                    CourseBoundary courseBoundary = new CourseBoundary();
                    courseBoundary.giveFeedback(memberEmail);
                    break;
                default:
                    System.out.println("\nLogging out...");
                    result = false;
                    break;
            }
        }
    }
}
