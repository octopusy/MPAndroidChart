package com.xxmassdeveloper.mpchartexample;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;
import com.xxmassdeveloper.mpchartexample.entity.BenfitMonthCountModel;
import com.xxmassdeveloper.mpchartexample.entity.CountMarkerView;
import com.xxmassdeveloper.mpchartexample.entity.DataSourceHelper;
import com.xxmassdeveloper.mpchartexample.entity.PublicMethodUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * 柱状图展示
 *
 * @author Richard
 * @Email 583206137@qq.com
 */
public class BarExtendChartActivity extends Activity {

    private BarChart chart1;
    private BarChartView mBarCharts;
    private CountMarkerView countMarkerView;

    private ArrayList<String> benfitXListData;  // X轴数据
    private ArrayList<BarEntry> benfitYListData;  // Y轴数据

    // 请求月份
    private String requestMonth = "";
    // 统计月份数据
    private List<BenfitMonthCountModel> benfitMonthCountModelList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart_extend);
        chart1 = findViewById(R.id.chart1);
        mBarCharts = new BarChartView();
        countMarkerView = new CountMarkerView(BarExtendChartActivity.this,onSelectMarkerViewListener);
        chart1.setMarker(countMarkerView);
        requestWalletCountList(requestMonth,"01");
    }

    private OnSelectMarkerViewListener onSelectMarkerViewListener = new OnSelectMarkerViewListener() {
        @Override
        public void onSelectMarkViewPosition(int position) {
            try {
                Toast.makeText(BarExtendChartActivity.this,"position:" + position,Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    /**
     * 请求账单统计数据
     */
    private void requestWalletCountList(String month,String changeType){
        try {
            benfitMonthCountModelList = DataSourceHelper.getBenfitCountList();
            if(null != benfitMonthCountModelList & benfitMonthCountModelList.size() > 0) {
                // 解析chartBarView
                benfitXListData = new ArrayList<>();
                benfitYListData = new ArrayList<BarEntry>();

                // 解析查询到的数据
                for(int i= 0;i< benfitMonthCountModelList.size();i++) {
                    String transDate = benfitMonthCountModelList.get(i).getMONTH();
                    if(requestMonth.contentEquals(transDate)) {
                        Toast.makeText(BarExtendChartActivity.this,"position:" + i,Toast.LENGTH_LONG).show();
                    }
                    transDate = PublicMethodUtils.getFormatMonthDateTime(transDate);
                    benfitXListData.add(transDate);

                    float transAmt = TextUtils.isEmpty(benfitMonthCountModelList.get(i).getSumAmount())?0:
                            Float.parseFloat(PublicMethodUtils.parseFenToYuan(benfitMonthCountModelList.get(i).getSumAmount()));

                    benfitYListData.add(new BarEntry(i,transAmt));
                }

                countMarkerView.setxListData(benfitXListData);
                mBarCharts.barChatHiddenSetting(BarExtendChartActivity.this,chart1,benfitXListData,true,"");
                mBarCharts.setBarChartShowData(BarExtendChartActivity.this,benfitYListData,chart1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
