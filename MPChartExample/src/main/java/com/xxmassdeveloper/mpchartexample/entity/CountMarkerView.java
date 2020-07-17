package com.xxmassdeveloper.mpchartexample.entity;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.xxmassdeveloper.mpchartexample.OnSelectMarkerViewListener;
import com.xxmassdeveloper.mpchartexample.R;

import java.util.List;

/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
public class CountMarkerView extends MarkerView {

    private final TextView tvXData;
    private final TextView tvYData;
    private List<String> xListData;
    private OnSelectMarkerViewListener mOnSelectMarkerViewListener;

    public CountMarkerView(Context context, OnSelectMarkerViewListener onSelectMarkerViewListener) {
        super(context, R.layout.trade_custom_marker_view);
        tvXData = findViewById(R.id.tvXData);
        tvYData = findViewById(R.id.tvYData);
        this.mOnSelectMarkerViewListener = onSelectMarkerViewListener;
    }

    public void setxListData(List<String> listData) {
        xListData = listData;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        int position = (int)e.getX();
        String valueData = "";
        try {
            if(!xListData.isEmpty()){
                valueData =  xListData.get(position);
            } else {
                valueData = String.valueOf(position);
            }
            if(!TextUtils.isEmpty(valueData) && valueData.length() >2) {
                valueData = valueData.substring(valueData.length() -2 ,valueData.length());
            }
            tvXData.setText(valueData + "月");
            tvYData.setText("￥" + PublicMethodUtils.formatBigWanAmount("" + e.getY(),false));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if(null != mOnSelectMarkerViewListener) {
            mOnSelectMarkerViewListener.onSelectMarkViewPosition(position);
        }
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2)- 20, -getHeight());
    }
}
