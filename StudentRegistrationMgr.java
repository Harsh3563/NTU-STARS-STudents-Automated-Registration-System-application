package Control;

import Entity.*;

import java.io.File;
import java.io.ObjectInputStream;
import java.time.LocalTime;
import java.util.*;

public class StudentRegistrationMgr {
    

    public static boolean registerStudentForCourse(Student student, String courseCode, int indexNum){

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
