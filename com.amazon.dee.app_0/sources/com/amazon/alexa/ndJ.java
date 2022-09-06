package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: StartDialogTurnCallbacksProvider_Factory.java */
/* loaded from: classes.dex */
public final class ndJ implements Factory<rJn> {
    public final Provider<shl> BIo;
    public final Provider<yaw> jiA;
    public final Provider<snw> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<iKQ> zyO;

    public ndJ(Provider<AlexaClientEventBus> provider, Provider<shl> provider2, Provider<snw> provider3, Provider<iKQ> provider4, Provider<yaw> provider5) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new rJn(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get());
    }
}
