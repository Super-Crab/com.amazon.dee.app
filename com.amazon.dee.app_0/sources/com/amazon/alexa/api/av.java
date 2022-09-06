package com.amazon.alexa.api;

import com.amazon.alexa.api.AlexaMobileFrameworkApisSpecification;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class av extends AlexaMobileFrameworkApisSpecification.Subcomponent implements LocaleApi {
    /* JADX INFO: Access modifiers changed from: package-private */
    public av(AlexaServicesConnection alexaServicesConnection, AtomicBoolean atomicBoolean, ConcurrentLinkedQueue<g> concurrentLinkedQueue) {
        super(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
    }

    @Override // com.amazon.alexa.api.LocaleApi
    public void deregisterListener(@NonNull AlexaLocalesListener alexaLocalesListener) {
        deregisterCallbacksObject(alexaLocalesListener, AlexaLocalesListener.class);
    }

    @Override // com.amazon.alexa.api.LocaleApi
    public void deregisterListener(@NonNull AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        deregisterCallbacksObject(alexaSupportedLocalesListener, AlexaSupportedLocalesListener.class);
    }

    @Override // com.amazon.alexa.api.LocaleApi
    public void registerListener(@NonNull final AlexaLocalesListener alexaLocalesListener) {
        registerCallbacksObject(alexaLocalesListener, AlexaLocalesListener.class, new Runnable() { // from class: com.amazon.alexa.api.av.2
            @Override // java.lang.Runnable
            public void run() {
                Locale.registerListener(av.this.connection, alexaLocalesListener);
            }
        }, new Runnable() { // from class: com.amazon.alexa.api.av.3
            @Override // java.lang.Runnable
            public void run() {
                Locale.deregisterListener(av.this.connection, alexaLocalesListener);
            }
        });
    }

    @Override // com.amazon.alexa.api.LocaleApi
    public void registerListener(@NonNull final AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        registerCallbacksObject(alexaSupportedLocalesListener, AlexaSupportedLocalesListener.class, new Runnable() { // from class: com.amazon.alexa.api.av.4
            @Override // java.lang.Runnable
            public void run() {
                Locale.registerListener(av.this.connection, alexaSupportedLocalesListener);
            }
        }, new Runnable() { // from class: com.amazon.alexa.api.av.5
            @Override // java.lang.Runnable
            public void run() {
                Locale.deregisterListener(av.this.connection, alexaSupportedLocalesListener);
            }
        });
    }

    @Override // com.amazon.alexa.api.LocaleApi
    public void setLocale(@NonNull java.util.Locale locale) {
        setLocales(Collections.singletonList(locale), null);
    }

    @Override // com.amazon.alexa.api.LocaleApi
    public void setLocale(@NonNull java.util.Locale locale, @Nullable AlexaApiCallbacks alexaApiCallbacks) {
        setLocales(Collections.singletonList(locale), alexaApiCallbacks);
    }

    @Override // com.amazon.alexa.api.LocaleApi
    public void setLocales(@NonNull List<java.util.Locale> list) {
        setLocales(list, null);
    }

    @Override // com.amazon.alexa.api.LocaleApi
    public void setLocales(@NonNull final List<java.util.Locale> list, @Nullable final AlexaApiCallbacks alexaApiCallbacks) {
        executeApi(new g(alexaApiCallbacks) { // from class: com.amazon.alexa.api.av.1
            @Override // com.amazon.alexa.api.g
            public void a() throws Exception {
                Locale.setLocales(av.this.connection, list, alexaApiCallbacks);
            }
        });
    }
}
