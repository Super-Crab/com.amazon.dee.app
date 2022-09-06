package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import com.amazon.alexa.biloba.service.TimeZoneApi;
import com.dee.app.http.CoralService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class TimeZoneModule_ProvideTimeZoneApiFactory implements Factory<TimeZoneApi> {
    private final Provider<CoralService> coralServiceProvider;
    private final Provider<SchedulerProvider> schedulerProvider;

    public TimeZoneModule_ProvideTimeZoneApiFactory(Provider<CoralService> provider, Provider<SchedulerProvider> provider2) {
        this.coralServiceProvider = provider;
        this.schedulerProvider = provider2;
    }

    public static TimeZoneModule_ProvideTimeZoneApiFactory create(Provider<CoralService> provider, Provider<SchedulerProvider> provider2) {
        return new TimeZoneModule_ProvideTimeZoneApiFactory(provider, provider2);
    }

    public static TimeZoneApi provideInstance(Provider<CoralService> provider, Provider<SchedulerProvider> provider2) {
        return proxyProvideTimeZoneApi(provider.mo10268get(), provider2.mo10268get());
    }

    public static TimeZoneApi proxyProvideTimeZoneApi(CoralService coralService, SchedulerProvider schedulerProvider) {
        return (TimeZoneApi) Preconditions.checkNotNull(TimeZoneModule.provideTimeZoneApi(coralService, schedulerProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TimeZoneApi mo10268get() {
        return provideInstance(this.coralServiceProvider, this.schedulerProvider);
    }
}
