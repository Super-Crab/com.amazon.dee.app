package com.amazon.alexa;

import android.content.Context;
import android.content.pm.PackageManager;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.google.gson.Gson;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: ExternalCapabilityAgentManifestExtractor_Factory.java */
/* loaded from: classes.dex */
public final class myG implements Factory<rOO> {
    public final Provider<Gson> BIo;
    public final Provider<Context> zQM;
    public final Provider<PackageManager> zZm;
    public final Provider<AlexaClientEventBus> zyO;

    public myG(Provider<PackageManager> provider, Provider<Gson> provider2, Provider<Context> provider3, Provider<AlexaClientEventBus> provider4) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new rOO(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get());
    }
}
