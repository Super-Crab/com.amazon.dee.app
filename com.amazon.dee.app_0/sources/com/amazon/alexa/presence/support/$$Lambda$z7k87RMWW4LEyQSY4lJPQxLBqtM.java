package com.amazon.alexa.presence.support;

import com.amazon.alexa.presence.support.BackgroundBatchProcessor;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.presence.support.-$$Lambda$z7k87RMWW4LEyQSY4lJPQxLBqtM  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$z7k87RMWW4LEyQSY4lJPQxLBqtM implements BackgroundBatchProcessor.Clock {
    public static final /* synthetic */ $$Lambda$z7k87RMWW4LEyQSY4lJPQxLBqtM INSTANCE = new $$Lambda$z7k87RMWW4LEyQSY4lJPQxLBqtM();

    private /* synthetic */ $$Lambda$z7k87RMWW4LEyQSY4lJPQxLBqtM() {
    }

    @Override // com.amazon.alexa.presence.support.BackgroundBatchProcessor.Clock
    public final long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
