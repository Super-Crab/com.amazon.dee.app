package com.amazon.rtcsc.service.appclient;

import com.amazon.rtcsc.interfaces.IRtcscAppClientListener;
import com.amazon.rtcsc.interfaces.IRtcscCustomMetricsPublisherAdapter;
import com.amazon.rtcsc.interfaces.RtcscAppDisconnectCode;
import com.amazon.rtcsc.interfaces.RtcscAppInfo;
import com.amazon.rtcsc.interfaces.RtcscErrorCode;
import com.amazon.rtcsc.interfaces.RtcscVideoEffect;
import com.amazon.rtcsc.utils.RtcscLogger;
import com.amazon.rtcsc.wrappers.RTCAppClientManagerInterface;
import com.amazon.rtcsc.wrappers.RTCAppInfo;
import com.amazon.rtcsc.wrappers.RTCSCAppDisconnectCode;
import com.amazon.rtcsc.wrappers.RTCSCErrorCode;
import com.amazon.rtcsc.wrappers.RTCSCManagerInterface;
import com.amazon.rtcsc.wrappers.RTCVideoEffect;
import com.google.common.base.Strings;
/* loaded from: classes13.dex */
public class RtcscAppClientJNIWrapper {
    private RtcscAppClientListenerJNIImpl mAppClientListenerJni;
    private RtcscCustomMetricsPublisherAdapterJNIImpl mCustomMetricsPublisherAdapter = null;
    private RtcscLogger mLog = RtcscLogger.getLogger(RtcscAppClientJNIWrapper.class);
    private RTCAppClientManagerInterface mRtcAppClient;
    private RTCSCManagerInterface mRtcManager;
    private RtcscAppClientCommon mRtcscAppClientCommon;

    public RtcscAppClientJNIWrapper(RTCAppClientManagerInterface rTCAppClientManagerInterface, RTCSCManagerInterface rTCSCManagerInterface, RtcscAppClientCommon rtcscAppClientCommon) {
        this.mRtcAppClient = null;
        this.mRtcManager = null;
        this.mRtcscAppClientCommon = null;
        this.mAppClientListenerJni = null;
        this.mRtcAppClient = rTCAppClientManagerInterface;
        this.mRtcManager = rTCSCManagerInterface;
        this.mRtcscAppClientCommon = rtcscAppClientCommon;
        this.mAppClientListenerJni = new RtcscAppClientListenerJNIImpl(rtcscAppClientCommon);
    }

    public void acceptSession(String str) {
        if (Strings.isNullOrEmpty(str)) {
            this.mLog.e("acceptSession. null or empty value passed for sessionId");
            return;
        }
        RTCSCErrorCode acceptSession = this.mRtcAppClient.acceptSession(str);
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("acceptSession returned from Native layer with errorCode: " + acceptSession);
        if (acceptSession == RTCSCErrorCode.SUCCESS) {
            return;
        }
        if (this.mRtcscAppClientCommon.getListenerForSessionId(str) == null) {
            this.mLog.e("acceptSession. listener not found. Cannot send onError.");
        } else {
            this.mAppClientListenerJni.onSessionError(str, acceptSession, acceptSession.toString());
        }
    }

    public void disconnectSession(String str, RtcscAppDisconnectCode rtcscAppDisconnectCode) {
        if (Strings.isNullOrEmpty(str)) {
            this.mLog.e("disconnectSession. null or empty value passed for sessionId");
            return;
        }
        RTCSCErrorCode disconnectSession = this.mRtcAppClient.disconnectSession(str, RTCSCAppDisconnectCode.swigToEnum(rtcscAppDisconnectCode.ordinal()));
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("disconnectSession for sessionID " + str + "returned from Native layer with errorCode: " + disconnectSession);
        if (disconnectSession == RTCSCErrorCode.SUCCESS) {
            return;
        }
        if (this.mRtcscAppClientCommon.getListenerForSessionId(str) == null) {
            this.mLog.e("disconnectSession. listener not found. Cannot send onError.");
        } else {
            this.mAppClientListenerJni.onSessionError(str, disconnectSession, disconnectSession.toString());
        }
    }

    public void registerRtcscAppClientListener(RtcscAppInfo rtcscAppInfo, IRtcscAppClientListener iRtcscAppClientListener) {
        if (rtcscAppInfo != null && iRtcscAppClientListener != null) {
            String appIdentifier = rtcscAppInfo.getAppIdentifier();
            this.mRtcscAppClientCommon.putDomainToListenerMapping(appIdentifier, iRtcscAppClientListener);
            RTCAppInfo rTCAppInfo = new RTCAppInfo();
            rTCAppInfo.setAppIdentifier(appIdentifier);
            RTCSCErrorCode registerRTCAppClientListener = this.mRtcAppClient.registerRTCAppClientListener(rTCAppInfo, this.mAppClientListenerJni);
            RtcscLogger rtcscLogger = this.mLog;
            rtcscLogger.i("registerRtcscAppClientListener returned from Native layer with errorCode : " + registerRTCAppClientListener);
            if (registerRTCAppClientListener == RTCSCErrorCode.SUCCESS) {
                return;
            }
            this.mAppClientListenerJni.onError(appIdentifier, RtcscErrorCode.valueOf(registerRTCAppClientListener.toString()), "Failed to register RtcscAppClientListener");
            return;
        }
        RtcscLogger rtcscLogger2 = this.mLog;
        rtcscLogger2.e("registerRtcscAppClientListener. null parameters passed. Cannot send error callback.The value of appInfo : " + rtcscAppInfo + " listener : " + iRtcscAppClientListener);
    }

    public void registerRtcscCustomMetricsPublisherAdapter(RtcscAppInfo rtcscAppInfo, IRtcscCustomMetricsPublisherAdapter iRtcscCustomMetricsPublisherAdapter) {
        if (iRtcscCustomMetricsPublisherAdapter == null) {
            this.mLog.e("registerRtcscCustomMetricsPublisherAdapter. null adapter passed in. Cannot send error callback");
            return;
        }
        this.mCustomMetricsPublisherAdapter = new RtcscCustomMetricsPublisherAdapterJNIImpl(iRtcscCustomMetricsPublisherAdapter);
        RTCAppInfo rTCAppInfo = new RTCAppInfo();
        rTCAppInfo.setAppIdentifier(rtcscAppInfo.getAppIdentifier());
        RTCSCErrorCode registerRTCCustomMetricsPublisherAdapter = this.mRtcManager.registerRTCCustomMetricsPublisherAdapter(rTCAppInfo, this.mCustomMetricsPublisherAdapter);
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("registerRtcscCustomMetricsPublisherAdapter returned from Native layer with errorCode: " + registerRTCCustomMetricsPublisherAdapter);
        if (registerRTCCustomMetricsPublisherAdapter == RTCSCErrorCode.SUCCESS) {
            return;
        }
        this.mCustomMetricsPublisherAdapter.onError(RtcscErrorCode.valueOf(registerRTCCustomMetricsPublisherAdapter.toString()));
    }

    public void setLocalAudioState(String str, boolean z) {
        if (Strings.isNullOrEmpty(str)) {
            this.mLog.e("setLocalAudioState. null or empty value passed for sessionId");
            return;
        }
        RTCSCErrorCode localAudioState = this.mRtcAppClient.setLocalAudioState(str, z);
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("setLocalAudioState returned from Native layer with errorCode: " + localAudioState);
        if (localAudioState == RTCSCErrorCode.SUCCESS) {
            return;
        }
        if (this.mRtcscAppClientCommon.getListenerForSessionId(str) == null) {
            this.mLog.e("setLocalAudioState. listener not found. Cannot send onError.");
        } else {
            this.mAppClientListenerJni.onSessionError(str, localAudioState, localAudioState.toString());
        }
    }

    public void setLocalVideoState(String str, boolean z) {
        if (Strings.isNullOrEmpty(str)) {
            this.mLog.e("setLocalVideoState. null or empty value passed for sessionId");
            return;
        }
        RTCSCErrorCode localVideoState = this.mRtcAppClient.setLocalVideoState(str, z);
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("setLocalVideoState returned from Native layer with errorCode: " + localVideoState);
        if (localVideoState == RTCSCErrorCode.SUCCESS) {
            return;
        }
        if (this.mRtcscAppClientCommon.getListenerForSessionId(str) == null) {
            this.mLog.e("setLocalVideoState. listener not found. Cannot send onError.");
        } else {
            this.mAppClientListenerJni.onSessionError(str, localVideoState, localVideoState.toString());
        }
    }

    public void setRemoteAudioState(String str, boolean z) {
        if (Strings.isNullOrEmpty(str)) {
            this.mLog.e("setRemoteAudioState. null or empty value passed for sessionId");
            return;
        }
        RTCSCErrorCode remoteAudioState = this.mRtcAppClient.setRemoteAudioState(str, z);
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("setRemoteAudioState returned from Native layer with errorCode: " + remoteAudioState);
        if (remoteAudioState == RTCSCErrorCode.SUCCESS) {
            return;
        }
        if (this.mRtcscAppClientCommon.getListenerForSessionId(str) == null) {
            this.mLog.e("setRemoteAudioState. listener not found. Cannot send onError.");
        } else {
            this.mAppClientListenerJni.onSessionError(str, remoteAudioState, remoteAudioState.toString());
        }
    }

    public void setVideoEffect(String str, RtcscVideoEffect rtcscVideoEffect, int i) {
        if (Strings.isNullOrEmpty(str)) {
            this.mLog.e("setVideoEffect. null or empty value passed for sessionId");
            return;
        }
        RTCSCErrorCode videoEffect = this.mRtcAppClient.setVideoEffect(str, RTCVideoEffect.swigToEnum(rtcscVideoEffect.ordinal()), i);
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("setVideoEffect returned from Native layer with errorCode: " + videoEffect);
        if (videoEffect == RTCSCErrorCode.SUCCESS) {
            return;
        }
        if (this.mRtcscAppClientCommon.getListenerForSessionId(str) == null) {
            this.mLog.e("setVideoEffect. listener not found. Cannot send onError.");
        } else {
            this.mAppClientListenerJni.onSessionError(str, videoEffect, videoEffect.toString());
        }
    }

    public void signalReadyForSession(String str) {
        if (Strings.isNullOrEmpty(str)) {
            this.mLog.e("signalReadyForSession. null or empty value passed for sessionId");
            return;
        }
        RTCSCErrorCode signalReadyForSession = this.mRtcAppClient.signalReadyForSession(str);
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("signalReadyForSession returned from Native layer with errorCode: " + signalReadyForSession);
        if (signalReadyForSession == RTCSCErrorCode.SUCCESS) {
            return;
        }
        if (this.mRtcscAppClientCommon.getListenerForSessionId(str) == null) {
            this.mLog.e("signalReadyForSession. listener not found. Cannot send onError.");
        } else {
            this.mAppClientListenerJni.onSessionError(str, signalReadyForSession, signalReadyForSession.toString());
        }
    }

    public void switchCamera(String str, String str2) {
        if (!Strings.isNullOrEmpty(str) && !Strings.isNullOrEmpty(str2)) {
            RTCSCErrorCode switchCamera = this.mRtcAppClient.switchCamera(str, str2);
            RtcscLogger rtcscLogger = this.mLog;
            rtcscLogger.i("switchCamera returned from Native layer with errorCode: " + switchCamera);
            if (switchCamera == RTCSCErrorCode.SUCCESS) {
                return;
            }
            if (this.mRtcscAppClientCommon.getListenerForSessionId(str) == null) {
                this.mLog.e("switchCamera. listener not found. Cannot send onError.");
                return;
            } else {
                this.mAppClientListenerJni.onSessionError(str, switchCamera, switchCamera.toString());
                return;
            }
        }
        RtcscLogger rtcscLogger2 = this.mLog;
        rtcscLogger2.e("switchCamera. null parameters passed.The value of sessionId : " + str + " cameraName : " + str2);
    }

    public void unregisterRtcscAppClientListener(RtcscAppInfo rtcscAppInfo) {
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("unregisterRtcscAppClientListener called with appInfo: " + rtcscAppInfo);
        if (rtcscAppInfo == null) {
            this.mLog.e("unregisterRtcscAppClientListener. null AppInfo passed");
            return;
        }
        String appIdentifier = rtcscAppInfo.getAppIdentifier();
        if (!this.mRtcscAppClientCommon.isSessionDomainRegistered(appIdentifier)) {
            RtcscLogger rtcscLogger2 = this.mLog;
            rtcscLogger2.e("unregisterRtcscAppClientListener. Cannot find listener for " + appIdentifier + ". Cannot unregister.");
            return;
        }
        RTCAppInfo rTCAppInfo = new RTCAppInfo();
        rTCAppInfo.setAppIdentifier(appIdentifier);
        RTCSCErrorCode unregisterRTCAppClientListener = this.mRtcAppClient.unregisterRTCAppClientListener(rTCAppInfo);
        RtcscLogger rtcscLogger3 = this.mLog;
        rtcscLogger3.i("unregisterRtcscAppClientListener returned from Native layer with errorCode: " + unregisterRTCAppClientListener);
        if (unregisterRTCAppClientListener != RTCSCErrorCode.SUCCESS) {
            this.mLog.w("Even though unregisterRTCAppClientListener did not return SUCCESS, the local mapping for this appInfo has been erased");
            this.mAppClientListenerJni.onError(appIdentifier, RtcscErrorCode.valueOf(unregisterRTCAppClientListener.toString()), "Failed to unregister RtcscAppClientListener");
        }
        this.mRtcscAppClientCommon.removeDomainToListenerMapping(appIdentifier);
    }

    public void unregisterRtcscCustomMetricsPublisherAdapter(RtcscAppInfo rtcscAppInfo) {
        RTCAppInfo rTCAppInfo = new RTCAppInfo();
        rTCAppInfo.setAppIdentifier(rtcscAppInfo.getAppIdentifier());
        RTCSCErrorCode unregisterRTCCustomMetricsPublisherAdapter = this.mRtcManager.unregisterRTCCustomMetricsPublisherAdapter(rTCAppInfo);
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("unregisterRtcscCustomMetricsPublisherAdapter returned from Native layer with errorCode: " + unregisterRTCCustomMetricsPublisherAdapter);
        if (unregisterRTCCustomMetricsPublisherAdapter != RTCSCErrorCode.SUCCESS) {
            this.mCustomMetricsPublisherAdapter.onError(RtcscErrorCode.valueOf(unregisterRTCCustomMetricsPublisherAdapter.toString()));
        }
        this.mCustomMetricsPublisherAdapter = null;
    }
}
