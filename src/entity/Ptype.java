package entity;

public class Ptype {
    private int tId;
    private String tName;

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public Ptype() {
    }

    public Ptype(int tId, String tName) {
        this.tId = tId;
        this.tName = tName;
    }
}
