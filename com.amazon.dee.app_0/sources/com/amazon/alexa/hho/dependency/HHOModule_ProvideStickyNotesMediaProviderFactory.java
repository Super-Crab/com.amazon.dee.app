package com.amazon.alexa.hho.dependency;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.hho.metrics.HHOMetricsService;
import com.amazon.alexa.hho.stickynotes.StickyNotesMediaDownloader;
import com.amazon.alexa.hho.stickynotes.StickyNotesMediaProvider;
import com.amazon.alexa.identity.api.IdentityService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class HHOModule_ProvideStickyNotesMediaProviderFactory implements Factory<StickyNotesMediaProvider> {
    private final Provider<StickyNotesMediaDownloader> downloaderProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<HHOMetricsService> metricsProvider;
    private final HHOModule module;

    public HHOModule_ProvideStickyNotesMediaProviderFactory(HHOModule hHOModule, Provider<StickyNotesMediaDownloader> provider, Provider<EventBus> provider2, Provider<IdentityService> provider3, Provider<HHOMetricsService> provider4) {
        this.module = hHOModule;
        this.downloaderProvider = provider;
        this.eventBusProvider = provider2;
        this.identityServiceProvider = provider3;
        this.metricsProvider = provider4;
    }

    public static HHOModule_ProvideStickyNotesMediaProviderFactory create(HHOModule hHOModule, Provider<StickyNotesMediaDownloader> provider, Provider<EventBus> provider2, Provider<IdentityService> provider3, Provider<HHOMetricsService> provider4) {
        return new HHOModule_ProvideStickyNotesMediaProviderFactory(hHOModule, provider, provider2, provider3, provider4);
    }

    public static StickyNotesMediaProvider provideInstance(HHOModule hHOModule, Provider<StickyNotesMediaDownloader> provider, Provider<EventBus> provider2, Provider<IdentityService> provider3, Provider<HHOMetricsService> provider4) {
        return proxyProvideStickyNotesMediaProvider(hHOModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static StickyNotesMediaProvider proxyProvideStickyNotesMediaProvider(HHOModule hHOModule, StickyNotesMediaDownloader stickyNotesMediaDownloader, EventBus eventBus, IdentityService identityService, HHOMetricsService hHOMetricsService) {
        return (StickyNotesMediaProvider) Preconditions.checkNotNull(hHOModule.provideStickyNotesMediaProvider(stickyNotesMediaDownloader, eventBus, identityService, hHOMetricsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public StickyNotesMediaProvider mo10268get() {
        return provideInstance(this.module, this.downloaderProvider, this.eventBusProvider, this.identityServiceProvider, this.metricsProvider);
    }
}
