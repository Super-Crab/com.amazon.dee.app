package com.amazon.alexa;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: ExternalCapabilityRegistrationDatabase_Factory.java */
/* loaded from: classes.dex */
public final class lzY implements Factory<ZVz> {
    public final Provider<Context> zZm;

    public lzY(Provider<Context> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new ZVz(this.zZm.mo10268get());
    }
}
