package org.webrtc;

import java.util.List;
import org.webrtc.PeerConnection;
/* loaded from: classes5.dex */
public class PeerConnectionShim extends PeerConnection {
    private static final String TAG = "PeerConnectionShim";

    /* loaded from: classes5.dex */
    public static abstract class Observer implements PeerConnection.Observer {
        @Override // org.webrtc.PeerConnection.Observer
        public void onAddTrack(RtpReceiver rtpReceiver, MediaStream[] mediaStreamArr) {
        }

        public abstract void onEncoderOutputResolutionChanged(int i, int i2);

        @Deprecated
        public void onUpdatedStream(MediaStream mediaStream) {
        }
    }

    /* loaded from: classes5.dex */
    public static class RTCConfiguration extends PeerConnection.RTCConfiguration {
        public RTCConfiguration(List<PeerConnection.IceServer> list) {
            super(list);
        }

        public void setDisableTrickleIce(boolean z) {
            String str = "setDisableTrickleIce = " + z + " ignored. Parameter not supported in this version of webrtc";
        }
    }

    PeerConnectionShim(long j, long j2) {
        super(j, j2);
    }

    public static void setAudioTrackVolume(AudioTrack audioTrack, float f) {
        String str = "setVolume volume level = " + f;
        audioTrack.setVolume(f);
    }

    public static void setConfiguration(PeerConnection peerConnection, PeerConnection.RTCConfiguration rTCConfiguration) {
        if (peerConnection != null) {
            peerConnection.setConfiguration(rTCConfiguration);
        }
    }

    public static boolean shouldProcessRemoteMediaStreamOnRemoteSdp() {
        return true;
    }
}
