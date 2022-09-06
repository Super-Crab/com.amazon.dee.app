package com.amazon.alexa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: DeviceTimezoneChangedBroadcastReceiver.java */
@Singleton
/* loaded from: classes.dex */
public class XPi extends BroadcastReceiver {
    public static final String zZm = "XPi";
    public final Context BIo;
    public final AlexaClientEventBus zQM;

    @Inject
    public XPi(Context context, AlexaClientEventBus alexaClientEventBus) {
        this.BIo = context;
        this.zQM = alexaClientEventBus;
    }

    public void BIo() {
        this.BIo.unregisterReceiver(this);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.TIMEZONE_CHANGED".equals(intent.getAction())) {
            this.zQM.zyO(new GIl());
        }
    }

    public void zZm() {
        this.BIo.registerReceiver(this, new IntentFilter("android.intent.action.TIMEZONE_CHANGED"));
    }
}
