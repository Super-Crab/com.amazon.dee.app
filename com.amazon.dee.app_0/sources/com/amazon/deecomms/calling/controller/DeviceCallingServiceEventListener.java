package com.amazon.deecomms.calling.controller;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.comms.calling.instrumentation.EventTracerConfig;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.CallListener;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.calling.service.DeviceCallingServiceListener;
import com.amazon.comms.calling.service.ErrorCode;
import com.amazon.comms.calling.service.HangupReason;
import com.amazon.comms.calling.service.MediaStatus;
import com.amazon.comms.calling.service.Participant;
import com.amazon.comms.calling.sipclient.RealTimeTextMetrics;
import com.amazon.comms.calling.sipclient.SipHeaders;
import com.amazon.comms.calling.sipclient.SipStatusCode;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.models.sip.SIPContact;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.comms.statemachine.StateMachine;
import com.amazon.deecomms.R;
import com.amazon.deecomms.alexa.AlexaInterface;
import com.amazon.deecomms.alexa.CallTypeEvent;
import com.amazon.deecomms.alexa.CallTypeEventConstants;
import com.amazon.deecomms.alexa.CallTypeEventModel;
import com.amazon.deecomms.alexa.CallTypeEventType;
import com.amazon.deecomms.alexa.HalloConstants;
import com.amazon.deecomms.alexa.HalloEvent;
import com.amazon.deecomms.alexa.HalloEventType;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.auth.AuthTokenHelper;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.deecomms.calling.controller.CallViewStateMachine;
import com.amazon.deecomms.calling.core.CallInitiationAuthority;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.enums.EnhancedProcessingState;
import com.amazon.deecomms.calling.impl.CallingAPIMonitor;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.calling.telecom.TelecomConstants;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.calling.util.ProximityLockHelper;
import com.amazon.deecomms.calling.util.SetupCallHelper;
import com.amazon.deecomms.calling.util.TelecomUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.EventBusEventType;
import com.amazon.deecomms.common.audio.AlexaAudioPlayer;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.acmsrecipes.GetDeviceCommsPreferences;
import com.amazon.deecomms.common.receiver.CommsConnectivityMonitor;
import com.amazon.deecomms.common.service.DeviceCallingAndroidService;
import com.amazon.deecomms.common.service.IncomingPushAndroidService;
import com.amazon.deecomms.common.service.PerformCallReconnectTask;
import com.amazon.deecomms.common.service.ProvisioningManager;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.sip.SipStatusListener;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.common.util.provider.MMSDKManager;
import com.amazon.deecomms.contacts.database.ContactEntry;
import com.amazon.deecomms.contacts.model.FullContactName;
import com.amazon.deecomms.contacts.util.ContactNameHelper;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.contacts.util.GetOrCreateContact;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.media.audio.RingTonePlaybackAuthority;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.amazon.deecomms.util.ThreadUtils;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Named;
/* loaded from: classes12.dex */
public class DeviceCallingServiceEventListener implements DeviceCallingServiceListener, CallListener {
    private static final String CALL_END_PREMATURELY_INCOMING_LOCAL = "INCOMING_LOCAL_END";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DeviceCallingServiceEventListener.class);
    public static DeviceCallingService.State sDeviceCallingServiceState = DeviceCallingService.State.Uninitialized;
    static List<SipStatusListener> sSipStatusListenerList = Collections.synchronizedList(new LinkedList());
    @Inject
    CallContext callContext;
    @Inject
    CallHistoryHelper callHistoryHelper;
    @Inject
    CallInitiationAuthority callInitiationAuthority;
    @Inject
    CallManager callManager;
    @Inject
    CallMetricsFactory callMetricsFactory;
    @Inject
    CallTimerManager callTimerManager;
    @Inject
    CallingAPIMonitor callingAPIMonitor;
    @Inject
    CapabilitiesManager capabilitiesManager;
    @Inject
    CommsConnectivityMonitor commsConnectivityMonitor;
    @Inject
    CommsIdentityManager commsIdentityManager;
    @Inject
    CommsNotificationManager commsNotificationManager;
    @Inject
    DeviceCallingService deviceCallingService;
    @Inject
    AlexaAudioPlayer mAlexaAudioPlayer;
    @Inject
    ArcusConfig mArcusConfig;
    private AudioManager mAudioManager;
    private String mCallDuration;
    private final CallingContentProviderNotifier mCallingContentProviderNotifier;
    @Inject
    Context mContext;
    private Call mCurrentActiveCall;
    @Inject
    AlexaInterface mCurrentAlexaInterface;
    private int mDurationInSeconds;
    private String mLocalParticipantId;
    private int mRateWorthyCalls;
    private FullContactName mRemoteContactName;
    private String mRemoteParticipantId;
    private CallViewStateMachine mStateMachine;
    @Inject
    PCCContextProvider pccContextProvider;
    @Inject
    @Named(Constants.Dagger.PREVIOUS_CALL_SIPSTATE)
    SipClientState previousSipClientState;
    @Inject
    ProvisioningManager provisioningManager;
    @Inject
    RingTonePlaybackAuthority ringTonePlaybackAuthority;
    @Inject
    @Named(Constants.Dagger.CURRENT_CALL_SIPSTATE)
    SipClientState sipClientState;
    private String mCallEndStatus = "";
    private AtomicBoolean attemptingRegistration = new AtomicBoolean(false);
    private final Map<String, ActiveCallInfo> activeCallInfo = new HashMap();

    /* renamed from: com.amazon.deecomms.calling.controller.DeviceCallingServiceEventListener$8  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$comms$calling$service$DeviceCallingService$State;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$comms$calling$service$ErrorCode = new int[ErrorCode.values().length];

        static {
            try {
                $SwitchMap$com$amazon$comms$calling$service$ErrorCode[ErrorCode.RegistrarUnreachable.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$ErrorCode[ErrorCode.LostConnection.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$com$amazon$comms$calling$service$DeviceCallingService$State = new int[DeviceCallingService.State.values().length];
            try {
                $SwitchMap$com$amazon$comms$calling$service$DeviceCallingService$State[DeviceCallingService.State.Registered.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$DeviceCallingService$State[DeviceCallingService.State.Unregistered.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$DeviceCallingService$State[DeviceCallingService.State.Uninitialized.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes12.dex */
    private static class ActiveCallInfo {
        final int errorCode;
        final String errorReason;

        public ActiveCallInfo() {
            this(SipStatusCode.OK, (String) null);
        }

        public ActiveCallInfo(@NonNull SipStatusCode sipStatusCode, String str) {
            this(sipStatusCode.getCode(), str);
        }

        public ActiveCallInfo(int i, String str) {
            this.errorCode = i;
            this.errorReason = str;
        }
    }

    public DeviceCallingServiceEventListener() {
        CommsDaggerWrapper.getComponent().inject(this);
        this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
        setupStateMachine();
        this.mCallingContentProviderNotifier = new CallingContentProviderNotifier(this.mContext);
    }

    public static void addListener(@NonNull SipStatusListener sipStatusListener) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("Adding listener: " + sipStatusListener);
        sSipStatusListenerList.add(sipStatusListener);
    }

    public static boolean canMakeACall() {
        if (sDeviceCallingServiceState == DeviceCallingService.State.Registered) {
            return true;
        }
        if (TextUtils.isEmpty(CommsDaggerWrapper.getComponent().getCommsIdentityManager().getCommsId("DeviceCallingServiceEventListener.canMakeACall", false))) {
            LOG.w("Cannot make a call until comms user is logged in!");
            return false;
        } else if (CommsDaggerWrapper.getComponent().getCommsConnectivityMonitor().isConnected()) {
            return true;
        } else {
            LOG.w("Cannot make a call until connectivity is established!");
            return false;
        }
    }

    private String computeScreenName(@NonNull String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -411586040) {
            if (str.equals(Constants.FRAGMENT_ACTIVE_CALL_KEY)) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode == -302938767) {
            if (str.equals(Constants.FRAGMENT_END_CALL_KEY)) {
                c = 3;
            }
            c = 65535;
        } else if (hashCode != 1143886882) {
            if (hashCode == 1871462568 && str.equals(Constants.FRAGMENT_INCOMING_CALL_KEY)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals(Constants.FRAGMENT_OUTGOING_CALL_KEY)) {
                c = 0;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c == 1) {
                return Constants.Calling.INCOMING_CALL_SCREEN;
            }
            if (c == 2) {
                return Constants.Calling.ACTIVE_CALL_SCREEN;
            }
            if (c == 3) {
                return Constants.Calling.END_CALL_SCREEN;
            }
            GeneratedOutlineSupport.outline4("Call UI Screen not available for Fragment Name : ", str, LOG);
            return Constants.Calling.END_CALL_SCREEN;
        }
        return Constants.Calling.OUTGOING_CALL_SCREEN;
    }

    private SIPContact createContact(Participant participant) {
        return SIPContact.builder().uri(participant.getUri()).name(participant.getName()).id(participant.getProviderSpecifiedId()).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getContactNameAndSetupCallIntent(@NonNull final String str, @NonNull String str2) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("Retrieving contact name to setup call intent for " + str);
        if (Utils.isRegisteredCommsId(str2)) {
            setupCallIntent(str, new FullContactName(ContactNameHelper.getActiveContactName(), null));
            return;
        }
        ContactsProviderUtils.removeUngettableCommsIDIfObtained(this.mContext, new String[]{str2});
        new AsyncTask<String, Void, FullContactName>() { // from class: com.amazon.deecomms.calling.controller.DeviceCallingServiceEventListener.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public FullContactName doInBackground(String... strArr) {
                ContactEntry contactEntry = new GetOrCreateContact("DeviceCallingServiceEventListener.getContactNameAndSetupCallIntent").getContactEntry(strArr[0]);
                if (contactEntry == null) {
                    return null;
                }
                return contactEntry.getFullContactName();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(FullContactName fullContactName) {
                if (DeviceCallingServiceEventListener.this.sipClientState.getCallState() == SipClientState.CallState.INACTIVE && !Constants.FRAGMENT_END_CALL_KEY.equals(str)) {
                    DeviceCallingServiceEventListener.LOG.e(String.format("Cannot launch %s since callState is inactive", str));
                } else {
                    DeviceCallingServiceEventListener.this.setupCallIntent(str, fullContactName);
                }
            }
        }.execute(str2);
    }

    private void getContactNameAndShowIncomingCallNotification(@NonNull String str) {
        LOG.i("[Comms-calling]: Retrieving contact name for remoteParticipantID");
        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.HEADUP_NOTIFICATION_CALL_VIA_SIP);
        if (Utils.isRegisteredCommsId(str)) {
            IncomingPushAndroidService.showHeadupIncomingCallNotification(this.mContext, ContactUtils.getFullName(new FullContactName(ContactNameHelper.getActiveContactName(), null)));
        } else {
            new AsyncTask<String, Void, FullContactName>() { // from class: com.amazon.deecomms.calling.controller.DeviceCallingServiceEventListener.7
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public FullContactName doInBackground(String... strArr) {
                    ContactEntry contactEntry = new GetOrCreateContact("DeviceCallingServiceEventListener.getContactNameAndSetupCallIntent").getContactEntry(strArr[0]);
                    if (contactEntry == null) {
                        return null;
                    }
                    return contactEntry.getFullContactName();
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public void onPostExecute(FullContactName fullContactName) {
                    String fullName = ContactUtils.getFullName(fullContactName);
                    DeviceCallingServiceEventListener.LOG.i("[Comms-calling]: getContactNameAndShowIncomingCallNotification: %s", fullName);
                    IncomingPushAndroidService.showHeadupIncomingCallNotification(DeviceCallingServiceEventListener.this.mContext, fullName);
                }
            }.execute(str);
        }
    }

    private void handleOfflineReason(@NonNull String str, @NonNull SetupCallHelper.MetadataBuilder metadataBuilder, @NonNull Call call) {
        this.mCallEndStatus = Utils.getStringFromResource(R.string.callee_unavailable);
        recordClickMetric(MetricKeys.CALL_OFFLINE, call);
        this.callHistoryHelper.setCallStatusCode(str, SipStatusCode.TEMPORARILY_UNAVAILABLE.getCode());
        this.sipClientState.setStatusCode(Integer.toString(SipStatusCode.TEMPORARILY_UNAVAILABLE.getCode()));
        this.mCurrentAlexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.CALL_CANCEL, str, this.sipClientState));
        SetupCallHelper.recordInitiationMetrics(str, this.sipClientState.getCspId(), SipStatusCode.TEMPORARILY_UNAVAILABLE, metadataBuilder);
    }

    private void handleRinging(Call call) {
        recordClickMetric(MetricKeys.CALL_RINGING, call);
        this.mCurrentAlexaInterface.acquireCommsFocus();
        this.sipClientState.setCallProvider(CallProvider.fromString(call.getProvider()));
        if (Call.Side.Local.equals(call.getOrigin())) {
            TimerMetric generateClickstream = TimerMetric.generateClickstream(MetricKeys.CALL_TIME_TO_RING);
            MetricsHelper.addCallAttributes(generateClickstream, call);
            MetricsHelper.stopTimerMetric(generateClickstream);
            this.sipClientState.setCallState(SipClientState.CallState.OUTBOUND_RINGING);
            call.getEventTracer().mark(EventTracerConfig.Event.Playing_Outgoing_Ringback_Tone);
            this.sipClientState.setStatusCode(Integer.toString(SipStatusCode.RINGING.getCode()));
            this.mCurrentAlexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.OUTBOUND_CALL_RINGING, call.getCallId(), this.sipClientState));
        } else {
            this.sipClientState.setCallState(SipClientState.CallState.INBOUND_RINGING);
            this.sipClientState.setStatusCode(Integer.toString(SipStatusCode.RINGING.getCode()));
            this.mCurrentAlexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.INBOUND_CALL_RINGING, call.getCallId(), this.sipClientState));
        }
        this.mCallingContentProviderNotifier.notifyObservers();
    }

    private void handleRingtone(int i) {
        this.mAlexaAudioPlayer.stop(1);
        if (i != 0) {
            this.mAlexaAudioPlayer.play(i, false, 0, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void incrementNumberOfCallsIfNecessary() {
        if (this.mDurationInSeconds >= this.mArcusConfig.getConfigInteger(RemoteConfigKeys.CALL_RATING_MIN_CALL_DURATION).intValue()) {
            this.mRateWorthyCalls = this.callManager.incrementAndGetNumberOfCalls();
        }
    }

    private boolean isAttemptingRegistration() {
        return this.attemptingRegistration.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playIncomingRingToneIfNeeded() {
        if (this.ringTonePlaybackAuthority.canPlayRingTone()) {
            this.mAlexaAudioPlayer.play(R.raw.alexa_app_system_comm_call_incoming_ringtone, true, 2, true, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareIntentExtrasToShowEndCallScreen(@NonNull String str, @NonNull String str2) {
        Intent intent = new Intent();
        intent.setAction(Constants.SHOW_CALL_UI);
        this.previousSipClientState.setRemoteParticipantName(str2);
        intent.putExtra(Constants.REMOTE_PARTICIPANT_NAME, str2);
        intent.putExtra(Constants.CALL_TYPE, this.previousSipClientState.getCallType().toString());
        intent.putExtra(Constants.CALL_PROVIDER, this.previousSipClientState.getCallProvider());
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra(Constants.LAUNCH_FRAGMENT_KEY, str);
            intent.putExtra(Constants.Calling.SCREEN_NAME, computeScreenName(str));
        }
        if (Constants.FRAGMENT_END_CALL_KEY.equals(str)) {
            intent.putExtra(Constants.CALL_END_STATUS, this.mCallEndStatus);
            intent.putExtra(Constants.CALL_RATING_SCREEN_DURATION, Long.valueOf(this.mArcusConfig.getConfigInteger(RemoteConfigKeys.CALL_RATING_SCREEN_DURATION).intValue()).longValue() * 1000);
            intent.putExtra(Constants.SHOW_CALL_RATING, shouldShowCallRating());
            intent.putExtra(Constants.GROUP_CALL, this.sipClientState.isGroupCall());
            Call call = this.mCurrentActiveCall;
            if (call != null) {
                this.callHistoryHelper.setCallingMetrics(this.callMetricsFactory.createCallingMetrics(call));
                this.callHistoryHelper.setCallQualityMetrics(this.callMetricsFactory.createCallQualityMetrics(this.mCurrentActiveCall));
                intent.putExtra(Constants.CALL_ID, this.mCurrentActiveCall.getCallId());
            }
            if (this.mCallEndStatus.equals(this.mContext.getResources().getString(R.string.call_end_status_mature))) {
                intent.putExtra(Constants.CALL_DURATION_KEY, this.mCallDuration);
            }
            this.callContext.setCallDuration(this.mCallDuration);
            this.callContext.setCallEndReason(this.mCallEndStatus);
            this.callContext.setShouldShowCallRating(shouldShowCallRating());
            this.mCallEndStatus = "";
            this.mCallDuration = "";
            this.mDurationInSeconds = -1;
            this.mCurrentActiveCall = null;
        }
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(intent);
    }

    private void processIncomingCall(@NonNull Call call, long j) {
        handleIncomingCall(call);
        recordPushNotificationSipInviteLatencyMetric(j);
        notifyObservers();
    }

    private void processUnknownHangUpReason(@Nullable String str, @NonNull SetupCallHelper.MetadataBuilder metadataBuilder) {
        this.mCallEndStatus = "";
        if (this.callHistoryHelper.getCallStatusCode(str) == -1) {
            this.callHistoryHelper.setCallStatusCode(str, ErrorCode.Unknown.getValue());
        }
        this.sipClientState.setStatusCode(Integer.toString(SipStatusCode.BAD_EVENT.getCode()));
        SetupCallHelper.recordInitiationMetrics(str, this.sipClientState.getCspId(), SetupCallHelper.ResultType.UNKNOWN, metadataBuilder);
    }

    private void publishHVAEvent(boolean z, CallType callType) {
        EventBusEventType eventBusEventType;
        EventBus eventBus = CommsDaggerWrapper.getComponent().getEventBus();
        try {
            if (z) {
                eventBusEventType = callType.isDropIn() ? EventBusEventType.HVA_DROP_IN_END : EventBusEventType.HVA_ALEXA_CALL_END;
            } else {
                eventBusEventType = callType.isDropIn() ? EventBusEventType.HVA_DROP_IN_START : EventBusEventType.HVA_ALEXA_CALL_START;
            }
            eventBus.publish(new Message.Builder().setEventType(eventBusEventType.toString()).build());
            CommsLogger commsLogger = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("published HVA event: ");
            sb.append(eventBusEventType.toString());
            commsLogger.i(sb.toString());
        } catch (Exception e) {
            CommsLogger commsLogger2 = LOG;
            commsLogger2.e("failed to publish HVA event for callEnded=" + z + ", callType=" + callType, e);
        }
    }

    private static void recordClickMetric(String str, Call call, Integer num) {
        CounterMetric generateClickstream = CounterMetric.generateClickstream(str);
        if (num != null) {
            generateClickstream.getMetadata().put("statusCode", num);
        }
        MetricsHelper.addCallAttributes(generateClickstream, call);
        MetricsHelper.recordSingleOccurrence(generateClickstream);
    }

    private static void recordReconnectMetric(Call call, boolean z, boolean z2, String str) {
        String str2 = z2 ? "initiator_" : "receiver_";
        CounterMetric generateClickstream = CounterMetric.generateClickstream(MetricKeys.CALL_RECONNECT_FAILURE);
        if (!TextUtils.isEmpty(str)) {
            generateClickstream.getMetadata().put("source", GeneratedOutlineSupport.outline0(str2, str));
        }
        MetricsHelper.addCallAttributes(generateClickstream, call);
        MetricsHelper.recordCounterMetric(generateClickstream, Double.valueOf(z ? FrostVideoEffectController.VIDEO_STRENGTH_CLEAR : 1.0d));
    }

    public static void removeListener(@NonNull SipStatusListener sipStatusListener) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("Removing listener: " + sipStatusListener);
        sSipStatusListenerList.remove(sipStatusListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupCallIntentInternal(@NonNull String str, @NonNull String str2) {
        Intent intent = new Intent();
        intent.setAction(Constants.SHOW_CALL_UI);
        this.sipClientState.setRemoteParticipantName(str2);
        intent.putExtra(Constants.REMOTE_PARTICIPANT_NAME, str2);
        intent.putExtra(Constants.CALL_TYPE, this.sipClientState.getCallType().toString());
        intent.putExtra(Constants.CALL_PROVIDER, this.sipClientState.getCallProvider());
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra(Constants.LAUNCH_FRAGMENT_KEY, str);
            intent.putExtra(Constants.Calling.SCREEN_NAME, computeScreenName(str));
        }
        if (Constants.FRAGMENT_END_CALL_KEY.equals(str)) {
            intent.putExtra(Constants.CALL_END_STATUS, this.mCallEndStatus);
            intent.putExtra(Constants.CALL_RATING_SCREEN_DURATION, Long.valueOf(this.mArcusConfig.getConfigInteger(RemoteConfigKeys.CALL_RATING_SCREEN_DURATION).intValue()).longValue() * 1000);
            intent.putExtra(Constants.SHOW_CALL_RATING, shouldShowCallRating());
            Call call = this.mCurrentActiveCall;
            if (call != null) {
                this.callHistoryHelper.setCallingMetrics(this.callMetricsFactory.createCallingMetrics(call));
                this.callHistoryHelper.setCallQualityMetrics(this.callMetricsFactory.createCallQualityMetrics(this.mCurrentActiveCall));
                intent.putExtra(Constants.CALL_ID, this.mCurrentActiveCall.getCallId());
            }
            if (this.mCallEndStatus.equals(this.mContext.getResources().getString(R.string.call_end_status_mature))) {
                intent.putExtra(Constants.CALL_DURATION_KEY, this.mCallDuration);
            }
            this.mCallEndStatus = "";
            this.mCallDuration = "";
            this.mDurationInSeconds = -1;
            this.mCurrentActiveCall = null;
        }
        boolean isGroupCall = this.sipClientState.isGroupCall();
        if (isGroupCall) {
            this.sipClientState.setEnhancedProcessingState(EnhancedProcessingState.ON);
        }
        intent.putExtra(Constants.GROUP_CALL, isGroupCall);
        if (Constants.FRAGMENT_INCOMING_CALL_KEY.equals(str)) {
            intent.putExtra(Constants.LOCAL_COMMS_ID, this.mLocalParticipantId);
            intent.putExtra("COMMS_ID", this.mRemoteParticipantId);
            intent.putExtra(Constants.BUNDLE_KEY_CONTACT_NAME_KEY, this.mRemoteContactName);
        }
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(intent);
    }

    private void setupStateActionActiveCallView() {
        this.mStateMachine.setAction(CallViewStateMachine.State.ActiveCallView, new StateMachine.Action() { // from class: com.amazon.deecomms.calling.controller.DeviceCallingServiceEventListener.4
            @Override // com.amazon.comms.statemachine.StateMachine.Action
            public void onExit() {
                DeviceCallingServiceEventListener.LOG.d("Exiting Active call state");
            }

            @Override // com.amazon.comms.statemachine.StateMachine.Action
            /* renamed from: onEnter */
            public CallViewStateMachine.Event mo3618onEnter() {
                DeviceCallingServiceEventListener.LOG.d("Entering Active call state");
                DeviceCallingServiceEventListener.this.callTimerManager.startTimer();
                Intent intent = new Intent(DeviceCallingServiceEventListener.this.mContext, DeviceCallingAndroidService.class);
                intent.setAction(Constants.BEGIN_CALL_TIMER);
                DeviceCallingServiceEventListener.this.mContext.startService(intent);
                DeviceCallingServiceEventListener.this.mAlexaAudioPlayer.stop(1);
                DeviceCallingServiceEventListener.this.mAlexaAudioPlayer.play(R.raw.alexa_app_system_comm_call_connected, false, 0, 1);
                DeviceCallingServiceEventListener.LOG.i("[Comms-calling]: launching the active call view");
                DeviceCallingServiceEventListener deviceCallingServiceEventListener = DeviceCallingServiceEventListener.this;
                deviceCallingServiceEventListener.setupCallIntentInternal(Constants.FRAGMENT_ACTIVE_CALL_KEY, deviceCallingServiceEventListener.sipClientState.getRemoteParticipantName());
                return null;
            }
        });
    }

    private void setupStateActionCallEndedView() {
        this.mStateMachine.setAction(CallViewStateMachine.State.CallEndedView, new StateMachine.Action() { // from class: com.amazon.deecomms.calling.controller.DeviceCallingServiceEventListener.5
            @Override // com.amazon.comms.statemachine.StateMachine.Action
            public void onExit() {
                DeviceCallingServiceEventListener.LOG.d("Exiting Call ended state");
            }

            @Override // com.amazon.comms.statemachine.StateMachine.Action
            /* renamed from: onEnter */
            public CallViewStateMachine.Event mo3618onEnter() {
                GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport.outline1("Entering Call ended state : "), DeviceCallingServiceEventListener.this.mCallEndStatus, DeviceCallingServiceEventListener.LOG);
                DeviceCallingServiceEventListener deviceCallingServiceEventListener = DeviceCallingServiceEventListener.this;
                deviceCallingServiceEventListener.mCallDuration = deviceCallingServiceEventListener.callTimerManager.getCallDuration();
                DeviceCallingServiceEventListener.this.callTimerManager.stopTimer();
                DeviceCallingServiceEventListener.this.incrementNumberOfCallsIfNecessary();
                if (!TextUtils.isEmpty(DeviceCallingServiceEventListener.this.commsIdentityManager.getCommsId("DCSEL::setupStateActionCallEndedView", false))) {
                    Intent intent = new Intent(DeviceCallingServiceEventListener.this.mContext, DeviceCallingAndroidService.class);
                    intent.setAction(Constants.CLEANUP_CALL);
                    DeviceCallingServiceEventListener.this.mContext.startService(intent);
                }
                if (!DeviceCallingServiceEventListener.this.mCallEndStatus.equals(DeviceCallingServiceEventListener.CALL_END_PREMATURELY_INCOMING_LOCAL)) {
                    DeviceCallingServiceEventListener deviceCallingServiceEventListener2 = DeviceCallingServiceEventListener.this;
                    deviceCallingServiceEventListener2.mCurrentActiveCall = deviceCallingServiceEventListener2.previousSipClientState.getCurrentActiveCall();
                    DeviceCallingServiceEventListener deviceCallingServiceEventListener3 = DeviceCallingServiceEventListener.this;
                    deviceCallingServiceEventListener3.prepareIntentExtrasToShowEndCallScreen(Constants.FRAGMENT_END_CALL_KEY, deviceCallingServiceEventListener3.previousSipClientState.getRemoteParticipantName());
                    MetricsHelper.recordRTTMetrics(DeviceCallingServiceEventListener.this.previousSipClientState);
                    return null;
                }
                LocalBroadcastManager.getInstance(DeviceCallingServiceEventListener.this.mContext).sendBroadcast(new Intent(Constants.CLEAR_CALL_ACTIVITY));
                DeviceCallingServiceEventListener.this.mCallEndStatus = "";
                return null;
            }
        });
    }

    private void setupStateActionIncomingCallView() {
        this.mStateMachine.setAction(CallViewStateMachine.State.IncomingCallView, new StateMachine.Action() { // from class: com.amazon.deecomms.calling.controller.DeviceCallingServiceEventListener.3
            @Override // com.amazon.comms.statemachine.StateMachine.Action
            public void onExit() {
                DeviceCallingServiceEventListener.LOG.d("Exiting Incoming call state");
                DeviceCallingServiceEventListener.this.mAlexaAudioPlayer.stop(1);
            }

            @Override // com.amazon.comms.statemachine.StateMachine.Action
            /* renamed from: onEnter */
            public CallViewStateMachine.Event mo3618onEnter() {
                DeviceCallingServiceEventListener.LOG.d("Entering Incoming call state");
                DeviceCallingServiceEventListener deviceCallingServiceEventListener = DeviceCallingServiceEventListener.this;
                deviceCallingServiceEventListener.getContactNameAndSetupCallIntent(Constants.FRAGMENT_INCOMING_CALL_KEY, deviceCallingServiceEventListener.mRemoteParticipantId);
                DeviceCallingServiceEventListener.this.playIncomingRingToneIfNeeded();
                return null;
            }
        });
    }

    private void setupStateActionOutgoingCallView() {
        this.mStateMachine.setAction(CallViewStateMachine.State.OutgoingCallView, new StateMachine.Action() { // from class: com.amazon.deecomms.calling.controller.DeviceCallingServiceEventListener.2
            @Override // com.amazon.comms.statemachine.StateMachine.Action
            public void onExit() {
                DeviceCallingServiceEventListener.LOG.d("Exiting Outgoing call state");
            }

            @Override // com.amazon.comms.statemachine.StateMachine.Action
            /* renamed from: onEnter */
            public CallViewStateMachine.Event mo3618onEnter() {
                DeviceCallingServiceEventListener.LOG.d("Entering Outgoing call state");
                if (!Utils.getBooleanPreferenceFromSharedPrefs(DeviceCallingServiceEventListener.this.mContext, TelecomConstants.SHARED_PREF_KEY_TELECOM_SUPPORTED, false)) {
                    CommsDaggerWrapper.getComponent().getBluetoothHeadsetHelper().start();
                    return null;
                }
                return null;
            }
        });
    }

    private void setupStateActionUnbound() {
        this.mStateMachine.setAction(CallViewStateMachine.State.Unbound, new StateMachine.Action() { // from class: com.amazon.deecomms.calling.controller.DeviceCallingServiceEventListener.1
            @Override // com.amazon.comms.statemachine.StateMachine.Action
            public void onExit() {
                DeviceCallingServiceEventListener.LOG.d("Exiting Unbound state");
            }

            @Override // com.amazon.comms.statemachine.StateMachine.Action
            /* renamed from: onEnter */
            public CallViewStateMachine.Event mo3618onEnter() {
                DeviceCallingServiceEventListener.LOG.d("Entering Unbound state");
                return null;
            }
        });
    }

    private void setupStateMachine() {
        this.mStateMachine = new CallViewStateMachine();
        setupStateActionUnbound();
        setupStateActionOutgoingCallView();
        setupStateActionIncomingCallView();
        setupStateActionActiveCallView();
        setupStateActionCallEndedView();
        this.mStateMachine.start();
    }

    private boolean shouldShowCallRating() {
        Integer configInteger = this.mArcusConfig.getConfigInteger(RemoteConfigKeys.CALL_RATING_CALL_FREQUENCY);
        Integer configInteger2 = this.mArcusConfig.getConfigInteger(RemoteConfigKeys.CALL_RATING_MIN_CALL_DURATION);
        int i = this.mRateWorthyCalls;
        return i > 0 && i % configInteger.intValue() == 0 && this.mDurationInSeconds >= configInteger2.intValue();
    }

    private void updateParticipantInformation(@NonNull Call call) {
        Participant localParticipant = call.getLocalParticipant();
        Participant remoteParticipant = call.getRemoteParticipant();
        String str = call.getOrigin() == Call.Side.Remote ? "callee" : HalloConstants.CALLER_SIDE;
        if (remoteParticipant != null) {
            if (str.equals("callee")) {
                this.sipClientState.setCaller(createContact(remoteParticipant));
            } else {
                this.sipClientState.setCallee(createContact(remoteParticipant));
            }
        }
        if (localParticipant != null) {
            if (str.equals("callee")) {
                this.sipClientState.setCallee(createContact(localParticipant));
            } else {
                this.sipClientState.setCaller(createContact(localParticipant));
            }
        }
        String str2 = null;
        this.mRemoteParticipantId = remoteParticipant != null ? remoteParticipant.getProviderSpecifiedId() : null;
        if (localParticipant != null) {
            str2 = localParticipant.getProviderSpecifiedId();
        }
        this.mLocalParticipantId = str2;
        this.sipClientState.setRemoteParticipantId(this.mRemoteParticipantId);
        this.sipClientState.setLocalParticipantId(this.mLocalParticipantId);
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingServiceListener
    public void configureCall(Call call) {
        LOG.i("configureCall callback received");
        if (Utils.areAccessoriesConnected() || Utils.isInDriveMode()) {
            LOG.i("Setting local media capability to audio true and video false as PCC Session is available");
            call.setLocalMediaCapability(true, false);
        }
        if (call.getOrigin() == Call.Side.Local) {
            call.getOutgoingHeaders().put(SipHeaders.SIP_HEADER_DEVICE_TYPE, "A2TF17PFR55MTB");
        }
    }

    @VisibleForTesting
    String getCallEndStatus() {
        return this.mCallEndStatus;
    }

    String getRemoteParticipantID() {
        return this.mRemoteParticipantId;
    }

    public void handleIncomingCall(Call call) {
        if (!Utils.shouldAllowAlexaCall(this.mContext)) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1(" You have an incoming Alexa Call with CallID ");
            outline1.append(call.getCallId());
            outline1.append(" but you are already on another call.");
            commsLogger.i(outline1.toString());
            call.hangup(Call.HangupRequest.ThisDevice);
        } else if (MMSDKManager.isCommsOnLenovoSmartTabletEnabled(this.mContext)) {
            LOG.i("Cannot process incoming call as MMSDK will handle it");
            call.hangup(Call.HangupRequest.ThisDevice);
        } else {
            DeviceCallingService deviceCallingService = this.deviceCallingService;
            if (deviceCallingService != null) {
                Call callByCallId = deviceCallingService.getCallByCallId(call.getCallId());
                if (callByCallId == null) {
                    LOG.i("Call no longer exists.");
                    return;
                } else if (callByCallId.getState() == Call.State.Complete) {
                    LOG.i("Call is already over");
                    return;
                }
            }
            call.getEventTracer().mark(EventTracerConfig.Event.Callee_NotifySpeechletOfInvite);
            call.getEventTracer().mark(EventTracerConfig.Event.Callee_Received_Ring_tts);
            this.sipClientState.setCallState(SipClientState.CallState.INBOUND_RINGING);
            this.sipClientState.setRealTimeTextMetrics(new RealTimeTextMetrics(RealTimeTextConstants.RTT_NOT_REQUESTED, "DISABLED"));
            updateParticipantInformation(call);
            String callId = call.getCallId();
            startHeadupIncomingCallNotification(call.getRemoteParticipant().getProviderSpecifiedId(), callId);
            this.callManager.enqueueActiveCall(callId);
            this.callContext.setRemoteParticipantName(call.getRemoteParticipant().getName());
            MetricsHelper.recordCounterMetricOperational(MetricKeys.CALL_INCOMING_RECEIVED, 1.0d);
            if (TelecomUtils.setAndGetTelecomSupported(this.mContext)) {
                if (!Utils.getBooleanPreferenceFromSharedPrefs(this.mContext, TelecomConstants.SHARED_PREF_KEY_TELECOM_REGISTERED, false)) {
                    TelecomUtils.registerPhoneAccount(TelecomConstants.INCOMING_CALL, this.mContext);
                }
                CommsDaggerWrapper.getComponent().getTelecomBridge().addTelecomCall(call, true);
            }
            CallType callType = CallUtils.setCallType(false, call.getMediaStatus().isRemoteVideoCapable(), false, true);
            this.sipClientState.setCurrentActiveCall(call);
            this.sipClientState.setCallId(callId);
            this.sipClientState.setCallProvider(CallProvider.fromString(call.getProvider()));
            this.callInitiationAuthority.setPreviousCallState(this.sipClientState.isGroupCall());
            this.mCurrentAlexaInterface.acquireCommsFocus();
            this.sipClientState.setStatusCode(Integer.toString(SipStatusCode.OK.getCode()));
            this.mCurrentAlexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.INBOUND_CALL_RECEIVED, callId, this.sipClientState));
            call.notifyRinging();
            this.callHistoryHelper.setCallType(callId, callType);
            this.callHistoryHelper.setCallProvider(callId, CallProvider.A2A);
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline12 = GeneratedOutlineSupport.outline1("MPU enabled for the incoming call : ");
            outline12.append(this.sipClientState.isEnhancedProcessedCall());
            commsLogger2.i(outline12.toString());
            ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.deecomms.calling.controller.-$$Lambda$DeviceCallingServiceEventListener$E_aYtJ9FUM3Emmvuj3ef6TOD5l8
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceCallingServiceEventListener.this.lambda$handleIncomingCall$1$DeviceCallingServiceEventListener();
                }
            });
        }
    }

    public void handleOutgoingCall(Call call) {
        String callId = this.sipClientState.getCallId();
        if (callId != null && callId.equals(call.getCallId())) {
            if (TelecomUtils.setAndGetTelecomSupported(this.mContext)) {
                if (!Utils.getBooleanPreferenceFromSharedPrefs(this.mContext, TelecomConstants.SHARED_PREF_KEY_TELECOM_REGISTERED, false)) {
                    TelecomUtils.registerPhoneAccount(TelecomConstants.OUTGOING_CALL, this.mContext);
                }
                CommsDaggerWrapper.getComponent().getTelecomBridge().addTelecomCall(call, false);
            }
            CallType callType = this.sipClientState.getCallType();
            this.sipClientState.setCallState(SipClientState.CallState.OUTBOUND_RINGING);
            this.sipClientState.setCurrentActiveCall(call);
            this.sipClientState.setCallProvider(CallProvider.fromString(call.getProvider()));
            this.mRemoteParticipantId = call.getRemoteParticipant().getProviderSpecifiedId();
            this.sipClientState.setRemoteParticipantId(this.mRemoteParticipantId);
            updateParticipantInformation(call);
            this.mCurrentAlexaInterface.acquireCommsFocus();
            this.sipClientState.setStatusCode(Integer.toString(SipStatusCode.OK.getCode()));
            String str = callType.isFromVox() ? CallTypeEventConstants.VUI_ONLY : CallTypeEventConstants.GUI_ONLY;
            CallTypeEventModel callTypeEventModel = new CallTypeEventModel(str, Collections.emptyList(), callId, this.sipClientState);
            if (this.capabilitiesManager.isRemovingGUIInCallPromptsEnabled() && str == CallTypeEventConstants.GUI_ONLY) {
                this.mCurrentAlexaInterface.sendCommsEvent(new CallTypeEvent(CallTypeEventType.OUTBOUND_CALL_REQUEST, callTypeEventModel));
            }
            this.mCurrentAlexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.OUTBOUND_CALL_REQUESTED, call.getCallId(), this.sipClientState));
            LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(new Intent(Constants.CALL_ADDED));
            ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.deecomms.calling.controller.-$$Lambda$DeviceCallingServiceEventListener$OF8ruXZ107Km_ioil951ei1Zx-I
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceCallingServiceEventListener.this.lambda$handleOutgoingCall$0$DeviceCallingServiceEventListener();
                }
            });
            return;
        }
        LOG.i(String.format("Hanging up outgoing call since it is already cancelled by the user. CurrentCallId = %s, CallIdFromRingService = %s", callId, call.getCallId()));
        call.hangup(Call.HangupRequest.Everywhere);
    }

    public /* synthetic */ void lambda$handleIncomingCall$1$DeviceCallingServiceEventListener() {
        this.mStateMachine.onEvent(CallViewStateMachine.Event.OnIncomingInvitationReceived);
    }

    public /* synthetic */ void lambda$handleOutgoingCall$0$DeviceCallingServiceEventListener() {
        this.mStateMachine.onEvent(CallViewStateMachine.Event.OnOutgoingInvitationSent);
    }

    public /* synthetic */ void lambda$onAccepted$4$DeviceCallingServiceEventListener() {
        this.mStateMachine.onEvent(CallViewStateMachine.Event.OnInvitationAccepted);
    }

    public /* synthetic */ void lambda$onAuthTokenExpiring$3$DeviceCallingServiceEventListener(long j, DeviceCallingService deviceCallingService) {
        LOG.i("onAuthTokenExpiring: expiryInSecs=" + j);
        boolean z = false;
        String commsId = this.commsIdentityManager.getCommsId("DCSEL::onAuthTokenExpiring", false);
        if (TextUtils.isEmpty(commsId)) {
            LOG.w("Unable to call updateAuthToken because comms id is not valid");
        } else if (!this.commsConnectivityMonitor.waitForConnected(5000L)) {
            LOG.w("Unable to call updateAuthToken because there is no network");
        } else {
            AuthTokenHelper.AuthToken fetchAuthToken = AuthTokenHelper.fetchAuthToken(commsId, AuthTokenHelper.TokenType.SIP, true);
            if (fetchAuthToken != null && !TextUtils.isEmpty(fetchAuthToken.getValue())) {
                if (j <= 0) {
                    z = true;
                }
                if (z) {
                    setAttemptingRegistration(true);
                }
                LOG.d("onAuthTokenExpiring: token=" + fetchAuthToken);
                deviceCallingService.updateAuthToken(fetchAuthToken.getValue(), fetchAuthToken.getExpiryInSecs(), z);
                return;
            }
            LOG.w("Unable to call updateAuthToken because we could not fetch auth token");
            this.provisioningManager.checkStatus();
        }
    }

    public /* synthetic */ void lambda$onCallAdded$2$DeviceCallingServiceEventListener(Call call, long j) {
        handleIncomingCall(call);
        recordPushNotificationSipInviteLatencyMetric(j);
        notifyObservers();
    }

    public /* synthetic */ void lambda$onError$6$DeviceCallingServiceEventListener() {
        this.mStateMachine.onEvent(CallViewStateMachine.Event.OnCallDisconnected);
    }

    public /* synthetic */ void lambda$onError$7$DeviceCallingServiceEventListener() {
        this.mStateMachine.onEvent(CallViewStateMachine.Event.OnInvitationRejected);
    }

    public /* synthetic */ void lambda$onHangup$5$DeviceCallingServiceEventListener(boolean z, String str, HangupReason hangupReason) {
        if (z) {
            this.mStateMachine.onEvent(CallViewStateMachine.Event.OnCallDisconnected);
        } else {
            this.mStateMachine.onEvent(CallViewStateMachine.Event.OnInvitationRejected);
        }
        if (Utils.getBooleanPreferenceFromSharedPrefs(this.mContext, TelecomConstants.SHARED_PREF_KEY_TELECOM_SUPPORTED, false)) {
            CommsDaggerWrapper.getComponent().getTelecomBridge().destroyTelecomConnection(str, hangupReason);
        } else {
            CommsDaggerWrapper.getComponent().getBluetoothHeadsetHelper().stop();
        }
    }

    void notifyListeners(DeviceCallingService.State state, List<SipStatusListener> list) {
        LOG.i("Notifying Listeners");
        for (SipStatusListener sipStatusListener : list) {
            sipStatusListener.onSipStatusChanged(state);
        }
    }

    public void notifyObservers() {
        this.mCallingContentProviderNotifier.notifyObservers();
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onAccepted(Call call) {
        LOG.i(String.format("onAccepted :: callId = %s, side = %s", call.getCallId(), call.getOrigin().toString()));
        if (Utils.isAndroid10AndAboveFOS() || Utils.isAndroid10ChangesEnabled()) {
            IncomingPushAndroidService.dismissHeadupNotification(this.mContext);
        }
        this.callHistoryHelper.setCallStatusCode(call.getCallId(), SipStatusCode.ACCEPTED.getCode());
        CallType callType = this.callHistoryHelper.getCallType(call.getCallId());
        MediaStatus mediaStatus = call.getMediaStatus();
        this.sipClientState.setCallState(SipClientState.CallState.ACTIVE);
        this.sipClientState.setCallProvider(CallProvider.fromString(call.getProvider()));
        if (mediaStatus.isLocalVideoEnabled() && !mediaStatus.isRemoteVideoCapable()) {
            LOG.i("Call has been downgraded to audio");
            this.callManager.setCallDowngraded(true);
            this.callContext.setCallDowngradedToAudio(true);
        } else {
            this.callManager.setCallDowngraded(false);
            this.callContext.setCallDowngradedToAudio(false);
        }
        if (Utils.getBooleanPreferenceFromSharedPrefs(this.mContext, Constants.ACCESSORY_PRIVACY_STATUS, false)) {
            CallUtils.toggleMuteAlexaCallForAccessories(true, call);
        }
        CallType callType2 = CallUtils.setCallType(call.isDropInCall(), mediaStatus.isRemoteVideoCapable(), callType.isDeviceTargeted(), callType.isA2A());
        this.callHistoryHelper.setCallType(call.getCallId(), callType2);
        if (Call.Side.Local.equals(call.getOrigin())) {
            LOG.i("Outgoing call is accepted");
            this.sipClientState.setStatusCode(Integer.toString(SipStatusCode.ACCEPTED.getCode()));
            this.mCurrentAlexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.OUTBOUND_CALL_ACCEPTED, call.getCallId(), this.sipClientState));
        } else {
            LOG.d("Incoming call is accepted");
            if (callType2.isAudio()) {
                ProximityLockHelper.getInstance(this.mContext).markCallStarted();
            }
            this.sipClientState.setStatusCode(Integer.toString(SipStatusCode.ACCEPTED.getCode()));
            this.mCurrentAlexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.INBOUND_CALL_ACCEPTED, call.getCallId(), this.sipClientState));
            this.callHistoryHelper.answeredCall(call.getCallId());
        }
        if (Utils.getBooleanPreferenceFromSharedPrefs(this.mContext, TelecomConstants.SHARED_PREF_KEY_TELECOM_SUPPORTED, false)) {
            CommsDaggerWrapper.getComponent().getTelecomBridge().activateTelecomConnection(call.getCallId());
        } else {
            CommsDaggerWrapper.getComponent().getBluetoothHeadsetHelper().start();
        }
        this.mCallingContentProviderNotifier.notifyObservers();
        Utils.setAudioMode(this.mAudioManager);
        if (!mediaStatus.isRemoteVideoCapable() && mediaStatus.isLocalVideoEnabled() && this.mAudioManager.isSpeakerphoneOn()) {
            this.mAudioManager.setSpeakerphoneOn(false);
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Tried to turn speakerphone false; speakerphone was set to ");
            outline1.append(this.mAudioManager.isSpeakerphoneOn());
            commsLogger.i(outline1.toString());
        }
        this.mRemoteParticipantId = call.getRemoteParticipant().getProviderSpecifiedId();
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.deecomms.calling.controller.-$$Lambda$DeviceCallingServiceEventListener$m1_9T6FFVfSuU1f0WorzC4oFl9M
            @Override // java.lang.Runnable
            public final void run() {
                DeviceCallingServiceEventListener.this.lambda$onAccepted$4$DeviceCallingServiceEventListener();
            }
        });
        SetupCallHelper.recordInitiationMetrics(call.getCallId(), this.sipClientState.getCspId(), SipStatusCode.OK, SetupCallHelper.MetadataBuilder.newBuilder().withCallType(callType2).withCallOrigin(call.getOrigin()).withSource(SetupCallHelper.Source.Connected).withReason("Call accepted"));
        if (callType2.isDropIn()) {
            TimerMetric generateClickstream = TimerMetric.generateClickstream(MetricKeys.CALL_TIME_TO_DROP_IN);
            MetricsHelper.addCallId(generateClickstream, call);
            MetricsHelper.addCallType(generateClickstream, callType2);
            MetricsHelper.addConnectionType(generateClickstream, call);
            MetricsHelper.stopTimerMetric(generateClickstream);
        }
        this.activeCallInfo.put(call.getCallId(), new ActiveCallInfo());
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingServiceListener
    public void onAuthTokenExpiring(final DeviceCallingService deviceCallingService, final long j) {
        AsyncTask.execute(new Runnable() { // from class: com.amazon.deecomms.calling.controller.-$$Lambda$DeviceCallingServiceEventListener$x7Xybc_YGd2X7U9b8WC9aaXuSOM
            @Override // java.lang.Runnable
            public final void run() {
                DeviceCallingServiceEventListener.this.lambda$onAuthTokenExpiring$3$DeviceCallingServiceEventListener(j, deviceCallingService);
            }
        });
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingServiceListener
    public void onCallAdded(final Call call) {
        LOG.i(String.format("[Comms-calling]: New Call had been added. CallId = %s, side = %s, localParticipantId = %s, remoteParticipantId = %s", call.getCallId(), call.getOrigin().toString(), LOG.sensitive(call.getLocalParticipant().getProviderSpecifiedId()), LOG.sensitive(call.getRemoteParticipant().getProviderSpecifiedId())));
        final long time = new Date(System.currentTimeMillis()).getTime();
        if (!GetDeviceCommsPreferences.isDeviceCommunicationsEnabled(this.mContext, this.capabilitiesManager)) {
            call.hangup(Call.HangupRequest.ThisDevice);
            return;
        }
        call.registerCallListener(this);
        this.callingAPIMonitor.onCallAdded(call);
        this.sipClientState.setSide(call.getOrigin().name());
        this.callContext.setCurrentCall(call);
        publishHVAEvent(false, this.sipClientState.getCallType());
        if (call.getOrigin() == Call.Side.Local) {
            handleOutgoingCall(call);
            recordPushNotificationSipInviteLatencyMetric(time);
            notifyObservers();
            return;
        }
        new MPUSettingHandler(this.commsIdentityManager.getHomeGroupId("onCallAdded", false), this.sipClientState, Arrays.asList(Constants.GET_MPU_ENABLED, Constants.GET_SETTING), call.getRemoteParticipant().getProviderSpecifiedId(), call.getProvider(), new Runnable() { // from class: com.amazon.deecomms.calling.controller.-$$Lambda$DeviceCallingServiceEventListener$1YyxUClFCB8SdUdc6HeOe2yuapg
            @Override // java.lang.Runnable
            public final void run() {
                DeviceCallingServiceEventListener.this.lambda$onCallAdded$2$DeviceCallingServiceEventListener(call, time);
            }
        }).execute(new Void[0]);
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingServiceListener
    public void onCallRemoved(Call call) {
        LOG.i(String.format("Existing Call had been removed. CallId = %s, side = %s, localParticipantId = %s, remoteParticipantId = %s", call.getCallId(), call.getOrigin().toString(), LOG.sensitive(call.getLocalParticipant().getProviderSpecifiedId()), LOG.sensitive(call.getRemoteParticipant().getProviderSpecifiedId())));
        if (Utils.isAndroid10AndAboveFOS() || Utils.isAndroid10ChangesEnabled()) {
            IncomingPushAndroidService.dismissHeadupNotification(this.mContext);
            this.mAlexaAudioPlayer.stop(1);
        }
        call.unregisterCallListener(this);
        this.mCurrentAlexaInterface.releaseCommsFocus();
        this.callingAPIMonitor.onCallRemoved(call);
        ActiveCallInfo remove = this.activeCallInfo.remove(call.getCallId());
        if (remove != null) {
            CallUtils.recordCallCompleted(call, remove.errorCode, remove.errorReason);
        }
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onEarlyMedia(Call call) {
        LOG.i(String.format("onEarlyMedia :: callId = %s, side = %s", call.getCallId(), call.getOrigin().toString()));
        this.callHistoryHelper.setCallStatusCode(call.getCallId(), SipStatusCode.PROGRESS.getCode());
        handleRinging(call);
        Utils.setAudioMode(this.mAudioManager);
        if (!Utils.getBooleanPreferenceFromSharedPrefs(this.mContext, TelecomConstants.SHARED_PREF_KEY_TELECOM_SUPPORTED, false)) {
            CommsDaggerWrapper.getComponent().getBluetoothHeadsetHelper().start();
        }
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingServiceListener
    public void onError(String str, ErrorCode errorCode, int i, String str2) {
        LOG.e(String.format("DeviceCallingListener CallError - callId = %s, errorCode = %s, internalErrorCode = %d, errorDesc = %s", str, errorCode, Integer.valueOf(i), str2));
        if (Utils.isAndroid10AndAboveFOS() || Utils.isAndroid10ChangesEnabled()) {
            IncomingPushAndroidService.dismissHeadupNotification(this.mContext);
        }
        this.callHistoryHelper.setCallStatusCode(str, i);
        if (errorCode == ErrorCode.RegistrationNotFound) {
            this.sipClientState.setStatusCode(Integer.toString(errorCode.getValue()));
        } else {
            this.sipClientState.setStatusCode(Integer.toString(i));
        }
        this.sipClientState.setReason(str2);
        this.mCurrentAlexaInterface.releaseCommsFocus();
        if (i != SipStatusCode.SERVICE_UNAVAILABLE.getCode()) {
            this.mCurrentAlexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.CALL_ERROR, str, this.sipClientState));
        }
        if (CallUtils.isCallIdValid(str)) {
            CallType callType = this.callHistoryHelper.getCallType(str);
            if (callType == CallType.NONE) {
                callType = this.sipClientState.getCallType();
            }
            String cspId = this.sipClientState.getCspId();
            SetupCallHelper.MetadataBuilder withSource = SetupCallHelper.MetadataBuilder.newBuilder().withCallType(callType).withSource(SetupCallHelper.Source.SipCallPreparation);
            SetupCallHelper.recordInitiationMetrics(str, cspId, i, withSource.withReason("Call Error (call id): " + str2));
        }
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onHangup(Call call, final HangupReason hangupReason) {
        final boolean z = true;
        LOG.i(String.format("onHangup :: callId = %s, hangupReason = %s", call.getCallId(), hangupReason.toString()));
        if (Utils.isAndroid10AndAboveFOS() || Utils.isAndroid10ChangesEnabled()) {
            IncomingPushAndroidService.dismissHeadupNotification(this.mContext);
        }
        final String callId = call.getCallId();
        this.mCurrentAlexaInterface.releaseCommsFocus();
        this.callHistoryHelper.keepAlive(callId);
        if (hangupReason != HangupReason.LocalHangup && hangupReason != HangupReason.RemoteHangup && this.sipClientState.getCallState() != SipClientState.CallState.ACTIVE) {
            z = false;
        }
        if (this.callManager.isActiveCallPresentWithId(callId)) {
            this.sipClientState.setCallState(SipClientState.CallState.INACTIVE);
            processHangupReason(call, hangupReason);
            if (this.sipClientState.getStatusCode() == null) {
                this.sipClientState.setStatusCode(Integer.toString(SipStatusCode.OK.getCode()));
                LOG.i("onCallRemoved does not have a status code. this should not be the case");
            }
            this.mCurrentAlexaInterface.releaseCommsFocus();
            this.mCurrentAlexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.CALL_DISCONNECTED, call.getCallId(), this.sipClientState));
            this.callManager.dequeueActiveCall(callId);
            ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.deecomms.calling.controller.-$$Lambda$DeviceCallingServiceEventListener$zw0jmRBVaxfxZDaDGO8j3CWFsTM
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceCallingServiceEventListener.this.lambda$onHangup$5$DeviceCallingServiceEventListener(z, callId, hangupReason);
                }
            });
            this.mAudioManager.setMode(0);
            LOG.i("Audio mode is set to normal after hanging up");
        }
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onMediaDTMFResponse(Call call, boolean z, String str) {
        HalloEvent halloEvent;
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("OnMediaDTMFResponse: CallId= ");
        outline1.append(call.getCallId());
        outline1.append(" success= ");
        outline1.append(z);
        outline1.append(" statusCode= ");
        GeneratedOutlineSupport1.outline177(outline1, str, commsLogger);
        if (z) {
            halloEvent = new HalloEvent(HalloEventType.SEND_DTMF_SUCCEEDED, call.getCallId(), this.sipClientState);
        } else {
            this.sipClientState.setStatusCode(str);
            halloEvent = new HalloEvent(HalloEventType.SEND_DTMF_FAILED, call.getCallId(), this.sipClientState);
        }
        this.mCurrentAlexaInterface.sendCommsEvent(halloEvent);
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onReconnect(Call call, boolean z, boolean z2, String str) {
        LOG.i(String.format("onReconnect :: callId = %s, side = %s", call.getCallId(), call.getOrigin().toString()));
        recordReconnectMetric(call, z2, z, str);
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onRemoteParticipantUpdated(Call call) {
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onRinging(Call call) {
        LOG.i(String.format("onRinging :: callId = %s, side = %s", call.getCallId(), call.getOrigin().toString()));
        this.callHistoryHelper.setCallStatusCode(call.getCallId(), SipStatusCode.RINGING.getCode());
        handleRinging(call);
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onSignalingDTMFResponse(Call call, int i, String str) {
        LOG.i(String.format("onSignalingDTMFResponse: callId=%s, statusCode=%d, reason=%s", call.getCallId(), Integer.valueOf(i), str));
        this.sipClientState.setStatusCode(Integer.toString(i));
        this.mCurrentAlexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.SIP_INFO_RESPONSE, call.getCallId(), this.sipClientState));
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingServiceListener
    public void onStateChanged(DeviceCallingService deviceCallingService, DeviceCallingService.State state) {
        LOG.i(String.format(" onStateChanged - %s -> %s", this.sipClientState.getSipRegistrationStatus(), state));
        HashMap hashMap = new HashMap();
        String sIPRegistrationReason = DeviceCallingAndroidService.getSIPRegistrationReason(this.mContext);
        GeneratedOutlineSupport.outline4("SIP registration source was ", sIPRegistrationReason, LOG);
        hashMap.put("source", sIPRegistrationReason);
        int i = AnonymousClass8.$SwitchMap$com$amazon$comms$calling$service$DeviceCallingService$State[state.ordinal()];
        if (i == 1) {
            MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, MetricKeys.SIP_REG_FAIL, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, hashMap);
            setAttemptingRegistration(false);
            boolean isCallReconnectv3Enabled = this.capabilitiesManager.isCallReconnectv3Enabled();
            GeneratedOutlineSupport.outline5("CallReconnectFeatureEnabled= ", isCallReconnectv3Enabled, LOG);
            if (isCallReconnectv3Enabled) {
                if (SipClientState.CallState.ACTIVE == this.sipClientState.getCallState()) {
                    LOG.i("Connectivity was restored. SIP is registered. Attempting to perform call reconnect.");
                    new PerformCallReconnectTask(Utils.getSipURIforRegisteredUser(this.mContext), this.sipClientState.getRemoteParticipantId(), this.sipClientState.getRemoteUri()).execute(new Void[0]);
                }
            } else {
                LOG.i("Call Reconnect feature not enabled for this user");
            }
        } else if (i == 2) {
            if (isAttemptingRegistration()) {
                CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.SIP_REG_FAIL);
                hashMap.put("errorSource", sDeviceCallingServiceState.name());
                generateOperational.getMetadata().putAll(hashMap);
                MetricsHelper.addCallAttributes(generateOperational, this.sipClientState.getCurrentActiveCall());
                MetricsHelper.recordSingleOccurrence(generateOperational);
            }
            setAttemptingRegistration(false);
        } else if (i == 3) {
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.SIP_UNINITIALIZED);
            if (isAttemptingRegistration()) {
                MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, MetricKeys.SIP_REG_FAIL, 1.0d, hashMap);
            }
            setAttemptingRegistration(false);
        }
        DeviceCallingAndroidService.clearSIPRegistrationReason(this.mContext);
        sDeviceCallingServiceState = state;
        notifyListeners(state, sSipStatusListenerList);
        this.sipClientState.setSipRegistrationStatus(state);
    }

    @VisibleForTesting
    void processHangupReason(Call call, HangupReason hangupReason) {
        int i;
        String callId = call.getCallId();
        SetupCallHelper.Source source = SetupCallHelper.Source.Disconnected;
        if (call.getCallDetails() != null && call.getCallDetails().getCallConnectedTime() != null && call.getCallDetails().getCallConnectedTime().getMillis() > 0) {
            source = SetupCallHelper.Source.Connected;
        }
        CallType callType = this.callHistoryHelper.getCallType(call.getCallId());
        Call.Side origin = call.getOrigin();
        this.sipClientState.setCallProvider(CallProvider.fromString(call.getProvider()));
        String cspId = this.sipClientState.getCspId();
        SetupCallHelper.MetadataBuilder withSource = SetupCallHelper.MetadataBuilder.newBuilder().withCallType(callType).withCallOrigin(origin).withSource(source);
        SetupCallHelper.MetadataBuilder withReason = withSource.withReason("Hangup: " + hangupReason);
        if (hangupReason == HangupReason.Cancelled) {
            this.sipClientState.setStatusCode(Integer.toString(SipStatusCode.REQUEST_TERMINATED.getCode()));
            if (call.getOrigin() == Call.Side.Local) {
                this.mCallEndStatus = "";
                this.mCurrentAlexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.LOCAL_HANG_UP, callId, this.sipClientState));
            } else if (call.getOrigin() == Call.Side.Remote) {
                this.mCallEndStatus = "";
                this.mCurrentAlexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.REMOTE_HANG_UP_ON_RING, callId, this.sipClientState));
                this.commsNotificationManager.showMissedCallNotification(call);
            }
            this.callHistoryHelper.setCallStatusCode(callId, SipStatusCode.REQUEST_TERMINATED.getCode());
            SetupCallHelper.recordInitiationMetrics(callId, cspId, SipStatusCode.OK, withReason);
        } else if (hangupReason == HangupReason.Rejected) {
            this.sipClientState.setStatusCode(Integer.toString(SipStatusCode.DECLINE.getCode()));
            if (call.getOrigin() == Call.Side.Local) {
                this.mCallEndStatus = Utils.getStringFromResource(R.string.callee_unavailable);
                this.mCurrentAlexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.CALL_CANCEL, callId, this.sipClientState));
            } else if (call.getOrigin() == Call.Side.Remote) {
                this.mCurrentAlexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.LOCAL_HANG_UP, callId, this.sipClientState));
                this.mCallEndStatus = CALL_END_PREMATURELY_INCOMING_LOCAL;
            }
            this.callHistoryHelper.setCallStatusCode(callId, SipStatusCode.DECLINE.getCode());
            SetupCallHelper.recordInitiationMetrics(callId, cspId, SipStatusCode.DECLINE, withReason);
        } else if (hangupReason == HangupReason.AnsweredElsewhere) {
            this.mCallEndStatus = Utils.getStringFromResource(R.string.call_answered_elsewhere);
            recordClickMetric(MetricKeys.CALL_FAIL_TO_ANOTHER_DEVICE, call);
            this.sipClientState.setStatusCode(Integer.toString(SipStatusCode.REQUEST_TERMINATED.getCode()));
            this.callHistoryHelper.setCallStatusCode(callId, SipStatusCode.REQUEST_TERMINATED.getCode());
            SetupCallHelper.recordInitiationMetrics(callId, cspId, SipStatusCode.REQUEST_TERMINATED, withReason);
        } else if (hangupReason == HangupReason.RejectedElsewhere) {
            this.mCallEndStatus = "";
            this.callHistoryHelper.setCallStatusCode(callId, SipStatusCode.DECLINE.getCode());
            this.sipClientState.setStatusCode(Integer.toString(SipStatusCode.DECLINE.getCode()));
            SetupCallHelper.recordInitiationMetrics(callId, cspId, SipStatusCode.DECLINE, withReason);
        } else {
            if (hangupReason == HangupReason.Busy) {
                this.mCallEndStatus = Utils.getStringFromResource(R.string.callee_unavailable);
                i = R.raw.alexa_app_system_state_error_generic_1;
                recordClickMetric(MetricKeys.CALL_BUSY, call);
                this.callHistoryHelper.setCallStatusCode(callId, SipStatusCode.BUSY_HERE.getCode());
                this.sipClientState.setStatusCode(Integer.toString(SipStatusCode.BUSY_HERE.getCode()));
                this.mCurrentAlexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.CALL_CANCEL, callId, this.sipClientState));
                SetupCallHelper.recordInitiationMetrics(callId, cspId, SipStatusCode.BUSY_HERE, withReason);
            } else if (hangupReason == HangupReason.LocalHangup) {
                this.mCallEndStatus = Utils.getStringFromResource(R.string.call_end_status_mature);
                i = R.raw.alexa_app_system_comm_call_disconnected;
                this.mDurationInSeconds = this.callTimerManager.getDurationInSeconds();
                recordClickMetric(MetricKeys.CALL_HANGUP, call);
                this.sipClientState.setStatusCode(Integer.toString(SipStatusCode.OK.getCode()));
                this.callHistoryHelper.setCallStatusCode(callId, SipStatusCode.OK.getCode());
                this.mCurrentAlexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.LOCAL_HANG_UP, callId, this.sipClientState));
                this.callHistoryHelper.userEndedCall(callId);
            } else if (hangupReason == HangupReason.RemoteHangup) {
                this.mCallEndStatus = Utils.getStringFromResource(R.string.call_end_status_mature);
                i = R.raw.alexa_app_system_comm_call_disconnected;
                this.mDurationInSeconds = this.callTimerManager.getDurationInSeconds();
                recordClickMetric(MetricKeys.CALL_HANGUP, call);
                this.sipClientState.setStatusCode(Integer.toString(SipStatusCode.OK.getCode()));
                this.callHistoryHelper.setCallStatusCode(callId, SipStatusCode.OK.getCode());
                this.mCurrentAlexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.REMOTE_HANG_UP, callId, this.sipClientState));
            } else if (hangupReason == HangupReason.TimedOut) {
                this.mCallEndStatus = Utils.getStringFromResource(R.string.callee_unavailable);
                i = R.raw.alexa_app_system_state_error_generic_1;
                recordClickMetric(MetricKeys.CALL_TIMEOUT, call);
                this.sipClientState.setStatusCode(Integer.toString(SipStatusCode.TEMPORARILY_UNAVAILABLE.getCode()));
                this.callHistoryHelper.setCallStatusCode(callId, SipStatusCode.TEMPORARILY_UNAVAILABLE.getCode());
                if (call.getOrigin() == Call.Side.Local) {
                    this.mCurrentAlexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.CALL_CANCEL, callId, this.sipClientState));
                } else {
                    this.mCurrentAlexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.LOCAL_HANG_UP, call.getCallId(), this.sipClientState));
                }
                SetupCallHelper.recordInitiationMetrics(callId, cspId, SipStatusCode.TEMPORARILY_UNAVAILABLE, withReason);
            } else if (hangupReason == HangupReason.Offline) {
                i = R.raw.alexa_app_system_state_error_generic_1;
                handleOfflineReason(callId, withReason, call);
            } else {
                processUnknownHangUpReason(callId, withReason);
            }
            handleRingtone(i);
            publishHVAEvent(true, callType);
        }
        i = 0;
        handleRingtone(i);
        publishHVAEvent(true, callType);
    }

    public void recordPushNotificationSipInviteLatencyMetric(long j) {
        long longPreferenceFromSharedPrefs = Utils.getLongPreferenceFromSharedPrefs(this.mContext, Constants.NOTIFICATION_CALL_TOKEN_START_TIME, 0L);
        if (longPreferenceFromSharedPrefs > 0) {
            Utils.writeLongPreferenceToSharedPrefs(this.mContext, Constants.NOTIFICATION_CALL_TOKEN_START_TIME, 0L);
            TimerMetric timerMetric = new TimerMetric(CommsMetric.MetricType.Operational, MetricKeys.NOTIFICATION_CALL_INVITE_LATENCY);
            timerMetric.setTimeDelta(j - longPreferenceFromSharedPrefs);
            MetricsHelper.recordTimerMetric(timerMetric);
        }
    }

    public boolean setAttemptingRegistration(boolean z) {
        return this.attemptingRegistration.getAndSet(z);
    }

    void setupCallIntent(@NonNull String str, @NonNull FullContactName fullContactName) {
        this.mRemoteContactName = fullContactName;
        setupCallIntentInternal(str, ContactUtils.getFullName(fullContactName));
    }

    @VisibleForTesting
    void startHeadupIncomingCallNotification(String str, @NonNull String str2) {
        boolean booleanPreferenceFromSharedPrefs = Utils.getBooleanPreferenceFromSharedPrefs(this.mContext, Constants.IS_APP_IN_FOREGROUND, false);
        String stringPreferenceFromSharedPrefs = Utils.getStringPreferenceFromSharedPrefs(this.mContext, Constants.RECENT_CALLID_VIA_PUSH, "");
        LOG.i("[Comms-calling]: recentCallIdViaPush: %s, SIP_callId: %s", stringPreferenceFromSharedPrefs, str2);
        LOG.i(booleanPreferenceFromSharedPrefs ? "[Comms-calling]: app is foreground" : "[Comms-calling]: app is background");
        if (!booleanPreferenceFromSharedPrefs) {
            if ((!Utils.isAndroid10AndAboveFOS() && !Utils.isAndroid10ChangesEnabled()) || !stringPreferenceFromSharedPrefs.equals(str2)) {
                return;
            }
            LOG.i("[Comms-calling]: app is in background. Show headup notification.");
            getContactNameAndShowIncomingCallNotification(str);
        }
    }

    private static void recordClickMetric(String str, Call call) {
        recordClickMetric(str, call, null);
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onError(Call call, ErrorCode errorCode, int i, String str) {
        CommsLogger commsLogger = LOG;
        commsLogger.e(" CallListener onError - errorCode = " + errorCode + ", internalErrorCode = " + i + ", CallId = " + call.getCallId() + ", Desc = " + str);
        if (Utils.isAndroid10AndAboveFOS() || Utils.isAndroid10ChangesEnabled()) {
            IncomingPushAndroidService.dismissHeadupNotification(this.mContext);
        }
        int i2 = AnonymousClass8.$SwitchMap$com$amazon$comms$calling$service$ErrorCode[errorCode.ordinal()];
        if (i2 == 1) {
            recordClickMetric(MetricKeys.CALL_FAIL_NO_SIP, call, Integer.valueOf(i));
        } else if (i2 != 2) {
            recordClickMetric(MetricKeys.CALL_FAIL_UNKNOWN, call, Integer.valueOf(i));
        } else {
            recordClickMetric(MetricKeys.CALL_FAIL_LOST_CONNECTION, call, Integer.valueOf(i));
        }
        this.callHistoryHelper.setCallStatusCode(call.getCallId(), i);
        if (this.activeCallInfo.remove(call.getCallId()) != null) {
            this.activeCallInfo.put(call.getCallId(), new ActiveCallInfo(i, str));
        }
        recordClickMetric(String.format(MetricKeys.CALL_FAIL_WITH_ERROR_CODE, Integer.valueOf(i)), call, Integer.valueOf(i));
        if (this.callManager.isActiveCallPresentWithId(call.getCallId())) {
            this.mAlexaAudioPlayer.play(R.raw.alexa_app_system_state_error_generic_1, false, 0, 1);
            if (this.sipClientState.getCallState() == SipClientState.CallState.ACTIVE) {
                ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.deecomms.calling.controller.-$$Lambda$DeviceCallingServiceEventListener$KkDfFLI6U6H_jzVY1crYtM65YPg
                    @Override // java.lang.Runnable
                    public final void run() {
                        DeviceCallingServiceEventListener.this.lambda$onError$6$DeviceCallingServiceEventListener();
                    }
                });
            } else {
                ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.deecomms.calling.controller.-$$Lambda$DeviceCallingServiceEventListener$0rwim4hrTErpCibxIxPbsUrhCDc
                    @Override // java.lang.Runnable
                    public final void run() {
                        DeviceCallingServiceEventListener.this.lambda$onError$7$DeviceCallingServiceEventListener();
                    }
                });
            }
            this.sipClientState.setCallState(SipClientState.CallState.INACTIVE);
            if (errorCode == ErrorCode.RegistrationNotFound) {
                this.sipClientState.setStatusCode(Integer.toString(errorCode.getValue()));
            } else {
                this.sipClientState.setStatusCode(Integer.toString(i));
            }
            this.mCurrentAlexaInterface.releaseCommsFocus();
            this.callManager.dequeueActiveCall(call.getCallId());
            this.mCurrentAlexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.CALL_ERROR, call.getCallId(), this.sipClientState));
            if (Utils.getBooleanPreferenceFromSharedPrefs(this.mContext, TelecomConstants.SHARED_PREF_KEY_TELECOM_SUPPORTED, false)) {
                CommsDaggerWrapper.getComponent().getTelecomBridge().destroyTelecomConnection(call.getCallId(), HangupReason.Unknown);
            } else {
                CommsDaggerWrapper.getComponent().getBluetoothHeadsetHelper().stop();
            }
            this.mAudioManager.setMode(0);
            LOG.i("Audio mode is set to normal after an error");
        }
        CallType callType = this.callHistoryHelper.getCallType(call.getCallId());
        String callId = call.getCallId();
        String cspId = this.sipClientState.getCspId();
        SetupCallHelper.MetadataBuilder withSource = SetupCallHelper.MetadataBuilder.newBuilder().withCallType(callType).withCallOrigin(call.getOrigin()).withSource(SetupCallHelper.Source.Disconnected);
        SetupCallHelper.recordInitiationMetrics(callId, cspId, i, withSource.withReason("Call Error (call object): " + str));
    }
}
