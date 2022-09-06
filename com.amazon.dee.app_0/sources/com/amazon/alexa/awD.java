package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: VoiceInteractionTaskFactory_Factory.java */
/* loaded from: classes.dex */
public final class awD implements Factory<Wiq> {
    public final Provider<IUt> BIo;
    public final Provider<PersistentStorage> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<TimeProvider> zyO;

    public awD(Provider<AlexaClientEventBus> provider, Provider<IUt> provider2, Provider<PersistentStorage> provider3, Provider<TimeProvider> provider4) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new Wiq(this.zZm.mo10268get(), this.BIo.mo10268get(), DoubleCheck.lazy(this.zQM), this.zyO.mo10268get());
    }
}
