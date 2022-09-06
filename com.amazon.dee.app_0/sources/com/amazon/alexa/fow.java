package com.amazon.alexa;

import android.content.Context;
import android.content.pm.PackageManager;
import com.amazon.alexa.api.CapabilityAgentConnectionMessageSenderFactory;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.messages.MessageTransformer;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: ExternalCapabilityAgentRegistry_Factory.java */
/* loaded from: classes.dex */
public final class fow implements Factory<LrI> {
    public final Provider<MessageTransformer> BIo;
    public final Provider<ClientConfiguration> JTe;
    public final Provider<gSO> LPk;
    public final Provider<lWz> Qle;
    public final Provider<CapabilityAgentConnectionMessageSenderFactory> jiA;
    public final Provider<PackageManager> zQM;
    public final Provider<Context> zZm;
    public final Provider<AlexaClientEventBus> zyO;

    public fow(Provider<Context> provider, Provider<MessageTransformer> provider2, Provider<PackageManager> provider3, Provider<AlexaClientEventBus> provider4, Provider<CapabilityAgentConnectionMessageSenderFactory> provider5, Provider<lWz> provider6, Provider<ClientConfiguration> provider7, Provider<gSO> provider8) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
        this.Qle = provider6;
        this.JTe = provider7;
        this.LPk = provider8;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new LrI(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get(), this.LPk.mo10268get());
    }
}
