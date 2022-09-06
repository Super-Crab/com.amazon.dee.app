package com.amazon.alexa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
/* compiled from: UiEventBroadcastReceiver.java */
/* loaded from: classes.dex */
public class JOD extends BroadcastReceiver {
    public static final String zZm = "JOD";
    public final Context BIo;
    public final AlexaClientEventBus zQM;

    @Inject
    public JOD(Context context, AlexaClientEventBus alexaClientEventBus) {
        this.BIo = context;
        this.zQM = alexaClientEventBus;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (!"com.amazon.alexa.intent.action.PUBLISH_UI_EVENT".equals(intent.getAction())) {
            return;
        }
        String stringExtra = intent.getStringExtra("com.amazon.alexa.intent.extra.EVENT_NAME");
        if (stringExtra == null) {
            Log.e(zZm, "UI Event lacked an event name");
            return;
        }
        UiEventName uiEventName = null;
        try {
            uiEventName = UiEventName.valueOf(stringExtra);
        } catch (IllegalArgumentException unused) {
        }
        if (uiEventName == null) {
            GeneratedOutlineSupport1.outline162("Unknown metric name: ", stringExtra, zZm);
            return;
        }
        Bundle bundleExtra = intent.getBundleExtra("com.amazon.alexa.intent.extra.EVENT_DATA");
        if (bundleExtra == null) {
            bundleExtra = new Bundle();
        }
        this.zQM.zyO(new C0236vPD(uiEventName, bundleExtra));
    }
}
