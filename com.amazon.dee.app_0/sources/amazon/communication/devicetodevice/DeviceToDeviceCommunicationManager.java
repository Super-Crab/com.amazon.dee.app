package amazon.communication.devicetodevice;

import amazon.communication.ConnectionAcquisitionFailedException;
import amazon.communication.MessageHandler;
import amazon.communication.MissingCredentialsException;
import amazon.communication.RegistrationFailedException;
import amazon.communication.connection.Connection;
import amazon.communication.identity.DeviceIdentity;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public interface DeviceToDeviceCommunicationManager {
    @FireOsSdk
    public static final String SYSTEM_SERVICE_KEY = "com.amazon.devicetodevice.DeviceToDeviceCommunicationManager";

    @FireOsSdk
    WakeupConnection acquireWakeupConnection(DeviceIdentity deviceIdentity, Connection.ConnectionListener connectionListener) throws ConnectionAcquisitionFailedException, MissingCredentialsException;

    @FireOsSdk
    void deregisterMessageHandler();

    @FireOsSdk
    void initializeD2DCommunication() throws ConnectionAcquisitionFailedException;

    @FireOsSdk
    void notifyRemoteDeviceForD2DCommunication(DeviceIdentity deviceIdentity, String str, String str2) throws NotificationServiceException, MissingCredentialsException;

    @FireOsSdk
    void registerMessageHandler(MessageHandler messageHandler) throws RegistrationFailedException;

    @FireOsSdk
    void shutdownD2DCommunication();
}
