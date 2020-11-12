package Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class Student implements Serializable {
    private String networkUsername;
    private String name;
    private String matricNumber;
    private String emailID;
    private String nationality;
    private char gender;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private HashMap<String, Integer> coursesRegistered = new HashMap<>();
    private HashMap<String, Integer> coursesWaiting = new HashMap<>();
    private TimeTable timeTable;
    private int AUsRegistered;

    public Student(String name, String matricNumber, String emailID, String nationality, char gender, int AUsRegistered,
            String networkUsername) {
        this.name = name;
        this.matricNumber = matricNumber;
        this.emailID = emailID;
        this.nationality = nationality;
        this.gender = gender;
        this.AUsRegistered = AUsRegistered;
        this.startDate = LocalDate.of(2020, 11, 12);
        this.endDate = LocalDate.of(2020, 11, 26);
        this.startTime = LocalTime.of(10, 0, 0);
        this.endTime = LocalTime.of(22, 0, 0);
        this.timeTable = new TimeTable();
        this.networkUsername = networkUsername;
    }

    public Student(String matricNo) {
        this.matricNumber = matricNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatricNumber() {
        return matricNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getAUsRegistered() {
        return AUsRegistered;
    }

    public void setAUsRegistered(int AUsRegistered) {
        this.AUsRegistered = AUsRegistered;
    }

    public TimeTable getTimeTable(){
        return timeTable;
    }

    public String getNetworkUsername() {
        return this.networkUsername;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public static Student downcast(Object object){
        return (Student)(object);
    }

    public boolean equals(Object s){
        return (this.matricNumber.equals(((Student)s).getMatricNumber()));
    }

    public void displayEveryDetail(){
        System.out.println(this.networkUsername);
        System.out.println(this.name);
        System.out.println(this.gender);
        System.out.println(this.nationality);
        System.out.println(this.matricNumber);
        System.out.println(this.startDate);
        System.out.println(this.endDate);
        System.out.println(this.startTime);
        System.out.println(this.endTime);
        System.out.println(this.AUsRegistered);
        System.out.println("Time table:");
        this.timeTable.printTimeTable();
    }
    public void displayDetails() {
        System.out.printf("%-15s %-7s %-15s\n", this.name, this.gender, this.nationality);
    }

    public void registerForCourse(String courseCode, int indexNum, int numAUs){
        this.coursesRegistered.put(courseCode, indexNum);
        this.AUsRegistered += numAUs;
    }

    public void addCourseToWaitingList(String courseCode, int indexNum){
        this.coursesWaiting.put(courseCode, indexNum);
    }
    
    public HashMap getcoursesRegistered() {
    	return coursesRegistered;
    }
}
