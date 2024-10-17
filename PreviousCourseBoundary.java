import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PreviousCourseBoundary {

    public PreviousCourseBoundary(){

    }

    public String getPreviousCourseString(String memberEmail){
        FileIO fileIO = new FileIO();
        String previousCourseString = fileIO.readFile("dataFolder/previousCourse.txt");
        String[] memberRecordList = previousCourseString.split("\n");

        for (String recordingLine: memberRecordList){
            if (recordingLine!=null){
                String[] recordingPart = recordingLine.split(";");
                String memberRecordingEmail = recordingPart[0];
                if (memberRecordingEmail.equals(memberEmail)){
                    return recordingPart[1];
                }

            }
        }
        return null;
    }

    public void displayPreviousCourseNameList(String previousCourseString){
        String[] recordingPart = previousCourseString.split(",");
        for (String recordingData: recordingPart){
            String[] recordingDataList = recordingData.split("/");
            String datetime = recordingDataList[0].replace("[", "").replace("]", "");
            String courseName = recordingDataList[1].replace("[", "").replace("]", "");
            String attendanceStatus = recordingDataList[2].replace("[", "").replace("]", "");
            // LocalDateTime dateTime = LocalDateTime.parse(datetime);
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD HH-MM-SS");
            // String formattedDateTime = dateTime.format(formatter);
            String formattedDate = datetime.substring(0,10);
            String formattedTime = datetime.substring(11, 19);
            System.out.println("course name: "+courseName+" attend time: "+ formattedDate + " " + formattedTime +" attendance status: "+attendanceStatus);
        }
    }
    
    public void displayCourseDetail(String email)
    {
        String rawPreviousCourae = getPreviousCourseString(email);
        rawPreviousCourae = rawPreviousCourae.substring(1, rawPreviousCourae.length() - 1);
        String[] courses = rawPreviousCourae.split(",");
        List<String> previousCourseNames = new ArrayList<>();
        for (String course : courses) {
            String[] courseList = course.split("/");
            String className = courseList[1];
            previousCourseNames.add(className);
        }

        System.out.println("\nPlease input the course name you want to see details");
        Scanner scanner = new Scanner(System.in);
        String courseName = scanner.nextLine();
        while (!previousCourseNames.contains(courseName)) {
            System.out.println("\nThe course you've input is not in your previous class list, please reinput the correct name, or press 0 to quit");
            courseName = scanner.nextLine();
            if (courseName.equals("0"))
            {
                break;
            }
        }
        if (previousCourseNames.contains(courseName))
        {
            CourseBoundary courseBoundary = new CourseBoundary();
            Course course = courseBoundary.getCourseByName(courseName);
            while (course == null)
            {
                System.out.println("\nCourse not found. Please check your course name, or press 0 to quit");
                courseName = scanner.nextLine();
                if (courseName.equals("0"))
                {
                    break;
                }
                course = courseBoundary.getCourseByName(courseName);
            }
            if (course != null)
            {
                System.out.println(String.format("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |", "Course Name","Instructor", "Weekday", "Time","Duration", "Occurrence", "Credit Points"));
                System.out.println("|----------------------|----------------------|----------------------|----------------------|----------------------|----------------------|----------------------|");
                System.out.println(String.format("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |",
                    course.getName(),
                    course.getEmail(),
                    course.getWeekday(),
                    course.getTime(),
                    course.getCourseDuration(),
                    course.getOccurrence(),
                    course.getCreditFee()));
            }
        }
    }
}
