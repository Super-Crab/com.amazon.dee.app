package com.amazon.identity.auth.device;

import android.text.TextUtils;
import com.amazon.client.metrics.thirdparty.MetricEvent;
/* compiled from: DCP */
/* loaded from: classes12.dex */
class my extends mv {
    private final MetricEvent mMetricEvent;
    private String vo;
    private long vp = -1;
    private long vq = -1;
    private boolean vr = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public my(MetricEvent metricEvent, String str) {
        this.mMetricEvent = metricEvent;
        this.vo = str;
    }

    @Override // com.amazon.identity.auth.device.mv
    public void eP(String str) {
        this.vo = str;
    }

    @Override // com.amazon.identity.auth.device.mv
    public void iK() {
        this.vr = true;
    }

    @Override // com.amazon.identity.auth.device.mv
    public void iL() {
        stop();
        iK();
    }

    @Override // com.amazon.identity.auth.device.mv
    public void iM() {
        this.vq = System.nanoTime();
    }

    @Override // com.amazon.identity.auth.device.mv
    public void start() {
        this.vp = System.nanoTime();
    }

    @Override // com.amazon.identity.auth.device.mv
    public void stop() {
        if (TextUtils.isEmpty(this.vo)) {
            io.dm("ThirdPartyPlatformDCPMetricsTimer");
        } else if (this.vr) {
            new StringBuilder("Timer already discarded : ").append(this.vo);
            io.dm("ThirdPartyPlatformDCPMetricsTimer");
        } else {
            long j = this.vp;
            if (j < 0) {
                new StringBuilder("Timer not started : ").append(this.vo);
                io.dm("ThirdPartyPlatformDCPMetricsTimer");
                return;
            }
            long j2 = this.vq;
            if (j2 <= 0) {
                j2 = System.nanoTime();
                j = this.vp;
            }
            this.vp = -1L;
            this.vq = -1L;
            this.mMetricEvent.addTimer(this.vo, (j2 - j) / 1000000.0d);
        }
    }
}
