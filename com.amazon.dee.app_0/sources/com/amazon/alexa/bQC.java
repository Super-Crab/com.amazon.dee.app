package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.metrics.core.DeviceInformation;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
/* compiled from: ExternalMediaPlayerModule_ProvidesUnnamedMediaPlayerFactory.java */
/* loaded from: classes.dex */
public final class bQC implements Factory<Blk> {
    public final Provider<Context> BIo;
    public final Provider<DeviceInformation> JTe;
    public final Provider<SKB> Qle;
    public final Provider<ScheduledExecutorService> jiA;
    public final Provider<AlexaClientEventBus> zQM;
    public final ueX zZm;
    public final Provider<liS> zyO;

    public bQC(ueX uex, Provider<Context> provider, Provider<AlexaClientEventBus> provider2, Provider<liS> provider3, Provider<ScheduledExecutorService> provider4, Provider<SKB> provider5, Provider<DeviceInformation> provider6) {
        this.zZm = uex;
        this.BIo = provider;
        this.zQM = provider2;
        this.zyO = provider3;
        this.jiA = provider4;
        this.Qle = provider5;
        this.JTe = provider6;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (Blk) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get(), DoubleCheck.lazy(this.zyO), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
