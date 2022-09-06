package com.amazon.alexa.hho.dependency;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.hho.api.HHOServiceImpl;
import com.amazon.alexa.hho.api.HHOServiceImpl_MembersInjector;
import com.amazon.alexa.hho.metrics.HHOMetricsService;
import com.amazon.alexa.hho.stickynotes.StickyNotesMediaProvider;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.dee.app.http.CoralService;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class DaggerHHOComponent implements HHOComponent {
    private HHOModule hHOModule;
    private Provider<CoralService> provideCoralServiceProvider;
    private Provider<EventBus> provideEventBusProvider;
    private Provider<IdentityService> provideIdentityServiceProvider;
    private Provider<HHOMetricsService> provideMetricsProvider;
    private Provider<LazyComponent<Mobilytics>> provideMobilyticsProvider;
    private HHOModule_ProvideStickyNotesMediaDownloaderFactory provideStickyNotesMediaDownloaderProvider;
    private Provider<StickyNotesMediaProvider> provideStickyNotesMediaProvider;

    /* loaded from: classes8.dex */
    public static final class Builder {
        private ApplicationModule applicationModule;
        private HHOModule hHOModule;

        public Builder applicationModule(ApplicationModule applicationModule) {
            this.applicationModule = (ApplicationModule) Preconditions.checkNotNull(applicationModule);
            return this;
        }

        public HHOComponent build() {
            Preconditions.checkBuilderRequirement(this.hHOModule, HHOModule.class);
            Preconditions.checkBuilderRequirement(this.applicationModule, ApplicationModule.class);
            return new DaggerHHOComponent(this);
        }

        public Builder hHOModule(HHOModule hHOModule) {
            this.hHOModule = (HHOModule) Preconditions.checkNotNull(hHOModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.provideCoralServiceProvider = DoubleCheck.provider(ApplicationModule_ProvideCoralServiceFactory.create(builder.applicationModule));
        this.provideMobilyticsProvider = DoubleCheck.provider(ApplicationModule_ProvideMobilyticsFactory.create(builder.applicationModule));
        this.provideMetricsProvider = DoubleCheck.provider(HHOModule_ProvideMetricsFactory.create(builder.hHOModule, this.provideMobilyticsProvider));
        this.provideStickyNotesMediaDownloaderProvider = HHOModule_ProvideStickyNotesMediaDownloaderFactory.create(builder.hHOModule, this.provideCoralServiceProvider, this.provideMetricsProvider);
        this.provideEventBusProvider = DoubleCheck.provider(ApplicationModule_ProvideEventBusFactory.create(builder.applicationModule));
        this.provideIdentityServiceProvider = DoubleCheck.provider(ApplicationModule_ProvideIdentityServiceFactory.create(builder.applicationModule));
        this.provideStickyNotesMediaProvider = DoubleCheck.provider(HHOModule_ProvideStickyNotesMediaProviderFactory.create(builder.hHOModule, this.provideStickyNotesMediaDownloaderProvider, this.provideEventBusProvider, this.provideIdentityServiceProvider, this.provideMetricsProvider));
    }

    private HHOServiceImpl injectHHOServiceImpl(HHOServiceImpl hHOServiceImpl) {
        HHOServiceImpl_MembersInjector.injectStickyNotesMediaProvider(hHOServiceImpl, this.provideStickyNotesMediaProvider.mo10268get());
        HHOServiceImpl_MembersInjector.injectKalamFontDownloader(hHOServiceImpl, HHOModule_ProvideKalamFontDownloaderFactory.proxyProvideKalamFontDownloader(this.hHOModule));
        return hHOServiceImpl;
    }

    @Override // com.amazon.alexa.hho.dependency.HHOComponent
    public void inject(HHOServiceImpl hHOServiceImpl) {
        injectHHOServiceImpl(hHOServiceImpl);
    }

    private DaggerHHOComponent(Builder builder) {
        this.hHOModule = builder.hHOModule;
        initialize(builder);
    }
}
