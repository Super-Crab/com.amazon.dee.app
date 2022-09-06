package com.amazon.alexa.accessory.streams.control;

import com.amazon.alexa.accessory.io.Sink;
import com.amazon.alexa.accessory.io.Source;
import com.amazon.alexa.accessory.protocol.Accessories;
import java.io.IOException;
/* loaded from: classes6.dex */
public interface ControlMessage {

    /* loaded from: classes6.dex */
    public interface Factory<T extends ControlMessage> {
        T create(Source source) throws IOException;
    }

    Accessories.Command getCommand();

    <T> T getPayload();

    boolean isResponse();

    void write(Sink sink) throws IOException;
}
