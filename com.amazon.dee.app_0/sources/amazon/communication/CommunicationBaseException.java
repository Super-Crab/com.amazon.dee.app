package amazon.communication;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public class CommunicationBaseException extends Exception {
    @FireOsSdk
    public CommunicationBaseException(Throwable th) {
        super(th);
    }

    @FireOsSdk
    public CommunicationBaseException(String str, Throwable th) {
        super(str, th);
    }

    @FireOsSdk
    public CommunicationBaseException(String str) {
        super(str);
    }
}
