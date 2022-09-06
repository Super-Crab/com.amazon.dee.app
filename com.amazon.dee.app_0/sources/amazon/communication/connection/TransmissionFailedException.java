package amazon.communication.connection;

import amazon.communication.CommunicationBaseException;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public class TransmissionFailedException extends CommunicationBaseException {
    @FireOsSdk
    public TransmissionFailedException(Exception exc) {
        super(exc);
    }

    @FireOsSdk
    public TransmissionFailedException(String str, Exception exc) {
        super(str, exc);
    }

    @FireOsSdk
    public TransmissionFailedException(String str) {
        super(str);
    }
}
