package com.amazon.alexa.voice.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.AlexaUserInterfaceOptions;
import com.amazon.alexa.api.AlexaUserInterfaceOptionsUtils;
import com.amazon.alexa.api.compat.AlexaServices;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.alexa.protocols.ui.TabBarAnimator;
import com.amazon.alexa.utils.audio.MicUtils;
import com.amazon.alexa.voice.elements.AlexaCardEventSender;
import com.amazon.alexa.voice.enablement.ComponentEnabler;
import com.amazon.alexa.voice.enablement.VoiceEnablement;
import com.amazon.alexa.voice.enablement.VoiceIdentityAdapter;
import com.amazon.alexa.voice.events.VoiceUiEventBroadcastReceiver;
import com.amazon.alexa.voice.events.VoxUiEventProcessingService;
import com.amazon.alexa.voice.features.FeatureAvailabilityObserver;
import com.amazon.alexa.voice.ftue.FtueManagerProvider;
import com.amazon.alexa.voice.ftue.FtuePreference;
import com.amazon.alexa.voice.ftue.VoicePermissionsChecker;
import com.amazon.alexa.voice.locale.LocaleInteractor;
import com.amazon.alexa.voice.locale.LocalePreference;
import com.amazon.alexa.voice.metrics.VoiceMetricsConstants;
import com.amazon.alexa.voice.metrics.VoxLaunchConstants;
import com.amazon.alexa.voice.metrics.VoxMetricEvent;
import com.amazon.alexa.voice.metrics.VoxMetricEventName;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import com.amazon.alexa.voice.model.DefaultVoiceService;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.navigation.NavigationAppInfo;
import com.amazon.alexa.voice.navigation.PreferredNavigationAppRepository;
import com.amazon.alexa.voice.nowplaying.AudioPlaybackController;
import com.amazon.alexa.voice.nowplaying.AudioPlayerListener;
import com.amazon.alexa.voice.nowplaying.AudioPlayerListenerAdapter;
import com.amazon.alexa.voice.nowplaying.NowPlayingCardManager;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceNowPlayingEventBusModule;
import com.amazon.alexa.voice.permissions.VoicePermissionsAuthority;
import com.amazon.alexa.voice.sdk.AlexaStateAdapter;
import com.amazon.alexa.voice.sdk.AlexaStateTracker;
import com.amazon.alexa.voice.sdk.DefaultAlexaConnectionManager;
import com.amazon.alexa.voice.settings.VoiceSettings;
import com.amazon.alexa.voice.tta.TypeToAlexaFeatureEnabler;
import com.amazon.alexa.voice.ui.VoiceActivity;
import com.amazon.alexa.voice.ui.VoiceActivityLauncher;
import com.amazon.alexa.voice.ui.VoiceEventBusRebroadcastSender;
import com.amazon.alexa.voice.ui.onedesign.scrim.ScrimParameters;
import com.amazon.alexa.voice.ui.onedesign.scrim.ScrimParametersModel;
import com.amazon.alexa.voice.ui.onedesign.util.Logger;
import com.amazon.alexa.voice.ui.player.PlaybackController;
import com.amazon.alexa.voice.ui.util.BooleanProperty;
import com.amazon.alexa.voice.wakeword.WakeWordAuthority;
import com.amazon.alexa.voice.wakeword.WakewordPreference;
import dagger.Lazy;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.SingleSubject;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes11.dex */
public class DefaultVoiceService implements VoiceService, HandsFreeSupportChecker {
    @VisibleForTesting
    static final String EXTRA_IS_SCREEN_LOCKED = "is_screen_locked";
    private static final String EXTRA_USER_INTERFACE_OPTIONS = "ui_options";
    private static final String MESSAGE_TYPE_CANCEL_SKILL = "vox::stop";
    private AlexaCardEventSender alexaCardEventSender;
    private final AlexaServicesConnection alexaServicesConnection;
    private final AlexaStateAdapter alexaStateAdapter;
    private final AlexaStateTracker alexaStateTracker;
    private final AudioPlaybackController audioPlaybackController;
    private final Map<AudioPlayerListener, AudioPlayerListenerAdapter> audioPlayerListeners;
    private final ComponentEnabler componentEnabler;
    private Subscriber.SubscriberUuid connectToVoiceServiceAfterUserLoggedIn;
    private final Context context;
    private final DefaultAlexaConnectionManager defaultAlexaConnectionManager;
    private boolean doNotBackground;
    private EventBus eventBus;
    private final VoiceEventBusRebroadcastSender eventBusRebroadcastSender;
    private final FeatureAvailabilityObserver featureAvailabilityObserver;
    private final FtueManagerProvider ftueManagerProvider;
    private final FtuePreference ftuePreference;
    private Handler handler;
    private final HandsFreeSupportChecker handsFreeSupportChecker;
    private boolean isConnected;
    private KeyguardManager keyguardManager;
    private final LocaleInteractor localeInteractor;
    private final LocalePreference localePreference;
    private final Lazy<MessagingReceiver> messagingReceiver;
    private final VoxMetricEventProcessingService metricEventProcessingService;
    private final MetricsService metricsService;
    private final NowPlayingCardManager nowPlayingCardManager;
    private final Set<Runnable> onConnectedRunnables;
    private String pendingHintText;
    private VoiceService.InvocationType pendingInvocationType;
    private boolean pendingMinimalTheme;
    private boolean pendingShowHints;
    private boolean pendingShowTtaIngress;
    private final PreferredNavigationAppRepository preferredNavigationAppRepository;
    private boolean shouldStartListening;
    private UUID skillLaunchSubscription;
    private final TypeToAlexaFeatureEnabler ttaFeatureEnabler;
    private final VoiceUiEventBroadcastReceiver uiEventBroadcastReceiver;
    private final VoiceEnablement voiceEnablement;
    private final VoiceIdentityAdapter voiceIdentityAdapter;
    private final VoiceNowPlayingEventBusModule voiceNowPlayingEventBusModule;
    private final VoicePermissionsAuthority voicePermissionsAuthority;
    private SingleSubject<Boolean> voiceRequestSuccess;
    private final VoiceSettings voiceSettings;
    private final WakeWordAuthority wakeWordAuthority;
    private final WakewordPreference wakewordPreference;
    private final VoicePermissionsChecker voicePermissionsChecker = new VoicePermissionsChecker() { // from class: com.amazon.alexa.voice.model.-$$Lambda$8ftqQndW1pChbHGSgoqFRzJs9Us
        @Override // com.amazon.alexa.voice.ftue.VoicePermissionsChecker
        public final boolean hasMinimumPermissions() {
            return DefaultVoiceService.this.hasMinimumPermission();
        }
    };
    private final AtomicBoolean isMainActivityVisible = new AtomicBoolean(false);
    private final BooleanProperty isSpeakingProperty = new BooleanProperty(false);
    private final CompositeDisposable disposable = new CompositeDisposable();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.voice.model.DefaultVoiceService$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    public class AnonymousClass1 implements VoiceIdentityAdapter.UserReadyForVoiceCallback {
        AnonymousClass1() {
        }

        public /* synthetic */ void lambda$onNoUserReadyForVoice$1$DefaultVoiceService$1() {
            DefaultVoiceService.this.componentEnabler.disableAlexaService();
        }

        public /* synthetic */ void lambda$onUserReadyForVoice$0$DefaultVoiceService$1() {
            DefaultVoiceService.this.componentEnabler.enable();
        }

        @Override // com.amazon.alexa.voice.enablement.VoiceIdentityAdapter.UserReadyForVoiceCallback
        public void onNoUserReadyForVoice() {
            DefaultVoiceService.this.handler.post(new Runnable() { // from class: com.amazon.alexa.voice.model.-$$Lambda$DefaultVoiceService$1$8N-9FS7MOnKmJv75IxrslUH0zUI
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultVoiceService.AnonymousClass1.this.lambda$onNoUserReadyForVoice$1$DefaultVoiceService$1();
                }
            });
        }

        @Override // com.amazon.alexa.voice.enablement.VoiceIdentityAdapter.UserReadyForVoiceCallback
        public void onUserReadyForVoice() {
            DefaultVoiceService.this.handler.post(new Runnable() { // from class: com.amazon.alexa.voice.model.-$$Lambda$DefaultVoiceService$1$32PaLNjy_3LJGPwppb4HdfiFYng
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultVoiceService.AnonymousClass1.this.lambda$onUserReadyForVoice$0$DefaultVoiceService$1();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultVoiceService(Context context, AlexaServicesConnection alexaServicesConnection, VoiceEnablement voiceEnablement, MetricsService metricsService, EventBus eventBus, AudioPlaybackController audioPlaybackController, VoiceNowPlayingEventBusModule voiceNowPlayingEventBusModule, NowPlayingCardManager nowPlayingCardManager, Lazy<MessagingReceiver> lazy, HandsFreeSupportChecker handsFreeSupportChecker, DefaultAlexaConnectionManager defaultAlexaConnectionManager, VoiceIdentityAdapter voiceIdentityAdapter, PreferredNavigationAppRepository preferredNavigationAppRepository, AlexaStateTracker alexaStateTracker, WakeWordAuthority wakeWordAuthority, LocaleInteractor localeInteractor, WakewordPreference wakewordPreference, VoiceSettings voiceSettings, ComponentEnabler componentEnabler, FeatureAvailabilityObserver featureAvailabilityObserver, MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar, VoiceUiEventBroadcastReceiver voiceUiEventBroadcastReceiver, VoxUiEventProcessingService voxUiEventProcessingService, VoxMetricEventProcessingService voxMetricEventProcessingService, FtueManagerProvider ftueManagerProvider, FtuePreference ftuePreference, VoicePermissionsAuthority voicePermissionsAuthority, ApplicationLifecycleService applicationLifecycleService, LocalePreference localePreference, AlexaCardEventSender alexaCardEventSender, Handler handler, TypeToAlexaFeatureEnabler typeToAlexaFeatureEnabler, VoiceEventBusRebroadcastSender voiceEventBusRebroadcastSender) {
        this.context = context;
        this.alexaServicesConnection = alexaServicesConnection;
        this.voiceEnablement = voiceEnablement;
        this.metricsService = metricsService;
        this.eventBus = eventBus;
        this.messagingReceiver = lazy;
        this.handsFreeSupportChecker = handsFreeSupportChecker;
        this.defaultAlexaConnectionManager = defaultAlexaConnectionManager;
        this.audioPlaybackController = audioPlaybackController;
        this.voiceNowPlayingEventBusModule = voiceNowPlayingEventBusModule;
        this.nowPlayingCardManager = nowPlayingCardManager;
        this.preferredNavigationAppRepository = preferredNavigationAppRepository;
        this.alexaStateTracker = alexaStateTracker;
        this.localeInteractor = localeInteractor;
        this.wakeWordAuthority = wakeWordAuthority;
        this.wakewordPreference = wakewordPreference;
        this.voiceSettings = voiceSettings;
        this.componentEnabler = componentEnabler;
        this.alexaStateAdapter = new AlexaStateAdapter(alexaServicesConnection, alexaStateTracker);
        this.featureAvailabilityObserver = featureAvailabilityObserver;
        this.voicePermissionsAuthority = voicePermissionsAuthority;
        this.voiceIdentityAdapter = voiceIdentityAdapter;
        this.uiEventBroadcastReceiver = voiceUiEventBroadcastReceiver;
        this.metricEventProcessingService = voxMetricEventProcessingService;
        this.ftueManagerProvider = ftueManagerProvider;
        this.ftuePreference = ftuePreference;
        this.localePreference = localePreference;
        CompositeDisposable compositeDisposable = this.disposable;
        Observable<Boolean> onSpeaking = this.alexaStateAdapter.onSpeaking();
        final BooleanProperty booleanProperty = this.isSpeakingProperty;
        booleanProperty.getClass();
        compositeDisposable.add(onSpeaking.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.model.-$$Lambda$PKdrZZRJrlvEBFdyHutChiNQ9Qc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                BooleanProperty.this.set(((Boolean) obj).booleanValue());
            }
        }));
        this.isConnected = false;
        this.audioPlayerListeners = new ConcurrentHashMap();
        this.onConnectedRunnables = new HashSet();
        this.keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        this.alexaCardEventSender = alexaCardEventSender;
        this.handler = handler;
        this.ttaFeatureEnabler = typeToAlexaFeatureEnabler;
        this.eventBusRebroadcastSender = voiceEventBusRebroadcastSender;
        initialize();
        configureExternalAvailability();
        applicationLifecycleService.addObserver(new AppLifecycleHandlerForVoiceService(this));
    }

    private void configureExternalAvailability() {
        this.voiceIdentityAdapter.subscribeToUserUpdates(new AnonymousClass1());
    }

    private boolean isOnLockScreen() {
        return this.keyguardManager.isKeyguardLocked();
    }

    private boolean isSpeaking() {
        return this.isSpeakingProperty.get();
    }

    private void markVoiceHasErrored() {
        SingleSubject<Boolean> singleSubject = this.voiceRequestSuccess;
        if (singleSubject != null) {
            singleSubject.onSuccess(false);
        }
    }

    private void markVoiceSucceeded() {
        SingleSubject<Boolean> singleSubject = this.voiceRequestSuccess;
        if (singleSubject != null) {
            singleSubject.onSuccess(true);
        }
    }

    private void preloadAlexaUiActivity(AlexaUserInterfaceOptions alexaUserInterfaceOptions) {
        Intent intent = new Intent("amazon.intent.action.PRELOAD_ALEXA_VOICE_UI");
        intent.setPackage(this.context.getPackageName());
        intent.setFlags(268500992);
        intent.putExtra("ui_options", AlexaUserInterfaceOptionsUtils.getBundle(alexaUserInterfaceOptions));
        intent.putExtra(EXTRA_IS_SCREEN_LOCKED, isOnLockScreen());
        this.context.startActivity(intent);
    }

    private void reportLaunchFailureDownChannelConnectionFailed() {
        StringBuilder sb = new StringBuilder(VoiceMetricsConstants.VOX_LAUNCH_FAILURE_DOWNCHANNEL_CONNECTION_FAILED);
        if (this.pendingInvocationType != null) {
            sb.append(":");
            sb.append(this.pendingInvocationType.getName());
        }
        reportVoiceLaunchFailed(sb.toString());
    }

    private void reportTapToVoiceRecordStartLatency() {
        this.metricsService.recordTimer(VoiceMetricsConstants.VOX_TAP_TO_VOICE_RECORD_START);
    }

    private void reportVoiceLaunchFailed(String str) {
        this.metricsService.recordOccurrence(VoiceMetricsConstants.OCCURRENCE_START_VOICE_SUCCESS, "vox_speech", false, null);
        this.metricsService.recordEvent(str, "vox_speech", null);
    }

    private void reportVoiceLaunchSucceeded() {
        this.metricsService.recordOccurrence(VoiceMetricsConstants.OCCURRENCE_START_VOICE_SUCCESS, "vox_speech", true, null);
    }

    private boolean requiresMinimalTheme(ScrimParametersModel scrimParametersModel) {
        return scrimParametersModel.getHideCancelButton() && scrimParametersModel.getTransparentBackground();
    }

    private void resetPendingLaunchOptions() {
        this.pendingInvocationType = null;
        this.pendingShowHints = false;
        this.pendingHintText = null;
        this.pendingMinimalTheme = false;
        this.pendingShowTtaIngress = false;
    }

    private void setAlexaLocale() {
        Configuration configuration = this.context.getResources().getConfiguration();
        int i = Build.VERSION.SDK_INT;
        updateLocale(configuration.getLocales().get(0));
    }

    private void setPendingLaunchOptions(VoiceService.InvocationType invocationType, boolean z, @Nullable String str, boolean z2, boolean z3) {
        this.pendingInvocationType = invocationType;
        this.pendingShowHints = z;
        this.pendingHintText = str;
        this.pendingMinimalTheme = z2;
        this.pendingShowTtaIngress = z3;
    }

    private synchronized void startRecording(VoiceService.InvocationType invocationType, boolean z, @Nullable String str, boolean z2, boolean z3) {
        Logger.debug("startRecording");
        if (!hasMinimumPermission()) {
            Logger.debug("Application does not have permission to record audio");
            reportVoiceLaunchFailed(VoiceMetricsConstants.VOX_LAUNCH_FAILURE_NO_PERMISSIONS);
            markVoiceHasErrored();
            return;
        }
        AlexaUserInterfaceOptions alexaUserInterfaceOptions = AlexaUserInterfaceOptionsUtils.getAlexaUserInterfaceOptions(z, str, z2, z3);
        preloadAlexaUiActivity(alexaUserInterfaceOptions);
        if (!this.isConnected) {
            this.shouldStartListening = true;
            setPendingLaunchOptions(invocationType, z, str, z2, z3);
            this.metricsService.recordEvent(VoiceMetricsConstants.VOX_LAUNCH_DELAYED_NOT_CONNECTED_YET, "vox_speech", null);
            return;
        }
        this.shouldStartListening = false;
        resetPendingLaunchOptions();
        boolean startDialog = startDialog(invocationType, alexaUserInterfaceOptions);
        Logger.debug("done startDialog, result is " + startDialog);
        if (startDialog) {
            markVoiceSucceeded();
            reportVoiceLaunchSucceeded();
            reportTapToVoiceRecordStartLatency();
        } else {
            markVoiceHasErrored();
            reportVoiceLaunchFailed(VoiceMetricsConstants.VOX_LAUNCH_FAILURE_AUDIO_NOT_AVAILABLE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateLocaleInternal */
    public void lambda$updateLocale$0$DefaultVoiceService(Locale locale) {
        this.localeInteractor.setSystemLocalesUpdatedTo(Arrays.asList(locale), 0, null);
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public synchronized void addAudioPlayerListener(AudioPlayerListener audioPlayerListener) {
        AudioPlayerListenerAdapter audioPlayerListenerAdapter = new AudioPlayerListenerAdapter(audioPlayerListener, this.voiceNowPlayingEventBusModule);
        this.audioPlayerListeners.put(audioPlayerListener, audioPlayerListenerAdapter);
        if (this.isConnected) {
            AlexaServices.Cards.registerPlayerInfoCardListener(this.alexaServicesConnection, audioPlayerListenerAdapter);
        }
    }

    boolean canMicStartRecording() {
        this.metricsService.startTimer(VoiceMetricsConstants.VOX_MIC_CAN_START_RECORDING, VoiceService.InvocationType.APP_INGRESS.getName(), Collections.emptyMap());
        boolean canStartRecording = MicUtils.canStartRecording();
        this.metricsService.recordTimer(VoiceMetricsConstants.VOX_MIC_CAN_START_RECORDING);
        return canStartRecording;
    }

    @VisibleForTesting
    boolean canStartRecording() {
        return this.wakeWordAuthority.isDetectingWakeWord() || canMicStartRecording();
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void cancel() {
        AlexaServices.Recognizer.stop(this.alexaServicesConnection);
        AlexaServices.Recognizer.cancelUserInteraction(this.alexaServicesConnection);
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void clearScrimSubscription() {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void doNotBackground(boolean z) {
        Logger.debug("doNotBackground? " + z);
        this.doNotBackground = z;
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void enableHandsfreeSettings(boolean z) {
        this.wakewordPreference.enableWakeword(z);
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public List<NavigationAppInfo> getAllNavigationApps() {
        return this.preferredNavigationAppRepository.getAllNavigationApps();
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public MessagingReceiver getMessagingReceiver() {
        return this.messagingReceiver.mo358get();
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public NowPlayingCardManager getNowPlayingCardManager() {
        return this.nowPlayingCardManager;
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public PlaybackController getPlaybackController() {
        return this.audioPlaybackController;
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public boolean hasMinimumPermission() {
        return this.voicePermissionsAuthority.hasMinimumPermissions();
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    @SuppressLint({"CheckResult"})
    public void ingressClicked() {
        this.metricsService.startTimer(VoiceMetricsConstants.VOX_TAP_TO_VOICE_RECORD_START, VoiceService.InvocationType.APP_INGRESS.getName(), Collections.emptyMap());
        this.metricsService.recordEvent(VoxLaunchConstants.VOX_LAUNCH_INGRESS_BUTTON, "vox_speech", null);
        this.metricEventProcessingService.process(VoxMetricEvent.occurNow(VoxLaunchConstants.VOX_LAUNCH_INGRESS_BUTTON));
        startVoiceExperience(this.context, VoxLaunchConstants.VOICE_INGRESS, VoiceActivity.ACTION_LAUNCH_FROM_INGRESS, null, VoiceService.InvocationType.APP_INGRESS, new ScrimParameters.Builder().showHint(false).hint(null).hideCancelButton(false).transparentBackground(false).showTTAIngress(this.ttaFeatureEnabler.isTypeToAlexaEnabled()).build());
    }

    void initialize() {
        this.featureAvailabilityObserver.startObserving();
        this.alexaServicesConnection.registerListener(this);
        this.alexaStateAdapter.initialize();
        this.audioPlaybackController.initialize();
        this.nowPlayingCardManager.initialize();
        this.voiceNowPlayingEventBusModule.initialize();
        this.defaultAlexaConnectionManager.initialize();
        this.localeInteractor.initialize();
        this.alexaCardEventSender.initialize();
        this.uiEventBroadcastReceiver.register(this.context);
        this.eventBusRebroadcastSender.start();
        setAlexaLocale();
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public boolean isAlexaActive() {
        return isListeningOrProcessing() || isSpeaking();
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public boolean isHandsfreeEnabled() {
        return this.wakewordPreference.isWakewordEnabled();
    }

    @Override // com.amazon.alexa.voice.model.VoiceService, com.amazon.alexa.voice.model.HandsFreeSupportChecker
    public boolean isHandsfreeSupported() {
        return this.handsFreeSupportChecker.isHandsfreeSupported();
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public boolean isListeningOrProcessing() {
        return this.alexaStateTracker.isListeningOrProcessing();
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public boolean isVoiceAllowed() {
        return hasMinimumPermission() && (this.ftuePreference.hasCompletedLegacyFtue() || this.ftuePreference.hasCompletedWakewordFtue());
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public boolean isVoicePossible() {
        return this.voiceEnablement.isVoicePossible();
    }

    public /* synthetic */ void lambda$null$1$DefaultVoiceService(UserIdentity userIdentity) {
        if (userIdentity == null) {
            onUserLoggedOut();
            onBackground();
            return;
        }
        onForeground(VoiceService.RecordPermission.NOT_REQUIRED);
    }

    public /* synthetic */ void lambda$onStartMainActivity$2$DefaultVoiceService(final UserIdentity userIdentity) {
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.voice.model.-$$Lambda$DefaultVoiceService$tatATlmRSWw2TLvKoySsu5fqbL0
            @Override // java.lang.Runnable
            public final void run() {
                DefaultVoiceService.this.lambda$null$1$DefaultVoiceService(userIdentity);
            }
        });
    }

    public /* synthetic */ void lambda$onStartMainActivity$4$DefaultVoiceService(Message message) {
        cancel();
    }

    public /* synthetic */ void lambda$startVoiceExperience$5$DefaultVoiceService(Boolean bool) throws Throwable {
        if (!bool.booleanValue()) {
            this.metricEventProcessingService.process(VoxMetricEvent.occurNow(VoxMetricEventName.REQUEST_ABORTED));
        }
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void onBackground() {
        if (!this.doNotBackground) {
            this.defaultAlexaConnectionManager.onBackground();
        }
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public synchronized void onConnected() {
        Logger.debug("DefaultVoiceService.onConnected");
        this.isConnected = true;
        AlexaServices.Recognizer.registerListener(this.alexaServicesConnection, this.alexaStateTracker);
        for (Runnable runnable : this.onConnectedRunnables) {
            runnable.run();
        }
        this.onConnectedRunnables.clear();
        for (AudioPlayerListenerAdapter audioPlayerListenerAdapter : this.audioPlayerListeners.values()) {
            AlexaServices.Cards.registerPlayerInfoCardListener(this.alexaServicesConnection, audioPlayerListenerAdapter);
        }
        if (this.shouldStartListening) {
            startRecording(this.pendingInvocationType, this.pendingShowHints, this.pendingHintText, this.pendingMinimalTheme, this.pendingShowTtaIngress);
        }
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public synchronized void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
        this.isConnected = false;
        if (this.onConnectedRunnables != null) {
            this.onConnectedRunnables.clear();
        }
        if (this.shouldStartListening) {
            this.shouldStartListening = false;
            reportLaunchFailureDownChannelConnectionFailed();
            resetPendingLaunchOptions();
        }
        markVoiceHasErrored();
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public synchronized void onDisconnected() {
        this.isConnected = false;
        AlexaServices.Recognizer.deregisterListener(this.alexaServicesConnection, this.alexaStateTracker);
        for (AudioPlayerListenerAdapter audioPlayerListenerAdapter : this.audioPlayerListeners.values()) {
            AlexaServices.Cards.deregisterPlayerInfoCardListener(this.alexaServicesConnection, audioPlayerListenerAdapter);
        }
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void onForeground(VoiceService.RecordPermission recordPermission) {
        if (!VoiceService.RecordPermission.REQUIRED.equals(recordPermission) || hasMinimumPermission()) {
            this.voiceIdentityAdapter.checkForVoiceCapableUser(new VoiceIdentityAdapter.UserReadyForVoiceCallback() { // from class: com.amazon.alexa.voice.model.DefaultVoiceService.2
                @Override // com.amazon.alexa.voice.enablement.VoiceIdentityAdapter.UserReadyForVoiceCallback
                public void onNoUserReadyForVoice() {
                }

                @Override // com.amazon.alexa.voice.enablement.VoiceIdentityAdapter.UserReadyForVoiceCallback
                public void onUserReadyForVoice() {
                    DefaultVoiceService.this.defaultAlexaConnectionManager.onForeground();
                }
            });
        }
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public Observable<Boolean> onIdle() {
        return this.alexaStateTracker.onIdle();
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void onResumeMainActivity(Activity activity) {
        onForeground(VoiceService.RecordPermission.NOT_REQUIRED);
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void onStartMainActivity(Activity activity) {
        Logger.debug("MainActivity is started");
        this.isMainActivityVisible.set(true);
        this.connectToVoiceServiceAfterUserLoggedIn = this.voiceIdentityAdapter.registerUserIdentityChange(new VoiceIdentityAdapter.UserIdentityChangedListener() { // from class: com.amazon.alexa.voice.model.-$$Lambda$DefaultVoiceService$b9h1ti-ZJEZ6m_AvC7p6gJUjnH8
            @Override // com.amazon.alexa.voice.enablement.VoiceIdentityAdapter.UserIdentityChangedListener
            public final void onUserChanged(UserIdentity userIdentity) {
                DefaultVoiceService.this.lambda$onStartMainActivity$2$DefaultVoiceService(userIdentity);
            }
        });
        this.voiceSettings.onMainActivityStarted();
        this.skillLaunchSubscription = this.eventBus.getSubscriber().subscribe($$Lambda$DefaultVoiceService$FCRnjpvJXOnNqn53SdgwhL3733Q.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.voice.model.-$$Lambda$DefaultVoiceService$rmwvlnZ7sfvvLrvsiFwZq9Cxqm8
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                DefaultVoiceService.this.lambda$onStartMainActivity$4$DefaultVoiceService(message);
            }
        });
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void onStopMainActivity(Activity activity) {
        Logger.debug("MainActivity is stopped");
        this.isMainActivityVisible.set(false);
        this.voiceIdentityAdapter.releaseIdentitySubscriber(this.connectToVoiceServiceAfterUserLoggedIn);
        this.voiceSettings.onMainActivityStopped();
        this.eventBus.getSubscriber().unsubscribe(this.skillLaunchSubscription);
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void onUserLoggedOut() {
        AlexaServices.Account.logOut(this.alexaServicesConnection);
        this.nowPlayingCardManager.clearCardData();
        this.ftueManagerProvider.getFtueManager().clearFtuePreferences();
        this.wakewordPreference.enableWakeword(false);
        this.localePreference.clearUpdatedByUserFlag();
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public synchronized Single<Boolean> recognizeSpeech(VoiceService.InvocationType invocationType) {
        return recognizeSpeech(invocationType, false, null, false, this.ttaFeatureEnabler.isTypeToAlexaEnabled());
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void registerScrimListenerIfSpeaking() {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void release() {
        this.uiEventBroadcastReceiver.unregister(this.context);
        this.alexaStateAdapter.release();
        this.audioPlaybackController.release();
        this.nowPlayingCardManager.release();
        this.voicePermissionsAuthority.release();
        this.voiceIdentityAdapter.release();
        this.alexaServicesConnection.deregisterListener(this);
        this.defaultAlexaConnectionManager.release();
        this.disposable.dispose();
        this.featureAvailabilityObserver.stopObserving();
        this.voiceNowPlayingEventBusModule.release();
        this.localeInteractor.release();
        this.alexaCardEventSender.release();
        this.eventBusRebroadcastSender.stop();
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public synchronized void removeAudioPlayerListener(AudioPlayerListener audioPlayerListener) {
        AudioPlayerListenerAdapter remove = this.audioPlayerListeners.remove(audioPlayerListener);
        if (remove == null) {
            return;
        }
        AlexaServices.Cards.deregisterPlayerInfoCardListener(this.alexaServicesConnection, remove);
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void setPreferredNavigationApp(String str) {
        this.preferredNavigationAppRepository.setPreferredNavigationApp(str);
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void setTabBarAnimator(TabBarAnimator tabBarAnimator) {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void setVoiceActivityLauncher(VoiceActivityLauncher voiceActivityLauncher) {
    }

    @SuppressLint({"MissingPermission"})
    @VisibleForTesting
    boolean startDialog(VoiceService.InvocationType invocationType, AlexaUserInterfaceOptions alexaUserInterfaceOptions) {
        if (this.voicePermissionsAuthority.hasMinimumPermissions()) {
            AlexaServices.Recognizer.start(this.alexaServicesConnection, invocationType.getName(), alexaUserInterfaceOptions);
            return true;
        }
        return false;
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void startVoiceExperience(Context context, String str, String str2, Map<String, String> map, VoiceService.InvocationType invocationType, ScrimParametersModel scrimParametersModel) {
        String str3 = map != null ? map.get("referer") : null;
        if (str3 == null) {
            str3 = "UNKNOWN";
        }
        if (this.ftueManagerProvider.getFtueManager().requiresFtue(this.voicePermissionsChecker, str, str3)) {
            Intent intent = new Intent(context, VoiceActivity.class);
            intent.setFlags(268435456);
            intent.setAction(str2);
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    intent.putExtra(entry.getKey(), entry.getValue());
                }
            }
            context.startActivity(intent);
            return;
        }
        recognizeSpeech(invocationType, scrimParametersModel).subscribe(new Consumer() { // from class: com.amazon.alexa.voice.model.-$$Lambda$DefaultVoiceService$ZwXhk_8Ue1ynII5dOkhwEyb9bEE
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultVoiceService.this.lambda$startVoiceExperience$5$DefaultVoiceService((Boolean) obj);
            }
        });
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void stopListening() {
        AlexaServices.Recognizer.stop(this.alexaServicesConnection);
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public synchronized void updateLocale(final Locale locale) {
        if (this.isConnected) {
            lambda$updateLocale$0$DefaultVoiceService(locale);
        } else {
            this.onConnectedRunnables.add(new Runnable() { // from class: com.amazon.alexa.voice.model.-$$Lambda$DefaultVoiceService$Kuwf-uj6VGxKzDpBwJijVKNfm6s
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultVoiceService.this.lambda$updateLocale$0$DefaultVoiceService(locale);
                }
            });
        }
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public synchronized Single<Boolean> recognizeSpeech(VoiceService.InvocationType invocationType, ScrimParametersModel scrimParametersModel) {
        return recognizeSpeech(invocationType, scrimParametersModel.getShowHint(), scrimParametersModel.getHint(), requiresMinimalTheme(scrimParametersModel), scrimParametersModel.getShowTTAIngress());
    }

    private Single<Boolean> recognizeSpeech(VoiceService.InvocationType invocationType, boolean z, @Nullable String str, boolean z2, boolean z3) {
        this.voiceRequestSuccess = SingleSubject.create();
        if (!canStartRecording()) {
            reportVoiceLaunchFailed(VoiceMetricsConstants.VOX_LAUNCH_FAILURE_AUDIO_NOT_AVAILABLE);
            markVoiceHasErrored();
        } else {
            startRecording(invocationType, z, str, z2, z3);
        }
        return this.voiceRequestSuccess;
    }
}
