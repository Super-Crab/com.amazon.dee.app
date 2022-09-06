package com.amazon.alexa.handsfree.notification.configurations.timebased;

import android.content.Context;
import com.amazon.alexa.handsfree.notification.api.ConfigurationProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class TimeIntervalConfigHandler_Factory implements Factory<TimeIntervalConfigHandler> {
    private final Provider<ConfigurationProvider> configurationProvider;
    private final Provider<Context> contextProvider;

    public TimeIntervalConfigHandler_Factory(Provider<Context> provider, Provider<ConfigurationProvider> provider2) {
        this.contextProvider = provider;
        this.configurationProvider = provider2;
    }

    public static TimeIntervalConfigHandler_Factory create(Provider<Context> provider, Provider<ConfigurationProvider> provider2) {
        return new TimeIntervalConfigHandler_Factory(provider, provider2);
    }

    public static TimeIntervalConfigHandler newTimeIntervalConfigHandler(Context context, ConfigurationProvider configurationProvider) {
        return new TimeIntervalConfigHandler(context, configurationProvider);
    }

    public static TimeIntervalConfigHandler provideInstance(Provider<Context> provider, Provider<ConfigurationProvider> provider2) {
        return new TimeIntervalConfigHandler(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TimeIntervalConfigHandler mo10268get() {
        return provideInstance(this.contextProvider, this.configurationProvider);
    }
}
