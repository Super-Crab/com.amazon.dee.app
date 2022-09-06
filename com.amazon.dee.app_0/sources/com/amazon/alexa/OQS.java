package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: MediaBrowserClientFactory.java */
@Singleton
/* loaded from: classes.dex */
public class OQS {
    public final AlexaClientEventBus BIo;
    public final aim zQM;
    public final Context zZm;
    public final yjR zyO;

    @Inject
    public OQS(Context context, AlexaClientEventBus alexaClientEventBus, yjR yjr, aim aimVar) {
        this.zZm = context;
        this.BIo = alexaClientEventBus;
        this.zyO = yjr;
        this.zQM = aimVar;
    }

    public bYx zZm(yfS yfs, gSO gso, vkx vkxVar) {
        return new bYx(this.zZm, yfs, this.zyO, this.zQM, gso, vkxVar, this.BIo);
    }
}
