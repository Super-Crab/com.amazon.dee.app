package com.amazon.deecomms.calling.datachannel;
/* loaded from: classes12.dex */
public interface AmcIncallDataChannelApplication {
    String getApplicationName();

    void onDataChannelEventReceived(CommsDataChannelEvent commsDataChannelEvent);
}
