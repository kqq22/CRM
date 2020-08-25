package com.crm.entity;

import java.io.Serializable;

public class Storage implements Serializable {
    private Integer stkId;

    private Integer stkProdId;

    private String stkWarehouse;

    private String stkWare;

    private Integer stkCount;

    private String stkMemo;

    private String prodName;

    private static final long serialVersionUID = 1L;

    public Integer getStkId() {
        return stkId;
    }

    public void setStkId(Integer stkId) {
        this.stkId = stkId;
    }

    public Integer getStkProdId() {
        return stkProdId;
    }

    public void setStkProdId(Integer stkProdId) {
        this.stkProdId = stkProdId;
    }

    public String getStkWarehouse() {
        return stkWarehouse;
    }

    public void setStkWarehouse(String stkWarehouse) {
        this.stkWarehouse = stkWarehouse == null ? null : stkWarehouse.trim();
    }

    public String getStkWare() {
        return stkWare;
    }

    public void setStkWare(String stkWare) {
        this.stkWare = stkWare == null ? null : stkWare.trim();
    }

    public Integer getStkCount() {
        return stkCount;
    }

    public void setStkCount(Integer stkCount) {
        this.stkCount = stkCount;
    }

    public String getStkMemo() {
        return stkMemo;
    }

    public void setStkMemo(String stkMemo) {
        this.stkMemo = stkMemo == null ? null : stkMemo.trim();
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Storage(Integer stkId, Integer stkProdId, String stkWarehouse, String stkWare, Integer stkCount, String stkMemo) {
        this.stkId = stkId;
        this.stkProdId = stkProdId;
        this.stkWarehouse = stkWarehouse;
        this.stkWare = stkWare;
        this.stkCount = stkCount;
        this.stkMemo = stkMemo;
    }

    public Storage(String stkWarehouse, String prodName) {
        this.stkWarehouse = stkWarehouse;
        this.prodName = prodName;
    }

    public Storage() {
    }
}