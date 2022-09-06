package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: OfflinePromptsDownloadMetricsListener.java */
@Singleton
/* loaded from: classes.dex */
public class Gju implements rOz {
    public final AlexaClientEventBus zZm;

    @Inject
    public Gju(AlexaClientEventBus alexaClientEventBus) {
        this.zZm = alexaClientEventBus;
    }
}
