
import java.util.*;

public class UserBoundary {
    public UserBoundary(){

    }

    public Member findMemberByEmail(String memberEmail){
        FileIO fileIO = new FileIO();


        ArrayList<Member> memberList = fileIO.getAllMember();
            for(Member currentMember: memberList){
                if(currentMember.getEmail().equals(memberEmail)){
                    return currentMember;
                }
            }
        return null;
    }
    // Method for a member to view all their booked classes
    public void displayBookedCourse(Member member) {
        member.displayBookedCourse();
    }

    // Method to review details of a specific booked class
    public void reviewCourseDetails(Member member, String courseName) {
        member.viewCourseDetails(courseName);
    }
}
