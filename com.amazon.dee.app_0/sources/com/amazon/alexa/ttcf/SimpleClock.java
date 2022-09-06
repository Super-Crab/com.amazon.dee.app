package com.amazon.alexa.ttcf;
/* compiled from: TTCFService.java */
/* loaded from: classes10.dex */
class SimpleClock implements TTCFClock {
    @Override // com.amazon.alexa.ttcf.TTCFClock
    public long getTime() {
        return System.currentTimeMillis();
    }
}
