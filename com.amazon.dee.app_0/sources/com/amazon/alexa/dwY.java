package com.amazon.alexa;

import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: SpeechInteractionFactory_Factory.java */
/* loaded from: classes.dex */
public final class dwY implements Factory<iHK> {
    public final Provider<vkx> BIo;
    public final Provider<ILi> jiA;
    public final Provider<Wyh> zQM;
    public final Provider<shl> zZm;
    public final Provider<TimeProvider> zyO;

    public dwY(Provider<shl> provider, Provider<vkx> provider2, Provider<Wyh> provider3, Provider<TimeProvider> provider4, Provider<ILi> provider5) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new iHK(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get());
    }
}
