package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
/* loaded from: classes12.dex */
public final class CloudDriveModule_ProvideClientConfigurationFactory implements Factory<ClientConfiguration> {
    private final Provider<Context> contextProvider;
    private final CloudDriveModule module;
    private final Provider<OkHttpClient> okHttpClientProvider;

    public CloudDriveModule_ProvideClientConfigurationFactory(CloudDriveModule cloudDriveModule, Provider<OkHttpClient> provider, Provider<Context> provider2) {
        this.module = cloudDriveModule;
        this.okHttpClientProvider = provider;
        this.contextProvider = provider2;
    }

    public static CloudDriveModule_ProvideClientConfigurationFactory create(CloudDriveModule cloudDriveModule, Provider<OkHttpClient> provider, Provider<Context> provider2) {
        return new CloudDriveModule_ProvideClientConfigurationFactory(cloudDriveModule, provider, provider2);
    }

    public static ClientConfiguration provideInstance(CloudDriveModule cloudDriveModule, Provider<OkHttpClient> provider, Provider<Context> provider2) {
        return proxyProvideClientConfiguration(cloudDriveModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static ClientConfiguration proxyProvideClientConfiguration(CloudDriveModule cloudDriveModule, OkHttpClient okHttpClient, Context context) {
        return (ClientConfiguration) Preconditions.checkNotNull(cloudDriveModule.provideClientConfiguration(okHttpClient, context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ClientConfiguration mo10268get() {
        return provideInstance(this.module, this.okHttpClientProvider, this.contextProvider);
    }
}
