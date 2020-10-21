package Control;

import Entity.Student;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StudentMgr {
    public static boolean isStudentDatabaseEmpty = true;
    public static int checkIfStudentExists(String studentId) {
        List<Student> studentList = new ArrayList<>();
        studentList = Student.readObjectsFromFile();
        int count = 0;
        for (Student student : studentList) {
            if (student.getMatricNumber().equals(studentId)) {
                System.out.println(studentId + "" + student.getMatricNumber());
                return count;
            }
            count += 1;
        }
        return -1;
    }

    public static boolean addStudent(Student student){
        int studentAlreadyExists = checkIfStudentExists(student.getMatricNumber());
        System.out.println("Hello" + studentAlreadyExists);
        if(studentAlreadyExists != -1){
            System.out.println("Student with this matriculation number already exists. Please enter a correct " +
                    "matriculation number or choose the edit student entry function.");
            return false;
        }
        Student.writeObjectsToFile(student);
        return true;
    }
    public static boolean editStudentAccessPeriod(String matricNumber, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime){
        int studentIndex = checkIfStudentExists(matricNumber);
        if(studentIndex == -1){
            System.out.println("The student record does not exist in the database. Please ensure you have " +
                    "entered the correct matriculation number.");
            return false;
        }
        List<Student> studentList = new ArrayList<>();
        studentList = Student.readObjectsFromFile();
        Student s = studentList.get(studentIndex);
        s.setStartDate(startDate);
        s.setEndDate(endDate);
        s.setStartTime(startTime);
        s.setEndTime(endTime);
        studentList.set(studentIndex, s);
        System.out.println("Student access period has been successfully edited.");
        return true;
    }
    public static void displayAllStudents(){
        List<Student> studentList = new ArrayList<>();
        studentList = Student.readObjectsFromFile();
        System.out.println("*****************************************");
        System.out.printf("%-15s %-7s %-15s\n", "Name", "Gender", "Nationality");
        System.out.println("*****************************************");
        for (Student student: studentList) {
            student.printStudentDetails();
        }
    }
}
