package com.amazon.rtcsc.appclient.datachannel;

import com.amazon.rtcsc.interfaces.IRtcscDataChannelListener;
import com.amazon.rtcsc.interfaces.RtcscDataChannelState;
/* loaded from: classes13.dex */
public abstract class RtcscDataChannelListener extends IRtcscDataChannelListener.Stub {
    @Override // com.amazon.rtcsc.interfaces.IRtcscDataChannelListener
    public int getAPIVersion() {
        return 1;
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscDataChannelListener
    public void onMessageReceived(String str, String str2, byte[] bArr, boolean z) {
        throw new UnsupportedOperationException("onMessageReceived is not implemented.");
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscDataChannelListener
    public void onStateChange(String str, String str2, RtcscDataChannelState rtcscDataChannelState) {
        throw new UnsupportedOperationException("onStateChange is not implemented.");
    }
}
