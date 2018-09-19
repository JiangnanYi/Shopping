package entity;

public class Sites {
    private int sId;
    private int lId;
    private String sName;
    private String  sPhone;
    private String sLocation;

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public int getlId() {
        return lId;
    }

    public void setlId(int lId) {
        this.lId = lId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPhone() {
        return sPhone;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    public String getsLocation() {
        return sLocation;
    }

    public void setsLocation(String sLocation) {
        this.sLocation = sLocation;
    }

    public Sites() {
    }

    public Sites(int sId, int lId, String sName, String sPhone, String sLocation) {
        this.sId = sId;
        this.lId = lId;
        this.sName = sName;
        this.sPhone = sPhone;
        this.sLocation = sLocation;
    }
    public Sites(int lId, String sName, String sPhone, String sLocation) {
        this.lId = lId;
        this.sName = sName;
        this.sPhone = sPhone;
        this.sLocation = sLocation;
    }
}
