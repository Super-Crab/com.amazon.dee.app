package com.amazon.rtcsc.capabilityagentclient;

import com.amazon.rtcsc.interfaces.IRtcscEventListener;
import com.amazon.rtcsc.interfaces.RtcscErrorCode;
import com.amazon.rtcsc.utils.RtcscLogger;
/* loaded from: classes13.dex */
public abstract class RtcscCAEventListener extends IRtcscEventListener.Stub {
    private RtcscLogger mLog = RtcscLogger.getLogger(RtcscCAEventListener.class);

    @Override // com.amazon.rtcsc.interfaces.IRtcscEventListener
    public int getAPIVersion() {
        return 1;
    }

    public void onError(RtcscErrorCode rtcscErrorCode) {
        this.mLog.i("Received onError.");
    }

    public void onRTCSessionContextUpdated(String str) {
        this.mLog.i("Received onRTCSessionContextUpdated from RtcscService.");
    }

    public void onSendEvent(String str, String str2, String str3, String str4) {
        this.mLog.i("Received onSendEvent from RtcscService.");
    }
}
