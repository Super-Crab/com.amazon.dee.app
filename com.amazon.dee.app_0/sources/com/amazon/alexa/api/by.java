package com.amazon.alexa.api;

import com.amazon.alexa.api.AlexaMobileFrameworkApisSpecification;
import com.amazon.alexa.client.annotations.NonNull;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class by extends AlexaMobileFrameworkApisSpecification.Subcomponent implements UserSpeechProvidersApi {
    /* JADX INFO: Access modifiers changed from: package-private */
    public by(AlexaServicesConnection alexaServicesConnection, AtomicBoolean atomicBoolean, ConcurrentLinkedQueue<g> concurrentLinkedQueue) {
        super(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
    }

    @Override // com.amazon.alexa.api.UserSpeechProvidersApi
    public void deregister(@NonNull AlexaUserSpeechProvider alexaUserSpeechProvider) {
        deregisterCallbacksObject(alexaUserSpeechProvider);
    }

    @Override // com.amazon.alexa.api.UserSpeechProvidersApi
    public void register(@NonNull final AlexaUserSpeechProvider alexaUserSpeechProvider, @NonNull final AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata) {
        registerCallbacksObject(alexaUserSpeechProvider, new Runnable() { // from class: com.amazon.alexa.api.by.1
            @Override // java.lang.Runnable
            public void run() {
                bx.a(by.this.connection, alexaUserSpeechProvider, alexaUserSpeechProviderMetadata);
            }
        }, new Runnable() { // from class: com.amazon.alexa.api.by.2
            @Override // java.lang.Runnable
            public void run() {
                bx.a(by.this.connection, alexaUserSpeechProvider);
            }
        });
    }

    @Override // com.amazon.alexa.api.UserSpeechProvidersApi
    public void requestDialog(@NonNull final AlexaUserSpeechProvider alexaUserSpeechProvider, @NonNull final AlexaDialogRequest alexaDialogRequest) {
        executeApi(new g() { // from class: com.amazon.alexa.api.by.3
            @Override // com.amazon.alexa.api.g
            public void a() {
                bz.a(by.this.connection, alexaUserSpeechProvider, alexaDialogRequest);
            }
        });
    }
}
