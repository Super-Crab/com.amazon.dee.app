package com.amazon.alexa.handsfree.notification.configurations.utterancebased;

import android.content.Context;
import com.amazon.alexa.handsfree.notification.api.ConfigurationProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class UtteranceConfigHandler_Factory implements Factory<UtteranceConfigHandler> {
    private final Provider<ConfigurationProvider> configurationProvider;
    private final Provider<Context> contextProvider;

    public UtteranceConfigHandler_Factory(Provider<Context> provider, Provider<ConfigurationProvider> provider2) {
        this.contextProvider = provider;
        this.configurationProvider = provider2;
    }

    public static UtteranceConfigHandler_Factory create(Provider<Context> provider, Provider<ConfigurationProvider> provider2) {
        return new UtteranceConfigHandler_Factory(provider, provider2);
    }

    public static UtteranceConfigHandler newUtteranceConfigHandler(Context context, ConfigurationProvider configurationProvider) {
        return new UtteranceConfigHandler(context, configurationProvider);
    }

    public static UtteranceConfigHandler provideInstance(Provider<Context> provider, Provider<ConfigurationProvider> provider2) {
        return new UtteranceConfigHandler(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UtteranceConfigHandler mo10268get() {
        return provideInstance(this.contextProvider, this.configurationProvider);
    }
}
