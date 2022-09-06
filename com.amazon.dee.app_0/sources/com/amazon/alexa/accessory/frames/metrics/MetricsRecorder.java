package com.amazon.alexa.accessory.frames.metrics;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.frames.utils.Log;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
/* loaded from: classes.dex */
public final class MetricsRecorder {
    private static final String MOBILYTICS_IDENTIFIER = "4c22bd9e-f936-44d2-95c3-249c93bd6042";
    private static final String TAG = "MetricsRecorder";
    private static MetricsRecorder recorder;
    private LazyComponent<Mobilytics> lazyMobilytics = ComponentRegistry.getInstance().getLazy(Mobilytics.class);

    public static synchronized MetricsRecorder getInstance() {
        MetricsRecorder metricsRecorder;
        synchronized (MetricsRecorder.class) {
            if (recorder == null) {
                Log.d(TAG, "getInstance - Creating new instance");
                recorder = new MetricsRecorder();
            }
            metricsRecorder = recorder;
        }
        return metricsRecorder;
    }

    @VisibleForTesting
    static void resetInstance() {
        recorder = new MetricsRecorder();
    }

    public void recordCounter(String str, String str2, String str3, double d) {
        Preconditions.notNull(str, JsonFields.EVENT_NAME);
        Preconditions.notNull(str2, "componentName");
        Preconditions.notNull(str2, "value");
        Mobilytics mo10268get = this.lazyMobilytics.mo10268get();
        MobilyticsMetricsCounter createCounter = mo10268get.createCounter(str, str2, str3, "4c22bd9e-f936-44d2-95c3-249c93bd6042");
        createCounter.incrementCounterByValue((long) d);
        mo10268get.recordCounter(createCounter);
        Log.i(TAG, String.format("Recorded accessory metric counter: %s, value: %f, subComponent: %s", str, Double.valueOf(d), str3));
    }

    public void recordTimer(MobilyticsMetricsTimer mobilyticsMetricsTimer) {
        this.lazyMobilytics.mo10268get().recordTimer(mobilyticsMetricsTimer);
    }

    public MobilyticsMetricsTimer startTimer(String str) {
        return this.lazyMobilytics.mo10268get().createTimer(str, MetricsConstants.COMPONENT_NAME, "default", "4c22bd9e-f936-44d2-95c3-249c93bd6042");
    }

    public void recordCounter(String str, String str2) {
        recordCounter(str, MetricsConstants.COMPONENT_NAME, str2, 1.0d);
    }

    public void recordCounter(String str) {
        recordCounter(str, MetricsConstants.COMPONENT_NAME, "default", 1.0d);
    }
}
