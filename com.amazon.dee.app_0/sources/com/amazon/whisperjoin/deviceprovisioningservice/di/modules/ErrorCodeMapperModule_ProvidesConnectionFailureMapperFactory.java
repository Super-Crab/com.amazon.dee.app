package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.common.sharedtypes.exceptions.ConnectionFailureException;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.BLETransportOperationErrorDetailsProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ErrorCodeMapperModule_ProvidesConnectionFailureMapperFactory implements Factory<WJErrorMapper<ConnectionFailureException>> {
    private final Provider<BLETransportOperationErrorDetailsProvider> bleTransportOperationErrorDetailsProvider;
    private final ErrorCodeMapperModule module;

    public ErrorCodeMapperModule_ProvidesConnectionFailureMapperFactory(ErrorCodeMapperModule errorCodeMapperModule, Provider<BLETransportOperationErrorDetailsProvider> provider) {
        this.module = errorCodeMapperModule;
        this.bleTransportOperationErrorDetailsProvider = provider;
    }

    public static ErrorCodeMapperModule_ProvidesConnectionFailureMapperFactory create(ErrorCodeMapperModule errorCodeMapperModule, Provider<BLETransportOperationErrorDetailsProvider> provider) {
        return new ErrorCodeMapperModule_ProvidesConnectionFailureMapperFactory(errorCodeMapperModule, provider);
    }

    public static WJErrorMapper<ConnectionFailureException> provideInstance(ErrorCodeMapperModule errorCodeMapperModule, Provider<BLETransportOperationErrorDetailsProvider> provider) {
        return proxyProvidesConnectionFailureMapper(errorCodeMapperModule, provider.mo10268get());
    }

    public static WJErrorMapper<ConnectionFailureException> proxyProvidesConnectionFailureMapper(ErrorCodeMapperModule errorCodeMapperModule, BLETransportOperationErrorDetailsProvider bLETransportOperationErrorDetailsProvider) {
        return (WJErrorMapper) Preconditions.checkNotNull(errorCodeMapperModule.providesConnectionFailureMapper(bLETransportOperationErrorDetailsProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WJErrorMapper<ConnectionFailureException> mo10268get() {
        return provideInstance(this.module, this.bleTransportOperationErrorDetailsProvider);
    }
}
