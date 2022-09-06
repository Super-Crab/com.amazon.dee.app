package com.amazon.rtcmedia.webrtc;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.deecomms.common.Constants;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.amazon.rtcmedia.util.LooperExecutor;
import com.amazon.rtcmedia.util.MediaUIBridge;
import com.amazon.rtcmedia.util.RtcscLogger;
import com.amazon.rtcmedia.util.ViewDirection;
import com.amazon.rtcmedia.webrtc.RTCDataChannelEvent;
import com.amazon.rtcmedia.webrtc.RTCLocalVideoHandler;
import com.amazon.rtcmedia.webrtc.RTCRendererEventsHandler;
import com.amazon.rtcmedia.webrtc.RTCVideoEffect;
import com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import org.webrtc.AudioSource;
import org.webrtc.AudioTrack;
import org.webrtc.DataChannel;
import org.webrtc.IceCandidate;
import org.webrtc.Logging;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnection;
import org.webrtc.RtpParameters;
import org.webrtc.RtpReceiver;
import org.webrtc.RtpSender;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;
import org.webrtc.StatsObserver;
import org.webrtc.StatsReport;
import org.webrtc.VideoRenderer;
import org.webrtc.VideoTrack;
/* loaded from: classes13.dex */
public class WebRTCAndroidSession implements WebRTCAndroidSessionInterface, MediaUIBridge.MediaUIBridgeListener, RTCLocalVideoHandler.RTCCameraListener, RTCVideoEffect.WebRTCVideoEffectTransitionListener, RTCRendererEventsHandler.RTCRendererEventsListener {
    private static final String AUDIO_AUTO_GAIN_CONTROL_CONSTRAINT = "googAutoGainControl";
    private static final String AUDIO_CODEC_ISAC = "ISAC";
    private static final String AUDIO_CODEC_OPUS = "opus";
    private static final String AUDIO_CODEC_PARAM_BITRATE = "maxaveragebitrate";
    private static final String AUDIO_ECHO_CANCELLATION_CONSTRAINT = "googEchoCancellation";
    private static final String AUDIO_HIGH_PASS_FILTER_CONSTRAINT = "googHighpassFilter";
    private static final String AUDIO_LEVEL_CONTROL_CONSTRAINT = "levelControl";
    private static final String AUDIO_NOISE_SUPPRESSION_CONSTRAINT = "googNoiseSuppression";
    public static final String AUDIO_TRACK_ID = "ARDAMSa0";
    private static final int BPS_IN_KBPS = 1000;
    private static final int DEFAULT_STATS_PULLING_FREQUENCY_IN_MS = 2000;
    private static final String DTLS_SRTP_KEY_AGREEMENT_CONSTRAINT = "DtlsSrtpKeyAgreement";
    private static final String GOOG_DSCP = "googDscp";
    public static final String MEDIA_STREAM_ID = "ARDAMS";
    private static final String VIDEO_CODEC_H264 = "H264";
    private static final String VIDEO_CODEC_H264_BASELINE = "H264 Baseline";
    private static final String VIDEO_CODEC_H264_HIGH = "H264 High";
    private static final String VIDEO_CODEC_PARAM_START_BITRATE = "x-google-start-bitrate";
    private static final String VIDEO_CODEC_VP8 = "VP8";
    private static final String VIDEO_CODEC_VP9 = "VP9";
    public static final String VIDEO_TRACK_ID = "ARDAMSv0";
    public static final String VIDEO_TRACK_TYPE = "video";
    private static RtcscLogger mLog = RtcscLogger.getLogger(WebRTCAndroidSession.class);
    private MediaConstraints audioConstraints;
    private AudioSource audioSource;
    private RTCDataChannelEventListener dataChannelListener;
    private WebRTCAndroidSessionInterface.PeerClientListener events;
    private final LooperExecutor executor;
    private PeerConnectionFactoryWrapper factoryWrapper;
    private boolean hasWarmedUp;
    private boolean isOfferer;
    private boolean localAudioEnabled;
    private RTCLocalVideoHandler localVideoHandler;
    private MediaConstraints pcConstraints;
    private PeerConnectionParameters peerConnectionParameters;
    private String preferredVideoCodec;
    private MediaStream remoteMediaStream;
    private boolean renderLocalVideo;
    private PeerConnection.RTCConfiguration rtcConfig;
    private MediaConstraints sdpMediaConstraints;
    private Timer statsTimer;
    private boolean videoCallEnabled;
    private RTCVideoEffect videoEffect;
    private int videoMaxKBitrate;
    private final PCObserver pcObserver = new PCObserver();
    private final LocalSDPObserver localSdpObserver = new LocalSDPObserver();
    private final RemoteSDPObserver remoteSdpObserver = new RemoteSDPObserver();
    private PeerConnection.SignalingState signalingState = PeerConnection.SignalingState.STABLE;
    private String sessionId = "";
    private boolean remoteAudioCapable = true;
    private boolean remoteVideoCapable = true;
    private boolean isPendingVideoMaxSendBitrateSet = false;
    private Map<VideoRenderer.Callbacks, VideoRenderer> localRendererMap = new HashMap();
    private Map<VideoRenderer.Callbacks, VideoRenderer> remoteRendererMap = new HashMap();
    private final Runnable setLocalAudioEnabledRunnable = new AudioStateRunnable(true, true);
    private final Runnable setLocalAudioDisabledRunnable = new AudioStateRunnable(false, true);
    private final Runnable setRemoteAudioEnabledRunnable = new AudioStateRunnable(true, false);
    private final Runnable setRemoteAudioDisabledRunnable = new AudioStateRunnable(false, false);
    private final Runnable setLocalVideoEnabledRunnable = new VideoStateRunnable(true, true);
    private final Runnable setLocalVideoDisabledRunnable = new VideoStateRunnable(false, true);
    private final Runnable setRemoteVideoEnabledRunnable = new VideoStateRunnable(true, false);
    private final Runnable setRemoteVideoDisabledRunnable = new VideoStateRunnable(false, false);
    private PeerConnection peerConnection = null;
    private boolean isError = false;
    private LinkedList<IceCandidate> queuedRemoteCandidates = null;
    private MediaStream localMediaStream = null;
    private VideoTrack localVideoTrack = null;
    private VideoTrack remoteVideoTrack = null;
    private RtpSender localVideoSender = null;
    private AudioTrack localAudioTrack = null;
    private AudioTrack remoteAudioTrack = null;
    private boolean renderRemoteVideo = true;
    private boolean remoteAudioEnabled = true;
    private boolean localDataChannelEnabled = false;

    /* renamed from: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession$32  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass32 {
        static final /* synthetic */ int[] $SwitchMap$org$webrtc$PeerConnection$IceConnectionState = new int[PeerConnection.IceConnectionState.values().length];

        static {
            try {
                $SwitchMap$org$webrtc$PeerConnection$IceConnectionState[PeerConnection.IceConnectionState.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$IceConnectionState[PeerConnection.IceConnectionState.DISCONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$IceConnectionState[PeerConnection.IceConnectionState.FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes13.dex */
    class AudioStateRunnable implements Runnable {
        private boolean enable;
        private boolean local;

        public AudioStateRunnable(boolean z, boolean z2) {
            this.enable = z;
            this.local = z2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (WebRTCAndroidSession.this.isError) {
                return;
            }
            RtcscLogger rtcscLogger = WebRTCAndroidSession.mLog;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AudioStateRunnable: localAudioEnabled:");
            outline107.append(WebRTCAndroidSession.this.localAudioEnabled);
            outline107.append(" enable:");
            outline107.append(this.enable);
            rtcscLogger.d(outline107.toString());
            if (this.local) {
                boolean z = WebRTCAndroidSession.this.localAudioEnabled;
                boolean z2 = this.enable;
                if (z != z2) {
                    WebRTCAndroidSession.this.localAudioEnabled = z2;
                    if (WebRTCAndroidSession.this.localAudioEnabled && WebRTCAndroidSession.this.localAudioTrack == null) {
                        WebRTCAndroidSession.this.localMediaStream.addTrack(WebRTCAndroidSession.this.createAudioTrack());
                    }
                    if (WebRTCAndroidSession.this.localAudioTrack == null) {
                        return;
                    }
                    WebRTCAndroidSession.mLog.d("AudioStateRunnable localAudioTrack is valid");
                    WebRTCAndroidSession.this.localAudioTrack.setEnabled(WebRTCAndroidSession.this.localAudioEnabled);
                    return;
                }
            }
            if (this.local) {
                return;
            }
            boolean z3 = WebRTCAndroidSession.this.remoteAudioEnabled;
            boolean z4 = this.enable;
            if (z3 == z4) {
                return;
            }
            WebRTCAndroidSession.this.remoteAudioEnabled = z4;
            if (WebRTCAndroidSession.this.remoteAudioTrack == null) {
                return;
            }
            WebRTCAndroidSession.this.remoteAudioTrack.setEnabled(WebRTCAndroidSession.this.remoteAudioEnabled);
        }
    }

    /* loaded from: classes13.dex */
    private class LocalSDPObserver implements SdpObserver {
        private LocalSDPObserver() {
        }

        @Override // org.webrtc.SdpObserver
        public void onCreateFailure(String str) {
            WebRTCAndroidSession.this.reportError(GeneratedOutlineSupport1.outline72("createSDP error: ", str));
        }

        @Override // org.webrtc.SdpObserver
        public void onCreateSuccess(final SessionDescription sessionDescription) {
            WebRTCAndroidSession.this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.LocalSDPObserver.1
                @Override // java.lang.Runnable
                public void run() {
                    if (WebRTCAndroidSession.this.events == null) {
                        WebRTCAndroidSession.mLog.w("events is null, can't fire callbacks");
                        return;
                    }
                    GeneratedOutlineSupport1.outline185(GeneratedOutlineSupport1.outline107("SDP onCreateSuccess isOfferer = "), WebRTCAndroidSession.this.isOfferer, WebRTCAndroidSession.mLog);
                    if (WebRTCAndroidSession.this.isOfferer) {
                        WebRTCAndroidSession.this.events.onLocalDescriptionCreated(WebRTCAndroidSession.this.sessionId, sessionDescription.description, true);
                    } else {
                        WebRTCAndroidSession.this.events.onLocalDescriptionCreated(WebRTCAndroidSession.this.sessionId, sessionDescription.description, false);
                    }
                }
            });
        }

        @Override // org.webrtc.SdpObserver
        public void onSetFailure(String str) {
            WebRTCAndroidSession.this.reportError(GeneratedOutlineSupport1.outline72("setSDP error: ", str));
        }

        @Override // org.webrtc.SdpObserver
        public void onSetSuccess() {
            WebRTCAndroidSession.this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.LocalSDPObserver.2
                @Override // java.lang.Runnable
                public void run() {
                    if (WebRTCAndroidSession.this.peerConnection == null || WebRTCAndroidSession.this.isError) {
                        return;
                    }
                    WebRTCAndroidSession.this.handleLocalOrRemoteSDPSet();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public class PCObserver implements PeerConnection.Observer {
        private PCObserver() {
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onAddStream(final MediaStream mediaStream) {
            WebRTCAndroidSession.mLog.i("onAddStream");
            WebRTCAndroidSession.this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.PCObserver.6
                @Override // java.lang.Runnable
                public void run() {
                    if (WebRTCAndroidSession.this.peerConnection == null || WebRTCAndroidSession.this.isError) {
                        return;
                    }
                    WebRTCAndroidSession.this.remoteMediaStream = mediaStream;
                    WebRTCAndroidSession.this.processRemoteStreamTracks(mediaStream);
                }
            });
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onAddTrack(RtpReceiver rtpReceiver, MediaStream[] mediaStreamArr) {
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onDataChannel(final DataChannel dataChannel) {
            RtcscLogger rtcscLogger = WebRTCAndroidSession.mLog;
            rtcscLogger.d("onDataChannel " + dataChannel);
            WebRTCAndroidSession.this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.PCObserver.8
                @Override // java.lang.Runnable
                public void run() {
                    String label = dataChannel.label();
                    RtcscLogger rtcscLogger2 = WebRTCAndroidSession.mLog;
                    rtcscLogger2.i("Received event for DataChannel with label: " + label);
                    if (MediaUIBridge.getInstance().getDataChannel(WebRTCAndroidSession.this.sessionId, label) != null) {
                        RtcscLogger rtcscLogger3 = WebRTCAndroidSession.mLog;
                        rtcscLogger3.e("Incoming DataChannel already exists for label: " + label);
                        return;
                    }
                    MediaUIBridge.getInstance().addDataChannel(WebRTCAndroidSession.this.sessionId, label, dataChannel);
                    WebRTCAndroidSession.this.registerDataChannelObserver(dataChannel);
                    GeneratedOutlineSupport1.outline160("Registered observer for DataChannel with label: ", label, WebRTCAndroidSession.mLog);
                }
            });
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onIceCandidate(final IceCandidate iceCandidate) {
            WebRTCAndroidSession.mLog.i("onIceCandidate");
            WebRTCAndroidSession.this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.PCObserver.1
                @Override // java.lang.Runnable
                public void run() {
                    if (WebRTCAndroidSession.this.events != null) {
                        WebRTCAndroidSession.this.events.onIceCandidate(WebRTCAndroidSession.this.sessionId, iceCandidate);
                    }
                }
            });
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onIceCandidatesRemoved(final IceCandidate[] iceCandidateArr) {
            WebRTCAndroidSession.mLog.i("onIceCandidatesRemoved");
            WebRTCAndroidSession.this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.PCObserver.2
                @Override // java.lang.Runnable
                public void run() {
                    if (WebRTCAndroidSession.this.events != null) {
                        WebRTCAndroidSession.this.events.onIceCandidatesRemoved(WebRTCAndroidSession.this.sessionId, iceCandidateArr);
                    }
                }
            });
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onIceConnectionChange(final PeerConnection.IceConnectionState iceConnectionState) {
            RtcscLogger rtcscLogger = WebRTCAndroidSession.mLog;
            rtcscLogger.i("onIceConnectionChange: " + iceConnectionState);
            WebRTCAndroidSession.this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.PCObserver.4
                @Override // java.lang.Runnable
                public void run() {
                    RtcscLogger rtcscLogger2 = WebRTCAndroidSession.mLog;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IceConnectionState: ");
                    outline107.append(iceConnectionState);
                    rtcscLogger2.d(outline107.toString());
                    if (WebRTCAndroidSession.this.events == null) {
                        WebRTCAndroidSession.mLog.w("events is null, can't fire callbacks.");
                        return;
                    }
                    int ordinal = iceConnectionState.ordinal();
                    if (ordinal == 2) {
                        WebRTCAndroidSession.this.events.onIceConnected(WebRTCAndroidSession.this.sessionId);
                        WebRTCAndroidSession.this.applyMaxBitrateForVideoSender();
                    } else if (ordinal == 4) {
                        WebRTCAndroidSession.this.events.onIceFailed(WebRTCAndroidSession.this.sessionId);
                    } else if (ordinal != 5) {
                        RtcscLogger rtcscLogger3 = WebRTCAndroidSession.mLog;
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("IceConnectionState :");
                        outline1072.append(iceConnectionState);
                        outline1072.append("not handled. No Action taken.");
                        rtcscLogger3.w(outline1072.toString());
                    } else {
                        WebRTCAndroidSession.this.events.onIceDisconnected(WebRTCAndroidSession.this.sessionId);
                    }
                }
            });
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onIceConnectionReceivingChange(boolean z) {
            RtcscLogger rtcscLogger = WebRTCAndroidSession.mLog;
            rtcscLogger.d("IceConnectionReceiving changed to " + z);
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onIceGatheringChange(final PeerConnection.IceGatheringState iceGatheringState) {
            RtcscLogger rtcscLogger = WebRTCAndroidSession.mLog;
            rtcscLogger.i("IceGatheringState: " + iceGatheringState);
            WebRTCAndroidSession.this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.PCObserver.5
                @Override // java.lang.Runnable
                public void run() {
                    if (WebRTCAndroidSession.this.peerConnection == null || WebRTCAndroidSession.this.isError || iceGatheringState != PeerConnection.IceGatheringState.COMPLETE) {
                        return;
                    }
                    SessionDescription localDescription = WebRTCAndroidSession.this.peerConnection.getLocalDescription();
                    if (WebRTCAndroidSession.this.events == null) {
                        return;
                    }
                    WebRTCAndroidSession.this.events.onIceGatheringDone(WebRTCAndroidSession.this.sessionId, localDescription.description, localDescription.type == SessionDescription.Type.OFFER);
                }
            });
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onRemoveStream(MediaStream mediaStream) {
            WebRTCAndroidSession.mLog.i("onRemoveStream");
            WebRTCAndroidSession.this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.PCObserver.7
                @Override // java.lang.Runnable
                public void run() {
                    WebRTCAndroidSession.this.remoteVideoTrack = null;
                    WebRTCAndroidSession.this.remoteAudioTrack = null;
                    WebRTCAndroidSession.this.remoteMediaStream = null;
                }
            });
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onRenegotiationNeeded() {
        }

        @Override // org.webrtc.PeerConnection.Observer
        public void onSignalingChange(final PeerConnection.SignalingState signalingState) {
            RtcscLogger rtcscLogger = WebRTCAndroidSession.mLog;
            rtcscLogger.i("SignalingState: " + signalingState);
            WebRTCAndroidSession.this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.PCObserver.3
                @Override // java.lang.Runnable
                public void run() {
                    WebRTCAndroidSession.this.signalingState = signalingState;
                    if (WebRTCAndroidSession.this.signalingState == PeerConnection.SignalingState.STABLE) {
                        WebRTCAndroidSession.this.applyMaxBitrateForVideoSender();
                    }
                }
            });
        }
    }

    /* loaded from: classes13.dex */
    public static class PeerConnectionParameters {
        public final boolean aecDump;
        public final String audioCodec;
        public final int audioStartBitrate;
        public final PeerConnection.BundlePolicy bundlePolicy;
        public List<RTCDataChannelParams> dataChannelParams;
        public final boolean disableBuiltInAEC;
        public final boolean disableBuiltInAGC;
        public final boolean disableBuiltInNS;
        public final boolean disableWebRtcAGCAndHPF;
        public final boolean enableLevelControl;
        public List<PeerConnection.IceServer> iceServers;
        public final String keyExchange;
        public final boolean localAudioEnabled;
        public final boolean localDataChannelEnabled;
        public final boolean localVideoEnabled;
        public final boolean loopback;
        public final boolean noAudioProcessing;
        public final boolean preferCamera1;
        public final boolean remoteAudioEnabled;
        public final boolean remoteVideoEnabled;
        public final PeerConnection.RtcpMuxPolicy rtcpMuxPolicy;
        public final boolean tracing;
        public final boolean useOpenSLES;
        public final String videoCodec;
        public final boolean videoCodecHwAcceleration;
        public final boolean videoFlexfecEnabled;
        public final int videoFps;
        public final int videoHeight;
        public final int videoMaxBitrate;
        public final String videoSrc;
        public final RTCVideoSrcType videoSrcType;
        public final int videoWidth;

        public PeerConnectionParameters(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, RTCVideoSrcType rTCVideoSrcType, String str, int i, int i2, int i3, int i4, String str2, boolean z8, boolean z9, int i5, String str3, boolean z10, boolean z11, boolean z12) {
            this(z, z2, z3, z4, z5, z6, z7, rTCVideoSrcType, str, i, i2, i3, i4, str2, z8, z9, i5, str3, z10, z11, z12, true, true, true, false, true, null, PeerConnection.BundlePolicy.MAXBUNDLE, PeerConnection.RtcpMuxPolicy.REQUIRE, "dtls", false, null);
        }

        public void setIceServers(List<PeerConnection.IceServer> list) {
            this.iceServers = list;
        }

        public PeerConnectionParameters(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, RTCVideoSrcType rTCVideoSrcType, String str, int i, int i2, int i3, int i4, String str2, boolean z8, boolean z9, int i5, String str3, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17, List<PeerConnection.IceServer> list, PeerConnection.BundlePolicy bundlePolicy, PeerConnection.RtcpMuxPolicy rtcpMuxPolicy, String str4, boolean z18, List<RTCDataChannelParams> list2) {
            this.localVideoEnabled = z;
            this.localAudioEnabled = z2;
            this.remoteVideoEnabled = z3;
            this.remoteAudioEnabled = z4;
            this.loopback = z5;
            this.tracing = z6;
            this.preferCamera1 = z7;
            this.videoSrcType = rTCVideoSrcType;
            this.videoSrc = str;
            this.videoWidth = i;
            this.videoHeight = i2;
            this.videoFps = i3;
            this.videoMaxBitrate = i4;
            this.videoCodec = str2;
            this.videoFlexfecEnabled = z9;
            this.videoCodecHwAcceleration = z8;
            this.audioStartBitrate = i5;
            this.audioCodec = str3;
            this.noAudioProcessing = z10;
            this.aecDump = z11;
            this.useOpenSLES = z12;
            this.disableBuiltInAEC = z13;
            this.disableBuiltInAGC = z14;
            this.disableBuiltInNS = z15;
            this.enableLevelControl = z16;
            this.disableWebRtcAGCAndHPF = z17;
            this.iceServers = list;
            this.bundlePolicy = bundlePolicy;
            this.rtcpMuxPolicy = rtcpMuxPolicy;
            this.keyExchange = str4;
            this.localDataChannelEnabled = z18;
            this.dataChannelParams = list2;
        }
    }

    /* loaded from: classes13.dex */
    public interface RTCDataChannelEventListener {
        void onDataChannelEvent(RTCDataChannelEvent rTCDataChannelEvent);
    }

    /* loaded from: classes13.dex */
    public class RTCDataChannelObserver implements DataChannel.Observer {
        private final DataChannel dataChannel;

        public RTCDataChannelObserver(DataChannel dataChannel) {
            this.dataChannel = dataChannel;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String dataChannelStateToString(DataChannel.State state) {
            if (state != null) {
                return state.name().toLowerCase(Locale.ENGLISH);
            }
            WebRTCAndroidSession.mLog.e("Invalid DataChannel state");
            return null;
        }

        @Override // org.webrtc.DataChannel.Observer
        public void onBufferedAmountChange(long j) {
        }

        @Override // org.webrtc.DataChannel.Observer
        public void onMessage(DataChannel.Buffer buffer) {
            byte[] bArr = new byte[buffer.data.remaining()];
            buffer.data.slice().get(bArr);
            final RTCDataChannelDTO rTCDataChannelDTO = new RTCDataChannelDTO(this.dataChannel.label(), bArr, buffer.binary);
            WebRTCAndroidSession.this.executor.post(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.RTCDataChannelObserver.2
                @Override // java.lang.Runnable
                public void run() {
                    RtcscLogger rtcscLogger = WebRTCAndroidSession.mLog;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received onMessage event with label = ");
                    outline107.append(RTCDataChannelObserver.this.dataChannel.label());
                    rtcscLogger.i(outline107.toString());
                    RTCDataChannelEvent build = RTCDataChannelEvent.builder().param(AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, WebRTCAndroidSession.this.sessionId).param("label", RTCDataChannelObserver.this.dataChannel.label()).param("data", rTCDataChannelDTO).eventType(RTCDataChannelEvent.Type.RECEIVED_MESSAGE).build();
                    WebRTCAndroidSession webRTCAndroidSession = WebRTCAndroidSession.this;
                    if (webRTCAndroidSession.isValidListener(webRTCAndroidSession.dataChannelListener)) {
                        WebRTCAndroidSession.this.dataChannelListener.onDataChannelEvent(build);
                        RtcscLogger rtcscLogger2 = WebRTCAndroidSession.mLog;
                        rtcscLogger2.i("Sending onMessage event " + build + " to listener " + WebRTCAndroidSession.this.dataChannelListener);
                    }
                }
            });
        }

        @Override // org.webrtc.DataChannel.Observer
        public void onStateChange() {
            final RTCDataChannelEvent build = RTCDataChannelEvent.builder().param(AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, WebRTCAndroidSession.this.sessionId).param("label", this.dataChannel.label()).param("state", dataChannelStateToString(this.dataChannel.state())).eventType(RTCDataChannelEvent.Type.STATE_CHANGED).build();
            WebRTCAndroidSession.this.executor.post(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.RTCDataChannelObserver.1
                @Override // java.lang.Runnable
                public void run() {
                    RtcscLogger rtcscLogger = WebRTCAndroidSession.mLog;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received onStateChange event with label = ");
                    outline107.append(RTCDataChannelObserver.this.dataChannel.label());
                    outline107.append(", state = ");
                    RTCDataChannelObserver rTCDataChannelObserver = RTCDataChannelObserver.this;
                    outline107.append(rTCDataChannelObserver.dataChannelStateToString(rTCDataChannelObserver.dataChannel.state()));
                    outline107.append("over ");
                    outline107.append(RTCDataChannelObserver.this.dataChannel);
                    rtcscLogger.i(outline107.toString());
                    WebRTCAndroidSession webRTCAndroidSession = WebRTCAndroidSession.this;
                    if (webRTCAndroidSession.isValidListener(webRTCAndroidSession.dataChannelListener)) {
                        WebRTCAndroidSession.this.dataChannelListener.onDataChannelEvent(build);
                        RtcscLogger rtcscLogger2 = WebRTCAndroidSession.mLog;
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Sending onStateChange event ");
                        outline1072.append(build);
                        outline1072.append(" to listener ");
                        outline1072.append(WebRTCAndroidSession.this.dataChannelListener);
                        rtcscLogger2.i(outline1072.toString());
                    }
                }
            });
        }
    }

    /* loaded from: classes13.dex */
    private class RemoteSDPObserver implements SdpObserver {
        private RemoteSDPObserver() {
        }

        @Override // org.webrtc.SdpObserver
        public void onCreateFailure(String str) {
            WebRTCAndroidSession.this.reportError(GeneratedOutlineSupport1.outline72("createRemoteSDP error: ", str));
        }

        @Override // org.webrtc.SdpObserver
        public void onCreateSuccess(SessionDescription sessionDescription) {
            WebRTCAndroidSession.mLog.w("remote sdp create success!");
        }

        @Override // org.webrtc.SdpObserver
        public void onSetFailure(final String str) {
            WebRTCAndroidSession.this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.RemoteSDPObserver.2
                @Override // java.lang.Runnable
                public void run() {
                    if (WebRTCAndroidSession.this.remoteMediaStream != null && WebRTCAndroidSession.this.peerConnection != null && !WebRTCAndroidSession.this.isError) {
                        WebRTCAndroidSession.mLog.i("remote sdp set failure, processing remote stream");
                        WebRTCAndroidSession webRTCAndroidSession = WebRTCAndroidSession.this;
                        webRTCAndroidSession.processRemoteStreamTracks(webRTCAndroidSession.remoteMediaStream);
                    }
                    WebRTCAndroidSession webRTCAndroidSession2 = WebRTCAndroidSession.this;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("setRemoteSDP set error: ");
                    outline107.append(str);
                    webRTCAndroidSession2.reportError(outline107.toString());
                }
            });
        }

        @Override // org.webrtc.SdpObserver
        public void onSetSuccess() {
            WebRTCAndroidSession.this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.RemoteSDPObserver.1
                @Override // java.lang.Runnable
                public void run() {
                    if (WebRTCAndroidSession.this.remoteMediaStream != null && WebRTCAndroidSession.this.peerConnection != null && !WebRTCAndroidSession.this.isError) {
                        WebRTCAndroidSession.mLog.i("remote sdp set failure, processing remote stream");
                        WebRTCAndroidSession webRTCAndroidSession = WebRTCAndroidSession.this;
                        webRTCAndroidSession.processRemoteStreamTracks(webRTCAndroidSession.remoteMediaStream);
                    }
                    WebRTCAndroidSession.this.handleLocalOrRemoteSDPSet();
                    WebRTCAndroidSession.mLog.d("remote sdp set success");
                }
            });
        }
    }

    /* loaded from: classes13.dex */
    class VideoStateRunnable implements Runnable {
        private boolean enable;
        private boolean local;

        public VideoStateRunnable(boolean z, boolean z2) {
            this.enable = z;
            this.local = z2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (WebRTCAndroidSession.this.isError) {
                return;
            }
            RtcscLogger rtcscLogger = WebRTCAndroidSession.mLog;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("VideoStateRunnable :  enable=");
            outline107.append(this.enable);
            outline107.append(" renderLocalVideo=");
            outline107.append(WebRTCAndroidSession.this.renderLocalVideo);
            rtcscLogger.d(outline107.toString());
            if (this.local) {
                boolean z = WebRTCAndroidSession.this.renderLocalVideo;
                boolean z2 = this.enable;
                if (z != z2) {
                    WebRTCAndroidSession.this.renderLocalVideo = z2;
                    if (WebRTCAndroidSession.this.renderLocalVideo) {
                        WebRTCAndroidSession.this.startVideoSource();
                        if (WebRTCAndroidSession.this.videoMaxKBitrate > 0) {
                            WebRTCAndroidSession.this.isPendingVideoMaxSendBitrateSet = true;
                        }
                        if (WebRTCAndroidSession.this.localVideoTrack == null) {
                            WebRTCAndroidSession.this.provideVideoTrack();
                        }
                    } else {
                        WebRTCAndroidSession.this.stopVideoSource();
                        WebRTCAndroidSession.this.isPendingVideoMaxSendBitrateSet = false;
                    }
                    if (WebRTCAndroidSession.this.localVideoTrack == null) {
                        return;
                    }
                    WebRTCAndroidSession.this.localVideoTrack.setEnabled(WebRTCAndroidSession.this.renderLocalVideo);
                    return;
                }
            }
            if (!this.local) {
                boolean z3 = WebRTCAndroidSession.this.renderRemoteVideo;
                boolean z4 = this.enable;
                if (z3 != z4) {
                    WebRTCAndroidSession.this.renderRemoteVideo = z4;
                    if (WebRTCAndroidSession.this.remoteVideoTrack == null) {
                        return;
                    }
                    WebRTCAndroidSession.this.remoteVideoTrack.setEnabled(WebRTCAndroidSession.this.renderRemoteVideo);
                    return;
                }
            }
            RtcscLogger rtcscLogger2 = WebRTCAndroidSession.mLog;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("VideoStateRunnable NO-OP:  renderLocalVideo=");
            outline1072.append(WebRTCAndroidSession.this.renderLocalVideo);
            outline1072.append(" enable=");
            outline1072.append(this.enable);
            rtcscLogger2.d(outline1072.toString());
        }
    }

    private WebRTCAndroidSession(WebRTCAndroidSessionInterface.PeerClientListener peerClientListener, LooperExecutor looperExecutor) {
        this.events = peerClientListener;
        this.executor = looperExecutor;
    }

    private void LoggingPeerParameters(PeerConnectionParameters peerConnectionParameters) {
        mLog.i("PeerConnectionParameters ===================");
        GeneratedOutlineSupport1.outline185(GeneratedOutlineSupport1.outline107("\t localVideoEnabled = "), peerConnectionParameters.localVideoEnabled, mLog);
        GeneratedOutlineSupport1.outline185(GeneratedOutlineSupport1.outline107("\t localAudioEnabled = "), peerConnectionParameters.localAudioEnabled, mLog);
        GeneratedOutlineSupport1.outline185(GeneratedOutlineSupport1.outline107("\t remoteVideoEnabled = "), peerConnectionParameters.remoteVideoEnabled, mLog);
        GeneratedOutlineSupport1.outline185(GeneratedOutlineSupport1.outline107("\t remoteAudioEnabled = "), peerConnectionParameters.remoteAudioEnabled, mLog);
        GeneratedOutlineSupport1.outline185(GeneratedOutlineSupport1.outline107("\t loopback = "), peerConnectionParameters.loopback, mLog);
        GeneratedOutlineSupport1.outline185(GeneratedOutlineSupport1.outline107("\t tracing = "), peerConnectionParameters.tracing, mLog);
        GeneratedOutlineSupport1.outline185(GeneratedOutlineSupport1.outline107("\t preferCamera1 = "), peerConnectionParameters.preferCamera1, mLog);
        RtcscLogger rtcscLogger = mLog;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("\t videoSrcType = ");
        outline107.append(peerConnectionParameters.videoSrcType);
        rtcscLogger.i(outline107.toString());
        RtcscLogger rtcscLogger2 = mLog;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("\t videoSrc = ");
        outline1072.append(peerConnectionParameters.videoSrc);
        rtcscLogger2.i(outline1072.toString());
        RtcscLogger rtcscLogger3 = mLog;
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("\t videoWidth = ");
        outline1073.append(peerConnectionParameters.videoWidth);
        rtcscLogger3.i(outline1073.toString());
        RtcscLogger rtcscLogger4 = mLog;
        StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("\t videoHeight = ");
        outline1074.append(peerConnectionParameters.videoHeight);
        rtcscLogger4.i(outline1074.toString());
        RtcscLogger rtcscLogger5 = mLog;
        StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("\t videoFps = ");
        outline1075.append(peerConnectionParameters.videoFps);
        rtcscLogger5.i(outline1075.toString());
        RtcscLogger rtcscLogger6 = mLog;
        StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("\t videoMaxBitrate = ");
        outline1076.append(peerConnectionParameters.videoMaxBitrate);
        rtcscLogger6.i(outline1076.toString());
        RtcscLogger rtcscLogger7 = mLog;
        StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("\t videoCodec = ");
        outline1077.append(peerConnectionParameters.videoCodec);
        rtcscLogger7.i(outline1077.toString());
        GeneratedOutlineSupport1.outline185(GeneratedOutlineSupport1.outline107("\t videoFlexfecEnabled = "), peerConnectionParameters.videoFlexfecEnabled, mLog);
        GeneratedOutlineSupport1.outline185(GeneratedOutlineSupport1.outline107("\t videoCodecHwAcceleration = "), peerConnectionParameters.videoCodecHwAcceleration, mLog);
        RtcscLogger rtcscLogger8 = mLog;
        StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("\t audioStartBitrate = ");
        outline1078.append(peerConnectionParameters.audioStartBitrate);
        rtcscLogger8.i(outline1078.toString());
        RtcscLogger rtcscLogger9 = mLog;
        StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("\t audioCodec = ");
        outline1079.append(peerConnectionParameters.audioCodec);
        rtcscLogger9.i(outline1079.toString());
        GeneratedOutlineSupport1.outline185(GeneratedOutlineSupport1.outline107("\t noAudioProcessing = "), peerConnectionParameters.noAudioProcessing, mLog);
        GeneratedOutlineSupport1.outline185(GeneratedOutlineSupport1.outline107("\t useOpenSLES = "), peerConnectionParameters.useOpenSLES, mLog);
        GeneratedOutlineSupport1.outline185(GeneratedOutlineSupport1.outline107("\t aecDump = "), peerConnectionParameters.aecDump, mLog);
        GeneratedOutlineSupport1.outline185(GeneratedOutlineSupport1.outline107("\t disableBuiltInAEC = "), peerConnectionParameters.disableBuiltInAEC, mLog);
        GeneratedOutlineSupport1.outline185(GeneratedOutlineSupport1.outline107("\t disableBuiltInAGC = "), peerConnectionParameters.disableBuiltInAGC, mLog);
        GeneratedOutlineSupport1.outline185(GeneratedOutlineSupport1.outline107("\t disableBuiltInNS = "), peerConnectionParameters.disableBuiltInNS, mLog);
        GeneratedOutlineSupport1.outline185(GeneratedOutlineSupport1.outline107("\t enableLevelControl = "), peerConnectionParameters.enableLevelControl, mLog);
        GeneratedOutlineSupport1.outline185(GeneratedOutlineSupport1.outline107("\t disableWebRtcAGCAndHPF = "), peerConnectionParameters.disableWebRtcAGCAndHPF, mLog);
        RtcscLogger rtcscLogger10 = mLog;
        StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("\t bundlePolicy = ");
        outline10710.append(peerConnectionParameters.bundlePolicy);
        rtcscLogger10.i(outline10710.toString());
        RtcscLogger rtcscLogger11 = mLog;
        StringBuilder outline10711 = GeneratedOutlineSupport1.outline107("\t rtcpMuxPolicy = ");
        outline10711.append(peerConnectionParameters.rtcpMuxPolicy);
        rtcscLogger11.i(outline10711.toString());
        RtcscLogger rtcscLogger12 = mLog;
        StringBuilder outline10712 = GeneratedOutlineSupport1.outline107("\t keyExchange = ");
        outline10712.append(peerConnectionParameters.keyExchange);
        rtcscLogger12.i(outline10712.toString());
        mLog.i("\t Ice Servers:");
        for (PeerConnection.IceServer iceServer : peerConnectionParameters.iceServers) {
            RtcscLogger rtcscLogger13 = mLog;
            StringBuilder outline10713 = GeneratedOutlineSupport1.outline107("\t\t ");
            outline10713.append(iceServer.toString());
            rtcscLogger13.d(outline10713.toString());
        }
        GeneratedOutlineSupport1.outline185(GeneratedOutlineSupport1.outline107("\t localDataChannelEnabled = "), peerConnectionParameters.localDataChannelEnabled, mLog);
        mLog.i("\t DataChannel Parameters:");
        for (RTCDataChannelParams rTCDataChannelParams : peerConnectionParameters.dataChannelParams) {
            RtcscLogger rtcscLogger14 = mLog;
            StringBuilder outline10714 = GeneratedOutlineSupport1.outline107("\t\t ");
            outline10714.append(rTCDataChannelParams.getLabel());
            rtcscLogger14.i(outline10714.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addVideoRenderer(Map<VideoRenderer.Callbacks, VideoRenderer> map, RTCSurfaceRenderer rTCSurfaceRenderer, VideoTrack videoTrack) {
        RTCRendererEventsHandler rendererEventsHandler = rTCSurfaceRenderer.getRendererEventsHandler();
        if (rendererEventsHandler != null) {
            rendererEventsHandler.registerListener(this);
        }
        VideoRenderer videoRenderer = new VideoRenderer(rTCSurfaceRenderer);
        map.put(rTCSurfaceRenderer, videoRenderer);
        if (videoTrack != null) {
            videoTrack.addRenderer(videoRenderer);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyMaxBitrateForVideoSender() {
        mLog.i("applyMaxBitrateForVideoSender");
        if (!this.renderLocalVideo) {
            mLog.w("Video is not being rendered. Ignoring the request");
        } else if (this.videoMaxKBitrate <= 0) {
            RtcscLogger rtcscLogger = mLog;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid max bitrate value: ");
            outline107.append(this.videoMaxKBitrate);
            outline107.append("Ignoring the request");
            rtcscLogger.w(outline107.toString());
        } else {
            this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.12
                @Override // java.lang.Runnable
                public void run() {
                    if (WebRTCAndroidSession.this.peerConnection == null || WebRTCAndroidSession.this.isError) {
                        return;
                    }
                    if (!WebRTCAndroidSession.this.isPendingVideoMaxSendBitrateSet) {
                        WebRTCAndroidSession.mLog.i("Max bitrate is already applied to RtpParams. Ignoring the request");
                        return;
                    }
                    RtcscLogger rtcscLogger2 = WebRTCAndroidSession.mLog;
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Requested max video bitrate: ");
                    outline1072.append(WebRTCAndroidSession.this.videoMaxKBitrate);
                    rtcscLogger2.d(outline1072.toString());
                    if (WebRTCAndroidSession.this.localVideoSender == null) {
                        WebRTCAndroidSession.mLog.w("Sender is not ready.");
                        return;
                    }
                    RtpParameters parameters = WebRTCAndroidSession.this.localVideoSender.getParameters();
                    if (parameters.encodings.size() == 0) {
                        WebRTCAndroidSession.mLog.w("RtpParameters are not ready.");
                        return;
                    }
                    Iterator<RtpParameters.Encoding> it2 = parameters.encodings.iterator();
                    while (it2.hasNext()) {
                        it2.next().maxBitrateBps = Integer.valueOf(WebRTCAndroidSession.this.videoMaxKBitrate * 1000);
                    }
                    if (!WebRTCAndroidSession.this.localVideoSender.setParameters(parameters)) {
                        WebRTCAndroidSession.mLog.e("RtpSender.setParameters failed.");
                        return;
                    }
                    WebRTCAndroidSession.this.isPendingVideoMaxSendBitrateSet = false;
                    RtcscLogger rtcscLogger3 = WebRTCAndroidSession.mLog;
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Configured max video bitrate to: ");
                    outline1073.append(WebRTCAndroidSession.this.videoMaxKBitrate);
                    rtcscLogger3.d(outline1073.toString());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AudioTrack createAudioTrack() {
        this.audioSource = this.factoryWrapper.createAudioSource(this.audioConstraints);
        this.localAudioTrack = this.factoryWrapper.createAudioTrack("ARDAMSa0", this.audioSource);
        this.localAudioTrack.setEnabled(this.localAudioEnabled);
        return this.localAudioTrack;
    }

    private void createDataChannel(RTCDataChannelParams rTCDataChannelParams) {
        if (this.peerConnection == null || this.isError) {
            return;
        }
        String label = rTCDataChannelParams.getLabel();
        DataChannel.Init init = new DataChannel.Init();
        init.ordered = rTCDataChannelParams.isOrdered();
        init.id = -1;
        GeneratedOutlineSupport1.outline160("Trying to create DataChannel with label: ", label, mLog);
        DataChannel createDataChannel = this.peerConnection.createDataChannel(label, init);
        if (createDataChannel == null) {
            RtcscLogger rtcscLogger = mLog;
            rtcscLogger.e("Failed to create DataChannel with label: " + label + ", Please check whether DTLS is enabled.");
            return;
        }
        RtcscLogger rtcscLogger2 = mLog;
        rtcscLogger2.i("Created DataChannel: " + createDataChannel);
        MediaUIBridge.getInstance().addDataChannel(this.sessionId, label, createDataChannel);
        registerDataChannelObserver(createDataChannel);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaConstraints createSdpConstraints() {
        MediaConstraints mediaConstraints = new MediaConstraints();
        mediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("OfferToReceiveAudio", "true"));
        if (!this.remoteVideoCapable && !this.peerConnectionParameters.loopback) {
            mediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("OfferToReceiveVideo", PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE));
        } else {
            mediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("OfferToReceiveVideo", "true"));
        }
        return mediaConstraints;
    }

    public static WebRTCAndroidSession createWebRTCAndroidSession(WebRTCAndroidSessionInterface.PeerClientListener peerClientListener) {
        if (WebRTCAndroidSessionFactory.getInstance() != null && WebRTCAndroidSessionFactory.getInstance().getExecutor() != null) {
            return new WebRTCAndroidSession(peerClientListener, WebRTCAndroidSessionFactory.getInstance().getExecutor());
        }
        mLog.e("WebRTCAndroidSessionFactory didn't create yet, can't createWebRTCAndroidSession");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void drainCandidates() {
        if (this.peerConnection == null || this.isError || this.queuedRemoteCandidates == null) {
            return;
        }
        RtcscLogger rtcscLogger = mLog;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Add ");
        outline107.append(this.queuedRemoteCandidates.size());
        outline107.append(" remote candidates");
        rtcscLogger.d(outline107.toString());
        Iterator<IceCandidate> it2 = this.queuedRemoteCandidates.iterator();
        while (it2.hasNext()) {
            this.peerConnection.addIceCandidate(it2.next());
        }
        this.queuedRemoteCandidates = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeChangeCaptureFormat(int i, int i2, int i3) {
        if (this.renderLocalVideo && !this.isError) {
            RtcscLogger rtcscLogger = mLog;
            StringBuilder outline110 = GeneratedOutlineSupport1.outline110("changeCaptureFormat: ", i, ReactProperties.HereMapMarker.X, i2, "@");
            outline110.append(i3);
            rtcscLogger.d(outline110.toString());
            this.localVideoHandler.changeCapturerOutputFormat(i, i2, i3);
            return;
        }
        RtcscLogger rtcscLogger2 = mLog;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to change capture format. Video: ");
        outline107.append(this.renderLocalVideo);
        outline107.append(". Error : ");
        outline107.append(this.isError);
        rtcscLogger2.e(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeClose() {
        mLog.i("Closing peer connection.");
        MediaUIBridge.getInstance().removeListener(this);
        PeerConnectionFactoryWrapper peerConnectionFactoryWrapper = this.factoryWrapper;
        if (peerConnectionFactoryWrapper != null && this.peerConnectionParameters.aecDump) {
            peerConnectionFactoryWrapper.stopAecDump();
        }
        this.statsTimer.cancel();
        mLog.i("disposing datachannel");
        MediaUIBridge.getInstance().removeDataChannels(this.sessionId);
        mLog.i("disposing peerConnection");
        PeerConnection peerConnection = this.peerConnection;
        if (peerConnection != null) {
            peerConnection.dispose();
            this.peerConnection = null;
        }
        mLog.i("disposing audio source.");
        AudioSource audioSource = this.audioSource;
        if (audioSource != null) {
            audioSource.dispose();
            this.audioSource = null;
        }
        mLog.i("stopping video effect pipeline.");
        stopVideoEffectPipelineInternal(false);
        mLog.i("disposing local video handler");
        RTCLocalVideoHandler rTCLocalVideoHandler = this.localVideoHandler;
        if (rTCLocalVideoHandler != null) {
            rTCLocalVideoHandler.dispose();
            this.localVideoHandler = null;
        }
        this.localRendererMap.clear();
        this.remoteRendererMap.clear();
        mLog.i("Closing peer connection done.");
        WebRTCAndroidSessionInterface.PeerClientListener peerClientListener = this.events;
        if (peerClientListener != null) {
            peerClientListener.onPeerConnectionClosed(this.sessionId);
        } else {
            mLog.w("Event handler already been released when trying to call onPeerConnectionClosed, double check the object life cycle at top level");
        }
        this.factoryWrapper = null;
        this.events = null;
    }

    private void executeCreateMediaConstraints() {
        mLog.i("executeCreateMediaConstraints");
        if (this.renderLocalVideo) {
            RTCLocalVideoHandler rTCLocalVideoHandler = this.localVideoHandler;
            PeerConnectionParameters peerConnectionParameters = this.peerConnectionParameters;
            rTCLocalVideoHandler.createVideoConstraintsIfSupported(peerConnectionParameters.videoWidth, peerConnectionParameters.videoHeight, peerConnectionParameters.videoFps);
        }
        this.audioConstraints = new MediaConstraints();
        if (this.peerConnectionParameters.noAudioProcessing) {
            mLog.d("Disabling audio processing");
            this.audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair(AUDIO_ECHO_CANCELLATION_CONSTRAINT, PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE));
            this.audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair(AUDIO_AUTO_GAIN_CONTROL_CONSTRAINT, PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE));
            this.audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair(AUDIO_HIGH_PASS_FILTER_CONSTRAINT, PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE));
            this.audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair(AUDIO_NOISE_SUPPRESSION_CONSTRAINT, PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE));
        }
        if (this.peerConnectionParameters.enableLevelControl) {
            mLog.d("Enabling level control.");
            this.audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair(AUDIO_LEVEL_CONTROL_CONSTRAINT, "true"));
        }
        this.sdpMediaConstraints = createSdpConstraints();
    }

    private void executeCreateMediaTracks() {
        PeerConnectionFactoryWrapper peerConnectionFactoryWrapper = this.factoryWrapper;
        if (peerConnectionFactoryWrapper != null && !this.isError) {
            this.localMediaStream = peerConnectionFactoryWrapper.createLocalMediaStream("ARDAMS");
            if (this.renderLocalVideo && !provideVideoTrack()) {
                mLog.w("Failed to create local video track");
                this.renderLocalVideo = false;
            }
            if (!this.localAudioEnabled) {
                return;
            }
            this.localMediaStream.addTrack(createAudioTrack());
            return;
        }
        mLog.e("Peerconnection factory is not created");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeCreatePeerConnection() {
        if (this.factoryWrapper != null && !this.isError) {
            mLog.d("Create peer connection.");
            RtcscLogger rtcscLogger = mLog;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PeerConnection Constraints: ");
            outline107.append(this.pcConstraints.toString());
            rtcscLogger.d(outline107.toString());
            this.queuedRemoteCandidates = new LinkedList<>();
            this.rtcConfig = new PeerConnection.RTCConfiguration(this.peerConnectionParameters.iceServers);
            PeerConnection.RTCConfiguration rTCConfiguration = this.rtcConfig;
            PeerConnectionParameters peerConnectionParameters = this.peerConnectionParameters;
            rTCConfiguration.bundlePolicy = peerConnectionParameters.bundlePolicy;
            rTCConfiguration.rtcpMuxPolicy = peerConnectionParameters.rtcpMuxPolicy;
            rTCConfiguration.tcpCandidatePolicy = PeerConnection.TcpCandidatePolicy.DISABLED;
            rTCConfiguration.continualGatheringPolicy = PeerConnection.ContinualGatheringPolicy.GATHER_ONCE;
            rTCConfiguration.keyType = PeerConnection.KeyType.ECDSA;
            this.peerConnection = this.factoryWrapper.createPeerConnection(rTCConfiguration, this.pcConstraints, this.pcObserver);
            this.dataChannelListener = MediaUIBridge.getInstance().getDataChannelListener(this.sessionId);
            if (this.localDataChannelEnabled) {
                mLog.i("Setup DataChannels");
                setupDataChannels();
            }
            Logging.enableLogToDebugOutput(Logging.Severity.LS_INFO);
            if (this.peerConnectionParameters.tracing) {
                mLog.d("Tracing is enabled.");
                Logging.enableTracing("logcat:", EnumSet.of(Logging.TraceLevel.TRACE_DEFAULT));
            }
            MediaUIBridge.getInstance().addListener(this);
            this.peerConnection.addStream(this.localMediaStream);
            if (this.renderLocalVideo && this.localVideoTrack != null) {
                findVideoSender();
            }
            mLog.d("Peer connection created.");
            return;
        }
        mLog.e("Peerconnection factory is not created");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeCreatePeerConnectionConstraints() {
        this.pcConstraints = new MediaConstraints();
        PeerConnectionParameters peerConnectionParameters = this.peerConnectionParameters;
        if (!peerConnectionParameters.loopback && peerConnectionParameters.keyExchange.equalsIgnoreCase(Constants.DTLS)) {
            this.pcConstraints.optional.add(new MediaConstraints.KeyValuePair(DTLS_SRTP_KEY_AGREEMENT_CONSTRAINT, "true"));
        } else {
            mLog.i("DTLS is disabled");
            this.pcConstraints.optional.add(new MediaConstraints.KeyValuePair(DTLS_SRTP_KEY_AGREEMENT_CONSTRAINT, PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE));
        }
        this.pcConstraints.optional.add(new MediaConstraints.KeyValuePair(GOOG_DSCP, "true"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeResetRemoteDescription() {
    }

    private void executeSetRemoteDescription(final SessionDescription sessionDescription) {
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.14
            @Override // java.lang.Runnable
            public void run() {
                if (WebRTCAndroidSession.this.peerConnection == null || WebRTCAndroidSession.this.isError) {
                    return;
                }
                WebRTCAndroidSession.mLog.d("Set remote SDP.");
                WebRTCAndroidSession.this.peerConnection.setRemoteDescription(WebRTCAndroidSession.this.remoteSdpObserver, sessionDescription);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeSwitchCamera() {
        if (this.renderLocalVideo && !this.isError) {
            this.localVideoHandler.switchCamera();
            return;
        }
        RtcscLogger rtcscLogger = mLog;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to switch camera. Video: ");
        outline107.append(this.renderLocalVideo);
        outline107.append(". Error : ");
        outline107.append(this.isError);
        rtcscLogger.e(outline107.toString());
    }

    private void findVideoSender() {
        PeerConnection peerConnection = this.peerConnection;
        if (peerConnection == null || this.isError) {
            return;
        }
        for (RtpSender rtpSender : peerConnection.getSenders()) {
            if (rtpSender.track() != null && rtpSender.track().kind().equals("video")) {
                mLog.d("Found video sender.");
                this.localVideoSender = rtpSender;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getStats() {
        PeerConnection peerConnection = this.peerConnection;
        if (peerConnection == null || this.isError || peerConnection.getStats(new StatsObserver() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.21
            @Override // org.webrtc.StatsObserver
            public void onComplete(final StatsReport[] statsReportArr) {
                WebRTCAndroidSession.this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.21.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (WebRTCAndroidSession.this.events != null) {
                            WebRTCAndroidSession.this.events.onPeerConnectionStatsReady(WebRTCAndroidSession.this.sessionId, statsReportArr);
                        }
                    }
                });
            }
        }, null)) {
            return;
        }
        mLog.e("getStats() returns false!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLocalOrRemoteSDPSet() {
        if (this.peerConnection == null || this.isError) {
            return;
        }
        RtcscLogger rtcscLogger = mLog;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SDP onSetSuccess signallingState = ");
        outline107.append(this.signalingState);
        outline107.append(" isOfferer = ");
        GeneratedOutlineSupport1.outline185(outline107, this.isOfferer, rtcscLogger);
        if (this.isOfferer) {
            PeerConnection.SignalingState signalingState = this.signalingState;
            if (signalingState == PeerConnection.SignalingState.HAVE_LOCAL_OFFER) {
                String str = this.peerConnection.getLocalDescription().description;
                mLog.i("Local SDP offer set Successfully");
                WebRTCAndroidSessionInterface.PeerClientListener peerClientListener = this.events;
                if (peerClientListener == null) {
                    return;
                }
                peerClientListener.onLocalDescriptionSet(this.sessionId, str, true);
            } else if (signalingState == PeerConnection.SignalingState.HAVE_REMOTE_PRANSWER) {
                mLog.i("Remote SDP provisional answer set successfully");
                drainCandidates();
                applyMaxBitrateForVideoSender();
            } else {
                mLog.i("Remote SDP answer set successfully");
                drainCandidates();
                this.isOfferer = false;
                if (this.events == null) {
                    return;
                }
                this.events.onRemoteDescriptionSet(this.sessionId, this.peerConnection.getRemoteDescription().description, false);
                this.events.onSignalingDone(this.sessionId);
                applyMaxBitrateForVideoSender();
            }
        } else if (this.signalingState == PeerConnection.SignalingState.HAVE_REMOTE_OFFER) {
            mLog.i("Remote SDP offer set successfully");
            if (this.events == null) {
                return;
            }
            this.events.onRemoteDescriptionSet(this.sessionId, this.peerConnection.getRemoteDescription().description, true);
        } else {
            String str2 = this.peerConnection.getLocalDescription().description;
            mLog.i("Local SDP answer set successfully");
            RtcscLogger rtcscLogger2 = mLog;
            rtcscLogger2.d("Answer SDP:\n" + str2);
            WebRTCAndroidSessionInterface.PeerClientListener peerClientListener2 = this.events;
            if (peerClientListener2 == null) {
                mLog.w("events is null, can't fire callbacks");
                return;
            }
            peerClientListener2.onLocalDescriptionSet(this.sessionId, str2, false);
            drainCandidates();
            this.events.onSignalingDone(this.sessionId);
            applyMaxBitrateForVideoSender();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006d, code lost:
        if (r9.equals("VP8") != false) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void initializeWithPeerConnectionParams(com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.PeerConnectionParameters r9, java.lang.String r10) {
        /*
            r8 = this;
            com.amazon.rtcmedia.util.RtcscLogger r0 = com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.mLog
            java.lang.String r1 = "initializeWithPeerConnectionParams"
            r0.i(r1)
            r8.peerConnectionParameters = r9
            r8.sessionId = r10
            boolean r10 = r9.localVideoEnabled
            r0 = 0
            r1 = 1
            if (r10 != 0) goto L18
            boolean r10 = r9.remoteVideoEnabled
            if (r10 == 0) goto L16
            goto L18
        L16:
            r10 = r0
            goto L19
        L18:
            r10 = r1
        L19:
            r8.videoCallEnabled = r10
            boolean r10 = r9.localVideoEnabled
            r8.renderLocalVideo = r10
            boolean r10 = r9.remoteVideoEnabled
            r8.renderRemoteVideo = r10
            int r10 = r9.videoMaxBitrate
            r8.videoMaxKBitrate = r10
            boolean r10 = r8.renderLocalVideo
            if (r10 == 0) goto L31
            int r10 = r8.videoMaxKBitrate
            if (r10 <= 0) goto L31
            r8.isPendingVideoMaxSendBitrateSet = r1
        L31:
            boolean r10 = r9.localAudioEnabled
            r8.localAudioEnabled = r10
            boolean r10 = r9.remoteAudioEnabled
            r8.remoteAudioEnabled = r10
            boolean r10 = r9.localDataChannelEnabled
            if (r10 == 0) goto L43
            boolean r10 = r9.loopback
            if (r10 != 0) goto L43
            r10 = r1
            goto L44
        L43:
            r10 = r0
        L44:
            r8.localDataChannelEnabled = r10
            java.lang.String r10 = "H264"
            r8.preferredVideoCodec = r10
            boolean r2 = r8.videoCallEnabled
            if (r2 == 0) goto L9b
            java.lang.String r9 = r9.videoCodec
            if (r9 == 0) goto L9b
            r2 = -1
            int r3 = r9.hashCode()
            java.lang.String r4 = "VP9"
            java.lang.String r5 = "VP8"
            r6 = 3
            r7 = 2
            switch(r3) {
                case -2140422726: goto L7a;
                case -1031013795: goto L70;
                case 85182: goto L69;
                case 85183: goto L61;
                default: goto L60;
            }
        L60:
            goto L84
        L61:
            boolean r9 = r9.equals(r4)
            if (r9 == 0) goto L84
            r0 = r1
            goto L85
        L69:
            boolean r9 = r9.equals(r5)
            if (r9 == 0) goto L84
            goto L85
        L70:
            java.lang.String r0 = "H264 Baseline"
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L84
            r0 = r7
            goto L85
        L7a:
            java.lang.String r0 = "H264 High"
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L84
            r0 = r6
            goto L85
        L84:
            r0 = r2
        L85:
            if (r0 == 0) goto L99
            if (r0 == r1) goto L96
            if (r0 == r7) goto L93
            if (r0 == r6) goto L90
            r8.preferredVideoCodec = r10
            goto L9b
        L90:
            r8.preferredVideoCodec = r10
            goto L9b
        L93:
            r8.preferredVideoCodec = r10
            goto L9b
        L96:
            r8.preferredVideoCodec = r4
            goto L9b
        L99:
            r8.preferredVideoCodec = r5
        L9b:
            com.amazon.rtcmedia.util.RtcscLogger r9 = com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.mLog
            java.lang.String r10 = "Preferred video codec: "
            java.lang.StringBuilder r10 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r10)
            java.lang.String r0 = r8.preferredVideoCodec
            r10.append(r0)
            java.lang.String r10 = r10.toString()
            r9.i(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.initializeWithPeerConnectionParams(com.amazon.rtcmedia.webrtc.WebRTCAndroidSession$PeerConnectionParameters, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isValidListener(RTCDataChannelEventListener rTCDataChannelEventListener) {
        if (rTCDataChannelEventListener == null) {
            mLog.e("DataChannelEventListener is null");
            return false;
        }
        return true;
    }

    private boolean isValidRenderer(RTCSurfaceRenderer rTCSurfaceRenderer) {
        if (rTCSurfaceRenderer == null) {
            mLog.e("surface renderer is null");
            return false;
        }
        return true;
    }

    private boolean isValidSessionId(String str) {
        String str2 = this.sessionId;
        if (str2 == null || !str2.equals(str)) {
            RtcscLogger rtcscLogger = mLog;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Not for us, our sessionId = ");
            outline107.append(this.sessionId);
            rtcscLogger.e(outline107.toString());
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareMedia() {
        mLog.i("prepareMedia");
        this.localVideoHandler = new RTCLocalVideoHandler(this, this.sessionId);
        this.localVideoHandler.configureCapturer(this.peerConnectionParameters.videoSrcType == RTCVideoSrcType.SCREEN, this.peerConnectionParameters.preferCamera1);
        executeCreateMediaConstraints();
        executeCreateMediaTracks();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processRemoteStreamTracks(MediaStream mediaStream) {
        if (mediaStream.audioTracks.size() <= 1 && mediaStream.videoTracks.size() <= 1) {
            if (mediaStream.audioTracks.size() == 1) {
                this.remoteAudioTrack = mediaStream.audioTracks.get(0);
                this.remoteAudioTrack.setEnabled(this.remoteAudioEnabled);
            }
            if (mediaStream.videoTracks.size() != 1) {
                return;
            }
            this.remoteVideoTrack = mediaStream.videoTracks.get(0);
            this.remoteVideoTrack.setEnabled(this.renderRemoteVideo);
            RTCSurfaceRenderer remoteRenderer = MediaUIBridge.getInstance().getRemoteRenderer(this.sessionId);
            RtcscLogger rtcscLogger = mLog;
            rtcscLogger.i("remoteRenderer = " + remoteRenderer);
            if (remoteRenderer != null) {
                addVideoRenderer(this.remoteRendererMap, remoteRenderer, this.remoteVideoTrack);
                return;
            } else {
                mLog.w("No remote renderer been added yet");
                return;
            }
        }
        reportError("Weird-looking stream: " + mediaStream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean provideVideoTrack() {
        this.localVideoTrack = this.localVideoHandler.provideLocalVideoTrack(this.factoryWrapper, this.renderLocalVideo, "ARDAMSv0", this.peerConnectionParameters.videoSrc);
        if (this.localVideoTrack != null) {
            mLog.i("Adding local video track to local media stream");
            this.localMediaStream.addTrack(this.localVideoTrack);
            RTCSurfaceRenderer localRenderer = MediaUIBridge.getInstance().getLocalRenderer(this.sessionId);
            RtcscLogger rtcscLogger = mLog;
            rtcscLogger.i("localRenderer = " + localRenderer);
            if (localRenderer != null) {
                addVideoRenderer(this.localRendererMap, localRenderer, this.localVideoTrack);
            } else {
                mLog.w("No local renderer been added yet");
            }
        }
        return this.localVideoTrack != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerDataChannelObserver(DataChannel dataChannel) {
        dataChannel.registerObserver(new RTCDataChannelObserver(dataChannel));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeVideoRenderer(Map<VideoRenderer.Callbacks, VideoRenderer> map, RTCSurfaceRenderer rTCSurfaceRenderer, VideoTrack videoTrack) {
        if (videoTrack != null) {
            videoTrack.removeRenderer(map.get(rTCSurfaceRenderer));
        }
        map.remove(rTCSurfaceRenderer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportError(final String str) {
        RtcscLogger rtcscLogger = mLog;
        rtcscLogger.e("Peerconnection error: " + str);
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.13
            @Override // java.lang.Runnable
            public void run() {
                if (!WebRTCAndroidSession.this.isError) {
                    if (WebRTCAndroidSession.this.events != null) {
                        WebRTCAndroidSession.this.events.onPeerConnectionError(WebRTCAndroidSession.this.sessionId, str);
                    }
                    WebRTCAndroidSession.this.isError = true;
                }
            }
        });
    }

    private void setupDataChannels() {
        for (RTCDataChannelParams rTCDataChannelParams : this.peerConnectionParameters.dataChannelParams) {
            createDataChannel(rTCDataChannelParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startVideoSource() {
        mLog.i("startVideoSource");
        this.localVideoHandler.restartLocalVideoSource();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopVideoEffectPipelineInternal(boolean z) {
        RTCVideoEffect rTCVideoEffect = this.videoEffect;
        if (rTCVideoEffect != null && !this.isError) {
            if (z) {
                WebRTCAndroidSessionInterface.PeerClientListener peerClientListener = this.events;
                if (peerClientListener != null) {
                    peerClientListener.onVideoEffectAbort(this.sessionId);
                }
            } else {
                rTCVideoEffect.endVideoEffect();
            }
            this.videoEffect = null;
            return;
        }
        mLog.w("stopVideoEffectPipelineInternal: videoEffect is null or error occurred");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopVideoSource() {
        mLog.i("stopVideoSource");
        stopVideoEffectPipelineInternal(true);
        this.localVideoHandler.stopLocalVideoSource();
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void addRemoteIceCandidate(final IceCandidate iceCandidate) {
        mLog.i("addRemoteIceCandidate");
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.6
            @Override // java.lang.Runnable
            public void run() {
                if (WebRTCAndroidSession.this.peerConnection == null || WebRTCAndroidSession.this.isError) {
                    return;
                }
                if (WebRTCAndroidSession.this.queuedRemoteCandidates != null) {
                    WebRTCAndroidSession.this.queuedRemoteCandidates.add(iceCandidate);
                } else {
                    WebRTCAndroidSession.this.peerConnection.addIceCandidate(iceCandidate);
                }
            }
        });
    }

    public void changeCaptureFormat(final int i, final int i2, final int i3) {
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.22
            @Override // java.lang.Runnable
            public void run() {
                WebRTCAndroidSession.this.executeChangeCaptureFormat(i, i2, i3);
            }
        });
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void createAnswer() {
        mLog.i("createAnswer");
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.5
            @Override // java.lang.Runnable
            public void run() {
                if (WebRTCAndroidSession.this.peerConnection == null || WebRTCAndroidSession.this.isError) {
                    return;
                }
                WebRTCAndroidSession.mLog.d("PeerConnection create ANSWER");
                WebRTCAndroidSession.this.peerConnection.createAnswer(WebRTCAndroidSession.this.localSdpObserver, WebRTCAndroidSession.this.sdpMediaConstraints);
            }
        });
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void createOffer() {
        mLog.i("createOffer");
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.4
            @Override // java.lang.Runnable
            public void run() {
                if (WebRTCAndroidSession.this.peerConnection == null || WebRTCAndroidSession.this.isError) {
                    return;
                }
                WebRTCAndroidSession.mLog.d("PeerConnection Create OFFER");
                WebRTCAndroidSession.this.isOfferer = true;
                WebRTCAndroidSession.this.peerConnection.createOffer(WebRTCAndroidSession.this.localSdpObserver, WebRTCAndroidSession.this.sdpMediaConstraints);
            }
        });
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void fetchStats() {
        mLog.i("fetchStats");
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.16
            @Override // java.lang.Runnable
            public void run() {
                WebRTCAndroidSession.this.getStats();
            }
        });
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void initialize(final PeerConnectionParameters peerConnectionParameters, final String str, boolean z) {
        RtcscLogger rtcscLogger = mLog;
        rtcscLogger.i("initialize: sessionId = " + str + ", isInitiator = " + z);
        LoggingPeerParameters(peerConnectionParameters);
        this.localRendererMap.clear();
        this.remoteRendererMap.clear();
        this.dataChannelListener = null;
        this.statsTimer = new Timer();
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.1
            @Override // java.lang.Runnable
            public void run() {
                WebRTCAndroidSession.this.factoryWrapper = WebRTCAndroidSessionFactory.getInstance().getPeerConnectionFactoryWrapper();
                if (WebRTCAndroidSession.this.factoryWrapper == null) {
                    WebRTCAndroidSession.mLog.e("PeerConnectionFactory is not created yet");
                    return;
                }
                WebRTCAndroidSession.this.initializeWithPeerConnectionParams(peerConnectionParameters, str);
                try {
                    if (!WebRTCAndroidSession.this.hasWarmedUp) {
                        WebRTCAndroidSession.this.prepareMedia();
                    }
                    WebRTCAndroidSession.this.executeCreatePeerConnectionConstraints();
                    WebRTCAndroidSession.this.executeCreatePeerConnection();
                } catch (Exception e) {
                    WebRTCAndroidSession.mLog.e("Failed to create peer connection", e);
                    WebRTCAndroidSession.this.reportError(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Failed to create peer connection: ")));
                }
            }
        });
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void initializeVideoEffectPipeline() {
        mLog.i("initializeVideoEffectPipeline");
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.17
            @Override // java.lang.Runnable
            public void run() {
                if (WebRTCAndroidSession.this.isError || WebRTCAndroidSession.this.localVideoHandler == null) {
                    WebRTCAndroidSession.mLog.w("initializeVideoEffectPipeline: localVideoHandler is null or error occurred");
                    return;
                }
                WebRTCAndroidSession webRTCAndroidSession = WebRTCAndroidSession.this;
                webRTCAndroidSession.videoEffect = webRTCAndroidSession.localVideoHandler.createRTCVideoEffect(WebRTCAndroidSession.this);
            }
        });
    }

    @Override // com.amazon.rtcmedia.webrtc.RTCLocalVideoHandler.RTCCameraListener
    public void onCameraFailure(final String str) {
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.29
            @Override // java.lang.Runnable
            public void run() {
                if (WebRTCAndroidSession.this.events != null) {
                    WebRTCAndroidSession.this.events.onCameraFailure(WebRTCAndroidSession.this.sessionId, str);
                }
            }
        });
    }

    @Override // com.amazon.rtcmedia.webrtc.RTCLocalVideoHandler.RTCCameraListener
    public void onCameraOpening(final boolean z) {
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.30
            @Override // java.lang.Runnable
            public void run() {
                if (WebRTCAndroidSession.this.events != null) {
                    WebRTCAndroidSession.this.events.onCameraOpening(WebRTCAndroidSession.this.sessionId, z);
                }
            }
        });
    }

    @Override // com.amazon.rtcmedia.util.MediaUIBridge.MediaUIBridgeListener
    public void onDataChannelListenerAdded(String str, final RTCDataChannelEventListener rTCDataChannelEventListener) {
        RtcscLogger rtcscLogger = mLog;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("dataChannelListenerAdded, sessionId = ");
        outline107.append(this.sessionId);
        rtcscLogger.i(outline107.toString());
        if (!isValidSessionId(this.sessionId) || !isValidListener(rTCDataChannelEventListener)) {
            return;
        }
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.27
            @Override // java.lang.Runnable
            public void run() {
                RTCDataChannelEventListener rTCDataChannelEventListener2 = WebRTCAndroidSession.this.dataChannelListener;
                RTCDataChannelEventListener rTCDataChannelEventListener3 = rTCDataChannelEventListener;
                if (rTCDataChannelEventListener2 == rTCDataChannelEventListener3) {
                    WebRTCAndroidSession.mLog.w("Already have a dataChannelListener with this sessionId");
                } else {
                    WebRTCAndroidSession.this.dataChannelListener = rTCDataChannelEventListener3;
                }
            }
        });
    }

    @Override // com.amazon.rtcmedia.util.MediaUIBridge.MediaUIBridgeListener
    public void onDataChannelListenerRemoved(String str, final RTCDataChannelEventListener rTCDataChannelEventListener) {
        RtcscLogger rtcscLogger = mLog;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("dataChannelListenerRemoved, sessionId = ");
        outline107.append(this.sessionId);
        rtcscLogger.i(outline107.toString());
        if (!isValidSessionId(this.sessionId) || !isValidListener(rTCDataChannelEventListener)) {
            return;
        }
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.28
            @Override // java.lang.Runnable
            public void run() {
                if (WebRTCAndroidSession.this.dataChannelListener != rTCDataChannelEventListener) {
                    WebRTCAndroidSession.mLog.w("dataChannelListener does not match");
                } else {
                    WebRTCAndroidSession.this.dataChannelListener = null;
                }
            }
        });
    }

    @Override // com.amazon.rtcmedia.webrtc.RTCRendererEventsHandler.RTCRendererEventsListener
    public void onFirstFrameRendered(final ViewDirection viewDirection) {
        RtcscLogger rtcscLogger = mLog;
        rtcscLogger.i("onFirstFrameRendered for direction: " + viewDirection);
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.31
            @Override // java.lang.Runnable
            public void run() {
                if (WebRTCAndroidSession.this.events == null) {
                    WebRTCAndroidSession.mLog.w("onFirstFrameRendered: events is null");
                } else {
                    WebRTCAndroidSession.this.events.onFirstFrameRendered(WebRTCAndroidSession.this.sessionId, viewDirection);
                }
            }
        });
    }

    @Override // com.amazon.rtcmedia.util.MediaUIBridge.MediaUIBridgeListener
    public void onLocalRendererAdded(String str, final RTCSurfaceRenderer rTCSurfaceRenderer) {
        GeneratedOutlineSupport1.outline160("localRendererAdded, sessionId = ", str, mLog);
        if (!isValidSessionId(str) || !isValidRenderer(rTCSurfaceRenderer)) {
            return;
        }
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.23
            @Override // java.lang.Runnable
            public void run() {
                if (WebRTCAndroidSession.this.localRendererMap.containsKey(rTCSurfaceRenderer)) {
                    WebRTCAndroidSession.mLog.i("local renderer already added");
                    return;
                }
                WebRTCAndroidSession webRTCAndroidSession = WebRTCAndroidSession.this;
                webRTCAndroidSession.addVideoRenderer(webRTCAndroidSession.localRendererMap, rTCSurfaceRenderer, WebRTCAndroidSession.this.localVideoTrack);
            }
        });
    }

    @Override // com.amazon.rtcmedia.util.MediaUIBridge.MediaUIBridgeListener
    public void onLocalRendererRemoved(String str, final RTCSurfaceRenderer rTCSurfaceRenderer) {
        GeneratedOutlineSupport1.outline160("localRendererRemoved, sessionId = ", str, mLog);
        if (!isValidSessionId(str) || !isValidRenderer(rTCSurfaceRenderer)) {
            return;
        }
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.24
            @Override // java.lang.Runnable
            public void run() {
                WebRTCAndroidSession webRTCAndroidSession = WebRTCAndroidSession.this;
                webRTCAndroidSession.removeVideoRenderer(webRTCAndroidSession.localRendererMap, rTCSurfaceRenderer, WebRTCAndroidSession.this.localVideoTrack);
            }
        });
    }

    @Override // com.amazon.rtcmedia.util.MediaUIBridge.MediaUIBridgeListener
    public void onRemoteRendererAdded(String str, final RTCSurfaceRenderer rTCSurfaceRenderer) {
        GeneratedOutlineSupport1.outline160("remoteRendererAdded, sessionId = ", str, mLog);
        if (!isValidSessionId(str) || !isValidRenderer(rTCSurfaceRenderer)) {
            return;
        }
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.25
            @Override // java.lang.Runnable
            public void run() {
                if (WebRTCAndroidSession.this.remoteRendererMap.containsKey(rTCSurfaceRenderer)) {
                    WebRTCAndroidSession.mLog.i("remote renderer already added");
                    return;
                }
                WebRTCAndroidSession webRTCAndroidSession = WebRTCAndroidSession.this;
                webRTCAndroidSession.addVideoRenderer(webRTCAndroidSession.remoteRendererMap, rTCSurfaceRenderer, WebRTCAndroidSession.this.remoteVideoTrack);
            }
        });
    }

    @Override // com.amazon.rtcmedia.util.MediaUIBridge.MediaUIBridgeListener
    public void onRemoteRendererRemoved(String str, final RTCSurfaceRenderer rTCSurfaceRenderer) {
        GeneratedOutlineSupport1.outline160("remoteRendererRemoved, sessionId = ", str, mLog);
        if (!isValidSessionId(str) || !isValidRenderer(rTCSurfaceRenderer)) {
            return;
        }
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.26
            @Override // java.lang.Runnable
            public void run() {
                WebRTCAndroidSession webRTCAndroidSession = WebRTCAndroidSession.this;
                webRTCAndroidSession.removeVideoRenderer(webRTCAndroidSession.remoteRendererMap, rTCSurfaceRenderer, WebRTCAndroidSession.this.remoteVideoTrack);
            }
        });
    }

    @Override // com.amazon.rtcmedia.util.MediaUIBridge.MediaUIBridgeListener
    public void onScreenCapturerDimensionsSet(String str, int i, int i2) {
        mLog.i(String.format(Locale.US, "onScreenCapturerDimensionsSet, sessionId = %s, width = %d, height = %d ", str, Integer.valueOf(i), Integer.valueOf(i2)));
        if (!isValidSessionId(str)) {
            return;
        }
        changeCaptureFormat(i, i2, this.peerConnectionParameters.videoFps);
    }

    @Override // com.amazon.rtcmedia.webrtc.RTCVideoEffect.WebRTCVideoEffectTransitionListener
    public void onWebRTCVideoEffectTransition(final String str) {
        mLog.i("onWebRTCVideoEffectTransition");
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.20
            @Override // java.lang.Runnable
            public void run() {
                RtcscLogger rtcscLogger = WebRTCAndroidSession.mLog;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onWebRTCVideoEffectTransition:");
                outline107.append(str);
                rtcscLogger.d(outline107.toString());
                if (WebRTCAndroidSession.this.videoEffect == null || WebRTCAndroidSession.this.events == null) {
                    WebRTCAndroidSession.mLog.w("onWebRTCVideoEffectTransition: videoEffect or events is null");
                } else {
                    WebRTCAndroidSession.this.events.onVideoEffectTransition(WebRTCAndroidSession.this.sessionId, str);
                }
            }
        });
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void processRemoteAnswer(String str, SessionDescription.Type type) {
        mLog.i("processRemoteAnswer");
        RtcscLogger rtcscLogger = mLog;
        rtcscLogger.d("Answer SDP:\n" + str);
        executeSetRemoteDescription(new SessionDescription(type, str));
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void processRemoteOffer(String str) {
        mLog.i("processRemoteOffer");
        RtcscLogger rtcscLogger = mLog;
        rtcscLogger.d("Offer SDP:\n" + str);
        executeSetRemoteDescription(new SessionDescription(SessionDescription.Type.OFFER, str));
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void removeRemoteIceCandidates(final IceCandidate[] iceCandidateArr) {
        mLog.i("removeRemoteIceCandidates");
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.7
            @Override // java.lang.Runnable
            public void run() {
                if (WebRTCAndroidSession.this.peerConnection == null || WebRTCAndroidSession.this.isError) {
                    return;
                }
                WebRTCAndroidSession.this.drainCandidates();
                WebRTCAndroidSession.this.peerConnection.removeIceCandidates(iceCandidateArr);
            }
        });
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void resetRemoteDescription() {
        mLog.i("resetRemoteDescription");
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.8
            @Override // java.lang.Runnable
            public void run() {
                WebRTCAndroidSession.this.executeResetRemoteDescription();
            }
        });
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void restartIce(List<PeerConnection.IceServer> list, boolean z) {
        RtcscLogger rtcscLogger = mLog;
        rtcscLogger.i("Restarting ICE. Sending New Offer : " + z);
        PeerConnection peerConnection = this.peerConnection;
        if (peerConnection == null || this.isError) {
            return;
        }
        PeerConnection.RTCConfiguration rTCConfiguration = this.rtcConfig;
        rTCConfiguration.iceServers = list;
        peerConnection.setConfiguration(rTCConfiguration);
        if (!z) {
            return;
        }
        createOffer();
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void sendVideoEffectCommand(final String str) {
        mLog.i("sendVideoEffectCommand");
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.18
            @Override // java.lang.Runnable
            public void run() {
                if (WebRTCAndroidSession.this.videoEffect == null || WebRTCAndroidSession.this.isError) {
                    WebRTCAndroidSession.mLog.w("sendVideoEffectCommand: videoEffect is null or error occurred");
                } else {
                    WebRTCAndroidSession.this.videoEffect.setVideoEffect(str);
                }
            }
        });
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void setLocalAnswer(final String str) {
        mLog.i("setLocalAnswer");
        RtcscLogger rtcscLogger = mLog;
        rtcscLogger.d("Answer SDP:\n" + str);
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.10
            @Override // java.lang.Runnable
            public void run() {
                if (WebRTCAndroidSession.this.peerConnection == null || WebRTCAndroidSession.this.isError) {
                    return;
                }
                WebRTCAndroidSession.mLog.d("Set local SDP from answer");
                WebRTCAndroidSession.this.peerConnection.setLocalDescription(WebRTCAndroidSession.this.localSdpObserver, new SessionDescription(SessionDescription.Type.ANSWER, str));
            }
        });
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void setLocalAudioEnabled(boolean z) {
        RtcscLogger rtcscLogger = mLog;
        rtcscLogger.i("setting local Audio State to: " + z);
        if (z) {
            this.executor.execute(this.setLocalAudioEnabledRunnable);
        } else {
            this.executor.execute(this.setLocalAudioDisabledRunnable);
        }
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void setLocalOffer(final String str) {
        mLog.i("setLocalOffer");
        RtcscLogger rtcscLogger = mLog;
        rtcscLogger.d("Offer SDP:\n" + str);
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.9
            @Override // java.lang.Runnable
            public void run() {
                if (WebRTCAndroidSession.this.peerConnection == null || WebRTCAndroidSession.this.isError) {
                    return;
                }
                SessionDescription sessionDescription = new SessionDescription(SessionDescription.Type.OFFER, str);
                WebRTCAndroidSession.mLog.d("Set local SDP from offer");
                WebRTCAndroidSession.this.peerConnection.setLocalDescription(WebRTCAndroidSession.this.localSdpObserver, sessionDescription);
            }
        });
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void setLocalVideoEnabled(boolean z) {
        RtcscLogger rtcscLogger = mLog;
        rtcscLogger.i("setting local Video State to: " + z);
        if (z) {
            this.executor.execute(this.setLocalVideoEnabledRunnable);
        } else {
            this.executor.execute(this.setLocalVideoDisabledRunnable);
        }
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void setMediaConstraints(Map<String, Integer> map) {
        mLog.i("setMediaConstraints");
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void setRemoteAudioEnabled(boolean z) {
        RtcscLogger rtcscLogger = mLog;
        rtcscLogger.i("setting remote Audio State to: " + z);
        if (z) {
            this.executor.execute(this.setRemoteAudioEnabledRunnable);
        } else {
            this.executor.execute(this.setRemoteAudioDisabledRunnable);
        }
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void setRemoteMediaCapability(final boolean z, final boolean z2) {
        RtcscLogger rtcscLogger = mLog;
        rtcscLogger.i("setRemoteMediaCapability: audioCapable - " + z + " videoCapable - " + z2);
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.11
            @Override // java.lang.Runnable
            public void run() {
                boolean z3 = (z == WebRTCAndroidSession.this.remoteAudioCapable && z2 == WebRTCAndroidSession.this.remoteVideoCapable) ? false : true;
                WebRTCAndroidSession.this.remoteAudioCapable = z;
                WebRTCAndroidSession.this.remoteVideoCapable = z2;
                if (z3) {
                    WebRTCAndroidSession webRTCAndroidSession = WebRTCAndroidSession.this;
                    webRTCAndroidSession.sdpMediaConstraints = webRTCAndroidSession.createSdpConstraints();
                }
            }
        });
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void setRemoteVideoEnabled(boolean z) {
        RtcscLogger rtcscLogger = mLog;
        rtcscLogger.i("setting remote Video State to: " + z);
        if (z) {
            this.executor.execute(this.setRemoteVideoEnabledRunnable);
        } else {
            this.executor.execute(this.setRemoteVideoDisabledRunnable);
        }
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void shutdown() {
        mLog.i("shutdown");
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    WebRTCAndroidSession.this.executeClose();
                } catch (Exception e) {
                    WebRTCAndroidSession.mLog.e("Failed to shutdown session", e);
                    WebRTCAndroidSession.this.reportError(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Failed to shutdown session")));
                }
            }
        });
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void stopVideoEffectPipeline() {
        mLog.i("stopVideoEffectPipeline");
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.19
            @Override // java.lang.Runnable
            public void run() {
                WebRTCAndroidSession.this.stopVideoEffectPipelineInternal(false);
            }
        });
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void switchCamera() {
        mLog.i("switchCamera");
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.15
            @Override // java.lang.Runnable
            public void run() {
                WebRTCAndroidSession.this.executeSwitchCamera();
            }
        });
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface
    public void warmUp(final PeerConnectionParameters peerConnectionParameters, final String str) {
        GeneratedOutlineSupport1.outline160("warmUp: sessionId = ", str, mLog);
        LoggingPeerParameters(peerConnectionParameters);
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.2
            @Override // java.lang.Runnable
            public void run() {
                WebRTCAndroidSession.this.factoryWrapper = WebRTCAndroidSessionFactory.getInstance().getPeerConnectionFactoryWrapper();
                if (WebRTCAndroidSession.this.factoryWrapper == null) {
                    WebRTCAndroidSession.mLog.e("PeerConnectionFactory is not created yet");
                    return;
                }
                WebRTCAndroidSession.this.initializeWithPeerConnectionParams(peerConnectionParameters, str);
                try {
                    WebRTCAndroidSession.this.prepareMedia();
                } catch (Exception e) {
                    WebRTCAndroidSession.mLog.e("Failed to warmUp media", e);
                    WebRTCAndroidSession.this.reportError(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Failed to warmUp media: ")));
                }
                WebRTCAndroidSession.this.hasWarmedUp = true;
                WebRTCAndroidSession.mLog.i("WarmUp completed");
            }
        });
    }
}
