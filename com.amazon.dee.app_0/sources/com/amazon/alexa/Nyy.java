package com.amazon.alexa;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.QYV;
import com.amazon.alexa.VXG;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import com.amazon.dee.app.Manifest;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: HandsFreeTimestampSender.java */
@Singleton
/* loaded from: classes.dex */
public class Nyy {
    public static final ComponentName BIo = new ComponentName("com.amazon.dee.app", "com.amazon.alexa.handsfree.latencyreporter.LatencyReceiver");
    public static final String zZm = "Nyy";
    public final AlexaHandsFreeDeviceInformation jiA;
    public final Context zQM;
    public final TimeProvider zyO;

    /* compiled from: HandsFreeTimestampSender.java */
    /* loaded from: classes.dex */
    public enum zZm {
        NEW_DIALOG_REQUEST_TIME,
        AUDIO_START_TIME,
        PRYON_START_TIME,
        PRYON_FINISH_TIME,
        PRYON_RESET_TIME
    }

    @Inject
    public Nyy(@NonNull AlexaClientEventBus alexaClientEventBus, @NonNull Context context, AlexaHandsFreeDeviceInformation alexaHandsFreeDeviceInformation) {
        TimeProvider timeProvider = new TimeProvider();
        this.zQM = context;
        this.zyO = timeProvider;
        this.jiA = alexaHandsFreeDeviceInformation;
        if (zZm()) {
            alexaClientEventBus.zZm(this);
        }
    }

    @Subscribe
    public void on(@NonNull VXG.Qle qle) {
        zZm(zZm.PRYON_START_TIME, null, ((JTw) qle).zQM);
        zZm(zZm.PRYON_FINISH_TIME, null, this.zyO.currentTimeMillis());
    }

    public void zZm(@NonNull zZm zzm, @Nullable String str, long j) {
        if (zZm()) {
            Intent intent = new Intent("com.amazon.alexa.handsfree.intent.action.REPORT_TIMESTAMP_DATA_EVENT");
            intent.setComponent(BIo);
            intent.putExtra("timestampName", zzm.name());
            intent.putExtra("identifier", str);
            intent.putExtra("timestamp", j);
            this.zQM.sendBroadcast(intent, Manifest.permission.LATENCY_PERMISSION);
        }
    }

    @Subscribe
    public void on(@NonNull VXG.jiA jia) {
        zZm(zZm.PRYON_RESET_TIME, null, ((NNF) jia).BIo);
    }

    @Subscribe
    public void on(@NonNull QYV.zZm zzm) {
        zZm(zZm.NEW_DIALOG_REQUEST_TIME, null, this.zyO.currentTimeMillis());
    }

    public boolean zZm() {
        return this.jiA.isCurrentDeviceHandsFree();
    }
}
