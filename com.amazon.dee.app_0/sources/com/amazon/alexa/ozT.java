package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ReleaseNetworkingModule_ProvidesResponseHandlerFactory.java */
/* loaded from: classes.dex */
public final class ozT implements Factory<nmS> {
    public final Provider<SmC> BIo;
    public final Provider<CrashReporter> JTe;
    public final Provider<ZPU> LPk;
    public final Provider<AlexaClientEventBus> Qle;
    public final Provider<qxC> jiA;
    public final Provider<SbM> yPL;
    public final Provider<shl> zQM;
    public final Provider<Gson> zZm;
    public final Provider<DYu> zyO;

    public ozT(Provider<Gson> provider, Provider<SmC> provider2, Provider<shl> provider3, Provider<DYu> provider4, Provider<qxC> provider5, Provider<AlexaClientEventBus> provider6, Provider<CrashReporter> provider7, Provider<ZPU> provider8, Provider<SbM> provider9) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
        this.Qle = provider6;
        this.JTe = provider7;
        this.LPk = provider8;
        this.yPL = provider9;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (nmS) Preconditions.checkNotNull(new nmS(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get(), this.LPk.mo10268get(), this.yPL.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
