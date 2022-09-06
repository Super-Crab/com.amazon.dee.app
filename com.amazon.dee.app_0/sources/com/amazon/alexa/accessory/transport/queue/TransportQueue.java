package com.amazon.alexa.accessory.transport.queue;

import com.amazon.alexa.accessory.transport.TransportData;
import java.io.IOException;
/* loaded from: classes6.dex */
public interface TransportQueue {
    void dispose();

    void executeNext() throws IOException;

    void prepare() throws IOException;

    TransportData read() throws IOException;
}
