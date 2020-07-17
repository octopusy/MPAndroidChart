package com.xxmassdeveloper.mpchartexample.entity;

import android.text.TextUtils;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

/**
 * X轴显示数据
 *
 * @author Richard
 * @Emial zhangh@qtopay.cn
 */
public class MyFormatXAxisValueFormatter extends ValueFormatter {

    private ArrayList<String> xListData;

    public MyFormatXAxisValueFormatter(ArrayList<String> listData) {
        this.xListData = listData;
    }

    @Override
    public String getFormattedValue(float value) {
        if(value < 0 || value == 0.5) return "";
        int position = 0;
        /*if(value > 1) {
            position = (int)value -1;
        } else {*/
            position = (int) value;
        // }
        String valueData = "";
        try {
            if(xListData.isEmpty() || position >= xListData.size()) {
                valueData = String.valueOf(position);
            } else {
                valueData =  xListData.get(position);
            }
            if(!TextUtils.isEmpty(valueData) && valueData.length() >2) {
                valueData = valueData.substring(valueData.length() -2 ,valueData.length());
                return valueData + "月";
            } else {
                return  "";
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return "";
        }
    }

}
