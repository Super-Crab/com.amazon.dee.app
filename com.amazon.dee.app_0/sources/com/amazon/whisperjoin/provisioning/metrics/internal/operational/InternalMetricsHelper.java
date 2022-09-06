package com.amazon.whisperjoin.provisioning.metrics.internal.operational;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricsFactory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes13.dex */
public class InternalMetricsHelper {
    private static final String APP_CLIENT_NAME_METRIC = "AppClient";
    private static final String APP_CLIENT_VERSION = "AppVersion";
    private static final String TAG = "com.amazon.whisperjoin.provisioning.metrics.internal.operational.InternalMetricsHelper";
    private static final String TARGET_DEVICE_PIVOT = "TargetDevice";
    private static MetricsFactory sMetricsFactory;
    private static final Map<String, String> sPivotMap = new ConcurrentHashMap();

    private static void addAppVersionPivot(Context context) {
        try {
            addPivot("AppVersion", context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    private static void addPivot(String str, String str2) {
        sPivotMap.put(str, str2);
    }

    public static void init(Context context, MetricsFactory metricsFactory) {
        sMetricsFactory = metricsFactory;
        addPivot(APP_CLIENT_NAME_METRIC, context.getPackageName());
        addAppVersionPivot(context);
    }

    public static MetricEvent newMetricEvent(String str, String str2) {
        MetricEvent createMetricEvent = sMetricsFactory.createMetricEvent(str, str2);
        populatePivots(createMetricEvent);
        return createMetricEvent;
    }

    public static MetricEvent newMetricEventForEndpoint(String str, String str2, String str3) {
        MetricEvent newMetricEvent = newMetricEvent(str, str2);
        newMetricEvent.addString(TARGET_DEVICE_PIVOT, str3);
        return newMetricEvent;
    }

    private static void populatePivots(MetricEvent metricEvent) {
        for (Map.Entry<String, String> entry : sPivotMap.entrySet()) {
            metricEvent.addString(entry.getKey(), entry.getValue());
        }
    }

    public static void record(MetricEvent metricEvent) {
        Log.i(TAG, metricEvent.toString());
        sMetricsFactory.record(metricEvent);
    }
}
