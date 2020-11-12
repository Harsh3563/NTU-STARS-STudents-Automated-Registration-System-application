package Entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IndexGroup implements Serializable {

    private String courseCode;
    private int indexNumber;
    private int maxLimit;
    private int vacancy;
    private int numStudentsRegistered;
    private int numStudentsWaiting;
    private String[] studentList;
    private String[] studentsWaiting;
    private int numTuts;
    private int numLabs;
    private Lesson[] tutorial;
    private Lesson[] lab;

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
        this.numStudentsRegistered = 0;
        this.numStudentsWaiting = 0;
        this.studentList = new String[maxLimit];
        this.studentsWaiting = new String[200];
        this.numTuts = numTuts;
        tutorial = new Lesson[this.numTuts];
        setTutorials();
        this.numLabs = numLabs;
        lab = new Lesson[this.numLabs];
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
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
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
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
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

    public String[] getStudentList() {
        return studentList;
    }

    public int getmaxLimit() {
        return maxLimit;
    }
    public void setMaxLimit(int maxLimit) {
        this.maxLimit = maxLimit;
    }
    public boolean equals(Object i){
        return (this.courseCode.equals(((IndexGroup)i).getCourseCode()) && this.indexNumber == ((IndexGroup)i).getIndexNumber());
    }
    public static IndexGroup downcast(Object object){
        return (IndexGroup) (object);
    }

    public int getVacancy() {
        return vacancy;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }



    public int getNumStudentsRegistered() {
        return numStudentsRegistered;
    }

    public void setNumStudentsRegistered(int numStudentsRegistered) {
        this.numStudentsRegistered = numStudentsRegistered;
    }

    public int getNumStudentsWaiting() {
        return numStudentsWaiting;
    }

    public void setNumStudentsWaiting(int numStudentsWaiting) {
        this.numStudentsWaiting = numStudentsWaiting;
    }
    public void addStudentToWaitingList(String matricNumber) {
        this.studentsWaiting[this.numStudentsWaiting] = matricNumber;
        this.numStudentsWaiting++;
    }

    public void addStudent(String matricNumber){
        this.studentList[this.numStudentsRegistered] = matricNumber;
        this.numStudentsRegistered++;
        this.vacancy--;
    }

    public void printIndexDetails(){
        System.out.printf("%-11s %-7s %-7s\n", this.indexNumber, this.vacancy, this.numStudentsWaiting);
    }

    public Lesson[] getLessons() {
        List<Lesson> lessonList = new ArrayList<>(Arrays.asList(tutorial));
        lessonList.addAll(Arrays.asList(lab));
        Lesson[] lessons = new Lesson[lessonList.size()];
        int count = 0;
        for (Lesson l: lessonList){
            lessons[count] = l;
            count++;
        }
        return lessons;
    }
    public void printEveryDetail(){
        System.out.println(this.indexNumber);
        System.out.println(this.vacancy);
        System.out.println(this.numStudentsRegistered);
        System.out.println(this.numStudentsWaiting);
        for(int i=0; i<numStudentsRegistered; ++i)
            System.out.println(studentList[i]);
    }
}
