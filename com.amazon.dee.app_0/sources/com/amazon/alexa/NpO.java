package com.amazon.alexa;

import com.amazon.alexa.wakeword.WakeWordArbitration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* compiled from: WakeWordModule_ProvideWakeWordArbitrationFactory.java */
/* loaded from: classes.dex */
public final class NpO implements Factory<WakeWordArbitration> {
    public final iPU zZm;

    public NpO(iPU ipu) {
        this.zZm = ipu;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (WakeWordArbitration) Preconditions.checkNotNull(this.zZm.zyO(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
