package com.amazon.alexa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: LocationProviderChangedBroadcastReceiver.java */
@Singleton
/* loaded from: classes.dex */
public class ZIT extends BroadcastReceiver {
    public static final String zZm = "ZIT";
    public final Context BIo;
    public final AlexaClientEventBus zQM;

    @Inject
    public ZIT(Context context, AlexaClientEventBus alexaClientEventBus) {
        this.BIo = context;
        this.zQM = alexaClientEventBus;
    }

    public void BIo() {
        this.BIo.unregisterReceiver(this);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.location.PROVIDERS_CHANGED".equals(intent.getAction())) {
            this.zQM.zyO(new mpw());
        }
    }

    public void zZm() {
        this.BIo.registerReceiver(this, new IntentFilter("android.location.PROVIDERS_CHANGED"));
    }
}
