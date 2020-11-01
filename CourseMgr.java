package Control;

import Entity.Course;
import Entity.IndexGroup;
import Entity.Lesson;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseMgr {
	static List<Course> courseList = new ArrayList<>();

	
	public boolean checkIfCourseExists(String courseCode) {
		//Course course = new Course();
		//List<Course> courseList = new ArrayList<Course>();
		//return courseList.stream().anyMatch(Course -> courseCode.equals(Course.getCourseCode()));
		return false;
	}
	public void addCoursetoList(String courseCode) {
		
		Scanner sc = new Scanner(System.in);
		
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

		Course newcourse = new Course(courseCode, numAUs, school, indexList, maxLimit, numTuts, numLabs,numLecs);//ask for lecture timings
		courseList.add(newcourse);
	}
	
	public boolean changeAU(String courseCode, int newAU) {
		for(int i = 0; i < courseList.size(); i++) {
			if(courseList.get(i).getCourseCode() == courseCode) {
				courseList.get(i).setAUs(newAU);
				System.out.println("New AUs is "+ courseList.get(i).getAUs());
				return true;
			}
		}
		return false;
	}
	
	public boolean changeSchool(String courseCode, String school) {
		for(int i = 0; i < courseList.size(); i++) {
			if(courseList.get(i).getCourseCode() == courseCode) {
				courseList.get(i).setSchool(school);
				System.out.println("New School is " + courseList.get(i).getSchool());
				return true;
			}
		}	
		return false;
	}
	
	public boolean changeMaxLimit(String courseCode, int maxLimit) {
		for(int i = 0; i < courseList.size(); i++) {
			if(courseList.get(i).getCourseCode() == courseCode) {
				courseList.get(i).setMaxLimit(maxLimit);
				System.out.println("New Max Limit is " + courseList.get(i).getMaxLimit());
				return true;
			}
		}
		return false;
	}
	
	public boolean changeCourseCode(String courseCode, String newCourseCode) {
		for(int i = 0; i < courseList.size(); i++) {
			if(courseList.get(i).getCourseCode() == courseCode) {
				courseList.get(i).setCourseCode(newCourseCode);
				System.out.println("New Course Code is " + courseList.get(i).getCourseCode());
				return true;
			}
		}
		return false;
	}
	
	public boolean numTuts(String courseCode, int numTuts) {
		for(int i = 0; i < courseList.size(); i++) {
			if(courseList.get(i).getCourseCode() == courseCode) {
				courseList.get(i).setNumTuts(numTuts);
				System.out.println("New number of tutorials is " + courseList.get(i).getNumTuts());
				return true;
			}
		}
		return false;
	}
	
	public boolean numLabs(String courseCode, int numLabs) {
		for(int i = 0; i < courseList.size(); i++) {
			if(courseList.get(i).getCourseCode() == courseCode) {
				courseList.get(i).setNumLabs(numLabs);
				System.out.println("New number of labs is " + courseList.get(i).getNumLabs());
				return true;
			}
		}
		return false;
	}
	
	public void printCourses() {
		for(int i = 0; i < courseList.size();i++) {
			System.out.println("Course Code: " + courseList.get(i).getCourseCode());
			System.out.println("Number of AUs: " + courseList.get(i).getAUs());
			System.out.println("School: " + courseList.get(i).getSchool());
			System.out.println("Max Limit: " + courseList.get(i).getMaxLimit());
			System.out.println("Number of Tutorials: " + courseList.get(i).getNumTuts());
			System.out.println("Number of Labs: " + courseList.get(i).getNumLabs());
			System.out.println("Number of Vacancies: " + courseList.get(i).getVacancy());
		}
	}
	
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
}