package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.error.WiFiSyncAuthTokenFailedValidationException;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ErrorCodeMapperModule_ProvidesWiFiSyncAuthTokenFailedValidationMapperFactory implements Factory<WJErrorMapper<WiFiSyncAuthTokenFailedValidationException>> {
    private final ErrorCodeMapperModule module;

    public ErrorCodeMapperModule_ProvidesWiFiSyncAuthTokenFailedValidationMapperFactory(ErrorCodeMapperModule errorCodeMapperModule) {
        this.module = errorCodeMapperModule;
    }

    public static ErrorCodeMapperModule_ProvidesWiFiSyncAuthTokenFailedValidationMapperFactory create(ErrorCodeMapperModule errorCodeMapperModule) {
        return new ErrorCodeMapperModule_ProvidesWiFiSyncAuthTokenFailedValidationMapperFactory(errorCodeMapperModule);
    }

    public static WJErrorMapper<WiFiSyncAuthTokenFailedValidationException> provideInstance(ErrorCodeMapperModule errorCodeMapperModule) {
        return proxyProvidesWiFiSyncAuthTokenFailedValidationMapper(errorCodeMapperModule);
    }

    public static WJErrorMapper<WiFiSyncAuthTokenFailedValidationException> proxyProvidesWiFiSyncAuthTokenFailedValidationMapper(ErrorCodeMapperModule errorCodeMapperModule) {
        return (WJErrorMapper) Preconditions.checkNotNull(errorCodeMapperModule.providesWiFiSyncAuthTokenFailedValidationMapper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WJErrorMapper<WiFiSyncAuthTokenFailedValidationException> mo10268get() {
        return provideInstance(this.module);
    }
}
