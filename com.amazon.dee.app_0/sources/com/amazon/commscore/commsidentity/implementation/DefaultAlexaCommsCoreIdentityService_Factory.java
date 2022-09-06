package com.amazon.commscore.commsidentity.implementation;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.commscore.commsidentity.common.TimeUtil;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class DefaultAlexaCommsCoreIdentityService_Factory implements Factory<DefaultAlexaCommsCoreIdentityService> {
    private final Provider<CommsCoreIdentityViewModel> commsCoreIdentityViewModelProvider;
    private final Provider<EventBus> eventBusLazyProvider;
    private final Provider<TimeUtil> timeUtilProvider;

    public DefaultAlexaCommsCoreIdentityService_Factory(Provider<EventBus> provider, Provider<CommsCoreIdentityViewModel> provider2, Provider<TimeUtil> provider3) {
        this.eventBusLazyProvider = provider;
        this.commsCoreIdentityViewModelProvider = provider2;
        this.timeUtilProvider = provider3;
    }

    public static DefaultAlexaCommsCoreIdentityService_Factory create(Provider<EventBus> provider, Provider<CommsCoreIdentityViewModel> provider2, Provider<TimeUtil> provider3) {
        return new DefaultAlexaCommsCoreIdentityService_Factory(provider, provider2, provider3);
    }

    public static DefaultAlexaCommsCoreIdentityService newDefaultAlexaCommsCoreIdentityService(Lazy<EventBus> lazy, CommsCoreIdentityViewModel commsCoreIdentityViewModel, TimeUtil timeUtil) {
        return new DefaultAlexaCommsCoreIdentityService(lazy, commsCoreIdentityViewModel, timeUtil);
    }

    public static DefaultAlexaCommsCoreIdentityService provideInstance(Provider<EventBus> provider, Provider<CommsCoreIdentityViewModel> provider2, Provider<TimeUtil> provider3) {
        return new DefaultAlexaCommsCoreIdentityService(DoubleCheck.lazy(provider), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultAlexaCommsCoreIdentityService mo10268get() {
        return provideInstance(this.eventBusLazyProvider, this.commsCoreIdentityViewModelProvider, this.timeUtilProvider);
    }
}
