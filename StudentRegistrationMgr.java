package Control;

import Entity.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class StudentRegistrationMgr {
    /*public static int findStudentByUserName(String username){
        List<Object> objectList = FileManipMgr.readObjectsFromFile("student.dat");
        int count = 0;
        for(Object o: objectList) {
            if (((Student) o).getNetworkUsername().equals(username))
                return count;
            count += 1;
        }
        return -1;
    }*/

    /*public static boolean checkTimeTableClash(HashMap<Lesson.Day, HashMap<String, LocalTime[]>> timeTable,
                                              Lesson[] lessons){
        Lesson.Day[] dayValues = Lesson.Day.values();
        for(int j = 0; j < 7; ++j){
            Iterator<Map.Entry<String, LocalTime[]>> it = timeTable.get(dayValues[j]).entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String, LocalTime[]> l = it.next();
                LocalTime lessonStartTime = l.getValue()[0];
                LocalTime lessonEndTime = l.getValue()[1];
                for(int k = 0; k < lessons.length; ++k){
                    LocalTime newCourseLessonStartTime = lessons[k].getStartTime();
                    LocalTime newCourseLessonEndTime = lessons[k].getEndTime();
                    if((newCourseLessonStartTime.compareTo(lessonEndTime) < 0) &&
                            (newCourseLessonEndTime.compareTo(lessonStartTime) > 0)) {
                        System.out.println("Time table clash with course code " + l.getKey());
                        return false;
                    }
                }
            }
        }
        System.out.println("No time table clash!");
        return true;
    }*/

    public static boolean registerStudentForCourse(Student student, String courseCode, int indexNum){
        //int index_student = findStudentByMatricNum(username);
        int index_student = StudentMgr.checkIfStudentExists(student);
        if(index_student == -1) {
            System.out.println("Did not find student record with this username.");
            return false;
        }
        int index_course = CourseMgr.checkIfCourseExists(courseCode);
        if(index_course == -1){
            System.out.println("Did not find course record with this course code.");
            return false;
        }
        List<Object> studentList = FileManipMgr.readObjectsFromFile("student.dat");
        Student s = (Student)(studentList.get(index_student));
        List<Object> courseList = FileManipMgr.readObjectsFromFile("course.dat");
        Course c = (Course)(courseList.get(index_course));
        if(s.checkIfAlreadyApplied(c.getCourseCode()))
            return false;
        IndexGroup i = null;
        IndexGroup[] indexList = c.getIndexList();
        int count = 0, index_ig = 0;
        for(IndexGroup ig: indexList){
            if(ig.getIndexNumber() == indexNum)
            {
                i = ig;
                index_ig = count;
                break;
            }
            count++;
        }
        boolean toAddToWaitingList = false;
        List<Lesson> lessonList = new ArrayList<>(Arrays.asList(i.getLessons()));
        lessonList.addAll(Arrays.asList(c.getLectureLessons()));
        Lesson[] lessons = new Lesson[lessonList.size()];
        int counter = 0;
        for (Lesson l: lessonList){
            lessons[counter] = l;
            counter++;
        }
        TimeTable timeTable = s.getTimeTable();
        if((s.getAUsRegistered() + c.getAUs()) > 21){
            System.out.println("Note that the course will not be registered immediately even if there are " +
                    "vacancies since registering for this course would go beyond the max AU limit. " +
                    "You will be placed on waiting list." +
                    "Consider dropping a course.");
            toAddToWaitingList = true;
        }
        if(c.getVacancy() == 0){
            System.out.println("Note that there in no vacancy for this course. You will be placed on " +
                    "waitlist");
            toAddToWaitingList = true;
        }
        else if(i.getVacancy() == 0) {
            System.out.println("Note that there is no vacancy for this index group. You will be placed " +
                    "on waitlist.");
            toAddToWaitingList = true;
        }
        System.out.println(toAddToWaitingList);
        if(!timeTable.checkForClash(lessons, courseCode))
            return false;
        if(toAddToWaitingList){
            s.addCourseToWaitingList(courseCode, indexNum);
            s.getTimeTable().addLesson(courseCode, indexNum, lessons, "WAITING");
            i.addStudentToWaitingList(s.getMatricNumber());
            indexList[index_ig] = i;
            c.setIndex(indexList);
        }
        else{
            s.registerForCourse(courseCode, indexNum, c.getAUs());
            s.getTimeTable().addLesson(courseCode, indexNum, lessons, "REGISTERED");
            i.addStudent(s.getMatricNumber());
            indexList[index_ig] = i;
            c.setIndex(indexList);
            c.addStudent(s.getMatricNumber());
        }
        studentList.set(index_student, s);
        courseList.set(index_course, c);
        StudentMgr.writeStudentsToFile(studentList);
        CourseMgr.writeCoursesToFile(courseList);
        return true;
    }
    public static boolean deregisterStudentFromCourse(Student student, String courseCode){
        int index_student = StudentMgr.checkIfStudentExists(student);
        if(index_student == -1) {
            System.out.println("Did not find student record with this username.");
            return false;
        }
        int index_course = CourseMgr.checkIfCourseExists(courseCode);
        if(index_course == -1){
            System.out.println("Did not find course record with this course code.");
            return false;
        }
        List<Object> studentList = FileManipMgr.readObjectsFromFile("student.dat");
        Student s = (Student)(studentList.get(index_student));
        List<Object> courseList = FileManipMgr.readObjectsFromFile("course.dat");
        Course c = (Course)(courseList.get(index_course));
        Integer[] result = s.deregisterFromCourse(c.getCourseCode(), c.getAUs());
        int status = result[0];
        int indexNum = result[1];
        IndexGroup i = null;
        IndexGroup[] indexList = c.getIndexList();
        int count = 0, index_ig = 0;
        for(IndexGroup ig: indexList){
            if(ig.getIndexNumber() == indexNum)
            {
                i = ig;
                index_ig = count;
                break;
            }
            count++;
        }
        String matricNumOfNewStud = i.removeStudent(s.getMatricNumber(), status);
        if(!matricNumOfNewStud.equals("NoWaiting")) {
            Student s1 = new Student(matricNumOfNewStud);
            int newStudentIndex = FileManipMgr.checkIfObjectExists(s1);
            s1 = (Student) studentList.get(newStudentIndex);
            s1.changeWaitingToRegistered(c.getCourseCode(), c.getAUs());
            c.addStudent(matricNumOfNewStud);
            studentList.set(newStudentIndex, s1);
        }
        indexList[index_ig] = i;
        c.setIndex(indexList);
        System.out.println(status);
        if(status == 1)
            c.removeStudent(s.getMatricNumber());
        studentList.set(index_student, s);
        courseList.set(index_course, c);
        StudentMgr.writeStudentsToFile(studentList);
        CourseMgr.writeCoursesToFile(courseList);
        return true;
    }
    public static boolean printCoursesRegistered(Student student){
        student.getTimeTable().printTimeTable();
        System.out.println("Total AUs Registered: " + student.getAUsRegistered());
        return true;
    }
    public static boolean changeIndexGroup(Student student, String courseCode,
                                           int newIndexNum, int oldIndexNum){
        int index_course = CourseMgr.checkIfCourseExists(courseCode);
        if(index_course == -1){
            System.out.println("Did not find course record with this course code.");
            return false;
        }
        List<Object> courseList = FileManipMgr.readObjectsFromFile("course.dat");
        Course c = (Course)(courseList.get(index_course));
        IndexGroup i = null;
        IndexGroup[] indexList = c.getIndexList();
        for(IndexGroup ig: indexList){
            if(ig.getIndexNumber() == newIndexNum)
            {
                i = ig;
                break;
            }
        }
        List<Lesson> lessonList = new ArrayList<>(Arrays.asList(i.getLessons()));
        lessonList.addAll(Arrays.asList(c.getLectureLessons()));
        Lesson[] lessons = new Lesson[lessonList.size()];
        int counter = 0;
        for (Lesson l: lessonList){
            lessons[counter] = l;
            counter++;
        }
        if(!student.getTimeTable().checkForClash(lessons, courseCode)) {
            return false;
        }
        deregisterStudentFromCourse(student, courseCode);
        //System.out.println("Hey");
        registerStudentForCourse(student, courseCode, newIndexNum);
        return true;
    }
    
    public static boolean swapIndex(Student s1, Student s2, int index1, int index2, Course course) throws ClassNotFoundException, NoSuchMethodException, 
    																									InvocationTargetException, IllegalAccessException, IOException {
    	Course course1 = (Course)s1.getcoursesRegistered().get(course);
    	Course course2 = (Course)s2.getcoursesRegistered().get(course);
    	s1.getcoursesRegistered().replace(course, course2);
    	s2.getcoursesRegistered().replace(course, course1);
    	TimeTable temp = s1.getTimeTable();
    	s1.setTimeTable(s2.getTimeTable());
    	s2.setTimeTable(temp);
    	List<Object> objectList = FileManipMgr.readObjectsFromFile("student.dat");
        objectList.set(index1, s1);
        objectList.set(index2, s2);
        FileManipMgr.writeObjectsToFile(objectList, "student.dat");
        return true;
    }
}
