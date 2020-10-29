package Control;

import Entity.Student;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
}
