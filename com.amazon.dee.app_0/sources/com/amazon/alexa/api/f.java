package com.amazon.alexa.api;

import com.amazon.alexa.api.AlexaMobileFrameworkApisSpecification;
import com.amazon.alexa.api.AlexaServices;
import com.amazon.alexa.client.annotations.NonNull;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class f extends AlexaMobileFrameworkApisSpecification.Subcomponent implements AlertsApi {
    /* JADX INFO: Access modifiers changed from: package-private */
    public f(AlexaServicesConnection alexaServicesConnection, AtomicBoolean atomicBoolean, ConcurrentLinkedQueue<g> concurrentLinkedQueue) {
        super(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
    }

    @Override // com.amazon.alexa.api.AlertsApi
    public void deregisterListener(@NonNull AlertsListener alertsListener) {
        deregisterCallbacksObject(alertsListener);
    }

    @Override // com.amazon.alexa.api.AlertsApi
    public void registerListener(@NonNull final AlertsListener alertsListener) {
        registerCallbacksObject(alertsListener, new Runnable() { // from class: com.amazon.alexa.api.f.1
            @Override // java.lang.Runnable
            public void run() {
                AlexaServices.Alerts.registerListener(f.this.connection, alertsListener);
            }
        }, new Runnable() { // from class: com.amazon.alexa.api.f.2
            @Override // java.lang.Runnable
            public void run() {
                AlexaServices.Alerts.deregisterListener(f.this.connection, alertsListener);
            }
        });
    }
}
