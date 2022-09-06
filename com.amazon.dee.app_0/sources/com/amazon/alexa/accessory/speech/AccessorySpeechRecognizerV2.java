package com.amazon.alexa.accessory.speech;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.avsclient.AccessoryWakeWordCallback;
import com.amazon.alexa.accessory.avsclient.ambient_sound.LastUtteranceSupplier;
import com.amazon.alexa.accessory.avsclient.mode.ModeStatusChecker;
import com.amazon.alexa.accessory.avsclient.multiturn_delay.MultiturnDelayProvider;
import com.amazon.alexa.accessory.avsclient.nearmiss.DefaultNearMissSpeechSessionFactory;
import com.amazon.alexa.accessory.avsclient.nearmiss.NearMissSpeechSession;
import com.amazon.alexa.accessory.capabilities.metrics.SpeechProcessingMetricsReporter;
import com.amazon.alexa.accessory.capabilities.speech.SpeechRecognizer;
import com.amazon.alexa.accessory.capabilities.speech.SpeechSession;
import com.amazon.alexa.accessory.capabilities.speech.SpeechSettings;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.nearmiss.MlisClient;
import com.amazon.alexa.accessory.registration.RegistrationSupplier;
import com.amazon.alexa.accessory.sco.ScoPrioritizer;
import com.amazon.alexa.accessory.speech.AccessorySpeechRecognizerV2;
import com.amazon.alexa.accessory.speech.AccessoryUserSpeechProvider;
import com.amazon.alexa.accessory.speechapi.AlexaConnection;
import com.amazon.alexa.accessory.speechapi.context.AccessoryContextProvider;
import com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener;
import com.amazon.alexa.accessory.speechapi.listeners.StateListener;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import javax.annotation.Nullable;
/* loaded from: classes6.dex */
public final class AccessorySpeechRecognizerV2 implements SpeechRecognizer, ConnectionListener, LastUtteranceSupplier {
    private static final long SPEECH_RECOGNITION_DEFAULT_DELAY_MILLIS = 700;
    private static final String TAG = "AccessorySpeechRecognizerV2:";
    private final AvsSpeechRecognizer avsSpeechRecognizer;
    private final AlexaConnection connection;
    private final AccessoryContextProvider contextProvider;
    private final Handler handler;
    private String lastUtteranceUuid;
    private int latestDialogTurnSequenceIdentifier;
    private final Object lock;
    private final MlisClient mlisClient;
    private final MultiturnDelayProvider multiturnDelayProvider;
    private final NearMissSpeechSession.Factory nearMissSpeechSessionFactory;
    private final RegistrationSupplier registrationSupplier;
    private final AccessorySpeechProviderManager speechProviderManager;
    private boolean wakewordInitiatedSpeechRecognitionEnabled;
    private Long wakewordInitiatedSpeechRecognitionEnabledTime;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.accessory.speech.AccessorySpeechRecognizerV2$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$speechapi$listeners$StateListener$AlexaState = new int[StateListener.AlexaState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$speechapi$listeners$StateListener$AlexaState[StateListener.AlexaState.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$speechapi$listeners$StateListener$AlexaState[StateListener.AlexaState.LISTENING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$speechapi$listeners$StateListener$AlexaState[StateListener.AlexaState.THINKING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$speechapi$listeners$StateListener$AlexaState[StateListener.AlexaState.SPEAKING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$speechapi$listeners$StateListener$AlexaState[StateListener.AlexaState.ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$speechapi$listeners$StateListener$AlexaState[StateListener.AlexaState.UNKNOWN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes6.dex */
    private final class AccessorySpeechProviderManagerWakeWordCallback implements AccessoryWakeWordCallback {
        private AccessorySpeechProviderManagerWakeWordCallback() {
        }

        @Override // com.amazon.alexa.accessory.avsclient.AccessoryWakeWordCallback
        public void pauseWakeWordDetection() {
            synchronized (AccessorySpeechRecognizerV2.this.lock) {
                AccessorySpeechRecognizerV2.this.wakewordInitiatedSpeechRecognitionEnabled = false;
                AccessorySpeechRecognizerV2.this.wakewordInitiatedSpeechRecognitionEnabledTime = null;
            }
            Logger.d("%s pauseWakeWordDetection() - setting wakewordInitiatedSpeechRecognitionEnabled = false", AccessorySpeechRecognizerV2.TAG);
        }

        @Override // com.amazon.alexa.accessory.avsclient.AccessoryWakeWordCallback
        public void resumeWakeWordDetection() {
            synchronized (AccessorySpeechRecognizerV2.this.lock) {
                AccessorySpeechRecognizerV2.this.wakewordInitiatedSpeechRecognitionEnabled = true;
                AccessorySpeechRecognizerV2.this.wakewordInitiatedSpeechRecognitionEnabledTime = Long.valueOf(System.currentTimeMillis());
            }
            Logger.d("%s resumeWakeWordDetection() - setting wakewordInitiatedSpeechRecognitionEnabled = true", AccessorySpeechRecognizerV2.TAG);
        }

        /* synthetic */ AccessorySpeechProviderManagerWakeWordCallback(AccessorySpeechRecognizerV2 accessorySpeechRecognizerV2, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public final class AvsSpeechRecognizer {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.amazon.alexa.accessory.speech.AccessorySpeechRecognizerV2$AvsSpeechRecognizer$1  reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass1 implements AccessoryUserSpeechProvider.Callback {
            private boolean dialogIsFinished;
            private int dialogTurnSequenceIdentifier = -1;
            private boolean isMultiturn;
            private StateListener.AlexaState lastState;
            private final int multiturnDelay;
            final /* synthetic */ SpeechSettings val$speechSettings;

            AnonymousClass1(SpeechSettings speechSettings) {
                this.val$speechSettings = speechSettings;
                this.multiturnDelay = AccessorySpeechRecognizerV2.this.multiturnDelayProvider.getMultiturnDelay(this.val$speechSettings.getDeviceType());
            }

            private void clearContext() {
                int currentDialogTurnRequestSequenceIdentifier = AccessorySpeechRecognizerV2.this.getCurrentDialogTurnRequestSequenceIdentifier();
                String identifier = this.val$speechSettings.getAccessoryIdentifierProvider().getIdentifier();
                int i = this.dialogTurnSequenceIdentifier;
                if (i != currentDialogTurnRequestSequenceIdentifier) {
                    Logger.d("%s ERROR: Ignoring request to clearContext. Current dialogTurnSequenceIdentifier %d is requesting to clear context but is superseded by a more recent dialogTurnSequenceIdentifier %d for accessory %s", AccessorySpeechRecognizerV2.TAG, Integer.valueOf(i), Integer.valueOf(currentDialogTurnRequestSequenceIdentifier), identifier);
                    Logger.e("%s Ignoring request to clearContext. Current dialogTurnSequenceIdentifier %d is requesting to clear context but is superseded by a more recent dialogTurnSequenceIdentifier %d for accessory", AccessorySpeechRecognizerV2.TAG, Integer.valueOf(this.dialogTurnSequenceIdentifier), Integer.valueOf(currentDialogTurnRequestSequenceIdentifier));
                    return;
                }
                Logger.d("%s Clearing context on behalf of accessory %s", AccessorySpeechRecognizerV2.TAG, identifier);
                AccessorySpeechRecognizerV2.this.contextProvider.clearActiveAccessoryMicrophone();
            }

            private void setContext() {
                this.dialogTurnSequenceIdentifier = AccessorySpeechRecognizerV2.this.createLatestDialogTurnSequenceIdentifier();
                Logger.d("%s Setting context on behalf of accessory %s for dialogTurnSequenceIdentifier %d", AccessorySpeechRecognizerV2.TAG, this.val$speechSettings.getAccessoryIdentifierProvider().getIdentifier(), Integer.valueOf(this.dialogTurnSequenceIdentifier));
                AccessorySpeechRecognizerV2.this.contextProvider.clearActiveAccessoryMicrophone();
                AccessorySpeechRecognizerV2.this.contextProvider.setActiveAccessoryMicrophone(this.val$speechSettings.getDeviceType(), this.val$speechSettings.getDeviceSerialNumber(), this.val$speechSettings.getDeviceFirmwareVersion(), this.val$speechSettings.getSessionIdentifierProvider().getIdentifier());
            }

            public /* synthetic */ void lambda$onSpeechRequest$0$AccessorySpeechRecognizerV2$AvsSpeechRecognizer$1(SpeechSettings speechSettings, SpeechSettings.SpeechRequest speechRequest) {
                setContext();
                speechSettings.getCallback().onSpeechRequest(speechRequest);
            }

            @Override // com.amazon.alexa.accessory.speechapi.listeners.StateListener
            public void onAlexaStateChanged(StateListener.AlexaState alexaState) {
                int ordinal = alexaState.ordinal();
                if (ordinal != 0) {
                    if (ordinal != 2) {
                        if (ordinal == 4) {
                            this.val$speechSettings.getCallback().onSpeechProcessingStarted();
                        } else if (ordinal == 5) {
                            this.val$speechSettings.getCallback().onSpeechStarted();
                        } else if (ordinal == 6) {
                            this.val$speechSettings.getCallback().onSpeechRecognitionFailed(new IllegalStateException("AlexaState is error"));
                            AvsSpeechRecognizer.this.recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.SPEECH_RECOGNIZER_V2_ON_ALEXA_STATE_ERROR, this.val$speechSettings);
                        } else if (ordinal == 7) {
                            this.val$speechSettings.getCallback().onSpeechRecognitionFailed(new IllegalStateException("AlexaState is unknown"));
                            AvsSpeechRecognizer.this.recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.SPEECH_RECOGNIZER_V2_ON_ALEXA_STATE_UNKNOWN, this.val$speechSettings);
                        }
                    } else if (this.dialogIsFinished) {
                        this.val$speechSettings.getCallback().onSpeechCompleted();
                    } else if (this.isMultiturn && this.multiturnDelay != 0) {
                        Handler handler = AccessorySpeechRecognizerV2.this.handler;
                        final SpeechSettings speechSettings = this.val$speechSettings;
                        handler.postDelayed(new Runnable() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessorySpeechRecognizerV2$AvsSpeechRecognizer$1$DgX79HBnN60Tvv6-8IT8q_-ov8o
                            @Override // java.lang.Runnable
                            public final void run() {
                                SpeechSettings.this.getCallback().onSpeechRecognitionStarted();
                            }
                        }, this.multiturnDelay);
                    } else {
                        this.val$speechSettings.getCallback().onSpeechRecognitionStarted();
                    }
                } else if (this.dialogIsFinished) {
                    this.val$speechSettings.getCallback().onSpeechCompleted();
                }
                this.lastState = alexaState;
            }

            @Override // com.amazon.alexa.accessory.speech.AccessoryUserSpeechProvider.Callback
            public void onDialogAccepted() {
                Logger.d("%s onDialogAccepted: %d", AccessorySpeechRecognizerV2.TAG, Integer.valueOf(this.val$speechSettings.getDialogId()));
            }

            @Override // com.amazon.alexa.accessory.speech.AccessoryUserSpeechProvider.Callback
            public void onDialogDenied() {
                Logger.d("%s onDialogDenied: %d", AccessorySpeechRecognizerV2.TAG, Integer.valueOf(this.val$speechSettings.getDialogId()));
                this.val$speechSettings.getCallback().onSpeechRecognitionDenied();
                clearContext();
            }

            @Override // com.amazon.alexa.accessory.speech.AccessoryUserSpeechProvider.Callback
            public void onDialogError(Throwable th) {
                Logger.e("%s onDialogError: %d", th, AccessorySpeechRecognizerV2.TAG, Integer.valueOf(this.val$speechSettings.getDialogId()));
                this.val$speechSettings.getCallback().onSpeechRecognitionFailed(th);
                clearContext();
                AvsSpeechRecognizer.this.recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.SPEECH_RECOGNIZER_V2_ON_DIALOG_ERROR, this.val$speechSettings);
            }

            @Override // com.amazon.alexa.accessory.speech.AccessoryUserSpeechProvider.Callback
            public void onDialogFinished() {
                Logger.d("%s onDialogFinished: %d", AccessorySpeechRecognizerV2.TAG, Integer.valueOf(this.val$speechSettings.getDialogId()));
                this.dialogIsFinished = true;
                if (this.lastState == StateListener.AlexaState.IDLE) {
                    this.val$speechSettings.getCallback().onSpeechCompleted();
                }
                clearContext();
            }

            @Override // com.amazon.alexa.accessory.speech.AccessoryUserSpeechProvider.Callback
            public void onDialogRequestedForSpeechProvider(AccessoryUserSpeechProvider accessoryUserSpeechProvider) {
                Logger.d("%s onDialogRequestedForSpeechProvider: %d", AccessorySpeechRecognizerV2.TAG, Integer.valueOf(this.val$speechSettings.getDialogId()));
                setContext();
            }

            @Override // com.amazon.alexa.accessory.speech.AccessoryUserSpeechProvider.Callback
            public void onEndpointed() {
                Logger.d("%s onEndpointed: %d", AccessorySpeechRecognizerV2.TAG, Integer.valueOf(this.val$speechSettings.getDialogId()));
                this.isMultiturn = true;
                this.val$speechSettings.getCallback().onSpeechRecognitionFinished();
            }

            @Override // com.amazon.alexa.accessory.speech.AccessoryUserSpeechProvider.Callback
            public void onSpeechRequest(final SpeechSettings.SpeechRequest speechRequest) {
                Logger.d("%s onSpeechRequest: %d", AccessorySpeechRecognizerV2.TAG, Integer.valueOf(this.val$speechSettings.getDialogId()));
                Logger.d("%s requesting a new speech turn in %d ms.", AccessorySpeechRecognizerV2.TAG, Integer.valueOf(this.multiturnDelay));
                if (this.multiturnDelay != 0) {
                    Handler handler = AccessorySpeechRecognizerV2.this.handler;
                    final SpeechSettings speechSettings = this.val$speechSettings;
                    handler.postDelayed(new Runnable() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessorySpeechRecognizerV2$AvsSpeechRecognizer$1$K6m7g-uuJUg9Ni4l2tz1TqXbG0s
                        @Override // java.lang.Runnable
                        public final void run() {
                            AccessorySpeechRecognizerV2.AvsSpeechRecognizer.AnonymousClass1.this.lambda$onSpeechRequest$0$AccessorySpeechRecognizerV2$AvsSpeechRecognizer$1(speechSettings, speechRequest);
                        }
                    }, this.multiturnDelay);
                    return;
                }
                setContext();
                this.val$speechSettings.getCallback().onSpeechRequest(speechRequest);
            }
        }

        private AvsSpeechRecognizer() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void recordFailureMetric(SpeechProcessingMetricsReporter.FailureType failureType, SpeechSettings speechSettings) {
            SpeechProcessingMetricsReporter.reportFailure(failureType, speechSettings.getDeviceType(), null, null);
        }

        SpeechSession recognizeSpeech(SpeechSettings speechSettings) {
            Preconditions.mainThread();
            Logger.d("%s recognizeSpeech with settings=%s", AccessorySpeechRecognizerV2.TAG, speechSettings);
            return AccessorySpeechRecognizerV2.this.speechProviderManager.requestSpeechDialog(speechSettings, new AnonymousClass1(speechSettings));
        }

        /* synthetic */ AvsSpeechRecognizer(AccessorySpeechRecognizerV2 accessorySpeechRecognizerV2, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* loaded from: classes6.dex */
    public static final class Builder {
        private AlexaConnection alexaConnection;
        private Context context;
        private AccessoryContextProvider contextProvider;
        private FeatureChecker featureChecker;
        private MlisClient mlisClient;
        private ModeStatusChecker modeStatusChecker;
        private MultiturnDelayProvider multiturnDelayProvider;
        private NearMissSpeechSession.Factory nearMissSpeechSessionFactory;
        private RegistrationSupplier registrationSupplier;
        private ScoPrioritizer scoPrioritizer;
        private SessionSupplier sessionSupplier;

        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        public AccessorySpeechRecognizerV2 build() {
            Preconditions.notNull(this.alexaConnection, "alexaConnection");
            Preconditions.notNull(this.contextProvider, "contextProvider");
            Preconditions.notNull(this.mlisClient, "mlisClient");
            Preconditions.notNull(this.modeStatusChecker, "modeStatusChecker");
            Preconditions.notNull(this.multiturnDelayProvider, "multiturnDelayProvider");
            Preconditions.notNull(this.registrationSupplier, "registrationSupplier");
            Preconditions.notNull(this.sessionSupplier, "sessionSupplier");
            Preconditions.notNull(this.context, "context");
            Preconditions.notNull(this.featureChecker, "featureChecker");
            if (this.nearMissSpeechSessionFactory == null) {
                this.nearMissSpeechSessionFactory = new DefaultNearMissSpeechSessionFactory();
            }
            return new AccessorySpeechRecognizerV2(this, null);
        }

        public Builder setAlexaConnection(AlexaConnection alexaConnection) {
            this.alexaConnection = alexaConnection;
            return this;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setContextProvider(AccessoryContextProvider accessoryContextProvider) {
            this.contextProvider = accessoryContextProvider;
            return this;
        }

        public Builder setFeatureChecker(FeatureChecker featureChecker) {
            this.featureChecker = featureChecker;
            return this;
        }

        public Builder setMlisClient(MlisClient mlisClient) {
            this.mlisClient = mlisClient;
            return this;
        }

        public Builder setModeStatusChecker(ModeStatusChecker modeStatusChecker) {
            this.modeStatusChecker = modeStatusChecker;
            return this;
        }

        public Builder setMultiturnDelayProvider(MultiturnDelayProvider multiturnDelayProvider) {
            this.multiturnDelayProvider = multiturnDelayProvider;
            return this;
        }

        public Builder setNearMissSpeechSessionFactory(NearMissSpeechSession.Factory factory) {
            this.nearMissSpeechSessionFactory = factory;
            return this;
        }

        public Builder setRegistrationSupplier(RegistrationSupplier registrationSupplier) {
            this.registrationSupplier = registrationSupplier;
            return this;
        }

        public Builder setScoPrioritizer(ScoPrioritizer scoPrioritizer) {
            this.scoPrioritizer = scoPrioritizer;
            return this;
        }

        public Builder setSessionSupplier(SessionSupplier sessionSupplier) {
            this.sessionSupplier = sessionSupplier;
            return this;
        }

        private Builder() {
        }
    }

    /* synthetic */ AccessorySpeechRecognizerV2(Builder builder, AnonymousClass1 anonymousClass1) {
        this(builder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int createLatestDialogTurnSequenceIdentifier() {
        int i;
        synchronized (this.lock) {
            this.latestDialogTurnSequenceIdentifier++;
            i = this.latestDialogTurnSequenceIdentifier;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getCurrentDialogTurnRequestSequenceIdentifier() {
        int i;
        synchronized (this.lock) {
            i = this.latestDialogTurnSequenceIdentifier;
        }
        return i;
    }

    private boolean isTimeElapsed(String str) {
        synchronized (this.lock) {
            boolean z = true;
            if (this.wakewordInitiatedSpeechRecognitionEnabledTime == null) {
                return true;
            }
            if (System.currentTimeMillis() - this.wakewordInitiatedSpeechRecognitionEnabledTime.longValue() <= this.multiturnDelayProvider.getMultiturnDelay(str) + SPEECH_RECOGNITION_DEFAULT_DELAY_MILLIS) {
                z = false;
            }
            return z;
        }
    }

    private SpeechSession nearMissSpeech(SpeechSettings speechSettings) {
        this.contextProvider.clearActiveAccessoryMicrophone();
        NearMissSpeechSession create = this.nearMissSpeechSessionFactory.create(this.mlisClient, speechSettings, this.registrationSupplier);
        create.start();
        return create;
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    @Override // com.amazon.alexa.accessory.avsclient.ambient_sound.LastUtteranceSupplier
    @Nullable
    public String getUuidForLastUtterance() {
        Preconditions.mainThread();
        return this.lastUtteranceUuid;
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechRecognizer
    public boolean isSpeechRecognitionEnabled(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.lock) {
            Logger.d("%s wakewordInitiatedSpeechRecognitionEnabled = %b", TAG, Boolean.valueOf(this.wakewordInitiatedSpeechRecognitionEnabled));
            if (this.wakewordInitiatedSpeechRecognitionEnabled && !isTimeElapsed(str)) {
                AccessoryMetricsServiceHolder.getInstance().get().recordTime(MetricsConstants.Speech.SPEECH_RECOGNITION_ENABLED_IN_BUFFER_TIME, str, System.currentTimeMillis() - currentTimeMillis, null);
                Logger.d("%s still in buffer time after wakewordInitiatedSpeechRecognition is Enabled", TAG);
                return false;
            }
            AccessoryMetricsServiceHolder.getInstance().get().recordTime(MetricsConstants.Speech.SPEECH_RECOGNITION_ENABLED_TIME, str, System.currentTimeMillis() - currentTimeMillis, null);
            return this.wakewordInitiatedSpeechRecognitionEnabled;
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
    public void onConnected() {
        Preconditions.mainThread();
        Logger.d("%s onConnected", TAG);
        this.contextProvider.activate();
        this.multiturnDelayProvider.activate();
        synchronized (this.lock) {
            this.wakewordInitiatedSpeechRecognitionEnabled = true;
            this.wakewordInitiatedSpeechRecognitionEnabledTime = null;
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
    public void onConnectingFailed(ConnectionListener.ConnectingFailedReason connectingFailedReason, String str) {
        Preconditions.mainThread();
        Logger.d("%s: onConnectingFailed with reason: %s and message: %s", TAG, connectingFailedReason, str);
        this.contextProvider.deactivate();
        this.multiturnDelayProvider.deactivate();
    }

    @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
    public void onDisconnected() {
        Preconditions.mainThread();
        Logger.d("%s: onDisconnected", TAG);
        this.speechProviderManager.release();
        this.contextProvider.deactivate();
        this.multiturnDelayProvider.deactivate();
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechRecognizer
    public SpeechSession recognizeSpeech(SpeechSettings speechSettings) {
        Preconditions.mainThread();
        Preconditions.notNull(speechSettings, "settings");
        Logger.d("%s Recognize Speech has been requested", TAG);
        if (speechSettings.getInitiator().hasWakeWord() && speechSettings.getInitiator().getWakeWord().getNearMiss()) {
            return nearMissSpeech(speechSettings);
        }
        this.lastUtteranceUuid = speechSettings.getSessionIdentifierProvider().getIdentifier();
        return this.avsSpeechRecognizer.recognizeSpeech(speechSettings);
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechRecognizer
    public void release() {
        Preconditions.mainThread();
        Logger.d("%s: release", TAG);
        this.connection.deregisterConnectionListener(this);
        this.contextProvider.deactivate();
        this.multiturnDelayProvider.deactivate();
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechRecognizer
    public void stopSpeech(String str) {
        if (str != null) {
            this.connection.cancelUserInteraction();
        }
    }

    private AccessorySpeechRecognizerV2(Builder builder) {
        this.latestDialogTurnSequenceIdentifier = 0;
        this.connection = builder.alexaConnection;
        this.contextProvider = builder.contextProvider;
        this.mlisClient = builder.mlisClient;
        this.multiturnDelayProvider = builder.multiturnDelayProvider;
        this.registrationSupplier = builder.registrationSupplier;
        this.nearMissSpeechSessionFactory = builder.nearMissSpeechSessionFactory;
        this.avsSpeechRecognizer = new AvsSpeechRecognizer(this, null);
        this.handler = new Handler(Looper.myLooper());
        this.speechProviderManager = new AccessorySpeechProviderManager(this.connection, builder.modeStatusChecker, builder.scoPrioritizer, builder.sessionSupplier, builder.context, new AccessorySpeechProviderManagerWakeWordCallback(this, null), builder.featureChecker);
        this.connection.registerConnectionListener(this);
        this.wakewordInitiatedSpeechRecognitionEnabled = true;
        this.wakewordInitiatedSpeechRecognitionEnabledTime = null;
        this.lock = new Object();
    }
}
