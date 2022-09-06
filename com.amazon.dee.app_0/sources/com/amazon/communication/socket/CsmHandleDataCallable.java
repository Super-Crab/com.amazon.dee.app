package com.amazon.communication.socket;

import amazon.communication.identity.EndpointIdentityFactory;
import amazon.communication.identity.ServiceIdentity;
import com.amazon.communication.ByteBufferChainMessageImpl;
import com.amazon.communication.PowerManagerWrapper;
import com.amazon.communication.ProtocolHandler;
import com.amazon.communication.ThreadName;
import com.amazon.communication.WorkExecutor;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dp.utils.ThreadGuard;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class CsmHandleDataCallable extends ProtocolSocketSingletonCallable {
    protected static final int DEFAULT_BUFFER_SIZE = 262144;
    private final ProtocolHandler mProtocolHandler;
    private final ByteBuffer mReadBuffer;
    private static final DPLogger log = new DPLogger("TComm.CsmHandleDataCallable");
    public static final ServiceIdentity GATEWAY_ENDPOINT = EndpointIdentityFactory.createServiceIdentity("DPGatewayService");

    public CsmHandleDataCallable(CsmProtocolSocket csmProtocolSocket, WorkExecutor workExecutor, ProtocolHandler protocolHandler, PowerManagerWrapper powerManagerWrapper) {
        super(csmProtocolSocket, workExecutor, powerManagerWrapper);
        this.mProtocolHandler = protocolHandler;
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
        int remaining = this.mReadBuffer.remaining();
        if (remaining <= 0) {
            log.warn("actualCall", "No data to read", new Object[0]);
        } else if (remaining > 262144) {
            this.mReadBuffer.clear();
            DPLogger dPLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received a message of size: ");
            outline107.append(this.mReadBuffer.remaining());
            outline107.append(" longer than expected: ");
            outline107.append(262144);
            dPLogger.warn("actualCall", outline107.toString(), new Object[0]);
        } else {
            this.mProtocolHandler.decodeMessage(GATEWAY_ENDPOINT, new ByteBufferChainMessageImpl(copyByteBuffer(this.mReadBuffer)));
        }
    }

    public void processBytesAndEnqueue(byte[] bArr) {
        this.mReadBuffer.clear();
        this.mReadBuffer.put(bArr);
        this.mReadBuffer.flip();
        enqueue();
    }
}
