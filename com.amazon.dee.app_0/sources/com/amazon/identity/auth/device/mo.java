package com.amazon.identity.auth.device;

import android.text.TextUtils;
import com.amazon.client.metrics.MetricEvent;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class mo extends mv {
    private final MetricEvent vm;
    private String vo;
    private long vp = -1;
    private long vq = -1;
    private boolean vr = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public mo(MetricEvent metricEvent, String str) {
        this.vm = metricEvent;
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
            io.dm("FireOSPlatformDCPMetricsTimer");
        } else if (this.vr) {
            new StringBuilder("Timer already discarded : ").append(this.vo);
            io.dm("FireOSPlatformDCPMetricsTimer");
        } else {
            long j = this.vp;
            if (j < 0) {
                new StringBuilder("Timer not started : ").append(this.vo);
                io.dm("FireOSPlatformDCPMetricsTimer");
                return;
            }
            long j2 = this.vq;
            if (j2 <= 0) {
                j2 = System.nanoTime();
                j = this.vp;
            }
            this.vp = -1L;
            this.vq = -1L;
            this.vm.addTimer(this.vo, (j2 - j) / 1000000.0d);
        }
    }
}
