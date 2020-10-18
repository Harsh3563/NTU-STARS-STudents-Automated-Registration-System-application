package Control;

import Entity.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentMgr {
    private static boolean isStudentDatabaseEmpty = true;

    public static boolean checkIfStudentExists(String studentId) {
        Student s = new Student();
        List<Student> studentList= new ArrayList<>();
        try {
            studentList = s.readObjectsFromFile();
        } catch (IOException e) {
            System.out.println("IO Error!");
            e.printStackTrace();
            System.exit(0);
        }
        for (Student student : studentList) {
            if (student.getMatricNumber().equals(studentId))
                return true;
        }
        return false;
    }

    public static boolean addStudent(Student student){
        boolean studentAlreadyExists = checkIfStudentExists(student.getMatricNumber());
        if(studentAlreadyExists){
            System.out.println("Student with this matriculation number already exists. Please enter a correct " +
                    "matriculation number or choose the edit student entry function.");
            return false;
        }
        Student.writeObjectsToFile(student);
        return true;
    }
}
