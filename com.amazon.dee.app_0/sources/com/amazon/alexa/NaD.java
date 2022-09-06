package com.amazon.alexa;

import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: ExternalMediaPlayerRegistrationPersister_Factory.java */
/* loaded from: classes.dex */
public final class NaD implements Factory<yWS> {
    public final Provider<uTP> zZm;

    public NaD(Provider<uTP> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new yWS(DoubleCheck.lazy(this.zZm));
    }
}
