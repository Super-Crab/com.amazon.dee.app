package com.amazon.alexa.accessory.speech;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.avsclient.AccessoryWakeWordCallback;
import com.amazon.alexa.accessory.avsclient.mode.ModeStatusChecker;
import com.amazon.alexa.accessory.capabilities.metrics.SpeechProcessingMetricsReporter;
import com.amazon.alexa.accessory.capabilities.speech.SpeechSession;
import com.amazon.alexa.accessory.capabilities.speech.SpeechSettings;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.RedactionUtil;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.sco.ScoPrioritizer;
import com.amazon.alexa.accessory.speech.AccessoryUserSpeechProvider;
import com.amazon.alexa.accessory.speechapi.AlexaConnection;
import com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener;
import com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider;
import com.amazon.alexa.accessory.speechapi.speech.UserSpeechProviderMetadata;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public final class AccessorySpeechProviderManager {
    private static final String AMA_SUPPORTED_DEVICES = "amaSupportedDevices";
    private static final String AMA_SUPPORTED_DEVICES_FILE = "AMASupportedDevices.json";
    private static final long BARGEIN_BACKOFF_TIME_MS = 750;
    private static final String DEVICE_TYPE_ID = "deviceTypeId";
    private static final String SERVER = "SERVER";
    private static final String SUPPORTED_INITIATION_TYPES = "supportedInitiationTypes";
    private static final String SUPPORTED_WAKE_WORDS = "supportedWakeWords";
    private static final String TAG = "AccessorySpeechProviderManager";
    private static final String TAP_TO_TALK = "TAP_TO_TALK";
    private static final String WAKE_WORD = "WAKE_WORD";
    private final AccessorySessionListener accessorySessionListener;
    private final AlexaConnection alexaConnection;
    private final ServiceConnectionListener connectionListener;
    private final Context context;
    private final Set<String> devicesSupportingExpectSpeech;
    private final FeatureChecker featureChecker;
    private final Map<String, UserSpeechProvider.SupportedInitiationType> initiationTypeMap;
    private String lastDialogOwner;
    private String lastDialogOwnerDeviceType = "NONE";
    private long lastTimeDialog;
    private final Object lock;
    private final ModeStatusChecker modeStatusChecker;
    private final ScoPrioritizer scoPrioritizer;
    private final SessionSupplier sessionSupplier;
    private final Map<String, SpeechProviderInfo> speechProvidersMap;
    private final AccessoryWakeWordCallback wakeWordCallback;

    /* renamed from: com.amazon.alexa.accessory.speech.AccessorySpeechProviderManager$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Common$SpeechInitiationType = new int[Common.SpeechInitiationType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Common$SpeechInitiationType[Common.SpeechInitiationType.TAP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Common$SpeechInitiationType[Common.SpeechInitiationType.WAKEWORD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Common$SpeechInitiationType[Common.SpeechInitiationType.SERVER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes6.dex */
    private final class AccessoryUserSpeechProviderWakeWordCallback implements AccessoryWakeWordCallback {
        private AccessoryUserSpeechProviderWakeWordCallback() {
        }

        @Override // com.amazon.alexa.accessory.avsclient.AccessoryWakeWordCallback
        public void pauseWakeWordDetection() {
            AccessorySpeechProviderManager.this.wakeWordCallback.pauseWakeWordDetection();
        }

        @Override // com.amazon.alexa.accessory.avsclient.AccessoryWakeWordCallback
        public void resumeWakeWordDetection() {
            AccessorySpeechProviderManager.this.wakeWordCallback.resumeWakeWordDetection();
        }

        /* synthetic */ AccessoryUserSpeechProviderWakeWordCallback(AccessorySpeechProviderManager accessorySpeechProviderManager, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* loaded from: classes6.dex */
    private final class FirmwareMissingSpeechInitiationInfo extends Exception {
        public FirmwareMissingSpeechInitiationInfo() {
            super("Firmware does not have SupportedSpeechInitiation info.");
        }
    }

    /* loaded from: classes6.dex */
    private class ServiceConnectionListener implements ConnectionListener {
        private ServiceConnectionListener() {
        }

        @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
        public void onConnected() {
            synchronized (AccessorySpeechProviderManager.this.lock) {
                for (SpeechProviderInfo speechProviderInfo : AccessorySpeechProviderManager.this.speechProvidersMap.values()) {
                    Logger.d("%s: Registering SpeechProviders again for accessories.", AccessorySpeechProviderManager.TAG);
                    AccessorySpeechProviderManager.this.alexaConnection.registerUserSpeechProvider(speechProviderInfo.getUserSpeechProvider(), speechProviderInfo.getSpeechProviderMetadata());
                    AccessorySpeechProviderManager.this.alexaConnection.registerStateListener(speechProviderInfo.userSpeechProvider);
                }
            }
        }

        @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
        public void onConnectingFailed(ConnectionListener.ConnectingFailedReason connectingFailedReason, String str) {
            Logger.d("AlexaServicesConnection onConnectingFailed reason: %s and message: %s", connectingFailedReason, str);
        }

        @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
        public void onDisconnected() {
            Logger.d("AlexaServicesConnection got disconnected.");
        }

        /* synthetic */ ServiceConnectionListener(AccessorySpeechProviderManager accessorySpeechProviderManager, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* loaded from: classes6.dex */
    private final class SpeechProviderAccessorySessionListener extends AccessorySessionListener {
        private SpeechProviderAccessorySessionListener() {
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionConnected(Accessory accessory) {
            Logger.d("%s: onAccessorySessionCreated for accessory %s", AccessorySpeechProviderManager.TAG, accessory);
            AccessorySession session = AccessorySpeechProviderManager.this.sessionSupplier.getSession(accessory);
            if (session == null) {
                return;
            }
            AccessorySpeechProviderManager.this.registerUserSpeechProvider(session);
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionReleased(Accessory accessory) {
            Logger.d("%s: onAccessorySessionReleased for accessory %s", AccessorySpeechProviderManager.TAG, accessory);
            AccessorySpeechProviderManager.this.deregisterUserSpeechProvider(accessory);
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionTransportChanged(Accessory accessory, AccessoryTransport.Type type, Accessory accessory2, AccessoryTransport.Type type2) {
            Logger.d("%s: onAccessorySessionTransportChanged for oldAccessory: %s to new Accessory: %s", AccessorySpeechProviderManager.TAG, accessory, accessory2);
            synchronized (AccessorySpeechProviderManager.this.lock) {
                if (!accessory.getAddress().equals(accessory2.getAddress())) {
                    if (AccessorySpeechProviderManager.this.speechProvidersMap.containsKey(accessory.getAddress())) {
                        Logger.d("%s: replacing Mac address for old accessory %s", AccessorySpeechProviderManager.TAG, accessory);
                        AccessorySpeechProviderManager.this.speechProvidersMap.put(accessory2.getAddress(), (SpeechProviderInfo) AccessorySpeechProviderManager.this.speechProvidersMap.remove(accessory.getAddress()));
                    } else {
                        Logger.d("%s: No SpeechProviderInfo available for oldAccessory: %s", AccessorySpeechProviderManager.TAG, accessory);
                    }
                    return;
                }
                Logger.d("%s: Mac address is same on Transport Upgrade. Returning.", AccessorySpeechProviderManager.TAG);
            }
        }

        /* synthetic */ SpeechProviderAccessorySessionListener(AccessorySpeechProviderManager accessorySpeechProviderManager, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static final class SpeechProviderInfo {
        private final UserSpeechProviderMetadata speechProviderMetadata;
        private final AccessoryUserSpeechProvider userSpeechProvider;

        public SpeechProviderInfo(AccessoryUserSpeechProvider accessoryUserSpeechProvider, UserSpeechProviderMetadata userSpeechProviderMetadata) {
            Preconditions.notNull(accessoryUserSpeechProvider, "userSpeechProvider");
            Preconditions.notNull(userSpeechProviderMetadata, "speechProviderMetadata");
            this.userSpeechProvider = accessoryUserSpeechProvider;
            this.speechProviderMetadata = userSpeechProviderMetadata;
        }

        public UserSpeechProviderMetadata getSpeechProviderMetadata() {
            return this.speechProviderMetadata;
        }

        public AccessoryUserSpeechProvider getUserSpeechProvider() {
            return this.userSpeechProvider;
        }
    }

    public AccessorySpeechProviderManager(AlexaConnection alexaConnection, ModeStatusChecker modeStatusChecker, ScoPrioritizer scoPrioritizer, SessionSupplier sessionSupplier, Context context, AccessoryWakeWordCallback accessoryWakeWordCallback, FeatureChecker featureChecker) {
        Preconditions.notNull(alexaConnection, "alexaConnection");
        Preconditions.notNull(modeStatusChecker, "modeStatusChecker");
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(context, "context");
        Preconditions.notNull(accessoryWakeWordCallback, "wakeWordCallback");
        Preconditions.notNull(featureChecker, "featureChecker");
        this.alexaConnection = alexaConnection;
        this.modeStatusChecker = modeStatusChecker;
        this.scoPrioritizer = scoPrioritizer;
        this.sessionSupplier = sessionSupplier;
        this.context = context;
        this.wakeWordCallback = accessoryWakeWordCallback;
        this.featureChecker = featureChecker;
        this.speechProvidersMap = new HashMap();
        this.accessorySessionListener = new SpeechProviderAccessorySessionListener(this, null);
        this.sessionSupplier.addSessionListener(this.accessorySessionListener);
        HashMap hashMap = new HashMap();
        hashMap.put(WAKE_WORD, UserSpeechProvider.SupportedInitiationType.WAKE_WORD);
        hashMap.put(TAP_TO_TALK, UserSpeechProvider.SupportedInitiationType.TAP_TO_TALK);
        hashMap.put(SERVER, UserSpeechProvider.SupportedInitiationType.SERVER);
        this.initiationTypeMap = Collections.unmodifiableMap(hashMap);
        this.devicesSupportingExpectSpeech = new HashSet(Arrays.asList("A2QDHDQIWC2LTG", "A31PMVIWCRNTX2", "A3HVREY4JWAZ6K", "A15QWUTQ6FSMYX", "A303PJF6ISQ7IC"));
        this.connectionListener = new ServiceConnectionListener(this, null);
        this.alexaConnection.registerConnectionListener(this.connectionListener);
        this.lock = new Object();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deregisterUserSpeechProvider(Accessory accessory) {
        SpeechProviderInfo remove;
        synchronized (this.lock) {
            remove = this.speechProvidersMap.remove(accessory.getAddress());
        }
        if (remove == null || !this.alexaConnection.isConnected()) {
            return;
        }
        this.alexaConnection.deRegisterUserSpeechProvider(remove.getUserSpeechProvider());
        this.alexaConnection.deregisterStateListener(remove.getUserSpeechProvider());
    }

    private Single<String> getAccessoryDeviceInfo(AccessorySession accessorySession) {
        Preconditions.notNull(accessorySession, "accessorySession");
        return accessorySession.getDeviceRepositoryV2().queryDeviceInformationSet().firstOrError().map($$Lambda$AccessorySpeechProviderManager$cTfjK8sR0LPkYcRxFUZL5X8Atis.INSTANCE);
    }

    private Single<UserSpeechProviderMetadata> getAlexaUserSpeechProviderMetadata(final AccessorySession accessorySession) {
        return getAlexaUserSpeechProviderMetadataFromFirmware(accessorySession).onErrorResumeNext(new Function() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessorySpeechProviderManager$6rPjP8fIHjhFmUVyj7tsFQjW_n4
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AccessorySpeechProviderManager.this.lambda$getAlexaUserSpeechProviderMetadata$2$AccessorySpeechProviderManager(accessorySession, (Throwable) obj);
            }
        });
    }

    private Single<UserSpeechProviderMetadata> getAlexaUserSpeechProviderMetadataFromFirmware(AccessorySession accessorySession) {
        Preconditions.notNull(accessorySession, "accessorySession");
        return accessorySession.getDeviceRepositoryV2().queryDeviceInformationSet().firstOrError().map(new Function() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessorySpeechProviderManager$LJZZSPWg4rA4s_QSUiIi4-fmLdI
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AccessorySpeechProviderManager.this.lambda$getAlexaUserSpeechProviderMetadataFromFirmware$4$AccessorySpeechProviderManager((Set) obj);
            }
        });
    }

    private Single<UserSpeechProviderMetadata> getAlexaUserSpeechProviderMetadataFromPersistence(AccessorySession accessorySession) {
        return getAccessoryDeviceInfo(accessorySession).map(new Function() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessorySpeechProviderManager$ekta2ZK23ggZmjh3V5ejZSawWyo
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AccessorySpeechProviderManager.this.lambda$getAlexaUserSpeechProviderMetadataFromPersistence$5$AccessorySpeechProviderManager((String) obj);
            }
        });
    }

    private JSONArray getAmaSupportedDevicesJson() {
        synchronized (this.lock) {
            try {
                InputStream open = this.context.getAssets().open(AMA_SUPPORTED_DEVICES_FILE);
                try {
                    byte[] bArr = new byte[open.available()];
                    open.read(bArr);
                    open.close();
                    JSONArray jSONArray = new JSONObject(new String(bArr, StandardCharsets.UTF_8)).getJSONArray(AMA_SUPPORTED_DEVICES);
                    open.close();
                    return jSONArray;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (open != null) {
                            try {
                                open.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        }
                        throw th2;
                    }
                }
            } catch (IOException | JSONException e) {
                recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.AMA_DEVICES_JSON_PARSING_ERROR, null);
                Logger.e("%s: Error while initializeAmaSupportedDevicesJson ", TAG, e);
                return null;
            }
        }
    }

    private UserSpeechProviderMetadata getDefaultSpeechProviderMetadata() {
        return UserSpeechProviderMetadata.create(Collections.singleton(this.initiationTypeMap.get(TAP_TO_TALK)), Collections.emptySet(), UserSpeechProvider.Scope.EXTERNAL_DEVICE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$registerUserSpeechProvider$1(Accessory accessory, Throwable th) throws Throwable {
        Logger.d("%s: ERROR: Error while registering AccessoryUserSpeechProvider for accessory: %s", TAG, accessory);
        Logger.e("%s: Error while registering AccessoryUserSpeechProvider for accessory: %s", TAG, RedactionUtil.redact(accessory));
        recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.ERROR_REGISTERING_SPEECH_PROVIDER, null);
    }

    private static void recordCounterMetric(SpeechProcessingMetricsReporter.CounterType counterType, String str) {
        SpeechProcessingMetricsReporter.reportCounter(counterType, str, 1.0d, null);
    }

    private static void recordFailureMetric(SpeechProcessingMetricsReporter.FailureType failureType, String str) {
        recordFailureMetric(failureType, str, null, null);
    }

    private static void recordTimerMetric(SpeechProcessingMetricsReporter.TimerType timerType, long j) {
        SpeechProcessingMetricsReporter.reportTimer(timerType, j, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"CheckResult"})
    public void registerUserSpeechProvider(final AccessorySession accessorySession) {
        Preconditions.notNull(accessorySession, "accessorySession");
        final long currentTimeMillis = System.currentTimeMillis();
        final Accessory accessory = accessorySession.getAccessory();
        getAlexaUserSpeechProviderMetadata(accessorySession).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessorySpeechProviderManager$4DvXEHQdIHpm29Wl_WXcV6vp8xQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessorySpeechProviderManager.this.lambda$registerUserSpeechProvider$0$AccessorySpeechProviderManager(accessorySession, accessory, currentTimeMillis, (UserSpeechProviderMetadata) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessorySpeechProviderManager$HDVGBHWSTOPR1YYhkwU--d8rbww
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessorySpeechProviderManager.lambda$registerUserSpeechProvider$1(Accessory.this, (Throwable) obj);
            }
        });
    }

    @VisibleForTesting
    Map<String, SpeechProviderInfo> getSpeechProvidersMap() {
        return this.speechProvidersMap;
    }

    public /* synthetic */ SingleSource lambda$getAlexaUserSpeechProviderMetadata$2$AccessorySpeechProviderManager(AccessorySession accessorySession, Throwable th) throws Throwable {
        Logger.d("%s: %s Fetching it from %s file", TAG, th, AMA_SUPPORTED_DEVICES_FILE);
        return getAlexaUserSpeechProviderMetadataFromPersistence(accessorySession);
    }

    public /* synthetic */ UserSpeechProviderMetadata lambda$getAlexaUserSpeechProviderMetadataFromFirmware$4$AccessorySpeechProviderManager(Set set) throws Throwable {
        Device.DeviceInformation deviceInformation = (Device.DeviceInformation) set.iterator().next();
        List<Common.SpeechInitiationType> supportedSpeechInitiationsList = deviceInformation.getSupportedSpeechInitiationsList();
        if (supportedSpeechInitiationsList != null && !supportedSpeechInitiationsList.isEmpty()) {
            HashSet hashSet = new HashSet();
            for (Common.SpeechInitiationType speechInitiationType : supportedSpeechInitiationsList) {
                int ordinal = speechInitiationType.ordinal();
                if (ordinal == 1) {
                    hashSet.add(UserSpeechProvider.SupportedInitiationType.TAP_TO_TALK);
                } else if (ordinal == 2) {
                    hashSet.add(UserSpeechProvider.SupportedInitiationType.WAKE_WORD);
                } else if (ordinal != 3) {
                    Logger.d("%s received speechInitiationType: %s is not supported by currently.", TAG, speechInitiationType);
                } else {
                    hashSet.add(UserSpeechProvider.SupportedInitiationType.SERVER);
                }
            }
            if (this.devicesSupportingExpectSpeech.contains(deviceInformation.getDeviceType())) {
                hashSet.add(UserSpeechProvider.SupportedInitiationType.SERVER);
            }
            return UserSpeechProviderMetadata.create(hashSet, new HashSet(deviceInformation.getSupportedWakewordsList()), UserSpeechProvider.Scope.EXTERNAL_DEVICE);
        }
        recordCounterMetric(SpeechProcessingMetricsReporter.CounterType.FIRMWARE_MISSING_SPEECH_INITIATION_TYPE_INFO, deviceInformation.getDeviceType());
        throw new FirmwareMissingSpeechInitiationInfo();
    }

    public /* synthetic */ UserSpeechProviderMetadata lambda$getAlexaUserSpeechProviderMetadataFromPersistence$5$AccessorySpeechProviderManager(String str) throws Throwable {
        JSONArray amaSupportedDevicesJson = getAmaSupportedDevicesJson();
        if (amaSupportedDevicesJson == null) {
            return getDefaultSpeechProviderMetadata();
        }
        int length = amaSupportedDevicesJson.length();
        JSONObject jSONObject = null;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            } else if (amaSupportedDevicesJson.getJSONObject(i).getString(DEVICE_TYPE_ID).equals(str)) {
                jSONObject = amaSupportedDevicesJson.getJSONObject(i);
                break;
            } else {
                i++;
            }
        }
        if (jSONObject == null) {
            return getDefaultSpeechProviderMetadata();
        }
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray(SUPPORTED_INITIATION_TYPES);
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            hashSet.add(this.initiationTypeMap.get(jSONArray.getString(i2)));
        }
        HashSet hashSet2 = new HashSet();
        JSONArray jSONArray2 = jSONObject.getJSONArray(SUPPORTED_WAKE_WORDS);
        for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
            hashSet2.add(jSONArray2.getString(i3));
        }
        return UserSpeechProviderMetadata.create(hashSet, hashSet2, UserSpeechProvider.Scope.EXTERNAL_DEVICE);
    }

    public /* synthetic */ void lambda$registerUserSpeechProvider$0$AccessorySpeechProviderManager(AccessorySession accessorySession, Accessory accessory, long j, UserSpeechProviderMetadata userSpeechProviderMetadata) throws Throwable {
        if (!accessorySession.isConnected()) {
            Logger.d("%s: AccessoryUserSpeechProvider tried to register but session got released in between.", TAG);
            recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.SESSION_RELEASED_IN_SPEECH_MANAGER, null);
            return;
        }
        AccessoryUserSpeechProvider accessoryUserSpeechProvider = new AccessoryUserSpeechProvider(this.modeStatusChecker, this.scoPrioritizer, this.alexaConnection, new AccessoryUserSpeechProviderWakeWordCallback(this, null), this.featureChecker, accessorySession, this.sessionSupplier, new AccessoryAudioPlaybackStatusListener(accessorySession));
        Logger.d("%s: AccessoryUserSpeechProvider registered for accessory: %s", TAG, accessory);
        synchronized (this.lock) {
            this.speechProvidersMap.put(accessory.getAddress(), new SpeechProviderInfo(accessoryUserSpeechProvider, userSpeechProviderMetadata));
        }
        if (!this.alexaConnection.isConnected()) {
            recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.ALEXA_CONNECTION_DISCONNECT_IN_SPEECH_MANAGER, null);
            this.alexaConnection.connect();
        } else {
            this.alexaConnection.registerUserSpeechProvider(accessoryUserSpeechProvider, userSpeechProviderMetadata);
            this.alexaConnection.registerStateListener(accessoryUserSpeechProvider);
        }
        recordTimerMetric(SpeechProcessingMetricsReporter.TimerType.REGISTER_USER_SPEECH_PROVIDER, System.currentTimeMillis() - j);
    }

    public void release() {
        synchronized (this.lock) {
            for (SpeechProviderInfo speechProviderInfo : this.speechProvidersMap.values()) {
                speechProviderInfo.getUserSpeechProvider().release();
            }
        }
    }

    @Nullable
    public SpeechSession requestSpeechDialog(SpeechSettings speechSettings, AccessoryUserSpeechProvider.Callback callback) {
        SpeechProviderInfo speechProviderInfo;
        Preconditions.mainThread();
        String identifier = speechSettings.getAccessoryIdentifierProvider().getIdentifier();
        synchronized (this.lock) {
            speechProviderInfo = this.speechProvidersMap.get(identifier);
        }
        if (speechProviderInfo == null) {
            Logger.d("%s: No speech provider available for accessory: %s", TAG, identifier);
            recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.NO_SPEECH_PROVIDER_AVAILABLE, speechSettings.getDeviceType());
            return null;
        }
        Logger.d("%s request speech dialog requested for accessory %s", TAG, identifier);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - BARGEIN_BACKOFF_TIME_MS <= this.lastTimeDialog && !identifier.equals(this.lastDialogOwner)) {
            Logger.d("%s: Speech barge-in for accessory denied for %s, another accessory is already doing utterance", TAG, identifier);
            recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.SPEECH_BARGING_BLOCKED_BY_ANOTHER_ACCESSORY, speechSettings.getDeviceType(), null, this.lastDialogOwnerDeviceType);
            recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.SPEECH_BARGING_FROM_ANOTHER_ACCESSORY, speechSettings.getDeviceType());
            return null;
        }
        this.lastTimeDialog = currentTimeMillis;
        this.lastDialogOwner = identifier;
        this.lastDialogOwnerDeviceType = speechSettings.getDeviceType();
        callback.onDialogRequestedForSpeechProvider(speechProviderInfo.getUserSpeechProvider());
        Logger.d("%s: speech requested for accessory: %s", TAG, identifier);
        recordCounterMetric(SpeechProcessingMetricsReporter.CounterType.SPEECH_REQUESTED_SUCCESS, speechSettings.getDeviceType());
        return speechProviderInfo.getUserSpeechProvider().recognizeSpeech(speechSettings, callback);
    }

    private static void recordFailureMetric(SpeechProcessingMetricsReporter.FailureType failureType, String str, Integer num, Object obj) {
        SpeechProcessingMetricsReporter.reportFailure(failureType, str, num, obj);
    }
}
