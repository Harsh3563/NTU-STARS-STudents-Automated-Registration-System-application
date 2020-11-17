package Control;

import Entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentRegistrationMgr {
    public static boolean registerStudentForCourse(Student student, String courseCode, int indexNum) {
        //int index_student = findStudentByMatricNum(username);
        int index_student = StudentMgr.checkIfStudentExists(student);
        if (index_student == -1) {
            System.out.println("Did not find student record with this username.");
            return false;
        }
        int index_course = CourseMgr.checkIfCourseExists(courseCode);
        if (index_course == -1) {
            System.out.println("Did not find course record with this course code.");
            return false;
        }
        List<Object> studentList = FileManipMgr.readObjectsFromFile("student.dat");
        Student s = (Student) (studentList.get(index_student));
        List<Object> courseList = FileManipMgr.readObjectsFromFile("course.dat");
        Course c = (Course) (courseList.get(index_course));
        if (s.checkIfAlreadyApplied(c.getCourseCode()))
            return false;
        IndexGroup i = null;
        IndexGroup[] indexList = c.getIndexList();
        int count = 0, index_ig = 0;
        for (IndexGroup ig : indexList) {
            if (ig.getIndexNumber() == indexNum) {
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
        for (Lesson l : lessonList) {
            lessons[counter] = l;
            counter++;
        }
        TimeTable timeTable = s.getTimeTable();
        if ((s.getAUsRegistered() + c.getAUs()) > 21) {
            System.out.println("Note that the course will not be registered immediately even if there are " +
                    "vacancies since registering for this course would go beyond the max AU limit. " +
                    "You will be placed on waiting list." +
                    "Consider dropping a course.");
            toAddToWaitingList = true;
        }
        if (c.getVacancy() == 0) {
            System.out.println("Note that there in no vacancy for this course. You will be placed on " +
                    "waitlist");
            toAddToWaitingList = true;
        } else if (i.getVacancy() == 0) {
            System.out.println("Note that there is no vacancy for this index group. You will be placed " +
                    "on waitlist.");
            toAddToWaitingList = true;
        }
        System.out.println(toAddToWaitingList);
        if (!timeTable.checkForClash(lessons, courseCode))
            return false;
        if (toAddToWaitingList) {
            s.addCourseToWaitingList(courseCode, indexNum);
            s.getTimeTable().addLesson(courseCode, indexNum, lessons, "WAITING");
            i.addStudentToWaitingList(s.getMatricNumber());
            indexList[index_ig] = i;
            c.setIndex(indexList);
        } else {
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

    public static boolean deregisterStudentFromCourse(Student student, String courseCode) {
        int index_student = StudentMgr.checkIfStudentExists(student);
        if (index_student == -1) {
            System.out.println("Did not find student record with this username.");
            return false;
        }
        int index_course = CourseMgr.checkIfCourseExists(courseCode);
        if (index_course == -1) {
            System.out.println("Did not find course record with this course code.");
            return false;
        }
        List<Object> studentList = FileManipMgr.readObjectsFromFile("student.dat");
        Student s = (Student) (studentList.get(index_student));
        List<Object> courseList = FileManipMgr.readObjectsFromFile("course.dat");
        Course c = (Course) (courseList.get(index_course));
        Integer[] result = s.deregisterFromCourse(c.getCourseCode(), c.getAUs());
        int status = result[0];
        int indexNum = result[1];
        IndexGroup i = null;
        IndexGroup[] indexList = c.getIndexList();
        int count = 0, index_ig = 0;
        for (IndexGroup ig : indexList) {
            if (ig.getIndexNumber() == indexNum) {
                i = ig;
                index_ig = count;
                break;
            }
            count++;
        }
        String matricNumOfNewStud = i.removeStudent(s.getMatricNumber(), status);
        if (!matricNumOfNewStud.equals("NoWaiting")) {
            Student s1 = new Student(matricNumOfNewStud);
            int newStudentIndex = FileManipMgr.checkIfObjectExists(s1);
            s1 = (Student) studentList.get(newStudentIndex);
            s1.changeWaitingToRegistered(c.getCourseCode(), c.getAUs());
            c.addStudent(matricNumOfNewStud);
            studentList.set(newStudentIndex, s1);
            NotificationMgr.sendEmail(s1.getEmailID(), courseCode, indexNum);
        }
        indexList[index_ig] = i;
        c.setIndex(indexList);
        System.out.println(status);
        if (status == 1)
            c.removeStudent(s.getMatricNumber());
        studentList.set(index_student, s);
        courseList.set(index_course, c);
        StudentMgr.writeStudentsToFile(studentList);
        CourseMgr.writeCoursesToFile(courseList);
        return true;
    }

    public static boolean printCoursesRegistered(Student student) {
        if(student.getCourses().isEmpty()) {
            System.out.println("You have neither registered nor are on waiting list for any courses!");
            return false;
        }
        student.getTimeTable().printTimeTable();
        System.out.println("Total AUs Registered: " + student.getAUsRegistered());
        return true;
    }

    public static boolean changeIndexGroup(Student student, String courseCode,
                                           int newIndexNum, int oldIndexNum) {
        int index_course = CourseMgr.checkIfCourseExists(courseCode);
        if (index_course == -1) {
            System.out.println("Did not find course record with this course code.");
            return false;
        }
        List<Object> courseList = FileManipMgr.readObjectsFromFile("course.dat");
        Course c = (Course) (courseList.get(index_course));
        IndexGroup i = null;
        IndexGroup[] indexList = c.getIndexList();
        for (IndexGroup ig : indexList) {
            if (ig.getIndexNumber() == newIndexNum) {
                i = ig;
                break;
            }
        }
        List<Lesson> lessonList = new ArrayList<>(Arrays.asList(i.getLessons()));
        lessonList.addAll(Arrays.asList(c.getLectureLessons()));
        Lesson[] lessons = new Lesson[lessonList.size()];
        int counter = 0;
        for (Lesson l : lessonList) {
            lessons[counter] = l;
            counter++;
        }
        if (!student.getTimeTable().checkForClash(lessons, courseCode)) {
            return false;
        }
        deregisterStudentFromCourse(student, courseCode);
        //System.out.println("Hey");
        registerStudentForCourse(student, courseCode, newIndexNum);
        return true;
    }

    public static boolean swapIndexGroupWithPeer(Student student, Student peer, String courseCode) {

        int index_student = StudentMgr.checkIfStudentExists(student);
        if (index_student == -1) {
            System.out.println("Did not find student record with this username.");
            return false;
        }
        int index_peer = StudentMgr.checkIfStudentExists(peer);
        if (index_peer == -1) {
            System.out.println("Did not find student record with this username.");
            return false;
        }
        int index_course = CourseMgr.checkIfCourseExists(courseCode);
        if (index_course == -1) {
            System.out.println("Did not find course record with this course code.");
            return false;
        }
        List<Object> studentList = FileManipMgr.readObjectsFromFile("student.dat");
        List<Object> courseList = FileManipMgr.readObjectsFromFile("course.dat");
        Course c = (Course) (courseList.get(index_course));
        if(!peer.getCoursesRegistered().containsKey(courseCode)){
            System.out.println("The second student is not registered for this course!");
            return false;
        }
        int indexNum1 = student.getCoursesRegistered().get(courseCode);
        int indexNum2 = peer.getCourses().get(courseCode);
        if(indexNum1 == indexNum2){
            System.out.println("You are both already in the same index group!");
            return false;
        }
        IndexGroup i1 = null, i2 = null;
        IndexGroup[] indexList = c.getIndexList();
        for (IndexGroup ig : indexList) {
            if (ig.getIndexNumber() == indexNum1) {
                i1 = ig;
            }
            if(ig.getIndexNumber() == indexNum2){
                i2 = ig;
            }
            if(i1 != null && i2 != null)
                break;
        }
        List<Lesson> lessonList = new ArrayList<>(Arrays.asList(i2.getLessons()));
        lessonList.addAll(Arrays.asList(c.getLectureLessons()));
        Lesson[] lessons1 = new Lesson[lessonList.size()];
        int counter = 0;
        for (Lesson l : lessonList) {
            lessons1[counter] = l;
            counter++;
        }
        if (!student.getTimeTable().checkForClash(lessons1, courseCode)) {
            return false;
        }
        lessonList = new ArrayList<>(Arrays.asList(i1.getLessons()));
        lessonList.addAll(Arrays.asList(c.getLectureLessons()));
        Lesson[] lessons2 = new Lesson[lessonList.size()];
        counter = 0;
        for(Lesson l: lessonList){
            lessons2[counter] = l;
            counter++;
        }
        if (!peer.getTimeTable().checkForClash(lessons2, courseCode)) {
            return false;
        }
        c.swapStudents(student, peer, indexNum1, indexNum2);
        student.changeIndex(courseCode, indexNum2, lessons1);
        peer.changeIndex(courseCode, indexNum1, lessons2);
        studentList.set(index_student, student);
        studentList.set(index_peer, peer);
        courseList.set(index_course, c);
        StudentMgr.writeStudentsToFile(studentList);
        CourseMgr.writeCoursesToFile(courseList);
        return true;
    }
}

