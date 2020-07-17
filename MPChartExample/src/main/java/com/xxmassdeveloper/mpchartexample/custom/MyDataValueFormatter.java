package com.xxmassdeveloper.mpchartexample.custom;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;

public class MyDataValueFormatter extends ValueFormatter {

    private String suffix;

    public MyDataValueFormatter(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public String getFormattedValue(float value) {
        return suffix + value;
    }

    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        return "" + value;
    }
}
