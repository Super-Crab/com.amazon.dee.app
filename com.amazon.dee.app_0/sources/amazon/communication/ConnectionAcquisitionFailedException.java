package amazon.communication;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public class ConnectionAcquisitionFailedException extends CommunicationBaseException {
    @FireOsSdk
    public ConnectionAcquisitionFailedException(Exception exc) {
        super(exc);
    }

    @FireOsSdk
    public ConnectionAcquisitionFailedException(String str) {
        super(str);
    }

    @FireOsSdk
    public ConnectionAcquisitionFailedException(String str, Exception exc) {
        super(str, exc);
    }
}
