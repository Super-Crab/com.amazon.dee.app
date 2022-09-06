package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.notifications.AlexaNotificationManager;
/* compiled from: AlexaNotificationManager.java */
/* loaded from: classes.dex */
public class kwY implements Runnable {
    public final /* synthetic */ AlexaNotificationManager zZm;

    public kwY(AlexaNotificationManager alexaNotificationManager) {
        this.zZm = alexaNotificationManager;
    }

    @Override // java.lang.Runnable
    public void run() {
        AlexaClientEventBus alexaClientEventBus;
        alexaClientEventBus = this.zZm.zyO;
        alexaClientEventBus.zyO(AgS.BIo());
    }
}
