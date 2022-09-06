package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.fuM;
import java.util.EnumSet;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: ListeningInteraction.java */
/* loaded from: classes.dex */
public class LUo extends zJO {
    public static final String jiA = "LUo";
    public final OGm JTe;
    public final DialogRequestIdentifier LPk;
    public final yqV Qle;

    public LUo(AlexaClientEventBus alexaClientEventBus, yqV yqv, vkx vkxVar, OGm oGm, DialogRequestIdentifier dialogRequestIdentifier) {
        super(alexaClientEventBus, vkxVar);
        this.BIo.zZm(this);
        this.Qle = yqv;
        this.JTe = oGm;
        this.LPk = dialogRequestIdentifier;
    }

    @Override // com.amazon.alexa.zJO
    public void LPk() {
        this.BIo.BIo(this);
        if (!EnumSet.of(wSq.THINKING, wSq.SPEAKING, wSq.IDLE).contains(this.zQM.zyO())) {
            this.BIo.zyO(mZe.zZm(aVo.DIALOG, new DIi(this.BIo, this.zQM, this.JTe), this.Qle.zZm(), this.LPk));
        }
        this.zQM.BIo(wSq.LISTENING);
        this.JTe.zZm(fuM.zyO.OTHER);
    }

    @Subscribe
    public void on(fuM.jiA jia) {
        JTe();
    }

    @Subscribe
    public void on(kOA koa) {
        this.zQM.BIo(wSq.THINKING);
        JTe();
    }
}
