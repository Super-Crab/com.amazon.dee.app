package com.amazon.alexa.accessory.repositories.hearing;

import com.amazon.alexa.accessory.protocol.Hearing;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public interface HearingEnhancementRepository {
    Single<Hearing.Audiogram> getAudiogram(int i);

    Single<Hearing.MediaEnhancementCorrectionAmount> getMediaEnhancementCorrectionAmount(int i);

    Completable setAudiogram(Hearing.Audiogram audiogram);

    Completable setMediaEnhancementCorrectionAmount(Hearing.MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount);
}
