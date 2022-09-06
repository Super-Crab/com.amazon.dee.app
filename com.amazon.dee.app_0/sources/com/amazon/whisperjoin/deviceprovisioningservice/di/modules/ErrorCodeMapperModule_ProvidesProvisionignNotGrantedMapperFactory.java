package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisioningNotGranted;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ErrorCodeMapperModule_ProvidesProvisionignNotGrantedMapperFactory implements Factory<WJErrorMapper<ProvisioningNotGranted>> {
    private final ErrorCodeMapperModule module;

    public ErrorCodeMapperModule_ProvidesProvisionignNotGrantedMapperFactory(ErrorCodeMapperModule errorCodeMapperModule) {
        this.module = errorCodeMapperModule;
    }

    public static ErrorCodeMapperModule_ProvidesProvisionignNotGrantedMapperFactory create(ErrorCodeMapperModule errorCodeMapperModule) {
        return new ErrorCodeMapperModule_ProvidesProvisionignNotGrantedMapperFactory(errorCodeMapperModule);
    }

    public static WJErrorMapper<ProvisioningNotGranted> provideInstance(ErrorCodeMapperModule errorCodeMapperModule) {
        return proxyProvidesProvisionignNotGrantedMapper(errorCodeMapperModule);
    }

    public static WJErrorMapper<ProvisioningNotGranted> proxyProvidesProvisionignNotGrantedMapper(ErrorCodeMapperModule errorCodeMapperModule) {
        return (WJErrorMapper) Preconditions.checkNotNull(errorCodeMapperModule.providesProvisionignNotGrantedMapper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WJErrorMapper<ProvisioningNotGranted> mo10268get() {
        return provideInstance(this.module);
    }
}
