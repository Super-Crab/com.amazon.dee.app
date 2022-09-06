package com.amazon.communication;

import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.communication.websocket.CloseStatusCodes;
import com.amazon.communication.websocket.WebSocketClient;
import com.amazon.dp.logger.DPLogger;
import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NotYetConnectedException;
/* loaded from: classes12.dex */
public class WebSocketClientQueuedByteBufferChainHandler extends AbstractQueuedByteBufferChainHandler {
    private static final DPLogger log = new DPLogger("TComm.WebSocketClientQueuedByteBufferChainHandler");
    private final WebSocketClient mWebSocketClient;

    public WebSocketClientQueuedByteBufferChainHandler(WorkExecutor workExecutor, WebSocketClient webSocketClient, ProtocolSocket protocolSocket, int i) {
        super(workExecutor, protocolSocket, i);
        this.mWebSocketClient = webSocketClient;
    }

    @Override // com.amazon.communication.AbstractQueuedByteBufferChainHandler
    protected void retriedTooManyTimes() {
        log.info("retriedTooManyTimes", "a higher level component has retried too many times, will close the socket", new Object[0]);
        this.mProtocolSocket.close(new CloseDetail(CloseStatusCodes.TOO_MANY_WRITE_RETRIES, "Too many retries trying to write to the socket"));
    }

    @Override // com.amazon.communication.AbstractQueuedByteBufferChainHandler
    protected int sendByteBufferChain(ByteBufferChain byteBufferChain) throws ByteBufferChainConsumptionException {
        try {
            return this.mWebSocketClient.write(byteBufferChain);
        } catch (ClosedChannelException e) {
            log.info("sendByteBufferChain", "underlying WebSocket is closed; this exception means WebSocket is closed while the QueuedByteBufferChainHandler is trying  to send a message; no need to log an error", "mProtocolSocket", this.mProtocolSocket, e);
            throw new ByteBufferChainConsumptionException(e);
        } catch (IOException e2) {
            log.error("sendByteBufferChain", "IOException while sending data", "mProtocolSocket", this.mProtocolSocket, e2);
            this.mProtocolSocket.close(new CloseDetail(CloseStatusCodes.IO_ERROR, "IOException while sending data"));
            throw new ByteBufferChainConsumptionException(e2);
        } catch (NotYetConnectedException e3) {
            log.error("sendByteBufferChain", "underlying WebSocket is not yet upgraded", "mProtocolSocket", this.mProtocolSocket, e3);
            this.mProtocolSocket.close(new CloseDetail(CloseStatusCodes.NOT_YET_CONNECTED_ERROR, "WebSocket is not yet upgraded"));
            throw new ByteBufferChainConsumptionException(e3);
        } catch (Exception e4) {
            log.error("sendByteBufferChain", "exception while sending data", "mProtocolSocket", this.mProtocolSocket, e4);
            this.mProtocolSocket.close(new CloseDetail(4000, "Unknown exception sending data"));
            throw new ByteBufferChainConsumptionException(e4);
        }
    }
}
