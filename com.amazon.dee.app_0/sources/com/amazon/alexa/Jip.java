package com.amazon.alexa;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.alexa.TtM;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.fuM;
import java.util.Collection;
/* compiled from: SendRecognizeMessageCallback.java */
/* loaded from: classes.dex */
public class Jip extends UcG {
    public static final String zZm = "Jip";
    public final AlexaClientEventBus BIo;
    public final Ado Qle;
    public final OGm jiA;
    public final Wyh zQM;
    public final NEe zyO;
    public boolean JTe = false;
    public boolean LPk = false;
    public boolean yPL = false;

    public Jip(AlexaClientEventBus alexaClientEventBus, Wyh wyh, NEe nEe, OGm oGm, Ado ado) {
        this.BIo = alexaClientEventBus;
        this.zQM = wyh;
        this.zyO = nEe;
        this.jiA = oGm;
        this.Qle = ado;
    }

    @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
    public void onFailure(RrI rrI, @Nullable Integer num, @Nullable Exception exc) {
        this.BIo.zyO(kOA.BIo());
        this.zQM.zZm(this.zyO);
    }

    @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
    public void onMessageReceived(RrI rrI, Message message) {
        String str;
        boolean z = false;
        if (AvsApiConstants.zZm(AvsApiConstants.SpeechSynthesizer.zZm, AvsApiConstants.SpeechSynthesizer.Directives.Speak.zZm, message) && ((str = ((UqQ) message.getPayload()).zyO) == null || !TextUtils.isEmpty(str))) {
            this.LPk = true;
            zZm(true, this.yPL);
        }
        Header header = message.getHeader();
        if (zZm(header) && AvsApiConstants.SpeechRecognizer.Directives.ExpectSpeech.zZm.equals(header.getName())) {
            this.JTe = true;
            this.jiA.zZm();
        }
        if (zZm(header) && AvsApiConstants.SpeechRecognizer.Directives.SetEndOfSpeechOffset.zZm.equals(header.getName())) {
            z = true;
        }
        if (z) {
            this.yPL = true;
            zZm(this.LPk, true);
        }
    }

    @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
    public void onRequestDropped(RrI rrI, TtM.zZm zzm) {
        OGm oGm = this.jiA;
        if (oGm != null) {
            oGm.zZm(fuM.zyO.OTHER);
        }
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
        if (!this.JTe) {
            this.zQM.zZm(this.zyO);
        }
        if (!this.LPk || !this.yPL) {
            this.Qle.BIo();
        }
    }

    public final void zZm(boolean z, boolean z2) {
        if (!z || !z2) {
            return;
        }
        OGm oGm = this.jiA;
        if (oGm.JTe == null || oGm.noQ) {
            return;
        }
        StringBuilder zZm2 = C0179Pya.zZm("UPL estimation is expected for turn ");
        zZm2.append(oGm.Qle());
        zZm2.toString();
        oGm.vkx = true;
    }

    public final boolean zZm(Header header) {
        return AvsApiConstants.SpeechRecognizer.zZm.equals(header.getNamespace());
    }
}
