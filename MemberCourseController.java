import java.util.*;
import java.time.*;
import java.util.Scanner;

public class MemberCourseController {
    private UserBoundary userBoundary;
    private CourseBoundary courseBoundary;;

    public MemberCourseController()
    {
        this.userBoundary = new UserBoundary();
    }

    public void memberCourseFunction(String memberEmail){
        UserBoundary userService = new UserBoundary();
        Member member = userService.findMemberByEmail(memberEmail);
        Scanner scanner = new Scanner(System.in);
        System.out.println(member.getFirstName()+", this is your menu for class management:");
        boolean menuTag = true;
        while(menuTag){ // display the menu
            System.out.println("\n-Press 1 to review your upcoming courses");
            System.out.println("-Press 2 to Cancel booked course");
            System.out.println("-Press other keys to go back");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch(choice){
                case "1":
                    displayUpcomingCourses(memberEmail);
                    break;
                case "2":
                    System.out.println("\nYou are eligible to cancel course in several CONDITIONS:\n-Freely cancel 24 hours or earlier before the course start\n-50% course credit penalty applied if cancel within 24 hours before the course start\n-NOT ELIGIBLE if cancel after the course start");    
                    boolean isMemberBookedClass = displayUpcomingCourses(memberEmail);
                    if (isMemberBookedClass == true)
                    {
                        cancelBookedCourse(memberEmail);
                    }
                    else
                    {
                        System.out.println("No course booked by " + member.getFirstName() + " " + member.getLastName());
                    }
                    break;   
                default:
                    System.out.println("Returning to the previous menu.");
                    menuTag = false;
                    break;
            }
        }
    }
    // display upcoming class
    private boolean displayUpcomingCourses(String memberEmail) {
        Member member = userBoundary.findMemberByEmail(memberEmail);
        ArrayList<String> upcomingClassList = member.getBookedCourse();
        CourseBoundary courseBoundary = new CourseBoundary();
        System.out.println("\nHere is your upcoming class:");
        System.out.println(String.format("| %-20s | %-20s | %-20s | %-20s | %-20s |", "Course name", "weekday", "time", "location", "Instructor"));
        System.out.println("|----------------------|----------------------|----------------------|----------------------|----------------------|");
        if (upcomingClassList != null)
        {
            for (String currentCourseName: upcomingClassList) {
                if (currentCourseName.isEmpty())
                {
                    continue;
                }
                Course currentCourse = courseBoundary.getCourseByName(currentCourseName);
                if (currentCourse != null){
                    System.out.println(String.format("| %-20s | %-20s | %-20s | %-20s | %-20s |",
                        currentCourse.getName(),
                        currentCourse.getWeekday(),
                        currentCourse.getTime(),
                        currentCourse.getLocation(),
                        currentCourse.getInstructorName()));                    
                }
            
            }
            return true;
        }
        return false; 
    }
    



    // booking cancellation
    private void cancelBookedCourse(String memberEmail) {
        Member member = userBoundary.findMemberByEmail(memberEmail);
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the course name you want to cancel: ");
        String courseName = scan.nextLine();
        Course course = courseBoundary.getCourseByName(courseName);
        while (course == null)
        {
            System.out.println("Course not found. Please check your course name, or press 0 to quit");
            courseName = scan.nextLine();
            if (courseName.equals("0"))
            {
                break;
            }
            course = courseBoundary.getCourseByName(courseName);
        }
        while (course != null)
        {
            TimeComparingBoundary timeComparingBoundary = new TimeComparingBoundary();
            LocalDateTime currenDateTime = LocalDateTime.now();
            if (timeComparingBoundary.isValidFreelyCancel(currenDateTime, course))
            {
                member.cancelBookedCourse(courseName);  // call method to cancel the course
                PaymentBoundary paymentBoundary = new PaymentBoundary();
                paymentBoundary.addCreditByCourse(member.getEmail(), courseName);
                CapacityBoundary capacityBoundary = new CapacityBoundary();
                capacityBoundary.addCapacityByMemberCancellation(courseName);
                ArrayList<String> memberCourseList = member.getBookedCourse();
                System.out.println("Now you have class:\n");
                for (String memberCourseName: memberCourseList)
                {
                    System.out.println(memberCourseName);
                } 
                break; 
            }
            else if (timeComparingBoundary.isValidChargedCancel(currenDateTime, courseName))
            {
                System.out.println("You are now facing a 50% credit points panelty science cancelling within 24 hours, are you sure to continue?\n-Press 1 to continue\n-Press other keys to quit");
                Scanner scanner = new Scanner(System.in);
                String choice = scanner.nextLine();
                if (choice.equals("1"))
                {
                    member.cancelBookedCourse(courseName);  // call method to cancel the course
                    PaymentBoundary paymentBoundary = new PaymentBoundary();
                    paymentBoundary.addHalfCreditByCourse(member.getEmail(), courseName);
                    CapacityBoundary capacityBoundary = new CapacityBoundary();
                    capacityBoundary.addCapacityByMemberCancellation(courseName);
                    System.out.println("Now you have class:\n");
                    ArrayList<String> memberCourseList = member.getBookedCourse();
                    for (String memberCourseName: memberCourseList)
                    {
                        System.out.println(memberCourseName);
                    } 
                }
                
                break;
            }
            else
            {
                System.out.println("Sorry, you are not eliigible to cancel the class right now,\nbecause the class has started.");
                break;
            } 
        }
          
    }
}
