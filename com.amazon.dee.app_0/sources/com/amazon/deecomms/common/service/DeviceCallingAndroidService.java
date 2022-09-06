package com.amazon.deecomms.common.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.featureservice.constants.FeatureConstants;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.CallFilters;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.calling.service.DeviceCallingServiceParams;
import com.amazon.comms.calling.service.ICallingApiBinder;
import com.amazon.comms.calling.sipclient.RegistrarConfiguration;
import com.amazon.comms.calling.sipclient.SipHeaders;
import com.amazon.comms.calling.sipclient.SipStatusCode;
import com.amazon.comms.instrumentation.EventTracerFactory;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.alexa.ModeSwitchHelper;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.auth.AuthTokenHelper;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallTimerManager;
import com.amazon.deecomms.calling.controller.CommandProcessor;
import com.amazon.deecomms.calling.controller.DeviceCallingServiceEventListener;
import com.amazon.deecomms.calling.controller.InitiateCall;
import com.amazon.deecomms.calling.core.CallInitiationAuthority;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.model.CallConfigurationsModel;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.calling.model.MediaRelayInfoModel;
import com.amazon.deecomms.calling.model.notification.CallInvite;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.calling.receiver.CallUIHandler;
import com.amazon.deecomms.calling.service.MediaButtonService;
import com.amazon.deecomms.calling.telecom.TelecomConstants;
import com.amazon.deecomms.calling.ui.InitiateCallService;
import com.amazon.deecomms.calling.ui.SIPRegistrationErrorDialogActivity;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.calling.util.ProximityLockHelper;
import com.amazon.deecomms.calling.util.SetupCallHelper;
import com.amazon.deecomms.common.CommsActivityMonitor;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.audio.AlexaAudioPlayer;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.GetCallConfigurations;
import com.amazon.deecomms.common.network.acmsrecipes.GetDeviceCommsPreferences;
import com.amazon.deecomms.common.receiver.CommsConnectivityMonitor;
import com.amazon.deecomms.common.receiver.UserSwitchReceiver;
import com.amazon.deecomms.common.service.DeviceCallingAndroidService;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.sip.SipStatusListener;
import com.amazon.deecomms.common.util.DeviceUtils;
import com.amazon.deecomms.common.util.JacksonJSONConverter;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.identity.CommsIdentityUtils;
import com.amazon.deecomms.notifications.PushNotificationManager;
import com.amazon.deecomms.notifications.util.NotificationUtils;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.amazon.deecomms.util.DeviceInfo;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Optional;
import dagger.Lazy;
import java.io.IOException;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import org.webrtc.MediaCodecVideoDecoder;
/* loaded from: classes12.dex */
public class DeviceCallingAndroidService extends Service implements CommsConnectivityMonitor.OnConnectionTypeChangedListener, SipStatusListener {
    private static final int AUTH_TOKEN_MIN_EXPIRATION_LEFT = 600000;
    private static final int CALL_INVITE_TIMEOUT = 15000;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DeviceCallingAndroidService.class);
    private static final int RESTART_SERVICE_DELAY = 1000;
    private static final int RESTART_SERVICE_REQUEST_CODE = 1001;
    private static final String SIP_REGISTRATION_FROM_OUTGOING_CALL = "SIP_REGISTRATION_FROM_OUTGOING_CALL";
    public static final long WAIT_FOR_CONNECTED_MAX_TIME = 5000;
    private static final int WAKE_LOCK_LEVEL_AND_FLAGS = 805306374;
    private static final int WAKE_LOCK_RELEASE_DELAY = 2000;
    @Inject
    AlarmManager alarmManager;
    @Inject
    ArcusConfig arcusConfig;
    @Inject
    @VisibleForTesting
    CallContext callContext;
    @Inject
    CallHistoryHelper callHistoryHelper;
    @Inject
    CallInitiationAuthority callInitiationAuthority;
    @Inject
    CallManager callManager;
    @Inject
    CallTimerManager callTimerManager;
    @Inject
    CallUIHandler callUIHandler;
    @Inject
    CapabilitiesManager capabilitiesManager;
    @Inject
    CommsActivityMonitor commsActivityMonitor;
    @Inject
    CommsConnectivityMonitor commsConnectivityMonitor;
    @Inject
    CommsIdentityManager commsIdentityManager;
    @Inject
    DeviceCallingService deviceCallingService;
    @Inject
    DeviceUtils deviceUtils;
    @Inject
    AlexaAudioPlayer mAlexaAudioPlayer;
    @Inject
    AudioManager mAudioManager;
    @Inject
    CapabilitiesManager mCapabilitiesManager;
    @Inject
    CommandProcessor mCommandProcessor;
    @Inject
    CommsNotificationManager mCommsNotificationManager;
    @Inject
    ConnectivityManager mConnectivityManager;
    @Inject
    Context mContext;
    @Inject
    DeviceCallingServiceParams mDeviceCallingServiceParams;
    @Inject
    EventTracerFactory mEventTracerFactory;
    @Inject
    ModeSwitchHelper mModeSwitchHelper;
    private CallTimerManager.NotificationUpdateListener mNotificationUpdateListener;
    private CustomPhoneStateListener mPhoneStateListener;
    @Inject
    TelephonyManager mTelephonyManager;
    private Intent mediaButtonIntent;
    private AsyncTask<Void, Void, AuthTokenHelper.AuthToken> networkReconnectTask;
    @Inject
    NotificationManager notificationManager;
    private Bundle outgoingCallBundle;
    @Inject
    PCCContextProvider pccContextProvider;
    @Inject
    PowerManager powerManager;
    @Inject
    ProvisioningManager provisioningManager;
    private ProximityLockHelper proximityLockHelper;
    @Inject
    Lazy<PushNotificationManager> pushNotificationManagerLazy;
    private DeviceCallingServiceEventListener serviceEventListener;
    @Inject
    @Named(Constants.Dagger.CURRENT_CALL_SIPSTATE)
    SipClientState sipClientState;
    private final DeviceCallingServiceBinder deviceCallingServiceBinder = new DeviceCallingServiceBinder(null);
    private final OutgoingCallBroadcastReceiver mOutgoingCallBroadcastReceiver = new OutgoingCallBroadcastReceiver(null);
    private boolean mIsBroadcastReceiverRegistered = false;
    private boolean deviceCallingServiceWasConfigured = false;
    private final Handler handler = new Handler(Looper.getMainLooper());
    protected final JacksonJSONConverter jsonConverter = new JacksonJSONConverter();
    private boolean shouldPlaceCallAfterSIPRegisteration = false;
    private final BroadcastReceiver deviceCallingAndroidServiceReceiver = new BroadcastReceiver() { // from class: com.amazon.deecomms.common.service.DeviceCallingAndroidService.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                DeviceCallingAndroidService.LOG.e("Intent is null and we cannot perform any action");
                return;
            }
            String action = intent.getAction();
            DeviceCallingAndroidService.LOG.i(String.format("onReceive: %s (%s)", action, DeviceCallingAndroidService.this.generateStatusString(null)));
            if (Constants.ACCOUNT_DEREGISTER_ACTION.equals(action)) {
                DeviceCallingAndroidService.LOG.i("Stopping Device Calling android service since account is de-registered");
                DeviceCallingAndroidService.this.mCommandProcessor.hangupAllCalls(Call.HangupRequest.ThisDevice);
                DeviceCallingAndroidService.this.stopSelf();
            } else if (Constants.APPLICATION_FOREGROUND_CHECKED_ACTION.equals(action)) {
                if (DeviceCallingAndroidService.this.commsActivityMonitor.isOnForeground()) {
                    DeviceCallingAndroidService.LOG.i("Application is now on the foreground");
                    MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.APP_FOREGROUND_SIP_REGISTER);
                    DeviceCallingAndroidService.this.activateCallingServiceIfNeeded(MetricKeys.VALUE_SIP_REGISTRATION_APP_FOREGROUNDED);
                    return;
                }
                DeviceCallingAndroidService.LOG.i("Application is now in the background");
                DeviceCallingAndroidService.this.deactivateCallingServiceIfNeeded();
            } else if ("android.os.action.POWER_SAVE_MODE_CHANGED".equals(action)) {
                if (Utils.isDeviceInPowerSaveMode()) {
                    DeviceCallingAndroidService.LOG.i("Entering power save mode");
                    DeviceCallingAndroidService.this.deactivateCallingServiceIfNeeded();
                    return;
                }
                DeviceCallingAndroidService.LOG.i("Exiting power save mode");
            } else if ("android.os.action.DEVICE_IDLE_MODE_CHANGED".equals(action)) {
                if (Utils.isDeviceInIdleMode()) {
                    DeviceCallingAndroidService.LOG.i("Entering device idle mode");
                    DeviceCallingAndroidService.this.deactivateCallingServiceIfNeeded();
                    return;
                }
                DeviceCallingAndroidService.LOG.i("Exiting device idle mode");
            } else if ("android.intent.action.SCREEN_OFF".equals(action)) {
                DeviceCallingAndroidService.LOG.i("Screen is off");
            } else if ("android.intent.action.SCREEN_ON".equals(action)) {
                DeviceCallingAndroidService.LOG.i("Screen is on");
            } else if (Constants.MODE_SWITCH_MULTI_MODAL.equals(action)) {
                DeviceCallingAndroidService.LOG.i("Mode has switched to Multimodal");
                DeviceCallingAndroidService.this.deactivateCallingServiceIfNeeded();
            } else if (Constants.MODE_SWITCH_TABLET.equals(action)) {
                DeviceCallingAndroidService.LOG.i("Mode has switched to Tablet");
                DeviceCallingAndroidService.this.activateCallingServiceIfNeeded(MetricKeys.VALUE_SIP_REGISTRATION_MODE_SWITCHED_TABLET);
            } else if (!Constants.DEVICE_COMMUNICATIONS_OFF.equals(action)) {
            } else {
                DeviceCallingAndroidService.LOG.i("Communications was turned off for this device.");
                DeviceCallingAndroidService.this.deactivateCallingServiceIfNeeded();
            }
        }
    };

    /* renamed from: com.amazon.deecomms.common.service.DeviceCallingAndroidService$4  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$comms$calling$service$DeviceCallingService$State = new int[DeviceCallingService.State.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState;

        static {
            try {
                $SwitchMap$com$amazon$comms$calling$service$DeviceCallingService$State[DeviceCallingService.State.Registered.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$DeviceCallingService$State[DeviceCallingService.State.Unregistered.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$DeviceCallingService$State[DeviceCallingService.State.Uninitialized.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState = new int[SipClientState.CallState.values().length];
            try {
                $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState[SipClientState.CallState.INACTIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState[SipClientState.CallState.CALLING_INITIATED.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState[SipClientState.CallState.CALLING.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState[SipClientState.CallState.INBOUND_RINGING.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState[SipClientState.CallState.OUTBOUND_RINGING.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState[SipClientState.CallState.ACTIVE.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState[SipClientState.CallState.LOCAL_HOLD.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState[SipClientState.CallState.REMOTE_HOLD.ordinal()] = 8;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    /* loaded from: classes12.dex */
    private class CustomPhoneStateListener extends PhoneStateListener {
        private CustomPhoneStateListener() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            if (Utils.getBooleanPreferenceFromSharedPrefs(DeviceCallingAndroidService.this.mContext, TelecomConstants.SHARED_PREF_KEY_TELECOM_SUPPORTED, false)) {
                DeviceCallingAndroidService.LOG.i("CustomPhoneListener obsolete. PSTN call states are managed by Telecom.");
                return;
            }
            super.onCallStateChanged(i, str);
            if (i != 2 || !DeviceCallingAndroidService.this.callManager.isInAlexaCallMode()) {
                return;
            }
            if (SipClientState.CallState.INBOUND_RINGING.equals(DeviceCallingAndroidService.this.sipClientState.getCallState())) {
                DeviceCallingAndroidService.LOG.i("Rejected incoming call since user answered the PSTN call");
                DeviceCallingAndroidService.this.mCommandProcessor.rejectIncomingCall();
            } else if (SipClientState.CallState.OUTBOUND_RINGING.equals(DeviceCallingAndroidService.this.sipClientState.getCallState())) {
                DeviceCallingAndroidService.LOG.i("Cancelling outgoing call since user answered the PSTN call");
                DeviceCallingAndroidService.this.mCommandProcessor.cancelOutgoingCall(DeviceCallingAndroidService.this.sipClientState.getCallId());
            } else if (DeviceCallingAndroidService.this.callManager.isAnyActiveCallPresent()) {
                DeviceCallingAndroidService.LOG.i("Ending Alexa Call since the user answered the PSTN call");
                DeviceCallingAndroidService.this.mCommandProcessor.endCurrentCall();
            } else {
                CommsLogger commsLogger = DeviceCallingAndroidService.LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("Call state changed. Unknown SIPClientState ");
                outline1.append(DeviceCallingAndroidService.this.sipClientState.getCallState());
                commsLogger.e(outline1.toString());
            }
        }

        /* synthetic */ CustomPhoneStateListener(AnonymousClass1 anonymousClass1) {
        }
    }

    /* loaded from: classes12.dex */
    private class DeviceCallingServiceBinder extends Binder implements ICallingApiBinder {
        private DeviceCallingServiceBinder() {
        }

        @Override // com.amazon.comms.calling.service.ICallingApiBinder
        public DeviceCallingService getCallingApi() {
            CommsLogger commsLogger = DeviceCallingAndroidService.LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("getCallingApi: state=");
            outline1.append(DeviceCallingAndroidService.this.deviceCallingService.getState());
            commsLogger.d(outline1.toString());
            return DeviceCallingAndroidService.this.deviceCallingService;
        }

        @Override // com.amazon.comms.calling.service.ICallingApiBinder
        public Service getService() {
            return DeviceCallingAndroidService.this;
        }

        /* synthetic */ DeviceCallingServiceBinder(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class OutgoingCallBroadcastReceiver extends BroadcastReceiver {
        private OutgoingCallBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String string = extras.getString("MESSAGE");
                if (!InitiateCallService.MAKE_CALL_MESSAGE.equals(string)) {
                    GeneratedOutlineSupport.outline3("Unknown Broadcast message: ", string, DeviceCallingAndroidService.LOG);
                    DeviceCallingAndroidService.this.handleCallInitiationFailure(context, extras);
                } else if (!extras.getBoolean(InitiateCallService.TRIGGER_MAKE_CALL_RESULT)) {
                    DeviceCallingAndroidService.LOG.e("Error in placing Outgoing call.");
                    DeviceCallingAndroidService.this.handleCallInitiationFailure(context, extras);
                } else {
                    SipClientState.CallState callState = DeviceCallingAndroidService.this.sipClientState.getCallState();
                    CallType fromBundle = CallType.fromBundle(extras, Constants.CALL_TYPE);
                    String fromBundle2 = CallProvider.fromBundle(extras, Constants.CALL_PROVIDER);
                    String string2 = extras.getString(Constants.CALLEE_COMMS_ID);
                    String string3 = extras.getString(Constants.CALLEE_SIP_URI);
                    String string4 = extras.getString(Constants.CALLER_COMMS_ID);
                    String string5 = extras.getString(Constants.CALLER_SIP_URI);
                    String string6 = extras.getString(Constants.MEDIA_RELAY_INFO_JSON);
                    ContactPhoneNumber contactPhoneNumber = (ContactPhoneNumber) extras.getParcelable(Constants.KEY_RECIPIENT_PHONE_NUMBER);
                    String string7 = extras.getString(Constants.CALL_ID);
                    long j = extras.getLong(Constants.CALL_START_TIME);
                    if (j <= 0) {
                        DeviceCallingAndroidService.LOG.e("CALL_START_TIME missing when making a call, will just use the time now instead");
                        j = System.currentTimeMillis();
                    }
                    long j2 = j;
                    MediaRelayInfoModel constructMediaRelayInfoFromJSON = Utils.constructMediaRelayInfoFromJSON(string6);
                    if (callState == SipClientState.CallState.INACTIVE) {
                        DeviceCallingAndroidService.this.handleCallInitiationFailure(context, extras);
                    } else if (DeviceCallingAndroidService.this.deviceCallingService.getState() != DeviceCallingService.State.Registered) {
                        DeviceCallingAndroidService.this.activateCallingServiceIfNeeded("Outgoing Call");
                        DeviceCallingAndroidService.this.handleCallInitiationFailure(context, extras);
                    } else if (DeviceCallingAndroidService.this.mCommandProcessor.makeACall(string2, string3, contactPhoneNumber, string4, string5, fromBundle, fromBundle2, constructMediaRelayInfoFromJSON, j2, string7, extras.getString(Constants.KEY_CALL_INITIATION_SCREEN_NAME, null))) {
                    } else {
                        if (!DeviceCallingAndroidService.this.callManager.isAnyActiveCallPresent()) {
                            DeviceCallingAndroidService.LOG.i("Cannot place a call and no active calls also are present");
                            DeviceCallingAndroidService.this.sipClientState.setCallState(SipClientState.CallState.INACTIVE);
                            DeviceCallingAndroidService.this.sipClientState.setCallType(CallType.NONE);
                            DeviceCallingAndroidService.this.sipClientState.setCallProvider("");
                            DeviceCallingAndroidService.this.sipClientState.setRemoteParticipantId(null);
                            DeviceCallingAndroidService.this.sipClientState.setRemoteParticipantName(null);
                            DeviceCallingAndroidService.this.callManager.setCallActivityLaunchedOnce(false);
                            return;
                        }
                        DeviceCallingAndroidService.LOG.i("Ongoing call is present and hence cannot make a call");
                    }
                }
            }
        }

        /* synthetic */ OutgoingCallBroadcastReceiver(AnonymousClass1 anonymousClass1) {
        }
    }

    /* loaded from: classes12.dex */
    public class PerformSIPRegistration extends AsyncTask<Void, Void, String> {
        private AuthTokenHelper.AuthToken authToken;
        private final String callToken;
        private final String callTokenVersion;
        private PowerManager.WakeLock wakeLock;

        public PerformSIPRegistration() {
            this.callToken = null;
            this.callTokenVersion = null;
        }

        private void acquireWakeLockIfNeeded() {
            if (this.wakeLock == null) {
                DeviceCallingAndroidService.LOG.d("Creating wake lock...");
                DeviceCallingAndroidService deviceCallingAndroidService = DeviceCallingAndroidService.this;
                this.wakeLock = deviceCallingAndroidService.powerManager.newWakeLock(DeviceCallingAndroidService.WAKE_LOCK_LEVEL_AND_FLAGS, deviceCallingAndroidService.getClass().getSimpleName());
            }
            PowerManager.WakeLock wakeLock = this.wakeLock;
            if (wakeLock == null || wakeLock.isHeld()) {
                return;
            }
            DeviceCallingAndroidService.LOG.d("Acquiring wake lock...");
            this.wakeLock.acquire();
            if (this.wakeLock.isHeld()) {
                DeviceCallingAndroidService.LOG.i("Acquired wake lock.");
            } else {
                DeviceCallingAndroidService.LOG.e("Failed to acquire wake lock.");
            }
        }

        private boolean hasCallToken() {
            return !TextUtils.isEmpty(this.callToken);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void releaseWakeLockIfNeeded() {
            PowerManager.WakeLock wakeLock = this.wakeLock;
            if (wakeLock != null && wakeLock.isHeld()) {
                DeviceCallingAndroidService.LOG.d("Releasing wake lock");
                this.wakeLock.release();
                if (!this.wakeLock.isHeld()) {
                    DeviceCallingAndroidService.LOG.i("Released wake lock.");
                } else {
                    DeviceCallingAndroidService.LOG.e("Failed to release wake lock.");
                }
            }
            this.wakeLock = null;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            if (hasCallToken()) {
                acquireWakeLockIfNeeded();
                DeviceCallingAndroidService.this.initializeCallingServiceIfNeeded();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(Void... voidArr) {
            DeviceCallingAndroidService.LOG.i("Fetching auth token");
            DeviceCallingServiceEventListener deviceCallingServiceEventListener = DeviceCallingAndroidService.this.serviceEventListener;
            String str = null;
            if (deviceCallingServiceEventListener == null) {
                DeviceCallingAndroidService.LOG.e("Cannot perform SIP registration if DCSEL is null");
                releaseWakeLockIfNeeded();
                return null;
            } else if (!DeviceCallingAndroidService.this.commsConnectivityMonitor.waitForConnected(5000L)) {
                DeviceCallingAndroidService.LOG.w("Internet is not connected and we cannot perform SIP Registration");
                releaseWakeLockIfNeeded();
                return null;
            } else {
                String commsId = DeviceCallingAndroidService.this.commsIdentityManager.getCommsId("PerformSIPRegistration", false);
                if (commsId == null) {
                    DeviceCallingAndroidService.LOG.w("Cannot retrieve commsId for the registered user");
                    releaseWakeLockIfNeeded();
                    return null;
                }
                DeviceCallingAndroidService deviceCallingAndroidService = DeviceCallingAndroidService.this;
                if (!GetDeviceCommsPreferences.isDeviceCommunicationsEnabled(deviceCallingAndroidService.mContext, deviceCallingAndroidService.mCapabilitiesManager)) {
                    DeviceCallingAndroidService.LOG.i("Communications is not enabled for this device, so do not perform SIP registration.");
                    return null;
                } else if (deviceCallingServiceEventListener.setAttemptingRegistration(true) && !hasCallToken()) {
                    CommsLogger commsLogger = DeviceCallingAndroidService.LOG;
                    StringBuilder outline1 = GeneratedOutlineSupport.outline1("Cannot perform SIP registration when another is in progress. Call Token is available: ");
                    outline1.append(hasCallToken());
                    commsLogger.w(outline1.toString());
                    MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.SIP_REG_FAIL_AS_ANOTHER_REG_IN_PROGRESS);
                    releaseWakeLockIfNeeded();
                    return null;
                } else {
                    this.authToken = AuthTokenHelper.getSavedAuthToken(DeviceCallingAndroidService.this.mContext, AuthTokenHelper.TokenType.SIP, FeatureConstants.SESSION_CHANGE_THRESHOLD_IN_MILLISECONDS);
                    if (this.authToken != null) {
                        DeviceCallingAndroidService.LOG.i("Using cached token...");
                    } else {
                        DeviceCallingAndroidService.LOG.i("No usable cached token, fetching new token...");
                        this.authToken = AuthTokenHelper.fetchAuthToken(commsId, AuthTokenHelper.TokenType.SIP, true);
                    }
                    AuthTokenHelper.AuthToken authToken = this.authToken;
                    if (authToken == null || TextUtils.isEmpty(authToken.getValue())) {
                        DeviceCallingAndroidService.LOG.e("Fetched auth token is null");
                        deviceCallingServiceEventListener.setAttemptingRegistration(false);
                        releaseWakeLockIfNeeded();
                        DeviceCallingAndroidService.this.provisioningManager.checkStatus();
                        if (Utils.getStringPreferenceFromSharedPrefs(DeviceCallingAndroidService.this.mContext, DeviceCallingAndroidService.SIP_REGISTRATION_FROM_OUTGOING_CALL, null) != null) {
                            Utils.writeStringPreferenceToSharedPrefs(DeviceCallingAndroidService.this.mContext, DeviceCallingAndroidService.SIP_REGISTRATION_FROM_OUTGOING_CALL, null);
                            if (DeviceCallingAndroidService.this.mCapabilitiesManager.isAuthTokenErrorDialogEnabled()) {
                                Intent intent = new Intent(DeviceCallingAndroidService.this.mContext, SIPRegistrationErrorDialogActivity.class);
                                intent.setFlags(276824064);
                                DeviceCallingAndroidService.this.mContext.startActivity(intent);
                            }
                        }
                        return null;
                    }
                    CommsLogger commsLogger2 = DeviceCallingAndroidService.LOG;
                    StringBuilder outline12 = GeneratedOutlineSupport.outline1("Auth token: ");
                    outline12.append(this.authToken);
                    commsLogger2.d(outline12.toString());
                    String aor = DeviceCallingAndroidService.this.commsIdentityManager.getAor();
                    if (TextUtils.isEmpty(aor)) {
                        try {
                            str = CommsIdentityUtils.fetchAndStoreUserAOR(DeviceCallingAndroidService.this.mContext);
                        } catch (ServiceException unused) {
                            DeviceCallingAndroidService.LOG.e("Error occurred trying to fetchAndStoreUserAOR -- setting aor to null");
                        }
                        aor = str;
                        if (TextUtils.isEmpty(aor)) {
                            deviceCallingServiceEventListener.setAttemptingRegistration(false);
                        }
                    }
                    return aor;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onCancelled(String str) {
            super.onCancelled((PerformSIPRegistration) str);
            releaseWakeLockIfNeeded();
            DeviceCallingAndroidService.this.deactivateCallingServiceIfNeeded();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            if (TextUtils.isEmpty(str)) {
                DeviceCallingAndroidService.LOG.w("Cannot perform SIP registration.");
                releaseWakeLockIfNeeded();
                return;
            }
            Map<String, String> userNameAndDomainNameFromAOR = Utils.getUserNameAndDomainNameFromAOR(str);
            String str2 = userNameAndDomainNameFromAOR.get(Constants.USER_NAME);
            String str3 = userNameAndDomainNameFromAOR.get(Constants.DOMAIN);
            if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
                DeviceCallingAndroidService.LOG.w("Cannot perform SIP registration as username/domain is null");
                DeviceCallingAndroidService.this.serviceEventListener.setAttemptingRegistration(false);
                releaseWakeLockIfNeeded();
                return;
            }
            CommsLogger commsLogger = DeviceCallingAndroidService.LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("onPostExecute sip userid: ");
            outline1.append(DeviceCallingAndroidService.LOG.sensitive(str2));
            commsLogger.i(outline1.toString());
            DeviceCallingAndroidService.this.mContext.getSharedPreferences("SHARED_PREFS", 0).edit().putString(Constants.SIP_USER_AOR, str).apply();
            RegistrarConfiguration.RegistrarConfigurationBuilder createBaseRegistrarConfigurationBuilder = DeviceCallingAndroidService.this.mCommandProcessor.createBaseRegistrarConfigurationBuilder(str3, str2, DeviceCallingAndroidService.this.getSIPRegistrationInstanceID(), str3, this.callToken, this.callTokenVersion);
            createBaseRegistrarConfigurationBuilder.authToken(this.authToken.getValue()).authTokenIntervalInSecs(this.authToken.getExpiryInSecs());
            CommsLogger commsLogger2 = DeviceCallingAndroidService.LOG;
            StringBuilder outline12 = GeneratedOutlineSupport.outline1("Registering SIP: token=");
            outline12.append(this.authToken);
            commsLogger2.d(outline12.toString());
            DeviceCallingAndroidService.this.mCommandProcessor.configureRegistrar(createBaseRegistrarConfigurationBuilder.build());
            DeviceCallingAndroidService.this.deviceCallingServiceWasConfigured = true;
            if (this.wakeLock == null) {
                return;
            }
            DeviceCallingAndroidService.this.handler.postDelayed(new Runnable() { // from class: com.amazon.deecomms.common.service.-$$Lambda$DeviceCallingAndroidService$PerformSIPRegistration$zJU9vRehOOAx4mweXJ8YizQUmH4
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceCallingAndroidService.PerformSIPRegistration.this.releaseWakeLockIfNeeded();
                }
            }, 2000L);
        }

        public PerformSIPRegistration(String str, String str2) {
            this.callToken = str;
            this.callTokenVersion = str2;
        }

        @Override // android.os.AsyncTask
        protected void onCancelled() {
            super.onCancelled();
            releaseWakeLockIfNeeded();
            DeviceCallingAndroidService.this.deactivateCallingServiceIfNeeded();
        }
    }

    public DeviceCallingAndroidService() {
        CommsDaggerWrapper.getComponent().inject(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void activateCallingServiceIfNeeded(String str) {
        GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport.outline1("activateCallingService: "), generateStatusString(null), LOG);
        if (this.commsActivityMonitor.isOnForeground() && this.powerManager.isInteractive() && !Utils.isDeviceInIdleMode() && !Utils.isDeviceInPowerSaveMode() && this.mModeSwitchHelper.isTabletMode() && GetDeviceCommsPreferences.isDeviceCommunicationsEnabled(this.mContext, this.capabilitiesManager)) {
            if (!initializeCallingServiceIfNeeded() && this.sipClientState.getSipRegistrationStatus() == DeviceCallingService.State.Registered) {
                LOG.i("activateCallingService: calling service was already started and registered");
                return;
            }
            LOG.i("activateCallingService: registering to SIP...");
            new PerformSIPRegistration().execute(new Void[0]);
            setSIPRegistrationReason(this.mContext, str);
            return;
        }
        LOG.w("activateCallingService: no need to activate device calling service");
    }

    private void cancelNetworkReconnectTask() {
        if (this.networkReconnectTask != null) {
            LOG.i("Cancelling network reconnect task");
            this.networkReconnectTask.cancel(true);
            this.networkReconnectTask = null;
        }
    }

    private void cleanupAlexaCall() {
        if (this.mIsBroadcastReceiverRegistered) {
            LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(this.mOutgoingCallBroadcastReceiver);
            this.mIsBroadcastReceiverRegistered = false;
        }
        resetAudio();
        LOG.i("Moving service to background");
        stopForeground(true);
        this.callTimerManager.removeListener(this.mNotificationUpdateListener);
    }

    public static void clearSIPRegistrationReason(Context context) {
        Utils.removePreferenceFromSharedPrefs(context, MetricKeys.SIP_REG_FAIL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deactivateCallingServiceIfNeeded() {
        boolean isAnyActiveCallPresent = this.callManager.isAnyActiveCallPresent();
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("deactivateCallingService: ");
        outline1.append(generateStatusString(null));
        outline1.append(", activeCall=");
        outline1.append(isAnyActiveCallPresent);
        commsLogger.i(outline1.toString());
        if (!isAnyActiveCallPresent && (!this.commsActivityMonitor.isOnForeground() || !this.powerManager.isInteractive() || Utils.isDeviceInIdleMode() || Utils.isDeviceInPowerSaveMode() || this.mModeSwitchHelper.isMultiModalMode() || !GetDeviceCommsPreferences.isDeviceCommunicationsEnabled(this.mContext, this.capabilitiesManager))) {
            LOG.i("deactivateCallingService: shutting down device calling service...");
            performPushRegistration();
            cancelNetworkReconnectTask();
            shutdownCallingServiceIfNeeded();
            return;
        }
        LOG.w("deactivateCallingService: no need to deactivate device calling service");
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public String generateStatusString(@Nullable DeviceCallingService.State state) {
        Object[] objArr = new Object[7];
        if (state == null) {
            state = this.sipClientState.getSipRegistrationStatus();
        }
        objArr[0] = state;
        objArr[1] = Boolean.valueOf(this.powerManager.isInteractive());
        objArr[2] = Boolean.valueOf(this.commsActivityMonitor.isOnForeground());
        objArr[3] = Boolean.valueOf(Utils.isDeviceInIdleMode());
        objArr[4] = Boolean.valueOf(Utils.isDeviceInPowerSaveMode());
        objArr[5] = Boolean.valueOf(this.commsConnectivityMonitor.isConnected());
        objArr[6] = Boolean.valueOf(this.mModeSwitchHelper.isMultiModalMode());
        return String.format("status=%s, isInteractive=%b, isOnForeground=%b, deviceIdleMode=%b, powerSaveMode=%b, connected=%b, multiModalMode=%b", objArr);
    }

    public static String getSIPRegistrationReason(Context context) {
        return Utils.getStringPreferenceFromSharedPrefs(context, MetricKeys.SIP_REG_FAIL, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCallInitiationFailure(@NonNull Context context, @NonNull Bundle bundle) {
        String remoteParticipantName = this.sipClientState.getRemoteParticipantName();
        String callId = this.sipClientState.getCallId();
        this.mAlexaAudioPlayer.stop(1);
        this.sipClientState.setCallId(null);
        this.sipClientState.setCallState(SipClientState.CallState.INACTIVE);
        this.sipClientState.setCallType(CallType.NONE);
        this.sipClientState.setCallProvider("");
        this.sipClientState.setRemoteParticipantId(null);
        this.sipClientState.setRemoteParticipantName(null);
        this.callManager.setCallActivityLaunchedOnce(false);
        cleanupAlexaCall();
        int i = bundle.getInt(InitiateCallService.ERROR_CODE, 0);
        if (i != SipStatusCode.FORBIDDEN.getCode()) {
            this.proximityLockHelper.markCallEnded();
            return;
        }
        LOG.d("Ending call activity.");
        String string = bundle.getString("requestId");
        String string2 = bundle.getString("source");
        CounterMetric generateClickstream = CounterMetric.generateClickstream(MetricKeys.CALL_BLOCKED);
        Map<String, Object> metadata = generateClickstream.getMetadata();
        metadata.put("statusCode", Integer.valueOf(i));
        if (!TextUtils.isEmpty(string2)) {
            metadata.put("errorSource", string2);
        }
        if (!TextUtils.isEmpty(string)) {
            metadata.put("requestId", string);
        }
        if (!TextUtils.isEmpty(callId)) {
            metadata.put(MetricKeys.META_COMMS_ITEM_ID, callId);
        }
        MetricsHelper.recordSingleOccurrence(generateClickstream);
        Intent intent = new Intent();
        intent.putExtra(Constants.REMOTE_PARTICIPANT_NAME, remoteParticipantName);
        intent.putExtra(Constants.LAUNCH_FRAGMENT_KEY, Constants.FRAGMENT_END_CALL_KEY);
        intent.setAction(Constants.SHOW_CALL_UI);
        intent.putExtra(Constants.CALL_END_STATUS, context.getString(R.string.callee_unavailable));
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(intent);
        this.proximityLockHelper.markCallEnded();
    }

    private void initializeAudioForCall(boolean z) {
        LOG.d("Request STREAM_VOICE_CALL transient audio focus");
        if (this.mAudioManager.requestAudioFocus(null, 0, 2) != 1) {
            LOG.e("Not able to acquire Audio Focus");
        }
        if (z) {
            Utils.setAudioMode(this.mAudioManager);
        }
        resetMicSpeakerState();
        if (CallUtils.isVideoOrDropInVideoCall() && !this.callInitiationAuthority.isNewCallInitiationUIFlowEnabled(Optional.fromNullable(this.sipClientState.getCallProvider()), Optional.absent())) {
            if (this.mAudioManager.isWiredHeadsetOn()) {
                LOG.i("Headset plugged in, initially");
                this.mAudioManager.setSpeakerphoneOn(false);
                CommsLogger commsLogger = LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("Tried to turn speakerphone false; speakerphone was set to ");
                outline1.append(this.mAudioManager.isSpeakerphoneOn());
                commsLogger.i(outline1.toString());
            } else {
                LOG.i("Speaker turned on");
                this.mAudioManager.setSpeakerphoneOn(true);
                CommsLogger commsLogger2 = LOG;
                StringBuilder outline12 = GeneratedOutlineSupport.outline1("Tried to turn speakerphone true; speakerphone was set to ");
                outline12.append(this.mAudioManager.isSpeakerphoneOn());
                commsLogger2.i(outline12.toString());
            }
        } else if (!DeviceInfo.isPhone(this.mContext) && !this.callInitiationAuthority.isNewCallInitiationUIFlowEnabled(Optional.fromNullable(this.sipClientState.getCallProvider()), Optional.absent())) {
            this.mAudioManager.setSpeakerphoneOn(true);
            CommsLogger commsLogger3 = LOG;
            StringBuilder outline13 = GeneratedOutlineSupport.outline1("Tried to turn speakerphone true; speakerphone was set to ");
            outline13.append(this.mAudioManager.isSpeakerphoneOn());
            commsLogger3.i(outline13.toString());
        }
        if (!Utils.getBooleanPreferenceFromSharedPrefs(this.mContext, TelecomConstants.SHARED_PREF_KEY_TELECOM_SUPPORTED, false)) {
            this.mContext.startService(this.mediaButtonIntent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean initializeCallingServiceIfNeeded() {
        if (this.deviceCallingService.getState() == DeviceCallingService.State.Uninitialized) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Initializing device calling service... ");
            outline1.append(generateStatusString(null));
            commsLogger.d(outline1.toString());
            this.deviceCallingService.initialize(this.mContext, this.mEventTracerFactory, this.mDeviceCallingServiceParams);
            this.deviceCallingService.configurePresence(false);
            this.deviceCallingService.registerListener(this.serviceEventListener);
            LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(new Intent(Constants.COMMS_DEVICE_CALLING_SERVICE_REGISTERED_NOTIFICATION));
            updateConfigurations();
            GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport.outline1("Initialized device calling service. "), generateStatusString(null), LOG);
            return true;
        }
        return false;
    }

    private void onInitialStartCommand(boolean z) {
        if (z || !this.commsIdentityManager.isCommsIdPopulated("DeviceCallingAndroidService.onInitialStartCommand", false)) {
            return;
        }
        LOG.i("sip registrar configure");
        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.CALLING_SERVICE_START_SIP_REGISTER);
        activateCallingServiceIfNeeded(MetricKeys.VALUE_SIP_REGISTRATION_APP_FOREGROUNDED);
        LOG.i("register for push if necessary");
        performPushRegistration();
    }

    private void onNetworkConnected(@NonNull CommsConnectivityMonitor.ConnectionType connectionType, @NonNull CommsConnectivityMonitor.ConnectionType connectionType2) {
        CommsLogger commsLogger = LOG;
        StringBuilder sb = new StringBuilder();
        final boolean z = true;
        sb.append(String.format("onNetworkConnected: %s -> %s, ", connectionType, connectionType2));
        sb.append(generateStatusString(null));
        commsLogger.i(sb.toString());
        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NETWORK_CONNECTED);
        if (this.deviceCallingService.getState() == DeviceCallingService.State.Uninitialized) {
            LOG.d("onNetworkConnected: Ignoring when device calling service is not initialized");
        } else if (!this.deviceCallingServiceWasConfigured) {
            LOG.d("onNetworkConnected: calling service was never configured");
        } else {
            final String commsId = this.commsIdentityManager.getCommsId("onNetworkConnected", false);
            if (TextUtils.isEmpty(commsId)) {
                CommsLogger commsLogger2 = LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("onNetworkConnected: invalid commsId: ");
                outline1.append(LOG.sensitive(commsId));
                commsLogger2.e(outline1.toString());
            } else if (this.networkReconnectTask != null) {
                LOG.d("Network reconnect task is already running");
            } else {
                if (connectionType2.getConnectionCount() < 2 || this.deviceCallingService.getCalls(CallFilters.DEFAULT).size() <= 0) {
                    z = false;
                }
                this.networkReconnectTask = new AsyncTask<Void, Void, AuthTokenHelper.AuthToken>() { // from class: com.amazon.deecomms.common.service.DeviceCallingAndroidService.2
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public AuthTokenHelper.AuthToken doInBackground(Void... voidArr) {
                        if (isCancelled()) {
                            return null;
                        }
                        AuthTokenHelper.AuthToken fetchAuthToken = AuthTokenHelper.fetchAuthToken(commsId, AuthTokenHelper.TokenType.SIP, FeatureConstants.SESSION_CHANGE_THRESHOLD_IN_MILLISECONDS, false);
                        if (fetchAuthToken != null && !TextUtils.isEmpty(fetchAuthToken.getValue())) {
                            CommsLogger commsLogger3 = DeviceCallingAndroidService.LOG;
                            commsLogger3.d("onNetworkConnected: fetched new token: " + fetchAuthToken);
                        } else {
                            DeviceCallingAndroidService.this.provisioningManager.checkStatus();
                            DeviceCallingAndroidService.LOG.e("onNetworkConnected: failed getting a new auth token");
                        }
                        return fetchAuthToken;
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public void onCancelled(AuthTokenHelper.AuthToken authToken) {
                        super.onCancelled((AnonymousClass2) authToken);
                        DeviceCallingAndroidService.this.networkReconnectTask = null;
                        DeviceCallingAndroidService.LOG.d("onNetworkConnected: auth token fetch was cancelled");
                        if (authToken != null) {
                            DeviceCallingAndroidService.LOG.i("onNetworkConnected: updating calling service's auth token");
                            DeviceCallingAndroidService.this.deviceCallingService.updateAuthToken(authToken.getValue(), authToken.getExpiryInSecs(), false);
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public void onPostExecute(AuthTokenHelper.AuthToken authToken) {
                        super.onPostExecute((AnonymousClass2) authToken);
                        DeviceCallingAndroidService.this.networkReconnectTask = null;
                        if (authToken != null) {
                            DeviceCallingAndroidService.LOG.i("onNetworkConnected: updating calling service's auth token");
                            DeviceCallingAndroidService.this.deviceCallingService.updateAuthToken(authToken.getValue(), authToken.getExpiryInSecs(), false);
                        }
                        if (!isCancelled()) {
                            DeviceCallingAndroidService.this.performNetworkReconnect(z);
                        }
                    }

                    @Override // android.os.AsyncTask
                    protected void onCancelled() {
                        super.onCancelled();
                        DeviceCallingAndroidService.this.networkReconnectTask = null;
                        DeviceCallingAndroidService.LOG.d("onNetworkConnected: auth token fetch was cancelled");
                    }
                };
                this.networkReconnectTask.execute(new Void[0]);
            }
        }
    }

    private void onNetworkDisconnected() {
        GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport.outline1("onNetworkDisconnected: "), generateStatusString(null), LOG);
        cancelNetworkReconnectTask();
        if (this.deviceCallingService.getState() == DeviceCallingService.State.Uninitialized) {
            LOG.d("onNetworkDisconnected: Ignoring when device calling service is not initialized");
            return;
        }
        updateNetworkConnectivityState(false);
        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NETWORK_DISCONNECTED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performNetworkReconnect(boolean z) {
        CommsLogger commsLogger = LOG;
        commsLogger.d("performNetworkReconnect: ignoreReconnect=" + z);
        if (!z) {
            updateNetworkConnectivityState(false);
        }
        updateNetworkConnectivityState(true);
    }

    private void performPushRegistration() {
        this.pushNotificationManagerLazy.mo358get().registerForPush();
    }

    private void performSIPRegistrationForCallInvite(String str) {
        PerformSIPRegistration performSIPRegistration;
        LOG.i("Perform SIP registration, handle payload to be able to receive sip INVITE.");
        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.PUSH_NOTIFICATION_SIP_REGISTER);
        if (!TextUtils.isEmpty(str)) {
            CallInvite callInvite = (CallInvite) this.jsonConverter.fromJson(str, CallInvite.class);
            this.callContext.setRemoteParticipantName(callInvite.getTokenCallerName());
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("CALLING push payload parsed, sending token and token version: ");
            outline1.append(callInvite.getTokenVersion());
            commsLogger.i(outline1.toString());
            postCallNotificationTimeoutRunnable();
            setSIPRegistrationReason(this.mContext, MetricKeys.VALUE_SIP_REGISTRATION_PUSH_NOTIFICATION);
            performSIPRegistration = new PerformSIPRegistration(callInvite.getTokenValue(), callInvite.getTokenVersion());
        } else {
            LOG.w("CALLING push payload not valid: no payload available");
            performSIPRegistration = new PerformSIPRegistration();
        }
        performSIPRegistration.execute(new Void[0]);
    }

    private void playEarconAndPostNotification(@NonNull CallType callType) {
        if (callType.isA2A() && !callType.isDropIn()) {
            this.mAudioManager.setMode(3);
            LOG.i("Audio mode is set to communication");
            this.mAlexaAudioPlayer.play(R.raw.alexa_app_system_comms_outbound_ringtone, true, 0, 1);
        }
        Notification pendingNotificationForFGService = this.mCommsNotificationManager.getPendingNotificationForFGService("");
        if (pendingNotificationForFGService != null) {
            LOG.i("Moving service to foreground");
            startForeground(2, pendingNotificationForFGService);
            return;
        }
        LOG.e("NULL notification received");
    }

    private void postCallNotificationTimeoutRunnable() {
        new Handler().postDelayed(new Runnable() { // from class: com.amazon.deecomms.common.service.-$$Lambda$DeviceCallingAndroidService$nfAE_3mHwbmc1aSUFLy3TqUo8Hw
            @Override // java.lang.Runnable
            public final void run() {
                DeviceCallingAndroidService.this.lambda$postCallNotificationTimeoutRunnable$1$DeviceCallingAndroidService();
            }
        }, 15000L);
    }

    private void reconnectCall(@NonNull CommsConnectivityMonitor.ConnectionType connectionType, @NonNull CommsConnectivityMonitor.ConnectionType connectionType2) {
        CommsLogger commsLogger = LOG;
        commsLogger.i(String.format("%s -> %s: reconnecting call media for call %s", connectionType, connectionType2, commsLogger.sensitive(this.sipClientState.getCallId())));
        new PerformCallReconnectTask(Utils.getSipURIforRegisteredUser(this.mContext), this.sipClientState.getRemoteParticipantId(), this.sipClientState.getRemoteUri()).execute(new Void[0]);
    }

    private void resetAudio() {
        this.mAudioManager.abandonAudioFocus(null);
        resetMicSpeakerState();
        this.mAudioManager.setMode(0);
        LOG.i("Audio mode is set to normal");
        if (!Utils.getBooleanPreferenceFromSharedPrefs(this.mContext, TelecomConstants.SHARED_PREF_KEY_TELECOM_SUPPORTED, false)) {
            this.mContext.stopService(this.mediaButtonIntent);
        }
    }

    private void resetMicSpeakerState() {
        this.mAudioManager.setMicrophoneMute(false);
        this.mAudioManager.setSpeakerphoneOn(false);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Tried to turn speakerphone false; speakerphone was set to ");
        outline1.append(this.mAudioManager.isSpeakerphoneOn());
        commsLogger.i(outline1.toString());
    }

    public static void setSIPRegistrationReason(Context context, String str) {
        if (TextUtils.isEmpty(getSIPRegistrationReason(context))) {
            Utils.writeStringPreferenceToSharedPrefs(context, MetricKeys.SIP_REG_FAIL, str);
        }
    }

    private void shutdownCallingServiceIfNeeded() {
        if (this.deviceCallingService.getState() != DeviceCallingService.State.Uninitialized) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Shutting down device calling service... ");
            outline1.append(generateStatusString(null));
            commsLogger.d(outline1.toString());
            this.deviceCallingService.shutdown();
            this.outgoingCallBundle = null;
            this.deviceCallingServiceWasConfigured = false;
            GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport.outline1("Shut down device calling service. "), generateStatusString(null), LOG);
        }
    }

    private void updateConfigurations() {
        try {
            CommsConnectivityMonitor.ConnectionType connectionType = this.commsConnectivityMonitor.getConnectionType();
            boolean hasWifi = connectionType.hasWifi();
            final boolean z = true;
            boolean z2 = connectionType.hasCellular() && this.commsConnectivityMonitor.isMobileDataEnabled();
            if (!hasWifi || z2) {
                z = false;
            }
            new AsyncTask<Void, Void, Void>() { // from class: com.amazon.deecomms.common.service.DeviceCallingAndroidService.3
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Void doInBackground(Void... voidArr) {
                    try {
                        CallConfigurationsModel execute = new GetCallConfigurations().execute("Audio", z ? "wifi" : "default");
                        CommsLogger commsLogger = DeviceCallingAndroidService.LOG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("new audio bitrate set to= ");
                        sb.append(execute.configurations.audioStartBitrateInKbps);
                        commsLogger.i(sb.toString());
                        DeviceCallingAndroidService.this.deviceCallingService.updateAudioBitrateForCalls(execute.configurations.audioStartBitrateInKbps);
                        return null;
                    } catch (ServiceException | IOException e) {
                        DeviceCallingAndroidService.LOG.e("Failed to get call configurations", e);
                        return null;
                    }
                }
            }.execute(new Void[0]);
        } catch (Exception e) {
            LOG.e("Exception hit when trying to update call configurations", e);
        }
    }

    private void updateNetworkConnectivityState(boolean z) {
        LOG.i(String.format("Calling to notify of connection change (connected = %b)", Boolean.valueOf(z)));
        if (!this.commsIdentityManager.isCommsIdPopulated("DeviceCallingAndroidService.updateNetworkConnectivityState", false)) {
            LOG.i("Network changed but we are not provisioned");
            return;
        }
        if (!z && SipClientState.CallState.CALLING.equals(this.sipClientState.getCallState())) {
            cancelPrematureCall();
        }
        this.deviceCallingService.updateConnectivityState(z);
    }

    @VisibleForTesting
    void cancelPrematureCall() {
        SetupCallHelper.recordInitiationMetrics(this.sipClientState.getCallId(), this.sipClientState.getCspId(), SetupCallHelper.ResultType.SUCCESS, SetupCallHelper.MetadataBuilder.newBuilder().withCallType(this.sipClientState.getCallType()).withCallOrigin(Call.Side.Local).withSource(SetupCallHelper.Source.Disconnected).withReason("Premature call cancellation"));
        LOG.i("Cancelling call before receiving onAdded notification");
        this.mAlexaAudioPlayer.stop(1);
        this.sipClientState.setCallId(null);
        this.sipClientState.setCallState(SipClientState.CallState.INACTIVE);
        this.sipClientState.setCallType(CallType.NONE);
        this.sipClientState.setCallProvider("");
        this.sipClientState.setRemoteParticipantId(null);
        this.callManager.setCallActivityLaunchedOnce(false);
        cleanupAlexaCall();
        stopService(new Intent(this, InitiateCallService.class));
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(Constants.CLEAR_CALL_ACTIVITY));
        if (!Utils.getBooleanPreferenceFromSharedPrefs(this.mContext, TelecomConstants.SHARED_PREF_KEY_TELECOM_SUPPORTED, false)) {
            CommsDaggerWrapper.getComponent().getBluetoothHeadsetHelper().stop();
        }
        this.proximityLockHelper.markCallEnded();
    }

    public String getSIPRegistrationInstanceID() {
        return this.deviceUtils.getDeviceSerialNumber();
    }

    @VisibleForTesting
    void handleCallCancellationFromExternalSource(@NonNull Intent intent) {
        boolean z = false;
        boolean booleanExtra = intent.getBooleanExtra(Constants.IGNORE_CALL_ID, false);
        String stringExtra = intent.getStringExtra(Constants.CALL_ID);
        if (booleanExtra) {
            this.mCommandProcessor.hangupAllCalls(Call.HangupRequest.Everywhere);
        }
        int ordinal = this.sipClientState.getCallState().ordinal();
        if (ordinal != 1) {
            if (ordinal == 4) {
                this.mCommandProcessor.rejectIncomingCall();
                return;
            } else if (ordinal != 5) {
                if (ordinal != 6) {
                    if (stringExtra != null && stringExtra.equals(this.callContext.getCallID()) && this.callContext.getCallEndReason() != null) {
                        z = true;
                    }
                    if (z) {
                        return;
                    }
                    cancelPrematureCall();
                    return;
                }
                this.mCommandProcessor.endCurrentCall();
                return;
            }
        }
        if (stringExtra != null) {
            this.mCommandProcessor.cancelOutgoingCall(stringExtra);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean handleStartAction(Intent intent) {
        char c;
        String action = intent.getAction();
        Bundle extras = intent.getExtras();
        LOG.i("Action string received: " + action);
        switch (action.hashCode()) {
            case -2099915149:
                if (action.equals(Constants.END_ACTIVE_CALL)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -769665951:
                if (action.equals(Constants.NOTIFY_INCOMING_CALL)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -468164020:
                if (action.equals(Constants.CANCEL_OUTGOING_CALL)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -137115815:
                if (action.equals(Constants.ACCEPT_INCOMING_VIDEO_CALL)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 296116791:
                if (action.equals(Constants.REJECT_INCOMING_CALL)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 566839065:
                if (action.equals(Constants.SEND_CALL_METRICS)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 1435116822:
                if (action.equals(Constants.CANCEL_ANY_CALL)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1594355660:
                if (action.equals(Constants.Calling.MAKE_OUTGOING_CALL_WITH_NEW_ARCHITECTURE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1634649417:
                if (action.equals(Constants.ACCEPT_INCOMING_AUDIO_CALL)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1732737914:
                if (action.equals(Constants.BEGIN_CALL_TIMER)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 2038053689:
                if (action.equals(Constants.CLEANUP_CALL)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 2063606464:
                if (action.equals(Constants.MAKE_OUTGOING_CALL)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                if (this.sipClientState.getSipRegistrationStatus() == DeviceCallingService.State.Registered) {
                    makeOutgoingCall(extras);
                    return true;
                }
                initializeCallingServiceIfNeeded();
                setSIPRegistrationReason(this.mContext, MetricKeys.VALUE_SIP_REGISTRATION_BEGIN_CALL);
                Utils.writeStringPreferenceToSharedPrefs(this.mContext, SIP_REGISTRATION_FROM_OUTGOING_CALL, "true");
                new PerformSIPRegistration().execute(new Void[0]);
                this.outgoingCallBundle = extras;
                return true;
            case 1:
                if (this.sipClientState.getSipRegistrationStatus() == DeviceCallingService.State.Registered) {
                    makeOutgoingCall();
                    return true;
                }
                initializeCallingServiceIfNeeded();
                setSIPRegistrationReason(this.mContext, MetricKeys.VALUE_SIP_REGISTRATION_BEGIN_CALL);
                Utils.writeStringPreferenceToSharedPrefs(this.mContext, SIP_REGISTRATION_FROM_OUTGOING_CALL, "true");
                this.shouldPlaceCallAfterSIPRegisteration = true;
                new PerformSIPRegistration().execute(new Void[0]);
                return true;
            case 2:
                LOG.i("[Comms-calling]: NOTIFY_INCOMING_CALL");
                initializeAudioForCall(false);
                Notification pendingNotificationForFGService = this.mCommsNotificationManager.getPendingNotificationForFGService("");
                if (pendingNotificationForFGService != null) {
                    LOG.i("[Comms-calling]: startForeground: Moving service to foreground");
                    startForeground(2, pendingNotificationForFGService);
                    return true;
                }
                LOG.e("[Comms-calling]: NULL Notification received");
                return true;
            case 3:
                this.mCommandProcessor.acceptAsAudioCall();
                return true;
            case 4:
                this.mCommandProcessor.acceptVideoCall();
                return true;
            case 5:
                this.mCommandProcessor.rejectIncomingCall();
                return true;
            case 6:
                handleCallCancellationFromExternalSource(intent);
                return true;
            case 7:
                this.mAlexaAudioPlayer.stop(1);
                SipClientState.CallState callState = this.sipClientState.getCallState();
                String callId = this.sipClientState.getCallId();
                if (callState.ordinal() < SipClientState.CallState.OUTBOUND_RINGING.ordinal()) {
                    cancelPrematureCall();
                    return true;
                } else if (callId == null) {
                    return true;
                } else {
                    this.mCommandProcessor.cancelOutgoingCall(callId);
                    return true;
                }
            case '\b':
                this.mCommandProcessor.endCurrentCall();
                return true;
            case '\t':
                LOG.i("[Comms-calling]: BEGIN_CALL_TIMER");
                this.mNotificationUpdateListener = new CallTimerManager.NotificationUpdateListener() { // from class: com.amazon.deecomms.common.service.-$$Lambda$DeviceCallingAndroidService$E4_wSk8CZZNeweu0ohipFHBQlvU
                    @Override // com.amazon.deecomms.calling.controller.CallTimerManager.NotificationUpdateListener
                    public final void onDurationUpdated(String str) {
                        DeviceCallingAndroidService.this.lambda$handleStartAction$0$DeviceCallingAndroidService(str);
                    }
                };
                this.callTimerManager.addListener(this.mNotificationUpdateListener);
                return true;
            case '\n':
                this.proximityLockHelper.markCallEnded();
                cleanupAlexaCall();
                deactivateCallingServiceIfNeeded();
                return true;
            case 11:
                String stringExtra = intent.getStringExtra(Constants.CALL_ID);
                if (Utils.isNullOrEmpty(stringExtra)) {
                    LOG.e("Missing CALL_ID for sending metrics!");
                    return true;
                }
                LOG.d("Sending call metrics");
                new SendCallMetricsTask(stringExtra, intent.getStringExtra(Constants.CALL_RATING)).execute(new Void[0]);
                return true;
            default:
                LOG.e("Unknown ACTION string received.");
                return false;
        }
    }

    public /* synthetic */ void lambda$handleStartAction$0$DeviceCallingAndroidService(String str) {
        Notification pendingNotificationForFGService = this.mCommsNotificationManager.getPendingNotificationForFGService(str);
        if (pendingNotificationForFGService != null) {
            this.notificationManager.notify(2, pendingNotificationForFGService);
        } else {
            LOG.e("NULL Notification received.");
        }
    }

    public /* synthetic */ void lambda$postCallNotificationTimeoutRunnable$1$DeviceCallingAndroidService() {
        if (Utils.getLongPreferenceFromSharedPrefs(this.mContext, Constants.NOTIFICATION_CALL_TOKEN_START_TIME, 0L) > 0) {
            Utils.writeLongPreferenceToSharedPrefs(this.mContext, Constants.NOTIFICATION_CALL_TOKEN_START_TIME, 0L);
            LOG.e("Call invite was not received within 15 seconds of the call push notification");
            MetricsHelper.recordCounterMetricOperational(MetricKeys.NOTIFICATION_CALL_INVITE_TIMEOUT, 1.0d);
            return;
        }
        LOG.d("Call invite was received within 15 seconds");
    }

    @VisibleForTesting
    void makeOutgoingCall() {
        this.sipClientState.setCallState(SipClientState.CallState.CALLING);
        initializeAudioForCall(!this.callContext.isVideoCall());
        playEarconAndPostNotification();
        this.proximityLockHelper.markCallStarted();
        activateCallingServiceIfNeeded("Initiate Call : New architecture");
        new InitiateCall(this.deviceCallingService, this.mEventTracerFactory, this.capabilitiesManager, this.callHistoryHelper, this.sipClientState, this.callManager).makeACall(this.callContext.getBeginCallPayload());
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LOG.i("onBind");
        return this.deviceCallingServiceBinder;
    }

    @Override // com.amazon.deecomms.common.receiver.CommsConnectivityMonitor.OnConnectionTypeChangedListener
    public void onConnectionTypeChanged(@NonNull CommsConnectivityMonitor.ConnectionType connectionType, @NonNull CommsConnectivityMonitor.ConnectionType connectionType2) {
        LOG.i(String.format("onConnectionTypeChanged: %s -> %s, %s", connectionType, connectionType2, generateStatusString(null)));
        if (connectionType2.isConnected()) {
            setSIPRegistrationReason(this.mContext, MetricKeys.VALUE_SIP_REGISTRATION_NETWORK_CHANGED);
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("CallReconnectFeatureEnabled= ");
            outline1.append(this.capabilitiesManager.isCallReconnectv3Enabled());
            commsLogger.i(outline1.toString());
            if (this.capabilitiesManager.isCallReconnectv3Enabled() && this.callManager.isAnyActiveCallPresent() && this.sipClientState.getCallState() == SipClientState.CallState.ACTIVE) {
                if (connectionType == CommsConnectivityMonitor.ConnectionType.Cellular && connectionType2 == CommsConnectivityMonitor.ConnectionType.CellularAndWifi) {
                    reconnectCall(connectionType, connectionType2);
                    return;
                } else {
                    onNetworkConnected(connectionType, connectionType2);
                    return;
                }
            }
            onNetworkConnected(connectionType, connectionType2);
            return;
        }
        onNetworkDisconnected();
    }

    @Override // android.app.Service
    public void onCreate() {
        LOG.i("[Comms-calling]: onCreate");
        this.serviceEventListener = new DeviceCallingServiceEventListener();
        new SipHeaders().put(SipHeaders.SIP_HEADER_DEVICE_TYPE, "A2TF17PFR55MTB");
        if (Utils.isFireOS()) {
            MediaCodecVideoDecoder.setMaxPendingFramesForH264(10);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACCOUNT_DEREGISTER_ACTION);
        intentFilter.addAction(Constants.APPLICATION_FOREGROUND_CHECKED_ACTION);
        intentFilter.addAction(Constants.MODE_SWITCH_MULTI_MODAL);
        intentFilter.addAction(Constants.MODE_SWITCH_TABLET);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.deviceCallingAndroidServiceReceiver, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.SCREEN_ON");
        intentFilter2.addAction("android.intent.action.SCREEN_OFF");
        if (Utils.isLollipopAndAbove()) {
            intentFilter2.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
        }
        if (Utils.isMarshMallowAndAbove()) {
            intentFilter2.addAction("android.os.action.DEVICE_IDLE_MODE_CHANGED");
        }
        this.mContext.registerReceiver(this.deviceCallingAndroidServiceReceiver, intentFilter2);
        UserSwitchReceiver userSwitchReceiver = new UserSwitchReceiver();
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction("android.intent.action.USER_BACKGROUND");
        intentFilter3.addAction("android.intent.action.USER_FOREGROUND");
        this.mContext.registerReceiver(userSwitchReceiver, intentFilter3);
        this.mPhoneStateListener = new CustomPhoneStateListener(null);
        this.mTelephonyManager.listen(this.mPhoneStateListener, 32);
        this.proximityLockHelper = ProximityLockHelper.getInstance(this.mContext);
        DeviceCallingServiceEventListener.addListener(this);
        this.commsConnectivityMonitor.registerListener(this);
        if (!this.commsConnectivityMonitor.isSubscribed()) {
            this.commsConnectivityMonitor.subscribeNetworkChangeEvents();
        }
        if (!this.commsActivityMonitor.isStarted()) {
            this.commsActivityMonitor.start();
        }
        this.mediaButtonIntent = new Intent(this.mContext, MediaButtonService.class);
    }

    @Override // android.app.Service
    public void onDestroy() {
        LOG.i("[Comms-calling]: onDestroy");
        this.deviceCallingService.unregisterListener(this.serviceEventListener);
        this.commsConnectivityMonitor.deRegisterListener(this);
        DeviceCallingServiceEventListener.removeListener(this);
        this.serviceEventListener = null;
        shutdownCallingServiceIfNeeded();
        this.deviceCallingServiceWasConfigured = false;
        resetAudio();
        this.mAlexaAudioPlayer.stop(1);
        this.mTelephonyManager.listen(this.mPhoneStateListener, 0);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.deviceCallingAndroidServiceReceiver);
        if (this.mIsBroadcastReceiverRegistered) {
            LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(this.mOutgoingCallBroadcastReceiver);
            this.mIsBroadcastReceiverRegistered = false;
        }
        this.mContext.unregisterReceiver(this.deviceCallingAndroidServiceReceiver);
        this.proximityLockHelper.cleanup();
        cancelNetworkReconnectTask();
    }

    @Override // com.amazon.deecomms.common.sip.SipStatusListener
    public void onSipStatusChanged(DeviceCallingService.State state) {
        DeviceCallingService.State sipRegistrationStatus = this.sipClientState.getSipRegistrationStatus();
        LOG.i(String.format("onSipStatusChanged: previous=%s, %s", sipRegistrationStatus, generateStatusString(state)));
        if (state != null && state != sipRegistrationStatus) {
            int i = AnonymousClass4.$SwitchMap$com$amazon$comms$calling$service$DeviceCallingService$State[state.ordinal()];
            if (i == 1) {
                Bundle bundle = this.outgoingCallBundle;
                if (bundle != null) {
                    makeOutgoingCall(bundle);
                }
                if (this.shouldPlaceCallAfterSIPRegisteration) {
                    makeOutgoingCall();
                    this.shouldPlaceCallAfterSIPRegisteration = false;
                }
            } else if (i == 2) {
                performPushRegistration();
            }
            if (state == DeviceCallingService.State.Initialized) {
                return;
            }
            this.outgoingCallBundle = null;
            return;
        }
        CommsLogger commsLogger = LOG;
        commsLogger.w("onSipStatusChanged: ignoring null or unchanged state " + state);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        LOG.i("[Comms-calling]: onStartCommand");
        boolean z = intent != null && intent.getBooleanExtra(Constants.SHOULD_START_FOREGROUND_NOTI, false);
        if (this.sipClientState.getCurrentActiveCall() == null && !z) {
            LOG.i("Removing previous notifications since there aren't any calls");
            LOG.i("Moving service to background");
            LOG.i("[Comms-calling]: stopForeground as getCurrentActiveCall is null.");
            stopForeground(true);
        }
        if (intent == null) {
            LOG.d("onStartCommand: no intent");
            onInitialStartCommand(false);
            return 1;
        }
        Bundle extras = intent.getExtras();
        boolean z2 = intent.getAction() != null && handleStartAction(intent);
        if (extras == null) {
            LOG.d("onStartCommand: no extras");
            onInitialStartCommand(z2);
            return 1;
        }
        if (extras.containsKey(Constants.REGISTRATION_EVENT_TYPE_KEY)) {
            int i3 = extras.getInt(Constants.REGISTRATION_EVENT_TYPE_KEY, 0);
            if (i3 == 1) {
                LOG.i("[Comms-calling]: Performing SIP registration for event: REGISTER_FOR_SIP_INVITE");
                String string = extras.getString(Constants.GCM_MESSAGE_ID_KEY);
                String string2 = extras.getString(Constants.AMP_KEY);
                if (this.deviceCallingService.getState() == DeviceCallingService.State.Registered) {
                    SipClientState.CallState callState = this.sipClientState.getCallState();
                    if (callState.ordinal() != 0) {
                        CommsLogger commsLogger = LOG;
                        commsLogger.w("Ignoring REGISTER_FOR_SIP_INVITE due to call state = " + callState);
                    } else {
                        performSIPRegistrationForCallInvite(string2);
                    }
                    NotificationUtils.logInfo(LOG, string, String.format("Received CALLING push but was already registered with call state %s", callState));
                } else {
                    NotificationUtils.logInfo(LOG, string, "Received CALLING push and sip was not registered");
                    performSIPRegistrationForCallInvite(string2);
                }
            } else if (i3 != 2) {
                CommsLogger commsLogger2 = LOG;
                commsLogger2.e("Unknown RegistrationEvent type: " + i3);
                onInitialStartCommand(z2);
            } else {
                LOG.d("Restarting with registration type REGISTER_ON_TASK_REMOVED");
            }
        } else {
            LOG.d("onStartCommand: no registration event");
            onInitialStartCommand(z2);
        }
        return 1;
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        if (intent == null) {
            LOG.e("onTaskRemoved: null intent");
            return;
        }
        CommsLogger commsLogger = LOG;
        StringBuilder sb = new StringBuilder();
        sb.append("onTaskRemoved: ");
        sb.append(intent);
        sb.append(", ");
        GeneratedOutlineSupport1.outline177(sb, generateStatusString(null), commsLogger);
        boolean z = false;
        if (!this.commsIdentityManager.isCommsIdPopulated("DeviceCallingAndroidService.onTaskRemoved", false)) {
            LOG.w("onTaskRemoved: user not comms enabled, ignoring...");
            return;
        }
        if (!Utils.isPieAndAbove()) {
            z = Utils.isMarshMallowAndAbove() ? this.callUIHandler.isCallIntent(intent) : true;
        }
        Call currentActiveCall = this.sipClientState.getCurrentActiveCall();
        if (z && currentActiveCall != null && currentActiveCall.isAccepted()) {
            this.deviceCallingService.hangup();
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("onTaskRemoved: hanging up active call: ");
            outline1.append(currentActiveCall.getCallId());
            commsLogger2.i(outline1.toString());
        }
        LOG.d("onTaskRemoved: restarted service in 1000ms");
        this.alarmManager.set(3, 1000L, PendingIntent.getService(this.mContext, 1001, new Intent(this.mContext, getClass()).putExtra(Constants.REGISTRATION_EVENT_TYPE_KEY, 2), 1073741824));
    }

    private void makeOutgoingCall(@Nullable Bundle bundle) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("makeOutgoingCall: ");
        outline1.append(generateStatusString(null));
        commsLogger.i(outline1.toString());
        CallType fromBundle = CallType.fromBundle(bundle, Constants.CALL_TYPE);
        boolean z = fromBundle == CallType.AUDIO || fromBundle == CallType.PSTN;
        String fromBundle2 = CallProvider.fromBundle(bundle, Constants.CALL_PROVIDER);
        initializeAudioForCall(z);
        playEarconAndPostNotification(fromBundle);
        this.proximityLockHelper.markCallStarted();
        if (bundle != null && bundle.containsKey(Constants.CALLING_NEW_ARCHITECTURE) && bundle.getBoolean(Constants.CALLING_NEW_ARCHITECTURE)) {
            activateCallingServiceIfNeeded("Initiate Call : New architecture");
            new InitiateCall(this.deviceCallingService, this.mEventTracerFactory, this.capabilitiesManager, this.callHistoryHelper, this.sipClientState, this.callManager).makeACall(this.sipClientState.getBeginCallPayload());
            return;
        }
        if (!this.mIsBroadcastReceiverRegistered) {
            LocalBroadcastManager.getInstance(this.mContext).registerReceiver(this.mOutgoingCallBroadcastReceiver, new IntentFilter(InitiateCallService.TRIGGER_MAKE_CALL));
            this.mIsBroadcastReceiverRegistered = true;
        }
        Intent intent = new Intent(this, InitiateCallService.class);
        intent.putExtra(Constants.CALL_TYPE, fromBundle.toString());
        intent.putExtra(Constants.CALL_PROVIDER, fromBundle2);
        if (bundle != null) {
            intent.putExtra(Constants.CALLEE_COMMS_ID, bundle.getString(Constants.CALLEE_COMMS_ID));
            intent.putExtra(Constants.KEY_RECIPIENT_PHONE_NUMBER, bundle.getParcelable(Constants.KEY_RECIPIENT_PHONE_NUMBER));
            intent.putExtra(Constants.DEVICE_GRUU, bundle.getString(Constants.DEVICE_GRUU));
            intent.putExtra(Constants.CALL_START_TIME, bundle.getLong(Constants.CALL_START_TIME));
            intent.putExtra(Constants.CALL_ID, bundle.getString(Constants.CALL_ID));
            intent.putExtra(Constants.KEY_CALL_INITIATION_SCREEN_NAME, bundle.getString(Constants.KEY_CALL_INITIATION_SCREEN_NAME));
        }
        InitiateCallService.enqueueWork(this.mContext, intent);
    }

    private void playEarconAndPostNotification() {
        if (!this.callContext.isDropIn() && !this.callContext.isPSTN()) {
            this.mAudioManager.setMode(3);
            LOG.i("Audio mode is set to communication");
            this.mAlexaAudioPlayer.play(R.raw.alexa_app_system_comms_outbound_ringtone, true, 0, 1);
        }
        Notification pendingNotificationForFGService = this.mCommsNotificationManager.getPendingNotificationForFGService("");
        if (pendingNotificationForFGService != null) {
            LOG.i("Moving service to foreground");
            startForeground(2, pendingNotificationForFGService);
            return;
        }
        LOG.e("NULL notification received");
    }
}
