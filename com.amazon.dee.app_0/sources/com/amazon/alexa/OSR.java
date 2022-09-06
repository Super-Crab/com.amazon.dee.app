package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.device.PersistentStorage;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: TextAuthority_Factory.java */
/* loaded from: classes.dex */
public final class OSR implements Factory<yjS> {
    public final Provider<vkx> BIo;
    public final Provider<Wyh> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<PersistentStorage> zyO;

    public OSR(Provider<AlexaClientEventBus> provider, Provider<vkx> provider2, Provider<Wyh> provider3, Provider<PersistentStorage> provider4) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new yjS(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), DoubleCheck.lazy(this.zyO));
    }
}
