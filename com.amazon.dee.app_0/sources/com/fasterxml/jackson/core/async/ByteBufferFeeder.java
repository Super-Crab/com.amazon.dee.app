package com.fasterxml.jackson.core.async;

import java.io.IOException;
import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
public interface ByteBufferFeeder extends NonBlockingInputFeeder {
    void feedInput(ByteBuffer byteBuffer) throws IOException;
}
