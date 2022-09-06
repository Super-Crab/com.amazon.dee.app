package com.amazon.alexa;

import com.amazon.alexa.api.AlexaDialogRequest;
import com.amazon.alexa.api.AlexaUserSpeechProvider;
import com.amazon.alexa.api.AlexaUserSpeechProviderMetadata;
import com.amazon.alexa.api.DialogData;
import com.amazon.alexa.api.DialogTurnData;
import com.amazon.alexa.api.ExtendedClient;
import java.util.Objects;
/* compiled from: ExternalUserSpeechProvider.java */
/* loaded from: classes.dex */
public class UWt implements mqw {
    public final AlexaUserSpeechProvider BIo;
    public final AlexaUserSpeechProviderMetadata zQM;
    public final ExtendedClient zZm;
    public final piE zyO;

    public UWt(ExtendedClient extendedClient, AlexaUserSpeechProvider alexaUserSpeechProvider, AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata) {
        this.zZm = extendedClient;
        this.BIo = alexaUserSpeechProvider;
        this.zQM = alexaUserSpeechProviderMetadata;
        this.zyO = piE.zZm(extendedClient, alexaUserSpeechProviderMetadata);
    }

    @Override // com.amazon.alexa.mqw
    public void BIo(qSf qsf) {
        this.BIo.onDialogFinished(DialogData.builder().setDialogId(qsf.getValue()).build());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && UWt.class == obj.getClass()) {
            return Objects.equals(this.BIo, ((UWt) obj).BIo);
        }
        return false;
    }

    @Override // com.amazon.alexa.mqw
    public ExtendedClient getClient() {
        return this.zZm;
    }

    @Override // com.amazon.alexa.mqw
    public AlexaUserSpeechProviderMetadata getMetadata() {
        return this.zQM;
    }

    public int hashCode() {
        return Objects.hash(this.BIo);
    }

    @Override // com.amazon.alexa.mqw
    public void pauseWakeWordDetection(String str) {
        this.BIo.pauseWakeWordDetection(str);
    }

    @Override // com.amazon.alexa.mqw
    public void resumeWakeWordDetection(String str) {
        this.BIo.resumeWakeWordDetection(str);
    }

    @Override // com.amazon.alexa.mqw
    public void setWakeWordDetectionEnabled(boolean z) {
        this.BIo.setWakeWordDetectionEnabled(z);
    }

    @Override // com.amazon.alexa.mqw
    public piE zZm() {
        return this.zyO;
    }

    @Override // com.amazon.alexa.mqw
    public void zZm(Jpo jpo, AlexaDialogRequest alexaDialogRequest) {
        this.BIo.onDialogRequested(jpo);
    }

    @Override // com.amazon.alexa.mqw
    public void zZm(yWg ywg) {
        this.BIo.onDialogTurnRequested(ywg);
    }

    @Override // com.amazon.alexa.mqw
    public void zZm(qSf qsf) {
        this.BIo.onDialogStarted(DialogData.builder().setDialogId(qsf.getValue()).build());
    }

    @Override // com.amazon.alexa.mqw
    public void BIo(XWx xWx) {
        this.BIo.onDialogTurnStarted(DialogTurnData.builder().setDialogTurnId(xWx.getValue()).build());
    }

    @Override // com.amazon.alexa.mqw
    public void zZm(XWx xWx) {
        this.BIo.onDialogTurnFinished(DialogTurnData.builder().setDialogTurnId(xWx.getValue()).build());
    }

    @Override // com.amazon.alexa.mqw
    public boolean BIo() {
        return this.zQM.supportsServerInitiatedDialogs();
    }
}
