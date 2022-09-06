package com.amazon.communication;

import amazon.communication.BufferedMessageHandler;
import amazon.communication.ConvertToInputStreamMessageImplMessageHandler;
import amazon.communication.MessageHandler;
import amazon.communication.RegistrationFailedException;
import amazon.communication.connection.Channels;
import amazon.communication.connection.Policy;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.EndpointIdentityFactory;
import amazon.communication.identity.IdentityResolver;
import amazon.speech.simclient.genericconnection.ConnectionResult;
import amazon.speech.simclient.genericconnection.DownstreamMessageCallback;
import amazon.speech.simclient.genericconnection.GenericConnectionStatusCallback;
import com.amazon.communication.devicetodevice.ChannelAwareD2DMessageRouter;
import com.amazon.communication.devicetodevice.D2DNotificationRouter;
import com.amazon.communication.devicetodevice.DeviceD2DApplicationProtocol;
import com.amazon.communication.devicetodevice.DeviceToDeviceMessagingMessageHandler;
import com.amazon.communication.devicetodevice.DeviceToDeviceNotificationMessageHandler;
import com.amazon.communication.gw.DeviceGatewayControlMessageHandler;
import com.amazon.communication.gw.GatewayApplicationProtocol;
import com.amazon.communication.gw.GatewayControlProtocol;
import com.amazon.communication.gw.GatewayMessageHandler;
import com.amazon.communication.socket.ConnectReason;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.socket.SocketAcquisitionFailedException;
import com.amazon.communication.socket.decisionengine.CsmSocketDecisionEngine;
import com.amazon.dp.logger.DPLogger;
import com.dp.framework.FlatStreamCodec;
import com.dp.framework.HexStreamCodec;
import java.util.HashMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class CsmCommunicationEngine extends CommunicationEngineBase implements DownstreamMessageCallback, GenericConnectionStatusCallback {
    private final DPLogger log;
    private final BufferedMessageHandler mBufferedMessageHandler;
    private final ChannelAwareD2DMessageRouter mD2DMessageRouter;
    private final MessageHandler mD2DMessagingMessageHandler;
    private final MessageHandler mD2DNotificationMessageHandler;
    private final D2DNotificationRouter mD2DNotificationRouter;
    private final DeviceD2DApplicationProtocol mD2DProtocol;
    private final DeviceGatewayControlMessageHandler mGatewayControlMessageHandler;
    private final IdentityResolver mIdentityResolver;
    private final MessageRouter mMessageRouter;
    private final CsmSocketDecisionEngine mSocketDecisionEngine;
    private final CsmSocketManager mSocketManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CsmCommunicationEngine(CsmSocketManager csmSocketManager, MessageRouter messageRouter, ResponseRouter responseRouter, BufferedMessageToInputStreamResponseRouter bufferedMessageToInputStreamResponseRouter, D2DNotificationRouter d2DNotificationRouter, ChannelAwareD2DMessageRouter channelAwareD2DMessageRouter, ProtocolHandlerManager protocolHandlerManager, IdentityResolver identityResolver, BandwidthToolByteAccountant bandwidthToolByteAccountant, ChannelRestrictor channelRestrictor, CsmSocketDecisionEngine csmSocketDecisionEngine, GatewayApplicationProtocol gatewayApplicationProtocol, GatewayControlProtocol gatewayControlProtocol, HexStreamCodec hexStreamCodec) throws RegistrationFailedException {
        super(protocolHandlerManager);
        this.log = new DPLogger("TComm.CsmCommunicationEngine");
        this.mSocketManager = csmSocketManager;
        loadSupportedProtocolHandlersIntoManager(messageRouter, responseRouter, bufferedMessageToInputStreamResponseRouter, protocolHandlerManager, bandwidthToolByteAccountant, channelRestrictor);
        this.mBufferedMessageHandler = new BufferedMessageHandler(new ConvertToInputStreamMessageImplMessageHandler(new GatewayMessageHandler(gatewayApplicationProtocol)));
        this.mGatewayControlMessageHandler = new DeviceGatewayControlMessageHandler(gatewayControlProtocol, this.mSocketManager);
        this.mIdentityResolver = identityResolver;
        this.mSocketDecisionEngine = csmSocketDecisionEngine;
        this.mD2DNotificationRouter = d2DNotificationRouter;
        this.mD2DMessageRouter = channelAwareD2DMessageRouter;
        this.mD2DProtocol = new DeviceD2DApplicationProtocol(hexStreamCodec, this.mD2DNotificationRouter, this.mD2DMessageRouter);
        this.mD2DNotificationMessageHandler = new DeviceToDeviceNotificationMessageHandler(this.mD2DProtocol);
        this.mD2DMessagingMessageHandler = new DeviceToDeviceMessagingMessageHandler(this.mD2DProtocol);
        this.mMessageRouter = messageRouter;
        this.mMessageRouter.registerMessageHandler(Channels.GW_CHANNEL, this.mBufferedMessageHandler);
        this.mMessageRouter.registerMessageHandler(Channels.GW_CTL_CHANNEL, this.mGatewayControlMessageHandler);
        this.mMessageRouter.registerMessageHandler(60000, this.mD2DNotificationMessageHandler);
        this.mMessageRouter.registerMessageHandler(Channels.D2D_MESSAGING_CHANNEL, this.mD2DMessagingMessageHandler);
    }

    private static void loadSupportedProtocolHandlersIntoManager(MessageRouter messageRouter, ResponseRouter responseRouter, BufferedMessageToInputStreamResponseRouter bufferedMessageToInputStreamResponseRouter, ProtocolHandlerManager protocolHandlerManager, BandwidthToolByteAccountant bandwidthToolByteAccountant, ChannelRestrictor channelRestrictor) {
        HexStreamCodec hexStreamCodec = new HexStreamCodec();
        FlatStreamCodec flatStreamCodec = new FlatStreamCodec();
        HashMap hashMap = new HashMap();
        hashMap.put(AlphaProtocolHandlerBase.MAX_FRAGMENT_SIZE_KEY, Integer.toString(16000));
        hashMap.put(AlphaProtocolHandlerBase.RECEIVE_WINDOW_SIZE_KEY, Integer.toString(16));
        protocolHandlerManager.addProtocolHandlerFactory(new DeviceAlphaProtocolHandlerFactory(messageRouter, responseRouter, bufferedMessageToInputStreamResponseRouter, hexStreamCodec, hashMap, bandwidthToolByteAccountant, channelRestrictor));
        protocolHandlerManager.addProtocolHandlerFactory(new DeviceAlphaProtocolHandlerFactory(messageRouter, responseRouter, bufferedMessageToInputStreamResponseRouter, flatStreamCodec, hashMap, bandwidthToolByteAccountant, channelRestrictor));
    }

    @Override // com.amazon.communication.CommunicationEngine
    public ProtocolSocket acquireProtocolSocket(EndpointIdentity endpointIdentity, Policy policy, ConnectReason connectReason, String str) throws SocketAcquisitionFailedException {
        EndpointIdentity createFromUrn = EndpointIdentityFactory.createFromUrn(endpointIdentity.toString());
        this.log.verbose("acquireProtocolSocket", "received EndpointIdentity", "endpoint", EndpointIdentity.logSafe(createFromUrn));
        ProtocolSocket acquireSocket = this.mSocketDecisionEngine.acquireSocket(createFromUrn, null, null, null, null);
        this.log.verbose("acquireProtocolSocket", "returning socket", "socket", acquireSocket);
        return acquireSocket;
    }

    @Override // amazon.speech.simclient.genericconnection.DownstreamMessageCallback
    public void onMessage(byte[] bArr) {
        this.mSocketManager.getCsmProtocolSocket().handleData(bArr);
    }

    @Override // amazon.speech.simclient.genericconnection.GenericConnectionStatusCallback
    public void onResult(ConnectionResult connectionResult) {
        this.mSocketManager.getCsmProtocolSocket().updateSocketState(connectionResult);
    }

    @Override // com.amazon.communication.CommunicationEngine
    public void shutdown() {
        this.log.verbose("shutdown", "entering", new Object[0]);
        MessageRouter messageRouter = this.mMessageRouter;
        if (messageRouter != null) {
            messageRouter.deregisterMessageHandler(Channels.GW_CHANNEL);
            this.mMessageRouter.deregisterMessageHandler(Channels.GW_CTL_CHANNEL);
            this.mMessageRouter.deregisterMessageHandler(60000);
            this.mMessageRouter.deregisterMessageHandler(Channels.D2D_MESSAGING_CHANNEL);
        }
    }
}
