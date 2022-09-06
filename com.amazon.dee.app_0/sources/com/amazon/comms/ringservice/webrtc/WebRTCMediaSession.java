package com.amazon.comms.ringservice.webrtc;

import amazon.media.AmazonAudioManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.amazon.asp.AudioSignalProcessor;
import com.amazon.comms.calling.instrumentation.EventTracerConfig;
import com.amazon.comms.calling.service.BundlePolicy;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.DataChannelDTO;
import com.amazon.comms.calling.service.DataChannelEvent;
import com.amazon.comms.calling.service.DynamicAcousticParams;
import com.amazon.comms.calling.service.ErrorCode;
import com.amazon.comms.calling.service.MediaParams;
import com.amazon.comms.calling.service.MediaStateChangeTrigger;
import com.amazon.comms.calling.service.MediaStats;
import com.amazon.comms.calling.service.MediaStatus;
import com.amazon.comms.calling.service.PlatformVoIPSelection;
import com.amazon.comms.calling.service.VideoConstraints;
import com.amazon.comms.device.AvsDeviceFacade;
import com.amazon.comms.instrumentation.ClocksImpl;
import com.amazon.comms.instrumentation.EventTracer;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.log.LogLevel;
import com.amazon.comms.ringservice.CallQualityStats;
import com.amazon.comms.ringservice.CameraMetricsReporter;
import com.amazon.comms.ringservice.DataChannelListener;
import com.amazon.comms.ringservice.MediaSession;
import com.amazon.comms.ringservice.MediaSessionListener;
import com.amazon.comms.ringservice.MetricsSession;
import com.amazon.comms.ringservice.RealTimeTextDataChannelListener;
import com.amazon.comms.ringservice.Sdp;
import com.amazon.comms.ringservice.util.DeviceModel;
import com.amazon.comms.ringservice.util.ThermalMitigationDetails;
import com.amazon.comms.ringservice.util.VideoConstraintsManager;
import com.amazon.comms.ringservice.webrtc.PeerConnectionClient;
import com.amazon.comms.ringservice.webrtc.VideoEffectController;
import com.amazon.comms.util.LooperExecutor;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import lombok.NonNull;
import org.json.simple.JSONObject;
import org.webrtc.CameraErrorCode;
import org.webrtc.CameraSwitchHandlerShim;
import org.webrtc.EglBase;
import org.webrtc.IceCandidate;
import org.webrtc.PeerConnection;
import org.webrtc.SessionDescription;
import org.webrtc.StatsReport;
import org.webrtc.VideoRenderer;
import org.webrtc.voiceengine.WebRtcAudioUtils;
/* loaded from: classes12.dex */
public class WebRTCMediaSession implements MediaSession, PeerConnectionClient.PeerConnectionEvents, VideoEffectController.VideoEffectChangeListener {
    private static final int AMZN_REDLINES_GRAY_COLOR = -12566463;
    private static final int ASP_CMD_SET_VOIP_CONFIG = 74;
    private static final String FROST_QUALITY_MODE_CONSTRAINT = "frostQualityMode";
    private static final int FROST_QUALITY_MODE_DISABLE = 0;
    private static final int FROST_QUALITY_MODE_ENABLE = 1;
    private static final String SUSPEND_VIDEO_THERMAL = "suspendVideoThermal";
    private static final String T140_PROTOCOL = "t140";
    private static final String TRACE_CALL_SIDE = "callSide";
    private static final String TRACE_MEDIA_STATUS = "MediaStatus";
    private static final String TRACE_MEDIA_TRIGGER = "MediaTrigger";
    private static final String TRACE_SOURCE_NAME = "WebRTCMediaSession";
    private static final String TRACE_TYPE = "WebRTCMediaTracer";
    private static final String TRACE_WEB_RTC_SAMPLE_RATE = "WebRTCSampleRate";
    private static final String VIDEO_CODEC_H264 = "H264";
    private static final String VOIP_ASP_OFF = "asp_voip_mode=0";
    private static final String VOIP_ASP_ON = "asp_voip_mode=1";
    private static final CommsLogger log = CommsLogger.getLogger(WebRTCMediaSession.class);
    private AmazonAudioManager audioManager;
    private final AvsDeviceFacade avsDeviceFacade;
    private String cachedAudioMid;
    private String cachedVideoMid;
    private boolean callEstablished;
    private String callProvider;
    private final CameraSwitchHandlerShim cameraSwitchHandler;
    private boolean createOfferPending;
    private PeerConnection.IceConnectionState currentIceConnectionState;
    private DataChannelListener dataChannelListener;
    private boolean dtmfInsertable;
    private final EglBase.Context eglContext;
    private final LooperExecutor executor;
    private final boolean forceReceiveOnlyVideo;
    private boolean iceConnectionStateConnectedAtLeastOnce;
    private boolean incomingCall;
    private String lastOpenedCameraName;
    private long lastWebrtcStatsTime;
    private final boolean localAudioCapable;
    private boolean localAudioEnabled;
    private boolean localAudioRequest;
    private final VideoConstraints localDeviceVideoConstraints;
    private boolean localRealTimeTextEnabled;
    private boolean localVideoCapable;
    private boolean localVideoEnabled;
    private boolean localVideoPermitted;
    private boolean localVideoRequest;
    private boolean localVideoSuspended;
    private List<DataChannelProperties> mActiveDataChannels;
    private Context mApplicationContext;
    private String mCallId;
    private EventTracer mEventTracer;
    private LinkedList<PeerConnection.IceServer> mIceServers;
    private boolean mIceTricklingAllowed;
    private final MediaParams mMediaParams;
    private MediaSessionListener mMediaSessionListener;
    private final Handler mOrchestratorHandler;
    private PeerConnectionClient mPeerConnectionClient;
    private PeerConnectionClient.PeerConnectionParameters mPeerConnectionParameters;
    private Sdp mPseudoSdpForIceTrickling;
    private List<IceCandidate> mTrickledRemoteCandidates;
    private VideoEffectController mVideoEffectController;
    private WarmupListener mWarmupListener;
    private final VideoConstraints maxVideoConstraintsToRequestFromRemote;
    private final MetricsSession metricsSession;
    private boolean needsToReportActiveConnections;
    private List<IceCandidate> pendingRemoteCandidateList;
    private Sdp pendingRemoteOffer;
    private RealTimeTextDataChannelListener realTimeTextDataChannelListener;
    private String realTimeTextLabel;
    private boolean realTimeTextRequested;
    private boolean reduceVideoResolutionOnNonH264Available;
    private VideoConstraints reducedVideoConstraintsToRequestFromRemote;
    private boolean remoteAudioCapable;
    private boolean remoteAudioEnabled;
    private boolean remoteAudioRequested;
    private boolean remoteRealTimeTextEnabled;
    private MediaStateChangeTrigger remoteSuspendedTrigger;
    private MediaStateChangeTrigger remoteSuspendedTriggerRequest;
    private boolean remoteVideoCapable;
    private boolean remoteVideoEnabled;
    private boolean remoteVideoH264Disabled;
    private boolean remoteVideoRequested;
    private boolean remoteVideoSuspendRequested;
    private boolean remoteVideoSuspended;
    private boolean renderRemoteVideoSupported;
    private boolean restartIcePending;
    private boolean restartingIce;
    private SignalingState signalingState;
    private final LooperExecutor statsExecutor;
    private MediaStateChangeTrigger suspendedTrigger;
    private boolean systemCameraEnabled;
    private boolean systemMediaEnabled;
    private ThermalMitigationDetails thermalMitigationDetails;
    private boolean usingFrontFacingCamera;
    private int videoSuspendedCount;
    private boolean waitingForRemoteUpdate;
    private long warmupStartedTimestampERT;
    private long warmupStartedTimestampEpoch;
    private boolean wasLastOfferer;
    private final WebRTCGlobalsProvider webRTCGlobalsProvider;

    /* renamed from: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession$22  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass22 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$comms$calling$service$DataChannelEvent$Type = new int[DataChannelEvent.Type.values().length];
        static final /* synthetic */ int[] $SwitchMap$org$webrtc$PeerConnection$IceConnectionState;

        static {
            try {
                $SwitchMap$com$amazon$comms$calling$service$DataChannelEvent$Type[DataChannelEvent.Type.StateChanged.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$DataChannelEvent$Type[DataChannelEvent.Type.ReceivedMessage.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$DataChannelEvent$Type[DataChannelEvent.Type.BufferedAmountChange.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $SwitchMap$org$webrtc$PeerConnection$IceConnectionState = new int[PeerConnection.IceConnectionState.values().length];
            try {
                $SwitchMap$org$webrtc$PeerConnection$IceConnectionState[PeerConnection.IceConnectionState.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$IceConnectionState[PeerConnection.IceConnectionState.DISCONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$IceConnectionState[PeerConnection.IceConnectionState.FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public enum MediaType {
        Audio,
        Video
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum SignalingState {
        OFFERING,
        ANSWERING,
        WEBRTC_STABLE,
        STABLE
    }

    public WebRTCMediaSession(Context context, String str, boolean z, MediaSessionListener mediaSessionListener, Handler handler, MetricsSession metricsSession, EventTracer eventTracer, PeerConnectionClient.PeerConnectionParameters peerConnectionParameters, MediaParams mediaParams, boolean z2, boolean z3, LooperExecutor looperExecutor, LooperExecutor looperExecutor2, WebRTCGlobalsProvider webRTCGlobalsProvider, EglBase.Context context2, DataChannelListener dataChannelListener, RealTimeTextDataChannelListener realTimeTextDataChannelListener, boolean z4, AvsDeviceFacade avsDeviceFacade) {
        this.mIceServers = new LinkedList<>();
        this.currentIceConnectionState = PeerConnection.IceConnectionState.NEW;
        this.iceConnectionStateConnectedAtLeastOnce = false;
        this.localAudioEnabled = true;
        this.localAudioRequest = true;
        this.remoteAudioEnabled = true;
        this.remoteVideoEnabled = true;
        this.systemCameraEnabled = true;
        this.localVideoSuspended = false;
        this.remoteVideoSuspended = false;
        this.remoteVideoSuspendRequested = false;
        this.restartingIce = false;
        this.videoSuspendedCount = 0;
        this.suspendedTrigger = null;
        this.remoteSuspendedTrigger = null;
        this.remoteSuspendedTriggerRequest = null;
        this.localAudioCapable = true;
        this.remoteAudioCapable = true;
        this.remoteVideoCapable = true;
        this.localRealTimeTextEnabled = false;
        this.remoteRealTimeTextEnabled = false;
        this.realTimeTextRequested = false;
        this.mCallId = "BAD";
        this.signalingState = SignalingState.STABLE;
        this.wasLastOfferer = false;
        this.waitingForRemoteUpdate = false;
        this.remoteVideoH264Disabled = false;
        this.reduceVideoResolutionOnNonH264Available = false;
        this.reducedVideoConstraintsToRequestFromRemote = null;
        this.localVideoPermitted = true;
        this.usingFrontFacingCamera = true;
        this.realTimeTextLabel = null;
        this.mActiveDataChannels = new ArrayList();
        this.lastWebrtcStatsTime = -1L;
        this.mPseudoSdpForIceTrickling = null;
        this.mIceTricklingAllowed = false;
        this.audioManager = null;
        this.mTrickledRemoteCandidates = null;
        this.cameraSwitchHandler = new CameraSwitchHandlerShim() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.1
            @Override // org.webrtc.CameraVideoCapturer.CameraSwitchHandler
            public void onCameraSwitchDone(final boolean z5) {
                WebRTCMediaSession.this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        WebRTCMediaSession.this.mMediaSessionListener.onCameraSwitchDone(z5);
                    }
                });
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraSwitchHandler
            public void onCameraSwitchError(final String str2) {
                WebRTCMediaSession.this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        WebRTCMediaSession.this.mMediaSessionListener.onCameraSwitchError(str2);
                        CommsLogger commsLogger = WebRTCMediaSession.log;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onCameraSwitchError");
                        outline107.append(str2);
                        commsLogger.e(outline107.toString());
                    }
                });
            }
        };
        Preconditions.checkNotNull(mediaSessionListener, "WebRTCMediaSessionListener must be non-null.");
        Preconditions.checkNotNull(peerConnectionParameters, "PeerConnectionParameters must be non-null.");
        this.mCallId = str;
        this.incomingCall = z;
        this.renderRemoteVideoSupported = z2;
        this.forceReceiveOnlyVideo = z3;
        this.pendingRemoteCandidateList = new LinkedList();
        this.mMediaSessionListener = mediaSessionListener;
        this.metricsSession = metricsSession;
        this.mOrchestratorHandler = handler;
        this.mApplicationContext = context;
        this.mEventTracer = eventTracer;
        this.mPeerConnectionParameters = peerConnectionParameters;
        this.localVideoCapable = this.mPeerConnectionParameters.isVideoCapable();
        this.localVideoRequest = this.mPeerConnectionParameters.isVideoRequestEnabled();
        this.systemMediaEnabled = this.mPeerConnectionParameters.isInitialSystemMediaEnabled();
        this.systemCameraEnabled = this.mPeerConnectionParameters.isInitialSystemCameraEnabled();
        this.localDeviceVideoConstraints = new VideoConstraints(this.mPeerConnectionParameters.getVideoWidth(), this.mPeerConnectionParameters.getVideoHeight(), this.mPeerConnectionParameters.getVideoFps());
        this.maxVideoConstraintsToRequestFromRemote = this.mPeerConnectionParameters.getMaxVideoConstraintsToRequestFromRemote();
        this.reducedVideoConstraintsToRequestFromRemote = this.mPeerConnectionParameters.getMaxVideoConstraintsOnReducedResolution();
        this.reduceVideoResolutionOnNonH264Available = this.mPeerConnectionParameters.isReduceVideoResolutionOnNoH264Remote();
        this.thermalMitigationDetails = new ThermalMitigationDetails(this.localDeviceVideoConstraints.getVideoFps(), this.localDeviceVideoConstraints.getVideoWidth());
        this.realTimeTextRequested = z4;
        updateLocalMediaState(true, MediaStateChangeTrigger.USER_REQUEST);
        this.mMediaParams = mediaParams;
        this.executor = looperExecutor;
        this.statsExecutor = looperExecutor2;
        this.webRTCGlobalsProvider = webRTCGlobalsProvider;
        this.eglContext = context2;
        this.dataChannelListener = dataChannelListener;
        this.realTimeTextDataChannelListener = realTimeTextDataChannelListener;
        this.mTrickledRemoteCandidates = new LinkedList();
        this.avsDeviceFacade = avsDeviceFacade;
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WebRTCMediaSession created. call ID = ");
        outline107.append(log.sensitiveCallId(this.mCallId));
        commsLogger.i(outline107.toString());
    }

    private boolean activeDataChannelExistsInSDP(Sdp sdp) {
        if (sdp.isMediaPresent("application")) {
            this.mActiveDataChannels = sdp.getActiveDataChannels();
            if (!this.mActiveDataChannels.isEmpty()) {
                log.i("Active data channel found in SDP");
                return true;
            }
            log.i("No active datachannels found");
            return false;
        }
        log.i("Media Application not found");
        this.mActiveDataChannels.clear();
        return false;
    }

    private void addRemoteIceCandidatesInternal(List<IceCandidate> list) {
        if (this.mPeerConnectionClient == null) {
            log.e("Received ICE candidate for a non-initialized peer connection.");
            this.mMediaSessionListener.onError(ErrorCode.Unknown, "Received ICE candidate for a non-initialized peer connection.");
            return;
        }
        log.i(String.format(Locale.US, "addRemoteIceCandidatesInternal: candidates size - %s", Integer.valueOf(list.size())));
        for (IceCandidate iceCandidate : list) {
            CommsLogger commsLogger = log;
            Locale locale = Locale.US;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("addRemoteIceCandidatesInternal: candidate ");
            outline107.append(iceCandidate.toString());
            commsLogger.d(String.format(locale, outline107.toString(), new Object[0]));
            this.mPeerConnectionClient.addRemoteIceCandidate(iceCandidate);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void appendVideoConstraints(Sdp sdp) {
        VideoConstraints videoConstraints = this.maxVideoConstraintsToRequestFromRemote;
        if (videoConstraints == null) {
            Map<Integer, VideoConstraints> supportedVideoResolutions = this.mPeerConnectionParameters.getSupportedVideoResolutions();
            if (supportedVideoResolutions == null || supportedVideoResolutions.size() <= 0) {
                videoConstraints = null;
            } else {
                TreeMap treeMap = new TreeMap(supportedVideoResolutions);
                videoConstraints = (VideoConstraints) treeMap.get((Integer) treeMap.lastKey());
            }
        }
        if (videoConstraints != null) {
            sdp.appendVideoConstraints(videoConstraints);
        }
    }

    private void clearPlatformVoIPSelection() {
        AmazonAudioManager amazonAudioManager;
        if (this.mPeerConnectionParameters.getPlatformVoIPSelection() == null || (amazonAudioManager = this.audioManager) == null) {
            return;
        }
        try {
            boolean dolbyDapEnabled = amazonAudioManager.setDolbyDapEnabled(true);
            CommsLogger commsLogger = log;
            commsLogger.i("Restore DolbyDap status: " + dolbyDapEnabled);
            this.audioManager.setParameters(VOIP_ASP_OFF);
        } catch (Exception e) {
            log.e("Exception on AmazonAudioManager.setDolbyDapEnabled operation: ", e);
        }
    }

    private void configureDynamicAcousticParams() {
        log.i("configureDynamicAcousticParams");
        if (this.mPeerConnectionParameters.getDynamicAcousticParamsConfigPath() != DynamicAcousticParams.ConfigPath.LIBASP) {
            return;
        }
        if (this.mPeerConnectionParameters.getDynamicAcousticParams() == null) {
            log.e("DynamicAcousticParams is null");
            return;
        }
        try {
            AudioSignalProcessor audioSignalProcessor = AudioSignalProcessor.getInstance();
            byte[] bArr = new byte[2];
            byte[] bArr2 = new byte[0];
            for (Map.Entry<DynamicAcousticParams.AcousticParamId, Boolean> entry : this.mPeerConnectionParameters.getDynamicAcousticParams().getAllParams().entrySet()) {
                bArr[0] = (byte) entry.getKey().getCode();
                bArr[1] = (byte) (entry.getValue().booleanValue() ? 0 : 1);
                if (audioSignalProcessor.command(74, bArr, bArr2) != 0) {
                    this.metricsSession.recordCount("DynamicAFE", String.format(Locale.US, "%s_BYPASS_%s_CMD_FAILED", Byte.valueOf(bArr[0]), Byte.valueOf(bArr[1])), 1.0d, this.mCallId);
                    log.e(String.format(Locale.US, "ASP command failed: cmdCode: %d, AcousticParamId: %d, Bypass: %d.", 74, Byte.valueOf(bArr[0]), Byte.valueOf(bArr[1])));
                } else {
                    this.metricsSession.recordCount("DynamicAFE", String.format(Locale.US, "%s_BYPASS_%s_CMD_FAILED", Byte.valueOf(bArr[0]), Byte.valueOf(bArr[1])), FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, this.mCallId);
                    log.i(String.format(Locale.US, "ASP command succeeded: cmdCode: %d, AcousticParamId: %d, Bypass: %d.", 74, Byte.valueOf(bArr[0]), Byte.valueOf(bArr[1])));
                }
            }
        } catch (Exception e) {
            CommsLogger commsLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception on ASP comamnd: ");
            outline107.append(e.getMessage());
            commsLogger.e(outline107.toString());
        }
    }

    private void configurePlatformVoIPSelection() {
        PlatformVoIPSelection platformVoIPSelection = this.mPeerConnectionParameters.getPlatformVoIPSelection();
        if (platformVoIPSelection == null) {
            log.i("No PlatformVoIPSelection configured ...");
            return;
        }
        try {
            boolean isEnableLibasp = platformVoIPSelection.isEnableLibasp();
            boolean isEnableDolbyDap = platformVoIPSelection.isEnableDolbyDap();
            CommsLogger commsLogger = log;
            commsLogger.i("Configure PlatformVoIPSelection in progress, enableLibasp = " + isEnableLibasp + " , enableDolbyDap = " + isEnableDolbyDap);
            if (this.audioManager == null) {
                this.audioManager = (AmazonAudioManager) this.mApplicationContext.getSystemService("audio");
                if (this.audioManager == null) {
                    log.e("Null audioManager, can't move forward with PlatformVoIPSelection");
                    return;
                }
            }
            if (isEnableLibasp) {
                this.audioManager.setParameters(VOIP_ASP_ON);
            } else {
                this.audioManager.setParameters(VOIP_ASP_OFF);
            }
            boolean dolbyDapEnabled = this.audioManager.setDolbyDapEnabled(isEnableDolbyDap);
            CommsLogger commsLogger2 = log;
            commsLogger2.i("setDolbyDapEnabled status: " + dolbyDapEnabled);
        } catch (Exception e) {
            log.e("Exception on AmazonAudioManager operation: ", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String createMediaStatusJson(Call.Side side, MediaStatus mediaStatus, MediaStateChangeTrigger mediaStateChangeTrigger) {
        String json = new Gson().toJson(mediaStatus);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(TRACE_CALL_SIDE, side);
        jSONObject.put(TRACE_MEDIA_STATUS, json);
        jSONObject.put(TRACE_MEDIA_TRIGGER, mediaStateChangeTrigger);
        return jSONObject.toString();
    }

    private void createOffer() {
        if (this.mPeerConnectionClient == null) {
            log.w("createOffer: PeerConnectionClient doesn't exist");
            return;
        }
        log.i("createOffer: signaling OFFERING");
        this.signalingState = SignalingState.OFFERING;
        this.mPeerConnectionClient.createOffer(false);
    }

    private String createSampleRateJson(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(TRACE_WEB_RTC_SAMPLE_RATE, str);
        return jSONObject.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void filterActiveDataChannels(Call.Side side, List<DataChannelProperties> list) {
        MediaStateChangeTrigger mediaStateChangeTrigger = side == Call.Side.Local ? MediaStateChangeTrigger.USER_REQUEST : MediaStateChangeTrigger.REMOTE_USER_REQUEST;
        DataChannelProperties realTimeTextDataChannel = getRealTimeTextDataChannel(list);
        PeerConnectionClient.PeerConnectionParameters peerConnectionParameters = this.mPeerConnectionParameters;
        if (peerConnectionParameters != null && !peerConnectionParameters.isRealTimeTextCapable()) {
            if (realTimeTextDataChannel != null) {
                this.realTimeTextRequested = true;
                list.remove(realTimeTextDataChannel);
            }
            updateRealTimeTextStatus(side, false, false, mediaStateChangeTrigger);
        } else if (realTimeTextDataChannel != null) {
            this.realTimeTextLabel = realTimeTextDataChannel.getLabel();
            this.realTimeTextRequested = true;
            updateRealTimeTextStatus(side, true, true, mediaStateChangeTrigger);
        } else {
            updateRealTimeTextStatus(side, false, false, mediaStateChangeTrigger);
        }
    }

    private String getCallProvider() {
        return this.callProvider;
    }

    private List<IceCandidate> getNewRemoteCandidates(List<IceCandidate> list) {
        boolean z;
        Vector vector = new Vector();
        for (IceCandidate iceCandidate : list) {
            Iterator<IceCandidate> it2 = this.mTrickledRemoteCandidates.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = false;
                    break;
                }
                IceCandidate next = it2.next();
                if (iceCandidate.sdpMLineIndex == next.sdpMLineIndex && iceCandidate.sdpMid.equalsIgnoreCase(next.sdpMid) && iceCandidate.sdp.equalsIgnoreCase(next.sdp)) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                CommsLogger commsLogger = log;
                Locale locale = Locale.US;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getNewRemoteCandidates: new candidate - ");
                outline107.append(iceCandidate.toString());
                commsLogger.d(String.format(locale, outline107.toString(), new Object[0]));
                vector.add(iceCandidate);
            }
        }
        return vector;
    }

    private DataChannelProperties getRealTimeTextDataChannel(List<DataChannelProperties> list) {
        for (DataChannelProperties dataChannelProperties : list) {
            if ("t140".equals(dataChannelProperties.getProtocol())) {
                return dataChannelProperties;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public VideoConstraints getVideoConstraintsToRequestFromRemote() {
        VideoConstraints videoConstraints = this.localDeviceVideoConstraints;
        if ((this.remoteVideoH264Disabled && this.reduceVideoResolutionOnNonH264Available) || shouldRequestReducedResolutionIfNoHwCodec()) {
            VideoConstraints videoConstraints2 = this.reducedVideoConstraintsToRequestFromRemote;
            if (videoConstraints2 != null) {
                CommsLogger commsLogger = log;
                commsLogger.v("getVideoConstraintsToRequestFromRemote:: videoConstraintsToRequest= " + videoConstraints2);
                videoConstraints = videoConstraints2;
            } else {
                log.e("Device requests reduced video resolution but no reduced video resolution available.");
            }
        }
        return VideoConstraintsManager.minVideoConstraints(videoConstraints, this.maxVideoConstraintsToRequestFromRemote);
    }

    private Call.VideoEffect getVideoEffect() {
        VideoEffectController videoEffectController = this.mVideoEffectController;
        return videoEffectController != null ? videoEffectController.getVideoEffect() : Call.VideoEffect.None;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCallSignalingDone() {
        log.i("handleCallSignalingDone");
        this.signalingState = SignalingState.STABLE;
        if (this.restartIcePending) {
            log.i("Handling pending restartIce request now");
            this.restartIcePending = false;
            restartIce(this.pendingRemoteOffer, false);
            this.pendingRemoteOffer = null;
            return;
        }
        Sdp sdp = this.pendingRemoteOffer;
        if (sdp != null) {
            receiveOffer(sdp);
            this.pendingRemoteOffer = null;
        } else if (!this.createOfferPending || this.waitingForRemoteUpdate) {
        } else {
            log.i("Waiting for update is false, we can create offer now");
            this.createOfferPending = false;
            if (this.remoteVideoCapable && this.localVideoCapable) {
                createOffer();
            } else {
                log.i("onSignalingDone: Ignoring SDP update");
            }
        }
    }

    private static boolean isIceConnected(PeerConnection.IceConnectionState iceConnectionState) {
        return iceConnectionState == PeerConnection.IceConnectionState.CONNECTED || iceConnectionState == PeerConnection.IceConnectionState.COMPLETED;
    }

    private boolean isVideoChannelSuspended() {
        return this.localVideoSuspended || this.remoteVideoSuspendRequested;
    }

    private void markEventInEventTracer(EventTracerConfig.Event event) {
        EventTracer eventTracer = this.mEventTracer;
        if (eventTracer == null) {
            return;
        }
        eventTracer.mark(event);
    }

    private void onIceConnected(PeerConnection.IceConnectionState iceConnectionState) {
        log.i("ICE connected");
        markEventInEventTracer(EventTracerConfig.Event.Webrtc_session_connected);
        if (!this.iceConnectionStateConnectedAtLeastOnce) {
            sendMetricToMetricsSession("FirstIceConnect", 1);
            markEventInEventTracer(EventTracerConfig.Event.call_ice_connected);
        } else if (!isIceConnected(iceConnectionState)) {
            sendMetricToMetricsSession("IceReconnect", 1);
            markEventInEventTracer(EventTracerConfig.Event.call_ice_reconnected);
        }
        this.needsToReportActiveConnections = true;
        if (this.mPeerConnectionClient == null) {
            log.w("onIceConnected PeerConnectionClient doesn't exist");
            return;
        }
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WebRTCStatsPollingFrequency=");
        outline107.append(this.mPeerConnectionParameters.getWebRTCStatsPollingFrequency());
        commsLogger.d(outline107.toString());
        this.mPeerConnectionClient.enableStatsEvents(true, this.mPeerConnectionParameters.getWebRTCStatsPollingFrequency());
        if (getVideoEffect() == Call.VideoEffect.Frosted) {
            setFrostQualityMode(true);
        }
        this.mMediaSessionListener.onIceConnected();
        recordWebRTCTrace(createSampleRateJson(Integer.toString(WebRtcAudioUtils.getDefaultSampleRateHz())));
    }

    private void onIceDisconnected() {
        log.i("ICE disconnected.");
        if (this.iceConnectionStateConnectedAtLeastOnce) {
            sendMetricToMetricsSession("IceDisconnectPostConnect", 1);
        } else {
            sendMetricToMetricsSession("IceDisconnectWithoutConnect", 1);
        }
        markEventInEventTracer(EventTracerConfig.Event.call_ice_disconnected);
        this.mMediaSessionListener.onIceDisconnected();
    }

    private void onIceFailed() {
        log.i("ICE failed.");
        if (this.iceConnectionStateConnectedAtLeastOnce) {
            sendMetricToMetricsSession("IceFailPostConnect", 1);
        } else {
            sendMetricToMetricsSession("IceFailWithoutConnect", 1);
        }
        this.mMediaSessionListener.onIceFailed();
    }

    private void preparePeerConnection(boolean z) {
        log.i("preparePeerConnection");
        this.mPeerConnectionClient = new PeerConnectionClient(this.mPeerConnectionParameters, this, this.eglContext, z, this.mEventTracer, this.executor, this.mApplicationContext, this.webRTCGlobalsProvider, this.localAudioEnabled, this.localVideoEnabled, this.renderRemoteVideoSupported, !this.incomingCall);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processIceConnectionStateChange(PeerConnection.IceConnectionState iceConnectionState) {
        PeerConnection.IceConnectionState iceConnectionState2 = this.currentIceConnectionState;
        this.currentIceConnectionState = iceConnectionState;
        int ordinal = iceConnectionState.ordinal();
        if (ordinal == 2) {
            onIceConnected(iceConnectionState2);
            this.iceConnectionStateConnectedAtLeastOnce = true;
        } else if (ordinal != 4) {
            if (ordinal != 5) {
                return;
            }
            onIceDisconnected();
        } else if (iceConnectionState2 == PeerConnection.IceConnectionState.CONNECTED) {
            log.i("putting ice disconnection callback itself");
            onIceDisconnected();
        } else {
            onIceFailed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processOnIceConnectionReceivingChange(boolean z) {
        if (z) {
            sendMetricToMetricsSession("IceConnectionReceiving", 1);
            markEventInEventTracer(EventTracerConfig.Event.ice_connection_receiving);
            return;
        }
        sendMetricToMetricsSession("IceConnectionNotReceiving", 1);
        markEventInEventTracer(EventTracerConfig.Event.ice_connection_notreceiving);
    }

    private void receiveAnswer(Sdp sdp, Sdp.Type type) {
        this.wasLastOfferer = true;
        Map.Entry<Boolean, MediaStateChangeTrigger> videoSuspendedData = sdp.getVideoSuspendedData();
        if (videoSuspendedData == null) {
            this.remoteSuspendedTrigger = null;
        } else {
            CommsLogger commsLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Receive ANSWER with suspend=");
            outline107.append(videoSuspendedData.getKey());
            outline107.append(", trigger=");
            outline107.append(videoSuspendedData.getValue().name());
            commsLogger.i(outline107.toString());
            suspendVideoInternal(Call.Side.Remote, videoSuspendedData.getKey().booleanValue(), videoSuspendedData.getValue());
        }
        this.mPeerConnectionClient.processRemoteDescription(sdp, type);
    }

    private void receiveOffer(Sdp sdp) {
        if (this.mPeerConnectionClient == null) {
            log.w("receiveOffer: PeerConnectionClient doesn't exist");
            return;
        }
        log.i("Signaling ANSWERING");
        this.wasLastOfferer = false;
        log.i("Received sdp update, reset waitingForRemoteUpdate");
        this.waitingForRemoteUpdate = false;
        this.signalingState = SignalingState.ANSWERING;
        Map.Entry<Boolean, MediaStateChangeTrigger> videoSuspendedData = sdp.getVideoSuspendedData();
        if (videoSuspendedData == null) {
            this.remoteSuspendedTrigger = null;
        } else {
            CommsLogger commsLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Receive OFFER with suspend=");
            outline107.append(videoSuspendedData.getKey());
            outline107.append(", trigger=");
            outline107.append(videoSuspendedData.getValue().name());
            commsLogger.i(outline107.toString());
            suspendVideoInternal(Call.Side.Remote, videoSuspendedData.getKey().booleanValue(), videoSuspendedData.getValue());
        }
        this.mPeerConnectionClient.processRemoteDescription(sdp, Sdp.Type.OFFER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordWebRTCTrace(String str) {
        this.metricsSession.recordTrace(TRACE_SOURCE_NAME, this.mCallId, TRACE_TYPE, "application/json", str);
        CommsLogger commsLogger = log;
        commsLogger.d("Recorded WebRTCMedia Trace :" + str + " into Insights");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEndOfCandidatesIndication() {
        Sdp sdp = this.mPseudoSdpForIceTrickling;
        if (sdp == null) {
            log.e("sendEndOfCandidatesIndication: Pseudo sdp is null");
            return;
        }
        sdp.setEndOfCandidates();
        sendTrickleIceMessage();
    }

    private void sendMetricToMetricsSession(String str, int i) {
        MetricsSession metricsSession = this.metricsSession;
        if (metricsSession == null) {
            return;
        }
        metricsSession.recordCount("PeerConnectionClient", str, i, this.mCallId, getCallProvider());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTrickleIceMessage() {
        Sdp sdp;
        log.i(String.format(Locale.US, "sendTrickleIceMessage: mIceTricklingAllowed - %s", Boolean.valueOf(this.mIceTricklingAllowed)));
        if (this.mIceTricklingAllowed && (sdp = this.mPseudoSdpForIceTrickling) != null && sdp.isAttributePresent(Sdp.ATTRIBUTE_CANDIDATE)) {
            this.mMediaSessionListener.onIceCandidatesGenerated(this.mPseudoSdpForIceTrickling);
            if (!this.mPseudoSdpForIceTrickling.isAttributePresent(Sdp.ATTRIBUTE_END_OF_CANDIDATES)) {
                return;
            }
            log.i("sendTrickleIceMessage: Resetting pseudo sdp as end-of-candidates is sent");
            this.mPseudoSdpForIceTrickling = null;
            return;
        }
        log.i("sendTrickleIceMessage: not ready to trickle or no pending candidates");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFrostQualityMode(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(FROST_QUALITY_MODE_CONSTRAINT, Integer.valueOf(z ? 1 : 0));
        setMediaConstraints(hashMap);
    }

    private void setLocalMediaStateInternal(boolean z, boolean z2, MediaStateChangeTrigger mediaStateChangeTrigger) {
        if (this.mPeerConnectionClient == null) {
            log.w("setLocalMediaStateInternal: PeerConnectionClient doesn't exist");
            return;
        }
        log.i(String.format("Setting Local Media State Audio = %s (%s), Video = %s (%s)", Boolean.valueOf(z), Boolean.valueOf(this.localAudioEnabled), Boolean.valueOf(z2), Boolean.valueOf(this.localVideoEnabled)));
        if (this.localAudioEnabled == z && this.localVideoEnabled == z2) {
            MediaSessionListener mediaSessionListener = this.mMediaSessionListener;
            if (mediaSessionListener != null) {
                mediaSessionListener.onMediaStatusUpdated(Call.Side.Local, getMediaStatus(), mediaStateChangeTrigger);
            }
            recordWebRTCTrace(createMediaStatusJson(Call.Side.Local, getMediaStatus(), mediaStateChangeTrigger));
            return;
        }
        this.mPeerConnectionClient.setAudioEnabled(z, Call.Side.Local);
        this.mPeerConnectionClient.setVideoEnabled(z2, Call.Side.Local);
        if (this.remoteVideoCapable && this.localVideoCapable) {
            if (this.callEstablished && this.signalingState == SignalingState.STABLE) {
                log.i("Creating offer in setLocalMediaState");
                createOffer();
            } else {
                log.i("Queueing offer Creation in setLocalMediaState");
                this.createOfferPending = true;
            }
        } else {
            log.i("setLocalMediaState: Setting Local Media w/out SDP update");
        }
        onMediaStateChanged(Call.Side.Local, z, z2, mediaStateChangeTrigger);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRemoteMediaState(boolean z, boolean z2, MediaStateChangeTrigger mediaStateChangeTrigger) {
        MediaSessionListener mediaSessionListener;
        if (this.mPeerConnectionClient == null) {
            log.w("setRemoteMediaState PeerConnectionClient doesn't exist");
            return;
        }
        if (DeviceModel.AMAZON_AEOKN && !this.remoteVideoCapable) {
            z = true;
        }
        log.i(String.format("setRemoteMediaState Setting Remote Media State Audio = %s Video = %s Trigger = %s", Boolean.valueOf(z), Boolean.valueOf(z2), mediaStateChangeTrigger.name()));
        if (this.remoteAudioEnabled == z && this.remoteVideoEnabled == z2) {
            log.d("No change of state detected in. Not updating remote media state");
            return;
        }
        boolean z3 = this.remoteVideoEnabled != z2;
        boolean z4 = this.remoteAudioCapable && z;
        boolean z5 = this.remoteVideoCapable && z2;
        log.i(String.format("Setting Remote Media State Audio = %s (%s), Video = %s (%s)", Boolean.valueOf(z4), Boolean.valueOf(this.remoteAudioEnabled), Boolean.valueOf(z5), Boolean.valueOf(this.remoteVideoEnabled)));
        this.remoteAudioEnabled = z4;
        this.remoteVideoEnabled = z5;
        this.remoteVideoSuspended = this.remoteVideoSuspendRequested;
        this.remoteSuspendedTrigger = mediaStateChangeTrigger;
        this.remoteSuspendedTriggerRequest = null;
        this.mPeerConnectionClient.setAudioEnabled(this.remoteAudioEnabled, Call.Side.Remote);
        this.mPeerConnectionClient.setVideoEnabled(this.remoteVideoEnabled, Call.Side.Remote);
        this.mMediaSessionListener.onMediaStatusUpdated(Call.Side.Remote, getMediaStatus(), this.remoteSuspendedTrigger);
        recordWebRTCTrace(createMediaStatusJson(Call.Side.Remote, getMediaStatus(), this.remoteSuspendedTrigger));
        if (z3 && this.remoteVideoEnabled && (mediaSessionListener = this.mMediaSessionListener) != null) {
            mediaSessionListener.onVideoReEnabled(Call.Side.Remote);
        } else {
            log.d("skipping video renabled callback. Nothing changed or media session listener not present");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldLogThisStatsEvent() {
        int webRTCStatsLoggingFrequency = this.mPeerConnectionParameters.getWebRTCStatsLoggingFrequency();
        if (this.lastWebrtcStatsTime > -1 && webRTCStatsLoggingFrequency > 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime - this.lastWebrtcStatsTime < webRTCStatsLoggingFrequency) {
                return false;
            }
            this.lastWebrtcStatsTime = elapsedRealtime;
            return true;
        }
        this.lastWebrtcStatsTime = SystemClock.elapsedRealtime();
        return true;
    }

    private boolean shouldRequestReducedResolutionIfNoHwCodec() {
        if (!this.localVideoCapable) {
            return false;
        }
        VideoCodecHwSupportChecker videoCodecHwSupportChecker = this.webRTCGlobalsProvider.getVideoCodecHwSupportChecker();
        if (this.forceReceiveOnlyVideo) {
            return !videoCodecHwSupportChecker.isH264OrVp8HwDecodeSupported();
        }
        return !videoCodecHwSupportChecker.isH264OrVp8HwEncodeAndDecodeSupported();
    }

    private void startMediaInternal(Sdp sdp) {
        this.mPeerConnectionClient.createPeerConnection(new PeerConnectionClient.SignalingParameters(this.mIceServers, "", "", "", null, !this.mPeerConnectionParameters.isTrickleIceEnabled()));
        this.metricsSession.recordCount("PeerConnectionClient", "IceCandidatePoolSize", this.localVideoCapable ? 4.0d : 2.0d, this.mCallId);
        GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport1.outline107("start media incomingCall - "), this.incomingCall, log);
        this.callEstablished = false;
        if (!this.incomingCall) {
            createOffer();
        } else if (sdp == null) {
        } else {
            this.pendingRemoteCandidateList = sdp.getIceCandidates();
            sdp.stripIceCandidates();
            processRemoteDescription(sdp, Sdp.Type.OFFER);
        }
    }

    private void suspendVideo(boolean z, MediaStateChangeTrigger mediaStateChangeTrigger) {
        if (z == this.localVideoSuspended) {
            CommsLogger commsLogger = log;
            commsLogger.d("suspendVideo: suspend video flag value: " + z + " matches current internal suspend state: " + this.localVideoSuspended + " . Ignoring call suspend video " + z + " due to : " + mediaStateChangeTrigger.name());
            return;
        }
        if (z) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("suspend_video_");
            outline107.append(mediaStateChangeTrigger.name());
            String sb = outline107.toString();
            MetricsSession metricsSession = this.metricsSession;
            int i = this.videoSuspendedCount + 1;
            this.videoSuspendedCount = i;
            metricsSession.recordCount("PeerConnectionClient", sb, i, this.mCallId);
        }
        suspendVideoInternal(Call.Side.Local, z, mediaStateChangeTrigger);
    }

    private void suspendVideoInternal(Call.Side side, boolean z, MediaStateChangeTrigger mediaStateChangeTrigger) {
        CommsLogger commsLogger = log;
        commsLogger.i("Suspend " + side + " Video : " + z + ", trigger=" + mediaStateChangeTrigger.name());
        if (this.mPeerConnectionClient == null) {
            log.i("suspendVideo: peer connection client is null.");
            return;
        }
        boolean isVideoChannelSuspended = isVideoChannelSuspended();
        if (side == Call.Side.Local && this.localVideoSuspended != z) {
            this.localVideoSuspended = z;
            if (!z) {
                mediaStateChangeTrigger = null;
            }
            this.suspendedTrigger = mediaStateChangeTrigger;
        } else if (side == Call.Side.Remote && this.remoteVideoSuspendRequested != z) {
            this.remoteVideoSuspendRequested = z;
            this.remoteSuspendedTriggerRequest = mediaStateChangeTrigger;
        }
        if (isVideoChannelSuspended() != isVideoChannelSuspended) {
            MediaStateChangeTrigger mediaStateChangeTrigger2 = this.suspendedTrigger;
            if (mediaStateChangeTrigger2 == null) {
                mediaStateChangeTrigger2 = MediaStateChangeTrigger.REMOTE_USER_REQUEST;
            }
            setLocalVideoState(!isVideoChannelSuspended(), mediaStateChangeTrigger2);
            return;
        }
        log.d("Video channel was already suspended. Not updating local state");
    }

    private void updateLocalMediaState(boolean z, MediaStateChangeTrigger mediaStateChangeTrigger) {
        boolean z2 = this.remoteAudioCapable && this.localAudioRequest && this.systemMediaEnabled;
        boolean z3 = this.localVideoCapable && this.remoteVideoCapable && this.localVideoRequest && this.systemMediaEnabled && this.systemCameraEnabled && this.localVideoPermitted;
        if (z) {
            this.localAudioEnabled = z2;
            this.localVideoEnabled = z3;
            log.i(String.format(Locale.US, "updateLocalMediaState - initial values, audio=%s video=%s system=%s camera=%s", Boolean.valueOf(this.localAudioEnabled), Boolean.valueOf(this.localVideoEnabled), Boolean.valueOf(this.systemMediaEnabled), Boolean.valueOf(this.systemCameraEnabled)));
            return;
        }
        setLocalMediaStateInternal(z2, z3, mediaStateChangeTrigger);
    }

    private void updateRealTimeTextStatus(Call.Side side, boolean z, boolean z2, MediaStateChangeTrigger mediaStateChangeTrigger) {
        if (this.localRealTimeTextEnabled == z && this.remoteRealTimeTextEnabled == z2) {
            return;
        }
        this.localRealTimeTextEnabled = z;
        this.remoteRealTimeTextEnabled = z2;
        if (this.mMediaSessionListener == null) {
            return;
        }
        log.i(String.format(Locale.US, "updateRealTimeTextStatus - Local=%s Remote=%s", Boolean.valueOf(this.localRealTimeTextEnabled), Boolean.valueOf(this.remoteRealTimeTextEnabled)));
        this.mMediaSessionListener.onMediaStatusUpdated(side, getMediaStatus(), mediaStateChangeTrigger);
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void addMediaRelayInfo(String str, String str2, String str3) {
        if (log.isLoggable(LogLevel.Debug)) {
            CommsLogger commsLogger = log;
            commsLogger.i("requested to add ice server= " + str);
        } else {
            log.i("requested to add ice server");
        }
        if (!str.isEmpty()) {
            this.mIceServers.add(new PeerConnection.IceServer(str, str2, str3));
        }
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void addRemoteIceCandidates() {
        log.i("addRemoteIceCandidates: adding cached candidates to the session");
        addRemoteIceCandidatesInternal(this.pendingRemoteCandidateList);
        this.pendingRemoteCandidateList.clear();
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void attachCall(String str, boolean z, MediaSessionListener mediaSessionListener, EventTracer eventTracer, PeerConnectionClient.PeerConnectionParameters peerConnectionParameters, DataChannelListener dataChannelListener, RealTimeTextDataChannelListener realTimeTextDataChannelListener, boolean z2) {
        log.i("attachCall");
        this.mCallId = str;
        this.incomingCall = z;
        this.mMediaSessionListener = mediaSessionListener;
        this.mEventTracer = eventTracer;
        this.mWarmupListener = null;
        this.mPeerConnectionParameters = peerConnectionParameters;
        this.localVideoCapable = this.mPeerConnectionParameters.isVideoCapable();
        this.localVideoRequest = this.mPeerConnectionParameters.isVideoRequestEnabled();
        this.systemMediaEnabled = this.mPeerConnectionParameters.isInitialSystemMediaEnabled();
        this.systemCameraEnabled = this.mPeerConnectionParameters.isInitialSystemCameraEnabled();
        this.realTimeTextRequested = z2;
        updateLocalMediaState(true, MediaStateChangeTrigger.USER_REQUEST);
        this.dataChannelListener = dataChannelListener;
        this.realTimeTextDataChannelListener = realTimeTextDataChannelListener;
        this.mEventTracer.markHistoric(EventTracerConfig.Event.Webrtc_media_warmup_started, this.warmupStartedTimestampERT, this.warmupStartedTimestampEpoch);
        this.mPeerConnectionClient.attachCall(this.mPeerConnectionParameters, this.localVideoCapable && this.remoteVideoCapable && this.localVideoRequest, this.localVideoEnabled, !this.incomingCall, this.mEventTracer);
        if (!Strings.isNullOrEmpty(this.lastOpenedCameraName)) {
            final String str2 = this.lastOpenedCameraName;
            this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.2
                @Override // java.lang.Runnable
                public void run() {
                    WebRTCMediaSession.this.mMediaSessionListener.onCameraOpening(str2);
                }
            });
        }
    }

    @Override // com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PeerConnectionEvents
    public void audioTrackUnderrunReport(final int i) {
        this.statsExecutor.execute(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.9
            @Override // java.lang.Runnable
            public void run() {
                if (WebRTCMediaSession.this.metricsSession == null) {
                    WebRTCMediaSession.log.w("couldn't upload AudioRenderTrackUnderrunCount metric as metricsSession is null");
                } else {
                    WebRTCMediaSession.this.metricsSession.recordCount("PeerConnectionClient", "AudioRenderTrackUnderrunCount", i, WebRTCMediaSession.this.mCallId);
                }
            }
        });
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void clearIceServers() {
        log.d("requested to clear ice servers");
        this.mIceServers.clear();
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public boolean didMediaConnectionEverEstablish() {
        return this.iceConnectionStateConnectedAtLeastOnce;
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public MediaStatus getMediaStatus() {
        return new MediaStatus(true, this.localVideoCapable, this.remoteAudioCapable, this.remoteVideoCapable, this.localAudioEnabled, this.localVideoEnabled, this.remoteAudioEnabled, this.remoteVideoEnabled, this.remoteAudioRequested, this.remoteVideoRequested, this.localRealTimeTextEnabled, this.remoteRealTimeTextEnabled, this.realTimeTextRequested);
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public ThermalMitigationDetails getThermalMitigationDetails() {
        return this.thermalMitigationDetails;
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void insertDtmf(String str, int i, int i2) {
        this.mPeerConnectionClient.insertDtmf(str, i, i2);
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public boolean isDtmfInsertable() {
        return this.dtmfInsertable;
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public boolean isMediaConnectionActive() {
        return isIceConnected(this.currentIceConnectionState);
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public boolean isUsingFrontFacingCamera() {
        return this.usingFrontFacingCamera;
    }

    @Override // com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PeerConnectionEvents
    public void onCameraError(final CameraErrorCode cameraErrorCode, final String str) {
        this.lastOpenedCameraName = null;
        this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.14
            @Override // java.lang.Runnable
            public void run() {
                CommsLogger commsLogger = WebRTCMediaSession.log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Attempt to change camera state failed with exception: ");
                outline107.append(str);
                commsLogger.w(outline107.toString());
                CameraErrorCode cameraErrorCode2 = cameraErrorCode;
                if (cameraErrorCode2 == CameraErrorCode.CAMERA_OPEN_FAILED) {
                    WebRTCMediaSession.this.metricsSession.recordCount("RingServiceCameraAccessDenied", cameraErrorCode.toString(), 1.0d, WebRTCMediaSession.this.mCallId);
                } else if (cameraErrorCode2 == CameraErrorCode.CAMERA_NOT_FOUND) {
                    WebRTCMediaSession.this.metricsSession.recordCount("RingServiceCameraNotFoundAtRuntime", cameraErrorCode.toString(), 1.0d, WebRTCMediaSession.this.mCallId);
                } else {
                    WebRTCMediaSession.this.metricsSession.recordCount("RingServiceCameraError", cameraErrorCode.toString(), 1.0d, WebRTCMediaSession.this.mCallId);
                }
                if (cameraErrorCode == CameraErrorCode.CAMERA_DISCONNECTED) {
                    WebRTCMediaSession.this.setLocalVideoState(false, MediaStateChangeTrigger.CAMERA_DISCONNECTED);
                } else {
                    WebRTCMediaSession.this.setLocalVideoState(false, MediaStateChangeTrigger.CAMERA_ERROR);
                }
            }
        });
    }

    @Override // com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PeerConnectionEvents
    public void onCameraOpening(final String str, final boolean z) {
        this.usingFrontFacingCamera = z;
        this.lastOpenedCameraName = str;
        if (this.mMediaSessionListener == null) {
            log.d("onCameraInUseUpdate, mediaSessionListener not attached yet");
        } else {
            this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.17
                @Override // java.lang.Runnable
                public void run() {
                    WebRTCMediaSession.this.mMediaSessionListener.onCameraInUseUpdate(z);
                    WebRTCMediaSession.this.mMediaSessionListener.onCameraOpening(str);
                }
            });
        }
    }

    @Override // com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PeerConnectionEvents
    public void onCameraQualityMetrics(final String str) {
        this.statsExecutor.execute(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.19
            @Override // java.lang.Runnable
            public void run() {
                if (WebRTCMediaSession.this.metricsSession == null || str == null || WebRTCMediaSession.this.mCallId == null) {
                    return;
                }
                CameraMetricsReporter.recordCameraQualityMetricsEvent(WebRTCMediaSession.this.metricsSession, str, WebRTCMediaSession.this.mCallId);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PeerConnectionEvents
    public void onDataChannelEvent(final DataChannelEvent dataChannelEvent) {
        this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.18
            @Override // java.lang.Runnable
            public void run() {
                String obj = dataChannelEvent.getParams().get("label").toString();
                DataChannelEvent.Type eventType = dataChannelEvent.getEventType();
                if (obj.equals(WebRTCMediaSession.this.realTimeTextLabel)) {
                    if (WebRTCMediaSession.this.realTimeTextDataChannelListener == null) {
                        return;
                    }
                    int ordinal = eventType.ordinal();
                    if (ordinal == 0) {
                        WebRTCMediaSession.this.realTimeTextDataChannelListener.onRealTimeTextReceivedOnDataChannel(((DataChannelDTO) dataChannelEvent.getParams().get("data")).getData());
                    } else if (ordinal != 1) {
                        WebRTCMediaSession.log.w("onDataChannelEvent: real time text unknown event type");
                    } else {
                        String obj2 = dataChannelEvent.getParams().get("state").toString();
                        CommsLogger commsLogger = WebRTCMediaSession.log;
                        commsLogger.d("RealTimeText state for label " + obj + " is " + obj2);
                        if (!obj2.equals("OPEN")) {
                            return;
                        }
                        if (WebRTCMediaSession.this.mPeerConnectionClient == null) {
                            WebRTCMediaSession.log.w("onDataChannelEvent: PeerConnectionClient doesn't exist");
                        } else {
                            WebRTCMediaSession.this.mPeerConnectionClient.sendQueuedDataChannelMessages(obj);
                        }
                    }
                } else if (WebRTCMediaSession.this.dataChannelListener == null) {
                } else {
                    int ordinal2 = eventType.ordinal();
                    if (ordinal2 == 0) {
                        WebRTCMediaSession.this.dataChannelListener.onMessageReceived(dataChannelEvent);
                    } else if (ordinal2 != 1) {
                        if (ordinal2 != 2) {
                            WebRTCMediaSession.log.w("onDataChannelEvent: unknown event type");
                        } else {
                            WebRTCMediaSession.this.dataChannelListener.onBufferedAmountChange(dataChannelEvent);
                        }
                    } else {
                        WebRTCMediaSession.this.dataChannelListener.onStateChange(dataChannelEvent);
                        String obj3 = dataChannelEvent.getParams().get("state").toString();
                        CommsLogger commsLogger2 = WebRTCMediaSession.log;
                        commsLogger2.d("DataChannel state for label " + obj + " is " + obj3);
                        if (!obj3.equals("OPEN")) {
                            return;
                        }
                        if (WebRTCMediaSession.this.mPeerConnectionClient == null) {
                            WebRTCMediaSession.log.w("onDataChannelEvent: PeerConnectionClient doesn't exist");
                        } else {
                            WebRTCMediaSession.this.mPeerConnectionClient.sendQueuedDataChannelMessages(obj);
                        }
                    }
                }
            }
        });
    }

    @Override // com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PeerConnectionEvents
    public void onDataChannelsCreated(final List<DataChannelProperties> list) {
        if (list.isEmpty()) {
            log.w("activeDataChannels cannot be empty.");
        } else {
            this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.20
                @Override // java.lang.Runnable
                public void run() {
                    WebRTCMediaSession.log.i("onDataChannelsCreated");
                    WebRTCMediaSession.this.mActiveDataChannels = list;
                    WebRTCMediaSession webRTCMediaSession = WebRTCMediaSession.this;
                    webRTCMediaSession.filterActiveDataChannels(Call.Side.Local, webRTCMediaSession.mActiveDataChannels);
                }
            });
        }
    }

    @Override // com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PeerConnectionEvents
    public void onDtmfInserted(final boolean z, final String str, final int i, final int i2) {
        this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.16
            @Override // java.lang.Runnable
            public void run() {
                WebRTCMediaSession.log.i("Insert Dtmf Done");
                WebRTCMediaSession.this.mMediaSessionListener.onDtmfInserted(z, str, i, i2);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PeerConnectionEvents
    public void onIceCandidate(final IceCandidate iceCandidate) {
        Preconditions.checkArgument(iceCandidate != null, "IceCandidate must be non-null.");
        this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.4
            @Override // java.lang.Runnable
            public void run() {
                WebRTCMediaSession.log.i(String.format(Locale.US, "onIceCandidate: sdpMLineIndex = %s, sdpMid = %s", Integer.valueOf(iceCandidate.sdpMLineIndex), iceCandidate.sdpMid));
                if (!WebRTCMediaSession.this.mPeerConnectionParameters.isTrickleIceEnabled()) {
                    return;
                }
                if (WebRTCMediaSession.this.mPseudoSdpForIceTrickling == null) {
                    WebRTCMediaSession.log.e("onIceCandidate: Pseudo sdp is null");
                    return;
                }
                WebRTCMediaSession.this.mPseudoSdpForIceTrickling.addIceCandidate(iceCandidate);
                WebRTCMediaSession.this.sendTrickleIceMessage();
            }
        });
    }

    @Override // com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PeerConnectionEvents
    public void onIceCandidatesRemoved(IceCandidate[] iceCandidateArr) {
        Preconditions.checkArgument(iceCandidateArr.length > 0, "IceCandidate must be non-null.");
        this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.5
            @Override // java.lang.Runnable
            public void run() {
                WebRTCMediaSession.log.d("Removing SOME Ice. (NO-OP)");
            }
        });
    }

    @Override // com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PeerConnectionEvents
    public void onIceConnectionReceivingChange(final boolean z) {
        this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.6
            @Override // java.lang.Runnable
            public void run() {
                WebRTCMediaSession.this.processOnIceConnectionReceivingChange(z);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PeerConnectionEvents
    public void onIceConnectionStateChange(final PeerConnection.IceConnectionState iceConnectionState) {
        this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.7
            @Override // java.lang.Runnable
            public void run() {
                WebRTCMediaSession.this.processIceConnectionStateChange(iceConnectionState);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PeerConnectionEvents
    public void onIceGatheringDone(final SessionDescription sessionDescription) {
        this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.8
            @Override // java.lang.Runnable
            public void run() {
                if (WebRTCMediaSession.this.mPeerConnectionParameters.isTrickleIceEnabled()) {
                    WebRTCMediaSession.log.i("onIceGatheringDone: sending end-of-candidates indication");
                    WebRTCMediaSession.this.sendEndOfCandidatesIndication();
                    WebRTCMediaSession.this.restartingIce = false;
                    return;
                }
                CommsLogger commsLogger = WebRTCMediaSession.log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onIceGatheringDone callEstablished - ");
                outline107.append(WebRTCMediaSession.this.callEstablished);
                outline107.append("restartingIce - ");
                GeneratedOutlineSupport1.outline184(outline107, WebRTCMediaSession.this.restartingIce, commsLogger);
                if (WebRTCMediaSession.this.callEstablished && !WebRTCMediaSession.this.restartingIce) {
                    return;
                }
                WebRTCMediaSession.this.restartingIce = false;
                try {
                    Sdp sdp = new Sdp(sessionDescription.description);
                    sdp.setVideoConstraintParameters(WebRTCMediaSession.this.getVideoConstraintsToRequestFromRemote());
                    if (WebRTCMediaSession.this.forceReceiveOnlyVideo) {
                        sdp.setMediaCapability("video", "recvonly");
                    } else {
                        sdp.setMediaCapability("video", "sendrecv");
                    }
                    sdp.setScreenParameters(WebRTCMediaSession.this.mPeerConnectionParameters.getScreenSize(), WebRTCMediaSession.this.mPeerConnectionParameters.getScreenShape());
                    if (WebRTCMediaSession.this.mPeerConnectionParameters.getBundlePolicy() == null || WebRTCMediaSession.this.mPeerConnectionParameters.getBundlePolicy() != BundlePolicy.MAXCOMPAT) {
                        sdp.matchMediaPortsIfBundled();
                    }
                    if (!WebRTCMediaSession.this.mActiveDataChannels.isEmpty()) {
                        sdp.setDataChannelMapParameters(WebRTCMediaSession.this.mActiveDataChannels);
                    }
                    WebRTCMediaSession.this.appendVideoConstraints(sdp);
                    if (WebRTCMediaSession.log.isLoggable(LogLevel.Debug)) {
                        CommsLogger commsLogger2 = WebRTCMediaSession.log;
                        commsLogger2.ds("onIceGatheringDone: incoming= " + sessionDescription.type + " sdp= " + sdp.toString());
                    }
                    WebRTCMediaSession.log.d("onIceGatheringDone: calling mMediaSessionListener.onFirstSdpAvailable");
                    WebRTCMediaSession.this.mMediaSessionListener.onFirstSdpAvailable(new MediaSessionListener.SdpParams(sdp, sessionDescription.type == SessionDescription.Type.OFFER ? Sdp.Type.OFFER : Sdp.Type.ANSWER));
                } catch (Exception e) {
                    MediaSessionListener mediaSessionListener = WebRTCMediaSession.this.mMediaSessionListener;
                    ErrorCode errorCode = ErrorCode.Unknown;
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("ERROR sending SDP ");
                    outline1072.append(sessionDescription.type);
                    outline1072.append(" from WebRTC to DeviceCallingService: ");
                    outline1072.append(e.getMessage());
                    mediaSessionListener.onError(errorCode, outline1072.toString());
                }
            }
        });
    }

    @Override // com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PeerConnectionEvents
    public void onLocalDescription(final SessionDescription sessionDescription) {
        Preconditions.checkArgument(sessionDescription != null, "SessionDescription must be non-null.");
        this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.3
            @Override // java.lang.Runnable
            public void run() {
                WebRTCMediaSession.log.i(String.format("onLocalDescription SDP %s, callEstablished = %s, restartingIce = %s, trickleIceEnabled = %s", sessionDescription.type, Boolean.valueOf(WebRTCMediaSession.this.callEstablished), Boolean.valueOf(WebRTCMediaSession.this.restartingIce), Boolean.valueOf(WebRTCMediaSession.this.mPeerConnectionParameters.isTrickleIceEnabled())));
                Sdp sdp = new Sdp(sessionDescription.description);
                if (!WebRTCMediaSession.this.callEstablished && WebRTCMediaSession.this.avsDeviceFacade != null) {
                    sdp.setScreenOrientation(WebRTCMediaSession.this.mPeerConnectionParameters.getScreenSize(), WebRTCMediaSession.this.avsDeviceFacade.getScreenRotation());
                }
                sdp.setVideoConstraintParameters(WebRTCMediaSession.this.getVideoConstraintsToRequestFromRemote());
                if (WebRTCMediaSession.this.forceReceiveOnlyVideo) {
                    sdp.setMediaCapability("video", "recvonly");
                } else {
                    sdp.setMediaCapability("video", "sendrecv");
                }
                sdp.setScreenParameters(WebRTCMediaSession.this.mPeerConnectionParameters.getScreenSize(), WebRTCMediaSession.this.mPeerConnectionParameters.getScreenShape());
                sdp.setVideoSuspendedFlags(WebRTCMediaSession.this.localVideoSuspended, WebRTCMediaSession.this.suspendedTrigger != null ? WebRTCMediaSession.this.suspendedTrigger : MediaStateChangeTrigger.REMOTE_USER_REQUEST);
                if (WebRTCMediaSession.this.mPeerConnectionParameters.getBundlePolicy() == null || WebRTCMediaSession.this.mPeerConnectionParameters.getBundlePolicy() != BundlePolicy.MAXCOMPAT) {
                    sdp.matchMediaPortsIfBundled();
                }
                if (!WebRTCMediaSession.this.mActiveDataChannels.isEmpty()) {
                    sdp.setDataChannelMapParameters(WebRTCMediaSession.this.mActiveDataChannels);
                }
                WebRTCMediaSession.this.appendVideoConstraints(sdp);
                if (WebRTCMediaSession.this.mPeerConnectionParameters.isTrickleIceEnabled()) {
                    if (WebRTCMediaSession.this.mPseudoSdpForIceTrickling == null) {
                        WebRTCMediaSession.log.i("onLocalDescription: creating pseudo sdp");
                        WebRTCMediaSession.this.mPseudoSdpForIceTrickling = sdp.getPseudoSdpForIceTrickling();
                    }
                    if (!WebRTCMediaSession.this.callEstablished || WebRTCMediaSession.this.restartingIce) {
                        WebRTCMediaSession.this.mMediaSessionListener.onFirstSdpAvailable(new MediaSessionListener.SdpParams(sdp, sessionDescription.type == SessionDescription.Type.OFFER ? Sdp.Type.OFFER : Sdp.Type.ANSWER));
                    } else {
                        WebRTCMediaSession.this.mMediaSessionListener.onSdpUpdate(new MediaSessionListener.SdpParams(sdp, sessionDescription.type == SessionDescription.Type.OFFER ? Sdp.Type.OFFER : Sdp.Type.ANSWER));
                    }
                } else if (!WebRTCMediaSession.this.callEstablished || WebRTCMediaSession.this.restartingIce) {
                } else {
                    WebRTCMediaSession.this.mMediaSessionListener.onSdpUpdate(new MediaSessionListener.SdpParams(sdp, sessionDescription.type == SessionDescription.Type.OFFER ? Sdp.Type.OFFER : Sdp.Type.ANSWER));
                }
            }
        });
    }

    @Override // com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PeerConnectionEvents
    public void onMediaStateChanged(final Call.Side side, final boolean z, final boolean z2, final MediaStateChangeTrigger mediaStateChangeTrigger) {
        Runnable runnable = new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.13
            @Override // java.lang.Runnable
            public void run() {
                WebRTCMediaSession.log.d(String.format(Locale.US, "Media State Changed. Side: %s, audio enabled = %s, video enabled = %s, systemEnabled = %s, systemCameraEnabled = %s trigger = %s", side.name(), Boolean.valueOf(z), Boolean.valueOf(z2), Boolean.valueOf(WebRTCMediaSession.this.systemMediaEnabled), Boolean.valueOf(WebRTCMediaSession.this.systemCameraEnabled), mediaStateChangeTrigger.name()));
                if (Call.Side.Local == side) {
                    if (WebRTCMediaSession.this.localAudioEnabled != z || WebRTCMediaSession.this.localVideoEnabled != z2) {
                        boolean z3 = z2 != WebRTCMediaSession.this.localVideoEnabled;
                        WebRTCMediaSession.this.localAudioEnabled = z;
                        WebRTCMediaSession.this.localVideoEnabled = z2;
                        WebRTCMediaSession webRTCMediaSession = WebRTCMediaSession.this;
                        webRTCMediaSession.recordWebRTCTrace(webRTCMediaSession.createMediaStatusJson(Call.Side.Local, webRTCMediaSession.getMediaStatus(), mediaStateChangeTrigger));
                        if (WebRTCMediaSession.this.mMediaSessionListener == null) {
                            WebRTCMediaSession.log.d("media session listener is null, will not update media status");
                            return;
                        }
                        WebRTCMediaSession.log.i(String.format("Reporting Local Media State Changed Audio = %s Video = %s", Boolean.valueOf(z), Boolean.valueOf(z2)));
                        WebRTCMediaSession.this.mMediaSessionListener.onMediaStatusUpdated(Call.Side.Local, WebRTCMediaSession.this.getMediaStatus(), mediaStateChangeTrigger);
                        if (!z3 || !WebRTCMediaSession.this.localVideoEnabled) {
                            WebRTCMediaSession.log.d("skipping video renabled callback. Nothing changed");
                            return;
                        } else {
                            WebRTCMediaSession.this.mMediaSessionListener.onVideoReEnabled(Call.Side.Local);
                            return;
                        }
                    }
                    WebRTCMediaSession.log.d("No change in a/v flags. Not updating anything");
                    return;
                }
                MediaStateChangeTrigger mediaStateChangeTrigger2 = WebRTCMediaSession.this.remoteSuspendedTriggerRequest != null ? WebRTCMediaSession.this.remoteSuspendedTriggerRequest : mediaStateChangeTrigger;
                WebRTCMediaSession.log.i(String.format("Reporting Remote Media State Changed Audio = %s Video = %s Trigger = %s", Boolean.valueOf(z), Boolean.valueOf(z2), mediaStateChangeTrigger2.name()));
                WebRTCMediaSession.this.setRemoteMediaState(z, z2, mediaStateChangeTrigger2);
            }
        };
        if (Looper.myLooper() != this.mOrchestratorHandler.getLooper()) {
            this.mOrchestratorHandler.post(runnable);
        } else {
            runnable.run();
        }
    }

    @Override // com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PeerConnectionEvents
    public void onPeerConnectionClosed() {
        this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.10
            @Override // java.lang.Runnable
            public void run() {
                if (WebRTCMediaSession.this.mEventTracer != null) {
                    WebRTCMediaSession.this.mEventTracer.mark(EventTracerConfig.Event.Call_MediaStop);
                } else {
                    WebRTCMediaSession.log.i("PeerConnectionClient created for warmup is closed");
                }
            }
        });
    }

    @Override // com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PeerConnectionEvents
    public void onPeerConnectionError(final String str) {
        this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.12
            @Override // java.lang.Runnable
            public void run() {
                WebRTCMediaSession.this.signalingState = SignalingState.STABLE;
                if (WebRTCMediaSession.this.mMediaSessionListener != null) {
                    WebRTCMediaSession.this.mMediaSessionListener.onError(ErrorCode.Unknown, str);
                } else if (WebRTCMediaSession.this.mWarmupListener == null) {
                } else {
                    WebRTCMediaSession.this.mWarmupListener.onError(str);
                }
            }
        });
    }

    @Override // com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PeerConnectionEvents
    public void onPeerConnectionStatsReady(final StatsReport[] statsReportArr) {
        this.statsExecutor.execute(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.11
            @Override // java.lang.Runnable
            public void run() {
                final MediaStats recordPeerConnectionStats = CallQualityStats.getInstance().recordPeerConnectionStats(WebRTCMediaSession.this.metricsSession, statsReportArr, WebRTCMediaSession.this.mCallId, WebRTCMediaSession.this.needsToReportActiveConnections, WebRTCMediaSession.this.shouldLogThisStatsEvent());
                WebRTCMediaSession.this.needsToReportActiveConnections = false;
                WebRTCMediaSession.this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.11.1
                    @Override // java.lang.Runnable
                    public void run() {
                        WebRTCMediaSession.log.d("Media Stats updated");
                        WebRTCMediaSession.this.mMediaSessionListener.onMediaStatsUpdated(recordPeerConnectionStats);
                    }
                });
            }
        });
    }

    @Override // com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PeerConnectionEvents
    public void onSignalingDone(final boolean z) {
        this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.15
            @Override // java.lang.Runnable
            public void run() {
                WebRTCMediaSession.log.i("WebRTC Signaling Done");
                WebRTCMediaSession.this.signalingState = SignalingState.WEBRTC_STABLE;
                WebRTCMediaSession.this.dtmfInsertable = z;
                if (WebRTCMediaSession.this.callEstablished) {
                    WebRTCMediaSession.this.handleCallSignalingDone();
                }
            }
        });
    }

    @Override // com.amazon.comms.ringservice.webrtc.VideoEffectController.VideoEffectChangeListener
    public void onVideoEffectChanged(VideoEffectController videoEffectController) {
        final Call.VideoEffect videoEffect = videoEffectController.getVideoEffect();
        this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.21
            @Override // java.lang.Runnable
            public void run() {
                WebRTCMediaSession.this.mMediaSessionListener.onVideoEffectChanged(videoEffect);
                if (WebRTCMediaSession.this.iceConnectionStateConnectedAtLeastOnce) {
                    if (videoEffect == Call.VideoEffect.Frosted) {
                        WebRTCMediaSession.this.setFrostQualityMode(true);
                    } else {
                        WebRTCMediaSession.this.setFrostQualityMode(false);
                    }
                }
            }
        });
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void postCallEstablished() {
        log.i("Call Established");
        this.callEstablished = true;
        if (this.signalingState == SignalingState.WEBRTC_STABLE) {
            handleCallSignalingDone();
        }
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void processAcceptParams(boolean z) {
        if (this.mPeerConnectionClient == null) {
            log.w("Received accept request for a non-initialized peer connection.");
            return;
        }
        setLocalVideoState(z, MediaStateChangeTrigger.USER_REQUEST);
        this.mPeerConnectionClient.setAnswer();
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public boolean processRemoteDescription(Sdp sdp, Sdp.Type type) {
        Preconditions.checkArgument(sdp != null, "Sdp must be non-null.");
        sdp.replaceRTPProfile("SAVPF");
        String str = null;
        String mid = sdp.isMediaPresent("audio") ? sdp.getMid("audio") : null;
        if (sdp.isMediaPresent("video")) {
            str = sdp.getMid("video");
        }
        if (!this.localVideoCapable) {
            sdp.markMediaDeleted("video");
        }
        if (this.callEstablished) {
            this.remoteAudioCapable |= sdp.isMediaPresent("audio");
            this.remoteVideoCapable |= sdp.isMediaPresent("video") && (sdp.isMediaSendCapable("video") || !this.forceReceiveOnlyVideo);
        } else {
            this.remoteAudioCapable = sdp.isMediaPresent("audio");
            this.remoteVideoCapable = sdp.isMediaPresent("video") && (sdp.isMediaSendCapable("video") || !this.forceReceiveOnlyVideo);
        }
        log.i(String.format("Remote media capability: audio=%s, video=%s", Boolean.valueOf(this.remoteAudioCapable), Boolean.valueOf(this.remoteVideoCapable)));
        Set<String> activeMediaTypes = sdp.getActiveMediaTypes();
        this.remoteAudioRequested = this.remoteAudioCapable && activeMediaTypes.contains("audio");
        this.remoteVideoRequested = this.remoteVideoCapable && activeMediaTypes.contains("video");
        log.i(String.format("Remote media request: audio=%s, video=%s", Boolean.valueOf(this.remoteAudioRequested), Boolean.valueOf(this.remoteVideoRequested)));
        this.remoteVideoH264Disabled = !sdp.isCodecPresent("H264");
        if (activeDataChannelExistsInSDP(sdp)) {
            filterActiveDataChannels(Call.Side.Remote, this.mActiveDataChannels);
        } else {
            updateRealTimeTextStatus(Call.Side.Remote, false, false, MediaStateChangeTrigger.REMOTE_USER_REQUEST);
        }
        if (this.mPeerConnectionClient == null) {
            this.mMediaSessionListener.onError(ErrorCode.Unknown, "Received remote SDP for non-initialized peer connection.");
            return false;
        } else if (this.signalingState == SignalingState.OFFERING && type == Sdp.Type.OFFER) {
            if (!this.wasLastOfferer) {
                return false;
            }
            this.pendingRemoteOffer = sdp;
            log.i("Queue remote offer");
            return true;
        } else {
            if (mid != null) {
                this.cachedAudioMid = mid;
                GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport1.outline107("Caching audio mid:"), this.cachedAudioMid, log);
            } else {
                String str2 = this.cachedAudioMid;
                if (str2 != null) {
                    sdp.appendMidIfSessionDeleted("audio", str2);
                }
            }
            if (str != null) {
                this.cachedVideoMid = str;
                GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport1.outline107("Caching video mid:"), this.cachedVideoMid, log);
            } else {
                String str3 = this.cachedVideoMid;
                if (str3 != null) {
                    sdp.appendMidIfSessionDeleted("video", str3);
                }
            }
            this.mPeerConnectionClient.setRemoteMediaCapability(this.remoteAudioCapable, this.remoteVideoCapable);
            if (type == Sdp.Type.OFFER) {
                if (log.isLoggable(LogLevel.Debug)) {
                    CommsLogger commsLogger = log;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Process remote offer: ");
                    outline107.append(sdp.toString());
                    commsLogger.ds(outline107.toString());
                }
                receiveOffer(sdp);
            } else {
                if (log.isLoggable(LogLevel.Debug)) {
                    CommsLogger commsLogger2 = log;
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Process remote answer: ");
                    outline1072.append(sdp.toString());
                    commsLogger2.ds(outline1072.toString());
                }
                receiveAnswer(sdp, type);
            }
            return true;
        }
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void refreshSession() {
        if (this.callEstablished && this.signalingState == SignalingState.STABLE) {
            log.i("Creating offer for session refresh");
            createOffer();
            return;
        }
        log.i("Queueing offer for session refresh");
        this.createOfferPending = true;
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void resetRemoteDescription(boolean z, boolean z2) {
        CommsLogger commsLogger = log;
        commsLogger.i("ResetRemoteDescription:" + z);
        this.createOfferPending = z;
        if (!this.wasLastOfferer && z2) {
            log.i("Previous answerer received 491, set waiting for update to be true");
            this.waitingForRemoteUpdate = true;
        }
        this.mPeerConnectionClient.resetRemoteDescription();
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void restartIce(Sdp sdp, boolean z) {
        if (this.mPeerConnectionClient == null) {
            log.i("restartIce: PeerConnectionClient doesn't exit.");
        } else if (this.signalingState != SignalingState.STABLE) {
            CommsLogger commsLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Queueing restartIce request w/ offer: ");
            outline107.append(sdp != null);
            outline107.append(" w/ resetRemoteDescriptionIfNeeded: ");
            outline107.append(z);
            commsLogger.i(outline107.toString());
            if (this.signalingState == SignalingState.OFFERING && z) {
                resetRemoteDescription(false, false);
            }
            this.restartIcePending = true;
            this.pendingRemoteOffer = sdp;
            this.createOfferPending = false;
        } else {
            GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport1.outline107("WebRTC restart ICE w/ offer : "), sdp != null, log);
            if (this.mPeerConnectionParameters.isTrickleIceEnabled()) {
                this.mPseudoSdpForIceTrickling = null;
            }
            this.restartingIce = true;
            if (sdp == null) {
                log.i("restartIce: signaling OFFERING");
                this.signalingState = SignalingState.OFFERING;
                this.mPeerConnectionClient.restartIce(this.mIceServers, true);
                this.mEventTracer.mark(EventTracerConfig.Event.reconnect_initiator_ice_restarted);
                return;
            }
            this.mPeerConnectionClient.restartIce(this.mIceServers, false);
            this.mEventTracer.mark(EventTracerConfig.Event.reconnect_receiver_ice_restarted);
            this.pendingRemoteCandidateList = sdp.getIceCandidates();
            sdp.stripIceCandidates();
            processRemoteDescription(sdp, Sdp.Type.OFFER);
        }
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void retryMedia() {
        PeerConnectionClient peerConnectionClient = this.mPeerConnectionClient;
        if (peerConnectionClient == null) {
            log.w("retryMedia PeerConnectionClient doesn't exist");
        } else {
            peerConnectionClient.retryMedia();
        }
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void sendData(@NonNull DataChannelDTO dataChannelDTO) {
        if (dataChannelDTO != null) {
            PeerConnectionClient peerConnectionClient = this.mPeerConnectionClient;
            if (peerConnectionClient == null) {
                log.w("sendData PeerConnectionClient doesn't exist");
                return;
            } else {
                peerConnectionClient.dataChannelSendData(dataChannelDTO);
                return;
            }
        }
        throw new IllegalArgumentException("dataObject is null");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setCallProvider(String str) {
        this.callProvider = str;
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void setLocalAudioState(boolean z, MediaStateChangeTrigger mediaStateChangeTrigger) {
        if (this.mPeerConnectionClient == null) {
            log.i("setLocalAudioState: PeerConnectionClient doesn't exist");
            return;
        }
        log.i(String.format("Attempting to Set Local Audio Request State To Audio %s", Boolean.valueOf(z)));
        this.localAudioRequest = z;
        updateLocalMediaState(false, mediaStateChangeTrigger);
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void setLocalVideoPermitted(boolean z, MediaStateChangeTrigger mediaStateChangeTrigger) {
        if (this.mPeerConnectionClient == null) {
            log.i("setLocalVideoPermitted: PeerConnectionClient doesn't exist");
            return;
        }
        CommsLogger commsLogger = log;
        commsLogger.i("setLocalVideoPermitted  videoPermitted = " + z);
        this.localVideoPermitted = z;
        if (isVideoChannelSuspended() && this.localVideoPermitted) {
            CommsLogger commsLogger2 = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("setLocalVideoPermitted: ");
            outline107.append(this.localVideoPermitted);
            outline107.append(" is ignored because video track has been suspended");
            commsLogger2.i(outline107.toString());
            return;
        }
        updateLocalMediaState(false, mediaStateChangeTrigger);
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void setLocalVideoState(boolean z, MediaStateChangeTrigger mediaStateChangeTrigger) {
        PeerConnectionClient peerConnectionClient = this.mPeerConnectionClient;
        if (peerConnectionClient == null) {
            log.i("setLocalMediaState: PeerConnectionClient doesn't exist");
        } else if (peerConnectionClient != null && isVideoChannelSuspended() && z) {
            CommsLogger commsLogger = log;
            commsLogger.i("setLocalVideoState: " + z + " ignored because video track has been suspended");
        } else {
            log.i(String.format("Attempting to Set Local Video Request State To %s", Boolean.valueOf(z)));
            this.localVideoRequest = z;
            updateLocalMediaState(false, mediaStateChangeTrigger);
        }
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void setMediaConstraints(Map<String, Integer> map) {
        PeerConnectionClient peerConnectionClient;
        this.thermalMitigationDetails.recordThermalMitigation(map);
        HashMap hashMap = new HashMap();
        boolean z = false;
        boolean z2 = false;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getKey().equals(SUSPEND_VIDEO_THERMAL)) {
                boolean z3 = true;
                z = entry.getValue().intValue() == 0 || entry.getValue().intValue() == 1;
                if (z) {
                    if (entry.getValue().intValue() != 1) {
                        z3 = false;
                    }
                    z2 = z3;
                }
            } else {
                hashMap.put(entry.getKey(), entry.getValue());
            }
        }
        if (!map.isEmpty() && (peerConnectionClient = this.mPeerConnectionClient) != null) {
            peerConnectionClient.setMediaConstraints(hashMap);
        }
        if (z) {
            suspendVideo(z2, MediaStateChangeTrigger.THERMAL_EVENT);
        }
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void setSystemCameraState(boolean z) {
        CommsLogger commsLogger = log;
        commsLogger.i("Setting camera state. State = " + z);
        if (this.systemCameraEnabled == z) {
            this.mMediaSessionListener.onMediaStatusUpdated(Call.Side.Local, getMediaStatus(), MediaStateChangeTrigger.USER_REQUEST);
            recordWebRTCTrace(createMediaStatusJson(Call.Side.Local, getMediaStatus(), MediaStateChangeTrigger.USER_REQUEST));
            return;
        }
        this.systemCameraEnabled = z;
        updateLocalMediaState(false, MediaStateChangeTrigger.USER_REQUEST);
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void setSystemMediaState(boolean z) {
        CommsLogger commsLogger = log;
        commsLogger.i("Setting System Media State = " + z);
        if (this.systemMediaEnabled == z) {
            this.mMediaSessionListener.onMediaStatusUpdated(Call.Side.Local, getMediaStatus(), MediaStateChangeTrigger.USER_REQUEST);
            recordWebRTCTrace(createMediaStatusJson(Call.Side.Local, getMediaStatus(), MediaStateChangeTrigger.USER_REQUEST));
            return;
        }
        this.systemMediaEnabled = z;
        updateLocalMediaState(false, MediaStateChangeTrigger.USER_REQUEST);
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void setVideoEffect(Call.VideoEffect videoEffect, double d) {
        if (videoEffect != getVideoEffect()) {
            if (videoEffect == Call.VideoEffect.None) {
                VideoEffectController videoEffectController = this.mVideoEffectController;
                if (videoEffectController == null) {
                    return;
                }
                videoEffectController.stop(d);
                return;
            }
            if (this.mVideoEffectController == null) {
                this.mVideoEffectController = VideoEffectControllerFactory.getVideoEffectController(videoEffect, this.mPeerConnectionClient, this);
            }
            this.mVideoEffectController.start(d);
        }
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void setVolume(float f) {
        PeerConnectionClient peerConnectionClient = this.mPeerConnectionClient;
        if (peerConnectionClient != null) {
            peerConnectionClient.setVolume(f);
        }
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void startIceTrickling() {
        log.i("startIceTrickling");
        if (!this.mPeerConnectionParameters.isTrickleIceEnabled()) {
            log.w("startIceTrickling: trickle ice feature is not enabled");
            return;
        }
        this.mIceTricklingAllowed = true;
        sendTrickleIceMessage();
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void startMedia(Sdp sdp, VideoRenderer.Callbacks callbacks, VideoRenderer.Callbacks callbacks2) {
        log.i("startMedia");
        if (this.mPeerConnectionClient == null) {
            preparePeerConnection(false);
        }
        configurePlatformVoIPSelection();
        configureDynamicAcousticParams();
        startMediaInternal(sdp);
        this.mPeerConnectionClient.attachVideoRenderers(callbacks, callbacks2);
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void stopIceTrickling() {
        log.i("stopIceTrickling");
        if (!this.mPeerConnectionParameters.isTrickleIceEnabled()) {
            log.w("stopIceTrickling: trickle ice feature is not enabled");
        } else {
            this.mIceTricklingAllowed = false;
        }
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void stopMedia() {
        log.i("Stopping Media");
        VideoEffectController videoEffectController = this.mVideoEffectController;
        if (videoEffectController != null) {
            videoEffectController.shutdown();
            this.mVideoEffectController = null;
        }
        PeerConnectionClient peerConnectionClient = this.mPeerConnectionClient;
        if (peerConnectionClient != null) {
            peerConnectionClient.close();
            this.mPeerConnectionClient = null;
        }
        clearPlatformVoIPSelection();
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void switchCamera() {
        this.mPeerConnectionClient.switchCamera(this.cameraSwitchHandler);
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void warmupMedia() {
        log.i("warmupMedia");
        ClocksImpl clocksImpl = new ClocksImpl();
        this.warmupStartedTimestampERT = clocksImpl.getElapsedRealtime();
        this.warmupStartedTimestampEpoch = clocksImpl.getCurrentTimeMillis();
        preparePeerConnection(true);
        this.mPeerConnectionClient.warmupMedia();
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void switchCamera(String str) {
        this.mPeerConnectionClient.switchCamera(this.cameraSwitchHandler, str);
    }

    @Override // com.amazon.comms.ringservice.MediaSession
    public void addRemoteIceCandidates(Sdp sdp) {
        log.i("addRemoteIceCandidates: adding trickled candidates to the session");
        List<IceCandidate> newRemoteCandidates = getNewRemoteCandidates(sdp.getIceCandidates());
        log.i(String.format(Locale.US, "addRemoteIceCandidates: found new candidates, size - %s", Integer.valueOf(newRemoteCandidates.size())));
        addRemoteIceCandidatesInternal(newRemoteCandidates);
        if (sdp.isAttributePresent(Sdp.ATTRIBUTE_END_OF_CANDIDATES)) {
            log.i("addRemoteIceCandidates: received end-of-candidates");
            this.mTrickledRemoteCandidates.clear();
            return;
        }
        for (IceCandidate iceCandidate : newRemoteCandidates) {
            this.mTrickledRemoteCandidates.add(iceCandidate);
        }
    }

    public WebRTCMediaSession(Context context, Handler handler, MetricsSession metricsSession, PeerConnectionClient.PeerConnectionParameters peerConnectionParameters, MediaParams mediaParams, boolean z, boolean z2, WarmupListener warmupListener, LooperExecutor looperExecutor, LooperExecutor looperExecutor2, WebRTCGlobalsProvider webRTCGlobalsProvider, EglBase.Context context2, AvsDeviceFacade avsDeviceFacade) {
        this.mIceServers = new LinkedList<>();
        this.currentIceConnectionState = PeerConnection.IceConnectionState.NEW;
        this.iceConnectionStateConnectedAtLeastOnce = false;
        this.localAudioEnabled = true;
        this.localAudioRequest = true;
        this.remoteAudioEnabled = true;
        this.remoteVideoEnabled = true;
        this.systemCameraEnabled = true;
        this.localVideoSuspended = false;
        this.remoteVideoSuspended = false;
        this.remoteVideoSuspendRequested = false;
        this.restartingIce = false;
        this.videoSuspendedCount = 0;
        this.suspendedTrigger = null;
        this.remoteSuspendedTrigger = null;
        this.remoteSuspendedTriggerRequest = null;
        this.localAudioCapable = true;
        this.remoteAudioCapable = true;
        this.remoteVideoCapable = true;
        this.localRealTimeTextEnabled = false;
        this.remoteRealTimeTextEnabled = false;
        this.realTimeTextRequested = false;
        this.mCallId = "BAD";
        this.signalingState = SignalingState.STABLE;
        this.wasLastOfferer = false;
        this.waitingForRemoteUpdate = false;
        this.remoteVideoH264Disabled = false;
        this.reduceVideoResolutionOnNonH264Available = false;
        this.reducedVideoConstraintsToRequestFromRemote = null;
        this.localVideoPermitted = true;
        this.usingFrontFacingCamera = true;
        this.realTimeTextLabel = null;
        this.mActiveDataChannels = new ArrayList();
        this.lastWebrtcStatsTime = -1L;
        this.mPseudoSdpForIceTrickling = null;
        this.mIceTricklingAllowed = false;
        this.audioManager = null;
        this.mTrickledRemoteCandidates = null;
        this.cameraSwitchHandler = new CameraSwitchHandlerShim() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.1
            @Override // org.webrtc.CameraVideoCapturer.CameraSwitchHandler
            public void onCameraSwitchDone(final boolean z5) {
                WebRTCMediaSession.this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        WebRTCMediaSession.this.mMediaSessionListener.onCameraSwitchDone(z5);
                    }
                });
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraSwitchHandler
            public void onCameraSwitchError(final String str2) {
                WebRTCMediaSession.this.mOrchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaSession.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        WebRTCMediaSession.this.mMediaSessionListener.onCameraSwitchError(str2);
                        CommsLogger commsLogger = WebRTCMediaSession.log;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onCameraSwitchError");
                        outline107.append(str2);
                        commsLogger.e(outline107.toString());
                    }
                });
            }
        };
        Preconditions.checkNotNull(peerConnectionParameters, "PeerConnectionParameters must be non-null.");
        this.pendingRemoteCandidateList = new LinkedList();
        this.renderRemoteVideoSupported = z;
        this.forceReceiveOnlyVideo = z2;
        this.metricsSession = metricsSession;
        this.mOrchestratorHandler = handler;
        this.mApplicationContext = context;
        this.mPeerConnectionParameters = peerConnectionParameters;
        this.localVideoCapable = this.mPeerConnectionParameters.isVideoCapable();
        this.localVideoRequest = this.mPeerConnectionParameters.isVideoRequestEnabled();
        this.systemMediaEnabled = this.mPeerConnectionParameters.isInitialSystemMediaEnabled();
        this.systemCameraEnabled = this.mPeerConnectionParameters.isInitialSystemCameraEnabled();
        this.localDeviceVideoConstraints = new VideoConstraints(this.mPeerConnectionParameters.getVideoWidth(), this.mPeerConnectionParameters.getVideoHeight(), this.mPeerConnectionParameters.getVideoFps());
        this.maxVideoConstraintsToRequestFromRemote = this.mPeerConnectionParameters.getMaxVideoConstraintsToRequestFromRemote();
        this.reducedVideoConstraintsToRequestFromRemote = this.mPeerConnectionParameters.getMaxVideoConstraintsOnReducedResolution();
        this.reduceVideoResolutionOnNonH264Available = this.mPeerConnectionParameters.isReduceVideoResolutionOnNoH264Remote();
        this.thermalMitigationDetails = new ThermalMitigationDetails(this.localDeviceVideoConstraints.getVideoFps(), this.localDeviceVideoConstraints.getVideoWidth());
        updateLocalMediaState(true, MediaStateChangeTrigger.USER_REQUEST);
        this.mWarmupListener = warmupListener;
        this.mMediaParams = mediaParams;
        this.executor = looperExecutor;
        this.statsExecutor = looperExecutor2;
        this.webRTCGlobalsProvider = webRTCGlobalsProvider;
        this.eglContext = context2;
        this.mTrickledRemoteCandidates = new LinkedList();
        this.avsDeviceFacade = avsDeviceFacade;
        log.i("WebRTCMediaSession created");
    }
}
