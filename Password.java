package Entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
public class Password implements  Serializable{

    private String username;
    private String domain;
    private String encryptedPassword;

    public Password(){}

    public Password (String user,String place,String hex){
        this.domain=place;
        this.username=user;
        this.encryptedPassword=hex;
    }
    //only password is needed
    @Override
    public String toString() {
        return encryptedPassword;
    }
    public boolean equals(Object h){
        Password conv=(Password)h;
        /*Password dat = new Password();
        try {
            FileInputStream fis = new FileInputStream("password.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);

            dat=(Password)ois.readObject();

        } catch (ClassNotFoundException| IOException e) {
            e.printStackTrace();
        }*/
        if(conv.domain.equals(this.domain) && conv.encryptedPassword.equals(this.encryptedPassword) && conv.username.equals(this.username)){
            return true;
        }
        else{
            return false;
        }
    }

    public String getUsername() {
        return username;
    }

    public String getDomain() {
        return domain;
    }
}
