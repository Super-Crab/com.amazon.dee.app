package com.amazon.alexa.accessory.repositories.transport;

import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.repositories.Producer;
/* loaded from: classes6.dex */
public interface TransportProducer extends Producer<ActionHandler> {

    /* loaded from: classes6.dex */
    public interface ActionHandler {
        void handleRequestUpgrade(Producer.Result<CompletableResult.Value> result);

        void handleShouldUpgrade(Producer.Result<Boolean> result);
    }
}
