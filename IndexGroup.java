package Entity;

import java.io.Serializable;
import java.time.LocalTime;

public class IndexGroup implements Serializable {

    private String courseCode;
    private int indexNumber;
    private int numStudents;
    private Student[] studentList;
    private int numLessons;
    private Lesson[] lessonTimings;

    /**
     * @param courseCode course to which this index number belongs
     * @param indexNumber unique index number of this index group
     * @param numStudents number of students in the index group
     * @param numLessons number of lessons for the index group
     */
    public IndexGroup(String courseCode, int indexNumber, int numStudents, int numLessons, Lesson[] lessonList){
        this.courseCode = courseCode;
        this.indexNumber = indexNumber;
        this.numStudents = numStudents;
        this.studentList = new Student[numStudents];
        this.numLessons = numLessons;
        this.lessonTimings = new Lesson[numLessons];
        for(int i=0; i < lessonTimings.length; ++i)
            lessonTimings[i] = lessonList[i];
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
    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    public Student[] getStudentList() {
        return studentList;
    }

    public int getNumStudents() {
        return numStudents;
    }

    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }
    public boolean equals(IndexGroup i){
        return (this.courseCode.equals(i.getCourseCode()) && this.indexNumber == i.getIndexNumber());
    }
    public static IndexGroup downcast(Object object){
        return (IndexGroup) (object);
    }
}
