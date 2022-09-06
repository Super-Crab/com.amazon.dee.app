package com.amazon.alexa.api;

import com.amazon.alexa.api.AlexaMobileFrameworkApisSpecification;
import com.amazon.alexa.client.annotations.NonNull;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class bk extends AlexaMobileFrameworkApisSpecification.Subcomponent implements ReadinessApi {
    /* JADX INFO: Access modifiers changed from: package-private */
    public bk(AlexaServicesConnection alexaServicesConnection, AtomicBoolean atomicBoolean, ConcurrentLinkedQueue<g> concurrentLinkedQueue) {
        super(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
    }

    @Override // com.amazon.alexa.api.ReadinessApi
    public void deregisterListener(@NonNull AlexaReadinessListener alexaReadinessListener) {
        deregisterCallbacksObject(alexaReadinessListener);
    }

    @Override // com.amazon.alexa.api.ReadinessApi
    public void registerListener(@NonNull final AlexaReadinessListener alexaReadinessListener) {
        registerCallbacksObject(alexaReadinessListener, new Runnable() { // from class: com.amazon.alexa.api.bk.1
            @Override // java.lang.Runnable
            public void run() {
                bj.a(bk.this.connection, alexaReadinessListener);
            }
        }, new Runnable() { // from class: com.amazon.alexa.api.bk.2
            @Override // java.lang.Runnable
            public void run() {
                bj.b(bk.this.connection, alexaReadinessListener);
            }
        });
    }
}
