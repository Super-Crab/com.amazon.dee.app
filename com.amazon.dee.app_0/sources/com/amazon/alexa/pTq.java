package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: MultiTurnDialogProvider_Factory.java */
/* loaded from: classes.dex */
public final class pTq implements Factory<QCK> {
    public final Provider<rJn> BIo;
    public final Provider<jSO> zZm;

    public pTq(Provider<jSO> provider, Provider<rJn> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new QCK(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
