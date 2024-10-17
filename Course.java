import java.time.LocalDateTime;

public class Course
{
      private String name;
      private String instructorName;
      private String description;
      private String weekday;
      private String time;
      private String courseDuration;
      private String occurrence;
      private String location;
      private int capacity;
      private int currentAmount;
      private int creditFee;
      private String category;
      private String feedback;
      private String difficultyLevel;
      private LocalDateTime startingDateTime;
      
      public Course()
      {
        name = "";
        instructorName = "";
        description = "";
        weekday = "";
        time = "";
        courseDuration = "";
        occurrence = "";
        location = "";
        capacity = 0;
        currentAmount = 0;
        creditFee = 0;
        category = "";
        feedback = "";
        difficultyLevel = "";
        startingDateTime = LocalDateTime.of(2025, 1, 3, 12, 0);
      }

      public Course(String name, String instructorName, String description, String weekday, String time, String courseDuration, String occurrence,
        String location, int capacity, int currentAmount, int creditFee, String category, String feedback, String difficultyLevel, LocalDateTime startingDateTime)
      {
        this.name = name;
        this.instructorName = instructorName;
        this.description = description;
        this.weekday = weekday;
        this.time = time;
        this.courseDuration = courseDuration;
        this.occurrence = occurrence;
        this.location = location;
        this.capacity = capacity;
        this.currentAmount = currentAmount;
        this.creditFee = creditFee;
        this.category = category;
        this.feedback = feedback;
        this.difficultyLevel = difficultyLevel;
        this.startingDateTime = startingDateTime;
      }

      public String getName()
      {
        return name;
      }

      public void setName(String name)
      {
        this.name = name;
      }

      public String getEmail()
      {
        return instructorName;
      }

      public void setEmail(String instructorName)
      {
        this.instructorName = instructorName;
      }

      public String getDescription()
      {
        return description;
      }

      public void setDescription(String description)
      {
        this.description = description;
      }
      
      public String getWeekday()
      {
        return weekday;
      }

      public void setWeekday(String weekday)
      {
        this.weekday = weekday; 
      }

      public String getTime()
      {
        return this.time;
      }

      public void setTime(String time)
      {
        this.time = time;
      }

      public String getCourseDuration()
      {
        return this.courseDuration;
      }

      public void setCourseDuration(String courseDuration)
      {
        this.courseDuration = courseDuration;
      }

      // 2.1. Each class entry should include the class name, DateTime, Location and
      //Instructor Name
      public String getCourseEntry(){
          return "Course Name: " + name + "\n" +
          "Time: " + time + "\n" +
          "Location: " + location + "\n" +
          "Instructor: " + instructorName;
      }
      
      public String getOccurrence(){
        return this.occurrence;
      }

      public void setOccurrence(String occurrence){
        this.occurrence = occurrence;
      }

      public String getLocation()
      {
        return location;
      }

      public void setLocation(String location)
      {
        this.location = location;
      }

      public int getCapacity()
      {
        return capacity;
      }

      public void setCapacity(int capacity)
      {
        this.capacity = capacity;
      }

      public int getCreditFee()
      {
        return creditFee;
      }

      public void setCreditFee(int creditFee)
      {
        this.creditFee = creditFee;
      }

      public String getInstructorName()
      {
        return instructorName;
      }

      public void setInstructorName()
      {
        this.instructorName = instructorName;
      }

      public String getCategory()
      {
        return category;
      }

      public void setCategory(String category)
      {
        this.category = category;
      }

      public int getCurrentAmount(){
        return this.currentAmount;
      }

      public void setCurrentAmount(int currentAmount){
        this.currentAmount = currentAmount;
      }

      public String getFeedback(){
        return this.feedback;
      }

      public void setFeedback(String feedback){
        this.feedback = feedback;
      }

      public String getDifficultyLevel()
      {
        return difficultyLevel;
      }

      public void setDifficultyLevel(String difficultyLevel)
      {
        this.difficultyLevel = difficultyLevel;
      }

      public LocalDateTime getDateTime()
      {
        return startingDateTime;
      }

      public void setDateTime(LocalDateTime startingDateTime){
        this.startingDateTime = startingDateTime;
      }

      public String toString() {
        return 
                name +
                ";" + instructorName +
                ";" + description +
                ";" + weekday +
                ";" + time  +
                ";" + courseDuration +
                ";" + occurrence +
                ";" + location +
                ";" + capacity +
                ";" + currentAmount +
                ";" + creditFee +
                ";" + category  +
                ";" + feedback  +
                ";" + difficultyLevel +
                ";" + startingDateTime.toString();
    }
}
