package Control;

import Entity.Student;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StudentMgr {
    public static boolean addStudent(Student s){
        int index = FileManipMgr.checkIfObjectExists(s);
        if(index != -1){
            System.out.println("Student record already exists. Make sure to enter the correct " +
                    "matriculation number.");
            return false;
        }
        try {
            FileManipMgr.addObjectToFile(s);
            return true;
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean displayAllStudentRecords() throws ClassNotFoundException, NoSuchMethodException{
        List<Student> studentList= new ArrayList<>();
        List<Object> objectList = FileManipMgr.readObjectsFromFile("student.dat");
        for(Object o: objectList){
            studentList.add((Student)o);
        }
        if(studentList.isEmpty()){
            System.out.println("There are no student records.");
            return false;
        }
        System.out.println("*****************************************");
        System.out.printf("%-15s %-7s %-15s\n", "Name", "Gender", "Nationality");
        System.out.println("*****************************************");
        for(Student s: studentList){
            //System.out.println(s);
            s.displayDetails();
        }
        return true;
    }
    public static boolean editStudentAccessPeriod(int index, LocalDate startDate, LocalDate endDate,
       LocalTime startTime, LocalTime endTime) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, IOException, InvocationTargetException {
        List<Object> objectList = FileManipMgr.readObjectsFromFile("student.dat");
        Student s1 = (Student)objectList.get(index);
        s1.setStartDate(startDate);
        s1.setEndDate(endDate);
        s1.setStartTime(startTime);
        s1.setEndTime(endTime);
        objectList.set(index, s1);
        FileManipMgr.writeObjectsToFile(objectList, "student.dat");
        return true;
    }
    public static boolean displayEveryPossibleStudentDetail() throws ClassNotFoundException, NoSuchMethodException {
        List<Student> studentList= new ArrayList<>();
        List<Object> objectList = FileManipMgr.readObjectsFromFile("student.dat");
        for(Object o: objectList){
            studentList.add((Student)o);
        }
        if(studentList.isEmpty()){
            System.out.println("There are no student records.");
            return false;
        }
        for(Student s: studentList){
            //System.out.println(s);
            s.displayEveryDetail();
        }
        return true;
    }

    public static boolean updateStudentDetails (String name, String nationality,
           char gender, int index) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, IOException, InvocationTargetException{

        List<Object> objectList = FileManipMgr.readObjectsFromFile("student.dat");
        Student s1 = (Student)objectList.get(index);
        s1.setName(name);
        s1.setNationality(nationality);
        s1.setGender(gender);
        objectList.set(index, s1);
        FileManipMgr.writeObjectsToFile(objectList, "student.dat");
        return true;
    }
    public static int checkIfStudentExists(Student s){
        return(FileManipMgr.checkIfObjectExists(s));
    }
    public static boolean writeStudentsToFile(List<Object> studentList){
        try {
            FileManipMgr.writeObjectsToFile(studentList, "student.dat");
        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean changeName(String name, int index)throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, IOException, InvocationTargetException {
        List<Object> objectList = FileManipMgr.readObjectsFromFile("student.dat");
        Student s1 = (Student)objectList.get(index);
        s1.setName(name);
        objectList.set(index, s1);
        FileManipMgr.writeObjectsToFile(objectList, "student.dat");
        return true;
    }

    public static boolean changeNetworkUsername(String networkUsername, int index)throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, IOException, InvocationTargetException {
        List<Object> objectList = FileManipMgr.readObjectsFromFile("student.dat");
        Student s1 = (Student)objectList.get(index);
        s1.setNetworkUsername(networkUsername);
        objectList.set(index, s1);
        FileManipMgr.writeObjectsToFile(objectList, "student.dat");
        return true;
    }

    public static boolean changeMatricNumber(String matricNumber, int index)throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, IOException, InvocationTargetException {
        List<Object> objectList = FileManipMgr.readObjectsFromFile("student.dat");
        Student s1 = (Student)objectList.get(index);
        s1.setMatricNumber(matricNumber);
        objectList.set(index, s1);
        FileManipMgr.writeObjectsToFile(objectList, "student.dat");
        return true;
    }

    public static boolean changeEmailID(String emailID, int index)throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, IOException, InvocationTargetException {
        List<Object> objectList = FileManipMgr.readObjectsFromFile("student.dat");
        Student s1 = (Student)objectList.get(index);
        s1.setEmailID(emailID);
        objectList.set(index, s1);
        FileManipMgr.writeObjectsToFile(objectList, "student.dat");
        return true;
    }

    public static boolean changeNationality(String nationality, int index)throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, IOException, InvocationTargetException {
        List<Object> objectList = FileManipMgr.readObjectsFromFile("student.dat");
        Student s1 = (Student)objectList.get(index);
        s1.setNationality(nationality);
        objectList.set(index, s1);
        FileManipMgr.writeObjectsToFile(objectList, "student.dat");
        return true;
    }

    public static boolean changeGender(char gender, int index)throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, IOException, InvocationTargetException {
        List<Object> objectList = FileManipMgr.readObjectsFromFile("student.dat");
        Student s1 = (Student)objectList.get(index);
        s1.setGender(gender);
        objectList.set(index, s1);
        FileManipMgr.writeObjectsToFile(objectList, "student.dat");
        return true;
    }

    public static boolean changeAUsRegistered(int AUsRegistered, int index)throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, IOException, InvocationTargetException {
        List<Object> objectList = FileManipMgr.readObjectsFromFile("student.dat");
        Student s1 = (Student)objectList.get(index);
        s1.setAUsRegistered(AUsRegistered);
        objectList.set(index, s1);
        FileManipMgr.writeObjectsToFile(objectList, "student.dat");
        return true;
    }
}
