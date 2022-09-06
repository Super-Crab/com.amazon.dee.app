package com.amazon.alexa;

import com.amazon.alexa.client.crashreporting.CrashReporter;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: ErrorReporter_Factory.java */
/* loaded from: classes.dex */
public final class eVO implements Factory<UYN> {
    public final Provider<CrashReporter> zZm;

    public eVO(Provider<CrashReporter> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new UYN(this.zZm.mo10268get());
    }
}
