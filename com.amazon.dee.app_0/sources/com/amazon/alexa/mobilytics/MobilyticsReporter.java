package com.amazon.alexa.mobilytics;

import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsMetricsTimer;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazon.latencyinfra.DefaultLatencyReporter;
import com.amazon.latencyinfra.LatencyReporterArgument;
import com.amazon.latencyinfra.LatencyReporterType;
import com.amazon.latencyinfra.LatencyType;
import com.google.common.base.Preconditions;
import java.util.Map;
/* loaded from: classes9.dex */
public class MobilyticsReporter extends DefaultLatencyReporter {
    private static final String TAG = Log.tag(MobilyticsReporter.class);
    private final Mobilytics mobilytics;

    public MobilyticsReporter(Mobilytics mobilytics) {
        super(LatencyReporterType.METRIC);
        this.mobilytics = (Mobilytics) Preconditions.checkNotNull(mobilytics);
    }

    @Override // com.amazon.latencyinfra.LatencyReporter
    public void report(LatencyReporterArgument latencyReporterArgument) {
        Log.enter();
        if (latencyReporterArgument == null || latencyReporterArgument.getType() == null) {
            return;
        }
        try {
            LatencyType type = latencyReporterArgument.getType();
            String namespace = latencyReporterArgument.getNamespace();
            String name = latencyReporterArgument.getName();
            Long timeInterval = latencyReporterArgument.getTimeInterval();
            Map<String, Long> events = latencyReporterArgument.getEvents();
            String sourceMetaData = latencyReporterArgument.getSourceMetaData();
            DefaultMobilyticsMetricsTimer defaultMobilyticsMetricsTimer = new DefaultMobilyticsMetricsTimer(name, namespace, namespace, timeInterval.longValue(), false);
            if (sourceMetaData != null) {
                defaultMobilyticsMetricsTimer.setSourceContext(sourceMetaData);
            }
            this.mobilytics.recordTimer(defaultMobilyticsMetricsTimer);
            if (type == LatencyType.TIMELINE && events != null) {
                for (Map.Entry<String, Long> entry : events.entrySet()) {
                    DefaultMobilyticsMetricsTimer defaultMobilyticsMetricsTimer2 = new DefaultMobilyticsMetricsTimer(name, namespace, entry.getKey(), entry.getValue().longValue(), false);
                    if (sourceMetaData != null) {
                        defaultMobilyticsMetricsTimer2.setSourceContext(sourceMetaData);
                    }
                    this.mobilytics.recordTimer(defaultMobilyticsMetricsTimer2);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e, "Error processing latency report.", new Object[0]);
        }
        Log.leave();
    }
}
