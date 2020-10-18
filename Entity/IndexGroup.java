package Entity;

public class IndexGroup {

    private Course course;
    private int indexNumber;
    private Student[] studentList;

    /**
     *
     * @param course course to which this index number belongs
     * @param indexNumber unique index number of this index group
     * @param studentList list of students enrolled in this index group
     */
    public IndexGroup(Course course, int indexNumber, Student[] studentList){
        this.course = course;
        this.indexNumber = indexNumber;
        this.studentList = studentList;
    }

    public int getCourseCode() {
        return this.course.getCourseCode();
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
}
