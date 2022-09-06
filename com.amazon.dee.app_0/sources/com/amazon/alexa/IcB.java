package com.amazon.alexa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: DeviceLocaleChangedBroadcastReceiver.java */
@Singleton
/* loaded from: classes.dex */
public class IcB extends BroadcastReceiver {
    public static final String zZm = "IcB";
    public final Context BIo;
    public final AlexaClientEventBus zQM;

    @Inject
    public IcB(Context context, AlexaClientEventBus alexaClientEventBus) {
        this.BIo = context;
        this.zQM = alexaClientEventBus;
    }

    public void BIo() {
        this.BIo.unregisterReceiver(this);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.LOCALE_CHANGED".equals(intent.getAction())) {
            this.zQM.zyO(new wXE());
        }
    }

    public void zZm() {
        this.BIo.registerReceiver(this, new IntentFilter("android.intent.action.LOCALE_CHANGED"));
    }
}
