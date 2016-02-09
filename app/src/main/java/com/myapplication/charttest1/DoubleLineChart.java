package com.myapplication.charttest1;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.CombinedXYChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.Threshold;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sanndy on 2016/2/5.
 */




public class DoubleLineChart {

    private  Date[] d1;
    private  Date[] d2;
    private String Title;
    private int[] data1;
    private int[] data2;
    private String series1;
    private String series2;
    private String[] X_Label;
    private Context context;
    private GraphicalView gv;
    private Threshold [] mthreshold;
    XYMultipleSeriesRenderer multiRenderer;
    XYMultipleSeriesDataset dataset;

//    public DoubleLineChart(int[]x, int[] data1, int[] data2, String[] X_Label, Context Context) {
//
//
//        this.x=x;
//        this.data1 = data1;
//        this.data2 = data2;
//        this.X_Label = X_Label;
//        this.context = Context;
//
//        InitSeries(this.x, this.data1, this.data2);
//        InitLineRender(Color.CYAN, Color.BLACK);
//        CombinedXYChart.XYCombinedChartDef[] types = new CombinedXYChart.XYCombinedChartDef[]{ new CombinedXYChart.XYCombinedChartDef("Time",0),new CombinedXYChart.XYCombinedChartDef("Time",1)};
//
//        // Creating a combined chart with the chart types specified in types array
//        this.gv = ChartFactory.getCombinedXYChartView(this.context, this.dataset, this.multiRenderer, types);
//    }


    public DoubleLineChart(String Title ,String seriesname1,String seriesname2 , Date[] d1 , Date[] d2, int[] data1, int[] data2,int color1,int color2, Threshold [] thrsehold, Context Context) {

       this.Title=Title;
        this.d1=d1;
        this.d2=d2;
        this.data1 = data1;
        this.data2 = data2;
        this.mthreshold=thrsehold;
        this.context = Context;
        this.series1=seriesname1;
        this.series2=seriesname2;
        InitSeries(this.d1,this.d2, this.data1, this.data2);
        InitLineRender(color1,color2);
        CombinedXYChart.XYCombinedChartDef[] types = new CombinedXYChart.XYCombinedChartDef[]{ new CombinedXYChart.XYCombinedChartDef("Time",0),new CombinedXYChart.XYCombinedChartDef("Time",1)};

        // Creating a combined chart with the chart types specified in types array
        this.gv = ChartFactory.getCombinedXYChartView(this.context, this.dataset, this.multiRenderer, types);
    }

    public void InitSeries(Date[] dt1,Date[] dt2 , int[] data1, int[] data2) {


        // Creating an  XYSeries for Income
        TimeSeries  s1 = new TimeSeries (this.series1);
        // Creating an  XYSeries for Expense
        TimeSeries s2 = new TimeSeries (this.series2);


        // Adding data to Income and Expense Series
        for (int i = 0; i <dt1.length; i++) {
            s1.add(dt1[i], data1[i]);

        }
        for (int i = 0; i <dt2.length; i++) {

            s2.add(dt2[i], data2[i]);
        }
        // Creating a dataset to hold each series
        this.dataset = new XYMultipleSeriesDataset();
        // Adding Income Series to the dataset
        this.dataset.addSeries(s1);
        // Adding Expense Series to dataset
        this.dataset.addSeries(s2);



    }



    public void InitSeries(int[] x , int[] data1, int[] data2) {


        // Creating an  XYSeries for Income
        XYSeries  s1 = new XYSeries ("1");
        // Creating an  XYSeries for Expense
        XYSeries s2 = new XYSeries ("2");



        // Adding data to Income and Expense Series
        for (int i = 0; i < x.length; i++) {
            s1.add(x[i], data1[i]);
            s2.add(x[i], data2[i]);
        }
        // Creating a dataset to hold each series
        this.dataset = new XYMultipleSeriesDataset();
        // Adding Income Series to the dataset
        this.dataset.addSeries(s1);
        // Adding Expense Series to dataset
        this.dataset.addSeries(s2);



    }

    private void InitLineRender(int data1Color,int data2Color) {


        XYSeriesRenderer r1 = new XYSeriesRenderer();
        r1.setColor(data1Color);
        r1.setPointStyle(PointStyle.CIRCLE);
        r1.setFillPoints(false);
        r1.setLineWidth(5);
        r1.setDisplayChartValues(false);


        XYSeriesRenderer r2 = new XYSeriesRenderer();
        r2.setColor(data2Color);
        r2.setPointStyle(PointStyle.CIRCLE);
        r2.setFillPoints(false);
        r2.setLineWidth(5);
        r2.setDisplayChartValues(false);

        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        this.multiRenderer= new XYMultipleSeriesRenderer();
        multiRenderer.setLabelsColor(Color.RED);
        multiRenderer.setChartTitle(this.Title);

        multiRenderer.setXLabelsPadding(0f);
        multiRenderer.setChartTitleTextSize(30);
        multiRenderer.setLabelsTextSize(25);
        multiRenderer.setLegendTextSize(25);
        multiRenderer.setPointSize(5f);
        multiRenderer.setXTitle("X");
        multiRenderer.setYTitle("Y");
        multiRenderer.setXLabels(7);
        multiRenderer.setXLabelsPadding(20);
        multiRenderer.setYLabels(6);
        multiRenderer.setShowGridY(true);
        multiRenderer.setGridColor(Color.GRAY);

        //LABEL Color
        multiRenderer.setXLabelsColor(Color.BLACK);
        multiRenderer.setYLabelsColor(0, Color.BLACK);


        //Y ON RIGTH SIDE
        multiRenderer.setYAxisAlign(Paint.Align.RIGHT, 0);
        multiRenderer.setYLabelsAlign(Paint.Align.RIGHT, 0);
        multiRenderer.setYLabelsPadding(0);


        multiRenderer.setZoomButtonsVisible(false);

        multiRenderer.setBarSpacing(4);


        multiRenderer.setPanEnabled(true, false);
        multiRenderer.setMarginsColor(Color.argb(0, 255, 0, 0));
//LEGEND POSITION
        multiRenderer.setMargins(new int[]{40, 20, 5, 10});

        multiRenderer.setShowLegend(true);
//Set Thread Hold YValue

        multiRenderer.setmThreshold(mthreshold);

        multiRenderer.setLabelsTextSize(20);
        //RoundCharth
        final SimpleDateFormat mDateFormatter = new SimpleDateFormat("HH.mm");
        multiRenderer.setXLabelFormat(new NumberFormat() {
            @Override
            public StringBuffer format(double value, StringBuffer buffer, FieldPosition field) {
                return mDateFormatter.format(value, buffer, field);
            }

            @Override
            public StringBuffer format(long value, StringBuffer buffer, FieldPosition field) {
                return mDateFormatter.format(value, buffer, field);
            }

            @Override
            public Number parse(String string, ParsePosition position) {
                return mDateFormatter.parse(string, position).getTime();
            }
        });

//        for (int i = 0; i < x.length; i++) {
//            this. multiRenderer.addXTextLabel(i, this.X_Label[i]);
//        }

        // Adding incomeRenderer and expenseRenderer to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
        // should be same
        multiRenderer.addSeriesRenderer(r1);
        multiRenderer.addSeriesRenderer(r2);

    }

    public XYMultipleSeriesRenderer SetRender(XYMultipleSeriesRenderer render) {



        render.setLegendHeight(10);
        render.setZoomButtonsVisible(true);
        render.setBackgroundColor(0);
        render.setBarSpacing(4);
        render.setAxisTitleTextSize(16.0F);
        render.setChartTitleTextSize(20.0F);
        render.setChartTitle("Android CHART");
        render.setLabelsTextSize(15.0F);
        render.setLegendTextSize(15.0F);
        render.setMargins(new int[]{20, 30, 15, 15});
        render.setPanEnabled(true, false);
        render.setMarginsColor(Color.argb(0, 255, 0, 0));
        render.setBackgroundColor(0);
        render.setApplyBackgroundColor(true);
        render.setXLabels(10);
        render.setYLabels(12);
        render.setXLabelsAlign(Paint.Align.RIGHT);
        render.setYLabelsAlign(Paint.Align.CENTER);
        render.setZoomButtonsVisible(false);
        render.setShowGrid(true);
        render.setGridColor(-1);
        render.setAxesColor(-16776961);
        render.setFitLegend(true);
        render.setYAxisMax(24.0D);
        render.setYAxisMin(23.0D);
        render.setLabelsColor(-16777216);
        render.setXTitle("Time");
        render.setYTitle("Number");
        render.setLabelsColor(-16777216);
        return render;
    }

    public View GetView() {
        return this.gv;
    }
//    private void InitDataSet(ChartPoint[] datas ) {
//        this.ds = new XYMultipleSeriesDataset();
//        this.series = new XYSeries(this.entity);
//
//        for(int i = 0; i < datas.length; ++i) {
//            this.series.add(datas[i].getX(), datas[i].getY());
//
//        }
//
//        this.ds.addSeries(this.series);
//
//    }
//private void openChart(){
//    int[] x = { 0,1,2,3,4,5,6,7 };
//    int[] income = { 2000,2500,2700,3000,2800,3500,3700,3800};
//    int[] expense = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400 };
//
//    // Creating an  XYSeries for Income
//    XYSeries incomeSeries = new XYSeries("Income");
//    // Creating an  XYSeries for Expense
//    XYSeries expenseSeries = new XYSeries("Expense");
//    // Adding data to Income and Expense Series
//    for(int i=0;i<x.length;i++){
//        incomeSeries.add(x[i], income[i]);
//        expenseSeries.add(x[i],expense[i]);
//    }
//
//    // Creating a dataset to hold each series
//    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
//    // Adding Income Series to the dataset
//    dataset.addSeries(incomeSeries);
//    // Adding Expense Series to dataset
//    dataset.addSeries(expenseSeries);
//
//    // Creating XYSeriesRenderer to customize incomeSeries
//    XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
//    incomeRenderer.setColor(Color.BLUE);
//    incomeRenderer.setPointStyle(PointStyle.CIRCLE);
//    incomeRenderer.setFillPoints(true);
//    incomeRenderer.setLineWidth(2);
//    incomeRenderer.setDisplayChartValues(true);
//
//    // Creating XYSeriesRenderer to customize expenseSeries
//    XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
//    expenseRenderer.setColor(Color.BLACK);
//    expenseRenderer.setPointStyle(PointStyle.CIRCLE);
//    expenseRenderer.setFillPoints(true);
//    expenseRenderer.setLineWidth(2);
//    expenseRenderer.setDisplayChartValues(true);
//
//    // Creating a XYMultipleSeriesRenderer to customize the whole chart
//    XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
//    multiRenderer.setXLabels(0);
//    multiRenderer.setChartTitle("Income vs Expense Chart");
//    multiRenderer.setXTitle("Year 2012");
//    multiRenderer.setYTitle("Amount in Dollars");
//    multiRenderer.setZoomButtonsVisible(true);
//    multiRenderer.setBarSpacing(4);
//    for(int i=0;i<x.length;i++){
//        multiRenderer.addXTextLabel(i, mMonth[i]);
//    }
//
//    // Adding incomeRenderer and expenseRenderer to multipleRenderer
//    // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
//    // should be same
//    multiRenderer.addSeriesRenderer(incomeRenderer);
//    multiRenderer.addSeriesRenderer(expenseRenderer);
//
//    // Getting a reference to LinearLayout of the MainActivity Layout
//    LinearLayout chartContainer = (LinearLayout) findViewById(R.id.l1);
//
//    // Specifying chart types to be drawn in the graph
//    // Number of data series and number of types should be same
//    // Order of data series and chart type will be same
//    String[] types = new String[] { LineChart.TYPE, LineChart.TYPE };
//
//    // Creating a combined chart with the chart types specified in types array
//    mChart = (GraphicalView) ChartFactory.getCombinedXYChartView(getBaseContext(), dataset, multiRenderer, types);
//
//    multiRenderer.setClickEnabled(true);
//    multiRenderer.setSelectableBuffer(10);
//
//    mChart.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            SeriesSelection seriesSelection = mChart.getCurrentSeriesAndPoint();
//
//            if (seriesSelection != null) {
//                int seriesIndex = seriesSelection.getSeriesIndex();
//                String selectedSeries="Income";
//                if(seriesIndex==0)
//                    selectedSeries = "Income";
//                else
//                    selectedSeries = "Expense";
//                // Getting the clicked Month
//                String month = mMonth[(int)seriesSelection.getXValue()];
//                // Getting the y value
//                int amount = (int) seriesSelection.getValue();
//                Toast.makeText(
//                        getBaseContext(),
//                        selectedSeries + " in " + month + " : " + amount,
//                        Toast.LENGTH_SHORT).show();
//            }
//        }
//
//    });
//
//    // Adding the Combined Chart to the LinearLayout
//    chartContainer.addView(mChart);
//}
//    private void InitRender() {
//            this.render = new XYMultipleSeriesRenderer();
//            this.render.setAxisTitleTextSize(16.0F);
//            this.render.setChartTitleTextSize(20.0F);
//            this.render.setChartTitle("Android CHART");
//            this.render.setLabelsTextSize(15.0F);
//            this.render.setLegendTextSize(15.0F);
//            this.render.setMargins(new int[]{20, 30, 15, 15});
//            this.render.setPanEnabled(true, false);
//            this.render.setMarginsColor(Color.argb(0, 255, 0, 0));
//            this.render.setBackgroundColor(0);
//            this.render.setApplyBackgroundColor(true);
//            this.render.setXLabels(10);
//            this.render.setYLabels(12);
//            this.render.setXLabelsAlign(Paint.Align.RIGHT);
//            this.render.setYLabelsAlign(Paint.Align.CENTER);
//            this.render.setZoomButtonsVisible(false);
//            this.render.setShowGrid(true);
//            this.render.setGridColor(-1);
//            this.render.setAxesColor(-16776961);
//            this.render.setFitLegend(true);
//            this.render.setYAxisMax(24.0D);
//            this.render.setYAxisMin(23.0D);
//            this.render.setLabelsColor(-16777216);
//            this.render.setXTitle("Time");
//            this.render.setYTitle("Number");
//            this.render.setLabelsColor(-16777216);
//            this.xyRender = new XYSeriesRenderer();
//            this.xyRender.setPointStyle(PointStyle.DIAMOND);
//            this.xyRender.setColor(this.EntityColors);
//            this.xyRender.setFillPoints(false);
//            this.render.addSeriesRenderer(this.xyRender);
//        }
}
