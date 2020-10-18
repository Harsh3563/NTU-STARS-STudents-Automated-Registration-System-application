package Entity;

public class Course {

    private int courseCode;
    private String school;
    private IndexGroup[] indexList;
    private int maxLimit;
    private int vacancy;
    private Student[] roster;
    private boolean hasLab;
    private boolean hasTut;

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
    public Course(int courseCode, String school, IndexGroup[] indexList, int maxLimit, int vacancy, Student[] roster, boolean hasLab, boolean hasTut) {
        this.courseCode = courseCode;
        this.school = school;
        this.indexList = indexList;
        this.maxLimit = maxLimit;
        this.vacancy = vacancy;
        this.roster = roster;
        this.hasLab = hasLab;
        this.hasTut = hasTut;
    }

    public int getCourseCode() {
        return this.courseCode;
    }

    /**
     *
     * @param courseCode the unique code for the course
     */
    public void setCourseCode(int courseCode) {
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
}
