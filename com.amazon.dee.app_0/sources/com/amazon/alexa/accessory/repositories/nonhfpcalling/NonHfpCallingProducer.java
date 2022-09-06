package com.amazon.alexa.accessory.repositories.nonhfpcalling;

import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.repositories.Producer;
/* loaded from: classes6.dex */
public interface NonHfpCallingProducer extends Producer<ActionHandler> {

    /* loaded from: classes6.dex */
    public interface ActionHandler {
        void handleUpdateCallInfo(CallInfo callInfo, Producer.Result<Common.ErrorCode> result);
    }
}
