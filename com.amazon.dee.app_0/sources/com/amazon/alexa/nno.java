package com.amazon.alexa;

import com.amazon.alexa.audiocapturer.MetricsListener;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: MicrophoneInitializationMetricsListener.java */
@Singleton
/* loaded from: classes.dex */
public class nno implements MetricsListener {
    public final AlexaClientEventBus zZm;

    @Inject
    public nno(AlexaClientEventBus alexaClientEventBus) {
        this.zZm = alexaClientEventBus;
    }

    @Override // com.amazon.alexa.audiocapturer.MetricsListener
    public void onMicInitializationFailure() {
        this.zZm.zyO(new nEp());
    }

    @Override // com.amazon.alexa.audiocapturer.MetricsListener
    public void onMicInitializationSuccess(long j) {
        this.zZm.zyO(new zjk(j));
    }
}
