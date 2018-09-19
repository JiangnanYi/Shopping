package entity;

import java.math.BigDecimal;

public class POtion {
    private int pId;
    private int lId;
    private int oId;
    private String pPhoto;
    private String pName;
    private BigDecimal pPrice;
    private int buy_num;
    private BigDecimal oCount;

    public String getpPhoto() {
        return pPhoto;
    }

    public void setpPhoto(String pPhoto) {
        this.pPhoto = pPhoto;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public BigDecimal getpPrice() {
        return pPrice;
    }

    public void setpPrice(BigDecimal pPrice) {
        this.pPrice = pPrice;
    }

    public int getBuy_num() {
        return buy_num;
    }

    public void setBuy_num(int buy_num) {
        this.buy_num = buy_num;
    }

    public BigDecimal getoCount() {
        return oCount;
    }

    public void setoCount(BigDecimal oCount) {
        this.oCount = oCount;
    }

    public POtion(int oId, String pPhoto, String pName, BigDecimal pPrice, int buy_num, BigDecimal oCount) {
        this.oId = oId;
        this.pPhoto = pPhoto;
        this.pName = pName;
        this.pPrice = pPrice;
        this.buy_num = buy_num;
        this.oCount = oCount;
    }

    public POtion(int oId, String pPhoto, String pName, BigDecimal pPrice, int buy_num) {
        this.oId = oId;
        this.pPhoto = pPhoto;
        this.pName = pName;
        this.pPrice = pPrice;
        this.buy_num = buy_num;
    }

    public POtion(int pId, int lId, int buy_num) {
        this.pId = pId;
        this.lId = lId;
        this.buy_num = buy_num;
    }

    public POtion() {
    }

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
}
