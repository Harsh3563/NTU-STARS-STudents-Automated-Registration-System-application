package Control;


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
     *read whole list then add new key in write so it constantly adds into the list
     *
     */
    private static final long serialVersionUID = 1L;
    public static void main (String[] args) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        PasswordMgr objectIO = new PasswordMgr();
        Scanner sc=new Scanner(System.in);
        
        String smh256,usertext="o", userdomain;
        int i=0;
        List<Object> passwordList = new ArrayList<>();
        
        while(true) {
        System.out.print("Please enter Username:");
        usertext=sc.next();
        if(usertext.equals("end")) {
        	break;
        }
        System.out.print("Please enter the domain(admin/user):");
        userdomain=sc.next();
        System.out.print("Please enter the password:");
        smh256=sc.next();
        smh256=Hashingtable.hexercon(Hashingtable.hasho(smh256));        
        Password student = new Password(usertext,userdomain,smh256);
        passwordList.add(student);
        FileManipMgr.writeObjectsToFile(passwordList, "password.dat");
        System.out.println(FileManipMgr.checkIfObjectExists(student));
    }
        sc.close();
    }

}

