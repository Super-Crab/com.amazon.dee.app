package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: ThinkingInteraction.java */
/* loaded from: classes.dex */
public class DIi extends zJO {
    public static final String jiA = "DIi";
    public final OGm Qle;

    public DIi(AlexaClientEventBus alexaClientEventBus, vkx vkxVar, OGm oGm) {
        super(alexaClientEventBus, vkxVar);
        this.Qle = oGm;
        this.BIo.zZm(this);
    }

    @Override // com.amazon.alexa.zJO
    public void LPk() {
        this.BIo.BIo(this);
        this.zQM.BIo(wSq.THINKING);
    }

    @Override // com.amazon.alexa.zJO, com.amazon.alexa.jDH
    public void jiA() {
        if (this.Qle.LPk()) {
            JTe();
        }
    }

    @Subscribe
    public void on(ISQ isq) {
        JTe();
    }

    @Subscribe
    public void on(kOA koa) {
        JTe();
    }
}
