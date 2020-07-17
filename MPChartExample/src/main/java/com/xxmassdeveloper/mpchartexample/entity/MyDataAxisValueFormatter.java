package com.xxmassdeveloper.mpchartexample.entity;

import com.github.mikephil.charting.formatter.ValueFormatter;

/**
 * @project：agentpos
 * @author：- zhangh on 2020/2/24 09:14
 * @email：zhangh@qtopay.com
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