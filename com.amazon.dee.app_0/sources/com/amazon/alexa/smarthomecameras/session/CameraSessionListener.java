package com.amazon.alexa.smarthomecameras.session;

import com.amazon.alexa.smarthomecameras.model.SessionId;
import com.amazon.rtcsc.interfaces.RtcscMediaType;
import com.amazon.rtcsc.interfaces.RtcscSide;
/* loaded from: classes10.dex */
public interface CameraSessionListener {
    void onError(String str, String str2);

    void onFirstFrameRendered(SessionId sessionId, VideoRendererSide videoRendererSide);

    void onMediaConnectionStateChanged(SessionId sessionId, MediaConnectionState mediaConnectionState);

    void onMediaStatusChanged(String str, RtcscSide rtcscSide, RtcscMediaType rtcscMediaType, boolean z);

    void onSessionConnected(SessionId sessionId);

    void onSessionDisconnected(SessionId sessionId);

    void onSessionStateChanged(SessionId sessionId, CameraSessionState cameraSessionState);
}
