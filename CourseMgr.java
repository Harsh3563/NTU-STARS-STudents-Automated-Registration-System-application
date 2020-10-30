package Control;

import Entity.Course;
import Entity.IndexGroup;
import Entity.Lesson;

import java.util.List;

public class CourseMgr {
	private List<Course> courseList;
	public boolean checkIfCourseExists(String courseCode) {
		//Course course = new Course();
		//List<Course> courseList = new ArrayList<Course>();
		return courseList.stream().anyMatch(Course -> courseCode.equals(Course.getCourseCode()));
	}
	public void addCoursetoList(String courseCode, int numAUs, String schools, IndexGroup[] indexList, 
			int maxLimit, Lesson[] lecture, int numTuts, int numLabs) {
		Course newcourse = new Course(courseCode, numAUs, schools, indexList, maxLimit, lecture, numTuts, numLabs);
		courseList.add(newcourse);
	}
	
	public boolean changeAU(String courseCode, int newAU) {
		for(int i = 0; i < courseList.size(); i++) {
			if(courseList.get(i).getCourseCode() == courseCode) {
				courseList.get(i).setAUs(newAU);
				return true;
			}
		}
		return false;
	}
	
	public boolean changeSchool(String courseCode, String school) {
		for(int i = 0; i < courseList.size(); i++) {
			if(courseList.get(i).getCourseCode() == courseCode) {
				courseList.get(i).setSchool(school);
				return true;
			}
		}	
		return false;
	}
	
	public boolean changeMaxLimit(String courseCode, int maxLimit) {
		for(int i = 0; i < courseList.size(); i++) {
			if(courseList.get(i).getCourseCode() == courseCode) {
				courseList.get(i).setMaxLimit(maxLimit);
				return true;
			}
		}
		return false;
	}
	
	public boolean changeCourseCode(String courseCode, String newCourseCode) {
		for(int i = 0; i < courseList.size(); i++) {
			if(courseList.get(i).getCourseCode() == courseCode) {
				courseList.get(i).setCourseCode(newCourseCode);
				return true;
			}
		}
		return false;
	}
	
	public boolean numTuts(String courseCode, int numTuts) {
		for(int i = 0; i < courseList.size(); i++) {
			if(courseList.get(i).getCourseCode() == courseCode) {
				courseList.get(i).setnumTuts(numTuts);
				return true;
			}
		}
		return false;
	}
	
	public boolean numLabs(String courseCode, int numLabs) {
		for(int i = 0; i < courseList.size(); i++) {
			if(courseList.get(i).getCourseCode() == courseCode) {
				courseList.get(i).setnumLabs(numLabs);
				return true;
			}
		}
		return false;
	}
}