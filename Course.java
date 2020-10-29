package Entity;

import java.io.Serializable;
import java.time.LocalTime;

public class Course implements Serializable {

    private String courseCode;
    private String school;
    private IndexGroup[] indexList = new IndexGroup[15];
    private int maxLimit;
    private int vacancy;
    private Student[] roster;
    private boolean hasLab;
    private boolean hasTut;

    /**
     * @param courseCode the unique code for the course
     * @param school the school by which the course is offered
     * @param maxLimit the maximum number of students that can be enrolled in this course
     * @param vacancy the number of vacancies for the course
     * @param hasLab boolean variable which indicates whether the course has labs
     * @param hasTut boolean variable which indicates whether the course has tutorials
     */
    public Course(String courseCode, String school, int maxLimit, int vacancy, boolean hasLab,
                  boolean hasTut) {
        this.courseCode = courseCode;
        this.school = school;
        this.maxLimit = maxLimit;
        this.roster = new Student[maxLimit];
        this.vacancy = vacancy;
        this.hasLab = hasLab;
        this.hasTut = hasTut;
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

    public boolean isHasLab() {
        return hasLab;
    }

    /**
     *
     * @param hasLab boolean variable which indicates whether the course has labs
     */
    public void setHasLab(boolean hasLab) {
        this.hasLab = hasLab;
    }


    public boolean isHasTut() {
        return hasTut;
    }

    /**
     *
     * @param hasTut boolean variable which indicates whether the course has tutorials
     */
    public void setHasTut(boolean hasTut) {
        this.hasTut = hasTut;
    }

    public Student[] getRoster() {
        return roster;
    }
    public boolean equals (Course c){
        return (this.courseCode.equals(c.getCourseCode()));
    }
    public static Course downcast(Object object){
        return (Course)(object);
    }
}
