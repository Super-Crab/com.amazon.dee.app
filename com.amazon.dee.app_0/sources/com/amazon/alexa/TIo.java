package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: RequestProcessingInteraction.java */
/* loaded from: classes.dex */
public class TIo extends zJO {
    public static final String jiA = "TIo";
    public final DialogRequestIdentifier Qle;

    public TIo(AlexaClientEventBus alexaClientEventBus, vkx vkxVar, DialogRequestIdentifier dialogRequestIdentifier) {
        super(alexaClientEventBus, vkxVar);
        this.Qle = dialogRequestIdentifier;
        alexaClientEventBus.zZm(this);
    }

    @Override // com.amazon.alexa.zJO
    public void LPk() {
        this.BIo.BIo(this);
        this.zQM.BIo(wSq.REQUEST_PROCESSING);
    }

    @Subscribe
    public void on(BQL bql) {
        if (((ISm) bql).BIo.equals(this.Qle)) {
            JTe();
        }
    }

    @Subscribe
    public void on(kOA koa) {
        JTe();
    }

    @Subscribe
    public void on(ISQ isq) {
        JTe();
    }
}
