package com.amazon.alexa;

import com.amazon.alexa.api.AlexaDialogRequest;
import com.amazon.alexa.api.AlexaDialogTurnStopCallback;
import com.amazon.alexa.api.AlexaSupportedInitiationType;
import com.amazon.alexa.api.AlexaUserSpeechProviderMetadata;
import com.amazon.alexa.api.AlexaUserSpeechProviderScope;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.kbp;
import java.util.Collections;
/* compiled from: InternalTextProvider.java */
/* loaded from: classes.dex */
public class iZJ implements tMo {
    public static final AlexaUserSpeechProviderMetadata BIo = AlexaUserSpeechProviderMetadata.create(Collections.singleton(AlexaSupportedInitiationType.TAP_TO_TALK), Collections.emptySet(), AlexaUserSpeechProviderScope.APPLICATION);
    public static final String zZm = "iZJ";
    public final String jiA;
    public final zZm zQM = new zZm(null);
    public final eOP zyO;

    /* compiled from: InternalTextProvider.java */
    /* loaded from: classes.dex */
    private static class zZm implements AlexaDialogTurnStopCallback {
        public /* synthetic */ zZm(ihm ihmVar) {
        }

        @Override // com.amazon.alexa.api.AlexaDialogTurnStopCallback
        public void stopRecording() {
        }
    }

    public iZJ(@NonNull String str, @NonNull eOP eop) {
        this.jiA = str;
        this.zyO = eop;
    }

    public void BIo(qSf qsf) {
        String str = "Dialog " + qsf + " started";
    }

    public void zZm(Jpo jpo, AlexaDialogRequest alexaDialogRequest) {
        String str = this.jiA;
        zZm zzm = this.zQM;
        eOP eop = this.zyO;
        OGm zZm2 = jpo.Qle.zZm(jpo.zZm, jpo.BIo, jpo.JTe.zZm(str), jpo.LPk, jpo.yPL, zzm, null);
        if (jpo.zQM.zZm(zZm2)) {
            jpo.zZm.zyO(new Arb(jpo.zQM, eop));
        } else {
            jpo.zZm.zyO(kbp.zZm.zZm(zZm2.Qle(), null, jpo.zZm() ? mMl.OUT_OF_TURN_UNEXPECTED_TURN : mMl.OUT_OF_TURN_UNEXPECTED_NEXT_TURN, true));
        }
    }

    public void zZm(yWg ywg) {
    }

    public void BIo(XWx xWx) {
        String str = "Dialog turn " + xWx + " started";
    }

    public void zZm(qSf qsf) {
        String str = "Dialog " + qsf + " finished";
    }

    public void zZm(XWx xWx) {
        String str = "Dialog turn " + xWx + " finished";
    }

    public String zZm() {
        return BIo.getSoftwareVersion();
    }
}
