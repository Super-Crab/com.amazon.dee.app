package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: MediaBrowserClientFactory_Factory.java */
/* loaded from: classes.dex */
public final class DnY implements Factory<OQS> {
    public final Provider<AlexaClientEventBus> BIo;
    public final Provider<yjR> zQM;
    public final Provider<Context> zZm;
    public final Provider<aim> zyO;

    public DnY(Provider<Context> provider, Provider<AlexaClientEventBus> provider2, Provider<yjR> provider3, Provider<aim> provider4) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new OQS(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get());
    }
}
