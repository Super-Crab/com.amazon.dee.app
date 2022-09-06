package com.amazon.dee.app.ui.web;

import com.amazon.dee.app.services.security.CertificateReaderService;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AlexaWebView_MembersInjector implements MembersInjector<AlexaWebView> {
    private final Provider<CertificateReaderService> certificateReaderServiceProvider;
    private final Provider<MetricsService> metricsServiceProvider;

    public AlexaWebView_MembersInjector(Provider<MetricsService> provider, Provider<CertificateReaderService> provider2) {
        this.metricsServiceProvider = provider;
        this.certificateReaderServiceProvider = provider2;
    }

    public static MembersInjector<AlexaWebView> create(Provider<MetricsService> provider, Provider<CertificateReaderService> provider2) {
        return new AlexaWebView_MembersInjector(provider, provider2);
    }

    public static void injectCertificateReaderService(AlexaWebView alexaWebView, Lazy<CertificateReaderService> lazy) {
        alexaWebView.certificateReaderService = lazy;
    }

    public static void injectMetricsService(AlexaWebView alexaWebView, MetricsService metricsService) {
        alexaWebView.metricsService = metricsService;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AlexaWebView alexaWebView) {
        injectMetricsService(alexaWebView, this.metricsServiceProvider.mo10268get());
        injectCertificateReaderService(alexaWebView, DoubleCheck.lazy(this.certificateReaderServiceProvider));
    }
}
