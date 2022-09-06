package com.amazon.alexa.accessory.repositories.inputevents;

import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.protocol.Input;
import com.amazon.alexa.accessory.repositories.Producer;
/* loaded from: classes6.dex */
public interface InputProducer extends Producer<ActionHandler> {

    /* loaded from: classes6.dex */
    public interface ActionHandler {
        void handleGetInputConfiguration(int i, Producer.Result<Input.InputBehaviorConfigurationSet> result);

        void handleResetInputConfiguration(int i, Producer.Result<Input.InputBehaviorConfigurationSet> result);

        void handleSetInputConfiguration(int i, Input.InputBehaviorConfiguration inputBehaviorConfiguration, Producer.Result<CompletableResult.Value> result);
    }
}
