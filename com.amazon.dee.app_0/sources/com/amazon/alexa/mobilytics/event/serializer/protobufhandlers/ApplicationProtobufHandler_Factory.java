package com.amazon.alexa.mobilytics.event.serializer.protobufhandlers;

import com.amazon.alexa.mobilytics.configuration.ApplicationConfiguration;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class ApplicationProtobufHandler_Factory implements Factory<ApplicationProtobufHandler> {
    private final Provider<ApplicationConfiguration> appConfigProvider;

    public ApplicationProtobufHandler_Factory(Provider<ApplicationConfiguration> provider) {
        this.appConfigProvider = provider;
    }

    public static ApplicationProtobufHandler_Factory create(Provider<ApplicationConfiguration> provider) {
        return new ApplicationProtobufHandler_Factory(provider);
    }

    public static ApplicationProtobufHandler newApplicationProtobufHandler(ApplicationConfiguration applicationConfiguration) {
        return new ApplicationProtobufHandler(applicationConfiguration);
    }

    public static ApplicationProtobufHandler provideInstance(Provider<ApplicationConfiguration> provider) {
        return new ApplicationProtobufHandler(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ApplicationProtobufHandler mo10268get() {
        return provideInstance(this.appConfigProvider);
    }
}
