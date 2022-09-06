package amazon.speech.simclient.genericconnection;

import amazon.speech.requestid.RequestIdGenerator;
import amazon.speech.simclient.common.AsyncServiceConnectionManager;
import amazon.speech.simclient.common.BaseClient;
import amazon.speech.simclient.common.HandlerWrapper;
import amazon.speech.simclient.common.InvocationStatus;
import amazon.speech.simclient.common.ServiceConnectionManager;
import amazon.speech.simclient.genericconnection.IDownstreamMessageCallback;
import amazon.speech.simclient.genericconnection.IGenericConnectionServer;
import amazon.speech.simclient.genericconnection.IGenericConnectionStatusCallback;
import amazon.speech.simclient.genericconnection.IServiceSupportedCallback;
import amazon.speech.simclient.genericconnection.IUpstreamMessageResultCallback;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class GenericConnectionClient extends BaseClient<IGenericConnectionServer> {
    static final Intent BIND_INTENT = new Intent(ImplicitBinding.BIND_ACTION);
    private static final String ID_PREFIX = "TComm";
    private static final String TAG = "GenericConnectionClient";
    private final Binder mBinder;
    private IGenericConnectionStatusCallback mConnectionStatusCallback;
    private IDownstreamMessageCallback mDownstreamMessageCallback;
    private GenericConnectionBinderDeathListener mGenericConnectionBinderDeathListener;
    private final HandlerWrapper mHandler;
    private final IBinder.DeathRecipient mSimDeathRecipient;

    public GenericConnectionClient(Context context) {
        this(context, new HandlerWrapper(new Handler(Looper.getMainLooper())), new AsyncServiceConnectionManager());
    }

    public static boolean isGenericConnectionServiceAvailable(Context context) {
        return BaseClient.serviceExists(context, BIND_INTENT, ImplicitBinding.PERMISSION);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postMessage(final DownstreamMessageCallback downstreamMessageCallback, final byte[] bArr) {
        if (downstreamMessageCallback == null) {
            return;
        }
        this.mHandler.post(new Runnable() { // from class: amazon.speech.simclient.genericconnection.GenericConnectionClient.8
            @Override // java.lang.Runnable
            public void run() {
                downstreamMessageCallback.onMessage(bArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postResult(final UpstreamMessageCallback upstreamMessageCallback, final UpstreamMessageResult upstreamMessageResult) {
        if (upstreamMessageCallback == null) {
            return;
        }
        this.mHandler.post(new Runnable() { // from class: amazon.speech.simclient.genericconnection.GenericConnectionClient.3
            @Override // java.lang.Runnable
            public void run() {
                upstreamMessageCallback.onResult(upstreamMessageResult);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postStatus(final GenericConnectionStatusCallback genericConnectionStatusCallback, final ConnectionResult connectionResult) {
        if (genericConnectionStatusCallback == null) {
            return;
        }
        this.mHandler.post(new Runnable() { // from class: amazon.speech.simclient.genericconnection.GenericConnectionClient.6
            @Override // java.lang.Runnable
            public void run() {
                genericConnectionStatusCallback.onResult(connectionResult);
            }
        });
    }

    private void setupServiceConnectionCallbacks() {
        getServiceConnectionManager().addCallback(new ServiceConnectionManager.Callback() { // from class: amazon.speech.simclient.genericconnection.GenericConnectionClient.1
            @Override // amazon.speech.simclient.common.ServiceConnectionManager.Callback
            public void onServiceConnected() {
                try {
                    if (GenericConnectionClient.this.mConnectionStatusCallback != null) {
                        ((IGenericConnectionServer) GenericConnectionClient.this.getService()).registerConnectionStatusCallback(GenericConnectionClient.this.mConnectionStatusCallback, GenericConnectionClient.this.mBinder);
                    }
                } catch (RemoteException unused) {
                    Log.e(GenericConnectionClient.TAG, "RemoteException registering connection status callback");
                }
                try {
                    if (GenericConnectionClient.this.mDownstreamMessageCallback == null) {
                        return;
                    }
                    ((IGenericConnectionServer) GenericConnectionClient.this.getService()).registerDownstreamMessageCallback(GenericConnectionClient.this.mDownstreamMessageCallback, GenericConnectionClient.this.mBinder);
                } catch (RemoteException unused2) {
                    Log.e(GenericConnectionClient.TAG, "RemoteException registering downstream message callback");
                }
            }

            @Override // amazon.speech.simclient.common.ServiceConnectionManager.Callback
            public void onServiceDisconnected() {
            }
        });
    }

    IDownstreamMessageCallback createIDownstreamMessageCallback(final DownstreamMessageCallback downstreamMessageCallback) {
        if (downstreamMessageCallback == null) {
            return null;
        }
        return new IDownstreamMessageCallback.Stub() { // from class: amazon.speech.simclient.genericconnection.GenericConnectionClient.7
            @Override // amazon.speech.simclient.genericconnection.IDownstreamMessageCallback
            public void onMessage(byte[] bArr) throws RemoteException {
                GenericConnectionClient.this.postMessage(downstreamMessageCallback, bArr);
            }
        };
    }

    IGenericConnectionStatusCallback createIGenericConnectionStatusCallback(final GenericConnectionStatusCallback genericConnectionStatusCallback) {
        if (genericConnectionStatusCallback == null) {
            return null;
        }
        return new IGenericConnectionStatusCallback.Stub() { // from class: amazon.speech.simclient.genericconnection.GenericConnectionClient.4
            @Override // amazon.speech.simclient.genericconnection.IGenericConnectionStatusCallback
            public void onResult(String str) throws RemoteException {
                ConnectionResult connectionResult = ConnectionResult.TCOMM_DISCONNECTED;
                try {
                    connectionResult = ConnectionResult.valueOf(str);
                } catch (Exception unused) {
                    GeneratedOutlineSupport1.outline162("Exception converting result to enum ", str, GenericConnectionClient.TAG);
                }
                GenericConnectionClient.this.postStatus(genericConnectionStatusCallback, connectionResult);
            }
        };
    }

    IServiceSupportedCallback createIServiceSupportedCallback(final ServiceSupportedCallback serviceSupportedCallback) {
        if (serviceSupportedCallback == null) {
            return null;
        }
        return new IServiceSupportedCallback.Stub() { // from class: amazon.speech.simclient.genericconnection.GenericConnectionClient.5
            @Override // amazon.speech.simclient.genericconnection.IServiceSupportedCallback
            public void onResult(final boolean z) throws RemoteException {
                GenericConnectionClient.this.mHandler.post(new Runnable() { // from class: amazon.speech.simclient.genericconnection.GenericConnectionClient.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        serviceSupportedCallback.onResult(z);
                    }
                });
            }
        };
    }

    IUpstreamMessageResultCallback createIUpstreamMessageResultCallback(final UpstreamMessageCallback upstreamMessageCallback) {
        if (upstreamMessageCallback == null) {
            return null;
        }
        return new IUpstreamMessageResultCallback.Stub() { // from class: amazon.speech.simclient.genericconnection.GenericConnectionClient.2
            @Override // amazon.speech.simclient.genericconnection.IUpstreamMessageResultCallback
            public void onResult(String str) throws RemoteException {
                UpstreamMessageResult upstreamMessageResult = UpstreamMessageResult.UNKNOWN_ERROR;
                try {
                    upstreamMessageResult = UpstreamMessageResult.valueOf(str);
                } catch (Exception unused) {
                    GeneratedOutlineSupport1.outline162("Exception converting result to enum ", str, GenericConnectionClient.TAG);
                }
                GenericConnectionClient.this.postResult(upstreamMessageCallback, upstreamMessageResult);
            }
        };
    }

    @Override // amazon.speech.simclient.common.BaseClient
    protected BaseClient<IGenericConnectionServer>.BaseClientServiceConnection createServiceConnection() {
        return new BaseClient<IGenericConnectionServer>.BaseClientServiceConnection() { // from class: amazon.speech.simclient.genericconnection.GenericConnectionClient.9
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // amazon.speech.simclient.common.BaseClient.BaseClientServiceConnection
            /* renamed from: convertIBinderToService */
            public IGenericConnectionServer mo49convertIBinderToService(IBinder iBinder) {
                try {
                    iBinder.linkToDeath(GenericConnectionClient.this.mSimDeathRecipient, 0);
                } catch (RemoteException e) {
                    Log.e(GenericConnectionClient.TAG, "RemoteException e", e);
                }
                return IGenericConnectionServer.Stub.asInterface(iBinder);
            }
        };
    }

    public void queryConnectionStatus(GenericConnectionStatusCallback genericConnectionStatusCallback) {
        try {
            getService().queryConnectionStatus(createIGenericConnectionStatusCallback(genericConnectionStatusCallback));
        } catch (RemoteException unused) {
            Log.e(TAG, "RemoteException sending message");
            postStatus(genericConnectionStatusCallback, ConnectionResult.CONNECTION_EXCEPTION);
        }
    }

    public InvocationStatus queryServiceSupported(ServiceSupportedCallback serviceSupportedCallback) {
        try {
            getService().queryServiceSupported(createIServiceSupportedCallback(serviceSupportedCallback));
            return InvocationStatus.SUCCESS;
        } catch (RemoteException unused) {
            Log.e(TAG, "RemoteException query service supported");
            return InvocationStatus.REMOTE_EXCEPTION;
        }
    }

    public void registerConnectionStatusCallback(GenericConnectionStatusCallback genericConnectionStatusCallback) {
        this.mConnectionStatusCallback = createIGenericConnectionStatusCallback(genericConnectionStatusCallback);
        try {
            getService().registerConnectionStatusCallback(this.mConnectionStatusCallback, this.mBinder);
        } catch (RemoteException unused) {
            Log.e(TAG, "RemoteException registering connection status callback");
            postStatus(genericConnectionStatusCallback, ConnectionResult.CONNECTION_EXCEPTION);
        }
    }

    public InvocationStatus registerDownstreamMessageCallback(DownstreamMessageCallback downstreamMessageCallback) {
        this.mDownstreamMessageCallback = createIDownstreamMessageCallback(downstreamMessageCallback);
        try {
            getService().registerDownstreamMessageCallback(this.mDownstreamMessageCallback, this.mBinder);
            return InvocationStatus.SUCCESS;
        } catch (RemoteException unused) {
            Log.e(TAG, "RemoteException register downstream message callback");
            return InvocationStatus.REMOTE_EXCEPTION;
        }
    }

    public void registerGenericConnectionBinderDeathListener(GenericConnectionBinderDeathListener genericConnectionBinderDeathListener) {
        this.mGenericConnectionBinderDeathListener = genericConnectionBinderDeathListener;
    }

    public void sendMessage(byte[] bArr) {
        sendMessage(bArr, null);
    }

    public InvocationStatus unregisterConnectionStatusCallback() {
        this.mConnectionStatusCallback = null;
        try {
            getService().unregisterConnectionStatusCallback(this.mBinder);
            return InvocationStatus.SUCCESS;
        } catch (RemoteException unused) {
            Log.e(TAG, "RemoteException unregisterConnectionStatusCallback");
            return InvocationStatus.REMOTE_EXCEPTION;
        }
    }

    public InvocationStatus unregisterDownstreamMessageCallback() {
        this.mDownstreamMessageCallback = null;
        try {
            getService().unregisterDownstreamMessageCallback(this.mBinder);
            return InvocationStatus.SUCCESS;
        } catch (RemoteException unused) {
            Log.e(TAG, "RemoteException unregisterDownstreamMessage");
            return InvocationStatus.REMOTE_EXCEPTION;
        }
    }

    GenericConnectionClient(Context context, HandlerWrapper handlerWrapper, ServiceConnectionManager serviceConnectionManager) {
        super(IGenericConnectionServer.class, context, BIND_INTENT, ImplicitBinding.PERMISSION, serviceConnectionManager);
        this.mGenericConnectionBinderDeathListener = null;
        this.mSimDeathRecipient = new IBinder.DeathRecipient() { // from class: amazon.speech.simclient.genericconnection.GenericConnectionClient.10
            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                Log.i(GenericConnectionClient.TAG, "Server binder died");
                if (GenericConnectionClient.this.mGenericConnectionBinderDeathListener != null) {
                    GenericConnectionClient.this.mGenericConnectionBinderDeathListener.onBinderDied();
                }
            }
        };
        this.mHandler = handlerWrapper;
        this.mBinder = new Binder();
        setupServiceConnectionCallbacks();
    }

    public void sendMessage(byte[] bArr, UpstreamMessageCallback upstreamMessageCallback) {
        sendMessage(bArr, false, upstreamMessageCallback);
    }

    public void sendMessage(byte[] bArr, boolean z, UpstreamMessageCallback upstreamMessageCallback) {
        if (bArr != null && bArr.length != 0) {
            String generateRequestId = RequestIdGenerator.generateRequestId(null, "TComm");
            try {
                getService().sendMessage(bArr, generateRequestId, z, createIUpstreamMessageResultCallback(upstreamMessageCallback));
                return;
            } catch (RemoteException unused) {
                Log.e(TAG, "RemoteException sending message");
                postResult(upstreamMessageCallback, UpstreamMessageResult.REMOTE_EXCEPTION);
                return;
            }
        }
        Log.e(TAG, "Empty message");
        postResult(upstreamMessageCallback, UpstreamMessageResult.INVALID_MESSAGE);
    }
}
