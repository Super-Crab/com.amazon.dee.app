package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
/* compiled from: AlexaStateInteraction.java */
/* loaded from: classes.dex */
public abstract class zJO extends jDH {
    public final AlexaClientEventBus BIo;
    public final vkx zQM;
    public boolean zyO;

    public zJO(AlexaClientEventBus alexaClientEventBus, vkx vkxVar) {
        this.BIo = alexaClientEventBus;
        this.zQM = vkxVar;
    }

    @Override // com.amazon.alexa.ndD
    public dnp BIo() {
        return AvsApiConstants.SpeechRecognizer.zQM;
    }

    public void JTe() {
        if (!this.zyO) {
            this.zyO = true;
            LPk();
            this.BIo.zyO(LBB.zZm(zZm()));
        }
    }

    public abstract void LPk();

    @Override // com.amazon.alexa.jDH
    public void Qle() {
    }

    @Override // com.amazon.alexa.jDH
    public void jiA() {
    }

    @Override // com.amazon.alexa.ndD
    public void zQM() {
        JTe();
    }

    @Override // com.amazon.alexa.jDH
    public void zyO() {
        JTe();
    }
}
