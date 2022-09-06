package com.amazon.alexa;

import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.configuration.PropertiesConfigurationLoader;
import com.amazon.alexa.client.core.configuration.ResourcesConfigurationLoader;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ConfigurationModule_ProvideClientConfigurationFactory.java */
/* loaded from: classes.dex */
public final class bEe implements Factory<ClientConfiguration> {
    public final Provider<mOV> BIo;
    public final Provider<PropertiesConfigurationLoader> zQM;
    public final VWb zZm;
    public final Provider<ResourcesConfigurationLoader> zyO;

    public bEe(VWb vWb, Provider<mOV> provider, Provider<PropertiesConfigurationLoader> provider2, Provider<ResourcesConfigurationLoader> provider3) {
        this.zZm = vWb;
        this.BIo = provider;
        this.zQM = provider2;
        this.zyO = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (ClientConfiguration) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
