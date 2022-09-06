package com.amazon.communication;

import amazon.communication.Message;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class ByteBufferChainMessageBuilder {
    private final ByteBufferChainMessageImpl mMessage;

    public ByteBufferChainMessageBuilder(ByteBuffer byteBuffer) {
        this.mMessage = new ByteBufferChainMessageImpl(byteBuffer);
    }

    public ByteBufferChainMessageBuilder append(ByteBuffer byteBuffer) {
        this.mMessage.appendPayload(byteBuffer);
        return this;
    }

    public ByteBufferChainMessageImpl getMessage() {
        return this.mMessage;
    }

    public ByteBufferChainMessageBuilder append(ByteBufferChain byteBufferChain) {
        for (ByteBuffer byteBuffer : byteBufferChain.getByteBuffers()) {
            this.mMessage.appendPayload(byteBuffer);
        }
        return this;
    }

    public ByteBufferChainMessageBuilder append(Message message) {
        this.mMessage.appendPayload(message);
        return this;
    }

    public ByteBufferChainMessageBuilder append(InputStream inputStream, int i) throws IOException {
        this.mMessage.appendPayload(inputStream, i);
        return this;
    }
}
