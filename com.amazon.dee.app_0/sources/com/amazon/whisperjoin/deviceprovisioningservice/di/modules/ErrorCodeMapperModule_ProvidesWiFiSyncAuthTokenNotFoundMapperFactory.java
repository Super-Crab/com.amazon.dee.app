package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.error.WiFiSyncAuthTokenNotFoundException;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ErrorCodeMapperModule_ProvidesWiFiSyncAuthTokenNotFoundMapperFactory implements Factory<WJErrorMapper<WiFiSyncAuthTokenNotFoundException>> {
    private final ErrorCodeMapperModule module;

    public ErrorCodeMapperModule_ProvidesWiFiSyncAuthTokenNotFoundMapperFactory(ErrorCodeMapperModule errorCodeMapperModule) {
        this.module = errorCodeMapperModule;
    }

    public static ErrorCodeMapperModule_ProvidesWiFiSyncAuthTokenNotFoundMapperFactory create(ErrorCodeMapperModule errorCodeMapperModule) {
        return new ErrorCodeMapperModule_ProvidesWiFiSyncAuthTokenNotFoundMapperFactory(errorCodeMapperModule);
    }

    public static WJErrorMapper<WiFiSyncAuthTokenNotFoundException> provideInstance(ErrorCodeMapperModule errorCodeMapperModule) {
        return proxyProvidesWiFiSyncAuthTokenNotFoundMapper(errorCodeMapperModule);
    }

    public static WJErrorMapper<WiFiSyncAuthTokenNotFoundException> proxyProvidesWiFiSyncAuthTokenNotFoundMapper(ErrorCodeMapperModule errorCodeMapperModule) {
        return (WJErrorMapper) Preconditions.checkNotNull(errorCodeMapperModule.providesWiFiSyncAuthTokenNotFoundMapper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WJErrorMapper<WiFiSyncAuthTokenNotFoundException> mo10268get() {
        return provideInstance(this.module);
    }
}
