package com.amazon.alexa.api;

import android.app.PendingIntent;
import com.amazon.alexa.api.AlexaMobileFrameworkApisSpecification;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class ba extends AlexaMobileFrameworkApisSpecification.Subcomponent implements MediaPlaybackControllerApi {
    private static final String a = "ba";

    /* JADX INFO: Access modifiers changed from: package-private */
    public ba(AlexaServicesConnection alexaServicesConnection, AtomicBoolean atomicBoolean, ConcurrentLinkedQueue<g> concurrentLinkedQueue) {
        super(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
    }

    @Override // com.amazon.alexa.api.MediaPlaybackControllerApi
    public void deregisterAlexaAudioPlaybackStatusListener(AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
        super.deregisterCallbacksObject(alexaAudioPlaybackStatusListener);
    }

    @Override // com.amazon.alexa.api.MediaPlaybackControllerApi
    public void deregisterMediaPlaybackListener(AlexaMediaPlaybackListener alexaMediaPlaybackListener) {
        super.deregisterCallbacksObject(alexaMediaPlaybackListener);
    }

    @Override // com.amazon.alexa.api.MediaPlaybackControllerApi
    public void next() {
        executeApi(new g() { // from class: com.amazon.alexa.api.ba.5
            @Override // com.amazon.alexa.api.g
            public void a() {
                MediaPlaybackController.next(ba.this.connection);
            }
        });
    }

    @Override // com.amazon.alexa.api.MediaPlaybackControllerApi
    public void pause() {
        executeApi(new g() { // from class: com.amazon.alexa.api.ba.4
            @Override // com.amazon.alexa.api.g
            public void a() {
                MediaPlaybackController.pause(ba.this.connection);
            }
        });
    }

    @Override // com.amazon.alexa.api.MediaPlaybackControllerApi
    public void play() {
        executeApi(new g() { // from class: com.amazon.alexa.api.ba.3
            @Override // com.amazon.alexa.api.g
            public void a() {
                MediaPlaybackController.play(ba.this.connection);
            }
        });
    }

    @Override // com.amazon.alexa.api.MediaPlaybackControllerApi
    public void previous() {
        executeApi(new g() { // from class: com.amazon.alexa.api.ba.1
            @Override // com.amazon.alexa.api.g
            public void a() {
                MediaPlaybackController.previous(ba.this.connection);
            }
        });
    }

    @Override // com.amazon.alexa.api.MediaPlaybackControllerApi
    public void registerAlexaAudioPlaybackStatusListener(final AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
        registerCallbacksObject(alexaAudioPlaybackStatusListener, new Runnable() { // from class: com.amazon.alexa.api.ba.10
            @Override // java.lang.Runnable
            public void run() {
                MediaPlaybackController.register(ba.this.connection, alexaAudioPlaybackStatusListener);
            }
        }, new Runnable() { // from class: com.amazon.alexa.api.ba.2
            @Override // java.lang.Runnable
            public void run() {
                MediaPlaybackController.deregister(ba.this.connection, alexaAudioPlaybackStatusListener);
            }
        });
    }

    @Override // com.amazon.alexa.api.MediaPlaybackControllerApi
    public void registerMediaPlaybackListener(final AlexaMediaPlaybackListener alexaMediaPlaybackListener) {
        registerCallbacksObject(alexaMediaPlaybackListener, new Runnable() { // from class: com.amazon.alexa.api.ba.7
            @Override // java.lang.Runnable
            public void run() {
                MediaPlaybackController.registerListener(ba.this.connection, alexaMediaPlaybackListener);
            }
        }, new Runnable() { // from class: com.amazon.alexa.api.ba.8
            @Override // java.lang.Runnable
            public void run() {
                MediaPlaybackController.deregisterListener(ba.this.connection, alexaMediaPlaybackListener);
            }
        });
    }

    @Override // com.amazon.alexa.api.MediaPlaybackControllerApi
    public void setMediaNotificationContentIntent(final PendingIntent pendingIntent) {
        executeApi(new g() { // from class: com.amazon.alexa.api.ba.9
            @Override // com.amazon.alexa.api.g
            public void a() {
                MediaPlaybackController.setMediaNotificationContentIntent(ba.this.connection, pendingIntent);
            }
        });
    }

    @Override // com.amazon.alexa.api.MediaPlaybackControllerApi
    public void stop() {
        executeApi(new g() { // from class: com.amazon.alexa.api.ba.6
            @Override // com.amazon.alexa.api.g
            public void a() {
                MediaPlaybackController.stop(ba.this.connection);
            }
        });
    }
}
