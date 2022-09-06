package com.amazon.rtcsc.appclient;

import com.amazon.rtcsc.interfaces.IRtcscAppClientListener;
import com.amazon.rtcsc.interfaces.RtcscErrorCode;
import com.amazon.rtcsc.interfaces.RtcscMediaConnectionState;
import com.amazon.rtcsc.interfaces.RtcscMediaType;
import com.amazon.rtcsc.interfaces.RtcscSessionState;
import com.amazon.rtcsc.interfaces.RtcscSide;
import com.amazon.rtcsc.interfaces.RtcscVideoEffect;
import com.amazon.rtcsc.utils.RtcscLogger;
import java.util.Locale;
/* loaded from: classes13.dex */
public abstract class RtcscAppClientListener extends IRtcscAppClientListener.Stub {
    private RtcscLogger mLog = RtcscLogger.getLogger(RtcscAppClientListener.class);

    @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
    public int getAPIVersion() {
        return 4;
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
    public void onError(RtcscErrorCode rtcscErrorCode, String str) {
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("Received onError from RtcscService. errorCode: " + rtcscErrorCode + ", errorMessage: " + str);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
    public void onFirstFrameReceived(String str, RtcscMediaType rtcscMediaType) {
        this.mLog.i(String.format(Locale.US, "Received onFirstFrameReceived from RtcscService. sessionId: %s, mediaType: %s", str, rtcscMediaType));
    }

    public void onFirstFrameRendered(String str, RtcscSide rtcscSide) {
        this.mLog.i(String.format(Locale.US, "Received onFirstFrameRendered from RtcscService. sessionId: %s, side: %s", str, rtcscSide));
    }

    public void onMediaConnectionStateChanged(String str, RtcscMediaConnectionState rtcscMediaConnectionState) {
        this.mLog.i(String.format(Locale.US, "Received onMediaConnectionStateChanged from RtcscService. sessionId: %s, connectionState: %s", str, rtcscMediaConnectionState));
    }

    public void onMediaStatusChanged(String str, RtcscSide rtcscSide, RtcscMediaType rtcscMediaType, boolean z) {
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("Received onMediaStatusChanged from RtcscService. sessionId : " + str + " side: " + rtcscSide + " mediaType: " + rtcscMediaType + " enabled: " + z);
    }

    public void onSessionAvailable(String str) {
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("Received onSessionAvailable from RtcscService. sessionId: " + str);
    }

    public void onSessionError(String str, RtcscErrorCode rtcscErrorCode, String str2) {
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("Received onSessionError from RtcscService. sessionId : " + str + ", errorCode: " + rtcscErrorCode + ", errorMessage: " + str2);
    }

    public void onSessionRemoved(String str) {
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("Received onSessionRemoved from RtcscService. sessionId: " + str);
    }

    public void onSessionStateChanged(String str, RtcscSessionState rtcscSessionState) {
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("Received onSessionStateChanged from RtcscService. sessionId : " + str + " sessionState: " + rtcscSessionState);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
    public void onVideoEffectChanged(String str, RtcscVideoEffect rtcscVideoEffect, int i) {
        this.mLog.i(String.format(Locale.US, "Received onVideoEffectChanged from RtcscService. sessionId: %s, currentVideoEffect: %s, videoEffectDuration: %d", str, rtcscVideoEffect, Integer.valueOf(i)));
    }
}
