package com.amazon.communication.socket;

import com.amazon.communication.ThreadName;
import com.amazon.communication.WorkExecutor;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.time.GlobalTimeSource;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.communication.websocket.CloseReason;
import com.amazon.dp.logger.DPLogger;
import com.dp.utils.ThreadGuard;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class CloseSocketCallable extends ProtocolSocketSingletonCallable {
    private static final DPLogger log = new DPLogger("TComm.CloseSocketCallable");
    private volatile CloseDetail mCloseDetail;
    private volatile CloseReason mCloseReason;
    private final SocketUsageWriter mSocketUsageWriter;

    public CloseSocketCallable(DirectBiDiSocket directBiDiSocket, WorkExecutor workExecutor, SocketUsageWriter socketUsageWriter) {
        super(directBiDiSocket, workExecutor);
        this.mSocketUsageWriter = socketUsageWriter;
    }

    @Override // com.amazon.communication.socket.ProtocolSocketSingletonCallable
    public void actualCall() throws Exception {
        log.verbose("actualCall", "closing socket for connection", "mSocket", this.mSocket);
        ThreadGuard.ensureThreadPrefix(ThreadName.HANDLER);
        this.mSocketUsageWriter.writeTimestamp(Measurements.COUNT_SOCKETS_CLOSED_TO_ENDPOINT, this.mSocket.getEndpointIdentity(), GlobalTimeSource.INSTANCE.currentTimeMillis());
        ((DirectBiDiSocket) this.mSocket).closeSocketChannel(this.mCloseReason, this.mCloseDetail);
    }

    @Override // com.amazon.communication.socket.ProtocolSocketSingletonCallable
    public boolean preCall() {
        ThreadGuard.ensureThreadPrefix(ThreadName.HANDLER);
        ProtocolSocket.ProtocolSocketState socketState = this.mSocket.socketState();
        if (socketState == ProtocolSocket.ProtocolSocketState.DISCONNECTED) {
            log.verbose("preCall", "nothing to do.", "socketState", socketState);
            return false;
        }
        return true;
    }

    public void setCloseDetail(CloseDetail closeDetail) {
        this.mCloseDetail = closeDetail;
    }

    public void setCloseReason(CloseReason closeReason) {
        this.mCloseReason = closeReason;
    }
}
