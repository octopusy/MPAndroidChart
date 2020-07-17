package com.xxmassdeveloper.mpchartexample.entity;

/**
 * @project：MPAndroidChart
 * @author：- richard on 2020/7/17 0017 11:31
 * @email：zhangyang5547662@126.com
 */
public class BenfitMonthCountModel {

    private String sumAmount = "";
    private String MONTH = "";
    private String countNum = "";

    public String getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(String sumAmount) {
        this.sumAmount = sumAmount;
    }

    public String getMONTH() {
        return MONTH;
    }

    public void setMONTH(String MONTH) {
        this.MONTH = MONTH;
    }

    public String getCountNum() {
        return countNum;
    }

    public void setCountNum(String countNum) {
        this.countNum = countNum;
    }

    @Override
    public String toString() {
        return "BenfitMonthCountModel{" +
                "sumAmount='" + sumAmount + '\'' +
                ", MONTH='" + MONTH + '\'' +
                ", countNum='" + countNum + '\'' +
                '}';
    }
}
