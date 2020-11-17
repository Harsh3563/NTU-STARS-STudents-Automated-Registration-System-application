package Control;

import Entity.Admin;
import Entity.Password;
import Entity.Student;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

//import java.io.FileOutputStream;
//import java.io.ObjectOutputStream;
// this is do allow user to WRITE into a precreated file, if that is not required you can just extract out the read function below and use it
//for comparison
public class PasswordMgr implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static void addPassword (Password password) throws IOException, ClassNotFoundException,
            NoSuchAlgorithmException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException {
        List<Object> passwordList = FileManipMgr.readObjectsFromFile("password.dat");
        passwordList.add(password);
        FileManipMgr.writeObjectsToFile(passwordList, "password.dat");
        System.out.println(FileManipMgr.checkIfObjectExists(password));
    }
    public static int validatePassword(Password password){
        return FileManipMgr.checkIfObjectExists(password);
    }
    public static Student retrieveStudentRecord(Password password){
        List<Object> studentList = FileManipMgr.readObjectsFromFile("student.dat");
        for(Object o: studentList){
            if(((Student)o).getNetworkUsername().equals(password.getUsername()))
                return (Student)o;
        }
        return null;
    }
    public static Admin retrieveAdminRecord(Password password){
        Admin a = new Admin(password.getUsername());
        int index_admin = FileManipMgr.checkIfObjectExists(a);
        List<Object> adminList = FileManipMgr.readObjectsFromFile("admin.dat");
        a = (Admin) adminList.get(index_admin);
        return a;
    }
}
