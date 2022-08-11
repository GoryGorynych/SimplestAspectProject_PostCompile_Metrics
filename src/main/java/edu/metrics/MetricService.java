package edu.metrics;


import com.codahale.metrics.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MetricService {

    private static ConsoleReporter consoleReporter;
//    private static ConcurrentHashMap <String, Metered> metrics;
    private static MetricRegistry registry;
    private static final String METRIC_NAME = "TestMetric";

    static {
        initReporters();
//        metrics = new ConcurrentHashMap<>();
    }

    private static void initReporters() {
        registry = SharedMetricRegistries.getOrCreate(METRIC_NAME);

        Set<MetricAttribute> disableMetric = new HashSet<>();
        disableMetric.add(MetricAttribute.MEAN_RATE);
        disableMetric.add(MetricAttribute.M1_RATE);
        disableMetric.add(MetricAttribute.M5_RATE);
        disableMetric.add(MetricAttribute.M15_RATE);
        disableMetric.add(MetricAttribute.STDDEV);
        disableMetric.add(MetricAttribute.P50);
        disableMetric.add(MetricAttribute.P75);
        disableMetric.add(MetricAttribute.P95);
        disableMetric.add(MetricAttribute.P98);
        disableMetric.add(MetricAttribute.P99);
        disableMetric.add(MetricAttribute.P999);

        consoleReporter = ConsoleReporter.forRegistry(registry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .disabledMetricAttributes(disableMetric)
                .build();
    }

//    public static Timer getTimer(String timerName) {
//        if (!metrics.contains(timerName)) {
//            final Timer timer = registry.timer(timerName);
//            metrics.put(timerName, timer);
//        }
//        Timer timer = (Timer) metrics.get(timerName);
//        return timer;
//    }

    public static Timer getTimer(String timerName) {
        final Timer timer = registry.timer(timerName);
        return timer;
    }

    public static void reportToConsole() {
        consoleReporter.report();
    }

    public static void removeMetrics() {
        Map<String, Metric> metrics = registry.getMetrics();
        for(String name : metrics.keySet()) {
            registry.remove(name);
        }
    }
}
