package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: SpeechSynthesizerComponentStateAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class QeM extends Bwo implements xkq {
    public static final String BIo = "QeM";
    public long Qle;
    public C0176Pce jiA;
    public final dDK zQM;
    public zZm zyO;

    /* compiled from: SpeechSynthesizerComponentStateAuthority.java */
    /* loaded from: classes.dex */
    public enum zZm {
        PLAYING,
        FINISHED
    }

    @Inject
    public QeM(dDK ddk) {
        super(AvsApiConstants.SpeechSynthesizer.zZm, AvsApiConstants.SpeechSynthesizer.ComponentStates.SpeechState.zZm);
        this.zQM = ddk;
        BIo();
    }

    public final void BIo() {
        this.jiA = new C0176Pce("");
        this.Qle = 0L;
        this.zyO = zZm.FINISHED;
    }

    @Override // com.amazon.alexa.dRG
    public synchronized ComponentState getState() {
        zQM();
        return ComponentState.create(this.zZm, tui.zZm(this.jiA, this.Qle, this.zyO));
    }

    public final void jiA(bqj bqjVar) {
        this.jiA = bqjVar.mo947BIo();
        zQM();
        this.zyO = zZm.FINISHED;
    }

    @Override // com.amazon.alexa.xkq
    public synchronized void zQM(bqj bqjVar) {
        this.jiA = bqjVar.mo947BIo();
        this.Qle = 0L;
        this.zyO = zZm.PLAYING;
    }

    @Override // com.amazon.alexa.xkq
    public void zZm(bqj bqjVar) {
    }

    @Override // com.amazon.alexa.xkq
    public void zZm(bqj bqjVar, long j) {
        GeneratedOutlineSupport1.outline153("onSpeechStarted, startElapsedTime: ", j);
    }

    @Override // com.amazon.alexa.xkq
    public synchronized void zyO(bqj bqjVar) {
        jiA(bqjVar);
    }

    @Override // com.amazon.alexa.xkq
    public synchronized void zZm(bqj bqjVar, Exception exc) {
        jiA(bqjVar);
    }

    public final void zQM() {
        if (this.zyO == zZm.PLAYING) {
            long zZm2 = this.zQM.zZm();
            if (zZm2 == -1) {
                zZm2 = 0;
            }
            this.Qle = zZm2;
        }
    }

    @Override // com.amazon.alexa.xkq
    public synchronized void BIo(bqj bqjVar) {
        jiA(bqjVar);
    }
}
