package com.amazon.alexa;

import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.voice.metrics.VoxMetricEventName;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: EventBroadcastSender.java */
@Singleton
/* loaded from: classes.dex */
public class pbo {
    public static final String zZm = "pbo";
    public final Context BIo;
    public final AlexaClientEventBus zQM;

    @Inject
    public pbo(Context context, AlexaClientEventBus alexaClientEventBus) {
        this.BIo = context;
        this.zQM = alexaClientEventBus;
        alexaClientEventBus.zZm(this);
    }

    @Subscribe
    public void on(BWd bWd) {
        zZm("amazon.alexa.intent.action.REPORT_METRIC", VoxMetricEventName.INGRESS_IN_APP_WAKEWORD_OCCUR, ((ocm) bWd).BIo);
    }

    public void zZm() {
        this.zQM.BIo(this);
    }

    public final void zZm(String str, String str2, long j) {
        Intent intent = new Intent(str);
        intent.putExtra("amazon.alexa.intent.extras.EVENT_NAME", str2);
        intent.putExtra("amazon.alexa.intent.extras.EVENT_TIMESTAMP", j);
        intent.setPackage(this.BIo.getPackageName());
        this.BIo.sendBroadcast(intent);
    }

    @Subscribe
    public void on(EVw eVw) {
        zZm("amazon.alexa.intent.action.REPORT_METRIC", "EXTERNAL_WAKEWORD_OCCUR", -1L);
    }
}
