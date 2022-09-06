package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.client.metrics.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.MetricEvent;
import com.amazon.client.metrics.MetricsFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class mn implements mu {
    private final MetricEvent vm;
    private final MetricsFactory vn;

    /* JADX INFO: Access modifiers changed from: package-private */
    public mn(Context context, String str, String str2) {
        this.vn = AndroidMetricsFactoryImpl.getInstance(context);
        this.vm = this.vn.createConcurrentMetricEvent(str, str2);
        this.vm.addCounter("MAPAPP_DCMFireOS_SUPPORTED", 1.0d);
        this.vn.record(this.vm);
        this.vm.clear();
    }

    private MetricEvent aC(String str, String str2) {
        return this.vn.createMetricEvent(str, str2);
    }

    @Override // com.amazon.identity.auth.device.mu
    public void a(String str, String str2, String... strArr) {
        MetricEvent aC = aC(str, str2);
        for (String str3 : strArr) {
            aC.incrementCounter(str3, 1.0d);
        }
        this.vn.record(aC);
    }

    @Override // com.amazon.identity.auth.device.mu
    public void b(String str, String... strArr) {
        this.vm.addCounter(str, 1.0d);
        if (strArr != null) {
            for (String str2 : strArr) {
                this.vm.addCounter(GeneratedOutlineSupport1.outline75(str, ":", str2), 1.0d);
            }
        }
    }

    @Override // com.amazon.identity.auth.device.mu
    public void bA(String str) {
        this.vm.incrementCounter(str, 1.0d);
    }

    @Override // com.amazon.identity.auth.device.mu
    public mv eO(String str) {
        return new mo(this.vm, str);
    }

    @Override // com.amazon.identity.auth.device.mu
    public void iJ() {
        this.vn.record(this.vm);
        this.vm.clear();
    }

    @Override // com.amazon.identity.auth.device.mu
    public void incrementCounter(String str, double d) {
        this.vm.incrementCounter(str, Math.round(d * 10.0d) / 10.0d);
    }

    @Override // com.amazon.identity.auth.device.mu
    public void a(String str, String str2, long j) {
        MetricEvent aC = aC(str, str2);
        aC.addTimer(str2, j);
        this.vn.record(aC);
    }
}
