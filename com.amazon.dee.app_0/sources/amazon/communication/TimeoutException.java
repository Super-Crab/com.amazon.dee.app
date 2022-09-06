package amazon.communication;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public class TimeoutException extends Exception {
    @FireOsSdk
    public TimeoutException(Exception exc) {
        super(exc);
    }

    @FireOsSdk
    public TimeoutException(String str, Exception exc) {
        super(str, exc);
    }

    @FireOsSdk
    public TimeoutException(String str) {
        super(str);
    }
}
