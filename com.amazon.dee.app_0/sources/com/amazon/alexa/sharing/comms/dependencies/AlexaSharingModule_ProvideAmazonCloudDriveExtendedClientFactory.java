package com.amazon.alexa.sharing.comms.dependencies;

import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.extended.AmazonCloudDriveExtendedClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class AlexaSharingModule_ProvideAmazonCloudDriveExtendedClientFactory implements Factory<AmazonCloudDriveExtendedClient> {
    private final Provider<AccountConfiguration> accountConfigurationProvider;
    private final Provider<ClientConfiguration> clientConfigurationProvider;
    private final AlexaSharingModule module;

    public AlexaSharingModule_ProvideAmazonCloudDriveExtendedClientFactory(AlexaSharingModule alexaSharingModule, Provider<AccountConfiguration> provider, Provider<ClientConfiguration> provider2) {
        this.module = alexaSharingModule;
        this.accountConfigurationProvider = provider;
        this.clientConfigurationProvider = provider2;
    }

    public static AlexaSharingModule_ProvideAmazonCloudDriveExtendedClientFactory create(AlexaSharingModule alexaSharingModule, Provider<AccountConfiguration> provider, Provider<ClientConfiguration> provider2) {
        return new AlexaSharingModule_ProvideAmazonCloudDriveExtendedClientFactory(alexaSharingModule, provider, provider2);
    }

    public static AmazonCloudDriveExtendedClient provideInstance(AlexaSharingModule alexaSharingModule, Provider<AccountConfiguration> provider, Provider<ClientConfiguration> provider2) {
        return proxyProvideAmazonCloudDriveExtendedClient(alexaSharingModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static AmazonCloudDriveExtendedClient proxyProvideAmazonCloudDriveExtendedClient(AlexaSharingModule alexaSharingModule, AccountConfiguration accountConfiguration, ClientConfiguration clientConfiguration) {
        return (AmazonCloudDriveExtendedClient) Preconditions.checkNotNull(alexaSharingModule.provideAmazonCloudDriveExtendedClient(accountConfiguration, clientConfiguration), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AmazonCloudDriveExtendedClient mo10268get() {
        return provideInstance(this.module, this.accountConfigurationProvider, this.clientConfigurationProvider);
    }
}
