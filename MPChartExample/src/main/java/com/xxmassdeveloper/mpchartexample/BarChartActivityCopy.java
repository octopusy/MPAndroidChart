package com.xxmassdeveloper.mpchartexample;

import android.app.Activity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

public class BarChartActivityCopy extends Activity {

    protected BarChart mChart;
    private int sCount = 31; //X轴坐标数据数量

    private ArrayList<String> data; //保存X轴坐标数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mChart = findViewById(R.id.bc_re);

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);
        //mChart.setContentDescription("××表");
        mChart.getDescription().setEnabled(false);
        mChart.setPinchZoom(false); //scaling can now only be done on x- and y-axis separately
        mChart.setDrawGridBackground(false);
        mChart.getAxisRight().setEnabled(false); //不绘制右侧轴线

        data = new ArrayList<>();
        for(int i = 0; i<sCount ; i++) {
            data.add("2019-08-"+(i+1));
        }

        XAxis xl = mChart.getXAxis();
        xl.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return String.valueOf(data.get((int) value));
            }
        });

        /*xl.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return String.valueOf(data.get((int) value));
            }
            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });*/
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xl.setLabelRotationAngle(45);  //标签倾斜
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setCenterAxisLabels(false); //可不加这句，默认为false
        //xl.setGranularity(sCount);  //x轴坐标占的宽度
        xl.setGranularity(1f); //x轴坐标占的宽度
        //xl.setCenterAxisLabels(true);
        xl.setAxisMinimum(-0.5f); // 此轴显示的最小值
        //xl.setAxisMaximum(sCount*sCount);  // 此轴显示的最大值
        xl.setLabelCount(sCount); //显示的坐标数量

        YAxis yl = mChart.getAxisLeft();
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setAxisMinimum(0f);//this replaces setStartAtZero(true)

        float[] val = {25000000, 29000000 , 21000000, 22000000, 20000000, 26000000, 22000000, 20000000, 28000000, 29000000, 29000000, 24003200,26003200, 26003200 , 26003200, 26003200, 26003200, 26003200, 26003200, 26003200, 26003200, 26003200, 26003200, 26003200,26003200,26003200,26003200,26003200,26003200,26003200,26003200};
        setData(sCount, val);
        mChart.setFitBars(true);
        mChart.animateXY(2000, 2000);

        Legend legend = mChart.getLegend();
        legend.setEnabled(false); //不显示图例
    }

    private void setData(int count, float[] val) {
        //float barWidth = count-1;    //每个彩色数据条的宽度
        //float spaceForBar = count;   //每个数据条实际占的宽度
        float barWidth = 0.8f; //每个彩色数据条的宽度
        float spaceForBar = 1f; //每个数据条实际占的宽度
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 0; i < count; i++)
        {
            //float val = (float) (Math.random() * range);
            yVals1.add(new BarEntry(i * spaceForBar, val[i]));
        }

        BarDataSet set1;

        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet)mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "sss");
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(barWidth);
            mChart.setData(data);
        }
    }

}
