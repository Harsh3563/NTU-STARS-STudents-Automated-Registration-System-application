package Boundary;

import Control.CourseMgr;
import Control.FileManipMgr;
import Control.PasswordMgr;
import Control.StudentRegistrationMgr;
import Entity.*;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class StudentRegistrationApp {
    public static void main(String args[]) throws NoSuchAlgorithmException {
        int choice, choice1, counter, count_curr_index, index_course, index_password;
        Iterator<Map.Entry<String, Integer>> it;
        HashMap<String, Integer> courses;
        List<Course> courseList;
        Course course;
        IndexGroup[] indexList;
        String chosenCourse, peerUsername, peerPassword;
        Student s = (Student)(FileManipMgr.readObjectsFromFile("student.dat")).get(2);
        System.out.println("Hi " + s.getName());
        do {
            System.out.println("Enter your choice according to the following menu:");
            System.out.println("1. Register for a course.\n2. Check/Print Courses registered.\n" +
                    "3. Deregister From a Course\n4. Change Index Number\n" +
                    "5. Swap Index Group with Peer\n6. Exit");
            s = (Student)(FileManipMgr.readObjectsFromFile("student.dat")).get(2);
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    courseList = CourseMgr.obtainCourseList();
                    System.out.println("Choose among the following courses (by inputting the respective " +
                            "choice number.");
                    System.out.println("========================================");
                    System.out.printf("%-10s %-15s %-7s\n", "Course Title", "Course Code", "No. of AUs");
                    System.out.println("========================================");
                    counter = 0;
                    for(Course c: courseList){
                        System.out.printf((counter + 1) + ". %-10s %-15s %-7s\n", c.getCourseTitle(),
                                c.getCourseCode(), c.getAUs());
                        counter++;
                    }
                    choice1 = sc.nextInt();
                    course = courseList.get(choice1 - 1);
                    indexList = course.getIndexList();
                    counter = 0;
                    System.out.println("Choose among the following courses (by inputting the respective " +
                            "choice number.");
                    System.out.println("============================");
                    System.out.printf("%-11s %-7s %-7s\n", "Index Group", "Vacancy", "Waiting");
                    System.out.println("============================");
                    for(IndexGroup i: indexList){
                        System.out.print((counter + 1) + ". ");
                        i.printIndexDetails();
                        counter++;
                    }
                    choice1 = sc.nextInt();
                    IndexGroup indexGroup = indexList[choice1 - 1];
                    StudentRegistrationMgr.registerStudentForCourse(s, course.getCourseCode(),
                            indexGroup.getIndexNumber());
                    break;
                case 2:
                    StudentRegistrationMgr.printCoursesRegistered(s);
                    break;
                case 3:
                    System.out.println("Here are the courses for which you are registered or are on " +
                            "waiting list. Choose amongst them.");
                    courses = s.getCourses();
                    it = courses.entrySet().iterator();
                    counter = 1;
                    choice1 = -1;
                    while(choice1 == -1) {
                        while (it.hasNext()) {
                            Map.Entry<String, Integer> l = it.next();
                            System.out.println(counter + ". " + l.getKey());
                            counter++;
                        }
                        choice1 = sc.nextInt();
                        if (choice1 < 1 || choice1 > counter) {
                            System.out.println("Please enter a valid option.");
                            choice1 = -1;
                        }
                    }
                    String courseToDeregister = (String) courses.keySet().toArray()[choice1 - 1];
                    System.out.println(StudentRegistrationMgr.deregisterStudentFromCourse(s, courseToDeregister));
                    break;
                case 4: System.out.println("Here are the courses for which you are registered or are on " +
                        "waiting list. Choose amongst them.");
                        courses = s.getCourses();
                        it = courses.entrySet().iterator();
                        counter = 1;
                        choice1 = -1;
                        while(choice1 == -1) {
                            while (it.hasNext()) {
                                Map.Entry<String, Integer> l = it.next();
                                System.out.println(counter + ". " + l.getKey());
                                counter++;
                            }
                            choice1 = sc.nextInt();
                            if (choice1 < 1 || choice1 > counter) {
                                System.out.println("Please enter a valid option.");
                                choice1 = -1;
                            }
                        }
                        chosenCourse = (String) courses.keySet().toArray()[choice1 - 1];
                        System.out.println(chosenCourse);
                        course = new Course(chosenCourse);
                        index_course = FileManipMgr.checkIfObjectExists(course);
                        if(index_course == -1) {
                            System.out.println("Error occurred");
                            break;
                        }
                        courseList = CourseMgr.obtainCourseList();
                        course = courseList.get(index_course);
                        count_curr_index = 0;
                        int oldIndexNum = courses.get(course.getCourseCode());
                        System.out.println("Your current index number: " + oldIndexNum);
                        indexList = course.getIndexList();
                        counter = 1;
                        System.out.println("Choose among the following other index numbers.");
                        for(IndexGroup i: indexList) {
                            if(i.getIndexNumber() == oldIndexNum) {
                                count_curr_index = counter;
                                continue;
                            }
                            System.out.println(counter + ". " + i.getIndexNumber() + "\t" + i.getVacancy()
                                    + "\t" + i.getNumStudentsWaiting());
                            counter++;
                        }
                        choice1 = sc.nextInt();
                        if(choice1 >= count_curr_index)
                            choice1++;
                        IndexGroup i = indexList[choice1 - 1];
                        StudentRegistrationMgr.changeIndexGroup(s, course.getCourseCode(),
                                i.getIndexNumber(), oldIndexNum);
                        break;
                case 5: System.out.println("Here are the courses you are registered for.");
                        courses = s.getCoursesRegistered();
                        it = courses.entrySet().iterator();
                        counter = 1;
                        choice1 = -1;
                        while(choice1 == -1){
                            while (it.hasNext()) {
                                Map.Entry<String, Integer> l = it.next();
                                System.out.println(counter + ". " + l.getKey());
                                counter++;
                            }
                            choice1 = sc.nextInt();
                            if (choice1 < 1 || choice1 > counter) {
                                System.out.println("Please enter a valid option.");
                                choice1 = -1;
                            }
                        }
                        chosenCourse = (String) courses.keySet().toArray()[choice1 - 1];
                        course = new Course(chosenCourse);
                        index_course = FileManipMgr.checkIfObjectExists(course);
                        if(index_course == -1) {
                            System.out.println("Error occurred");
                            break;
                        }
                        courseList = CourseMgr.obtainCourseList();
                        course = courseList.get(index_course);
                        System.out.println("Please enter peer's username.");
                        peerUsername = sc.next();
                        System.out.println("Please enter peer's password.");
                        peerPassword = sc.next();
                        Password password = new Password(peerUsername, "student",
                                Hashingtable.hexercon(Hashingtable.hasho(peerPassword)));
                        index_password = PasswordMgr.validatePassword(password);
                        if(index_password == -1){
                            System.out.println("The peer has entered the wrong credentials!");
                            break;
                        }
                        Student peer = PasswordMgr.retrieveStudentRecord(password);
                        if(peer == null)
                            System.out.println("Error while finding student by username.");
                        StudentRegistrationMgr.swapIndexGroupWithPeer(s, peer, course.getCourseCode());
                        break;
                case 6:
                    System.exit(0);
                default: System.out.println("Please enter a correct option.");
            }
        }while(choice != -1);
    }
}
