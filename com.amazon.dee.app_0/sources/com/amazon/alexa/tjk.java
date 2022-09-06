package com.amazon.alexa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: BecomingNoisyManager.java */
@Singleton
/* loaded from: classes.dex */
public class tjk extends BroadcastReceiver {
    public final QJr zQM;
    public final Context zyO;
    public static final IntentFilter zZm = new IntentFilter("android.media.AUDIO_BECOMING_NOISY");
    public static final String BIo = tjk.class.getSimpleName();

    @Inject
    public tjk(Context context, AlexaClientEventBus alexaClientEventBus, QJr qJr) {
        alexaClientEventBus.zZm(this);
        this.zyO = context;
        this.zQM = qJr;
    }

    @Subscribe
    public void onAudioPlaybackChangedEvent(qZM qzm) {
        StringBuilder zZm2 = C0179Pya.zZm("onAudioPlaybackChangedEvent: ");
        CYr cYr = (CYr) qzm;
        zZm2.append(cYr.BIo);
        zZm2.toString();
        int i = ulX.zZm[cYr.BIo.ordinal()];
        if (i != 1 && i != 2) {
            this.zyO.unregisterReceiver(this);
        } else {
            this.zyO.registerReceiver(this, zZm);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
            this.zQM.HvC();
        }
    }
}
