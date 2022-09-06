package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.error.PreconditionFailureException;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ErrorCodeMapperModule_ProvidesPreconditionalFailureMapperFactory implements Factory<WJErrorMapper<PreconditionFailureException>> {
    private final ErrorCodeMapperModule module;

    public ErrorCodeMapperModule_ProvidesPreconditionalFailureMapperFactory(ErrorCodeMapperModule errorCodeMapperModule) {
        this.module = errorCodeMapperModule;
    }

    public static ErrorCodeMapperModule_ProvidesPreconditionalFailureMapperFactory create(ErrorCodeMapperModule errorCodeMapperModule) {
        return new ErrorCodeMapperModule_ProvidesPreconditionalFailureMapperFactory(errorCodeMapperModule);
    }

    public static WJErrorMapper<PreconditionFailureException> provideInstance(ErrorCodeMapperModule errorCodeMapperModule) {
        return proxyProvidesPreconditionalFailureMapper(errorCodeMapperModule);
    }

    public static WJErrorMapper<PreconditionFailureException> proxyProvidesPreconditionalFailureMapper(ErrorCodeMapperModule errorCodeMapperModule) {
        return (WJErrorMapper) Preconditions.checkNotNull(errorCodeMapperModule.providesPreconditionalFailureMapper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WJErrorMapper<PreconditionFailureException> mo10268get() {
        return provideInstance(this.module);
    }
}
