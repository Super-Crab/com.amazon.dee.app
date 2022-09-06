package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.greenrobot.eventbus.EventBus;
/* compiled from: AsynchronousAlexaClientEventBus_Factory.java */
/* loaded from: classes.dex */
public final class rvx implements Factory<wGG> {
    public final Provider<gSO> BIo;
    public final Provider<EventBus> zZm;

    public rvx(Provider<EventBus> provider, Provider<gSO> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    public static rvx zZm(Provider<EventBus> provider, Provider<gSO> provider2) {
        return new rvx(provider, provider2);
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new wGG(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
