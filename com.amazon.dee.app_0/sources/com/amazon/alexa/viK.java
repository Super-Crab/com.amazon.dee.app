package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: UserSpeechProviderArbitrator_Factory.java */
/* loaded from: classes.dex */
public final class viK implements Factory<iDr> {
    public final Provider<gSO> zZm;

    public viK(Provider<gSO> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new iDr(this.zZm.mo10268get());
    }
}
