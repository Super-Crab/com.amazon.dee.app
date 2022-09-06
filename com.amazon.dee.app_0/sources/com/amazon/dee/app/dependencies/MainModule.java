package com.amazon.dee.app.dependencies;

import android.app.Activity;
import android.content.Context;
import com.amazon.alexa.crashreporting.CrashReportingService;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.featureservice.configuration.FeatureServiceConfiguration;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.permissions.DefaultPermissionsService;
import com.amazon.alexa.permissions.api.PermissionsService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.usermessage.UserMessageService;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.ui.player.PlayerCardUpdater;
import com.amazon.dee.app.services.appreviewrequest.AppReviewRequestService;
import com.amazon.dee.app.services.core.MainActivityLifecycleService;
import com.amazon.dee.app.services.security.CertificateReaderService;
import com.amazon.dee.app.services.tabLayout.TabLayoutStatusService;
import com.amazon.dee.app.services.testing.TestArgumentsService;
import com.amazon.dee.app.services.testing.TestConfigurationService;
import com.amazon.dee.app.services.usermessage.DefaultUserMessageService;
import com.amazon.dee.app.services.wifi.WifiService;
import com.amazon.dee.app.ui.comms.CommsViewModel;
import com.amazon.dee.app.ui.fullscreentakeover.FullScreenTakeoverViewModel;
import com.amazon.dee.app.ui.main.AuthenticationExceptionHandler;
import com.amazon.dee.app.ui.main.MainActivity;
import com.amazon.dee.app.ui.main.MainBindingThemeSetter;
import com.amazon.dee.app.ui.main.MainHandler;
import com.amazon.dee.app.ui.main.MainRoutingAdapter;
import com.amazon.dee.app.ui.main.TabSelectionAnimator;
import com.amazon.dee.app.ui.main.ThemeRecorder;
import com.amazon.dee.app.ui.menu.AlexaMenu;
import com.amazon.dee.app.ui.nowplaying.NowPlayingViewModel;
import com.amazon.dee.app.ui.util.LocationPermissionMetricHelper;
import com.amazon.dee.app.ui.util.SonarUrlHandler;
import com.amazon.dee.app.ui.web.WebRoutingAdapter;
import com.dee.app.metrics.MetricsService;
import com.facebook.react.ReactInstanceManager;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
@Module
/* loaded from: classes12.dex */
public class MainModule {
    MainActivity activity;

    public MainModule(MainActivity mainActivity) {
        this.activity = mainActivity;
    }

    @Provides
    @MainScope
    public Activity provideActivity() {
        return this.activity;
    }

    @Provides
    @MainScope
    public AppReviewRequestService provideAppReviewRequestService(EventBus eventBus, RoutingService routingService, IdentityService identityService, WifiService wifiService, EnvironmentService environmentService, Lazy<ModeService> lazy, Context context, ReviewManager reviewManager, Lazy<FeatureServiceV2> lazy2, Lazy<Mobilytics> lazy3) {
        return new AppReviewRequestService(eventBus, routingService, identityService, wifiService, environmentService, lazy, context, reviewManager, lazy2, lazy3);
    }

    @Provides
    @MainScope
    public AuthenticationExceptionHandler provideAuthenticationExceptionHandler(Mobilytics mobilytics, UserMessageService userMessageService, RoutingService routingService) {
        return new AuthenticationExceptionHandler(this.activity, mobilytics, userMessageService, routingService);
    }

    @Provides
    @MainScope
    public CommsViewModel provideCommsViewModel(Context context, MainActivityLifecycleService mainActivityLifecycleService) {
        return new CommsViewModel(context, mainActivityLifecycleService);
    }

    @Provides
    @MainScope
    public DefaultPermissionsService provideDefaultPermissionsService(Activity activity) {
        return new DefaultPermissionsService(activity);
    }

    @Provides
    @MainScope
    public FullScreenTakeoverViewModel provideFullScreenTakeoverViewModel(ReactInstanceManager reactInstanceManager) {
        return new FullScreenTakeoverViewModel(reactInstanceManager);
    }

    @Provides
    @MainScope
    public LocationPermissionMetricHelper provideLocationPermissionMetricHelper(Activity activity, MetricsService metricsService) {
        return new LocationPermissionMetricHelper(activity, metricsService);
    }

    @Provides
    @MainScope
    public MainActivity provideMainActivity() {
        return this.activity;
    }

    @Provides
    @MainScope
    public MainBindingThemeSetter provideMainBindingThemeSetter(Activity activity) {
        return new MainBindingThemeSetter(activity.getWindow());
    }

    @Provides
    @MainScope
    public MainHandler provideMainHandler() {
        return this.activity;
    }

    @Provides
    public MainRoutingAdapter provideMainRoutingAdapter() {
        return new MainRoutingAdapter(this.activity);
    }

    @Provides
    @MainScope
    public AlexaMenu provideMenu(Activity activity) {
        return new AlexaMenu(activity);
    }

    @Provides
    @MainScope
    public UserMessageService provideMessageUserService() {
        return new DefaultUserMessageService(this.activity);
    }

    @Provides
    @MainScope
    public NowPlayingViewModel provideNowPlayingViewModel(Activity activity, MainActivityLifecycleService mainActivityLifecycleService, PlayerCardUpdater playerCardUpdater, MetricsService metricsService, VoiceService voiceService, IdentityService identityService) {
        return new NowPlayingViewModel(activity, mainActivityLifecycleService, playerCardUpdater, metricsService, voiceService, identityService);
    }

    @Provides
    @MainScope
    public PermissionsService providePermissionsService(DefaultPermissionsService defaultPermissionsService) {
        return defaultPermissionsService;
    }

    @Provides
    @MainScope
    public ReviewManager provideReviewManager(Activity activity) {
        return ReviewManagerFactory.create(activity);
    }

    @Provides
    @MainScope
    public SonarUrlHandler provideSonarUrlHandler(Lazy<OkHttpClient> lazy) {
        return new SonarUrlHandler(lazy);
    }

    @Provides
    @MainScope
    public TabLayoutStatusService provideTabLayoutStatusService() {
        return new TabLayoutStatusService();
    }

    @Provides
    @MainScope
    public TabSelectionAnimator provideTabSelectionAnimator(Context context, Lazy<EventBus> lazy, Lazy<IdentityService> lazy2) {
        return new TabSelectionAnimator(context, lazy, lazy2);
    }

    @Provides
    @MainScope
    public TestArgumentsService provideTestArgumentsService(Activity activity, Lazy<CertificateReaderService> lazy) {
        return new TestArgumentsService(activity.getIntent(), lazy);
    }

    @Provides
    @MainScope
    public TestConfigurationService provideTestConfigurationService(TestArgumentsService testArgumentsService, Lazy<CrashReportingService> lazy, Lazy<CertificateReaderService> lazy2, Lazy<AppReviewRequestService> lazy3, Lazy<FeatureServiceConfiguration> lazy4) {
        return new TestConfigurationService(testArgumentsService, lazy, lazy2, lazy3, lazy4);
    }

    @Provides
    @MainScope
    public ThemeRecorder provideThemeRecorder(Context context, Lazy<Mobilytics> lazy, Lazy<IdentityService> lazy2) {
        return new ThemeRecorder(context, lazy, lazy2);
    }

    @Provides
    public WebRoutingAdapter provideWebRoutingAdapter() {
        return new WebRoutingAdapter(this.activity);
    }
}
