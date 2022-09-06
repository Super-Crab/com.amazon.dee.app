package com.amazon.alexa.accessory.repositories.state;

import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.Producer;
/* loaded from: classes6.dex */
public interface StateProducer extends Producer<ActionHandler> {

    /* loaded from: classes6.dex */
    public interface ActionHandler {
        void handleGetState(StateFeature stateFeature, Producer.Result<StateOuterClass.State> result);

        void handleSetState(StateOuterClass.State state, Producer.Result<CompletableResult.Value> result);
    }
}
