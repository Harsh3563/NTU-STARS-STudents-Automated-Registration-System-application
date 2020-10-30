package Entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class IndexGroup implements Serializable {

    private String courseCode;
    private int indexNumber;
    private int maxLimit;
    private int vacancy;
    private Student[] studentList;
    private int numTuts;
    private int numLabs;
    private Lesson[] tutorial;
    private Lesson[] lab;
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * @param courseCode course to which this index number belongs
     * @param indexNumber unique index number of this index group
     * @param numStudents number of students in the index group
     * @param numLessons number of lessons for the index group
     */
    
    
    public IndexGroup(String courseCode, int indexNumber, int maxLimit,int numTuts, int numLabs){
        this.courseCode = courseCode;
        this.indexNumber = indexNumber;
        this.maxLimit = maxLimit;
        this.vacancy = maxLimit;
        this.studentList = new Student[maxLimit];
        tutorial = new Lesson[numTuts];
        setTutorials();
        lab = new Lesson[numLabs];
        setLabs();
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public int getIndexNumber() {
        return this.indexNumber;
    }

    /**
     *
     * @param indexNumber unique index number of this index group
     */
    
    public void setTutorials() {
    	Scanner sc = new Scanner(System.in);
    	LocalTime startTime;
        LocalTime endTime;
        String day;
    	System.out.println("Setting of Tutorials");
    	for(int i = 0; i < numTuts; i++) {
    		System.out.println("For tutorial " + i);
    		System.out.print("Enter Start time(hh:mm:ss): ");
    		startTime = LocalTime.parse(sc.next(), timeFormatter);
    		System.out.print("Enter End Time: ");
    		endTime = LocalTime.parse(sc.next(), timeFormatter);
    		System.out.print("Enter Day of the week: ");
    		day = sc.next();
    		tutorial[i] = new Lesson(startTime, endTime, day);
    	}
    }
    
    public void setLabs() {
    	Scanner sc = new Scanner(System.in);
    	LocalTime startTime;
        LocalTime endTime;
        String day;
    	System.out.println("Setting of Labs");
    	for(int i = 0; i < numLabs; i++) {
    		System.out.println("For lab " + i);
    		System.out.print("Enter Start time(hh:mm:ss): ");
    		startTime = LocalTime.parse(sc.next(), timeFormatter);
    		System.out.print("Enter End Time: ");
    		endTime = LocalTime.parse(sc.next(), timeFormatter);
    		System.out.print("Enter Day of the week: ");
    		day = sc.next();
    		lab[i] = new Lesson(startTime, endTime, day);
    	}
    }
    
    
    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    public Student[] getStudentList() {
        return studentList;
    }

    public int getmaxLimit() {
        return maxLimit;
    }
    public void setMaxLimit(int maxLimit) {
        this.maxLimit = maxLimit;
    }
    public boolean equals(IndexGroup i){
        return (this.courseCode.equals(i.getCourseCode()) && this.indexNumber == i.getIndexNumber());
    }
    public static IndexGroup downcast(Object object){
        return (IndexGroup) (object);
    }
}