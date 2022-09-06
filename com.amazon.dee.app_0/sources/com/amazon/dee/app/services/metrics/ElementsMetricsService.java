package com.amazon.dee.app.services.metrics;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricComponentName;
import com.dee.app.metrics.MetricDescriptor;
import com.dee.app.metrics.MetricName;
import com.dee.app.metrics.MetricsService;
import com.dee.app.metrics.MetricsServiceV2;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/* loaded from: classes12.dex */
public class ElementsMetricsService implements MetricsServiceV2 {
    private static final String TAG = Log.tag(ElementsMetricsService.class);
    private MetricsService metricsService;
    private Random rand;

    public ElementsMetricsService(@NonNull MetricsService metricsService) {
        this.metricsService = metricsService;
        this.rand = new Random();
    }

    private String getMetricNameWithType(@NonNull MetricDescriptor metricDescriptor, @NonNull MetricType metricType) {
        String metricName = metricDescriptor.getName().toString();
        if (metricType != MetricType.DATA) {
            StringBuilder outline108 = GeneratedOutlineSupport1.outline108(metricName, '.');
            outline108.append(metricType.toString());
            String sb = outline108.toString();
            int emissionFactor = metricDescriptor.getEmissionFactor();
            return emissionFactor < 100 ? GeneratedOutlineSupport1.outline49(sb, emissionFactor) : sb;
        }
        return metricName;
    }

    private void insertCountEntries(@NonNull Map<String, Object> map, double d) {
        map.put("EventNumericValue", Double.valueOf(d));
        map.put("CounterValue", Double.valueOf(d));
    }

    private void recordCustom(@NonNull MetricDescriptor metricDescriptor, @NonNull MetricType metricType, @NonNull String str) {
        if (!shouldLog(metricDescriptor)) {
            return;
        }
        MetricName name = metricDescriptor.getName();
        MetricComponentName componentName = metricDescriptor.getComponentName();
        if (TextUtils.isEmpty(name.getModule())) {
            Map<String, Object> customEntries = metricDescriptor.getCustomEntries();
            Log.e(TAG, "Empty module in metric name.");
            if (customEntries.containsKey("url")) {
                new Object[1][0] = customEntries.get("url");
            }
            name.setModule("unknown");
        }
        if (TextUtils.isEmpty(componentName.toString())) {
            Map<String, Object> customEntries2 = metricDescriptor.getCustomEntries();
            Log.e(TAG, "Empty category id in metric component name.");
            if (customEntries2.containsKey("url")) {
                new Object[1][0] = customEntries2.get("url");
            }
            componentName.setCategoryId("unknown");
        }
        if (TextUtils.isEmpty(name.getSource())) {
            name.setSource("native");
        }
        this.metricsService.recordCustom(str, getMetricNameWithType(metricDescriptor, metricType), metricDescriptor.getComponentName().toString(), new HashMap(metricDescriptor.getCustomEntries()));
    }

    private boolean shouldLog(@NonNull MetricDescriptor metricDescriptor) {
        int emissionFactor = metricDescriptor.getEmissionFactor();
        return emissionFactor == 100 || this.rand.nextInt(100) + 1 <= emissionFactor;
    }

    @Override // com.dee.app.metrics.MetricsServiceV2
    public void recordAvailable(@NonNull MetricDescriptor metricDescriptor) {
        insertCountEntries(metricDescriptor.getCustomEntries(), 1.0d);
        MetricType metricType = MetricType.AVAILABILITY;
        recordCustom(metricDescriptor, metricType, metricType.toString());
    }

    @Override // com.dee.app.metrics.MetricsServiceV2
    public void recordCount(@NonNull MetricDescriptor metricDescriptor, double d) {
        insertCountEntries(metricDescriptor.getCustomEntries(), d);
        recordCustom(metricDescriptor, MetricType.COUNT, "Counter");
    }

    @Override // com.dee.app.metrics.MetricsServiceV2
    public void recordData(@NonNull MetricDescriptor metricDescriptor, @NonNull Object obj) {
        metricDescriptor.getCustomEntries().put("EventValue", obj);
        recordCustom(metricDescriptor, MetricType.DATA, "Data");
    }

    @Override // com.dee.app.metrics.MetricsServiceV2
    public void recordFailure(@NonNull MetricDescriptor metricDescriptor, String str) {
        Map<String, Object> customEntries = metricDescriptor.getCustomEntries();
        customEntries.put("EventValue", str);
        insertCountEntries(customEntries, 1.0d);
        MetricType metricType = MetricType.FAULT;
        recordCustom(metricDescriptor, metricType, metricType.toString());
    }

    @Override // com.dee.app.metrics.MetricsServiceV2
    public void recordLimit(@NonNull MetricDescriptor metricDescriptor, boolean z) {
        insertCountEntries(metricDescriptor.getCustomEntries(), z ? 1.0d : FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        recordCustom(metricDescriptor, MetricType.LIMIT, AlexaMetricsConstants.EventTypes.EVENT_TYPE_OCCURRENCE);
    }

    @Override // com.dee.app.metrics.MetricsServiceV2
    public void recordSuccess(@NonNull MetricDescriptor metricDescriptor) {
        insertCountEntries(metricDescriptor.getCustomEntries(), FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        MetricType metricType = MetricType.FAULT;
        recordCustom(metricDescriptor, metricType, metricType.toString());
    }

    @Override // com.dee.app.metrics.MetricsServiceV2
    public void recordTime(@NonNull MetricDescriptor metricDescriptor, long j) {
        metricDescriptor.getCustomEntries().put("TimerValue", Long.valueOf(j));
        recordCustom(metricDescriptor, MetricType.TIME, "Timer");
    }

    @Override // com.dee.app.metrics.MetricsServiceV2
    public void recordUnavailable(@NonNull MetricDescriptor metricDescriptor) {
        insertCountEntries(metricDescriptor.getCustomEntries(), FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        MetricType metricType = MetricType.AVAILABILITY;
        recordCustom(metricDescriptor, metricType, metricType.toString());
    }

    @VisibleForTesting
    ElementsMetricsService(@NonNull MetricsService metricsService, Random random) {
        this.metricsService = metricsService;
        this.rand = random;
    }
}
