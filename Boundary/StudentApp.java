package Boundary;

import Control.StudentMgr;
import Entity.Student;

import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentApp {
    public static void main(String args[]){
        String name, matricNumber, nationality;
        LocalDate startDate, endDate;
        LocalTime startTime, endTime;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        char gender;
        int choice;
        do{
            System.out.println("Choose the option according to the following menu:");
            System.out.println("1. Edit student access period.");
            System.out.println("2. Add a student");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice){
                case 1: System.out.println("Enter the matriculation number of the student.");
                        matricNumber = sc.next();
                        System.out.println("Enter the start date. (yyyy/MM/dd)");
                        startDate = LocalDate.parse(sc.next(), dateFormatter);
                        System.out.println("Enter the end date. (yyyy/MM/dd)");
                        endDate = LocalDate.parse(sc.next(), dateFormatter);
                        System.out.println("Enter the start time. (HH:mm:ss)");
                        startTime = LocalTime.parse(sc.next(), timeFormatter);
                        System.out.println("Enter the end time. (HH:mm:ss)");
                        endTime = LocalTime.parse(sc.next(), timeFormatter);
                        StudentMgr.editStudentAccessPeriod(matricNumber, startDate, endDate, startTime, endTime);
                        break;
                case 2: System.out.println("Enter the matriculation number of the student.");
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
                        break;
            }

        }while(choice != -1);

    }
}
