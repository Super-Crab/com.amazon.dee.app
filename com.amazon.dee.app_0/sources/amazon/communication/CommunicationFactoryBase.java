package amazon.communication;

import amazon.communication.authentication.RequestSigner;
import amazon.communication.devicetodevice.IDeviceToDeviceCommunicationManager;
import amazon.communication.identity.IdentityResolver;
import android.content.Context;
import com.amazon.alexa.accessoryclient.common.util.MetricsConstants;
import com.amazon.communication.AndroidIdentityResolver;
import com.amazon.communication.AndroidTCommManager;
import com.amazon.communication.authentication.DcpOAuthRequestSigner;
import com.amazon.communication.authentication.DcpRequestSigner;
import com.amazon.communication.devicetodevice.AndroidDeviceToDeviceCommunicationManager;
import com.amazon.communication.time.AndroidTimeSource;
import com.amazon.communication.time.GlobalTimeSource;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.api.AuthenticationType;
import com.dp.framework.HexStreamCodec;
import com.dp.utils.FailFast;
/* loaded from: classes.dex */
public class CommunicationFactoryBase {
    private static final DPLogger log = new DPLogger("TComm.CommunicationFactoryBase");
    private static AndroidTCommManager sCommunicationManager = null;
    private static IdentityResolver sIdentityResolver = null;
    private static IDeviceToDeviceCommunicationManager sD2dCommunicationManager = null;
    private static DcpRequestSigner sDcpRequestSigner = null;
    private static DcpOAuthRequestSigner sDcpOAuthRequestSigner = null;

    /* renamed from: amazon.communication.CommunicationFactoryBase$2  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$identity$auth$device$api$AuthenticationType = new int[AuthenticationType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$AuthenticationType[AuthenticationType.ADPAuthenticator.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$AuthenticationType[AuthenticationType.OAuth.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static {
        GlobalTimeSource.INSTANCE = AndroidTimeSource.INSTANCE;
    }

    public static ICommunicationManager getCommunicationManager(Context context) {
        AndroidTCommManager androidTCommManager;
        synchronized (CommunicationFactoryBase.class) {
            try {
                if (context != null) {
                    if (sCommunicationManager == null) {
                        sCommunicationManager = new AndroidTCommManager(context.getApplicationContext());
                        sCommunicationManager.registerServiceConnectivityListener(new ServiceConnectivityListener() { // from class: amazon.communication.CommunicationFactoryBase.1
                            @Override // amazon.communication.ServiceConnectivityListener
                            public void onServiceConnected() {
                                CommunicationFactoryBase.log.info("onServiceConnected", "Connected to TCommService", new Object[0]);
                            }

                            @Override // amazon.communication.ServiceConnectivityListener
                            public void onServiceDisconnected() {
                                CommunicationFactoryBase.log.info(MetricsConstants.CLIENT_CONNECTION_ON_SERVICE_DISCONNECTED, "Disconnected from TCommService, probably because it was killed", new Object[0]);
                                CommunicationFactoryBase.sCommunicationManager.binderDied();
                            }
                        });
                    }
                    androidTCommManager = sCommunicationManager;
                } else {
                    throw new IllegalArgumentException("Context must not be null");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return androidTCommManager;
    }

    public static IDeviceToDeviceCommunicationManager getDeviceToDeviceCommunicationManager(Context context, String str) throws TCommServiceDownException {
        IDeviceToDeviceCommunicationManager iDeviceToDeviceCommunicationManager;
        synchronized (CommunicationFactoryBase.class) {
            try {
                if (context != null) {
                    if (sD2dCommunicationManager == null) {
                        AndroidDeviceToDeviceCommunicationManager androidDeviceToDeviceCommunicationManager = new AndroidDeviceToDeviceCommunicationManager(context.getApplicationContext(), str, new HexStreamCodec());
                        androidDeviceToDeviceCommunicationManager.setCommunicationManager(getCommunicationManager(context));
                        sD2dCommunicationManager = androidDeviceToDeviceCommunicationManager;
                    }
                    iDeviceToDeviceCommunicationManager = sD2dCommunicationManager;
                } else {
                    throw new IllegalArgumentException("Context must not be null");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return iDeviceToDeviceCommunicationManager;
    }

    public static GatewayConnectivity getGatewayConnectivity(Context context) throws TCommServiceDownException {
        GatewayConnectivity gatewayConnectivity;
        synchronized (CommunicationFactoryBase.class) {
            try {
                if (context != null) {
                    if (sCommunicationManager == null) {
                        getCommunicationManager(context);
                        FailFast.expectNotNull(sCommunicationManager, "Unable to get ICommunicationManager");
                    }
                    gatewayConnectivity = sCommunicationManager.getGatewayConnectivity();
                } else {
                    throw new IllegalArgumentException("Context must not be null");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return gatewayConnectivity;
    }

    public static IdentityResolver getIdentityResolver(Context context) {
        IdentityResolver identityResolver;
        synchronized (CommunicationFactoryBase.class) {
            try {
                if (context != null) {
                    if (sIdentityResolver == null) {
                        sIdentityResolver = new AndroidIdentityResolver(context.getApplicationContext());
                    }
                    identityResolver = sIdentityResolver;
                } else {
                    throw new IllegalArgumentException("Context must not be null");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return identityResolver;
    }

    @Deprecated
    public static RequestSigner getRequestSigner(Context context, String str) {
        RequestSigner requestSigner;
        synchronized (CommunicationFactoryBase.class) {
            try {
                if (context != null) {
                    requestSigner = getRequestSigner(context, (AuthenticationType) Enum.valueOf(AuthenticationType.class, str));
                } else {
                    throw new IllegalArgumentException("Context must not be null");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return requestSigner;
    }

    private static RequestSigner getRequestSigner(Context context, AuthenticationType authenticationType) {
        int ordinal = authenticationType.ordinal();
        if (ordinal == 2) {
            if (sDcpRequestSigner == null) {
                sDcpRequestSigner = new DcpRequestSigner(context);
            }
            return sDcpRequestSigner;
        } else if (ordinal == 3) {
            if (sDcpOAuthRequestSigner == null) {
                sDcpOAuthRequestSigner = new DcpOAuthRequestSigner(context);
            }
            return sDcpOAuthRequestSigner;
        } else {
            throw new IllegalArgumentException("Unrecognized authentication type " + authenticationType);
        }
    }
}
