package com.amazon.alexa.accessory.repositories.hearing;

import com.amazon.alexa.accessory.internal.repositories.BaseProducer;
import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.internal.repositories.ResultCallable;
import com.amazon.alexa.accessory.internal.repositories.SingleResult;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Hearing;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.hearing.HearingEnhancementProducer;
import com.amazon.alexa.audiopersonalization.constants.EventBusConstants;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public class MemoryHearingEnhancementRepository extends BaseProducer<HearingEnhancementProducer.ActionHandler> implements HearingEnhancementRepository, HearingEnhancementProducer {
    @Override // com.amazon.alexa.accessory.repositories.hearing.HearingEnhancementRepository
    public Single<Hearing.Audiogram> getAudiogram(final int i) {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.hearing.-$$Lambda$MemoryHearingEnhancementRepository$RsbLj2_GuaFVRDVSflr-Q4lSxJI
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryHearingEnhancementRepository.this.lambda$getAudiogram$0$MemoryHearingEnhancementRepository(i, result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.hearing.HearingEnhancementRepository
    public Single<Hearing.MediaEnhancementCorrectionAmount> getMediaEnhancementCorrectionAmount(final int i) {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.hearing.-$$Lambda$MemoryHearingEnhancementRepository$aJmRGFpOi5o5bQcQaOUGsMhAqL0
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryHearingEnhancementRepository.this.lambda$getMediaEnhancementCorrectionAmount$2$MemoryHearingEnhancementRepository(i, result);
            }
        });
    }

    public /* synthetic */ void lambda$getAudiogram$0$MemoryHearingEnhancementRepository(int i, Producer.Result result) {
        getHandler().handleGetAudiogram(i, result);
    }

    public /* synthetic */ void lambda$getMediaEnhancementCorrectionAmount$2$MemoryHearingEnhancementRepository(int i, Producer.Result result) {
        getHandler().handleGetMediaEnhancementCorrectionAmount(i, result);
    }

    public /* synthetic */ void lambda$setAudiogram$1$MemoryHearingEnhancementRepository(Hearing.Audiogram audiogram, Producer.Result result) {
        getHandler().handleSetAudiogram(audiogram, result);
    }

    public /* synthetic */ void lambda$setMediaEnhancementCorrectionAmount$3$MemoryHearingEnhancementRepository(Hearing.MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount, Producer.Result result) {
        getHandler().handleSetMediaEnhancementCorrectionAmount(mediaEnhancementCorrectionAmount, result);
    }

    @Override // com.amazon.alexa.accessory.repositories.hearing.HearingEnhancementRepository
    public Completable setAudiogram(final Hearing.Audiogram audiogram) {
        Preconditions.notNull(audiogram, EventBusConstants.JSON_KEY_AUDIO_PROFILE);
        return CompletableResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.hearing.-$$Lambda$MemoryHearingEnhancementRepository$9YKVM_OvAbuSkIAJ1Li4t5G9rYA
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryHearingEnhancementRepository.this.lambda$setAudiogram$1$MemoryHearingEnhancementRepository(audiogram, result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.hearing.HearingEnhancementRepository
    public Completable setMediaEnhancementCorrectionAmount(final Hearing.MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount) {
        Preconditions.notNull(mediaEnhancementCorrectionAmount, "correctionAmount");
        return CompletableResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.hearing.-$$Lambda$MemoryHearingEnhancementRepository$8NuFNctMQlNq-H4g3mLLLJ37Gn4
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryHearingEnhancementRepository.this.lambda$setMediaEnhancementCorrectionAmount$3$MemoryHearingEnhancementRepository(mediaEnhancementCorrectionAmount, result);
            }
        });
    }
}
