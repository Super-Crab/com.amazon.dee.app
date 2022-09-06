package amazon.communication;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public class RegistrationFailedException extends CommunicationBaseException {
    private static final long serialVersionUID = 1114444472481745442L;

    @FireOsSdk
    public RegistrationFailedException(Exception exc) {
        super(exc);
    }

    @FireOsSdk
    public RegistrationFailedException(String str, Exception exc) {
        super(str, exc);
    }

    @FireOsSdk
    public RegistrationFailedException(String str) {
        super(str);
    }
}
