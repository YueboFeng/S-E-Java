import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDateTime;

public class AdministratorBoundary {

    private FileIO fileIO = new FileIO();
    private Scanner scanner = new Scanner(System.in);
    private CourseBoundary CourseBoundary = new CourseBoundary();

    public String acceptCourseName() {
        displayCourses();
        System.out.println("\nPlease input the course name: ");    
        return scanner.nextLine();
    }

    public String acceptMemberEmail() {
        displayMembers();
        System.out.println("\nPlease input the member email: ");    
        String email = scanner.nextLine();
        return email;
    }

    public void manageScheduleByAdmin() {
        String courseName = acceptCourseName();
        Course targetCourse = CourseBoundary.getCourseByName(courseName);
        while (targetCourse == null)
        {
            System.out.println("\nCourse not found. Please check your course name, or press other key to cancel.");
            courseName = acceptCourseName();
            targetCourse = CourseBoundary.getCourseByName(courseName);    
        }
        boolean result = true;
        while (result) {
            
            System.out.println("\n-Press 1 to edit course schedule\n-Press 2 to delete existing course schedule\n-Press other keys to cancel");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("\nPlease input the weekday (Input Monday to Sunday): ");
                    String weekday = scanner.nextLine();
                    String weekdays[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
                    while (!Arrays.asList(weekdays).contains(weekday))
                    {
                        System.out.println("\nInvalid weekday name. Please input complete name of weekdays like Monday");
                        weekday = scanner.nextLine();
                    }
                    targetCourse.setWeekday(weekday);
                    System.out.println("\nPlease input the time as the format\"00:00\": ");
                    String time = scanner.nextLine();
                    String timePattern = "^([01]?\\d|2[0-3]):([0-5]\\d)$";
                    while (!time.matches(timePattern))
                    {
                        System.out.println("\nInvalid time format. Please input time as the format \"00:00\": ");
                        time = scanner.nextLine();
                    }
                    targetCourse.setTime(time);
                    System.out.println("\nSchedule updated successfully:\n" + targetCourse.getName() + ": on " + targetCourse.getWeekday() + " at " + targetCourse.getTime());
                    fileIO.updateCourseFile(targetCourse);
                    result = false;
                    break;
                case "2":
                    System.out.println("\nPress 1 to confirm the deletion of the schedule");
                    if (scanner.nextLine().equals("1")) {
                        targetCourse.setWeekday(null);
                        targetCourse.setTime(null);
                        System.out.println("\nSchedule deleted successfully:\n"  + targetCourse.getName() + ": on " + targetCourse.getWeekday() + " at " + targetCourse.getTime());
                    } else {
                        System.out.println("\nDeletion cancelled.");
                    }
                    fileIO.updateCourseFile(targetCourse);
                    result = false;
                    break;
                default:
                    System.out.println("\nOperation cancelled.");
                    result = false;
                    break;
            }
        }
    }

    public void manageCapacityByAdmin() {
        String courseName = acceptCourseName();
        CourseBoundary courseBoundary = new CourseBoundary();
        Course targetCourse = courseBoundary.getCourseByName(courseName);
        while (targetCourse == null)
        {
            System.out.println("\nCourse not found. Please check your course name, or press other key to cancel.");
            courseName = acceptCourseName();
            targetCourse = courseBoundary.getCourseByName(courseName); 
        }
        boolean result = true;
        while (result) {
            System.out.println("\nPlease enter the new capacity level (Input number 1-50):");
            int capacity = -1;
            while (capacity < 1 || capacity > 50) {
                if (scanner.hasNextInt()) {
                    capacity = scanner.nextInt();
                    if (capacity < 1 || capacity > 50) {
                        System.out.println("\nInvalid capacity. Please enter a number between 1 and 50.");
                    }
                } else {
                    System.out.println("\nInvalid input. Please enter a number.");
                    scanner.next();
                }
            }
            targetCourse.setCapacity(capacity);
            System.out.println("\nCapacity updated successfully:\n" + targetCourse.getName() + ": Current capacity: " + targetCourse.getCapacity());
            fileIO.updateCourseFile(targetCourse);
            result = false;
        }
    }

    public void addCreditByAdmin() {
        String memberEmail = acceptMemberEmail();
        UserBoundary userBoundary = new UserBoundary();
        Member targetMember = userBoundary.findMemberByEmail(memberEmail);
        while (targetMember == null)
        {
            System.out.println("\nMember not found. Please check your member email, or press 0 to quit");
            memberEmail = acceptMemberEmail();
            if (memberEmail.equals("0"))
            {
                break;
            }
            targetMember = userBoundary.findMemberByEmail(memberEmail);
        }
        if (targetMember != null)
        {
            System.out.println("\nPlease input credit amount to add (number 0-1000): ");        
            int addingCredit = -1;
            while (addingCredit < 0 || addingCredit > 1000) {
                if (scanner.hasNextInt()) {
                    addingCredit = scanner.nextInt();
                    scanner.nextLine();
                    if (addingCredit < 0 || addingCredit > 1000) {
                        System.out.println("\nInvalid credit amount. Please enter a number between 0 and 1000");
                    }
                    else
                    {
                        targetMember.setCreditAmount(targetMember.getCreditAmount() + addingCredit);
                        System.out.println("\nCredit points are successfully added:\n" + "Member email: "+ targetMember.getEmail() + "\nCurrent credit amount: " + targetMember.getCreditAmount());
                        targetMember.addCreditHistory("Added " + addingCredit);
                        fileIO.updateMemberFile(targetMember);
                        displayMembers();
                    }
                } else {
                    System.out.println("\nInvalid input. Please enter a number.");
                    scanner.next();
                }
            }
        }    
    }
    
    public void cancelClass() {
        System.out.println("You are eligible to cancel class ONLY 1 week earlier than scheduled class start time");
        String courseName = acceptCourseName();
        CourseBoundary courseBoundary = new CourseBoundary();
        Course targetCourse = courseBoundary.getCourseByName(courseName);
        while (targetCourse == null)
        {
            System.out.println("\nCourse not found. Please check your course name, or press 0 to cancel.");
            courseName = acceptCourseName();
            if (courseName.equals("0"))
            {
                break;
            }
            targetCourse = courseBoundary.getCourseByName(courseName);
        }
        if (targetCourse != null)
        {
            LocalDateTime currenDateTime = LocalDateTime.now();
            TimeComparingBoundary timeComparingBoundary = new TimeComparingBoundary();
            boolean isValid = timeComparingBoundary.isValidCancelByAdmin(currenDateTime, courseName);
            if (isValid = true)
            {
                boolean result = true;
                while (result) {
                    System.out.println("\nPress 1 to ensure cancellation, or other keys to cancel the option: ");
                    if (scanner.nextLine().equals("1")) {
                        System.out.println("\nClass cancelled successfully.\nDisplay current course list:\n");
                        fileIO.deleteCourseFile(targetCourse);
                        displayCourses();
                    } else {
                        System.out.println("\nOption cancelled.");
                    }
                    result = false;
                }
            }
            else
            {
                System.out.println("Sorry, you are not eligible to cancel the course now. because the course start is within 1 week");
            }
        }
        
    }

    private void displayCourses() {
        CourseBoundary courseBoundary = new CourseBoundary();
        ArrayList<Course> ClassList = courseBoundary.getAllAvaliableCourses();
        System.out.println("\nHere is all available classes:\n");
        System.out.println(String.format("| %-20s | %-20s | %-20s | %-20s |", "Course name", "weekday", "time", "capacity"));
        System.out.println("|----------------------|----------------------|----------------------|----------------------|");
        for (Course currentCourse: ClassList) {
            System.out.println(String.format("| %-20s | %-20s | %-20s | %-20s |",
                currentCourse.getName(),
                currentCourse.getWeekday(),
                currentCourse.getTime(),
                currentCourse.getCapacity()));
        }
    }

    private void displayMembers() {
        FileIO fileIO = new FileIO();
        ArrayList<Member> MemberList = fileIO.getAllMember();
        System.out.println("\nHere is all available Members:\n");
        System.out.println(String.format("| %-20s | %-20s | %-30s | %-20s |", "First Name", "Last Name", "Email", "Credit Amount"));
        System.out.println("|----------------------|----------------------|--------------------------------|----------------------|");
        for (Member member: MemberList) {
            System.out.println(String.format("| %-20s | %-20s | %-30s | %-20s |",
                member.getFirstName(),
                member.getLastName(),
                member.getEmail(),
                member.getCreditAmount()));
        }
    }
}
