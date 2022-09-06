package com.amazon.alexa.featureservice.implementation;

import com.amazon.alexa.featureservice.sessionManagement.SessionManager;
import com.amazon.alexa.featureservice.util.Analytics;
import com.amazon.alexa.featureservice.util.SafeEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class AlexaMobileAndroidFeatureServiceImpl_Factory implements Factory<AlexaMobileAndroidFeatureServiceImpl> {
    private final Provider<Analytics> analyticsProvider;
    private final Provider<SafeEventBus> safeEventBusProvider;
    private final Provider<SessionManager> sessionManagerProvider;
    private final Provider<FeatureServiceViewModel> viewModelProvider;

    public AlexaMobileAndroidFeatureServiceImpl_Factory(Provider<FeatureServiceViewModel> provider, Provider<SessionManager> provider2, Provider<SafeEventBus> provider3, Provider<Analytics> provider4) {
        this.viewModelProvider = provider;
        this.sessionManagerProvider = provider2;
        this.safeEventBusProvider = provider3;
        this.analyticsProvider = provider4;
    }

    public static AlexaMobileAndroidFeatureServiceImpl_Factory create(Provider<FeatureServiceViewModel> provider, Provider<SessionManager> provider2, Provider<SafeEventBus> provider3, Provider<Analytics> provider4) {
        return new AlexaMobileAndroidFeatureServiceImpl_Factory(provider, provider2, provider3, provider4);
    }

    public static AlexaMobileAndroidFeatureServiceImpl newAlexaMobileAndroidFeatureServiceImpl(FeatureServiceViewModel featureServiceViewModel, SessionManager sessionManager, SafeEventBus safeEventBus, Analytics analytics) {
        return new AlexaMobileAndroidFeatureServiceImpl(featureServiceViewModel, sessionManager, safeEventBus, analytics);
    }

    public static AlexaMobileAndroidFeatureServiceImpl provideInstance(Provider<FeatureServiceViewModel> provider, Provider<SessionManager> provider2, Provider<SafeEventBus> provider3, Provider<Analytics> provider4) {
        return new AlexaMobileAndroidFeatureServiceImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaMobileAndroidFeatureServiceImpl mo10268get() {
        return provideInstance(this.viewModelProvider, this.sessionManagerProvider, this.safeEventBusProvider, this.analyticsProvider);
    }
}
