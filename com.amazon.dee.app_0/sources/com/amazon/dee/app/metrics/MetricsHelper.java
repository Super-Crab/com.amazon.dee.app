package com.amazon.dee.app.metrics;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.client.metrics.common.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes12.dex */
public final class MetricsHelper {
    private static final String ANDROID = "ANDROID";
    private static final String FIRE_OS = "FIRE_OS";
    private static final String OBERLIN = "5.3.6.4";
    @VisibleForTesting
    static Boolean isFireOS;
    @VisibleForTesting
    static MetricsFactory sMetricsFactory;
    private static final String TAG = Log.tag(MetricsHelper.class);
    @VisibleForTesting
    static final ConcurrentMap<String, MetricEvent> METRIC_INDEX = new ConcurrentHashMap();

    private MetricsHelper() {
    }

    public static MetricEvent getMetric(CommsMetric commsMetric) {
        if (!METRIC_INDEX.containsKey(commsMetric.getServiceName())) {
            METRIC_INDEX.put(commsMetric.getServiceName(), sMetricsFactory.createMetricEvent("AlexaMobileAndroid", commsMetric.getServiceName()));
        }
        return METRIC_INDEX.get(commsMetric.getServiceName());
    }

    public static MetricsFactory getMetricsFactory(Context context) {
        return AndroidMetricsFactoryImpl.getInstance(context);
    }

    public static MetricsFactory getMetricsFactoryInstance() {
        return sMetricsFactory;
    }

    public static String getOsType() {
        return isFireOS() ? "FIRE_OS" : "ANDROID";
    }

    public static void initializeMetricsFactory(Context context) {
        sMetricsFactory = getMetricsFactory(context);
    }

    public static boolean isFireOS() {
        if (isFireOS == null) {
            try {
                Class.forName("amazon.os.Build$VERSION");
                isFireOS = true;
            } catch (ClassNotFoundException unused) {
                isFireOS = false;
            }
        }
        return isFireOS.booleanValue();
    }

    public static void recordCounterMetric(CounterMetric counterMetric) {
        MetricEvent metric = getMetric(counterMetric);
        metric.addCounter(counterMetric.getMetricName(), counterMetric.getCount().doubleValue());
        sMetricsFactory.record(metric);
    }

    public static void startTimerMetric(CommsMetric commsMetric) {
        getMetric(commsMetric).startTimer(commsMetric.getMetricName());
    }

    public static void stopTimerMetric(CommsMetric commsMetric) {
        MetricEvent metric = getMetric(commsMetric);
        metric.stopTimer(commsMetric.getMetricName());
        sMetricsFactory.record(metric);
    }
}
