import java.util.*;
import java.time.Duration;
import java.time.*;

public class CourseBoundary {

    MemberBoundary memberBoundary = new MemberBoundary();

    public CourseBoundary(){

    }

    public static Course getCourseByName(String courseName){
        FileIO fileIO = new FileIO();
        ArrayList<Course> courseList = fileIO.getAllCourse();
        for(Course currentCourse : courseList){
            if (currentCourse.getName().toLowerCase().equals(courseName.toLowerCase())){
                return currentCourse;
            }
        }
        return null;
    }

    public ArrayList<Course> getAllAvaliableCourses(){
        FileIO fileIO = new FileIO();
        ArrayList<Course> allCourses = fileIO.getAllCourse();
        ArrayList<Course> allAvaliableCoursesList = new ArrayList<Course>();
        for(Course currentCourse: allCourses){
            if (currentCourse.getCapacity() > 0){
                allAvaliableCoursesList.add(currentCourse);
            }
        }
        return allAvaliableCoursesList;
    }

    public void displayAvaliableCourse(){
        ArrayList<Course> allAvaliableCoursesList = getAllAvaliableCourses();
        System.out.println(String.format("| %-20s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |", "Course Name","Instructor", "Weekday", "Time","Duration", "Occurrence", "Credit Points"));
        System.out.println("|----------------------|-----------------|-----------------|-----------------|-----------------|-----------------|-----------------|");
        for (Course currentCourse: allAvaliableCoursesList){
            displayCourse(currentCourse);
        }
    }

    public void displayFeedBack(){
        memberBoundary.displayCoursesTable();
        System.out.println("Input the course name that you want to check feedback");
        Scanner scan = new Scanner(System.in);
        String courseName = scan.nextLine().trim();
        Course course = getCourseByName(courseName);
        while (course == null)
        {
            System.out.println("Course not found. Please check your course name, or press 0 to quit");
            Scanner scanner = new Scanner(System.in);
            courseName = scanner.nextLine();
            if (courseName.equals("0"))
            {
                break;
            }
            course = getCourseByName(courseName);
        }
        String feedBack = course.getFeedback();
        if (feedBack != null)
        {
            System.out.println("Course name: "+ course.getName() + "\nCourse feedback: " + feedBack);
        }
        else
        {
            System.out.println("Not feedback for " + course.getName() + " yet");
        }
        
    }

    public void displayCourse(Course currentCourse) {
        System.out.println(String.format("| %-20s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |",
            currentCourse.getName(),
            currentCourse.getEmail(),
            currentCourse.getWeekday(),
            currentCourse.getTime(),
            currentCourse.getCourseDuration(),
            currentCourse.getOccurrence(),
            currentCourse.getCreditFee())); 
    }

    @SuppressWarnings("resource")
    public boolean courseBooking(Course bookedCourse, String memberEmail){
        System.out.println("\nYou are eligible to book a class:\n-2 hours or more before the class starts\n-There is available capacity\n");
        UserBoundary userService = new UserBoundary();
        Member member = userService.findMemberByEmail(memberEmail);
        boolean confirmation = false;
        while(!confirmation){
            if (member != null){
                
                ArrayList<String> courseNameList = member.getBookedCourse();
    
                for (String currentCourseName: courseNameList){
                    if(!currentCourseName.isEmpty()){
                        Course currentCourse = getCourseByName(currentCourseName);
                        if (currentCourse != null)
                        {
                            if (currentCourse.getTime().equals(bookedCourse.getTime()) && currentCourse.getWeekday().equals(bookedCourse.getWeekday())){
                                System.out.println("New booked class time is conflict!!!");
                                return false;
                            }
                            
                        }
                    }
                }

                LocalDateTime currenDateTime = LocalDateTime.now();
                LocalDateTime courseStartingDateTime = bookedCourse.getDateTime();
                Duration duration = Duration.between(currenDateTime, courseStartingDateTime);
                if (duration.compareTo(Duration.ofHours(2)) > 0)
                {
                    if (bookedCourse.getCapacity() > 0)
                    {
                        System.out.println("Booking confirmation\n"+ "The detail of your class: ");
                        displayCourse(bookedCourse);
                        Scanner scan = new Scanner(System.in);
                        System.out.println("Press 1 to confirm booking class "+bookedCourse.getName());
                        System.out.println("Press any other key to cancel booking.");
                        String choice = scan.nextLine().trim();
                        if (choice.equals("1")){
                            PaymentBoundary paymentBoundary = new PaymentBoundary();
                            CapacityBoundary capacityBoundary = new CapacityBoundary();
                            boolean creditDeductResult = paymentBoundary.deductCreditByCourse(memberEmail, bookedCourse.getName());
                            boolean capacityDeductResult = capacityBoundary.deductCapacityByMemberBooking(memberEmail, bookedCourse.getName());
                            if(creditDeductResult && capacityDeductResult){
                                return true;
                            }else{
                                return false;
                            }

                        }
                        else
                        {
                            System.out.println("Book Cancelled");
                            return false;
                        }
                    }
                    else
                    {
                        System.out.println("Sorry, currently there is no available capacity for " + bookedCourse.getName() + "\n");
                        return false;
                    }
                }
                else
                {
                    System.out.println("Sorry, you are not eligible to book this course now, since it will start within 2 hours");
                    return false;
                }
                
            }

        }
        return true;
        
    }

    public void courseSelect(String memberEmail){
        boolean selectOutcome = false;
        
        while (!selectOutcome){
            displayAvaliableCourse();
            UserBoundary userBoundary = new UserBoundary();
            Member member = userBoundary.findMemberByEmail(memberEmail);
            System.out.println("\nThe amount of credit you have: " +member.getCreditAmount());
            System.out.println("\nNow you can input the name of the course you want to select:");
            Scanner scan = new Scanner(System.in);
            String choice = scan.nextLine().trim();
            Course selectedCourse = getCourseByName(choice);
            if (selectedCourse != null){
                courseBooking(selectedCourse, memberEmail);
            }else{
                System.out.println("There's no such available course named "+choice+". Please check the spelling of your chosen class.");
                System.out.println("Press 1 to select class again, or press any other key to quit selecting class");
                String secChoice = scan.nextLine().trim();
                if (!secChoice.equals("1")){
                    break;
                }
            }
        }
        System.out.println("Course selection process completed.");
    }

    public void giveFeedback(String memberEmail){
        Scanner scanner = new Scanner(System.in);
        memberBoundary.displayCoursesTable();
        System.out.println("\nInput the course name that you want to give feedback to:");
        String courseName = scanner.nextLine().trim();
        CourseBoundary courseService = new CourseBoundary();
        Course course = courseService.getCourseByName(courseName);
        if (course != null){
            String rate="";
            while(true){
                System.out.println("Pls input your rate for course:"+courseName+"(input number 1-5)");
                rate = scanner.nextLine().trim();
                if (rate.equals("!") || rate.equals("2") || rate.equals("3") || rate.equals("4") || rate.equals("5")) {
                    break;
                } else {
                    System.out.println("You can only input number 1-5");
                }
            }
            String newFeedback = "";
            while(true){
                System.out.println("Now you can give your feedback to the course and your instructor: "+courseName+" (less than 100 words)");  
                newFeedback = scanner.nextLine().trim();
                if (newFeedback.length()<=100){
                    break;
                }else{
                    System.out.println("Your feedback cannot be over than 100 words.");
                }
            }
            String oldFeedback = course.getFeedback();     
            course.setFeedback(oldFeedback+" rate: "+rate+" feedback: "+newFeedback+" ");
            FileIO fileIO = new FileIO();
            fileIO.updateCourseFile(course);
            System.out.println("\nFeedback added:\n" + courseName + ": " + newFeedback);
        }else{
            System.out.println("There is no such course named "+courseName);
        }
        System.out.println("Now you can give your feedback to the course: "+courseName);    
        String newFeedback = scanner.nextLine().trim();     
        String oldFeedback = course.getFeedback();     
        course.setFeedback(oldFeedback + newFeedback + " ");
        FileIO fileIO = new FileIO();
        fileIO.updateCourseFile(course);
        System.out.println("\nFeedback added:\n" + courseName + ": " + newFeedback);
    }    
}
