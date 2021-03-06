package com.xxmassdeveloper.mpchartexample.custom;

import android.annotation.SuppressLint;
import android.content.Context;
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
@SuppressLint("ViewConstructor")
public class XYMarkerView extends MarkerView {

    private final TextView tvXData;
    private final TextView tvYData;
    private List<String> xListData;
    private OnSelectMarkerViewListener mOnSelectMarkerViewListener;

    public XYMarkerView(Context context,OnSelectMarkerViewListener onSelectMarkerViewListener) {
        super(context, R.layout.custom_marker_view);
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
        tvXData.setText("" + e.getX());
        tvYData.setText(/*"交易金额:" +*/ "" + e.getY());
        mOnSelectMarkerViewListener.onSelectMarkViewPosition(position);
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
