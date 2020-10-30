package Control;

import Entity.Student;
import jdk.vm.ci.meta.Local;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StudentMgr {
    public static boolean addStudent(Student s){
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
        Class student = Class.forName("Entity.Student");
        Method studentMethod = student.getMethod("downcast", Object.class);
        List<Object> objectList = FileManipMgr.readObjectsFromFile("student.dat");
        for(Object o: objectList){
            studentList.add((Student)o);
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
    public static boolean editStudentAccessPeriod(String matricNo, LocalDate startDate, LocalDate endDate,
       LocalTime startTime, LocalTime endTime) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IOException, InvocationTargetException {
        Student s = new Student(matricNo);
        int index = FileManipMgr.checkIfObjectExists(s);
        if(index == -1){
            System.out.println("Student record does not exist!");
            return false;
        }
        List<Object> objectList = FileManipMgr.readObjectsFromFile("student.dat");
        Student s1 = (Student)objectList.get(index);
        s1.setStartDate(startDate);
        s1.setEndDate(endDate);
        s1.setStartTime(startTime);
        s1.setEndTime(endTime);
        FileManipMgr.editObjectRecord(s1, index);
        return true;
    }
}
