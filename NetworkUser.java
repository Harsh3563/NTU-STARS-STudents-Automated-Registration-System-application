package Entity;

public abstract class NetworkUser {
    private String networkUsername;
    private String name;
    private String emailID;

    protected NetworkUser() { }

    public String getNetworkUsername() {
        return networkUsername;
    }

    public void setNetworkUsername(String networkUsername) {
        this.networkUsername = networkUsername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public abstract boolean equals(Object s);
}
