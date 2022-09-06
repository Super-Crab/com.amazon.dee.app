package com.amazon.alexa.accessory.repositories.calling;

import com.amazon.alexa.accessory.internal.repositories.BaseProducer;
import com.amazon.alexa.accessory.internal.repositories.ResultCallable;
import com.amazon.alexa.accessory.internal.repositories.SingleResult;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.calling.CallingProducer;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public class MemoryCallingRepository extends BaseProducer<CallingProducer.ActionHandler> implements CallingRepository, CallingProducer {
    @Override // com.amazon.alexa.accessory.repositories.calling.CallingRepository
    public Single<Common.ErrorCode> forwardAtCommand(final ATCommand aTCommand) {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.calling.-$$Lambda$MemoryCallingRepository$FmN3XTZwVAY2cwFmxVPaG8yOsUk
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryCallingRepository.this.lambda$forwardAtCommand$0$MemoryCallingRepository(aTCommand, result);
            }
        });
    }

    public /* synthetic */ void lambda$forwardAtCommand$0$MemoryCallingRepository(ATCommand aTCommand, Producer.Result result) {
        getHandler().handleForwardAtCommand(aTCommand, result);
    }
}
