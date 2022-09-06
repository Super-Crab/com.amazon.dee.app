package com.amazon.comms.calling.service;
/* loaded from: classes11.dex */
public interface DataChannelEventListener {
    void onBufferedAmountChange(Call call, DataChannelEvent dataChannelEvent);

    void onMessageReceived(Call call, DataChannelEvent dataChannelEvent);

    void onStateChange(Call call, DataChannelEvent dataChannelEvent);
}
