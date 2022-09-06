package amazon.speech.simclient.settings;

import amazon.speech.simclient.common.AsyncServiceConnectionManager;
import amazon.speech.simclient.common.BaseClient;
import amazon.speech.simclient.common.HandlerWrapper;
import amazon.speech.simclient.common.InvocationStatus;
import amazon.speech.simclient.common.ServiceConnectionManager;
import amazon.speech.simclient.settings.ISettingsCallback;
import amazon.speech.simclient.settings.ISimClientSettingsServer;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class SettingsClient extends BaseClient<ISimClientSettingsServer> implements ISettingsClient {
    static final Intent BIND_INTENT = new Intent(SettingsBinding.BIND_ACTION);
    public static final SettingsClientHandle HANDLE = new SettingsClientHandle();
    private static final String TAG = "SettingsClient";
    private final Binder mBinder;
    final RemoteSettingsCallbackDispatcher mDispatcher;
    private final HandlerWrapper mHandler;

    /* loaded from: classes.dex */
    private class DispatchRunnable implements Runnable {
        private final Set<SettingsStatusCallback> mCallbacks;
        private final SettingsData mData;

        DispatchRunnable(SettingsData settingsData, Set<SettingsStatusCallback> set) {
            if (settingsData != null) {
                if (set != null) {
                    this.mData = settingsData;
                    this.mCallbacks = set;
                    return;
                }
                throw new IllegalArgumentException("callbacks is null");
            }
            throw new IllegalArgumentException("data is null");
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mData.getName();
            for (SettingsStatusCallback settingsStatusCallback : this.mCallbacks) {
                settingsStatusCallback.onResult(this.mData);
            }
        }
    }

    /* loaded from: classes.dex */
    class RemoteSettingsCallbackDispatcher extends ISettingsCallback.Stub {
        private Map<String, Set<SettingsStatusCallback>> mCallbacks = new HashMap();

        RemoteSettingsCallbackDispatcher() {
        }

        private boolean disableSetting(String str) {
            GeneratedOutlineSupport1.outline163("Disabling ", str, SettingsClient.TAG);
            try {
                ((ISimClientSettingsServer) SettingsClient.this.getService()).unregisterCallback2(str, SettingsClient.this.mBinder);
                return true;
            } catch (RemoteException unused) {
                Log.e(SettingsClient.TAG, "RemoteException unregistering callback2");
                return false;
            } catch (Throwable unused2) {
                Log.w(SettingsClient.TAG, "registerCallback2 method not found. Fallback to registerCallback.");
                try {
                    ((ISimClientSettingsServer) SettingsClient.this.getService()).unregisterCallback(str, this);
                    return true;
                } catch (RemoteException unused3) {
                    Log.e(SettingsClient.TAG, "RemoteException unregistering callback");
                    return false;
                }
            }
        }

        private InvocationStatus enableSetting(String str) {
            GeneratedOutlineSupport1.outline163("Enabling ", str, SettingsClient.TAG);
            try {
                ((ISimClientSettingsServer) SettingsClient.this.getService()).registerCallback2(str, this, SettingsClient.this.mBinder);
                return InvocationStatus.SUCCESS;
            } catch (RemoteException unused) {
                Log.e(SettingsClient.TAG, "RemoteException registering callback2");
                return InvocationStatus.REMOTE_EXCEPTION;
            } catch (Throwable unused2) {
                Log.w(SettingsClient.TAG, "registerCallback2 method not found. Fallback to registerCallback.");
                try {
                    ((ISimClientSettingsServer) SettingsClient.this.getService()).registerCallback(str, this);
                    return InvocationStatus.SUCCESS;
                } catch (RemoteException unused3) {
                    Log.e(SettingsClient.TAG, "RemoteException registering callback");
                    return InvocationStatus.REMOTE_EXCEPTION;
                }
            }
        }

        private InvocationStatus querySetting(String str) {
            GeneratedOutlineSupport1.outline163("Querying ", str, SettingsClient.TAG);
            try {
                ((ISimClientSettingsServer) SettingsClient.this.getService()).querySetting(str, this);
                return InvocationStatus.SUCCESS;
            } catch (RemoteException unused) {
                Log.e(SettingsClient.TAG, "RemoteException querying setting");
                return InvocationStatus.REMOTE_EXCEPTION;
            }
        }

        @Override // amazon.speech.simclient.settings.ISettingsCallback
        public synchronized void onResult(SettingsData settingsData) {
            String name = settingsData.getName();
            Set<SettingsStatusCallback> set = this.mCallbacks.get(name);
            if (set == null) {
                String str = SettingsClient.TAG;
                Log.i(str, "No callback registered for " + name);
                return;
            }
            for (SettingsStatusCallback settingsStatusCallback : set) {
                settingsStatusCallback.onResult(settingsData);
            }
        }

        public synchronized void reEstablishCallbacks() {
            Log.i(SettingsClient.TAG, String.format("Re-establishing (%d) callbacks", Integer.valueOf(this.mCallbacks.size())));
            if (this.mCallbacks.isEmpty()) {
                return;
            }
            for (String str : this.mCallbacks.keySet()) {
                enableSetting(str);
            }
        }

        public synchronized InvocationStatus register(String str, SettingsStatusCallback settingsStatusCallback, boolean z) {
            Set<SettingsStatusCallback> set = this.mCallbacks.get(str);
            if (set == null) {
                InvocationStatus enableSetting = enableSetting(str);
                if (enableSetting != InvocationStatus.SUCCESS) {
                    return enableSetting;
                }
                set = new HashSet<>();
                this.mCallbacks.put(str, set);
            }
            set.add(settingsStatusCallback);
            if (z) {
                return querySetting(str);
            }
            return InvocationStatus.SUCCESS;
        }

        public synchronized void teardown() {
            for (String str : this.mCallbacks.keySet()) {
                Set<SettingsStatusCallback> set = this.mCallbacks.get(str);
                if (set != null) {
                    set.clear();
                    disableSetting(str);
                }
            }
        }

        public synchronized boolean unregister(String str, SettingsStatusCallback settingsStatusCallback) {
            Set<SettingsStatusCallback> set = this.mCallbacks.get(str);
            if (set == null) {
                return false;
            }
            boolean remove = set.remove(settingsStatusCallback);
            if (remove && set.isEmpty()) {
                remove &= disableSetting(str);
                this.mCallbacks.remove(str);
            }
            return remove;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public class ServiceConnectionListener extends ServiceConnectionManager.Callback {
        private boolean mLostConnection = false;

        protected ServiceConnectionListener() {
        }

        @Override // amazon.speech.simclient.common.ServiceConnectionManager.Callback
        public synchronized void onServiceConnected() {
            String unused = SettingsClient.TAG;
            String.format("onServiceConnected reconnect (%s)", Boolean.valueOf(this.mLostConnection));
            if (this.mLostConnection) {
                SettingsClient.this.mDispatcher.reEstablishCallbacks();
            }
            this.mLostConnection = false;
        }

        @Override // amazon.speech.simclient.common.ServiceConnectionManager.Callback
        public synchronized void onServiceDisconnected() {
            String unused = SettingsClient.TAG;
            this.mLostConnection = true;
        }
    }

    public SettingsClient(Context context) {
        this(context, new HandlerWrapper(new Handler(Looper.getMainLooper())), new AsyncServiceConnectionManager());
    }

    private void attachServiceConnectionListener() {
        getServiceConnectionManager().addCallback(getServiceConnectionListener());
    }

    public static boolean isSettingsServiceAvailable(Context context) {
        return BaseClient.serviceExists(context, BIND_INTENT, "amazon.speech.permission.SEND_DATA_TO_ALEXA");
    }

    @Override // amazon.speech.simclient.common.BaseClient
    protected BaseClient<ISimClientSettingsServer>.BaseClientServiceConnection createServiceConnection() {
        return new BaseClient<ISimClientSettingsServer>.BaseClientServiceConnection() { // from class: amazon.speech.simclient.settings.SettingsClient.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // amazon.speech.simclient.common.BaseClient.BaseClientServiceConnection
            /* renamed from: convertIBinderToService */
            public ISimClientSettingsServer mo49convertIBinderToService(IBinder iBinder) {
                return ISimClientSettingsServer.Stub.asInterface(iBinder);
            }
        };
    }

    protected ServiceConnectionManager.Callback getServiceConnectionListener() {
        return new ServiceConnectionListener();
    }

    @Override // amazon.speech.simclient.settings.ISettingsClient
    public InvocationStatus registerCallback(String str, SettingsStatusCallback settingsStatusCallback, boolean z) {
        if (str != null) {
            if (settingsStatusCallback != null) {
                return this.mDispatcher.register(str, settingsStatusCallback, z);
            }
            throw new IllegalArgumentException("callback is null");
        }
        throw new IllegalArgumentException("setting is null");
    }

    @Override // amazon.speech.simclient.common.BaseClient, amazon.speech.simclient.settings.ISettingsClient
    public synchronized void teardown() {
        try {
            this.mDispatcher.teardown();
        } catch (RuntimeException e) {
            Log.e(TAG, "Teardown failed: ", e);
        }
        super.teardown();
    }

    @Override // amazon.speech.simclient.settings.ISettingsClient
    public boolean unregisterCallback(String str, SettingsStatusCallback settingsStatusCallback) {
        if (str != null) {
            if (settingsStatusCallback != null) {
                return this.mDispatcher.unregister(str, settingsStatusCallback);
            }
            throw new IllegalArgumentException("callback is null");
        }
        throw new IllegalArgumentException("setting is null");
    }

    SettingsClient(Context context, HandlerWrapper handlerWrapper, ServiceConnectionManager serviceConnectionManager) {
        this(context, BIND_INTENT, "amazon.speech.permission.SEND_DATA_TO_ALEXA", handlerWrapper, serviceConnectionManager, new Binder());
    }

    SettingsClient(Context context, Intent intent, String str, HandlerWrapper handlerWrapper, ServiceConnectionManager serviceConnectionManager, Binder binder) {
        super(ISimClientSettingsServer.class, context, intent, str, serviceConnectionManager);
        this.mHandler = handlerWrapper;
        this.mBinder = binder;
        this.mDispatcher = new RemoteSettingsCallbackDispatcher();
        attachServiceConnectionListener();
    }
}
