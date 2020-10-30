package Entity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Course {

    private String courseCode;
    private int numAUs;
    private String school;
    private IndexGroup[] indexList;
    private int maxLimit;
    private int vacancy;
    private Student[] roster;
    private Lesson[] lecture;
    private int numTuts;
    private int numLabs;
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

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
    
    public Course(String courseCode, int numAUs, String school, IndexGroup[] indexList, int maxLimit, int numTuts, int numLabs) {
        this.courseCode = courseCode;
        this.numAUs = numAUs;
        this.school = school;
        this.indexList = indexList;
        this.maxLimit = maxLimit;
        this.vacancy = maxLimit;
        this.numTuts = numTuts;
        this.numLabs = numLabs;
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
    	Scanner sc = new Scanner(System.in);
    	LocalTime startTime;
        LocalTime endTime;
        String day;
    	System.out.println("Setting of Lectures");
    	for(int i = 0; i < numLabs; i++) {
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

    /**
     *
     * @param hasTut boolean variable which indicates whether the course has tutorials
     */
    public void setNumTuts(int numTuts) {
        this.numTuts = numTuts;
    }

    public Student[] getRoster() {
        return roster;
    }
}