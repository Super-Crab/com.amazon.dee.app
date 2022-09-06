package com.amazon.alexa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: BatteryLevelReceiver.java */
@Singleton
/* loaded from: classes.dex */
public class ktr extends BroadcastReceiver {
    public static final String zZm = "ktr";
    public final Context BIo;
    public final AlexaClientEventBus zQM;
    public boolean jiA = false;
    public final IntentFilter zyO = new IntentFilter();

    @Inject
    public ktr(Context context, AlexaClientEventBus alexaClientEventBus) {
        this.BIo = context;
        this.zQM = alexaClientEventBus;
        this.zyO.addAction("android.intent.action.BATTERY_CHANGED");
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
            int intExtra = (int) ((intent.getIntExtra(ModelTransformer.KEY_BATTERY_LEVEL, -1) / intent.getIntExtra(ModelTransformer.KEY_BATTERY_SCALE, -1)) * 100.0d);
            if (intExtra <= 20 && intExtra >= 0) {
                GeneratedOutlineSupport1.outline149("The device's power is low, battery level is ", intExtra);
                this.zQM.zyO(new rQh(true));
            } else if (intExtra <= 20 || intExtra > 100) {
            } else {
                GeneratedOutlineSupport1.outline149("The device's power is okay, battery level is ", intExtra);
                this.zQM.zyO(new rQh(false));
            }
        }
    }
}
