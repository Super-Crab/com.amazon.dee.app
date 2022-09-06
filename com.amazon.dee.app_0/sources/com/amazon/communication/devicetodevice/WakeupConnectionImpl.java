package com.amazon.communication.devicetodevice;

import amazon.communication.Message;
import amazon.communication.MissingCredentialsException;
import amazon.communication.ResponseHandlerBase;
import amazon.communication.connection.Channels;
import amazon.communication.connection.IConnection;
import amazon.communication.connection.IllegalConnectionStateException;
import amazon.communication.connection.TransmissionFailedException;
import amazon.communication.devicetodevice.IWakeupConnection;
import amazon.communication.identity.DeviceIdentity;
import com.amazon.dp.logger.DPLogger;
import org.apache.http.client.methods.HttpRequestBase;
/* loaded from: classes12.dex */
public class WakeupConnectionImpl implements IWakeupConnection {
    private static final DPLogger log = new DPLogger("TComm.WakeupConnectionImpl");
    private final IConnection mConnection;
    private final D2DApplicationProtocol mD2DProtocol;
    private final String mSourceApplication;
    private final DeviceIdentity mTargetDevice;

    public WakeupConnectionImpl(IConnection iConnection, D2DApplicationProtocol d2DApplicationProtocol, DeviceIdentity deviceIdentity, String str) {
        this.mConnection = iConnection;
        this.mD2DProtocol = d2DApplicationProtocol;
        this.mTargetDevice = deviceIdentity;
        this.mSourceApplication = str;
    }

    @Override // amazon.communication.connection.IConnection
    public void addConnectionListener(IConnection.ConnectionListener connectionListener) {
        this.mConnection.addConnectionListener(connectionListener);
    }

    @Override // amazon.communication.connection.IConnection
    public int getConnectionState() {
        return this.mConnection.getConnectionState();
    }

    @Override // amazon.communication.connection.IConnection
    public void release() {
        this.mConnection.release();
    }

    @Override // amazon.communication.connection.IConnection
    public void removeConnectionListener(IConnection.ConnectionListener connectionListener) {
        this.mConnection.removeConnectionListener(connectionListener);
    }

    @Override // amazon.communication.connection.IConnection
    public void sendMessage(Message message, int i) throws TransmissionFailedException, IllegalConnectionStateException, MissingCredentialsException {
        this.mConnection.sendMessage(message, i);
    }

    @Override // amazon.communication.devicetodevice.IWakeupConnection
    public void sendMessageWithWakeup(Message message, int i, String str) throws TransmissionFailedException, IllegalConnectionStateException, MissingCredentialsException {
        D2DMessage d2DMessage = new D2DMessage();
        d2DMessage.destination = this.mTargetDevice;
        d2DMessage.destinationApp = str;
        d2DMessage.originApp = this.mSourceApplication;
        d2DMessage.messageType = D2DApplicationProtocol.D2D_MESSAGE_WITH_CHANNEL_MESSAGE_TYPE;
        d2DMessage.message = message;
        d2DMessage.channel = i;
        this.mConnection.sendMessage(this.mD2DProtocol.encode(d2DMessage), Channels.D2D_MESSAGING_CHANNEL);
    }

    @Override // amazon.communication.connection.IConnection
    public void sendRequest(HttpRequestBase httpRequestBase, ResponseHandlerBase responseHandlerBase) {
        throw new UnsupportedOperationException("sendRequest not supported on D2D connections");
    }
}
