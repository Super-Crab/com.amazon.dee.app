package com.amazon.communication;

import com.amazon.communication.socket.ProtocolSocket;
import java.util.Map;
/* loaded from: classes12.dex */
public interface ProtocolHandlerFactory {
    Map<String, String> getLocalProtocolParameters(ProtocolHandler protocolHandler);

    String getProtocolName();

    ProtocolHandler makeProtocolHandler(ByteBufferChainHandler byteBufferChainHandler, WorkExecutor workExecutor, ProtocolSocket protocolSocket, boolean z, Map<String, String> map) throws TuningFailedException;
}
