package com.amazon.alexa.biloba.network.api;

import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.dee.app.http.CoralService;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class GroupV2Api_Factory implements Factory<GroupV2Api> {
    private final Provider<CoralService> coralServiceProvider;
    private final Provider<PersonIdProvider> personIdProvider;
    private final Provider<SchedulerProvider> schedulerProvider;

    public GroupV2Api_Factory(Provider<CoralService> provider, Provider<PersonIdProvider> provider2, Provider<SchedulerProvider> provider3) {
        this.coralServiceProvider = provider;
        this.personIdProvider = provider2;
        this.schedulerProvider = provider3;
    }

    public static GroupV2Api_Factory create(Provider<CoralService> provider, Provider<PersonIdProvider> provider2, Provider<SchedulerProvider> provider3) {
        return new GroupV2Api_Factory(provider, provider2, provider3);
    }

    public static GroupV2Api newGroupV2Api(CoralService coralService, PersonIdProvider personIdProvider, SchedulerProvider schedulerProvider) {
        return new GroupV2Api(coralService, personIdProvider, schedulerProvider);
    }

    public static GroupV2Api provideInstance(Provider<CoralService> provider, Provider<PersonIdProvider> provider2, Provider<SchedulerProvider> provider3) {
        return new GroupV2Api(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public GroupV2Api mo10268get() {
        return provideInstance(this.coralServiceProvider, this.personIdProvider, this.schedulerProvider);
    }
}
