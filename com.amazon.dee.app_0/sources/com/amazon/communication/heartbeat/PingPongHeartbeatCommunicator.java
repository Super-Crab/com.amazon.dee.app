package com.amazon.communication.heartbeat;

import amazon.communication.Message;
import amazon.communication.MessageFactory;
import amazon.communication.MissingCredentialsException;
import amazon.communication.RegistrationFailedException;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.EchoMessageHandlerBase;
import com.amazon.communication.MessageRouter;
import com.amazon.communication.ProtocolHandler;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.time.GlobalTimeSource;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.animated.InterpolationAnimatedNode;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class PingPongHeartbeatCommunicator implements HeartbeatCommunicator {
    private static final String METRICS_SOURCE = "PingPongHeartbeatCommunicator";
    private static final int SOCKET_HASHCODE_LENGTH = 4;
    private static final int TIMESTAMP_LENGTH = 8;
    private static final DPLogger log = new DPLogger("TComm.PingPongHeartbeatCommunicator");
    private final int mChannel;
    private HeartbeatReceivedHandler mHeartbeatReceivedHandler;
    private final MessageRouter mMessageRouter;
    private final PongHeartbeatResponseMessageHandler mResponseMessageHandler = new PongHeartbeatResponseMessageHandler();

    /* loaded from: classes12.dex */
    private class PongHeartbeatResponseMessageHandler extends EchoMessageHandlerBase {
        private static final int SIZE_OF_INT = 4;
        private static final int SIZE_OF_LONG = 8;
        private final DPLogger log;

        private PongHeartbeatResponseMessageHandler() {
            this.log = new DPLogger("TComm.PongHeartbeatResponseMessageHandler");
        }

        private int readInt(InputStream inputStream) throws IOException {
            int read;
            int i = 0;
            for (int i2 = 0; i2 < 4 && (read = inputStream.read()) != -1; i2++) {
                i = (i << 8) + read;
            }
            return i;
        }

        private long readLong(InputStream inputStream) throws IOException {
            int read;
            long j = 0;
            for (int i = 0; i < 8 && (read = inputStream.read()) != -1; i++) {
                j = (j << 8) + read;
            }
            return j;
        }

        public int getHeaderLength() {
            return 3;
        }

        @Override // amazon.communication.MessageHandler
        public void onMessage(EndpointIdentity endpointIdentity, Message message) {
            this.log.verbose("onMessage", "message received", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity));
            InputStream payload = message.getPayload();
            if (EchoMessageHandlerBase.isPongMessage(payload)) {
                try {
                    int readInt = readInt(payload);
                    this.log.verbose("onMessage", "parsed pong message", "socketHashCode", Integer.valueOf(readInt), "timestamp", Long.valueOf(readLong(payload)));
                    if (PingPongHeartbeatCommunicator.this.mHeartbeatReceivedHandler != null) {
                        PingPongHeartbeatCommunicator.this.mHeartbeatReceivedHandler.onHeartbeatReceived(endpointIdentity, readInt);
                    } else {
                        this.log.warn("onMessage", "HeartbeatReceivedHandler is null", new Object[0]);
                    }
                    return;
                } catch (IOException e) {
                    this.log.error("onMessage", "error in handling heartbeat response", e);
                    return;
                }
            }
            this.log.warn("onMessage", "non-pong message received on channel", "channel", Integer.valueOf(PingPongHeartbeatCommunicator.this.mChannel));
        }

        @Override // amazon.communication.MessageHandler
        public void onMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z) {
            throw new UnsupportedOperationException("onMessageFragment is not supported for this class");
        }

        public void prepareHeader(ByteBuffer byteBuffer) {
            EchoMessageHandlerBase.preparePingHeader(byteBuffer);
        }
    }

    public PingPongHeartbeatCommunicator(MessageRouter messageRouter, int i) {
        this.mMessageRouter = messageRouter;
        this.mChannel = i;
        try {
            messageRouter.deregisterMessageHandler(this.mChannel);
            messageRouter.registerMessageHandler(this.mChannel, this.mResponseMessageHandler);
        } catch (RegistrationFailedException e) {
            log.error("constructor", "registration on channel failed", "mChannel", Integer.valueOf(this.mChannel), e);
            throw new IllegalStateException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("Registration on channel "), this.mChannel, " failed"), e);
        }
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatCommunicator
    public void registerHeartbeatReceivedHandler(HeartbeatReceivedHandler heartbeatReceivedHandler) {
        if (heartbeatReceivedHandler != null) {
            this.mHeartbeatReceivedHandler = heartbeatReceivedHandler;
            return;
        }
        throw new IllegalArgumentException("Handler must not be null.");
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatCommunicator
    public boolean sendHeartbeat(ProtocolSocket protocolSocket) {
        boolean z;
        boolean z2;
        log.verbose("sendHeartbeat", "sending heartbeat", "socket", protocolSocket, "socket hashCode", Integer.valueOf(protocolSocket.hashCode()));
        String purpose = protocolSocket.getPurpose().getPurpose();
        int length = purpose.length();
        long currentTimeMillis = GlobalTimeSource.INSTANCE.currentTimeMillis();
        ByteBuffer allocate = ByteBuffer.allocate(((length * 16) / 8) + GeneratedOutlineSupport1.outline1(this.mResponseMessageHandler.getHeaderLength(), 4, 8, 4));
        this.mResponseMessageHandler.prepareHeader(allocate);
        allocate.putInt(protocolSocket.hashCode());
        allocate.putLong(currentTimeMillis);
        allocate.putInt(length);
        log.verbose("sendHeartbeat", "adding socket's purpose to the message", "purposeLength", Integer.valueOf(length), "purpose", purpose);
        for (int i = 0; i < length; i++) {
            allocate.putChar(purpose.charAt(i));
        }
        allocate.rewind();
        try {
            protocolSocket.sendMessage(MessageFactory.createMessage(allocate), ProtocolHandler.MESSAGE_MESSAGE_TYPE, this.mChannel);
        } catch (MissingCredentialsException e) {
            e = e;
            z2 = false;
        } catch (IOException e2) {
            e = e2;
            z = false;
        }
        try {
            log.verbose("sendHeartbeat", "successfully sent heartbeat", "socket", protocolSocket);
            return true;
        } catch (MissingCredentialsException e3) {
            e = e3;
            z2 = true;
            log.error("sendHeartbeat", "cannot send heartbeat", "socket.hashCode()", Integer.valueOf(protocolSocket.hashCode()), "socket.getEndpointIdentity()", protocolSocket.getEndpointIdentity(), e);
            return z2;
        } catch (IOException e4) {
            e = e4;
            z = true;
            log.error("sendHeartbeat", "cannot send heartbeat", "socket.hashCode()", Integer.valueOf(protocolSocket.hashCode()), "socket.getEndpointIdentity()", protocolSocket.getEndpointIdentity(), e);
            return z;
        }
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatCommunicator
    public void shutdown() {
        this.mMessageRouter.deregisterMessageHandler(this.mChannel);
    }
}
