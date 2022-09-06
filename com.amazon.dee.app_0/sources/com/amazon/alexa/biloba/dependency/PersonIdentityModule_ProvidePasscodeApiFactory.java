package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import com.amazon.alexa.biloba.service.PasscodeApi;
import com.dee.app.http.CoralService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class PersonIdentityModule_ProvidePasscodeApiFactory implements Factory<PasscodeApi> {
    private final Provider<CoralService> coralServiceProvider;
    private final Provider<SchedulerProvider> schedulerProvider;

    public PersonIdentityModule_ProvidePasscodeApiFactory(Provider<CoralService> provider, Provider<SchedulerProvider> provider2) {
        this.coralServiceProvider = provider;
        this.schedulerProvider = provider2;
    }

    public static PersonIdentityModule_ProvidePasscodeApiFactory create(Provider<CoralService> provider, Provider<SchedulerProvider> provider2) {
        return new PersonIdentityModule_ProvidePasscodeApiFactory(provider, provider2);
    }

    public static PasscodeApi provideInstance(Provider<CoralService> provider, Provider<SchedulerProvider> provider2) {
        return proxyProvidePasscodeApi(provider.mo10268get(), provider2.mo10268get());
    }

    public static PasscodeApi proxyProvidePasscodeApi(CoralService coralService, SchedulerProvider schedulerProvider) {
        return (PasscodeApi) Preconditions.checkNotNull(PersonIdentityModule.providePasscodeApi(coralService, schedulerProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PasscodeApi mo10268get() {
        return provideInstance(this.coralServiceProvider, this.schedulerProvider);
    }
}
