package com.amazon.communication;
/* loaded from: classes12.dex */
public interface ByteBufferChainHandlerNotificationSink {
    void chainHandled(ByteBufferChain byteBufferChain);

    void chainRejected(ByteBufferChain byteBufferChain, boolean z);

    void okToResubmitRejectedChain(ByteBufferChain byteBufferChain);
}
