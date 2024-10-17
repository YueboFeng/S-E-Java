import java.time.*;

public class TimeComparingBoundary {

    public boolean isValidFreelyCancel(LocalDateTime currentDateTime, Course course) {
        if (course == null) {
            System.out.println("Course not found.");
            return false;
        }
        String comparedDateTime = course.getDateTime().toString().trim();
        String comparedDateTimeString = comparedDateTime.trim();  
        try {
            LocalDateTime comparedDateTime1 = LocalDateTime.parse(comparedDateTimeString);
            Duration duration = Duration.between(currentDateTime, comparedDateTime1);
            if (duration.toHours() > 24) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error parsing date and time");
        }
        return false;
    }

    public boolean isValidChargedCancel(LocalDateTime currentDateTime, String courseName) {
        CourseBoundary courseBoundary = new CourseBoundary();
        Course course = courseBoundary.getCourseByName(courseName);
        if (course == null) {
            System.out.println("Course not found.");
            return false;
        }
        String comparedDateTime = course.getDateTime().toString().trim();
        String comparedDateTimeString = comparedDateTime.trim();  
        try {
            LocalDateTime comparedDateTime1 = LocalDateTime.parse(comparedDateTimeString);
            Duration duration = Duration.between(currentDateTime, comparedDateTime1);
            if (duration.toHours() < 24 && duration.toHours() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error parsing date and time");
        }
        return false;
    }

    public boolean isValidCancelByAdmin(LocalDateTime currentDateTime, String courseName)
    {
        CourseBoundary courseService = new CourseBoundary();
        Course course = courseService.getCourseByName(courseName);
        if (course == null) {
            System.out.println("Course not found.");
            return false;
        }
        String comparedDateTime = course.getDateTime().toString().trim();
        String comparedDateTimeString = comparedDateTime.trim();  
        try {
            LocalDateTime comparedDateTime1 = LocalDateTime.parse(comparedDateTimeString);
            Duration duration = Duration.between(currentDateTime, comparedDateTime1);
            if (duration.toDays() > 7) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error parsing date and time");
        }
        return false;
    }
}
