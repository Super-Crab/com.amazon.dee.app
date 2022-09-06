package com.amazon.communication;

import com.amazon.communication.socket.ProtocolSocket;
import com.dp.framework.StreamCodec;
import java.util.Map;
/* loaded from: classes12.dex */
public class DeviceAlphaProtocolHandlerFactory extends AlphaProtocolHandlerFactoryBase {
    private final BufferedMessageToInputStreamResponseRouter mBufferedMessageToResponseRouter;
    private final BandwidthToolByteAccountant mByteAccountant;
    private final ChannelRestrictor mChannelRestrictor;
    private final MessageRouter mMessageRouter;
    private final ResponseRouter mResponseRouter;

    public DeviceAlphaProtocolHandlerFactory(MessageRouter messageRouter, ResponseRouter responseRouter, BufferedMessageToInputStreamResponseRouter bufferedMessageToInputStreamResponseRouter, StreamCodec streamCodec, Map<String, String> map, BandwidthToolByteAccountant bandwidthToolByteAccountant, ChannelRestrictor channelRestrictor) {
        super(streamCodec, map);
        this.mMessageRouter = messageRouter;
        this.mResponseRouter = responseRouter;
        this.mBufferedMessageToResponseRouter = bufferedMessageToInputStreamResponseRouter;
        this.mByteAccountant = bandwidthToolByteAccountant;
        this.mChannelRestrictor = channelRestrictor;
    }

    @Override // com.amazon.communication.ProtocolHandlerFactory
    public ProtocolHandler makeProtocolHandler(ByteBufferChainHandler byteBufferChainHandler, WorkExecutor workExecutor, ProtocolSocket protocolSocket, boolean z, Map<String, String> map) throws TuningFailedException {
        DeviceAlphaProtocolHandler deviceAlphaProtocolHandler = new DeviceAlphaProtocolHandler(this.mStreamCodec, this.mMessageRouter, this.mResponseRouter, this.mBufferedMessageToResponseRouter, byteBufferChainHandler, workExecutor, protocolSocket, z, this.mByteAccountant, this.mChannelRestrictor);
        deviceAlphaProtocolHandler.setLocalProtocolParameters(this.mLocalParameters);
        map.put(AlphaProtocolHandlerBase.MAX_FRAGMENT_SIZE_KEY, "16000");
        deviceAlphaProtocolHandler.setRemoteProtocolParameters(map);
        return deviceAlphaProtocolHandler;
    }
}
