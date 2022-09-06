package com.amazon.deecomms.core;

import com.amazon.deecomms.common.network.okhttp.OkHttpClientWrapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class CloudDriveModule_ProvideOkHttpClientWrapperFactory implements Factory<OkHttpClientWrapper> {
    private final CloudDriveModule module;

    public CloudDriveModule_ProvideOkHttpClientWrapperFactory(CloudDriveModule cloudDriveModule) {
        this.module = cloudDriveModule;
    }

    public static CloudDriveModule_ProvideOkHttpClientWrapperFactory create(CloudDriveModule cloudDriveModule) {
        return new CloudDriveModule_ProvideOkHttpClientWrapperFactory(cloudDriveModule);
    }

    public static OkHttpClientWrapper provideInstance(CloudDriveModule cloudDriveModule) {
        return (OkHttpClientWrapper) Preconditions.checkNotNull(cloudDriveModule.provideOkHttpClientWrapper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static OkHttpClientWrapper proxyProvideOkHttpClientWrapper(CloudDriveModule cloudDriveModule) {
        return (OkHttpClientWrapper) Preconditions.checkNotNull(cloudDriveModule.provideOkHttpClientWrapper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public OkHttpClientWrapper mo10268get() {
        return provideInstance(this.module);
    }
}
