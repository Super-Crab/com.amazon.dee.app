package com.amazon.alexa.accessory.repositories.display;

import com.amazon.alexa.accessory.protocol.Cardrendering;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.repositories.Producer;
/* loaded from: classes6.dex */
public interface DisplayContentProducer extends Producer<ActionHandler> {

    /* loaded from: classes6.dex */
    public interface ActionHandler {
        void handleSetDisplayContent(Cardrendering.DisplayContent displayContent, Producer.Result<Common.ErrorCode> result);
    }
}
