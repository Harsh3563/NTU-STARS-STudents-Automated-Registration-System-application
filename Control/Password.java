import java.io.Serializable;
public class Password implements  Serializable{
	
String username;
String domain;
String encryptedPassword;

Password(){}

Password (String user,String place,String hex){	
this.domain=place;
this.username=user;
this.encryptedPassword=hex;
}
//only password is needed
@Override
public String toString() {
	return encryptedPassword;
}
}
