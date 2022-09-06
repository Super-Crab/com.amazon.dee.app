package com.amazon.alexa.handsfree.notification.configurations.quicksettings;

import android.content.Context;
import com.amazon.alexa.handsfree.notification.api.ConfigurationProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class QuickSettingsConfigHandler_Factory implements Factory<QuickSettingsConfigHandler> {
    private final Provider<ConfigurationProvider> configurationProvider;
    private final Provider<Context> contextProvider;

    public QuickSettingsConfigHandler_Factory(Provider<Context> provider, Provider<ConfigurationProvider> provider2) {
        this.contextProvider = provider;
        this.configurationProvider = provider2;
    }

    public static QuickSettingsConfigHandler_Factory create(Provider<Context> provider, Provider<ConfigurationProvider> provider2) {
        return new QuickSettingsConfigHandler_Factory(provider, provider2);
    }

    public static QuickSettingsConfigHandler newQuickSettingsConfigHandler(Context context, ConfigurationProvider configurationProvider) {
        return new QuickSettingsConfigHandler(context, configurationProvider);
    }

    public static QuickSettingsConfigHandler provideInstance(Provider<Context> provider, Provider<ConfigurationProvider> provider2) {
        return new QuickSettingsConfigHandler(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public QuickSettingsConfigHandler mo10268get() {
        return provideInstance(this.contextProvider, this.configurationProvider);
    }
}
