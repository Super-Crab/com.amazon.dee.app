package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* compiled from: ReleaseInitializationModule_ProvidesExoAudioPlayerInitializerFactory.java */
/* loaded from: classes.dex */
public final class jtv implements Factory<uXm> {
    public final Vnn zZm;

    public jtv(Vnn vnn) {
        this.zZm = vnn;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (uXm) Preconditions.checkNotNull(this.zZm.zZm(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
