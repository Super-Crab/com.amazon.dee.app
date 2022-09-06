package amazon.communication.devicetodevice;

import amazon.communication.identity.DeviceIdentity;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public interface DeviceToDeviceNotificationHandler {
    @FireOsSdk
    void onNotificationReceived(DeviceIdentity deviceIdentity, String str);
}
