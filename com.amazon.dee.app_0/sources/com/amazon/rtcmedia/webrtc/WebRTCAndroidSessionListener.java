package com.amazon.rtcmedia.webrtc;

import com.amazon.rtcmedia.util.ViewDirection;
import com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface;
import org.webrtc.IceCandidate;
import org.webrtc.StatsReport;
/* loaded from: classes13.dex */
public class WebRTCAndroidSessionListener implements WebRTCAndroidSessionInterface.PeerClientListener {
    private long nativeListener;

    public WebRTCAndroidSessionListener(long j) {
        this.nativeListener = j;
    }

    private native void onCameraFailureNative(String str, String str2, long j);

    private native void onCameraOpeningNative(String str, boolean z, long j);

    private native void onFirstFrameRenderedNative(String str, boolean z, long j);

    private native void onIceCandidateNative(String str, IceCandidate iceCandidate, long j);

    private native void onIceCandidatesRemovedNative(String str, IceCandidate[] iceCandidateArr, long j);

    private native void onIceConnectedNative(String str, long j);

    private native void onIceDisconnectedNative(String str, long j);

    private native void onIceFailedNative(String str, long j);

    private native void onIceGatheringDoneNative(String str, String str2, boolean z, long j);

    private native void onLocalDescriptionCreatedNative(String str, String str2, boolean z, long j);

    private native void onLocalDescriptionSetNative(String str, String str2, boolean z, long j);

    private native void onPeerConnectionClosedNative(String str, long j);

    private native void onPeerConnectionErrorNative(String str, String str2, long j);

    private native void onPeerConnectionStatsReadyNative(String str, StatsReport[] statsReportArr, long j);

    private native void onRemoteDescriptionSetNative(String str, String str2, boolean z, long j);

    private native void onSignalingDoneNative(String str, long j);

    private native void onVideoEffectAbortNative(String str, long j);

    private native void onVideoEffectTransitionNative(String str, String str2, long j);

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface.PeerClientListener
    public void onCameraFailure(String str, String str2) {
        onCameraFailureNative(str, str2, this.nativeListener);
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface.PeerClientListener
    public void onCameraOpening(String str, boolean z) {
        onCameraOpeningNative(str, z, this.nativeListener);
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface.PeerClientListener
    public void onFirstFrameRendered(String str, ViewDirection viewDirection) {
        onFirstFrameRenderedNative(str, viewDirection == ViewDirection.LOCAL_VIEW, this.nativeListener);
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface.PeerClientListener
    public void onIceCandidate(String str, IceCandidate iceCandidate) {
        onIceCandidateNative(str, iceCandidate, this.nativeListener);
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface.PeerClientListener
    public void onIceCandidatesRemoved(String str, IceCandidate[] iceCandidateArr) {
        onIceCandidatesRemovedNative(str, iceCandidateArr, this.nativeListener);
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface.PeerClientListener
    public void onIceConnected(String str) {
        onIceConnectedNative(str, this.nativeListener);
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface.PeerClientListener
    public void onIceDisconnected(String str) {
        onIceDisconnectedNative(str, this.nativeListener);
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface.PeerClientListener
    public void onIceFailed(String str) {
        onIceFailedNative(str, this.nativeListener);
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface.PeerClientListener
    public void onIceGatheringDone(String str, String str2, boolean z) {
        onIceGatheringDoneNative(str, str2, z, this.nativeListener);
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface.PeerClientListener
    public void onLocalDescriptionCreated(String str, String str2, boolean z) {
        onLocalDescriptionCreatedNative(str, str2, z, this.nativeListener);
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface.PeerClientListener
    public void onLocalDescriptionSet(String str, String str2, boolean z) {
        onLocalDescriptionSetNative(str, str2, z, this.nativeListener);
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface.PeerClientListener
    public void onPeerConnectionClosed(String str) {
        onPeerConnectionClosedNative(str, this.nativeListener);
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface.PeerClientListener
    public void onPeerConnectionError(String str, String str2) {
        onPeerConnectionErrorNative(str, str2, this.nativeListener);
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface.PeerClientListener
    public void onPeerConnectionStatsReady(String str, StatsReport[] statsReportArr) {
        onPeerConnectionStatsReadyNative(str, statsReportArr, this.nativeListener);
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface.PeerClientListener
    public void onRemoteDescriptionSet(String str, String str2, boolean z) {
        onRemoteDescriptionSetNative(str, str2, z, this.nativeListener);
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface.PeerClientListener
    public void onSignalingDone(String str) {
        onSignalingDoneNative(str, this.nativeListener);
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface.PeerClientListener
    public void onVideoEffectAbort(String str) {
        onVideoEffectAbortNative(str, this.nativeListener);
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionInterface.PeerClientListener
    public void onVideoEffectTransition(String str, String str2) {
        onVideoEffectTransitionNative(str, str2, this.nativeListener);
    }
}
