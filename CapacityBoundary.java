public class CapacityBoundary {
    
    public boolean deductCapacityByMemberBooking(String memberEmail, String courseName){
        UserBoundary userBoundary = new UserBoundary();
        Member member = userBoundary.findMemberByEmail(memberEmail);
        if (member != null){
            CourseBoundary courseBoundary = new CourseBoundary();
            Course course = courseBoundary.getCourseByName(courseName);
            int courseCapacity = course.getCapacity();
            if (courseCapacity > 0){
                course.setCapacity(courseCapacity - 1);
                FileIO fileIO = new FileIO();
                fileIO.updateCourseFile(course);
                System.out.println("Booking successfully! remaining capacity: " + course.getCapacity());
            }else{
                System.out.println("Not enough course capacity, which is currently "+courseCapacity);
                return false;
            }

        }
        return false;
    }

    public boolean addCapacityByMemberCancellation(String courseName){
        
        CourseBoundary courseBoundary = new CourseBoundary();
        Course course = courseBoundary.getCourseByName(courseName);
        int courseCapacity = course.getCapacity();
        course.setCapacity(courseCapacity + 1);
        FileIO fileIO = new FileIO();
        fileIO.updateCourseFile(course);
        return true;
    }
}
