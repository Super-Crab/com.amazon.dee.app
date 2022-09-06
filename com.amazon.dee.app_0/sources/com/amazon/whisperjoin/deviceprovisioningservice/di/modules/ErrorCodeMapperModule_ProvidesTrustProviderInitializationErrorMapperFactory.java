package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.common.sharedtypes.exceptions.TrustProviderInitializationFailedException;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.error.DSSClientError;
import com.amazon.whisperjoin.provisionerSDK.radios.error.ProvisionerCommandError;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ErrorCodeMapperModule_ProvidesTrustProviderInitializationErrorMapperFactory implements Factory<WJErrorMapper<TrustProviderInitializationFailedException>> {
    private final Provider<WJErrorMapper<DSSClientError>> dssClientErrorWJErrorMapperProvider;
    private final ErrorCodeMapperModule module;
    private final Provider<WJErrorMapper<ProvisionerCommandError>> provisionerCommandErrorWJErrorMapperProvider;

    public ErrorCodeMapperModule_ProvidesTrustProviderInitializationErrorMapperFactory(ErrorCodeMapperModule errorCodeMapperModule, Provider<WJErrorMapper<ProvisionerCommandError>> provider, Provider<WJErrorMapper<DSSClientError>> provider2) {
        this.module = errorCodeMapperModule;
        this.provisionerCommandErrorWJErrorMapperProvider = provider;
        this.dssClientErrorWJErrorMapperProvider = provider2;
    }

    public static ErrorCodeMapperModule_ProvidesTrustProviderInitializationErrorMapperFactory create(ErrorCodeMapperModule errorCodeMapperModule, Provider<WJErrorMapper<ProvisionerCommandError>> provider, Provider<WJErrorMapper<DSSClientError>> provider2) {
        return new ErrorCodeMapperModule_ProvidesTrustProviderInitializationErrorMapperFactory(errorCodeMapperModule, provider, provider2);
    }

    public static WJErrorMapper<TrustProviderInitializationFailedException> provideInstance(ErrorCodeMapperModule errorCodeMapperModule, Provider<WJErrorMapper<ProvisionerCommandError>> provider, Provider<WJErrorMapper<DSSClientError>> provider2) {
        return proxyProvidesTrustProviderInitializationErrorMapper(errorCodeMapperModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static WJErrorMapper<TrustProviderInitializationFailedException> proxyProvidesTrustProviderInitializationErrorMapper(ErrorCodeMapperModule errorCodeMapperModule, WJErrorMapper<ProvisionerCommandError> wJErrorMapper, WJErrorMapper<DSSClientError> wJErrorMapper2) {
        return (WJErrorMapper) Preconditions.checkNotNull(errorCodeMapperModule.providesTrustProviderInitializationErrorMapper(wJErrorMapper, wJErrorMapper2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WJErrorMapper<TrustProviderInitializationFailedException> mo10268get() {
        return provideInstance(this.module, this.provisionerCommandErrorWJErrorMapperProvider, this.dssClientErrorWJErrorMapperProvider);
    }
}
