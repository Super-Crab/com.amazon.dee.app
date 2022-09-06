package com.amazon.alexa.accessory.repositories.nonhfpcalling;

import com.amazon.alexa.accessory.internal.repositories.BaseProducer;
import com.amazon.alexa.accessory.internal.repositories.ResultCallable;
import com.amazon.alexa.accessory.internal.repositories.SingleResult;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallingProducer;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public class MemoryNonHfpCallingRepository extends BaseProducer<NonHfpCallingProducer.ActionHandler> implements NonHfpCallingRepository, NonHfpCallingProducer {
    public /* synthetic */ void lambda$updateCallInfo$0$MemoryNonHfpCallingRepository(CallInfo callInfo, Producer.Result result) {
        getHandler().handleUpdateCallInfo(callInfo, result);
    }

    @Override // com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallingRepository
    public Single<Common.ErrorCode> updateCallInfo(final CallInfo callInfo) {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.nonhfpcalling.-$$Lambda$MemoryNonHfpCallingRepository$r8tMHGWOd_-XhTOMvlOkkgPUqXY
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryNonHfpCallingRepository.this.lambda$updateCallInfo$0$MemoryNonHfpCallingRepository(callInfo, result);
            }
        });
    }
}
