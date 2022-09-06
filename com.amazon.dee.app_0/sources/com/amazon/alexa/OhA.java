package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* compiled from: ReleaseInitializationModule_ProvidesStrictModeInitializerFactory.java */
/* loaded from: classes.dex */
public final class OhA implements Factory<pYn> {
    public final Vnn zZm;

    public OhA(Vnn vnn) {
        this.zZm = vnn;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (pYn) Preconditions.checkNotNull(this.zZm.BIo(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
