package amazon.communication.devicetodevice;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public class NotificationServiceException extends Exception {
    @FireOsSdk
    public NotificationServiceException(Throwable th) {
        super(th);
    }

    @FireOsSdk
    public NotificationServiceException(String str, Throwable th) {
        super(str, th);
    }

    @FireOsSdk
    public NotificationServiceException(String str) {
        super(str);
    }
}
