package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ReleaseNetworkingModule_ProvidesDownchannelCallFactory.java */
/* loaded from: classes.dex */
public final class hJM implements Factory<jQK> {
    public final Provider<dAN> BIo;
    public final Provider<CrashReporter> JTe;
    public final Provider<tol> Qle;
    public final Provider<AlexaClientEventBus> jiA;
    public final Provider<xUA> zQM;
    public final Provider<qxC> zZm;
    public final Provider<nmS> zyO;

    public hJM(Provider<qxC> provider, Provider<dAN> provider2, Provider<xUA> provider3, Provider<nmS> provider4, Provider<AlexaClientEventBus> provider5, Provider<tol> provider6, Provider<CrashReporter> provider7) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
        this.Qle = provider6;
        this.JTe = provider7;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (jQK) Preconditions.checkNotNull(new jQK(this.zZm.mo10268get(), this.BIo.mo10268get(), DoubleCheck.lazy(this.zQM), this.zyO.mo10268get(), this.jiA.mo10268get(), DoubleCheck.lazy(this.Qle), this.JTe.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
