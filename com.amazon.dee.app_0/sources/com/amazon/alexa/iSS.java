package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: ExternalMediaPlayerStateProvider_Factory.java */
/* loaded from: classes.dex */
public final class iSS implements Factory<Boy> {
    public final Provider<XTJ> BIo;
    public final Provider<zmg> zZm;

    public iSS(Provider<zmg> provider, Provider<XTJ> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new Boy(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
