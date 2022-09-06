package com.amazon.alexa;

import android.content.Context;
import android.location.LocationManager;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AndroidLocationComponent_Factory.java */
/* loaded from: classes.dex */
public final class UmT implements Factory<SCB> {
    public final Provider<LocationManager> BIo;
    public final Provider<Context> Qle;
    public final Provider<Dtt> jiA;
    public final Provider<vYS> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<TimeProvider> zyO;

    public UmT(Provider<AlexaClientEventBus> provider, Provider<LocationManager> provider2, Provider<vYS> provider3, Provider<TimeProvider> provider4, Provider<Dtt> provider5, Provider<Context> provider6) {
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
        return new SCB(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get());
    }
}
