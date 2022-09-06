package com.amazon.alexa.client.alexaservice.eventing;

import com.amazon.alexa.Kqq;
import com.amazon.alexa.gSO;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes6.dex */
public abstract class AlexaClientEventBus {
    public final gSO BIo;
    public final EventBus zZm;

    public AlexaClientEventBus(EventBus eventBus, gSO gso) {
        this.zZm = eventBus;
        this.BIo = gso;
    }

    public abstract void BIo(Kqq kqq);

    public void BIo(Object obj) {
        this.zZm.unregister(obj);
    }

    public void jiA(Kqq kqq) {
        zQM(kqq);
        BIo(kqq);
    }

    public final boolean zQM(Kqq kqq) {
        kqq.zZm();
        return true;
    }

    public abstract void zZm(Kqq kqq);

    public void zZm(Object obj) {
        this.zZm.register(obj);
    }

    public void zyO(Kqq kqq) {
        zQM(kqq);
        zZm(kqq);
    }

    public boolean zZm(Class<?> cls) {
        return this.zZm.hasSubscriberForEvent(cls);
    }
}
