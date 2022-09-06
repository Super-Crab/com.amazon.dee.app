package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import com.amazon.alexa.biloba.service.AlertConfigurationApi;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.dee.app.http.CoralService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlertConfigurationModule_ProvideAlertConfigurationApiFactory implements Factory<AlertConfigurationApi> {
    private final Provider<CoralService> coralServiceProvider;
    private final Provider<PersonIdProvider> personIdProvider;
    private final Provider<SchedulerProvider> schedulerProvider;

    public AlertConfigurationModule_ProvideAlertConfigurationApiFactory(Provider<CoralService> provider, Provider<PersonIdProvider> provider2, Provider<SchedulerProvider> provider3) {
        this.coralServiceProvider = provider;
        this.personIdProvider = provider2;
        this.schedulerProvider = provider3;
    }

    public static AlertConfigurationModule_ProvideAlertConfigurationApiFactory create(Provider<CoralService> provider, Provider<PersonIdProvider> provider2, Provider<SchedulerProvider> provider3) {
        return new AlertConfigurationModule_ProvideAlertConfigurationApiFactory(provider, provider2, provider3);
    }

    public static AlertConfigurationApi provideInstance(Provider<CoralService> provider, Provider<PersonIdProvider> provider2, Provider<SchedulerProvider> provider3) {
        return proxyProvideAlertConfigurationApi(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static AlertConfigurationApi proxyProvideAlertConfigurationApi(CoralService coralService, PersonIdProvider personIdProvider, SchedulerProvider schedulerProvider) {
        return (AlertConfigurationApi) Preconditions.checkNotNull(AlertConfigurationModule.provideAlertConfigurationApi(coralService, personIdProvider, schedulerProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlertConfigurationApi mo10268get() {
        return provideInstance(this.coralServiceProvider, this.personIdProvider, this.schedulerProvider);
    }
}
