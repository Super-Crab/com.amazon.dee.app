package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ComponentStateModule_ProvidesExternalComponentStateDaoFactory.java */
/* loaded from: classes.dex */
public final class xGG implements Factory<TFi> {
    public final Provider<dCo> BIo;
    public final yxr zZm;

    public xGG(yxr yxrVar, Provider<dCo> provider) {
        this.zZm = yxrVar;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (TFi) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
