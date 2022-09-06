package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AlexaContextsProviderFactory_Factory.java */
/* loaded from: classes.dex */
public final class zzR implements Factory<Mlj> {
    public final Provider<KvZ> zZm;

    public zzR(Provider<KvZ> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new Mlj(this.zZm.mo10268get());
    }
}
