package amazon.communication;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public final class WiFiUnavailableException extends ConnectionAcquisitionFailedException {
    @FireOsSdk
    public WiFiUnavailableException(Exception exc) {
        super(exc);
    }

    @FireOsSdk
    public WiFiUnavailableException(String str, Exception exc) {
        super(str, exc);
    }

    @FireOsSdk
    public WiFiUnavailableException(String str) {
        super(str);
    }
}
