package com.amazon.alexa.accessory.streams.control;

import com.amazon.alexa.accessory.protocol.Accessories;
/* loaded from: classes6.dex */
public interface ControlMessageHandler<T> {
    void onMessageReceived(ControlStream controlStream, Accessories.Command command, T t) throws Exception;
}
