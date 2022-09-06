package com.amazon.communication.socket;

import amazon.communication.CommunicationBaseException;
import com.amazon.communication.ByteBufferChainMessageImpl;
import com.amazon.communication.ThreadName;
import com.amazon.communication.TuningProtocolHandler;
import com.amazon.communication.WorkExecutor;
import com.amazon.communication.socket.DirectBiDiSocket;
import com.amazon.communication.socket.SelectionKeyChangeQueue;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.communication.websocket.CloseStatusCodes;
import com.amazon.communication.websocket.WebSocketClient;
import com.amazon.dp.logger.DPLogger;
import com.dp.utils.ThreadGuard;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NotYetConnectedException;
/* loaded from: classes12.dex */
public class HandleTuningCallable extends ProtocolSocketSingletonCallable {
    private static final DPLogger log = new DPLogger("TComm.HandleTuningCallable");
    private final SelectionKeyChangeQueue mChangeQueue;
    private final TuningProtocolHandler mTuningProtocolHandler;
    private final WebSocketClient mWebSocketClient;

    public HandleTuningCallable(DirectBiDiSocket directBiDiSocket, WorkExecutor workExecutor, WebSocketClient webSocketClient, TuningProtocolHandler tuningProtocolHandler, SelectionKeyChangeQueue selectionKeyChangeQueue) {
        super(directBiDiSocket, workExecutor);
        this.mWebSocketClient = webSocketClient;
        this.mTuningProtocolHandler = tuningProtocolHandler;
        this.mChangeQueue = selectionKeyChangeQueue;
    }

    @Override // com.amazon.communication.socket.ProtocolSocketSingletonCallable
    public void actualCall() throws Exception {
        ThreadGuard.ensureThreadPrefix(ThreadName.HANDLER);
        DirectBiDiSocket directBiDiSocket = (DirectBiDiSocket) this.mSocket;
        try {
            long dataBytesAvailable = this.mWebSocketClient.getDataBytesAvailable();
            DirectBiDiSocket.SocketConnectionState socketConnectionState = directBiDiSocket.getSocketConnectionState();
            if (dataBytesAvailable > 0) {
                ByteBuffer allocate = ByteBuffer.allocate((int) dataBytesAvailable);
                int read = this.mWebSocketClient.read(allocate);
                log.verbose("actualCall", "read completed", "numBytes", Long.valueOf(dataBytesAvailable), "numRead", Integer.valueOf(read), "directBiDiSocket", directBiDiSocket);
                if (read == dataBytesAvailable) {
                    allocate.rewind();
                    this.mTuningProtocolHandler.decodeMessage(directBiDiSocket.getEndpointIdentity(), new ByteBufferChainMessageImpl(allocate));
                    directBiDiSocket.setSocketConnectionState(DirectBiDiSocket.SocketConnectionState.TUNING_FINISHED);
                    log.debug("actualCall", "Tuning finished", new Object[0]);
                    directBiDiSocket.onOpened();
                }
                if (socketConnectionState != DirectBiDiSocket.SocketConnectionState.WEBSOCKET_CONNECTED && socketConnectionState != DirectBiDiSocket.SocketConnectionState.TUNING_FINISHED) {
                    return;
                }
                log.debug("actualCall", "registering for read", new Object[0]);
                this.mChangeQueue.queueChange(directBiDiSocket, SelectionKeyChangeQueue.SelectionKeyOperation.ADD, 1);
            } else if (socketConnectionState != DirectBiDiSocket.SocketConnectionState.WEBSOCKET_CONNECTED) {
            } else {
                log.info("actualCall", "didn't read complete websocket frame. Wait for next READ key and read more bytes later.", new Object[0]);
            }
        } catch (CommunicationBaseException e) {
            log.error("actualCall", "exception while tuning, closing", "directBiDiSocket", directBiDiSocket, e);
            directBiDiSocket.close(new CloseDetail(CloseStatusCodes.TUNING_FAILED, e.toString()));
        } catch (ClosedChannelException e2) {
            log.error("actualCall", "underlying WebSocket is closed", "directBiDiSocket", directBiDiSocket, e2);
            directBiDiSocket.close(new CloseDetail(CloseStatusCodes.TUNING_FAILED, e2.toString()));
        } catch (IOException e3) {
            log.error("actualCall", "IOException while tuning, closing", "directBiDiSocket", directBiDiSocket, e3);
            directBiDiSocket.close(new CloseDetail(CloseStatusCodes.IO_ERROR, e3.toString()));
        } catch (NotYetConnectedException e4) {
            log.error("actualCall", "underlying WebSocket is not yet upgraded, closing", "directBiDiSocket", directBiDiSocket, e4);
            directBiDiSocket.close(new CloseDetail(CloseStatusCodes.NOT_YET_CONNECTED_ERROR, e4.toString()));
        }
    }
}
