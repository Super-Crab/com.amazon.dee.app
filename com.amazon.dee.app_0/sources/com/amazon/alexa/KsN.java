package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: UserSpeechProviderModule_ProvidesInternalUserSpeechProviderFactory.java */
/* loaded from: classes.dex */
public final class KsN implements Factory<Lbc> {
    public final Provider<leZ> BIo;
    public final BBX zZm;

    public KsN(BBX bbx, Provider<leZ> provider) {
        this.zZm = bbx;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (Lbc) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
