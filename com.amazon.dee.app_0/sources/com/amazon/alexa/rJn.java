package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: StartDialogTurnCallbacksProvider.java */
@Singleton
/* loaded from: classes.dex */
public class rJn {
    public final shl BIo;
    public final yaw jiA;
    public final snw zQM;
    public final AlexaClientEventBus zZm;
    public final iKQ zyO;

    @Inject
    public rJn(AlexaClientEventBus alexaClientEventBus, shl shlVar, snw snwVar, iKQ ikq, yaw yawVar) {
        this.zZm = alexaClientEventBus;
        this.BIo = shlVar;
        this.zQM = snwVar;
        this.zyO = ikq;
        this.jiA = yawVar;
    }

    public yWg BIo(NEe nEe, esV esv, LjN ljN, XWx xWx) {
        return new yWg(this.zZm, esv, nEe, this.BIo, this.jiA, this.zQM, this.zyO, ljN, xWx);
    }

    public Jpo zZm(NEe nEe, esV esv, LjN ljN, XWx xWx) {
        return new Jpo(this.zZm, esv, nEe, this.BIo, this.jiA, this.zQM, this.zyO, ljN, xWx);
    }
}
