package coderealm;

public class UserData {
    private  String Un;
    private  String pn;
    private  String UID;

    public UserData(String Un, String pn, String UID) {
        this.Un = Un;
        this.pn = pn;
        this.UID = UID;
    }

    public UserData() {
    }

    public String getUn() {
        return Un;
    }

    public void setUn(String Un) {
        this.Un = Un;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
