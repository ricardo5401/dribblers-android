package pe.edu.upc.dribblers.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.activities.MainActivity;

/**
 * Created by Richard on 14/05/2017.
 */

public class EstatisticFragment extends Fragment {


    PieChart mPieChart;
    BarChart mBarChart;
    private float[] yData = { 70, 30};
    private String[] xData = { "Acertados" ,"Fallidos"};

    public EstatisticFragment (){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_statistic, container, false);
        //getActivity().setTitle("Estadisticas");


        mPieChart = (PieChart) v.findViewById(R.id.pieChart);
       configurePie();


        return v;
    }
    private void configurePie(){
        mPieChart.setRotationEnabled(true);

        // configure pie chart
        mPieChart.setUsePercentValues(true);
        // mChart.setDescription("Smartphones Market Share");

        // enable hole and configure
        mPieChart.setDrawHoleEnabled(true);
        // mChart.setHoleColorTransparent(true);
        mPieChart.setHoleRadius(20);
        mPieChart.setTransparentCircleRadius(25);

        // enable rotation of the chart by touch
        mPieChart.setRotationAngle(0);
        mPieChart.setRotationEnabled(true);

        // set a chart value selected listener
        mPieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (e == null)
                    return;
            }

            @Override
            public void onNothingSelected() {

            }
        });

        // add data
        addPieData();

        // customize legends
        Legend l = mPieChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
    }
    private void addPieData() {
        ArrayList<PieEntry> yVals1 = new ArrayList<PieEntry>();

        for (int i = 0; i < yData.length; i++)
            yVals1.add(new PieEntry(yData[i], i));

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);

        // create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "Market Share");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        // add many colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);



        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        // instantiate pie data object now
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        mPieChart.setData(data);

        // undo all highlights
        mPieChart.highlightValues(null);

        // update pie chart
        mPieChart.invalidate();
    }

}
