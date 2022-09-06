package com.amazon.rtcsc.service.capabilityagentclient;

import com.amazon.rtcsc.interfaces.IRtcscEventListener;
import com.amazon.rtcsc.interfaces.RtcscAppInfo;
import com.amazon.rtcsc.interfaces.RtcscErrorCode;
import com.amazon.rtcsc.service.PayloadObject;
import com.amazon.rtcsc.service.appclient.RtcscAppClientCommon;
import com.amazon.rtcsc.utils.RtcscLogger;
import com.amazon.rtcsc.wrappers.RTCAppInfo;
import com.amazon.rtcsc.wrappers.RTCClientInterface;
import com.amazon.rtcsc.wrappers.RTCSCErrorCode;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
/* loaded from: classes13.dex */
public class RtcscClientJNIWrapper {
    private HashMap<String, RtcscEventListenerJNIImpl> mEventListenersMap = new HashMap<>();
    private RtcscLogger mLog = RtcscLogger.getLogger(RtcscClientJNIWrapper.class);
    private RTCClientInterface mRtcClient;
    private RtcscAppClientCommon mRtcscAppClientCommon;

    public RtcscClientJNIWrapper(RTCClientInterface rTCClientInterface, RtcscAppClientCommon rtcscAppClientCommon) {
        this.mRtcClient = null;
        if (rTCClientInterface == null) {
            this.mLog.e("RtcscClientJNIWrapper. null value passed for rtcClient");
            return;
        }
        this.mRtcClient = rTCClientInterface;
        this.mRtcscAppClientCommon = rtcscAppClientCommon;
    }

    public void handleDirective(String str, String str2, PayloadObject payloadObject) {
        RtcscEventListenerJNIImpl rtcscEventListenerJNIImpl;
        if (str != null && str2 != null) {
            RTCSCErrorCode handleDirective = this.mRtcClient.handleDirective(str, str2);
            RtcscLogger rtcscLogger = this.mLog;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("handleDirective returned from native layer. Error code is: ");
            outline107.append(handleDirective.toString());
            rtcscLogger.i(outline107.toString());
            if (handleDirective == RTCSCErrorCode.SUCCESS || (rtcscEventListenerJNIImpl = this.mEventListenersMap.get(payloadObject.getSessionDomain())) == null) {
                return;
            }
            rtcscEventListenerJNIImpl.onError(RtcscErrorCode.valueOf(handleDirective.toString()));
            return;
        }
        this.mLog.e("handleDirective. null parameters passed.");
        RtcscLogger rtcscLogger2 = this.mLog;
        rtcscLogger2.d("handleDirective. The value of directiveName : " + str + " directivePayload : " + str2);
    }

    public void registerEventListener(RtcscAppInfo rtcscAppInfo, IRtcscEventListener iRtcscEventListener) {
        if (rtcscAppInfo != null && iRtcscEventListener != null) {
            RtcscEventListenerJNIImpl rtcscEventListenerJNIImpl = new RtcscEventListenerJNIImpl(iRtcscEventListener, this.mRtcscAppClientCommon);
            this.mEventListenersMap.put(rtcscAppInfo.getAppIdentifier(), rtcscEventListenerJNIImpl);
            RTCAppInfo rTCAppInfo = new RTCAppInfo();
            rTCAppInfo.setAppIdentifier(rtcscAppInfo.getAppIdentifier());
            RTCSCErrorCode registerEventListener = this.mRtcClient.registerEventListener(rTCAppInfo, rtcscEventListenerJNIImpl);
            RtcscLogger rtcscLogger = this.mLog;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("registerEventListener returned from Native layer. Error code is: ");
            outline107.append(registerEventListener.toString());
            rtcscLogger.i(outline107.toString());
            if (registerEventListener == RTCSCErrorCode.SUCCESS) {
                return;
            }
            rtcscEventListenerJNIImpl.onError(RtcscErrorCode.valueOf(registerEventListener.toString()));
            return;
        }
        this.mLog.e("registerEventListener. null parameter passed");
    }

    public void unregisterEventListener(RtcscAppInfo rtcscAppInfo) {
        if (rtcscAppInfo == null) {
            this.mLog.e("unregisterEventListener. null appInfo  passed");
            return;
        }
        RtcscEventListenerJNIImpl remove = this.mEventListenersMap.remove(rtcscAppInfo.getAppIdentifier());
        if (remove == null) {
            this.mLog.e("unregisterEventListener. Cannot find listener for this appInfo. Cannot unregister.");
            return;
        }
        RTCAppInfo rTCAppInfo = new RTCAppInfo();
        rTCAppInfo.setAppIdentifier(rtcscAppInfo.getAppIdentifier());
        RTCSCErrorCode unregisterEventListener = this.mRtcClient.unregisterEventListener(rTCAppInfo);
        RtcscLogger rtcscLogger = this.mLog;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unregisterEventListener returned from native layer. Error code is: ");
        outline107.append(unregisterEventListener.toString());
        rtcscLogger.i(outline107.toString());
        if (unregisterEventListener == RTCSCErrorCode.SUCCESS) {
            return;
        }
        this.mLog.w("Even though unregisterRTCAppClientListener did not return SUCCESS, the local mapping for this appInfo has been erased");
        remove.onError(RtcscErrorCode.valueOf(unregisterEventListener.toString()));
    }
}
