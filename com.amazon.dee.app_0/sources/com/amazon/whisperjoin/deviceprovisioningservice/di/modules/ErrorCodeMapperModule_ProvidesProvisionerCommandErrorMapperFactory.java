package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.BLETransportOperationErrorDetailsProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import com.amazon.whisperjoin.provisionerSDK.radios.error.ProvisionerCommandError;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ErrorCodeMapperModule_ProvidesProvisionerCommandErrorMapperFactory implements Factory<WJErrorMapper<ProvisionerCommandError>> {
    private final Provider<BLETransportOperationErrorDetailsProvider> bleTransportOperationErrorDetailsProvider;
    private final ErrorCodeMapperModule module;

    public ErrorCodeMapperModule_ProvidesProvisionerCommandErrorMapperFactory(ErrorCodeMapperModule errorCodeMapperModule, Provider<BLETransportOperationErrorDetailsProvider> provider) {
        this.module = errorCodeMapperModule;
        this.bleTransportOperationErrorDetailsProvider = provider;
    }

    public static ErrorCodeMapperModule_ProvidesProvisionerCommandErrorMapperFactory create(ErrorCodeMapperModule errorCodeMapperModule, Provider<BLETransportOperationErrorDetailsProvider> provider) {
        return new ErrorCodeMapperModule_ProvidesProvisionerCommandErrorMapperFactory(errorCodeMapperModule, provider);
    }

    public static WJErrorMapper<ProvisionerCommandError> provideInstance(ErrorCodeMapperModule errorCodeMapperModule, Provider<BLETransportOperationErrorDetailsProvider> provider) {
        return proxyProvidesProvisionerCommandErrorMapper(errorCodeMapperModule, provider.mo10268get());
    }

    public static WJErrorMapper<ProvisionerCommandError> proxyProvidesProvisionerCommandErrorMapper(ErrorCodeMapperModule errorCodeMapperModule, BLETransportOperationErrorDetailsProvider bLETransportOperationErrorDetailsProvider) {
        return (WJErrorMapper) Preconditions.checkNotNull(errorCodeMapperModule.providesProvisionerCommandErrorMapper(bLETransportOperationErrorDetailsProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WJErrorMapper<ProvisionerCommandError> mo10268get() {
        return provideInstance(this.module, this.bleTransportOperationErrorDetailsProvider);
    }
}
