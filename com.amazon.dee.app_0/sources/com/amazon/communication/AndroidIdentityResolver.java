package com.amazon.communication;

import amazon.communication.connection.Purpose;
import amazon.communication.identity.IRServiceEndpoint;
import amazon.communication.identity.IdentityResolver;
import amazon.communication.identity.ServiceIdentity;
import android.content.Context;
import android.os.RemoteException;
import com.amazon.communication.ICommunicationService;
import com.amazon.dp.logger.DPLogger;
import com.facebook.react.animated.InterpolationAnimatedNode;
/* loaded from: classes12.dex */
public class AndroidIdentityResolver implements IdentityResolver {
    private static final DPLogger log = new DPLogger("TComm.AndroidIdentityResolver");
    protected final AndroidTCommServiceConnection mServiceConnection;

    public AndroidIdentityResolver(Context context) {
        this.mServiceConnection = new AndroidTCommServiceConnection(context);
    }

    public void close() {
        this.mServiceConnection.unbindTCommService();
    }

    @Override // amazon.communication.identity.IdentityResolver
    public IRServiceEndpoint getEndpointForServiceName(String str) {
        return getEndpointForServiceNameDomainAndRealm(str, null, null);
    }

    protected IRServiceEndpoint getEndpointForServiceNameDomainAndRealm(String str, String str2, String str3) {
        try {
            this.mServiceConnection.bindTCommService();
            ICommunicationService asInterface = ICommunicationService.Stub.asInterface(this.mServiceConnection.getBinder());
            if (asInterface != null) {
                return asInterface.getIdentityResolver().resolveServiceEndpoint(str, str2, str3);
            }
            throw new amazon.communication.TCommServiceDownException("Acquired null instance of ICommunicationService");
        } catch (amazon.communication.TCommServiceDownException e) {
            throw new IllegalStateException(e);
        } catch (RemoteException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // amazon.communication.identity.IdentityResolver
    public IRServiceEndpoint resolveServiceEndpoint(ServiceIdentity serviceIdentity) {
        return getEndpointForServiceNameDomainAndRealm(serviceIdentity.getServiceName(), serviceIdentity.getDomain(), serviceIdentity.getRealm());
    }

    @Override // amazon.communication.identity.IdentityResolver
    public IRServiceEndpoint resolveServiceEndpoint(ServiceIdentity serviceIdentity, Purpose purpose) {
        log.warn("resolveServiceEndpoint", "Calling this method is discouraged! We'll ignore the Purpose in this impl.", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, serviceIdentity, "purpose", purpose);
        return resolveServiceEndpoint(serviceIdentity);
    }
}
