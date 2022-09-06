package com.amazon.alexa.api;

import com.amazon.alexa.api.AlexaMobileFrameworkApisSpecification;
import com.amazon.alexa.client.annotations.NonNull;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class m extends AlexaMobileFrameworkApisSpecification.Subcomponent implements AttentionSystemApi {
    /* JADX INFO: Access modifiers changed from: package-private */
    public m(AlexaServicesConnection alexaServicesConnection, AtomicBoolean atomicBoolean, ConcurrentLinkedQueue<g> concurrentLinkedQueue) {
        super(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
    }

    @Override // com.amazon.alexa.api.AttentionSystemApi
    public void deregisterAttentionSystemListener(@NonNull AlexaAttentionSystemListener alexaAttentionSystemListener) {
        super.deregisterCallbacksObject(alexaAttentionSystemListener);
    }

    @Override // com.amazon.alexa.api.AttentionSystemApi
    public void deregisterListener(@NonNull AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
        deregisterCallbacksObject(alexaAttentionSystemSettingsListener);
    }

    @Override // com.amazon.alexa.api.AttentionSystemApi
    public void deregisterListener(@NonNull AlexaStateListener alexaStateListener) {
        super.deregisterCallbacksObject(alexaStateListener);
    }

    @Override // com.amazon.alexa.api.AttentionSystemApi
    public void deregisterListener(@NonNull AlexaUserSpeechListener alexaUserSpeechListener) {
        deregisterCallbacksObject(alexaUserSpeechListener);
    }

    @Override // com.amazon.alexa.api.AttentionSystemApi
    public void registerAttentionSystemListener(@NonNull final AlexaAttentionSystemListener alexaAttentionSystemListener) {
        registerCallbacksObject(alexaAttentionSystemListener, new Runnable() { // from class: com.amazon.alexa.api.m.5
            @Override // java.lang.Runnable
            public void run() {
                j.a(m.this.connection, alexaAttentionSystemListener);
            }
        }, new Runnable() { // from class: com.amazon.alexa.api.m.6
            @Override // java.lang.Runnable
            public void run() {
                j.b(m.this.connection, alexaAttentionSystemListener);
            }
        });
    }

    @Override // com.amazon.alexa.api.AttentionSystemApi
    public void registerListener(@NonNull final AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
        registerCallbacksObject(alexaAttentionSystemSettingsListener, new Runnable() { // from class: com.amazon.alexa.api.m.11
            @Override // java.lang.Runnable
            public void run() {
                j.a(m.this.connection, alexaAttentionSystemSettingsListener);
            }
        }, new Runnable() { // from class: com.amazon.alexa.api.m.2
            @Override // java.lang.Runnable
            public void run() {
                j.b(m.this.connection, alexaAttentionSystemSettingsListener);
            }
        });
    }

    @Override // com.amazon.alexa.api.AttentionSystemApi
    public void registerListener(@NonNull final AlexaStateListener alexaStateListener) {
        registerCallbacksObject(alexaStateListener, new Runnable() { // from class: com.amazon.alexa.api.m.1
            @Override // java.lang.Runnable
            public void run() {
                j.a(m.this.connection, alexaStateListener);
            }
        }, new Runnable() { // from class: com.amazon.alexa.api.m.4
            @Override // java.lang.Runnable
            public void run() {
                j.b(m.this.connection, alexaStateListener);
            }
        });
    }

    @Override // com.amazon.alexa.api.AttentionSystemApi
    public void registerListener(@NonNull final AlexaUserSpeechListener alexaUserSpeechListener) {
        registerCallbacksObject(alexaUserSpeechListener, new Runnable() { // from class: com.amazon.alexa.api.m.7
            @Override // java.lang.Runnable
            public void run() {
                j.a(m.this.connection, alexaUserSpeechListener);
            }
        }, new Runnable() { // from class: com.amazon.alexa.api.m.8
            @Override // java.lang.Runnable
            public void run() {
                j.b(m.this.connection, alexaUserSpeechListener);
            }
        });
    }

    @Override // com.amazon.alexa.api.AttentionSystemApi
    public void setEndpointSoundEnabled(final boolean z) {
        executeApi(new g() { // from class: com.amazon.alexa.api.m.10
            @Override // com.amazon.alexa.api.g
            public void a() {
                j.b(m.this.connection, z);
            }
        });
    }

    @Override // com.amazon.alexa.api.AttentionSystemApi
    public void setWakeSoundEnabled(final boolean z) {
        executeApi(new g() { // from class: com.amazon.alexa.api.m.9
            @Override // com.amazon.alexa.api.g
            public void a() {
                j.a(m.this.connection, z);
            }
        });
    }

    @Override // com.amazon.alexa.api.AttentionSystemApi
    public void stopForegroundAudioTask() {
        executeApi(new g() { // from class: com.amazon.alexa.api.m.3
            @Override // com.amazon.alexa.api.g
            public void a() {
                j.c(m.this.connection);
            }
        });
    }
}
