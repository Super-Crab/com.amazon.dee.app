package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.ui.player.PlayerCardUpdater;
import com.amazon.dee.app.services.core.MainActivityLifecycleService;
import com.amazon.dee.app.ui.nowplaying.NowPlayingViewModel;
import com.dee.app.metrics.MetricsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MainModule_ProvideNowPlayingViewModelFactory implements Factory<NowPlayingViewModel> {
    private final Provider<Activity> activityProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<MainActivityLifecycleService> mainActivityLifecycleServiceProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final MainModule module;
    private final Provider<PlayerCardUpdater> nowPlayingCardUpdaterProvider;
    private final Provider<VoiceService> voiceServiceProvider;

    public MainModule_ProvideNowPlayingViewModelFactory(MainModule mainModule, Provider<Activity> provider, Provider<MainActivityLifecycleService> provider2, Provider<PlayerCardUpdater> provider3, Provider<MetricsService> provider4, Provider<VoiceService> provider5, Provider<IdentityService> provider6) {
        this.module = mainModule;
        this.activityProvider = provider;
        this.mainActivityLifecycleServiceProvider = provider2;
        this.nowPlayingCardUpdaterProvider = provider3;
        this.metricsServiceProvider = provider4;
        this.voiceServiceProvider = provider5;
        this.identityServiceProvider = provider6;
    }

    public static MainModule_ProvideNowPlayingViewModelFactory create(MainModule mainModule, Provider<Activity> provider, Provider<MainActivityLifecycleService> provider2, Provider<PlayerCardUpdater> provider3, Provider<MetricsService> provider4, Provider<VoiceService> provider5, Provider<IdentityService> provider6) {
        return new MainModule_ProvideNowPlayingViewModelFactory(mainModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static NowPlayingViewModel provideInstance(MainModule mainModule, Provider<Activity> provider, Provider<MainActivityLifecycleService> provider2, Provider<PlayerCardUpdater> provider3, Provider<MetricsService> provider4, Provider<VoiceService> provider5, Provider<IdentityService> provider6) {
        return proxyProvideNowPlayingViewModel(mainModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get());
    }

    public static NowPlayingViewModel proxyProvideNowPlayingViewModel(MainModule mainModule, Activity activity, MainActivityLifecycleService mainActivityLifecycleService, PlayerCardUpdater playerCardUpdater, MetricsService metricsService, VoiceService voiceService, IdentityService identityService) {
        return (NowPlayingViewModel) Preconditions.checkNotNull(mainModule.provideNowPlayingViewModel(activity, mainActivityLifecycleService, playerCardUpdater, metricsService, voiceService, identityService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NowPlayingViewModel mo10268get() {
        return provideInstance(this.module, this.activityProvider, this.mainActivityLifecycleServiceProvider, this.nowPlayingCardUpdaterProvider, this.metricsServiceProvider, this.voiceServiceProvider, this.identityServiceProvider);
    }
}
