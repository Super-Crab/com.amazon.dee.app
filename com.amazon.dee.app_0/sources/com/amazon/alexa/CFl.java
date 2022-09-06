package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;
/* compiled from: ChannelsModule_ProvidesChannelsFactory.java */
/* loaded from: classes.dex */
public final class CFl implements Factory<QJr> {
    public final Provider<AlexaClientEventBus> BIo;
    public final Provider<TimeProvider> jiA;
    public final Provider<AzE> zQM;
    public final dsY zZm;
    public final Provider<Set<dnp>> zyO;

    public CFl(dsY dsy, Provider<AlexaClientEventBus> provider, Provider<AzE> provider2, Provider<Set<dnp>> provider3, Provider<TimeProvider> provider4) {
        this.zZm = dsy;
        this.BIo = provider;
        this.zQM = provider2;
        this.zyO = provider3;
        this.jiA = provider4;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (QJr) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
