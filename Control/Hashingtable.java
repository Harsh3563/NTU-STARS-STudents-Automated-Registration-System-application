//this will be the sha 256 encrymption hashing
import java.math.BigInteger;  
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
//standard imports

public class Hashingtable {
	//create an original byte to hexadeimcal converter
	// this is to create an byte array
	public static  byte[] hasho(String inputed) throws NoSuchAlgorithmException {
		//return a byte array
		MessageDigest msg=MessageDigest.getInstance("SHA-256");
		//digest() is hash function <- the hash formula is inside
		//this is the hasher to see changes and alterations
		// will return array of a byte
		return msg.digest(inputed.getBytes(StandardCharsets.UTF_8));
	}
	
	//string to hexadecimal conversion
	public static String hexercon(byte[] sha) {
		// the above array will then be converted using signum and depending on the value
		//it will return a value
		BigInteger num= new BigInteger(1,sha);
		
		//now to convert the original msg to hash
		
		StringBuilder hexor=new StringBuilder(num.toString(16));
		
		//pad zeros
		while(hexor.length()<32) {
			hexor.insert(0,'0');
		}
		return hexor.toString();
	}

}
	
	
	


