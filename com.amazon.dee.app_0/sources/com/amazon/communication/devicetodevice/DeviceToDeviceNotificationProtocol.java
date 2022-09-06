package com.amazon.communication.devicetodevice;
/* loaded from: classes12.dex */
public class DeviceToDeviceNotificationProtocol {
    private static String DELIMITER = "_";
    public static int TCOMM_CHANNEL = 60000;

    private DeviceToDeviceNotificationProtocol() {
    }

    public static String createNotificationMessage(ApplicationIdentifier applicationIdentifier, ApplicationIdentifier applicationIdentifier2) {
        return applicationIdentifier.toString() + DELIMITER + applicationIdentifier2.toString();
    }
}
