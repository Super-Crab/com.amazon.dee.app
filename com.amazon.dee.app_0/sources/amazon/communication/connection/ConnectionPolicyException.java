package amazon.communication.connection;

import amazon.communication.CommunicationBaseException;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public class ConnectionPolicyException extends CommunicationBaseException {
    @FireOsSdk
    public ConnectionPolicyException(Exception exc) {
        super(exc);
    }

    @FireOsSdk
    public ConnectionPolicyException(String str, Exception exc) {
        super(str, exc);
    }

    @FireOsSdk
    public ConnectionPolicyException(String str) {
        super(str);
    }
}
