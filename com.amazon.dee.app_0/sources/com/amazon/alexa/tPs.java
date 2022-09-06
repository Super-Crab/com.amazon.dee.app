package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ReleaseInstrumentationModule_ProvidesInstrumentationBroadcastReceiverFactory.java */
/* loaded from: classes.dex */
public final class tPs implements Factory<jbU> {
    public final Provider<fGu> BIo;
    public final PJy zZm;

    public tPs(PJy pJy, Provider<fGu> provider) {
        this.zZm = pJy;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (jbU) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
