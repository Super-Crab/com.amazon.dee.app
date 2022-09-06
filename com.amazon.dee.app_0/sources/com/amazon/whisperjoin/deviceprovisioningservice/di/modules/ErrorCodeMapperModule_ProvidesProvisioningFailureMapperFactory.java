package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisioningFailureException;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ErrorCodeMapperModule_ProvidesProvisioningFailureMapperFactory implements Factory<WJErrorMapper<ProvisioningFailureException>> {
    private final ErrorCodeMapperModule module;

    public ErrorCodeMapperModule_ProvidesProvisioningFailureMapperFactory(ErrorCodeMapperModule errorCodeMapperModule) {
        this.module = errorCodeMapperModule;
    }

    public static ErrorCodeMapperModule_ProvidesProvisioningFailureMapperFactory create(ErrorCodeMapperModule errorCodeMapperModule) {
        return new ErrorCodeMapperModule_ProvidesProvisioningFailureMapperFactory(errorCodeMapperModule);
    }

    public static WJErrorMapper<ProvisioningFailureException> provideInstance(ErrorCodeMapperModule errorCodeMapperModule) {
        return proxyProvidesProvisioningFailureMapper(errorCodeMapperModule);
    }

    public static WJErrorMapper<ProvisioningFailureException> proxyProvidesProvisioningFailureMapper(ErrorCodeMapperModule errorCodeMapperModule) {
        return (WJErrorMapper) Preconditions.checkNotNull(errorCodeMapperModule.providesProvisioningFailureMapper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WJErrorMapper<ProvisioningFailureException> mo10268get() {
        return provideInstance(this.module);
    }
}
