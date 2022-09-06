package com.amazon.alexa.api;

import com.amazon.alexa.api.AlexaMobileFrameworkApisSpecification;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class z extends AlexaMobileFrameworkApisSpecification.Subcomponent implements CapabilitiesApi {
    /* JADX INFO: Access modifiers changed from: package-private */
    public z(AlexaServicesConnection alexaServicesConnection, AtomicBoolean atomicBoolean, ConcurrentLinkedQueue<g> concurrentLinkedQueue) {
        super(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
    }

    @Override // com.amazon.alexa.api.CapabilitiesApi
    public void cacheContexts(@NonNull final Set<AlexaContext> set) {
        executeApi(new g() { // from class: com.amazon.alexa.api.z.9
            @Override // com.amazon.alexa.api.g
            public void a() {
                y.a(z.this.connection, set);
            }
        });
    }

    @Override // com.amazon.alexa.api.CapabilitiesApi
    public void clearContextCache() {
        executeApi(new g() { // from class: com.amazon.alexa.api.z.10
            @Override // com.amazon.alexa.api.g
            public void a() {
                y.a(z.this.connection);
            }
        });
    }

    @Override // com.amazon.alexa.api.CapabilitiesApi
    public void clearContextCache(@NonNull final Set<String> set) {
        executeApi(new g() { // from class: com.amazon.alexa.api.z.2
            @Override // com.amazon.alexa.api.g
            public void a() {
                y.b(z.this.connection, set);
            }
        });
    }

    @Override // com.amazon.alexa.api.CapabilitiesApi
    public void deregisterContextsProvider(@NonNull AlexaContextsProvider alexaContextsProvider) {
        deregisterCallbacksObject(alexaContextsProvider);
    }

    @Override // com.amazon.alexa.api.CapabilitiesApi
    public void registerContextsProvider(@NonNull final AlexaContextsProvider alexaContextsProvider) {
        registerCallbacksObject(alexaContextsProvider, new Runnable() { // from class: com.amazon.alexa.api.z.5
            @Override // java.lang.Runnable
            public void run() {
                y.a(z.this.connection, alexaContextsProvider);
            }
        }, new Runnable() { // from class: com.amazon.alexa.api.z.6
            @Override // java.lang.Runnable
            public void run() {
                y.b(z.this.connection, alexaContextsProvider);
            }
        });
    }

    @Override // com.amazon.alexa.api.CapabilitiesApi
    public void sendEvent(@NonNull final AlexaEvent alexaEvent) {
        executeApi(new g() { // from class: com.amazon.alexa.api.z.1
            @Override // com.amazon.alexa.api.g
            public void a() {
                y.a(z.this.connection, alexaEvent);
            }
        });
    }

    @Override // com.amazon.alexa.api.CapabilitiesApi
    public void sendEvent(@NonNull final AlexaEvent alexaEvent, final boolean z) {
        executeApi(new g() { // from class: com.amazon.alexa.api.z.3
            @Override // com.amazon.alexa.api.g
            public void a() {
                y.a(z.this.connection, alexaEvent, z);
            }
        });
    }

    @Override // com.amazon.alexa.api.CapabilitiesApi
    public void sendEvent(@NonNull final AlexaEvent alexaEvent, final boolean z, @Nullable final AlexaApiCallbacks alexaApiCallbacks) {
        executeApi(new g(alexaApiCallbacks) { // from class: com.amazon.alexa.api.z.4
            @Override // com.amazon.alexa.api.g
            public void a() {
                y.a(z.this.connection, alexaEvent, z, alexaApiCallbacks);
            }
        });
    }

    @Override // com.amazon.alexa.api.CapabilitiesApi
    public void setContextCachingEnabled(@NonNull final Set<String> set, final boolean z) {
        executeApi(new g() { // from class: com.amazon.alexa.api.z.8
            @Override // com.amazon.alexa.api.g
            public void a() {
                y.a(z.this.connection, set, z);
            }
        });
    }

    @Override // com.amazon.alexa.api.CapabilitiesApi
    public void setContextCachingEnabled(final boolean z) {
        executeApi(new g() { // from class: com.amazon.alexa.api.z.7
            @Override // com.amazon.alexa.api.g
            public void a() {
                y.a(z.this.connection, z);
            }
        });
    }
}
