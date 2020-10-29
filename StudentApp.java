package Boundary;

import Control.FileManipMgr;
import Control.StudentMgr;
import Entity.Student;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class StudentApp {
    public static void main(String args[]) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String name, matricNumber, nationality;
        LocalDate startDate, endDate;
        LocalTime startTime, endTime;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        char gender;
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.println("Enter the choice according to the following menu:");
            System.out.println("1. Add a student record.\n2. Display all student records.\n6. Exit");
            choice = sc.nextInt();
            switch(choice){
                case 1: System.out.println("Enter the matriculation number of the student.");
                    matricNumber = sc.next();
                    System.out.println("Enter the name of the student.");
                    name = sc.next();
                    System.out.println("Enter the nationality of the student.");
                    nationality = sc.next();
                    System.out.println("Enter the gender of the student.");
                    gender = sc.next().charAt(0);
                    Student s = new Student(name, matricNumber, nationality, gender, 0);
                    StudentMgr.addStudent(s);
                    break;
                case 2: StudentMgr.displayAllStudentRecords();
                    break;
                case 6: System.exit(0);
            }
        }while(choice != -1);
    }
}
