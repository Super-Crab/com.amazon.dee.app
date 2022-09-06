package com.amazon.rtcsc.service.screencapturer;

import android.os.RemoteException;
import com.amazon.rtcmedia.service.android.RTCScreenCapturerServiceInterface;
import com.amazon.rtcsc.interfaces.IRtcscScreenCapturerListener;
import com.amazon.rtcsc.utils.RtcscLogger;
/* loaded from: classes13.dex */
public class RtcscScreenCapturerListenerWrapper implements RTCScreenCapturerServiceInterface.RTCScreenCapturerServiceListener {
    IRtcscScreenCapturerListener mListener;
    private RtcscLogger mLog = RtcscLogger.getLogger(RtcscScreenCapturerListenerWrapper.class);

    public RtcscScreenCapturerListenerWrapper(IRtcscScreenCapturerListener iRtcscScreenCapturerListener) {
        this.mListener = iRtcscScreenCapturerListener;
    }

    @Override // com.amazon.rtcmedia.service.android.RTCScreenCapturerServiceInterface.RTCScreenCapturerServiceListener
    public void onPutScreenCapturerData() {
        try {
            this.mListener.onPutScreenCapturerData();
        } catch (RemoteException unused) {
            this.mLog.w("onPutScreenCapturerData callback could not be invoked.");
        }
    }
}
