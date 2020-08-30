package com.crm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class OrdersLine implements Serializable {
    private Integer oddId;

    private Integer oddOrderId;

    private Integer oddProdId;

    private Integer oddCount;

    private String oddUnit;

    private Long oddPrice;

    private String oddProdName;

    private String odrAddr;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date odrDate;

    private Integer odrStatus;

    private static final long serialVersionUID = 1L;

    public Integer getOddId() {
        return oddId;
    }

    public void setOddId(Integer oddId) {
        this.oddId = oddId;
    }

    public Integer getOddOrderId() {
        return oddOrderId;
    }

    public void setOddOrderId(Integer oddOrderId) {
        this.oddOrderId = oddOrderId;
    }

    public Integer getOddProdId() {
        return oddProdId;
    }

    public void setOddProdId(Integer oddProdId) {
        this.oddProdId = oddProdId;
    }

    public Integer getOddCount() {
        return oddCount;
    }

    public void setOddCount(Integer oddCount) {
        this.oddCount = oddCount;
    }

    public String getOddUnit() {
        return oddUnit;
    }

    public void setOddUnit(String oddUnit) {
        this.oddUnit = oddUnit == null ? null : oddUnit.trim();
    }

    public Long getOddPrice() {
        return oddPrice;
    }

    public void setOddPrice(Long oddPrice) {
        this.oddPrice = oddPrice;
    }

    public String getOddProdName() {
        return oddProdName;
    }

    public void setOddProdName(String oddProdName) {
        this.oddProdName = oddProdName;
    }

    public String getOdrAddr() {
        return odrAddr;
    }

    public void setOdrAddr(String odrAddr) {
        this.odrAddr = odrAddr;
    }

    public Date getOdrDate() {
        return odrDate;
    }

    public void setOdrDate(Date odrDate) {
        this.odrDate = odrDate;
    }

    public Integer getOdrStatus() {
        return odrStatus;
    }

    public void setOdrStatus(Integer odrStatus) {
        this.odrStatus = odrStatus;
    }
}