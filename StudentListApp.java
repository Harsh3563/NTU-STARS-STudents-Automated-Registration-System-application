package Boundary;

import Control.CourseMgr;
import Control.StudentListMgr;
import Entity.Course;
import Entity.IndexGroup;

import java.util.List;
import java.util.Scanner;

public class StudentListUI {
    public static void StudentListApp(){
        Scanner sc = new Scanner(System.in);
        int choice = -1, counter, choice1;
        List<Course> courseList;
        Course c;
        do{
            System.out.println("Choose your option according to the following menu: ");
            System.out.println("1. Print student list by course.\n2. Print student list by index group.");
            choice =  sc.nextInt();
            switch(choice){
                case 1:
                    courseList = CourseMgr.obtainCourseList();
                    counter = 1;
                    for(Course course: courseList) {
                        System.out.println(counter + ". " + course.getCourseCode());
                        counter++;
                    }
                    choice1 = sc.nextInt();
                    c = courseList.get(choice1 - 1);
                    StudentListMgr.printStudentListByCourse(c);
                    break;
                case 2:
                    courseList = CourseMgr.obtainCourseList();
                    counter = 1;
                    for(Course course: courseList) {
                        System.out.println(counter + ". " + course.getCourseCode());
                        counter++;
                    }
                    choice1 = sc.nextInt();
                    c = courseList.get(choice1 - 1);
                    counter = 1;
                    System.out.println("Choose from the following index groups:");
                    IndexGroup[] indexGroups = c.getIndexList();
                    for(IndexGroup i: indexGroups){
                        System.out.println(counter + ". " + i.getIndexNumber());
                        counter++;
                    }
                    choice1 = sc.nextInt();
                    IndexGroup i = indexGroups[choice1 - 1];
                    StudentListMgr.printStudentListByIndexGroup(i);
                    break;
                default: System.out.println("Please enter a valid option.");
            }
        }while(choice!=-1);

    }
}
