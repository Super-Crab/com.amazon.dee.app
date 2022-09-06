package com.amazon.alexa.biloba.view.infoModal;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.BilobaViewWithMetrics_MembersInjector;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.utils.CommsHelper;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class InfoModalView_MembersInjector implements MembersInjector<InfoModalView> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<CommsHelper> commsHelperProvider;

    public InfoModalView_MembersInjector(Provider<BilobaMetricsService> provider, Provider<CommsHelper> provider2, Provider<CareActorsStore> provider3) {
        this.bilobaMetricsServiceProvider = provider;
        this.commsHelperProvider = provider2;
        this.careActorsStoreProvider = provider3;
    }

    public static MembersInjector<InfoModalView> create(Provider<BilobaMetricsService> provider, Provider<CommsHelper> provider2, Provider<CareActorsStore> provider3) {
        return new InfoModalView_MembersInjector(provider, provider2, provider3);
    }

    public static void injectCareActorsStore(InfoModalView infoModalView, Lazy<CareActorsStore> lazy) {
        infoModalView.careActorsStore = lazy;
    }

    public static void injectCommsHelper(InfoModalView infoModalView, Lazy<CommsHelper> lazy) {
        infoModalView.commsHelper = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(InfoModalView infoModalView) {
        BilobaViewWithMetrics_MembersInjector.injectBilobaMetricsService(infoModalView, this.bilobaMetricsServiceProvider.mo10268get());
        injectCommsHelper(infoModalView, DoubleCheck.lazy(this.commsHelperProvider));
        injectCareActorsStore(infoModalView, DoubleCheck.lazy(this.careActorsStoreProvider));
    }
}
