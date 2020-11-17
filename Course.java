

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Course implements Serializable {

    private String courseCode;
    private String courseTitle;
    private int numAUs;
    private String school;
    private IndexGroup[] indexList;
    private int maxLimit;
    private int vacancy;
    private int numStudentsRegistered;
    private String[] roster;
    private Lesson[] lecture;
    private int numTuts;
    private int numLabs;
    private int numLecs;
        
    private Venue vent;
    private WeekSchedule Weksc;

    /**
     * @param courseCode the unique code for the course
     * @param school the school by which the course is offered
     * @param indexList the list of index groups which the course is composed of
     * @param maxLimit the maximum number of students that can be enrolled in this course
     * @param vacancy the number of vacancies for the course
     * @param roster list of all the students that are enrolled in the course
     * @param hasLab boolean variable which indicates whether the course has labs
     * @param hasTut boolean variable which indicates whether the course has tutorials
     */

    public Course(String courseCode) {
        this.courseCode = courseCode;
    }

    public Course(String courseCode, String courseTitle, int numAUs, String school, IndexGroup[] indexList,
                  int maxLimit, int numTuts, int numLabs, int numLecs,Venue ven,WeekSchedule Weekt) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.numAUs = numAUs;
        this.school = school;
        this.indexList = indexList;
        this.maxLimit = maxLimit;
        this.roster = new String[maxLimit];
        this.numStudentsRegistered = 0;
        this.vacancy = maxLimit;
        this.numTuts = numTuts;
        this.numLabs = numLabs;
        this.numLecs = numLecs;
        
        this.vent=ven;
        this.Weksc=Weekt;
        
        
        
        lecture = new Lesson[this.numLecs];
        setLecture();
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    /**
     *
     * @param courseCode the unique code for the course
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getAUs() {
        return this.numAUs;
    }

    public void setAUs(int numAUs) {
        this.numAUs = numAUs;
    }

    public String getSchool() {
        return this.school;
    }

    /**
     *
     * @param school the school by which the course is offered
     */
    public void setSchool(String school) {
        this.school = school;
    }

    public IndexGroup[] getIndexList() {
        return this.indexList;
    }

    /**
     *
     * @param indexList the list of index groups which the course is composed of
     */
    public void setIndex(IndexGroup[] indexList) {
        this.indexList = indexList;
    }

    public int getVacancy() {
        return this.vacancy;
    }

    /**
     *
     * @param vacancy the number of vacancies for the course
     */
    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }


    public void setLecture() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        Scanner sc = new Scanner(System.in);
        LocalTime startTime;
        LocalTime endTime;
        String day;
        System.out.println("Setting of Lectures");
        for(int i = 0; i < numLecs; i++) {
            System.out.println("For lecture " + i);
            System.out.print("Enter Start time(hh:mm:ss): ");
            startTime = LocalTime.parse(sc.next(), timeFormatter);
            System.out.print("Enter End Time: ");
            endTime = LocalTime.parse(sc.next(), timeFormatter);
            System.out.print("Enter Day of the week: ");
            day = sc.next();
            lecture[i] = new Lesson(startTime, endTime, day);
        }
    }


    public int getMaxLimit() {
        return maxLimit;
    }

    /**
     *
     * @param maxLimit the maximum number of students that can be enrolled in this course
     */
    public void setMaxLimit(int maxLimit) {
        this.maxLimit = maxLimit;
    }

    public int getNumLabs() {
        return numLabs;
    }

    /**
     *
     * @param hasLab boolean variable which indicates whether the course has labs
     */
    public void setNumLabs(int numLabs) {
        this.numLabs = numLabs;
    }


    public int getNumTuts() {
        return numTuts;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    /**
     *
     * @param numTuts integer variable which indicates number of tutorials
     */
    public void setNumTuts(int numTuts) {
        this.numTuts = numTuts;
    }

    public String[] getRoster() {
        return roster;
    }

    public int getNumStudentRegistered() {
        return numStudentsRegistered;
    }

    public static Course downcast(Object object){
        return (Course)(object);
    }

    public void displayDetails(){
        System.out.printf("%-9s %20s %-9s %-9s %-9s %-15s %-15s %-15s\n", this.courseCode, this.courseTitle,
                this.numAUs, this.school, this.maxLimit, this.numTuts, this.numLabs, this.vacancy);
    }

    public boolean equals(Object c){
        return this.courseCode.equals(((Course)c).getCourseCode());
    }

    public Lesson[] getLectureLessons(){
        return lecture;
    }

    public void addStudent(String matricNum) {
        this.roster[this.numStudentsRegistered] = matricNum;
        this.numStudentsRegistered++;
        this.vacancy--;
    }

    public void removeStudent(String matricNum) {
        for(int i=0; i<numStudentsRegistered; ++i)
            if(roster[i].equals(matricNum)){
                List<String> studList = new ArrayList<>(Arrays.asList(roster));
                studList.remove(i);
                int counter = 0;
                for(String s: studList)
                    roster[counter++] = s;
                this.numStudentsRegistered--;
                this.vacancy++;
                break;
            }
    }

    
    
    public void setvenue() {
    	String vet;
    	Scanner sc = new Scanner(System.in);
    	System.out.print("Please set the Venue of the lesson: ");
    	vet=sc.next();
    	Venue vet1=new Venue(vet);
    	this.vent=vet1;
    }
    public void setweek() {
    	
    	int wet1,out=0;
    	Scanner sc = new Scanner(System.in);
    	do {
    	System.out.print("Please choose the week type 0=EVEN,1=ODD,2=BOTH ");
    	wet1=sc.nextInt();
    	switch(wet1) {
    	case 0:
    		System.out.println("EVEN weeks chosen");
    	this.Weksc=WeekSchedule.EVEN;
    	out=1;
    	break;
    	case 1:
    		System.out.println("ODD weeks chosen");
    		this.Weksc=WeekSchedule.ODD;
    		out=1;
    		break;
    	case 2: 
    		System.out.println("BOTH weeks chosen");
    		this.Weksc=WeekSchedule.BOTH;
    		out=1;
    		break;
    	default:
    		System.out.println("Error detected try again");
    	break;
    	}
    	}while(out!=1);
    }
    
    
    public void displayEveryDetail() {
        
        System.out.println("Course Code: " + this.courseCode);
        System.out.println("Course Title: " + this.courseTitle);      
        System.out.println("Venue location: " + this.vent);
        System.out.println("Week type: " + this.Weksc);
        System.out.println("Number of AUs: " + this.numAUs);
        System.out.println("School: " + this.school);
        System.out.println("Max. Limit:" + this.maxLimit);
        System.out.println("No. of Tuts: " + this.numTuts);
        System.out.println("No. of Labs: " + this.numLabs);
        System.out.println("Vacancy: " + this.vacancy);
        System.out.println("No. of registered students: " + this.numStudentsRegistered);
        System.out.println("Registered Students:");
        for(int i=0; i<numStudentsRegistered; ++i){
            System.out.println(roster[i]);
        }
        System.out.println("Index groups details: ");
        for(int i=0; i<indexList.length; ++i){
            indexList[i].printEveryDetail();
        }
        System.out.println("Lecture details: ");
        Lesson[] lectures = getLectureLessons();
        for(int i=0; i<lectures.length; ++i){
            lectures[i].displayEveryDetail();
        }
    }


}
