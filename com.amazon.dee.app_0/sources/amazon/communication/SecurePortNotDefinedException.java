package amazon.communication;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public class SecurePortNotDefinedException extends ConnectionAcquisitionFailedException {
    @FireOsSdk
    public SecurePortNotDefinedException(Exception exc) {
        super(exc);
    }

    @FireOsSdk
    public SecurePortNotDefinedException(String str, Exception exc) {
        super(str, exc);
    }

    @FireOsSdk
    public SecurePortNotDefinedException(String str) {
        super(str);
    }
}
