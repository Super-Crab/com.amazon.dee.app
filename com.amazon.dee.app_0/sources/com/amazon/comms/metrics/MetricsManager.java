package com.amazon.comms.metrics;

import android.content.Context;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.reflect.Factory;
/* loaded from: classes11.dex */
public class MetricsManager {
    public static final MetricsDestination TAC_DEFAULT_METRIC_DESTINATION;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private final String mDomainName;
    private final Map<String, MetricEvent> mEvents;
    private final TachyonMetricsFactory mMetricsFactory;

    static {
        ajc$preClinit();
        TAC_DEFAULT_METRIC_DESTINATION = MetricsDestination.DCM;
    }

    public MetricsManager(TachyonMetricsFactory tachyonMetricsFactory, String str) {
        JoinPoint makeJP = Factory.makeJP(ajc$tjp_0, this, this, tachyonMetricsFactory, str);
        try {
            this.mMetricsFactory = tachyonMetricsFactory;
            this.mDomainName = str;
            this.mEvents = new HashMap();
        } finally {
            MetricsManagerAspect.aspectOf().setMetricsManager(makeJP);
        }
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory factory = new Factory("MetricsManager.java", MetricsManager.class);
        ajc$tjp_0 = factory.makeSJP(JoinPoint.CONSTRUCTOR_EXECUTION, factory.makeConstructorSig("1", "com.amazon.comms.metrics.MetricsManager", "com.amazon.comms.metrics.TachyonMetricsFactory:java.lang.String", "tachyonMetricsFactoryImpl:domain", ""), 44);
        ajc$tjp_1 = factory.makeSJP(JoinPoint.CONSTRUCTOR_EXECUTION, factory.makeConstructorSig("1", "com.amazon.comms.metrics.MetricsManager", "android.content.Context:java.lang.String", "context:domain", ""), 50);
    }

    private MetricEvent getOrCreateMetricEvent(String str) {
        if (!hasMetricEvent(str)) {
            this.mEvents.put(str, this.mMetricsFactory.createMetricEvent(this.mDomainName, str));
        }
        return this.mEvents.get(str);
    }

    private boolean hasMetricEvent(String str) {
        return this.mEvents.containsKey(str);
    }

    public void addCounter(String str, String str2) {
        getOrCreateMetricEvent(str).addCounter(str2, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
    }

    public void addMetadata(String str, String str2, String str3) {
        getOrCreateMetricEvent(str).addString(str2, str3);
    }

    public void addTimer(String str, String str2) {
        getOrCreateMetricEvent(str).addTimer(str2, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
    }

    public MetricEvent createMetricEvent(String str) {
        return getOrCreateMetricEvent(str);
    }

    public void incrementCounter(String str, String str2) {
        incrementCounter(str, str2, 1.0d);
    }

    public void record(String str) {
        record(str, TAC_DEFAULT_METRIC_DESTINATION);
    }

    public void removeCounter(String str, String str2) {
        if (hasMetricEvent(str)) {
            getOrCreateMetricEvent(str).removeCounter(str2);
        }
    }

    public void removeTimer(String str, String str2) {
        if (hasMetricEvent(str)) {
            getOrCreateMetricEvent(str).removeTimer(str2);
        }
    }

    public void startTimer(String str, String str2) {
        getOrCreateMetricEvent(str).startTimer(str2);
    }

    public void stopTimer(String str, String str2) {
        if (hasMetricEvent(str)) {
            getOrCreateMetricEvent(str).stopTimer(str2);
        }
    }

    public void incrementCounter(String str, String str2, double d) {
        getOrCreateMetricEvent(str).incrementCounter(str2, d);
    }

    public void record(String str, MetricsDestination metricsDestination) {
        if (hasMetricEvent(str)) {
            this.mMetricsFactory.record(getOrCreateMetricEvent(str), metricsDestination);
        }
    }

    public void record(String str, MetricsMetadataProvider metricsMetadataProvider) {
        record(str, metricsMetadataProvider, TAC_DEFAULT_METRIC_DESTINATION);
    }

    public MetricsManager(Context context, String str) {
        JoinPoint makeJP = Factory.makeJP(ajc$tjp_1, this, this, context, str);
        try {
            this.mMetricsFactory = TachyonMetricsFactoryImpl.getInstance(context);
            this.mDomainName = str;
            this.mEvents = new HashMap();
        } finally {
            MetricsManagerAspect.aspectOf().setMetricsManager(makeJP);
        }
    }

    public void record(String str, MetricsMetadataProvider metricsMetadataProvider, MetricsDestination metricsDestination) {
        if (hasMetricEvent(str)) {
            MetricEvent orCreateMetricEvent = getOrCreateMetricEvent(str);
            HashMap hashMap = new HashMap();
            metricsMetadataProvider.provideMetricsMetadata(str, hashMap);
            for (Map.Entry entry : hashMap.entrySet()) {
                orCreateMetricEvent.addString((String) entry.getKey(), (String) entry.getValue());
            }
            this.mMetricsFactory.record(orCreateMetricEvent, metricsDestination);
        }
    }
}
