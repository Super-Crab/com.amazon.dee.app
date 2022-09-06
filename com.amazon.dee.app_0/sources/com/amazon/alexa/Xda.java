package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* compiled from: WakeWordModule_ProvideClientPresenceTrackerFactory.java */
/* loaded from: classes.dex */
public final class Xda implements Factory<FLQ> {
    public final iPU zZm;

    public Xda(iPU ipu) {
        this.zZm = ipu;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (FLQ) Preconditions.checkNotNull(this.zZm.zZm(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
