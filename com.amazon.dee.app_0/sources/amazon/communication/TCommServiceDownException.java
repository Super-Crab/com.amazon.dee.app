package amazon.communication;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public class TCommServiceDownException extends Exception {
    @FireOsSdk
    public TCommServiceDownException(Exception exc) {
        super(exc);
    }

    @FireOsSdk
    public TCommServiceDownException(String str, Exception exc) {
        super(str, exc);
    }

    @FireOsSdk
    public TCommServiceDownException(String str) {
        super(str);
    }
}
