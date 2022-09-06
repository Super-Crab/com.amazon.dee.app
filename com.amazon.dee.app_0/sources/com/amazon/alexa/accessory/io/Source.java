package com.amazon.alexa.accessory.io;

import java.io.Closeable;
import java.io.IOException;
/* loaded from: classes.dex */
public interface Source extends Closeable {
    int read(byte[] bArr, int i, int i2) throws IOException;
}
