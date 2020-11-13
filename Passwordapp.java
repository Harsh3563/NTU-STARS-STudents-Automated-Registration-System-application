import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Passwordapp {
public static void Passwordenable()throws ClassNotFoundException, NoSuchAlgorithmException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
	FileManipMgr fmile=new FileManipMgr();
	Scanner scd=new Scanner(System.in);
	String Name,Domain,Password1,enPassword;
	System.out.println("Please enter Username: ");
	Name=scd.next();
	System.out.println("Please enter Domain: ");
	Domain=scd.next();
	System.out.println("Please enter Password: ");
	Password1=scd.next();
	enPassword=Hashingtable.hexercon(Hashingtable.hasho(Password1)); 
	Password login=new Password(Name,Domain,enPassword);
	List<Object> passwordList = new ArrayList<>();
	passwordList=fmile.readObjectsFromFile(fmile.checkTypeOfObject(login));
	for(int i=0;i<passwordList.size();i++) {
	if(passwordList.get(i).equals(login)) {	
		System.out.println("you may enter");
		break;
	}
	else {
		if(i==passwordList.size()-1) {
			System.out.println("Error please try again");
		}
	}
	}

	
}
}
