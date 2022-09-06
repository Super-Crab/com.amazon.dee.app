package com.amazon.alexa.accessory;

import com.amazon.alexa.accessory.io.Sink;
import com.amazon.alexa.accessory.io.Source;
import java.io.Closeable;
import java.io.IOException;
/* loaded from: classes.dex */
public interface AccessoryTransport extends Closeable {

    /* loaded from: classes.dex */
    public interface Factory {
        AccessoryTransport createTransport(Accessory accessory, AccessorySessionOptions accessorySessionOptions);
    }

    /* loaded from: classes.dex */
    public enum Type {
        GATT,
        RFCOMM
    }

    Accessory getAccessory();

    Sink sink() throws IOException;

    Source source() throws IOException;
}
