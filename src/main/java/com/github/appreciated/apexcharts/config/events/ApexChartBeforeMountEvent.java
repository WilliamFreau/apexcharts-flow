package com.github.appreciated.apexcharts.config.events;

import com.github.appreciated.apexcharts.ApexCharts;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;

@DomEvent("apex-chart-before-mount-event")
public class ApexChartBeforeMountEvent<R extends ApexCharts> extends ComponentEvent<R> {
    /**
     * Creates a new event using the given source and indicator whether the
     * event originated from the client side or the server side.
     *
     * @param source     the source component
     * @param fromClient <code>true</code> if the event originated from the client
     */
    public ApexChartBeforeMountEvent(R source, boolean fromClient) {
        super(source, fromClient);
    }
}
