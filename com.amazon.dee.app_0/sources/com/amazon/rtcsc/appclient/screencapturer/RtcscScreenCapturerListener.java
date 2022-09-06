package com.amazon.rtcsc.appclient.screencapturer;

import com.amazon.rtcsc.interfaces.IRtcscScreenCapturerListener;
/* loaded from: classes13.dex */
public abstract class RtcscScreenCapturerListener extends IRtcscScreenCapturerListener.Stub {
    @Override // com.amazon.rtcsc.interfaces.IRtcscScreenCapturerListener
    public int getAPIVersion() {
        return 1;
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscScreenCapturerListener
    public void onPutScreenCapturerData() {
        throw new UnsupportedOperationException("onPutScreenCapturerData is not implemented in client application");
    }
}
