package com.amazon.deecomms.calling.controller;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.adobe.xmp.XMPConst;
import com.amazon.comms.calling.instrumentation.EventTracerConfig;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.CallFilters;
import com.amazon.comms.calling.service.DataChannelConfiguration;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.calling.service.RealTimeText;
import com.amazon.comms.calling.sipclient.AuthenticationInfo;
import com.amazon.comms.calling.sipclient.MediaRelayInfo;
import com.amazon.comms.calling.sipclient.RegistrarConfiguration;
import com.amazon.comms.calling.sipclient.SipHeaders;
import com.amazon.comms.calling.sipclient.TurnEndPointInfo;
import com.amazon.comms.instrumentation.EventTracer;
import com.amazon.comms.instrumentation.EventTracerFactory;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.MetricsSession;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.enums.DataChannelLabel;
import com.amazon.deecomms.calling.model.MediaRelayInfoModel;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.calling.util.SetupCallHelper;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes12.dex */
public class CommandProcessor {
    private final CallManager callManager;
    private final CapabilitiesManager capabilitiesManager;
    private final DeviceCallingService deviceCallingApi;
    private final EventTracerFactory eventTracerFactory;
    private final RealTimeTextEnablementAuthority realTimeTextEnablementAuthority;
    private final SipClientState sipClientState;
    private static final CommsLogger sLog = CommsLogger.getLogger(Constants.LOG_TAG, CommandProcessor.class);
    private static final Pattern SIP_URL_PATTERN = Pattern.compile("sips:(.*):\\d*");

    public CommandProcessor(DeviceCallingService deviceCallingService, EventTracerFactory eventTracerFactory, SipClientState sipClientState, CallManager callManager, CapabilitiesManager capabilitiesManager, RealTimeTextEnablementAuthority realTimeTextEnablementAuthority) {
        if (deviceCallingService != null && eventTracerFactory != null) {
            this.callManager = callManager;
            this.deviceCallingApi = deviceCallingService;
            this.eventTracerFactory = eventTracerFactory;
            this.sipClientState = sipClientState;
            this.capabilitiesManager = capabilitiesManager;
            this.realTimeTextEnablementAuthority = realTimeTextEnablementAuthority;
            return;
        }
        throw new IllegalArgumentException("deviceCallingService or EventTracerFactory arg must be non-null");
    }

    private List<DataChannelConfiguration> getDataChannelConfigurations() {
        if (this.sipClientState.isEnhancedProcessedCall()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(DataChannelConfiguration.builder().label(DataChannelLabel.IN_CALL_APPLICATION.getName()).ordered(true).build());
            return arrayList;
        }
        return null;
    }

    private List<EventTracerFactory.Pivot> getPivots(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new EventTracerFactory.Pivot(MetricsSession.CALL_PROVIDER_PIVOT, str));
        arrayList.add(new EventTracerFactory.Pivot(MetricsSession.TRICKLE_ICE_PIVOT, XMPConst.FALSESTR));
        return arrayList;
    }

    private boolean isCallReconnectInitiationSupported(String str) {
        return CallProvider.A2A.equals(str) && this.capabilitiesManager.isCallReconnectv3Enabled();
    }

    private void markEvents(EventTracer eventTracer, boolean z) {
        eventTracer.mark(EventTracerConfig.Event.Caller_BeginCall_Directive);
        if (z) {
            eventTracer.mark(EventTracerConfig.Event.Caller_DropIn_BeginCall_Directive);
        }
    }

    public void acceptAsAudioCall() {
        List<Call> calls = this.deviceCallingApi.getCalls(CallFilters.INCOMING_CALLS_ONLY);
        if (calls.size() > 0) {
            Call call = calls.get(0);
            call.getCallId();
            sLog.i(" Accepted Call ");
            call.accept(Call.AcceptParams.builder().callReconnectInitiation(isCallReconnectInitiationSupported(call.getProvider())).videoEnabled(false).build());
            return;
        }
        sLog.e(" No Incoming Calls ");
        SetupCallHelper.recordInitiationMetrics(this.sipClientState.getCallId(), (String) null, SetupCallHelper.ResultType.UNEXPECTED, SetupCallHelper.MetadataBuilder.newBuilder().withCallType(CallType.AUDIO).withCallOrigin(Call.Side.Remote).withSource(SetupCallHelper.Source.SipCallPreparation).withReason("No incoming call"));
    }

    public void acceptVideoCall() {
        List<Call> calls = this.deviceCallingApi.getCalls(CallFilters.INCOMING_CALLS_ONLY);
        if (calls.size() > 0) {
            Call call = calls.get(0);
            call.getCallId();
            sLog.i(" Accepted Call ");
            calls.get(0).accept(Call.AcceptParams.builder().callReconnectInitiation(isCallReconnectInitiationSupported(call.getProvider())).build());
            return;
        }
        sLog.e(" No Incoming Calls ");
    }

    public void cancelOutgoingCall(String str) {
        if (str == null) {
            sLog.e("Unable to cancel outgoing call since call id is null");
            return;
        }
        Call callByCallId = this.deviceCallingApi.getCallByCallId(str);
        if (callByCallId == null) {
            GeneratedOutlineSupport.outline3("Cannot fina a call with the callId = ", str, sLog);
            return;
        }
        GeneratedOutlineSupport.outline4("Trying to Cancel Outgoing Call with callId = ", str, sLog);
        callByCallId.hangup(Call.HangupRequest.Everywhere);
    }

    public void configureRegistrar(RegistrarConfiguration registrarConfiguration) {
        this.deviceCallingApi.configureRegistrar(registrarConfiguration);
    }

    public RegistrarConfiguration.RegistrarConfigurationBuilder createBaseRegistrarConfigurationBuilder(String str, String str2, String str3, String str4, String str5, String str6) {
        boolean z = false;
        Preconditions.checkArgument(str != null, " Domain must not be null.");
        Preconditions.checkArgument(str2 != null, " Username must not be null.");
        Preconditions.checkArgument(str4 != null, " Realm must not be null.");
        Preconditions.checkArgument(str3 != null, " SIP instance Id must not be null.");
        String sIPProxyDomain = CommsDaggerWrapper.getComponent().getAppUrl().getSIPProxyDomain();
        if (sIPProxyDomain != null) {
            z = true;
        }
        Preconditions.checkArgument(z, "SIP Proxy Domain must not be null.");
        Matcher matcher = SIP_URL_PATTERN.matcher(sIPProxyDomain);
        if (matcher.matches()) {
            sIPProxyDomain = matcher.group(1);
        }
        ArcusConfig arcusConfig = CommsDaggerWrapper.getComponent().getArcusConfig();
        RegistrarConfiguration.RegistrarConfigurationBuilder proxyPort = RegistrarConfiguration.builder().scheme(Constants.SCHEME).expires(arcusConfig.getConfigInteger(RemoteConfigKeys.SIP_PROXY_REGISTRATION_TIMEOUT_SEC).intValue()).sipInstance(str3).domain(str).user(str2).realm(str4).proxyHost(sIPProxyDomain).proxyPort(arcusConfig.getConfigInteger(RemoteConfigKeys.SIP_PROXY_REGISTRATION_PORT_NUMBER).intValue());
        if (!TextUtils.isEmpty(str5) && !TextUtils.isEmpty(str6)) {
            HashMap hashMap = new HashMap();
            hashMap.put("X-Alexa-CallToken", str5);
            hashMap.put("X-Alexa-CallTokenVersion", str6);
            proxyPort.headers(hashMap);
        }
        return proxyPort;
    }

    public void enableAudio(boolean z) {
        if (this.sipClientState.getCurrentActiveCall() != null) {
            GeneratedOutlineSupport.outline5(" Toggling audio ", z, sLog);
            this.sipClientState.getCurrentActiveCall().setLocalAudioState(z);
        }
    }

    public void enableVideoStreamInVideoCall(boolean z) {
        CallUtils.enableVideoStreamInVideoCall(this.sipClientState, z);
    }

    public void endCurrentCall() {
        List<Call> calls = this.deviceCallingApi.getCalls(CallFilters.ACCEPTED_CALLS_ONLY);
        if (calls.size() > 0) {
            sLog.i(" End Call ");
            calls.get(0).hangup(Call.HangupRequest.Everywhere);
        }
    }

    @VisibleForTesting
    public AuthenticationInfo getAuthenticationInfo(@NonNull String str, @NonNull String str2) {
        return AuthenticationInfo.builder().realm(Constants.SIP_DOMAIN).user(Utils.getUsernameFromUri(str)).password("").authToken(str2).build();
    }

    @NonNull
    @VisibleForTesting
    public MediaRelayInfo getMediaRelayInfo(@NonNull MediaRelayInfoModel mediaRelayInfoModel) {
        sLog.i(" Retrieving TURN Configuration to build MediaRelayInfo ");
        TurnEndPointInfo build = TurnEndPointInfo.builder().url(mediaRelayInfoModel.getCalleeTURNUDP().getUrl()).username(mediaRelayInfoModel.getCalleeTURNUDP().getUsername()).credential(mediaRelayInfoModel.getCalleeTURNUDP().getCredential()).build();
        return MediaRelayInfo.builder().callee(build).caller(TurnEndPointInfo.builder().url(mediaRelayInfoModel.getCallerTURNUDP().getUrl()).username(mediaRelayInfoModel.getCallerTURNUDP().getUsername()).credential(mediaRelayInfoModel.getCallerTURNUDP().getCredential()).build()).build();
    }

    public void hangupAllCalls(Call.HangupRequest hangupRequest) {
        for (Call call : this.deviceCallingApi.getCalls(CallFilters.DEFAULT)) {
            call.hangup(hangupRequest);
        }
    }

    public boolean makeACall(@Nullable String str, String str2, ContactPhoneNumber contactPhoneNumber, String str3, String str4, @NonNull CallType callType, @NonNull String str5, MediaRelayInfoModel mediaRelayInfoModel, long j, @NonNull String str6, @Nullable String str7) {
        String str8;
        boolean isVideo = callType.isVideo();
        boolean isDropIn = callType.isDropIn();
        if ((!callType.isA2A() || str != null) && ((callType.isA2A() || contactPhoneNumber != null) && str2 != null && str3 != null && str4 != null && mediaRelayInfoModel != null)) {
            MediaRelayInfo mediaRelayInfo = getMediaRelayInfo(mediaRelayInfoModel);
            AuthenticationInfo authenticationInfo = getAuthenticationInfo(str4, mediaRelayInfoModel.getAuthToken());
            Preconditions.checkArgument(authenticationInfo != null, "Caller's Auth Info must not be null.");
            String str9 = str5;
            if ("".equalsIgnoreCase(str9)) {
                str9 = CallProvider.A2A;
            }
            CallHistoryHelper callHistoryHelper = CommsDaggerWrapper.getComponent().getCallHistoryHelper();
            callHistoryHelper.setCallType(str6, callType);
            callHistoryHelper.setCallProvider(str6, str9);
            callHistoryHelper.setCalleePhoneNumber(str6, contactPhoneNumber);
            HashMap hashMap = new HashMap();
            hashMap.put(SipHeaders.SIP_HEADER_CALL_PROVIDER, str9);
            boolean z = this.sipClientState.isEnhancedProcessedCall() && this.capabilitiesManager.isCallCaptioningEnabled();
            if (z) {
                str8 = "";
                hashMap.put(Constants.SIP_HEADER_ALEXA_VUA, "Enabled");
            } else {
                str8 = "";
            }
            GeneratedOutlineSupport.outline5("MPU enabled for the outgoing call : ", z, sLog);
            DeviceCallingService.OutgoingCallParams.OutgoingCallParamsBuilder headers = DeviceCallingService.OutgoingCallParams.builder().provider(str9).callId(str6).calleeCommsId(callType.isA2A() ? str : str8).calleeUri(str2).callerCommsId(str3).callerUri(str4).videoEnabled(isVideo).dropIn(isDropIn).authInfo(authenticationInfo).mediaRelayInfo(mediaRelayInfo).dataChannelParams(getDataChannelConfigurations()).headers(hashMap);
            if (!TextUtils.isEmpty(this.sipClientState.getSrtpKey())) {
                headers.srtpKeying(this.sipClientState.getSrtpKey());
            }
            if (this.realTimeTextEnablementAuthority.isFeatureFlagAndSettingEnabled() && str9.equalsIgnoreCase(CallProvider.A2A)) {
                headers.srtpKeying(Constants.DTLS).realTimeText(RealTimeText.SENDRECV);
            }
            if (!str9.equalsIgnoreCase(CallProvider.COBO)) {
                headers.srtpKeying(Constants.DTLS);
            }
            boolean isCallReconnectInitiationSupported = isCallReconnectInitiationSupported(str9);
            CommsLogger commsLogger = sLog;
            commsLogger.i("Call reconnection initiation enabled: " + isCallReconnectInitiationSupported);
            headers.callReconnectInitiation(isCallReconnectInitiationSupported);
            DeviceCallingService.OutgoingCallParams build = headers.build();
            if (this.deviceCallingApi.getCalls(CallFilters.DEFAULT).size() == 0) {
                CommsLogger commsLogger2 = sLog;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("Make A Call from ");
                outline1.append(sLog.sensitive(str4));
                outline1.append(" to ");
                outline1.append(sLog.sensitive(str2));
                outline1.append(" using mediaRelayInfo ");
                outline1.append(sLog.sensitive(mediaRelayInfo.toString()));
                commsLogger2.i(outline1.toString());
                if (this.callManager.enqueueActiveCall(str6)) {
                    EventTracer create = this.eventTracerFactory.create(str6, EventTracerFactory.Role.CALLER, getPivots(str9));
                    markEvents(create, isDropIn);
                    GeneratedOutlineSupport.outline4(" Going to make a call with ID ", str6, sLog);
                    this.deviceCallingApi.beginCall(build, create);
                    return true;
                }
                sLog.e("Not able to enqueue new call. Dropping the call.");
                SetupCallHelper.recordInitiationMetrics(str6, (String) null, SetupCallHelper.ResultType.UNKNOWN, SetupCallHelper.MetadataBuilder.newBuilder().withCallType(callType).withCallOrigin(Call.Side.Local).withSource(SetupCallHelper.Source.SipCallPreparation).withReason("Enqueue error"));
                return false;
            }
            sLog.i("You have an ongoing call");
            SetupCallHelper.recordInitiationMetrics(str6, (String) null, SetupCallHelper.ResultType.CANCELLED, SetupCallHelper.MetadataBuilder.newBuilder().withCallType(callType).withCallOrigin(Call.Side.Local).withSource(SetupCallHelper.Source.ActiveCommsCall).withReason("Existing call"));
            SetupCallHelper.recordInitiationMetrics((String) null, (String) null, 412, SetupCallHelper.MetadataBuilder.newBuilder().withCallType(callType).withCallOrigin(Call.Side.Local).withSource(SetupCallHelper.Source.ActiveCommsCall).withReason("Existing call"));
            return false;
        }
        sLog.e(" Caller/Callee commsID/SIP URI information not available to make a call");
        SetupCallHelper.recordInitiationMetrics((String) null, (String) null, SetupCallHelper.ResultType.UNEXPECTED, SetupCallHelper.MetadataBuilder.newBuilder().withCallType(callType).withCallOrigin(Call.Side.Local).withSource(SetupCallHelper.Source.SipCallPreparation).withReason("Invalid caller or callee commsId or SIP URI"));
        return false;
    }

    public void reconnectCall(@NonNull String str, @NonNull MediaRelayInfoModel mediaRelayInfoModel) {
        MediaRelayInfo mediaRelayInfo = getMediaRelayInfo(mediaRelayInfoModel);
        CommsLogger commsLogger = sLog;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Turn info callee: ");
        outline1.append(sLog.sensitive(mediaRelayInfo.getCallee().toString()));
        commsLogger.i(outline1.toString());
        CommsLogger commsLogger2 = sLog;
        StringBuilder outline12 = GeneratedOutlineSupport.outline1("Turn info caller: ");
        outline12.append(sLog.sensitive(mediaRelayInfo.getCaller().toString()));
        commsLogger2.i(outline12.toString());
        String authToken = mediaRelayInfoModel.getAuthToken();
        CommsLogger commsLogger3 = sLog;
        StringBuilder outline13 = GeneratedOutlineSupport.outline1("Auth token included in the media relay info: ");
        outline13.append(sLog.sensitive(authToken));
        commsLogger3.i(outline13.toString());
        AuthenticationInfo authenticationInfo = getAuthenticationInfo(str, authToken);
        if (authenticationInfo == null) {
            sLog.w("Unable to call reconnect api because Auth Info was null.");
            return;
        }
        sLog.i(" Calling reconnect api ");
        this.deviceCallingApi.reconnectCall(authenticationInfo, mediaRelayInfo);
    }

    public void rejectIncomingCall() {
        List<Call> calls = this.deviceCallingApi.getCalls(CallFilters.INCOMING_CALLS_ONLY);
        if (calls.size() > 0) {
            sLog.i(" Rejected Incoming Call ");
            calls.get(0).hangup(Call.HangupRequest.Everywhere);
            return;
        }
        sLog.e(" No Incoming Calls ");
    }
}
