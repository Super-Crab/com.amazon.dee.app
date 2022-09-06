package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: PlaybackStateReporterProvider_Factory.java */
/* loaded from: classes.dex */
public final class PDO implements Factory<HTC> {
    public final Provider<zmg> zZm;

    public PDO(Provider<zmg> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new HTC(this.zZm.mo10268get());
    }
}
