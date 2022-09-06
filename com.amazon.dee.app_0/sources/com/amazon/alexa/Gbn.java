package com.amazon.alexa;

import android.content.BroadcastReceiver;
import com.amazon.alexa.client.alexaservice.metrics.client.MetricsBroadcastReceiver;
/* compiled from: MetricsBroadcastReceiver.java */
/* loaded from: classes.dex */
public class Gbn implements Runnable {
    public final /* synthetic */ lfx BIo;
    public final /* synthetic */ BroadcastReceiver.PendingResult zQM;
    public final /* synthetic */ VZt zZm;
    public final /* synthetic */ MetricsBroadcastReceiver zyO;

    public Gbn(MetricsBroadcastReceiver metricsBroadcastReceiver, VZt vZt, lfx lfxVar, BroadcastReceiver.PendingResult pendingResult) {
        this.zyO = metricsBroadcastReceiver;
        this.zZm = vZt;
        this.BIo = lfxVar;
        this.zQM = pendingResult;
    }

    @Override // java.lang.Runnable
    public void run() {
        ((iQX) this.zyO.BIo).BIo(this.zZm, this.BIo);
        this.zQM.finish();
    }
}
