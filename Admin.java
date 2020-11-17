package Entity;

import java.io.Serializable;

public class Admin extends NetworkUser implements Serializable {
    private String networkUsername;
    private String name;
    private String emailID;
    public Admin(String networkUsername, String name, String emailID){
        this.networkUsername = networkUsername;
        this.name = name;
        this.emailID = emailID;
    }

    public Admin(String networkUsername){
        this.networkUsername = networkUsername;
    }

    @Override
    public String getNetworkUsername() {
        return networkUsername;
    }

    @Override
    public void setNetworkUsername(String networkUsername) {
        this.networkUsername = networkUsername;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmailID() {
        return emailID;
    }

    @Override
    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public boolean equals(Object a){
        return this.networkUsername.equals(((Admin) a).getNetworkUsername());
    }
}
