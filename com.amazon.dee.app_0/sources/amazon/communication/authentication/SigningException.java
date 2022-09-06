package amazon.communication.authentication;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public class SigningException extends Exception {
    @FireOsSdk
    public SigningException(String str) {
        super(str);
    }

    @FireOsSdk
    public SigningException(String str, Throwable th) {
        super(str, th);
    }

    @FireOsSdk
    public SigningException(Throwable th) {
        super(th);
    }
}
