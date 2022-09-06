package amazon.communication;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public final class MissingCredentialsException extends CommunicationBaseException {
    @FireOsSdk
    public MissingCredentialsException(Exception exc) {
        super(exc);
    }

    @FireOsSdk
    public MissingCredentialsException(String str, Exception exc) {
        super(str, exc);
    }

    @FireOsSdk
    public MissingCredentialsException(String str) {
        super(str);
    }
}
