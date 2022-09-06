package com.amazon.dee.app.ui.main;

import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.drivemode.api.DriveModeMainActivityCompanion;
import com.amazon.alexa.feature.provider.api.FeatureStore;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.permissions.DefaultPermissionsService;
import com.amazon.alexa.protocols.features.FeatureQuery;
import com.amazon.alexa.viewmanagement.impl.ViewControllerFactoryProducer;
import com.amazon.dee.app.services.security.CertificateReaderService;
import com.amazon.dee.app.services.tabLayout.TabLayoutStatusService;
import com.amazon.dee.app.services.testing.TestConfigurationService;
import com.amazon.dee.app.ui.comms.CommsViewModel;
import com.amazon.dee.app.ui.fullscreentakeover.FullScreenTakeoverViewModel;
import com.amazon.dee.app.ui.menu.AlexaMenu;
import com.amazon.dee.app.ui.nowplaying.NowPlayingViewModel;
import com.amazon.latencyinfra.LatencyInfra;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
    private final Provider<AlexaMenu> alexaMenuProvider;
    private final Provider<CertificateReaderService> certificateReaderServiceProvider;
    private final Provider<CommsViewModel> commsViewModelProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<DriveModeMainActivityCompanion.ViewModel> driveModeViewModelProvider;
    private final Provider<FeatureQuery> featureQueryProvider;
    private final Provider<FeatureServiceV2> featureServiceV2LazyProvider;
    private final Provider<FeatureStore> featureStoreProvider;
    private final Provider<FullScreenTakeoverViewModel> fullScreenTakeoverViewModelProvider;
    private final Provider<LatencyInfra> latencyInfraProvider;
    private final Provider<MainBindingThemeSetter> mainBindingThemeSetterProvider;
    private final Provider<ModeService> modeServiceProvider;
    private final Provider<NowPlayingViewModel> nowPlayingViewModelProvider;
    private final Provider<DefaultPermissionsService> permissionsServiceProvider;
    private final Provider<TabLayoutStatusService> tabLayoutStatusProvider;
    private final Provider<TabSelectionAnimator> tabSelectionAnimatorProvider;
    private final Provider<TestConfigurationService> testConfigurationsProvider;
    private final Provider<ThemeRecorder> themeRecorderProvider;
    private final Provider<ViewControllerFactoryProducer> viewControllerFactoryProducerProvider;

    public MainActivity_MembersInjector(Provider<NowPlayingViewModel> provider, Provider<CommsViewModel> provider2, Provider<FullScreenTakeoverViewModel> provider3, Provider<DriveModeMainActivityCompanion.ViewModel> provider4, Provider<AlexaMenu> provider5, Provider<TabLayoutStatusService> provider6, Provider<LatencyInfra> provider7, Provider<CrashMetadata> provider8, Provider<FeatureStore> provider9, Provider<MainBindingThemeSetter> provider10, Provider<ThemeRecorder> provider11, Provider<TabSelectionAnimator> provider12, Provider<DefaultPermissionsService> provider13, Provider<TestConfigurationService> provider14, Provider<CertificateReaderService> provider15, Provider<ViewControllerFactoryProducer> provider16, Provider<ModeService> provider17, Provider<FeatureQuery> provider18, Provider<FeatureServiceV2> provider19) {
        this.nowPlayingViewModelProvider = provider;
        this.commsViewModelProvider = provider2;
        this.fullScreenTakeoverViewModelProvider = provider3;
        this.driveModeViewModelProvider = provider4;
        this.alexaMenuProvider = provider5;
        this.tabLayoutStatusProvider = provider6;
        this.latencyInfraProvider = provider7;
        this.crashMetadataProvider = provider8;
        this.featureStoreProvider = provider9;
        this.mainBindingThemeSetterProvider = provider10;
        this.themeRecorderProvider = provider11;
        this.tabSelectionAnimatorProvider = provider12;
        this.permissionsServiceProvider = provider13;
        this.testConfigurationsProvider = provider14;
        this.certificateReaderServiceProvider = provider15;
        this.viewControllerFactoryProducerProvider = provider16;
        this.modeServiceProvider = provider17;
        this.featureQueryProvider = provider18;
        this.featureServiceV2LazyProvider = provider19;
    }

    public static MembersInjector<MainActivity> create(Provider<NowPlayingViewModel> provider, Provider<CommsViewModel> provider2, Provider<FullScreenTakeoverViewModel> provider3, Provider<DriveModeMainActivityCompanion.ViewModel> provider4, Provider<AlexaMenu> provider5, Provider<TabLayoutStatusService> provider6, Provider<LatencyInfra> provider7, Provider<CrashMetadata> provider8, Provider<FeatureStore> provider9, Provider<MainBindingThemeSetter> provider10, Provider<ThemeRecorder> provider11, Provider<TabSelectionAnimator> provider12, Provider<DefaultPermissionsService> provider13, Provider<TestConfigurationService> provider14, Provider<CertificateReaderService> provider15, Provider<ViewControllerFactoryProducer> provider16, Provider<ModeService> provider17, Provider<FeatureQuery> provider18, Provider<FeatureServiceV2> provider19) {
        return new MainActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19);
    }

    public static void injectAlexaMenu(MainActivity mainActivity, AlexaMenu alexaMenu) {
        mainActivity.alexaMenu = alexaMenu;
    }

    public static void injectCertificateReaderService(MainActivity mainActivity, Lazy<CertificateReaderService> lazy) {
        mainActivity.certificateReaderService = lazy;
    }

    public static void injectCommsViewModel(MainActivity mainActivity, CommsViewModel commsViewModel) {
        mainActivity.commsViewModel = commsViewModel;
    }

    public static void injectCrashMetadata(MainActivity mainActivity, CrashMetadata crashMetadata) {
        mainActivity.crashMetadata = crashMetadata;
    }

    public static void injectDriveModeViewModel(MainActivity mainActivity, DriveModeMainActivityCompanion.ViewModel viewModel) {
        mainActivity.driveModeViewModel = viewModel;
    }

    public static void injectFeatureQuery(MainActivity mainActivity, FeatureQuery featureQuery) {
        mainActivity.featureQuery = featureQuery;
    }

    public static void injectFeatureServiceV2Lazy(MainActivity mainActivity, Lazy<FeatureServiceV2> lazy) {
        mainActivity.featureServiceV2Lazy = lazy;
    }

    public static void injectFeatureStore(MainActivity mainActivity, Lazy<FeatureStore> lazy) {
        mainActivity.featureStore = lazy;
    }

    public static void injectFullScreenTakeoverViewModel(MainActivity mainActivity, FullScreenTakeoverViewModel fullScreenTakeoverViewModel) {
        mainActivity.fullScreenTakeoverViewModel = fullScreenTakeoverViewModel;
    }

    public static void injectLatencyInfra(MainActivity mainActivity, LatencyInfra latencyInfra) {
        mainActivity.latencyInfra = latencyInfra;
    }

    public static void injectMainBindingThemeSetter(MainActivity mainActivity, Lazy<MainBindingThemeSetter> lazy) {
        mainActivity.mainBindingThemeSetter = lazy;
    }

    public static void injectModeService(MainActivity mainActivity, Lazy<ModeService> lazy) {
        mainActivity.modeService = lazy;
    }

    public static void injectNowPlayingViewModel(MainActivity mainActivity, NowPlayingViewModel nowPlayingViewModel) {
        mainActivity.nowPlayingViewModel = nowPlayingViewModel;
    }

    public static void injectPermissionsService(MainActivity mainActivity, Lazy<DefaultPermissionsService> lazy) {
        mainActivity.permissionsService = lazy;
    }

    public static void injectTabLayoutStatus(MainActivity mainActivity, TabLayoutStatusService tabLayoutStatusService) {
        mainActivity.tabLayoutStatus = tabLayoutStatusService;
    }

    public static void injectTabSelectionAnimator(MainActivity mainActivity, Lazy<TabSelectionAnimator> lazy) {
        mainActivity.tabSelectionAnimator = lazy;
    }

    public static void injectTestConfigurations(MainActivity mainActivity, Lazy<TestConfigurationService> lazy) {
        mainActivity.testConfigurations = lazy;
    }

    public static void injectThemeRecorder(MainActivity mainActivity, Lazy<ThemeRecorder> lazy) {
        mainActivity.themeRecorder = lazy;
    }

    public static void injectViewControllerFactoryProducer(MainActivity mainActivity, Lazy<ViewControllerFactoryProducer> lazy) {
        mainActivity.viewControllerFactoryProducer = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MainActivity mainActivity) {
        injectNowPlayingViewModel(mainActivity, this.nowPlayingViewModelProvider.mo10268get());
        injectCommsViewModel(mainActivity, this.commsViewModelProvider.mo10268get());
        injectFullScreenTakeoverViewModel(mainActivity, this.fullScreenTakeoverViewModelProvider.mo10268get());
        injectDriveModeViewModel(mainActivity, this.driveModeViewModelProvider.mo10268get());
        injectAlexaMenu(mainActivity, this.alexaMenuProvider.mo10268get());
        injectTabLayoutStatus(mainActivity, this.tabLayoutStatusProvider.mo10268get());
        injectLatencyInfra(mainActivity, this.latencyInfraProvider.mo10268get());
        injectCrashMetadata(mainActivity, this.crashMetadataProvider.mo10268get());
        injectFeatureStore(mainActivity, DoubleCheck.lazy(this.featureStoreProvider));
        injectMainBindingThemeSetter(mainActivity, DoubleCheck.lazy(this.mainBindingThemeSetterProvider));
        injectThemeRecorder(mainActivity, DoubleCheck.lazy(this.themeRecorderProvider));
        injectTabSelectionAnimator(mainActivity, DoubleCheck.lazy(this.tabSelectionAnimatorProvider));
        injectPermissionsService(mainActivity, DoubleCheck.lazy(this.permissionsServiceProvider));
        injectTestConfigurations(mainActivity, DoubleCheck.lazy(this.testConfigurationsProvider));
        injectCertificateReaderService(mainActivity, DoubleCheck.lazy(this.certificateReaderServiceProvider));
        injectViewControllerFactoryProducer(mainActivity, DoubleCheck.lazy(this.viewControllerFactoryProducerProvider));
        injectModeService(mainActivity, DoubleCheck.lazy(this.modeServiceProvider));
        injectFeatureQuery(mainActivity, this.featureQueryProvider.mo10268get());
        injectFeatureServiceV2Lazy(mainActivity, DoubleCheck.lazy(this.featureServiceV2LazyProvider));
    }
}
