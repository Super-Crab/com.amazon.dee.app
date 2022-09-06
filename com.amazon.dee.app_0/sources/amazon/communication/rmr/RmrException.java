package amazon.communication.rmr;

import amazon.communication.CommunicationBaseException;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
@Deprecated
/* loaded from: classes.dex */
public class RmrException extends CommunicationBaseException {
    @FireOsSdk
    @Deprecated
    public RmrException(Exception exc) {
        super(exc);
        throw new UnsupportedOperationException();
    }

    @FireOsSdk
    @Deprecated
    public RmrException(String str) {
        super(str);
        throw new UnsupportedOperationException();
    }

    @FireOsSdk
    @Deprecated
    public RmrException(String str, Exception exc) {
        super(str, exc);
        throw new UnsupportedOperationException();
    }
}
