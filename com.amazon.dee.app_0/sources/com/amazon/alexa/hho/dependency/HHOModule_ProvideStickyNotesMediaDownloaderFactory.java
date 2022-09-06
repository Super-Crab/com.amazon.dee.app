package com.amazon.alexa.hho.dependency;

import com.amazon.alexa.hho.metrics.HHOMetricsService;
import com.amazon.alexa.hho.stickynotes.StickyNotesMediaDownloader;
import com.dee.app.http.CoralService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class HHOModule_ProvideStickyNotesMediaDownloaderFactory implements Factory<StickyNotesMediaDownloader> {
    private final Provider<CoralService> coralServiceProvider;
    private final Provider<HHOMetricsService> metricsProvider;
    private final HHOModule module;

    public HHOModule_ProvideStickyNotesMediaDownloaderFactory(HHOModule hHOModule, Provider<CoralService> provider, Provider<HHOMetricsService> provider2) {
        this.module = hHOModule;
        this.coralServiceProvider = provider;
        this.metricsProvider = provider2;
    }

    public static HHOModule_ProvideStickyNotesMediaDownloaderFactory create(HHOModule hHOModule, Provider<CoralService> provider, Provider<HHOMetricsService> provider2) {
        return new HHOModule_ProvideStickyNotesMediaDownloaderFactory(hHOModule, provider, provider2);
    }

    public static StickyNotesMediaDownloader provideInstance(HHOModule hHOModule, Provider<CoralService> provider, Provider<HHOMetricsService> provider2) {
        return proxyProvideStickyNotesMediaDownloader(hHOModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static StickyNotesMediaDownloader proxyProvideStickyNotesMediaDownloader(HHOModule hHOModule, CoralService coralService, HHOMetricsService hHOMetricsService) {
        return (StickyNotesMediaDownloader) Preconditions.checkNotNull(hHOModule.provideStickyNotesMediaDownloader(coralService, hHOMetricsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public StickyNotesMediaDownloader mo10268get() {
        return provideInstance(this.module, this.coralServiceProvider, this.metricsProvider);
    }
}
