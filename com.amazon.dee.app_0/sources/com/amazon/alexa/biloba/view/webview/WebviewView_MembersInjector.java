package com.amazon.alexa.biloba.view.webview;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.BilobaViewWithMetrics_MembersInjector;
import com.amazon.alexa.biloba.utils.UrlHelper;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class WebviewView_MembersInjector implements MembersInjector<WebviewView> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<UrlHelper> urlHelperProvider;

    public WebviewView_MembersInjector(Provider<BilobaMetricsService> provider, Provider<RoutingService> provider2, Provider<UrlHelper> provider3, Provider<FeatureServiceV2> provider4) {
        this.bilobaMetricsServiceProvider = provider;
        this.routingServiceProvider = provider2;
        this.urlHelperProvider = provider3;
        this.featureServiceV2Provider = provider4;
    }

    public static MembersInjector<WebviewView> create(Provider<BilobaMetricsService> provider, Provider<RoutingService> provider2, Provider<UrlHelper> provider3, Provider<FeatureServiceV2> provider4) {
        return new WebviewView_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectFeatureServiceV2(WebviewView webviewView, Lazy<FeatureServiceV2> lazy) {
        webviewView.featureServiceV2 = lazy;
    }

    public static void injectRoutingService(WebviewView webviewView, Lazy<RoutingService> lazy) {
        webviewView.routingService = lazy;
    }

    public static void injectUrlHelper(WebviewView webviewView, Lazy<UrlHelper> lazy) {
        webviewView.urlHelper = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(WebviewView webviewView) {
        BilobaViewWithMetrics_MembersInjector.injectBilobaMetricsService(webviewView, this.bilobaMetricsServiceProvider.mo10268get());
        injectRoutingService(webviewView, DoubleCheck.lazy(this.routingServiceProvider));
        injectUrlHelper(webviewView, DoubleCheck.lazy(this.urlHelperProvider));
        injectFeatureServiceV2(webviewView, DoubleCheck.lazy(this.featureServiceV2Provider));
    }
}
