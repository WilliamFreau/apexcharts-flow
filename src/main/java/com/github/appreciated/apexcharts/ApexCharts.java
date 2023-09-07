package com.github.appreciated.apexcharts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.appreciated.apexcharts.config.*;
import com.github.appreciated.apexcharts.config.events.*;
import com.github.appreciated.apexcharts.helper.Series;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.page.PendingJavaScriptResult;
import com.vaadin.flow.shared.Registration;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Tag("apex-charts-wrapper")
@NpmPackage(value = "apexcharts", version = "3.35.0")
@NpmPackage(value = "onecolor", version = "3.1.0")
@JsModule("./com/github/appreciated/apexcharts/apexcharts-wrapper.ts")
@CssImport(value = "./com/github/appreciated/apexcharts/apexcharts-wrapper-styles.css", id = "apex-charts-style")
public class ApexCharts extends LitTemplate implements HasSize, HasStyle, HasTheme {

    private static final Logger LOG = Logger.getLogger(ApexCharts.class.getName());

    private final ObjectMapper objectMapper;

    public ApexCharts() {
        this(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL));
    }

    /**
     * Method to set a custom {@link ObjectMapper} used for customizing object serialization.
     * Usually, this objectMapper should set {@link JsonInclude.Include#NON_NULL}.
     *
     * @param objectMapper the objectMapper to use
     */
    public ApexCharts(final ObjectMapper objectMapper) {
        assert objectMapper != null : "objectMapper may not be null";
        this.objectMapper = objectMapper;
        setWidth("100%");
    }

    private void setPropertyObject(final String property, final Object value) {
        try {
            getElement().setProperty(property, objectMapper.writeValueAsString(value));
        } catch (JsonProcessingException ex) {
            LOG.log(Level.SEVERE, String.format("Error mapping [%s] to json: %s", property, ex.getMessage()), ex);
        }
    }

    private void setProperty(final String property, final String value) {
        getElement().setProperty(property, value);
    }

    private <T> void setPropertyList(final String property, final List<T> value) {
        getElement().setPropertyList(property, value);
    }

    @Override
    public void setWidth(String width) {
        HasSize.super.setWidth(width);
        setProperty("width", width);
    }

    @Override
    public void setHeight(String height) {
        HasSize.super.setHeight(height);
        setProperty("height", height);
    }

    public void setAnnotations(Annotations annotations) {
        setPropertyObject("annotations", annotations);
    }

    public void setChart(Chart chart) {
        setPropertyObject("chart", chart);
    }

    public void setColors(String... colors) {
        setPropertyObject("colors", colors);
    }

    public void setDataLabels(DataLabels dataLabels) {
        setPropertyObject("dataLabels", dataLabels);
    }

    public void setFill(Fill fill) {
        setPropertyObject("fill", fill);
    }

    public void setGrid(Grid grid) {
        setPropertyObject("grid", grid);
    }

    public void setLabels(String... labels) {
        setPropertyList("labels", Arrays.asList(labels));
    }

    public void setLegend(Legend legend) {
        setPropertyObject("legend", legend);
    }

    public void setForecastDataPoints(ForecastDataPoints forecastDataPoints) {
        setPropertyObject("forecastDataPoints", forecastDataPoints);
    }

    public void setMarkers(Markers markers) {
        setPropertyObject("markers", markers);
    }

    public void setNoData(NoData noData) {
        setPropertyObject("noData", noData);
    }

    public void setPlotOptions(PlotOptions plotOptions) {
        setPropertyObject("plotOptions", plotOptions);
    }

    public void setResponsive(Responsive... responsive) {
        setPropertyObject("responsive", responsive);
    }

    public void setStates(States states) {
        setPropertyObject("states", states);
    }

    public void setStroke(Stroke stroke) {
        setPropertyObject("stroke", stroke);
    }

    public void setSubtitle(TitleSubtitle subtitle) {
        setPropertyObject("subtitle", subtitle);
    }

    public void setTheme(Theme theme) {
        setPropertyObject("theme", theme);
    }

    public void setTitle(TitleSubtitle chartTitle) {
        setPropertyObject("chartTitle", chartTitle);
    }

    public void setTooltip(Tooltip tooltip) {
        setPropertyObject("tooltip", tooltip);
    }

    public void setXaxis(XAxis xaxis) {
        setPropertyObject("xaxis", xaxis);
    }

    public void setYaxis(YAxis[] yaxis) {
        setPropertyObject("yaxis", yaxis);
    }

    /**
     * This method will be called automatically and does not require to be called manually
     */
    public void render() {
        getElement().callJsFunction("reRender");
    }

    public PendingJavaScriptResult dataURI() {
        return getElement().callJsFunction("dataURI");
    }

    /**
     * To enable client-side debug logs
     *
     * @param enabled true enabled the debug output false disables it
     */
    public void setDebug(boolean enabled) {
        getElement().setProperty("debug", enabled);
    }

    /**
     * Method to set update the data for all chart types except {@link com.github.appreciated.apexcharts.config.chart.Type#PIE} and
     * {@link com.github.appreciated.apexcharts.config.chart.Type#DONUT}.
     * For these both types use setSeries{@link #setSeries(Double[])}
     *
     * @param series the data series to update to the Chart with
     */
    public void updateSeries(Double... series) {
        setSeries(series);
        getElement().callJsFunction("updateData");
    }

    /**
     * Method to set update the data for all chart types except {@link com.github.appreciated.apexcharts.config.chart.Type#PIE} and
     * {@link com.github.appreciated.apexcharts.config.chart.Type#DONUT}.
     * For all other chart types use setSeries{@link #setSeries(Series[])}
     *
     * @param series the data series to update to the Chart with
     */
    public void updateSeries(Series... series) {
        setSeries(series);
        getElement().callJsFunction("updateData");
    }

    /**
     * Method to set the data for all chart types except {@link com.github.appreciated.apexcharts.config.chart.Type#PIE} and
     * {@link com.github.appreciated.apexcharts.config.chart.Type#DONUT}.
     * For these both types use setSeries{@link #setSeries(Double[])}
     *
     * @param series the data series to assign to the Chart
     */
    public void setSeries(Series... series) {
        setPropertyObject("series", series);
    }

    /**
     * Method to set the data for {@link com.github.appreciated.apexcharts.config.chart.Type#PIE} and
     * {@link com.github.appreciated.apexcharts.config.chart.Type#DONUT}.
     * For all other chart types use setSeries{@link #setSeries(Series[])}
     *
     * @param series the data series to assign to the Chart
     */
    public void setSeries(Double... series) {
        setPropertyObject("series", series);
    }

    /**
     * Method to toggle the visibility of series programmatically. Useful when you have a custom legend.
     *
     * @param seriesName the series name which you want to toggle visibility for
     * @return the pending javascript result
     */
    public PendingJavaScriptResult toggleSeries(String seriesName) {
        return getElement().callJsFunction("toggleSeries", seriesName);
    }

    /**
     * Method to show the hidden series. If the series is already visible, this doesn’t affect it.
     *
     * @param seriesName the series name which you want to show
     */
    public void showSeries(String seriesName) {
        getElement().callJsFunction("showSeries", seriesName);
    }

    /**
     * Method to hide the visible series. If the series is already hidden, this method doesn’t affect it.
     *
     * @param seriesName the series name which you want to hide
     */
    public void hideSeries(String seriesName) {
        getElement().callJsFunction("hideSeries", seriesName);
    }

    /**
     * Method to resets all toggled series and bring back the chart to its original state.
     *
     * @param shouldUpdateChart after resetting the series, the chart data should update and return to it’s original series
     * @param shouldResetZoom if the user has zoomed in when this method is called, the zoom level should also reset.
     */
    public void resetSeries(Boolean shouldUpdateChart, Boolean shouldResetZoom) {
        getElement().callJsFunction("resetSeries", shouldUpdateChart, shouldResetZoom);
    }
    
    public Registration addClickListener(ComponentEventListener<ApexChartClickEvent<ApexCharts>> listener) {
        return addListener(ApexChartClickEvent.class, (ComponentEventListener) listener);
    }
    
    public Registration addAnimationEndListener(ComponentEventListener<ApexChartAnimationEndEvent<ApexCharts>> listener) {
        return addListener(ApexChartAnimationEndEvent.class, (ComponentEventListener) listener);
    }
    
    public Registration addBeforeMountListener(ComponentEventListener<ApexChartBeforeMountEvent<ApexCharts>> listener) {
        return addListener(ApexChartBeforeMountEvent.class, (ComponentEventListener) listener);
    }
    
    public Registration addMountedListener(ComponentEventListener<ApexChartMountedEvent<ApexCharts>> listener) {
        return addListener(ApexChartMountedEvent.class, (ComponentEventListener) listener);
    }
    
    public Registration addUpdatedListener(ComponentEventListener<ApexChartUpdatedEvent<ApexCharts>> listener) {
        return addListener(ApexChartUpdatedEvent.class, (ComponentEventListener) listener);
    }
    
    public Registration addLegendClickListener(ComponentEventListener<ApexChartLegendClickEvent<ApexCharts>> listener) {
        return addListener(ApexChartLegendClickEvent.class, (ComponentEventListener) listener);
    }
    
    public Registration addMouseMoveListener(ComponentEventListener<ApexChartMouseMoveEvent<ApexCharts>> listener) {
        return addListener(ApexChartMouseMoveEvent.class, (ComponentEventListener) listener);
    }
    
    public Registration addMarkerClickListener(ComponentEventListener<ApexChartMarkerClickEvent<ApexCharts>> listener) {
        return addListener(ApexChartMarkerClickEvent.class, (ComponentEventListener) listener);
    }
    
    public Registration addSelectionListener(ComponentEventListener<ApexChartSelectionEvent<ApexCharts>> listener) {
        return addListener(ApexChartSelectionEvent.class, (ComponentEventListener) listener);
    }
    
    public Registration addDataPointSelectionListener(ComponentEventListener<ApexChartDataPointSelectionEvent<ApexCharts>> listener) {
        return addListener(ApexChartDataPointSelectionEvent.class, (ComponentEventListener) listener);
    }
    
    public Registration addDataPointMouseEnterListener(ComponentEventListener<ApexChartDataPointMouseEnterEvent<ApexCharts>> listener) {
        return addListener(ApexChartDataPointMouseEnterEvent.class, (ComponentEventListener) listener);
    }
    
    public Registration addDataPointMouseLeaveListener(ComponentEventListener<ApexChartDataPointMouseLeaveEvent<ApexCharts>> listener) {
        return addListener(ApexChartDataPointMouseLeaveEvent.class, (ComponentEventListener) listener);
    }
    
    public Registration addZoomedListener(ComponentEventListener<ApexChartZoomedEvent<ApexCharts>> listener) {
        return addListener(ApexChartZoomedEvent.class, (ComponentEventListener) listener);
    }
    
    public Registration addScrolledListener(ComponentEventListener<ApexChartScrolledEvent<ApexCharts>> listener) {
        return addListener(ApexChartScrolledEvent.class, (ComponentEventListener) listener);
    }
}
