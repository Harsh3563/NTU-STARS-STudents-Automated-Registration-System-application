package Boundary;

import Control.StudentMgr;
import Entity.Student;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class StudentApp {
    public static void main(String args[]) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
        String name, matricNumber, nationality;
        LocalDate startDate, endDate;
        LocalTime startTime, endTime;
        int index;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        char gender;
        Student s;
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.println("Enter the choice according to the following menu:");
            System.out.println("1. Add a student record.\n2. Display all student records.\n" +
                    "3. Edit Student Access Period\n4. Update Student Details\n" +
                    "6. Exit\n100. Display every student detail.");
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
                    s = new Student(name, matricNumber, nationality, gender, 0);
                    StudentMgr.addStudent(s);
                    break;
                case 2: StudentMgr.displayAllStudentRecords();
                    break;
                case 3: System.out.println("Enter the matriculation number of the student.");
                        matricNumber = sc.next();
                        index = StudentMgr.checkIfStudentExists(new Student(matricNumber));
                        if(index == -1){
                            System.out.println("Student record does not exist. Please ensure you have" +
                                    "entered the correct matriculation number.");
                            break;
                        }
                        try {
                            System.out.println("Enter the start date. (yyyy/MM/dd)");
                            startDate = LocalDate.parse(sc.next(), dateFormatter);
                            System.out.println("Enter the end date. (yyyy/MM/dd)");
                            endDate = LocalDate.parse(sc.next(), dateFormatter);
                            System.out.println("Enter the start time. (HH:mm:ss)");
                            startTime = LocalTime.parse(sc.next(), timeFormatter);
                            System.out.println("Enter the end time. (HH:mm:ss)");
                            endTime = LocalTime.parse(sc.next(), timeFormatter);
                            StudentMgr.editStudentAccessPeriod(index, startDate, endDate, startTime, endTime);
                        } catch (IOException e) {
                            System.out.println("IO Error!");
                            //e.printStackTrace();
                        }catch(DateTimeParseException e){
                            System.out.println("Please enter the date or time correctly.");
                            //e.printStackTrace();
                        }
                    break;
                case 4: System.out.println("Enter the matriculation number.");
                        matricNumber = sc.next();
                        index = StudentMgr.checkIfStudentExists(new Student(matricNumber));
                        if(index == -1){
                            System.out.println("Student record does not exist. Please ensure you have" +
                                    "entered the correct matriculation number.");
                            break;
                        }
                        System.out.println("Enter the new student details.");
                        System.out.println("Enter the name of the student.");
                        name = sc.next();
                        System.out.println("Enter the nationality of the student.");
                        nationality = sc.next();
                        System.out.println("Enter the gender of the student.");
                        gender = sc.next().charAt(0);
                        StudentMgr.updateStudentDetails(name, nationality, gender, index);
                        break;
                case 100: StudentMgr.displayEveryPossibleStudentDetail();
                          break;
                case 6: System.exit(0);
            }
        }while(choice != -1);
    }
}
