package com.amazon.alexa.accessory.transport.operations;

import com.amazon.alexa.accessory.transport.TransportPriority;
import java.io.IOException;
/* loaded from: classes6.dex */
public interface TransportOperation {
    void execute() throws IOException;

    String getKey();

    TransportPriority getPriority();
}
