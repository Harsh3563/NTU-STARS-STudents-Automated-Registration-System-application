package Boundary;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Scanner;

import Control.CourseMgr;
import Entity.Course;
import Entity.IndexGroup;
import Entity.Lesson;
import Entity.Student;

public class CourseApp {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IOException, IllegalAccessException, InvocationTargetException {
        // TODO Auto-generated method stub
        int choice;
        String courseCode, newCourseCode;
        int numAUs;
        String school;
        IndexGroup[] indexList;
        int maxLimit;
        Lesson[] lecture;
        int numTuts;
        int numLabs;

        CourseMgr manager = new CourseMgr();

        Scanner sc = new Scanner(System.in);
        int index;
        do {
            System.out.println("\nCourse Application\n"
                    + "Select your choice:\n"
                    + "[1] Add course to List\n"
                    + "[2] Change number of AUs\n"
                    + "[3] Change School\n"
                    + "[4] Change Max Limit\n"
                    + "[5] Change Course Code\n"
                    + "[6] Change Number of Tutorials\n"
                    + "[7] Change Number of Labs\n"
                    + "[8] Print all course codes\n"
                    + "[9] Exit the program");
            choice = sc.nextInt();
            switch(choice) {
                case 1: //Adding Course to List
                    System.out.print("Enter desired course code: ");
                    courseCode = sc.next();
                    if(manager.checkIfCourseExists(courseCode) != -1) {
                        System.out.println("Course Code already exists!");
                        break;
                    }
                    else {
                        manager.addCoursetoList(courseCode);
                        break;
                    }
                case 2://Changing number of AUs
                    System.out.print("Enter desired course code: ");
                    courseCode = sc.next();
                    index = manager.checkIfCourseExists(courseCode);
                    if(index == -1)
                        System.out.println("Course does not exist!");
                    System.out.print("Enter new number of AUs: ");
                    numAUs = sc.nextInt();
                    manager.changeAU(courseCode, numAUs, index);
                    break;
                case 3://Changing School
                    System.out.print("Enter desired course code: ");
                    courseCode = sc.next();
                    index = manager.checkIfCourseExists(courseCode);
                    if(index == -1)
                        System.out.println("Course does not exist!");
                    System.out.print("Enter new school: ");
                    school = sc.next();
                    manager.changeSchool(courseCode, school, index);
                    break;
                case 4://Change max limit
                    System.out.print("Enter desired course code: ");
                    courseCode = sc.next();
                    index = manager.checkIfCourseExists(courseCode);
                    if(index == -1)
                        System.out.println("Course does not exist!");
                    System.out.print("Enter new max number of students: ");
                    maxLimit = sc.nextInt();
                    manager.changeMaxLimit(courseCode, maxLimit, index);
                    break;
                case 5: //Change course code
                    System.out.print("Enter desired course code: ");
                    courseCode = sc.next();
                    index = manager.checkIfCourseExists(courseCode);
                    if(index == -1)
                        System.out.println("Course does not exist!");
                    System.out.print("Enter new course code: ");
                    newCourseCode = sc.next();
                    manager.changeCourseCode(courseCode, newCourseCode, index);
                    break;
                case 6: //Change number of tutorials
                    System.out.println("Changing of number of tutorials may cause problems!!");
                    System.out.print("Enter desired course code: ");
                    courseCode = sc.next();
                    index = manager.checkIfCourseExists(courseCode);
                    if(index == -1)
                        System.out.println("Course does not exist!");
                    System.out.print("Enter new number of tutorials: ");
                    numTuts = sc.nextInt();
                    manager.changeNumTuts(courseCode, numTuts, index);
                    break;
                case 7://Change number of labs
                    System.out.println("Changing of number of labs may cause problems!!");
                    System.out.print("Enter desired course code: ");
                    courseCode = sc.next();
                    index = manager.checkIfCourseExists(courseCode);
                    if(index == -1)
                        System.out.println("Course does not exist!");
                    System.out.print("Enter new number of labs: ");
                    numLabs = sc.nextInt();
                    manager.changeNumLabs(courseCode, numLabs, index);
                    break;
                case 8:
                    manager.printCourses();
                    break;
                case 100:
                    manager.printEveryCourseDetailPossible();
                    break;
                default:
                    System.out.println("Thank you for using STARS!!!\n"
                            + "Have a good day!");
            }
        }while(choice != 9);
    }


}
