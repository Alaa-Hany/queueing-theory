package com.alaa.queueing_theory.app.controllers;

import com.alaa.queueing_theory.app.queues.DD1K;
import com.alaa.queueing_theory.utility.Env;
import com.alaa.queueing_theory.utility.Globals;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryStepRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.RangeType;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtils;

import java.util.ArrayList;

public class PlotChart {
    private DD1K dd1K;

    public PlotChart(DD1K dd1K) {
        this.dd1K = dd1K;
    }


    private ArrayList<Number> generateData() {
        ArrayList<Number> dataset = new ArrayList<>();
        int current = 0;
        while (current < (this.getDd1K().getTi() + (int) ((1 / this.getDd1K().getLamda()) * 1.5))) {
            dataset.add(this.getDd1K().getNoOfCustomersAtT(current));
            current += 1;
        }
        return dataset;
    }

    private CategoryDataset createDataset() {
        Number[][] dataset = {this.generateData().toArray(new Number[0])};
        return DatasetUtils.createCategoryDataset("D\\D\\1\\" + (this.getDd1K().getK() - 1) + " , no : ", "", dataset);
    }


    private NumberAxis getNumberAxis(String title) {
        NumberAxis numberAxis = new NumberAxis(title);
        numberAxis.setRangeType(RangeType.POSITIVE);
        numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return numberAxis;
    }

    private CategoryAxis getCategoryAxis(String title) {
        return new CategoryAxis(title);

    }

    public JFreeChart createChart() {
        //
        CategoryPlot categoryPlot = new CategoryPlot(this.createDataset(), this.getCategoryAxis(""), this.getNumberAxis("n(t)"), new CategoryStepRenderer(true));
        categoryPlot.setDomainGridlinesVisible(true);
        categoryPlot.setRangeGridlinesVisible(true);
        categoryPlot.setAxisOffset(new RectangleInsets(5.0D, 5.0D, 5.0D, 5.0D));
        return (new JFreeChart("", categoryPlot));
    }


    public void show() {
        Globals.getChartStage().setTitle("Deterministic Queue");
        ((ChartViewer) Globals.getChartStage().getScene().getRoot()).setChart(this.createChart());
        Globals.getChartStage().setWidth(Env.width * 1.5);
        Globals.getChartStage().setHeight(Env.height);
        Globals.getChartStage().show();
    }


    public DD1K getDd1K() {
        return dd1K;
    }
}
