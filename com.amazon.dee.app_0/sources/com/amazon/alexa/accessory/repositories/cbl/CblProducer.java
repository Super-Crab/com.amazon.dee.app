package com.amazon.alexa.accessory.repositories.cbl;

import com.amazon.alexa.accessory.protocol.Cbl;
import com.amazon.alexa.accessory.repositories.Producer;
/* loaded from: classes6.dex */
public interface CblProducer extends Producer<ActionHandler> {

    /* loaded from: classes6.dex */
    public interface ActionHandler {
        void handleGetCblInformation(Producer.Result<Cbl.CblInformation> result);

        void handleGetCblLoginState();
    }
}
