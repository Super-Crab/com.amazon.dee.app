package com.amazon.alexa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.amazon.alexa.alertsca.AlertsIntentFactory;
import com.amazon.alexa.api.AlertType;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.dee.app.Manifest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: AlertsBroadcastReceiver.java */
@Singleton
/* loaded from: classes.dex */
public class QtV extends BroadcastReceiver {
    public static final String zZm = "QtV";
    public final Context BIo;
    public final AlexaClientEventBus zQM;

    @Inject
    public QtV(Context context, AlexaClientEventBus alexaClientEventBus) {
        this.BIo = context;
        this.zQM = alexaClientEventBus;
    }

    public void BIo() {
        this.BIo.unregisterReceiver(this);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        boolean z;
        String action = intent.getAction();
        Preconditions.notNull(action, "action is null");
        String str = "got an alerts intent: " + action;
        int hashCode = action.hashCode();
        if (hashCode == 1306375637) {
            if (action.equals("com.amazon.alexa.alertsca.intent.action.UPDATE_CAPABILITIES")) {
                z = true;
            }
            z = true;
        } else if (hashCode != 1864905392) {
            if (hashCode == 1877771260 && action.equals("com.amazon.alexa.alertsca.intent.action.ALERTS_STOPPED")) {
                z = true;
            }
            z = true;
        } else {
            if (action.equals("com.amazon.alexa.alertsca.intent.action.ALERTS_STARTED")) {
                z = false;
            }
            z = true;
        }
        if (!z) {
            try {
                AlexaClientEventBus alexaClientEventBus = this.zQM;
                String stringExtra = intent.getStringExtra(AlertsIntentFactory.ExtraKeys.ALERT_RECORD_ID);
                Preconditions.notNull(stringExtra, "alertId is null");
                alexaClientEventBus.zyO(new C0187aUD(stringExtra, AlertType.fromOrdinal(intent.getIntExtra(AlertsIntentFactory.ExtraKeys.ALERT_RECORD_TYPE, -1))));
            } catch (Exception e) {
                Log.e(zZm, "malformed alert intent", e);
            }
        } else if (!z) {
            if (!z) {
                GeneratedOutlineSupport1.outline164("Unrecognized action intent:", action, zZm);
            } else {
                this.zQM.zyO(new Wxy());
            }
        } else {
            try {
                AlexaClientEventBus alexaClientEventBus2 = this.zQM;
                String stringExtra2 = intent.getStringExtra(AlertsIntentFactory.ExtraKeys.ALERT_RECORD_ID);
                Preconditions.notNull(stringExtra2, "alertId is null");
                alexaClientEventBus2.zyO(new TGU(stringExtra2, AlertType.fromOrdinal(intent.getIntExtra(AlertsIntentFactory.ExtraKeys.ALERT_RECORD_TYPE, -1))));
            } catch (Exception e2) {
                Log.e(zZm, "malformed alert intent", e2);
            }
        }
    }

    public void zZm() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.amazon.alexa.alertsca.intent.action.ALERTS_STARTED");
        intentFilter.addAction("com.amazon.alexa.alertsca.intent.action.ALERTS_STOPPED");
        this.BIo.registerReceiver(this, intentFilter, Manifest.permission.AlertEvent, null);
    }
}
