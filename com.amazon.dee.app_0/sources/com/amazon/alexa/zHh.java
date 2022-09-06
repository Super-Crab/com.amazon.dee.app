package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: SoundEffectPlayer_Factory.java */
/* loaded from: classes.dex */
public final class zHh implements Factory<VEQ> {
    public final Provider<jcJ> BIo;
    public final Provider<vVi> jiA;
    public final Provider<Wyh> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<ClientConfiguration> zyO;

    public zHh(Provider<AlexaClientEventBus> provider, Provider<jcJ> provider2, Provider<Wyh> provider3, Provider<ClientConfiguration> provider4, Provider<vVi> provider5) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new VEQ(this.zZm.mo10268get(), DoubleCheck.lazy(this.BIo), DoubleCheck.lazy(this.zQM), DoubleCheck.lazy(this.zyO), this.jiA.mo10268get());
    }
}
