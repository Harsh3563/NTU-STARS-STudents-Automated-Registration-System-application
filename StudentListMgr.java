package Control;

import Entity.Course;
import Entity.IndexGroup;
import Entity.Student;

import java.util.List;

public class StudentListMgr {
    public static boolean printStudentListByCourse(Course c){
        List<Object> studList = FileManipMgr.readObjectsFromFile("student.dat");
        String[] registeredStudents = c.getRoster();
        int numStudentsRegistered = c.getNumStudentRegistered();
        for(int i=0; i<numStudentsRegistered; ++i){
            Student s = new Student(registeredStudents[i]);
            int index_student = FileManipMgr.checkIfObjectExists(s);
            s = (Student) studList.get(index_student);
            s.displayDetails();
        }
        return true;
    }
    public static boolean printStudentListByIndexGroup(IndexGroup ig){
        List<Object> studList = FileManipMgr.readObjectsFromFile("student.dat");
        String[] registeredStudents = ig.getStudentList();
        int numStudentsRegistered = ig.getNumStudentsRegistered();
        for(int i=0; i<numStudentsRegistered; ++i) {
            Student s = new Student(registeredStudents[i]);
            int index_student = FileManipMgr.checkIfObjectExists(s);
            s = (Student) studList.get(index_student);
            s.displayDetails();
        }
        return true;
    }
}
