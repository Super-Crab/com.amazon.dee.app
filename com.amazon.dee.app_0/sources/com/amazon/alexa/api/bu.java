package com.amazon.alexa.api;

import com.amazon.alexa.api.AlexaMobileFrameworkApisSpecification;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class bu extends AlexaMobileFrameworkApisSpecification.Subcomponent implements SystemApi {
    private static final String a = "bu";

    /* JADX INFO: Access modifiers changed from: package-private */
    public bu(AlexaServicesConnection alexaServicesConnection, AtomicBoolean atomicBoolean, ConcurrentLinkedQueue<g> concurrentLinkedQueue) {
        super(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
    }

    @Override // com.amazon.alexa.api.SystemApi
    public void setBaseURLs(final AlexaBaseURLs alexaBaseURLs) {
        executeApi(new g() { // from class: com.amazon.alexa.api.bu.1
            @Override // com.amazon.alexa.api.g
            public void a() {
                System.setBaseURLs(bu.this.connection, alexaBaseURLs);
            }
        });
    }
}
