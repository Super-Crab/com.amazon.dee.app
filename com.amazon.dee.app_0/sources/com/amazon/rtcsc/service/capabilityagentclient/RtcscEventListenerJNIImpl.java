package com.amazon.rtcsc.service.capabilityagentclient;

import android.os.RemoteException;
import com.amazon.rtcsc.interfaces.IRtcscEventListener;
import com.amazon.rtcsc.interfaces.RtcscErrorCode;
import com.amazon.rtcsc.service.appclient.RtcscAppClientCommon;
import com.amazon.rtcsc.utils.RtcscLogger;
import com.amazon.rtcsc.wrappers.RTCEventListenerInterface;
import java.util.Locale;
/* loaded from: classes13.dex */
public class RtcscEventListenerJNIImpl extends RTCEventListenerInterface {
    private IRtcscEventListener mEventListener;
    private RtcscLogger mLog = RtcscLogger.getLogger(RtcscEventListenerJNIImpl.class);
    private RtcscAppClientCommon mRtcscAppClientCommon;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RtcscEventListenerJNIImpl(IRtcscEventListener iRtcscEventListener, RtcscAppClientCommon rtcscAppClientCommon) {
        this.mEventListener = null;
        this.mEventListener = iRtcscEventListener;
        this.mRtcscAppClientCommon = rtcscAppClientCommon;
    }

    public void onError(RtcscErrorCode rtcscErrorCode) {
        try {
            this.mEventListener.onError(rtcscErrorCode);
        } catch (RemoteException unused) {
            this.mLog.w("onError callback could not be invoked.");
        }
    }

    @Override // com.amazon.rtcsc.wrappers.RTCEventListenerInterface
    public void onRTCSessionContextUpdated(String str) {
        if (str == null) {
            this.mLog.e("onRTCSessionContextUpdated. null value passed for rtcSessionContext");
            return;
        }
        this.mLog.i("Updated Session Context Received from Native Layer ");
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("SessionContext : " + str);
        try {
            this.mEventListener.onRTCSessionContextUpdated(str);
        } catch (RemoteException unused) {
            this.mLog.w("onRTCSessionContextUpdated callback could not be invoked.");
        }
    }

    @Override // com.amazon.rtcsc.wrappers.RTCEventListenerInterface
    public void onSendEvent(String str, String str2, String str3, String str4) {
        this.mLog.i(String.format(Locale.US, "onSendEvent. Received event from Native Layer. sessionId: %s. eventName: %s, sessionContext: %s", str, str2, str4));
        this.mLog.d(String.format(Locale.US, "Payload: %s", str3));
        if (str != null && str2 != null && str3 != null && str4 != null) {
            try {
                this.mEventListener.onSendEvent(str, str2, str3, str4);
                return;
            } catch (RemoteException unused) {
                this.mLog.w("onSendEvent callback could not be invoked.");
                return;
            }
        }
        this.mLog.e("onSendEvent. null parameter passed");
    }
}
