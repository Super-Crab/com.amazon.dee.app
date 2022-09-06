package com.amazon.alexa.accessory.repositories.instrumentation;

import com.amazon.alexa.accessory.internal.repositories.BaseProducer;
import com.amazon.alexa.accessory.internal.repositories.ResultCallable;
import com.amazon.alexa.accessory.internal.repositories.SingleResult;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.instrumentation.InstrumentationProducer;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public final class MemoryInstrumentationRepository extends BaseProducer<InstrumentationProducer.ActionHandler> implements InstrumentationRepository, InstrumentationProducer {
    @Override // com.amazon.alexa.accessory.repositories.instrumentation.InstrumentationRepository
    public Single<Common.ErrorCode> issueRemoteClearPairing() {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.instrumentation.-$$Lambda$MemoryInstrumentationRepository$TUuVqfrSs0DqxOoyWLLHyLP4NMI
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryInstrumentationRepository.this.lambda$issueRemoteClearPairing$3$MemoryInstrumentationRepository(result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.instrumentation.InstrumentationRepository
    public Single<Common.ErrorCode> issueRemoteCommand(final String str) {
        Preconditions.notNull(str, "commandLine");
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.instrumentation.-$$Lambda$MemoryInstrumentationRepository$FTWJs5m7kUCuU-IRm1LPSmqWGlQ
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryInstrumentationRepository.this.lambda$issueRemoteCommand$0$MemoryInstrumentationRepository(str, result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.instrumentation.InstrumentationRepository
    public Single<Common.ErrorCode> issueRemoteReset() {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.instrumentation.-$$Lambda$MemoryInstrumentationRepository$1Ez-pwnyIrF4mAJnallbO_vGMXM
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryInstrumentationRepository.this.lambda$issueRemoteReset$2$MemoryInstrumentationRepository(result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.instrumentation.InstrumentationRepository
    public Single<Common.ErrorCode> issueRemoteRestart() {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.instrumentation.-$$Lambda$MemoryInstrumentationRepository$dHGId2-S5clA2PTg_Vu2uTUFfU8
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryInstrumentationRepository.this.lambda$issueRemoteRestart$1$MemoryInstrumentationRepository(result);
            }
        });
    }

    public /* synthetic */ void lambda$issueRemoteClearPairing$3$MemoryInstrumentationRepository(Producer.Result result) {
        getHandler().handleIssueRemoteClearPairing(result);
    }

    public /* synthetic */ void lambda$issueRemoteCommand$0$MemoryInstrumentationRepository(String str, Producer.Result result) {
        getHandler().handleIssueRemoteCommand(str, result);
    }

    public /* synthetic */ void lambda$issueRemoteReset$2$MemoryInstrumentationRepository(Producer.Result result) {
        getHandler().handleIssueRemoteReset(result);
    }

    public /* synthetic */ void lambda$issueRemoteRestart$1$MemoryInstrumentationRepository(Producer.Result result) {
        getHandler().handleIssueRemoteRestart(result);
    }
}
