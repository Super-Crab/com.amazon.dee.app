package com.amazon.alexa.client.core.configuration;

import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import java.util.Properties;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class PropertiesConfigurationLoader_Factory implements Factory<PropertiesConfigurationLoader> {
    private final Provider<Properties> propertiesProvider;

    public PropertiesConfigurationLoader_Factory(Provider<Properties> provider) {
        this.propertiesProvider = provider;
    }

    public static PropertiesConfigurationLoader_Factory create(Provider<Properties> provider) {
        return new PropertiesConfigurationLoader_Factory(provider);
    }

    public static PropertiesConfigurationLoader newPropertiesConfigurationLoader(Lazy<Properties> lazy) {
        return new PropertiesConfigurationLoader(lazy);
    }

    public static PropertiesConfigurationLoader provideInstance(Provider<Properties> provider) {
        return new PropertiesConfigurationLoader(DoubleCheck.lazy(provider));
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PropertiesConfigurationLoader mo10268get() {
        return provideInstance(this.propertiesProvider);
    }
}
