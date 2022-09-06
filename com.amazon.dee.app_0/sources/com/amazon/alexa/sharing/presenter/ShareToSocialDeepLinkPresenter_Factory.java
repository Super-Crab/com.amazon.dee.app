package com.amazon.alexa.sharing.presenter;

import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class ShareToSocialDeepLinkPresenter_Factory implements Factory<ShareToSocialDeepLinkPresenter> {
    private final Provider<AlexaCommsCoreMetricsService> metricsServiceLazyProvider;

    public ShareToSocialDeepLinkPresenter_Factory(Provider<AlexaCommsCoreMetricsService> provider) {
        this.metricsServiceLazyProvider = provider;
    }

    public static ShareToSocialDeepLinkPresenter_Factory create(Provider<AlexaCommsCoreMetricsService> provider) {
        return new ShareToSocialDeepLinkPresenter_Factory(provider);
    }

    public static ShareToSocialDeepLinkPresenter newShareToSocialDeepLinkPresenter(Lazy<AlexaCommsCoreMetricsService> lazy) {
        return new ShareToSocialDeepLinkPresenter(lazy);
    }

    public static ShareToSocialDeepLinkPresenter provideInstance(Provider<AlexaCommsCoreMetricsService> provider) {
        return new ShareToSocialDeepLinkPresenter(DoubleCheck.lazy(provider));
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ShareToSocialDeepLinkPresenter mo10268get() {
        return provideInstance(this.metricsServiceLazyProvider);
    }
}
