package com.yd.ttdms.pojo;

import java.math.BigDecimal;

public class JeecgMonthlyGrowthAnalysis {
    private Integer id;

    private String year;

    private String month;

    private BigDecimal mainIncome;

    private BigDecimal otherIncome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public BigDecimal getMainIncome() {
        return mainIncome;
    }

    public void setMainIncome(BigDecimal mainIncome) {
        this.mainIncome = mainIncome;
    }

    public BigDecimal getOtherIncome() {
        return otherIncome;
    }

    public void setOtherIncome(BigDecimal otherIncome) {
        this.otherIncome = otherIncome;
    }
}