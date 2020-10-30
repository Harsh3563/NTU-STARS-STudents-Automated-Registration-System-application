package Entity;

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
    
    public Course(String courseCode, int numAUs, String school, IndexGroup[] indexList, int maxLimit, int vacancy, Student[] roster, Lesson[] lecture, int numTuts, int numLabs) {
        this.courseCode = courseCode;
        this.numAUs = numAUs;
        this.school = school;
        this.indexList = indexList;
        this.maxLimit = maxLimit;
        this.vacancy = vacancy;
        this.roster = roster;
        this.lecture = lecture;
        this.numTuts = numTuts;
        this.numLabs = numLabs;
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

    public int getnumLabs() {
        return numLabs;
    }

    /**
     *
     * @param hasLab boolean variable which indicates whether the course has labs
     */
    public void setnumLabs(int numLabs) {
        this.numLabs = numLabs;
    }


    public int getnumTuts() {
        return numTuts;
    }

    /**
     *
     * @param hasTut boolean variable which indicates whether the course has tutorials
     */
    public void setnumTuts(int numTuts) {
        this.numTuts = numTuts;
    }

    public Student[] getRoster() {
        return roster;
    }
}
