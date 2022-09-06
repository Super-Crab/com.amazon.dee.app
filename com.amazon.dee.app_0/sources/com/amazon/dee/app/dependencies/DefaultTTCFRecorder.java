package com.amazon.dee.app.dependencies;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.ttcf.TTCFRecord;
import com.amazon.alexa.ttcf.TTCFRecorder;
import com.amazon.alexa.ttcf.TTCFTimedRoute;
import com.amazon.alexa.ttcf.api.TTCFRoute;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.services.metrics.TTCFTrailingRoute;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsService;
import java.util.HashMap;
/* compiled from: TTCFModule.java */
/* loaded from: classes12.dex */
class DefaultTTCFRecorder implements TTCFRecorder {
    private static final String TAG = "DefaultTTCFRecorder";
    private final MetricsService metricsService;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultTTCFRecorder(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @Override // com.amazon.alexa.ttcf.TTCFRecorder
    public void record(TTCFRecord tTCFRecord) {
        String name = tTCFRecord.isInitial() ? tTCFRecord.getName() + "-init" : tTCFRecord.getName();
        record(AlexaMetricsConstants.MetricEvents.TTCF.ROUTING, name, Long.valueOf(tTCFRecord.getStartTime()), tTCFRecord.getRoutingEndTime());
        record(AlexaMetricsConstants.MetricEvents.TTCF.LOADING, name, tTCFRecord.getRoutingEndTime(), Long.valueOf(tTCFRecord.getReadyTime()));
        record(AlexaMetricsConstants.MetricEvents.TTCF.TTCF, name, Long.valueOf(tTCFRecord.getStartTime()), Long.valueOf(tTCFRecord.getReadyTime()));
        for (TTCFTimedRoute tTCFTimedRoute : tTCFRecord.getRoutings()) {
            TTCFRoute route = tTCFTimedRoute.getRoute();
            if (route instanceof TTCFTrailingRoute) {
                TTCFTrailingRoute tTCFTrailingRoute = (TTCFTrailingRoute) route;
                record(tTCFTrailingRoute.getMetricName(), name, Long.valueOf(tTCFTrailingRoute.getStartTime()), Long.valueOf(tTCFRecord.getReadyTime()));
            }
        }
    }

    private void record(@NonNull String str, @NonNull String str2, @Nullable Long l, @Nullable Long l2) {
        if (l != null && l2 != null) {
            long longValue = l2.longValue() - l.longValue();
            HashMap hashMap = new HashMap();
            hashMap.put("TimerValue", Long.valueOf(longValue));
            hashMap.put("EventTimestamp", l2);
            this.metricsService.recordCustom("Timer", str, str2, hashMap);
            String str3 = TAG;
            StringBuilder outline116 = GeneratedOutlineSupport1.outline116("TTCF did record ", str2, " ", str, " ");
            outline116.append(longValue);
            outline116.append("ms");
            Log.i(str3, outline116.toString());
            return;
        }
        GeneratedOutlineSupport1.outline164("TTCF did not record ", str, TAG);
    }
}
