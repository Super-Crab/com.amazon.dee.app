package com.amazon.alexa.voice.ui;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.voice.locale.DefaultLocaleAuthority;
import com.amazon.alexa.voice.locale.LocaleInteractor;
import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import com.amazon.alexa.voice.ui.locale.AlexaLocaleAuthority;
import com.amazon.alexa.voice.ui.marketplace.MarketplaceAuthority;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public final class UiModule {
    private UiModule() {
    }

    @Provides
    @Singleton
    public static AlexaLocaleAuthority provideAlexaLocaleAuthority(LocaleInteractor localeInteractor) {
        return new DefaultLocaleAuthority(localeInteractor);
    }

    @Provides
    @Singleton
    public static CardMetricsInteractor provideCardMetricsInteractor(MetricsBridge metricsBridge) {
        return new CardMetricsInteractorImpl(metricsBridge);
    }

    @Provides
    @Singleton
    public static MarketplaceAuthority provideMarketplaceAuthority(IdentityService identityService) {
        return new DefaultMarketplaceAuthority(identityService);
    }

    @Provides
    @Singleton
    public static MetricsBridge provideMetricsBridge(MetricsService metricsService, VoxMetricEventProcessingService voxMetricEventProcessingService) {
        return new DefaultMetricsBridge(metricsService, voxMetricEventProcessingService);
    }

    @Provides
    @Singleton
    public static VoiceEventBusRebroadcastSender provideVoiceEventBusRebroadcastSender(Context context, EventBus eventBus) {
        return new DefaultVoiceEventBusRebroadcastSender(context, eventBus);
    }
}
