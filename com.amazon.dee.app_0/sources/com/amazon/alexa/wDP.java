package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
/* compiled from: AlexaStateAuthority_Factory.java */
/* loaded from: classes.dex */
public final class wDP implements Factory<vkx> {
    public final Provider<ScheduledExecutorService> BIo;
    public final Provider<AlexaClientEventBus> zZm;

    public wDP(Provider<AlexaClientEventBus> provider, Provider<ScheduledExecutorService> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new vkx(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
