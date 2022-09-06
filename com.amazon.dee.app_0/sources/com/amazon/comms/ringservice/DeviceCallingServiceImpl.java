package com.amazon.comms.ringservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.calling.service.DeviceCallingServiceListener;
import com.amazon.comms.calling.service.DeviceCallingServiceParams;
import com.amazon.comms.calling.service.ErrorCode;
import com.amazon.comms.calling.service.HistoricalCall;
import com.amazon.comms.calling.service.VolumeController;
import com.amazon.comms.calling.service.WebRTCViewRenderer;
import com.amazon.comms.calling.sipclient.AuthenticationInfo;
import com.amazon.comms.calling.sipclient.MediaRelayInfo;
import com.amazon.comms.calling.sipclient.RegistrarConfiguration;
import com.amazon.comms.instrumentation.EventTracer;
import com.amazon.comms.instrumentation.EventTracerFactory;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.authtoken.AlarmAuthTokenManager;
import com.amazon.comms.ringservice.orchestrator.AuthTokenConfig;
import com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator;
import com.amazon.comms.ringservice.util.Capability;
import com.amazon.comms.util.ListenerSet;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes12.dex */
public class DeviceCallingServiceImpl implements DeviceCallingService, VolumeController {
    static final int CALL_MAP_INITIAL_CAPACITY = 2;
    private static final CommsLogger log = CommsLogger.getLogger(DeviceCallingServiceImpl.class);
    private Context applicationContext;
    private AuthTokenAlarmManagerReceiver authTokenAlarmBroadcastReceiver;
    private long authTokenExpiryTime;
    private DeviceCallingServiceParams deviceCallingServiceParams;
    private Handler mainThreadHandler;
    private Call mostRecentCall;
    private SignalAndMediaOrchestrator orchestrator;
    private OrchestratorCallbackListener orchestratorListener;
    private RegistrarConfiguration registrarConfiguration;
    private boolean networkDisconnected = false;
    private final ListenerSet<DeviceCallingServiceListener> serviceListeners = new ListenerSet<>();
    private DeviceCallingService.State state = DeviceCallingService.State.Uninitialized;
    private boolean systemMediaEnabled = true;
    private boolean systemCameraEnabled = true;
    private boolean authTokenBroadcastReceiverRegistered = false;
    private final Map<String, Call> callMap = new HashMap(2);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static final class AuthTokenAlarmManagerReceiver extends BroadcastReceiver {
        static final String TAG = AuthTokenAlarmManagerReceiver.class.getSimpleName();
        final WeakReference<DeviceCallingServiceImpl> serviceHandle;

        AuthTokenAlarmManagerReceiver(DeviceCallingServiceImpl deviceCallingServiceImpl) {
            this.serviceHandle = new WeakReference<>(deviceCallingServiceImpl);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                CommsLogger commsLogger = DeviceCallingServiceImpl.log;
                commsLogger.e(TAG + ": Invalid intent: null");
                return;
            }
            String action = intent.getAction();
            if (!AlarmAuthTokenManager.ACTION_AUTH_TOKEN_ALARM.equals(action)) {
                CommsLogger commsLogger2 = DeviceCallingServiceImpl.log;
                commsLogger2.e(TAG + ": Invalid action: " + action);
                return;
            }
            if (!(Thread.currentThread() == Looper.getMainLooper().getThread())) {
                CommsLogger commsLogger3 = DeviceCallingServiceImpl.log;
                commsLogger3.w(TAG + ": onReceive was not invoked on system on main thread!");
            }
            DeviceCallingServiceImpl deviceCallingServiceImpl = this.serviceHandle.get();
            if (deviceCallingServiceImpl == null) {
                CommsLogger commsLogger4 = DeviceCallingServiceImpl.log;
                commsLogger4.e(TAG + ": Cannot invoke onAuthTokenExpiring callback, service handle no longer available");
                return;
            }
            deviceCallingServiceImpl.onAuthTokenAlarmTrigerred(deviceCallingServiceImpl.authTokenExpiryTime - (SystemClock.elapsedRealtime() / 1000));
        }
    }

    /* loaded from: classes12.dex */
    private class OrchestratorCallbackListener implements SignalAndMediaOrchestrator.Callbacks {
        private boolean isShutdown;

        private OrchestratorCallbackListener() {
            this.isShutdown = false;
        }

        public void instanceShutdown() {
            this.isShutdown = true;
        }

        @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator.Callbacks
        public void onAuthTokenExpiry(long j) {
            DeviceCallingServiceImpl.this.onAuthTokenAlarmTrigerred(j);
        }

        @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator.Callbacks
        public void onCallAdded(final Call call) {
            DeviceCallingServiceImpl.log.d("notifying call state listeners fon onCallAdded");
            DeviceCallingServiceImpl.this.mostRecentCall = call;
            DeviceCallingServiceImpl.this.callMap.put(call.getCallId(), call);
            DeviceCallingServiceImpl.this.serviceListeners.notify(new ListenerSet.Notifier<DeviceCallingServiceListener>() { // from class: com.amazon.comms.ringservice.DeviceCallingServiceImpl.OrchestratorCallbackListener.3
                @Override // com.amazon.comms.util.ListenerSet.Notifier
                public void notify(DeviceCallingServiceListener deviceCallingServiceListener) {
                    deviceCallingServiceListener.onCallAdded(call);
                }
            });
        }

        @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator.Callbacks
        public void onCallConfigure(final CallImpl callImpl) {
            DeviceCallingServiceImpl.log.d("notifying call state listeners fon configureCall");
            DeviceCallingServiceImpl.this.serviceListeners.notify(new ListenerSet.Notifier<DeviceCallingServiceListener>() { // from class: com.amazon.comms.ringservice.DeviceCallingServiceImpl.OrchestratorCallbackListener.2
                @Override // com.amazon.comms.util.ListenerSet.Notifier
                public void notify(DeviceCallingServiceListener deviceCallingServiceListener) {
                    deviceCallingServiceListener.configureCall(callImpl);
                }
            });
            callImpl.postCallConfigured(DeviceCallingServiceImpl.this.applicationContext);
        }

        @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator.Callbacks
        public void onCallRemoved(final Call call) {
            DeviceCallingServiceImpl.log.d("notifying call state listeners fon onCallRemoved");
            DeviceCallingServiceImpl.this.callMap.remove(call.getCallId());
            if (DeviceCallingServiceImpl.this.orchestrator != null) {
                DeviceCallingServiceImpl.this.orchestrator.notifyOfCallRemovedReceived(call);
            }
            DeviceCallingServiceImpl.this.serviceListeners.notify(new ListenerSet.Notifier<DeviceCallingServiceListener>() { // from class: com.amazon.comms.ringservice.DeviceCallingServiceImpl.OrchestratorCallbackListener.4
                @Override // com.amazon.comms.util.ListenerSet.Notifier
                public void notify(DeviceCallingServiceListener deviceCallingServiceListener) {
                    deviceCallingServiceListener.onCallRemoved(call);
                }
            });
        }

        @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator.Callbacks
        public void onSignalOrMediaError(final String str, final ErrorCode errorCode, final int i, final String str2) {
            DeviceCallingServiceImpl.this.serviceListeners.notify(new ListenerSet.Notifier<DeviceCallingServiceListener>() { // from class: com.amazon.comms.ringservice.DeviceCallingServiceImpl.OrchestratorCallbackListener.1
                @Override // com.amazon.comms.util.ListenerSet.Notifier
                public void notify(DeviceCallingServiceListener deviceCallingServiceListener) {
                    deviceCallingServiceListener.onError(str, errorCode, i, str2);
                }
            });
        }

        @Override // com.amazon.comms.ringservice.orchestrator.SignalAndMediaOrchestrator.Callbacks
        public void onStateReport(DeviceCallingService.State state) {
            if (this.isShutdown) {
                return;
            }
            DeviceCallingServiceImpl.this.setState(state);
        }
    }

    private String generateUserAgentInfoTemplate(String str) {
        String userAgentStackVersionCodePrepend = this.deviceCallingServiceParams.getUserAgentStackVersionCodePrepend();
        if (Strings.isNullOrEmpty(userAgentStackVersionCodePrepend)) {
            userAgentStackVersionCodePrepend = "RS";
        }
        return Build.MODEL + "/" + this.deviceCallingServiceParams.getBuildVersion() + " " + userAgentStackVersionCodePrepend + "/" + str + " Pjsua2/Android-__PJSIP_VERSION__ Android/" + Build.VERSION.RELEASE;
    }

    private String generateUserAgentStackVersionCode() {
        String userAgentStackVersionCode = this.deviceCallingServiceParams.getUserAgentStackVersionCode();
        if (Strings.isNullOrEmpty(userAgentStackVersionCode)) {
            int i = 0;
            try {
                i = this.applicationContext.getPackageManager().getPackageInfo(this.applicationContext.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                log.e(String.format("Exception getting PackageInfo: %s", e.getMessage()), e);
            }
            return Integer.toString(i);
        }
        return userAgentStackVersionCode;
    }

    @VisibleForTesting
    static long getAuthTokenExpiryTime(int i) {
        return (SystemClock.elapsedRealtime() / 1000) + i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAuthTokenAlarmTrigerred(final long j) {
        CommsLogger commsLogger = log;
        commsLogger.i("onAuthTokenAlarmTrigerred: timeLeftInSecs= " + j);
        this.serviceListeners.notify(new ListenerSet.Notifier<DeviceCallingServiceListener>() { // from class: com.amazon.comms.ringservice.DeviceCallingServiceImpl.1
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(DeviceCallingServiceListener deviceCallingServiceListener) {
                deviceCallingServiceListener.onAuthTokenExpiring(DeviceCallingServiceImpl.this, j);
            }
        });
    }

    private void registerAuthTokenAlarmReceiverIfNeeded() {
        if (this.applicationContext != null && this.authTokenAlarmBroadcastReceiver != null) {
            if (this.authTokenBroadcastReceiverRegistered) {
                return;
            }
            log.d("Registering receiver for alarm");
            this.applicationContext.registerReceiver(this.authTokenAlarmBroadcastReceiver, new IntentFilter(AlarmAuthTokenManager.ACTION_AUTH_TOKEN_ALARM));
            this.authTokenBroadcastReceiverRegistered = true;
            return;
        }
        log.w("Can't register alarm receiver");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setState(final DeviceCallingService.State state) {
        CommsLogger commsLogger = log;
        commsLogger.i("SetState: PreviousState:" + this.state + " NewState:" + state);
        this.state = state;
        this.serviceListeners.notify(new ListenerSet.Notifier<DeviceCallingServiceListener>() { // from class: com.amazon.comms.ringservice.DeviceCallingServiceImpl.2
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(DeviceCallingServiceListener deviceCallingServiceListener) {
                deviceCallingServiceListener.onStateChanged(DeviceCallingServiceImpl.this, state);
            }
        });
    }

    private void unregisterAuthTokenAlarmReceiverIfNeeded() {
        if (this.applicationContext != null && this.authTokenAlarmBroadcastReceiver != null) {
            if (!this.authTokenBroadcastReceiverRegistered) {
                return;
            }
            log.d("Unregistering receiver for alarm");
            this.applicationContext.unregisterReceiver(this.authTokenAlarmBroadcastReceiver);
            this.authTokenBroadcastReceiverRegistered = false;
            return;
        }
        log.w("Can't un-register alarm receiver");
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public void beginCall(DeviceCallingService.OutgoingCallParams outgoingCallParams, EventTracer eventTracer) {
        Preconditions.checkState(DeviceCallingService.State.Uninitialized != getState(), "initialize() must be called before using calling service");
        if (this.orchestrator == null) {
            log.w("Cannot beginCall, orchestrator is null.Initialize service before requesting op.");
            return;
        }
        log.i(MetricKeys.VALUE_SIP_REGISTRATION_BEGIN_CALL);
        String callId = outgoingCallParams.getCallId();
        ParticipantImpl build = ParticipantImpl.builder().name(outgoingCallParams.getCallerName()).endpointDescription(outgoingCallParams.getCallerEndpointDescription()).providerSpecifiedId(outgoingCallParams.getCallerCommsId()).uri(outgoingCallParams.getCallerUri()).origin(Call.Side.Local).dropInPermission(outgoingCallParams.isDropInPermissionGrantedForCaller()).build();
        ParticipantImpl build2 = ParticipantImpl.builder().name(outgoingCallParams.getCalleeName()).endpointDescription(outgoingCallParams.getCalleeEndpointDescription()).providerSpecifiedId(outgoingCallParams.getCalleeCommsId()).uri(outgoingCallParams.getCalleeUri()).origin(Call.Side.Remote).build();
        log.i(String.format(Locale.US, "begin call for callId:%s, isDropIn:%b, calleeUri=%s, callerUri=%s", log.sensitiveCallId(callId), Boolean.valueOf(outgoingCallParams.isDropIn()), log.sensitive(build2.getUri()), log.sensitive(build.getUri())));
        this.orchestrator.beginOutgoingCall(build, build2, outgoingCallParams, eventTracer);
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public void configurePresence(boolean z) {
        if (this.orchestrator == null) {
            log.w("cannot configurePresence, no orchestrator! Initialize service before op");
            return;
        }
        CommsLogger commsLogger = log;
        commsLogger.i("Changing sendPresence to be: " + z);
        this.orchestrator.configurePresence(z);
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public void configureRegistrar(RegistrarConfiguration registrarConfiguration) {
        Preconditions.checkArgument(registrarConfiguration != null, "config must be non-null.");
        this.registrarConfiguration = registrarConfiguration;
        this.authTokenExpiryTime = getAuthTokenExpiryTime(registrarConfiguration.getAuthTokenIntervalInSecs());
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("configureRegistrar. authTokenExpiryTime= ");
        outline107.append(this.authTokenExpiryTime);
        outline107.append(" interval= ");
        outline107.append(registrarConfiguration.getAuthTokenIntervalInSecs());
        commsLogger.i(outline107.toString());
        SignalAndMediaOrchestrator signalAndMediaOrchestrator = this.orchestrator;
        if (signalAndMediaOrchestrator == null) {
            log.w("cannot configureRegistrar, no orchestrator! Initialize service before op.");
        } else {
            signalAndMediaOrchestrator.configureRegistrar(registrarConfiguration, this.authTokenExpiryTime);
        }
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public Call getCallByCallId(String str) {
        if (this.orchestrator == null) {
            return null;
        }
        return this.callMap.get(str);
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public List<Call> getCalls(Predicate<Call> predicate) {
        if (this.orchestrator == null) {
            return new LinkedList();
        }
        return ImmutableList.copyOf(Iterables.filter(this.callMap.values(), predicate));
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public HistoricalCall getHistoricalCallByCallId(String str) {
        SignalAndMediaOrchestrator signalAndMediaOrchestrator = this.orchestrator;
        if (signalAndMediaOrchestrator == null) {
            return null;
        }
        return signalAndMediaOrchestrator.getHistoricalCallByCallId(str);
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public Iterable<HistoricalCall> getHistoricalCalls() {
        SignalAndMediaOrchestrator signalAndMediaOrchestrator = this.orchestrator;
        if (signalAndMediaOrchestrator == null) {
            return new LinkedList();
        }
        return signalAndMediaOrchestrator.getHistoricalCalls();
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public WebRTCViewRenderer getLocalViewRenderer() {
        if (this.orchestrator == null) {
            log.w("Cannot give local WebRTCViewRenderer as orchestrator is null.Initialize service before op.");
            return null;
        }
        log.d("getLocalViewRenderer");
        CallImpl activeCall = this.orchestrator.getActiveCall();
        if (activeCall != null) {
            return activeCall.getLocalViewRenderer();
        }
        return null;
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public Call getMostRecentCall() {
        if (this.orchestrator == null) {
            return null;
        }
        return this.mostRecentCall;
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public RegistrarConfiguration getRegistrarConfiguration() {
        log.d("getRegistrarConfiguration");
        return this.registrarConfiguration;
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public WebRTCViewRenderer getRemoteViewRenderer() {
        if (this.orchestrator == null) {
            log.w("Cannot give remote WebRTCViewRenderer as orchestrator is null.Initialize service before op.");
            return null;
        }
        log.d("getRemoteViewRenderer");
        CallImpl activeCall = this.orchestrator.getActiveCall();
        if (activeCall != null) {
            return activeCall.getRemoteViewRenderer();
        }
        return null;
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public synchronized DeviceCallingService.State getState() {
        if (!this.state.equals(DeviceCallingService.State.Uninitialized) && !this.state.equals(DeviceCallingService.State.Initialized) && this.orchestrator != null) {
            return this.orchestrator.getOrchestratorState();
        }
        return this.state;
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public VolumeController getVolumeController() {
        return this;
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public void hangup() {
        if (this.orchestrator == null) {
            log.w("Cannot hangup, orchestrator is null.Initialize service before requesting op.");
            return;
        }
        log.i("hangup");
        this.orchestrator.hangupAll();
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public void initialize(Context context, EventTracerFactory eventTracerFactory, DeviceCallingServiceParams deviceCallingServiceParams) {
        boolean hasSystemFeature;
        boolean z = true;
        Preconditions.checkArgument(context != null, "Context must be non-null.");
        Preconditions.checkArgument(eventTracerFactory != null, "EventTracerFactory must be non-null.");
        DeviceCallingService.State state = getState();
        if (DeviceCallingService.State.Uninitialized != state) {
            z = false;
        }
        Preconditions.checkState(z, "initialize() has already been called. current state= " + state);
        log.i("Initializing DeviceCallingServiceImpl!");
        this.mainThreadHandler = new Handler(Looper.getMainLooper());
        this.orchestratorListener = new OrchestratorCallbackListener();
        this.deviceCallingServiceParams = deviceCallingServiceParams;
        this.applicationContext = context;
        if (deviceCallingServiceParams.isUseAlarmManagerAuthTokenManager()) {
            if (this.authTokenAlarmBroadcastReceiver == null) {
                this.authTokenAlarmBroadcastReceiver = new AuthTokenAlarmManagerReceiver(this);
            }
            registerAuthTokenAlarmReceiverIfNeeded();
        }
        String generateUserAgentStackVersionCode = generateUserAgentStackVersionCode();
        MetricsSession metricsSession = new MetricsSession(context, generateUserAgentStackVersionCode);
        if (deviceCallingServiceParams.getAvsDeviceFacade() != null) {
            hasSystemFeature = deviceCallingServiceParams.getAvsDeviceFacade().isCameraPresent();
        } else {
            hasSystemFeature = context.getPackageManager().hasSystemFeature("android.hardware.camera");
        }
        boolean z2 = hasSystemFeature;
        if (this.orchestrator != null) {
            log.w("Recreation of orchestrator! You may leak threads.Did you forget to call shutdown before re-initialization?");
        }
        this.orchestrator = SignalingAndMediaOrchestrator.provideInstance(this.mainThreadHandler, this.orchestratorListener, this.systemMediaEnabled, this.systemCameraEnabled, true, false);
        this.orchestrator.performInitialization(context, eventTracerFactory, deviceCallingServiceParams, metricsSession, z2, generateUserAgentInfoTemplate(generateUserAgentStackVersionCode), false);
        setState(DeviceCallingService.State.Initialized);
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public boolean isSystemMediaEnabled() {
        return this.systemMediaEnabled;
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public void reconnectCall(AuthenticationInfo authenticationInfo, MediaRelayInfo mediaRelayInfo) {
        if (this.orchestrator == null) {
            log.w("Cannot reconnectCall, orchestrator is null.Initialize service before requesting op.");
        } else if (authenticationInfo != null && mediaRelayInfo != null) {
            log.i("reconnectCall");
            this.orchestrator.reconnectCall(authenticationInfo, mediaRelayInfo);
        } else {
            log.e("Invalid parameters, cannot initiate call reconnection");
        }
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public void registerListener(DeviceCallingServiceListener deviceCallingServiceListener) {
        this.serviceListeners.add(deviceCallingServiceListener);
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public void setMediaConstraints(Map<String, Integer> map) {
        Preconditions.checkNotNull(map, "updatedMediaConstraints must be non-null.");
        if (this.orchestrator == null) {
            log.w("cannot setMediaConstraints, no orchestrator! Initialize service before op");
            return;
        }
        log.i("setMediaConstraints");
        this.orchestrator.setMediaConstraints(map);
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public void setSystemMediaState(boolean z) {
        CommsLogger commsLogger = log;
        commsLogger.i("setSystemMediaState. enabled= " + z);
        this.systemMediaEnabled = z;
        SignalAndMediaOrchestrator signalAndMediaOrchestrator = this.orchestrator;
        if (signalAndMediaOrchestrator == null) {
            log.w("cannot setSystemMediaState, no orchestrator! Initialize service before op");
        } else {
            signalAndMediaOrchestrator.setSystemMediaState(z);
        }
    }

    @Override // com.amazon.comms.calling.service.VolumeController
    public void setVolume(float f) {
        if (this.orchestrator == null) {
            log.w("cannot call volume, no orchestrator.");
            return;
        }
        log.i("setVolume. level= " + f);
        if (1.0f < f) {
            f = 1.0f;
        } else if (0.0f > f) {
            f = 0.0f;
        }
        this.orchestrator.setAllCallVolume(f);
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public void shutdown() {
        log.i("shutdown");
        this.orchestratorListener.instanceShutdown();
        setState(DeviceCallingService.State.Initialized);
        this.serviceListeners.clear();
        this.orchestrator.shutdownInstance(false);
        this.orchestrator = null;
        setState(DeviceCallingService.State.Uninitialized);
        this.mainThreadHandler = null;
        this.orchestratorListener = null;
        this.callMap.clear();
        this.mostRecentCall = null;
        if (this.authTokenAlarmBroadcastReceiver != null) {
            unregisterAuthTokenAlarmReceiverIfNeeded();
            this.authTokenAlarmBroadcastReceiver = null;
        }
        this.registrarConfiguration = null;
        this.deviceCallingServiceParams = null;
        this.applicationContext = null;
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public long timeLeftInAuthToken() {
        return this.authTokenExpiryTime - (SystemClock.elapsedRealtime() / 1000);
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public void unregisterListener(DeviceCallingServiceListener deviceCallingServiceListener) {
        this.serviceListeners.remove(deviceCallingServiceListener);
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public void updateAudioBitrateForCalls(int i) {
        SignalAndMediaOrchestrator signalAndMediaOrchestrator = this.orchestrator;
        if (signalAndMediaOrchestrator == null) {
            log.w("cannot updateAudioBitrateForCalls, no orchestrator!");
        } else if (i <= 0) {
            log.w("cannot updateAudioBitrateForCalls with non-positive ints!");
        } else {
            signalAndMediaOrchestrator.updateAudioBitrateForCalls(i);
        }
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public void updateAuthToken(String str, int i, boolean z) {
        this.authTokenExpiryTime = getAuthTokenExpiryTime(i);
        if (this.orchestrator == null) {
            log.w("cannot updateAuthToken, no orchestrator! Initialize service before op");
            return;
        }
        CommsLogger commsLogger = log;
        commsLogger.i("Updating AuthToken, forceRegister: " + z + " expiryTime= " + this.authTokenExpiryTime + " interval= " + i);
        this.orchestrator.updateAuthToken(new AuthTokenConfig(str, i, z), this.authTokenExpiryTime);
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public void updateConnectivityState(boolean z) {
        if (this.networkDisconnected == (!z) || this.orchestrator == null) {
            return;
        }
        CommsLogger commsLogger = log;
        commsLogger.i("updateConnectivityState: connected = " + z);
        this.orchestrator.updateConnectivityState(z);
        this.networkDisconnected = z ^ true;
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public void updateDeviceCapabilities(boolean z, boolean z2) {
        CommsLogger commsLogger = log;
        commsLogger.i("updateDeviceCapabilities: AudioCapable= " + z + " VideoCapable= " + z2);
        if (this.orchestrator == null) {
            log.w("cannot updateDeviceCapabilities, no orchestrator!");
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.add(Capability.AUDIO);
        }
        if (this.deviceCallingServiceParams.getAvsDeviceFacade() != null && this.deviceCallingServiceParams.getAvsDeviceFacade().isCameraPresent() && z2) {
            arrayList.add(Capability.VIDEO);
        }
        this.orchestrator.updateDeviceCapabilities(arrayList);
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public void updatePresenceInfo(String str) {
        if (this.orchestrator != null && !Strings.isNullOrEmpty(str)) {
            this.orchestrator.updatePresenceInfo(str);
        } else {
            log.i("cannot updatePresenceInfo. Invalid input or state");
        }
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public void updateSystemCameraState(boolean z) {
        CommsLogger commsLogger = log;
        commsLogger.i("updateSystemCameraState= " + z);
        if (this.orchestrator == null) {
            log.w("cannot updateCameraState, no orchestrator!");
            return;
        }
        if (!this.deviceCallingServiceParams.isCameraShutterPresent()) {
            updateDeviceCapabilities(true, z);
        }
        this.systemCameraEnabled = z;
        this.orchestrator.setSystemCameraState(z);
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingService
    public void warmup(int i) {
        if (this.orchestrator == null) {
            log.w("Cannot warmup, orchestrator is null.Initialize service before requesting op.");
            return;
        }
        CommsLogger commsLogger = log;
        commsLogger.i("warmup. maxDurationMs= " + i);
        this.orchestrator.warmupMediaPipeline(i);
    }
}
