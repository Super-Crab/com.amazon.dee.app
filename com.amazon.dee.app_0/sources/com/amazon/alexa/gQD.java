package com.amazon.alexa;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AlexaNotificationBuilder_Factory.java */
/* loaded from: classes.dex */
public final class gQD implements Factory<gOn> {
    public final Provider<liS> BIo;
    public final Provider<Context> zZm;

    public gQD(Provider<Context> provider, Provider<liS> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new gOn(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
