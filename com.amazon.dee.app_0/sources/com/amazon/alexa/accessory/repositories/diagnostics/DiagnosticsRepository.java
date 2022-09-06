package com.amazon.alexa.accessory.repositories.diagnostics;

import com.amazon.alexa.accessory.io.Source;
import com.amazon.alexa.accessory.protocol.DiagnosticsOuterClass;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public interface DiagnosticsRepository {
    Single<Source> queryDiagnostics(DiagnosticsOuterClass.DiagnosticsType diagnosticsType);
}
