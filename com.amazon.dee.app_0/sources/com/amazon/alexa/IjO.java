package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.TtM;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.messages.Message;
import java.util.Collection;
/* compiled from: SendTextMessageCallback.java */
/* loaded from: classes.dex */
public class IjO extends UcG {
    public static final String zZm = "IjO";
    public final AlexaClientEventBus BIo;
    public final OGm jiA;
    public final Wyh zQM;
    public final NEe zyO;

    public IjO(AlexaClientEventBus alexaClientEventBus, Wyh wyh, NEe nEe, OGm oGm) {
        this.BIo = alexaClientEventBus;
        this.zQM = wyh;
        this.zyO = nEe;
        this.jiA = oGm;
    }

    @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
    public void onFailure(RrI rrI, @Nullable Integer num, @Nullable Exception exc) {
        this.BIo.zyO(kOA.BIo());
        this.zQM.zZm(this.zyO);
    }

    @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
    public void onMessageReceived(RrI rrI, Message message) {
        this.jiA.zZm();
    }

    @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
    public void onRequestDropped(RrI rrI, TtM.zZm zzm) {
        this.BIo.zyO(kOA.BIo());
        this.zQM.zZm(this.zyO);
    }

    @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
    public void onSuccess(RrI rrI, Collection<Message> collection) {
        StringBuilder zZm2 = C0179Pya.zZm("send succeeded ");
        zZm2.append(this.zyO);
        zZm2.append(" ");
        zZm2.append(this.jiA);
        zZm2.toString();
        this.zQM.zZm(this.zyO);
    }
}
