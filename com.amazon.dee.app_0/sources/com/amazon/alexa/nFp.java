package com.amazon.alexa;

import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.wakeword.davs.MultiWakeWordFeatureEnabledProvider;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: WakeWordModule_ProvidesMultiWakeWordFeatureEnabledProviderFactory.java */
/* loaded from: classes.dex */
public final class nFp implements Factory<MultiWakeWordFeatureEnabledProvider> {
    public final Provider<gSO> BIo;
    public final Provider<ClientConfiguration> zQM;
    public final iPU zZm;

    public nFp(iPU ipu, Provider<gSO> provider, Provider<ClientConfiguration> provider2) {
        this.zZm = ipu;
        this.BIo = provider;
        this.zQM = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (MultiWakeWordFeatureEnabledProvider) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), DoubleCheck.lazy(this.zQM)), "Cannot return null from a non-@Nullable @Provides method");
    }
}
