package com.amazon.commscore.commsidentity.dependencies;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.commsidentity.common.TimeUtil;
import com.amazon.commscore.commsidentity.implementation.CommsCoreIdentityViewModel;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsIdentityModule_ProvidesCommsCoreIdentityServiceFactory implements Factory<AlexaCommsCoreIdentityService> {
    private final Provider<CommsCoreIdentityViewModel> commsCoreIdentityViewModelProvider;
    private final Provider<EventBus> eventBusLazyProvider;
    private final CommsIdentityModule module;
    private final Provider<TimeUtil> timeUtilProvider;

    public CommsIdentityModule_ProvidesCommsCoreIdentityServiceFactory(CommsIdentityModule commsIdentityModule, Provider<EventBus> provider, Provider<CommsCoreIdentityViewModel> provider2, Provider<TimeUtil> provider3) {
        this.module = commsIdentityModule;
        this.eventBusLazyProvider = provider;
        this.commsCoreIdentityViewModelProvider = provider2;
        this.timeUtilProvider = provider3;
    }

    public static CommsIdentityModule_ProvidesCommsCoreIdentityServiceFactory create(CommsIdentityModule commsIdentityModule, Provider<EventBus> provider, Provider<CommsCoreIdentityViewModel> provider2, Provider<TimeUtil> provider3) {
        return new CommsIdentityModule_ProvidesCommsCoreIdentityServiceFactory(commsIdentityModule, provider, provider2, provider3);
    }

    public static AlexaCommsCoreIdentityService provideInstance(CommsIdentityModule commsIdentityModule, Provider<EventBus> provider, Provider<CommsCoreIdentityViewModel> provider2, Provider<TimeUtil> provider3) {
        return proxyProvidesCommsCoreIdentityService(commsIdentityModule, DoubleCheck.lazy(provider), provider2.mo10268get(), provider3.mo10268get());
    }

    public static AlexaCommsCoreIdentityService proxyProvidesCommsCoreIdentityService(CommsIdentityModule commsIdentityModule, Lazy<EventBus> lazy, CommsCoreIdentityViewModel commsCoreIdentityViewModel, TimeUtil timeUtil) {
        return (AlexaCommsCoreIdentityService) Preconditions.checkNotNull(commsIdentityModule.providesCommsCoreIdentityService(lazy, commsCoreIdentityViewModel, timeUtil), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaCommsCoreIdentityService mo10268get() {
        return provideInstance(this.module, this.eventBusLazyProvider, this.commsCoreIdentityViewModelProvider, this.timeUtilProvider);
    }
}
