package com.amazon.alexa.accessory.repositories.diagnostics;

import com.amazon.alexa.accessory.internal.repositories.BaseProducer;
import com.amazon.alexa.accessory.internal.repositories.ResultCallable;
import com.amazon.alexa.accessory.internal.repositories.SingleResult;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.Source;
import com.amazon.alexa.accessory.protocol.DiagnosticsOuterClass;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.diagnostics.DiagnosticsProducer;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public final class MemoryDiagnosticsRepository extends BaseProducer<DiagnosticsProducer.ActionHandler> implements DiagnosticsProducer, DiagnosticsRepository {
    public /* synthetic */ void lambda$queryDiagnostics$0$MemoryDiagnosticsRepository(DiagnosticsOuterClass.DiagnosticsType diagnosticsType, Producer.Result result) {
        getHandler().handleGetDiagnostics(diagnosticsType, result);
    }

    @Override // com.amazon.alexa.accessory.repositories.diagnostics.DiagnosticsRepository
    public Single<Source> queryDiagnostics(final DiagnosticsOuterClass.DiagnosticsType diagnosticsType) {
        Preconditions.notNull(diagnosticsType, "type");
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.diagnostics.-$$Lambda$MemoryDiagnosticsRepository$ls0slMqL-G93U5_GGyzWnGpcQPs
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryDiagnosticsRepository.this.lambda$queryDiagnostics$0$MemoryDiagnosticsRepository(diagnosticsType, result);
            }
        });
    }
}
