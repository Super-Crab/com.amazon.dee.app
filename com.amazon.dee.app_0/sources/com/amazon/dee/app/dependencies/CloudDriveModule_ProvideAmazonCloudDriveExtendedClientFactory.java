package com.amazon.dee.app.dependencies;

import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.extended.AmazonCloudDriveExtendedClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CloudDriveModule_ProvideAmazonCloudDriveExtendedClientFactory implements Factory<AmazonCloudDriveExtendedClient> {
    private final Provider<AccountConfiguration> accountConfigurationProvider;
    private final Provider<ClientConfiguration> clientConfigurationProvider;
    private final CloudDriveModule module;

    public CloudDriveModule_ProvideAmazonCloudDriveExtendedClientFactory(CloudDriveModule cloudDriveModule, Provider<AccountConfiguration> provider, Provider<ClientConfiguration> provider2) {
        this.module = cloudDriveModule;
        this.accountConfigurationProvider = provider;
        this.clientConfigurationProvider = provider2;
    }

    public static CloudDriveModule_ProvideAmazonCloudDriveExtendedClientFactory create(CloudDriveModule cloudDriveModule, Provider<AccountConfiguration> provider, Provider<ClientConfiguration> provider2) {
        return new CloudDriveModule_ProvideAmazonCloudDriveExtendedClientFactory(cloudDriveModule, provider, provider2);
    }

    public static AmazonCloudDriveExtendedClient provideInstance(CloudDriveModule cloudDriveModule, Provider<AccountConfiguration> provider, Provider<ClientConfiguration> provider2) {
        return proxyProvideAmazonCloudDriveExtendedClient(cloudDriveModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static AmazonCloudDriveExtendedClient proxyProvideAmazonCloudDriveExtendedClient(CloudDriveModule cloudDriveModule, AccountConfiguration accountConfiguration, ClientConfiguration clientConfiguration) {
        return (AmazonCloudDriveExtendedClient) Preconditions.checkNotNull(cloudDriveModule.provideAmazonCloudDriveExtendedClient(accountConfiguration, clientConfiguration), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AmazonCloudDriveExtendedClient mo10268get() {
        return provideInstance(this.module, this.accountConfigurationProvider, this.clientConfigurationProvider);
    }
}
