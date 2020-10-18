package Boundary;

import Control.StudentMgr;
import Entity.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentApp {
    public static void main(String args[]){
        String name, matricNumber, nationality;
        char gender;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the matriculation number of the student.");
        matricNumber = sc.next();
        System.out.println("Enter the name of the student.");
        name = sc.next();
        System.out.println("Enter the nationality of the student.");
        nationality = sc.next();
        System.out.println("Enter the gender of the student.");
        gender = sc.next().charAt(0);
        Student s = new Student(name, matricNumber, nationality, gender);
        List<Student> studentList = new ArrayList<>();
        studentList = Student.readObjectsFromFile();
        for (Student student: studentList)
            System.out.println("Matric no. " + student.getMatricNumber());
        StudentMgr.addStudent(s);
    }
}
