package entity;

import java.math.BigDecimal;

public class Option {
    private int oId;
    private int pId;
    private int lId;
    private int oState;
    private BigDecimal buy_num;
    private BigDecimal oCount;

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getlId() {
        return lId;
    }

    public void setlId(int lId) {
        this.lId = lId;
    }

    public int getoState() {
        return oState;
    }

    public void setoState(int oState) {
        this.oState = oState;
    }

    public BigDecimal getBuy_num() {
        return buy_num;
    }

    public void setBuy_num(BigDecimal buy_num) {
        this.buy_num = buy_num;
    }

    public BigDecimal getoCount() {
        return oCount;
    }

    public void setoCount(BigDecimal oCount) {
        this.oCount = oCount;
    }

    public Option() {

    }

    public Option(int oId, int pId, int lId,int oState, BigDecimal buy_num, BigDecimal oCount) {
        this.oId = oId;
        this.pId = pId;
        this.lId = lId;
        this.oState = oState;
        this.buy_num = buy_num;
        this.oCount = oCount;
    }
    public Option(int pId, int lId,BigDecimal buy_num) {
        this.pId = pId;
        this.lId = lId;
        this.buy_num = buy_num;
    }
}
