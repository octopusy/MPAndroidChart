package com.richard.chart.utils;

import com.github.mikephil.charting.formatter.ValueFormatter;

/**
 * @project：agentpos
 * @author：- richard on 2020/7/17 0017 11:31
 * @email：583206137@qq.com
 */
public class MyDataAxisValueFormatter extends ValueFormatter {

    private boolean dottNum;

    public MyDataAxisValueFormatter(boolean dottNum) {
        this.dottNum = dottNum;
    }

    @Override
    public String getFormattedValue(float value) {
        String number = value + "";
        try {
            if(dottNum) {
                number = "￥" + PublicMethodUtils.formatBigWanAmountNew(number,false);
            } else {
                number = "￥" + PublicMethodUtils.formatBigWanTerminal(number,false);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return number;
    }
}