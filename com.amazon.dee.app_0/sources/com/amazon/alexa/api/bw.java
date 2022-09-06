package com.amazon.alexa.api;

import com.amazon.alexa.api.AlexaMobileFrameworkApisSpecification;
import com.amazon.alexa.client.annotations.NonNull;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class bw extends AlexaMobileFrameworkApisSpecification.Subcomponent implements TextApi {
    /* JADX INFO: Access modifiers changed from: package-private */
    public bw(AlexaServicesConnection alexaServicesConnection, AtomicBoolean atomicBoolean, ConcurrentLinkedQueue<g> concurrentLinkedQueue) {
        super(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
    }

    @Override // com.amazon.alexa.api.TextApi
    public void deregisterListener(@NonNull AlexaExpectTextListener alexaExpectTextListener) {
        deregisterCallbacksObject(alexaExpectTextListener);
    }

    @Override // com.amazon.alexa.api.TextApi
    public void deregisterListener(@NonNull AlexaTextResponseListener alexaTextResponseListener) {
        deregisterCallbacksObject(alexaTextResponseListener);
    }

    @Override // com.amazon.alexa.api.TextApi
    public void registerListener(@NonNull final AlexaExpectTextListener alexaExpectTextListener) {
        registerCallbacksObject(alexaExpectTextListener, new Runnable() { // from class: com.amazon.alexa.api.bw.5
            @Override // java.lang.Runnable
            public void run() {
                bv.a(bw.this.connection, alexaExpectTextListener);
            }
        }, new Runnable() { // from class: com.amazon.alexa.api.bw.6
            @Override // java.lang.Runnable
            public void run() {
                bv.b(bw.this.connection, alexaExpectTextListener);
            }
        });
    }

    @Override // com.amazon.alexa.api.TextApi
    public void registerListener(@NonNull final AlexaTextResponseListener alexaTextResponseListener) {
        registerCallbacksObject(alexaTextResponseListener, new Runnable() { // from class: com.amazon.alexa.api.bw.3
            @Override // java.lang.Runnable
            public void run() {
                bv.a(bw.this.connection, alexaTextResponseListener);
            }
        }, new Runnable() { // from class: com.amazon.alexa.api.bw.4
            @Override // java.lang.Runnable
            public void run() {
                bv.b(bw.this.connection, alexaTextResponseListener);
            }
        });
    }

    @Override // com.amazon.alexa.api.TextApi
    public void sendMessage(@NonNull final String str) {
        executeApi(new g() { // from class: com.amazon.alexa.api.bw.1
            @Override // com.amazon.alexa.api.g
            public void a() {
                bv.a(bw.this.connection, str, null);
            }
        });
    }

    @Override // com.amazon.alexa.api.TextApi
    public void sendMessage(@NonNull final String str, final TextAlexaDialogExtras textAlexaDialogExtras) {
        executeApi(new g() { // from class: com.amazon.alexa.api.bw.2
            @Override // com.amazon.alexa.api.g
            public void a() {
                bv.a(bw.this.connection, str, textAlexaDialogExtras);
            }
        });
    }
}
