package com.amazon.comms.ringservice;

import com.amazon.comms.calling.service.DataChannelEvent;
/* loaded from: classes12.dex */
public interface DataChannelListener {
    void onBufferedAmountChange(DataChannelEvent dataChannelEvent);

    void onMessageReceived(DataChannelEvent dataChannelEvent);

    void onStateChange(DataChannelEvent dataChannelEvent);
}
