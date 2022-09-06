package com.amazon.alexa.accessory.repositories.diagnostics;

import com.amazon.alexa.accessory.io.Source;
import com.amazon.alexa.accessory.protocol.DiagnosticsOuterClass;
import com.amazon.alexa.accessory.repositories.Producer;
/* loaded from: classes6.dex */
public interface DiagnosticsProducer extends Producer<ActionHandler> {

    /* loaded from: classes6.dex */
    public interface ActionHandler {
        void handleGetDiagnostics(DiagnosticsOuterClass.DiagnosticsType diagnosticsType, Producer.Result<Source> result);
    }
}
