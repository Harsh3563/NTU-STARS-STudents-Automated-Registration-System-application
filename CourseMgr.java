package Control;

import Entity.Course;
import Entity.IndexGroup;
import Entity.Lesson;
import Entity.Student;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseMgr {

    static List<Course> courseList;
    /*public void createCourseList() {
        List<Object> objectList = FileManipMgr.readObjectsFromFile("course.dat");
        for(Object o: objectList){
            courseList.add((Course)o);
        }
    } */

    public static int checkIfCourseExists(String courseCode) {
        Course course = new Course(courseCode);
        return FileManipMgr.checkIfObjectExists(course);
    }

    public void addCoursetoList(String courseCode) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the course title: ");
        String courseTitle = sc.next();
        System.out.print("Enter number of AUs: ");
        int numAUs = sc.nextInt();
        System.out.print("Enter school: ");
        String school = sc.next();

        System.out.print("Enter total number of students allowed: ");
        int maxLimit = sc.nextInt();

        System.out.print("Enter the number of tutorials: ");
        int numTuts = sc.nextInt();
        System.out.print("Enter the number of labs: ");
        int numLabs = sc.nextInt();
        System.out.print("Enter the number of lectures: ");
        int numLecs = sc.nextInt();

        //Function defined below
        IndexGroup[] indexList = inputIndexList(courseCode, numTuts, numLabs);//Ask for tutorial and lab timings

        Course newcourse = new Course(courseCode, courseTitle,
                numAUs, school, indexList, maxLimit, numTuts, numLabs,numLecs);//ask for lecture timings
        //courseList.add(newcourse);
        try {
            FileManipMgr.addObjectToFile(newcourse);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException
                | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
    }

    public boolean changeAU(String courseCode, int newAU, int index) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException,
            IOException {
        List<Object> objectList = FileManipMgr.readObjectsFromFile("course.dat");
        Course course = (Course) objectList.get(index);
        course.setAUs(newAU);
        objectList.set(index, course);
        FileManipMgr.writeObjectsToFile(objectList, "course.dat");
        return true;
    }

    public boolean changeSchool(String courseCode, String school, int index) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException,
            IOException {
        List<Object> objectList = FileManipMgr.readObjectsFromFile("course.dat");
        Course course = (Course) objectList.get(index);
        course.setSchool(school);
        objectList.set(index, course);
        FileManipMgr.writeObjectsToFile(objectList, "course.dat");
        return true;
    }

    public boolean changeMaxLimit(String courseCode, int maxLimit, int index) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException,
            IOException {
        List<Object> objectList = FileManipMgr.readObjectsFromFile("course.dat");
        Course course = (Course) objectList.get(index);
        course.setMaxLimit(maxLimit);
        objectList.set(index, course);
        FileManipMgr.writeObjectsToFile(objectList, "course.dat");
        return true;
    }

    public boolean changeCourseCode(String courseCode, String newCourseCode, int index) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException,
            IOException {
        List<Object> objectList = FileManipMgr.readObjectsFromFile("course.dat");
        Course course = (Course) objectList.get(index);
        course.setCourseCode(newCourseCode);
        objectList.set(index, course);
        FileManipMgr.writeObjectsToFile(objectList, "course.dat");
        return true;
    }

    public boolean changeNumTuts(String courseCode, int numTuts, int index) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException,
            IOException {
        List<Object> objectList = FileManipMgr.readObjectsFromFile("course.dat");
        Course course = (Course) objectList.get(index);
        
        int oldNumTuts = course.getNumTuts();
        course.setNumTuts(numTuts);
        System.out.println("New number of tutorials is " + course.getNumTuts());
        if(numTuts > oldNumTuts) {
            course.setIndex(inputIndexList(courseCode, numTuts, course.getNumTuts()));
        }
        //course = courseList.get(i);
        //updateCourse(i);
        objectList.set(index, course);
        FileManipMgr.writeObjectsToFile(objectList, "course.dat");
        return true;
    }

    public boolean changeNumLabs(String courseCode, int numLabs, int index) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException,
            IOException {
        List<Object> objectList = FileManipMgr.readObjectsFromFile("course.dat");
        Course course = (Course) objectList.get(index);
        
        int oldNumLabs = course.getNumLabs();
        course.setNumLabs(numLabs);
        System.out.println("New number of labs is " + course.getNumLabs());
        if(numLabs > oldNumLabs) {
            course.setIndex(inputIndexList(courseCode, numLabs, course.getNumLabs()));
        }
        //course = courseList.get(i);
        //updateCourse(i);
        objectList.set(index, course);
        FileManipMgr.writeObjectsToFile(objectList, "course.dat");
        return true;
    }

    public static boolean printCourses() {
        courseList = new ArrayList<>();
        List<Object> objectList = FileManipMgr.readObjectsFromFile("course.dat");
        for(Object o: objectList){
            courseList.add((Course) o);
        }
        if (courseList.isEmpty()){
            System.out.println("There are no course records.");
            return false;
        }
        System.out.println("******************************************************************************************************************");
        System.out.printf("%-10s %-10s %-9s %-9s %-9s %-15s %-15s %-16s\n", "Course Code", "Course Title",
                "No. of AUs", "School", "Max Limit", "No. of Tuts", "No. of Labs", "No. of Vacancies");
        System.out.println("******************************************************************************************************************");
        for (Course c: courseList){
            c.displayDetails();
        }
        return true;
    }

    /*public void updateCourse(int index) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException,
            IOException {
        //FileManipMgr.writeObjectsToFile(courseList, index);
    }*/

    private IndexGroup[] inputIndexList(String courseCode, int numTuts, int numLabs) {
        Scanner sc = new Scanner(System.in);
        int numIndexes, maxLimit, indexNumber;

        System.out.print("Enter number of indexes: ");
        numIndexes = sc.nextInt();
        IndexGroup[] indexList = new IndexGroup[numIndexes];
        for(int i = 0; i < numIndexes; i ++) {
            System.out.print("Enter desired index number: ");
            indexNumber = sc.nextInt();
            System.out.print("Enter the max number of students for this index: ");
            maxLimit = sc.nextInt();
            indexList[i] = new IndexGroup(courseCode, indexNumber, maxLimit, numTuts, numLabs);
        }

        System.out.println("Done with inputting of indexes.");
        return indexList;
    }
    public static List<Course> obtainCourseList(){
        List<Object> objectList = FileManipMgr.readObjectsFromFile("course.dat");
        courseList = new ArrayList<>();
        for(Object o:objectList){
            courseList.add((Course)o);
        }
        return courseList;
    }

    public static boolean writeCoursesToFile(List<Object> courseList) {
        try {
            FileManipMgr.writeObjectsToFile(courseList, "course.dat");
        } catch (ClassNotFoundException | NoSuchMethodException | IOException
                | InvocationTargetException | IllegalAccessException e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean printEveryCourseDetailPossible() {
        courseList = new ArrayList<>();
        List<Object> objectList = FileManipMgr.readObjectsFromFile("course.dat");
        for(Object o: objectList){
            courseList.add((Course) o);
        }
        if (courseList.isEmpty()){
            System.out.println("There are no course records.");
            return false;
        }
        for (Course c: courseList){
            c.displayEveryDetail();
        }
        return true;
    }
}
