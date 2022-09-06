package com.amazon.alexa.mobilytics.configuration;

import com.amazonaws.regions.Regions;
import dagger.internal.Factory;
import java.util.Map;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class DefaultEndpointManager_Factory implements Factory<DefaultEndpointManager> {
    private final Provider<Map<Regions, String>> cognitoPoolIdsProvider;
    private final Provider<ConfigManager> configManagerProvider;

    public DefaultEndpointManager_Factory(Provider<ConfigManager> provider, Provider<Map<Regions, String>> provider2) {
        this.configManagerProvider = provider;
        this.cognitoPoolIdsProvider = provider2;
    }

    public static DefaultEndpointManager_Factory create(Provider<ConfigManager> provider, Provider<Map<Regions, String>> provider2) {
        return new DefaultEndpointManager_Factory(provider, provider2);
    }

    public static DefaultEndpointManager newDefaultEndpointManager(ConfigManager configManager, Map<Regions, String> map) {
        return new DefaultEndpointManager(configManager, map);
    }

    public static DefaultEndpointManager provideInstance(Provider<ConfigManager> provider, Provider<Map<Regions, String>> provider2) {
        return new DefaultEndpointManager(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultEndpointManager mo10268get() {
        return provideInstance(this.configManagerProvider, this.cognitoPoolIdsProvider);
    }
}
