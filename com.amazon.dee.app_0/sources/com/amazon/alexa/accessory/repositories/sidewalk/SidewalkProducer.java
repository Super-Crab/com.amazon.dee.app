package com.amazon.alexa.accessory.repositories.sidewalk;

import com.amazon.alexa.accessory.io.SizedSource;
import com.amazon.alexa.accessory.repositories.Producer;
/* loaded from: classes6.dex */
public interface SidewalkProducer extends Producer<ActionHandler> {

    /* loaded from: classes6.dex */
    public interface ActionHandler {
        void handleWriteData(SizedSource sizedSource);
    }
}
