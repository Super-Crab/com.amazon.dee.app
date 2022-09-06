package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* compiled from: WakeWordModule_ProvideUserSpeechArbitrationWakeWordPreconditionFactory.java */
/* loaded from: classes.dex */
public final class Ltc implements Factory<GSf> {
    public final iPU zZm;

    public Ltc(iPU ipu) {
        this.zZm = ipu;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (GSf) Preconditions.checkNotNull(this.zZm.zQM(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
