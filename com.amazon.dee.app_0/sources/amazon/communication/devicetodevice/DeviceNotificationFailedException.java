package amazon.communication.devicetodevice;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public class DeviceNotificationFailedException extends Exception {
    @FireOsSdk
    public DeviceNotificationFailedException(Throwable th) {
        super(th);
    }

    @FireOsSdk
    public DeviceNotificationFailedException(String str, Throwable th) {
        super(str, th);
    }

    @FireOsSdk
    public DeviceNotificationFailedException(String str) {
        super(str);
    }
}
