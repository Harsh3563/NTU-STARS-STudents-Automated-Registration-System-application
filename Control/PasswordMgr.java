import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
 // this is do allow user to WRITE into a precreated file, if that is not required you can just extract out the read function below and use it 
//for comparison
public class PasswordMgr implements Serializable{

public static void main (String[] args) throws IOException, ClassNotFoundException, NoSuchAlgorithmException{
	PasswordMgr objectIO = new PasswordMgr();
	String smh256,usertext,userdomain;
	Scanner sc=new Scanner(System.in);
		System.out.print("Please enter Username:");			
		usertext=sc.next();		
		System.out.print("Please enter the domain(admin/user):");			
		userdomain=sc.next();
		System.out.print("Please enter the password:");		
		smh256=sc.next();
		smh256=Hashingtable.hexercon(Hashingtable.hasho(smh256));	
    Password student = new Password(usertext,userdomain,smh256);    
   
    FileOutputStream fos = new FileOutputStream("attempt2.dat");
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(student);
    
//this would write the object into file   
    // now we are reading so we use inputstreams instead
    //Read object from file

	try {
		FileInputStream fis = new FileInputStream("attempt2.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
	    Password st=(Password)ois.readObject();
	    System.out.println(st.toString());
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    

}
}


