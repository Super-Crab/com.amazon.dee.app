package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: SpeechPlayer_Factory.java */
/* loaded from: classes.dex */
public final class JWa implements Factory<dDK> {
    public final Provider<uxN> zZm;

    public JWa(Provider<uxN> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new dDK(this.zZm.mo10268get());
    }
}
