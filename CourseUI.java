package Boundary;

import Control.CourseMgr;
import Entity.IndexGroup;
import Entity.Lesson;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class CourseUI {

    public static void CourseApp() throws ClassNotFoundException, NoSuchMethodException, IOException, IllegalAccessException, InvocationTargetException {
        int choice, numAUs, maxLimit, numTuts, numLabs, index;
        String courseCode, newCourseCode, school, quitOpt;
        IndexGroup[] indexList;
        Lesson[] lecture;

        CourseMgr manager = new CourseMgr();

        Scanner sc = new Scanner(System.in);
        do {
            try
            {
            System.out.println("\nCourse Application\n"
                    + "Select your choice:\n"
                    + "[1] Add course to List\n"
                    + "[2] Change number of AUs\n"
                    + "[3] Change School\n"
                    + "[4] Change Max Limit\n"
                    + "[5] Change Course Code\n"
                    + "[6] Print all course codes\n"
                    + "[7] Exit the program");
            choice = sc.nextInt();
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid choice! Please enter an integer value");
                continue;
            }                                           

                switch(choice) {
                    case 1: //Adding Course to List
                        try
                        {
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
                        }
                        catch (Exception e)
                        {
                            System.out.println("Invalid value! Please enter an integer value");
                            break;
                        }    
                    case 2://Changing number of AUs
                        try
                        {
                        System.out.print("Enter desired course code: ");
                        courseCode = sc.next();
                        index = manager.checkIfCourseExists(courseCode);
                        if(index == -1) {
                            System.out.println("Course does not exist!");
                            break;
                        }
                        try
                        {
                        System.out.print("Enter new number of AUs: ");
                        numAUs = sc.nextInt();
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Invalid value! Please enter an integer value");
                            break;
                        }
                        manager.changeAU(courseCode, numAUs, index);
                        }
                        catch (Exception e)
                        {
                            System.out.println("Invalid value! Please enter an integer value");
                            break;
                        }
                        break;
                    case 3://Changing School
                        try
                        {
                        System.out.print("Enter desired course code: ");
                        courseCode = sc.next();
                        index = manager.checkIfCourseExists(courseCode);
                        if(index == -1) {
                            System.out.println("Course does not exist!");
                            break;
                        }
                        try
                        {
                        System.out.print("Enter new school: ");
                        school = sc.next();
                        }
                        catch (Exception e)
                        {
                            System.out.println("Invalid value! Please enter an integer value");
                            break;
                        }
                        manager.changeSchool(courseCode, school, index);
                        }
                        catch (Exception e)
                        {
                            System.out.println("Invalid value! Please enter an integer value");
                            break;
                        }
                        break;
                    case 4://Change max limit
                        try
                        {
                        System.out.print("Enter desired course code: ");
                        courseCode = sc.next();
                        index = manager.checkIfCourseExists(courseCode);
                        if(index == -1) {
                            System.out.println("Course does not exist!");
                            break;
                        }
                        try
                        {
                        System.out.print("Enter new max number of students: ");
                        maxLimit = sc.nextInt();
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Invalid value! Please enter an integer value");
                            break;
                        }
                        manager.changeMaxLimit(courseCode, maxLimit, index);
                        }
                        catch (Exception e)
                        {
                            System.out.println("Invalid value! Please enter an integer value");
                            break;
                        }
                        break;
                    case 5: //Change course code
                        try
                        {
                        System.out.print("Enter desired course code: ");
                        courseCode = sc.next();
                        index = manager.checkIfCourseExists(courseCode);
                        if(index == -1) {
                            System.out.println("Course does not exist!");
                            break;
                        }
                        try
                        {
                        System.out.print("Enter new course code: ");
                        newCourseCode = sc.next();
                        }
                        catch (Exception e)
                        {
                            System.out.println("Invalid value! Please enter an integer value");
                            break;
                        }
                        manager.changeCourseCode(courseCode, newCourseCode, index);
                        }
                        catch (Exception e)
                        {
                            System.out.println("Invalid value! Please enter an integer value");
                            break;
                        }
                        break;
                    case 6:
                        manager.printCourses();
                        break;
                    case 7:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice! Enter the correct value");
                }
            try
            {
            System.out.println("Press 'Q' to terminate , 'C' to continue");
            quitOpt=sc.next();
            }
            catch (Exception e)
            {
                System.out.println("Invalid value! Please enter correct value");
            }
        } while(quitOpt!="Q");
    }


}
