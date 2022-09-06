package com.amazon.comms.ringservice.webrtc;

import android.content.Context;
import android.os.Handler;
import android.util.Pair;
import com.amazon.comms.calling.instrumentation.EventTracerConfig;
import com.amazon.comms.calling.service.AcousticParams;
import com.amazon.comms.calling.service.BundlePolicy;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.DataChannelConfiguration;
import com.amazon.comms.calling.service.DataChannelDTO;
import com.amazon.comms.calling.service.DataChannelEvent;
import com.amazon.comms.calling.service.DynamicAcousticParams;
import com.amazon.comms.calling.service.MediaStateChangeTrigger;
import com.amazon.comms.calling.service.PlatformVoIPSelection;
import com.amazon.comms.calling.service.RtcpMuxPolicy;
import com.amazon.comms.calling.service.VideoConstraints;
import com.amazon.comms.instrumentation.ClocksImpl;
import com.amazon.comms.instrumentation.EventTracer;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.log.LogLevel;
import com.amazon.comms.ringservice.Sdp;
import com.amazon.comms.ringservice.VideoEffectCommand;
import com.amazon.comms.ringservice.util.SrtpCryptoType;
import com.amazon.comms.ringservice.util.VideoConstraintsManager;
import com.amazon.comms.ringservice.webrtc.utils.SslRole;
import com.amazon.comms.util.LooperExecutor;
import com.amazon.comms.util.Size;
import com.amazon.comms.util.SystemProperty;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.amazon.rtcmedia.webrtc.PeerConnectionFactoryWrapper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Strings;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.NonNull;
import org.webrtc.AudioTrack;
import org.webrtc.CameraErrorCode;
import org.webrtc.CameraMetadataShim;
import org.webrtc.CameraSwitchHandlerShim;
import org.webrtc.DataChannel;
import org.webrtc.EglBase;
import org.webrtc.FollowMeShim;
import org.webrtc.IceCandidate;
import org.webrtc.LocalAudioVideoShim;
import org.webrtc.Logging;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.MediaStreamTrack;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionShim;
import org.webrtc.RtpParameters;
import org.webrtc.RtpSender;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;
import org.webrtc.StatsObserver;
import org.webrtc.StatsReport;
import org.webrtc.VideoEffectShim;
import org.webrtc.VideoRenderer;
import org.webrtc.VideoTrack;
import org.webrtc.WebRTCAudioUtilsShim;
/* loaded from: classes12.dex */
public class PeerConnectionClient implements VideoEffectShim.WebRTCVideoEffectTransitionListener, LocalAudioVideoShim.LocalAudioVideoListener {
    private static final String AMZ_NB_AUDIO_FILTER_CONSTRAINT = "amzNbAudioFilter";
    private static final String AUDIO_AUTO_GAIN_CONTROL_CONSTRAINT = "googAutoGainControl";
    public static final String AUDIO_CODEC_OPUS = "opus";
    private static final String AUDIO_CODEC_PARAM_BITRATE = "maxaveragebitrate";
    private static final String AUDIO_ECHO_CANCELLATION_CONSTRAINT = "googEchoCancellation";
    private static final String AUDIO_HIGH_PASS_FILTER_CONSTRAINT = "googHighpassFilter";
    private static final String AUDIO_NOISE_SUPPRESSION_CONSTRAINT = "googNoiseSuppression";
    public static final String AUDIO_TRACK_ID = "ARDAMSa0";
    private static final String DTLS_SRTP_KEY_AGREEMENT_CONSTRAINT = "DtlsSrtpKeyAgreement";
    private static final String GOOG_DSCP = "googDscp";
    private static final int MAX_QUEUE_SIZE = 50;
    public static final String STREAM_ID = "ARDAMS";
    public static final String T140_PROTOCOL = "t140";
    public static final String VIDEO_CODEC_H264 = "H264";
    private static final String VIDEO_CODEC_PARAM_MAX_BITRATE = "x-google-max-bitrate";
    private static final String VIDEO_CODEC_PARAM_START_BITRATE = "x-google-start-bitrate";
    public static final String VIDEO_CODEC_VP8 = "VP8";
    public static final String VIDEO_TRACK_ID = "ARDAMSv0";
    private static final String WEBRTC_RELAY_ONLY_ICE_PROPERTY_DISABLE = "disable";
    private static final String WEBRTC_RELAY_ONLY_ICE_PROPERTY_ENABLE = "enable";
    private static final String WEBRTC_RELAY_ONLY_ICE_PROPERTY_NAME = "comms_webrtc_relay_only_ice";
    private static final CommsLogger log = CommsLogger.getLogger(PeerConnectionClient.class);
    private Context applicationContext;
    private MediaConstraints audioConstraints;
    private Sdp cachedRemoteOffer;
    private SessionDescription cachedSdp;
    private boolean callVideoCapability;
    private CameraMetadataShim cameraMetadataShim;
    private EglBase.Context eglContext;
    private EventTracer eventTracer;
    private PeerConnectionEvents events;
    private final LooperExecutor executor;
    private boolean followMeEnabled;
    private FollowMeShim followMeShim;
    private boolean initiator;
    private boolean isError;
    private boolean isOfferer;
    private boolean localAudioEnabled;
    private AudioTrack localAudioTrack;
    private LocalAudioVideoShim localAudioVideoShim;
    private MediaStream localMediaStream;
    private VideoRenderer.Callbacks localRender;
    private VideoTrack localVideoTrack;
    private MediaConstraints pcConstraints;
    private PeerConnection peerConnection;
    private PeerConnectionParameters peerConnectionParameters;
    private String preferredVideoCodec;
    private LinkedList<IceCandidate> queuedRemoteCandidates;
    private boolean remoteAudioEnabled;
    private AudioTrack remoteAudioTrack;
    private MediaStream remoteMediaStream;
    private VideoRenderer.Callbacks remoteRender;
    private VideoTrack remoteVideoTrack;
    private boolean renderLocalVideo;
    private boolean renderRemoteVideo;
    private boolean renderRemoteVideoSupported;
    private boolean retryMediaInit;
    private PeerConnectionShim.RTCConfiguration rtcConfig;
    private MediaConstraints sdpMediaConstraints;
    private SignalingParameters signalingParameters;
    private SslRole sslRole;
    private Timer statsTimer;
    private boolean videoCapable;
    private VideoEffectShim videoEffectShim;
    private VideoEffectTransitionListener videoEffectTransitionListener;
    private RtpSender videoRtpSender;
    private boolean warmupClient;
    private long warmupCompletedTimestampERT;
    private long warmupCompletedTimestampEpoch;
    private final WebRTCGlobalsProvider webRTCGlobalsProvider;
    private final PCObserver pcObserver = new PCObserver();
    private final LocalSDPObserver localSdpObserver = new LocalSDPObserver();
    private final RemoteSDPObserver remoteSdpObserver = new RemoteSDPObserver();
    private final VideoConstraintsManager videoConstraintsManager = new VideoConstraintsManager();
    private AtomicInteger remoteSdpSetInProgress = new AtomicInteger(0);
    private PeerConnection.SignalingState signalingState = PeerConnection.SignalingState.STABLE;
    private boolean callAnswered = false;
    private boolean remoteAudioCapable = true;
    private boolean remoteVideoCapable = true;
    private RtpSender audioRtpSender = null;
    private boolean isPendingVideoMaxSendBitrateSet = false;
    private int remoteRequestVideoMaxSendBitrate = Integer.MAX_VALUE;
    private PeerConnection.IceGatheringState currentIceGatheringState = PeerConnection.IceGatheringState.NEW;
    private boolean resetRemoteSignaling = false;
    private boolean noSupportedAudioCodecsPresent = false;
    private final String UNSUPPORTED_CODEC_ERROR = "Unsupported codec";
    private Map<String, DataChannel> dataChannelMap = new HashMap();
    private Map<String, List<DataChannel.Buffer>> queuedMessages = new HashMap();
    private int dataChannelId = 0;
    private boolean isReducedResolutionOnDevice = false;
    private String logTag = log.getTag();
    private final CameraMetadataShim.WebRTCCameraMetadataObserver cameraMetadataObserver = new CameraMetadataShim.WebRTCCameraMetadataObserver() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.1
        @Override // org.webrtc.CameraMetadataShim.WebRTCCameraMetadataObserver
        public void onCameraQualityMetrics(final String str) {
            if (str == null || str.isEmpty()) {
                return;
            }
            PeerConnectionClient.this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), PeerConnectionClient.this.logTag, ":onCameraQualityMetrics"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.1.1
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionClient.this.events == null || PeerConnectionClient.this.isError || PeerConnectionClient.this.peerConnection == null) {
                        return;
                    }
                    PeerConnectionClient.this.events.onCameraQualityMetrics(str);
                }
            });
        }
    };
    private final Runnable setLocalAudioEnabledRunnable = new AudioStateRunnable(true, Call.Side.Local);
    private final Runnable setLocalAudioDisabledRunnable = new AudioStateRunnable(false, Call.Side.Local);
    private final Runnable setRemoteAudioEnabledRunnable = new AudioStateRunnable(true, Call.Side.Remote);
    private final Runnable setRemoteAudioDisabledRunnable = new AudioStateRunnable(false, Call.Side.Remote);
    private final Runnable setLocalVideoEnabledRunnable = new VideoStateRunnable(true, Call.Side.Local);
    private final Runnable setLocalVideoDisabledRunnable = new VideoStateRunnable(false, Call.Side.Local);
    private final Runnable setRemoteVideoEnabledRunnable = new VideoStateRunnable(true, Call.Side.Remote);
    private final Runnable setRemoteVideoDisabledRunnable = new VideoStateRunnable(false, Call.Side.Remote);

    /* renamed from: com.amazon.comms.ringservice.webrtc.PeerConnectionClient$31  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass31 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$comms$calling$service$AcousticParams$Constraint;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$comms$calling$service$BundlePolicy;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$comms$ringservice$Sdp$Type = new int[Sdp.Type.values().length];

        static {
            try {
                $SwitchMap$com$amazon$comms$ringservice$Sdp$Type[Sdp.Type.OFFER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$comms$ringservice$Sdp$Type[Sdp.Type.PRANSWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$comms$ringservice$Sdp$Type[Sdp.Type.ANSWER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $SwitchMap$com$amazon$comms$calling$service$BundlePolicy = new int[BundlePolicy.values().length];
            try {
                $SwitchMap$com$amazon$comms$calling$service$BundlePolicy[BundlePolicy.MAXCOMPAT.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$BundlePolicy[BundlePolicy.BALANCED.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$BundlePolicy[BundlePolicy.MAXBUNDLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            $SwitchMap$com$amazon$comms$calling$service$AcousticParams$Constraint = new int[AcousticParams.Constraint.values().length];
            try {
                $SwitchMap$com$amazon$comms$calling$service$AcousticParams$Constraint[AcousticParams.Constraint.ECHO_CANCELLATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$AcousticParams$Constraint[AcousticParams.Constraint.AUTO_GAIN_CONTROL.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$AcousticParams$Constraint[AcousticParams.Constraint.HIGH_PASS_FILTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$AcousticParams$Constraint[AcousticParams.Constraint.NOISE_SUPPRESSION.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$AcousticParams$Constraint[AcousticParams.Constraint.AUDIO_NB_FILTER.ordinal()] = 5;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    /* loaded from: classes12.dex */
    class AudioStateRunnable implements Runnable {
        private boolean enable;
        private Call.Side side;

        public AudioStateRunnable(boolean z, Call.Side side) {
            this.enable = z;
            this.side = side;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (PeerConnectionClient.this.isError) {
                return;
            }
            CommsLogger commsLogger = PeerConnectionClient.log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AudioStateRunnable: localAudioEnabled:");
            outline107.append(PeerConnectionClient.this.localAudioEnabled);
            outline107.append(" enable:");
            outline107.append(this.enable);
            outline107.append(" side:");
            outline107.append(this.side);
            commsLogger.d(outline107.toString());
            if (Call.Side.Local == this.side) {
                boolean z = PeerConnectionClient.this.localAudioEnabled;
                boolean z2 = this.enable;
                if (z != z2) {
                    PeerConnectionClient.this.localAudioEnabled = z2;
                    if (PeerConnectionClient.this.localAudioTrack == null) {
                        return;
                    }
                    PeerConnectionClient.log.d("AudioStateRunnable localAudioTrack is valid");
                    PeerConnectionClient.this.localAudioTrack.setEnabled(PeerConnectionClient.this.localAudioEnabled);
                    return;
                }
            }
            if (Call.Side.Remote != this.side) {
                return;
            }
            boolean z3 = PeerConnectionClient.this.remoteAudioEnabled;
            boolean z4 = this.enable;
            if (z3 == z4) {
                return;
            }
            PeerConnectionClient.this.remoteAudioEnabled = z4;
            if (PeerConnectionClient.this.remoteSdpSetInProgress.get() > 0) {
                PeerConnectionClient.log.i("setAudioEnabled for remote audio track can't be done due to pending remote sdp processing.");
            } else if (PeerConnectionClient.this.remoteAudioTrack == null) {
            } else {
                PeerConnectionClient.this.remoteAudioTrack.setEnabled(PeerConnectionClient.this.remoteAudioEnabled);
            }
        }
    }

    /* loaded from: classes12.dex */
    private class LocalSDPObserver implements SdpObserver {
        private LocalSDPObserver() {
        }

        @Override // org.webrtc.SdpObserver
        public void onCreateFailure(final String str) {
            PeerConnectionClient.this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), PeerConnectionClient.this.logTag, ":LocalSDPObserver:onCreateFailure"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.LocalSDPObserver.3
                @Override // java.lang.Runnable
                public void run() {
                    PeerConnectionClient peerConnectionClient = PeerConnectionClient.this;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("createSDP error: ");
                    outline107.append(str);
                    peerConnectionClient.reportError(outline107.toString());
                }
            });
        }

        @Override // org.webrtc.SdpObserver
        public void onCreateSuccess(final SessionDescription sessionDescription) {
            PeerConnectionClient.this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), PeerConnectionClient.this.logTag, ":LocalSDPObserver:onCreateSuccess"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.LocalSDPObserver.1
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionClient.this.peerConnection == null || PeerConnectionClient.this.isError) {
                        PeerConnectionClient.log.e("Created SDP for non existent or errored PeerConnection");
                        return;
                    }
                    PeerConnectionClient.log.i(String.format("Set local SDP %s", sessionDescription.type));
                    if (PeerConnectionClient.log.isLoggable(LogLevel.Debug)) {
                        PeerConnectionClient.log.ds(String.format("Set local SDP Content :%n%s", sessionDescription.description));
                    }
                    if (!PeerConnectionClient.this.callAnswered && SessionDescription.Type.OFFER != sessionDescription.type && PeerConnectionClient.this.peerConnection.getLocalDescription() == null && PeerConnectionClient.this.peerConnectionParameters.waitForAcceptBeforeInitSDP) {
                        PeerConnectionClient.this.cachedSdp = sessionDescription;
                    } else {
                        PeerConnectionClient.this.peerConnection.setLocalDescription(PeerConnectionClient.this.localSdpObserver, PeerConnectionClient.this.modifySdpForLocalMediaState(sessionDescription));
                    }
                }
            });
        }

        @Override // org.webrtc.SdpObserver
        public void onSetFailure(final String str) {
            PeerConnectionClient.this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), PeerConnectionClient.this.logTag, ":LocalSDPObserver:onSetFailure"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.LocalSDPObserver.4
                @Override // java.lang.Runnable
                public void run() {
                    PeerConnectionClient peerConnectionClient = PeerConnectionClient.this;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("setSDP error: ");
                    outline107.append(str);
                    peerConnectionClient.reportError(outline107.toString());
                }
            });
        }

        @Override // org.webrtc.SdpObserver
        public void onSetSuccess() {
            PeerConnectionClient.this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), PeerConnectionClient.this.logTag, ":LocalSDPObserver:onSetSuccess"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.LocalSDPObserver.2
                @Override // java.lang.Runnable
                public void run() {
                    PeerConnectionClient.this.handleLocalOrRemoteSDPSet();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class PCObserver extends PeerConnectionShim.Observer {
        private PCObserver() {
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onAddStream(final MediaStream mediaStream) {
            PeerConnectionClient.this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), PeerConnectionClient.this.logTag, ":onAddStream"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PCObserver.7
                @Override // java.lang.Runnable
                public void run() {
                    PeerConnectionClient.log.i("onAddStream");
                    if (PeerConnectionClient.this.peerConnection == null || PeerConnectionClient.this.isError) {
                        return;
                    }
                    PeerConnectionClient.this.remoteMediaStream = mediaStream;
                    PeerConnectionClient.this.processRemoteStreamTracks(mediaStream);
                }
            });
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onDataChannel(final DataChannel dataChannel) {
            PeerConnectionClient.this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), PeerConnectionClient.this.logTag, ":onDataChannel"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PCObserver.10
                @Override // java.lang.Runnable
                public void run() {
                    String label = dataChannel.label();
                    CommsLogger commsLogger = PeerConnectionClient.log;
                    commsLogger.i("Received event for data channel with label: " + label);
                    if (PeerConnectionClient.this.dataChannelMap.containsKey(label)) {
                        CommsLogger commsLogger2 = PeerConnectionClient.log;
                        commsLogger2.w("Unregister and close the DC. Incoming data channel already exists for label: " + label);
                        DataChannel dataChannel2 = (DataChannel) PeerConnectionClient.this.dataChannelMap.get(label);
                        dataChannel2.unregisterObserver();
                        dataChannel2.close();
                        if (PeerConnectionClient.this.queuedMessages.containsKey(label)) {
                            PeerConnectionClient.this.queuedMessages.remove(label);
                        }
                    }
                    PeerConnectionClient.this.registerDataChannelObserver(dataChannel);
                    PeerConnectionClient.this.dataChannelMap.put(label, dataChannel);
                    PeerConnectionClient.this.sendQueuedDataChannelMessages(label);
                }
            });
        }

        @Override // org.webrtc.PeerConnectionShim.Observer
        public void onEncoderOutputResolutionChanged(int i, int i2) {
            CommsLogger commsLogger = PeerConnectionClient.log;
            commsLogger.i("onEncoderOutputResolutionChanged: width - " + i + " height - " + i2);
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onIceCandidate(final IceCandidate iceCandidate) {
            PeerConnectionClient.this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), PeerConnectionClient.this.logTag, ":onIceCandidate"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PCObserver.1
                @Override // java.lang.Runnable
                public void run() {
                    PeerConnectionClient.this.events.onIceCandidate(iceCandidate);
                }
            });
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onIceCandidatesRemoved(final IceCandidate[] iceCandidateArr) {
            PeerConnectionClient.this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), PeerConnectionClient.this.logTag, ":onIceCandidatesRemoved"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PCObserver.2
                @Override // java.lang.Runnable
                public void run() {
                    PeerConnectionClient.this.events.onIceCandidatesRemoved(iceCandidateArr);
                }
            });
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onIceConnectionChange(final PeerConnection.IceConnectionState iceConnectionState) {
            PeerConnectionClient.this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), PeerConnectionClient.this.logTag, ":onIceConnectionChange"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PCObserver.4
                @Override // java.lang.Runnable
                public void run() {
                    CommsLogger commsLogger = PeerConnectionClient.log;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IceConnectionState: ");
                    outline107.append(iceConnectionState);
                    outline107.append("signalingState: ");
                    outline107.append(PeerConnectionClient.this.signalingState);
                    commsLogger.i(outline107.toString());
                    PeerConnectionClient.this.events.onIceConnectionStateChange(iceConnectionState);
                    if (iceConnectionState == PeerConnection.IceConnectionState.CONNECTED) {
                        if (!PeerConnectionClient.this.videoCapable) {
                            PeerConnectionClient.log.i("device not video capable, no need to update video max constraints");
                            return;
                        }
                        if (PeerConnectionClient.log.isLoggable(LogLevel.Debug)) {
                            PeerConnectionClient.this.videoConstraintsManager.printAllConstraints();
                        }
                        PeerConnectionClient.this.videoConstraintsManager.setConstraintsUntilFirstIceConn(null);
                        VideoConstraints reducedVideoConstraints = PeerConnectionClient.this.videoConstraintsManager.getReducedVideoConstraints();
                        if (reducedVideoConstraints != null) {
                            CommsLogger commsLogger2 = PeerConnectionClient.log;
                            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Setting reduced outgoing resolution to: ");
                            outline1072.append(reducedVideoConstraints.toString());
                            commsLogger2.i(outline1072.toString());
                            PeerConnectionClient.this.localAudioVideoShim.setMaxVideoConstraints(reducedVideoConstraints.getVideoWidth(), reducedVideoConstraints.getVideoHeight(), reducedVideoConstraints.getVideoFps(), PeerConnectionClient.this.peerConnection);
                        }
                        if (!PeerConnectionClient.this.videoCapable) {
                            return;
                        }
                        PeerConnectionClient.this.applyMaxBitrateForVideoSender();
                        VideoConstraints currentVideoConstraints = PeerConnectionClient.this.videoConstraintsManager.getCurrentVideoConstraints();
                        if (currentVideoConstraints == null || currentVideoConstraints.equals(reducedVideoConstraints)) {
                            return;
                        }
                        List<Pair<String, Integer>> convertVideoConstraintToMediaConstraintList = VideoConstraintsManager.convertVideoConstraintToMediaConstraintList(currentVideoConstraints);
                        CommsLogger commsLogger3 = PeerConnectionClient.log;
                        commsLogger3.i("Updating VideoConstraints due to constraintVideoUntilIceConnection. Constaints: " + currentVideoConstraints);
                        PeerConnectionClient.this.localAudioVideoShim.updateVideoConstraints(PeerConnectionClient.this.peerConnection, convertVideoConstraintToMediaConstraintList);
                    }
                }
            });
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onIceConnectionReceivingChange(final boolean z) {
            PeerConnectionClient.this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), PeerConnectionClient.this.logTag, ":onIceConnectionReceivingChange"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PCObserver.6
                @Override // java.lang.Runnable
                public void run() {
                    GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport1.outline107("IceConnectionReceiving changed to "), z, PeerConnectionClient.log);
                    PeerConnectionClient.this.events.onIceConnectionReceivingChange(z);
                }
            });
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onIceGatheringChange(final PeerConnection.IceGatheringState iceGatheringState) {
            PeerConnectionClient.this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), PeerConnectionClient.this.logTag, ":onIceGatheringChange"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PCObserver.5
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionClient.this.currentIceGatheringState == iceGatheringState) {
                        return;
                    }
                    CommsLogger commsLogger = PeerConnectionClient.log;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IceGatheringState: ");
                    outline107.append(iceGatheringState);
                    commsLogger.i(outline107.toString());
                    if (PeerConnectionClient.this.peerConnection == null || PeerConnectionClient.this.isError) {
                        return;
                    }
                    PeerConnectionClient.this.currentIceGatheringState = iceGatheringState;
                    PeerConnection.IceGatheringState iceGatheringState2 = iceGatheringState;
                    if (iceGatheringState2 == PeerConnection.IceGatheringState.COMPLETE) {
                        PeerConnectionClient.this.events.onIceGatheringDone(PeerConnectionClient.this.peerConnection.getLocalDescription());
                        PeerConnectionClient.this.eventTracer.mark(EventTracerConfig.Event.Webrtc_icegatheringstate_complete);
                    } else if (iceGatheringState2 != PeerConnection.IceGatheringState.GATHERING) {
                    } else {
                        PeerConnectionClient.this.eventTracer.mark(EventTracerConfig.Event.Webrtc_icegatheringstate_gathering);
                    }
                }
            });
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onRemoveStream(MediaStream mediaStream) {
            PeerConnectionClient.this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), PeerConnectionClient.this.logTag, ":onRemoveStream"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PCObserver.9
                @Override // java.lang.Runnable
                public void run() {
                    PeerConnectionClient.log.i("onRemoveStream");
                    PeerConnectionClient.this.remoteVideoTrack = null;
                    PeerConnectionClient.this.remoteAudioTrack = null;
                    PeerConnectionClient.this.remoteMediaStream = null;
                }
            });
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onRenegotiationNeeded() {
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onSignalingChange(final PeerConnection.SignalingState signalingState) {
            PeerConnectionClient.this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), PeerConnectionClient.this.logTag, ":onSignalingChange"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PCObserver.3
                @Override // java.lang.Runnable
                public void run() {
                    CommsLogger commsLogger = PeerConnectionClient.log;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SignalingState: ");
                    outline107.append(signalingState);
                    commsLogger.i(outline107.toString());
                    PeerConnectionClient.this.signalingState = signalingState;
                    if (PeerConnectionClient.this.signalingState != PeerConnection.SignalingState.STABLE || !PeerConnectionClient.this.videoCapable) {
                        return;
                    }
                    PeerConnectionClient.this.applyMaxBitrateForVideoSender();
                }
            });
        }

        @Override // org.webrtc.PeerConnectionShim.Observer
        public void onUpdatedStream(final MediaStream mediaStream) {
            PeerConnectionClient.this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), PeerConnectionClient.this.logTag, ":onUpdatedStream"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.PCObserver.8
                @Override // java.lang.Runnable
                public void run() {
                    PeerConnectionClient.log.i("onUpdatedStream");
                    if (PeerConnectionClient.this.peerConnection == null || PeerConnectionClient.this.isError) {
                        return;
                    }
                    PeerConnectionClient.this.remoteMediaStream = mediaStream;
                }
            });
        }
    }

    /* loaded from: classes12.dex */
    public interface PeerConnectionEvents {
        void audioTrackUnderrunReport(int i);

        void onCameraError(CameraErrorCode cameraErrorCode, String str);

        void onCameraOpening(String str, boolean z);

        void onCameraQualityMetrics(String str);

        void onDataChannelEvent(DataChannelEvent dataChannelEvent);

        void onDataChannelsCreated(List<DataChannelProperties> list);

        void onDtmfInserted(boolean z, String str, int i, int i2);

        void onIceCandidate(IceCandidate iceCandidate);

        void onIceCandidatesRemoved(IceCandidate[] iceCandidateArr);

        void onIceConnectionReceivingChange(boolean z);

        void onIceConnectionStateChange(PeerConnection.IceConnectionState iceConnectionState);

        void onIceGatheringDone(SessionDescription sessionDescription);

        void onLocalDescription(SessionDescription sessionDescription);

        void onMediaStateChanged(Call.Side side, boolean z, boolean z2, MediaStateChangeTrigger mediaStateChangeTrigger);

        void onPeerConnectionClosed();

        void onPeerConnectionError(String str);

        void onPeerConnectionStatsReady(StatsReport[] statsReportArr);

        void onSignalingDone(boolean z);
    }

    /* loaded from: classes12.dex */
    public static class PeerConnectionParameters {
        private boolean aecDump;
        private String audioCodec;
        private AcousticParams audioProcessingParams;
        private int audioStartBitrateInKbps;
        private BundlePolicy bundlePolicy;
        private boolean camera1ApiPreferred;
        private boolean camera2ApiForced;
        private boolean captureToTexture;
        private boolean constraintVideoUntilIceConnection;
        private List<DataChannelConfiguration> dataChannelParams;
        private DynamicAcousticParams dynamicAcousticParams;
        private DynamicAcousticParams.ConfigPath dynamicAcousticParamsConfigPath;
        private boolean enableCameraMetricsReporting;
        private String fieldTrials;
        private boolean ignoreCameraEvictionError;
        private boolean initialSystemCameraEnabled;
        private boolean initialSystemMediaEnabled;
        private boolean loopback;
        private VideoConstraints maxVideoConstraintsOnReducedResolution;
        private VideoConstraints maxVideoConstraintsToRequestFromRemote;
        private boolean overrideRemoteVideoMaxBitrate;
        private PlatformVoIPSelection platformVoIPSelection;
        private boolean provideCallingServiceHalParameter;
        private boolean realTimeTextCapable;
        private boolean reduceVideoResolutionOnNoH264Remote;
        private boolean relayOnlyIceTransport;
        private RtcpMuxPolicy rtcpMuxPolicy;
        private String screenShape;
        private Size screenSize;
        private boolean simulateFirstFrameReceived;
        private SrtpCryptoType srtpCryptoType;
        private Map<Integer, VideoConstraints> supportedVideoResolutions;
        private boolean tracing;
        private boolean trickleIceEnabled;
        private boolean updateCameraHalFramerateAllowed;
        private boolean useOpenSLES;
        private boolean videoCapable;
        private String videoCodec;
        private boolean videoCodecHwAcceleration;
        private VideoConstraints videoConstraintsUntilIceConnection;
        private String videoFileInjectionPath;
        private int videoFps;
        private int videoHeight;
        private int videoMaxBitrate;
        private boolean videoRequestEnabled;
        private int videoStartBitrate;
        private int videoWidth;
        private boolean waitForAcceptBeforeInitSDP;
        private int webRTCStatsLoggingFrequency;
        private int webRTCStatsPollingFrequency;

        /* loaded from: classes12.dex */
        public static class PeerConnectionParametersBuilder {
            private BundlePolicy bundlePolicy;
            private boolean camera1ApiPreferred;
            private boolean camera2ApiForced;
            private boolean captureToTexture;
            private List<DataChannelConfiguration> dataChannelParams;
            private DynamicAcousticParams dynamicAcousticParams;
            private String fieldTrials;
            private boolean ignoreCameraEvictionError;
            private boolean initialSystemCameraEnabled;
            private boolean initialSystemMediaEnabled;
            private VideoConstraints maxVideoConstraintsOnReducedResolution;
            private VideoConstraints maxVideoConstraintsToRequestFromRemote;
            private boolean provideCallingServiceHalParameter;
            private boolean realTimeTextCapable;
            private String screenShape;
            private Size screenSize;
            private boolean simulateFirstFrameReceived;
            private Map<Integer, VideoConstraints> supportedVideoResolutions;
            private boolean updateCameraHalFramerateAllowed;
            private VideoConstraints videoConstraintsUntilIceConnection;
            private String videoFileInjectionPath;
            private int videoFps;
            private int videoHeight;
            private int videoMaxBitrate;
            private boolean videoRequestEnabled;
            private int videoStartBitrate;
            private int videoWidth;
            private int webRTCStatsPollingFrequency;
            private boolean videoCapable = true;
            private boolean loopback = false;
            private boolean tracing = false;
            private String videoCodec = "VP8";
            private boolean videoCodecHwAcceleration = true;
            private int audioStartBitrateInKbps = 20;
            private String audioCodec = "OPUS";
            private AcousticParams audioProcessingParams = AcousticParams.enabled();
            private boolean aecDump = false;
            private boolean useOpenSLES = false;
            private boolean overrideRemoteVideoMaxBitrate = false;
            private boolean waitForAcceptBeforeInitSDP = true;
            private boolean relayOnlyIceTransport = true;
            private boolean reduceVideoResolutionOnNoH264Remote = false;
            private SrtpCryptoType srtpCryptoType = SrtpCryptoType.SDES;
            private int webRTCStatsLoggingFrequency = -1;
            private boolean constraintVideoUntilIceConnection = false;
            private RtcpMuxPolicy rtcpMuxPolicy = RtcpMuxPolicy.NEGOTIATE;
            private DynamicAcousticParams.ConfigPath dynamicAcousticParamsConfigPath = DynamicAcousticParams.ConfigPath.NONE;
            private boolean enableCameraMetricsReporting = false;
            private boolean trickleIceEnabled = false;
            private PlatformVoIPSelection platformVoIPSelection = null;

            PeerConnectionParametersBuilder() {
            }

            public PeerConnectionParametersBuilder aecDump(boolean z) {
                this.aecDump = z;
                return this;
            }

            public PeerConnectionParametersBuilder audioCodec(String str) {
                this.audioCodec = str;
                return this;
            }

            public PeerConnectionParametersBuilder audioProcessingParams(AcousticParams acousticParams) {
                this.audioProcessingParams = acousticParams;
                return this;
            }

            public PeerConnectionParametersBuilder audioStartBitrateInKbps(int i) {
                this.audioStartBitrateInKbps = i;
                return this;
            }

            public PeerConnectionParameters build() {
                return new PeerConnectionParameters(this.videoCapable, this.loopback, this.tracing, this.videoWidth, this.videoHeight, this.videoFps, this.videoStartBitrate, this.videoCodec, this.videoCodecHwAcceleration, this.captureToTexture, this.camera1ApiPreferred, this.camera2ApiForced, this.updateCameraHalFramerateAllowed, this.provideCallingServiceHalParameter, this.audioStartBitrateInKbps, this.audioCodec, this.audioProcessingParams, this.aecDump, this.useOpenSLES, this.videoRequestEnabled, this.videoMaxBitrate, this.overrideRemoteVideoMaxBitrate, this.waitForAcceptBeforeInitSDP, this.relayOnlyIceTransport, this.initialSystemMediaEnabled, this.initialSystemCameraEnabled, this.reduceVideoResolutionOnNoH264Remote, this.maxVideoConstraintsOnReducedResolution, this.fieldTrials, this.ignoreCameraEvictionError, this.simulateFirstFrameReceived, this.srtpCryptoType, this.supportedVideoResolutions, this.maxVideoConstraintsToRequestFromRemote, this.webRTCStatsPollingFrequency, this.webRTCStatsLoggingFrequency, this.dataChannelParams, this.constraintVideoUntilIceConnection, this.videoConstraintsUntilIceConnection, this.screenShape, this.screenSize, this.bundlePolicy, this.rtcpMuxPolicy, this.dynamicAcousticParamsConfigPath, this.dynamicAcousticParams, this.realTimeTextCapable, this.enableCameraMetricsReporting, this.videoFileInjectionPath, this.trickleIceEnabled, this.platformVoIPSelection);
            }

            public PeerConnectionParametersBuilder bundlePolicy(BundlePolicy bundlePolicy) {
                this.bundlePolicy = bundlePolicy;
                return this;
            }

            public PeerConnectionParametersBuilder camera1ApiPreferred(boolean z) {
                this.camera1ApiPreferred = z;
                return this;
            }

            public PeerConnectionParametersBuilder camera2ApiForced(boolean z) {
                this.camera2ApiForced = z;
                return this;
            }

            public PeerConnectionParametersBuilder captureToTexture(boolean z) {
                this.captureToTexture = z;
                return this;
            }

            public PeerConnectionParametersBuilder constraintVideoUntilIceConnection(boolean z) {
                this.constraintVideoUntilIceConnection = z;
                return this;
            }

            public PeerConnectionParametersBuilder dataChannelParams(List<DataChannelConfiguration> list) {
                this.dataChannelParams = list;
                return this;
            }

            public PeerConnectionParametersBuilder dynamicAcousticParams(DynamicAcousticParams dynamicAcousticParams) {
                this.dynamicAcousticParams = dynamicAcousticParams;
                return this;
            }

            public PeerConnectionParametersBuilder dynamicAcousticParamsConfigPath(DynamicAcousticParams.ConfigPath configPath) {
                this.dynamicAcousticParamsConfigPath = configPath;
                return this;
            }

            public PeerConnectionParametersBuilder enableCameraMetricsReporting(boolean z) {
                this.enableCameraMetricsReporting = z;
                return this;
            }

            public PeerConnectionParametersBuilder fieldTrials(String str) {
                this.fieldTrials = str;
                return this;
            }

            public PeerConnectionParametersBuilder ignoreCameraEvictionError(boolean z) {
                this.ignoreCameraEvictionError = z;
                return this;
            }

            public PeerConnectionParametersBuilder initialSystemCameraEnabled(boolean z) {
                this.initialSystemCameraEnabled = z;
                return this;
            }

            public PeerConnectionParametersBuilder initialSystemMediaEnabled(boolean z) {
                this.initialSystemMediaEnabled = z;
                return this;
            }

            public PeerConnectionParametersBuilder loopback(boolean z) {
                this.loopback = z;
                return this;
            }

            public PeerConnectionParametersBuilder maxVideoConstraintsOnReducedResolution(VideoConstraints videoConstraints) {
                this.maxVideoConstraintsOnReducedResolution = videoConstraints;
                return this;
            }

            public PeerConnectionParametersBuilder maxVideoConstraintsToRequestFromRemote(VideoConstraints videoConstraints) {
                this.maxVideoConstraintsToRequestFromRemote = videoConstraints;
                return this;
            }

            public PeerConnectionParametersBuilder overrideRemoteVideoMaxBitrate(boolean z) {
                this.overrideRemoteVideoMaxBitrate = z;
                return this;
            }

            public PeerConnectionParametersBuilder platformVoIPSelection(PlatformVoIPSelection platformVoIPSelection) {
                this.platformVoIPSelection = platformVoIPSelection;
                return this;
            }

            public PeerConnectionParametersBuilder provideCallingServiceHalParameter(boolean z) {
                this.provideCallingServiceHalParameter = z;
                return this;
            }

            public PeerConnectionParametersBuilder realTimeTextCapable(boolean z) {
                this.realTimeTextCapable = z;
                return this;
            }

            public PeerConnectionParametersBuilder reduceVideoResolutionOnNoH264Remote(boolean z) {
                this.reduceVideoResolutionOnNoH264Remote = z;
                return this;
            }

            public PeerConnectionParametersBuilder relayOnlyIceTransport(boolean z) {
                this.relayOnlyIceTransport = z;
                return this;
            }

            public PeerConnectionParametersBuilder rtcpMuxPolicy(RtcpMuxPolicy rtcpMuxPolicy) {
                this.rtcpMuxPolicy = rtcpMuxPolicy;
                return this;
            }

            public PeerConnectionParametersBuilder screenShape(String str) {
                this.screenShape = str;
                return this;
            }

            public PeerConnectionParametersBuilder screenSize(Size size) {
                this.screenSize = size;
                return this;
            }

            public PeerConnectionParametersBuilder simulateFirstFrameReceived(boolean z) {
                this.simulateFirstFrameReceived = z;
                return this;
            }

            public PeerConnectionParametersBuilder srtpCryptoType(SrtpCryptoType srtpCryptoType) {
                this.srtpCryptoType = srtpCryptoType;
                return this;
            }

            public PeerConnectionParametersBuilder supportedVideoResolutions(Map<Integer, VideoConstraints> map) {
                this.supportedVideoResolutions = map;
                return this;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PeerConnectionClient.PeerConnectionParameters.PeerConnectionParametersBuilder(videoCapable=");
                outline107.append(this.videoCapable);
                outline107.append(", loopback=");
                outline107.append(this.loopback);
                outline107.append(", tracing=");
                outline107.append(this.tracing);
                outline107.append(", videoWidth=");
                outline107.append(this.videoWidth);
                outline107.append(", videoHeight=");
                outline107.append(this.videoHeight);
                outline107.append(", videoFps=");
                outline107.append(this.videoFps);
                outline107.append(", videoStartBitrate=");
                outline107.append(this.videoStartBitrate);
                outline107.append(", videoCodec=");
                outline107.append(this.videoCodec);
                outline107.append(", videoCodecHwAcceleration=");
                outline107.append(this.videoCodecHwAcceleration);
                outline107.append(", captureToTexture=");
                outline107.append(this.captureToTexture);
                outline107.append(", camera1ApiPreferred=");
                outline107.append(this.camera1ApiPreferred);
                outline107.append(", camera2ApiForced=");
                outline107.append(this.camera2ApiForced);
                outline107.append(", updateCameraHalFramerateAllowed=");
                outline107.append(this.updateCameraHalFramerateAllowed);
                outline107.append(", provideCallingServiceHalParameter=");
                outline107.append(this.provideCallingServiceHalParameter);
                outline107.append(", audioStartBitrateInKbps=");
                outline107.append(this.audioStartBitrateInKbps);
                outline107.append(", audioCodec=");
                outline107.append(this.audioCodec);
                outline107.append(", audioProcessingParams=");
                outline107.append(this.audioProcessingParams);
                outline107.append(", aecDump=");
                outline107.append(this.aecDump);
                outline107.append(", useOpenSLES=");
                outline107.append(this.useOpenSLES);
                outline107.append(", videoRequestEnabled=");
                outline107.append(this.videoRequestEnabled);
                outline107.append(", videoMaxBitrate=");
                outline107.append(this.videoMaxBitrate);
                outline107.append(", overrideRemoteVideoMaxBitrate=");
                outline107.append(this.overrideRemoteVideoMaxBitrate);
                outline107.append(", waitForAcceptBeforeInitSDP=");
                outline107.append(this.waitForAcceptBeforeInitSDP);
                outline107.append(", relayOnlyIceTransport=");
                outline107.append(this.relayOnlyIceTransport);
                outline107.append(", initialSystemMediaEnabled=");
                outline107.append(this.initialSystemMediaEnabled);
                outline107.append(", initialSystemCameraEnabled=");
                outline107.append(this.initialSystemCameraEnabled);
                outline107.append(", reduceVideoResolutionOnNoH264Remote=");
                outline107.append(this.reduceVideoResolutionOnNoH264Remote);
                outline107.append(", maxVideoConstraintsOnReducedResolution=");
                outline107.append(this.maxVideoConstraintsOnReducedResolution);
                outline107.append(", fieldTrials=");
                outline107.append(this.fieldTrials);
                outline107.append(", ignoreCameraEvictionError=");
                outline107.append(this.ignoreCameraEvictionError);
                outline107.append(", simulateFirstFrameReceived=");
                outline107.append(this.simulateFirstFrameReceived);
                outline107.append(", srtpCryptoType=");
                outline107.append(this.srtpCryptoType);
                outline107.append(", supportedVideoResolutions=");
                outline107.append(this.supportedVideoResolutions);
                outline107.append(", maxVideoConstraintsToRequestFromRemote=");
                outline107.append(this.maxVideoConstraintsToRequestFromRemote);
                outline107.append(", webRTCStatsPollingFrequency=");
                outline107.append(this.webRTCStatsPollingFrequency);
                outline107.append(", webRTCStatsLoggingFrequency=");
                outline107.append(this.webRTCStatsLoggingFrequency);
                outline107.append(", dataChannelParams=");
                outline107.append(this.dataChannelParams);
                outline107.append(", constraintVideoUntilIceConnection=");
                outline107.append(this.constraintVideoUntilIceConnection);
                outline107.append(", videoConstraintsUntilIceConnection=");
                outline107.append(this.videoConstraintsUntilIceConnection);
                outline107.append(", screenShape=");
                outline107.append(this.screenShape);
                outline107.append(", screenSize=");
                outline107.append(this.screenSize);
                outline107.append(", bundlePolicy=");
                outline107.append(this.bundlePolicy);
                outline107.append(", rtcpMuxPolicy=");
                outline107.append(this.rtcpMuxPolicy);
                outline107.append(", dynamicAcousticParamsConfigPath=");
                outline107.append(this.dynamicAcousticParamsConfigPath);
                outline107.append(", dynamicAcousticParams=");
                outline107.append(this.dynamicAcousticParams);
                outline107.append(", realTimeTextCapable=");
                outline107.append(this.realTimeTextCapable);
                outline107.append(", enableCameraMetricsReporting=");
                outline107.append(this.enableCameraMetricsReporting);
                outline107.append(", videoFileInjectionPath=");
                outline107.append(this.videoFileInjectionPath);
                outline107.append(", trickleIceEnabled=");
                outline107.append(this.trickleIceEnabled);
                outline107.append(", platformVoIPSelection=");
                outline107.append(this.platformVoIPSelection);
                outline107.append(")");
                return outline107.toString();
            }

            public PeerConnectionParametersBuilder tracing(boolean z) {
                this.tracing = z;
                return this;
            }

            public PeerConnectionParametersBuilder trickleIceEnabled(boolean z) {
                this.trickleIceEnabled = z;
                return this;
            }

            public PeerConnectionParametersBuilder updateCameraHalFramerateAllowed(boolean z) {
                this.updateCameraHalFramerateAllowed = z;
                return this;
            }

            public PeerConnectionParametersBuilder useOpenSLES(boolean z) {
                this.useOpenSLES = z;
                return this;
            }

            public PeerConnectionParametersBuilder videoCapable(boolean z) {
                this.videoCapable = z;
                return this;
            }

            public PeerConnectionParametersBuilder videoCodec(String str) {
                this.videoCodec = str;
                return this;
            }

            public PeerConnectionParametersBuilder videoCodecHwAcceleration(boolean z) {
                this.videoCodecHwAcceleration = z;
                return this;
            }

            public PeerConnectionParametersBuilder videoConstraintsUntilIceConnection(VideoConstraints videoConstraints) {
                this.videoConstraintsUntilIceConnection = videoConstraints;
                return this;
            }

            public PeerConnectionParametersBuilder videoFileInjectionPath(String str) {
                this.videoFileInjectionPath = str;
                return this;
            }

            public PeerConnectionParametersBuilder videoFps(int i) {
                this.videoFps = i;
                return this;
            }

            public PeerConnectionParametersBuilder videoHeight(int i) {
                this.videoHeight = i;
                return this;
            }

            public PeerConnectionParametersBuilder videoMaxBitrate(int i) {
                this.videoMaxBitrate = i;
                return this;
            }

            public PeerConnectionParametersBuilder videoRequestEnabled(boolean z) {
                this.videoRequestEnabled = z;
                return this;
            }

            public PeerConnectionParametersBuilder videoStartBitrate(int i) {
                this.videoStartBitrate = i;
                return this;
            }

            public PeerConnectionParametersBuilder videoWidth(int i) {
                this.videoWidth = i;
                return this;
            }

            public PeerConnectionParametersBuilder waitForAcceptBeforeInitSDP(boolean z) {
                this.waitForAcceptBeforeInitSDP = z;
                return this;
            }

            public PeerConnectionParametersBuilder webRTCStatsLoggingFrequency(int i) {
                this.webRTCStatsLoggingFrequency = i;
                return this;
            }

            public PeerConnectionParametersBuilder webRTCStatsPollingFrequency(int i) {
                this.webRTCStatsPollingFrequency = i;
                return this;
            }
        }

        PeerConnectionParameters(boolean z, boolean z2, boolean z3, int i, int i2, int i3, int i4, String str, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, int i5, String str2, AcousticParams acousticParams, boolean z10, boolean z11, boolean z12, int i6, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17, boolean z18, VideoConstraints videoConstraints, String str3, boolean z19, boolean z20, SrtpCryptoType srtpCryptoType, Map<Integer, VideoConstraints> map, VideoConstraints videoConstraints2, int i7, int i8, List<DataChannelConfiguration> list, boolean z21, VideoConstraints videoConstraints3, String str4, Size size, BundlePolicy bundlePolicy, RtcpMuxPolicy rtcpMuxPolicy, DynamicAcousticParams.ConfigPath configPath, DynamicAcousticParams dynamicAcousticParams, boolean z22, boolean z23, String str5, boolean z24, PlatformVoIPSelection platformVoIPSelection) {
            this.videoCapable = z;
            this.loopback = z2;
            this.tracing = z3;
            this.videoWidth = i;
            this.videoHeight = i2;
            this.videoFps = i3;
            this.videoStartBitrate = i4;
            this.videoCodec = str;
            this.videoCodecHwAcceleration = z4;
            this.captureToTexture = z5;
            this.camera1ApiPreferred = z6;
            this.camera2ApiForced = z7;
            this.updateCameraHalFramerateAllowed = z8;
            this.provideCallingServiceHalParameter = z9;
            this.audioStartBitrateInKbps = i5;
            this.audioCodec = str2;
            this.audioProcessingParams = acousticParams;
            this.aecDump = z10;
            this.useOpenSLES = z11;
            this.videoRequestEnabled = z12;
            this.videoMaxBitrate = i6;
            this.overrideRemoteVideoMaxBitrate = z13;
            this.waitForAcceptBeforeInitSDP = z14;
            this.relayOnlyIceTransport = z15;
            this.initialSystemMediaEnabled = z16;
            this.initialSystemCameraEnabled = z17;
            this.reduceVideoResolutionOnNoH264Remote = z18;
            this.maxVideoConstraintsOnReducedResolution = videoConstraints;
            this.fieldTrials = str3;
            this.ignoreCameraEvictionError = z19;
            this.simulateFirstFrameReceived = z20;
            this.srtpCryptoType = srtpCryptoType;
            this.supportedVideoResolutions = map;
            this.maxVideoConstraintsToRequestFromRemote = videoConstraints2;
            this.webRTCStatsPollingFrequency = i7;
            this.webRTCStatsLoggingFrequency = i8;
            this.dataChannelParams = list;
            this.constraintVideoUntilIceConnection = z21;
            this.videoConstraintsUntilIceConnection = videoConstraints3;
            this.screenShape = str4;
            this.screenSize = size;
            this.bundlePolicy = bundlePolicy;
            this.rtcpMuxPolicy = rtcpMuxPolicy;
            this.dynamicAcousticParamsConfigPath = configPath;
            this.dynamicAcousticParams = dynamicAcousticParams;
            this.realTimeTextCapable = z22;
            this.enableCameraMetricsReporting = z23;
            this.videoFileInjectionPath = str5;
            this.trickleIceEnabled = z24;
            this.platformVoIPSelection = platformVoIPSelection;
        }

        public static PeerConnectionParametersBuilder builder() {
            return new PeerConnectionParametersBuilder();
        }

        public String getAudioCodec() {
            return this.audioCodec;
        }

        public AcousticParams getAudioProcessingParams() {
            return this.audioProcessingParams;
        }

        public int getAudioStartBitrateInKbps() {
            return this.audioStartBitrateInKbps;
        }

        public BundlePolicy getBundlePolicy() {
            return this.bundlePolicy;
        }

        public List<DataChannelConfiguration> getDataChannelParams() {
            return this.dataChannelParams;
        }

        public DynamicAcousticParams getDynamicAcousticParams() {
            return this.dynamicAcousticParams;
        }

        public DynamicAcousticParams.ConfigPath getDynamicAcousticParamsConfigPath() {
            return this.dynamicAcousticParamsConfigPath;
        }

        public String getFieldTrials() {
            return this.fieldTrials;
        }

        public VideoConstraints getMaxVideoConstraintsOnReducedResolution() {
            return this.maxVideoConstraintsOnReducedResolution;
        }

        public VideoConstraints getMaxVideoConstraintsToRequestFromRemote() {
            return this.maxVideoConstraintsToRequestFromRemote;
        }

        public PlatformVoIPSelection getPlatformVoIPSelection() {
            return this.platformVoIPSelection;
        }

        public RtcpMuxPolicy getRtcpMuxPolicy() {
            return this.rtcpMuxPolicy;
        }

        public String getScreenShape() {
            return this.screenShape;
        }

        public Size getScreenSize() {
            return this.screenSize;
        }

        public SrtpCryptoType getSrtpCryptoType() {
            return this.srtpCryptoType;
        }

        public Map<Integer, VideoConstraints> getSupportedVideoResolutions() {
            return this.supportedVideoResolutions;
        }

        public String getVideoCodec() {
            return this.videoCodec;
        }

        public VideoConstraints getVideoConstraintsUntilIceConnection() {
            return this.videoConstraintsUntilIceConnection;
        }

        public String getVideoFileInjectionPath() {
            return this.videoFileInjectionPath;
        }

        public int getVideoFps() {
            return this.videoFps;
        }

        public int getVideoHeight() {
            return this.videoHeight;
        }

        public int getVideoMaxBitrate() {
            return this.videoMaxBitrate;
        }

        public int getVideoStartBitrate() {
            return this.videoStartBitrate;
        }

        public int getVideoWidth() {
            return this.videoWidth;
        }

        public int getWebRTCStatsLoggingFrequency() {
            return this.webRTCStatsLoggingFrequency;
        }

        public int getWebRTCStatsPollingFrequency() {
            return this.webRTCStatsPollingFrequency;
        }

        public boolean isAecDump() {
            return this.aecDump;
        }

        public boolean isCamera1ApiPreferred() {
            return this.camera1ApiPreferred;
        }

        public boolean isCamera2ApiForced() {
            return this.camera2ApiForced;
        }

        public boolean isCaptureToTexture() {
            return this.captureToTexture;
        }

        public boolean isConstraintVideoUntilIceConnection() {
            return this.constraintVideoUntilIceConnection;
        }

        public boolean isEnableCameraMetricsReporting() {
            return this.enableCameraMetricsReporting;
        }

        public boolean isIgnoreCameraEvictionError() {
            return this.ignoreCameraEvictionError;
        }

        public boolean isInitialSystemCameraEnabled() {
            return this.initialSystemCameraEnabled;
        }

        public boolean isInitialSystemMediaEnabled() {
            return this.initialSystemMediaEnabled;
        }

        public boolean isLoopback() {
            return this.loopback;
        }

        public boolean isOverrideRemoteVideoMaxBitrate() {
            return this.overrideRemoteVideoMaxBitrate;
        }

        public boolean isProvideCallingServiceHalParameter() {
            return this.provideCallingServiceHalParameter;
        }

        public boolean isRealTimeTextCapable() {
            return this.realTimeTextCapable;
        }

        public boolean isReduceVideoResolutionOnNoH264Remote() {
            return this.reduceVideoResolutionOnNoH264Remote;
        }

        public boolean isRelayOnlyIceTransport() {
            return this.relayOnlyIceTransport;
        }

        public boolean isSimulateFirstFrameReceived() {
            return this.simulateFirstFrameReceived;
        }

        public boolean isTracing() {
            return this.tracing;
        }

        public boolean isTrickleIceEnabled() {
            return this.trickleIceEnabled;
        }

        public boolean isUpdateCameraHalFramerateAllowed() {
            return this.updateCameraHalFramerateAllowed;
        }

        public boolean isUseOpenSLES() {
            return this.useOpenSLES;
        }

        public boolean isVideoCapable() {
            return this.videoCapable;
        }

        public boolean isVideoCodecHwAcceleration() {
            return this.videoCodecHwAcceleration;
        }

        public boolean isVideoRequestEnabled() {
            return this.videoRequestEnabled;
        }

        public boolean isWaitForAcceptBeforeInitSDP() {
            return this.waitForAcceptBeforeInitSDP;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class RemoteSDPObserver implements SdpObserver {
        private RemoteSDPObserver() {
        }

        @Override // org.webrtc.SdpObserver
        public void onCreateFailure(final String str) {
            PeerConnectionClient.this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), PeerConnectionClient.this.logTag, ":RemoteSDPObserver:onCreateFailure"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.RemoteSDPObserver.1
                @Override // java.lang.Runnable
                public void run() {
                    PeerConnectionClient peerConnectionClient = PeerConnectionClient.this;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("createRemoteSDP error: ");
                    outline107.append(str);
                    peerConnectionClient.reportError(outline107.toString());
                }
            });
        }

        @Override // org.webrtc.SdpObserver
        public void onCreateSuccess(SessionDescription sessionDescription) {
            PeerConnectionClient.log.w("remote sdp create success!");
        }

        @Override // org.webrtc.SdpObserver
        public void onSetFailure(final String str) {
            PeerConnectionClient.this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), PeerConnectionClient.this.logTag, ":RemoteSDPObserver:onSetFailure"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.RemoteSDPObserver.3
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionShim.shouldProcessRemoteMediaStreamOnRemoteSdp() && PeerConnectionClient.this.remoteMediaStream != null && PeerConnectionClient.this.peerConnection != null && !PeerConnectionClient.this.isError) {
                        PeerConnectionClient.log.i("remote sdp set failure, processing remote stream");
                        PeerConnectionClient peerConnectionClient = PeerConnectionClient.this;
                        peerConnectionClient.processRemoteStreamTracks(peerConnectionClient.remoteMediaStream);
                    }
                    int decrementAndGet = PeerConnectionClient.this.remoteSdpSetInProgress.decrementAndGet();
                    if (PeerConnectionClient.this.noSupportedAudioCodecsPresent) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported codec:");
                        outline107.append(str);
                        String sb = outline107.toString();
                        PeerConnectionClient peerConnectionClient2 = PeerConnectionClient.this;
                        peerConnectionClient2.reportError("setRemoteSDP set error: " + sb + " pendingdp= " + decrementAndGet);
                        PeerConnectionClient.this.noSupportedAudioCodecsPresent = false;
                        return;
                    }
                    PeerConnectionClient peerConnectionClient3 = PeerConnectionClient.this;
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("setRemoteSDP set error: ");
                    outline1072.append(str);
                    outline1072.append(" pendingdp= ");
                    outline1072.append(decrementAndGet);
                    peerConnectionClient3.reportError(outline1072.toString());
                }
            });
        }

        @Override // org.webrtc.SdpObserver
        public void onSetSuccess() {
            PeerConnectionClient.this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), PeerConnectionClient.this.logTag, ":RemoteSDPObserver:onSetSuccess"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.RemoteSDPObserver.2
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionShim.shouldProcessRemoteMediaStreamOnRemoteSdp() && PeerConnectionClient.this.remoteMediaStream != null && PeerConnectionClient.this.peerConnection != null && !PeerConnectionClient.this.isError) {
                        PeerConnectionClient.log.i("remote sdp set, processing remote stream");
                        PeerConnectionClient peerConnectionClient = PeerConnectionClient.this;
                        peerConnectionClient.processRemoteStreamTracks(peerConnectionClient.remoteMediaStream);
                    }
                    PeerConnectionClient.this.handleLocalOrRemoteSDPSet();
                    int decrementAndGet = PeerConnectionClient.this.remoteSdpSetInProgress.decrementAndGet();
                    CommsLogger commsLogger = PeerConnectionClient.log;
                    commsLogger.d("remote sdp set success. Pending sdp = " + decrementAndGet);
                }
            });
        }
    }

    /* loaded from: classes12.dex */
    public static class SignalingParameters {
        public final String clientId;
        public final List<IceCandidate> iceCandidates;
        public final List<PeerConnection.IceServer> iceServers;
        public final boolean stripTrickleIceFromSdp;
        public final String wssPostUrl;
        public final String wssUrl;

        public SignalingParameters(List<PeerConnection.IceServer> list, String str, String str2, String str3, List<IceCandidate> list2, boolean z) {
            this.iceServers = list;
            this.clientId = str;
            this.wssUrl = str2;
            this.wssPostUrl = str3;
            this.iceCandidates = list2;
            this.stripTrickleIceFromSdp = z;
        }
    }

    /* loaded from: classes12.dex */
    public interface VideoEffectTransitionListener {
        void onAbort();

        void onVideoEffectTransition(String str);
    }

    /* loaded from: classes12.dex */
    class VideoStateRunnable implements Runnable {
        private boolean enable;
        private Call.Side side;

        public VideoStateRunnable(boolean z, Call.Side side) {
            this.enable = z;
            this.side = side;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!PeerConnectionClient.this.isVideoCapable() || PeerConnectionClient.this.isError) {
                return;
            }
            CommsLogger commsLogger = PeerConnectionClient.log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("VideoStateRunnable : ");
            outline107.append(this.side);
            outline107.append(" enable=");
            outline107.append(this.enable);
            outline107.append(" renderLocalVideo=");
            outline107.append(PeerConnectionClient.this.renderLocalVideo);
            commsLogger.d(outline107.toString());
            if (Call.Side.Local == this.side) {
                boolean z = PeerConnectionClient.this.renderLocalVideo;
                boolean z2 = this.enable;
                if (z != z2) {
                    PeerConnectionClient.this.renderLocalVideo = z2;
                    if (PeerConnectionClient.this.renderLocalVideo) {
                        PeerConnectionClient.this.startVideoSource();
                        if (PeerConnectionClient.this.localVideoTrack == null) {
                            PeerConnectionClient.this.provideVideoTrack();
                        }
                    } else {
                        PeerConnectionClient.this.stopVideoSource();
                    }
                    if (PeerConnectionClient.this.localVideoTrack == null) {
                        return;
                    }
                    PeerConnectionClient.this.localVideoTrack.setEnabled(PeerConnectionClient.this.renderLocalVideo);
                    return;
                }
            }
            if (Call.Side.Remote == this.side) {
                boolean z3 = PeerConnectionClient.this.renderRemoteVideo;
                boolean z4 = this.enable;
                if (z3 != z4) {
                    PeerConnectionClient.this.renderRemoteVideo = z4;
                    if (PeerConnectionClient.this.remoteSdpSetInProgress.get() > 0) {
                        PeerConnectionClient.log.i("setVideoEnabled for remote video track can't be done due to pending remote sdp processing.");
                        return;
                    } else if (PeerConnectionClient.this.remoteVideoTrack == null) {
                        return;
                    } else {
                        PeerConnectionClient.this.remoteVideoTrack.setEnabled(PeerConnectionClient.this.renderRemoteVideo);
                        return;
                    }
                }
            }
            CommsLogger commsLogger2 = PeerConnectionClient.log;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("VideoStateRunnable NO-OP: ");
            outline1072.append(this.side);
            outline1072.append(" renderLocalVideo=");
            outline1072.append(PeerConnectionClient.this.renderLocalVideo);
            outline1072.append(" enable=");
            outline1072.append(this.enable);
            commsLogger2.d(outline1072.toString());
        }
    }

    public PeerConnectionClient(PeerConnectionParameters peerConnectionParameters, PeerConnectionEvents peerConnectionEvents, EglBase.Context context, boolean z, EventTracer eventTracer, LooperExecutor looperExecutor, Context context2, WebRTCGlobalsProvider webRTCGlobalsProvider, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.videoRtpSender = null;
        this.executor = looperExecutor;
        this.peerConnectionParameters = peerConnectionParameters;
        this.events = peerConnectionEvents;
        this.eglContext = context;
        this.warmupClient = z;
        this.eventTracer = eventTracer;
        this.videoConstraintsManager.setLocalVideoConstraints(new VideoConstraints(peerConnectionParameters.getVideoWidth(), peerConnectionParameters.getVideoHeight(), peerConnectionParameters.getVideoFps()));
        this.applicationContext = context2;
        this.videoCapable = peerConnectionParameters.videoCapable;
        this.renderRemoteVideoSupported = z4;
        this.callVideoCapability = z3;
        this.initiator = z5;
        this.webRTCGlobalsProvider = webRTCGlobalsProvider;
        this.peerConnection = null;
        this.isError = false;
        this.queuedRemoteCandidates = null;
        this.renderLocalVideo = z3;
        this.renderRemoteVideo = true;
        this.localVideoTrack = null;
        this.videoRtpSender = null;
        this.remoteVideoTrack = null;
        this.remoteAudioTrack = null;
        this.remoteMediaStream = null;
        this.remoteSdpSetInProgress.set(0);
        this.localAudioEnabled = z2;
        this.remoteAudioEnabled = true;
        this.statsTimer = new Timer();
        this.followMeEnabled = false;
        this.followMeShim = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyMaxBitrateForVideoSender() {
        VideoTrack videoTrack;
        if (!this.isPendingVideoMaxSendBitrateSet || (videoTrack = this.localVideoTrack) == null) {
            return;
        }
        if (this.videoRtpSender == null) {
            this.videoRtpSender = findRtpSender(videoTrack);
        }
        int videoMaxBitrate = this.peerConnectionParameters.getVideoMaxBitrate();
        if (!this.peerConnectionParameters.overrideRemoteVideoMaxBitrate) {
            videoMaxBitrate = Math.min(this.remoteRequestVideoMaxSendBitrate, videoMaxBitrate);
        }
        this.isPendingVideoMaxSendBitrateSet = !setRtpSenderMaxBitrate(this.videoRtpSender, videoMaxBitrate);
    }

    private void attachRemoteAudioTrack(AudioTrack audioTrack) {
        this.remoteAudioTrack = audioTrack;
        AudioTrack audioTrack2 = this.remoteAudioTrack;
        if (audioTrack2 == null) {
            log.i("No remote audio track in stream");
            return;
        }
        boolean enabled = audioTrack2.enabled();
        boolean z = this.remoteAudioEnabled;
        if (enabled != z) {
            this.remoteAudioTrack.setEnabled(z);
            CommsLogger commsLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Attached remote audio track for rendering. Id= ");
            outline107.append(this.remoteAudioTrack.id());
            commsLogger.i(outline107.toString());
            return;
        }
        CommsLogger commsLogger2 = log;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Remote Audio track already enabled for rendering. Id= ");
        outline1072.append(this.remoteAudioTrack.id());
        commsLogger2.i(outline1072.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void attachRemoteVideoTrack(VideoTrack videoTrack) {
        if (this.remoteRender == null) {
            log.i("No remote renderer. Ignoring call to attach.");
            return;
        }
        boolean z = this.remoteVideoTrack != videoTrack;
        this.remoteVideoTrack = videoTrack;
        VideoTrack videoTrack2 = this.remoteVideoTrack;
        if (videoTrack2 == null) {
            log.i("No remote video track in stream");
            return;
        }
        if (z) {
            videoTrack2.addRenderer(new VideoRenderer(this.remoteRender));
        } else {
            log.d("note a remote video track or renderer not supplied yet.");
        }
        if (this.renderRemoteVideo != this.remoteVideoTrack.enabled()) {
            this.remoteVideoTrack.setEnabled(this.renderRemoteVideo);
            CommsLogger commsLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Updated remote video track with desired state. Id= ");
            outline107.append(this.remoteVideoTrack.id());
            commsLogger.i(outline107.toString());
            return;
        }
        CommsLogger commsLogger2 = log;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Remote video track state already matches requested state. Id= ");
        outline1072.append(this.remoteVideoTrack.id());
        outline1072.append(" requested: ");
        GeneratedOutlineSupport1.outline184(outline1072, this.renderRemoteVideo, commsLogger2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeCaptureFormatInternal(int i, int i2, int i3) {
        if (this.videoCapable && !this.isError) {
            CommsLogger commsLogger = log;
            StringBuilder outline110 = GeneratedOutlineSupport1.outline110("changeCaptureFormat: ", i, ReactProperties.HereMapMarker.X, i2, "@");
            outline110.append(i3);
            commsLogger.d(outline110.toString());
            this.localAudioVideoShim.changeCapturerOutputFormat(i, i2, i3);
            return;
        }
        CommsLogger commsLogger2 = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to change capture format. Video: ");
        outline107.append(this.videoCapable);
        outline107.append(". Error : ");
        outline107.append(this.isError);
        commsLogger2.e(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkDeviceVideoHwCodecSupport() {
        if (!this.peerConnectionParameters.videoCapable) {
            this.isReducedResolutionOnDevice = false;
            return;
        }
        VideoCodecHwSupportChecker videoCodecHwSupportChecker = this.webRTCGlobalsProvider.getVideoCodecHwSupportChecker();
        if (!this.peerConnectionParameters.videoRequestEnabled) {
            this.isReducedResolutionOnDevice = !videoCodecHwSupportChecker.isH264OrVp8HwDecodeSupported();
        } else {
            this.isReducedResolutionOnDevice = !videoCodecHwSupportChecker.isH264OrVp8HwEncodeAndDecodeSupported();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeInternal() {
        int intValue;
        log.i("Closing peer connection.");
        this.statsTimer.cancel();
        PeerConnection peerConnection = this.peerConnection;
        if (peerConnection != null) {
            peerConnection.dispose();
            this.peerConnection = null;
        } else {
            MediaStream mediaStream = this.localMediaStream;
            if (mediaStream != null) {
                mediaStream.dispose();
                this.localVideoTrack = null;
                this.videoRtpSender = null;
                this.localMediaStream = null;
            }
        }
        this.cameraMetadataShim = null;
        stopVideoEffectPipelineInternal(false);
        log.d("Closing video source.");
        LocalAudioVideoShim localAudioVideoShim = this.localAudioVideoShim;
        if (localAudioVideoShim != null) {
            localAudioVideoShim.disposeLocalAudioVideoPipe();
            this.localAudioVideoShim = null;
        }
        this.followMeShim = null;
        List<Integer> readAndResetUnderrunCounts = WebRTCAudioUtilsShim.readAndResetUnderrunCounts();
        if (readAndResetUnderrunCounts != null && readAndResetUnderrunCounts.size() > 0 && (intValue = ((Integer) Collections.max(readAndResetUnderrunCounts)).intValue()) > 0) {
            CommsLogger commsLogger = log;
            commsLogger.i("maxUnderrunCount= " + intValue);
            this.events.audioTrackUnderrunReport(intValue);
        }
        this.dataChannelMap.clear();
        this.queuedMessages.clear();
        this.applicationContext = null;
        log.i("Closing peer connection done.");
        this.events.onPeerConnectionClosed();
    }

    private MediaConstraints createAudioConstraints() {
        MediaConstraints.KeyValuePair keyValuePair;
        MediaConstraints mediaConstraints = new MediaConstraints();
        List<AcousticParams.Param> allParams = this.peerConnectionParameters.audioProcessingParams.getAllParams();
        PlatformVoIPSelection platformVoIPSelection = this.peerConnectionParameters.getPlatformVoIPSelection();
        boolean z = platformVoIPSelection == null || (!platformVoIPSelection.isEnableLibasp() && !platformVoIPSelection.isEnableDolbyDap());
        CommsLogger commsLogger = log;
        commsLogger.i("allow WebRTC ASP: " + z);
        for (AcousticParams.Param param : allParams) {
            AcousticParams.Constraint key = param.getKey();
            int ordinal = key.ordinal();
            if (ordinal == 0) {
                keyValuePair = new MediaConstraints.KeyValuePair(AUDIO_ECHO_CANCELLATION_CONSTRAINT, String.valueOf(param.isEnabled() && z));
            } else if (ordinal == 1) {
                keyValuePair = new MediaConstraints.KeyValuePair(AUDIO_AUTO_GAIN_CONTROL_CONSTRAINT, String.valueOf(param.isEnabled() && z));
            } else if (ordinal == 2) {
                keyValuePair = new MediaConstraints.KeyValuePair(AUDIO_HIGH_PASS_FILTER_CONSTRAINT, String.valueOf(param.isEnabled() && z));
            } else if (ordinal == 3) {
                keyValuePair = new MediaConstraints.KeyValuePair(AUDIO_NOISE_SUPPRESSION_CONSTRAINT, String.valueOf(param.isEnabled() && z));
            } else if (ordinal != 4) {
                CommsLogger commsLogger2 = log;
                commsLogger2.w("Ignoring unknown Acoustic param key: " + key);
                keyValuePair = null;
            } else {
                keyValuePair = new MediaConstraints.KeyValuePair(AMZ_NB_AUDIO_FILTER_CONSTRAINT, String.valueOf(param.isEnabled()));
            }
            if (keyValuePair != null) {
                mediaConstraints.mandatory.add(keyValuePair);
            }
        }
        CommsLogger commsLogger3 = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Created audio constraints: ");
        outline107.append(mediaConstraints.toString());
        commsLogger3.i(outline107.toString());
        return mediaConstraints;
    }

    private AudioTrack createAudioTrack() {
        GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport1.outline107("createAudioTrack: localAudioEnabled:"), this.localAudioEnabled, log);
        this.localAudioTrack = this.localAudioVideoShim.provideAudioTrack("ARDAMSa0", this.webRTCGlobalsProvider.getPeerConnectionFactoryWrapper(), this.audioConstraints);
        this.localAudioTrack.setEnabled(this.localAudioEnabled);
        return this.localAudioTrack;
    }

    private DataChannelProperties createDataChannel(int i, DataChannelConfiguration dataChannelConfiguration) {
        String label = dataChannelConfiguration.getLabel();
        String protocol = dataChannelConfiguration.getProtocol();
        if (label != null && !label.isEmpty()) {
            if (protocol == null) {
                protocol = "";
            } else if ("t140".equals(protocol) && !this.peerConnectionParameters.realTimeTextCapable) {
                log.i("Device does not have real time text capability");
                return null;
            }
            DataChannel.Init init = new DataChannel.Init();
            init.ordered = dataChannelConfiguration.isOrdered();
            init.protocol = protocol;
            init.maxRetransmits = dataChannelConfiguration.getMaxRetransmits();
            init.id = i;
            CommsLogger commsLogger = log;
            commsLogger.i("Trying to create data channel with label: " + label);
            DataChannel createDataChannel = this.peerConnection.createDataChannel(label, init);
            if (createDataChannel != null) {
                this.dataChannelMap.put(label, createDataChannel);
                registerDataChannelObserver(createDataChannel);
                DataChannelProperties dataChannelProperties = new DataChannelProperties(i, label, protocol);
                CommsLogger commsLogger2 = log;
                commsLogger2.i("Created a new data channel with label: " + label);
                return dataChannelProperties;
            }
            CommsLogger commsLogger3 = log;
            commsLogger3.e("Could not create data channel with label: " + label);
            return null;
        }
        log.w("DataChannel label is null or empty");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createFollowMeShim() {
        log.d("creating FollowMe Shim");
        this.followMeShim = new FollowMeShim(this.applicationContext);
        if (!this.followMeShim.bindToService()) {
            log.e("createFollowMeShim: failed to bind to mmcv service");
            this.followMeShim = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createMediaConstraintsInternal() {
        boolean hasLocalVideoCapability = this.localAudioVideoShim.hasLocalVideoCapability();
        if (this.videoCapable && !hasLocalVideoCapability) {
            log.w("Camera not available for this call.");
            this.events.onCameraError(CameraErrorCode.CAMERA_NOT_FOUND, "Camera not found at runtime");
        }
        this.videoCapable = (this.videoCapable && hasLocalVideoCapability) || this.renderRemoteVideoSupported;
        if (!this.videoCapable) {
            log.w("Device not video capable. Switch to audio only call.");
        } else {
            createVideoConstraints();
        }
        this.audioConstraints = createAudioConstraints();
        this.sdpMediaConstraints = createSdpConstraints();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaConstraints createPeerConnectionConstraints() {
        MediaConstraints mediaConstraints = new MediaConstraints();
        mediaConstraints.optional.add(new MediaConstraints.KeyValuePair(DTLS_SRTP_KEY_AGREEMENT_CONSTRAINT, this.peerConnectionParameters.getSrtpCryptoType() == SrtpCryptoType.DTLS ? "true" : PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE));
        mediaConstraints.optional.add(new MediaConstraints.KeyValuePair(GOOG_DSCP, "true"));
        return mediaConstraints;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createPeerConnectionInternal() {
        log.i("Create peer connection.");
        this.eventTracer.mark(EventTracerConfig.Event.Webrtc_create_peer_connection_internal);
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PCConstraints: ");
        outline107.append(this.pcConstraints.toString());
        commsLogger.d(outline107.toString());
        this.queuedRemoteCandidates = new LinkedList<>();
        this.rtcConfig = new PeerConnectionShim.RTCConfiguration(this.signalingParameters.iceServers);
        PeerConnectionShim.RTCConfiguration rTCConfiguration = this.rtcConfig;
        rTCConfiguration.tcpCandidatePolicy = PeerConnection.TcpCandidatePolicy.DISABLED;
        rTCConfiguration.bundlePolicy = getBundlePolicy();
        this.rtcConfig.rtcpMuxPolicy = getRtcpMuxPolicy();
        if ((this.peerConnectionParameters.relayOnlyIceTransport || relayOnlyIceTransportForDebugging()) && !this.signalingParameters.iceServers.isEmpty()) {
            log.i("relay only ICE transport is enabled.");
            this.rtcConfig.iceTransportsType = PeerConnection.IceTransportsType.RELAY;
        }
        PeerConnectionShim.RTCConfiguration rTCConfiguration2 = this.rtcConfig;
        rTCConfiguration2.continualGatheringPolicy = PeerConnection.ContinualGatheringPolicy.GATHER_ONCE;
        rTCConfiguration2.keyType = PeerConnection.KeyType.ECDSA;
        rTCConfiguration2.iceCandidatePoolSize = this.videoCapable ? 4 : 2;
        this.rtcConfig.audioJitterBufferMaxPackets = 30;
        this.peerConnection = this.webRTCGlobalsProvider.getPeerConnectionFactoryWrapper().createPeerConnection(this.rtcConfig, this.pcConstraints, this.pcObserver);
        setupDataChannels();
        LocalAudioVideoShim.setTraceAndDebugLogLevel(Logging.TraceLevel.TRACE_DEFAULT, Logging.Severity.LS_INFO);
        if (!this.warmupClient) {
            prepareMedia(false);
        }
        this.peerConnection.addStream(this.localMediaStream);
        this.eventTracer.mark(EventTracerConfig.Event.Webrtc_peer_connection_created);
        this.audioRtpSender = findRtpSender(this.localAudioTrack);
        if (this.videoCapable) {
            this.videoRtpSender = findRtpSender(this.localVideoTrack);
            if (this.peerConnectionParameters.getVideoMaxBitrate() > 0) {
                this.isPendingVideoMaxSendBitrateSet = true;
            }
            if (this.peerConnectionParameters.constraintVideoUntilIceConnection) {
                this.videoConstraintsManager.setConstraintsUntilFirstIceConn(this.peerConnectionParameters.getVideoConstraintsUntilIceConnection());
            }
            VideoConstraints currentVideoConstraints = this.videoConstraintsManager.getCurrentVideoConstraints();
            if (currentVideoConstraints != null) {
                List<Pair<String, Integer>> convertVideoConstraintToMediaConstraintList = VideoConstraintsManager.convertVideoConstraintToMediaConstraintList(currentVideoConstraints);
                CommsLogger commsLogger2 = log;
                commsLogger2.i("Updating VideoConstraints due to constraintVideoUntilIceConnection. Constaints: " + currentVideoConstraints);
                this.localAudioVideoShim.updateVideoConstraints(this.peerConnection, convertVideoConstraintToMediaConstraintList);
            }
        }
        this.warmupClient = false;
        log.i("Peer connection created.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaConstraints createSdpConstraints() {
        MediaConstraints mediaConstraints = new MediaConstraints();
        mediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("OfferToReceiveAudio", "true"));
        if ((this.videoCapable && this.remoteVideoCapable) || this.peerConnectionParameters.loopback) {
            mediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("OfferToReceiveVideo", "true"));
        } else {
            mediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("OfferToReceiveVideo", PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE));
        }
        return mediaConstraints;
    }

    private void createVideoConstraints() {
        this.preferredVideoCodec = "VP8";
        if (this.peerConnectionParameters.videoCodec != null && this.peerConnectionParameters.videoCodec.equals("H264")) {
            this.preferredVideoCodec = "H264";
        }
        GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport1.outline107("Preferred video codec: "), this.preferredVideoCodec, log);
        if (this.isReducedResolutionOnDevice) {
            VideoConstraints maxVideoConstraintsOnReducedResolution = this.peerConnectionParameters.getMaxVideoConstraintsOnReducedResolution();
            CommsLogger commsLogger = log;
            commsLogger.v("video capable but no hw codec supported. videoConstraints supplied= " + maxVideoConstraintsOnReducedResolution);
            this.videoConstraintsManager.setReducedVideoConstraints(maxVideoConstraintsOnReducedResolution);
            this.peerConnectionParameters.videoWidth = maxVideoConstraintsOnReducedResolution.getVideoWidth();
            this.peerConnectionParameters.videoHeight = maxVideoConstraintsOnReducedResolution.getVideoHeight();
            this.peerConnectionParameters.videoFps = maxVideoConstraintsOnReducedResolution.getVideoFps();
        }
        this.localAudioVideoShim.createVideoConstraintsIfSupported(this.peerConnectionParameters.videoWidth, this.peerConnectionParameters.videoHeight, this.peerConnectionParameters.videoFps, this.peerConnectionParameters.videoCodecHwAcceleration, this.preferredVideoCodec);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disposeCachedLocalVideoTrack() {
        if (this.localVideoTrack == null) {
            return;
        }
        log.i("disposeCachedLocalVideoTrack");
        this.localMediaStream.removeTrack(this.localVideoTrack);
        this.localVideoTrack.dispose();
        this.localVideoTrack = null;
        this.videoRtpSender = null;
        this.localAudioVideoShim.disposeLocalVideoSourcePipe();
        log.i("disposeCachedLocalVideoTrack done.");
    }

    private void drainCandidates() {
        if (this.queuedRemoteCandidates != null) {
            CommsLogger commsLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Add ");
            outline107.append(this.queuedRemoteCandidates.size());
            outline107.append(" remote candidates");
            commsLogger.d(outline107.toString());
            Iterator<IceCandidate> it2 = this.queuedRemoteCandidates.iterator();
            while (it2.hasNext()) {
                this.peerConnection.addIceCandidate(it2.next());
            }
            this.queuedRemoteCandidates = null;
        }
    }

    private RtpSender findRtpSender(MediaStreamTrack mediaStreamTrack) {
        if (mediaStreamTrack == null) {
            log.d("No rtpSender. local track null");
            return null;
        }
        for (RtpSender rtpSender : this.peerConnection.getSenders()) {
            if (rtpSender.track().kind().equals(mediaStreamTrack.kind())) {
                return rtpSender;
            }
        }
        CommsLogger commsLogger = log;
        commsLogger.d("RtpSender for track type not found. " + mediaStreamTrack);
        return null;
    }

    private PeerConnection.BundlePolicy getBundlePolicy() {
        if (!this.initiator) {
            log.i("Setting BundlePolicy to MAXCOMPAT since this is not an initiator of the call");
            return PeerConnection.BundlePolicy.MAXCOMPAT;
        }
        PeerConnectionParameters peerConnectionParameters = this.peerConnectionParameters;
        if (peerConnectionParameters != null && peerConnectionParameters.getBundlePolicy() != null) {
            CommsLogger commsLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Setting BundlePolicy to ");
            outline107.append(this.peerConnectionParameters.getBundlePolicy().name());
            commsLogger.i(outline107.toString());
            int ordinal = this.peerConnectionParameters.getBundlePolicy().ordinal();
            if (ordinal == 0) {
                return PeerConnection.BundlePolicy.MAXBUNDLE;
            }
            if (ordinal == 1) {
                return PeerConnection.BundlePolicy.MAXCOMPAT;
            }
            if (ordinal == 2) {
                return PeerConnection.BundlePolicy.BALANCED;
            }
        }
        log.w("Unknown BundlePolicy, will fall back to MAXBUNDLE");
        return PeerConnection.BundlePolicy.MAXBUNDLE;
    }

    private PeerConnection.RtcpMuxPolicy getRtcpMuxPolicy() {
        if (!this.initiator) {
            log.i("Setting RtcpMuxPolicy to NEGOTIATE since this is not an initiator of the call");
            return PeerConnection.RtcpMuxPolicy.NEGOTIATE;
        }
        PeerConnectionParameters peerConnectionParameters = this.peerConnectionParameters;
        if (peerConnectionParameters != null && peerConnectionParameters.getRtcpMuxPolicy() != null) {
            CommsLogger commsLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Setting RtcpMuxPolicy to ");
            outline107.append(this.peerConnectionParameters.getRtcpMuxPolicy().name());
            commsLogger.i(outline107.toString());
            return this.peerConnectionParameters.getRtcpMuxPolicy() == RtcpMuxPolicy.REQUIRE ? PeerConnection.RtcpMuxPolicy.REQUIRE : PeerConnection.RtcpMuxPolicy.NEGOTIATE;
        }
        log.i("Setting RtcpMuxPolicy to default NEGOTIATE");
        return PeerConnection.RtcpMuxPolicy.NEGOTIATE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getStats() {
        PeerConnection peerConnection = this.peerConnection;
        if (peerConnection == null || this.isError || peerConnection.getStats(new StatsObserver() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.9
            @Override // org.webrtc.StatsObserver
            public void onComplete(StatsReport[] statsReportArr) {
                PeerConnectionClient.this.events.onPeerConnectionStatsReady(statsReportArr);
            }
        }, null)) {
            return;
        }
        log.e("getStats() returns false!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLocalOrRemoteSDPSet() {
        if (this.peerConnection == null || this.isError) {
            return;
        }
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SDP onSetSuccess signallingState = ");
        outline107.append(this.signalingState);
        outline107.append(" isOfferer=");
        outline107.append(this.isOfferer);
        commsLogger.d(outline107.toString());
        boolean z = true;
        if (this.isOfferer) {
            PeerConnection.SignalingState signalingState = this.signalingState;
            if (signalingState == PeerConnection.SignalingState.HAVE_LOCAL_OFFER) {
                log.i("Local SDP offer set Successfully");
                this.events.onLocalDescription(this.peerConnection.getLocalDescription());
            } else if (signalingState == PeerConnection.SignalingState.HAVE_REMOTE_PRANSWER) {
                log.i("Remote SDP provisional answer set successfully");
                drainCandidates();
                if (!this.resetRemoteSignaling) {
                    reportMediaStateFromSDP(this.peerConnection.getRemoteDescription().description, Call.Side.Remote);
                } else {
                    this.resetRemoteSignaling = false;
                }
                applyMaxBitrateForVideoSender();
            } else {
                log.i("Remote SDP answer set successfully");
                drainCandidates();
                setSslRole();
                this.isOfferer = false;
                if (!this.resetRemoteSignaling) {
                    reportMediaStateFromSDP(this.peerConnection.getRemoteDescription().description, Call.Side.Remote);
                } else {
                    this.resetRemoteSignaling = false;
                }
                RtpSender rtpSender = this.audioRtpSender;
                if (rtpSender == null || rtpSender.dtmf() == null || !this.audioRtpSender.dtmf().canInsertDtmf()) {
                    z = false;
                }
                this.events.onSignalingDone(z);
                applyMaxBitrateForVideoSender();
            }
        } else if (this.signalingState == PeerConnection.SignalingState.HAVE_REMOTE_OFFER) {
            log.i("Remote SDP offer set successfully");
            createAnswer();
            reportMediaStateFromSDP(this.peerConnection.getRemoteDescription().description, Call.Side.Remote);
        } else {
            log.i("Local SDP answer set successfully");
            this.events.onLocalDescription(this.peerConnection.getLocalDescription());
            drainCandidates();
            setSslRole();
            RtpSender rtpSender2 = this.audioRtpSender;
            if (rtpSender2 == null || rtpSender2.dtmf() == null || !this.audioRtpSender.dtmf().canInsertDtmf()) {
                z = false;
            }
            this.events.onSignalingDone(z);
            applyMaxBitrateForVideoSender();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareMedia(boolean z) {
        GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport1.outline107("prepareMedia callVideoCapability - "), this.callVideoCapability, log);
        this.localMediaStream = this.webRTCGlobalsProvider.getPeerConnectionFactoryWrapper().createLocalMediaStream("ARDAMS");
        this.localMediaStream.addTrack(createAudioTrack());
        if (!this.videoCapable || !this.callVideoCapability) {
            return;
        }
        prepareVideo(z);
    }

    private void prepareVideo(boolean z) {
        log.i("prepareVideo");
        if (!provideVideoTrack()) {
            if (z) {
                log.w("Failed to create video track during warmup. Try later.");
            } else if (!this.initiator) {
                log.i("Failed to create video track for the incoming call. Retry when user accepts the call.");
                this.retryMediaInit = true;
            } else {
                log.w("Failed to get access to camera");
                this.renderLocalVideo = false;
                this.events.onCameraError(CameraErrorCode.CAMERA_OPEN_FAILED, "Failed to get access to camera");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processRemoteDescriptionInternal(Sdp sdp, Sdp.Type type) {
        SessionDescription.Type type2;
        log.i("processRemoteDescriptionInternal");
        if (this.retryMediaInit) {
            this.cachedRemoteOffer = sdp;
            log.i("processRemoteDescriptionInternal: return early as media is not initialized");
            return;
        }
        int ordinal = type.ordinal();
        if (ordinal == 0) {
            type2 = SessionDescription.Type.OFFER;
        } else if (ordinal == 1) {
            type2 = SessionDescription.Type.PRANSWER;
        } else if (ordinal != 2) {
            reportError("Invalid SDP type");
            return;
        } else {
            type2 = SessionDescription.Type.ANSWER;
        }
        CommsLogger commsLogger = log;
        commsLogger.i("setting remote SDP " + type);
        setRemoteDescription(type2, sdp);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processRemoteStreamTracks(MediaStream mediaStream) {
        if (mediaStream.audioTracks.size() <= 1 && mediaStream.videoTracks.size() <= 1) {
            if (mediaStream.audioTracks.size() == 1) {
                attachRemoteAudioTrack(mediaStream.audioTracks.get(0));
            } else {
                attachRemoteAudioTrack(null);
                log.i("No remote audio track attached for rendering.");
            }
            if (mediaStream.videoTracks.size() == 1) {
                attachRemoteVideoTrack(mediaStream.videoTracks.get(0));
                return;
            }
            attachRemoteVideoTrack(null);
            log.i("No remote video track attached for rendering.");
            return;
        }
        reportError("Weird-looking stream: " + mediaStream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean provideVideoTrack() {
        PeerConnectionFactoryWrapper peerConnectionFactoryWrapper = this.webRTCGlobalsProvider.getPeerConnectionFactoryWrapper();
        if (Strings.isNullOrEmpty(this.peerConnectionParameters.videoFileInjectionPath)) {
            if (this.peerConnectionParameters.enableCameraMetricsReporting) {
                log.i("CameraMetricsReporting is ON");
                this.localVideoTrack = this.localAudioVideoShim.provideLocalVideoTrack(this.eglContext, peerConnectionFactoryWrapper, this.renderLocalVideo, "ARDAMSv0", this.peerConnection, new Handler(), this.cameraMetadataObserver);
            } else {
                log.i("CameraMetricsReporting is OFF");
                this.localVideoTrack = this.localAudioVideoShim.provideLocalVideoTrack(this.eglContext, peerConnectionFactoryWrapper, this.renderLocalVideo, "ARDAMSv0", this.peerConnection, null, null);
            }
        } else {
            log.i("Video file injection is ON, initializing file video streaming.");
            this.localVideoTrack = this.localAudioVideoShim.provideLocalFileBasedVideoTrack(peerConnectionFactoryWrapper, this.renderLocalVideo, "ARDAMSv0", this.peerConnectionParameters.videoFileInjectionPath);
        }
        if (this.localVideoTrack != null) {
            log.i("adding local video track to local media stream");
            this.localMediaStream.addTrack(this.localVideoTrack);
            if (this.localRender != null) {
                CommsLogger commsLogger = log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("attaching renderer to local video track. Renderer= ");
                outline107.append(this.localRender);
                commsLogger.i(outline107.toString());
                this.localVideoTrack.addRenderer(new VideoRenderer(this.localRender));
            }
        } else {
            log.i("cannot provide video track, localVideoTrack could not be created.");
        }
        return this.localVideoTrack != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queueMessage(String str, DataChannel.Buffer buffer) {
        List<DataChannel.Buffer> list = this.queuedMessages.get(str);
        if (list == null) {
            list = new ArrayList<>();
            this.queuedMessages.put(str, list);
        }
        if (this.queuedMessages.size() >= 50) {
            log.w("Attempt to queue more than the max number of messages. The oldest will be deleted.");
            list.remove(0);
        }
        list.add(buffer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerDataChannelObserver(DataChannel dataChannel) {
        dataChannel.registerObserver(new DataChannelObserver(dataChannel, this.events, this.executor));
    }

    private boolean relayOnlyIceTransportForDebugging() {
        String systemProperty = SystemProperty.getSystemProperty(WEBRTC_RELAY_ONLY_ICE_PROPERTY_NAME, WEBRTC_RELAY_ONLY_ICE_PROPERTY_DISABLE);
        return systemProperty != null && systemProperty.equalsIgnoreCase(WEBRTC_RELAY_ONLY_ICE_PROPERTY_ENABLE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportError(String str) {
        CommsLogger commsLogger = log;
        commsLogger.e("Peerconnection error: " + str);
        if (!this.isError) {
            this.events.onPeerConnectionError(str);
            this.isError = true;
        }
    }

    private void reportMediaStateFromSDP(String str, Call.Side side) {
        Sdp sdp = new Sdp(str);
        Set<String> activeMediaTypes = sdp.getActiveMediaTypes();
        Map.Entry<Boolean, MediaStateChangeTrigger> videoSuspendedData = sdp.getVideoSuspendedData();
        MediaStateChangeTrigger mediaStateChangeTrigger = side == Call.Side.Local ? MediaStateChangeTrigger.USER_REQUEST : MediaStateChangeTrigger.REMOTE_USER_REQUEST;
        if (videoSuspendedData != null && side == Call.Side.Remote && videoSuspendedData.getKey().booleanValue()) {
            mediaStateChangeTrigger = videoSuspendedData.getValue();
        }
        this.events.onMediaStateChanged(side, activeMediaTypes.contains("audio"), activeMediaTypes.contains("video"), mediaStateChangeTrigger);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetRemoteDescriptionInternal() {
        if (this.peerConnection == null || this.isError) {
            return;
        }
        GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport1.outline107("resetRemoteDescriptionInternal: initiator: "), this.initiator, log);
        this.resetRemoteSignaling = true;
        this.remoteSdpSetInProgress.incrementAndGet();
        Sdp sdp = new Sdp(this.peerConnection.getRemoteDescription().description);
        if (log.isLoggable(LogLevel.Debug)) {
            CommsLogger commsLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Reset remote Description : previous remote sdp ");
            outline107.append(sdp.toString());
            commsLogger.ds(outline107.toString());
        }
        String dTLSRole = sdp.getDTLSRole();
        if (dTLSRole != null && dTLSRole.equals("actpass")) {
            if (this.sslRole == SslRole.SERVER) {
                sdp.modifyDTLSRole("active");
            } else {
                sdp.modifyDTLSRole("passive");
            }
            if (log.isLoggable(LogLevel.Debug)) {
                CommsLogger commsLogger2 = log;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("New remote Description :  ");
                outline1072.append(sdp.toString());
                commsLogger2.ds(outline1072.toString());
            }
        }
        this.peerConnection.setRemoteDescription(this.remoteSdpObserver, new SessionDescription(SessionDescription.Type.ANSWER, sdp.toString()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sanitizeMedia() {
        GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport1.outline107("sanitizeMedia callVideoCapability - "), this.callVideoCapability, log);
        if (this.callVideoCapability) {
            VideoTrack videoTrack = this.localVideoTrack;
            if (videoTrack == null) {
                log.i("sanitizeMedia prepareVideo.");
                prepareVideo(false);
                return;
            }
            videoTrack.setEnabled(this.renderLocalVideo);
            return;
        }
        disposeCachedLocalVideoTrack();
    }

    private void setRemoteDescription(SessionDescription.Type type, Sdp sdp) {
        if (this.peerConnection == null || this.isError) {
            return;
        }
        CommsLogger commsLogger = log;
        commsLogger.d("setRemoteDescription type: " + type);
        sdp.preferCodec(AUDIO_CODEC_OPUS, true);
        if (this.peerConnectionParameters.audioStartBitrateInKbps > 0) {
            sdp.setBitrate(AUDIO_CODEC_OPUS, false, AUDIO_CODEC_PARAM_BITRATE, this.peerConnectionParameters.audioStartBitrateInKbps, false);
        } else {
            log.d("audio start bitrate not set");
        }
        if (this.videoCapable) {
            sdp.preferCodec(this.preferredVideoCodec, false);
            if (this.peerConnectionParameters.videoStartBitrate > 0) {
                sdp.setBitrate("VP8", true, VIDEO_CODEC_PARAM_START_BITRATE, this.peerConnectionParameters.videoStartBitrate, false).setBitrate("H264", true, VIDEO_CODEC_PARAM_START_BITRATE, this.peerConnectionParameters.videoStartBitrate, false);
            } else {
                log.d("video start bitrate not set");
            }
            int i = this.remoteRequestVideoMaxSendBitrate;
            int stripAndReturnBitrateFromVideoCodecFmtp = sdp.stripAndReturnBitrateFromVideoCodecFmtp("VP8", VIDEO_CODEC_PARAM_MAX_BITRATE, Integer.MAX_VALUE);
            int stripAndReturnBitrateFromVideoCodecFmtp2 = sdp.stripAndReturnBitrateFromVideoCodecFmtp("H264", VIDEO_CODEC_PARAM_MAX_BITRATE, Integer.MAX_VALUE);
            this.remoteRequestVideoMaxSendBitrate = stripAndReturnBitrateFromVideoCodecFmtp2;
            VideoCodecHwSupportChecker videoCodecHwSupportChecker = this.webRTCGlobalsProvider.getVideoCodecHwSupportChecker();
            if ("VP8".equals(this.preferredVideoCodec) || !videoCodecHwSupportChecker.isH264HwEncodeSupported() || (stripAndReturnBitrateFromVideoCodecFmtp2 == Integer.MAX_VALUE && !sdp.isCodecPresent("H264"))) {
                this.remoteRequestVideoMaxSendBitrate = stripAndReturnBitrateFromVideoCodecFmtp;
            }
            int i2 = this.remoteRequestVideoMaxSendBitrate;
            if (i != i2 && i2 != Integer.MAX_VALUE) {
                CommsLogger commsLogger2 = log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("remote had requested video max bitrate= ");
                outline107.append(this.remoteRequestVideoMaxSendBitrate);
                commsLogger2.i(outline107.toString());
                this.isPendingVideoMaxSendBitrateSet = true;
            }
            VideoConstraints currentVideoConstraints = this.videoConstraintsManager.getCurrentVideoConstraints();
            this.videoConstraintsManager.setRemoteRequestedVideoConstraints(sdp.getVideoConstraints());
            if ((!sdp.isCodecPresent("H264") && this.peerConnectionParameters.isReduceVideoResolutionOnNoH264Remote()) || this.isReducedResolutionOnDevice) {
                this.videoConstraintsManager.setReducedVideoConstraints(this.peerConnectionParameters.getMaxVideoConstraintsOnReducedResolution());
            } else {
                this.videoConstraintsManager.setReducedVideoConstraints(null);
            }
            Map<Integer, VideoConstraints> supportedVideoResolutions = this.peerConnectionParameters.getSupportedVideoResolutions();
            if (supportedVideoResolutions != null) {
                this.videoConstraintsManager.setDynamicVideoConstraints(sdp.getDynamicVideoConstraints(supportedVideoResolutions));
            }
            VideoConstraints currentVideoConstraints2 = this.videoConstraintsManager.getCurrentVideoConstraints();
            if (currentVideoConstraints2.compareTo(currentVideoConstraints) != 0) {
                List<Pair<String, Integer>> convertVideoConstraintToMediaConstraintList = VideoConstraintsManager.convertVideoConstraintToMediaConstraintList(currentVideoConstraints2);
                CommsLogger commsLogger3 = log;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Updating media constraints to: ");
                outline1072.append(currentVideoConstraints2.toString());
                commsLogger3.i(outline1072.toString());
                this.localAudioVideoShim.updateVideoConstraints(this.peerConnection, convertVideoConstraintToMediaConstraintList);
            }
        }
        this.remoteSdpSetInProgress.incrementAndGet();
        this.peerConnection.setRemoteDescription(this.remoteSdpObserver, new SessionDescription(type, sdp.toString()));
    }

    private boolean setRtpSenderMaxBitrate(RtpSender rtpSender, int i) {
        LinkedList<RtpParameters.Encoding> linkedList;
        if (rtpSender != null && this.peerConnection != null && !this.isError) {
            RtpParameters parameters = rtpSender.getParameters();
            if (parameters != null && (linkedList = parameters.encodings) != null && linkedList.size() != 0) {
                int i2 = i * 1000;
                Iterator<RtpParameters.Encoding> it2 = parameters.encodings.iterator();
                while (it2.hasNext()) {
                    it2.next().maxBitrateBps = Integer.valueOf(i2);
                }
                if (!rtpSender.setParameters(parameters)) {
                    log.w("Could not set max bitrate to video rtp sender!");
                    return false;
                }
                CommsLogger commsLogger = log;
                commsLogger.i("set setRtpSenderMaxBitrate in bps= " + i2);
                return true;
            }
            log.i("RtpSender parameters is not ready");
            return false;
        }
        log.i("chosen RTP sender not assigned or peerconnection error");
        return false;
    }

    private void setSslRole() {
        if (this.sslRole != null) {
            return;
        }
        this.sslRole = SslRole.getLocalSslRole(this.initiator, new Sdp(this.peerConnection.getLocalDescription().description).getDTLSRole(), new Sdp(this.peerConnection.getRemoteDescription().description).getDTLSRole());
    }

    private void setupDataChannels() {
        if (this.initiator) {
            if (this.peerConnectionParameters.dataChannelParams != null) {
                ArrayList arrayList = new ArrayList();
                for (DataChannelConfiguration dataChannelConfiguration : this.peerConnectionParameters.dataChannelParams) {
                    int i = this.dataChannelId;
                    this.dataChannelId = i + 1;
                    DataChannelProperties createDataChannel = createDataChannel(i, dataChannelConfiguration);
                    if (createDataChannel != null) {
                        arrayList.add(createDataChannel);
                    }
                }
                if (arrayList.isEmpty()) {
                    return;
                }
                this.events.onDataChannelsCreated(arrayList);
                return;
            }
            log.i("Data channel params is null");
            return;
        }
        log.i("Not creating data channels since this is not an initiator of the call");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startVideoSource() {
        this.localAudioVideoShim.restartLocalVideoSource();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopVideoEffectPipelineInternal(boolean z) {
        VideoEffectShim videoEffectShim = this.videoEffectShim;
        if (videoEffectShim == null || this.isError) {
            return;
        }
        if (z) {
            this.videoEffectTransitionListener.onAbort();
        } else {
            videoEffectShim.endVideoEffect();
        }
        this.videoEffectShim = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopVideoSource() {
        log.d("stopVideoSource");
        stopVideoEffectPipelineInternal(true);
        this.localAudioVideoShim.stopLocalVideoSource();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchCameraInternal(CameraSwitchHandlerShim cameraSwitchHandlerShim, String str) {
        if (this.videoCapable && !this.isError) {
            this.localAudioVideoShim.switchCamera(cameraSwitchHandlerShim, str);
            return;
        }
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to switch camera. Video: ");
        outline107.append(this.videoCapable);
        outline107.append(". Error : ");
        outline107.append(this.isError);
        commsLogger.e(outline107.toString());
    }

    public void addRemoteIceCandidate(final IceCandidate iceCandidate) {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":addRemoteIceCandidate"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.13
            @Override // java.lang.Runnable
            public void run() {
                PeerConnectionClient.log.i("addRemoteIceCandidate");
                if (PeerConnectionClient.this.peerConnection == null || PeerConnectionClient.this.isError) {
                    return;
                }
                if (PeerConnectionClient.this.queuedRemoteCandidates != null) {
                    PeerConnectionClient.this.queuedRemoteCandidates.add(iceCandidate);
                } else {
                    PeerConnectionClient.this.peerConnection.addIceCandidate(iceCandidate);
                }
            }
        });
    }

    public void attachCall(final PeerConnectionParameters peerConnectionParameters, final boolean z, final boolean z2, final boolean z3, final EventTracer eventTracer) {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":attachCall"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.24
            @Override // java.lang.Runnable
            public void run() {
                if (PeerConnectionClient.this.isError) {
                    return;
                }
                if (PeerConnectionClient.this.webRTCGlobalsProvider.getPeerConnectionFactoryWrapper() == null) {
                    PeerConnectionClient.log.e("Peerconnection factory is null");
                    return;
                }
                PeerConnectionClient.log.i("attaching call");
                PeerConnectionClient.this.eventTracer = eventTracer;
                PeerConnectionClient.this.peerConnectionParameters = peerConnectionParameters;
                PeerConnectionClient peerConnectionClient = PeerConnectionClient.this;
                peerConnectionClient.videoCapable = peerConnectionClient.peerConnectionParameters.isVideoCapable();
                PeerConnectionClient.this.checkDeviceVideoHwCodecSupport();
                PeerConnectionClient peerConnectionClient2 = PeerConnectionClient.this;
                peerConnectionClient2.sdpMediaConstraints = peerConnectionClient2.createSdpConstraints();
                PeerConnectionClient.this.callVideoCapability = z;
                PeerConnectionClient.this.initiator = z3;
                PeerConnectionClient.this.renderLocalVideo = z2;
                PeerConnectionClient.this.eventTracer.markHistoric(EventTracerConfig.Event.Webrtc_media_warmup_completed, PeerConnectionClient.this.warmupCompletedTimestampERT, PeerConnectionClient.this.warmupCompletedTimestampEpoch);
                PeerConnectionClient.this.sanitizeMedia();
                if (PeerConnectionClient.this.videoCapable) {
                    return;
                }
                PeerConnectionClient.this.isPendingVideoMaxSendBitrateSet = false;
            }
        });
    }

    public void attachVideoRenderers(final VideoRenderer.Callbacks callbacks, final VideoRenderer.Callbacks callbacks2) {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, "attachVideoRenderer"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.4
            @Override // java.lang.Runnable
            public void run() {
                if (PeerConnectionClient.this.isError) {
                    PeerConnectionClient.log.w("is inError state, can't update renderers");
                    return;
                }
                PeerConnectionClient.this.localRender = callbacks;
                if (PeerConnectionClient.this.localVideoTrack != null && callbacks != null) {
                    PeerConnectionClient.this.localVideoTrack.addRenderer(new VideoRenderer(callbacks));
                }
                PeerConnectionClient.this.remoteRender = callbacks2;
                if (PeerConnectionClient.this.remoteSdpSetInProgress.get() > 0 || PeerConnectionClient.this.remoteMediaStream == null || PeerConnectionClient.this.remoteMediaStream.videoTracks.size() != 1) {
                    return;
                }
                PeerConnectionClient peerConnectionClient = PeerConnectionClient.this;
                peerConnectionClient.attachRemoteVideoTrack(peerConnectionClient.remoteMediaStream.videoTracks.get(0));
            }
        });
    }

    public void changeCaptureFormat(final int i, final int i2, final int i3) {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":changeCaptureFormat"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.20
            @Override // java.lang.Runnable
            public void run() {
                PeerConnectionClient.this.changeCaptureFormatInternal(i, i2, i3);
            }
        });
    }

    public void close() {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":close"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.3
            @Override // java.lang.Runnable
            public void run() {
                PeerConnectionClient.this.closeInternal();
            }
        });
    }

    public void createAnswer() {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":createAnswer"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.12
            @Override // java.lang.Runnable
            public void run() {
                if (PeerConnectionClient.this.peerConnection == null || PeerConnectionClient.this.isError) {
                    return;
                }
                PeerConnectionClient.log.i("PC create ANSWER");
                PeerConnectionClient.this.peerConnection.createAnswer(PeerConnectionClient.this.localSdpObserver, PeerConnectionClient.this.sdpMediaConstraints);
            }
        });
    }

    public void createOffer(final boolean z) {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":createOffer"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.11
            @Override // java.lang.Runnable
            public void run() {
                MediaConstraints mediaConstraints;
                if (PeerConnectionClient.this.peerConnection == null || PeerConnectionClient.this.isError) {
                    return;
                }
                GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport1.outline107("PC Create OFFER, icRestart : "), z, PeerConnectionClient.log);
                PeerConnectionClient.this.isOfferer = true;
                if (z) {
                    mediaConstraints = PeerConnectionClient.this.createSdpConstraints();
                    mediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("IceRestart", "true"));
                } else {
                    mediaConstraints = PeerConnectionClient.this.sdpMediaConstraints;
                }
                PeerConnectionClient.this.peerConnection.createOffer(PeerConnectionClient.this.localSdpObserver, mediaConstraints);
            }
        });
    }

    public void createPeerConnection(SignalingParameters signalingParameters) {
        this.signalingParameters = signalingParameters;
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":createPeerConnection"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.2
            @Override // java.lang.Runnable
            public void run() {
                if (PeerConnectionClient.this.peerConnection != null) {
                    PeerConnectionClient.this.reportError("Request to create new peer connection when one already exists!");
                } else if (PeerConnectionClient.this.isError) {
                } else {
                    if (PeerConnectionClient.this.webRTCGlobalsProvider.getPeerConnectionFactoryWrapper() == null) {
                        PeerConnectionClient.this.reportError("Peerconnection factory is nul");
                        return;
                    }
                    PeerConnectionClient.this.eventTracer.mark(EventTracerConfig.Event.Webrtc_create_peer_connection);
                    GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport1.outline107("createPeerConnection warmupClient - "), PeerConnectionClient.this.warmupClient, PeerConnectionClient.log);
                    if (!PeerConnectionClient.this.warmupClient) {
                        if (PeerConnectionClient.this.followMeEnabled) {
                            PeerConnectionClient.this.createFollowMeShim();
                        }
                        PeerConnectionClient peerConnectionClient = PeerConnectionClient.this;
                        Context context = peerConnectionClient.applicationContext;
                        boolean z = PeerConnectionClient.this.peerConnectionParameters.captureToTexture;
                        boolean z2 = PeerConnectionClient.this.peerConnectionParameters.camera1ApiPreferred;
                        boolean z3 = PeerConnectionClient.this.peerConnectionParameters.camera2ApiForced;
                        boolean z4 = PeerConnectionClient.this.peerConnectionParameters.updateCameraHalFramerateAllowed;
                        boolean z5 = PeerConnectionClient.this.peerConnectionParameters.provideCallingServiceHalParameter;
                        PeerConnectionClient peerConnectionClient2 = PeerConnectionClient.this;
                        peerConnectionClient.localAudioVideoShim = new LocalAudioVideoShim(context, z, z2, z3, z4, z5, peerConnectionClient2, peerConnectionClient2.followMeShim);
                        PeerConnectionClient.this.localAudioVideoShim.setMaxVideoConstraints(PeerConnectionClient.this.peerConnectionParameters.getVideoWidth(), PeerConnectionClient.this.peerConnectionParameters.getVideoHeight(), PeerConnectionClient.this.peerConnectionParameters.getVideoFps(), PeerConnectionClient.this.peerConnection);
                        PeerConnectionClient.this.createMediaConstraintsInternal();
                    }
                    PeerConnectionClient peerConnectionClient3 = PeerConnectionClient.this;
                    peerConnectionClient3.pcConstraints = peerConnectionClient3.createPeerConnectionConstraints();
                    PeerConnectionClient.this.createPeerConnectionInternal();
                }
            }
        });
    }

    public void dataChannelSendData(@NonNull final DataChannelDTO dataChannelDTO) {
        if (dataChannelDTO != null) {
            this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":dataChannelSendData"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.29
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionClient.this.peerConnection == null) {
                        PeerConnectionClient.log.w("Cannot send data with no peer connection");
                        return;
                    }
                    String label = dataChannelDTO.getLabel();
                    DataChannel.Buffer buffer = new DataChannel.Buffer(ByteBuffer.wrap(dataChannelDTO.getData()), dataChannelDTO.isBinary());
                    DataChannel dataChannel = (DataChannel) PeerConnectionClient.this.dataChannelMap.get(label);
                    if (dataChannel == null) {
                        PeerConnectionClient.log.i("Queueing the message because the data channel is not connected yet.");
                        PeerConnectionClient.this.queueMessage(label, buffer);
                        return;
                    }
                    DataChannel.State state = dataChannel.state();
                    if (state == DataChannel.State.CONNECTING) {
                        PeerConnectionClient.this.queueMessage(label, buffer);
                    } else if (state != DataChannel.State.OPEN) {
                        CommsLogger commsLogger = PeerConnectionClient.log;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Could not send data, since data channel state is ");
                        outline107.append(state.name());
                        commsLogger.w(outline107.toString());
                    } else {
                        dataChannel.send(buffer);
                    }
                }
            });
            return;
        }
        throw new IllegalArgumentException("dataChannelDTO is null");
    }

    public void enableStatsEvents(boolean z, int i) {
        if (z) {
            try {
                this.statsTimer.cancel();
                this.statsTimer = new Timer();
                this.statsTimer.schedule(new TimerTask() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.10
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        PeerConnectionClient.this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), PeerConnectionClient.this.logTag, ":getStats"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.10.1
                            @Override // java.lang.Runnable
                            public void run() {
                                PeerConnectionClient.this.getStats();
                            }
                        });
                    }
                }, 0L, i);
                return;
            } catch (Exception e) {
                log.e("Can not schedule statistics timer", e);
                return;
            }
        }
        this.statsTimer.cancel();
    }

    public void initializeVideoEffectPipeline(final VideoEffectTransitionListener videoEffectTransitionListener) {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":initializeVideoEffectPipeline"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.5
            @Override // java.lang.Runnable
            public void run() {
                if (PeerConnectionClient.this.isError) {
                    return;
                }
                VideoEffectShim createVideoEffectShim = PeerConnectionClient.this.localAudioVideoShim.createVideoEffectShim(PeerConnectionClient.this);
                if (createVideoEffectShim == null) {
                    PeerConnectionClient.log.d("Can't create videoEffectShim");
                    return;
                }
                PeerConnectionClient.this.videoEffectShim = createVideoEffectShim;
                PeerConnectionClient.this.videoEffectTransitionListener = videoEffectTransitionListener;
            }
        });
    }

    public void insertDtmf(final String str, final int i, final int i2) {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":insertDtmf"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.25
            @Override // java.lang.Runnable
            public void run() {
                PeerConnectionClient.this.events.onDtmfInserted((PeerConnectionClient.this.peerConnection == null || PeerConnectionClient.this.audioRtpSender == null || PeerConnectionClient.this.audioRtpSender.dtmf() == null) ? false : PeerConnectionClient.this.audioRtpSender.dtmf().insertDtmf(str, i, i2), str, i, i2);
            }
        });
    }

    public boolean isHDVideo() {
        return this.localAudioVideoShim.isHDVideo();
    }

    public boolean isVideoCapable() {
        return this.videoCapable;
    }

    @VisibleForTesting
    SessionDescription modifySdpForLocalMediaState(SessionDescription sessionDescription) {
        Sdp sdp = new Sdp(sessionDescription.description);
        if (this.videoCapable) {
            sdp.changeMediaDirection("video", this.renderLocalVideo);
        } else {
            sdp.markMediaDeleted("video");
        }
        log.d("changeMediaDirection: force to true");
        sdp.changeMediaDirection("audio", true);
        if (this.videoCapable) {
            sdp.preferCodec(this.preferredVideoCodec, false);
        }
        sdp.preferCodec(AUDIO_CODEC_OPUS, true);
        sdp.replaceRTPProfile("SAVP");
        SignalingParameters signalingParameters = this.signalingParameters;
        if (signalingParameters == null || signalingParameters.stripTrickleIceFromSdp) {
            sdp.removeTrickleIce();
        }
        if (this.peerConnectionParameters.audioStartBitrateInKbps > 0) {
            sdp.setBitrate(AUDIO_CODEC_OPUS, false, AUDIO_CODEC_PARAM_BITRATE, this.peerConnectionParameters.audioStartBitrateInKbps, false);
        } else {
            log.d("audio start bitrate not set");
        }
        log.i("Modifying media description for application media type if exists");
        sdp.modifyApplicationMediaDescription();
        return new SessionDescription(sessionDescription.type, sdp.toString());
    }

    @Override // org.webrtc.LocalAudioVideoShim.LocalAudioVideoListener
    public void onCameraFailure(final CameraErrorCode cameraErrorCode, final String str) {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":onCameraFailure"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.27
            @Override // java.lang.Runnable
            public void run() {
                if (PeerConnectionClient.this.warmupClient) {
                    PeerConnectionClient.log.i("camera failure: warmup");
                    PeerConnectionClient.this.disposeCachedLocalVideoTrack();
                    return;
                }
                CommsLogger commsLogger = PeerConnectionClient.log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("camera failure: in-call: ");
                outline107.append(cameraErrorCode);
                outline107.append(" ");
                GeneratedOutlineSupport1.outline177(outline107, str, commsLogger);
                if (cameraErrorCode == CameraErrorCode.CAMERA_EVICTED && PeerConnectionClient.this.peerConnectionParameters.ignoreCameraEvictionError) {
                    return;
                }
                PeerConnectionClient.this.events.onCameraError(cameraErrorCode, str);
            }
        });
    }

    @Override // org.webrtc.LocalAudioVideoShim.LocalAudioVideoListener
    public void onCameraOpening(final String str, final boolean z) {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":onCameraOpening"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.28
            @Override // java.lang.Runnable
            public void run() {
                PeerConnectionClient.this.events.onCameraOpening(str, z);
            }
        });
    }

    @Override // org.webrtc.VideoEffectShim.WebRTCVideoEffectTransitionListener
    public void onWebRTCVideoEffectTransition(final String str) {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":onWebRTCVideoEffectTransition"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.8
            @Override // java.lang.Runnable
            public void run() {
                CommsLogger commsLogger = PeerConnectionClient.log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onVideoEffectTransition:");
                outline107.append(str);
                commsLogger.d(outline107.toString());
                if (PeerConnectionClient.this.videoEffectShim != null) {
                    PeerConnectionClient.this.videoEffectTransitionListener.onVideoEffectTransition(str);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void processRemoteDescription(final Sdp sdp, final Sdp.Type type) {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":processRemoteDescription"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.15
            @Override // java.lang.Runnable
            public void run() {
                PeerConnectionClient.this.noSupportedAudioCodecsPresent = sdp.noSupportedAudioCodecsPresent();
                PeerConnectionClient.this.processRemoteDescriptionInternal(sdp, type);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resetRemoteDescription() {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":resetRemoteDescription"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.14
            @Override // java.lang.Runnable
            public void run() {
                PeerConnectionClient.this.resetRemoteDescriptionInternal();
            }
        });
    }

    public void restartIce(List<PeerConnection.IceServer> list, boolean z) {
        CommsLogger commsLogger = log;
        commsLogger.i("Restarting ICE. Sending New Offer : " + z);
        PeerConnectionShim.RTCConfiguration rTCConfiguration = this.rtcConfig;
        rTCConfiguration.iceServers = list;
        PeerConnectionShim.setConfiguration(this.peerConnection, rTCConfiguration);
        if (z) {
            createOffer(true);
        }
    }

    public void retryMedia() {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":retryMedia"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.22
            @Override // java.lang.Runnable
            public void run() {
                PeerConnectionClient.log.i("retryMedia");
                if (PeerConnectionClient.this.isError || !PeerConnectionClient.this.retryMediaInit) {
                    return;
                }
                if (PeerConnectionClient.this.renderLocalVideo && PeerConnectionClient.this.localVideoTrack == null && !PeerConnectionClient.this.provideVideoTrack()) {
                    PeerConnectionClient.log.w("Failed to get access to camera");
                    PeerConnectionClient.this.renderLocalVideo = false;
                    PeerConnectionClient.this.events.onCameraError(CameraErrorCode.CAMERA_OPEN_FAILED, "Failed to get access to camera");
                }
                PeerConnectionClient.this.retryMediaInit = false;
                PeerConnectionClient.log.i("retryMedia: processing remote description");
                if (PeerConnectionClient.this.cachedRemoteOffer == null) {
                    PeerConnectionClient.this.reportError("cached remote offer is null");
                    return;
                }
                PeerConnectionClient peerConnectionClient = PeerConnectionClient.this;
                peerConnectionClient.processRemoteDescriptionInternal(peerConnectionClient.cachedRemoteOffer, Sdp.Type.OFFER);
                PeerConnectionClient.this.cachedRemoteOffer = null;
            }
        });
    }

    public void sendQueuedDataChannelMessages(final String str) {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":sendQueuedDataChannelMessages"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.30
            @Override // java.lang.Runnable
            public void run() {
                if (PeerConnectionClient.this.queuedMessages.containsKey(str)) {
                    DataChannel dataChannel = (DataChannel) PeerConnectionClient.this.dataChannelMap.get(str);
                    for (DataChannel.Buffer buffer : (List) PeerConnectionClient.this.queuedMessages.get(str)) {
                        dataChannel.send(buffer);
                    }
                    PeerConnectionClient.this.queuedMessages.remove(str);
                }
            }
        });
    }

    public void sendVideoEffectCommand(final VideoEffectCommand videoEffectCommand) {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":sendVideoEffectCommand"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.6
            @Override // java.lang.Runnable
            public void run() {
                if (PeerConnectionClient.this.videoEffectShim == null || PeerConnectionClient.this.isError) {
                    return;
                }
                PeerConnectionClient.this.videoEffectShim.setVideoEffect(videoEffectCommand.toString());
            }
        });
    }

    public void setAnswer() {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":setAnswer"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.17
            @Override // java.lang.Runnable
            public void run() {
                if (PeerConnectionClient.this.peerConnection == null || PeerConnectionClient.this.isError) {
                    return;
                }
                PeerConnectionClient.log.i("setAnswer");
                PeerConnectionClient.this.callAnswered = true;
                if (PeerConnectionClient.this.cachedSdp == null) {
                    return;
                }
                PeerConnectionClient.log.d("setLocalDescription Cached Answer");
                PeerConnection peerConnection = PeerConnectionClient.this.peerConnection;
                LocalSDPObserver localSDPObserver = PeerConnectionClient.this.localSdpObserver;
                PeerConnectionClient peerConnectionClient = PeerConnectionClient.this;
                peerConnection.setLocalDescription(localSDPObserver, peerConnectionClient.modifySdpForLocalMediaState(peerConnectionClient.cachedSdp));
                PeerConnectionClient.this.cachedSdp = null;
            }
        });
    }

    public void setAudioEnabled(boolean z, Call.Side side) {
        CommsLogger commsLogger = log;
        commsLogger.i("setting Audio State to: " + z + " on side: " + side);
        if (side == Call.Side.Local) {
            this.executor.cancel(this.setLocalAudioEnabledRunnable);
            this.executor.cancel(this.setLocalAudioDisabledRunnable);
            if (z) {
                this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":setLocalAudioEnabled"), this.setLocalAudioEnabledRunnable);
            } else {
                this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":setLocalAudioDisabled"), this.setLocalAudioDisabledRunnable);
            }
        } else if (side != Call.Side.Remote) {
        } else {
            this.executor.cancel(this.setRemoteAudioEnabledRunnable);
            this.executor.cancel(this.setRemoteAudioDisabledRunnable);
            if (z) {
                this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":setRemoteAudioEnabled"), this.setRemoteAudioEnabledRunnable);
            } else {
                this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":setRemoteAudioDisabled"), this.setRemoteAudioDisabledRunnable);
            }
        }
    }

    public void setMediaConstraints(final Map<String, Integer> map) {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":setMediaConstraints"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.21
            @Override // java.lang.Runnable
            public void run() {
                if (PeerConnectionClient.this.isError) {
                    return;
                }
                VideoConstraints localVideoConstraints = PeerConnectionClient.this.videoConstraintsManager.getThermalVideoConstraints() == null ? PeerConnectionClient.this.videoConstraintsManager.getLocalVideoConstraints() : PeerConnectionClient.this.videoConstraintsManager.getThermalVideoConstraints();
                int intValue = map.containsKey("maxWidth") ? ((Integer) map.get("maxWidth")).intValue() : localVideoConstraints.getVideoWidth();
                int intValue2 = map.containsKey("maxHeight") ? ((Integer) map.get("maxHeight")).intValue() : localVideoConstraints.getVideoHeight();
                int intValue3 = map.containsKey(LocalAudioVideoShim.MAX_VIDEO_FPS_CONSTRAINT) ? ((Integer) map.get(LocalAudioVideoShim.MAX_VIDEO_FPS_CONSTRAINT)).intValue() : localVideoConstraints.getVideoFps();
                if (intValue != 0 || intValue2 != 0 || intValue3 != 0) {
                    PeerConnectionClient.this.videoConstraintsManager.setThermalVideoConstraints(new VideoConstraints(intValue, intValue2, intValue3));
                } else {
                    PeerConnectionClient.this.videoConstraintsManager.setThermalVideoConstraints(null);
                }
                List<Pair<String, Integer>> convertVideoConstraintToMediaConstraintList = VideoConstraintsManager.convertVideoConstraintToMediaConstraintList(new ArrayList(map.size()), PeerConnectionClient.this.videoConstraintsManager.getCurrentVideoConstraints());
                for (Map.Entry entry : map.entrySet()) {
                    if (!((String) entry.getKey()).equals("maxWidth") && !((String) entry.getKey()).equals("maxHeight") && !((String) entry.getKey()).equals(LocalAudioVideoShim.MAX_VIDEO_FPS_CONSTRAINT)) {
                        convertVideoConstraintToMediaConstraintList.add(Pair.create(entry.getKey(), entry.getValue()));
                    }
                }
                PeerConnectionClient.this.localAudioVideoShim.updateVideoConstraints(PeerConnectionClient.this.peerConnection, convertVideoConstraintToMediaConstraintList);
            }
        });
    }

    public void setRemoteMediaCapability(final boolean z, final boolean z2) {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":setRemoteMediaCapability"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.26
            @Override // java.lang.Runnable
            public void run() {
                CommsLogger commsLogger = PeerConnectionClient.log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("setRemoteMediaCapability: audioCapable - ");
                outline107.append(z);
                outline107.append(" videoCapable - ");
                GeneratedOutlineSupport1.outline184(outline107, z2, commsLogger);
                boolean z3 = (z == PeerConnectionClient.this.remoteAudioCapable && z2 == PeerConnectionClient.this.remoteVideoCapable) ? false : true;
                PeerConnectionClient.this.remoteAudioCapable = z;
                PeerConnectionClient.this.remoteVideoCapable = z2;
                if (z3) {
                    PeerConnectionClient peerConnectionClient = PeerConnectionClient.this;
                    peerConnectionClient.sdpMediaConstraints = peerConnectionClient.createSdpConstraints();
                }
                if (!PeerConnectionClient.this.remoteVideoCapable) {
                    PeerConnectionClient.this.disposeCachedLocalVideoTrack();
                    PeerConnectionClient.this.isPendingVideoMaxSendBitrateSet = false;
                }
            }
        });
    }

    public void setVideoEnabled(boolean z, Call.Side side) {
        CommsLogger commsLogger = log;
        commsLogger.i("setting Video State to: " + z + " on side: " + side);
        if (side == Call.Side.Local) {
            this.executor.cancel(this.setLocalVideoEnabledRunnable);
            this.executor.cancel(this.setLocalVideoDisabledRunnable);
            if (z) {
                this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":setLocalVideoEnabled"), this.setLocalVideoEnabledRunnable);
            } else {
                this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":setLocalVideoDisabled"), this.setLocalVideoDisabledRunnable);
            }
        } else if (side != Call.Side.Remote) {
        } else {
            this.executor.cancel(this.setRemoteVideoEnabledRunnable);
            this.executor.cancel(this.setRemoteVideoDisabledRunnable);
            if (z) {
                this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":setRemoteVideoEnabled"), this.setRemoteVideoEnabledRunnable);
            } else {
                this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":setRemoteVideoDisabled"), this.setRemoteVideoDisabledRunnable);
            }
        }
    }

    public void setVolume(final float f) {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":setVolume"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.16
            @Override // java.lang.Runnable
            public void run() {
                if (PeerConnectionClient.this.peerConnection == null || PeerConnectionClient.this.isError || PeerConnectionClient.this.remoteSdpSetInProgress.get() > 0) {
                    PeerConnectionClient.log.i("setVolume can't be done. pending remote sdp processing?");
                } else if (PeerConnectionClient.this.remoteAudioTrack == null) {
                } else {
                    PeerConnectionShim.setAudioTrackVolume(PeerConnectionClient.this.remoteAudioTrack, f);
                }
            }
        });
    }

    public void stopVideoEffectPipeline() {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":stopVideoEffectPipeline"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.7
            @Override // java.lang.Runnable
            public void run() {
                PeerConnectionClient.this.stopVideoEffectPipelineInternal(false);
            }
        });
    }

    public void switchCamera(final CameraSwitchHandlerShim cameraSwitchHandlerShim) {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":switchCamera"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.18
            @Override // java.lang.Runnable
            public void run() {
                PeerConnectionClient.this.switchCameraInternal(cameraSwitchHandlerShim, null);
            }
        });
    }

    public void warmupMedia() {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":warmupMedia"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.23
            @Override // java.lang.Runnable
            public void run() {
                if (PeerConnectionClient.this.isError) {
                    return;
                }
                if (PeerConnectionClient.this.webRTCGlobalsProvider.getPeerConnectionFactoryWrapper() == null) {
                    PeerConnectionClient.this.reportError("Peerconnection factory is null");
                    return;
                }
                PeerConnectionClient.log.i("warming up.");
                PeerConnectionClient.this.checkDeviceVideoHwCodecSupport();
                if (PeerConnectionClient.this.followMeEnabled) {
                    PeerConnectionClient.this.createFollowMeShim();
                }
                PeerConnectionClient peerConnectionClient = PeerConnectionClient.this;
                Context context = peerConnectionClient.applicationContext;
                boolean z = PeerConnectionClient.this.peerConnectionParameters.captureToTexture;
                boolean z2 = PeerConnectionClient.this.peerConnectionParameters.camera1ApiPreferred;
                boolean z3 = PeerConnectionClient.this.peerConnectionParameters.camera2ApiForced;
                boolean z4 = PeerConnectionClient.this.peerConnectionParameters.updateCameraHalFramerateAllowed;
                boolean z5 = PeerConnectionClient.this.peerConnectionParameters.provideCallingServiceHalParameter;
                PeerConnectionClient peerConnectionClient2 = PeerConnectionClient.this;
                peerConnectionClient.localAudioVideoShim = new LocalAudioVideoShim(context, z, z2, z3, z4, z5, peerConnectionClient2, peerConnectionClient2.followMeShim);
                PeerConnectionClient.this.localAudioVideoShim.setMaxVideoConstraints(PeerConnectionClient.this.peerConnectionParameters.getVideoWidth(), PeerConnectionClient.this.peerConnectionParameters.getVideoHeight(), PeerConnectionClient.this.peerConnectionParameters.getVideoFps(), PeerConnectionClient.this.peerConnection);
                PeerConnectionClient.this.createMediaConstraintsInternal();
                PeerConnectionClient.this.prepareMedia(true);
                ClocksImpl clocksImpl = new ClocksImpl();
                PeerConnectionClient.this.warmupCompletedTimestampERT = clocksImpl.getElapsedRealtime();
                PeerConnectionClient.this.warmupCompletedTimestampEpoch = clocksImpl.getCurrentTimeMillis();
                PeerConnectionClient.log.i("warmed up.");
            }
        });
    }

    public void switchCamera(final CameraSwitchHandlerShim cameraSwitchHandlerShim, final String str) {
        this.executor.execute(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.logTag, ":switchCamera"), new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.PeerConnectionClient.19
            @Override // java.lang.Runnable
            public void run() {
                PeerConnectionClient.this.switchCameraInternal(cameraSwitchHandlerShim, str);
            }
        });
    }
}
