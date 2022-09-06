package amazon.communication;

import amazon.communication.authentication.RequestSigner;
import amazon.communication.connection.Policy;
import amazon.communication.devicetodevice.DeviceToDeviceCommunicationManager;
import amazon.communication.devicetodevice.DeviceToDeviceCommunicationManagerDelegate;
import amazon.communication.identity.IdentityResolver;
import amazon.communication.rmr.RmrInitializationFailedException;
import amazon.communication.rmr.RmrManager;
import amazon.communication.srr.DeviceTCommSrrManager;
import amazon.communication.srr.SrrManager;
import android.content.Context;
import com.amazon.communication.AndroidTCommManager;
import com.amazon.communication.devicetodevice.AndroidDeviceToDeviceCommunicationManager;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.dp.utils.FailFast;
/* loaded from: classes.dex */
public final class CommunicationFactory {
    private static SrrManager sSrrManager;

    @FireOsSdk
    public static CommunicationManager getCommunicationManager(Context context) {
        return new CommunicationManagerDelegate((AndroidTCommManager) CommunicationFactoryBase.getCommunicationManager(context));
    }

    @FireOsSdk
    public static DeviceToDeviceCommunicationManager getDeviceToDeviceCommunicationManager(Context context, String str) throws TCommServiceDownException {
        return new DeviceToDeviceCommunicationManagerDelegate((AndroidDeviceToDeviceCommunicationManager) CommunicationFactoryBase.getDeviceToDeviceCommunicationManager(context, str));
    }

    @FireOsSdk
    public static GatewayConnectivity getGatewayConnectivity(Context context) throws TCommServiceDownException {
        return CommunicationFactoryBase.getGatewayConnectivity(context);
    }

    @FireOsSdk
    public static IdentityResolver getIdentityResolver(Context context) {
        return CommunicationFactoryBase.getIdentityResolver(context);
    }

    @FireOsSdk
    @Deprecated
    public static RequestSigner getRequestSigner(Context context, String str) {
        return CommunicationFactoryBase.getRequestSigner(context, str);
    }

    @FireOsSdk
    @Deprecated
    public static RmrManager getRmrManager(Context context, int i) throws RmrInitializationFailedException {
        throw new UnsupportedOperationException();
    }

    @FireOsSdk
    @Deprecated
    public static SrrManager getSrrManager(Context context) {
        SrrManager srrManager;
        synchronized (CommunicationFactory.class) {
            try {
                if (context != null) {
                    if (sSrrManager == null) {
                        sSrrManager = new DeviceTCommSrrManager(getCommunicationManager(context), Policy.ONE_SHOT);
                    }
                    FailFast.expectNotNull(sSrrManager, "Unable to get SrrManager implementation");
                    srrManager = sSrrManager;
                } else {
                    throw new IllegalArgumentException("Context must not be null");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return srrManager;
    }
}
