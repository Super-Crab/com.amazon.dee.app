package com.amazon.deecomms.calling.controller;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.adobe.xmp.XMPConst;
import com.amazon.comms.calling.instrumentation.EventTracerConfig;
import com.amazon.comms.calling.service.DataChannelConfiguration;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.calling.service.RealTimeText;
import com.amazon.comms.calling.service.RtcpMuxPolicy;
import com.amazon.comms.calling.sipclient.AuthenticationInfo;
import com.amazon.comms.calling.sipclient.MediaRelayInfo;
import com.amazon.comms.calling.sipclient.TurnEndPointInfo;
import com.amazon.comms.instrumentation.EventTracer;
import com.amazon.comms.instrumentation.EventTracerFactory;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.models.sip.MediaStream;
import com.amazon.comms.ringservice.MetricsSession;
import com.amazon.deecomms.calling.enums.DataChannelLabel;
import com.amazon.deecomms.calling.enums.EnhancedProcessingState;
import com.amazon.deecomms.calling.model.BeginCallPayload;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CapabilitiesManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes12.dex */
public class InitiateCall {
    private static final String DEFAULT_PROVIDER = "A2A";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, InitiateCall.class);
    private final CallHistoryHelper callHistoryHelper;
    private final CallManager callManager;
    private final CapabilitiesManager capabilitiesManager;
    private final DeviceCallingService deviceCallingService;
    private final EventTracerFactory eventTracerFactory;
    private final SipClientState sipClientState;

    public InitiateCall(@NonNull DeviceCallingService deviceCallingService, @NonNull EventTracerFactory eventTracerFactory, @NonNull CapabilitiesManager capabilitiesManager, @NonNull CallHistoryHelper callHistoryHelper, @NonNull SipClientState sipClientState, @NonNull CallManager callManager) {
        this.eventTracerFactory = eventTracerFactory;
        this.capabilitiesManager = capabilitiesManager;
        this.deviceCallingService = deviceCallingService;
        this.callManager = callManager;
        this.callHistoryHelper = callHistoryHelper;
        this.sipClientState = sipClientState;
    }

    private List<DataChannelConfiguration> getDataChannelConfigurations(@NonNull BeginCallPayload.DisplayInfo displayInfo) {
        if (EnhancedProcessingState.ON == EnhancedProcessingState.valueOf(displayInfo.mediaSettingsInfo.enhancedProcessing)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(DataChannelConfiguration.builder().label(DataChannelLabel.IN_CALL_APPLICATION.getName()).ordered(true).build());
            return arrayList;
        }
        return null;
    }

    private List<EventTracerFactory.Pivot> getPivots(@NonNull String str, boolean z) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new EventTracerFactory.Pivot(MetricsSession.CALL_PROVIDER_PIVOT, str));
        arrayList.add(new EventTracerFactory.Pivot(MetricsSession.TRICKLE_ICE_PIVOT, z ? XMPConst.TRUESTR : XMPConst.FALSESTR));
        return arrayList;
    }

    private RtcpMuxPolicy getRtcpMuxPolicy(@NonNull BeginCallPayload.SipPayload sipPayload) {
        if (RtcpMuxPolicy.REQUIRE.toString().equals(sipPayload.offer.rtcpMuxPolicy)) {
            return RtcpMuxPolicy.REQUIRE;
        }
        return RtcpMuxPolicy.NEGOTIATE;
    }

    @VisibleForTesting
    public RealTimeText computeRTTStatus(@NonNull BeginCallPayload.SipPayload sipPayload) {
        List<BeginCallPayload.MediaStream> list = sipPayload.offer.mediaStreams;
        if (!list.isEmpty()) {
            for (BeginCallPayload.MediaStream mediaStream : list) {
                if (MediaStream.Type.TEXT.toString().equals(mediaStream.type) && MediaStream.Direction.SENDRECV.toString().equals(mediaStream.direction)) {
                    LOG.i("Text media stream is present and the direction is SENDRECV");
                    return RealTimeText.SENDRECV;
                }
            }
        }
        return RealTimeText.OFF;
    }

    @VisibleForTesting
    boolean computeVideoEnabled(@NonNull BeginCallPayload.SipPayload sipPayload) {
        boolean z = false;
        for (BeginCallPayload.MediaStream mediaStream : sipPayload.offer.mediaStreams) {
            if (mediaStream.type.equalsIgnoreCase("VIDEO")) {
                z = true;
            }
        }
        return z && !Utils.areAccessoriesConnected() && !Utils.isInDriveMode();
    }

    @VisibleForTesting
    boolean isReconnectEnabled(@NonNull String str) {
        return "A2A".equals(str) && this.capabilitiesManager.isCallReconnectv3Enabled();
    }

    public void makeACall(@NonNull BeginCallPayload beginCallPayload) {
        BeginCallPayload.SipCommand sipCommand;
        if (beginCallPayload != null && (sipCommand = beginCallPayload.sipCommand) != null) {
            BeginCallPayload.SipPayload sipPayload = sipCommand.payload;
            HashMap hashMap = new HashMap();
            List<Map<String, Object>> list = sipPayload.headers;
            if (list != null) {
                for (Map<String, Object> map : list) {
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        hashMap.put(entry.getKey(), entry.getValue().toString());
                    }
                }
            }
            BeginCallPayload.MediaRelayInfo mediaRelayInfo = beginCallPayload.sipCommand.payload.mediaRelayInfo;
            CommsLogger commsLogger = LOG;
            commsLogger.d(String.format("BeginCall for caller url: %s. caller username: %s, caller credential: %s, Callee url: %s, Callee username: %s, Callee credential: %s", commsLogger.sensitive(mediaRelayInfo.callerEndpoint.url), LOG.sensitive(mediaRelayInfo.callerEndpoint.username), LOG.sensitive(mediaRelayInfo.callerEndpoint.credential), LOG.sensitive(mediaRelayInfo.calleeEndpoint.url), LOG.sensitive(mediaRelayInfo.calleeEndpoint.username), LOG.sensitive(mediaRelayInfo.calleeEndpoint.credential)));
            MediaRelayInfo build = MediaRelayInfo.builder().caller(TurnEndPointInfo.builder().url(mediaRelayInfo.callerEndpoint.url).username(mediaRelayInfo.callerEndpoint.username).credential(mediaRelayInfo.callerEndpoint.credential).build()).callee(TurnEndPointInfo.builder().url(mediaRelayInfo.calleeEndpoint.url).username(mediaRelayInfo.calleeEndpoint.username).credential(mediaRelayInfo.calleeEndpoint.credential).build()).build();
            List<DataChannelConfiguration> dataChannelConfigurations = getDataChannelConfigurations(beginCallPayload.displayInfo);
            String str = sipPayload.callProvider;
            if (str == null) {
                str = "A2A";
            }
            boolean booleanValue = sipPayload.offer.trickleICE.booleanValue();
            EventTracer create = this.eventTracerFactory.create(beginCallPayload.sipCommand.payload.callId, EventTracerFactory.Role.CALLER, getPivots(str, booleanValue));
            String str2 = sipPayload.callId;
            this.callHistoryHelper.setCallType(str2, this.sipClientState.getCallType());
            this.callHistoryHelper.setCallProvider(str2, sipPayload.callProvider);
            DeviceCallingService.OutgoingCallParams build2 = DeviceCallingService.OutgoingCallParams.builder().provider(str).callId(sipPayload.callId).calleeName(sipPayload.callee.name).calleeCommsId(sipPayload.callee.id).calleeUri(sipPayload.callee.uri).callerName(sipPayload.caller.name).callerCommsId(sipPayload.caller.id).callerUri(sipPayload.caller.uri).authInfo(AuthenticationInfo.builder().realm(sipPayload.authenticationInfo.realm).user(sipPayload.authenticationInfo.user).password(sipPayload.authenticationInfo.password).authToken(sipPayload.authenticationInfo.authToken).build()).mediaRelayInfo(build).dropIn(sipPayload.isDropIn.booleanValue()).videoEnabled(computeVideoEnabled(sipPayload)).headers(hashMap).srtpKeying(sipPayload.mediaInfo.srtp.keying).realTimeText(computeRTTStatus(sipPayload)).dataChannelParams(dataChannelConfigurations).trickleIceEnabled(booleanValue).callReconnectInitiation(isReconnectEnabled(str)).rtcpMuxPolicy(getRtcpMuxPolicy(sipPayload)).build();
            if (this.callManager.enqueueActiveCall(sipPayload.callId)) {
                LOG.i(" Placing a call via Ringservice ");
                create.mark(EventTracerConfig.Event.Caller_BeginCall_Directive);
                if (sipPayload.isDropIn.booleanValue()) {
                    create.mark(EventTracerConfig.Event.Caller_DropIn_BeginCall_Directive);
                }
                this.deviceCallingService.beginCall(build2, create);
                return;
            }
            LOG.e("Cannot place a call. There is an existing call in progress");
            return;
        }
        LOG.e("Unable to start the call as begincall payload is null!!");
    }
}
