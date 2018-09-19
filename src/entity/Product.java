package entity;

import java.math.BigDecimal;

public class Product {
    private int pId;
    private String pName;
    private int tId;
    private BigDecimal pPrice;
    private String pPhoto;
    private String pRemark;
    private int pState;

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public BigDecimal getpPrice() {
        return pPrice;
    }

    public void setpPrice(BigDecimal pPrice) {
        this.pPrice = pPrice;
    }

    public String getpPhoto() {
        return pPhoto;
    }

    public void setpPhoto(String pPhoto) {
        this.pPhoto = pPhoto;
    }

    public String getpRemark() {
        return pRemark;
    }

    public void setpRemark(String pRemark) {
        this.pRemark = pRemark;
    }

    public int getpState() {
        return pState;
    }

    public void setpState(int pState) {
        this.pState = pState;
    }

    public Product() {

    }
    public Product(int pId, String pName, int tId, BigDecimal pPrice, String pPhoto, String pRemark, int pState) {
        this.pId = pId;
        this.pName = pName;
        this.tId = tId;
        this.pPrice = pPrice;
        this.pPhoto = pPhoto;
        this.pRemark = pRemark;
        this.pState = pState;
    }
}
