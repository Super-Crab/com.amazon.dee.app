package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.MessageIdentifier;
import com.amazon.alexa.client.core.messages.MessageMetadata;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import java.util.List;
/* compiled from: SpeechInteraction.java */
/* loaded from: classes.dex */
public class WvJ extends zJO implements xkq {
    public static final String jiA = "WvJ";
    public final shl JTe;
    public final Wyh LPk;
    public final MessageMetadata Mlj;
    public final xkq Qle;
    public final NEe yPL;

    public WvJ(AlexaClientEventBus alexaClientEventBus, QeM qeM, vkx vkxVar, shl shlVar, Wyh wyh, @Nullable NEe nEe, MessageMetadata messageMetadata) {
        super(alexaClientEventBus, vkxVar);
        this.Qle = qeM;
        this.JTe = shlVar;
        this.LPk = wyh;
        this.yPL = nEe;
        this.Mlj = messageMetadata;
    }

    @Override // com.amazon.alexa.zJO, com.amazon.alexa.ndD
    public dnp BIo() {
        return AvsApiConstants.SpeechSynthesizer.zQM;
    }

    @Override // com.amazon.alexa.zJO
    public void JTe() {
        if (!this.zyO) {
            this.zyO = true;
            this.zQM.BIo(wSq.SPEAKING);
            NEe nEe = this.yPL;
            if (nEe != null && nEe.wDP()) {
                return;
            }
            this.BIo.zyO(LBB.zZm(this.zZm));
        }
    }

    @Override // com.amazon.alexa.zJO
    public void LPk() {
        this.zQM.BIo(wSq.SPEAKING);
    }

    public void Mlj() {
        StringBuilder zZm = C0179Pya.zZm("Stopping Interaction ");
        zZm.append(this.zZm);
        zZm.toString();
        zZm(true);
    }

    @Override // com.amazon.alexa.zJO, com.amazon.alexa.jDH
    public void Qle() {
    }

    public boolean yPL() {
        throw null;
    }

    @Override // com.amazon.alexa.zJO, com.amazon.alexa.ndD
    public void zQM() {
        Mlj();
    }

    @Override // com.amazon.alexa.xkq
    public void zZm(bqj bqjVar) {
    }

    @Override // com.amazon.alexa.xkq
    public void zZm(bqj bqjVar, long j) {
        DialogRequestIdentifier dialogRequestIdentifier = bqjVar.LPk;
        if (dialogRequestIdentifier != null) {
            OGm BIo = this.LPk.BIo(dialogRequestIdentifier);
            XWx xWx = XWx.zZm;
            if (BIo != null) {
                xWx = BIo.Qle();
            }
            this.BIo.zyO(new odt(bqjVar.LPk, xWx, j));
        }
    }

    public void zZm(bqj bqjVar, MessageProcessingCallbacks messageProcessingCallbacks) {
        throw null;
    }

    public void zZm(boolean z) {
        throw null;
    }

    @Override // com.amazon.alexa.zJO, com.amazon.alexa.jDH
    public void zyO() {
        Mlj();
    }

    @Override // com.amazon.alexa.xkq
    public void BIo(bqj bqjVar) {
        this.Qle.BIo(bqjVar);
    }

    @Override // com.amazon.alexa.xkq
    public void zQM(bqj bqjVar) {
        this.Qle.zQM(bqjVar);
        DialogRequestIdentifier dialogRequestIdentifier = bqjVar.LPk;
        if (dialogRequestIdentifier != null) {
            OGm BIo = this.LPk.BIo(dialogRequestIdentifier);
            XWx xWx = XWx.zZm;
            if (BIo != null) {
                xWx = BIo.Qle();
            }
            this.BIo.zyO(new sVH(bqjVar.LPk, xWx));
        }
    }

    public void zZm(List<bqj> list) {
        for (bqj bqjVar : list) {
            if (bqjVar.zQM()) {
                this.JTe.BIo(bqjVar.zZm());
            }
        }
    }

    public JjI zZm(Name name, C0176Pce c0176Pce) {
        return JjI.BIo().zZm(Message.create(Header.builder().setMessageIdentifier(MessageIdentifier.createRandom()).setName(name).setNamespace(AvsApiConstants.SpeechSynthesizer.zZm).build(), yXU.zZm(c0176Pce), this.Mlj)).zZm();
    }
}
