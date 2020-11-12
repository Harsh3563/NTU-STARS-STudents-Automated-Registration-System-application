package Control;

import Entity.Password;
import Entity.Hashingtable;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.io.Serializable;
// this is do allow user to WRITE into a precreated file, if that is not required you can just extract out the read function below and use it
//for comparison
public class PasswordMgr implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static void main (String[] args) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        PasswordMgr objectIO = new PasswordMgr();
        String smh256,usertext, userdomain;
        Scanner sc=new Scanner(System.in);
        System.out.print("Please enter Username:");
        usertext=sc.next();
        System.out.print("Please enter the domain(admin/user):");
        userdomain=sc.next();
        System.out.print("Please enter the password:");
        smh256=sc.next();
        smh256=Hashingtable.hexercon(Hashingtable.hasho(smh256));
        List<Object> passwordList = new ArrayList<>();
        Password student = new Password(usertext,userdomain,smh256);
        passwordList.add(student);
        sc.close();
        FileManipMgr.writeObjectsToFile(passwordList, "password.dat");
        System.out.println(FileManipMgr.checkIfObjectExists(student));
    }

}
