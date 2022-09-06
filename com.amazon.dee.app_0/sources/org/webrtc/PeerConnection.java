package org.webrtc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.webrtc.DataChannel;
/* loaded from: classes5.dex */
public class PeerConnection {
    private final long nativeObserver;
    private final long nativePeerConnection;
    private final List<MediaStream> localStreams = new LinkedList();
    private List<RtpSender> senders = new LinkedList();
    private List<RtpReceiver> receivers = new LinkedList();

    /* loaded from: classes5.dex */
    public enum BundlePolicy {
        BALANCED,
        MAXBUNDLE,
        MAXCOMPAT
    }

    /* loaded from: classes5.dex */
    public enum CandidateNetworkPolicy {
        ALL,
        LOW_COST
    }

    /* loaded from: classes5.dex */
    public enum ContinualGatheringPolicy {
        GATHER_ONCE,
        GATHER_CONTINUALLY
    }

    /* loaded from: classes5.dex */
    public enum IceConnectionState {
        NEW,
        CHECKING,
        CONNECTED,
        COMPLETED,
        FAILED,
        DISCONNECTED,
        CLOSED
    }

    /* loaded from: classes5.dex */
    public enum IceGatheringState {
        NEW,
        GATHERING,
        COMPLETE
    }

    /* loaded from: classes5.dex */
    public static class IceServer {
        public final String hostname;
        public final String password;
        public final List<String> tlsAlpnProtocols;
        public final TlsCertPolicy tlsCertPolicy;
        public final List<String> tlsEllipticCurves;
        @Deprecated
        public final String uri;
        public final List<String> urls;
        public final String username;

        /* loaded from: classes5.dex */
        public static class Builder {
            private String hostname;
            private String password;
            private List<String> tlsAlpnProtocols;
            private TlsCertPolicy tlsCertPolicy;
            private List<String> tlsEllipticCurves;
            private final List<String> urls;
            private String username;

            public IceServer createIceServer() {
                return new IceServer(this.urls.get(0), this.urls, this.username, this.password, this.tlsCertPolicy, this.hostname, this.tlsAlpnProtocols, this.tlsEllipticCurves);
            }

            public Builder setHostname(String str) {
                this.hostname = str;
                return this;
            }

            public Builder setPassword(String str) {
                this.password = str;
                return this;
            }

            public Builder setTlsAlpnProtocols(List<String> list) {
                this.tlsAlpnProtocols = list;
                return this;
            }

            public Builder setTlsCertPolicy(TlsCertPolicy tlsCertPolicy) {
                this.tlsCertPolicy = tlsCertPolicy;
                return this;
            }

            public Builder setTlsEllipticCurves(List<String> list) {
                this.tlsEllipticCurves = list;
                return this;
            }

            public Builder setUsername(String str) {
                this.username = str;
                return this;
            }

            private Builder(List<String> list) {
                this.username = "";
                this.password = "";
                this.tlsCertPolicy = TlsCertPolicy.TLS_CERT_POLICY_SECURE;
                this.hostname = "";
                if (list != null && !list.isEmpty()) {
                    this.urls = list;
                    return;
                }
                throw new IllegalArgumentException("urls == null || urls.isEmpty(): " + list);
            }
        }

        public static Builder builder(String str) {
            return new Builder(Collections.singletonList(str));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.urls);
            sb.append(" [");
            sb.append(this.username);
            sb.append(":");
            sb.append(this.password);
            sb.append("] [");
            sb.append(this.tlsCertPolicy);
            sb.append("] [");
            sb.append(this.hostname);
            sb.append("] [");
            sb.append(this.tlsAlpnProtocols);
            sb.append("] [");
            return GeneratedOutlineSupport1.outline95(sb, this.tlsEllipticCurves, "]");
        }

        @Deprecated
        public IceServer(String str) {
            this(str, "", "");
        }

        public static Builder builder(List<String> list) {
            return new Builder(list);
        }

        @Deprecated
        public IceServer(String str, String str2, String str3) {
            this(str, str2, str3, TlsCertPolicy.TLS_CERT_POLICY_SECURE);
        }

        @Deprecated
        public IceServer(String str, String str2, String str3, TlsCertPolicy tlsCertPolicy) {
            this(str, str2, str3, tlsCertPolicy, "");
        }

        @Deprecated
        public IceServer(String str, String str2, String str3, TlsCertPolicy tlsCertPolicy, String str4) {
            this(str, Collections.singletonList(str), str2, str3, tlsCertPolicy, str4, null, null);
        }

        private IceServer(String str, List<String> list, String str2, String str3, TlsCertPolicy tlsCertPolicy, String str4, List<String> list2, List<String> list3) {
            if (str != null && list != null && !list.isEmpty()) {
                for (String str5 : list) {
                    if (str5 == null) {
                        throw new IllegalArgumentException("urls element is null: " + list);
                    }
                }
                if (str2 == null) {
                    throw new IllegalArgumentException("username == null");
                }
                if (str3 == null) {
                    throw new IllegalArgumentException("password == null");
                }
                if (str4 != null) {
                    this.uri = str;
                    this.urls = list;
                    this.username = str2;
                    this.password = str3;
                    this.tlsCertPolicy = tlsCertPolicy;
                    this.hostname = str4;
                    this.tlsAlpnProtocols = list2;
                    this.tlsEllipticCurves = list3;
                    return;
                }
                throw new IllegalArgumentException("hostname == null");
            }
            throw new IllegalArgumentException("uri == null || urls == null || urls.isEmpty()");
        }
    }

    /* loaded from: classes5.dex */
    public enum IceTransportsType {
        NONE,
        RELAY,
        NOHOST,
        ALL
    }

    /* loaded from: classes5.dex */
    public static class IntervalRange {
        private final int max;
        private final int min;

        public IntervalRange(int i, int i2) {
            this.min = i;
            this.max = i2;
        }

        public int getMax() {
            return this.max;
        }

        public int getMin() {
            return this.min;
        }
    }

    /* loaded from: classes5.dex */
    public enum KeyType {
        RSA,
        ECDSA
    }

    /* loaded from: classes5.dex */
    public interface Observer {
        void onAddStream(MediaStream mediaStream);

        void onAddTrack(RtpReceiver rtpReceiver, MediaStream[] mediaStreamArr);

        void onDataChannel(DataChannel dataChannel);

        void onIceCandidate(IceCandidate iceCandidate);

        void onIceCandidatesRemoved(IceCandidate[] iceCandidateArr);

        void onIceConnectionChange(IceConnectionState iceConnectionState);

        void onIceConnectionReceivingChange(boolean z);

        void onIceGatheringChange(IceGatheringState iceGatheringState);

        void onRemoveStream(MediaStream mediaStream);

        void onRenegotiationNeeded();

        void onSignalingChange(SignalingState signalingState);
    }

    /* loaded from: classes5.dex */
    public static class RTCConfiguration {
        public List<IceServer> iceServers;
        public TurnCustomizer turnCustomizer;
        public IceTransportsType iceTransportsType = IceTransportsType.ALL;
        public BundlePolicy bundlePolicy = BundlePolicy.BALANCED;
        public RtcpMuxPolicy rtcpMuxPolicy = RtcpMuxPolicy.REQUIRE;
        public TcpCandidatePolicy tcpCandidatePolicy = TcpCandidatePolicy.ENABLED;
        public CandidateNetworkPolicy candidateNetworkPolicy = CandidateNetworkPolicy.ALL;
        public int audioJitterBufferMaxPackets = 50;
        public boolean audioJitterBufferFastAccelerate = false;
        public int iceConnectionReceivingTimeout = -1;
        public int iceBackupCandidatePairPingInterval = -1;
        public KeyType keyType = KeyType.ECDSA;
        public ContinualGatheringPolicy continualGatheringPolicy = ContinualGatheringPolicy.GATHER_ONCE;
        public int iceCandidatePoolSize = 0;
        public boolean pruneTurnPorts = false;
        public boolean presumeWritableWhenFullyRelayed = false;
        public Integer iceCheckMinInterval = null;
        public boolean disableIPv6OnWifi = false;
        public int maxIPv6Networks = 5;
        public IntervalRange iceRegatherIntervalRange = null;

        public RTCConfiguration(List<IceServer> list) {
            this.iceServers = list;
        }
    }

    /* loaded from: classes5.dex */
    public enum RtcpMuxPolicy {
        NEGOTIATE,
        REQUIRE
    }

    /* loaded from: classes5.dex */
    public enum SignalingState {
        STABLE,
        HAVE_LOCAL_OFFER,
        HAVE_LOCAL_PRANSWER,
        HAVE_REMOTE_OFFER,
        HAVE_REMOTE_PRANSWER,
        CLOSED
    }

    /* loaded from: classes5.dex */
    public enum TcpCandidatePolicy {
        ENABLED,
        DISABLED
    }

    /* loaded from: classes5.dex */
    public enum TlsCertPolicy {
        TLS_CERT_POLICY_SECURE,
        TLS_CERT_POLICY_INSECURE_NO_CHECK
    }

    static {
        System.loadLibrary("jingle_peerconnection_so");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PeerConnection(long j, long j2) {
        this.nativePeerConnection = j;
        this.nativeObserver = j2;
    }

    private static native void freeObserver(long j);

    private native boolean nativeAddIceCandidate(String str, int i, String str2);

    private native boolean nativeAddLocalStream(long j);

    private native RtpSender nativeCreateSender(String str, String str2);

    private native List<RtpReceiver> nativeGetReceivers();

    private native List<RtpSender> nativeGetSenders();

    private native void nativeNewGetStats(RTCStatsCollectorCallback rTCStatsCollectorCallback);

    private native boolean nativeOldGetStats(StatsObserver statsObserver, long j);

    private native boolean nativeRemoveIceCandidates(IceCandidate[] iceCandidateArr);

    private native void nativeRemoveLocalStream(long j);

    private native boolean nativeStartRtcEventLog(int i, int i2);

    private native void nativeStopRtcEventLog();

    public boolean addIceCandidate(IceCandidate iceCandidate) {
        return nativeAddIceCandidate(iceCandidate.sdpMid, iceCandidate.sdpMLineIndex, iceCandidate.sdp);
    }

    public boolean addStream(MediaStream mediaStream) {
        if (!nativeAddLocalStream(mediaStream.nativeStream)) {
            return false;
        }
        this.localStreams.add(mediaStream);
        return true;
    }

    public native void close();

    public native void createAnswer(SdpObserver sdpObserver, MediaConstraints mediaConstraints);

    public native DataChannel createDataChannel(String str, DataChannel.Init init);

    public native void createOffer(SdpObserver sdpObserver, MediaConstraints mediaConstraints);

    public RtpSender createSender(String str, String str2) {
        RtpSender nativeCreateSender = nativeCreateSender(str, str2);
        if (nativeCreateSender != null) {
            this.senders.add(nativeCreateSender);
        }
        return nativeCreateSender;
    }

    public void dispose() {
        close();
        for (MediaStream mediaStream : this.localStreams) {
            nativeRemoveLocalStream(mediaStream.nativeStream);
            mediaStream.dispose();
        }
        this.localStreams.clear();
        for (RtpSender rtpSender : this.senders) {
            rtpSender.dispose();
        }
        this.senders.clear();
        for (RtpReceiver rtpReceiver : this.receivers) {
            rtpReceiver.dispose();
        }
        this.receivers.clear();
        JniCommon.nativeReleaseRef(this.nativePeerConnection);
        freeObserver(this.nativeObserver);
    }

    public native SessionDescription getLocalDescription();

    public List<RtpReceiver> getReceivers() {
        for (RtpReceiver rtpReceiver : this.receivers) {
            rtpReceiver.dispose();
        }
        this.receivers = nativeGetReceivers();
        return Collections.unmodifiableList(this.receivers);
    }

    public native SessionDescription getRemoteDescription();

    public List<RtpSender> getSenders() {
        for (RtpSender rtpSender : this.senders) {
            rtpSender.dispose();
        }
        this.senders = nativeGetSenders();
        return Collections.unmodifiableList(this.senders);
    }

    @Deprecated
    public boolean getStats(StatsObserver statsObserver, MediaStreamTrack mediaStreamTrack) {
        return nativeOldGetStats(statsObserver, mediaStreamTrack == null ? 0L : mediaStreamTrack.nativeTrack);
    }

    public native IceConnectionState iceConnectionState();

    public native IceGatheringState iceGatheringState();

    public native boolean nativeSetConfiguration(RTCConfiguration rTCConfiguration, long j);

    public boolean removeIceCandidates(IceCandidate[] iceCandidateArr) {
        return nativeRemoveIceCandidates(iceCandidateArr);
    }

    public void removeStream(MediaStream mediaStream) {
        nativeRemoveLocalStream(mediaStream.nativeStream);
        this.localStreams.remove(mediaStream);
    }

    public native boolean setBitrate(Integer num, Integer num2, Integer num3);

    public boolean setConfiguration(RTCConfiguration rTCConfiguration) {
        return nativeSetConfiguration(rTCConfiguration, this.nativeObserver);
    }

    public native void setLocalDescription(SdpObserver sdpObserver, SessionDescription sessionDescription);

    public native void setRemoteDescription(SdpObserver sdpObserver, SessionDescription sessionDescription);

    public native SignalingState signalingState();

    public boolean startRtcEventLog(int i, int i2) {
        return nativeStartRtcEventLog(i, i2);
    }

    public void stopRtcEventLog() {
        nativeStopRtcEventLog();
    }

    public void getStats(RTCStatsCollectorCallback rTCStatsCollectorCallback) {
        nativeNewGetStats(rTCStatsCollectorCallback);
    }
}
