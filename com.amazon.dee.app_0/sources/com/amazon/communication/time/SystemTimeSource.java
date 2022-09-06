package com.amazon.communication.time;
/* loaded from: classes12.dex */
public final class SystemTimeSource implements TimeSource {
    public static final SystemTimeSource INSTANCE = new SystemTimeSource();

    private SystemTimeSource() {
    }

    @Override // com.amazon.communication.time.TimeSource
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
