package amazon.communication.devicetodevice;

import amazon.communication.ConnectionAcquisitionFailedException;
import amazon.communication.ICommunicationManager;
import amazon.communication.MessageHandler;
import amazon.communication.MissingCredentialsException;
import amazon.communication.RegistrationFailedException;
import amazon.communication.ServiceConnectedHandler;
import amazon.communication.connection.Connection;
import amazon.communication.connection.ConnectionListenerDelegate;
import amazon.communication.connection.IConnection;
import amazon.communication.identity.DeviceIdentity;
import com.amazon.communication.devicetodevice.AndroidDeviceToDeviceCommunicationManager;
import com.amazon.communication.devicetodevice.WakeupConnectionImpl;
/* loaded from: classes.dex */
public class DeviceToDeviceCommunicationManagerDelegate implements DeviceToDeviceCommunicationManager {
    private AndroidDeviceToDeviceCommunicationManager delegate;

    public DeviceToDeviceCommunicationManagerDelegate(AndroidDeviceToDeviceCommunicationManager androidDeviceToDeviceCommunicationManager) {
        this.delegate = androidDeviceToDeviceCommunicationManager;
    }

    public IWakeupConnection acquireWakeupConnection(DeviceIdentity deviceIdentity, IConnection.ConnectionListener connectionListener) throws ConnectionAcquisitionFailedException, MissingCredentialsException {
        return this.delegate.acquireWakeupConnection(deviceIdentity, connectionListener);
    }

    public void close() {
        this.delegate.close();
    }

    @Override // amazon.communication.devicetodevice.DeviceToDeviceCommunicationManager
    public void deregisterMessageHandler() {
        this.delegate.deregisterMessageHandler();
    }

    @Override // amazon.communication.devicetodevice.DeviceToDeviceCommunicationManager
    public void initializeD2DCommunication() throws ConnectionAcquisitionFailedException {
        this.delegate.initializeD2DCommunication();
    }

    @Override // amazon.communication.devicetodevice.DeviceToDeviceCommunicationManager
    public void notifyRemoteDeviceForD2DCommunication(DeviceIdentity deviceIdentity, String str, String str2) throws NotificationServiceException, MissingCredentialsException {
        this.delegate.notifyRemoteDeviceForD2DCommunication(deviceIdentity, str, str2);
    }

    @Override // amazon.communication.devicetodevice.DeviceToDeviceCommunicationManager
    public void registerMessageHandler(MessageHandler messageHandler) throws RegistrationFailedException {
        this.delegate.registerMessageHandler(messageHandler);
    }

    public void registerServiceConnectedHandler(ServiceConnectedHandler serviceConnectedHandler) {
        this.delegate.registerServiceConnectedHandler(serviceConnectedHandler);
    }

    public void setCommunicationManager(ICommunicationManager iCommunicationManager) {
        this.delegate.setCommunicationManager(iCommunicationManager);
    }

    @Override // amazon.communication.devicetodevice.DeviceToDeviceCommunicationManager
    public void shutdownD2DCommunication() {
        this.delegate.shutdownD2DCommunication();
    }

    @Override // amazon.communication.devicetodevice.DeviceToDeviceCommunicationManager
    public WakeupConnection acquireWakeupConnection(DeviceIdentity deviceIdentity, Connection.ConnectionListener connectionListener) throws ConnectionAcquisitionFailedException, MissingCredentialsException {
        return new WakeupConnectionDelegate((WakeupConnectionImpl) this.delegate.acquireWakeupConnection(deviceIdentity, new ConnectionListenerDelegate(connectionListener)));
    }
}
