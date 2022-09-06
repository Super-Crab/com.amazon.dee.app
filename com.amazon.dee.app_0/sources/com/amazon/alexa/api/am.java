package com.amazon.alexa.api;

import com.amazon.alexa.api.AlexaMobileFrameworkApisSpecification;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class am extends AlexaMobileFrameworkApisSpecification.Subcomponent implements DialogApi {
    /* JADX INFO: Access modifiers changed from: package-private */
    public am(AlexaServicesConnection alexaServicesConnection, AtomicBoolean atomicBoolean, ConcurrentLinkedQueue<g> concurrentLinkedQueue) {
        super(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
    }

    @Override // com.amazon.alexa.api.DialogApi
    public void cancel() {
        executeApi(new g() { // from class: com.amazon.alexa.api.am.4
            @Override // com.amazon.alexa.api.g
            public void a() {
                aj.b(am.this.connection);
            }
        });
    }

    @Override // com.amazon.alexa.api.DialogApi
    public void start(final String str, final LaunchType launchType) {
        executeApi(new g() { // from class: com.amazon.alexa.api.am.1
            @Override // com.amazon.alexa.api.g
            public void a() {
                aj.a(am.this.connection, str, launchType);
            }
        });
    }

    @Override // com.amazon.alexa.api.DialogApi
    public void start(final String str, final LaunchType launchType, final AlexaUserInterfaceOptions alexaUserInterfaceOptions) {
        executeApi(new g() { // from class: com.amazon.alexa.api.am.2
            @Override // com.amazon.alexa.api.g
            public void a() {
                aj.a(am.this.connection, str, launchType, alexaUserInterfaceOptions);
            }
        });
    }

    @Override // com.amazon.alexa.api.DialogApi
    public void stop() {
        executeApi(new g() { // from class: com.amazon.alexa.api.am.3
            @Override // com.amazon.alexa.api.g
            public void a() {
                aj.a(am.this.connection);
            }
        });
    }
}
