package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.intent.DSHSSetCredentialsAPI;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.metrics.CredentialSyncMetricsRecorder;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ProvisioningServicesModule_ProvidesDSHSSetCredentialsAPIFactory implements Factory<DSHSSetCredentialsAPI> {
    private final Provider<Context> contextProvider;
    private final Provider<CredentialSyncMetricsRecorder> credentialSyncMetricsRecorderProvider;
    private final ProvisioningServicesModule module;

    public ProvisioningServicesModule_ProvidesDSHSSetCredentialsAPIFactory(ProvisioningServicesModule provisioningServicesModule, Provider<Context> provider, Provider<CredentialSyncMetricsRecorder> provider2) {
        this.module = provisioningServicesModule;
        this.contextProvider = provider;
        this.credentialSyncMetricsRecorderProvider = provider2;
    }

    public static ProvisioningServicesModule_ProvidesDSHSSetCredentialsAPIFactory create(ProvisioningServicesModule provisioningServicesModule, Provider<Context> provider, Provider<CredentialSyncMetricsRecorder> provider2) {
        return new ProvisioningServicesModule_ProvidesDSHSSetCredentialsAPIFactory(provisioningServicesModule, provider, provider2);
    }

    public static DSHSSetCredentialsAPI provideInstance(ProvisioningServicesModule provisioningServicesModule, Provider<Context> provider, Provider<CredentialSyncMetricsRecorder> provider2) {
        return proxyProvidesDSHSSetCredentialsAPI(provisioningServicesModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static DSHSSetCredentialsAPI proxyProvidesDSHSSetCredentialsAPI(ProvisioningServicesModule provisioningServicesModule, Context context, CredentialSyncMetricsRecorder credentialSyncMetricsRecorder) {
        return (DSHSSetCredentialsAPI) Preconditions.checkNotNull(provisioningServicesModule.providesDSHSSetCredentialsAPI(context, credentialSyncMetricsRecorder), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DSHSSetCredentialsAPI mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.credentialSyncMetricsRecorderProvider);
    }
}
