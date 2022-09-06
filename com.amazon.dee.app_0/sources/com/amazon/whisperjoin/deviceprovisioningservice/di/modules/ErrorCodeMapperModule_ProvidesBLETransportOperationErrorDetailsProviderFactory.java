package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.BLETransportOperationErrorDetailsProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ErrorCodeMapperModule_ProvidesBLETransportOperationErrorDetailsProviderFactory implements Factory<BLETransportOperationErrorDetailsProvider> {
    private final ErrorCodeMapperModule module;

    public ErrorCodeMapperModule_ProvidesBLETransportOperationErrorDetailsProviderFactory(ErrorCodeMapperModule errorCodeMapperModule) {
        this.module = errorCodeMapperModule;
    }

    public static ErrorCodeMapperModule_ProvidesBLETransportOperationErrorDetailsProviderFactory create(ErrorCodeMapperModule errorCodeMapperModule) {
        return new ErrorCodeMapperModule_ProvidesBLETransportOperationErrorDetailsProviderFactory(errorCodeMapperModule);
    }

    public static BLETransportOperationErrorDetailsProvider provideInstance(ErrorCodeMapperModule errorCodeMapperModule) {
        return proxyProvidesBLETransportOperationErrorDetailsProvider(errorCodeMapperModule);
    }

    public static BLETransportOperationErrorDetailsProvider proxyProvidesBLETransportOperationErrorDetailsProvider(ErrorCodeMapperModule errorCodeMapperModule) {
        return (BLETransportOperationErrorDetailsProvider) Preconditions.checkNotNull(errorCodeMapperModule.providesBLETransportOperationErrorDetailsProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BLETransportOperationErrorDetailsProvider mo10268get() {
        return provideInstance(this.module);
    }
}
