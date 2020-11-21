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

public class StudentUI {
    public static void StudentApp() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
        String name, matricNumber, nationality, networkUsername, emailID;
        LocalDate startDate, endDate;
        LocalTime startTime, endTime;
        int index, AUsRegistered;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        char gender;
        Student s;
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do{
            try
            {
            System.out.println("Enter the choice according to the following menu:");
            System.out.println("1. Add a student record.\n"
            		+ "2. Display all student records.\n"
                    + "3. Edit Student Access Period\n"
                    + "4. Update Student Details\n"
                    + "5. Change Matriculation Number\n"
                    + "6. Change Name of student\n"
                    + "7. Change Network Username\n"
                    + "8. Change email ID\n"
                    + "9. Change Nationality\n"
                    + "10. Change Gender\n"
                    + "11. Change AUs Registered\n"
                    + "99. Exit\n"
                    + "100. Display every student detail.");
            choice = sc.nextInt();
            }
            catch (Exception e)
                    {
                        System.out.println("Invalid value! Please enter correct value");
                        break;
                    }
            switch(choice){
                case 1: 
                    try
                    {
                    System.out.println("Enter the matriculation number of the student.");
                    matricNumber = sc.next();
                    System.out.println("Enter the name of the student.");
                    name = sc.next();
                    System.out.println("Enter the email ID.");
                    emailID = sc.next();
                    System.out.println("Enter the nationality of the student.");
                    nationality = sc.next();
                    System.out.println("Enter the gender of the student.");
                    gender = sc.next().charAt(0);
                    System.out.println("Enter the network username.");
                    networkUsername = sc.next();
                    s = new Student(name, matricNumber, emailID, nationality, gender, 0, networkUsername);
                    StudentMgr.addStudent(s);
                    }
                    catch (Exception e)
                    {
                        System.out.println("Invalid value! Please enter correct value");
                        break;
                    }
                    break;
                case 2: StudentMgr.displayAllStudentRecords();
                    break;
                case 3: 
                        try
                        {
                        System.out.println("Enter the matriculation number of the student.");
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
                        }
                        catch (Exception e)
                        {
                            System.out.println("Invalid value! Please enter correct value");
                            break;
                        }
                    break;
                case 4: 
                        try
                        {
                        System.out.println("Enter the matriculation number.");
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
                        }
                        catch (Exception e)
                        {
                            System.out.println("Invalid value! Please enter correct value");
                            break;
                        }
                        break;
                case 5://Change Matric Number
                     try
                     {
                	 System.out.println("Enter the matriculation number.");
                     matricNumber = sc.next();
                     index = StudentMgr.checkIfStudentExists(new Student(matricNumber));
                     if(index == -1){
                         System.out.println("Student record does not exist. Please ensure you have" +
                                 "entered the correct matriculation number.");
                         break;
                     }
                     System.out.print("Enter new Matriculation number: ");
                     matricNumber = sc.next();
                     StudentMgr.changeMatricNumber(matricNumber, index);
                     }
                    catch (Exception e)
                    {
                        System.out.println("Invalid value! Please enter correct value");
                        break;
                    }
                	break;
                case 6://Change Name
                    try
                    {
                	System.out.println("Enter the matriculation number.");
                    matricNumber = sc.next();
                    index = StudentMgr.checkIfStudentExists(new Student(matricNumber));
                    if(index == -1){
                        System.out.println("Student record does not exist. Please ensure you have" +
                                "entered the correct matriculation number.");
                        break;
                    }
                    System.out.print("Enter new name of student: ");
                    name = sc.next();
                    StudentMgr.changeName(name, index);
                    }
                    catch (Exception e)
                    {
                        System.out.println("Invalid value! Please enter correct value");
                        break;
                    }
                    break;
                case 7:
                    try
                    {
                    //Change Network Username
                	System.out.println("Enter the matriculation number.");
                    matricNumber = sc.next();
                    index = StudentMgr.checkIfStudentExists(new Student(matricNumber));
                    if(index == -1){
                        System.out.println("Student record does not exist. Please ensure you have" +
                                "entered the correct matriculation number.");
                        break;
                    }
                    System.out.print("Enter the new Network Username: ");
                    networkUsername = sc.next();
                    StudentMgr.changeNetworkUsername(networkUsername, index);
                    }
                    catch (Exception e)
                    {
                        System.out.println("Invalid value! Please enter correct value");
                        break;
                    }
                    break;
                case 8: 
                    try
                    {
                    //Change email ID
                	System.out.println("Enter the matriculation number.");
                    matricNumber = sc.next();
                    index = StudentMgr.checkIfStudentExists(new Student(matricNumber));
                    if(index == -1){
                        System.out.println("Student record does not exist. Please ensure you have" +
                                "entered the correct matriculation number.");
                        break;
                    }
                    System.out.print("Enter new email ID: ");
                    emailID = sc.next();
                    StudentMgr.changeEmailID(emailID, index);
                    }
                    catch (Exception e)
                    {
                        System.out.println("Invalid value! Please enter correct value");
                        break;
                    }
                    break;
                case 9:
                    try
                    {
                    //Change Nationality
                	System.out.println("Enter the matriculation number.");
                    matricNumber = sc.next();
                    index = StudentMgr.checkIfStudentExists(new Student(matricNumber));
                    if(index == -1){
                        System.out.println("Student record does not exist. Please ensure you have" +
                                "entered the correct matriculation number.");
                        break;
                    }
                    System.out.print("Enter new nationality: ");
                    nationality = sc.next();
                    StudentMgr.changeNationality(nationality, index);
                    }
                    catch (Exception e)
                    {
                        System.out.println("Invalid value! Please enter correct value");
                        break;
                    }
                    break;
                case 10:
                    try
                    {
                    //Change Gender
                	System.out.println("Enter the matriculation number.");
                    matricNumber = sc.next();
                    index = StudentMgr.checkIfStudentExists(new Student(matricNumber));
                    if(index == -1){
                        System.out.println("Student record does not exist. Please ensure you have" +
                                "entered the correct matriculation number.");
                        break;
                    }
                    System.out.print("Enter new gender: ");
                    gender = sc.next().charAt(0);
                    StudentMgr.changeGender(gender, index);
                    }
                    catch (Exception e)
                    {
                        System.out.println("Invalid value! Please enter correct value");
                        break;
                    }
                    break;
                case 11:
                    try
                    {
                    //Change AU registered
                	System.out.println("Enter the matriculation number.");
                    matricNumber = sc.next();
                    index = StudentMgr.checkIfStudentExists(new Student(matricNumber));
                    if(index == -1){
                        System.out.println("Student record does not exist. Please ensure you have" +
                                "entered the correct matriculation number.");
                        break;
                    }
                    System.out.print("Enter new AU registered: ");
                    AUsRegistered = sc.nextInt();
                    StudentMgr.changeAUsRegistered(AUsRegistered, index);
                    }
                    catch (Exception e)
                    {
                        System.out.println("Invalid value! Please enter correct value");
                        break;
                    }
                    break;
                case 100: StudentMgr.displayEveryPossibleStudentDetail();
                          break;
                case 99: System.exit(0);
            }
        }while(choice != -1);
    }
}
