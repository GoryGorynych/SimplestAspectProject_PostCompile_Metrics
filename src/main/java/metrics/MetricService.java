package metrics;

import com.codahale.metrics.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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

        consoleReporter = ConsoleReporter.forRegistry(registry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
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
