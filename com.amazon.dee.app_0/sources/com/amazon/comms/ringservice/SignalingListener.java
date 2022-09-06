package com.amazon.comms.ringservice;

import com.amazon.comms.ringservice.Signaling;
/* loaded from: classes12.dex */
public interface SignalingListener {
    void onConnected(long j);

    void onConnectionError(int i, String str);

    void onNewChannel(Signaling.Channel channel, Signaling.Message message);

    void onNewChannelError(Signaling.MessageError messageError, Signaling.MessageErrorInfo messageErrorInfo);

    void onPublishError(int i, String str);
}
