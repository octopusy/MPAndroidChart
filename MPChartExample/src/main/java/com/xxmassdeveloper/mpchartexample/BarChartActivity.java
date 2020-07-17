package com.xxmassdeveloper.mpchartexample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.Fill;
import com.xxmassdeveloper.mpchartexample.custom.BarChatXAxisValueFormatter;
import com.xxmassdeveloper.mpchartexample.custom.MyDataValueFormatter;
import com.xxmassdeveloper.mpchartexample.custom.MyValueFormatter;
import com.xxmassdeveloper.mpchartexample.custom.XYMarkerView;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;

public class BarChartActivity extends Activity {

    private BarChart chart1;
    private TextView tv_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart_basic);
        chart1 = findViewById(R.id.chart1);
        tv_select = (TextView) findViewById(R.id.tv_select);

        barChatSetting(chart1);
        setData(getYNumList(),chart1);
    }

    private void barChatSetting(BarChart chart) {
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.getDescription().setEnabled(false);
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(12);
        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(true);
        chart.setDrawGridBackground(false);
        // chart.setDrawYLabels(false);


        chart.setDragEnabled(true);
        chart.setScaleEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        //xAxis.setVisibleXRangeMaximum();

        // xAxis.setGranularity(1f);
        if(null != getYNumList() && getYNumList().size() <=6) {
            xAxis.setLabelCount(getYNumList().size());
        } else {
            xAxis.setLabelCount(6);
        }
        xAxis.setValueFormatter(new BarChatXAxisValueFormatter());

        ValueFormatter custom = new MyValueFormatter("");
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setDrawGridLines(false);
        // leftAxis.setSpaceTop(15f);
        leftAxis.setStartAtZero(true);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setGranularity(1.0f);
        leftAxis.setEnabled(false);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
        Legend mLegend = chart.getLegend();
        mLegend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        mLegend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        mLegend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        mLegend.setDrawInside(false);
        mLegend.setFormToTextSpace(-10f);
        mLegend.setExtra(new LegendEntry[]{
                new LegendEntry(
                        "2020年",
                        Legend.LegendForm.SQUARE,
                        20f,
                        50f,
                        null,
                        android.R.color.holo_blue_light),
        });

        XYMarkerView mv = new XYMarkerView(this,onSelectMarkerViewListener);
        chart.setMarker(mv);

        /*Matrix m = new Matrix();
        m.postScale(scaleNum(20), 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的1.5倍
        chart.getViewPortHandler().refresh(m, chart, false);//将图表动画显示之前进行缩放*/
    }

    private OnSelectMarkerViewListener onSelectMarkerViewListener = new OnSelectMarkerViewListener() {
        @Override
        public void onSelectMarkViewPosition(int position) {
            tv_select.setText("" + getYNumList().get(position).getY());
            Toast.makeText(BarChartActivity.this, "" + position, Toast.LENGTH_SHORT).show();
        }
    };

    //12个横坐标时，缩放4f是正好的。
    private float scalePercent = 4f/30f;

    private float scaleNum(int xCount){
        /*float barWidth = 0.9f;
        if(xCount <= 4) {
            barWidth = xCount * scalePercent;
        }
        if(barWidth > 0.9f) {
            barWidth = 0.9f;
        }
        return barWidth;*/
        return xCount * scalePercent;
    }

    private void setData(ArrayList<BarEntry> tradeYListData, BarChart chart) {
        BarDataSet barDataSet = new BarDataSet(tradeYListData, "");
        int startColor = ContextCompat.getColor(this, android.R.color.holo_blue_light);
        List<Fill> gradientFills = new ArrayList<>();
        gradientFills.add(new Fill(startColor, startColor));
        barDataSet.setFills(gradientFills);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);

        //barDataSet.setValueFormatter(new BarChatXValueFormatter());
        barDataSet.setDrawValues(true);


        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        data.setValueFormatter(new MyDataValueFormatter("Y"));
        data.setBarWidth(0.9f);

        chart.setData(data);
        chart.invalidate();
    }

    /**
     * 获取Y轴数据
     *
     * @return
     */
    private ArrayList<BarEntry> getYList(){
        ArrayList<BarEntry> xList = new ArrayList<>();
        xList.add(new BarEntry(0,Float.parseFloat("0.00")));
        xList.add(new BarEntry(1,Float.parseFloat("1598.56")));
        /*xList.add(new BarEntry(2,Float.parseFloat("15646.56")));
        xList.add(new BarEntry(3,Float.parseFloat("0.00")));
        xList.add(new BarEntry(4,Float.parseFloat("0.00")));
        xList.add(new BarEntry(5,Float.parseFloat("0.55")));
        xList.add(new BarEntry(6,Float.parseFloat("1.55")));
        xList.add(new BarEntry(7,Float.parseFloat("1568.00")));
        xList.add(new BarEntry(8,Float.parseFloat("15788.0")));
        xList.add(new BarEntry(9,Float.parseFloat("15789.0")));
        xList.add(new BarEntry(10,Float.parseFloat("0")));
        xList.add(new BarEntry(11,Float.parseFloat("0")));
        xList.add(new BarEntry(12,Float.parseFloat("0")));
        xList.add(new BarEntry(13,Float.parseFloat("0")));
        xList.add(new BarEntry(14,Float.parseFloat("0")));
        xList.add(new BarEntry(15,Float.parseFloat("0")));
        xList.add(new BarEntry(16,Float.parseFloat("1454.0")));
        xList.add(new BarEntry(17,Float.parseFloat("0")));
        xList.add(new BarEntry(18,Float.parseFloat("0")));
        xList.add(new BarEntry(19,Float.parseFloat("0")));
        xList.add(new BarEntry(20,Float.parseFloat("0")));
        xList.add(new BarEntry(21,Float.parseFloat("7854")));
        xList.add(new BarEntry(22,Float.parseFloat("0")));
        xList.add(new BarEntry(23,Float.parseFloat("0")));
        xList.add(new BarEntry(24,Float.parseFloat("0")));
        xList.add(new BarEntry(25,Float.parseFloat("4898")));
        xList.add(new BarEntry(26,Float.parseFloat("5455")));
        xList.add(new BarEntry(27,Float.parseFloat("0")));
        xList.add(new BarEntry(28,Float.parseFloat("0")));
        xList.add(new BarEntry(29,Float.parseFloat("0")));
        xList.add(new BarEntry(30,Float.parseFloat("78798")));*/
        return xList;
    }


    /**
     * 获取Y轴数据
     *
     * @return
     */
    private ArrayList<BarEntry> getYNumList(){
        ArrayList<BarEntry> xList = new ArrayList<>();
        xList.add(new BarEntry(0,Float.parseFloat("20")));
        xList.add(new BarEntry(1,Float.parseFloat("3")));
        xList.add(new BarEntry(2,Float.parseFloat("2")));
        xList.add(new BarEntry(3,Float.parseFloat("0")));
        xList.add(new BarEntry(4,Float.parseFloat("0")));
        xList.add(new BarEntry(5,Float.parseFloat("0")));
        xList.add(new BarEntry(6,Float.parseFloat("1")));
        xList.add(new BarEntry(7,Float.parseFloat("2")));
        xList.add(new BarEntry(8,Float.parseFloat("15")));
        xList.add(new BarEntry(9,Float.parseFloat("5")));
        xList.add(new BarEntry(10,Float.parseFloat("0")));
        xList.add(new BarEntry(11,Float.parseFloat("0")));
        xList.add(new BarEntry(12,Float.parseFloat("0")));
        xList.add(new BarEntry(13,Float.parseFloat("0")));
        xList.add(new BarEntry(14,Float.parseFloat("0")));
        xList.add(new BarEntry(15,Float.parseFloat("0")));
        xList.add(new BarEntry(16,Float.parseFloat("4")));
        xList.add(new BarEntry(17,Float.parseFloat("0")));
        xList.add(new BarEntry(18,Float.parseFloat("0")));
        xList.add(new BarEntry(19,Float.parseFloat("0")));
        /*xList.add(new BarEntry(20,Float.parseFloat("0")));
        xList.add(new BarEntry(21,Float.parseFloat("7")));
        xList.add(new BarEntry(22,Float.parseFloat("0")));
        xList.add(new BarEntry(23,Float.parseFloat("0")));
        xList.add(new BarEntry(24,Float.parseFloat("0")));
        xList.add(new BarEntry(25,Float.parseFloat("4")));
        xList.add(new BarEntry(26,Float.parseFloat("5")));
        xList.add(new BarEntry(27,Float.parseFloat("0")));
        xList.add(new BarEntry(28,Float.parseFloat("0")));
        xList.add(new BarEntry(29,Float.parseFloat("0")));
        xList.add(new BarEntry(30,Float.parseFloat("7")));*/
        return xList;
    }

    /**
     * 获取Y轴数据
     *
     * @return
     */
    private ArrayList<BarEntry> getYNumNewList(){
        ArrayList<BarEntry> xList = new ArrayList<>();
        xList.add(new BarEntry(0,Float.parseFloat("0")));
        xList.add(new BarEntry(1,Float.parseFloat("0.00")));
        xList.add(new BarEntry(2,Float.parseFloat("0.00")));
        xList.add(new BarEntry(3,Float.parseFloat("0")));
        xList.add(new BarEntry(4,Float.parseFloat("0")));
        xList.add(new BarEntry(5,Float.parseFloat("0")));
        xList.add(new BarEntry(6,Float.parseFloat("0")));
        xList.add(new BarEntry(7,Float.parseFloat("0")));
        xList.add(new BarEntry(8,Float.parseFloat("0")));
        xList.add(new BarEntry(9,Float.parseFloat("0")));
        xList.add(new BarEntry(10,Float.parseFloat("0")));
        xList.add(new BarEntry(11,Float.parseFloat("0")));
        xList.add(new BarEntry(12,Float.parseFloat("0")));
        xList.add(new BarEntry(13,Float.parseFloat("0")));
        xList.add(new BarEntry(14,Float.parseFloat("0")));
        xList.add(new BarEntry(15,Float.parseFloat("0")));
        xList.add(new BarEntry(16,Float.parseFloat("0")));
        xList.add(new BarEntry(17,Float.parseFloat("0")));
        xList.add(new BarEntry(18,Float.parseFloat("0")));
        xList.add(new BarEntry(19,Float.parseFloat("0")));
        xList.add(new BarEntry(20,Float.parseFloat("0")));
        xList.add(new BarEntry(21,Float.parseFloat("0")));
        xList.add(new BarEntry(22,Float.parseFloat("0")));
        xList.add(new BarEntry(23,Float.parseFloat("0")));
        xList.add(new BarEntry(24,Float.parseFloat("0")));
        xList.add(new BarEntry(25,Float.parseFloat("0")));
        xList.add(new BarEntry(26,Float.parseFloat("0")));
        xList.add(new BarEntry(27,Float.parseFloat("0")));
        xList.add(new BarEntry(28,Float.parseFloat("0")));
        xList.add(new BarEntry(29,Float.parseFloat("0")));
        xList.add(new BarEntry(30,Float.parseFloat("0")));
        return xList;
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
