package amazon.speech.simclient.capability;

import amazon.speech.simclient.capability.ICapabilityPublishCallback;
import amazon.speech.simclient.capability.ICapabilityQueryCallback;
import amazon.speech.simclient.capability.ISimClientCapabilityServer;
import amazon.speech.simclient.common.AsyncServiceConnectionManager;
import amazon.speech.simclient.common.BaseClient;
import amazon.speech.simclient.common.HandlerWrapper;
import amazon.speech.simclient.common.ServiceConnectionManager;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
/* loaded from: classes.dex */
public class CapabilityClient extends BaseClient<ISimClientCapabilityServer> {
    static final Intent BIND_INTENT = new Intent(ImplicitBinding.BIND_ACTION);
    public static final CapabilityClientHandle HANDLE = new CapabilityClientHandle();
    private static final String TAG = "CapabilityClient";
    private final Binder mBinder;
    private final HandlerWrapper mHandler;
    private final String mPackageName;

    public CapabilityClient(Context context) {
        this(context, BIND_INTENT, "amazon.speech.permission.SEND_DATA_TO_ALEXA", new HandlerWrapper(new Handler(Looper.getMainLooper())), new AsyncServiceConnectionManager(), new Binder(), context.getPackageName());
    }

    private static String convert(InputStream inputStream, Charset charset) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset));
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    bufferedReader.close();
                    return sb.toString();
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
    }

    public static boolean isCapabilityServiceAvailable(Context context) {
        return BaseClient.serviceExists(context, BIND_INTENT, "amazon.speech.permission.SEND_DATA_TO_ALEXA");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postResult(final CapabilityQueryCallback capabilityQueryCallback, final boolean z, final String str) {
        if (capabilityQueryCallback != null) {
            this.mHandler.post(new Runnable() { // from class: amazon.speech.simclient.capability.CapabilityClient.4
                @Override // java.lang.Runnable
                public void run() {
                    capabilityQueryCallback.onResult(z, str);
                }
            });
            return;
        }
        throw new IllegalArgumentException("QueryCallback cannot be null");
    }

    private String validateAndAmend(String str) {
        Log.i(TAG, String.format("validateAndAmend (%s)", str));
        try {
            if (!CapabilityClient.class.isAssignableFrom(Class.forName(str))) {
                return str.contains(String.format("%s/", this.mPackageName)) ? str : String.format("%s/%s", this.mPackageName, str);
            }
            Log.e(TAG, "Supply class using the client, not the client reference");
            return null;
        } catch (ClassNotFoundException | LinkageError e) {
            Log.e(TAG, "Incorrect class reference", e);
            return null;
        }
    }

    ICapabilityQueryCallback createICapabilityCallback(final CapabilityQueryCallback capabilityQueryCallback) {
        if (capabilityQueryCallback != null) {
            return new ICapabilityQueryCallback.Stub() { // from class: amazon.speech.simclient.capability.CapabilityClient.1
                @Override // amazon.speech.simclient.capability.ICapabilityQueryCallback
                public void onResult(String str) throws RemoteException {
                    CapabilityClient.this.postResult(capabilityQueryCallback, true, str);
                }
            };
        }
        throw new IllegalArgumentException("QueryCallback cannot be null");
    }

    ICapabilityPublishCallback createICapabilityPublishCallback(final CapabilityPublishCallback capabilityPublishCallback) {
        if (capabilityPublishCallback != null) {
            return new ICapabilityPublishCallback.Stub() { // from class: amazon.speech.simclient.capability.CapabilityClient.2
                @Override // amazon.speech.simclient.capability.ICapabilityPublishCallback
                public void onResult(String str) throws RemoteException {
                    PublishResult publishResult = PublishResult.ERROR_SERVER_ERROR;
                    try {
                        publishResult = PublishResult.valueOf(str);
                    } catch (IllegalArgumentException e) {
                        Log.e(CapabilityClient.TAG, String.format("Unable to map returnCode (%s) to PubilishResult; defaulting to (%s)", str, publishResult), e);
                    }
                    CapabilityClient.this.postResult(capabilityPublishCallback, publishResult);
                }
            };
        }
        throw new IllegalArgumentException("PublishCallback cannot be null");
    }

    @Override // amazon.speech.simclient.common.BaseClient
    protected BaseClient<ISimClientCapabilityServer>.BaseClientServiceConnection createServiceConnection() {
        return new BaseClient<ISimClientCapabilityServer>.BaseClientServiceConnection() { // from class: amazon.speech.simclient.capability.CapabilityClient.3
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // amazon.speech.simclient.common.BaseClient.BaseClientServiceConnection
            /* renamed from: convertIBinderToService */
            public ISimClientCapabilityServer mo49convertIBinderToService(IBinder iBinder) {
                return ISimClientCapabilityServer.Stub.asInterface(iBinder);
            }
        };
    }

    public void getInterfaceVersion(String str, CapabilityQueryCallback capabilityQueryCallback) {
        if (str != null) {
            try {
                getService().getInterfaceVersion(str, createICapabilityCallback(capabilityQueryCallback));
                return;
            } catch (RemoteException unused) {
                GeneratedOutlineSupport1.outline162("RemoteException getting interface version (callback) ", str, TAG);
                postResult(capabilityQueryCallback, false, CapabilityQueryConstants.BINDER_ERROR);
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    public void publishDeviceCapabilities(String str, CapabilityPublishCallback capabilityPublishCallback) {
        if (capabilityPublishCallback != null) {
            String validateAndAmend = validateAndAmend(str);
            if (validateAndAmend == null) {
                Log.e(TAG, String.format("Client reference incorrect (%s)", str));
                postResult(capabilityPublishCallback, PublishResult.ERROR_CLIENT_REFERENCE);
                return;
            }
            try {
                getService().attemptCapabilityPublish(validateAndAmend, createICapabilityPublishCallback(capabilityPublishCallback));
                return;
            } catch (RemoteException unused) {
                Log.e(TAG, String.format("RemoteException scheduling publish attempt for client (%s)", str));
                postResult(capabilityPublishCallback, PublishResult.ERROR_REMOTE_EXCEPTION);
                return;
            }
        }
        throw new IllegalArgumentException("cannot pass a null callback");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postResult(final CapabilityPublishCallback capabilityPublishCallback, final PublishResult publishResult) {
        if (capabilityPublishCallback != null) {
            this.mHandler.post(new Runnable() { // from class: amazon.speech.simclient.capability.CapabilityClient.5
                @Override // java.lang.Runnable
                public void run() {
                    capabilityPublishCallback.onResult(publishResult);
                }
            });
            return;
        }
        throw new IllegalArgumentException("PublishCallback cannot be null");
    }

    CapabilityClient(Context context, Intent intent, String str, HandlerWrapper handlerWrapper, ServiceConnectionManager serviceConnectionManager, Binder binder, String str2) {
        super(ISimClientCapabilityServer.class, context, intent, str, serviceConnectionManager);
        this.mHandler = handlerWrapper;
        this.mBinder = binder;
        this.mPackageName = str2;
    }

    public void publishDeviceCapabilities(String str, String str2, CapabilityPublishCallback capabilityPublishCallback) {
        if (!TextUtils.isEmpty(str) && str != null) {
            if (TextUtils.isEmpty(str2) || str2 == null) {
                throw new IllegalArgumentException("payload must be non-empty & non-null");
            }
            if (capabilityPublishCallback != null) {
                String validateAndAmend = validateAndAmend(str);
                if (validateAndAmend == null) {
                    Log.e(TAG, String.format("Client reference incorrect (%s)", str));
                    postResult(capabilityPublishCallback, PublishResult.ERROR_CLIENT_REFERENCE);
                    return;
                }
                try {
                    getService().publishDeviceCapabilities(validateAndAmend, str2, createICapabilityPublishCallback(capabilityPublishCallback));
                    return;
                } catch (RemoteException unused) {
                    Log.e(TAG, String.format("RemoteException publishing capabilities (%s) for client (%s)", str2, str));
                    postResult(capabilityPublishCallback, PublishResult.ERROR_REMOTE_EXCEPTION);
                    return;
                }
            }
            throw new IllegalArgumentException("cannot pass a null callback");
        }
        throw new IllegalArgumentException("client qualifier must be non-empty & non-null");
    }

    public void publishDeviceCapabilities(String str, InputStream inputStream, CapabilityPublishCallback capabilityPublishCallback) throws IOException {
        if (inputStream != null) {
            publishDeviceCapabilities(str, convert(inputStream, StandardCharsets.UTF_8), capabilityPublishCallback);
            return;
        }
        throw new IllegalArgumentException("cannot pass a null InputStream");
    }
}
