package com.amazon.alexa.mobilytics.configuration;

import com.amazon.alexa.mobilytics.auth.CognitoCredentialsProvider;
import com.amazon.alexa.mobilytics.internal.JsonConverter;
import com.amazon.alexa.mobilytics.stream.KinesisClientProvider;
import com.amazonaws.regions.Regions;
import dagger.internal.Factory;
import java.util.Map;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class ConfigManager_Factory implements Factory<ConfigManager> {
    private final Provider<KinesisClientProvider> clientProvider;
    private final Provider<Map<Regions, String>> cognitoPoolIdsProvider;
    private final Provider<Config> configProvider;
    private final Provider<ConfigPuller> configPullerProvider;
    private final Provider<ConfigStorage> configStorageProvider;
    private final Provider<JsonConverter> converterProvider;
    private final Provider<CognitoCredentialsProvider.Factory> credentialsProviderFactoryProvider;

    public ConfigManager_Factory(Provider<ConfigPuller> provider, Provider<JsonConverter> provider2, Provider<ConfigStorage> provider3, Provider<CognitoCredentialsProvider.Factory> provider4, Provider<KinesisClientProvider> provider5, Provider<Map<Regions, String>> provider6, Provider<Config> provider7) {
        this.configPullerProvider = provider;
        this.converterProvider = provider2;
        this.configStorageProvider = provider3;
        this.credentialsProviderFactoryProvider = provider4;
        this.clientProvider = provider5;
        this.cognitoPoolIdsProvider = provider6;
        this.configProvider = provider7;
    }

    public static ConfigManager_Factory create(Provider<ConfigPuller> provider, Provider<JsonConverter> provider2, Provider<ConfigStorage> provider3, Provider<CognitoCredentialsProvider.Factory> provider4, Provider<KinesisClientProvider> provider5, Provider<Map<Regions, String>> provider6, Provider<Config> provider7) {
        return new ConfigManager_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static ConfigManager newConfigManager(ConfigPuller configPuller, JsonConverter jsonConverter, ConfigStorage configStorage, CognitoCredentialsProvider.Factory factory, KinesisClientProvider kinesisClientProvider, Map<Regions, String> map, Config config) {
        return new ConfigManager(configPuller, jsonConverter, configStorage, factory, kinesisClientProvider, map, config);
    }

    public static ConfigManager provideInstance(Provider<ConfigPuller> provider, Provider<JsonConverter> provider2, Provider<ConfigStorage> provider3, Provider<CognitoCredentialsProvider.Factory> provider4, Provider<KinesisClientProvider> provider5, Provider<Map<Regions, String>> provider6, Provider<Config> provider7) {
        return new ConfigManager(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ConfigManager mo10268get() {
        return provideInstance(this.configPullerProvider, this.converterProvider, this.configStorageProvider, this.credentialsProviderFactoryProvider, this.clientProvider, this.cognitoPoolIdsProvider, this.configProvider);
    }
}
