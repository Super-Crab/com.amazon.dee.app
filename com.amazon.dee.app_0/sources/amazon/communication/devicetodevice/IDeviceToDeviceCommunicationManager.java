package amazon.communication.devicetodevice;

import amazon.communication.ConnectionAcquisitionFailedException;
import amazon.communication.MessageHandler;
import amazon.communication.MissingCredentialsException;
import amazon.communication.RegistrationFailedException;
import amazon.communication.connection.IConnection;
import amazon.communication.identity.DeviceIdentity;
/* loaded from: classes.dex */
public interface IDeviceToDeviceCommunicationManager {
    public static final String SYSTEM_SERVICE_KEY = "com.amazon.devicetodevice.IDeviceToDeviceCommunicationManager";

    IWakeupConnection acquireWakeupConnection(DeviceIdentity deviceIdentity, IConnection.ConnectionListener connectionListener) throws ConnectionAcquisitionFailedException, MissingCredentialsException;

    void deregisterMessageHandler();

    void initializeD2DCommunication() throws ConnectionAcquisitionFailedException;

    void notifyRemoteDeviceForD2DCommunication(DeviceIdentity deviceIdentity, String str, String str2) throws NotificationServiceException, MissingCredentialsException;

    void registerMessageHandler(MessageHandler messageHandler) throws RegistrationFailedException;

    void shutdownD2DCommunication();
}
