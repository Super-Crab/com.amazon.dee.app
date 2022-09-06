package com.amazon.alexa;

import com.amazon.alexa.bluetooth.sco.ScoMetrics;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: AlexaServiceScoMetrics.java */
@Singleton
/* loaded from: classes.dex */
public class MLT implements ScoMetrics {
    public final AlexaClientEventBus zZm;

    @Inject
    public MLT(AlexaClientEventBus alexaClientEventBus) {
        this.zZm = alexaClientEventBus;
    }

    @Override // com.amazon.alexa.bluetooth.sco.ScoMetrics
    public void recordCount(String str, int i) {
        this.zZm.zyO(new RwV(str, i));
    }

    @Override // com.amazon.alexa.bluetooth.sco.ScoMetrics
    public void recordTime(String str, long j) {
        this.zZm.zyO(new wCw(str, j));
    }
}
