package com.amazon.alexa.api;

import com.amazon.alexa.api.AlexaMobileFrameworkApisSpecification;
import com.amazon.alexa.api.AlexaServices;
import com.amazon.alexa.client.annotations.NonNull;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class bf extends AlexaMobileFrameworkApisSpecification.Subcomponent implements MetricsApi {
    /* JADX INFO: Access modifiers changed from: package-private */
    public bf(AlexaServicesConnection alexaServicesConnection, AtomicBoolean atomicBoolean, ConcurrentLinkedQueue<g> concurrentLinkedQueue) {
        super(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
    }

    @Override // com.amazon.alexa.api.MetricsApi
    public void deregisterListener(@NonNull AlexaMetricsListener alexaMetricsListener) {
        deregisterCallbacksObject(alexaMetricsListener);
    }

    @Override // com.amazon.alexa.api.MetricsApi
    public void registerListener(@NonNull final AlexaMetricsListener alexaMetricsListener) {
        registerCallbacksObject(alexaMetricsListener, new Runnable() { // from class: com.amazon.alexa.api.bf.1
            @Override // java.lang.Runnable
            public void run() {
                AlexaServices.Metrics.registerMetricsListener(bf.this.connection, alexaMetricsListener);
            }
        }, new Runnable() { // from class: com.amazon.alexa.api.bf.2
            @Override // java.lang.Runnable
            public void run() {
                AlexaServices.Metrics.deregisterMetricsListener(bf.this.connection, alexaMetricsListener);
            }
        });
    }
}
