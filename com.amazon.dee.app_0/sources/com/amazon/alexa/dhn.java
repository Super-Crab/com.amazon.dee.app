package com.amazon.alexa;

import com.amazon.alexa.wakeword.pryon.LocaleProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: WakeWordModule_ProvideLocaleProviderFactory.java */
/* loaded from: classes.dex */
public final class dhn implements Factory<LocaleProvider> {
    public final Provider<MBE> BIo;
    public final iPU zZm;

    public dhn(iPU ipu, Provider<MBE> provider) {
        this.zZm = ipu;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (LocaleProvider) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
