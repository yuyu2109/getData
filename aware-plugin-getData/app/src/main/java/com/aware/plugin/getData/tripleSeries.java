package com.aware.plugin.getData;

import org.achartengine.model.XYSeries;

/**
 * Created by Melon Griottes Bananes on 30/05/2015.
 */
public class tripleSeries {
        private XYSeries x_series;
        private XYSeries y_series;
        private XYSeries z_series;

    public tripleSeries(XYSeries x_series, XYSeries y_series, XYSeries z_series){
        this.x_series = x_series;
        this.y_series = y_series;
        this.z_series = z_series;
    }

    public XYSeries getX_series(){
        return this.x_series;
    }
    public XYSeries getY_series(){
        return  this.y_series;
    }
    public XYSeries getZ_series(){
        return  this.z_series;
    }
}