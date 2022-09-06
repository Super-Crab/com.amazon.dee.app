package com.amazon.comms.ringservice;

import android.os.Handler;
import com.amazon.comms.calling.instrumentation.EventTracerConfig;
import com.amazon.comms.calling.service.BundlePolicy;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.DataChannelConfiguration;
import com.amazon.comms.calling.service.DataChannelDTO;
import com.amazon.comms.calling.service.DataChannelEvent;
import com.amazon.comms.calling.service.DeviceCallingServiceParams;
import com.amazon.comms.calling.service.DropInController;
import com.amazon.comms.calling.service.DynamicAcousticParams;
import com.amazon.comms.calling.service.ErrorCode;
import com.amazon.comms.calling.service.HangupReason;
import com.amazon.comms.calling.service.MediaParams;
import com.amazon.comms.calling.service.MediaStateChangeTrigger;
import com.amazon.comms.calling.service.MediaStats;
import com.amazon.comms.calling.service.MediaStatus;
import com.amazon.comms.calling.service.Participant;
import com.amazon.comms.calling.service.RealTimeText;
import com.amazon.comms.calling.service.RtcpMuxPolicy;
import com.amazon.comms.calling.service.TimeoutRule;
import com.amazon.comms.calling.sipclient.CallDetails;
import com.amazon.comms.calling.sipclient.MediaRelayInfo;
import com.amazon.comms.calling.sipclient.SipHeaders;
import com.amazon.comms.calling.sipclient.SipStatusCode;
import com.amazon.comms.calling.sipclient.TurnEndPointInfo;
import com.amazon.comms.instrumentation.EventTracer;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.log.LogLevel;
import com.amazon.comms.ringservice.MediaSessionListener;
import com.amazon.comms.ringservice.Sdp;
import com.amazon.comms.ringservice.Signaling;
import com.amazon.comms.ringservice.pjsip.PjsipSignaling;
import com.amazon.comms.ringservice.util.SrtpCryptoType;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.comms.ringservice.webrtc.PeerConnectionClient;
import com.amazon.comms.ringservice.webrtc.WebRTCMediaManager;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.audio.SilenceSkippingAudioProcessor;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import javax.annotation.Nonnull;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.webrtc.EglBase;
import org.webrtc.VideoRenderer;
/* loaded from: classes12.dex */
public class CallImplInternal implements SignalingChannelListener, MediaSessionListener, DataChannelListener, RealTimeTextDataChannelListener {
    private static final int CALLEE_AUTO_CANCEL_INTERVAL_IN_SECONDS = 65;
    private static final String CALLEE_DROPIN_MIC_MUTED_METRIC_NAME = "CalleeDropInMicMuted";
    private static final int CALLER_AUTO_CANCEL_INTERVAL_IN_SECONDS = 60;
    private static final String CONNECTED_CALL_ATTRIBUTES_METRICS_SOURCE_NAME = "ConnectedCallAttributes";
    public static final String DTMFUPLMETRIC_SENDDTMFRFC2833 = "SendDTMFRFC2833";
    public static final String DTMFUPLMETRIC_SENDDTMFTOSIP = "SendDTMFToSIP";
    private static final int ICE_DISCONNECT_TIMEOUT_IN_SECONDS = 20;
    public static final boolean INBOUND = true;
    private static final String MEDIA_PJMEDIA = "pjmedia";
    public static final boolean OUTBOUND = false;
    public static final String REAL_TIME_TEXT_LABEL = "realtimetext";
    public static final String SENDDTMF_ERROR_CODE_CALL_NOT_IN_PROGRESS = "CALL_NOT_IN_PROGRESS";
    public static final String SENDDTMF_ERROR_CODE_DTMF_FAILED = "DTMF_FAILED";
    public static final String T140_PROTOCOL = "t140";
    private static final CommsLogger log = CommsLogger.getLogger(CallImplInternal.class);
    private Sdp answerSdp;
    private final Handler appCallbackHandler;
    private BundlePolicy bundlePolicy;
    private boolean callAccepted;
    private boolean callConfigured;
    private final CallDetails callDetails;
    private CallFinishedListener callFinishedListener;
    private final String callId;
    private CallImpl callImpl;
    private AmazonCallInfo callInfo;
    private Signaling.Channel channel;
    private MediaStatus currentMediaStatus;
    private List<DataChannelConfiguration> dataChannelParams;
    private final DeviceCallingServiceParams deviceCallingServiceParams;
    private DynamicAcousticParams dynamicAcousticParams;
    private final EventTracer eventTracer;
    private final Sdp initialCallCreationOfferSdp;
    private final boolean initialSystemCameraEnabledState;
    private final boolean initialSystemMediaStateState;
    private final boolean isDropInCall;
    private boolean isReconnecting;
    private boolean isReconnectingInitiator;
    private boolean isTrickleIceEnabled;
    private final MediaManager mediaManager;
    private MediaSession mediaSession;
    private final MetricsSession metricsSession;
    private final Handler orchestratorHandler;
    private final Queue<Runnable> pendingOperationsQueue;
    private RealTimeText realTimeText;
    private String remoteDeviceType;
    private String remoteSessionName;
    private RtcpMuxPolicy rtcpMuxPolicy;
    private final String srtpKeying;
    private Call.State state;
    private Map<Call.State, TimeoutRule> stateTransitionTimeouts;
    private boolean videoCapable;
    private boolean videoRequested;
    private final boolean waitForAcceptBeforeInitSDP;
    private boolean callReconnectInitiationSupported = false;
    private boolean isAttributesEmitted = false;
    private Call.State currentTimeoutStartState = null;
    private boolean autoCancelInitialized = false;
    private boolean autoDisconnectScheduled = false;
    private int networkDownCount = 0;
    private int networkUpCount = 0;
    private final String UNSUPPORTED_CODEC_ERROR = "Unsupported codec";
    private final String METRIC_LABEL_CALLIMPL = "CallImpl";
    private final String INITIATE_RECONNECT_FAILED = "InitiateReconnectFailed";
    private final String HANDLE_RECONNECT_FAILED = "HandleReconnectFailed";
    private final Runnable autoCancelRunnable = new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.1
        @Override // java.lang.Runnable
        public void run() {
            CommsLogger commsLogger = CallImplInternal.log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Canceling the call because it timed out: ");
            outline107.append(CallImplInternal.log.sensitiveCallId(CallImplInternal.this.callId));
            commsLogger.i(outline107.toString());
            if (CallImplInternal.this.getState() == Call.State.Complete) {
                return;
            }
            if (CallImplInternal.this.currentTimeoutStartState != Call.State.Created) {
                if (CallImplInternal.this.currentTimeoutStartState == Call.State.Ringing && CallImplInternal.this.getOrigin() == Call.Side.Local) {
                    CallImplInternal callImplInternal = CallImplInternal.this;
                    HangupReason hangupReason = HangupReason.OutboundRingingTimedOut;
                    callImplInternal.hangup(hangupReason, hangupReason);
                } else {
                    CallImplInternal callImplInternal2 = CallImplInternal.this;
                    HangupReason hangupReason2 = HangupReason.TimedOut;
                    callImplInternal2.hangup(hangupReason2, hangupReason2);
                }
            } else {
                CallImplInternal callImplInternal3 = CallImplInternal.this;
                HangupReason hangupReason3 = HangupReason.CallingTimedOut;
                callImplInternal3.hangup(hangupReason3, hangupReason3);
            }
            if (!CallImplInternal.this.isReconnecting) {
                return;
            }
            if (CallImplInternal.this.isReconnectingInitiator) {
                CallImplInternal.this.metricsSession.recordCount("CallImpl", "InitiateReconnectFailed", 1.0d, CallImplInternal.this.callId, Boolean.valueOf(CallImplInternal.this.isTrickleIceEnabled));
                CallImplInternal.this.eventTracer.mark(EventTracerConfig.Event.reconnect_initiator_timeout);
            } else {
                CallImplInternal.this.metricsSession.recordCount("CallImpl", "HandleReconnectFailed", 1.0d, CallImplInternal.this.callId, Boolean.valueOf(CallImplInternal.this.isTrickleIceEnabled));
                CallImplInternal.this.eventTracer.mark(EventTracerConfig.Event.reconnect_receiver_timeout);
            }
            CallImplInternal callImplInternal4 = CallImplInternal.this;
            callImplInternal4.postReconnectToAppListener(callImplInternal4.isReconnectingInitiator, false, "timeout");
        }
    };
    private final Runnable autoDisconnectRunnable = new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.2
        @Override // java.lang.Runnable
        public void run() {
            CommsLogger commsLogger = CallImplInternal.log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Hangup the call because ICE disconnected: ");
            outline107.append(CallImplInternal.log.sensitiveCallId(CallImplInternal.this.callId));
            commsLogger.i(outline107.toString());
            CallImplInternal callImplInternal = CallImplInternal.this;
            ErrorCode errorCode = ErrorCode.LostConnection;
            callImplInternal.onError(errorCode, errorCode.getValue(), "Ice Disconnected");
            CallImplInternal.this.autoDisconnectScheduled = false;
        }
    };

    protected CallImplInternal(@Nonnull MediaManager mediaManager, @Nonnull Handler handler, @Nonnull Handler handler2, @Nonnull Signaling.Channel channel, boolean z, boolean z2, String str, AmazonCallInfo amazonCallInfo, boolean z3, EventTracer eventTracer, MetricsSession metricsSession, String str2, @Nonnull CallFinishedListener callFinishedListener, boolean z4, Sdp sdp, boolean z5, boolean z6, DeviceCallingServiceParams deviceCallingServiceParams, Map<Call.State, TimeoutRule> map, BundlePolicy bundlePolicy, RtcpMuxPolicy rtcpMuxPolicy, DynamicAcousticParams dynamicAcousticParams, RealTimeText realTimeText, List<DataChannelConfiguration> list, boolean z7, boolean z8) {
        this.isTrickleIceEnabled = false;
        this.orchestratorHandler = handler;
        this.appCallbackHandler = handler2;
        this.mediaManager = mediaManager;
        this.channel = channel;
        this.videoCapable = z;
        this.videoRequested = z && z2;
        this.isDropInCall = z3;
        this.srtpKeying = Strings.isNullOrEmpty(str2) ? Constants.SDES : str2;
        this.callId = str;
        this.callInfo = amazonCallInfo;
        this.eventTracer = eventTracer;
        this.metricsSession = metricsSession;
        this.callFinishedListener = callFinishedListener;
        this.deviceCallingServiceParams = deviceCallingServiceParams;
        this.initialCallCreationOfferSdp = sdp;
        this.initialSystemMediaStateState = z5;
        this.initialSystemCameraEnabledState = z6;
        this.waitForAcceptBeforeInitSDP = z4;
        this.mediaSession = null;
        this.callConfigured = false;
        this.pendingOperationsQueue = new LinkedList();
        this.isReconnecting = false;
        this.isReconnectingInitiator = false;
        this.callDetails = new CallDetails();
        this.callDetails.setCallStartTime(DateTime.now(DateTimeZone.UTC));
        this.stateTransitionTimeouts = map;
        setState(Call.State.Created);
        this.bundlePolicy = bundlePolicy;
        this.rtcpMuxPolicy = rtcpMuxPolicy;
        this.dynamicAcousticParams = dynamicAcousticParams;
        this.realTimeText = realTimeText;
        this.dataChannelParams = list;
        this.isTrickleIceEnabled = z8;
        boolean z9 = this.videoCapable;
        boolean z10 = this.videoRequested;
        this.currentMediaStatus = new MediaStatus(true, z9, true, z7, true, z10, true, z7, true, z10, false, false, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void acceptCallInternal(Call.AcceptParams acceptParams) {
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AcceptCallInternal Call: ");
        outline107.append(log.sensitiveCallId(this.callId));
        outline107.append(" this= ");
        outline107.append(this);
        commsLogger.i(outline107.toString());
        if (isCallAccepted()) {
            log.i("acceptCallInternal, call already accepted, dropping this accept.");
            return;
        }
        this.eventTracer.mark(EventTracerConfig.Event.Callee_User_Answers);
        CommsLogger commsLogger2 = log;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("callReconnectInitiation= ");
        outline1072.append(acceptParams.isCallReconnectInitiation());
        commsLogger2.d(outline1072.toString());
        setCallReconnectInitiationSupported(acceptParams.isCallReconnectInitiation());
        if (this.answerSdp == null) {
            this.mediaSession.retryMedia();
        }
        boolean isVideoEnabled = acceptParams.isVideoEnabled();
        if (this.isDropInCall) {
            updateParticipantInfo(acceptParams);
            isVideoEnabled &= !DropInController.UserPreference.AudioOnly.equals(acceptParams.getDropInUserPreference());
            this.eventTracer.mark(EventTracerConfig.Event.Callee_DropIn_Accepted);
        }
        this.mediaSession.processAcceptParams(isVideoEnabled);
        answer();
    }

    private void addWorkToPendingQueueOrExecute(Runnable runnable) {
        synchronized (this) {
            if (!this.callConfigured) {
                this.pendingOperationsQueue.add(runnable);
            } else {
                postOrExecuteOnOrchestratorThread(runnable);
            }
        }
    }

    private void answer() {
        setCallAccepted(true);
        Sdp sdp = this.answerSdp;
        if (sdp != null) {
            answer(sdp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callHangupRequestedInternal(Call.HangupRequest hangupRequest) {
        HangupReason hangupReason;
        HangupReason hangupReason2;
        HangupReason hangupReason3;
        if (getState() == Call.State.Active) {
            hangupReason = HangupReason.LocalHangup;
            hangupReason2 = HangupReason.RemoteHangup;
        } else if (getOrigin() == Call.Side.Remote) {
            HangupReason hangupReason4 = HangupReason.Rejected;
            if (hangupRequest == Call.HangupRequest.ThisDevice) {
                hangupReason3 = HangupReason.Busy;
            } else {
                hangupReason3 = HangupReason.Rejected;
            }
            hangupReason2 = hangupReason3;
            hangupReason = hangupReason4;
        } else {
            hangupReason = HangupReason.Cancelled;
            hangupReason2 = hangupReason;
        }
        hangup(hangupReason, hangupReason2);
    }

    private void checkAndThrowErrorIfNotOnWorkerThread() {
        if (isOnOrchestratorThread()) {
            return;
        }
        throw new RuntimeException("Called on wrong thread! Must be called on orchestrator thread.");
    }

    private synchronized void checkIfConfiguredWithError() {
        if (!this.callConfigured) {
            throw new RuntimeException("this should not have been called without configuration finishing!");
        }
    }

    private void clearPendingOperationsQueueForHangup() {
        synchronized (this) {
            CommsLogger commsLogger = log;
            commsLogger.i("cleared pending operations= " + this.pendingOperationsQueue.size());
            this.pendingOperationsQueue.clear();
            this.callConfigured = true;
        }
    }

    private void clearPendingOpsAndPostExternalHangup(Runnable runnable) {
        clearPendingOperationsQueueForHangup();
        addWorkToPendingQueueOrExecute(runnable);
    }

    public static CallImplInternal createCallImplInternal(@Nonnull MediaManager mediaManager, @Nonnull Handler handler, @Nonnull Handler handler2, @Nonnull Signaling.Channel channel, boolean z, boolean z2, String str, AmazonCallInfo amazonCallInfo, boolean z3, EventTracer eventTracer, MetricsSession metricsSession, String str2, @Nonnull CallFinishedListener callFinishedListener, boolean z4, Sdp sdp, boolean z5, boolean z6, DeviceCallingServiceParams deviceCallingServiceParams, Map<Call.State, TimeoutRule> map, BundlePolicy bundlePolicy, RtcpMuxPolicy rtcpMuxPolicy, DynamicAcousticParams dynamicAcousticParams, RealTimeText realTimeText, List<DataChannelConfiguration> list, boolean z7, boolean z8) {
        CallImplInternal callImplInternal = new CallImplInternal(mediaManager, handler, handler2, channel, z, z2, str, amazonCallInfo, z3, eventTracer, metricsSession, str2, callFinishedListener, z4, sdp, z5, z6, deviceCallingServiceParams, map, bundlePolicy, rtcpMuxPolicy, dynamicAcousticParams, realTimeText, list, z7, z8);
        callImplInternal.channel.registerListener(callImplInternal);
        callImplInternal.remoteSessionName = sdp != null ? sdp.getSessionName() : "";
        return callImplInternal;
    }

    private PeerConnectionClient.PeerConnectionParameters createPeerConnectionParameters() {
        PeerConnectionClient.PeerConnectionParameters.PeerConnectionParametersBuilder createPeerConnectionParametersBuilder = WebRTCMediaManager.createPeerConnectionParametersBuilder(this.deviceCallingServiceParams);
        return createPeerConnectionParametersBuilder.videoCapable(this.videoCapable).videoRequestEnabled(this.videoRequested).waitForAcceptBeforeInitSDP(this.waitForAcceptBeforeInitSDP).initialSystemMediaEnabled(this.initialSystemMediaStateState).initialSystemCameraEnabled(this.initialSystemCameraEnabledState).srtpCryptoType(SrtpCryptoType.getSrtpCryptoType(this.srtpKeying)).bundlePolicy(this.bundlePolicy).rtcpMuxPolicy(this.rtcpMuxPolicy).dynamicAcousticParams(this.dynamicAcousticParams).dataChannelParams(updateDataChannelParams()).trickleIceEnabled(this.isTrickleIceEnabled).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized Call.Side getOrigin() {
        return this.callInfo.getOrigin();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hangup(HangupReason hangupReason, HangupReason hangupReason2) {
        if (getState() != Call.State.Complete) {
            this.channel.sendMessage(new PjsipSignaling.HangupMessage(hangupReason2));
            CommsLogger commsLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Notifying call listeners of local hangup: ");
            outline107.append(log.sensitiveCallId(this.callId));
            commsLogger.i(outline107.toString());
            onHangup(hangupReason);
        }
    }

    private void initializeAutoCanceler(int i) {
        if (!this.autoCancelInitialized) {
            this.orchestratorHandler.postDelayed(this.autoCancelRunnable, i);
            this.autoCancelInitialized = true;
        }
    }

    private void initializeAutoDisconnect() {
        this.autoDisconnectScheduled = this.orchestratorHandler.postDelayed(this.autoDisconnectRunnable, SilenceSkippingAudioProcessor.DEFAULT_PADDING_SILENCE_US);
    }

    private boolean isAutoDisconnectScheduled() {
        return this.autoDisconnectScheduled;
    }

    private boolean isCallAccepted() {
        return this.callAccepted;
    }

    private boolean isOnOrchestratorThread() {
        return Thread.currentThread().getId() == this.orchestratorHandler.getLooper().getThread().getId();
    }

    private boolean isRealTimeTextEnabled() {
        RealTimeText realTimeText = this.realTimeText;
        return realTimeText != null && realTimeText == RealTimeText.SENDRECV;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isValidDTO(DataChannelDTO dataChannelDTO) {
        if (dataChannelDTO != null) {
            String label = dataChannelDTO.getLabel();
            byte[] data = dataChannelDTO.getData();
            if (label == null || label.isEmpty()) {
                log.w("DataChannel label is null or empty");
                return false;
            } else if (data != null) {
                return true;
            } else {
                log.w("data is null");
                return false;
            }
        }
        log.w("Data object is null");
        return false;
    }

    private void notifyListenersOfCallEndOrError(Runnable runnable, CallImpl callImpl) {
        this.appCallbackHandler.post(runnable);
        CallFinishedListener callFinishedListener = this.callFinishedListener;
        if (callFinishedListener != null) {
            callFinishedListener.onCallFinished(callImpl);
        }
        this.callFinishedListener = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyOfCallRingingInternal() {
        log.i("notifyOfCallRingingInternal");
        initializeAutoCanceler(65000);
        setState(Call.State.Ringing);
        this.channel.sendMessage(new PjsipSignaling.RingingMessage());
        postNotifyOnRingingClbk();
    }

    private void onAccepted(Sdp sdp) {
        log.i("onAccepted");
        shutdownAutoCanceler();
        if (this.isTrickleIceEnabled) {
            this.mediaSession.startIceTrickling();
        }
        try {
            if (getOrigin() == Call.Side.Local && sdp != null) {
                this.remoteSessionName = sdp.getSessionName();
                this.mediaSession.processRemoteDescription(sdp, Sdp.Type.ANSWER);
            } else if (getOrigin() == Call.Side.Remote) {
                this.mediaSession.addRemoteIceCandidates();
            }
        } catch (IllegalArgumentException e) {
            CommsLogger commsLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Ignoring recoverable media session error: ");
            outline107.append(e.getMessage());
            commsLogger.i(outline107.toString());
        }
        MediaStatus mediaStatus = this.mediaSession.getMediaStatus();
        synchronized (this) {
            this.currentMediaStatus = mediaStatus;
        }
        setState(Call.State.Active);
        if (!this.isReconnecting) {
            this.callDetails.setCallConnectedTime(DateTime.now(DateTimeZone.UTC));
            getEventTracer().mark(EventTracerConfig.Event.Call_Accepted_Notify);
            CommsLogger commsLogger2 = log;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Notifying call listeners of acceptance: ");
            outline1072.append(log.sensitiveCallId(this.callId));
            commsLogger2.i(outline1072.toString());
        }
        final boolean z = this.isReconnecting;
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.5
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.onCallAcceptProcessingComplete(z);
            }
        });
        if (this.isReconnecting) {
            if (this.isReconnectingInitiator) {
                this.metricsSession.recordCount("CallImpl", "InitiateReconnectFailed", FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, this.callId, Boolean.valueOf(this.isTrickleIceEnabled));
            } else {
                this.metricsSession.recordCount("CallImpl", "HandleReconnectFailed", FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, this.callId, Boolean.valueOf(this.isTrickleIceEnabled));
            }
            postReconnectToAppListener(this.isReconnectingInitiator, true, "answered");
        }
        this.isReconnecting = false;
        this.remoteDeviceType = getIncomingHeaders().get(SipHeaders.SIP_HEADER_DEVICE_TYPE, null);
        this.mediaSession.postCallEstablished();
    }

    private boolean onCallerIdInfoMessage(String str, String str2, boolean z) {
        if (z) {
            log.w("Second incoming call is not handled");
            return true;
        }
        getCallInfo().getRemoteParticipant().setProviderSpecifiedId(str2);
        getCallInfo().getRemoteParticipant().setProviderSpecifiedName(str);
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.6
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.onRemoteParticipantUpdated();
            }
        });
        return true;
    }

    private void onEarlyMedia(Sdp sdp) {
        this.mediaSession.processRemoteDescription(sdp, Sdp.Type.PRANSWER);
        getEventTracer().mark(EventTracerConfig.Event.Call_Early_Media_Notify);
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Notifying call listeners of early media: ");
        outline107.append(log.sensitiveCallId(this.callId));
        commsLogger.i(outline107.toString());
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.7
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.notifyOnEarlyMedia();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onError(final ErrorCode errorCode, final int i, final String str) {
        shutdownAutoCanceler();
        shutdownAutoDisconnect();
        reportCallIceAndNetworkConnectionMetricOnHangup();
        setState(Call.State.Complete);
        clearPendingOperationsQueueForHangup();
        if (this.isReconnecting) {
            if (this.isReconnectingInitiator) {
                this.metricsSession.recordCount("CallImpl", "InitiateReconnectFailed", 1.0d, this.callId, Boolean.valueOf(this.isTrickleIceEnabled));
                this.eventTracer.mark(EventTracerConfig.Event.reconnect_initiator_fail);
            } else {
                this.metricsSession.recordCount("CallImpl", "HandleReconnectFailed", 1.0d, this.callId, Boolean.valueOf(this.isTrickleIceEnabled));
                this.eventTracer.mark(EventTracerConfig.Event.reconnect_receiver_fail);
            }
            postReconnectToAppListener(this.isReconnectingInitiator, false, str);
        }
        log.e(String.format(Locale.US, "Call Error: %s, code: %d, desc: %s", this.callId, Integer.valueOf(i), str));
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Hanging up the call: ");
        outline107.append(this.callId);
        commsLogger.i(outline107.toString());
        if (str.contains("Unsupported codec")) {
            this.channel.sendMessage(new PjsipSignaling.HangupMessage(HangupReason.UnsupportedCodec));
        } else {
            this.channel.sendMessage(new PjsipSignaling.HangupMessage(HangupReason.Unknown));
        }
        if (this.mediaSession != null) {
            GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport1.outline107("Stopping media session: "), this.callId, log);
            try {
                this.mediaSession.stopMedia();
            } catch (IllegalArgumentException e) {
                log.e("Exception while stopping media session", e);
            }
        }
        this.callDetails.setCallCompletedTime(DateTime.now(DateTimeZone.UTC));
        MediaSession mediaSession = this.mediaSession;
        if (mediaSession != null) {
            mediaSession.getThermalMitigationDetails().endMitigation(this.callDetails.getCallCompletedTime().getMillis());
            this.metricsSession.recordThermalMitigationStatus(this.mediaSession.getThermalMitigationDetails(), this.callDetails, this.callId);
        }
        this.metricsSession.recordCallCompletionStatus(this.callId, false, errorCode, errorCode.toString(), this.remoteDeviceType, Boolean.valueOf(this.isTrickleIceEnabled), getCallInfo().getProvider());
        GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport1.outline107("Notifying call listeners of error: "), this.callId, log);
        this.channel.unregisterListener();
        notifyListenersOfCallEndOrError(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.13
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.onErrorProcessingComplete(errorCode, i, str);
            }
        }, getCallForSession());
    }

    private void onHangup(final HangupReason hangupReason) {
        shutdownAutoCanceler();
        shutdownAutoDisconnect();
        reportCallIceAndNetworkConnectionMetricOnHangup();
        setState(Call.State.Complete);
        clearPendingOperationsQueueForHangup();
        if (this.mediaSession != null) {
            GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport1.outline107("Stopping media session: "), this.callId, log);
            try {
                this.mediaSession.stopMedia();
            } catch (IllegalArgumentException e) {
                log.e("Exception while stopping media session", e);
            }
        }
        this.callDetails.setCallCompletedTime(DateTime.now(DateTimeZone.UTC));
        this.eventTracer.mark(EventTracerConfig.Event.Call_Hangup);
        MediaSession mediaSession = this.mediaSession;
        if (mediaSession != null) {
            mediaSession.getThermalMitigationDetails().endMitigation(this.callDetails.getCallCompletedTime().getMillis());
            this.metricsSession.recordThermalMitigationStatus(this.mediaSession.getThermalMitigationDetails(), this.callDetails, this.callId);
        }
        this.metricsSession.recordCallCompletionStatus(this.callId, true, null, hangupReason.toString(), this.remoteDeviceType, Boolean.valueOf(this.isTrickleIceEnabled), getCallInfo().getProvider());
        log.i(String.format(Locale.US, "Notifying call listeners of hangup: %s, reason: %s", this.callId, hangupReason));
        this.channel.unregisterListener();
        notifyListenersOfCallEndOrError(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.12
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.hangupProcessingComplete(hangupReason);
            }
        }, getCallForSession());
    }

    private void onRinging() {
        setState(Call.State.Ringing);
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Notifying call listeners of ringing: ");
        outline107.append(log.sensitiveCallId(this.callId));
        commsLogger.i(outline107.toString());
        postNotifyOnRingingClbk();
    }

    private void onSessionRefresh() {
        this.mediaSession.refreshSession();
    }

    private void onSignalingDTMFResponse(final int i, final String str) {
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.9
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.onSignalingDTMFResponse(i, str);
            }
        });
    }

    private void postMediaDTMFResponseCallback(final boolean z, final String str) {
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.10
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.onMediaDTMFResponse(z, str);
            }
        });
    }

    private void postNotifyOnRingingClbk() {
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.8
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.notifyOnRinging();
            }
        });
    }

    private void postOrExecuteOnOrchestratorThread(Runnable runnable) {
        if (isOnOrchestratorThread()) {
            runnable.run();
        } else if (getState() == Call.State.Complete) {
            CommsLogger commsLogger = log;
            commsLogger.i("Cannot post runnable for call operation on worker thread after call is marked completed! Dropping " + runnable);
        } else {
            this.orchestratorHandler.post(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postReconnectToAppListener(final boolean z, final boolean z2, final String str) {
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.3
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.notifyReconnectToAppListener(z, z2, str);
            }
        });
    }

    private void postSendDtmfTonesCallback(final boolean z, final String str, final int i, final int i2) {
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.11
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.postSendDtmfTones(z, str, i, i2);
            }
        });
    }

    private void reconnectInternal(Signaling.Channel channel, Sdp sdp, MediaRelayInfo mediaRelayInfo) {
        TurnEndPointInfo callee;
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("reconnectInternal: isReconnecting= ");
        outline107.append(this.isReconnecting);
        outline107.append(" isReconnectingInitiator= ");
        outline107.append(this.isReconnectingInitiator);
        outline107.append(" sdpOffer= ");
        boolean z = false;
        GeneratedOutlineSupport1.outline184(outline107, sdp != null, commsLogger);
        if (!this.isReconnecting) {
            this.isReconnecting = true;
            if (this.channel != channel) {
                log.d("un-registering from old channel");
                this.channel.unregisterListener();
                z = true;
            }
            this.channel = channel;
            this.channel.registerListener(this);
            if (this.isTrickleIceEnabled) {
                this.mediaSession.stopIceTrickling();
            }
            if (mediaRelayInfo != null) {
                if (sdp == null) {
                    callee = mediaRelayInfo.getCaller();
                } else {
                    callee = mediaRelayInfo.getCallee();
                }
                if (callee != null && !Strings.isNullOrEmpty(callee.getUrl())) {
                    this.mediaSession.clearIceServers();
                    this.mediaSession.addMediaRelayInfo(callee.getUrl(), callee.getUsername(), callee.getCredential());
                } else {
                    log.i("turn endpoint provided for reconnect ice restart is empty");
                }
            }
            this.mediaSession.restartIce(sdp, z);
        }
    }

    private void reportCallIceAndNetworkConnectionMetricOnHangup() {
        if (this.metricsSession == null) {
            log.d("cannot report reportCallIceAndNetworkConnectionMetricOnHangup, no metrics session");
        }
        Call.State state = getState();
        if (state == Call.State.Complete) {
            log.w("reportCallIceAndNetworkConnectionMetricOnHangup must be called before marking call complete");
            return;
        }
        String provider = getProvider();
        if (state != Call.State.Created && state != Call.State.Ringing) {
            this.metricsSession.recordCount("CallImpl", "ActiveCallNetIfaceUpEvents", this.networkUpCount, this.callId, provider);
            this.metricsSession.recordCount("CallImpl", "ActiveCallNetIfaceDownEvents", this.networkDownCount, this.callId, provider);
            boolean z = this.networkDownCount > 0 || this.networkUpCount > 0;
            this.metricsSession.recordCount("CallImpl", "ActiveCallEndWithAtLeastANetInterfaceEvent", z ? 1.0d : 0.0d, this.callId, provider);
            MediaSession mediaSession = this.mediaSession;
            if (mediaSession == null) {
                return;
            }
            boolean isMediaConnectionActive = mediaSession.isMediaConnectionActive();
            boolean didMediaConnectionEverEstablish = this.mediaSession.didMediaConnectionEverEstablish();
            this.metricsSession.recordCount("CallImpl", "ActiveCallEndWithIceDisconnected", (isMediaConnectionActive || !didMediaConnectionEverEstablish) ? 0.0d : 1.0d, this.callId, provider);
            this.metricsSession.recordCount("CallImpl", "ActiveCallEndWithANetIfaceEventAndIceDisconnected", (isMediaConnectionActive || !didMediaConnectionEverEstablish || !z) ? 0.0d : 1.0d, this.callId, provider);
            this.metricsSession.recordCount("CallImpl", "HangupWithANetIfaceEventAndNoIceConnectionEver", (didMediaConnectionEverEstablish || !z) ? 0.0d : 1.0d, this.callId, provider);
            return;
        }
        this.metricsSession.recordCount("CallImpl", "InactiveCallNetIfaceUpEvents", this.networkUpCount, this.callId, provider);
        this.metricsSession.recordCount("CallImpl", "InactiveCallNetIfaceDownEvents", this.networkDownCount, this.callId, provider);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendDtmfTonesInternal(String str, int i, int i2) {
        if (getState() != Call.State.Active) {
            postMediaDTMFResponseCallback(false, SENDDTMF_ERROR_CODE_CALL_NOT_IN_PROGRESS);
        } else if (this.mediaSession.isDtmfInsertable()) {
            this.mediaSession.insertDtmf(str, i, i2);
        } else {
            boolean sendMessage = this.channel.sendMessage(new PjsipSignaling.DtmfMessage(str, i));
            this.eventTracer.mark(EventTracerConfig.Event.DTMF_Sent_To_Sip, DTMFUPLMETRIC_SENDDTMFTOSIP);
            postSendDtmfTonesCallback(sendMessage, str, i, i2);
        }
    }

    private void setCallAccepted(boolean z) {
        this.callAccepted = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLocalMediaCapabilityInternal(boolean z, boolean z2) {
        if (this.mediaSession == null) {
            this.videoCapable = z2;
            return;
        }
        throw new RuntimeException("Capability cannot be changed once media has been created.");
    }

    private void setState(Call.State state) {
        Map<Call.State, TimeoutRule> map = this.stateTransitionTimeouts;
        if (map != null && !map.isEmpty()) {
            Call.State state2 = this.currentTimeoutStartState;
            if (state2 != null && this.stateTransitionTimeouts.get(state2) != null && this.stateTransitionTimeouts.get(this.currentTimeoutStartState).getTimeoutEndStates().contains(state)) {
                shutdownAutoCanceler();
                this.currentTimeoutStartState = null;
            }
            TimeoutRule timeoutRule = this.stateTransitionTimeouts.get(state);
            if (timeoutRule != null && !state.equals(this.currentTimeoutStartState)) {
                if (this.autoCancelInitialized) {
                    shutdownAutoCanceler();
                }
                this.currentTimeoutStartState = state;
                initializeAutoCanceler(timeoutRule.getTimeoutInMilliseconds());
            }
        }
        if (state != getState() && (state == Call.State.Active || getState() == Call.State.Active)) {
            this.networkUpCount = 0;
            this.networkDownCount = 0;
        }
        synchronized (this) {
            this.state = state;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVideoEffectInternal(Call.VideoEffect videoEffect, double d) {
        this.mediaSession.setVideoEffect(videoEffect, d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVolumeInternal(float f) {
        if (this.mediaSession == null) {
            log.d("can't adjust call volume, media session null");
            return;
        }
        CommsLogger commsLogger = log;
        commsLogger.i("setVolume= " + f);
        this.mediaSession.setVolume(f);
    }

    private void shutdownAutoCanceler() {
        this.orchestratorHandler.removeCallbacks(this.autoCancelRunnable);
        this.autoCancelInitialized = false;
    }

    private void shutdownAutoDisconnect() {
        this.orchestratorHandler.removeCallbacks(this.autoDisconnectRunnable);
        this.autoDisconnectScheduled = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startMediaSessionInternal(DeviceCallingServiceParams deviceCallingServiceParams, VideoRenderer.Callbacks callbacks, VideoRenderer.Callbacks callbacks2) {
        log.i("startMediaSessionInternal");
        checkAndThrowErrorIfNotOnWorkerThread();
        if (this.mediaSession == null && getState() != Call.State.Complete) {
            checkIfConfiguredWithError();
            if (log.isLoggable(LogLevel.Debug)) {
                CommsLogger commsLogger = log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SDP OFFER = \n");
                outline107.append(this.initialCallCreationOfferSdp);
                commsLogger.ds(outline107.toString());
            }
            Sdp sdp = this.initialCallCreationOfferSdp;
            if (sdp != null) {
                this.videoRequested = this.videoRequested && sdp.isMediaPresent("video");
            }
            this.mediaSession = this.mediaManager.getMediaSession(this.callId, getOrigin() != Call.Side.Local, this, this.eventTracer, createPeerConnectionParameters(), this, this, isRealTimeTextEnabled(), getProvider());
            onCameraInUseUpdate(this.mediaSession.isUsingFrontFacingCamera());
            MediaRelayInfo mediaRelayInfo = getCallInfo().getMediaRelayInfo();
            if (mediaRelayInfo != null) {
                TurnEndPointInfo caller = getOrigin() == Call.Side.Local ? mediaRelayInfo.getCaller() : mediaRelayInfo.getCallee();
                if (caller != null) {
                    this.mediaSession.addMediaRelayInfo(caller.getUrl(), caller.getUsername(), caller.getCredential());
                }
            }
            this.mediaSession.startMedia(this.initialCallCreationOfferSdp, callbacks, callbacks2);
            final EglBase.Context eglContext = this.mediaManager.getEglContext();
            this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.4
                @Override // java.lang.Runnable
                public void run() {
                    CallImplInternal.this.callImpl.initializeRenderersIfNeeded(eglContext);
                }
            });
            return;
        }
        log.w("Media already started or call already finished. can't start again");
    }

    private List<DataChannelConfiguration> updateDataChannelParams() {
        if (this.dataChannelParams == null) {
            this.dataChannelParams = new ArrayList();
        }
        if (this.deviceCallingServiceParams.isRealTimeTextCapable() && isRealTimeTextEnabled()) {
            log.i("Creating the data channel configuration for the RealTimeText");
            this.dataChannelParams.add(DataChannelConfiguration.builder().label(REAL_TIME_TEXT_LABEL).ordered(true).protocol("t140").build());
        }
        return this.dataChannelParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateStateTransitionTimeouts(Map<Call.State, TimeoutRule> map) {
        this.stateTransitionTimeouts = map;
    }

    public void acceptCall(final Call.AcceptParams acceptParams) {
        CommsLogger commsLogger = log;
        commsLogger.i("acceptCall. this= " + this);
        addWorkToPendingQueueOrExecute(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.25
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.acceptCallInternal(acceptParams);
            }
        });
    }

    public void callConfigurationFinished(final VideoRenderer.Callbacks callbacks, final VideoRenderer.Callbacks callbacks2) {
        log.i("callConfigurationFinished.");
        if (getCallForSession() != null) {
            Runnable runnable = new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.24
                @Override // java.lang.Runnable
                public void run() {
                    CallImplInternal callImplInternal = CallImplInternal.this;
                    callImplInternal.startMediaSessionInternal(callImplInternal.deviceCallingServiceParams, callbacks, callbacks2);
                }
            };
            synchronized (this) {
                this.callConfigured = true;
                this.orchestratorHandler.post(runnable);
                CommsLogger commsLogger = log;
                commsLogger.i("callConfigurationFinished: number of queued ops to be posted= " + this.pendingOperationsQueue.size());
                while (!this.pendingOperationsQueue.isEmpty()) {
                    this.orchestratorHandler.post(this.pendingOperationsQueue.remove());
                }
            }
            return;
        }
        throw new RuntimeException("CallImpl was never attached!");
    }

    public synchronized boolean callReconnectInitiationSupported() {
        if (!this.srtpKeying.equals(SrtpCryptoType.DTLS.toString()) || !this.remoteSessionName.equals(MEDIA_PJMEDIA)) {
            return this.callReconnectInitiationSupported;
        }
        return false;
    }

    public synchronized CallDetails getCallDetails() {
        return this.callDetails;
    }

    public synchronized CallImpl getCallForSession() {
        return this.callImpl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized String getCallId() {
        return this.callId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized AmazonCallInfo getCallInfo() {
        return this.callInfo;
    }

    public synchronized Call.Side getCallOrigin() {
        return this.callInfo.getCallOrigin();
    }

    public Signaling.Channel getChannel() {
        return this.channel;
    }

    public synchronized EventTracer getEventTracer() {
        return this.eventTracer;
    }

    public synchronized SipHeaders getIncomingHeaders() {
        return this.callInfo.getIncomingHeaders();
    }

    public synchronized Participant getLocalParticipant() {
        return this.callInfo.getLocalParticipant();
    }

    public synchronized MediaStatus getMediaStatus() {
        return this.currentMediaStatus;
    }

    public synchronized SipHeaders getOutgoingHeaders() {
        return this.callInfo.getOutgoingHeaders();
    }

    public synchronized String getProvider() {
        if (this.callInfo == null) {
            return null;
        }
        return this.callInfo.getProvider();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized String getRemoteDeviceType() {
        return this.remoteDeviceType;
    }

    public synchronized Participant getRemoteParticipant() {
        return this.callInfo.getRemoteParticipant();
    }

    public synchronized Call.State getState() {
        return this.state;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleReconnect(Signaling.Channel channel, Sdp sdp, AmazonCallInfo amazonCallInfo) {
        this.channel.sendMessage(new PjsipSignaling.HangupMessage(HangupReason.LocalHangup, false));
        this.eventTracer.mark(EventTracerConfig.Event.reconnect_receiver_signaling_hangup);
        this.isReconnectingInitiator = false;
        getCallInfo().setOrigin(Call.Side.Remote);
        getCallInfo().setRemoteGruu(amazonCallInfo.getRemoteGruu());
        reconnectInternal(channel, sdp, amazonCallInfo.getMediaRelayInfo());
    }

    public void hangupThisCall(final Call.HangupRequest hangupRequest) {
        clearPendingOpsAndPostExternalHangup(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.39
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callHangupRequestedInternal(hangupRequest);
            }
        });
    }

    public boolean isCallReconnectInitiationSupported() {
        return this.callReconnectInitiationSupported;
    }

    public boolean isTrickleIceEnabled() {
        return this.isTrickleIceEnabled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void networkInterfaceConnectivityUpdate(boolean z) {
        if (z) {
            this.networkUpCount++;
            return;
        }
        if (this.isReconnecting) {
            this.metricsSession.recordCount("CallImpl", "NetworkDownDuringReconnect", 1.0d, this.callId);
        }
        this.networkDownCount++;
    }

    public void notifyOfCallRinging() {
        addWorkToPendingQueueOrExecute(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.29
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.notifyOfCallRingingInternal();
            }
        });
    }

    @Override // com.amazon.comms.ringservice.DataChannelListener
    public void onBufferedAmountChange(final DataChannelEvent dataChannelEvent) {
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.43
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.onBufferedAmountChange(dataChannelEvent);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.MediaSessionListener
    public void onCameraInUseUpdate(final boolean z) {
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.17
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.setMirroringOnSelfView(z);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.MediaSessionListener
    public void onCameraOpening(final String str) {
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.16
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.onCameraOpening(str);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.MediaSessionListener
    public void onCameraSwitchDone(final boolean z) {
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.21
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.onCameraSwitchDone(z);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.MediaSessionListener
    public void onCameraSwitchError(final String str) {
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.22
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.onCameraSwitchError(str);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.MediaSessionListener
    public void onDtmfInserted(final boolean z, final String str, final int i, final int i2) {
        this.eventTracer.mark(EventTracerConfig.Event.DTMF_Sent_RFC2833, DTMFUPLMETRIC_SENDDTMFRFC2833);
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.20
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.onDtmfInserted(z, str, i, i2);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.MediaSessionListener
    public void onFirstSdpAvailable(MediaSessionListener.SdpParams sdpParams) {
        if (getState() == Call.State.Complete) {
            log.i("OnFirstSdpAvailable but call state already complete");
        } else if (sdpParams.getType() == Sdp.Type.OFFER) {
            if (this.isReconnecting && this.channel.getState() == Signaling.Channel.State.Active) {
                log.i("onFirstSdpAvailable: sending sdp as update in reconnect state");
                onSdpUpdate(sdpParams);
                this.isReconnecting = false;
                if (!this.isTrickleIceEnabled) {
                    return;
                }
                this.mediaSession.startIceTrickling();
                return;
            }
            log.i("onFirstSdpAvailable: sending sdp as new call");
            initializeAutoCanceler(60000);
            if (!this.channel.sendMessage(new PjsipSignaling.CallMessage(getCallInfo(), sdpParams.getSdp()))) {
                ErrorCode errorCode = ErrorCode.SIPInternalError;
                onError(errorCode, errorCode.getValue(), "Error sending sdp offer");
            }
            if (!this.isReconnecting) {
                return;
            }
            this.eventTracer.mark(EventTracerConfig.Event.reconnect_initiator_signaling_offer);
        } else if (getState() == Call.State.Active && !this.isReconnecting) {
            CommsLogger commsLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onFirstSdpAvailable: not doing anything for: sdp type= ");
            outline107.append(Sdp.Type.OFFER);
            outline107.append(" state= ");
            outline107.append(getState());
            outline107.append(" isReconnecting= ");
            outline107.append(this.isReconnecting);
            commsLogger.w(outline107.toString());
        } else {
            CommsLogger commsLogger2 = log;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("onFirstSdpAvailable: calling answer. state= ");
            outline1072.append(getState());
            outline1072.append(" isReconnecting= ");
            outline1072.append(this.isReconnecting);
            commsLogger2.i(outline1072.toString());
            answer(sdpParams.getSdp());
        }
    }

    @Override // com.amazon.comms.ringservice.MediaSessionListener
    public void onIceCandidatesGenerated(Sdp sdp) {
        log.i("onIceCandidateGenerated");
        if (getState() == Call.State.Complete) {
            log.i("onIceCandidateGenerated: call state is already complete");
            return;
        }
        if (this.channel.sendMessage(new PjsipSignaling.IceCandidateMessage(sdp))) {
            return;
        }
        this.metricsSession.recordCount("CallImpl", "ICECandidateInfoError", 1.0d, this.callId, Boolean.valueOf(this.isTrickleIceEnabled));
        ErrorCode errorCode = ErrorCode.SIPInternalError;
        onError(errorCode, errorCode.getValue(), "onIceCandidatesGenerated, error sending ice candidate");
    }

    @Override // com.amazon.comms.ringservice.MediaSessionListener
    public void onIceConnected() {
        shutdownAutoDisconnect();
        if (!this.isAttributesEmitted) {
            if (this.isDropInCall && getOrigin() == Call.Side.Remote) {
                this.metricsSession.recordCount(CONNECTED_CALL_ATTRIBUTES_METRICS_SOURCE_NAME, CALLEE_DROPIN_MIC_MUTED_METRIC_NAME, this.mediaSession.getMediaStatus().isLocalAudioEnabled() ? FrostVideoEffectController.VIDEO_STRENGTH_CLEAR : 1.0d, this.callId);
            }
            boolean isLocalVideoCapable = this.mediaSession.getMediaStatus().isLocalVideoCapable();
            boolean isRemoteVideoCapable = this.mediaSession.getMediaStatus().isRemoteVideoCapable();
            if (this.deviceCallingServiceParams.getAvsDeviceFacade().isExternalCameraSupported()) {
                isLocalVideoCapable = this.deviceCallingServiceParams.getAvsDeviceFacade().isExternalCameraPresent();
            }
            MetricsSession metricsSession = this.metricsSession;
            boolean z = false;
            boolean z2 = getOrigin() == Call.Side.Local;
            if (isLocalVideoCapable && isRemoteVideoCapable) {
                z = true;
            }
            metricsSession.recordConnectedCallAttributes(z2, z, getCallInfo().getProvider(), this.callId);
            this.isAttributesEmitted = true;
        }
    }

    @Override // com.amazon.comms.ringservice.MediaSessionListener
    public void onIceDisconnected() {
        if (getState() == Call.State.Complete) {
            return;
        }
        initializeAutoDisconnect();
    }

    @Override // com.amazon.comms.ringservice.MediaSessionListener
    public void onIceFailed() {
        if (getState() == Call.State.Complete) {
            return;
        }
        if (isAutoDisconnectScheduled()) {
            log.i("iceFailed. there is a pending auto disconnect which will hang up the call");
        } else {
            onError(ErrorCode.MediaConnectionFailed, "ICE failed");
        }
    }

    @Override // com.amazon.comms.ringservice.MediaSessionListener
    public void onMediaStatsUpdated(final MediaStats mediaStats) {
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.18
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.updateMediaStatsData(mediaStats);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.MediaSessionListener
    public void onMediaStatusUpdated(final Call.Side side, final MediaStatus mediaStatus, final MediaStateChangeTrigger mediaStateChangeTrigger) {
        CommsLogger commsLogger = log;
        commsLogger.i("Notifying Media Update: " + mediaStatus + " on side " + side + ", trigger=" + mediaStateChangeTrigger.name());
        synchronized (this) {
            this.currentMediaStatus = mediaStatus;
        }
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.14
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.notifyMediaStatusUpdated(side, mediaStatus, mediaStateChangeTrigger);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.SignalingChannelListener
    public void onMessageError(Signaling.MessageError messageError, Signaling.MessageErrorInfo messageErrorInfo) {
        PjsipSignaling.MessageErrorInfo messageErrorInfo2 = (PjsipSignaling.MessageErrorInfo) messageErrorInfo;
        if (messageErrorInfo2.getInternalErrorCode() == SipStatusCode.REQUEST_TIMEOUT.getCode()) {
            HangupReason hangupReason = HangupReason.TimedOut;
            hangup(hangupReason, hangupReason);
            return;
        }
        onError(messageErrorInfo2.getErrorCode(), messageErrorInfo2.getInternalErrorCode(), messageErrorInfo2.getErrorDescription());
    }

    @Override // com.amazon.comms.ringservice.DataChannelListener
    public void onMessageReceived(final DataChannelEvent dataChannelEvent) {
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.42
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.onMessageReceived(dataChannelEvent);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.RealTimeTextDataChannelListener
    public void onRealTimeTextReceivedOnDataChannel(final byte[] bArr) {
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.44
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.log.d("RealTimeText received on Data Channel");
                CallImplInternal.this.callImpl.notifyRealTimeTextMessageReceived(bArr);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.SignalingChannelListener
    public void onRecvMessage(Signaling.Message message) {
        if (message instanceof PjsipSignaling.HangupMessage) {
            onHangup(((PjsipSignaling.HangupMessage) message).getReason());
        } else if (message instanceof PjsipSignaling.AnswerMessage) {
            if (this.isReconnecting) {
                this.eventTracer.mark(EventTracerConfig.Event.reconnect_initiator_signaling_answered);
            }
            PjsipSignaling.AnswerMessage answerMessage = (PjsipSignaling.AnswerMessage) message;
            getCallInfo().updateOnAnswer(answerMessage.getHeaders());
            setCallAccepted(true);
            onAccepted(answerMessage.getSdp());
        } else if (message instanceof PjsipSignaling.EarlyMediaMessage) {
            onEarlyMedia(((PjsipSignaling.EarlyMediaMessage) message).getSdp());
        } else if (message instanceof PjsipSignaling.RingingMessage) {
            onRinging();
        } else if (message instanceof PjsipSignaling.SdpUpdateMessage) {
            PjsipSignaling.SdpUpdateMessage sdpUpdateMessage = (PjsipSignaling.SdpUpdateMessage) message;
            if (sdpUpdateMessage.getSdp() != null) {
                if (this.mediaSession.processRemoteDescription(sdpUpdateMessage.getSdp(), sdpUpdateMessage.getType())) {
                    return;
                }
                log.i("SDP Update Failed");
                onSdpUpdate(new MediaSessionListener.SdpParams(new Sdp(""), Sdp.Type.ANSWER));
                return;
            }
            log.i("Reset Remote Signaling");
            if (this.isReconnecting) {
                return;
            }
            this.mediaSession.resetRemoteDescription(true, sdpUpdateMessage.isRemoteUpdateInProgress());
        } else if (message instanceof PjsipSignaling.CallerIdInfoMessage) {
            PjsipSignaling.CallerIdInfoMessage callerIdInfoMessage = (PjsipSignaling.CallerIdInfoMessage) message;
            onCallerIdInfoMessage(callerIdInfoMessage.getName(), callerIdInfoMessage.getCallerId(), callerIdInfoMessage.isSecondCall());
        } else if (message instanceof PjsipSignaling.DtmfResponseMessage) {
            PjsipSignaling.DtmfResponseMessage dtmfResponseMessage = (PjsipSignaling.DtmfResponseMessage) message;
            onSignalingDTMFResponse(dtmfResponseMessage.getStatusCode(), dtmfResponseMessage.getReason());
        } else if (message instanceof PjsipSignaling.SessionRefreshMessage) {
            onSessionRefresh();
        } else if (message instanceof PjsipSignaling.IceCandidateMessage) {
            this.mediaSession.addRemoteIceCandidates(((PjsipSignaling.IceCandidateMessage) message).getPseudoSdp());
        } else if (!(message instanceof PjsipSignaling.IceCandidateResponseMessage) || ((PjsipSignaling.IceCandidateResponseMessage) message).getStatusCode() == SipStatusCode.OK.getCode()) {
        } else {
            this.metricsSession.recordCount("CallImpl", "ICECandidateInfoError", 1.0d, this.callId, Boolean.valueOf(this.isTrickleIceEnabled));
        }
    }

    @Override // com.amazon.comms.ringservice.MediaSessionListener
    public void onSdpUpdate(MediaSessionListener.SdpParams sdpParams) {
        if (this.isReconnecting && !this.isReconnectingInitiator) {
            log.w("onSdpUpdate: handling reconnect, ignore SDP update");
        } else if (this.channel.getState() == Signaling.Channel.State.Closed && callReconnectInitiationSupported()) {
            log.w("onSdpUpdate: signaling channel closed, ignore SDP update");
        } else {
            if (this.channel.sendMessage(new PjsipSignaling.SdpUpdateMessage(sdpParams.getSdp(), sdpParams.getType(), false))) {
                return;
            }
            ErrorCode errorCode = ErrorCode.SIPInternalError;
            onError(errorCode, errorCode.getValue(), "Error sending sdp update");
        }
    }

    @Override // com.amazon.comms.ringservice.DataChannelListener
    public void onStateChange(final DataChannelEvent dataChannelEvent) {
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.41
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.onStateChange(dataChannelEvent);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.MediaSessionListener
    public void onVideoEffectChanged(final Call.VideoEffect videoEffect) {
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.19
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.onVideoEffectChanged(videoEffect);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.MediaSessionListener
    public void onVideoReEnabled(final Call.Side side) {
        final MediaParams.LastFrameClearOption localLastFrameClearOption;
        if (side == Call.Side.Remote) {
            localLastFrameClearOption = this.deviceCallingServiceParams.getMediaParams().getRemoteLastFrameClearOption();
        } else {
            localLastFrameClearOption = this.deviceCallingServiceParams.getMediaParams().getLocalLastFrameClearOption();
        }
        final EglBase.Context eglContext = this.mediaManager.getEglContext();
        this.appCallbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.15
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.callImpl.clearLastFrame(side, eglContext, localLastFrameClearOption);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reconnect(Signaling.Channel channel, AmazonCallInfo amazonCallInfo) {
        synchronized (this) {
            this.callInfo = amazonCallInfo;
        }
        this.isReconnectingInitiator = true;
        reconnectInternal(channel, null, amazonCallInfo.getMediaRelayInfo());
    }

    public void sendData(final DataChannelDTO dataChannelDTO) {
        addWorkToPendingQueueOrExecute(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.40
            @Override // java.lang.Runnable
            public void run() {
                if (CallImplInternal.this.mediaSession == null || !CallImplInternal.this.isValidDTO(dataChannelDTO)) {
                    return;
                }
                CallImplInternal.this.mediaSession.sendData(dataChannelDTO);
            }
        });
    }

    public void sendDtmfTones(final String str, final int i, final int i2) {
        addWorkToPendingQueueOrExecute(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.37
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.sendDtmfTonesInternal(str, i, i2);
            }
        });
    }

    public void sendRealTimeTextData(byte[] bArr) {
        log.d("Sending Real Time Text");
        sendData(new DataChannelDTO(REAL_TIME_TEXT_LABEL, bArr, false));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void setCallForSession(@Nonnull CallImpl callImpl) {
        if (this.callImpl != null && this.callImpl != callImpl) {
            throw new RuntimeException("Cannot assign reference to callImpl again!");
        }
        this.callImpl = callImpl;
    }

    public void setCallReconnectInitiationSupported(boolean z) {
        this.callReconnectInitiationSupported = z;
    }

    public void setLocalAudioState(final boolean z) {
        addWorkToPendingQueueOrExecute(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.31
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.mediaSession.setLocalAudioState(z, MediaStateChangeTrigger.USER_REQUEST);
            }
        });
    }

    public void setLocalMediaCapability(final boolean z, final boolean z2) {
        this.orchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.23
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.setLocalMediaCapabilityInternal(z, z2);
            }
        });
    }

    public void setLocalVideoPermitted(final boolean z) {
        addWorkToPendingQueueOrExecute(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.33
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.mediaSession.setLocalVideoPermitted(z, MediaStateChangeTrigger.PERMISSION_CHANGE);
            }
        });
    }

    public void setLocalVideoState(final boolean z) {
        addWorkToPendingQueueOrExecute(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.32
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.mediaSession.setLocalVideoState(z, MediaStateChangeTrigger.USER_REQUEST);
            }
        });
    }

    public void setMediaConstraints(final Map<String, Integer> map) {
        addWorkToPendingQueueOrExecute(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.36
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.log.i("setMediaConstraints");
                if (CallImplInternal.this.mediaSession == null || map.isEmpty()) {
                    return;
                }
                CallImplInternal.this.mediaSession.setMediaConstraints(map);
            }
        });
    }

    public void setSystemCameraState(final boolean z) {
        addWorkToPendingQueueOrExecute(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.35
            @Override // java.lang.Runnable
            public void run() {
                GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport1.outline107("setSystemCameraState. Enabled = "), z, CallImplInternal.log);
                if (CallImplInternal.this.mediaSession != null) {
                    CallImplInternal.this.mediaSession.setSystemCameraState(z);
                }
            }
        });
    }

    public void setSystemMediaState(final boolean z) {
        addWorkToPendingQueueOrExecute(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.34
            @Override // java.lang.Runnable
            public void run() {
                GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport1.outline107("setSystemMediaState. enabled= "), z, CallImplInternal.log);
                if (CallImplInternal.this.mediaSession != null) {
                    CallImplInternal.this.mediaSession.setSystemMediaState(z);
                }
            }
        });
    }

    public void setVideoEffect(final Call.VideoEffect videoEffect, final double d) {
        addWorkToPendingQueueOrExecute(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.26
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.setVideoEffectInternal(videoEffect, d);
            }
        });
    }

    public void setVolume(final float f) {
        addWorkToPendingQueueOrExecute(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.38
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.setVolumeInternal(f);
            }
        });
    }

    public void switchCamera() {
        addWorkToPendingQueueOrExecute(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.27
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.mediaSession.switchCamera();
            }
        });
    }

    public void updateCallStateTransitionTimeouts(final Map<Call.State, TimeoutRule> map) {
        this.orchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.30
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.updateStateTransitionTimeouts(map);
            }
        });
    }

    synchronized void updateParticipantInfo(Call.AcceptParams acceptParams) {
        Preconditions.checkArgument(this.isDropInCall, "This should only be used for drop in calls");
        this.callInfo.getCalleeBasedOnCallOrigin().setName(acceptParams.getCalleeName());
        this.callInfo.getCalleeBasedOnCallOrigin().setDropInPermission(acceptParams.isCalleeDropInPermission());
        this.callInfo.getCallerBasedOnCallOrigin().setName(acceptParams.getCallerName());
        this.callInfo.getCallerBasedOnCallOrigin().setDropInPermission(acceptParams.isCallerDropInPermission());
    }

    public void switchCamera(final String str) {
        addWorkToPendingQueueOrExecute(new Runnable() { // from class: com.amazon.comms.ringservice.CallImplInternal.28
            @Override // java.lang.Runnable
            public void run() {
                CallImplInternal.this.mediaSession.switchCamera(str);
            }
        });
    }

    private void answer(Sdp sdp) {
        CommsLogger commsLogger = log;
        commsLogger.d("answer. This= " + this);
        if (isCallAccepted()) {
            this.channel.sendMessage(new PjsipSignaling.AnswerMessage(getCallInfo().getOutgoingHeaders(), sdp));
            if (this.isReconnecting) {
                this.eventTracer.mark(EventTracerConfig.Event.reconnect_receiver_signaling_answer);
            }
            log.i("answer: Received answer command from UI");
            onAccepted(null);
            return;
        }
        GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport1.outline107("answer: call not accepted yet. answerSdp= "), this.answerSdp != null, log);
        this.answerSdp = sdp;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void updateParticipantInfo(Call.CallInfoParams callInfoParams) {
        this.callInfo.getCallerBasedOnCallOrigin().setName(callInfoParams.getCallerName());
        this.callInfo.getCallerBasedOnCallOrigin().setEndpointDescription(callInfoParams.getCallerEndpointDescription());
        this.callInfo.getCalleeBasedOnCallOrigin().setName(callInfoParams.getCalleeName());
        this.callInfo.getCalleeBasedOnCallOrigin().setEndpointDescription(callInfoParams.getCalleeEndpointDescription());
    }

    @Override // com.amazon.comms.ringservice.MediaSessionListener
    public void onError(ErrorCode errorCode, String str) {
        onError(errorCode, errorCode.getValue(), str);
    }
}
