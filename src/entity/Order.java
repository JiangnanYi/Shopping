package entity;

import java.math.BigDecimal;

public class Order {
    private int orId;
    private int oId;
    private int sId;
    private int oState;
    private BigDecimal oCount;

    public int getorId() {
        return orId;
    }

    public void setOrIdId(int orIdId) {
        this.orId = orId;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public BigDecimal getoCount() {
        return oCount;
    }

    public void setoCount(BigDecimal oCount) {
        this.oCount = oCount;
    }

    public int getoState() {
        return oState;
    }

    public void setoState(int oState) {
        this.oState = oState;
    }

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }

    public Order() {

    }

    public Order(int orId, int oId, int sId, int oState, BigDecimal oCount) {
        this.orId = orId;
        this.oId = oId;
        this.sId = sId;
        this.oState = oState;
        this.oCount = oCount;
    }

    public Order(int oId, int sId, int oState, BigDecimal oCount) {
        this.oId = oId;
        this.sId = sId;
        this.oState = oState;
        this.oCount = oCount;
    }
}
