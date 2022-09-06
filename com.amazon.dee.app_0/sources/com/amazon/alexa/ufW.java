package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: AudioPlayerModule_ProvidesMediaPlaybackAuthorityFactory.java */
/* loaded from: classes.dex */
public final class ufW implements Factory<liS> {
    public final Provider<Context> BIo;
    public final Provider<C0245zoo> zQM;
    public final uuv zZm;
    public final Provider<AlexaClientEventBus> zyO;

    public ufW(uuv uuvVar, Provider<Context> provider, Provider<C0245zoo> provider2, Provider<AlexaClientEventBus> provider3) {
        this.zZm = uuvVar;
        this.BIo = provider;
        this.zQM = provider2;
        this.zyO = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (liS) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
