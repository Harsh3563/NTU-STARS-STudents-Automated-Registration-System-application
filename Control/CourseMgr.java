package Control;

import Entity.Course;
import Entity.IndexGroup;
import Entity.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CourseMgr {
	static List<Course> courseList;
	public static boolean checkIfCourseExists(String courseCode) {
		//Course course = new Course();
		//List<Course> courseList = new ArrayList<Course>();
		return courseList.stream().anyMatch(Course -> courseCode.equals(Course.getCourseCode()));
	}
	public static void addCoursetoList(int courseCode, String school, IndexGroup[] indexList, 
									   int maxLimit, int vacancy, Student[] roster, 
									   boolean hasLab, boolean hasTut) {
		Course newcourse = new Course(courseCode, school, indexList, maxLimit, vacancy, roster, hasLab, hasTut);
		courseList.add(newcourse);
	}
}
