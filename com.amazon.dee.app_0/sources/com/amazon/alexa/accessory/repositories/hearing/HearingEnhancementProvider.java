package com.amazon.alexa.accessory.repositories.hearing;

import com.amazon.alexa.accessory.protocol.Hearing;
/* loaded from: classes6.dex */
public interface HearingEnhancementProvider {
    void provideAudiogram(Hearing.Audiogram audiogram);

    void provideMediaEnhancementCorrectionAmount(Hearing.MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount);
}
