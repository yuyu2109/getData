package com.aware.plugin.getData;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.aware.Aware;
import com.aware.Light;
import com.aware.utils.IContextCard;
import com.aware.providers.Light_Provider.Light_Data;
//import com.aware.plugin.lux_meter.Provider.LuxMeter_Data;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.Calendar;

/**
 * Created by Melon Griottes Bananes on 30/05/2015.
 */

public class ContextCardGraph implements IContextCard {

    /**
     * Empty constructor required for Java reflection to load the card
     *
     */
    public ContextCardGraph(){}

    private static LinearLayout plot;

    //private static int freq;

    static String[] x_hours = new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};

    public View getContextCard( Context context ) {

        Log.d("asd", "getContextCard");

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View card = inflater.inflate(R.layout.layout, null);

        //freq = Integer.parseInt(Aware.getSetting(context, Settings.FREQUENCY_PLUGIN_LUX_METER));

        plot = (LinearLayout) card.findViewById(R.id.lux_plot);
        plot.removeAllViews();
        plot.addView(drawGraph(context));

        return card;
    }

    private static GraphicalView drawGraph( Context context ) {
        GraphicalView mChart = null;

        /*
        if(freq ==  0) {
            Log.d("asd", "refresh graph 0");
            //Apply user-defined time window
            if( Aware.getSetting(context, Settings.TIME_WINDOW_PLUGIN_LUX_METER).length() == 0 ) {
                Aware.setSetting(context, Settings.TIME_WINDOW_PLUGIN_LUX_METER, 5);
            }

            long delta_time = System.currentTimeMillis()-(Integer.valueOf(Aware.getSetting(context, Settings.TIME_WINDOW_PLUGIN_LUX_METER)) * 60 * 1000);

            XYSeries frequency_series = new XYSeries("Light (Lux)");
            Cursor light_cursor = context.getContentResolver().query(Light_Data.CONTENT_URI, null, Light_Data.TIMESTAMP + " > " + delta_time, null, Light_Data.TIMESTAMP + " ASC");
            if( light_cursor != null && light_cursor.moveToFirst() ) {
                do {
                    frequency_series.add(light_cursor.getPosition(), light_cursor.getDouble(light_cursor.getColumnIndex(Light_Data.LIGHT_LUX)));
                } while( light_cursor.moveToNext() );
                light_cursor.close();
            }


            XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
            dataset.addSeries(frequency_series);

            //setup frequency
            XYSeriesRenderer frequency_renderer = new XYSeriesRenderer();
            frequency_renderer.setColor(Color.BLUE);
            frequency_renderer.setPointStyle(PointStyle.POINT);
            frequency_renderer.setDisplayChartValues(false);
            frequency_renderer.setLineWidth(1);
            frequency_renderer.setFillPoints(true);

            //Setup graph
            XYMultipleSeriesRenderer dataset_renderer = new XYMultipleSeriesRenderer();
            dataset_renderer.setChartTitle("Realtime Lux Chart");
            dataset_renderer.setApplyBackgroundColor(true);
            dataset_renderer.setBackgroundColor(Color.WHITE);
            dataset_renderer.setMarginsColor(Color.WHITE);
            dataset_renderer.setAxesColor(Color.BLACK); //used in titlesv
            dataset_renderer.setYTitle("Lux");
            dataset_renderer.setXTitle("Time");
            dataset_renderer.setFitLegend(true);
            dataset_renderer.setXLabels(0);
            dataset_renderer.setYLabels(0);
            dataset_renderer.setPanEnabled(false);
            dataset_renderer.setClickEnabled(false);
            dataset_renderer.setShowAxes(true);
            dataset_renderer.setShowGrid(true);
            dataset_renderer.setShowLabels(true);
            dataset_renderer.setAntialiasing(true);

            //add plot renderers to main renderer
            dataset_renderer.addSeriesRenderer(frequency_renderer);

            //put everything together
            mChart = (GraphicalView) ChartFactory.getLineChartView(context, dataset, dataset_renderer);
        } else {
        */
            Log.d("asd", "refresh graph other");
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);

            //stores screen on counts grouped per hour
            //int[] frequencies = new int[24];

            //add frequencies to the right hour buffer
            /*
            Cursor lux_cursor = context.getContentResolver().query(LuxMeter_Data.CONTENT_URI, new String[]{ "avg("+LuxMeter_Data.LUX_AVG+") as luxAvg","strftime('%H',"+ LuxMeter_Data.TIMESTAMP + "/1000, 'unixepoch', 'localtime')+0 as time_of_day" }, LuxMeter_Data.LUX_AVG + " > 0 AND " + LuxMeter_Data.TIMESTAMP + " >= " + c.getTimeInMillis() + " ) GROUP BY ( time_of_day ", null, LuxMeter_Data.TIMESTAMP + " ASC");

            if( lux_cursor != null && lux_cursor.moveToFirst() ) {
                do{
                    frequencies[lux_cursor.getInt(1)] = lux_cursor.getInt(0);

                } while( lux_cursor.moveToNext() );

                lux_cursor.close();
            }
            */

        /*
        long start = System.currentTimeMillis() - 200000;
        long end = System.currentTimeMillis() - 100000;
        */


        /*
        long start = System.currentTimeMillis() - 60000;
        long end = System.currentTimeMillis() - 10000;
        tripleSeries accel_series = DataHandlerGraph.getAccelerometerData(context.getContentResolver(), start, end);
        */


        /*Creating the data to test the FFT--------------------------------------------*/

        int sizeData = (int) Math.pow(2,8);
        Double[] abs = new Double[sizeData];
        Double[] abs2 = new Double[sizeData];

        Double[] transforme = new Double[sizeData];
        Double[] transforme2 = new Double[sizeData];



        XYSeries transfoSerie = new XYSeries("serieTransform�eFourier");
        XYSeries transfoSerie2 = new XYSeries("serieTransform�eFourier");



        for (int k = 0 ; k < sizeData; k++){
            abs[k] = Math.cos(k);
            abs2[k] = Math.cos(2*k);
            transforme[k]=0.0;
            transforme2[k] = 0.0;
        }
        FFT res = new FFT(sizeData);
        res.fft(abs, transforme);
        res.fft(abs2, transforme2);

        for (int k = 0; k<sizeData;k++){
            transfoSerie.add(k,transforme[k]);
            transfoSerie2.add(k,transforme2[k]);
        }

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(transfoSerie);
        dataset.addSeries(transfoSerie2);









         /*
        XYSeries xy_series = new XYSeries("Hour average light (lx)");
            for( int i = 0; i<frequencies.length; i++ ) {
                xy_series.add(i, frequencies[i]);
            }
            */


          //  XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
            //For the time being we will only add X.
          //  dataset.addSeries(accel_series.getX_series());








        //Setup the line colors, labels, etc
            XYSeriesRenderer series_renderer = new XYSeriesRenderer();
            series_renderer.setColor(Color.RED);
            series_renderer.setPointStyle(PointStyle.POINT);
            series_renderer.setDisplayChartValues(false);
            series_renderer.setLineWidth(2);
            series_renderer.setFillPoints(false);

        //Setup the line colors, labels, etc
        XYSeriesRenderer series_renderer2 = new XYSeriesRenderer();
        series_renderer.setColor(Color.GREEN);
        series_renderer.setPointStyle(PointStyle.POINT);
        series_renderer.setDisplayChartValues(false);
        series_renderer.setLineWidth(2);
        series_renderer.setFillPoints(false);

            //Setup graph colors, labels, etc
            XYMultipleSeriesRenderer dataset_renderer = new XYMultipleSeriesRenderer();
            //Add the series renderer to the chart renderer
            dataset_renderer.addSeriesRenderer(series_renderer);
            dataset_renderer.addSeriesRenderer(series_renderer2);


            dataset_renderer.setLabelsColor(Color.BLACK);
            dataset_renderer.setDisplayValues(true);
            dataset_renderer.setFitLegend(false);
            dataset_renderer.setXLabelsColor(Color.BLACK);
            dataset_renderer.setYLabelsColor(0, Color.BLACK);
            dataset_renderer.setLegendHeight(0);
            dataset_renderer.setYLabels(4);
            dataset_renderer.setYTitle("Acceleration");
            dataset_renderer.setZoomButtonsVisible(true);
            dataset_renderer.setXLabels(0);
            dataset_renderer.setPanEnabled(false);
            dataset_renderer.setShowGridY(false);
            dataset_renderer.setClickEnabled(false);
            dataset_renderer.setAntialiasing(true);
            dataset_renderer.setAxesColor(Color.BLACK);
            dataset_renderer.setApplyBackgroundColor(true);
            dataset_renderer.setBackgroundColor(Color.WHITE);
            dataset_renderer.setMarginsColor(Color.WHITE);
            dataset_renderer.setExternalZoomEnabled(false);
            dataset_renderer.setZoomEnabled(false);

            /* Do we need that?
            for(int i=0; i< x_hours.length; i++) {
                dataset_renderer.addXTextLabel(i, x_hours[i]);
            }
            */








        /*
        series_renderer.setColor(Color.GREEN);
        dataset_renderer.addSeriesRenderer(series_renderer);
        dataset_renderer.addSeriesRenderer(series_renderer);
        mChart.repaint();
        */

        /*
        dataset.addSeries(accel_series.getZ_series());
        series_renderer.setColor(Color.BLUE);
        dataset_renderer.addSeriesRenderer(series_renderer);
        */


        //Create the chart with our data and setup
        // mChart = (GraphicalView) ChartFactory.getBarChartView(context, dataset, dataset_renderer, Type.DEFAULT); //bar chart
        mChart = (GraphicalView) ChartFactory.getLineChartView(context, dataset, dataset_renderer); //bar chart

        return mChart;
    }

}