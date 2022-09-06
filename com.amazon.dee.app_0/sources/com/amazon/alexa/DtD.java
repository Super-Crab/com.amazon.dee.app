package com.amazon.alexa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: PowerConnectionReceiver.java */
@Singleton
/* loaded from: classes.dex */
public class DtD extends BroadcastReceiver {
    public static final String zZm = "DtD";
    public final Context BIo;
    public final AlexaClientEventBus zQM;
    public boolean jiA = false;
    public final IntentFilter zyO = new IntentFilter();

    @Inject
    public DtD(Context context, AlexaClientEventBus alexaClientEventBus) {
        this.BIo = context;
        this.zQM = alexaClientEventBus;
        this.zyO.addAction("android.intent.action.BATTERY_CHANGED");
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
            if (intent.getIntExtra("status", -1) == 2) {
                this.zQM.zyO(new qgZ(true));
            } else {
                this.zQM.zyO(new qgZ(false));
            }
        }
    }
}
