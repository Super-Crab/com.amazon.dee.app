package com.amazon.alexa.accessory.internal.session;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.capabilities.diagnostics.DiagnosticsCapability;
import com.amazon.alexa.accessory.capabilities.diagnostics.DiagnosticsObserver;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.DiagnosticsOuterClass;
/* loaded from: classes.dex */
final class SessionDiagnosticsListener implements DiagnosticsCapability.DiagnosticsAvailableListener {
    private final DiagnosticsObserver observer;
    private final AccessorySession session;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SessionDiagnosticsListener(AccessorySession accessorySession, DiagnosticsObserver diagnosticsObserver) {
        Preconditions.notNull(accessorySession, "session");
        Preconditions.notNull(diagnosticsObserver, "observer");
        this.session = accessorySession;
        this.observer = diagnosticsObserver;
    }

    @Override // com.amazon.alexa.accessory.capabilities.diagnostics.DiagnosticsCapability.DiagnosticsAvailableListener
    public boolean onDiagnosticsAvailable(DiagnosticsOuterClass.DiagnosticsType diagnosticsType) {
        Preconditions.notNull(diagnosticsType, "type");
        return this.observer.onDiagnosticsAvailable(this.session, diagnosticsType);
    }
}
