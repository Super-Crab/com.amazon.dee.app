package com.amazon.alexa;

import android.content.Context;
import android.location.LocationManager;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import com.google.android.gms.common.api.GoogleApiClient;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: GooglePlayLocationComponent_Factory.java */
/* loaded from: classes.dex */
public final class Utr implements Factory<blL> {
    public final Provider<GoogleApiClient> BIo;
    public final Provider<Dtt> JTe;
    public final Provider<TimeProvider> Qle;
    public final Provider<vYS> jiA;
    public final Provider<Context> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<LocationManager> zyO;

    public Utr(Provider<AlexaClientEventBus> provider, Provider<GoogleApiClient> provider2, Provider<Context> provider3, Provider<LocationManager> provider4, Provider<vYS> provider5, Provider<TimeProvider> provider6, Provider<Dtt> provider7) {
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
        return new blL(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get(), ManagedExecutorFactory.newSingleThreadExecutor("google-play-location", ManagedExecutorFactory.Group.INITIALIZATION));
    }
}
