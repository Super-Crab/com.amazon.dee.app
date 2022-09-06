package com.amazon.alexa.accessory.transport;

import com.amazon.alexa.accessory.io.SizedSource;
/* loaded from: classes6.dex */
public interface TransportReceiver {
    void onDataReceived(SizedSource sizedSource, int i) throws Exception;
}
