package com.amazon.alexa.accessory.avsclient;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.avsclient.AccessoryDiagnostics;
import com.amazon.alexa.accessory.capabilities.diagnostics.DiagnosticsObserver;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.DiagnosticsOuterClass;
import io.reactivex.rxjava3.functions.Function;
/* loaded from: classes.dex */
public final class AccessoryDiagnosticsObserver implements DiagnosticsObserver {
    @Override // com.amazon.alexa.accessory.capabilities.diagnostics.DiagnosticsObserver
    public boolean onDiagnosticsAvailable(final AccessorySession accessorySession, final DiagnosticsOuterClass.DiagnosticsType diagnosticsType) {
        Preconditions.notNull(accessorySession, "accessorySession");
        Preconditions.notNull(diagnosticsType, "type");
        AccessoryDiagnostics.getDevice(accessorySession).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.avsclient.-$$Lambda$AccessoryDiagnosticsObserver$3U2JW7rnt7YGDSfYf-IMNn_PRLc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AccessoryDiagnostics.uploadAccessoryDiagnostics(AccessorySession.this, (AccessoryDiagnostics.AccessoryDevice) obj, diagnosticsType);
            }
        }).subscribe($$Lambda$AccessoryDiagnosticsObserver$Fae58HMSZ6hgXEUxCmHbyL5SeUM.INSTANCE, $$Lambda$AccessoryDiagnosticsObserver$F6l9eFaYjF02Xr00aIZ6LAQJYA.INSTANCE);
        return true;
    }
}
