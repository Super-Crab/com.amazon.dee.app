package com.amazon.alexa.biloba.view.gettingStartedV3;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.BilobaViewWithMetrics_MembersInjector;
import com.amazon.alexa.imageloader.api.ImageLoader;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class GettingStartedViewV3_MembersInjector implements MembersInjector<GettingStartedViewV3> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<ImageLoader> imageLoaderProvider;

    public GettingStartedViewV3_MembersInjector(Provider<BilobaMetricsService> provider, Provider<ImageLoader> provider2) {
        this.bilobaMetricsServiceProvider = provider;
        this.imageLoaderProvider = provider2;
    }

    public static MembersInjector<GettingStartedViewV3> create(Provider<BilobaMetricsService> provider, Provider<ImageLoader> provider2) {
        return new GettingStartedViewV3_MembersInjector(provider, provider2);
    }

    public static void injectImageLoader(GettingStartedViewV3 gettingStartedViewV3, Lazy<ImageLoader> lazy) {
        gettingStartedViewV3.imageLoader = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(GettingStartedViewV3 gettingStartedViewV3) {
        BilobaViewWithMetrics_MembersInjector.injectBilobaMetricsService(gettingStartedViewV3, this.bilobaMetricsServiceProvider.mo10268get());
        injectImageLoader(gettingStartedViewV3, DoubleCheck.lazy(this.imageLoaderProvider));
    }
}
