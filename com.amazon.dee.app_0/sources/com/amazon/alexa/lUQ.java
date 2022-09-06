package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: PreparingToSpeakInteraction.java */
/* loaded from: classes.dex */
public class lUQ extends zJO {
    public lUQ(AlexaClientEventBus alexaClientEventBus, vkx vkxVar) {
        super(alexaClientEventBus, vkxVar);
        alexaClientEventBus.zZm(this);
    }

    @Override // com.amazon.alexa.zJO
    public void LPk() {
        this.BIo.BIo(this);
        this.zQM.BIo(wSq.PREPARING_TO_SPEAK);
    }

    @Subscribe
    public void on(kOA koa) {
        JTe();
    }

    public void yPL() {
        JTe();
    }
}
