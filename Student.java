package Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

    public void setMatricNumber(String matricNumber) {
        this.matricNumber = matricNumber;
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
    
    public void setTimeTable(TimeTable timeTable){
        this.timeTable = timeTable;
    }

    public String getNetworkUsername() {
        return this.networkUsername;
    }

    public void setNetworkUsername (String networkUsername) {
        this.networkUsername = networkUsername;
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
        System.out.println("Network Username: " + this.networkUsername);
        System.out.println("Name: " + this.name);
        System.out.println("Gender: " + this.gender);
        System.out.println("Nationality: " + this.nationality);
        System.out.println("Matric Number: " + this.matricNumber);
        System.out.println("Start Date: " + this.startDate);
        System.out.println("End Date: " + this.endDate);
        System.out.println("Start Time: " + this.startTime);
        System.out.println("End Time: " + this.endTime);
        System.out.println("Number of AUs registered: " + this.AUsRegistered);
        System.out.println("Courses Registered: ");
        System.out.println(coursesRegistered);
        System.out.println("Courses Waiting: ");
        System.out.println(coursesWaiting);
        System.out.println("Time table: ");
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

    public boolean checkIfAlreadyApplied(String courseCode){
        Iterator<Map.Entry<String, Integer>> it = coursesRegistered.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Integer> l = it.next();
            if(l.getKey().equals(courseCode))
            {
                System.out.println("You have already registered for this course!");
                return true;
            }
        }
        it = coursesWaiting.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Integer> l = it.next();
            if(l.getKey().equals(courseCode))
            {
                System.out.println("You are already on waiting list for this course!");
                return true;
            }
        }
        return false;
    }

    public HashMap<String, Integer> getCourses() {
        HashMap<String, Integer> courses = new HashMap();
        courses.putAll(coursesRegistered);
        courses.putAll(coursesWaiting);
        return courses;
    }

    public Integer[] deregisterFromCourse(String courseCode, int AUs) {
        int status = -1, indexNum = 0;
        boolean flag = true;
        if(coursesRegistered.containsKey(courseCode)){
            indexNum = coursesRegistered.get(courseCode);
            flag = false;
            status = 1;
            coursesRegistered.remove(courseCode);
        }
        else if(coursesWaiting.containsKey(courseCode)){
            indexNum = coursesWaiting.get(courseCode);
            flag = false;
            status = 2;
            coursesWaiting.remove(courseCode);
        }
        /*HashMap<String, Integer> courses = this.coursesRegistered;
        Iterator<Map.Entry<String, Integer>> it = courses.entrySet().iterator();

        while(it.hasNext()){
            Map.Entry<String, Integer> l = it.next();
            if(l.getKey().equals(courseCode)){
                it.remove();
                this.coursesRegistered = (HashMap<String, Integer>) courses.clone();
                indexNum = l.getValue();
                flag = false;
                status = 1;
                break;
            }
        }
        courses = this.coursesWaiting;
        it = courses.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, Integer> l = it.next();
            if(l.getKey().equals(courseCode)) {
                it.remove();
                this.coursesWaiting = (HashMap<String, Integer>) courses.clone();
                indexNum = l.getValue();
                flag = false;
                status = 2;
                break;
            }
        }*/
        if(flag) {
            System.out.println("You have neither registered nor are on waiting list for this course!");
            return new Integer[]{-1, -1};
        }
        this.timeTable.removeLessons(courseCode);
        if(status == 1)
            this.AUsRegistered -= AUs;
        return new Integer[]{status, indexNum};
    }

    public void changeWaitingToRegistered(String courseCode, int AUs) {
        int indexNum = coursesWaiting.get(courseCode);
        coursesWaiting.remove(courseCode);
        coursesRegistered.put(courseCode, indexNum);
        this.AUsRegistered += AUs;
        this.timeTable.changeWaitingToRegistered(courseCode);
        /*HashMap<String, Integer> courses = this.coursesWaiting;
        Iterator<Map.Entry<String, Integer>> it = courses.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Integer> l = it.next();
            if(l.getKey().equals(courseCode)){
                it.remove();
                this.coursesWaiting = (HashMap<String, Integer>) courses.clone();
                indexNum = l.getValue();
            }
        }*/
    }
    
    public HashMap getcoursesRegistered() {
    	return coursesRegistered;
    }
}
