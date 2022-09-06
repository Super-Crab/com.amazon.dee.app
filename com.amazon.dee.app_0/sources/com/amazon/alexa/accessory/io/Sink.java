package com.amazon.alexa.accessory.io;

import java.io.Closeable;
import java.io.IOException;
/* loaded from: classes.dex */
public interface Sink extends Closeable {
    void flush() throws IOException;

    void write(byte[] bArr, int i, int i2) throws IOException;
}
