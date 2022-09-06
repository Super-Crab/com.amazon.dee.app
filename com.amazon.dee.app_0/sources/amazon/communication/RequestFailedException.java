package amazon.communication;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public class RequestFailedException extends Exception {
    @FireOsSdk
    public RequestFailedException(Exception exc) {
        super(exc);
    }

    @FireOsSdk
    public RequestFailedException(String str, Exception exc) {
        super(str, exc);
    }

    @FireOsSdk
    public RequestFailedException(String str) {
        super(str);
    }
}
