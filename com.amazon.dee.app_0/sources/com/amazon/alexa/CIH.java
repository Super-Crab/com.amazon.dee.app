package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: NetworkingModule_ProvidesAlexaEventDaoFactory.java */
/* loaded from: classes.dex */
public final class CIH implements Factory<ZPU> {
    public final Provider<VBC> zZm;

    public CIH(Provider<VBC> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (ZPU) Preconditions.checkNotNull(this.zZm.mo10268get(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
