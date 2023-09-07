package com.github.appreciated.apexcharts;

import com.github.appreciated.apexcharts.config.Legend;
import com.github.appreciated.apexcharts.config.builder.LegendBuilder;
import com.github.appreciated.apexcharts.config.events.ApexChartClickEvent;
import com.github.appreciated.apexcharts.config.legend.Labels;
import com.github.appreciated.apexcharts.examples.ExampleChartGenerator;
import com.github.appreciated.apexcharts.examples.RevenueExample;
import com.github.appreciated.apexcharts.examples.event.ScatterChartWithEventsExample;
import com.github.appreciated.apexcharts.examples.tooltip.ScatterChartWithCustomTooltipsExample;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Route("")
public class DemoView extends HorizontalLayout {
    VerticalLayout left = new VerticalLayout();
    VerticalLayout right = new VerticalLayout();

    public DemoView() {
        initLayouts();
        addExamples();
    }

    private void initLayouts() {
        setSizeFull();
        getStyle()
                .set("overflow", "auto");
        setSizeUndefined();
        add(left, right);
        left.setHeight("unset");
        left.setWidth("50%");
        right.setWidth("50%");
        right.setHeight("unset");
    }

    private void addExamples() {
        List<ApexCharts> charts = Arrays.stream(ExampleChartGenerator.getCharts()).map(ApexChartsBuilder::build).collect(Collectors.toList());
        charts.forEach(left::add);
        //charts.forEach(this::initListener);
        
        Arrays.stream(ExampleChartGenerator.getColoredCharts()).map(ApexChartsBuilder::build).forEach(right::add);
        left.add(new RevenueExample());
        right.add(new ScatterChartWithEventsExample().build());
        left.add(new ScatterChartWithCustomTooltipsExample().build());
    }
    
    private void initListener(ApexCharts charts) {
        charts.addClickListener(event -> {
            Notification.show("ApexChart clicked: " + event);
            event.getSource().setSeries(30.0d, 30.0d, 30.0d);
            event.getSource().setLabels("Team A", "Team B", "Team C");
            event.getSource().render();
        });
        /*charts.addAnimationEndListener(System.out::println);
        charts.addBeforeMountListener(System.out::println);
        charts.addMountedListener(System.out::println);
        charts.addUpdatedListener(System.out::println);
        charts.addLegendClickListener(System.out::println);
        charts.addMouseMoveListener(System.out::println);
        charts.addMarkerClickListener(System.out::println);
        charts.addSelectionListener(System.out::println);
        charts.addDataPointSelectionListener(System.out::println);
        charts.addDataPointMouseEnterListener(System.out::println);
        charts.addDataPointMouseLeaveListener(System.out::println);
        charts.addZoomedListener(System.out::println);
        charts.addScrolledListener(System.out::println);*/
    }

}
