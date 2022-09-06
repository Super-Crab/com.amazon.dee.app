package amazon.communication;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public class DuplicateHandlerException extends RegistrationFailedException {
    private static final long serialVersionUID = 248902769586705417L;

    @FireOsSdk
    public DuplicateHandlerException(Exception exc) {
        super(exc);
    }

    @FireOsSdk
    public DuplicateHandlerException(String str, Exception exc) {
        super(str, exc);
    }

    @FireOsSdk
    public DuplicateHandlerException(String str) {
        super(str);
    }
}
