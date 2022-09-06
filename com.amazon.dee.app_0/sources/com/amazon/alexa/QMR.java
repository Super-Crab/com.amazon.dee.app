package com.amazon.alexa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: PackagesChangedBroadcastReceiver.java */
@Singleton
/* loaded from: classes.dex */
public class QMR extends BroadcastReceiver {
    public static final String zZm = "QMR";
    public final Context BIo;
    public final AlexaClientEventBus zQM;

    @Inject
    public QMR(Context context, AlexaClientEventBus alexaClientEventBus) {
        this.BIo = context;
        this.zQM = alexaClientEventBus;
    }

    public void BIo() {
        this.BIo.unregisterReceiver(this);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        char c;
        String action = intent.getAction();
        int hashCode = action.hashCode();
        if (hashCode != 525384130) {
            if (hashCode == 1544582882 && action.equals("android.intent.action.PACKAGE_ADDED")) {
                c = 0;
            }
            c = 65535;
        } else {
            if (action.equals("android.intent.action.PACKAGE_REMOVED")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            this.zQM.zyO(new jSY());
        } else if (c != 1) {
        } else {
            this.zQM.zyO(new C0191bkj(intent.getData() != null ? intent.getData().getEncodedSchemeSpecificPart() : ""));
        }
    }

    public void zZm() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        this.BIo.registerReceiver(this, intentFilter);
    }
}
