package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: StandardVoiceCardMetricsAuthority_Factory.java */
/* loaded from: classes.dex */
public final class MCY implements Factory<C0211jTe> {
    public final Provider<AlexaClientEventBus> BIo;
    public final Provider<ClientConfiguration> jiA;
    public final Provider<paF> zQM;
    public final Provider<Context> zZm;
    public final Provider<TimeProvider> zyO;

    public MCY(Provider<Context> provider, Provider<AlexaClientEventBus> provider2, Provider<paF> provider3, Provider<TimeProvider> provider4, Provider<ClientConfiguration> provider5) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new C0211jTe(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), DoubleCheck.lazy(this.jiA));
    }
}
