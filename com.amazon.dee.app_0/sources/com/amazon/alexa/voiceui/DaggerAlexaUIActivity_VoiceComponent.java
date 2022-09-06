package com.amazon.alexa.voiceui;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.PowerManager;
import android.view.ViewGroup;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.voice.ui.driveMode.metrics.DriveModeCardMetricsInteractor;
import com.amazon.alexa.voice.ui.locale.GetLocaleAuthority;
import com.amazon.alexa.voice.ui.marketplace.MarketplaceAuthority;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.permissions.AndroidPermissionsChecker;
import com.amazon.alexa.voice.ui.permissions.AndroidPermissionsRequester;
import com.amazon.alexa.voice.ui.provider.CardCreationEventListenerProvider;
import com.amazon.alexa.voice.ui.provider.CardFactoryWithMode;
import com.amazon.alexa.voice.ui.routing.Navigator;
import com.amazon.alexa.voice.ui.speech.AttentionSystemContract;
import com.amazon.alexa.voice.ui.speech.SpeechRecognizer;
import com.amazon.alexa.voice.ui.tta.TypingPrimerDisplayer;
import com.amazon.alexa.voice.ui.window.WindowInteractor;
import com.amazon.alexa.voiceui.AlexaUIActivity;
import com.amazon.alexa.voiceui.accessibility.AccessibilityDelegate;
import com.amazon.alexa.voiceui.accessibility.AccessibilityHandler;
import com.amazon.alexa.voiceui.accessibility.AccessibilityHandler_Factory;
import com.amazon.alexa.voiceui.cards.CardInteractor;
import com.amazon.alexa.voiceui.cards.CardInteractor_Factory;
import com.amazon.alexa.voiceui.cards.CardModel_Factory;
import com.amazon.alexa.voiceui.cards.CardModule;
import com.amazon.alexa.voiceui.cards.CardModule_ProvidesAccessibilityDelegateFactory;
import com.amazon.alexa.voiceui.cards.CardModule_ProvidesBackButtonPressHandlerFactory;
import com.amazon.alexa.voiceui.cards.CardModule_ProvidesCardCreationEventListenerProviderFactory;
import com.amazon.alexa.voiceui.cards.CardModule_ProvidesCardFactoryWithModeFactory;
import com.amazon.alexa.voiceui.cards.CardModule_ProvidesCardMetricsInteractorFactory;
import com.amazon.alexa.voiceui.cards.CardModule_ProvidesCardsContainerFactory;
import com.amazon.alexa.voiceui.cards.CardModule_ProvidesCardsRouterDelegateFactory;
import com.amazon.alexa.voiceui.cards.CardModule_ProvidesCardsRouterFactory;
import com.amazon.alexa.voiceui.cards.CardModule_ProvidesDriveModeCardMetricsInteractorFactory;
import com.amazon.alexa.voiceui.cards.CardModule_ProvidesGetLocaleAuthorityFactory;
import com.amazon.alexa.voiceui.cards.CardModule_ProvidesMarketplaceAuthorityFactory;
import com.amazon.alexa.voiceui.cards.CardModule_ProvidesSaveInstanceStateHandlerFactory;
import com.amazon.alexa.voiceui.cards.CardPresenter;
import com.amazon.alexa.voiceui.cards.CardPresenter_Factory;
import com.amazon.alexa.voiceui.cards.CardView;
import com.amazon.alexa.voiceui.cards.CardView_Factory;
import com.amazon.alexa.voiceui.cards.DefaultLocaleAuthority;
import com.amazon.alexa.voiceui.cards.DefaultLocaleAuthority_Factory;
import com.amazon.alexa.voiceui.cards.DefaultMarketplaceAuthority_Factory;
import com.amazon.alexa.voiceui.cards.DriveModeCardCreationEventListener_Factory;
import com.amazon.alexa.voiceui.cards.DriveModeCardMetricsInteractorImpl_Factory;
import com.amazon.alexa.voiceui.cards.StandardCardCreationEventListener_Factory;
import com.amazon.alexa.voiceui.cards.StandardCardMetricsInteractor_Factory;
import com.amazon.alexa.voiceui.cards.VoiceNavigator_Factory;
import com.amazon.alexa.voiceui.chrome.VoiceChromeInteractor;
import com.amazon.alexa.voiceui.chrome.VoiceChromeInteractor_Factory;
import com.amazon.alexa.voiceui.chrome.VoiceChromeModel_Factory;
import com.amazon.alexa.voiceui.chrome.VoiceChromeModule;
import com.amazon.alexa.voiceui.chrome.VoiceChromeModule_ProvidesAttentionSystemContractFactory;
import com.amazon.alexa.voiceui.chrome.VoiceChromeModule_ProvidesBackButtonPressHandlerFactory;
import com.amazon.alexa.voiceui.chrome.VoiceChromeModule_ProvidesSaveInstanceStateHandlerFactory;
import com.amazon.alexa.voiceui.chrome.VoiceChromeModule_ProvidesTypingPrimerDisplayerFactory;
import com.amazon.alexa.voiceui.chrome.VoiceChromeModule_ProvidesVoiceChromeContainerFactory;
import com.amazon.alexa.voiceui.chrome.VoiceChromeModule_ProvidesVoiceChromeRouterDelegateFactory;
import com.amazon.alexa.voiceui.chrome.VoiceChromeModule_ProvidesVoiceChromeRouterFactory;
import com.amazon.alexa.voiceui.chrome.VoiceChromePresenter;
import com.amazon.alexa.voiceui.chrome.VoiceChromePresenter_Factory;
import com.amazon.alexa.voiceui.chrome.VoiceChromeView;
import com.amazon.alexa.voiceui.chrome.VoiceChromeView_Factory;
import com.amazon.alexa.voiceui.driveMode.DriveModeModel;
import com.amazon.alexa.voiceui.driveMode.DriveModeModel_Factory;
import com.amazon.alexa.voiceui.driveMode.DriveModeStateProvider;
import com.amazon.alexa.voiceui.events.UiEventReporter;
import com.amazon.alexa.voiceui.events.UiEventReporter_Factory;
import com.amazon.alexa.voiceui.lockscreen.LockscreenController;
import com.amazon.alexa.voiceui.lockscreen.LockscreenController_Factory;
import com.amazon.alexa.voiceui.notifications.SmartLockNotificationInteractor_Factory;
import com.amazon.alexa.voiceui.notifications.SmartLockNotificationPresenter;
import com.amazon.alexa.voiceui.notifications.SmartLockNotificationPresenter_Factory;
import com.amazon.alexa.voiceui.notifications.SmartLockNotificationView_Factory;
import com.amazon.alexa.voiceui.screen.ScreenLockManager;
import com.amazon.alexa.voiceui.voice.AlexaUserInterfaceOptionsTracker;
import com.amazon.alexa.voiceui.voice.AlexaUserInterfaceOptionsTracker_Factory;
import com.amazon.alexa.voiceui.voice.VoiceInteractor;
import com.amazon.alexa.voiceui.voice.VoiceInteractor_Factory;
import com.amazon.alexa.voiceui.voice.VoicePresenter;
import com.amazon.alexa.voiceui.voice.VoicePresenter_Factory;
import com.amazon.alexa.voiceui.voice.VoiceView;
import com.amazon.alexa.voiceui.voice.VoiceView_Factory;
import com.amazon.alexa.voiceui.window.WindowManager;
import com.amazon.regulator.Component;
import com.amazon.regulator.Router;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class DaggerAlexaUIActivity_VoiceComponent implements AlexaUIActivity.VoiceComponent {
    private Provider<AccessibilityHandler> accessibilityHandlerProvider;
    private Provider<AlexaUserInterfaceOptionsTracker> alexaUserInterfaceOptionsTrackerProvider;
    private Provider<CardInteractor> cardInteractorProvider;
    private Provider cardModelProvider;
    private Provider<CardPresenter> cardPresenterProvider;
    private Provider<CardView> cardViewProvider;
    private Provider<DefaultAndroidPermissionsHandler> defaultAndroidPermissionsHandlerProvider;
    private Provider<DefaultLocaleAuthority> defaultLocaleAuthorityProvider;
    private Provider defaultMarketplaceAuthorityProvider;
    private Provider<DefaultSpeechRecognizer> defaultSpeechRecognizerProvider;
    private Provider driveModeCardCreationEventListenerProvider;
    private DriveModeCardMetricsInteractorImpl_Factory driveModeCardMetricsInteractorImplProvider;
    private Provider<DriveModeModel> driveModeModelProvider;
    private Provider<LockscreenController> lockscreenControllerProvider;
    private Provider<AccessibilityDelegate> providesAccessibilityDelegateProvider;
    private Provider<Activity> providesActivityProvider;
    private Provider<AlexaServicesApis> providesAlexaServicesApisProvider;
    private Provider<AlexaServicesConnection> providesAlexaServicesConnectionProvider;
    private Provider<AttentionSystemContract> providesAttentionSystemContractProvider;
    private Provider<BackButtonPressHandler> providesBackButtonPressHandlerProvider;
    private Provider<BackButtonPressHandler> providesBackButtonPressHandlerProvider2;
    private Provider<CardCreationEventListenerProvider> providesCardCreationEventListenerProvider;
    private Provider<CardFactoryWithMode> providesCardFactoryWithModeProvider;
    private Provider<CardMetricsInteractor> providesCardMetricsInteractorProvider;
    private Provider<ViewGroup> providesCardsContainerProvider;
    private Provider<RouterDelegate> providesCardsRouterDelegateProvider;
    private Provider<Router> providesCardsRouterProvider;
    private Provider<Component> providesComponentProvider;
    private Provider<Context> providesContextProvider;
    private Provider<DriveModeCardMetricsInteractor> providesDriveModeCardMetricsInteractorProvider;
    private Provider<DriveModeStateProvider> providesDriveModeStateProvider;
    private Provider<GetLocaleAuthority> providesGetLocaleAuthorityProvider;
    private Provider<KeyguardManager> providesKeyguardManagerProvider;
    private Provider<MarketplaceAuthority> providesMarketplaceAuthorityProvider;
    private Provider<Navigator> providesNavigatorProvider;
    private Provider<PackageManager> providesPackageManagerProvider;
    private Provider<AndroidPermissionsChecker> providesPermissionsCheckerProvider;
    private Provider<AndroidPermissionsRequester> providesPermissionsRequesterProvider;
    private Provider<PowerManager> providesPowerManagerProvider;
    private Provider<SaveInstanceStateHandler> providesSaveInstanceStateHandlerProvider;
    private Provider<SaveInstanceStateHandler> providesSaveInstanceStateHandlerProvider2;
    private Provider<ScreenLockManager> providesScreenLockManagerProvider;
    private Provider<SpeechRecognizer> providesSpeechRecognizerProvider;
    private Provider<TypingPrimerDisplayer> providesTypingPrimerDisplayerProvider;
    private Provider<ViewGroup> providesVoiceChromeContainerProvider;
    private Provider<RouterDelegate> providesVoiceChromeRouterDelegateProvider;
    private Provider<Router> providesVoiceChromeRouterProvider;
    private Provider<WindowManager> providesWindowControllerProvider;
    private Provider<WindowInteractor> providesWindowInteractorProvider;
    private Provider smartLockNotificationInteractorProvider;
    private Provider<SmartLockNotificationPresenter> smartLockNotificationPresenterProvider;
    private Provider smartLockNotificationViewProvider;
    private Provider standardCardCreationEventListenerProvider;
    private StandardCardMetricsInteractor_Factory standardCardMetricsInteractorProvider;
    private Provider<UiEventReporter> uiEventReporterProvider;
    private Provider<VoiceChromeInteractor> voiceChromeInteractorProvider;
    private Provider voiceChromeModelProvider;
    private Provider<VoiceChromePresenter> voiceChromePresenterProvider;
    private Provider<VoiceChromeView> voiceChromeViewProvider;
    private Provider<VoiceInteractor> voiceInteractorProvider;
    private VoiceNavigator_Factory voiceNavigatorProvider;
    private Provider<VoicePresenter> voicePresenterProvider;
    private Provider<VoiceView> voiceViewProvider;

    /* loaded from: classes11.dex */
    public static final class Builder {
        private AlexaServicesModule alexaServicesModule;
        private CardModule cardModule;
        private VoiceChromeModule voiceChromeModule;
        private VoiceModule voiceModule;

        public Builder alexaServicesModule(AlexaServicesModule alexaServicesModule) {
            this.alexaServicesModule = (AlexaServicesModule) Preconditions.checkNotNull(alexaServicesModule);
            return this;
        }

        public AlexaUIActivity.VoiceComponent build() {
            if (this.alexaServicesModule == null) {
                this.alexaServicesModule = new AlexaServicesModule();
            }
            Preconditions.checkBuilderRequirement(this.voiceModule, VoiceModule.class);
            Preconditions.checkBuilderRequirement(this.voiceChromeModule, VoiceChromeModule.class);
            Preconditions.checkBuilderRequirement(this.cardModule, CardModule.class);
            return new DaggerAlexaUIActivity_VoiceComponent(this);
        }

        public Builder cardModule(CardModule cardModule) {
            this.cardModule = (CardModule) Preconditions.checkNotNull(cardModule);
            return this;
        }

        public Builder voiceChromeModule(VoiceChromeModule voiceChromeModule) {
            this.voiceChromeModule = (VoiceChromeModule) Preconditions.checkNotNull(voiceChromeModule);
            return this;
        }

        public Builder voiceModule(VoiceModule voiceModule) {
            this.voiceModule = (VoiceModule) Preconditions.checkNotNull(voiceModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.providesContextProvider = DoubleCheck.provider(VoiceModule_ProvidesContextFactory.create(builder.voiceModule));
        this.providesAlexaServicesConnectionProvider = DoubleCheck.provider(AlexaServicesModule_ProvidesAlexaServicesConnectionFactory.create(builder.alexaServicesModule, this.providesContextProvider));
        this.providesAlexaServicesApisProvider = DoubleCheck.provider(AlexaServicesModule_ProvidesAlexaServicesApisFactory.create(builder.alexaServicesModule, this.providesAlexaServicesConnectionProvider));
        this.defaultLocaleAuthorityProvider = DoubleCheck.provider(DefaultLocaleAuthority_Factory.create());
        this.voiceChromeModelProvider = DoubleCheck.provider(VoiceChromeModel_Factory.create(this.providesAlexaServicesApisProvider, this.defaultLocaleAuthorityProvider));
        this.uiEventReporterProvider = DoubleCheck.provider(UiEventReporter_Factory.create(this.providesContextProvider));
        this.providesVoiceChromeContainerProvider = DoubleCheck.provider(VoiceChromeModule_ProvidesVoiceChromeContainerFactory.create(builder.voiceChromeModule));
        this.defaultAndroidPermissionsHandlerProvider = DoubleCheck.provider(DefaultAndroidPermissionsHandler_Factory.create(this.providesContextProvider));
        this.providesPermissionsCheckerProvider = DoubleCheck.provider(VoiceModule_ProvidesPermissionsCheckerFactory.create(builder.voiceModule, this.defaultAndroidPermissionsHandlerProvider));
        this.alexaUserInterfaceOptionsTrackerProvider = DoubleCheck.provider(AlexaUserInterfaceOptionsTracker_Factory.create());
        this.defaultSpeechRecognizerProvider = DoubleCheck.provider(DefaultSpeechRecognizer_Factory.create(this.providesPermissionsCheckerProvider, this.providesAlexaServicesApisProvider, this.alexaUserInterfaceOptionsTrackerProvider));
        this.providesSpeechRecognizerProvider = DoubleCheck.provider(VoiceModule_ProvidesSpeechRecognizerFactory.create(builder.voiceModule, this.defaultSpeechRecognizerProvider));
        this.providesGetLocaleAuthorityProvider = DoubleCheck.provider(CardModule_ProvidesGetLocaleAuthorityFactory.create(builder.cardModule, this.defaultLocaleAuthorityProvider));
        this.defaultMarketplaceAuthorityProvider = DoubleCheck.provider(DefaultMarketplaceAuthority_Factory.create());
        this.providesMarketplaceAuthorityProvider = DoubleCheck.provider(CardModule_ProvidesMarketplaceAuthorityFactory.create(builder.cardModule, this.defaultMarketplaceAuthorityProvider));
        this.standardCardMetricsInteractorProvider = StandardCardMetricsInteractor_Factory.create(this.uiEventReporterProvider);
        this.providesCardMetricsInteractorProvider = DoubleCheck.provider(CardModule_ProvidesCardMetricsInteractorFactory.create(builder.cardModule, this.standardCardMetricsInteractorProvider));
        this.driveModeCardMetricsInteractorImplProvider = DriveModeCardMetricsInteractorImpl_Factory.create(this.uiEventReporterProvider);
        this.providesDriveModeCardMetricsInteractorProvider = DoubleCheck.provider(CardModule_ProvidesDriveModeCardMetricsInteractorFactory.create(builder.cardModule, this.driveModeCardMetricsInteractorImplProvider));
        this.providesWindowInteractorProvider = DoubleCheck.provider(VoiceModule_ProvidesWindowInteractorFactory.create(builder.voiceModule));
        this.providesAttentionSystemContractProvider = DoubleCheck.provider(VoiceChromeModule_ProvidesAttentionSystemContractFactory.create(builder.voiceChromeModule, this.voiceChromeModelProvider));
        this.providesPermissionsRequesterProvider = DoubleCheck.provider(VoiceModule_ProvidesPermissionsRequesterFactory.create(builder.voiceModule, this.defaultAndroidPermissionsHandlerProvider));
        this.providesActivityProvider = DoubleCheck.provider(VoiceModule_ProvidesActivityFactory.create(builder.voiceModule));
        this.providesPackageManagerProvider = DoubleCheck.provider(VoiceModule_ProvidesPackageManagerFactory.create(builder.voiceModule, this.providesContextProvider));
        this.voiceNavigatorProvider = VoiceNavigator_Factory.create(this.providesActivityProvider, this.providesPackageManagerProvider);
        this.providesNavigatorProvider = DoubleCheck.provider(VoiceModule_ProvidesNavigatorFactory.create(builder.voiceModule, this.voiceNavigatorProvider));
        this.providesTypingPrimerDisplayerProvider = DoubleCheck.provider(VoiceChromeModule_ProvidesTypingPrimerDisplayerFactory.create(builder.voiceChromeModule));
        this.providesComponentProvider = DoubleCheck.provider(VoiceModule_ProvidesComponentFactory.create(builder.voiceModule, this.providesSpeechRecognizerProvider, this.providesGetLocaleAuthorityProvider, this.providesMarketplaceAuthorityProvider, this.providesCardMetricsInteractorProvider, this.providesDriveModeCardMetricsInteractorProvider, this.providesWindowInteractorProvider, this.providesAttentionSystemContractProvider, this.providesPermissionsRequesterProvider, this.providesPermissionsCheckerProvider, this.providesNavigatorProvider, this.providesTypingPrimerDisplayerProvider));
        this.providesVoiceChromeRouterProvider = DoubleCheck.provider(VoiceChromeModule_ProvidesVoiceChromeRouterFactory.create(builder.voiceChromeModule, this.providesVoiceChromeContainerProvider, this.providesComponentProvider));
        this.providesVoiceChromeRouterDelegateProvider = DoubleCheck.provider(VoiceChromeModule_ProvidesVoiceChromeRouterDelegateFactory.create(builder.voiceChromeModule, this.providesVoiceChromeRouterProvider));
        this.providesBackButtonPressHandlerProvider = DoubleCheck.provider(VoiceChromeModule_ProvidesBackButtonPressHandlerFactory.create(builder.voiceChromeModule, this.providesVoiceChromeRouterDelegateProvider));
        this.providesSaveInstanceStateHandlerProvider = DoubleCheck.provider(VoiceChromeModule_ProvidesSaveInstanceStateHandlerFactory.create(builder.voiceChromeModule, this.providesVoiceChromeRouterDelegateProvider));
        this.providesDriveModeStateProvider = DoubleCheck.provider(VoiceModule_ProvidesDriveModeStateProviderFactory.create(builder.voiceModule));
        this.providesKeyguardManagerProvider = DoubleCheck.provider(VoiceModule_ProvidesKeyguardManagerFactory.create(builder.voiceModule, this.providesContextProvider));
        this.lockscreenControllerProvider = DoubleCheck.provider(LockscreenController_Factory.create(this.providesKeyguardManagerProvider, this.providesActivityProvider));
        this.voiceChromeInteractorProvider = DoubleCheck.provider(VoiceChromeInteractor_Factory.create(this.voiceChromeModelProvider, this.uiEventReporterProvider, this.providesBackButtonPressHandlerProvider, this.providesSaveInstanceStateHandlerProvider, this.alexaUserInterfaceOptionsTrackerProvider, this.providesDriveModeStateProvider, this.lockscreenControllerProvider));
        this.voiceChromeViewProvider = DoubleCheck.provider(VoiceChromeView_Factory.create(this.providesVoiceChromeRouterDelegateProvider));
        this.voiceChromePresenterProvider = DoubleCheck.provider(VoiceChromePresenter_Factory.create(this.voiceChromeInteractorProvider, this.voiceChromeViewProvider));
        this.cardModelProvider = DoubleCheck.provider(CardModel_Factory.create(this.providesAlexaServicesApisProvider, this.defaultMarketplaceAuthorityProvider, this.defaultLocaleAuthorityProvider));
        this.providesCardsContainerProvider = DoubleCheck.provider(CardModule_ProvidesCardsContainerFactory.create(builder.cardModule));
        this.providesCardsRouterProvider = DoubleCheck.provider(CardModule_ProvidesCardsRouterFactory.create(builder.cardModule, this.providesCardsContainerProvider, this.providesComponentProvider));
        this.providesCardsRouterDelegateProvider = DoubleCheck.provider(CardModule_ProvidesCardsRouterDelegateFactory.create(builder.cardModule, this.providesCardsRouterProvider));
        this.providesBackButtonPressHandlerProvider2 = DoubleCheck.provider(CardModule_ProvidesBackButtonPressHandlerFactory.create(builder.cardModule, this.providesCardsRouterDelegateProvider));
        this.providesSaveInstanceStateHandlerProvider2 = DoubleCheck.provider(CardModule_ProvidesSaveInstanceStateHandlerFactory.create(builder.cardModule, this.providesCardsRouterDelegateProvider));
        this.cardInteractorProvider = DoubleCheck.provider(CardInteractor_Factory.create(this.cardModelProvider, this.uiEventReporterProvider, this.providesBackButtonPressHandlerProvider2, this.providesSaveInstanceStateHandlerProvider2));
        this.standardCardCreationEventListenerProvider = DoubleCheck.provider(StandardCardCreationEventListener_Factory.create(this.uiEventReporterProvider));
        this.driveModeCardCreationEventListenerProvider = DoubleCheck.provider(DriveModeCardCreationEventListener_Factory.create(this.uiEventReporterProvider));
        this.providesCardCreationEventListenerProvider = DoubleCheck.provider(CardModule_ProvidesCardCreationEventListenerProviderFactory.create(builder.cardModule, this.standardCardCreationEventListenerProvider, this.driveModeCardCreationEventListenerProvider));
        this.providesCardFactoryWithModeProvider = DoubleCheck.provider(CardModule_ProvidesCardFactoryWithModeFactory.create(builder.cardModule, this.providesCardCreationEventListenerProvider));
        this.driveModeModelProvider = DoubleCheck.provider(DriveModeModel_Factory.create(this.providesAlexaServicesApisProvider));
        this.cardViewProvider = DoubleCheck.provider(CardView_Factory.create(this.providesCardsRouterDelegateProvider, this.providesCardFactoryWithModeProvider, this.driveModeModelProvider, this.providesWindowInteractorProvider));
        this.cardPresenterProvider = DoubleCheck.provider(CardPresenter_Factory.create(this.cardInteractorProvider, this.cardViewProvider));
        this.smartLockNotificationViewProvider = DoubleCheck.provider(SmartLockNotificationView_Factory.create(this.providesContextProvider));
        this.smartLockNotificationInteractorProvider = DoubleCheck.provider(SmartLockNotificationInteractor_Factory.create(this.lockscreenControllerProvider));
        this.smartLockNotificationPresenterProvider = DoubleCheck.provider(SmartLockNotificationPresenter_Factory.create(this.smartLockNotificationViewProvider, this.smartLockNotificationInteractorProvider));
        this.providesPowerManagerProvider = DoubleCheck.provider(VoiceModule_ProvidesPowerManagerFactory.create(builder.voiceModule, this.providesContextProvider));
        this.providesScreenLockManagerProvider = DoubleCheck.provider(VoiceModule_ProvidesScreenLockManagerFactory.create(builder.voiceModule, this.providesPowerManagerProvider, this.providesAttentionSystemContractProvider, this.providesDriveModeStateProvider));
        this.providesAccessibilityDelegateProvider = DoubleCheck.provider(CardModule_ProvidesAccessibilityDelegateFactory.create(builder.cardModule, this.providesCardsContainerProvider));
        this.accessibilityHandlerProvider = DoubleCheck.provider(AccessibilityHandler_Factory.create(this.providesAttentionSystemContractProvider, this.providesAccessibilityDelegateProvider));
        this.providesWindowControllerProvider = DoubleCheck.provider(VoiceModule_ProvidesWindowControllerFactory.create(builder.voiceModule));
        this.voiceInteractorProvider = DoubleCheck.provider(VoiceInteractor_Factory.create(this.providesActivityProvider, this.providesAlexaServicesApisProvider, this.lockscreenControllerProvider, this.providesSpeechRecognizerProvider, this.providesScreenLockManagerProvider, this.voiceChromeInteractorProvider, this.cardInteractorProvider, this.accessibilityHandlerProvider, this.uiEventReporterProvider, this.providesWindowControllerProvider, this.providesDriveModeStateProvider));
        this.voiceViewProvider = DoubleCheck.provider(VoiceView_Factory.create(this.voiceChromeViewProvider, this.cardViewProvider));
        this.voicePresenterProvider = DoubleCheck.provider(VoicePresenter_Factory.create(this.voiceInteractorProvider, this.voiceViewProvider));
    }

    private AlexaUIActivity injectAlexaUIActivity(AlexaUIActivity alexaUIActivity) {
        AlexaUIActivity_MembersInjector.injectVoicePresenter(alexaUIActivity, this.voicePresenterProvider.mo10268get());
        return alexaUIActivity;
    }

    @Override // com.amazon.alexa.voiceui.AlexaUIActivity.VoiceComponent
    public CardPresenter cardPresenter() {
        return this.cardPresenterProvider.mo10268get();
    }

    @Override // com.amazon.alexa.voiceui.AlexaUIActivity.VoiceComponent
    public void inject(AlexaUIActivity alexaUIActivity) {
        injectAlexaUIActivity(alexaUIActivity);
    }

    @Override // com.amazon.alexa.voiceui.AlexaUIActivity.VoiceComponent
    public SmartLockNotificationPresenter smartLockNotificationPresenter() {
        return this.smartLockNotificationPresenterProvider.mo10268get();
    }

    @Override // com.amazon.alexa.voiceui.AlexaUIActivity.VoiceComponent
    public VoiceChromePresenter voiceChromePresenter() {
        return this.voiceChromePresenterProvider.mo10268get();
    }

    private DaggerAlexaUIActivity_VoiceComponent(Builder builder) {
        initialize(builder);
    }
}
