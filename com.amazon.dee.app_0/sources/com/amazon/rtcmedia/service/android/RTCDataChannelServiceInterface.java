package com.amazon.rtcmedia.service.android;
/* loaded from: classes13.dex */
public interface RTCDataChannelServiceInterface {

    /* loaded from: classes13.dex */
    public interface RTCDataChannelListenerInterface {

        /* loaded from: classes13.dex */
        public enum State {
            CONNECTING,
            OPEN,
            CLOSING,
            CLOSED
        }

        void onMessageReceived(String str, String str2, byte[] bArr, boolean z);

        void onStateChange(String str, String str2, State state);
    }

    boolean registerDataChannelListener(String str, RTCDataChannelListenerInterface rTCDataChannelListenerInterface);

    boolean sendData(String str, String str2, byte[] bArr, boolean z);

    boolean unregisterDataChannelListener(String str);
}
