package com.amazon.alexa.sharing.activity;

import com.amazon.alexa.sharing.presenter.FallbackStrategyPresenter;
import com.amazon.alexa.sharing.presenter.ShareToSocialDeepLinkPresenter;
import com.amazon.alexa.sharing.util.FeatureServiceUtil;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class ShareSheetActivity_MembersInjector implements MembersInjector<ShareSheetActivity> {
    private final Provider<FallbackStrategyPresenter> fallbackStrategyPresenterProvider;
    private final Provider<FeatureServiceUtil> featureServiceUtilProvider;
    private final Provider<AlexaCommsCoreMetricsService> metricsServiceLazyProvider;
    private final Provider<ShareToSocialDeepLinkPresenter> presenterProvider;

    public ShareSheetActivity_MembersInjector(Provider<AlexaCommsCoreMetricsService> provider, Provider<ShareToSocialDeepLinkPresenter> provider2, Provider<FallbackStrategyPresenter> provider3, Provider<FeatureServiceUtil> provider4) {
        this.metricsServiceLazyProvider = provider;
        this.presenterProvider = provider2;
        this.fallbackStrategyPresenterProvider = provider3;
        this.featureServiceUtilProvider = provider4;
    }

    public static MembersInjector<ShareSheetActivity> create(Provider<AlexaCommsCoreMetricsService> provider, Provider<ShareToSocialDeepLinkPresenter> provider2, Provider<FallbackStrategyPresenter> provider3, Provider<FeatureServiceUtil> provider4) {
        return new ShareSheetActivity_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectFallbackStrategyPresenter(ShareSheetActivity shareSheetActivity, FallbackStrategyPresenter fallbackStrategyPresenter) {
        shareSheetActivity.fallbackStrategyPresenter = fallbackStrategyPresenter;
    }

    public static void injectFeatureServiceUtil(ShareSheetActivity shareSheetActivity, FeatureServiceUtil featureServiceUtil) {
        shareSheetActivity.featureServiceUtil = featureServiceUtil;
    }

    public static void injectMetricsServiceLazy(ShareSheetActivity shareSheetActivity, Lazy<AlexaCommsCoreMetricsService> lazy) {
        shareSheetActivity.metricsServiceLazy = lazy;
    }

    public static void injectPresenter(ShareSheetActivity shareSheetActivity, ShareToSocialDeepLinkPresenter shareToSocialDeepLinkPresenter) {
        shareSheetActivity.presenter = shareToSocialDeepLinkPresenter;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ShareSheetActivity shareSheetActivity) {
        injectMetricsServiceLazy(shareSheetActivity, DoubleCheck.lazy(this.metricsServiceLazyProvider));
        injectPresenter(shareSheetActivity, this.presenterProvider.mo10268get());
        injectFallbackStrategyPresenter(shareSheetActivity, this.fallbackStrategyPresenterProvider.mo10268get());
        injectFeatureServiceUtil(shareSheetActivity, this.featureServiceUtilProvider.mo10268get());
    }
}
