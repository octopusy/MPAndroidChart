package com.xxmassdeveloper.mpchartexample.custom;

import android.util.Log;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

/**
 * Created by philipp on 02/06/16.
 */
public class BarChatXValueFormatter extends ValueFormatter{

    public BarChatXValueFormatter() {}

    @Override
    public String getFormattedValue(float value) {
        int position = 0;
        if(value > 0) {
            position = (int)value -1;
        }
        Log.e("value:" , "value:" + position);
        return getXList().get(position) + "月";
    }

    /**
     * X轴数据
     *
     * @return
     */
    private ArrayList<String> getXList(){
        ArrayList<String> xList = new ArrayList<>();
        xList.add("2020-04-01");
        xList.add("2020-04-02");
        xList.add("2020-04-03");
        xList.add("2020-04-04");
        xList.add("2020-04-05");
        xList.add("2020-04-06");
        xList.add("2020-04-07");
        xList.add("2020-04-08");
        xList.add("2020-04-09");
        xList.add("2020-04-10");
        xList.add("2020-04-12");
        xList.add("2020-04-13");
        xList.add("2020-04-14");
        xList.add("2020-04-15");
        xList.add("2020-04-16");
        xList.add("2020-04-17");
        xList.add("2020-04-18");
        xList.add("2020-04-19");
        xList.add("2020-04-20");
        xList.add("2020-04-21");
        xList.add("2020-04-22");
        xList.add("2020-04-23");
        xList.add("2020-04-24");
        xList.add("2020-04-25");
        xList.add("2020-04-26");
        xList.add("2020-04-27");
        xList.add("2020-04-28");
        xList.add("2020-04-29");
        xList.add("2020-04-30");
        xList.add("2020-04-31");
        return xList;
    }
}
