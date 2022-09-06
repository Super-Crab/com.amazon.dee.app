package com.amazon.communication;

import com.amazon.communication.websocket.WebSocketClient;
import java.io.IOException;
/* loaded from: classes12.dex */
public class WebSocketClientByteBufferChainHandler implements ByteBufferChainHandler {
    private final WebSocketClient mWebSocketClient;

    public WebSocketClientByteBufferChainHandler(WebSocketClient webSocketClient) {
        this.mWebSocketClient = webSocketClient;
    }

    @Override // com.amazon.communication.ByteBufferChainHandler
    public synchronized void onByteBufferChain(ByteBufferChain byteBufferChain, ByteBufferChainHandlerNotificationSink byteBufferChainHandlerNotificationSink) throws IOException {
        int dataSize = byteBufferChain.getDataSize();
        while (dataSize > 0) {
            int write = this.mWebSocketClient.write(byteBufferChain);
            dataSize -= write;
            if (write == 0) {
                byteBufferChainHandlerNotificationSink.chainRejected(byteBufferChain, true);
                return;
            }
        }
        byteBufferChainHandlerNotificationSink.chainHandled(byteBufferChain);
    }
}
