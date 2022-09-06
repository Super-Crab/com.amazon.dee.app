package com.amazon.alexa.biloba.utils;

import java.io.Closeable;
import java.io.IOException;
/* loaded from: classes6.dex */
public interface Source extends Closeable {
    int read(byte[] bArr, int i, int i2) throws IOException;
}
