package com.amazon.communication.devicetodevice;

import amazon.communication.ConnectionAcquisitionFailedException;
import amazon.communication.ICommunicationManager;
import amazon.communication.Message;
import amazon.communication.MessageHandler;
import amazon.communication.MissingCredentialsException;
import amazon.communication.RegistrationFailedException;
import amazon.communication.ServiceConnectedHandler;
import amazon.communication.TCommServiceDownException;
import amazon.communication.connection.IConnection;
import amazon.communication.connection.Policy;
import amazon.communication.connection.Purpose;
import amazon.communication.devicetodevice.IDeviceToDeviceCommunicationManager;
import amazon.communication.devicetodevice.IWakeupConnection;
import amazon.communication.devicetodevice.NotificationServiceException;
import amazon.communication.identity.DeviceIdentity;
import amazon.communication.identity.EndpointIdentity;
import android.content.Context;
import com.amazon.communication.AndroidTCommServiceConnection;
import com.amazon.communication.ICommunicationService;
import com.amazon.dp.logger.DPLogger;
import com.dp.framework.StreamCodec;
/* loaded from: classes12.dex */
public class AndroidDeviceToDeviceCommunicationManager implements IDeviceToDeviceCommunicationManager {
    private static final String D2D_RELAY_SERVICE = "D2DRelayService";
    private static final DPLogger log = new DPLogger("TComm.AndroidDeviceToDeviceCommunicationManager");
    private ICommunicationManager mCommunicationManager;
    private final Context mContext;
    private final D2DApplicationProtocol mD2DProtocol;
    private final Policy mPolicy = new Policy.Builder().setIsLowLatencyNecessary(false).setPurpose(Purpose.D2D_MESSAGING).build();
    protected final AndroidTCommServiceConnection mServiceConnection;
    private final String mThisApplication;

    /* loaded from: classes12.dex */
    private class EncodeOnlyDeviceD2DApplicationProtocol extends D2DApplicationProtocol {
        public EncodeOnlyDeviceD2DApplicationProtocol(StreamCodec streamCodec) {
            super(streamCodec);
        }

        @Override // com.amazon.communication.devicetodevice.D2DApplicationProtocol
        public void decode(Message message, EndpointIdentity endpointIdentity) {
            throw new UnsupportedOperationException("This class can only encode!");
        }
    }

    public AndroidDeviceToDeviceCommunicationManager(Context context, String str, StreamCodec streamCodec) {
        if (str != null) {
            this.mThisApplication = str;
            this.mContext = context;
            this.mServiceConnection = new AndroidTCommServiceConnection(context);
            this.mServiceConnection.bindTCommService();
            this.mD2DProtocol = new EncodeOnlyDeviceD2DApplicationProtocol(streamCodec);
            return;
        }
        throw new IllegalArgumentException("applicationIdentifier must not be null");
    }

    @Override // amazon.communication.devicetodevice.IDeviceToDeviceCommunicationManager
    public IWakeupConnection acquireWakeupConnection(DeviceIdentity deviceIdentity, IConnection.ConnectionListener connectionListener) throws ConnectionAcquisitionFailedException, MissingCredentialsException {
        return new WakeupConnectionImpl(this.mCommunicationManager.acquireConnection(deviceIdentity, this.mPolicy, connectionListener), this.mD2DProtocol, deviceIdentity, this.mThisApplication);
    }

    public void close() {
        this.mServiceConnection.unbindTCommService();
    }

    @Override // amazon.communication.devicetodevice.IDeviceToDeviceCommunicationManager
    public void deregisterMessageHandler() {
    }

    protected ICommunicationService getService() throws TCommServiceDownException {
        ICommunicationService asInterface = ICommunicationService.Stub.asInterface(this.mServiceConnection.getBinder());
        if (asInterface != null) {
            return asInterface;
        }
        throw new TCommServiceDownException("acquired null instance of ICommunicationService");
    }

    @Override // amazon.communication.devicetodevice.IDeviceToDeviceCommunicationManager
    public void initializeD2DCommunication() throws ConnectionAcquisitionFailedException {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override // amazon.communication.devicetodevice.IDeviceToDeviceCommunicationManager
    public void notifyRemoteDeviceForD2DCommunication(DeviceIdentity deviceIdentity, String str, String str2) throws NotificationServiceException, MissingCredentialsException {
        throw new UnsupportedOperationException();
    }

    @Override // amazon.communication.devicetodevice.IDeviceToDeviceCommunicationManager
    public void registerMessageHandler(MessageHandler messageHandler) throws RegistrationFailedException {
    }

    public void registerServiceConnectedHandler(ServiceConnectedHandler serviceConnectedHandler) {
        this.mServiceConnection.registerServiceConnectedHandler(serviceConnectedHandler);
    }

    public void setCommunicationManager(ICommunicationManager iCommunicationManager) {
        this.mCommunicationManager = iCommunicationManager;
    }

    @Override // amazon.communication.devicetodevice.IDeviceToDeviceCommunicationManager
    public void shutdownD2DCommunication() {
        close();
    }
}
