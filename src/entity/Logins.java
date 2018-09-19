package entity;

public class Logins {
    private int lId;
    private String lUser;
    private String lPwd;
    private String lPhone;
    private String lMailbox;

    public String getlUser() {
        return lUser;
    }

    public void setlUser(String lUser) {
        this.lUser = lUser;
    }

    public String getlPwd() {
        return lPwd;
    }

    public void setlPwd(String lPwd) {
        this.lPwd = lPwd;
    }

    public String getlPhone() {
        return lPhone;
    }

    public void setlPhone(String lPhone) {
        this.lPhone = lPhone;
    }

    public String getlMailbox() {
        return lMailbox;
    }

    public void setlMailbox(String lMailbox) {
        this.lMailbox = lMailbox;
    }

    public Logins() {
    }
    public Logins(int lId,String lUser, String lPwd, String lPhone, String lMailbox) {
        this.lId=lId;
        this.lUser = lUser;
        this.lPwd = lPwd;
        this.lPhone = lPhone;
        this.lMailbox = lMailbox;
    }
    public Logins(String lUser, String lPwd, String lPhone, String lMailbox) {
        this.lUser = lUser;
        this.lPwd = lPwd;
        this.lPhone = lPhone;
        this.lMailbox = lMailbox;
    }

    public int getlId() {
        return lId;
    }

    public void setlId(int lId) {
        this.lId = lId;
    }
}
