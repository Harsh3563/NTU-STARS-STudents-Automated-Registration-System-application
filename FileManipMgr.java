package Control;

import Entity.Course;
import Entity.IndexGroup;
import Entity.Lesson;
import Entity.Student;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class FileManipMgr {

    /**
     * helper method to determine correct database based on object type
     * @param object
     * @return
     */
    public static String checkTypeOfObject(Object object){
        if(object instanceof Student)
            return "student.dat";
        else if (object instanceof Course)
            return "course.dat";
        else if(object instanceof IndexGroup)
            return "indexgrp.dat";
        return "Error!";
    }

    /**
     * helper method to return an identifying number associated with instance type of an object
     * @param object
     * @return
     */
    public static int convertObjectToType(Object object){
        if(object instanceof Student)
            return 1;
        else if (object instanceof Course)
            return 2;
        else if(object instanceof IndexGroup)
            return 3;
        return -1;
    }

    /**
     * method to check if an object record exists in the database
     * @param object
     * @return
     */
    public static int checkIfObjectExists(Object object){
        String file = checkTypeOfObject(object);
        FileInputStream foStream = null;
        try {
            foStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedInputStream boStream = new BufferedInputStream(foStream);
        ObjectInputStream oiStream = null;
        try {
            oiStream = new ObjectInputStream(boStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            int counter = 0;
            while(true){
                Object inputObject;
                inputObject = oiStream.readObject();
                if(inputObject.equals(object))
                    return counter;
                counter += 1;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * method to read all objects from a file
     * @param file
     * @return
     */
    public static List<Object> readObjectsFromFile(String file){
        FileInputStream foStream = null;
        List<Object> objects = new ArrayList<>();
        try {
            foStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            return objects;
        }
        BufferedInputStream boStream = new BufferedInputStream(foStream);
        ObjectInputStream oiStream = null;
        try {
            oiStream = new ObjectInputStream(boStream);
        }catch(EOFException e){
            System.out.println("File is currently empty!");
            return objects;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try{
            while(true){
                objects.add(oiStream.readObject());
            }
        }catch (EOFException e) {
            System.out.println("Objects successfully read!");
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return objects;
    }

    /**
     * helper method to write a list of objects to the correct file
     * @param objects
     * @param object
     * @param file
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IOException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static boolean writeObjectsToFile(List<Object> objects, Object object, String file) throws
            ClassNotFoundException, NoSuchMethodException, IOException, InvocationTargetException, IllegalAccessException {
        FileOutputStream foStream = null;
        BufferedOutputStream boStream = null;
        ObjectOutputStream ooStream = null;
        try {
            foStream = new FileOutputStream(file);
            boStream = new BufferedOutputStream(foStream);
            ooStream = new ObjectOutputStream(boStream);
        } catch (IOException e) {
            System.out.println("An IO error has occurred.");
            e.printStackTrace();
            return false;
        }
        int requiredType = convertObjectToType(object);
        Class student = Class.forName("Entity.Student");
        Class course = Class.forName("Entity.Course");
        Class indexGroup = Class.forName("Entity.IndexGroup");
        Method studentMethod = student.getMethod("downcast", Object.class);
        Method courseMethod = course.getMethod("downcast", Object.class);
        Method indexMethod = indexGroup.getMethod("downcast", Object.class);
        System.out.println(requiredType);
        for(Object o: objects){
            switch (requiredType){
                case 1: Student s = (Student) studentMethod.invoke(null, o);
                    ooStream.writeObject(s);
                    break;
                case 2: Course c = (Course) courseMethod.invoke(null, o);
                    ooStream.writeObject(c);
                    break;
                case 3: IndexGroup i = (IndexGroup) indexMethod.invoke(null, o);
                    ooStream.writeObject(i);
                    break;
            }
        }
        ooStream.close();
        return true;
    }

    /**
     * method to add an object record to a file
     * @param object
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IOException
     */
    public static boolean addObjectToFile(Object object) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, IOException {
        List<Object> objects = new ArrayList<>();
        String file = checkTypeOfObject(object);
        objects = readObjectsFromFile(file);
        objects.add(object);
        for(Object o: objects){
            System.out.println(o);
        }
        writeObjectsToFile(objects, object, file);
        System.out.println("Records successfully added!");
        return true;
    }

    /**
     * method to edit an object record in a file
     * @param object
     * @param index
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IOException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static boolean editObjectRecord(Object object, int index) throws ClassNotFoundException,
            NoSuchMethodException, IOException, InvocationTargetException, IllegalAccessException {
        List<Object> objects = new ArrayList<>();
        String file = checkTypeOfObject(object);
        objects = readObjectsFromFile(file);
        objects.set(index, object);
        writeObjectsToFile(objects, object, file);
        System.out.println("The record has been successully modified.");
        return true;
    }
}
