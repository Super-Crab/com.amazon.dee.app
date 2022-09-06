package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.error.DSSClientError;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ErrorCodeMapperModule_ProvidesDSSClientErrorMapperFactory implements Factory<WJErrorMapper<DSSClientError>> {
    private final ErrorCodeMapperModule module;

    public ErrorCodeMapperModule_ProvidesDSSClientErrorMapperFactory(ErrorCodeMapperModule errorCodeMapperModule) {
        this.module = errorCodeMapperModule;
    }

    public static ErrorCodeMapperModule_ProvidesDSSClientErrorMapperFactory create(ErrorCodeMapperModule errorCodeMapperModule) {
        return new ErrorCodeMapperModule_ProvidesDSSClientErrorMapperFactory(errorCodeMapperModule);
    }

    public static WJErrorMapper<DSSClientError> provideInstance(ErrorCodeMapperModule errorCodeMapperModule) {
        return proxyProvidesDSSClientErrorMapper(errorCodeMapperModule);
    }

    public static WJErrorMapper<DSSClientError> proxyProvidesDSSClientErrorMapper(ErrorCodeMapperModule errorCodeMapperModule) {
        return (WJErrorMapper) Preconditions.checkNotNull(errorCodeMapperModule.providesDSSClientErrorMapper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WJErrorMapper<DSSClientError> mo10268get() {
        return provideInstance(this.module);
    }
}
