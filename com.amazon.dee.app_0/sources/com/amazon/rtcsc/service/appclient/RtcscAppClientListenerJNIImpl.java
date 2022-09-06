package com.amazon.rtcsc.service.appclient;

import android.os.RemoteException;
import com.amazon.rtcsc.interfaces.IRtcscAppClientListener;
import com.amazon.rtcsc.interfaces.RtcscErrorCode;
import com.amazon.rtcsc.interfaces.RtcscMediaConnectionState;
import com.amazon.rtcsc.interfaces.RtcscMediaType;
import com.amazon.rtcsc.interfaces.RtcscSessionState;
import com.amazon.rtcsc.interfaces.RtcscSide;
import com.amazon.rtcsc.interfaces.RtcscVideoEffect;
import com.amazon.rtcsc.utils.RtcscLogger;
import com.amazon.rtcsc.wrappers.RTCAppClientListenerInterface;
import com.amazon.rtcsc.wrappers.RTCMediaConnectionState;
import com.amazon.rtcsc.wrappers.RTCMediaType;
import com.amazon.rtcsc.wrappers.RTCSCErrorCode;
import com.amazon.rtcsc.wrappers.RTCSessionState;
import com.amazon.rtcsc.wrappers.RTCSide;
import com.amazon.rtcsc.wrappers.RTCVideoEffect;
import com.google.common.base.Strings;
import java.util.Locale;
/* loaded from: classes13.dex */
public class RtcscAppClientListenerJNIImpl extends RTCAppClientListenerInterface {
    private static int CLIENT_VERSION_2 = 2;
    private static int CLIENT_VERSION_3 = 3;
    private static int CLIENT_VERSION_4 = 4;
    private RtcscLogger mLog = RtcscLogger.getLogger(RtcscAppClientListenerJNIImpl.class);
    private RtcscAppClientCommon mRtcscAppClientCommon;

    public RtcscAppClientListenerJNIImpl(RtcscAppClientCommon rtcscAppClientCommon) {
        this.mRtcscAppClientCommon = rtcscAppClientCommon;
    }

    public void onError(String str, RtcscErrorCode rtcscErrorCode, String str2) {
        IRtcscAppClientListener listenerForDomain = this.mRtcscAppClientCommon.getListenerForDomain(str);
        if (listenerForDomain == null) {
            this.mLog.e("onError. listener is null. Call cannot be forwarded to AppClient");
            return;
        }
        try {
            listenerForDomain.onError(rtcscErrorCode, str2);
        } catch (RemoteException unused) {
            this.mLog.w("onError callback could not be invoked.");
        }
    }

    @Override // com.amazon.rtcsc.wrappers.RTCAppClientListenerInterface
    public void onFirstFrameReceived(String str, RTCMediaType rTCMediaType) {
        if (Strings.isNullOrEmpty(str)) {
            this.mLog.e("onFirstFrameReceived. null or empty value passed for sessionId");
            return;
        }
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("onFirstFrameReceived called with sessionId: " + str);
        IRtcscAppClientListener listenerForSessionId = this.mRtcscAppClientCommon.getListenerForSessionId(str);
        if (listenerForSessionId == null) {
            this.mLog.e("onFirstFrameReceived. listener is null. Call cannot be forwarded to AppClient");
            return;
        }
        try {
            if (listenerForSessionId.getAPIVersion() >= CLIENT_VERSION_4) {
                listenerForSessionId.onFirstFrameReceived(str, RtcscMediaType.values()[rTCMediaType.swigValue()]);
            } else {
                this.mLog.w("onFirstFrameReceived: listener does not support callback yet. Not forwarding");
            }
        } catch (RemoteException unused) {
            this.mLog.w("onFirstFrameReceived callback could not be invoked.");
        }
    }

    @Override // com.amazon.rtcsc.wrappers.RTCAppClientListenerInterface
    public void onFirstFrameRendered(String str, RTCSide rTCSide) {
        if (Strings.isNullOrEmpty(str)) {
            this.mLog.e("onFirstFrameRendered. null or empty value passed for sessionId");
            return;
        }
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("onFirstFrameRendered called with sessionId: " + str);
        IRtcscAppClientListener listenerForSessionId = this.mRtcscAppClientCommon.getListenerForSessionId(str);
        if (listenerForSessionId == null) {
            this.mLog.e("onFirstFrameRendered. listener is null. Call cannot be forwarded to AppClient");
            return;
        }
        try {
            if (listenerForSessionId.getAPIVersion() >= CLIENT_VERSION_3) {
                listenerForSessionId.onFirstFrameRendered(str, RtcscSide.values()[rTCSide.swigValue()]);
            } else {
                this.mLog.w("onFirstFrameRendered: listener does not support callback yet. Not forwarding");
            }
        } catch (RemoteException unused) {
            this.mLog.w("onFirstFrameRendered callback could not be invoked.");
        }
    }

    @Override // com.amazon.rtcsc.wrappers.RTCAppClientListenerInterface
    public void onMediaConnectionStateChanged(String str, RTCMediaConnectionState rTCMediaConnectionState) {
        if (Strings.isNullOrEmpty(str)) {
            this.mLog.e("onMediaConnectionStateChanged. null or empty value passed for sessionId");
            return;
        }
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("onMediaConnectionStateChanged called with sessionId: " + str);
        IRtcscAppClientListener listenerForSessionId = this.mRtcscAppClientCommon.getListenerForSessionId(str);
        if (listenerForSessionId == null) {
            this.mLog.e("onMediaConnectionStateChanged. listener is null. Call cannot be forwarded to AppClient");
            return;
        }
        try {
            if (listenerForSessionId.getAPIVersion() >= CLIENT_VERSION_2) {
                listenerForSessionId.onMediaConnectionStateChanged(str, RtcscMediaConnectionState.values()[rTCMediaConnectionState.swigValue()]);
            } else {
                this.mLog.w("onMediaConnectionStateChanged: listener does not support callback yet. Not forwarding");
            }
        } catch (RemoteException unused) {
            this.mLog.w("onMediaConnectionStateChanged callback could not be invoked.");
        }
    }

    @Override // com.amazon.rtcsc.wrappers.RTCAppClientListenerInterface
    public void onMediaStatusChanged(String str, RTCAppClientListenerInterface.Side side, RTCAppClientListenerInterface.MediaType mediaType, boolean z) {
        if (Strings.isNullOrEmpty(str)) {
            this.mLog.e("onMediaStatusChanged. null or empty value passed for sessionId");
            return;
        }
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("onMediaStatusChanged called with sessionId: " + str + ", side:  mediaType: " + mediaType + " enabled: " + z);
        IRtcscAppClientListener listenerForSessionId = this.mRtcscAppClientCommon.getListenerForSessionId(str);
        if (listenerForSessionId == null) {
            this.mLog.e("onMediaStatusChanged. listener is null. Call cannot be forwarded to AppClient");
            return;
        }
        if (mediaType == RTCAppClientListenerInterface.MediaType.AUDIO && z) {
            this.mRtcscAppClientCommon.onAudioEnabledForSession();
        }
        try {
            listenerForSessionId.onMediaStatusChanged(str, RtcscSide.values()[side.swigValue()], RtcscMediaType.values()[mediaType.swigValue()], z);
        } catch (RemoteException unused) {
            this.mLog.w("onMediaStatusChanged callback could not be invoked.");
        }
    }

    @Override // com.amazon.rtcsc.wrappers.RTCAppClientListenerInterface
    public void onSessionAvailable(String str) {
        if (Strings.isNullOrEmpty(str)) {
            this.mLog.e("onSessionAvailable. null or empty value passed for sessionId");
            return;
        }
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("onSessionAvailable called with sessionId: " + str);
        IRtcscAppClientListener listenerForSessionId = this.mRtcscAppClientCommon.getListenerForSessionId(str);
        if (listenerForSessionId == null) {
            this.mLog.e("onSessionAvailable. listener is null. Call cannot be forwarded to AppClient");
            return;
        }
        try {
            listenerForSessionId.onSessionAvailable(str);
        } catch (RemoteException unused) {
            this.mLog.w("onSessionAvailable callback could not be invoked.");
        }
        this.mRtcscAppClientCommon.onSessionAvailable(str);
    }

    @Override // com.amazon.rtcsc.wrappers.RTCAppClientListenerInterface
    public void onSessionError(String str, RTCSCErrorCode rTCSCErrorCode, String str2) {
        if (Strings.isNullOrEmpty(str)) {
            this.mLog.e("onSessionError. null or empty value passed for sessionId");
            return;
        }
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("onSessionError called with sessionId: " + str + ", errorCode: " + rTCSCErrorCode + ", errorMessage: " + str2);
        IRtcscAppClientListener listenerForSessionId = this.mRtcscAppClientCommon.getListenerForSessionId(str);
        if (listenerForSessionId == null) {
            this.mLog.e("onSessionError. listener is null. Call cannot be forwarded to AppClient");
            return;
        }
        try {
            listenerForSessionId.onSessionError(str, RtcscErrorCode.values()[rTCSCErrorCode.swigValue()], str2);
        } catch (RemoteException unused) {
            this.mLog.w("onSessionError callback could not be invoked.");
        }
    }

    @Override // com.amazon.rtcsc.wrappers.RTCAppClientListenerInterface
    public void onSessionRemoved(String str) {
        if (Strings.isNullOrEmpty(str)) {
            this.mLog.e("onSessionRemoved. null or empty value passed for sessionId");
            return;
        }
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("onSessionRemoved called with sessionId: " + str);
        IRtcscAppClientListener listenerForSessionId = this.mRtcscAppClientCommon.getListenerForSessionId(str);
        if (listenerForSessionId == null) {
            this.mLog.e("onSessionRemoved. listener is null. Call cannot be forwarded to AppClient");
            return;
        }
        try {
            listenerForSessionId.onSessionRemoved(str);
        } catch (RemoteException unused) {
            this.mLog.w("onSessionRemoved callback could not be invoked.");
        }
        this.mRtcscAppClientCommon.onSessionRemoved(str);
    }

    @Override // com.amazon.rtcsc.wrappers.RTCAppClientListenerInterface
    public void onSessionStateChanged(String str, RTCSessionState rTCSessionState) {
        if (Strings.isNullOrEmpty(str)) {
            this.mLog.e("onSessionStateChanged. null or empty value passed for sessionId");
            return;
        }
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("onSessionStateChanged called with sessionId: " + str + ", sessionState: " + rTCSessionState);
        IRtcscAppClientListener listenerForSessionId = this.mRtcscAppClientCommon.getListenerForSessionId(str);
        if (listenerForSessionId == null) {
            this.mLog.e("onSessionStateChanged. listener is null. Call cannot be forwarded to AppClient");
            return;
        }
        try {
            listenerForSessionId.onSessionStateChanged(str, RtcscSessionState.values()[rTCSessionState.swigValue()]);
        } catch (RemoteException unused) {
            this.mLog.w("onSessionStateChanged callback could not be invoked.");
        }
    }

    @Override // com.amazon.rtcsc.wrappers.RTCAppClientListenerInterface
    public void onVideoEffectChanged(String str, RTCVideoEffect rTCVideoEffect, int i) {
        if (Strings.isNullOrEmpty(str)) {
            this.mLog.e("onVideoEffectChanged. null or empty value passed for sessionId");
            return;
        }
        this.mLog.i(String.format(Locale.US, "onVideoEffectChanged called with sessionId: %s, currentVideoEffect: %s, videoEffectDurationMs: %d", str, rTCVideoEffect, Integer.valueOf(i)));
        IRtcscAppClientListener listenerForSessionId = this.mRtcscAppClientCommon.getListenerForSessionId(str);
        if (listenerForSessionId == null) {
            this.mLog.e("onVideoEffectChanged. listener is null. Call cannot be forwarded to AppClient");
            return;
        }
        try {
            listenerForSessionId.onVideoEffectChanged(str, RtcscVideoEffect.values()[rTCVideoEffect.swigValue()], i);
        } catch (RemoteException unused) {
            this.mLog.w("onVideoEffectChanged callback could not be invoked.");
        }
    }
}
