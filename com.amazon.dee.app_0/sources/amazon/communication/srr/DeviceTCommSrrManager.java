package amazon.communication.srr;

import amazon.communication.CommunicationManager;
import amazon.communication.authentication.AccountRequestContext;
import amazon.communication.authentication.RequestContext;
import amazon.communication.connection.Policy;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public class DeviceTCommSrrManager extends TCommSrrManager {
    @FireOsSdk
    @Deprecated
    public DeviceTCommSrrManager(CommunicationManager communicationManager, Policy policy) {
        super(communicationManager, policy);
    }

    @Override // amazon.communication.srr.TCommSrrManager
    @FireOsSdk
    @Deprecated
    protected boolean isAnonymousCredentialsAllowed(RequestContext requestContext) {
        return requestContext != null && (requestContext instanceof AccountRequestContext) && ((AccountRequestContext) requestContext).getDirectedId() == null;
    }
}
