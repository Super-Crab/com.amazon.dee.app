package com.amazon.alexa.accessory.repositories.hearing;

import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.protocol.Hearing;
import com.amazon.alexa.accessory.repositories.Producer;
/* loaded from: classes6.dex */
public interface HearingEnhancementProducer extends Producer<ActionHandler> {

    /* loaded from: classes6.dex */
    public interface ActionHandler {
        void handleGetAudiogram(int i, Producer.Result<Hearing.Audiogram> result);

        void handleGetMediaEnhancementCorrectionAmount(int i, Producer.Result<Hearing.MediaEnhancementCorrectionAmount> result);

        void handleSetAudiogram(Hearing.Audiogram audiogram, Producer.Result<CompletableResult.Value> result);

        void handleSetMediaEnhancementCorrectionAmount(Hearing.MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount, Producer.Result<CompletableResult.Value> result);
    }
}
