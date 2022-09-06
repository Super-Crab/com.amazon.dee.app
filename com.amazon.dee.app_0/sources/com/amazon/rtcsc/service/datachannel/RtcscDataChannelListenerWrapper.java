package com.amazon.rtcsc.service.datachannel;

import android.os.RemoteException;
import com.amazon.rtcmedia.service.android.RTCDataChannelServiceInterface;
import com.amazon.rtcsc.interfaces.IRtcscDataChannelListener;
import com.amazon.rtcsc.interfaces.RtcscDataChannelState;
import com.amazon.rtcsc.utils.RtcscLogger;
import java.util.Locale;
/* loaded from: classes13.dex */
class RtcscDataChannelListenerWrapper implements RTCDataChannelServiceInterface.RTCDataChannelListenerInterface {
    private IRtcscDataChannelListener mListener;
    private RtcscLogger mLog = RtcscLogger.getLogger(RtcscDataChannelListenerWrapper.class);

    public RtcscDataChannelListenerWrapper(IRtcscDataChannelListener iRtcscDataChannelListener) {
        this.mLog.i("RtcscDataChannelListenerWrapper constructor called");
        this.mListener = iRtcscDataChannelListener;
    }

    @Override // com.amazon.rtcmedia.service.android.RTCDataChannelServiceInterface.RTCDataChannelListenerInterface
    public void onMessageReceived(String str, String str2, byte[] bArr, boolean z) {
        this.mLog.i(String.format(Locale.US, "RtcscDataChannelListenerWrapper onMessageReceived called. Passing to AppClientListener. SessionId: %s, Label: %s", str, str2));
        this.mLog.d(String.format(Locale.US, "Data Length: %d, isBinary: %s", Integer.valueOf(bArr == null ? 0 : bArr.length), Boolean.valueOf(z)));
        try {
            this.mListener.onMessageReceived(str, str2, bArr, z);
        } catch (RemoteException e) {
            this.mLog.e("Exception while passing onMessageReceived call to AppClient's Listener: ", e);
        }
    }

    @Override // com.amazon.rtcmedia.service.android.RTCDataChannelServiceInterface.RTCDataChannelListenerInterface
    public void onStateChange(String str, String str2, RTCDataChannelServiceInterface.RTCDataChannelListenerInterface.State state) {
        this.mLog.i(String.format(Locale.US, "RtcscDataChannelListenerWrapper onStateChange called. Passing to AppClientListener. SessionId: %s, Label: %s, State: %s", str, str2, state.name()));
        try {
            this.mListener.onStateChange(str, str2, RtcscDataChannelState.valueOf(state.toString()));
        } catch (RemoteException e) {
            this.mLog.e("Exception while passing onStateChange call to AppClient's Listener: ", e);
        }
    }
}
