package Entity;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import FileIO.FileIO;
import Entity.Course;

import javax.sound.midi.VoiceStatus;


public class Student implements Serializable {

    private String name;
    private String matricNumber;
    private String nationality;
    private char gender;
    private Course[] coursesTaken;
    private Course[] coursesWaiting;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * @param name the name of the student
     * @param matricNumber the matriculation number of the student
     * @param nationality the nationality of the student
     * @param gender the gender of the student
     * @param coursesTaken the courses in which the student is enrolled
     * @param coursesWaiting the courses for which the student is on waiting list
     * @param startDate starting date of student access period
     * @param endDate ending date of student access period
     * @param startTime starting time of student access period
     * @param endTime ending time of student access period
     */
    public Student(String name, String matricNumber, String nationality, char gender, Course[] coursesTaken, Course[] coursesWaiting, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        this.name = name;
        this.matricNumber = matricNumber;
        this.nationality = nationality;
        this.gender = gender;
        this.coursesTaken = coursesTaken;
        this.coursesWaiting = coursesWaiting;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Student() {
        //do nothing
    }

    public String getName() {
        return this.name;
    }

    /**
     *
     * @param name the name of the student
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getMatricNumber() {
        return this.matricNumber;
    }

    /**
     *
     * @param matricNumber the matriculation number of the student
     */
    public void setMatricNumber(String matricNumber) {
        this.matricNumber = matricNumber;
    }

    public String getNationality() {
        return this.nationality;
    }

    /**
     *
     * @param nationality the nationality of the student
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public char getGender() {
        return this.gender;
    }

    /**
     *
     * @param gender the gender of the student
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    public Course[] getCoursesTaken() {
        return coursesTaken;
    }

    public Course[] getCoursesWaiting() {
        return coursesWaiting;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate starting date of student access period
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     *
     * @param endDate ending date of student access period
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     *
     * @param startTime starting time of student access period
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     *
     * @param endTime ending time of student access period
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public static List<Student> readObjectsFromFile() throws IOException {
        List<Student> studentList = new ArrayList<>();
        FileInputStream fiStream = new FileInputStream("student.dat");
        BufferedInputStream biStream = new BufferedInputStream(fiStream);
        ObjectInputStream oiStream = new ObjectInputStream(biStream);
        try {
            int counter = 0;
            while (true) {
                Object inputObject;
                inputObject = oiStream.readObject();
                if (inputObject instanceof Student)
                    studentList.set(counter, (Student) inputObject);
                else {
                    System.out.println("Database error! Wrong type of object in file.");
                    System.exit(0);
                }
                counter++;
            }
        } catch (EOFException e) {
            System.out.println("Objects successfully read!");
        } catch (ClassNotFoundException e) {
            System.out.println("Database error! Class type does not match.");
            e.printStackTrace();
            System.exit(0);
        }finally {
            oiStream.close();
        }
        return studentList;
    }

    public static void writeObjectsToFile(Object object) {
        try {
            FileOutputStream foStream = new FileOutputStream("student.dat", true);
            BufferedOutputStream boStream = new BufferedOutputStream(foStream);
            ObjectOutputStream ooStream = new ObjectOutputStream(boStream);
            ooStream.writeObject(object);
            ooStream.close();
            System.out.println("Student record has been successfully added!");
        } catch (IOException e) {
            System.out.println("An IO error has occurred.");
            e.printStackTrace();
            System.exit(0);
        }
    }
}
