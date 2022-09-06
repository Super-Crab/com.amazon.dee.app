package com.amazon.alexa;

import com.amazon.alexa.wakeword.RecordingTracker;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* compiled from: WakeWordModule_ProvideRecordingTrackerFactory.java */
/* renamed from: com.amazon.alexa.dUL  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0196dUL implements Factory<RecordingTracker> {
    public final iPU zZm;

    public C0196dUL(iPU ipu) {
        this.zZm = ipu;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (RecordingTracker) Preconditions.checkNotNull(this.zZm.BIo(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
