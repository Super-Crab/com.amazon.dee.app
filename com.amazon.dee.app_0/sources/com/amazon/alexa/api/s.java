package com.amazon.alexa.api;

import com.amazon.alexa.api.AlexaMobileFrameworkApisSpecification;
import com.amazon.alexa.api.AlexaServices;
import com.amazon.alexa.client.annotations.NonNull;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class s extends AlexaMobileFrameworkApisSpecification.Subcomponent implements AudioTaskSchedulerApi {

    /* loaded from: classes6.dex */
    private static class a implements AlexaAudioInteraction {
        private final AlexaAudioTask a;

        private a(AlexaAudioTask alexaAudioTask) {
            this.a = alexaAudioTask;
        }

        @Override // com.amazon.alexa.api.AlexaAudioInteraction
        public AlexaAudioChannel getAlexaAudioChannel() {
            return this.a.getAlexaAudioChannel();
        }

        @Override // com.amazon.alexa.api.AlexaAudioInteraction
        public String getInteractionComponentName() {
            return this.a.getAudioTaskComponentName();
        }

        @Override // com.amazon.alexa.api.AlexaAudioInteraction
        public void onBackground() {
            this.a.onBackground();
        }

        @Override // com.amazon.alexa.api.AlexaAudioInteraction
        public void onForeground() {
            this.a.onForeground();
        }

        @Override // com.amazon.alexa.api.AlexaAudioInteraction
        public void onPause() {
            this.a.onPause();
        }

        @Override // com.amazon.alexa.api.AlexaAudioInteraction
        public void onResume() {
            this.a.onResume();
        }

        @Override // com.amazon.alexa.api.AlexaAudioInteraction
        public void onStop() {
            this.a.onStop();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public s(AlexaServicesConnection alexaServicesConnection, AtomicBoolean atomicBoolean, ConcurrentLinkedQueue<g> concurrentLinkedQueue) {
        super(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
    }

    @Override // com.amazon.alexa.api.AudioTaskSchedulerApi
    public void scheduleAudioTask(@NonNull AlexaAudioTask alexaAudioTask) {
        final a aVar = new a(alexaAudioTask);
        registerCallbacksObject(alexaAudioTask, new Runnable() { // from class: com.amazon.alexa.api.s.1
            @Override // java.lang.Runnable
            public void run() {
                AlexaServices.InteractionScheduler.schedule(s.this.connection, aVar);
            }
        }, new Runnable() { // from class: com.amazon.alexa.api.s.2
            @Override // java.lang.Runnable
            public void run() {
                AlexaServices.InteractionScheduler.unschedule(s.this.connection, aVar);
            }
        });
    }

    @Override // com.amazon.alexa.api.AudioTaskSchedulerApi
    public void unscheduleAudioTask(@NonNull AlexaAudioTask alexaAudioTask) {
        deregisterCallbacksObject(alexaAudioTask);
    }
}
