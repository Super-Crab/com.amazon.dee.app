package com.amazon.communication.socket;

import amazon.communication.CommunicationBaseException;
import android.support.v4.media.session.PlaybackStateCompat;
import com.amazon.communication.ByteBufferChainMessageImpl;
import com.amazon.communication.PowerManagerWrapper;
import com.amazon.communication.ProtocolHandler;
import com.amazon.communication.ThreadName;
import com.amazon.communication.WorkExecutor;
import com.amazon.communication.socket.DirectBiDiSocket;
import com.amazon.communication.socket.SelectionKeyChangeQueue;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.communication.websocket.CloseStatusCodes;
import com.amazon.communication.websocket.WebSocketClient;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dp.utils.FailFast;
import com.dp.utils.ThreadGuard;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NotYetConnectedException;
/* loaded from: classes12.dex */
public class HandleDataCallable extends ProtocolSocketSingletonCallable {
    protected static final int DEFAULT_BUFFER_SIZE = 262144;
    private static final DPLogger log = new DPLogger("TComm.HandleDataCallable");
    private final SelectionKeyChangeQueue mChangeQueue;
    private final ProtocolHandler mProtocolHandler;
    private final ByteBuffer mReadBuffer;
    private final WebSocketClient mWebSocketClient;

    public HandleDataCallable(DirectBiDiSocket directBiDiSocket, WorkExecutor workExecutor, WebSocketClient webSocketClient, ProtocolHandler protocolHandler, SelectionKeyChangeQueue selectionKeyChangeQueue, PowerManagerWrapper powerManagerWrapper) {
        super(directBiDiSocket, workExecutor, powerManagerWrapper);
        this.mWebSocketClient = webSocketClient;
        this.mProtocolHandler = protocolHandler;
        this.mChangeQueue = selectionKeyChangeQueue;
        this.mReadBuffer = ByteBuffer.allocate(262144);
    }

    private ByteBuffer copyByteBuffer(ByteBuffer byteBuffer) {
        byteBuffer.rewind();
        byte[] bArr = new byte[byteBuffer.limit()];
        byteBuffer.get(bArr);
        byteBuffer.rewind();
        return ByteBuffer.wrap(bArr);
    }

    @Override // com.amazon.communication.socket.ProtocolSocketSingletonCallable
    public void actualCall() throws Exception {
        ThreadGuard.ensureThreadPrefix(ThreadName.HANDLER);
        DirectBiDiSocket directBiDiSocket = (DirectBiDiSocket) this.mSocket;
        log.verbose("actualCall", "about to read available data from socket channel", "directBiDiSocket", directBiDiSocket);
        while (true) {
            try {
                long dataBytesAvailable = this.mWebSocketClient.getDataBytesAvailable();
                if (dataBytesAvailable == 0) {
                    break;
                }
                if (dataBytesAvailable > PlaybackStateCompat.ACTION_SET_REPEAT_MODE) {
                    log.error("actualCall", "web socket received a too long message, cannot handle it", "longExpectedBytes", Long.valueOf(dataBytesAvailable));
                    directBiDiSocket.close(new CloseDetail(CloseStatusCodes.CLIENT_ERROR, "Received a message of size: " + dataBytesAvailable + " longer than expected: 262144"));
                }
                int i = (int) dataBytesAvailable;
                this.mReadBuffer.limit(this.mReadBuffer.position() + i);
                int read = directBiDiSocket.read(this.mReadBuffer);
                log.verbose("actualCall", "socket read completed", "expectedBytes", Integer.valueOf(i), "numRead", Integer.valueOf(read));
                if (read == -1) {
                    log.verbose("actualCall", "reached end of stream", new Object[0]);
                    break;
                } else if (read == 0) {
                    log.verbose("actualCall", "0 bytes read, breaking and waiting for next selector", new Object[0]);
                    break;
                } else if (read == i) {
                    log.verbose("actualCall", "got a complete message for decoding", new Object[0]);
                    this.mProtocolHandler.decodeMessage(directBiDiSocket.getEndpointIdentity(), new ByteBufferChainMessageImpl(copyByteBuffer(this.mReadBuffer)));
                    directBiDiSocket.messageReceived();
                } else if (read < i) {
                    log.verbose("actualCall", "got incomplete message - continue reading", new Object[0]);
                } else {
                    FailFast.expectTrue(false, "Read more than a complete message. Expected: " + i + " bytes, read: " + read + " bytes.");
                }
            } catch (CommunicationBaseException e) {
                log.warn("actualCall", "exception while reading data; closing.", "directBiDiSocket", directBiDiSocket, e);
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown exception while reading data: ");
                outline107.append(e.getMessage());
                directBiDiSocket.close(new CloseDetail(4000, outline107.toString()));
                return;
            } catch (ClosedChannelException e2) {
                log.warn("actualCall", "underlying WebSocket is already closed.", "directBiDiSocket", directBiDiSocket, e2);
                return;
            } catch (IOException e3) {
                log.warn("actualcall", "exception while reading data; closing.", "directBiDiSocket", directBiDiSocket, e3);
                directBiDiSocket.close(new CloseDetail(CloseStatusCodes.IO_ERROR, GeneratedOutlineSupport1.outline37(e3, GeneratedOutlineSupport1.outline107("IOException reading from socket: "))));
                return;
            } catch (NotYetConnectedException e4) {
                log.warn("actualCall", "underlying WebSocket is not yet connected", "directBiDiSocket", directBiDiSocket, e4);
                return;
            }
        }
        if (directBiDiSocket.getSocketConnectionState() == DirectBiDiSocket.SocketConnectionState.TUNING_FINISHED) {
            log.debug("actualCall", "registering for read", new Object[0]);
            this.mChangeQueue.queueChange(directBiDiSocket, SelectionKeyChangeQueue.SelectionKeyOperation.ADD, 1);
        }
    }
}
