package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: WakeWordMetricsListener.java */
@Singleton
/* loaded from: classes.dex */
public class FdV implements WakeWordDetectionMetricsListener {
    public final AlexaClientEventBus zZm;

    @Inject
    public FdV(AlexaClientEventBus alexaClientEventBus) {
        this.zZm = alexaClientEventBus;
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener
    public void onLocaleMismatch() {
        this.zZm.zyO(new uLm());
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener
    public void onPryonErrorEvent(int i) {
        this.zZm.zyO(new CQJ(i));
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener
    public void onPryonInitializationFailure(int i, String str) {
        this.zZm.zyO(new kBD(i, str));
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener
    public void onPryonInitializationSuccess(long j, String str) {
        this.zZm.zyO(new ZBr(j, str));
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener
    public void onPryonReset(long j) {
        this.zZm.zyO(new NNF(j));
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener
    public void onWakeWordModelDownloadFailure(long j, String str, String str2) {
        this.zZm.zyO(new Lhs(j, str, str2));
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener
    public void onWakeWordModelDownloadInterrupted(long j) {
        this.zZm.zyO(new UBQ(j));
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener
    public void onWakeWordModelDownloadSuccess(long j, String str) {
        this.zZm.zyO(new NUX(j, str));
    }
}
