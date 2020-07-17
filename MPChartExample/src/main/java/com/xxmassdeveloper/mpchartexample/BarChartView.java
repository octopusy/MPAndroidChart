package com.xxmassdeveloper.mpchartexample;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.Fill;
import com.xxmassdeveloper.mpchartexample.entity.MyDataAxisValueFormatter;
import com.xxmassdeveloper.mpchartexample.entity.MyFormatXAxisValueFormatter;
import com.xxmassdeveloper.mpchartexample.entity.MyYAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class BarChartView {

    /**
     * @param barChart  控件
     * @param barData   数据
     * @param isSlither 用来控制是否可以滑动
     */
    public void showBarChart(BarChart barChart, BarData barData, boolean isSlither) {
        //绘制高亮箭头
        // barChart.setDrawHighlightArrow(false);
        //设置值显示在柱状图的上边或者下边
        barChart.setDrawValueAboveBar(true);
        //设置绘制网格背景
        barChart.setDrawGridBackground(true);
        //设置双击缩放功能
        barChart.setDoubleTapToZoomEnabled(false);
        //设置规模Y启用
        barChart.setScaleYEnabled(false);
        //设置规模X启用
        barChart.setScaleXEnabled(false);
        //启用设置阻力
        barChart.setScaleEnabled(true);
        //设置每个拖动启用的高亮显示
        barChart.setHighlightPerDragEnabled(false);
        // 设置硬件加速功能
        barChart.setHardwareAccelerationEnabled(true);
        // 设置绘制标记视图
        barChart.setDrawMarkerViews(true);
        // 设置启用日志
        barChart.setLogEnabled(true);
        // 设置突出功能
//        barChart.setHighlightEnabled(true);
        // 设置拖动减速功能
        barChart.setDragDecelerationEnabled(true);
        // 数据描述
//        barChart.setDescription("");
        // 如果没有数据的时候，会显示这个，类似ListView的EmptyView
//        barChart.setNoDataTextDescription("没有数据了");
        barChart.setNoDataText("O__O …");
        // 是否显示表格颜色  
        barChart.setDrawGridBackground(false);

        /**
         * 下面这几个属性你们可以试试 挺有意思的
         * */
        // 设置是否可以触摸
        barChart.setTouchEnabled(isSlither);
        // 是否可以拖拽
        barChart.setDragEnabled(isSlither);//放大可拖拽
        // 是否可以缩放
        barChart.setScaleEnabled(false);
        // 集双指缩放
        barChart.setPinchZoom(false);
        // 设置背景
        barChart.setBackgroundColor(Color.parseColor("#01000000"));
        // 如果打开，背景矩形将出现在已经画好的绘图区域的后边。
        barChart.setDrawGridBackground(false);
        // 集拉杆阴影
        barChart.setDrawBarShadow(false);
        // 图例
        barChart.getLegend().setEnabled(false);
        // 设置数据
        barChart.setData(barData);
        // 隐藏右边的坐标轴 (就是右边的0 - 100 - 200 - 300 ... 和图表中横线)
        barChart.getAxisRight().setEnabled(false);
        // 隐藏左边的左边轴 (同上)
        // 网格背景颜色
        barChart.setGridBackgroundColor(Color.parseColor("#00000000"));
        // 是否显示表格颜色
        barChart.setDrawGridBackground(false);
        // 设置边框颜色
        barChart.setBorderColor(Color.parseColor("#00000000"));
        // 说明颜色
//        barChart.setDescriptionColor(Color.parseColor("#00000000"));
        // 拉杆阴影
        barChart.setDrawBarShadow(false);
        // 打开或关闭绘制的图表边框。（环绕图表的线）
        barChart.setDrawBorders(false);
        Legend mLegend = barChart.getLegend(); // 设置比例图标示
        // 设置窗体样式
        mLegend.setForm(Legend.LegendForm.CIRCLE);
        // 字体
        mLegend.setFormSize(4f);
        // 字体颜色
        mLegend.setTextSize(16);
        mLegend.setTextColor(Color.parseColor("#00000000"));
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
//        xAxis.setSpaceBetweenLabels(2);
//        xAxis.setTextColor(0x000000); // 设置轴标签的颜色。
//        xAxis.setTextSize(18); // 设置轴标签的文字大小。
//        xAxis.setTypeface( ) ;// 设置轴标签的 Typeface。
//        xAxis.setGridColor( int color); /// 设置该轴的网格线颜色。
//        xAxis.setGridLineWidth( long width);// 设置该轴网格线的宽度。
//        xAxis.setAxisLineColor( int color); // 设置轴线的轴的颜色。
//        xAxis.setAxisLineWidth( long width);// 设置该轴轴行的宽度。
//        barChart.animateY(1000); // 立即执行的动画,Y轴
        if (isSlither) {
            //当为true时,放大图
            // 为了使 柱状图成为可滑动的,将水平方向 放大 2.5倍
            barChart.invalidate();
            Matrix mMatrix = new Matrix();
            mMatrix.postScale(2f, 1f);
            barChart.getViewPortHandler().refresh(mMatrix, barChart, false);
            barChart.animateY(1000);
        } else {
            //当为false时 不对图进行操作
            barChart.animateY(1000);
        }
    }

    /**
     * 图表设置
     *
     * @param chart
     * @param xListData
     * @param dottNum
     */
    public void barChatHiddenSetting(Context mContext, BarChart chart, ArrayList<String> xListData, boolean dottNum, String yearData) {
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
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(xListData.size());
        xAxis.setValueFormatter(new MyFormatXAxisValueFormatter(xListData));

        ValueFormatter custom = new MyYAxisValueFormatter(dottNum);
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setLabelCount(12, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setStartAtZero(true);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setGranularity(1.0f);
        leftAxis.setEnabled(true);
        leftAxis.setDrawZeroLine(true);
        leftAxis.setDrawLabels(false);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawTopYLabelEntry(false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(50);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
        Legend mLegend = chart.getLegend();
        mLegend.setEnabled(true);
        mLegend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        mLegend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        mLegend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        mLegend.setDrawInside(false);
        mLegend.setFormToTextSpace(-16f);
        mLegend.setForm(Legend.LegendForm.NONE);
        mLegend.setExtra(new LegendEntry[]{
                new LegendEntry(
                        "2020",
                        Legend.LegendForm.NONE,
                        20f,
                        10f,
                        null,
                        mContext.getResources().getColor(R.color.colorPrimary)),
        });

        Matrix m = new Matrix();
        m.postScale(scaleWalletNum(xListData.size()), 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的1.5倍
        chart.getViewPortHandler().refresh(m, chart, false);//将图表动画显示之前进行缩放
    }

    //30个横坐标时，缩放4f是正好的。
    private float scaleWalletPercent = 4f/30f;

    private float scaleWalletNum(int xCount){
        return xCount * scaleWalletPercent;
    }

    public void setBarChartShowData(Context mContext, ArrayList<BarEntry> tradeYListData, BarChart chart) {
        BarDataSet barDataSet = new BarDataSet(tradeYListData, "");
        List<Fill> gradientFills = new ArrayList<>();
        gradientFills.add(new Fill(mContext.getResources().getColor(R.color.colorPrimary), mContext.getResources().getColor(R.color.colorPrimary)));
        barDataSet.setFills(gradientFills);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);

        barDataSet.setDrawValues(true);
        barDataSet.setValueFormatter(new MyDataAxisValueFormatter(true));
        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        // data.setBarWidth(0.9f);
        data.setBarWidth(scaleNum(tradeYListData.size()));

        chart.setData(data);
        chart.invalidate();
    }

    //30个横坐标时,缩放4f是正好的。
    private float scalePercent = 4f/30f;  // 默认值

    private float scaleNum(int xCount){
        float barWidth = 0.9f;
        if(xCount <= 4) {
            barWidth = xCount * scalePercent;
        }
        if(barWidth > 0.9f) {
            barWidth = 0.9f;
        }
        return barWidth;
    }

}
