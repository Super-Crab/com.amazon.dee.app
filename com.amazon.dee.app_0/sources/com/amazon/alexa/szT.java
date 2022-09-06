package com.amazon.alexa;

import com.amazon.alexa.Ppr;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: OfflinePromptDownloadInitiator.java */
@Singleton
/* loaded from: classes.dex */
public class szT {
    public static final String zZm = "szT";
    public final JEn BIo;
    public final AlexaClientEventBus zQM;

    @Inject
    public szT(JEn jEn, AlexaClientEventBus alexaClientEventBus) {
        this.BIo = jEn;
        this.zQM = alexaClientEventBus;
        alexaClientEventBus.zZm(this);
    }

    @Subscribe
    public void on(tkb tkbVar) {
        ((C0217lcZ) this.BIo).zZm();
    }

    public void zZm() {
        this.zQM.BIo(this);
        ((C0217lcZ) this.BIo).BIo();
    }

    @Subscribe
    public void on(Ppr ppr) {
        IlB ilB = (IlB) ppr;
        if (!ilB.BIo.equals(Ppr.zZm.LOCALE) || !ilB.zQM) {
            return;
        }
        ((C0217lcZ) this.BIo).zZm();
    }
}
