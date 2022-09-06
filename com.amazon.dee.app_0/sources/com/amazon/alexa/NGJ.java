package com.amazon.alexa;

import com.amazon.alexa.client.metrics.core.DeviceInformation;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ComponentStateModule_ProvidesComponentStateAuthorityFactory.java */
/* loaded from: classes.dex */
public final class NGJ implements Factory<lhN> {
    public final Provider<YKQ> BIo;
    public final Provider<DeviceInformation> JTe;
    public final Provider<ZCC> LPk;
    public final Provider<zmg> Qle;
    public final Provider<C0245zoo> jiA;
    public final Provider<lEV> zQM;
    public final yxr zZm;
    public final Provider<ClS> zyO;

    public NGJ(yxr yxrVar, Provider<YKQ> provider, Provider<lEV> provider2, Provider<ClS> provider3, Provider<C0245zoo> provider4, Provider<zmg> provider5, Provider<DeviceInformation> provider6, Provider<ZCC> provider7) {
        this.zZm = yxrVar;
        this.BIo = provider;
        this.zQM = provider2;
        this.zyO = provider3;
        this.jiA = provider4;
        this.Qle = provider5;
        this.JTe = provider6;
        this.LPk = provider7;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (lhN) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get(), this.LPk.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
