package com.amazon.communication;

import java.io.InputStream;
/* loaded from: classes12.dex */
public class KnownSizeInputStreamMessage extends InputStreamMessageImpl {
    private final int mPayloadSize;

    public KnownSizeInputStreamMessage(InputStream inputStream, int i) {
        super(inputStream);
        this.mPayloadSize = i;
    }

    @Override // com.amazon.communication.InputStreamMessageImpl, amazon.communication.Message
    public int getPayloadSize() {
        return this.mPayloadSize;
    }
}
