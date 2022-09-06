package com.amazon.alexa.api;

import com.amazon.alexa.api.AlexaMobileFrameworkApisSpecification;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class cg extends AlexaMobileFrameworkApisSpecification.Subcomponent implements WakeWordApi {
    private static final Object a = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    public cg(AlexaServicesConnection alexaServicesConnection, AtomicBoolean atomicBoolean, ConcurrentLinkedQueue<g> concurrentLinkedQueue) {
        super(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
    }

    private void a(@Nullable final AlexaDialogExtras alexaDialogExtras) {
        registerCallbacksObject(a, new Runnable() { // from class: com.amazon.alexa.api.cg.3
            @Override // java.lang.Runnable
            public void run() {
                cf.a(cg.this.connection, alexaDialogExtras);
            }
        }, new Runnable() { // from class: com.amazon.alexa.api.cg.4
            @Override // java.lang.Runnable
            public void run() {
                cf.a(cg.this.connection);
            }
        });
    }

    private void a(final List<String> list, final AlexaApiCallbacks alexaApiCallbacks) {
        executeApi(new g(alexaApiCallbacks) { // from class: com.amazon.alexa.api.cg.5
            @Override // com.amazon.alexa.api.g
            public void a() throws Exception {
                cf.a(cg.this.connection, list, alexaApiCallbacks);
            }
        });
    }

    @Override // com.amazon.alexa.api.WakeWordApi
    public void deregisterWakeWordListener(@NonNull AlexaWakeWordListener alexaWakeWordListener) {
        deregisterCallbacksObject(alexaWakeWordListener);
    }

    @Override // com.amazon.alexa.api.WakeWordApi
    @Deprecated
    public void disable() {
        deregisterCallbacksObject(a);
    }

    @Override // com.amazon.alexa.api.WakeWordApi
    @Deprecated
    public void disable(@NonNull String str) {
        deregisterCallbacksObject(str);
    }

    @Override // com.amazon.alexa.api.WakeWordApi
    @Deprecated
    public void enable() {
        a(null);
    }

    @Override // com.amazon.alexa.api.WakeWordApi
    @Deprecated
    public void enable(@NonNull AlexaDialogExtras alexaDialogExtras) {
        a(alexaDialogExtras);
    }

    @Override // com.amazon.alexa.api.WakeWordApi
    @Deprecated
    public void enable(@NonNull String str) {
        a(null);
    }

    @Override // com.amazon.alexa.api.WakeWordApi
    @Deprecated
    public void enable(@NonNull String str, @NonNull AlexaDialogExtras alexaDialogExtras) {
        a(alexaDialogExtras);
    }

    @Override // com.amazon.alexa.api.WakeWordApi
    public void registerWakeWordListener(@NonNull final AlexaWakeWordListener alexaWakeWordListener) {
        registerCallbacksObject(alexaWakeWordListener, new Runnable() { // from class: com.amazon.alexa.api.cg.1
            @Override // java.lang.Runnable
            public void run() {
                cf.a(cg.this.connection, alexaWakeWordListener);
            }
        }, new Runnable() { // from class: com.amazon.alexa.api.cg.2
            @Override // java.lang.Runnable
            public void run() {
                cf.b(cg.this.connection, alexaWakeWordListener);
            }
        });
    }

    @Override // com.amazon.alexa.api.WakeWordApi
    public void setWakeWords(@NonNull List<String> list) {
        a(list, null);
    }

    @Override // com.amazon.alexa.api.WakeWordApi
    public void setWakeWords(@NonNull List<String> list, @NonNull AlexaApiCallbacks alexaApiCallbacks) {
        a(list, alexaApiCallbacks);
    }

    @Override // com.amazon.alexa.api.WakeWordApi
    public void startListening() {
        a(null);
    }

    @Override // com.amazon.alexa.api.WakeWordApi
    public void startListening(@NonNull AlexaApiCallbacks alexaApiCallbacks) {
        throw new RuntimeException("startListening(callbacks) is not implemented");
    }

    @Override // com.amazon.alexa.api.WakeWordApi
    public void startListening(@NonNull AlexaDialogExtras alexaDialogExtras) {
        a(alexaDialogExtras);
    }

    @Override // com.amazon.alexa.api.WakeWordApi
    public void startListening(@NonNull AlexaDialogExtras alexaDialogExtras, @NonNull AlexaApiCallbacks alexaApiCallbacks) {
        throw new RuntimeException("startListening(extras, callback) is not implemented");
    }

    @Override // com.amazon.alexa.api.WakeWordApi
    public void stopListening() {
        deregisterCallbacksObject(a);
    }
}
