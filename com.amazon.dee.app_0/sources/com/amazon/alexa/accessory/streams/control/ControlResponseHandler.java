package com.amazon.alexa.accessory.streams.control;

import com.amazon.alexa.accessory.protocol.Accessories;
/* loaded from: classes6.dex */
public interface ControlResponseHandler {
    void onResponseReceived(ControlStream controlStream, Accessories.Command command, Accessories.Response response) throws Exception;
}
