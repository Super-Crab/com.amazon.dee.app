package com.amazon.alexa.accessory.capabilities.metrics;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.capabilities.metrics.AccessoryMetric;
import com.amazon.alexa.accessory.capabilities.metrics.MetricsCapability;
import com.amazon.alexa.accessory.internal.util.Int64Util;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Metrics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public final class DefaultMetricsListener implements MetricsCapability.PushMetricsListener {
    private final AccessorySession accessorySession;
    private final Map<Integer, Metrics.MetricDescriptor> metricMappings;
    private final MetricsObserver metricsObserver;

    /* renamed from: com.amazon.alexa.accessory.capabilities.metrics.DefaultMetricsListener$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Metrics$MetricType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Metrics$MetricValue$ValueCase = new int[Metrics.MetricValue.ValueCase.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Metrics$MetricValue$ValueCase[Metrics.MetricValue.ValueCase.AFLOAT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Metrics$MetricValue$ValueCase[Metrics.MetricValue.ValueCase.ADOUBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Metrics$MetricValue$ValueCase[Metrics.MetricValue.ValueCase.ASTRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Metrics$MetricValue$ValueCase[Metrics.MetricValue.ValueCase.ABOOLEAN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Metrics$MetricValue$ValueCase[Metrics.MetricValue.ValueCase.ANINTEGER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $SwitchMap$com$amazon$alexa$accessory$protocol$Metrics$MetricType = new int[Metrics.MetricType.values().length];
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Metrics$MetricType[Metrics.MetricType.COUNTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Metrics$MetricType[Metrics.MetricType.TIMER.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public DefaultMetricsListener(AccessorySession accessorySession, MetricsObserver metricsObserver) {
        Preconditions.notNull(accessorySession, "accessorySession");
        Preconditions.notNull(metricsObserver, "metricsObserver");
        this.accessorySession = accessorySession;
        this.metricsObserver = metricsObserver;
        this.metricMappings = new HashMap();
    }

    private AccessoryMetric processMetricEvent(Metrics.MetricsEvent metricsEvent) {
        Metrics.MetricDescriptor metricDescriptor = this.metricMappings.get(Integer.valueOf(metricsEvent.getKey()));
        if (metricDescriptor == null) {
            return null;
        }
        String name = metricDescriptor.getName();
        int destination = metricDescriptor.getDestination();
        long j = Int64Util.getLong(metricsEvent.getTimestampHi(), metricsEvent.getTimestampLo());
        long j2 = Int64Util.getLong(0, metricsEvent.getBootNum());
        long j3 = Int64Util.getLong(0, metricsEvent.getSeqNum());
        AccessoryMetric.Type processType = processType(metricDescriptor.getType());
        Logger.d("DefaultMetricListener.processMetricEvent: name: " + name + " with destination: " + destination + " at timestamp: " + j + " with bootNumber: " + j2 + " with sequenceNumber: " + j3 + " with type:" + metricDescriptor.getType().name());
        if (processType != null) {
            return new AccessoryMetric(name, j, destination, processType, processValues(metricsEvent.getValuesList()), j2, j3);
        }
        return null;
    }

    private AccessoryMetric.Type processType(Metrics.MetricType metricType) {
        int ordinal = metricType.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return AccessoryMetric.Type.TIMER;
            }
            return null;
        }
        return AccessoryMetric.Type.COUNTER;
    }

    private List<AccessoryMetric.Value> processValues(List<Metrics.MetricValue> list) {
        ArrayList arrayList = new ArrayList();
        for (Metrics.MetricValue metricValue : list) {
            int ordinal = metricValue.getValueCase().ordinal();
            if (ordinal == 0) {
                arrayList.add(new AccessoryMetric.Value(Boolean.valueOf(metricValue.getABoolean())));
            } else if (ordinal == 1) {
                arrayList.add(new AccessoryMetric.Value(metricValue.getAString()));
            } else if (ordinal == 2) {
                arrayList.add(new AccessoryMetric.Value(Integer.valueOf(metricValue.getAnInteger())));
            } else if (ordinal == 3) {
                arrayList.add(new AccessoryMetric.Value(Float.valueOf(metricValue.getAFloat())));
            } else if (ordinal == 4) {
                arrayList.add(new AccessoryMetric.Value(Double.valueOf(metricValue.getADouble())));
            }
        }
        return arrayList;
    }

    @Override // com.amazon.alexa.accessory.capabilities.metrics.MetricsCapability.PushMetricsListener
    public void onMetricsMapUpdated(Map<Integer, Metrics.MetricDescriptor> map) {
        Preconditions.mainThread();
        Preconditions.notNull(map, "metricMappings");
        this.metricMappings.putAll(map);
    }

    @Override // com.amazon.alexa.accessory.capabilities.metrics.MetricsCapability.PushMetricsListener
    public void onMetricsPushed(Collection<Metrics.MetricsEvent> collection) {
        Preconditions.mainThread();
        Preconditions.notNull(collection, "metricsEvents");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Metrics.MetricsEvent metricsEvent : collection) {
            AccessoryMetric processMetricEvent = processMetricEvent(metricsEvent);
            if (processMetricEvent != null) {
                arrayList2.add(processMetricEvent);
            } else {
                arrayList.add(metricsEvent);
            }
        }
        this.metricsObserver.onMetricsAvailable(this.accessorySession, arrayList2, arrayList);
    }
}
