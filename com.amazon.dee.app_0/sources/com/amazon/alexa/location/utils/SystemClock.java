package com.amazon.alexa.location.utils;
/* loaded from: classes9.dex */
public class SystemClock implements Clock {
    @Override // com.amazon.alexa.location.utils.Clock
    public long millis() {
        return System.currentTimeMillis();
    }
}
