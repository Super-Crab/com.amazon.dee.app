package com.amazon.alexa.accessory.speech;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.avsclient.AccessorySpeechSession;
import com.amazon.alexa.accessory.avsclient.AccessoryWakeWordCallback;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.accessory.avsclient.mode.ModeStatusChecker;
import com.amazon.alexa.accessory.capabilities.metrics.SpeechProcessingMetricsReporter;
import com.amazon.alexa.accessory.capabilities.speech.SpeechSession;
import com.amazon.alexa.accessory.capabilities.speech.SpeechSettings;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.OutputStreamSink;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.protocol.Speech;
import com.amazon.alexa.accessory.repositories.device.DeviceFeature;
import com.amazon.alexa.accessory.repositories.device.DeviceFeatures;
import com.amazon.alexa.accessory.repositories.device.Features;
import com.amazon.alexa.accessory.sco.ScoPrioritizer;
import com.amazon.alexa.accessory.speech.display.DisplayContentListenersFactory;
import com.amazon.alexa.accessory.speechapi.AlexaConnection;
import com.amazon.alexa.accessory.speechapi.LaunchType;
import com.amazon.alexa.accessory.speechapi.listeners.AudioPlaybackStatusListener;
import com.amazon.alexa.accessory.speechapi.listeners.CaptionListener;
import com.amazon.alexa.accessory.speechapi.listeners.CardListener;
import com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener;
import com.amazon.alexa.accessory.speechapi.listeners.StateListener;
import com.amazon.alexa.accessory.speechapi.speech.AccessoryAudioMetadata;
import com.amazon.alexa.accessory.speechapi.speech.AccessoryAudioProfile;
import com.amazon.alexa.accessory.speechapi.speech.AccessorySink;
import com.amazon.alexa.accessory.speechapi.speech.AccessoryWakeWord;
import com.amazon.alexa.accessory.speechapi.speech.DialogExtras;
import com.amazon.alexa.accessory.speechapi.speech.DialogRequest;
import com.amazon.alexa.accessory.speechapi.speech.DialogTurn;
import com.amazon.alexa.accessory.speechapi.speech.NextDialogTurn;
import com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider;
import com.amazon.alexa.accessory.utils.feature.AccessoryFeature;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.functions.Consumer;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes6.dex */
public final class AccessoryUserSpeechProvider implements UserSpeechProvider, StateListener {
    private static final String ACCESSORY_INVOCATION_KEY = "Accessories.";
    private static final String ALEXA = "ALEXA";
    private static final String DEFAULT_FRIENDLY_NAME = "DEFAULT_OUTPUT";
    private static final String DEFAULT_PHYSICAL_ADDRESS = "INTERNAL";
    private static final String MSBC = "MSBC";
    private static final String NOT_AVAILABLE = "NA";
    private static final String OPUS_16KHZ_16KBPS_CBR_0_20MS = "AUDIO_X_CBR_OPUS_WITH_PREAMBLE_SIZE_0_BIT_RATE_16000_FRAME_SIZE_MILLISECONDS_20";
    private static final String OPUS_16KHZ_32KBPS_CBR_0_20MS = "AUDIO_X_CBR_OPUS_WITH_PREAMBLE_SIZE_0_BIT_RATE_32000_FRAME_SIZE_MILLISECONDS_20";
    private static final String TAG = "AccessoryUserSpeechProvider";
    private static final String UNKNOWN = "UNKNOWN";
    private AccessorySink accessoryAudioSink;
    private String accessoryDeviceType;
    private String accessoryIdentifier;
    private final AccessorySession accessorySession;
    private final AlexaConnection alexaConnection;
    private NextDialogTurn alexaNextDialogTurn;
    private final AudioPlaybackStatusListener audioPlaybackStatusListener;
    private CaptionListener captionListener;
    private CardListener cardListener;
    private Callback currentCallback;
    private SpeechSession currentSession;
    private SpeechSessionCallback currentSpeechSessionCallback;
    private DialogTurn dialogTurn;
    private AtomicInteger dialogTurnSequenceId;
    private final DisplayContentListenersFactory displayContentListenersFactory;
    private final FeatureChecker featureChecker;
    private boolean finished;
    private boolean hasRegisteredAudioPlaybackStatusListener;
    private boolean isMultiTurn;
    private boolean isStartSpeech;
    private final Handler mainThreadHandler;
    private final ModeStatusChecker modeStatusChecker;
    private Callback pendingCallback;
    private SpeechSession pendingSession;
    private SpeechSessionCallback pendingSpeechSessionCallback;
    private boolean released;
    private final ScoPrioritizer scoPrioritizer;
    private final SessionSupplier sessionSupplier;
    private boolean shouldRegisterAudioPlaybackStatusListener;
    private SpeechSettings speechSettings;
    private AccessoryWakeWordCallback wakeWordCallback;

    /* renamed from: com.amazon.alexa.accessory.speech.AccessoryUserSpeechProvider$4  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Speech$AudioFormat = new int[Speech.AudioFormat.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Speech$AudioProfile;

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Speech$AudioFormat[Speech.AudioFormat.OPUS_16KHZ_16KBPS_CBR_0_20MS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Speech$AudioFormat[Speech.AudioFormat.MSBC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Speech$AudioFormat[Speech.AudioFormat.OPUS_16KHZ_32KBPS_CBR_0_20MS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $SwitchMap$com$amazon$alexa$accessory$protocol$Speech$AudioProfile = new int[Speech.AudioProfile.values().length];
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Speech$AudioProfile[Speech.AudioProfile.CLOSE_TALK.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Speech$AudioProfile[Speech.AudioProfile.FAR_FIELD.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Speech$AudioProfile[Speech.AudioProfile.NEAR_FIELD.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes6.dex */
    public interface Callback extends StateListener {
        void onDialogAccepted();

        void onDialogDenied();

        void onDialogError(Throwable th);

        void onDialogFinished();

        void onDialogRequestedForSpeechProvider(AccessoryUserSpeechProvider accessoryUserSpeechProvider);

        void onEndpointed();

        void onSpeechRequest(SpeechSettings.SpeechRequest speechRequest);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes6.dex */
    public class SpeechSessionCallback implements SpeechSession.SpeechSessionCallback {
        private Callback callback;
        private boolean endpointedByController;

        public SpeechSessionCallback(Callback callback) {
            this.callback = callback;
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSession.SpeechSessionCallback
        public void onEndpointSpeech(SpeechSession speechSession) {
            Preconditions.mainThread();
            if (this.endpointedByController) {
                this.callback.onEndpointed();
            }
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSession.SpeechSessionCallback
        public void onError(SpeechSession speechSession, Throwable th) {
            Preconditions.mainThread();
            Logger.i("%s: SpeechSession onError. Releasing controller", AccessoryUserSpeechProvider.TAG, th);
            AccessoryUserSpeechProvider.this.release(th, this.callback);
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSession.SpeechSessionCallback
        public void onRelease(SpeechSession speechSession) {
            Logger.i("%s: SpeechSession onRelease", AccessoryUserSpeechProvider.TAG);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void setEndpointedByController() {
            this.endpointedByController = true;
        }
    }

    public AccessoryUserSpeechProvider(ModeStatusChecker modeStatusChecker, ScoPrioritizer scoPrioritizer, AlexaConnection alexaConnection, AccessoryWakeWordCallback accessoryWakeWordCallback, FeatureChecker featureChecker, AccessorySession accessorySession, SessionSupplier sessionSupplier, AudioPlaybackStatusListener audioPlaybackStatusListener) {
        Preconditions.notNull(modeStatusChecker, "modeStatusChecker");
        Preconditions.notNull(alexaConnection, "alexaConnection");
        Preconditions.notNull(accessoryWakeWordCallback, "wakeWordCallback");
        Preconditions.notNull(featureChecker, "featureChecker");
        Preconditions.notNull(accessorySession, "accessorySession");
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(audioPlaybackStatusListener, "audioPlaybackStatusListener");
        this.modeStatusChecker = modeStatusChecker;
        this.scoPrioritizer = scoPrioritizer;
        this.alexaConnection = alexaConnection;
        this.wakeWordCallback = accessoryWakeWordCallback;
        this.featureChecker = featureChecker;
        this.mainThreadHandler = new Handler(Looper.getMainLooper());
        this.accessorySession = accessorySession;
        this.sessionSupplier = sessionSupplier;
        this.audioPlaybackStatusListener = audioPlaybackStatusListener;
        this.displayContentListenersFactory = new DisplayContentListenersFactory(sessionSupplier);
        this.isStartSpeech = false;
        this.dialogTurnSequenceId = new AtomicInteger(0);
        initializeDeviceType();
        checkDeviceFeaturesAndRegisterAudioPlaybackStatusListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void assignPendingToCurrent() {
        Preconditions.mainThread();
        this.currentCallback = this.pendingCallback;
        this.currentSession = this.pendingSession;
        this.currentSpeechSessionCallback = this.pendingSpeechSessionCallback;
        this.pendingCallback = null;
        this.pendingSession = null;
        this.pendingSpeechSessionCallback = null;
    }

    private void checkAlexaConnectionAndRegisterAudioPlaybackStatusListener() {
        if (this.alexaConnection.isConnected()) {
            Logger.d("%s alexa connected, check to see if audio playback status listener needs to be registered", TAG);
            registerAudioPlaybackStatusListenerIfNecessary();
            SpeechProcessingMetricsReporter.reportCounter(SpeechProcessingMetricsReporter.CounterType.ALEXA_CONNECTION_ALREADY_CONNECTED_IN_USER_SPEECH_PROVIDER, this.accessoryDeviceType, 1.0d, "NA");
            return;
        }
        Logger.d("%s alexa not connected, must connect first before registering audio playback status listener", TAG);
        connectAlexaConnectionAndRegisterAudioPlaybackStatusListener(System.currentTimeMillis());
        SpeechProcessingMetricsReporter.reportCounter(SpeechProcessingMetricsReporter.CounterType.ALEXA_CONNECTION_NOT_CONNECTED_IN_USER_SPEECH_PROVIDER, this.accessoryDeviceType, 1.0d, "NA");
    }

    @SuppressLint({"CheckResult"})
    private void checkDeviceFeaturesAndRegisterAudioPlaybackStatusListener() {
        Preconditions.mainThread();
        if (!this.featureChecker.hasAccess(AccessoryFeature.ALEXA_ACCESSORY_ANDROID_AUDIO_TUNING)) {
            Logger.d("%s audio tuning weblab is off", TAG);
            return;
        }
        Logger.d("%s audio tuning weblab is on, querying device features", TAG);
        this.accessorySession.getDeviceRepositoryV2().queryDeviceFeatures().map($$Lambda$AccessoryUserSpeechProvider$ZI4L6HRpHVQKCdCty5t4Gr5UKo.INSTANCE).toObservable().flatMap($$Lambda$tNLLyz36wpjmL1kezURjOHIEA.INSTANCE).filter($$Lambda$AccessoryUserSpeechProvider$m3FnnNXvPETGOxutzmZrIbErops.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessoryUserSpeechProvider$9xxT_hk2GI337U9l3hQpC5ydQKo
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryUserSpeechProvider.this.lambda$checkDeviceFeaturesAndRegisterAudioPlaybackStatusListener$4$AccessoryUserSpeechProvider((DeviceFeature) obj);
            }
        }, $$Lambda$AccessoryUserSpeechProvider$JI5oeyg8bLOuFutTwDjNttWZpHM.INSTANCE);
    }

    private void connectAlexaConnectionAndRegisterAudioPlaybackStatusListener(final long j) {
        this.alexaConnection.registerConnectionListener(new ConnectionListener() { // from class: com.amazon.alexa.accessory.speech.AccessoryUserSpeechProvider.1
            @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
            public void onConnected() {
                AccessoryUserSpeechProvider.this.alexaConnection.deregisterConnectionListener(this);
                if (!AccessoryUserSpeechProvider.this.released) {
                    AccessoryUserSpeechProvider.this.registerAudioPlaybackStatusListenerIfNecessary();
                    SpeechProcessingMetricsReporter.reportCounter(SpeechProcessingMetricsReporter.CounterType.ALEXA_CONNECTION_GOT_CONNECTED_IN_USER_SPEECH_PROVIDER, AccessoryUserSpeechProvider.this.accessoryDeviceType, 1.0d, "NA");
                    SpeechProcessingMetricsReporter.reportTimer(SpeechProcessingMetricsReporter.TimerType.CONNECT_ALEXA_CONNECTION_IN_REGISTER_USER_SPEECH_PROVIDER, System.currentTimeMillis() - j, AccessoryUserSpeechProvider.this.accessoryDeviceType, null);
                    return;
                }
                Logger.i("%s: AlexaConnection onConnected but already released. Ignoring connection event.", AccessoryUserSpeechProvider.TAG);
            }

            @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
            public void onConnectingFailed(ConnectionListener.ConnectingFailedReason connectingFailedReason, String str) {
                AccessoryUserSpeechProvider.this.alexaConnection.deregisterConnectionListener(this);
                Logger.d("%s: AlexaConnection onConnectingFailed for reason: %s and message: %s", AccessoryUserSpeechProvider.TAG, connectingFailedReason, str);
                AccessoryUserSpeechProvider.recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.ALEXA_CONNECTION_FAILED_IN_USER_SPEECH_PROVIDER, AccessoryUserSpeechProvider.this.accessoryDeviceType);
                AccessoryUserSpeechProvider accessoryUserSpeechProvider = AccessoryUserSpeechProvider.this;
                accessoryUserSpeechProvider.release(new IllegalStateException("AccessoryUserSpeechProvider: AlexaConnection onConnectingFailed, reason: " + connectingFailedReason + ", message: " + str), AccessoryUserSpeechProvider.this.pendingCallback);
            }

            @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
            public void onDisconnected() {
                AccessoryUserSpeechProvider.this.alexaConnection.deregisterConnectionListener(this);
                AccessoryUserSpeechProvider.recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.ALEXA_CONNECTION_DISCONNECT_IN_USER_SPEECH_PROVIDER, AccessoryUserSpeechProvider.this.accessoryDeviceType);
                AccessoryUserSpeechProvider.this.release(new IllegalStateException("AccessoryUserSpeechProvider: AlexaConnection onDisconnected"), AccessoryUserSpeechProvider.this.pendingCallback);
            }
        });
        this.alexaConnection.connect();
    }

    private static AccessoryAudioProfile convertToAccessoryAudioProfile(Speech.AudioProfile audioProfile) {
        int ordinal = audioProfile.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return AccessoryAudioProfile.NEAR_FIELD;
            }
            if (ordinal == 2) {
                return AccessoryAudioProfile.FAR_FIELD;
            }
            throw new IllegalStateException("Unrecognized Alexa Profile from accessory");
        }
        return AccessoryAudioProfile.CLOSE_TALK;
    }

    private static AccessoryAudioMetadata createAlexaAudioMetaData(SpeechSettings speechSettings) {
        AccessoryAudioProfile convertToAccessoryAudioProfile = convertToAccessoryAudioProfile(speechSettings.getAudioProfile());
        Speech.SpeechInitiator initiator = speechSettings.getInitiator();
        String audioFormat = getAudioFormat(speechSettings.getAudioFormat());
        if (initiator.getType() == Speech.SpeechInitiator.Type.WAKEWORD) {
            Speech.SpeechInitiator.WakeWord wakeWord = initiator.getWakeWord();
            return AccessoryAudioMetadata.create(convertToAccessoryAudioProfile, audioFormat, new AccessoryWakeWord(!TextUtils.isEmpty(wakeWord.getWakewordString()) ? wakeWord.getWakewordString().toUpperCase() : "ALEXA", wakeWord.getStartIndexInSamples(), wakeWord.getEndIndexInSamples()));
        }
        return AccessoryAudioMetadata.create(convertToAccessoryAudioProfile, audioFormat);
    }

    private void createAlexaConnectionListenerAndRequestDialog(final long j, final SpeechSettings speechSettings) {
        this.alexaConnection.registerConnectionListener(new ConnectionListener() { // from class: com.amazon.alexa.accessory.speech.AccessoryUserSpeechProvider.3
            @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
            public void onConnected() {
                AccessoryUserSpeechProvider.this.alexaConnection.deregisterConnectionListener(this);
                if (!AccessoryUserSpeechProvider.this.released) {
                    AccessoryUserSpeechProvider.this.registerAudioPlaybackStatusListenerIfNecessary();
                    AccessoryUserSpeechProvider.this.requestDialog();
                    AccessoryUserSpeechProvider.recordMetric(SpeechProcessingMetricsReporter.CounterType.ALEXA_CONNECTION_GOT_CONNECTED_IN_USER_SPEECH_PROVIDER, speechSettings);
                    AccessoryUserSpeechProvider.recordTimerMetric(SpeechProcessingMetricsReporter.TimerType.CONNECT_ALEXA_CONNECTION_IN_REGISTER_USER_SPEECH_PROVIDER, System.currentTimeMillis() - j, speechSettings);
                    return;
                }
                Logger.i("%s: AlexaConnection onConnected but already released. Ignoring connection event.", AccessoryUserSpeechProvider.TAG);
            }

            @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
            public void onConnectingFailed(ConnectionListener.ConnectingFailedReason connectingFailedReason, String str) {
                AccessoryUserSpeechProvider.this.alexaConnection.deregisterConnectionListener(this);
                Logger.d("%s: AlexaConnection onConnectingFailed for reason: %s and message: %s", AccessoryUserSpeechProvider.TAG, connectingFailedReason, str);
                AccessoryUserSpeechProvider.recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.ALEXA_CONNECTION_FAILED_IN_USER_SPEECH_PROVIDER, speechSettings.getDeviceType());
                AccessoryUserSpeechProvider accessoryUserSpeechProvider = AccessoryUserSpeechProvider.this;
                accessoryUserSpeechProvider.release(new IllegalStateException("AccessoryUserSpeechProvider: AlexaConnection onConnectingFailed, reason: " + connectingFailedReason + ", message: " + str), AccessoryUserSpeechProvider.this.pendingCallback);
            }

            @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
            public void onDisconnected() {
                AccessoryUserSpeechProvider.this.alexaConnection.deregisterConnectionListener(this);
                AccessoryUserSpeechProvider.recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.ALEXA_CONNECTION_DISCONNECT_IN_USER_SPEECH_PROVIDER, speechSettings.getDeviceType());
                AccessoryUserSpeechProvider.this.release(new IllegalStateException("AccessoryUserSpeechProvider: AlexaConnection onDisconnected"), AccessoryUserSpeechProvider.this.pendingCallback);
            }
        });
        this.alexaConnection.connect();
    }

    private AccessorySink createAlexaDataSink() throws IOException {
        Speech.SpeechInitiator initiator = this.speechSettings.getInitiator();
        if (initiator.getType() != Speech.SpeechInitiator.Type.WAKEWORD) {
            return null;
        }
        try {
            AccessorySink createAccessoryDataSink = this.alexaConnection.createAccessoryDataSink();
            OutputStream accessoryOutputStream = createAccessoryDataSink.getAccessoryOutputStream();
            accessoryOutputStream.write(initiator.getWakeWord().getMetadata().toByteArray());
            accessoryOutputStream.close();
            return createAccessoryDataSink;
        } catch (Exception e) {
            Logger.e(TAG, "Exception while creating AlexaDataSink", e);
            return null;
        }
    }

    private DialogExtras createAlexaDialogExtras() {
        ScoPrioritizer scoPrioritizer = this.scoPrioritizer;
        boolean z = scoPrioritizer != null && scoPrioritizer.shouldUseSco();
        recordEventMetric(AccessoryMetricsConstants.SCOPRIORITIZER_IS_NULL, this.scoPrioritizer == null, this.speechSettings);
        return DialogExtras.newBuilder().invocationType(getInvocationType()).defaultAudioDeviceFriendlyName(DEFAULT_FRIENDLY_NAME).defaultAudioDevicePhysicalAddress(DEFAULT_PHYSICAL_ADDRESS).shouldUseSco(z).suppressWakeSound(this.speechSettings.getSuppressStartEarcon()).suppressEndpointSound(this.speechSettings.getSuppressEndpointEarcon()).userVoiceVerified(false).suppressUserInterface(true ^ this.modeStatusChecker.isDriveModeForeground()).suppressSpeechResponse(shouldSuppressSpeechResponse()).build();
    }

    private static String getAudioFormat(Speech.AudioFormat audioFormat) {
        int ordinal = audioFormat.ordinal();
        if (ordinal != 1) {
            if (ordinal == 2) {
                return OPUS_16KHZ_16KBPS_CBR_0_20MS;
            }
            if (ordinal == 3) {
                return MSBC;
            }
            throw new IllegalArgumentException("Unsupported audio format: " + audioFormat);
        }
        return OPUS_16KHZ_32KBPS_CBR_0_20MS;
    }

    private String getInvocationType() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(ACCESSORY_INVOCATION_KEY);
        outline107.append(this.speechSettings.getDeviceType());
        outline107.append(".");
        outline107.append(this.speechSettings.getInitiator().getType());
        return outline107.toString();
    }

    @SuppressLint({"CheckResult"})
    private void initializeDeviceType() {
        this.accessorySession.getDeviceRepositoryV2().queryDeviceInformationSet().firstOrError().map($$Lambda$HenQHgTbazd1IYnDrhy_DQG1T8w.INSTANCE).map($$Lambda$fFtSfZI18QY7Io9iFa3QkWDvcnQ.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessoryUserSpeechProvider$-Rykpe7-sa-_U7wMABp5de1-ku8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryUserSpeechProvider.this.lambda$initializeDeviceType$0$AccessoryUserSpeechProvider((String) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessoryUserSpeechProvider$7Ndr-5rzQA4vNRPJcQqux-WrDQI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryUserSpeechProvider.this.lambda$initializeDeviceType$1$AccessoryUserSpeechProvider((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$checkDeviceFeaturesAndRegisterAudioPlaybackStatusListener$2(DeviceFeatures deviceFeatures) throws Throwable {
        List<DeviceFeature> features = deviceFeatures.getFeatures();
        Logger.d("%s obtained device features with %d entries", TAG, Integer.valueOf(features.size()));
        return features;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$checkDeviceFeaturesAndRegisterAudioPlaybackStatusListener$3(DeviceFeature deviceFeature) throws Throwable {
        return deviceFeature.getId() == 18;
    }

    @VisibleForTesting
    static void recordCounterMetric(String str, String str2) {
        if (str2 == null || str2.isEmpty()) {
            str2 = "UNKNOWN";
        }
        AccessoryMetricsServiceHolder.getInstance().get().recordCounter(str, str2, 1.0d, null);
    }

    private static void recordEventMetric(String str, boolean z, SpeechSettings speechSettings) {
        GeneratedOutlineSupport1.outline171(str, speechSettings == null ? "NA" : speechSettings.getDeviceType(), z, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void recordFailureMetric(SpeechProcessingMetricsReporter.FailureType failureType, String str) {
        SpeechProcessingMetricsReporter.reportFailure(failureType, str, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void recordMetric(SpeechProcessingMetricsReporter.CounterType counterType, SpeechSettings speechSettings) {
        recordMetric(counterType, speechSettings, speechSettings == null ? "NA" : speechSettings.getInitiator().getType().toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void recordTimerMetric(SpeechProcessingMetricsReporter.TimerType timerType, long j, SpeechSettings speechSettings) {
        SpeechProcessingMetricsReporter.reportTimer(timerType, j, speechSettings.getDeviceType(), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerAudioPlaybackStatusListenerIfNecessary() {
        if (this.featureChecker.hasAccess(AccessoryFeature.ALEXA_ACCESSORY_ANDROID_AUDIO_TUNING)) {
            boolean z = this.shouldRegisterAudioPlaybackStatusListener;
            if (z && !this.hasRegisteredAudioPlaybackStatusListener) {
                Logger.d("%s registering audio playback status listener. shouldRegister: %s, hasRegistered: %s", TAG, Boolean.valueOf(z), Boolean.valueOf(this.hasRegisteredAudioPlaybackStatusListener));
                this.alexaConnection.registerAudioPlaybackStatusListener(this.audioPlaybackStatusListener);
                this.hasRegisteredAudioPlaybackStatusListener = true;
                return;
            }
            Logger.d("%s not registering audio playback status listener. shouldRegister: %s, hasRegistered: %s", TAG, Boolean.valueOf(this.shouldRegisterAudioPlaybackStatusListener), Boolean.valueOf(this.hasRegisteredAudioPlaybackStatusListener));
        }
    }

    private void registerListenersForAccessoryWithDisplayContentFeature() {
        deregisterDisplayListeners();
        SpeechSettings speechSettings = this.speechSettings;
        if (speechSettings == null) {
            return;
        }
        for (DeviceFeature deviceFeature : speechSettings.getSpeechRelatedFeatures()) {
            if (deviceFeature.getId() == Features.DISPLAY_CARD_FEATURE.value()) {
                this.cardListener = this.displayContentListenersFactory.createCardListener(this.speechSettings, this.dialogTurnSequenceId.get());
                Logger.d("%s: Register card listener for %s with dialog turn sequence id %d", TAG, this.speechSettings.getDeviceSerialNumber(), Integer.valueOf(this.dialogTurnSequenceId.get()));
                this.alexaConnection.registerCardListener(this.cardListener);
            } else if (deviceFeature.getId() == Features.DISPLAY_CAPTION_FEATURE.value()) {
                this.captionListener = this.displayContentListenersFactory.createCaptionListener(this.speechSettings, this.dialogTurnSequenceId.get());
                Logger.d("%s: Register caption listener for %s with dialog turn sequence id %d", TAG, this.speechSettings.getDeviceSerialNumber(), Integer.valueOf(this.dialogTurnSequenceId.get()));
                this.alexaConnection.registerCaptionListener(this.captionListener);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestDialog() {
        DialogTurn dialogTurn;
        NextDialogTurn nextDialogTurn;
        Preconditions.mainThread();
        if (this.isMultiTurn && (nextDialogTurn = this.alexaNextDialogTurn) != null) {
            this.isMultiTurn = false;
            this.alexaNextDialogTurn.startTurn(this.accessoryAudioSink, new AccessoryDialogTurnStopCallback(this.pendingSpeechSessionCallback, this.pendingSession, this.accessoryIdentifier), createAlexaAudioMetaData(this.speechSettings), new AccessoryDialogTurnMetricsCallback(this.speechSettings, nextDialogTurn.getDialogTurnId()));
        } else if (!this.isStartSpeech && (dialogTurn = this.dialogTurn) != null) {
            Logger.i("%s: Executing EXPECT_SPEECH for dialogTurnId=%s", TAG, dialogTurn.getDialogTurnId());
            recordCounterMetric(SpeechProcessingMetricsReporter.CounterType.EXPECT_SPEECH_EXECUTED.getDescription(), this.accessoryDeviceType);
            startDialogTurn();
        } else {
            this.isStartSpeech = true;
            recordMetric(SpeechProcessingMetricsReporter.CounterType.SPEECH_DIALOG_REQUESTED, this.speechSettings);
            this.alexaConnection.requestDialog(this, new DialogRequest(getInvocationType(), LaunchType.WAKE_WORD));
        }
    }

    private boolean shouldSuppressSpeechResponse() {
        for (DeviceFeature deviceFeature : this.speechSettings.getSpeechRelatedFeatures()) {
            if (deviceFeature.getId() == Features.SUPPRESSING_SPEECH_RESPONSE_FEATURE.value()) {
                Logger.d("%s: Accessory has suppress speech response feature, should suppress speech response", TAG);
                return true;
            }
        }
        return false;
    }

    private void startDialogTurn() {
        String str;
        this.finished = false;
        if (this.speechSettings != null && this.accessoryAudioSink != null && this.pendingSpeechSessionCallback != null && this.pendingSession != null && (str = this.accessoryIdentifier) != null) {
            Logger.d("%s: startDialogTurn for accessory: %s with dialogTurnIdentifier: %s", TAG, str, this.dialogTurn.getDialogTurnId());
            try {
                recordMetric(SpeechProcessingMetricsReporter.CounterType.SPEECH_DIALOG_ACCEPTED, this.speechSettings);
                this.dialogTurn.startTurn(createAlexaAudioMetaData(this.speechSettings), this.accessoryAudioSink, createAlexaDataSink(), new AccessoryDialogTurnStopCallback(this.pendingSpeechSessionCallback, this.pendingSession, this.accessoryIdentifier), new AccessoryDialogTurnMetricsCallback(this.speechSettings, this.dialogTurn.getDialogTurnId()), createAlexaDialogExtras());
                this.dialogTurn = null;
                return;
            } catch (IOException e) {
                Logger.e("%s: Failed to create AlexaDataSink", e, TAG);
                assignPendingToCurrent();
                release(e, this.currentCallback);
                return;
            }
        }
        Object[] objArr = new Object[3];
        objArr[0] = TAG;
        String str2 = this.accessoryIdentifier;
        if (str2 == null) {
            str2 = "null";
        }
        objArr[1] = str2;
        objArr[2] = this.dialogTurn.getDialogTurnId();
        Logger.d("%s: startDialogTurn after release for accessory: %s with dialogTurnIdentifier: %s", objArr);
    }

    @VisibleForTesting
    void deregisterDisplayListeners() {
        CardListener cardListener = this.cardListener;
        if (cardListener != null) {
            this.alexaConnection.deregisterCardListener(cardListener);
            Logger.d("%s: Card listener is deregistered for %s", TAG, this.speechSettings.getDeviceSerialNumber());
            this.cardListener = null;
        }
        CaptionListener captionListener = this.captionListener;
        if (captionListener != null) {
            this.alexaConnection.deregisterCaptionListener(captionListener);
            Logger.d("%s: Caption listener is deregistered for %s", TAG, this.speechSettings.getDeviceSerialNumber());
            this.captionListener = null;
        }
    }

    public String getIdentifier() {
        return this.accessoryIdentifier;
    }

    public /* synthetic */ void lambda$checkDeviceFeaturesAndRegisterAudioPlaybackStatusListener$4$AccessoryUserSpeechProvider(DeviceFeature deviceFeature) throws Throwable {
        Logger.d("%s accessory supports audio tuning", TAG);
        this.shouldRegisterAudioPlaybackStatusListener = true;
        checkAlexaConnectionAndRegisterAudioPlaybackStatusListener();
    }

    public /* synthetic */ void lambda$initializeDeviceType$0$AccessoryUserSpeechProvider(String str) throws Throwable {
        this.accessoryDeviceType = str;
    }

    public /* synthetic */ void lambda$initializeDeviceType$1$AccessoryUserSpeechProvider(Throwable th) throws Throwable {
        this.accessoryDeviceType = "UNKNOWN";
    }

    public /* synthetic */ void lambda$onDialogFinished$11$AccessoryUserSpeechProvider() {
        if (this.currentCallback != null) {
            deregisterDisplayListeners();
            this.currentCallback.onDialogFinished();
        }
    }

    public /* synthetic */ void lambda$onDialogRequestDenied$7$AccessoryUserSpeechProvider() {
        recordMetric(SpeechProcessingMetricsReporter.CounterType.SPEECH_DIALOG_DENIED, this.speechSettings);
        Callback callback = this.pendingCallback;
        if (callback != null) {
            callback.onDialogDenied();
        }
    }

    public /* synthetic */ void lambda$onDialogRequested$6$AccessoryUserSpeechProvider(DialogTurn dialogTurn) {
        this.dialogTurn = dialogTurn;
        registerListenersForAccessoryWithDisplayContentFeature();
        if (this.featureChecker.hasAccess(AccessoryFeature.ALEXA_ACCESSORY_ANDROID_EXPECT_SPEECH)) {
            if (!this.isStartSpeech) {
                Logger.i("%s: received EXPECT_SPEECH for accessory.", TAG);
                recordCounterMetric(SpeechProcessingMetricsReporter.CounterType.EXPECT_SPEECH_REQUESTED.getDescription(), this.accessoryDeviceType);
                this.accessorySession.mo313getSpeechRepository().initiateSpeech();
                return;
            }
            Logger.i("%s: onDialogRequested for accessory.", TAG);
            this.isStartSpeech = false;
            startDialogTurn();
            return;
        }
        this.isStartSpeech = false;
        startDialogTurn();
    }

    public /* synthetic */ void lambda$onDialogStarted$8$AccessoryUserSpeechProvider() {
        Callback callback = this.pendingCallback;
        if (callback != null) {
            callback.onDialogAccepted();
        }
    }

    public /* synthetic */ void lambda$onDialogTurnFinished$10$AccessoryUserSpeechProvider() {
        this.finished = true;
        if (this.currentSession != null) {
            this.currentSpeechSessionCallback.setEndpointedByController();
            this.currentSession.endpointSpeech();
        }
    }

    public /* synthetic */ void lambda$onDialogTurnRequested$9$AccessoryUserSpeechProvider(NextDialogTurn nextDialogTurn) {
        this.alexaNextDialogTurn = nextDialogTurn;
        recordMetric(SpeechProcessingMetricsReporter.CounterType.SPEECH_REQUEST, this.speechSettings, null);
        recordMetric(SpeechProcessingMetricsReporter.CounterType.PROVIDE_SPEECH_REQUEST, this.speechSettings);
        Callback callback = this.currentCallback;
        if (callback != null) {
            callback.onSpeechRequest(new SpeechSettings.SpeechRequest() { // from class: com.amazon.alexa.accessory.speech.AccessoryUserSpeechProvider.2
                @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings.SpeechRequest
                public void cancel() {
                    AccessoryUserSpeechProvider.this.release(new IllegalStateException("Speech request was cancelled"), AccessoryUserSpeechProvider.this.currentCallback);
                }

                @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings.SpeechRequest
                public SpeechSession proceed(SpeechSettings speechSettings) {
                    AccessoryUserSpeechProvider.this.isMultiTurn = true;
                    AccessoryUserSpeechProvider accessoryUserSpeechProvider = AccessoryUserSpeechProvider.this;
                    return accessoryUserSpeechProvider.recognizeSpeech(speechSettings, accessoryUserSpeechProvider.currentCallback);
                }
            });
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.listeners.StateListener
    public void onAlexaStateChanged(@NonNull StateListener.AlexaState alexaState) {
        Preconditions.mainThread();
        Logger.d("%s: onAlexaStateChanged: %s with pendingCallback: %s and currentCallback: %s", TAG, alexaState, this.pendingCallback, this.currentCallback);
        Logger.i("%s: onAlexaStateChanged: %s", TAG, alexaState);
        Callback callback = this.pendingCallback;
        if (callback != null) {
            callback.onAlexaStateChanged(alexaState);
        }
        Callback callback2 = this.currentCallback;
        if (callback2 != null) {
            callback2.onAlexaStateChanged(alexaState);
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider
    public void onDialogFinished() {
        Logger.d("%s: onDialogFinished for accessory: %s", TAG, this.accessoryIdentifier);
        Logger.i("%s: onDialogFinished for accessory.", TAG);
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessoryUserSpeechProvider$C_ayBjS8D6t2760fpibgoUQ-ziQ
            @Override // java.lang.Runnable
            public final void run() {
                AccessoryUserSpeechProvider.this.lambda$onDialogFinished$11$AccessoryUserSpeechProvider();
            }
        });
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider
    public void onDialogRequestDenied() {
        Logger.d("%s: onDialogRequestDenied for accessory: %s", TAG, this.accessoryIdentifier);
        Logger.i("%s: onDialogRequestDenied for accessory.", TAG);
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessoryUserSpeechProvider$A0LbmwE7MdnJciyaXO-uTpTq43U
            @Override // java.lang.Runnable
            public final void run() {
                AccessoryUserSpeechProvider.this.lambda$onDialogRequestDenied$7$AccessoryUserSpeechProvider();
            }
        });
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider
    public void onDialogRequested(final DialogTurn dialogTurn) {
        Logger.d("%s: onDialogRequested for accessory: %s with dialogTurnIdentifier: %s", TAG, this.accessoryIdentifier, "def");
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessoryUserSpeechProvider$1-omeVBawgStJC9ZYbYIE20ojOs
            @Override // java.lang.Runnable
            public final void run() {
                AccessoryUserSpeechProvider.this.lambda$onDialogRequested$6$AccessoryUserSpeechProvider(dialogTurn);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider
    public void onDialogStarted() {
        Logger.d("%s: onDialogStarted for accessory: %s", TAG, this.accessoryIdentifier);
        Logger.i("%s: onDialogStarted for accessory.", TAG);
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessoryUserSpeechProvider$21cAdN4R7I4pp4NaK6iRwVth464
            @Override // java.lang.Runnable
            public final void run() {
                AccessoryUserSpeechProvider.this.lambda$onDialogStarted$8$AccessoryUserSpeechProvider();
            }
        });
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider
    public void onDialogTurnFinished() {
        Logger.d("%s: onDialogTurnFinished for accessory: %s", TAG, this.accessoryIdentifier);
        Logger.i("%s: onDialogTurnFinished for accessory.", TAG);
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessoryUserSpeechProvider$kEEErOlUF46msWzwD0c_r7EIhco
            @Override // java.lang.Runnable
            public final void run() {
                AccessoryUserSpeechProvider.this.lambda$onDialogTurnFinished$10$AccessoryUserSpeechProvider();
            }
        });
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider
    public void onDialogTurnRequested(final NextDialogTurn nextDialogTurn) {
        Logger.d("%s: onDialogTurnRequested for accessory: %s with dialogTurnIdentifier: %s", TAG, this.accessoryIdentifier, nextDialogTurn.getDialogTurnId());
        Logger.i("%s: onDialogTurnRequested for accessory.", TAG);
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessoryUserSpeechProvider$_5hguKDCUAK0tVC3dKUGett_-R4
            @Override // java.lang.Runnable
            public final void run() {
                AccessoryUserSpeechProvider.this.lambda$onDialogTurnRequested$9$AccessoryUserSpeechProvider(nextDialogTurn);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider
    public void onDialogTurnStarted() {
        Logger.d("%s: onDialogTurnStarted for accessory: %s", TAG, this.accessoryIdentifier);
        Logger.i("%s: onDialogTurnStarted for accessory.", TAG);
        this.dialogTurnSequenceId.getAndIncrement();
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessoryUserSpeechProvider$1AXKrwzmsONQjDPG3a5pYK64Il8
            @Override // java.lang.Runnable
            public final void run() {
                AccessoryUserSpeechProvider.this.assignPendingToCurrent();
            }
        });
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider
    public void pauseWakeWordDetection() {
        Logger.i("%s: pauseWakeWordDetection called.", TAG);
        this.wakeWordCallback.pauseWakeWordDetection();
    }

    public SpeechSession recognizeSpeech(SpeechSettings speechSettings, Callback callback) {
        Preconditions.notNull(speechSettings, "speechSettings");
        Preconditions.notNull(callback, "callback");
        Preconditions.mainThread();
        this.speechSettings = speechSettings;
        this.pendingCallback = callback;
        this.accessoryIdentifier = speechSettings.getAccessoryIdentifierProvider().getIdentifier();
        this.released = false;
        try {
            this.accessoryAudioSink = this.alexaConnection.createAccessoryAudioSink();
            this.pendingSession = new AccessorySpeechSession(new OutputStreamSink(this.accessoryAudioSink.getAccessoryOutputStream()));
            this.pendingSpeechSessionCallback = new SpeechSessionCallback(this.pendingCallback);
            this.pendingSession.addCallback(this.pendingSpeechSessionCallback);
            if (this.alexaConnection.isConnected()) {
                registerAudioPlaybackStatusListenerIfNecessary();
                requestDialog();
                recordMetric(SpeechProcessingMetricsReporter.CounterType.ALEXA_CONNECTION_ALREADY_CONNECTED_IN_USER_SPEECH_PROVIDER, speechSettings);
            } else {
                createAlexaConnectionListenerAndRequestDialog(System.currentTimeMillis(), speechSettings);
                recordMetric(SpeechProcessingMetricsReporter.CounterType.ALEXA_CONNECTION_NOT_CONNECTED_IN_USER_SPEECH_PROVIDER, speechSettings);
            }
            return this.pendingSession;
        } catch (Exception e) {
            release(new IllegalStateException("AccessoryUserSpeechProviderFailed to create AccessoryAudioSink", e), this.pendingCallback);
            recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.ACCESSORY_AUDIO_SINK_ERROR, speechSettings.getDeviceType());
            return null;
        }
    }

    public void release() {
        release(new IllegalStateException("AccessoryUserSpeechProvider is Released"), this.currentCallback);
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider
    public void resumeWakeWordDetection() {
        Logger.i("%s: resumeWakeWordDetection called.", TAG);
        this.wakeWordCallback.resumeWakeWordDetection();
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider
    public void setWakeWordDetectionEnabled(boolean z) {
        Logger.i("%s: setWakeWordDetectionEnabled with value: %b", TAG, Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void release(Throwable th, Callback callback) {
        Preconditions.mainThread();
        if (this.released) {
            return;
        }
        this.released = true;
        Logger.d("%s: release for accessory: %s finished=%b", th, TAG, this.accessoryIdentifier, Boolean.valueOf(this.finished));
        Logger.i("%s: release for accessory.", TAG);
        SpeechSession speechSession = this.currentSession;
        if (speechSession != null) {
            speechSession.release();
        }
        deregisterDisplayListeners();
        this.isMultiTurn = false;
        this.alexaNextDialogTurn = null;
        this.dialogTurn = null;
        this.currentSession = null;
        this.speechSettings = null;
        this.currentCallback = null;
        this.currentSpeechSessionCallback = null;
        if (!this.finished && callback != null) {
            Logger.d("%s: release for accessory: %s and dialog is not finished. Cancelling ongoing utterance.", TAG, this.accessoryIdentifier);
            Logger.i("%s: release for accessory and dialog is not finished. Cancelling ongoing utterance.", TAG);
            this.alexaConnection.cancelUserInteraction();
            callback.onDialogError(th);
        }
        if (!this.hasRegisteredAudioPlaybackStatusListener) {
            return;
        }
        Logger.d("%s deregistering audio playback status listener", TAG);
        this.alexaConnection.deregisterAudioPlaybackStatusListener(this.audioPlaybackStatusListener);
        this.hasRegisteredAudioPlaybackStatusListener = false;
    }

    private static void recordMetric(SpeechProcessingMetricsReporter.CounterType counterType, SpeechSettings speechSettings, String str) {
        SpeechProcessingMetricsReporter.reportCounter(counterType, speechSettings == null ? "NA" : speechSettings.getDeviceType(), 1.0d, str);
    }
}
