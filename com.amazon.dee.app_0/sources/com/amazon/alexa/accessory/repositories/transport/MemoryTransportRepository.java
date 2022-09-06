package com.amazon.alexa.accessory.repositories.transport;

import com.amazon.alexa.accessory.internal.repositories.BaseProducer;
import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.internal.repositories.ResultCallable;
import com.amazon.alexa.accessory.internal.repositories.SingleResult;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.transport.TransportProducer;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public class MemoryTransportRepository extends BaseProducer<TransportProducer.ActionHandler> implements TransportRepository, TransportProducer {
    public /* synthetic */ void lambda$requestUpgrade$1$MemoryTransportRepository(Producer.Result result) {
        getHandler().handleRequestUpgrade(result);
    }

    public /* synthetic */ void lambda$shouldUpgrade$0$MemoryTransportRepository(Producer.Result result) {
        getHandler().handleShouldUpgrade(result);
    }

    @Override // com.amazon.alexa.accessory.repositories.transport.TransportRepository
    public Completable requestUpgrade() {
        return CompletableResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.transport.-$$Lambda$MemoryTransportRepository$H4i4d4E7e41HdQoCWcF-_DQDxek
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryTransportRepository.this.lambda$requestUpgrade$1$MemoryTransportRepository(result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.transport.TransportRepository
    public Single<Boolean> shouldUpgrade() {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.transport.-$$Lambda$MemoryTransportRepository$tX8juSyNKd43qo79aPjKTNfohos
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryTransportRepository.this.lambda$shouldUpgrade$0$MemoryTransportRepository(result);
            }
        });
    }
}
