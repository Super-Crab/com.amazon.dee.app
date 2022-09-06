package com.amazon.communication;

import amazon.communication.Message;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public interface ByteBufferBackedMessage {
    int appendPayload(InputStream inputStream, int i) throws IOException;

    void appendPayload(Message message);

    ByteBufferChain getByteBufferChain();

    ByteBuffer[] getByteBuffers();

    InputStream getInputStream();

    OutputStream getOutputStream();
}
