package com.amazon.alexa.api;

import com.amazon.alexa.api.AlexaMobileFrameworkApisSpecification;
import com.amazon.alexa.client.annotations.NonNull;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class ce extends AlexaMobileFrameworkApisSpecification.Subcomponent implements VisualTaskApi {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ce(AlexaServicesConnection alexaServicesConnection, AtomicBoolean atomicBoolean, ConcurrentLinkedQueue<g> concurrentLinkedQueue) {
        super(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
    }

    @Override // com.amazon.alexa.api.VisualTaskApi
    public void schedule(@NonNull final AlexaVisualTask alexaVisualTask) {
        executeApi(new g() { // from class: com.amazon.alexa.api.ce.1
            @Override // com.amazon.alexa.api.g
            public void a() {
                cd.a(ce.this.connection, alexaVisualTask);
            }
        });
    }

    @Override // com.amazon.alexa.api.VisualTaskApi
    public void unschedule(@NonNull final AlexaVisualTask alexaVisualTask) {
        executeApi(new g() { // from class: com.amazon.alexa.api.ce.2
            @Override // com.amazon.alexa.api.g
            public void a() {
                cd.b(ce.this.connection, alexaVisualTask);
            }
        });
    }
}
