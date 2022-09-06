package com.amazon.rtcsc.service.datachannel;

import com.amazon.rtcmedia.service.android.RTCDataChannelService;
import com.amazon.rtcmedia.service.android.RTCDataChannelServiceInterface;
import com.amazon.rtcsc.interfaces.IRtcscDataChannelListener;
import com.amazon.rtcsc.utils.RtcscLogger;
/* loaded from: classes13.dex */
public class RtcscDataChannelWrapper {
    private RtcscLogger mLog = RtcscLogger.getLogger(RtcscDataChannelWrapper.class);
    private RTCDataChannelServiceInterface mRtcDataChannelServiceInterface;

    private RtcscDataChannelWrapper() {
        this.mRtcDataChannelServiceInterface = null;
        this.mLog.i("Constructor --");
        this.mRtcDataChannelServiceInterface = RTCDataChannelService.getInstance();
    }

    public static RtcscDataChannelWrapper createRtcscDataChannelWrapper() {
        return new RtcscDataChannelWrapper();
    }

    public boolean registerDataChannelListener(String str, IRtcscDataChannelListener iRtcscDataChannelListener) {
        this.mLog.i(String.format("registerDataChannelListener. SessionId: %s, Listener: %s", str, iRtcscDataChannelListener));
        return this.mRtcDataChannelServiceInterface.registerDataChannelListener(str, new RtcscDataChannelListenerWrapper(iRtcscDataChannelListener));
    }

    public boolean sendData(String str, String str2, byte[] bArr, boolean z) {
        this.mLog.i(String.format("sendData. SessionId: %s, Label: %s, Data Length: %d, isBinary: %s", str, str2, Integer.valueOf(bArr.length), Boolean.valueOf(z)));
        return this.mRtcDataChannelServiceInterface.sendData(str, str2, bArr, z);
    }

    public boolean unregisterDataChannelListener(String str) {
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("unregisterDataChannelListener, sessionId = " + str);
        return this.mRtcDataChannelServiceInterface.unregisterDataChannelListener(str);
    }
}
