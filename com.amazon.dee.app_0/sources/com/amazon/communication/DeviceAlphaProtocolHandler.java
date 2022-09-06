package com.amazon.communication;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
import android.os.Binder;
import com.amazon.communication.AlphaProtocolHandlerBase;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.dp.logger.DPLogger;
import com.dp.framework.StreamCodec;
import com.facebook.react.animated.InterpolationAnimatedNode;
import java.io.IOException;
import java.io.InputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class DeviceAlphaProtocolHandler extends AlphaProtocolHandlerBase {
    private static final DPLogger log = new DPLogger("TComm.DeviceAlphaProtocolHandler");
    private final BufferedMessageToInputStreamResponseRouter mBufferedMessageToResponseRouter;
    private BandwidthToolByteAccountant mByteAccountant;
    private final MessageRouter mMessageRouter;
    private final ResponseRouter mResponseRouter;

    /* loaded from: classes12.dex */
    private class DeviceMetricsLoggingNotificationSink extends AlphaProtocolHandlerBase.MetricsLoggingNotificationSink {
        private DeviceMetricsLoggingNotificationSink() {
            super();
        }

        @Override // com.amazon.communication.AlphaProtocolHandlerBase.MetricsLoggingNotificationSink, com.amazon.communication.ByteBufferChainHandlerNotificationSink
        public void chainHandled(ByteBufferChain byteBufferChain) {
            super.chainHandled(byteBufferChain);
        }

        @Override // com.amazon.communication.AlphaProtocolHandlerBase.MetricsLoggingNotificationSink, com.amazon.communication.ByteBufferChainHandlerNotificationSink
        public void chainRejected(ByteBufferChain byteBufferChain, boolean z) {
            super.chainRejected(byteBufferChain, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeviceAlphaProtocolHandler(StreamCodec streamCodec, MessageRouter messageRouter, ResponseRouter responseRouter, BufferedMessageToInputStreamResponseRouter bufferedMessageToInputStreamResponseRouter, ByteBufferChainHandler byteBufferChainHandler, WorkExecutor workExecutor, ProtocolSocket protocolSocket, boolean z, BandwidthToolByteAccountant bandwidthToolByteAccountant, ChannelRestrictor channelRestrictor) {
        super(streamCodec, byteBufferChainHandler, workExecutor, protocolSocket, z, channelRestrictor);
        this.mMessageRouter = messageRouter;
        this.mResponseRouter = responseRouter;
        this.mBufferedMessageToResponseRouter = bufferedMessageToInputStreamResponseRouter;
        this.mByteAccountant = bandwidthToolByteAccountant;
    }

    @Override // com.amazon.communication.AlphaProtocolHandlerBase
    protected LongLivedMessageLifeCycleTracker createLongLivedMessageLifeCycleTracker(ByteBufferChainHandler byteBufferChainHandler, WorkExecutor workExecutor, ProtocolSocket protocolSocket, int i, InputStream inputStream, String str, int i2, int i3) {
        return new DeviceLongLivedMessageLifeCycleTracker(this, byteBufferChainHandler, workExecutor, protocolSocket, i, inputStream, str, i2, i3, this.mByteAccountant);
    }

    @Override // com.amazon.communication.AlphaProtocolHandlerBase
    protected ByteBufferChainHandlerNotificationSink createNotificationSink() {
        return new DeviceMetricsLoggingNotificationSink();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.communication.AlphaProtocolHandlerBase
    public ByteBufferChain encodeMessageFragment(ByteBufferChain byteBufferChain, boolean z, String str, int i, int i2, int i3) throws ProtocolException, IOException {
        ByteBufferChain encodeMessageFragment = super.encodeMessageFragment(byteBufferChain, z, str, i, i2, i3);
        if (!z && i3 == 1) {
            this.mByteAccountant.accountBytesTransmitted(Binder.getCallingUid(), encodeMessageFragment.getDataSize());
        }
        return encodeMessageFragment;
    }

    @Override // com.amazon.communication.AlphaProtocolHandlerBase
    protected void handleSEQMessage(int i, int i2) throws ProtocolException {
        throw new UnsupportedOperationException("SEQ messages not yet handled");
    }

    @Override // com.amazon.communication.AlphaProtocolHandlerBase
    protected void handleSimpleMessage(EndpointIdentity endpointIdentity, String str, int i, int i2, boolean z, int i3, boolean z2, Message message) throws ProtocolException {
        log.verbose("handleSimpleMessage", "received message", "messageType", str, "messageId", Integer.valueOf(i2), "is there more", Boolean.valueOf(z), "message size", Integer.valueOf(message.getPayloadSize()), "channel", Integer.valueOf(i), InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, endpointIdentity);
        if (ProtocolHandler.MESSAGE_MESSAGE_TYPE.equals(str)) {
            if (z2) {
                this.mMessageRouter.routeMessageFragment(endpointIdentity, i2, message, z, i);
            } else {
                this.mMessageRouter.routeMessage(endpointIdentity, message, i);
            }
        } else if (!ProtocolHandler.RESPONSE_MESSAGE_TYPE.equals(str)) {
            log.warn("handleSimpleMessage", "Unsupported message type received", "messageType", str);
            throw new ProtocolException("Unsupported message type received");
        } else if (!z2) {
            this.mResponseRouter.routeResponse(endpointIdentity, message, i);
        } else {
            this.mBufferedMessageToResponseRouter.routeMessageFragment(endpointIdentity, i2, message, z, i);
        }
    }
}
