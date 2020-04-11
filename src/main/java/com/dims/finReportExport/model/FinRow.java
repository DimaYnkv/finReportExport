package com.dims.finReportExport.model;

import java.util.Date;

public class FinRow {


    private String id;
    private Date dealDate;
    private String businessName;
    private Double dealSum;
    private Double chargeSum;
    private String originalCurrency;
    private String info;

    public FinRow(){}

    public FinRow(Date dealDate, String businessName, Double dealSum, Double chargeSum, String originalCurrency, String info) {
        this.dealDate = dealDate;
        this.businessName = businessName;
        this.dealSum = dealSum;
        this.chargeSum = chargeSum;
        this.info = info;
    }

    public FinRow(String id, Date dealDate, String businessName, Double dealSum, Double chargeSum, String originalCurrency, String info) {
        this.id = id;
        this.dealDate = dealDate;
        this.businessName = businessName;
        this.dealSum = dealSum;
        this.chargeSum = chargeSum;
        this.info = info;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Double getDealSum() {
        return dealSum;
    }

    public void setDealSum(Double dealSum) {
        this.dealSum = dealSum;
    }

    public Double getChargeSum() {
        return chargeSum;
    }

    public void setChargeSum(Double chargeSum) {
        this.chargeSum = chargeSum;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(String originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    @Override
    public String toString() {
        return "\n" +
                "FinRow{" +
                "id=" + id +
                ", dealDate=" + dealDate +
                ", businessName='" + businessName + '\'' +
                ", dealSum=" + dealSum +
                ", chargeSum=" + chargeSum +
                ", originalCurrency='" + originalCurrency + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
