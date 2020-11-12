package Control;

import Entity.*;

import java.io.File;
import java.io.ObjectInputStream;
import java.time.LocalTime;
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
        }
        List<Object> studentList = FileManipMgr.readObjectsFromFile("student.dat");
        Student s = (Student)(studentList.get(index_student));
        List<Object> courseList = FileManipMgr.readObjectsFromFile("course.dat");
        Course c = (Course)(courseList.get(index_course));
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
        if(!timeTable.checkForClash(lessons))
            return false;
        if(toAddToWaitingList){
            s.addCourseToWaitingList(courseCode, indexNum);
            s.getTimeTable().addLesson(courseCode, indexNum, lessons);
            i.addStudentToWaitingList(s.getMatricNumber());
            indexList[index_ig] = i;
            c.setIndex(indexList);
        }
        else{
            s.registerForCourse(courseCode, indexNum, c.getAUs());
            s.getTimeTable().addLesson(courseCode, indexNum, lessons);
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
}