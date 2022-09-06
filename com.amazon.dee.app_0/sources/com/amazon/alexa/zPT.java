package com.amazon.alexa;

import android.media.AudioManager;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: VolumeAuthority_Factory.java */
/* loaded from: classes.dex */
public final class zPT implements Factory<QMz> {
    public final Provider<AudioManager> BIo;
    public final Provider<wVr> zQM;
    public final Provider<AlexaClientEventBus> zZm;

    public zPT(Provider<AlexaClientEventBus> provider, Provider<AudioManager> provider2, Provider<wVr> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new QMz(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get());
    }
}
