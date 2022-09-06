package amazon.communication.gmd;

import amazon.communication.CommunicationBaseException;
/* loaded from: classes.dex */
public class GmdUnableToAcceptMessageException extends CommunicationBaseException {
    private static final long serialVersionUID = 4327248501268182274L;

    public GmdUnableToAcceptMessageException(Exception exc) {
        super(exc);
    }

    public GmdUnableToAcceptMessageException(String str, Exception exc) {
        super(str, exc);
    }

    public GmdUnableToAcceptMessageException(String str) {
        super(str);
    }
}
