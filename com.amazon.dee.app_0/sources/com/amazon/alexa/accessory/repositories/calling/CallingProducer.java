package com.amazon.alexa.accessory.repositories.calling;

import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.repositories.Producer;
/* loaded from: classes6.dex */
public interface CallingProducer extends Producer<ActionHandler> {

    /* loaded from: classes6.dex */
    public interface ActionHandler {
        void handleForwardAtCommand(ATCommand aTCommand, Producer.Result<Common.ErrorCode> result);
    }
}
