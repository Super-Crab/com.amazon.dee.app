package com.amazon.alexa;

import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.Factory;
import java.util.Map;
import javax.inject.Provider;
/* compiled from: AudioActivityTrackerProvider_Factory.java */
/* loaded from: classes.dex */
public final class yvc implements Factory<FQX> {
    public final Provider<TimeProvider> BIo;
    public final Provider<Map<dnp, dnp>> zQM;
    public final Provider<QJr> zZm;

    public yvc(Provider<QJr> provider, Provider<TimeProvider> provider2, Provider<Map<dnp, dnp>> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new FQX(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get());
    }
}
