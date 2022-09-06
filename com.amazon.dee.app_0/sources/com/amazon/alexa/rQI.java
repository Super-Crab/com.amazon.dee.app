package com.amazon.alexa;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: MuteStateChangedEventSender_Factory.java */
/* loaded from: classes.dex */
public final class rQI implements Factory<wVr> {
    public final Provider<Context> zZm;

    public rQI(Provider<Context> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new wVr(this.zZm.mo10268get());
    }
}
