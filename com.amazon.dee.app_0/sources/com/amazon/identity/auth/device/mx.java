package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.client.metrics.thirdparty.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.thirdparty.MetricEvent;
import com.amazon.client.metrics.thirdparty.MetricsFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class mx implements mu {
    private final MetricEvent mMetricEvent;
    private final MetricsFactory mMetricsFactory;

    /* JADX INFO: Access modifiers changed from: package-private */
    public mx(Context context, String str, String str2) {
        this.mMetricsFactory = AndroidMetricsFactoryImpl.getInstance(context);
        this.mMetricEvent = this.mMetricsFactory.createConcurrentMetricEvent(str, str2);
        this.mMetricEvent.addCounter("MAPAPP_DCMThirdParty_SUPPORTED", 1.0d);
        this.mMetricsFactory.record(this.mMetricEvent);
        this.mMetricEvent.clear();
    }

    private MetricEvent aH(String str, String str2) {
        return this.mMetricsFactory.createMetricEvent(str, str2);
    }

    @Override // com.amazon.identity.auth.device.mu
    public void a(String str, String str2, String... strArr) {
        MetricEvent aH = aH(str, str2);
        for (String str3 : strArr) {
            aH.incrementCounter(str3, 1.0d);
        }
        this.mMetricsFactory.record(aH);
    }

    @Override // com.amazon.identity.auth.device.mu
    public void b(String str, String... strArr) {
        this.mMetricEvent.addCounter(str, 1.0d);
        if (strArr != null) {
            for (String str2 : strArr) {
                this.mMetricEvent.addCounter(GeneratedOutlineSupport1.outline75(str, ":", str2), 1.0d);
            }
        }
    }

    @Override // com.amazon.identity.auth.device.mu
    public void bA(String str) {
        this.mMetricEvent.incrementCounter(str, 1.0d);
    }

    @Override // com.amazon.identity.auth.device.mu
    public mv eO(String str) {
        return new my(this.mMetricEvent, str);
    }

    @Override // com.amazon.identity.auth.device.mu
    public void iJ() {
        this.mMetricsFactory.record(this.mMetricEvent);
        this.mMetricEvent.clear();
    }

    @Override // com.amazon.identity.auth.device.mu
    public void incrementCounter(String str, double d) {
        this.mMetricEvent.incrementCounter(str, Math.round(d * 10.0d) / 10.0d);
    }

    @Override // com.amazon.identity.auth.device.mu
    public void a(String str, String str2, long j) {
        MetricEvent aH = aH(str, str2);
        aH.addTimer(str2, j);
        this.mMetricsFactory.record(aH);
    }
}
