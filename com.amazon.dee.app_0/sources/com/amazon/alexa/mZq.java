package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: VisualActivityTrackerProvider_Factory.java */
/* loaded from: classes.dex */
public final class mZq implements Factory<IOV> {
    public final Provider<Rpb> zZm;

    public mZq(Provider<Rpb> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new IOV(this.zZm.mo10268get());
    }
}
