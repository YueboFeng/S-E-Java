import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.time.*;

public class FileIO {
    private final String memberAccountFile = "dataFolder/memberAccount.txt";
    private final String adminAccountFile = "dataFolder/adminAccount.txt";
    private final String classFile = "dataFolder/classFile.txt";

    public FileIO() {
    }

    public ArrayList<Member> getAllMember() {
        String fileContent = readFile(memberAccountFile); // Read file contents

        // Check if fileContent is not null or empty
        if (fileContent == null || fileContent.isEmpty()) {
            System.out.println("The member account file is empty or could not be read.");
            return new ArrayList<>(); // Return an empty list if the file is empty
        }

        // Split the file content into individual member records
        String[] memberList = fileContent.split("\n");
        ArrayList<Member> allMembers = new ArrayList<>();

        // Process each member record
        for (String memberString : memberList) {
            try {
                String[] member = memberString.split(";");

                // Extract and clean up courses (remove brackets and split by comma)
                String coursesString = member[9].replace("[", "").replace("]", "").trim();
                String[] coursesArray = coursesString.split(",");
                ArrayList<String> courseList = new ArrayList<>();

                // Only add non-empty course names
                for (String course : coursesArray) {
                    course = course.trim();
                    if (!course.isEmpty()) {
                        courseList.add(course);
                    }
                }

                // Extract and clean up credit history (remove brackets and split by comma)
                String creditHistoryString = member[11].replace("[", "").replace("]", "").trim();
                String[] creditHistoryArray = creditHistoryString.split(",");
                ArrayList<String> creditHistoryList = new ArrayList<>();

                // Only add non-empty credit history entries
                for (String credit : creditHistoryArray) {
                    credit = credit.trim();
                    if (!credit.isEmpty()) {
                        creditHistoryList.add(credit);
                    }
                }

                // Create a new Member object, ensuring that we parse integers safely
                Member currentMember = new Member(
                    member[0].trim(), // Member ID
                    member[1].trim(), // Email
                    member[2].trim(), // Password
                    member[3].trim(), // First Name
                    member[4].trim(), // Last Name
                    member[5].trim(), // Date of Birth
                    member[6].trim(), // Gender
                    member[7].trim(), // Phone Number
                    member[8].trim(), // Address
                    courseList, // List of booked courses
                    Integer.parseInt(member[10].trim()), // Credit Amount
                    creditHistoryList // List of credit history
                );

                // Add the member to the list
                allMembers.add(currentMember);

            } catch (NumberFormatException e) {
                System.out.println("Error parsing credit amount for member: " + memberString);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error processing member data (incomplete fields): " + memberString);
            }
        }

        return allMembers;
    }

    public String readAdmin() {
        return readFile(adminAccountFile);
    }

    public String readFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
            return null;
        }
    }

    public void writeFile(String filePath, String content) {
        try {
            Files.write(Paths.get(filePath), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }

    public void writeCourse(Course course) {
        String courseString = course.toString() + "\n";
        try {
            Files.write(Paths.get(classFile), courseString.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error appending to file: " + classFile);
            e.printStackTrace();
        }
    }

    public ArrayList<Course> getAllCourse(){
        ArrayList<Course> allCourseList = new ArrayList<Course>();
        String courseList[] = readFile(classFile).split("\n");
            for(int i=0;i<courseList.length;i++){
                String[] course = courseList[i].split(";");
                LocalDateTime localDateTime = LocalDateTime.parse(course[14].trim());
                
                Course course2 = new Course(course[0],course[1],course[2],course[3],course[4],course[5],course[6],course[7],Integer.parseInt(course[8]),Integer.parseInt(course[9]),Integer.parseInt(course[10]),course[11],course[12],course[13], localDateTime);
                allCourseList.add(course2);
            }
        return allCourseList;
    }

    public void updateMemberFile(Member updatedMember) {
        ArrayList<Member> allMembers = getAllMember();
        ArrayList<Member> updatedMembers = new ArrayList<Member>();
        
        for (Member currentMember: allMembers) {
            if (currentMember.getEmail().equals(updatedMember.getEmail())){
                updatedMembers.add(updatedMember);
            }else{
                updatedMembers.add(currentMember);
            }
        }
        
        StringBuilder content = new StringBuilder();
        for (Member member : updatedMembers) {
            content.append(member.toString()+"\n");
        }
        
        writeFile(memberAccountFile, content.toString());
    }

    public void updateCourseFile(Course updatedCourse) {
        ArrayList<Course> allCourse = getAllCourse();
        ArrayList<Course> updatedCourses = new ArrayList<Course>();
        
        for (Course currentCourse: allCourse) {
            if (currentCourse.getEmail().equals(updatedCourse.getEmail())){
                updatedCourses.add(updatedCourse);
            }else{
                updatedCourses.add(currentCourse);
            }
        }
        
        StringBuilder content = new StringBuilder();
        for (Course course : updatedCourses) {
            content.append(course.toString()+"\n");
        }
        
        writeFile(classFile, content.toString());
    }

    public void deleteCourseFile(Course updatedCourse) {
        ArrayList<Course> allCourse = getAllCourse();
        ArrayList<Course> updatedCourses = new ArrayList<Course>();
        
        for (Course currentCourse: allCourse) {
            if (!currentCourse.getEmail().equals(updatedCourse.getEmail())){
                updatedCourses.add(currentCourse);
            }
        }
        
        StringBuilder content = new StringBuilder();
        for (Course course : updatedCourses) {
            content.append(course.toString()+"\n");
        }
        
        writeFile(classFile, content.toString());
    }
}