package com.amazon.alexa.accessory.avsclient.metrics;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsObserver;
import com.amazon.alexa.accessory.capabilities.metrics.AccessoryMetric;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.protocol.Metrics;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public final class TimerMetricsHandler implements AccessoryMetricsObserver.Handler {
    private final String name;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimerMetricsHandler(String str) {
        Preconditions.notNull(str, "name");
        this.name = str;
    }

    public static void handle(AccessoryMetric accessoryMetric, Map<String, Object> map, AccessoryMetricsService accessoryMetricsService) {
        String name = accessoryMetric.getName();
        List<AccessoryMetric.Value> values = accessoryMetric.getValues();
        if (values.size() == 1 && values.get(0).isInteger()) {
            accessoryMetricsService.recordTime(name, "alexa_accessories", values.get(0).getInteger().intValue(), map);
        } else {
            Logger.e("AccessoryMetricsObserver dropping %s, timer metrics should contain exactly one integer value.", name);
        }
    }

    @Override // com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsObserver.Handler
    public void handle(AccessorySession accessorySession, Metrics.MetricsEvent metricsEvent, Map<String, Object> map, AccessoryMetricsService accessoryMetricsService) {
        List<Metrics.MetricValue> valuesList = metricsEvent.getValuesList();
        if (valuesList.size() != 1) {
            Logger.e("AccessoryMetricsObserver dropping %s, timer metrics should contain exactly one value, had %d", this.name, Integer.valueOf(valuesList.size()));
            return;
        }
        Metrics.MetricValue metricValue = valuesList.get(0);
        if (metricValue.getValueCase() != Metrics.MetricValue.ValueCase.ANINTEGER) {
            Logger.e("AccessoryMetricsObserver dropping %s, only integer values are valid for this metric.", this.name);
        } else {
            accessoryMetricsService.recordTime(this.name, "alexa_accessories", metricValue.getAnInteger(), map);
        }
    }
}
