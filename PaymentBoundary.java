import java.time.*;

public class PaymentBoundary {
    public PaymentBoundary(){

    }

    public boolean addCreditByCourse(String memberEmail, String courseName){
        // use it when canceling course
        CourseBoundary courseBoundary = new CourseBoundary();
        Course course = courseBoundary.getCourseByName(courseName);
        if (course != null){
            UserBoundary userService = new UserBoundary();
            int creditAmount = course.getCreditFee();
            Member member = userService.findMemberByEmail(memberEmail);
            member.cancelBookedCourse(courseName);
            int memberBalance = member.getCreditAmount();
            member.setCreditAmount(memberBalance+creditAmount);
            LocalDateTime addingDateTime = LocalDateTime.now();
            member.addCreditHistory("time: " + addingDateTime.toString() + " add: "+creditAmount + " for cancel course: " + courseName);
            FileIO fileIO = new FileIO();
            fileIO.updateMemberFile(member);
            return true;
        }
        return false;
    }

    public boolean addHalfCreditByCourse(String memberEmail, String courseName){
        // use it when canceling course
        CourseBoundary courseBoundary = new CourseBoundary();
        Course course = courseBoundary.getCourseByName(courseName);
        if (course != null){
            UserBoundary userService = new UserBoundary();
            int creditAmount = (int)(course.getCreditFee() * 0.5);
            Member member = userService.findMemberByEmail(memberEmail);
            member.cancelBookedCourse(courseName);
            int memberBalance = member.getCreditAmount();
            member.setCreditAmount(memberBalance+creditAmount);
            LocalDateTime addingDateTime = LocalDateTime.now();
            member.addCreditHistory("time: " + addingDateTime.toString() + " add: "+creditAmount + " for cancel course: " + courseName);
            FileIO fileIO = new FileIO();
            fileIO.updateMemberFile(member);
            return true;
        }
        return false;
    }

    public boolean deductCreditByCourse(String memberEmail, String courseName){
        // use it when booking course
        CourseBoundary courseBoundary = new CourseBoundary();
        Course course = courseBoundary.getCourseByName(courseName);
        if (course != null){
            UserBoundary userBoundary = new UserBoundary();
            int creditAmount = course.getCreditFee();
            Member member = userBoundary.findMemberByEmail(memberEmail);
            int memberBalance = member.getCreditAmount();
            if (memberBalance-creditAmount>0){
                member.setCreditAmount(memberBalance-creditAmount);
                FileIO fileIO = new FileIO();
                fileIO.updateMemberFile(member);
                member.addBookedCourse(courseName);
                System.out.println("Booking successfully! Now you have course: "+member.getBookedCourse());
                LocalDateTime addingDateTime = LocalDateTime.now();
                member.addCreditHistory("time: " + addingDateTime.toString() + " deduct: "+creditAmount + " for booking course: " + courseName);
                FileIO fileIO2 = new FileIO();
                fileIO2.updateMemberFile(member);
                return true;
            }else{
                System.out.println("Not enough credit balance! Your current credit balance is "+memberBalance);
                System.out.println("Pls email system admin: admin@monash.edu");
                return false;
            }

        }
        return false;
    }
}
