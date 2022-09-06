package com.amazon.alexa.biloba.model;

import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import com.amazon.alexa.biloba.generated.network.api.ActivityApi;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.dee.app.http.CoralService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class Configuration_ProvideActivityApiFactory implements Factory<ActivityApi> {
    private final Provider<CoralService> coralServiceProvider;
    private final Provider<PersonIdProvider> personIdProvider;
    private final Provider<SchedulerProvider> schedulerProvider;

    public Configuration_ProvideActivityApiFactory(Provider<CoralService> provider, Provider<PersonIdProvider> provider2, Provider<SchedulerProvider> provider3) {
        this.coralServiceProvider = provider;
        this.personIdProvider = provider2;
        this.schedulerProvider = provider3;
    }

    public static Configuration_ProvideActivityApiFactory create(Provider<CoralService> provider, Provider<PersonIdProvider> provider2, Provider<SchedulerProvider> provider3) {
        return new Configuration_ProvideActivityApiFactory(provider, provider2, provider3);
    }

    public static ActivityApi provideInstance(Provider<CoralService> provider, Provider<PersonIdProvider> provider2, Provider<SchedulerProvider> provider3) {
        return proxyProvideActivityApi(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static ActivityApi proxyProvideActivityApi(CoralService coralService, PersonIdProvider personIdProvider, SchedulerProvider schedulerProvider) {
        return (ActivityApi) Preconditions.checkNotNull(Configuration.provideActivityApi(coralService, personIdProvider, schedulerProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ActivityApi mo10268get() {
        return provideInstance(this.coralServiceProvider, this.personIdProvider, this.schedulerProvider);
    }
}
