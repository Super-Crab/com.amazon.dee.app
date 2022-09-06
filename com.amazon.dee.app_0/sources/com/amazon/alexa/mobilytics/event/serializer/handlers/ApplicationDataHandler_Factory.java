package com.amazon.alexa.mobilytics.event.serializer.handlers;

import com.amazon.alexa.mobilytics.configuration.ApplicationConfiguration;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class ApplicationDataHandler_Factory implements Factory<ApplicationDataHandler> {
    private final Provider<ApplicationConfiguration> appConfigProvider;

    public ApplicationDataHandler_Factory(Provider<ApplicationConfiguration> provider) {
        this.appConfigProvider = provider;
    }

    public static ApplicationDataHandler_Factory create(Provider<ApplicationConfiguration> provider) {
        return new ApplicationDataHandler_Factory(provider);
    }

    public static ApplicationDataHandler newApplicationDataHandler(ApplicationConfiguration applicationConfiguration) {
        return new ApplicationDataHandler(applicationConfiguration);
    }

    public static ApplicationDataHandler provideInstance(Provider<ApplicationConfiguration> provider) {
        return new ApplicationDataHandler(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ApplicationDataHandler mo10268get() {
        return provideInstance(this.appConfigProvider);
    }
}
