package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: ExternalCapabilityAgentFinder_Factory.java */
/* loaded from: classes.dex */
public final class tXP implements Factory<JgM> {
    public final Provider<rOO> BIo;
    public final Provider<gSO> jiA;
    public final Provider<wdQ> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<ClientConfiguration> zyO;

    public tXP(Provider<AlexaClientEventBus> provider, Provider<rOO> provider2, Provider<wdQ> provider3, Provider<ClientConfiguration> provider4, Provider<gSO> provider5) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new JgM(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), DoubleCheck.lazy(this.zyO), this.jiA.mo10268get());
    }
}
