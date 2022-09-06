package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AlexaSpeechmarkParser_Factory.java */
/* renamed from: com.amazon.alexa.enl  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0204enl implements Factory<DtB> {
    public final Provider<ILi> BIo;
    public final Provider<AlexaClientEventBus> zQM;
    public final Provider<fUD> zZm;

    public C0204enl(Provider<fUD> provider, Provider<ILi> provider2, Provider<AlexaClientEventBus> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new DtB(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get());
    }
}
