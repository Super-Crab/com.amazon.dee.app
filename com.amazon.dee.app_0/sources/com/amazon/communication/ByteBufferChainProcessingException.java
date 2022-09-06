package com.amazon.communication;

import java.io.IOException;
/* loaded from: classes12.dex */
public class ByteBufferChainProcessingException extends IOException {
    public ByteBufferChainProcessingException(Throwable th) {
        super(th);
    }

    public ByteBufferChainProcessingException(String str, Throwable th) {
        super(str, th);
    }

    public ByteBufferChainProcessingException(String str) {
        super(str);
    }
}
