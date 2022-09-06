package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.audio.ScaledVolumeProcessor;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AttachmentStore_Factory.java */
/* loaded from: classes.dex */
public final class tSf implements Factory<shl> {
    public final Provider<AlexaClientEventBus> BIo;
    public final Provider<DtB> zQM;
    public final Provider<ScaledVolumeProcessor> zZm;
    public final Provider<Vyl> zyO;

    public tSf(Provider<ScaledVolumeProcessor> provider, Provider<AlexaClientEventBus> provider2, Provider<DtB> provider3, Provider<Vyl> provider4) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new shl(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get());
    }
}
