package Boundary;

import java.util.Scanner;

import Control.CourseMgr;
import Entity.IndexGroup;
import Entity.Lesson;
import Entity.Student;

public class CourseApp {

	public static void main(String[] args) {
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
		
		do {
		System.out.println("Course Application\n"
				+ "Select your choice:\n"
				+ "[1] Add course to List\n"
				+ "[2] Change number of AUs\n"
				+ "[3] Change School\n"
				+ "[4] Change Max Limit\n"
				+ "[5] Change Course Code\n"
				+ "[6] Change Number of Tutorials\n"
				+ "[7] Change Number of Labs\n"
				+ "[8] Print all course codes");
		choice = sc.nextInt();
		switch(choice) {
		case 1: //Adding Course to List
			System.out.print("Enter desired course code: ");
			courseCode = sc.next();
			if(manager.checkIfCourseExists(courseCode)) {
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
			System.out.print("Enter new number of AUs: ");
			numAUs = sc.nextInt();
			if(!manager.changeAU(courseCode, numAUs))
				System.out.println("Course does not exist!");
			break;
		case 3://Changing School
			System.out.print("Enter desired course code: ");
			courseCode = sc.next();
			System.out.print("Enter new school: ");
			school = sc.next();
			if(!manager.changeSchool(courseCode, school))
				System.out.println("Course does not exist!");
			break;
		case 4://Change max limit
			System.out.print("Enter desired course code: ");
			courseCode = sc.next();
			System.out.print("Enter new max number of students: ");
			maxLimit = sc.nextInt();
			if(!manager.changeMaxLimit(courseCode, maxLimit))
				System.out.println("Course does not exist!");
			break;
		case 5: //Change course code
			System.out.print("Enter desired course code: ");
			courseCode = sc.next();
			System.out.print("Enter new course code: ");
			newCourseCode = sc.next();
			if(!manager.changeCourseCode(courseCode, newCourseCode))
				System.out.println("Course does not exist!");
			break;
		case 8:
			manager.printCourses();
			break;
		default:
			System.out.println("Thank you for using STARS!!!\n"
					+ "Have a good day!");
		}
		}while(choice != 9);
	}


}
