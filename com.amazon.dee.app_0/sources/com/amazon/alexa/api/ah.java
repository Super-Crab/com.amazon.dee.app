package com.amazon.alexa.api;

import com.amazon.alexa.api.AlexaMobileFrameworkApisSpecification;
import com.amazon.alexa.api.AlexaServices;
import com.amazon.alexa.client.annotations.NonNull;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class ah extends AlexaMobileFrameworkApisSpecification.Subcomponent implements CardsApi {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ah(AlexaServicesConnection alexaServicesConnection, AtomicBoolean atomicBoolean, ConcurrentLinkedQueue<g> concurrentLinkedQueue) {
        super(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
    }

    @Override // com.amazon.alexa.api.CardsApi
    public void deregisterListener(@NonNull AlexaCardListener alexaCardListener) {
        super.deregisterCallbacksObject(alexaCardListener);
    }

    @Override // com.amazon.alexa.api.CardsApi
    public void deregisterListener(@NonNull AlexaPlayerInfoCardListener alexaPlayerInfoCardListener) {
        deregisterCallbacksObject(alexaPlayerInfoCardListener);
    }

    @Override // com.amazon.alexa.api.CardsApi
    public void registerListener(@NonNull final AlexaCardListener alexaCardListener) {
        super.registerCallbacksObject(alexaCardListener, new Runnable() { // from class: com.amazon.alexa.api.ah.3
            @Override // java.lang.Runnable
            public void run() {
                AlexaServices.Cards.registerAlexaCardRendererListener(ah.this.connection, alexaCardListener);
            }
        }, new Runnable() { // from class: com.amazon.alexa.api.ah.4
            @Override // java.lang.Runnable
            public void run() {
                AlexaServices.Cards.deregisterAlexaCardRendererListener(ah.this.connection, alexaCardListener);
            }
        });
    }

    @Override // com.amazon.alexa.api.CardsApi
    public void registerListener(@NonNull final AlexaPlayerInfoCardListener alexaPlayerInfoCardListener) {
        super.registerCallbacksObject(alexaPlayerInfoCardListener, new Runnable() { // from class: com.amazon.alexa.api.ah.1
            @Override // java.lang.Runnable
            public void run() {
                AlexaServices.Cards.registerPlayerInfoCardListener(ah.this.connection, alexaPlayerInfoCardListener);
            }
        }, new Runnable() { // from class: com.amazon.alexa.api.ah.2
            @Override // java.lang.Runnable
            public void run() {
                AlexaServices.Cards.deregisterPlayerInfoCardListener(ah.this.connection, alexaPlayerInfoCardListener);
            }
        });
    }
}
