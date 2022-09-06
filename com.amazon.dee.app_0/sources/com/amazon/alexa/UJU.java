package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.Factory;
import java.util.Set;
import javax.inject.Provider;
/* compiled from: ExternalComponentStateAuthority_Factory.java */
/* loaded from: classes.dex */
public final class UJU implements Factory<YKQ> {
    public final Provider<ICz> BIo;
    public final Provider<Set<Namespace>> jiA;
    public final Provider<AlexaClientEventBus> zQM;
    public final Provider<LrI> zZm;
    public final Provider<TimeProvider> zyO;

    public UJU(Provider<LrI> provider, Provider<ICz> provider2, Provider<AlexaClientEventBus> provider3, Provider<TimeProvider> provider4, Provider<Set<Namespace>> provider5) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new YKQ(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get());
    }
}
