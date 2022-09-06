package com.amazon.alexa;

import dagger.internal.Factory;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
/* compiled from: VolumeUpdateScheduler_Factory.java */
/* loaded from: classes.dex */
public final class dpb implements Factory<EuG> {
    public final Provider<ScheduledExecutorService> BIo;
    public final Provider<Kfo> zZm;

    public dpb(Provider<Kfo> provider, Provider<ScheduledExecutorService> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new EuG(this.zZm, this.BIo.mo10268get());
    }
}
