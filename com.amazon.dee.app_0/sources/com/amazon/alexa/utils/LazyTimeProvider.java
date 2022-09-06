package com.amazon.alexa.utils;
/* loaded from: classes10.dex */
public class LazyTimeProvider implements Provider<TimeProvider> {
    private TimeProvider timeProvider;

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.utils.Provider
    /* renamed from: get */
    public synchronized TimeProvider mo2864get() {
        if (this.timeProvider == null) {
            this.timeProvider = new TimeProvider();
        }
        return this.timeProvider;
    }
}
