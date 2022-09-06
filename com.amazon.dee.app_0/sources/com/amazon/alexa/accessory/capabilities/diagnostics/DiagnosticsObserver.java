package com.amazon.alexa.accessory.capabilities.diagnostics;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.protocol.DiagnosticsOuterClass;
/* loaded from: classes.dex */
public interface DiagnosticsObserver {
    boolean onDiagnosticsAvailable(AccessorySession accessorySession, DiagnosticsOuterClass.DiagnosticsType diagnosticsType);
}
