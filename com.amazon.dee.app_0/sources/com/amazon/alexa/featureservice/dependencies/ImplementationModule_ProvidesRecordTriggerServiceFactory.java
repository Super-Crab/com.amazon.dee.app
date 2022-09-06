package com.amazon.alexa.featureservice.dependencies;

import com.amazon.alexa.featureservice.recordTrigger.RecordTriggerService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.dee.app.http.CoralService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class ImplementationModule_ProvidesRecordTriggerServiceFactory implements Factory<RecordTriggerService> {
    private final Provider<CoralService> coralServiceLazyProvider;
    private final Provider<Mobilytics> mobilyticsLazyProvider;
    private final ImplementationModule module;

    public ImplementationModule_ProvidesRecordTriggerServiceFactory(ImplementationModule implementationModule, Provider<CoralService> provider, Provider<Mobilytics> provider2) {
        this.module = implementationModule;
        this.coralServiceLazyProvider = provider;
        this.mobilyticsLazyProvider = provider2;
    }

    public static ImplementationModule_ProvidesRecordTriggerServiceFactory create(ImplementationModule implementationModule, Provider<CoralService> provider, Provider<Mobilytics> provider2) {
        return new ImplementationModule_ProvidesRecordTriggerServiceFactory(implementationModule, provider, provider2);
    }

    public static RecordTriggerService provideInstance(ImplementationModule implementationModule, Provider<CoralService> provider, Provider<Mobilytics> provider2) {
        return proxyProvidesRecordTriggerService(implementationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2));
    }

    public static RecordTriggerService proxyProvidesRecordTriggerService(ImplementationModule implementationModule, Lazy<CoralService> lazy, Lazy<Mobilytics> lazy2) {
        return (RecordTriggerService) Preconditions.checkNotNull(implementationModule.providesRecordTriggerService(lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RecordTriggerService mo10268get() {
        return provideInstance(this.module, this.coralServiceLazyProvider, this.mobilyticsLazyProvider);
    }
}
