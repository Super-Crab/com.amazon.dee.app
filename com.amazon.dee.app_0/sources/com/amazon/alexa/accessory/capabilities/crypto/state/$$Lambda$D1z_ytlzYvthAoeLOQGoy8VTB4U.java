package com.amazon.alexa.accessory.capabilities.crypto.state;

import com.amazon.alexa.accessory.metrics.Stopwatch;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.crypto.state.-$$Lambda$D1z_ytlzYvthAoeLOQGoy8VTB4U  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$D1z_ytlzYvthAoeLOQGoy8VTB4U implements Stopwatch.CurrentTimeSupplier {
    public static final /* synthetic */ $$Lambda$D1z_ytlzYvthAoeLOQGoy8VTB4U INSTANCE = new $$Lambda$D1z_ytlzYvthAoeLOQGoy8VTB4U();

    private /* synthetic */ $$Lambda$D1z_ytlzYvthAoeLOQGoy8VTB4U() {
    }

    @Override // com.amazon.alexa.accessory.metrics.Stopwatch.CurrentTimeSupplier
    public final long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
