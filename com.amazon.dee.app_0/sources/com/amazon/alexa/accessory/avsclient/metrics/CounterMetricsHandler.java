package com.amazon.alexa.accessory.avsclient.metrics;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsObserver;
import com.amazon.alexa.accessory.capabilities.metrics.AccessoryMetric;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.protocol.Metrics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public final class CounterMetricsHandler implements AccessoryMetricsObserver.Handler {
    private final String name;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CounterMetricsHandler(String str) {
        Preconditions.notNull(str, "name");
        this.name = str;
    }

    private static <T> void addValuesToMap(List<T> list, Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            double doubleValue = getDoubleValue(list.get(i));
            sb.append(doubleValue);
            if (i < list.size() - 1) {
                sb.append(AccessoryMetricsConstants.DELIMITER);
            }
            map.put(GeneratedOutlineSupport1.outline49(AccessoryMetricsConstants.VALUE_PREFIX, i), Double.valueOf(doubleValue));
        }
        map.put(AccessoryMetricsConstants.COMBINED_VALUES, sb.toString());
    }

    private static double getDoubleValue(Object obj) throws IllegalArgumentException {
        if (obj instanceof AccessoryMetric.Value) {
            AccessoryMetric.Value value = (AccessoryMetric.Value) obj;
            if (value.isInteger()) {
                return value.getInteger().intValue();
            }
            if (value.isDouble()) {
                return value.getDouble().doubleValue();
            }
        }
        if (obj instanceof Metrics.MetricValue) {
            Metrics.MetricValue metricValue = (Metrics.MetricValue) obj;
            if (metricValue.getValueCase() == Metrics.MetricValue.ValueCase.ANINTEGER) {
                return metricValue.getAnInteger();
            }
            if (metricValue.getValueCase() == Metrics.MetricValue.ValueCase.ADOUBLE) {
                return metricValue.getADouble();
            }
        }
        throw new IllegalArgumentException("Only integer and double values are valid for counter metrics.");
    }

    public static void handle(AccessoryMetric accessoryMetric, Map<String, Object> map, AccessoryMetricsService accessoryMetricsService) {
        String name = accessoryMetric.getName();
        List<AccessoryMetric.Value> values = accessoryMetric.getValues();
        try {
            addValuesToMap(values, map);
            accessoryMetricsService.recordCounter(name, "alexa_accessories", getDoubleValue(values.get(0)), map);
        } catch (IllegalArgumentException e) {
            Logger.e("AccessoryMetricsObserver dropping %s, %s", name, e.getMessage());
        }
    }

    @Override // com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsObserver.Handler
    public void handle(AccessorySession accessorySession, Metrics.MetricsEvent metricsEvent, Map<String, Object> map, AccessoryMetricsService accessoryMetricsService) {
        List<Metrics.MetricValue> valuesList = metricsEvent.getValuesList();
        if (valuesList.size() == 0) {
            Logger.e("AccessoryMetricsObserver dropping %s, counter metrics should contain at least one value.", this.name);
            return;
        }
        try {
            addValuesToMap(valuesList, map);
            accessoryMetricsService.recordCounter(this.name, "alexa_accessories", getDoubleValue(valuesList.get(0)), map);
        } catch (IllegalArgumentException unused) {
            Logger.e("AccessoryMetricsObserver dropping %s, only integer and double values are valid for this metric.", this.name);
        }
    }
}
