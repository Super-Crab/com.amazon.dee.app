package com.amazon.alexa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: VolumeChangeHandler.java */
@Singleton
/* loaded from: classes.dex */
public class KrR extends BroadcastReceiver {
    public static final IntentFilter BIo = new IntentFilter("android.media.VOLUME_CHANGED_ACTION");
    public static final String zZm = "KrR";
    public final Context zQM;
    public final EuG zyO;

    @Inject
    public KrR(Context context, EuG euG) {
        this.zQM = context;
        this.zyO = euG;
    }

    public void BIo() {
        this.zQM.unregisterReceiver(this);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")) {
            EuG euG = this.zyO;
            ScheduledFuture<?> scheduledFuture = euG.zQM;
            if (scheduledFuture != null && !scheduledFuture.isDone()) {
                euG.zQM.cancel(true);
            }
            euG.zQM = euG.BIo.schedule(euG.zZm.mo10268get(), 1L, TimeUnit.SECONDS);
        }
    }

    public void zZm() {
        this.zQM.registerReceiver(this, BIo);
    }
}
