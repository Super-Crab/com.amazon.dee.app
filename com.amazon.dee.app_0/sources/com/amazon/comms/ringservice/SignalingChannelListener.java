package com.amazon.comms.ringservice;

import com.amazon.comms.ringservice.Signaling;
/* loaded from: classes12.dex */
public interface SignalingChannelListener {
    void onMessageError(Signaling.MessageError messageError, Signaling.MessageErrorInfo messageErrorInfo);

    void onRecvMessage(Signaling.Message message);
}
