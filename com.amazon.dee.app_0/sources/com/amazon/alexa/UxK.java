package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: InteractionScheduler_Factory.java */
/* loaded from: classes.dex */
public final class UxK implements Factory<brA> {
    public final Provider<AlexaClientEventBus> BIo;
    public final Provider<gSO> Qle;
    public final Provider<OPl> jiA;
    public final Provider<vVi> zQM;
    public final Provider<QJr> zZm;
    public final Provider<ZIZ> zyO;

    public UxK(Provider<QJr> provider, Provider<AlexaClientEventBus> provider2, Provider<vVi> provider3, Provider<ZIZ> provider4, Provider<OPl> provider5, Provider<gSO> provider6) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
        this.Qle = provider6;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new brA(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get());
    }
}
