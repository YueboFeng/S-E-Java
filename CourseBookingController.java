public class CourseBookingController {
    CourseBoundary courseService = new CourseBoundary();
    public void courseBooking(String memberEmail){
        courseService.courseSelect(memberEmail);
    }
}
