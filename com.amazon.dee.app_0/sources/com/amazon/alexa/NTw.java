package com.amazon.alexa;

import com.amazon.alexa.api.AlexaMetricsName;
/* compiled from: DialogCancelReason.java */
/* loaded from: classes.dex */
public enum NTw implements pRk {
    PAUSE_CONTROL(AlexaMetricsName.VoiceInteraction.Cancelled.PAUSE_CONTROL),
    CANCEL_USER_INTERACTION(AlexaMetricsName.VoiceInteraction.Cancelled.CANCEL_USER_INTERACTION);
    
    public final AlexaMetricsName metricName;

    NTw(AlexaMetricsName alexaMetricsName) {
        this.metricName = alexaMetricsName;
    }

    @Override // com.amazon.alexa.pRk
    public Pmp getType() {
        return Pmp.CANCEL;
    }

    @Override // com.amazon.alexa.pRk
    public AlexaMetricsName zZm() {
        return this.metricName;
    }

    @Override // com.amazon.alexa.pRk
    public void zZm(boolean z) {
    }
}
