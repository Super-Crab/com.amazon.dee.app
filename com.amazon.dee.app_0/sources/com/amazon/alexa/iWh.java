package com.amazon.alexa;

import android.media.AudioManager;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AudioFocusManager_Factory.java */
/* loaded from: classes.dex */
public final class iWh implements Factory<OPl> {
    public final Provider<AudioManager> BIo;
    public final Provider<Khf> jiA;
    public final Provider<QJr> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<SKB> zyO;

    public iWh(Provider<AlexaClientEventBus> provider, Provider<AudioManager> provider2, Provider<QJr> provider3, Provider<SKB> provider4, Provider<Khf> provider5) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new OPl(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get());
    }
}
