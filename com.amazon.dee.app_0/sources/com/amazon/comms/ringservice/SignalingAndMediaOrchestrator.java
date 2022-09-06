package com.amazon.comms.ringservice;

import android.content.Context;
import android.media.AudioManager;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.SystemClock;
import android.text.TextUtils;
import com.amazon.comms.calling.instrumentation.EventTracerConfig;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.calling.service.DeviceCallingServiceParams;
import com.amazon.comms.calling.service.DynamicAcousticParams;
import com.amazon.comms.calling.service.ErrorCode;
import com.amazon.comms.calling.service.HangupReason;
import com.amazon.comms.calling.service.HistoricalCall;
import com.amazon.comms.calling.service.VideoConfiguration;
import com.amazon.comms.calling.sipclient.AuthenticationInfo;
import com.amazon.comms.calling.sipclient.MediaRelayInfo;
import com.amazon.comms.calling.sipclient.RegistrarConfiguration;
import com.amazon.comms.calling.sipclient.SipHeaders;
import com.amazon.comms.calling.sipclient.SipStatusCode;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.amazon.comms.instrumentation.Clocks;
import com.amazon.comms.instrumentation.ClocksImpl;
import com.amazon.comms.instrumentation.EventTracer;
import com.amazon.comms.instrumentation.EventTracerFactory;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.log.LogLevel;
import com.amazon.comms.ringservice.Signaling;
import com.amazon.comms.ringservice.authtoken.AlarmAuthTokenManager;
import com.amazon.comms.ringservice.authtoken.AuthTokenManager;
import com.amazon.comms.ringservice.authtoken.AuthTokenManagerListener;
import com.amazon.comms.ringservice.authtoken.HandlerAuthTokenManager;
import com.amazon.comms.ringservice.orchestrator.AuthTokenConfig;
import com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator;
import com.amazon.comms.ringservice.pjsip.PjsipSignaling;
import com.amazon.comms.ringservice.util.Capability;
import com.amazon.comms.ringservice.util.DeviceModel;
import com.amazon.comms.ringservice.util.NameServersResult;
import com.amazon.comms.ringservice.util.NetworkConfigs;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.EvictingQueue;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes12.dex */
class SignalingAndMediaOrchestrator implements SignalAndMediaOrchestrator, AuthTokenManagerListener, SignalingListener, CallFinishedListener {
    private static final String A2A_CALL_PROVIDER = "A2A";
    private static final String CALLTOKEN_INVITE_RECEIVED_METRICS_COUNTER = "ReceivedInviteForCallToken";
    private static final String CALLTOKEN_METRICS_SOURCE = "RingService";
    private static final String CALLTOKEN_RECEIVED_METRICS_COUNTER = "ReceivedCallToken";
    private static final String CALLTOKEN_RECEIVED_WHEN_REGISTERED_METRICS_COUNTER = "ReceivedCallTokenWhenRegistered";
    private static final String DEFAULT_DNS_SERVER = "8.8.8.8";
    private static final int MAX_HISTORICAL_CALLS = 8;
    private static final int MAX_WAIT_TIME_ON_ORCHESTRATOR_GATE_MS = 7000;
    private static final int MSG_DELAY_TIME_MS = 3000;
    private static final int MSG_SMALL_DELAY_TIME_MS = 300;
    static final String NO_CALL_ID = "NO_CALL_ID";
    private CallImplInternal activeCall;
    private WeakReference<Context> applicationContext;
    private AudioManager audioManager;
    private AuthTokenManager authTokenManager;
    private int callCount;
    private final ConcurrentHashMap<String, CallImpl> callMap;
    private final SignalAndMediaOrchestrator.Callbacks callbackListener;
    private DeviceCallingServiceParams deviceCallingServiceParams;
    private long elapsedRealTimeAtCallTokenReceive;
    private EventTracerFactory eventTracerFactory;
    private final EvictingQueue<HistoricalCall> historicalCalls;
    private PjsipSignaling.InitParams initParams;
    private final AtomicBoolean instanceShutdown;
    private String lastReceivedCallTokenCallId;
    private boolean localCameraDetected;
    private MediaManager mediaManager;
    private MetricsSession metricsSession;
    private long millisSinceEpochAtCallTokenReceive;
    private CallImplInternal mostRecentCall;
    private NameServersResult nameServersResult;
    private boolean networkDisconnected;
    private OrchestratorHandler orchestratorMsgHandler;
    private Handler orchestratorToAppServiceHandler;
    private final Map<String, CallImpl> pendingCallRemovedNotifyCleanup;
    private String pendingPresenceStatusForTx;
    private boolean pendingSignalingExit;
    private boolean performingSignalingConnect;
    private Map<String, Integer> persistentMediaConstraintOverrides;
    private RegistrarConfiguration registrarConfiguration;
    private SignalAndMediaOrchestrator.RegistrationRequestStatus registrationRequestStatus;
    private boolean renderRemoteVideoSupported;
    private String rootCACertFilePath;
    private boolean sendPresence;
    private Signaling signaling;
    private boolean systemCameraEnabled;
    private boolean systemMediaEnabled;
    private String userAgentInfoTemplate;
    private final ConditionVariable waitForOrchestratorGate;
    private static final CommsLogger log = CommsLogger.getLogger(SignalingAndMediaOrchestrator.class);
    private static SignalingAndMediaOrchestrator orchestratorInstance = null;
    private static AtomicBoolean nativeLibsLoaded = new AtomicBoolean(false);
    private static final Runnable loadNativeLibRunnable = new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.1
        @Override // java.lang.Runnable
        public void run() {
            SignalingAndMediaOrchestrator.loadNativeLib();
        }
    };
    private final Clocks clocks = new ClocksImpl();
    private DeviceCallingService.State orchestratorState = DeviceCallingService.State.Uninitialized;
    private final HandlerThread orchestratorThread = new HandlerThread(SignalingAndMediaOrchestrator.class.getSimpleName());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class AuthTokenPayload {
        final AuthTokenConfig authTokenConfig;
        final long authTokenExpireTime;

        public AuthTokenPayload(AuthTokenConfig authTokenConfig, long j) {
            this.authTokenConfig = authTokenConfig;
            this.authTokenExpireTime = j;
        }

        public AuthTokenConfig getAuthTokenConfig() {
            return this.authTokenConfig;
        }

        public long getAuthTokenExpireTime() {
            return this.authTokenExpireTime;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class ConfigureRegistrarPayload {
        final long authTokenExpireTime;
        final RegistrarConfiguration registrarConfig;

        public ConfigureRegistrarPayload(RegistrarConfiguration registrarConfiguration, long j) {
            this.registrarConfig = registrarConfiguration;
            this.authTokenExpireTime = j;
        }

        public long getAuthTokenExpireTime() {
            return this.authTokenExpireTime;
        }

        public RegistrarConfiguration getRegistrarConfig() {
            return this.registrarConfig;
        }
    }

    /* loaded from: classes12.dex */
    static class MSG_TYPE {
        public static final int MSG_CONFIGURE_COMMS_CMD = 202;
        public static final int MSG_NETWORK_CONNECTED = 201;
        public static final int MSG_NETWORK_DISCONNECTED = 200;
        public static final int MSG_UPDATE_AUTH_TOKEN = 203;

        MSG_TYPE() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class OrchestratorHandler extends Handler {
        OrchestratorHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            SignalingAndMediaOrchestrator.this.handleMessageForOrchestrator(message);
        }
    }

    private SignalingAndMediaOrchestrator(Handler handler, SignalAndMediaOrchestrator.Callbacks callbacks, boolean z, boolean z2) {
        this.orchestratorToAppServiceHandler = handler;
        this.callbackListener = callbacks;
        this.orchestratorThread.start();
        this.orchestratorMsgHandler = new OrchestratorHandler(this.orchestratorThread.getLooper());
        this.instanceShutdown = new AtomicBoolean(false);
        this.waitForOrchestratorGate = new ConditionVariable(true);
        this.networkDisconnected = false;
        this.rootCACertFilePath = null;
        this.callMap = new ConcurrentHashMap<>(2);
        this.systemMediaEnabled = z;
        this.systemCameraEnabled = z2;
        this.historicalCalls = EvictingQueue.create(8);
        this.pendingCallRemovedNotifyCleanup = new HashMap(2);
    }

    private void addCallInternal(String str, CallImpl callImpl, CallImplInternal callImplInternal, Sdp sdp) {
        PowerManager powerManager;
        callImplInternal.setCallForSession(callImpl);
        synchronized (this) {
            this.activeCall = callImplInternal;
            this.mostRecentCall = callImplInternal;
            this.callMap.put(str, callImpl);
        }
        notifyServiceToConfigureCall(callImpl);
        Context context = this.applicationContext.get();
        if (context == null) {
            log.w("addCallInternal, could not acquire wakelock as context is null!");
        } else if (this.deviceCallingServiceParams.getAvsDeviceFacade() != null && !this.deviceCallingServiceParams.getAvsDeviceFacade().isDoNotDisturbOn() && (powerManager = (PowerManager) context.getSystemService("power")) != null) {
            PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(805306394, "DeviceCallingServiceImpl:WakelockTag");
            newWakeLock.acquire(5000L);
            newWakeLock.release();
        }
        MetricsSession metricsSession = this.metricsSession;
        int i = this.callCount + 1;
        this.callCount = i;
        metricsSession.recordCount(CALLTOKEN_METRICS_SOURCE, "CallsSinceRingServiceStart", i, str);
        notifySericeCallAdded(callImpl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callFinishedOrCallRemovedNetworkCleanup(Call call) {
        this.pendingCallRemovedNotifyCleanup.remove(call.getCallId());
        if (!this.pendingSignalingExit || isAnyCallPresent()) {
            return;
        }
        log.i("Attempting pending close of transport and delete of pjsip account");
        performSignalingExit();
        this.pendingSignalingExit = false;
        if (this.networkDisconnected) {
            return;
        }
        this.orchestratorMsgHandler.removeMessages(201);
        this.orchestratorMsgHandler.sendEmptyMessage(201);
    }

    private boolean callTokenInRegHeaders() {
        if (this.registrarConfiguration.getHeaders() != null) {
            for (String str : this.registrarConfiguration.getHeaders().keySet()) {
                if (str.equals("X-Alexa-CallToken")) {
                    log.i("CallToken headers found");
                    return true;
                }
            }
        }
        return false;
    }

    private void closeFileStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
                log.v("Couldn't close stream.  Ignorable error.");
            }
        }
    }

    private boolean enableLocalVideoAtCallInitiation() {
        if (this.deviceCallingServiceParams.getAvsDeviceFacade() == null) {
            return true;
        }
        boolean enableLocalVideoAtCallInitiation = this.deviceCallingServiceParams.getAvsDeviceFacade().enableLocalVideoAtCallInitiation();
        return this.deviceCallingServiceParams.getAvsDeviceFacade().isExternalCameraSupported() ? enableLocalVideoAtCallInitiation && this.deviceCallingServiceParams.getAvsDeviceFacade().isExternalCameraPresent() : enableLocalVideoAtCallInitiation;
    }

    private String generateRootCACertFileLocation() {
        Closeable closeable;
        Throwable th;
        Exception e;
        Context context = this.applicationContext.get();
        String str = "";
        if (context == null) {
            log.e("Cannot generateRootCACertFileLocation, appContext is null!!");
            return str;
        }
        InputStream openRawResource = context.getResources().openRawResource(R.raw.mozilla_rootca_certs);
        try {
            int available = openRawResource.available();
            log.d("Size of cert resource file= " + available);
            File file = new File(context.getFilesDir(), "rootca_list.crt");
            boolean z = true;
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e2) {
                    log.e("Could not make the output cert file!", e2);
                    z = false;
                }
            }
            if (z) {
                byte[] bArr = new byte[Math.max(131072, Math.min(available, 524288))];
                try {
                    closeable = new FileOutputStream(file);
                    while (true) {
                        try {
                            try {
                                int read = openRawResource.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                closeable.write(bArr, 0, read);
                            } catch (Exception e3) {
                                e = e3;
                                log.e("Could not make output cert file!", e);
                                closeFileStream(closeable);
                                log.i("Root ca cert file path= " + str);
                                closeFileStream(openRawResource);
                                return str;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            closeFileStream(closeable);
                            throw th;
                        }
                    }
                    closeable.flush();
                    log.i("Output cert file written. Buffer= " + bArr.length);
                    str = file.getAbsolutePath();
                } catch (Exception e4) {
                    closeable = null;
                    e = e4;
                } catch (Throwable th3) {
                    closeable = null;
                    th = th3;
                    closeFileStream(closeable);
                    throw th;
                }
                closeFileStream(closeable);
            }
            log.i("Root ca cert file path= " + str);
            closeFileStream(openRawResource);
            return str;
        } catch (IOException e5) {
            log.e("Unable to read the resource file size! ", e5);
            closeFileStream(openRawResource);
            return str;
        }
    }

    private synchronized CallImplInternal getActiveCallImplInternal() {
        return this.activeCall;
    }

    private synchronized String getCallId() {
        return this.callMap.isEmpty() ? NO_CALL_ID : this.activeCall.getCallId();
    }

    private synchronized String getCallProvider() {
        return this.callMap.isEmpty() ? null : this.activeCall.getProvider();
    }

    private NameServersResult getNameServers() {
        this.nameServersResult = NetworkConfigs.getNameServers(this.applicationContext.get(), DEFAULT_DNS_SERVER);
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Adding dns servers in endpoint config: ");
        outline107.append(this.nameServersResult.getNameServers());
        commsLogger.ds(outline107.toString());
        CommsLogger commsLogger2 = log;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("There are and are only IPv6 name servers present: ");
        outline1072.append(this.nameServersResult.isOnlyIPv6ServersPresent());
        commsLogger2.ds(outline1072.toString());
        CommsLogger commsLogger3 = log;
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Default name server is applied: ");
        outline1073.append(this.nameServersResult.isDefaultNameServerApplied());
        commsLogger3.ds(outline1073.toString());
        return this.nameServersResult;
    }

    private String getSafeCallId(String str) {
        return TextUtils.isEmpty(str) ? NO_CALL_ID : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMessageForOrchestrator(Message message) {
        switch (message.what) {
            case 200:
                processConnectivityChange(false);
                return;
            case 201:
                processConnectivityChange(true);
                return;
            case 202:
                configureRegistrarInternal((ConfigureRegistrarPayload) message.obj);
                return;
            case 203:
                handleUpdateAuthTokenInternal((AuthTokenPayload) message.obj);
                return;
            default:
                CommsLogger commsLogger = log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Message UNKNOWN. Not Handled: ");
                outline107.append(message.what);
                commsLogger.e(outline107.toString());
                return;
        }
    }

    private void handleUpdateAuthTokenInternal(AuthTokenPayload authTokenPayload) {
        log.i("handleUpdateAuthTokenInternal");
        if (this.registrarConfiguration == null) {
            synchronized (this.orchestratorMsgHandler) {
                if (this.orchestratorMsgHandler.hasMessages(202) && !this.orchestratorMsgHandler.hasMessages(203)) {
                    log.i("Delaying updateAuthToken: 1) no registrar configured; 2) having pending CC");
                    this.orchestratorMsgHandler.sendMessageDelayed(this.orchestratorMsgHandler.obtainMessage(203, authTokenPayload), 300L);
                    log.i("updateAuthToken has been pushed ino back of the queue");
                    return;
                }
                log.w("Dropping updateAuthToken: 1) no registrar configured; 2) having no pending CC or having new tokens");
                return;
            }
        }
        AuthTokenConfig authTokenConfig = authTokenPayload.getAuthTokenConfig();
        int authTokenExpireTime = (int) (authTokenPayload.getAuthTokenExpireTime() - (SystemClock.elapsedRealtime() / 1000));
        this.registrarConfiguration.setAuthTokenIntervalInSecs(authTokenExpireTime);
        this.registrarConfiguration.setAuthToken(authTokenConfig.getAuthToken());
        this.authTokenManager.setAuthTokenAlarm(authTokenExpireTime, authTokenPayload.getAuthTokenExpireTime());
        SipHeaders sipHeaders = new SipHeaders(this.registrarConfiguration.getHeaders());
        if (!Strings.isNullOrEmpty(authTokenConfig.getAuthToken())) {
            sipHeaders.put(SipHeaders.SIP_HEADER_AUTH_TOKEN, authTokenConfig.getAuthToken());
        }
        boolean updateConfigurationParams = this.signaling.updateConfigurationParams(new PjsipSignaling.ConfigurationParams(sipHeaders), authTokenConfig.isForceRegister());
        if (!authTokenConfig.isForceRegister()) {
            return;
        }
        if (updateConfigurationParams) {
            this.registrationRequestStatus = SignalAndMediaOrchestrator.RegistrationRequestStatus.InProgress;
        } else {
            log.i("ConfigureRegistrarInternal, Forced registration attempt failed.");
        }
    }

    private void hangupAllCalls() {
        log.i("Calling hangup on all calls");
        for (Map.Entry<String, CallImpl> entry : this.callMap.entrySet()) {
            entry.getValue().hangup(Call.HangupRequest.Everywhere);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hangupAllInternal() {
        hangupAllCalls();
        this.mediaManager.disposeCachedMediaSession();
    }

    private boolean havePendingConnectivityMsgs() {
        return this.orchestratorMsgHandler.hasMessages(201) || this.orchestratorMsgHandler.hasMessages(200);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeInternal(Context context, EventTracerFactory eventTracerFactory, DeviceCallingServiceParams deviceCallingServiceParams, MetricsSession metricsSession, boolean z, String str) {
        boolean z2;
        log.i("Initializing SignalingAndMediaOrchestrator!");
        this.deviceCallingServiceParams = deviceCallingServiceParams;
        this.applicationContext = new WeakReference<>(context);
        this.rootCACertFilePath = generateRootCACertFileLocation();
        if (Strings.isNullOrEmpty(this.rootCACertFilePath)) {
            ErrorCode errorCode = ErrorCode.Unknown;
            reportError(null, null, errorCode, errorCode.getValue(), "Could not generate rootCACertFilePath");
            return;
        }
        this.eventTracerFactory = eventTracerFactory;
        this.metricsSession = metricsSession;
        if (this.deviceCallingServiceParams.isUseAlarmManagerAuthTokenManager()) {
            log.i("Will use Alarm based AuthToken manager!");
            this.authTokenManager = new AlarmAuthTokenManager(context);
        } else {
            log.i("Will use handler based AuthToken manager!");
            this.authTokenManager = new HandlerAuthTokenManager(this, this.orchestratorMsgHandler);
        }
        this.localCameraDetected = z;
        this.renderRemoteVideoSupported = this.deviceCallingServiceParams.isRenderRemoteVideoSupported();
        if (this.mediaManager == null) {
            try {
                OrchestratorHandler orchestratorHandler = this.orchestratorMsgHandler;
                MetricsSession metricsSession2 = this.metricsSession;
                DeviceCallingServiceParams deviceCallingServiceParams2 = this.deviceCallingServiceParams;
                if (!this.localCameraDetected && !this.renderRemoteVideoSupported) {
                    z2 = false;
                    this.mediaManager = MediaManagerFactory.createMediaManager(context, orchestratorHandler, metricsSession2, deviceCallingServiceParams2, z2);
                }
                z2 = true;
                this.mediaManager = MediaManagerFactory.createMediaManager(context, orchestratorHandler, metricsSession2, deviceCallingServiceParams2, z2);
            } catch (Exception e) {
                ErrorCode errorCode2 = ErrorCode.Unknown;
                reportError(null, null, errorCode2, errorCode2.getValue(), GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Could not create mediaManager ")));
                return;
            }
        }
        this.userAgentInfoTemplate = str;
        this.signaling = new PjsipSignaling(this, this.orchestratorMsgHandler);
        this.nameServersResult = getNameServers();
        this.initParams = new PjsipSignaling.InitParams(this.rootCACertFilePath, this.nameServersResult.getNameServers(), this.userAgentInfoTemplate, this.eventTracerFactory, this.deviceCallingServiceParams.getRegistrationHeaders(), this.deviceCallingServiceParams.isPresencePublishCapable());
        this.audioManager = (AudioManager) context.getSystemService("audio");
        setOrchestratorState(DeviceCallingService.State.Initialized);
        this.registrationRequestStatus = SignalAndMediaOrchestrator.RegistrationRequestStatus.None;
    }

    private boolean isAnyCallPresent() {
        boolean z = true;
        boolean z2 = !this.pendingCallRemovedNotifyCleanup.isEmpty();
        synchronized (this) {
            if (this.callMap.isEmpty() && !z2) {
                z = false;
            }
        }
        return z;
    }

    private boolean isDropInBlocked() {
        if (this.deviceCallingServiceParams.getAvsDeviceFacade() == null) {
            log.w("deviceFacade was not initialized. Defaulting to isDropInBlocked=false");
            return false;
        }
        boolean isDropInDisallowed = this.deviceCallingServiceParams.getAvsDeviceFacade().isDropInDisallowed();
        boolean isDoNotDisturbOn = this.deviceCallingServiceParams.getAvsDeviceFacade().isDoNotDisturbOn();
        log.d(String.format(Locale.US, "isDropInDisallowed: %b isDoNotDisturbOn: %b", Boolean.valueOf(isDropInDisallowed), Boolean.valueOf(isDoNotDisturbOn)));
        return isDoNotDisturbOn || isDropInDisallowed;
    }

    private boolean isLocalVideoSupported() {
        return (this.localCameraDetected && !(this.deviceCallingServiceParams.getAvsDeviceFacade() != null ? this.deviceCallingServiceParams.getAvsDeviceFacade().isCameraPermitted() ^ true : false)) || this.renderRemoteVideoSupported;
    }

    private boolean isPerformingSignalingConnect() {
        return this.performingSignalingConnect;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void loadNativeLib() {
        try {
            System.loadLibrary("pjsua2");
            nativeLibsLoaded.set(true);
            log.i("pjsua2 Library loaded");
        } catch (UnsatisfiedLinkError e) {
            log.e(String.format("UnsatisfiedLinkError loading pjsua: %s", e.getMessage()), e);
            nativeLibsLoaded.set(false);
        }
    }

    private void notifySericeCallAdded(final Call call) {
        this.orchestratorToAppServiceHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.6
            @Override // java.lang.Runnable
            public void run() {
                SignalingAndMediaOrchestrator.this.callbackListener.onCallAdded(call);
            }
        });
    }

    private void notifySericeCallRemoved(final Call call) {
        this.orchestratorToAppServiceHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.7
            @Override // java.lang.Runnable
            public void run() {
                SignalingAndMediaOrchestrator.this.callbackListener.onCallRemoved(call);
            }
        });
    }

    private void notifyServiceToConfigureCall(final CallImpl callImpl) {
        this.orchestratorToAppServiceHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.5
            @Override // java.lang.Runnable
            public void run() {
                SignalingAndMediaOrchestrator.this.callbackListener.onCallConfigure(callImpl);
            }
        });
    }

    private DynamicAcousticParams parseDynamicAcousticParamsFromSipHeaders(SipHeaders sipHeaders) {
        log.d("parseDynamicAcousticParamsFromSipHeaders");
        DynamicAcousticParams dynamicAcousticParams = new DynamicAcousticParams();
        for (Map.Entry<String, DynamicAcousticParams.AcousticParamId> entry : SipHeaders.DYNAMIC_ACOUSTIC_PARAMS_SIP_HEADERS.entrySet()) {
            String str = sipHeaders.get(entry.getKey(), null);
            if (str != null) {
                dynamicAcousticParams.setParam(entry.getValue(), Boolean.valueOf(str.equalsIgnoreCase("true")));
            }
        }
        return dynamicAcousticParams;
    }

    private void performMediaManagerExit() {
        MediaManager mediaManager = this.mediaManager;
        if (mediaManager != null) {
            mediaManager.close();
            this.mediaManager = null;
        }
    }

    private void performSignalingExit() {
        log.i("performSignalingExit");
        this.signaling.exit();
        setOrchestratorState(DeviceCallingService.State.Initialized);
        this.registrationRequestStatus = SignalAndMediaOrchestrator.RegistrationRequestStatus.None;
    }

    private void processConnectivityChange(boolean z) {
        CallImplInternal activeCallImplInternal = getActiveCallImplInternal();
        boolean callReconnectInitiationSupported = activeCallImplInternal != null ? activeCallImplInternal.callReconnectInitiationSupported() : false;
        boolean isAnyCallPresent = isAnyCallPresent();
        CommsLogger commsLogger = log;
        StringBuilder sb = new StringBuilder();
        sb.append("processConnectivityChange: connected= ");
        sb.append(z);
        sb.append(" activeCallReconnectionWorkNeeded= ");
        sb.append(callReconnectInitiationSupported);
        sb.append(" isAnyCallPresent= ");
        GeneratedOutlineSupport1.outline184(sb, isAnyCallPresent, commsLogger);
        if (z) {
            if (isAnyCallPresent && !callReconnectInitiationSupported) {
                log.i("Connectivity restored. Making new transport");
                this.signaling.open();
                return;
            }
            if (callReconnectInitiationSupported) {
                activeCallImplInternal.getEventTracer().mark(EventTracerConfig.Event.reconnect_initiator_network_up);
            }
            reinitAndConfigureSignaling();
            if (!callReconnectInitiationSupported) {
                return;
            }
            activeCallImplInternal.getEventTracer().mark(EventTracerConfig.Event.reconnect_initiator_signaling_init);
            return;
        }
        if (callReconnectInitiationSupported) {
            activeCallImplInternal.getEventTracer().mark(EventTracerConfig.Event.reconnect_initiator_network_down);
        }
        if (isAnyCallPresent) {
            log.i("Connectivity lost. Attempting close of transport");
            this.signaling.close();
            this.pendingSignalingExit = true;
        }
        if (!isAnyCallPresent || callReconnectInitiationSupported) {
            log.i("Connectivity lost, performSignalingExit");
            performSignalingExit();
            this.pendingSignalingExit = false;
            if (callReconnectInitiationSupported) {
                activeCallImplInternal.getEventTracer().mark(EventTracerConfig.Event.reconnect_initiator_signaling_shutdown);
            }
        }
        this.performingSignalingConnect = false;
    }

    public static synchronized SignalingAndMediaOrchestrator provideInstance(Handler handler, SignalAndMediaOrchestrator.Callbacks callbacks, boolean z, boolean z2, boolean z3, boolean z4) {
        SignalingAndMediaOrchestrator signalingAndMediaOrchestrator;
        synchronized (SignalingAndMediaOrchestrator.class) {
            boolean z5 = true;
            Preconditions.checkArgument(handler != null, "callback handler cannot be null.");
            if (callbacks == null) {
                z5 = false;
            }
            Preconditions.checkArgument(z5, "callback listener cannot be null.");
            if (orchestratorInstance == null) {
                orchestratorInstance = new SignalingAndMediaOrchestrator(handler, callbacks, z, z2);
                if (z3) {
                    orchestratorInstance.orchestratorMsgHandler.post(loadNativeLibRunnable);
                }
                if (z3 && z4) {
                    orchestratorInstance.waitForOrchestratorGate.close();
                    orchestratorInstance.orchestratorMsgHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.2
                        @Override // java.lang.Runnable
                        public void run() {
                            SignalingAndMediaOrchestrator.orchestratorInstance.waitForOrchestratorGate.open();
                        }
                    });
                    orchestratorInstance.waitForOrchestratorGate.block(7000L);
                }
            }
            signalingAndMediaOrchestrator = orchestratorInstance;
        }
        return signalingAndMediaOrchestrator;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reconnectCallInternal(AuthenticationInfo authenticationInfo, MediaRelayInfo mediaRelayInfo) {
        Signaling.Channel channel;
        AmazonCallInfo amazonCallInfo;
        CallImplInternal activeCallImplInternal = getActiveCallImplInternal();
        if (activeCallImplInternal == null) {
            log.i("Cannot reconnect call, no active call found");
        } else if (!activeCallImplInternal.callReconnectInitiationSupported()) {
            log.w("Cannot reconnect call, not allowed");
        } else {
            AmazonCallInfo callInfo = activeCallImplInternal.getCallInfo();
            if (callInfo.getRemoteGruu() == null) {
                log.e("Remote did not share it's gruu, cannot initiate call reconnection");
                return;
            }
            if (activeCallImplInternal.getChannel().getState() != Signaling.Channel.State.Active) {
                channel = this.signaling.createChannel(new PjsipSignaling.ChannelParams(activeCallImplInternal.getCallId(), callInfo.getRemoteGruu().toString(), activeCallImplInternal.getEventTracer()));
                if (channel == null) {
                    log.e("Cannot reconnect, channel couldn't be created.");
                    return;
                }
                amazonCallInfo = AmazonCallInfo.createOutgoing(callInfo.getProvider(), activeCallImplInternal.getCallId(), callInfo.getLocalParticipant(), callInfo.getRemoteParticipant(), mediaRelayInfo, authenticationInfo.getAuthToken(), false, callInfo.getExtraHeaders(), callInfo.getCallOrigin());
                amazonCallInfo.getOutgoingHeaders().put(SipHeaders.SIP_HEADER_CALL_RECONNECTION, "true");
                amazonCallInfo.getOutgoingHeaders().put(SipHeaders.SIP_HEADER_PREVIOUS_SIP_CALL_ID, callInfo.getSipCallId() != null ? callInfo.getSipCallId() : "");
            } else {
                channel = activeCallImplInternal.getChannel();
                amazonCallInfo = callInfo;
            }
            activeCallImplInternal.reconnect(channel, amazonCallInfo);
        }
    }

    private void recordCallTokenMetric() {
        this.metricsSession.recordCount(CALLTOKEN_METRICS_SOURCE, CALLTOKEN_RECEIVED_METRICS_COUNTER, 1.0d, this.lastReceivedCallTokenCallId);
    }

    private void recordCallTokenWhenRegisteredMetric() {
        this.metricsSession.recordCount(CALLTOKEN_METRICS_SOURCE, CALLTOKEN_RECEIVED_WHEN_REGISTERED_METRICS_COUNTER, 1.0d, this.lastReceivedCallTokenCallId);
    }

    private void recordInviteForCallTokenMetric() {
        this.metricsSession.recordCount(CALLTOKEN_METRICS_SOURCE, CALLTOKEN_INVITE_RECEIVED_METRICS_COUNTER, 1.0d, this.lastReceivedCallTokenCallId);
    }

    private boolean register(RegistrarConfiguration registrarConfiguration) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Capability.AUDIO);
        if (this.deviceCallingServiceParams.getAvsDeviceFacade() != null && this.deviceCallingServiceParams.getAvsDeviceFacade().isCameraPermitted()) {
            arrayList.add(Capability.VIDEO);
        }
        PjsipSignaling.ConnectParams connectParams = new PjsipSignaling.ConnectParams(registrarConfiguration, arrayList);
        log.i("Performing signaling connect");
        this.performingSignalingConnect = true;
        if (!this.signaling.connect(connectParams)) {
            this.performingSignalingConnect = false;
            return false;
        }
        this.registrationRequestStatus = SignalAndMediaOrchestrator.RegistrationRequestStatus.InProgress;
        return true;
    }

    private void reinitAndConfigureSignaling() {
        log.i("Connectivity restored. Making new endpoint, transport, and account");
        this.nameServersResult = getNameServers();
        this.initParams = new PjsipSignaling.InitParams(this.rootCACertFilePath, this.nameServersResult.getNameServers(), this.userAgentInfoTemplate, this.eventTracerFactory, this.deviceCallingServiceParams.getRegistrationHeaders(), this.deviceCallingServiceParams.isPresencePublishCapable());
        if (this.registrarConfiguration != null) {
            if (!this.authTokenManager.isAuthTokenValid()) {
                log.i("Auth token invalid");
                this.authTokenManager.cancelAuthTokenAlarm();
                onAuthTokenExpiry(0L);
            } else if (!this.signaling.init(this.initParams)) {
                log.e("processConnectivityChange: Initialization failed");
            } else {
                register(this.registrarConfiguration);
            }
        }
    }

    private synchronized void removeCallFromActiveCallList(Call call) {
        this.callMap.remove(call.getCallId());
        this.activeCall = null;
        this.historicalCalls.add(HistoricalCall.builder().withCall(call).build());
    }

    private void reportErrorToService(final String str, final ErrorCode errorCode, final int i, final String str2) {
        this.orchestratorToAppServiceHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.3
            @Override // java.lang.Runnable
            public void run() {
                SignalingAndMediaOrchestrator.this.callbackListener.onSignalOrMediaError(str, errorCode, i, str2);
            }
        });
    }

    private void reportSipError(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str)) {
            str = NO_CALL_ID;
        }
        String str3 = str;
        boolean z2 = true;
        log.e(String.format(Locale.US, "Reporting sip error - (%s): %s", str2, log.sensitiveCallId(str3)));
        if (this.metricsSession != null) {
            if (!this.nameServersResult.isOnlyIPv6ServersPresent() || !this.nameServersResult.isDefaultNameServerApplied()) {
                z2 = false;
            }
            this.metricsSession.reportSipStatus(str2, str3, true, z, z2);
        }
    }

    private void sendPresenceMsgToRegistrar() {
        if (Strings.isNullOrEmpty(this.pendingPresenceStatusForTx)) {
            return;
        }
        log.d("preparing to send presence message");
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("<json>");
        sb.append("\n");
        GeneratedOutlineSupport1.outline181(sb, this.pendingPresenceStatusForTx, "\n", "</json>", "\n");
        this.pendingPresenceStatusForTx = null;
        this.signaling.sendServerMessage(new PjsipSignaling.PresenceInfoForRegistrar(sb.toString()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAllCallsVolumeInternal(float f) {
        for (Map.Entry<String, CallImpl> entry : this.callMap.entrySet()) {
            entry.getValue().getInternalCallOrchestrator().setVolume(f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMediaConstraintsInternal(Map<String, Integer> map) {
        if (this.persistentMediaConstraintOverrides == null) {
            this.persistentMediaConstraintOverrides = new HashMap();
        }
        try {
            this.persistentMediaConstraintOverrides.putAll(map);
        } catch (Exception e) {
            CommsLogger commsLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to cache persistent media constraint overrides due to exception: ");
            outline107.append(e.getMessage());
            commsLogger.w(outline107.toString());
            this.persistentMediaConstraintOverrides = null;
        }
        for (Map.Entry<String, CallImpl> entry : this.callMap.entrySet()) {
            entry.getValue().setMediaConstraints(map);
        }
    }

    private void setOrchestratorState(final DeviceCallingService.State state) {
        synchronized (this) {
            CommsLogger commsLogger = log;
            commsLogger.i("SetState: PreviousState:" + this.orchestratorState + " NewState:" + state);
            this.orchestratorState = state;
        }
        this.orchestratorToAppServiceHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.4
            @Override // java.lang.Runnable
            public void run() {
                SignalingAndMediaOrchestrator.this.callbackListener.onStateReport(state);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSystemCameraStateInternal(boolean z) {
        this.systemCameraEnabled = z;
        for (Map.Entry<String, CallImpl> entry : this.callMap.entrySet()) {
            entry.getValue().setSystemCameraState(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSystemMediaStateInternal(boolean z) {
        this.systemMediaEnabled = z;
        for (Map.Entry<String, CallImpl> entry : this.callMap.entrySet()) {
            entry.getValue().setSystemMediaState(z);
        }
        if (!z) {
            this.mediaManager.disposeCachedMediaSession();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shutdownInternal() {
        log.i("shutdownInternal");
        shutdownSignalingAndMediaManagers();
        this.orchestratorThread.quitSafely();
        this.orchestratorMsgHandler.removeCallbacksAndMessages(null);
        AuthTokenManager authTokenManager = this.authTokenManager;
        if (authTokenManager != null) {
            authTokenManager.cancelAuthTokenAlarm();
            this.authTokenManager = null;
        }
        setOrchestratorState(DeviceCallingService.State.Uninitialized);
        this.registrationRequestStatus = SignalAndMediaOrchestrator.RegistrationRequestStatus.None;
        this.orchestratorToAppServiceHandler = null;
        this.orchestratorMsgHandler = null;
        this.applicationContext = null;
        this.metricsSession = null;
        this.rootCACertFilePath = null;
        this.audioManager = null;
        this.deviceCallingServiceParams = null;
        this.performingSignalingConnect = false;
        this.waitForOrchestratorGate.open();
    }

    private void shutdownSignalingAndMediaManagers() {
        log.i("shutdownSignalingAndMediaManagers");
        performSignalingExit();
        performMediaManagerExit();
        synchronized (this) {
            this.callMap.clear();
            this.historicalCalls.clear();
        }
        this.pendingCallRemovedNotifyCleanup.clear();
        this.performingSignalingConnect = false;
        this.initParams = null;
        this.registrarConfiguration = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDeviceCapability(List<Capability> list) {
        this.signaling.updateCapabilities(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNewPresenceInfo(String str) {
        this.pendingPresenceStatusForTx = str;
        if (getOrchestratorState() == DeviceCallingService.State.Registered && this.sendPresence && this.authTokenManager.isAuthTokenValid()) {
            sendPresenceMsgToRegistrar();
            return;
        }
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("updatePresenceInfo: NOT sending presence to registrar, serviceState:");
        outline107.append(getOrchestratorState());
        outline107.append(" authTokenValid:");
        outline107.append(this.authTokenManager.isAuthTokenValid());
        commsLogger.i(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePresenceSendPrefInternal(boolean z) {
        CommsLogger commsLogger = log;
        commsLogger.i("Changing sendPresence to be: " + z);
        this.sendPresence = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void warmupInternal(int i) {
        if (!this.callMap.isEmpty()) {
            log.i("warmup received during active call.");
        } else if (!this.systemMediaEnabled) {
            log.i("warmup received but media not enabled.");
        } else {
            this.mediaManager.warmupMediaSession(i, isLocalVideoSupported(), enableLocalVideoAtCallInitiation(), this.systemMediaEnabled, this.systemCameraEnabled, this.deviceCallingServiceParams);
        }
    }

    public void beginCallInternal(ParticipantImpl participantImpl, ParticipantImpl participantImpl2, DeviceCallingService.OutgoingCallParams outgoingCallParams, EventTracer eventTracer) {
        log.i("beginCallInternal");
        String callId = outgoingCallParams.getCallId();
        if (isAnyCallPresent()) {
            CommsLogger commsLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Already in a call, ignore multiple outbound call requests. id= ");
            outline107.append(log.sensitiveCallId(callId));
            commsLogger.w(outline107.toString());
        } else if (!getOrchestratorState().equals(DeviceCallingService.State.Registered)) {
            String provider = outgoingCallParams.getProvider();
            ErrorCode errorCode = ErrorCode.RegistrationNotFound;
            reportError(callId, provider, errorCode, errorCode.getValue(), "Call attempted when SIP Client not registered");
        } else {
            AmazonCallInfo createOutgoing = AmazonCallInfo.createOutgoing(outgoingCallParams.getProvider(), callId, participantImpl, participantImpl2, outgoingCallParams.getMediaRelayInfo(), outgoingCallParams.getAuthInfo().getAuthToken(), outgoingCallParams.isDropIn(), outgoingCallParams.getHeaders());
            if (outgoingCallParams.getJoin() != null) {
                createOutgoing.getOutgoingHeaders().put(SipHeaders.SIP_HEADER_JOIN, outgoingCallParams.getJoin());
            }
            Signaling.Channel createChannel = this.signaling.createChannel(new PjsipSignaling.ChannelParams(callId, createOutgoing.getRemoteParticipant().getUri(), eventTracer));
            if (createChannel == null) {
                String provider2 = outgoingCallParams.getProvider();
                ErrorCode errorCode2 = ErrorCode.Unknown;
                reportError(callId, provider2, errorCode2, errorCode2.getValue(), "Unable to create call, channel creation failed");
                return;
            }
            float surfaceViewBorderRadius = this.deviceCallingServiceParams.getMediaParams().getSurfaceViewBorderRadius();
            VideoConfiguration videoConfiguration = this.deviceCallingServiceParams.getVideoConfiguration();
            boolean isSimulateFirstFrameReceived = videoConfiguration == null ? false : videoConfiguration.isSimulateFirstFrameReceived();
            boolean z = outgoingCallParams.isVideoEnabled() && !this.deviceCallingServiceParams.isForceReceiveOnlyVideo() && enableLocalVideoAtCallInitiation();
            boolean z2 = isLocalVideoSupported() && outgoingCallParams.isVideoEnabled();
            CallImplInternal createCallImplInternal = CallImplInternal.createCallImplInternal(this.mediaManager, this.orchestratorMsgHandler, this.orchestratorToAppServiceHandler, createChannel, z2, z, outgoingCallParams.getCallId(), createOutgoing, outgoingCallParams.isDropIn(), eventTracer, this.metricsSession, outgoingCallParams.getSrtpKeying(), this, !DeviceModel.AMAZON_AEOKN, null, this.systemMediaEnabled, this.systemCameraEnabled, this.deviceCallingServiceParams, outgoingCallParams.getStateTransitionTimeouts(), outgoingCallParams.getBundlePolicy(), outgoingCallParams.getRtcpMuxPolicy(), outgoingCallParams.getDynamicAcousticParams(), outgoingCallParams.getRealTimeText(), outgoingCallParams.getDataChannelParams(), z2, outgoingCallParams.isTrickleIceEnabled());
            createCallImplInternal.setCallReconnectInitiationSupported(outgoingCallParams.isCallReconnectInitiation());
            CallImpl beginCall = CallImpl.beginCall(createCallImplInternal, isLocalVideoSupported() && outgoingCallParams.isVideoEnabled(), z, outgoingCallParams.getCallId(), createOutgoing, outgoingCallParams.isDropIn(), eventTracer, this.metricsSession, this.deviceCallingServiceParams.isRequireCallAcknowledgement(), isSimulateFirstFrameReceived, surfaceViewBorderRadius, this.deviceCallingServiceParams.isUseTextureViewForRendering(), this.deviceCallingServiceParams.isTranslucentRemoteVideoRendererSupported());
            addCallInternal(callId, beginCall, createCallImplInternal, null);
            Map<String, Integer> map = this.persistentMediaConstraintOverrides;
            if (map == null) {
                return;
            }
            beginCall.setMediaConstraints(map);
        }
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public void beginOutgoingCall(final ParticipantImpl participantImpl, final ParticipantImpl participantImpl2, final DeviceCallingService.OutgoingCallParams outgoingCallParams, final EventTracer eventTracer) {
        this.orchestratorMsgHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.22
            @Override // java.lang.Runnable
            public void run() {
                SignalingAndMediaOrchestrator.this.beginCallInternal(participantImpl, participantImpl2, outgoingCallParams, eventTracer);
            }
        });
    }

    @VisibleForTesting
    protected HangupReason canAcceptCall(Signaling.Channel channel, AmazonCallInfo amazonCallInfo, String str) {
        if (getCallByCallId(str) != null) {
            HangupReason hangupReason = HangupReason.Busy;
            CommsLogger commsLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Found duplicate call with callId: ");
            outline107.append(log.sensitiveCallId(str));
            commsLogger.i(outline107.toString());
            return hangupReason;
        } else if (getHistoricalCallByCallId(str) != null) {
            HangupReason hangupReason2 = HangupReason.Busy;
            CommsLogger commsLogger2 = log;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Found historical call with callId: ");
            outline1072.append(log.sensitiveCallId(str));
            commsLogger2.i(outline1072.toString());
            return hangupReason2;
        } else if (!this.callMap.isEmpty()) {
            HangupReason hangupReason3 = HangupReason.Busy;
            CommsLogger commsLogger3 = log;
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Currently busy and rejecting call with callId: ");
            outline1073.append(log.sensitiveCallId(str));
            commsLogger3.i(outline1073.toString());
            return hangupReason3;
        } else if (this.deviceCallingServiceParams.getAvsDeviceFacade() != null && this.deviceCallingServiceParams.getAvsDeviceFacade().isMmSdkApplication() && this.audioManager.getMode() == 3) {
            HangupReason hangupReason4 = HangupReason.Busy;
            CommsLogger commsLogger4 = log;
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Another app has set audio mode as AudioManager.MODE_IN_COMMUNICATION. Rejecting call with callId: ");
            outline1074.append(log.sensitiveCallId(str));
            commsLogger4.i(outline1074.toString());
            return hangupReason4;
        } else if (amazonCallInfo.isDropIn() && isDropInBlocked()) {
            HangupReason hangupReason5 = HangupReason.Busy;
            CommsLogger commsLogger5 = log;
            commsLogger5.i("DND mode is on or drop-in is not permitted on the device. Rejecting incoming drop-in for callId: " + str);
            return hangupReason5;
        } else if (!this.deviceCallingServiceParams.isBuiltInSpeakerPresent() && this.deviceCallingServiceParams.getAvsDeviceFacade() != null && !this.deviceCallingServiceParams.getAvsDeviceFacade().isAuxPluggedIn() && !this.deviceCallingServiceParams.getAvsDeviceFacade().isBluetoothA2dpOn()) {
            HangupReason hangupReason6 = HangupReason.Busy;
            CommsLogger commsLogger6 = log;
            commsLogger6.i("No speaker is connected to the speakerless device. Rejecting an incoming call with callId:" + str);
            return hangupReason6;
        } else if (this.deviceCallingServiceParams.getAvsDeviceFacade() == null || !this.deviceCallingServiceParams.getAvsDeviceFacade().isPrivilegedTaskInProgress()) {
            return null;
        } else {
            HangupReason hangupReason7 = HangupReason.Busy;
            CommsLogger commsLogger7 = log;
            commsLogger7.i("Privileged Task is currently in progress. Rejecting an incoming call with callId:" + str);
            return hangupReason7;
        }
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public void configurePresence(final boolean z) {
        this.orchestratorMsgHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.15
            @Override // java.lang.Runnable
            public void run() {
                SignalingAndMediaOrchestrator.this.updatePresenceSendPrefInternal(z);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public void configureRegistrar(RegistrarConfiguration registrarConfiguration, long j) {
        ConfigureRegistrarPayload configureRegistrarPayload = new ConfigureRegistrarPayload(registrarConfiguration, j);
        synchronized (this.orchestratorMsgHandler) {
            if (this.orchestratorMsgHandler.hasMessages(202)) {
                log.i("configureRegistrar: queue has MSG_CONFIGURE_COMMS_CMD messages");
            } else {
                log.i("configureRegistrar: queue has NO MSG_CONFIGURE_COMMS_CMD messages");
            }
            this.orchestratorMsgHandler.removeMessages(202);
            this.orchestratorMsgHandler.obtainMessage(202, configureRegistrarPayload).sendToTarget();
        }
    }

    public void configureRegistrarInternal(ConfigureRegistrarPayload configureRegistrarPayload) {
        boolean hasMessages;
        RegistrarConfiguration registrarConfig = configureRegistrarPayload.getRegistrarConfig();
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("configureRegistrarInternal, DeviceCallingServiceState=");
        outline107.append(getOrchestratorState());
        commsLogger.i(outline107.toString());
        boolean z = true;
        updateConnectivityStateInternal(true);
        if (!havePendingConnectivityMsgs() && !isPerformingSignalingConnect()) {
            boolean z2 = !registrarConfig.equals(this.registrarConfiguration);
            if (z2) {
                log.i("RegistrarConfiguration has changed");
                if (log.isLoggable(LogLevel.Debug)) {
                    CommsLogger commsLogger2 = log;
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("ActiveRegistrarConfiguration:");
                    outline1072.append(this.registrarConfiguration);
                    commsLogger2.d(outline1072.toString());
                    log.d("NewRegistrarConfiguration:" + registrarConfig);
                }
            }
            this.registrarConfiguration = registrarConfig;
            boolean callTokenInRegHeaders = callTokenInRegHeaders();
            if (callTokenInRegHeaders) {
                this.lastReceivedCallTokenCallId = this.registrarConfiguration.getCallId();
                this.elapsedRealTimeAtCallTokenReceive = this.clocks.getElapsedRealtime();
                this.millisSinceEpochAtCallTokenReceive = this.clocks.getCurrentTimeMillis();
                recordCallTokenMetric();
                if (DeviceCallingService.State.Registered == getOrchestratorState()) {
                    recordCallTokenWhenRegisteredMetric();
                }
            }
            if (!z2 && DeviceCallingService.State.Initialized != getOrchestratorState()) {
                synchronized (this.orchestratorMsgHandler) {
                    hasMessages = this.orchestratorMsgHandler.hasMessages(203);
                }
                if (!hasMessages) {
                    log.i("ConfigureRegistrarInternal, Updating headers");
                    if (DeviceCallingService.State.Registered == getOrchestratorState() && !callTokenInRegHeaders) {
                        z = false;
                    }
                    this.authTokenManager.setAuthTokenAlarm(this.registrarConfiguration.getAuthTokenIntervalInSecs(), configureRegistrarPayload.getAuthTokenExpireTime());
                    SipHeaders sipHeaders = new SipHeaders(this.registrarConfiguration.getHeaders());
                    if (!Strings.isNullOrEmpty(this.registrarConfiguration.getAuthToken())) {
                        sipHeaders.put(SipHeaders.SIP_HEADER_AUTH_TOKEN, this.registrarConfiguration.getAuthToken());
                    }
                    boolean updateConfigurationParams = this.signaling.updateConfigurationParams(new PjsipSignaling.ConfigurationParams(sipHeaders), z);
                    if (!z) {
                        return;
                    }
                    if (updateConfigurationParams) {
                        this.registrationRequestStatus = SignalAndMediaOrchestrator.RegistrationRequestStatus.InProgress;
                        return;
                    } else {
                        log.i("ConfigureRegistrarInternal, Forced registration attempt failed.");
                        return;
                    }
                }
                log.i("ConfigureRegistrarInternal, not updating token since there is a pending token");
                return;
            }
            log.i("Processing new registrar configuration");
            this.authTokenManager.setAuthTokenAlarm(registrarConfig.getAuthTokenIntervalInSecs(), configureRegistrarPayload.getAuthTokenExpireTime());
            if (!this.signaling.init(this.initParams)) {
                log.e("Failed to initialize signaling");
                setOrchestratorState(DeviceCallingService.State.Initialized);
                this.registrationRequestStatus = SignalAndMediaOrchestrator.RegistrationRequestStatus.None;
                return;
            } else if (register(registrarConfig)) {
                return;
            } else {
                log.e("Failed to connect to signaling");
                setOrchestratorState(DeviceCallingService.State.Initialized);
                this.registrationRequestStatus = SignalAndMediaOrchestrator.RegistrationRequestStatus.None;
                return;
            }
        }
        synchronized (this.orchestratorMsgHandler) {
            if (!this.orchestratorMsgHandler.hasMessages(202)) {
                log.i("Pending network connectivity message. Deferring configure comms");
                this.orchestratorMsgHandler.sendMessageDelayed(this.orchestratorMsgHandler.obtainMessage(202, configureRegistrarPayload), DeviceConfigConstants.DEFAULT_CALL_END_TO_SHUTDOWN_TIMEOUT_MS);
            } else {
                log.i("configureRegistrarInternal, not scheduling internal configure comms since there is a pending CC in the queue");
            }
        }
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public synchronized CallImpl getActiveCall() {
        if (this.activeCall == null) {
            log.d("activeCall is null");
            return null;
        }
        return this.activeCall.getCallForSession();
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public synchronized Call getCallByCallId(String str) {
        return this.callMap.get(str);
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public synchronized List<Call> getCalls(Predicate<Call> predicate) {
        return ImmutableList.copyOf(Iterables.filter(this.callMap.values(), predicate));
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public synchronized HistoricalCall getHistoricalCallByCallId(final String str) {
        return (HistoricalCall) Iterables.find(this.historicalCalls, new Predicate<HistoricalCall>() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.10
            @Override // com.google.common.base.Predicate
            public boolean apply(HistoricalCall historicalCall) {
                return historicalCall.getCallId().equals(str);
            }
        }, null);
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public synchronized Iterable<HistoricalCall> getHistoricalCalls() {
        return this.historicalCalls;
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public synchronized Call getMostRecentCall() {
        if (this.mostRecentCall == null) {
            return null;
        }
        return this.mostRecentCall.getCallForSession();
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public synchronized DeviceCallingService.State getOrchestratorState() {
        return this.orchestratorState;
    }

    void handleIncomingCallInternal(Signaling.Channel channel, String str, AmazonCallInfo amazonCallInfo, Sdp sdp, EventTracer eventTracer) {
        log.i("handleIncomingCallInternal");
        HangupReason canAcceptCall = canAcceptCall(channel, amazonCallInfo, str);
        if (canAcceptCall == null) {
            boolean isLocalVideoSupported = isLocalVideoSupported();
            boolean z = false;
            boolean z2 = isLocalVideoSupported && !this.deviceCallingServiceParams.isForceReceiveOnlyVideo() && enableLocalVideoAtCallInitiation();
            float surfaceViewBorderRadius = this.deviceCallingServiceParams.getMediaParams().getSurfaceViewBorderRadius();
            VideoConfiguration videoConfiguration = this.deviceCallingServiceParams.getVideoConfiguration();
            boolean isSimulateFirstFrameReceived = videoConfiguration == null ? false : videoConfiguration.isSimulateFirstFrameReceived();
            boolean isMediaPresent = sdp.isMediaPresent("video");
            if (amazonCallInfo.getProvider().equalsIgnoreCase("A2A")) {
                z = sdp.isTrickleIceEnabled();
            }
            CallImplInternal createCallImplInternal = CallImplInternal.createCallImplInternal(this.mediaManager, this.orchestratorMsgHandler, this.orchestratorToAppServiceHandler, channel, isLocalVideoSupported, z2, str, amazonCallInfo, amazonCallInfo.isDropIn(), eventTracer, this.metricsSession, sdp.getSrtpKeying(), this, !DeviceModel.AMAZON_AEOKN, sdp, this.systemMediaEnabled, this.systemCameraEnabled, this.deviceCallingServiceParams, null, null, null, parseDynamicAcousticParamsFromSipHeaders(amazonCallInfo.getIncomingHeaders()), null, null, isMediaPresent, z);
            CallImpl startIncomingCall = CallImpl.startIncomingCall(createCallImplInternal, isLocalVideoSupported, z2, str, amazonCallInfo, eventTracer, this.metricsSession, this.deviceCallingServiceParams.isRequireCallAcknowledgement(), isSimulateFirstFrameReceived, surfaceViewBorderRadius, isMediaPresent, this.deviceCallingServiceParams.isUseTextureViewForRendering(), this.deviceCallingServiceParams.isTranslucentRemoteVideoRendererSupported());
            addCallInternal(str, startIncomingCall, createCallImplInternal, sdp);
            Map<String, Integer> map = this.persistentMediaConstraintOverrides;
            if (map == null) {
                return;
            }
            startIncomingCall.setMediaConstraints(map);
            return;
        }
        CallImplInternal activeCallImplInternal = getActiveCallImplInternal();
        if (activeCallImplInternal != null && activeCallImplInternal.getState() == Call.State.Active && activeCallImplInternal.getCallForSession() == ((CallImpl) getCallByCallId(str))) {
            log.i("Received a reconnect request.");
            activeCallImplInternal.getEventTracer().mark(EventTracerConfig.Event.reconnect_receiver_signaling_offered);
            activeCallImplInternal.handleReconnect(channel, sdp, amazonCallInfo);
            return;
        }
        log.i("sending rejection as incoming call different from active call");
        channel.sendMessage(new PjsipSignaling.HangupMessage(canAcceptCall));
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public void hangupAll() {
        this.orchestratorMsgHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.19
            @Override // java.lang.Runnable
            public void run() {
                SignalingAndMediaOrchestrator.this.hangupAllInternal();
            }
        });
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public void notifyOfCallRemovedReceived(final Call call) {
        log.i("notifyOfCallRemovedReceived");
        if (call == null) {
            return;
        }
        removeCallFromActiveCallList(call);
        this.orchestratorMsgHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.9
            @Override // java.lang.Runnable
            public void run() {
                SignalingAndMediaOrchestrator.this.callFinishedOrCallRemovedNetworkCleanup(call);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.authtoken.AuthTokenManagerListener
    public void onAuthTokenExpiry(final long j) {
        this.orchestratorToAppServiceHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.8
            @Override // java.lang.Runnable
            public void run() {
                SignalingAndMediaOrchestrator.this.callbackListener.onAuthTokenExpiry(j);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.CallFinishedListener
    public void onCallFinished(CallImpl callImpl) {
        String callId = callImpl.getCallId();
        this.pendingCallRemovedNotifyCleanup.put(callId, callImpl);
        log.i(String.format(Locale.US, "Notifying service listeners of call disconnection: %s", log.sensitiveCallId(callId)));
        notifySericeCallRemoved(callImpl);
    }

    @Override // com.amazon.comms.ringservice.SignalingListener
    public void onConnected(long j) {
        CommsLogger commsLogger = log;
        commsLogger.i("Signaling connect succeeded, expiration:" + j);
        boolean z = false;
        this.performingSignalingConnect = false;
        setOrchestratorState(DeviceCallingService.State.Registered);
        if (this.metricsSession != null) {
            if (this.nameServersResult.isOnlyIPv6ServersPresent() && this.nameServersResult.isDefaultNameServerApplied()) {
                z = true;
            }
            this.metricsSession.reportSipStatus(null, null, false, true, z);
        }
        this.registrationRequestStatus = SignalAndMediaOrchestrator.RegistrationRequestStatus.Succeeded;
        if (this.sendPresence) {
            sendPresenceMsgToRegistrar();
        }
        synchronized (this) {
            if (this.activeCall != null) {
                this.activeCall.getEventTracer().mark(EventTracerConfig.Event.reconnect_initiator_signaling_registered);
            }
        }
    }

    @Override // com.amazon.comms.ringservice.SignalingListener
    public void onConnectionError(int i, String str) {
        boolean z = false;
        this.performingSignalingConnect = false;
        setOrchestratorState(DeviceCallingService.State.Unregistered);
        if (i == SipStatusCode.OK.getCode()) {
            log.i("Intentionally disconnected, skipping reporting error");
            return;
        }
        log.e("Signaling connect ended up in error, sipErrorCode=" + i + " errorStr=" + str);
        String callId = getCallId();
        StringBuilder sb = new StringBuilder(Integer.toString(i));
        if (i == SipStatusCode.SERVICE_UNAVAILABLE.getCode()) {
            String[] split = str.split("[\\(\\)]");
            String replace = ((split == null || split.length <= 1) ? str : split[1]).replace(Chars.SPACE, '_');
            log.e("extracted errorReason for signaling connect failure: " + replace);
            if (!replace.isEmpty()) {
                sb.append("_");
                sb.append(replace);
            }
        }
        ErrorCode errorCode = ErrorCode.RegistrationFailed;
        if (i == SipStatusCode.SERVICE_UNAVAILABLE.getCode() || i == SipStatusCode.REQUEST_TIMEOUT.getCode()) {
            errorCode = ErrorCode.RegistrarUnreachable;
        }
        String callProvider = getCallProvider();
        reportError(callId, callProvider, errorCode, i, "Failed to register, sipErrorCode:" + i + " sipErrorStr:" + str);
        if (this.registrationRequestStatus != SignalAndMediaOrchestrator.RegistrationRequestStatus.Failed) {
            z = true;
        }
        reportSipError(callId, sb.toString(), z);
        if (i == SipStatusCode.FORBIDDEN.getCode()) {
            log.i("Auth Token Register Failed, Firing expiry callback.");
            this.authTokenManager.cancelAuthTokenAlarm();
            onAuthTokenExpiry(0L);
        }
        this.registrationRequestStatus = SignalAndMediaOrchestrator.RegistrationRequestStatus.Failed;
    }

    @Override // com.amazon.comms.ringservice.SignalingListener
    public void onNewChannel(Signaling.Channel channel, Signaling.Message message) {
        PjsipSignaling.ChannelInfo channelInfo = (PjsipSignaling.ChannelInfo) channel.getInfo();
        if (message instanceof PjsipSignaling.CallMessage) {
            PjsipSignaling.CallMessage callMessage = (PjsipSignaling.CallMessage) message;
            CommsLogger commsLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("OnNewChannel: lastReceivedCallTokenCallId:");
            outline107.append(this.lastReceivedCallTokenCallId);
            outline107.append(" callId of the call:");
            outline107.append(channelInfo.getCallId());
            commsLogger.d(outline107.toString());
            String str = this.lastReceivedCallTokenCallId;
            if (str != null && str.equals(channelInfo.getCallId())) {
                channelInfo.getEventTracer().markHistoric(EventTracerConfig.Event.Callee_Received_CallToken, this.elapsedRealTimeAtCallTokenReceive, this.millisSinceEpochAtCallTokenReceive);
                recordInviteForCallTokenMetric();
            }
            handleIncomingCallInternal(channel, channelInfo.getCallId(), callMessage.getCallInfo(), callMessage.getSdp(), channelInfo.getEventTracer());
            return;
        }
        log.e("New channel created with incorrect message.");
    }

    @Override // com.amazon.comms.ringservice.SignalingListener
    public void onNewChannelError(Signaling.MessageError messageError, Signaling.MessageErrorInfo messageErrorInfo) {
        if (messageError != Signaling.MessageError.RECV_ERROR || !(messageErrorInfo instanceof PjsipSignaling.MessageErrorInfo)) {
            return;
        }
        PjsipSignaling.MessageErrorInfo messageErrorInfo2 = (PjsipSignaling.MessageErrorInfo) messageErrorInfo;
        reportError(messageErrorInfo2.getCallId(), getCallProvider(), messageErrorInfo2.getErrorCode(), messageErrorInfo2.getInternalErrorCode(), messageErrorInfo2.getErrorDescription());
    }

    @Override // com.amazon.comms.ringservice.SignalingListener
    public void onPublishError(int i, String str) {
        CommsLogger commsLogger = log;
        commsLogger.i("onPublishError, sipErr=" + i + " sipErrStr=" + str);
        if (i == SipStatusCode.FORBIDDEN.getCode()) {
            this.signaling.disablePublish();
        } else if (i != SipStatusCode.SERVICE_UNAVAILABLE.getCode() && i != SipStatusCode.REQUEST_TIMEOUT.getCode()) {
        } else {
            reportErrorToService(getCallId(), ErrorCode.RegistrarUnreachable, i, str);
        }
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public void performInitialization(final Context context, final EventTracerFactory eventTracerFactory, final DeviceCallingServiceParams deviceCallingServiceParams, final MetricsSession metricsSession, final boolean z, final String str, final boolean z2) {
        if (this.instanceShutdown.get()) {
            log.w("cannot request initialization after orchestrator shutdown");
            return;
        }
        Runnable runnable = new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.11
            @Override // java.lang.Runnable
            public void run() {
                SignalingAndMediaOrchestrator.this.initializeInternal(context, eventTracerFactory, deviceCallingServiceParams, metricsSession, z, str);
                if (z2) {
                    SignalingAndMediaOrchestrator.this.waitForOrchestratorGate.open();
                }
            }
        };
        if (!nativeLibsLoaded.get()) {
            this.orchestratorMsgHandler.post(loadNativeLibRunnable);
        }
        if (z2) {
            this.waitForOrchestratorGate.close();
        }
        this.orchestratorMsgHandler.post(runnable);
        if (!z2) {
            return;
        }
        this.waitForOrchestratorGate.block(7000L);
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public void reconnectCall(final AuthenticationInfo authenticationInfo, final MediaRelayInfo mediaRelayInfo) {
        this.orchestratorMsgHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.23
            @Override // java.lang.Runnable
            public void run() {
                SignalingAndMediaOrchestrator.this.reconnectCallInternal(authenticationInfo, mediaRelayInfo);
            }
        });
    }

    void reportError(String str, String str2, ErrorCode errorCode, int i, String str3) {
        String remoteDeviceType;
        Boolean valueOf;
        String safeCallId = getSafeCallId(str);
        if (TextUtils.isEmpty(str3)) {
            str3 = "";
        }
        log.e(String.format(Locale.US, "Reporting error - %s (%d): %s", str3, Integer.valueOf(errorCode.getValue()), log.sensitiveCallId(safeCallId)));
        if (this.metricsSession != null) {
            if (!safeCallId.equals(NO_CALL_ID) && ErrorCode.isCallError(errorCode)) {
                synchronized (this) {
                    remoteDeviceType = this.activeCall == null ? null : this.activeCall.getRemoteDeviceType();
                    valueOf = this.activeCall == null ? null : Boolean.valueOf(this.activeCall.isTrickleIceEnabled());
                }
                this.metricsSession.recordCallCompletionStatus(safeCallId, false, errorCode, errorCode.toString(), remoteDeviceType, valueOf, str2);
            } else {
                this.metricsSession.reportError(errorCode, safeCallId);
            }
        }
        reportErrorToService(safeCallId, errorCode, i, str3);
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public void setAllCallVolume(final float f) {
        this.orchestratorMsgHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.24
            @Override // java.lang.Runnable
            public void run() {
                SignalingAndMediaOrchestrator.this.setAllCallsVolumeInternal(f);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public void setMediaConstraints(final Map<String, Integer> map) {
        if (map == null) {
            return;
        }
        this.orchestratorMsgHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.18
            @Override // java.lang.Runnable
            public void run() {
                SignalingAndMediaOrchestrator.this.setMediaConstraintsInternal(map);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public void setSystemCameraState(final boolean z) {
        this.orchestratorMsgHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.17
            @Override // java.lang.Runnable
            public void run() {
                SignalingAndMediaOrchestrator.this.setSystemCameraStateInternal(z);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public void setSystemMediaState(final boolean z) {
        this.orchestratorMsgHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.16
            @Override // java.lang.Runnable
            public void run() {
                SignalingAndMediaOrchestrator.this.setSystemMediaStateInternal(z);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public void shutdownInstance(boolean z) {
        synchronized (SignalingAndMediaOrchestrator.class) {
            if (orchestratorInstance == null) {
                log.i("orchestrator already stopped.");
                return;
            }
            this.instanceShutdown.set(true);
            if (z) {
                this.waitForOrchestratorGate.close();
            }
            this.orchestratorMsgHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.12
                @Override // java.lang.Runnable
                public void run() {
                    SignalingAndMediaOrchestrator.this.shutdownInternal();
                }
            });
            if (z) {
                this.waitForOrchestratorGate.block(7000L);
            }
            orchestratorInstance = null;
        }
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public void updateAudioBitrateForCalls(final int i) {
        this.orchestratorMsgHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.25
            @Override // java.lang.Runnable
            public void run() {
                if (SignalingAndMediaOrchestrator.this.deviceCallingServiceParams == null) {
                    return;
                }
                SignalingAndMediaOrchestrator.this.deviceCallingServiceParams.setAudioStartBitrateInKbps(i);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public void updateAuthToken(AuthTokenConfig authTokenConfig, long j) {
        AuthTokenPayload authTokenPayload = new AuthTokenPayload(authTokenConfig, j);
        synchronized (this.orchestratorMsgHandler) {
            this.orchestratorMsgHandler.removeMessages(203);
            this.orchestratorMsgHandler.obtainMessage(203, authTokenPayload).sendToTarget();
        }
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public void updateConnectivityState(final boolean z) {
        this.orchestratorMsgHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.13
            @Override // java.lang.Runnable
            public void run() {
                SignalingAndMediaOrchestrator.this.updateConnectivityStateInternal(z);
            }
        });
    }

    public void updateConnectivityStateInternal(boolean z) {
        if (this.networkDisconnected == (!z)) {
            return;
        }
        CallImplInternal activeCallImplInternal = getActiveCallImplInternal();
        CommsLogger commsLogger = log;
        StringBuilder sb = new StringBuilder();
        sb.append("updateConnectivityState: connected = ");
        sb.append(z);
        sb.append(" activeCallInternal= ");
        GeneratedOutlineSupport1.outline184(sb, activeCallImplInternal != null, commsLogger);
        if (activeCallImplInternal != null) {
            activeCallImplInternal.networkInterfaceConnectivityUpdate(z);
        }
        if (z) {
            log.i("Network connectivity restored. Requesting endpoint regen");
            this.orchestratorMsgHandler.removeMessages(201);
            this.orchestratorMsgHandler.sendEmptyMessage(201);
        } else {
            log.i("Network connectivity change. Network down. Initiating cleanup");
            if (activeCallImplInternal != null && (activeCallImplInternal.getState() == Call.State.Created || activeCallImplInternal.getState() == Call.State.Ringing)) {
                hangupAllCalls();
            }
            this.orchestratorMsgHandler.removeMessages(200);
            this.orchestratorMsgHandler.sendEmptyMessage(200);
        }
        this.networkDisconnected = !z;
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public void updateDeviceCapabilities(final List<Capability> list) {
        this.orchestratorMsgHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.21
            @Override // java.lang.Runnable
            public void run() {
                SignalingAndMediaOrchestrator.this.updateDeviceCapability(list);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public void updatePresenceInfo(final String str) {
        if (Strings.isNullOrEmpty(str)) {
            return;
        }
        this.orchestratorMsgHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.14
            @Override // java.lang.Runnable
            public void run() {
                SignalingAndMediaOrchestrator.this.updateNewPresenceInfo(str);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator
    public void warmupMediaPipeline(final int i) {
        this.orchestratorMsgHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.SignalingAndMediaOrchestrator.20
            @Override // java.lang.Runnable
            public void run() {
                SignalingAndMediaOrchestrator.this.warmupInternal(i);
            }
        });
    }
}
