import java.util.*;
public class PreviousCourse {

    private String memberEmail;
    private ArrayList<String> previousCourseRecord;
    public PreviousCourse(){
        this.memberEmail="";
        this.previousCourseRecord = new ArrayList<>();
    }

    public PreviousCourse(String memberEmail, ArrayList<String> previousCourseRecord){
        this.memberEmail = memberEmail;
        this.previousCourseRecord = previousCourseRecord;
    }

    public String getMemberEmail(){
        return this.memberEmail;
    }

    public ArrayList<String> getPreviousCourseRecord(){
        return this.previousCourseRecord;
    }

    public void setMemberEmail(String memberEmail){
        this.memberEmail = memberEmail;
    }

    public void setPreviousCourseRecord(ArrayList<String> previousCourseRecord){
        this.previousCourseRecord = previousCourseRecord;
    }
}
