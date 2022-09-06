package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: OfflinePromptDownloadInitiator_Factory.java */
/* loaded from: classes.dex */
public final class wBN implements Factory<szT> {
    public final Provider<AlexaClientEventBus> BIo;
    public final Provider<JEn> zZm;

    public wBN(Provider<JEn> provider, Provider<AlexaClientEventBus> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new szT(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
